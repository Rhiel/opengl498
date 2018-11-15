package com.jagex;
/* ModelList - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import com.rs2.client.scene.Scene;

public class ModelList
{
	public static int[] anIntArray1424;
	public static RSString aClass16_1428 = RSString.createString("purple:");
	static RSString aClass16_1430 = aClass16_1428;
	static RSString aClass16_1431;
	public MemoryCache jList;
	static RSString aClass16_1436 = aClass16_1428;
	public static SomeSoundClass aSomeSoundClass_1437;
	public static RSString aClass16_1439 = RSString.createString(" is already on your ignore list)3");
	
	final void put(long l, SceneNode abstractModel) {
		jList.put(l, new ModelNode(abstractModel));
	}
	
	final SceneNode get(long l) {
		ModelNode class23_sub13_sub25 = (ModelNode) jList.get(l);
		if (class23_sub13_sub25 != null) {
			return class23_sub13_sub25.model;
		}
		return null;
	}
	
	static final void method1368(byte b) {
		Class25.anInt388 = -1;
		Huffman.anInt1819 = 0;
		ContextMenu.menuActionRow = 0;
		StaticMethods2.anInt666 = -1;
		PacketParser.currentOpcode = -1;
		if (b == 41) {
			ContextMenu.menuOpen = false;
			ComponentMinimap.flag_x = 0;
			Class25.anInt379 = -1;
			GameClient.outBuffer.index = 0;
			StaticMethods2.packet.index = 0;
			DataBuffer.anInt992 = 0;
			StaticMethods.currentLength = 0;
			Queuable.systemUpdateTime = 0;
			for (int i = 0; i < GameClient.localPlayers.length; i++) {
				if (GameClient.localPlayers[i] != null) {
					GameClient.localPlayers[i].faceIndex = -1;
				}
			}
			for (int i = 0; GameClient.activeNPCs.length > i; i++) {
				if (GameClient.activeNPCs[i] != null) {
					GameClient.activeNPCs[i].faceIndex = -1;
				}
			}
			ClientInventoryList.clearList(32);
			GameClient.updateClientState(30);
			for (int i = 0; i < 100; i++) {
				RSInterfaceList.is_dirty[i] = true;
			}
		}
	}
	
	final void clearModelCache(byte b) {
		jList.clear();
	}
	
	final void method1370(int i, long l) {
		jList.method70(l, 2047);
	}
	
	public ModelList(int i) {
		jList = new MemoryCache(i);
	}
	
	public static void method1371(int i) {
		if (i != -1) {
			method1371(116);
		}
		anIntArray1424 = null;
		Scene.anIntArray1433 = null;
		SpotType.spots_js5 = null;
		aClass16_1436 = null;
		aClass16_1428 = null;
		aClass16_1430 = null;
		aClass16_1431 = null;
		aClass16_1439 = null;
		aSomeSoundClass_1437 = null;
	}
	
	static {
		aClass16_1431 = aClass16_1439;
	}
}
