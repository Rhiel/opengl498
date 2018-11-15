package com.jagex.window;

import com.jagex.ArrayUtils;
import com.jagex.GameClient;
import com.jagex.game.runetek4.clientoptions.GamePreferences;

/**
 * Created by Chris on 4/24/2017.
 */
public class VideoMode {

    public int width;
    public int refresh_rate;
    public int bit_depth;
    public int height;
    public static VideoMode[] currentVideoMode;

    public VideoMode() {
	/* empty */
    }

    public static final VideoMode[] getVideoModes() {
        if (currentVideoMode == null) {
            VideoMode[] var_videoModes = FullscreenUtilities.list_video_modes(GameClient.gameSignlink);
            VideoMode[] var_mkas_35_ = new VideoMode[var_videoModes.length];
            int i_36_ = 0;
            int i_37_ = GamePreferences.marginMode;
            while_60_:
            for (int i_38_ = 0; var_videoModes.length > i_38_; i_38_++) {
                VideoMode var_videoMode = var_videoModes[i_38_];
                if ((var_videoMode.refresh_rate <= 0 || var_videoMode.refresh_rate >= 24)
                        && var_videoMode.width >= 800 && var_videoMode.height >= 600
                        && (i_37_ != 2 || (var_videoMode.width <= 800
                        && var_videoMode.height <= 600))
                        && (i_37_ != 1 || (var_videoMode.width <= 1024
                        && var_videoMode.height <= 768))) {
                    for (int i_39_ = 0; i_36_ > i_39_; i_39_++) {
                        VideoMode var_videoMode_40_ = var_mkas_35_[i_39_];
                        if (((var_videoMode_40_.width ^ 0xffffffff)
                                == (var_videoMode.width ^ 0xffffffff))
                                && var_videoMode_40_.height == var_videoMode.height) {
                            if ( var_videoMode.refresh_rate > var_videoMode_40_.refresh_rate)
                                var_mkas_35_[i_39_] = var_videoMode;
                            continue while_60_;
                        }
                    }
                    var_mkas_35_[i_36_] = var_videoMode;
                    i_36_++;
                }
            }
            currentVideoMode = new VideoMode[i_36_];
            ArrayUtils.arraycopy(var_mkas_35_, 0, currentVideoMode, 0, i_36_);
            int[] is = new int[currentVideoMode.length];
            for (int i_41_ = 0;
                 (i_41_ ^ 0xffffffff) > (currentVideoMode.length ^ 0xffffffff);
                 i_41_++) {
                VideoMode var_videoMode = currentVideoMode[i_41_];
                is[i_41_] = var_videoMode.width * var_videoMode.height;
            }
            a(currentVideoMode, is);
        }
        return currentVideoMode;
    }

    public static final void a(Object[] objects, int[] is) {
        a(2, objects, is.length - 1, is, 0);
    }

    public static final void a(int i, Object[] objects, int i_9_, int[] is, int i_10_) {
        if (i_10_ < i_9_) {
            int i_11_ = (i_9_ + i_10_) / 2;
            int i_12_ = i_10_;
            int i_13_ = is[i_11_];
            is[i_11_] = is[i_9_];
            is[i_9_] = i_13_;
            Object object = objects[i_11_];
            objects[i_11_] = objects[i_9_];
            objects[i_9_] = object;
            int i_14_ = i_13_ == 2147483647 ? 0 : 1;
            for (int i_15_ = i_10_; i_15_ < i_9_; i_15_++) {
                if ((i_15_ & i_14_) + i_13_ > is[i_15_]) {
                    int i_16_ = is[i_15_];
                    is[i_15_] = is[i_12_];
                    is[i_12_] = i_16_;
                    Object object_17_ = objects[i_15_];
                    objects[i_15_] = objects[i_12_];
                    objects[i_12_++] = object_17_;
                }
            }
            is[i_9_] = is[i_12_];
            is[i_12_] = i_13_;
            objects[i_9_] = objects[i_12_];
            objects[i_12_] = object;
            a(2, objects, -1 + i_12_, is, i_10_);
            a(2, objects, i_9_, is, i_12_ + 1);
        }
    }


}
