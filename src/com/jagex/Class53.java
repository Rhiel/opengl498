package com.jagex;
/* Class53 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;

import com.jagex.launcher.GamePlayConfiguration;
import com.jagex.launcher.Properties;

public class Class53 {
	public SignlinkRequest aRequest_832;
	public static int anInt833;
	public int anInt834;
	static RSString aClass16_836;
	public int anInt837;
	public long aLong839;
	public DataInputStream aDataInputStream844;
	public byte[] aByteArray845;
	public byte[] aByteArray846 = new byte[4];
	public int anInt848;
	public static RSString aClass16_849 = RSString.createString("Loaded update list");

	final byte[] method1171(int i) throws IOException {
		if ((TimeTools.getMillis() ^ 0xffffffffffffffffL) < (aLong839 ^ 0xffffffffffffffffL)) {
			throw new IOException("fdt");
		}
		if (anInt837 == 0) {
			if (aRequest_832.status == 2) {
				throw new IOException("fds");
			}
			if (aRequest_832.status == 1) {
				anInt837 = 1;
				aDataInputStream844 = (DataInputStream) aRequest_832.result;
			}
		}
		if (anInt837 == 1) {
			int i_0_ = aDataInputStream844.available();
			if ((i_0_ ^ 0xffffffff) < -1) {
				if (i_0_ - -anInt848 > 4) {
					i_0_ = 4 - anInt848;
				}
				anInt848 += aDataInputStream844.read(aByteArray846, anInt848, i_0_);
				if (anInt848 == 4) {
					int i_1_ = new Packet(aByteArray846).g4();
					aByteArray845 = new byte[i_1_];
					anInt837 = 2;
				}
			}
		}
		if (i < 14) {
			ContextMenu.menuOpen = false;
		}
		if (anInt837 == 2) {
			int i_2_ = aDataInputStream844.available();
			if (i_2_ > 0) {
				if (aByteArray845.length < i_2_ - -anInt834) {
					i_2_ = -anInt834 + aByteArray845.length;
				}
				anInt834 += aDataInputStream844.read(aByteArray845, anInt834, i_2_);
				if (anInt834 == aByteArray845.length) {
					return aByteArray845;
				}
			}
		}
		return null;
	}

	public static void method1172(boolean bool) {
		MapLoader.square_map_data = null;
		aClass16_836 = null;
		aClass16_849 = null;
	}

	static final void processClientCommands(int i, RSString inputString) {
		if (GameClient.outBuffer != null && GameClient.outBuffer.byteBuffer != null) {
			if (GameClient.rights >= 0) {
				if (inputString.equalsIgnoreCase(PlayerIdentityKit.aClass16_4080)) {
					for (int i_3_ = 0; i_3_ < 10; i_3_++) {
						System.gc();
					}
					Runtime runtime = Runtime.getRuntime();
					int i_4_ = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
					Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { IoSession.aClass16_528, RSString.valueOf(i_4_), Class55.aClass16_868 }), null);
				} else if (inputString.equalsIgnoreCase(RSString.create("::groundblending"))) {
					GraphicSettings.setGroundBlending(!GraphicSettings.hasGroundBlending());
					DebugConsole.write("Ground blending is now " + (GraphicSettings.hasGroundBlending() ? "enabled" : "disabled"));
				} else if (inputString.equalsIgnoreCase(RSString.create("::textures"))) {
					GraphicSettings.setTextures(!GraphicSettings.hasTextures());
					DebugConsole.write("Textures are now " + (GraphicSettings.hasTextures() ? "enabled" : "disabled"));
				} else if (inputString.equalsIgnoreCase(StaticMethods.aClass16_2964)) {
					Class44.method1128(-104);
				} else if (inputString.equalsIgnoreCase(StaticMethods.aClass16_3180)) {
					GamePlayConfiguration.isFPSEnabled = true;
				} else if (inputString.equalsIgnoreCase(RSString.create("::reloadmaps"))) {
					MapLoader.load_region();
				} else if (inputString.equalsIgnoreCase(Queue.aClass16_403)) {
					GamePlayConfiguration.isFPSEnabled = false;
				} else if (inputString.equalsIgnoreCase(RSString.create("::vote")) || inputString.equalsIgnoreCase(RSString.create("::voting"))) {
					WebTools.openWebpage("http://www.zaros.rs/vote");
				} else if (inputString.equalsIgnoreCase(RSString.create("::store"))) {
					WebTools.openWebpage("http://www.zaros.rs/shop/");
				} else if (inputString.equalsIgnoreCase(RSString.create("::site")) || inputString.equalsIgnoreCase(RSString.create("::website"))) {
					WebTools.openWebpage("http://www.zaros.rs/");
				} else if (inputString.equalsIgnoreCase(RSString.create("::togglefk"))) {
					boolean b = !Properties.get().isModernHotkeys();
					Properties.get().setModernHotkeys(b);
					Properties.get().save();
					System.out.println("Using " + (b ? "modern" : "default") + " hotkey layout.");
				} else if (inputString.equalsIgnoreCase(ClanChatMember.noClipCommand)) {
					if (!GameClient.isNoClip()) {
						for (int i_5_ = 0; i_5_ < 4; i_5_++) {
							for (int i_6_ = 1; i_6_ < 103; i_6_++) {
								for (int i_7_ = 1; i_7_ < 103; i_7_++) {
									MapLoader.collision_maps[i_5_].clippingFlags[i_6_][i_7_] = 0;
								}
							}
						}
					}
					GameClient.setNoClip(!GameClient.isNoClip());
					Class95.sendGameMessage(0, -1, RSString.createString("Clipping flags " + (GameClient.isNoClip() ? "disabled." : "enabled.")), null);
				} else if (inputString.startsWith(StaticMethods.aClass16_2284) && (GameClient.getGameType() ^ 0xffffffff) != -1) {
					Class71_Sub2_Sub1.method1284(1000, inputString.substring(6).toInteger());
				} else if (inputString.equalsIgnoreCase(Class71.aClass16_1272) && GameClient.getGameType() == 2) {
					throw new RuntimeException("here9317");
				} else if (inputString.startsWith(CullingCluster.aClass16_928)) {
					Class57.anInt901 = inputString.substring(12).method169().toInteger();
					Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { NameHashTable.aClass16_1198, RSString.valueOf(Class57.anInt901) }), null);
				} else if (inputString.equalsIgnoreCase(StaticMethods2.aClass16_3886)) {
					Class75.qa_op_test = true;
				} else {
					GameClient.outBuffer.putOpcode(46);
					GameClient.outBuffer.p1(inputString.length() - 1);
					GameClient.outBuffer.putString(inputString.substring(2), -124);
				}
			}
		}
	}

	Class53(SignLink signLink, URL url) {
		aRequest_832 = signLink.newURLThread(url, 119);
		anInt837 = 0;
		aLong839 = 30000L + TimeTools.getMillis();
	}

	static {
		aClass16_836 = aClass16_849;
	}
}
