package com.jagex;
/* StringNode - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class StringNode extends Linkable
{
	static RSString aClass16_2465 = RSString.createString("sind fehlgeschlagen)3 Bitte warten Sie 5 Minuten)1");
	static RSString usernameLabel;
	static volatile int anInt2469;
	public static RSString aClass16_2470 = RSString.createString("Username: ");
	public RSString value;
	public static int anInt2473;

	public static final void method909(int i, int i_0_, int i_1_, int i_2_, byte b, int i_3_, int i_4_, int i_5_, int i_6_) {
		if (!AbstractTimer.hasActiveInterface(-10924, i_5_)) {
			if (i_2_ != -1) {
				RSInterfaceList.is_dirty[i_2_] = true;
			} else {
				for (int i_7_ = 0; i_7_ < 100; i_7_++)
					RSInterfaceList.is_dirty[i_7_] = true;
			}
		} else {
			Class31.aClass64Array484 = null;
			InterfaceManager.render_layer(StaticMethods.cached_interfaces[i_5_], -1, i_0_, i, i_4_, i_1_, i_3_, i_6_, i_2_, -1);
			if (Class31.aClass64Array484 != null) {
				InterfaceManager.render_layer(Class31.aClass64Array484, -1412584499, i_0_, i, i_4_, i_1_, FileSystem.anInt250, PlayerMasks.anInt907, i_2_, -1);
				Class31.aClass64Array484 = null;
			}
			if (b != 21) {
				aClass16_2465 = null;
			}
		}
	}
	
	static final void method910(boolean bool) {
		if (bool != true) {
			method910(true);
		}
		ISAACPacket.models_cache.clearModelCache((byte) 110);
	}
	
	public StringNode() {
		/* empty */
	}
	
	StringNode(RSString string) {
		value = string;
	}
	
	public static void method911(boolean bool) {
		aClass16_2470 = null;
		aClass16_2465 = null;
		usernameLabel = null;
		if (bool != true) {
			method911(false);
		}
	}
	
	static {
		anInt2469 = -1;
		usernameLabel = aClass16_2470;
		anInt2473 = 0;
	}
}
