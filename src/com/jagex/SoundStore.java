package com.jagex;


public final class SoundStore
{
    int soundOffset1;
    byte k;
    Class23_Sub3 r;
    SomeSoundClass2 t;
    Class23_Sub10_Sub1 b;
    int soundVolume;
    Entity player;
    int soundDelay;
    int anIntArray1497;
    int soundOffset2;
    int soundId;
    Sound f_l;

    public final boolean c(int i) {
        if (this.k != 2 && this.k != 3)
            return false;
        return true;
    }

    SoundStore(byte i, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_, Entity var_playerNode) {
        this.soundOffset2 = i_4_;
        this.soundDelay = i_3_;
        this.soundVolume = i_2_;
        this.k = i;
        this.soundOffset1 = i_6_;
        this.player = var_playerNode;
        this.soundId = i_1_;
        this.anIntArray1497 = i_5_;
    }
}

