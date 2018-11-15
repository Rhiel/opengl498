package com.jagex;

public class TimeTools {
	/**
	 * The last time cycle.
	 */
	public static long time;
	/**
	 * The secondary time (unknown functionality.)
	 */
	public static long secondaryTime;

	/**
	 * Method used to a the real current time.
	 * 
	 * @return the time.
	 */
	public static final synchronized long getMillis() {
		long currentTime = System.currentTimeMillis();
		if (currentTime < time) {
			System.out.println("WARNING: safetime: time jumped back by " + (time - currentTime) + " ms");
			secondaryTime += time - currentTime;
		}
		time = currentTime;
		return currentTime + secondaryTime;
	}

	static RSString aClass16_1599;
	static RSString aClass16_1600;
	static RSString aClass16_1601;
	public static int[] maskUpdates;
	public static int soundPreference1;
	public static int soundPreference2;
	public static RSString aClass16_1605;
	static RSString aClass16_1610;

	static {
		aClass16_1605 = RSString.createString("Walk here");
		soundPreference1 = 127;
		soundPreference2 = 127;
		aClass16_1600 = RSString.createString("");
		aClass16_1601 = RSString.createString("null");
		maskUpdates = new int[2048];
		aClass16_1599 = aClass16_1605;
		aClass16_1610 = RSString.createString("Lade Wordpack )2 ");
	}

	public static final void sleep(long l) {
		if ((l ^ 0xffffffffffffffffL) < -1L) {
			if (l % 10L == 0L) {
				sleep_inner((byte) -93, -1L + l);
				sleep_inner((byte) -79, 1L);
			} else {
				sleep_inner((byte) -77, l);
			}
		}
	}

	static final void sleep_inner(byte b, long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			/* empty */
		}
	}
}
