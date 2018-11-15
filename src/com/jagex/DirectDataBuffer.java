package com.jagex;
/* DirectDataBuffer - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.nio.ByteBuffer;

public class DirectDataBuffer extends DataBuffer {
    public ByteBuffer byteBuffer;

    @Override
	final byte[] get_data(int i) {
        byte[] bs = new byte[byteBuffer.capacity()];
        byteBuffer.position(i);
        byteBuffer.get(bs);
        return bs;
    }

    DirectDataBuffer() {
        /* empty */
    }

    @Override
	final void put_data(byte[] bs) {
        byteBuffer = ByteBuffer.allocateDirect(bs.length);
        byteBuffer.position(0);
        byteBuffer.put(bs);
    }
}
