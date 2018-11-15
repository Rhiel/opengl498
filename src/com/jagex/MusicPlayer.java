package com.jagex;

/**
 * Created by Chris on 4/1/2017.
 */
public class MusicPlayer {

    public static int musicId;
    public static Js5 musicContainer;

    static final void updateCurrentMusic(int musicId, Js5 class105, int i_0_, int i_1_, int i_2_, boolean bool) {
        musicContainer = class105;
        CS2Event.anInt2257 = i_2_;
        CullingCluster.anInt931 = i_1_;
        StaticMethods.aBoolean3018 = bool;
        GroundObjEntity.anInt708 = i_0_;
        LocResult.anInt3720 = 10000;
        musicId = musicId;
    }

    static final void stopMusic(boolean bool) {
        ModelList.aSomeSoundClass_1437.method551((byte) 24);
        if (bool == false) {
            musicContainer = null;
            GroundObjEntity.anInt708 = 1;
        }
    }

    public static final void playMusic(int id, int i_13_, int i_14_, boolean bool, int i_15_, Js5 class105, int i_16_) {
        CullingCluster.anInt931 = i_14_;
        if (i_13_ == 13910) {
            CS2Event.anInt2257 = i_16_;
            LocResult.anInt3720 = i_15_;
            musicId = id;
            GroundObjEntity.anInt708 = 1;
            musicContainer = class105;
            StaticMethods.aBoolean3018 = bool;
        }
    }
}
