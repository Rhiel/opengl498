package com.jagex;
/* MonochromeImageCache - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class MonochromeImageCache
{
	/**
     * A field which is not refactored.
     */
    public static MonochromeImageCacheSlot SLOT_USED = new MonochromeImageCacheSlot(0, 0);
	public int slot_count;
	static short[] aShortArray1667;
	public int max_id;
	public MonochromeImageCacheSlot[] slots;
	static RSString aClass16_1672;
	public int used_slots = 0;
	public NodeDeque usage_tracker;
	static RSString aClass16_1677;
	public int last_request = -1;
	public int[][] images;
	public boolean dirty;

	public final void clear(byte b) {
		for (int i = 0; slot_count > i; i++) {
			images[i] = null;
		}
		images = null;
		slots = null;
		usage_tracker.clear();
		if (b != -13) {
			images = null;
		}
		usage_tracker = null;
	}
	
	static final RSString getSelectedActionName(RSInterface class64) {
		if (Class47.getOptionMask(class64, 65).method101() == 0) {
			return null;
		}
		if (class64.targetVerb == null || class64.targetVerb.method169().length() == 0) {
			if (Class75.qa_op_test) {
				return StaticMethods2.aClass16_1711;
			}
			return null;
		}
		return class64.targetVerb;
	}
	
	public final int[][] get_all(byte b) {
		if (max_id != slot_count) {
			throw new RuntimeException("Can only retrieve a full image cache");
		}
		for (int i = 0; (i ^ 0xffffffff) > (slot_count ^ 0xffffffff); i++) {
			slots[i] = SLOT_USED;
		}
		return images;
	}
	
	public final int[] get(int i, byte b) {
		if (slot_count == max_id) {
			dirty = slots[i] == null;
			slots[i] = SLOT_USED;
			return images[i];
		}
		if (slot_count != 1) {
			MonochromeImageCacheSlot class23_sub15 = slots[i];
			if (class23_sub15 != null) {
				dirty = false;
			} else {
				dirty = true;
				if ((used_slots ^ 0xffffffff) > (slot_count ^ 0xffffffff)) {
					class23_sub15 = new MonochromeImageCacheSlot(i, used_slots);
					used_slots++;
				} else {
					MonochromeImageCacheSlot class23_sub15_14_ = (MonochromeImageCacheSlot) usage_tracker.get_last(124);
					class23_sub15 = new MonochromeImageCacheSlot(i, class23_sub15_14_.slot_id);
					slots[class23_sub15_14_.image_id] = null;
					class23_sub15_14_.unlink();
				}
				slots[i] = class23_sub15;
			}
			usage_tracker.add_first(class23_sub15, -1);
			return images[class23_sub15.slot_id];
		}
		dirty = (i ^ 0xffffffff) != (last_request ^ 0xffffffff);
		last_request = i;
		return images[0];
	}
	
	public static void method1501(int i) {
		aClass16_1677 = null;
		if (i > -97) {
			Scene.object_selected = true;
		}
		aShortArray1667 = null;
		aClass16_1672 = null;
	}
	
	public MonochromeImageCache(int slots, int max, int image_size) {
		usage_tracker = new NodeDeque();
		dirty = false;
		max_id = max;
		this.slots = new MonochromeImageCacheSlot[max_id];
		slot_count = slots;
		images = new int[slot_count][image_size];
	}
	
	static {
		aShortArray1667 = new short[] { -4160, -4163, -8256, -8259, 22461 };
		aClass16_1677 = RSString.createString(" loggt sich ein)3");
		aClass16_1672 = RSString.createString("; Expires=Thu)1 01)2Jan)21970 00:00:00 GMT; Max)2Age=0");
	}
}
