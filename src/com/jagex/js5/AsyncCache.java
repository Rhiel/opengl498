package com.jagex.js5;

import com.jagex.FileSystem;
import com.jagex.Queue;
import com.jagex.SignLink;
import com.jagex.SignlinkRequest;
import com.jagex.TimeTools;

/**
 * Created by Chris on 6/1/2017.
 */
public final class AsyncCache implements Runnable {
    public Queue request_queue = new Queue();
    public  int     request_count = 0;
    public boolean shutdown      = false;
    public Thread thread;

    @Override
	public final void run() {
        while (!shutdown) {
            CacheFileRequest request;
            synchronized (request_queue) {
                request = (CacheFileRequest) request_queue.remove();
                if (request != null) {
					request_count--;
				} else {
                    try {
                        request_queue.wait();
                    } catch (InterruptedException interruptedexception) {
            /* empty */
                    }
                    continue;
                }
            }
            try {
                if (request.opcode == 2) {
					request.datafs.put((int) request.queue_id, request.buffer, request.buffer.length);
                } else if (request.opcode == 3) {
					request.buffer = request.datafs.get((byte) 1, (int) request.queue_id);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            request.in_progress = false;
        }
    }

    public final void shutdown() {
        shutdown = true;
        synchronized (request_queue) {
            request_queue.notifyAll();
        }
        try {
            thread.join();
        } catch (InterruptedException interruptedexception) {
	    /* empty */
        }
        thread = null;
    }

    public final CacheFileRequest put(FileSystem datafs, int file_id, byte[] buffer) {
        CacheFileRequest var_cacheFileRequest = new CacheFileRequest();
        var_cacheFileRequest.isPriority = false;
        var_cacheFileRequest.queue_id = file_id;
		var_cacheFileRequest.datafs = datafs;
        var_cacheFileRequest.opcode = 2;
        var_cacheFileRequest.buffer = buffer;
        post_request(var_cacheFileRequest);
        return var_cacheFileRequest;
    }

    public final CacheFileRequest get(FileSystem datafs, int file_id) {
        CacheFileRequest new_req = new CacheFileRequest();
        new_req.opcode = 1;
        synchronized (request_queue) {
            for (CacheFileRequest req = (CacheFileRequest) request_queue.get_first(); req != null; req = (CacheFileRequest) request_queue.get_next()) {
                if (req.queue_id == file_id &&
						datafs == req.datafs &&
                        req.opcode == 2) {
                    new_req.buffer = req.buffer;
                    new_req.in_progress = false;
                    return new_req;
                }
            }
        }
        new_req.buffer = datafs.get((byte) 1, file_id);
        new_req.isPriority = true;
        new_req.in_progress = false;
        return new_req;
    }

    public final CacheFileRequest post_request(FileSystem datafs, int file_id, int opcode) {
        CacheFileRequest var_cacheFileRequest = new CacheFileRequest();
		var_cacheFileRequest.datafs = datafs;
        var_cacheFileRequest.queue_id = file_id;
        var_cacheFileRequest.opcode = opcode;
        var_cacheFileRequest.isPriority = false;
        post_request(var_cacheFileRequest);
        return var_cacheFileRequest;
    }

    public final void post_request(CacheFileRequest var_cacheFileRequest) {
        synchronized (request_queue) {
            request_queue.add_last(var_cacheFileRequest);
            request_count++;
            request_queue.notifyAll();
        }
    }

    public AsyncCache(SignLink signlink) {
        try {
            SignlinkRequest request = signlink.newRunnable(5, this, (byte) 72);
            while (request.status == 0) {
				TimeTools.sleep(10L);
			}
            if (request.status == 2) {
				throw new RuntimeException();
			}
            thread = (Thread) request.result;
        } catch (RuntimeException runtimeexception) {
            runtimeexception.printStackTrace();
        }
    }
}
