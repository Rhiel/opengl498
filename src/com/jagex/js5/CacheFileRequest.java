package com.jagex.js5;

import com.jagex.FileSystem;

/**
 * Created by Chris on 6/1/2017.
 */
public final class CacheFileRequest extends FileRequest
{
    public static int    get_data_callcount;
    public        byte[] buffer;
    public static int    get_progress_callcount;
    public        int    opcode;
    public FileSystem datafs;

    @Override
	public final int get_progress() {
        get_progress_callcount++;
        if (this.in_progress)
            return 0;
        return 100;
    }

    @Override
	public final byte[] get_data() {
        try {
            get_data_callcount++;
            if (this.in_progress)
                throw new RuntimeException();
            return this.buffer;
        } catch (RuntimeException runtimeexception) {
            throw  runtimeexception;
        }
    }

    public CacheFileRequest() {
	/* empty */
    }
}
