package com.jagex.js5;

/**
 * Created by Chris on 6/1/2017.
 */
public abstract class Archive {

    public abstract Js5Index get_index();

    public Archive() {
	/* empty */
    }

    public abstract void request_group(int i_0_);

    public abstract int get_group_progress(int i);

    public abstract byte[] get_group_data(int i_1_);

}
