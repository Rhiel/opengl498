package com.jagex;
/* ReflectionRequest - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Image;

public class ReflectionRequest extends Linkable
{
	public int[] int_values;
	public byte[][][] parameter_data;
	public SignlinkRequest[] method_requests;
	public int link;
	public int[] status;
	static Image barImage;
	public static int[] anIntArray2482 = new int[26];
	public SignlinkRequest[] field_requests;
	public int[] opcode;
	public int op_count;
	static HintIcon[] currentHintIcons = new HintIcon[4];
	
	public static void method913(int i) {
		currentHintIcons = null;
		barImage = null;
		anIntArray2482 = null;
	}
	
	static final void method914(int i) {
		for (ObjectNode class23_sub8 = (ObjectNode) Js5.aClass89_1767.get_first(); class23_sub8 != null; class23_sub8 = (ObjectNode) Js5.aClass89_1767.get_next()) {
			if (class23_sub8.objectDef != null) {
				class23_sub8.method497((byte) 43);
			}
		}
		if (i != 25) {
			method914(17);
		}
	}
}
