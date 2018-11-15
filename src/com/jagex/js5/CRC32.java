package com.jagex.js5;

/**
 * Created by Chris on 6/1/2017.
 */
public class CRC32 {

    public static int[] crc_table = new int[256];

    public static int calculate(byte[] data, int start, int end) {
        int crc32 = -1;
        for ( int pos = start; end > pos; pos++ )
            crc32 = crc32 >>> 8 ^ crc_table[0xff & (crc32 ^ data[pos])];
        crc32 ^= 0xffffffff;
        return crc32;
    }

    public static int calculate(byte[] data, int length) {
        return calculate(data, 0, length);
    }

    static {
        for ( int n = 0; n < 256; n++ ) {
            int v = n;
            for ( int i_15_ = 0; i_15_ < 8; i_15_++ ) {
                if ( (v & 0x1) != 1 )
                    v >>>= 1;
                else
                    v = v >>> 1 ^ 0xEDB88320;
            }
            CRC32.crc_table[n] = v;
        }
    }
}
