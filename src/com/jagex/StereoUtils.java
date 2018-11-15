package com.jagex;
/* StereoUtils - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;

public class StereoUtils extends Stereo
{
	public int anInt1980;
	public static StereoInterface anStereoInterface_1981;
	
	@Override
	final int get_buffer_used_size() {
		return anStereoInterface_1981.method13(anInt1980, -119);
	}
	
	@Override
	final void close_line() {
		anStereoInterface_1981.method11(anInt1980, false);
	}
	
	StereoUtils(SignLink signLink, int i) {
		anStereoInterface_1981 = signLink.method108(true);
		anInt1980 = i;
	}
	
	@Override
	final void flush_line() {
		anStereoInterface_1981.method14(anInt1980, 70);
	}
	
	public static void method88() {
		anStereoInterface_1981 = null;
	}
	
	@Override
	final void copy_buffers() {
		anStereoInterface_1981.method12(anInt1980, samples);
	}
	
	@Override
	final void open_line(int i) throws Exception {
		if (i > 32768) {
			throw new IllegalArgumentException();
		}
		anStereoInterface_1981.method9(i, anInt1980, 37);
	}
	
	@Override
	final void initialize(Component component) throws Exception {
		anStereoInterface_1981.method10(FileSystem.stereo, component, Keyboard.sampleRate, (byte) -113);
	}
}
