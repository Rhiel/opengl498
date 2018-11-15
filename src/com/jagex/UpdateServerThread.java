package com.jagex;

import com.rs2.client.scene.Scene;

public class UpdateServerThread {
	public static int anInt169;

	public static void method91(int i) {
		PlayerAppearance.cache = null;
	}

	public static final int method92(boolean bool) {
		if (bool != false) {
			method91(72);
		}
		int i = Scene.get_average_height(ObjType.localHeight, Camera.xCameraPos, Camera.yCameraPos);
		if (-Camera.zCameraPos + i < 800 && (com.jagex.MapLoader.settings[ObjType.localHeight][Camera.xCameraPos >> 7][Camera.yCameraPos >> 7] & 0x4) != 0) {
			return ObjType.localHeight;
		}
		return 3;
	}

	static final void method94(int i) {
		if (i != 0) {
			method92(true);
		}
		StaticMethods2.aModelList_4267.clearModelCache((byte) -46);
	}

	UpdateServerThread() {
		/* empty */
	}

	static final void method95(int i, int i_0_) {
		GameClient.outBuffer.putOpcode(76);
		GameClient.outBuffer.putIntA(i_0_);
		GameClient.outBuffer.putShort(i);
	}
}
