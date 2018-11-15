package com.jagex;
/* HintIcon - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class HintIcon
{
	static RSString aClass16_202 = RSString.createString("Um ein neues Spielkonto zu erstellen)1 m-Ussen Sie");
	public int targetType;
	public int height;
	public int arrowId;
	static NodeDeque aClass89_206 = new NodeDeque();
	public int offsetY;
    public int entityIndex;
	public int offsetX;
	public int targetX;
	public int targetY;
	public int modelId = -1;
	
	static final boolean isHidden(boolean dummy, RSInterface inter) {
		if (Class75.qa_op_test) {
			if (Class47.getOptionMask(inter, 74).anInt2452 != 0) {
				return false;
			}
			if (inter.type == 0) {
				return false;
			}
		}
		if (dummy != true) {
			return false;
		}
		return inter.hidden;
	}
	
	public static void method110(byte b) {
		if (b < 116) {
			aClass89_206 = null;
		}
		aClass89_206 = null;
		aClass16_202 = null;
	}
}
