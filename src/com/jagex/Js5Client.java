package com.jagex;

import java.io.IOException;

import com.jagex.js5.Js5FileRequest;

/**
 * Created by Chris on 4/15/2017.
 */
public class Js5Client {
    public int reconnect_count;
    long last_time;
    public int latency;
    Queue normal_request = new Queue();
    Queue priority_request = new Queue();
    public IoSession client_stream;
    Queue normal_sent = new Queue();
    Queue priority_sent = new Queue();
    public Js5FileRequest current_request;
    public Packet input_packet;
    public byte xorcode = 0;
    public volatile int status;

    public final void connect(IoSession class34, boolean loggedIn) {
        if (client_stream != null) {
            try {
                client_stream.shutdown();
            } catch (Exception exception) {
                /* empty */
            }
            client_stream = null;
        }
        client_stream = class34;
        send_connected();
        notify_login(0, loggedIn);
        current_request = null;
		input_packet.index = 0;
        for (; ; ) {
            Js5FileRequest bufferedRequest = (Js5FileRequest) priority_sent.remove();
            if (bufferedRequest == null) {
                break;
            }
            priority_request.add_last(bufferedRequest);
        }
        for (; ; ) {
            Js5FileRequest bufferedRequest = (Js5FileRequest) normal_sent.remove();
            if (bufferedRequest == null) {
                break;
            }
            normal_request.add_last(bufferedRequest);
        }
        if (xorcode != 0) {
            try {
                Packet class23_sub5 = new Packet(4);
                class23_sub5.p1(4);
                class23_sub5.p1(xorcode);
                class23_sub5.putShort(0);
                client_stream.write(4, 0, class23_sub5.byteBuffer);
            } catch (java.io.IOException ioexception) {
                try {
                    client_stream.shutdown();
                } catch (Exception exception) {
                    /* empty */
                }
                reconnect_count++;
                client_stream = null;
                status = -2;
                ioexception.printStackTrace();
            }
        }
        latency = 0;
        last_time = TimeTools.getMillis();
    }

    public final Js5FileRequest request_file(int cache_id, int file_id, byte extra_size, boolean priority) {
        try {
            long _file_id = ((long) cache_id << 32) + file_id;
            Js5FileRequest request = new Js5FileRequest();
            request.extra_size = extra_size;
            request.queue_id = _file_id;
            request.isPriority = priority;
            do {
                if (!priority) {
                    if (get_normal_request_count() >= 20) {
						throw new RuntimeException("Prefetch list exceeded max limit of 20");
					}
                    normal_request.add_last(request);
                    break;
                }
                if (get_priority_request_count() >= 20) {
					throw new RuntimeException("Urgent list exceeded max limit of 50");
				}
                priority_request.add_last(request);
            } while (false);
            return request;
        } catch (RuntimeException runtimeexception) {
            throw runtimeexception;
        }
    }

    public void send_connected() {
        if (client_stream != null) {
            try {
                Packet class23_sub5 = new Packet(4);
                class23_sub5.p1(6);
                class23_sub5.putTriByte(1, 3);
                client_stream.write(4, 0, class23_sub5.byteBuffer);
            } catch (IOException ioexception) {
                try {
                    client_stream.shutdown();
                } catch (Exception exception) {
		    /* empty */
                }
                client_stream = null;
                reconnect_count++;
                status = -2;
                ioexception.printStackTrace();
            }
        }
    }

    public final void enable_encryption() {
        System.out.println("here");
        try {
            client_stream.shutdown();
        } catch (Exception exception) {
	    /* empty */
        }
        reconnect_count++;
        status = -1;
        xorcode = (byte) (int) (1 + 255.0 * Math.random());
        client_stream = null;
    }

    public final boolean process() {
        if(client_stream != null && get_priority_request_count() != 0 && get_normal_request_count() != 0) {
            long current_time = TimeTools.getMillis();
            int delta_t = (int) (current_time - last_time);
            last_time = current_time;
            if (delta_t > 200) {
                delta_t = 200;
            }
            latency += delta_t;
            if (latency > 30000) {
                try {
                    System.out.println("poop: "+latency);
                    client_stream.shutdown();
                } catch (Exception exception) {
                }
                client_stream = null;
            }
        }
        if (client_stream == null) {
            return get_priority_request_count() == 0 && get_normal_request_count() == 0;
        }
        try {
            client_stream.handle_error();
            //JS5 high priority requests.
            for (Js5FileRequest request = (Js5FileRequest) priority_request.get_first();
                 request != null;
                 request = (Js5FileRequest) priority_request.get_next()) {
                Packet buffer = new Packet(6);
                buffer.p1(1);
                buffer.put32Bit(request.queue_id);
                client_stream.write(6, 0, buffer.byteBuffer);
                priority_sent.add_last(request);
            }
            //JS5 low priority requests.
            for (Js5FileRequest request = (Js5FileRequest) normal_request.get_first();
                 request != null;
                 request = (Js5FileRequest) normal_request.get_next()) {
                Packet buffer = new Packet(6);
                buffer.p1(0);
                buffer.put32Bit(request.queue_id);
                client_stream.write(6, 0, buffer.byteBuffer);
                normal_sent.add_last(request);
            }
            for (int block_ctr = 0; block_ctr < 100; block_ctr++) {
                int available = client_stream.available(-46);
                if (available < 0) {
                    throw new IOException("beaner1");
                }
                if (available == 0) {
                    break;
                }
                latency = 0;
                int required_bytes = 0;
                if (current_request == null) {
					required_bytes = 10;
				} else if (current_request.block_pos == 0) {
					required_bytes = 1;
				}
                if (required_bytes <= 0) {
                    int file_len = current_request.packet.byteBuffer.length - current_request.extra_size;
                    int read_len = 512 - current_request.block_pos;
					if (read_len > file_len - current_request.packet.index) {
						read_len = file_len - current_request.packet.index;
					}
                    if (read_len > available) {
                        read_len = available;
                    }
					client_stream.read(current_request.packet.byteBuffer, current_request.packet.index, read_len);
                    if (xorcode != 0) {
                        for (int i_12_ = 0; read_len > i_12_; i_12_++) {
							current_request.packet.byteBuffer[current_request.packet.index - -i_12_] = (byte) MathUtils.power(current_request.packet.byteBuffer[current_request.packet.index + i_12_], xorcode);
						}
                    }
					current_request.packet.index += read_len;
                    current_request.block_pos += read_len;
					if (current_request.packet.index == file_len) {
                        current_request.unlink_queue();
                        current_request.in_progress = false;
                        current_request = null;
                    } else if (current_request.block_pos == 512) {
						current_request.block_pos = 0;
					}
                } else {
					int read_len = -input_packet.index + required_bytes;
                    if (read_len > available) {
                        read_len = available;
                    }
					client_stream.read(input_packet.byteBuffer, input_packet.index, read_len);
                    if (xorcode != 0) {
                        for (int decrypt_pos = 0; read_len > decrypt_pos; decrypt_pos++) {
							input_packet.byteBuffer[input_packet.index + decrypt_pos] = (byte) MathUtils.power(input_packet.byteBuffer[input_packet.index + decrypt_pos], xorcode);
						}
                    }
					input_packet.index += read_len;
					if (required_bytes > input_packet.index) {
                        break;
                    }
                    if (current_request == null) {
						input_packet.index = 0;
                        int i_19_ = input_packet.readUnsignedByte();
                        int i_20_ = input_packet.g4();
                        int i_21_ = input_packet.readUnsignedByte(); //Settings
                        int i_23_ = input_packet.g4();
                        int compression_type = i_21_ & 0x7f;
                        boolean bool = (i_21_ & 0x80) != 0;
                        long l_22_ = ((long) i_19_ << 32) + i_20_;
                        Js5FileRequest request;
                        if (bool) {
                            for (request = (Js5FileRequest) normal_sent.get_first();
                                 request != null;
                                 request = (Js5FileRequest) normal_sent.get_next()) {
                                if (l_22_ == request.queue_id) {
									break;
								}
                            }
                        } else {
                            for (request = (Js5FileRequest) priority_sent.get_first();
                                 request != null;
                                 request = (Js5FileRequest) priority_sent.get_next()) {
                                if (l_22_ == request.queue_id) {
									break;
								}
                            }
                        }
                        if (request == null) {
							throw new IOException("Could not find cache file " + i_19_ + ", " + i_20_ + "!");
						}
                        current_request = request;
                        int header_size = compression_type != 0 ? 9 : 5;
//						System.out.println("Settings=" + i_21_ + ", length=" + (i_23_ + (i_24_ - -TextureOperation17.current_request.extra_size)));
                        current_request.packet = new Packet(i_23_ + header_size + current_request.extra_size);
                        current_request.packet.p1(compression_type);
                        current_request.packet.p4(i_23_);
                        current_request.block_pos = 10;
						input_packet.index = 0;
                    } else if (current_request.block_pos == 0) {
                        if (input_packet.byteBuffer[0] != -1) {
							current_request = null;
						} else {
                            current_request.block_pos = 1;
							input_packet.index = 0;
                        }
                    } else {
						throw new IOException("beeeeeeer");
					}
                }
            }
            return true;
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
            try {
                client_stream.shutdown();
            } catch (Exception exception) {
                /* empty */
            }
            status = -2;
            client_stream = null;
            reconnect_count++;
            return get_priority_request_count() == 0 && get_normal_request_count() == 0;
        }
    }

    public int get_normal_request_count() {
        return normal_request.size() + normal_sent.size();
    }

    public final int get_priority_request_count() {
        return priority_request.size() + priority_sent.size();
    }

    public final void notify_login(int i, boolean bool) {
        if (client_stream != null) {
            try {
                Packet class23_sub5 = new Packet(4);
                class23_sub5.p1(bool ? 2 : 3);
                class23_sub5.putTriByte(-483923896, i);
                client_stream.write(4, 0, class23_sub5.byteBuffer);
            } catch (IOException ioexception) {
                try {
                    client_stream.shutdown();
                } catch (Exception exception) {
                    /* empty */
                }
                client_stream = null;
                reconnect_count++;
                status = -2;
                ioexception.printStackTrace();
            }
        }
    }

    public final void close_connection() {
        System.out.println("closing js5 connection");
        if (client_stream != null) {
            client_stream.shutdown();
        }
    }

    public Js5Client() {
        reconnect_count = 0;
        status = 0;
        input_packet = new Packet(10);
    }

    public final boolean more_than_20_normal_requests() {
        return get_normal_request_count() >= 20;
    }

    public boolean more_than_20_priority_requests() {
        return get_priority_request_count() >= 20;
    }
}
