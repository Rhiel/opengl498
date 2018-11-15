package com.jagex;
/* Class31 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Calendar;

public class Class31
{
	static RSString aClass16_481 = RSString.createString("Bitte entfernen Sie ");
	public static RSString aClass16_482;
    static RSInterface[] aClass64Array484;
	static Calendar aCalendar485;
	static RSString aClass16_486 = RSString.createString("");
	static RSString aClass16_487;
	public static int anInt488;
	static short[] aShortArray489 = { -10304, 9104, -1, -1, -1 };
	public static RSString aClass16_491;
	static RSString aClass16_492;
	static RSString aClass16_493;
	
	public static void method961(byte b) {
		aCalendar485 = null;
		aClass16_491 = null;
		GameClient.activeNPCs = null;
		aClass16_481 = null;
		aClass16_492 = null;
		aClass16_487 = null;
		aClass16_493 = null;
		aClass16_486 = null;
		aClass64Array484 = null;
		aShortArray489 = null;
		if (b <= -122) {
			aClass16_482 = null;
		}
	}
	
	static {
		aClass16_482 = RSString.createString("Attack");
		aClass16_487 = aClass16_482;
		aCalendar485 = Calendar.getInstance();
		aClass16_491 = RSString.createString("red:");
		aClass16_493 = aClass16_491;
		aClass16_492 = aClass16_491;
	}
}
