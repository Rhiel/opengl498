package com.jagex;

public class PositionedGraphicNode extends Queuable
{
	static RSString aClass16_3853 = RSString.createString(" steht bereits auf Ihrer Freunde)2Liste(Q");
	public static int[] queueX = new int[4096];
	public SpotEntity positionedGraphic;

	static final RSString method685(int i, RSInterface class64, int i_0_) {
		if (!Class47.getOptionMask(class64, 105).a(i) && class64.anObjectArray1116 == null) {
			return null;
		}
		if (class64.ops == null || (i ^ 0xffffffff) <= (class64.ops.length ^ 0xffffffff) || class64.ops[i] == null || (class64.ops[i].method169().length() ^ 0xffffffff) == -1) {
			if (Class75.qa_op_test) {
				return RSString.joinRsStrings(new RSString[] { Class44.aClass16_673, RSString.valueOf(i) });
			}
			return null;
		}
		return class64.ops[i];
	}
	
	public static void method687(boolean bool) {
		if (bool == true) {
			queueX = null;
		}
	}
	
	public static final RSInterface method690(RSInterface class64, int i) {
		RSInterface class64_6_ = RSInterface.method281((byte) 79, class64);
		if (class64_6_ == null) {
			class64_6_ = class64.aClass64_1121;
		}
		return class64_6_;
	}
	
	PositionedGraphicNode(SpotEntity graphic) {
		positionedGraphic = graphic;
	}
	
	static {
		GameClient.setAffId(0);
		ContextMenu.menuActionRow = 0;
	}
}
