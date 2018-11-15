package com.jagex;
/* NanoTimer - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class NanoTimer extends AbstractTimer
{
	public long timeAsLong = System.nanoTime();
	
	@Override
	final int wait_for_next_frame(int i, byte b, int i_0_) {
		long l = timeAsLong - System.nanoTime();
		long l_1_ = 1000000L * i_0_;
		if (l < l_1_) {
			l = l_1_;
		}
		int i_2_ = 0;
		TimeTools.sleep(l / 1000000L);
		long l_3_;
		for (l_3_ = System.nanoTime(); i_2_ < 10 && (1 > i_2_ || timeAsLong < l_3_); i_2_++)
			timeAsLong += 1000000L * i;
		if ((timeAsLong ^ 0xffffffffffffffffL) > (l_3_ ^ 0xffffffffffffffffL)) {
			timeAsLong = l_3_;
		}
		return i_2_;
	}
	
	@Override
	final void method188(int i) {
		timeAsLong = System.nanoTime();
	}
	
	NanoTimer() {
		/* empty */
	}
}
