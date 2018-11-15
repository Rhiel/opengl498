package com.jagex.window;

import java.awt.*;

import com.jagex.SignLink;
import com.jagex.SignlinkRequest;
import com.jagex.TimeTools;

/**
 * Created by Chris on 4/24/2017.
 */
public class FullscreenUtilities {

    public static int exit_full_screen_callcount;
    public static int list_video_modes_callcount;
    public static int enter_full_screen_callcount;

    public static void exit_full_screen(Frame frame, SignLink var_wla) {
        for (; ; ) {
            SignlinkRequest request = var_wla.exit_full_screen(frame);
            while ( request.status == 0 )
                TimeTools.sleep(10L);
            if ( request.status == 1 )
                break;
            TimeTools.sleep(100L);
        }
        exit_full_screen_callcount++;
        frame.setVisible(false);
        frame.dispose();
    }

    public static VideoMode[] list_video_modes(SignLink signlink) {
        list_video_modes_callcount++;
        if ( !signlink.can_change_resolution() )
            return new VideoMode[0];
        SignlinkRequest request = signlink.list_video_modes();
        while ( request.status == 0 )
            TimeTools.sleep(10L);
        if ( request.status == 2 )
            return new VideoMode[0];
        int[] data = (int[]) request.result;
        VideoMode[] modes = new VideoMode[data.length >> 2];
        for ( int mode_ptr = 0; modes.length > mode_ptr; mode_ptr++ ) {
            VideoMode var_videoMode = new VideoMode();
            modes[mode_ptr] = var_videoMode;
            var_videoMode.width = data[mode_ptr << 2];
            var_videoMode.height = data[(mode_ptr << 2) + 1];
            var_videoMode.refresh_rate = data[(mode_ptr << 2) + 2];
            var_videoMode.bit_depth = data[(mode_ptr << 2) + 3];
        }
        return modes;
    }

    public static Frame enter_full_screen(int width, int height, int bit_depth, int refresh_rate, SignLink signlink) {
        enter_full_screen_callcount++;
        if ( !signlink.can_change_resolution() )
            return null;
        VideoMode[] modes = list_video_modes(signlink);
        if ( modes == null )
            return null;
        boolean found_mode = false;
        for ( VideoMode mode : modes ) {
            if ( mode.width == width &&
                    height == mode.height &&
                    (bit_depth == 0 || mode.bit_depth == bit_depth) &&
                    (!found_mode || mode.refresh_rate > refresh_rate) ) {
                found_mode = true;
                refresh_rate = mode.refresh_rate;
            }
        }
        if ( !found_mode )
            return null;
        SignlinkRequest request = signlink.enter_full_screen(width, height, bit_depth, refresh_rate);
        while ( request.status == 0 )
            TimeTools.sleep(10L);
        Frame frame = (Frame) request.result;
        if ( frame == null )
            return null;
        if ( request.status == 2 ) {
            exit_full_screen(frame, signlink);
            return null;
        }
        return frame;
    }
}
