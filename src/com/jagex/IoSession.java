package com.jagex;
/* Class34 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IoSession implements Runnable {
    public OutputStream output_stream;
    public boolean write_error_occurred = false;
    static RSString aClass16_528;
    static RSString aClass16_529;
    public SignlinkRequest request;
    static int anInt531 = 0;
    public int write_buffer_in_ptr;
    public Socket socket;
    public boolean shutdown = false;
    static RSString aClass16_539;
    static RSString aClass16_540 = RSString.createString("; Expires=");
    public static RSString aClass16_541;
    public int write_buffer_out_ptr = 0;
    public SignLink signlink;
    public byte[] write_buffer;
    public InputStream input_stream;
    public int write_buffer_size;

    public static final void method970(byte b, int i, int i_0_, int i_1_, int i_2_) {
        for (ObjectNode class23_sub8 = (ObjectNode) Js5.aClass89_1767.get_first(); class23_sub8 != null; class23_sub8 = (ObjectNode) Js5.aClass89_1767.get_next()) {
            if ((class23_sub8.anInt2227 ^ 0xffffffff) != 0 || class23_sub8.anIntArray2239 != null) {
                int i_4_ = 0;
                if (i_0_ > class23_sub8.anInt2254) {
                    i_4_ += i_0_ + -class23_sub8.anInt2254;
                } else if (i_0_ < class23_sub8.anInt2246) {
                    i_4_ += class23_sub8.anInt2246 - i_0_;
                }
                if ((class23_sub8.anInt2229 ^ 0xffffffff) > (i_1_ ^ 0xffffffff)) {
                    i_4_ += i_1_ - class23_sub8.anInt2229;
                } else if (i_1_ < class23_sub8.anInt2240) {
                    i_4_ += class23_sub8.anInt2240 - i_1_;
                }
                if (class23_sub8.anInt2233 < -64 + i_4_ || (TimeTools.soundPreference1 ^ 0xffffffff) == -1 || (i_2_ ^ 0xffffffff) != (class23_sub8.anInt2253 ^ 0xffffffff)) {
                    if (class23_sub8.aClass23_Sub10_Sub1_2228 != null) {
                        Class23_Sub7.aClass23_Sub10_Sub4_2201.method595(class23_sub8.aClass23_Sub10_Sub1_2228);
                        class23_sub8.aClass23_Sub10_Sub1_2228 = null;
                    }
                    if (class23_sub8.aClass23_Sub10_Sub1_2232 != null) {
                        Class23_Sub7.aClass23_Sub10_Sub4_2201.method595(class23_sub8.aClass23_Sub10_Sub1_2232);
                        class23_sub8.aClass23_Sub10_Sub1_2232 = null;
                    }
                } else {
                    i_4_ -= 64;
                    if ((i_4_ ^ 0xffffffff) > -1) {
                        i_4_ = 0;
                    }
                    int i_5_ = (-i_4_ + class23_sub8.anInt2233) * TimeTools.soundPreference1 / class23_sub8.anInt2233;
                    if (class23_sub8.aClass23_Sub10_Sub1_2228 == null) {
                        if (class23_sub8.anInt2227 >= 0) {
                            Sound sound = Sound.method195(CacheConstants.soundsCacheIdx, class23_sub8.anInt2227, 0);
                            if (sound != null) {
                                SomeSoundClass2 someSoundClass2 = sound.method196().method493(WallObject.aClass45_1462);
                                Class23_Sub10_Sub1 class23_sub10_sub1 = Class23_Sub10_Sub1.method510(someSoundClass2, 100, i_5_);
                                class23_sub10_sub1.method536(-1);
                                Class23_Sub7.aClass23_Sub10_Sub4_2201.method590(class23_sub10_sub1);
                                class23_sub8.aClass23_Sub10_Sub1_2228 = class23_sub10_sub1;
                            }
                        }
                    } else {
                        class23_sub8.aClass23_Sub10_Sub1_2228.method543(i_5_);
                    }
                    if (class23_sub8.aClass23_Sub10_Sub1_2232 == null) {
                        if (class23_sub8.anIntArray2239 != null && ((class23_sub8.anInt2242 -= i) ^ 0xffffffff) >= -1) {
                            int i_6_ = (int) (Math.random() * class23_sub8.anIntArray2239.length);
                            Sound sound = Sound.method195(CacheConstants.soundsCacheIdx, class23_sub8.anIntArray2239[i_6_], 0);
                            if (sound != null) {
                                SomeSoundClass2 someSoundClass2 = sound.method196().method493(WallObject.aClass45_1462);
                                Class23_Sub10_Sub1 class23_sub10_sub1 = Class23_Sub10_Sub1.method510(someSoundClass2, 100, i_5_);
                                class23_sub10_sub1.method536(0);
                                Class23_Sub7.aClass23_Sub10_Sub4_2201.method590(class23_sub10_sub1);
                                class23_sub8.anInt2242 = class23_sub8.anInt2236 + (int) ((class23_sub8.anInt2241 + -class23_sub8.anInt2236) * Math.random());
                                class23_sub8.aClass23_Sub10_Sub1_2232 = class23_sub10_sub1;
                            }
                        }
                    } else {
                        class23_sub8.aClass23_Sub10_Sub1_2232.method543(i_5_);
                        if (!class23_sub8.aClass23_Sub10_Sub1_2232.method227(-18996)) {
                            class23_sub8.aClass23_Sub10_Sub1_2232 = null;
                        }
                    }
                }
            }
        }
    }

    final int read(byte b) throws IOException {
        if (shutdown) {
            System.out.println("shutdown");
            return 0;
        }
        return input_stream.read();
    }

    public final void handle_error() throws IOException {
        if (!shutdown) {
            if (write_error_occurred) {
                write_error_occurred = false;
                throw new IOException("write error");
            }
        }
    }

    @Override
	public final void run() {
        try {
            for (; ; ) {
                int write_off;
                int write_len;
                synchronized (this) {
                    if (write_buffer_in_ptr == write_buffer_out_ptr) {
                        if (shutdown) {
                            break;
                        }
                        try {
                            this.wait();
                        } catch (InterruptedException interruptedexception) {
                            /* empty */
                        }
                    }
                    if (write_buffer_out_ptr > write_buffer_in_ptr) {
                        write_len = write_buffer_size - write_buffer_out_ptr;
                    } else {
                        write_len = write_buffer_in_ptr - write_buffer_out_ptr;
                    }
                    write_off = write_buffer_out_ptr;
                }
                if (write_len > 0) {
                    try {
                        output_stream.write(write_buffer, write_off, write_len);
                    } catch (IOException ioexception) {
                        write_error_occurred = true;
                    }
                    write_buffer_out_ptr = (write_buffer_out_ptr + write_len) % write_buffer_size;
                    try {
                        if (write_buffer_out_ptr == write_buffer_in_ptr) {
                            output_stream.flush();
                        }
                    } catch (IOException ioexception) {
                        write_error_occurred = true;
                    }
                }
            }
            try {
                if (input_stream != null) {
                    input_stream.close();
                }
                if (output_stream != null) {
                    output_stream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ioexception) {
				/* empty */
            }
            write_buffer = null;
        } catch (Exception exception) {
            ForceMovement.sendError(95, exception, null);
        }
    }

    public static void method972(byte b) {
        aClass16_539 = null;
        aClass16_540 = null;
        aClass16_528 = null;
        aClass16_541 = null;
        aClass16_529 = null;
    }

    @Override
	public final void finalize() {
        shutdown();
    }

    final int available(int i) throws IOException {
        if (shutdown) {
            return 0;
        }
        return input_stream.available();
    }

    final void read(int length, int offset, byte[] buffer) throws IOException {
        if ( !shutdown ) {
            while ( length > 0 ) {
                int read_len = input_stream.read(buffer, offset, length);
                if ( read_len <= 0 )
                    throw new EOFException();
                length -= read_len;
                offset += read_len;
            }
        }
    }

    public final void read(byte[] buffer, int offset, int length) throws IOException {
        try {
            if (!shutdown) {
                while (length > 0) {
                    int read_len = input_stream.read(buffer, offset, length);
                    if (read_len <= 0)
                        throw new EOFException();
                    length -= read_len;
                    offset += read_len;
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public final void shutdown() {
        if (!shutdown) {
            synchronized (this) {
                shutdown = true;
                this.notifyAll();
            }
            if (request != null) {
                while (request.status == 0)
                    TimeTools.sleep(1L);
                if (request.status == 1) {
                    try {
                        ((Thread) request.result).join();
                    } catch (InterruptedException interruptedexception) {
						/* empty */
                    }
                }
            }
            request = null;
        }
    }

    final void write(int length, int offset, byte[] buffer) throws IOException {
        if (!shutdown) {
            if (write_error_occurred) {
                write_error_occurred = false;
                throw new IOException("beaner 3");
            }
            if (write_buffer == null) {
                write_buffer = new byte[write_buffer_size];
            }
            synchronized (this) {
                for (int i = 0; length > i; i++) {
                    write_buffer[write_buffer_in_ptr] = buffer[offset + i];
                    write_buffer_in_ptr = (1 + write_buffer_in_ptr) % write_buffer_size;
                    if (write_buffer_in_ptr == (write_buffer_out_ptr + (write_buffer_size - 100)) % write_buffer_size) {
                        throw new IOException("beaner 4");
                    }
                }
                if (request == null) {
                    request = signlink.newRunnable(3, this, (byte) 72);
                }
                this.notifyAll();
            }
        }
    }

    IoSession(Socket socket, SignLink signLink, int i) throws IOException {
        write_buffer_in_ptr = 0;
        signlink = signLink;
        this.socket = socket;
        this.socket.setSoTimeout(50000); //30000
        this.socket.setTcpNoDelay(true);
        input_stream = this.socket.getInputStream();
        output_stream = this.socket.getOutputStream();
        write_buffer_size = i;
    }

    static {
        aClass16_528 = RSString.createString("mem=");
        aClass16_541 = RSString.createString("Unable to find ");
        aClass16_539 = RSString.createString("http:)4)4www)3matrixftw)3com)4l=");
        aClass16_529 = aClass16_541;
    }

    public OutputStream getOutputStream() {
        return output_stream;
    }
}
