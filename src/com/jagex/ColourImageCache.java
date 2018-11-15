package com.jagex;
/* ColourImageCache - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class ColourImageCache
{
	public static ColourImageCacheSlot SLOT_USED = new ColourImageCacheSlot(0, 0);
	public int max_id;
	public int[][][] images;
	public int last_request = -1;
	public int used_slots = 0;
	static RSString aClass16_1720 = RSString.createString("Spieler");
	public int slot_count;
	public ColourImageCacheSlot[] slots;
	public static int anInt1724 = 0;
	static RSString aClass16_1725;
	static RSString aClass16_1726;
	public static int anInt1728 = 0;
	public NodeDeque usage_tracker = new NodeDeque();
	public boolean dirty = false;

	public final int[][] get(int i, int i_5_) {
		if ((slot_count ^ 0xffffffff) == (max_id ^ 0xffffffff)) {
			dirty = slots[i_5_] == null;
			slots[i_5_] = SLOT_USED;
			return images[i_5_];
		}
		if (slot_count == 1) {
			dirty = last_request != i_5_;
			last_request = i_5_;
			return images[0];
		}
		ColourImageCacheSlot slot = slots[i_5_];
		if (slot != null) {
			dirty = false;
		} else {
			dirty = true;
			if ((slot_count ^ 0xffffffff) >= (used_slots ^ 0xffffffff)) {
				ColourImageCacheSlot old_slot = (ColourImageCacheSlot) usage_tracker.get_last(14);
				slot = new ColourImageCacheSlot(i_5_, old_slot.slot_id);
				slots[old_slot.image_id] = null;
				old_slot.unlink();
			} else {
				slot = new ColourImageCacheSlot(i_5_, used_slots);
				used_slots++;
			}
			slots[i_5_] = slot;
		}
		usage_tracker.add_first(slot, -1);
		return images[slot.slot_id];
	}
	
	public final void clear(int i) {
		for (int i_7_ = 0; (slot_count ^ 0xffffffff) < (i_7_ ^ 0xffffffff); i_7_++) {
			images[i_7_][0] = null;
			images[i_7_][1] = null;
			images[i_7_][2] = null;
			images[i_7_] = null;
		}
		slots = null;
		images = null;
		usage_tracker.clear();
		usage_tracker = null;
	}
	
	public final int[][][] get_all(int i) {
		if (max_id != slot_count) {
			throw new RuntimeException("Can only retrieve a full image cache");
		}
		for (int i_9_ = 0; slot_count > i_9_; i_9_++)
			slots[i_9_] = SLOT_USED;
		return images;
	}

	static final String printThrowable(byte b, Throwable throwable) throws IOException {
		String string;
		if (!(throwable instanceof RuntimeException_Sub1)) {
			string = "";
		} else {
			RuntimeException_Sub1 runtimeexception_sub1 = (RuntimeException_Sub1) throwable;
			string = runtimeexception_sub1.aString1850 + " | ";
			throwable = runtimeexception_sub1.aThrowable1848;
		}
		StringWriter stringwriter = new StringWriter();
		PrintWriter printwriter = new PrintWriter(stringwriter);
		throwable.printStackTrace(printwriter);
		printwriter.close();
		String string_12_ = stringwriter.toString();
		BufferedReader bufferedreader = new BufferedReader(new StringReader(string_12_));
		String string_13_ = bufferedreader.readLine();
		for (;;) {
			String string_14_ = bufferedreader.readLine();
			if (string_14_ == null) {
				break;
			}
			int i_15_ = string_14_.indexOf('(');
			int i_16_ = string_14_.indexOf(')', 1 + i_15_);
			if ((i_15_ ^ 0xffffffff) <= -1 && i_16_ >= 0) {
				String string_17_ = string_14_.substring(1 + i_15_, i_16_);
				int i_18_ = string_17_.indexOf(".java:");
				if (i_18_ >= 0) {
					string_17_ = string_17_.substring(0, i_18_) + string_17_.substring(i_18_ - -5);
					string += string_17_ + ' ';
					continue;
				}
				string_14_ = string_14_.substring(0, i_15_);
			}
			string_14_ = string_14_.trim();
			string_14_ = string_14_.substring(string_14_.lastIndexOf(' ') + 1);
			string_14_ = string_14_.substring(string_14_.lastIndexOf('\t') - -1);
			string += string_14_ + ' ';
		}
		string += "| " + string_13_;
		return string;
	}
	
	public static void method1523(int i) {
		if (i >= 72) {
			aClass16_1726 = null;
			aClass16_1720 = null;
			aClass16_1725 = null;
		}
	}
	
	public ColourImageCache(int i, int i_19_, int i_20_) {
		max_id = i_19_;
		slot_count = i;
		slots = new ColourImageCacheSlot[max_id];
		images = new int[slot_count][3][i_20_];
	}
	
	static {
		aClass16_1725 = RSString.createString(" loggt sich aus)3");
		aClass16_1726 = RSString.createString(" <col=00ff80>");
	}
}
