package com.jagex.js5;

import com.jagex.GameClient;

/**
 * Created by Chris on 6/16/2017.
 */
public class Js5Shutdown {

    public static void destruct() {
        CRC32.crc_table = null;
        GameClient.async_cache = null;
        GameClient.js5_archives = null;
        GameClient.js5_client = null;
        GameClient.js5_manager = null;
    }

}
