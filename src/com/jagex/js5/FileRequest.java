package com.jagex.js5;

import com.jagex.Queuable;

/**
 * Created by Chris on 5/31/2017.
 */
public abstract class FileRequest extends Queuable {

    public        boolean isPriority;
    public volatile      boolean in_progress = true;
    public boolean v;

    public abstract int get_progress();

    public abstract byte[] get_data();

    public FileRequest() {
    /* empty */
    }
}
