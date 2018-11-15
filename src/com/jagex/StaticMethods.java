package com.jagex;

import java.util.Random;
import java.util.zip.CRC32;

import com.jagex.core.tools.MathTools;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.graphics.runetek4.media.PaletteSprite;
import com.jagex.graphics.runetek4.software.sprite.SoftwarePaletteSprite;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.scene.Scene;

public class StaticMethods {
	public static RSString aClass16_3458 = RSString.createString("<col=00ff00>");
	public static RSString aClass16_4041 = RSString.createString("<)4col> x");
	public static RSString aClass16_3230 = RSString.createString(" <col=ffff00>");
	public static RSString aClass16_4048 = RSString.createString("");
	public static RSString aClass16_4047;
	public static RSString aClass16_2290 = RSString.createString("::autoshadow off");
	public static RSString aClass16_3237;
	public static RSString aClass16_3338;
	public static RSString aClass16_3344;
	public static RSString aClass16_3345;
	public static RSString aClass16_2960 = RSString.createString("Ihre Freunde)2Liste ist voll(Q Maximale Eintr-=ge: Mitglieder 200)4freie Spieler 100");
	public static RSString chatOwnerName;
	public static RSString aClass16_2963;
	public static RSString aClass16_2964;
	public static RSString aClass16_2966;
	public static RSString aClass16_2896 = RSString.createString("<col=ffffff>");
	public static RSString aClass16_2906;
	public static RSString aClass16_2898;
	public static RSString aClass16_2901;
	public static RSString aClass16_2907;
	public static RSString aClass16_2885;
	public static RSString aClass16_3050 = RSString.createString(" )2> ");
	public static RSString aClass16_3057;
	public static RSString aClass16_3045 = RSString.createString("white:");
	public static RSString aClass16_3047 = aClass16_3045;
	public static RSString aClass16_3048;
	public static RSString aClass16_3376;
	public static RSString optionOK;
	public static RSString aClass16_3378;
	public static RSString aClass16_3016 = RSString.createString("Zu viele Anmelde)2Versuche von Ihrer Adresse");
	public static RSString aClass16_3020;
	public static RSString aClass16_3022;
	public static RSString aClass16_3253;
	public static RSString aClass16_3248 = RSString.createString(" )2> <col=ffff00>");
	public static RSString aClass16_3245;
	public static RSString aClass16_3244;
	public static RSString aClass16_3159 = RSString.createString("nicht hergestellt werden)3");
	public static RSString aClass16_3220 = RSString.createString("und die Schaltfl-=che (WSpielkonto erstellen(W am");
	public static RSString aClass16_3200 = RSString.createString("Connection lost)3");
	public static RSString aClass16_3201 = aClass16_3200;
	public static RSString aClass16_3219;
	public static RSString aClass16_3434 = RSString.createString("Zu viele Verbindungen von Ihrer Adresse)3");
	public static RSString aClass16_2913;
	public static RSString aClass16_2931;
	public static RSString aClass16_2929;
	public static RSString aClass16_2105 = RSString.createString("welle2:");
	public static RSString aClass16_66 = RSString.createString("scrollen:");
	public static RSString aClass16_65;
	public static RSString aClass16_4113 = RSString.createString("Benutzeroberfl-=che geladen)3");
	public static RSString aClass16_4117;
	public static RSString aClass16_1861;
	public static RSString aClass16_1858;
	public static RSString aClass16_1862;
	public static RSString aClass16_1871;
	public static RSString aClass16_2919;
	public static RSString clanChatName;
	public static RSString aClass16_3450;
	public static RSString aClass16_3437;
	public static RSString aClass16_3439;
	public static RSString aClass16_3440;
	public static RSString aClass16_3445;
	public static RSString aClass16_3258 = RSString.createString("Verbinde mit Server)3)3)3");
	public static RSString aClass16_3270 = RSString.createString("oder benutzen Sie eine andere Welt)3");
	public static RSString aClass16_3275 = RSString.createString(":duelstake:");
	public static RSString aClass16_3288;
	public static RSString aClass16_3316 = RSString.createString("Free world");
	public static RSString aClass16_3320 = aClass16_3316;
	public static RSString aClass16_2941 = RSString.createString("Spielwelt erstellt)3");
	public static RSString aClass16_3294 = RSString.createString("Mem:");
	public static RSString OFFHEAP = RSString.createString("Offheap:");
	public static RSString PING = RSString.createString("Ping:");
	public static RSString NA = RSString.createString("N/A");
	public static RSString MS = RSString.createString("ms");
	public static RSString aClass16_3296 = RSString.createString("Ihr Charakter)2Profil wird in:");
	public static RSString aClass16_3068;
	public static RSString aClass16_3066;
	public static RSString aClass16_4483;
	public static RSString aClass16_4484 = RSString.createString("glow2:");
	public static RSString aClass16_4486;
	public static RSString aClass16_3353 = RSString.createString(" <col=ffffff>");
	public static RSString aClass16_3037;
	public static RSString aClass16_3038;
	public static RSString aClass16_3388 = RSString.createString("<col=ffb000>");
	public static RSString aClass16_3403 = RSString.createString("<br>(X100(U(Y");
	public static RSString aClass16_3336 = RSString.createString("Bitte warten Sie)3)3)3");
	public static RSString aClass16_3362;
	public static RSString aClass16_3367 = RSString.createString("Zugewiesener Speicher)3");
	public static RSString aClass16_3366;
	public static RSString aClass16_3363 = RSString.createString("Benutzen Sie die (WPasswort -=ndern(W Option");
	public static RSString aClass16_3180;
	public static RSString aClass16_3177 = RSString.createString("Ung-Ultige Verbindung mit einem Anmelde)2Server)3");
	public static RSString aClass16_3168 = RSString.createString("Ladevorgang )2 bitte warten Sie)3");
	public static RSString imgIcon = RSString.createString("<img=1>");
	public static RSString[] friends_name;
	public static RSString aClass16_3132;
	public static RSString aClass16_3496;
	public static RSString aClass16_3486 = RSString.createString("Discard");
	public static RSString aClass16_3481 = RSString.createString("http:)4)4");
	public static RSString aClass16_3108 = RSString.createString("Bitte versuchen Sie es in ");
	public static RSString aClass16_3115;
	public static RSString aClass16_3102;
	public static RSString aClass16_3418 = RSString.createString("<col=ff9040>");
	public static RSString aClass16_3419;
	public static RSString aClass16_3422 = RSString.createString("flash1:");
	public static RSString aClass16_3424;
	public static RSString aClass16_3406 = RSString.createString("Loading interfaces )2 ");
	public static RSString aClass16_3475;
	public static RSString aClass16_3478;
	public static RSString aClass16_2982;
	public static RSString aClass16_2985;
	public static int anInt3453;
	public static int[] variousSettings = new int[32];
	public static ModelList aModelList_3469;
	public static int[] anIntArray2954;
	public static RSInterface aClass64_2965;
	public static boolean aBoolean2968;
	public static short aShort3456 = 32767;
	public static int anInt3470 = 0;
	public static int result_buffer_size;
	public static Js5 modelCacheIndex;

	static {
		aClass16_4047 = RSString.createString("Das ist eine Mitglieder)2Welt(Q");
		LoginHandler.musicDisabled = false;
	}

	public static void sendWalkPacket(int i, int[] is, int[] is_15_, int i_16_) {
		int i_17_ = i;
		i--;
		if (i_17_ > 25) {
			i_17_ = 25;
		}
		int i_18_ = is[i];
		int i_19_ = is_15_[i];
		if (i_16_ == 0) {
			GameClient.outBuffer.putOpcode(149);
			GameClient.outBuffer.p1(3 + (i_17_ << 1));
		}
		if (i_16_ == 1) {
			GameClient.outBuffer.putOpcode(74);
			GameClient.outBuffer.p1((i_17_ << 1) + 17);
		}
		if (i_16_ == 2) {
			GameClient.outBuffer.putOpcode(177);
			GameClient.outBuffer.p1(3 + (i_17_ << 1));
		}
		GameClient.outBuffer.putLEShortA(MapLoader.region_aboslute_x + i_18_);
		GameClient.outBuffer.putLEShort(i_19_ + MapLoader.region_aboslute_z);
		GameClient.outBuffer.putByteS(StaticMethods2.keysPressed[82] ? 1 : 0);
		ComponentMinimap.flag_x = is_15_[0]; // Minimap flag
		ComponentMinimap.flag_y = is[0];
		for (int i_20_ = 1; i_20_ < i_17_; i_20_++) {
			i--;
			GameClient.outBuffer.putByteA(-i_19_ + is_15_[i]);
			GameClient.outBuffer.putByteA(-i_18_ + is[i]);
		}
	}

	public static void reset_frametimer() {
		GameShell.frame_timebase.method188(-34);
		for (int i = 0; i < 32; i++) {
			UpdateServerNode.aLongArray2334[i] = 0L;
		}
		for (int i = 0; i < 32; i++) {
			Queue.aLongArray421[i] = 0L;
		}
		GameShell.missed_logic_steps = 0;
	}

	public static boolean method779(boolean bool, int i, long l, int i_3_) {
		int type = 0x1f & (int) l >> 14;
		int rotation = 0x3 & (int) l >> 20;
		if (bool != true) {
			StaticMethods.aClass16_4048 = null;
		}
		int objectId = (int) (l >>> 32) & 0x7fffffff;
		if (type == 10 || type == 11 || type == 22) {
			LocType def = LocTypeList.list(objectId);
			int i_7_;
			int i_8_;
			if ((rotation ^ 0xffffffff) != -1 && rotation != 2) {
				i_7_ = def.size3d;
				i_8_ = def.size2d;
			} else {
				i_7_ = def.size2d;
				i_8_ = def.size3d;
			}
			int walkingFlag = def.walkingFlag;
			if (rotation != 0) {
				walkingFlag = (walkingFlag << rotation & 0xf) + (walkingFlag >> 4 - rotation);
			}
			GameClient.walkPath(0, i_3_, 2, i, true, !bool, 0, walkingFlag, GameClient.currentPlayer.waypointsX[0], i_8_, GameClient.currentPlayer.waypointsY[0], i_7_);
		} else {
			GameClient.walkPath(1 + type, i_3_, 2, i, true, !bool, rotation, 0, GameClient.currentPlayer.waypointsX[0], 0, GameClient.currentPlayer.waypointsY[0], 0);
		}
		GameClient.crossX = Mouse.mouseClickX;
		GameClient.cross_index = 0;
		GameClient.cross_type = 2;
		GameShell.crossY = Mouse.mouseClickY;
		return true;
	}

	public static void method781(byte b) {
		StaticMethods.aClass16_4041 = null;
		StaticMethods.aClass16_4047 = null;
		StaticMethods.aClass16_4048 = null;
	}

	public static void method784(Js5 class105) {
		InvType.invTypeContainer = class105;
	}

	public static final void method834(byte var0) {
		WorldMap.reset(43, false);
		System.gc();
		GameClient.updateClientState(25);
	}

	public static void method597(int i) {
		if (GroundItem.loginscreen_loaded) {
			Class87_Sub4.anIntArray2828 = null;
			LobbyWorld.members_star_sprites = null;
			StaticMethods.anIntArray3224 = null;
			LoginHandler.login_button = null;
			LoginHandler.login_button_hovered = null;
			LoginHandler.login_button_unhovered = null;
			Stereo.anIntArray144 = null;
			InteractiveEntity.anIntArray618 = null;
			LoginHandler.primary_login_box = null;
			LobbyWorld.world_list_arrows_sprites = null;
			ISAACPacket.anIntArray3543 = null;
			Class49.aClass23_Sub13_Sub10_Sub1_754 = null;
			GroundDecoration.logoImage = null;
			LobbyWorld.country_flags_sprites = null;
			Class35.anIntArray557 = null;
			CollisionMap.aIndexedSpriteArray1303 = null;
			LobbyWorld.selectable_world_sprites = null;
			StaticMethods2.aClass23_Sub13_Sub10_Sub1_3880 = null;
			LoginHandler.world_switch_button = null;
			LoginHandler.world_switch_button_hovered = null;
			LoginHandler.world_switch_button_unhovered = null;
			Class28.anIntArray425 = null;
			Class71_Sub2_Sub1.anIntArray4473 = null;
			StaticMethods.method333(-257, 2);
			GameClient.js5_client.notify_login(i ^ i, true);
			GroundItem.loginscreen_loaded = false;
		}
	}

	public static void method598(byte b) {
		LobbyWorld.world_list_arrows_sprites = null;
		aClass16_2290 = null;
	}

	public static void method412(int i) {
		if (i != -22323) {
			method412(-112);
		}
		CS2Runtime.call_stack = null;
		aModelList_3469 = null;
	}

	public static int method413(int i, byte b, int i_2_, int i_3_) {
		i &= 0x3;
		if (i == 0) {
			return i_2_;
		}
		if (i == 1) {
			return 7 + -i_3_;
		}
		if (i == 2) {
			return -i_2_ + 7;
		}
		if (b < 63) {
			StaticMethods.method414();
		}
		return i_3_;
	}

	static {
		StaticMethods.aModelList_3469 = new ModelList(5);
	}

	public static void method414() {
		for (int i = 0; i < Scene.num_offscreen_entities; i++) {
			InteractiveEntity interactiveObject = Scene.offscreen_entities[i];
			CS2ScriptDefinition.method844(interactiveObject);
			Scene.offscreen_entities[i] = null;
		}
		Scene.num_offscreen_entities = 0;
	}

	static {
		anIntArray3164 = new int[] { 1, -1, -1, 1 };
		aClass16_3180 = RSString.createString("::fpson");
	}

	public static void method335(boolean bool) {
		StaticMethods.anIntArray3164 = null;
		StaticMethods.aClass16_3168 = null;
		InputManager.mouse_wheel = null;
		StaticMethods.aClass16_3177 = null;
		StaticMethods.aClass16_3180 = null;
		EntityUpdating.localNPCIndexes = null;
	}

	public static int[] anIntArray3164;
	public static int anInt3167 = 1;
	public static MusicDefinition aClass23_Sub18_2952;
	static {
		chatOwnerName = null;
		anIntArray2954 = new int[4096];
		for (int i = 0; i < 4096; i++) {
			anIntArray2954[i] = Class56.method1182(i, true);
		}
		aClass16_2964 = RSString.createString("::clientdrop");
		aClass16_2966 = RSString.createString("Your ignore list is full)3 Max of 100 users)3");
		aClass16_2963 = aClass16_2966;
	}

	public static void method284(boolean bool) {
		aClass23_Sub18_2952 = null;
		StaticMethods.aClass16_2966 = null;
		StaticMethods.aClass16_2960 = null;
		if (bool == true) {
			StaticMethods.aClass16_2964 = null;
			StaticMethods.chatOwnerName = null;
			imgIcon = null;
			StaticMethods.anIntArray2954 = null;
			StaticMethods.aClass16_2963 = null;
			StaticMethods.aClass64_2965 = null;
		}
	}

	public static void method410(byte b) {
		if (b >= 9) {
			StaticMethods.aClass16_3458 = null;
		}
	}

	static {
		StaticMethods.anInt3453 = 0;
	}
	static {
		int i = 2;
		for (int i_16_ = 0; i_16_ < 32; i_16_++) {
			StaticMethods.variousSettings[i_16_] = i + -1;
			i += i;
		}
		StaticMethods.aClass16_3237 = RSString.createString("(U4");
	}

	public static void method348(byte b) {
		aClass16_3237 = null;
		variousSettings = null;
		aClass16_3230 = null;
	}

	public static int[][] pathCost;

	public static void method383(int i) {
		aClass16_3367 = null;
		if (i < 49) {
			pathCost = null;
		}
		aClass16_3366 = null;
		aClass16_3363 = null;
		pathCost = null;
		aClass16_3362 = null;
	}

	static {
		StaticMethods.aClass16_3366 = RSString.createString("Allocated memory");
		StaticMethods.aClass16_3362 = StaticMethods.aClass16_3366;
		StaticMethods.pathCost = new int[104][104];
	}

	public static CRC32 aCRC32_3337 = new CRC32();
	static {
		aClass16_3344 = RSString.createString("Cancel");
		aClass16_3338 = aClass16_3344;
		aClass16_3345 = RSString.createString(" ");
	}

	public static void method381(boolean bool) {
		StaticMethods.aClass16_3344 = null;
		aClass16_3336 = null;
		AnimFrameset.framesJs5 = null;
		StaticMethods.aClass16_3338 = null;
		StaticMethods.aClass16_3345 = null;
		if (bool == true) {
			aCRC32_3337 = null;
		}
	}

	public static byte currentUserClanRights;
	public static int anInt3139;

	public static void method326(boolean bool) {
		if (bool != true) {
			method326(false);
		}
		if (ColourImageCacheSlot.session != null) {
			GameClient.pingManager.disconnect();
			ColourImageCacheSlot.session.shutdown();
			ColourImageCacheSlot.session = null;
		}
		GraphicsCache.removeAll();
		Scene.reset_scene();
		for (int i = 0; i < 4; i++) {
			MapLoader.collision_maps[i].method1298(113);
		}
		WorldMap.reset(62, false);
		System.gc();
		StaticMethods.method333(-257, 2);
		Js5.aBoolean1806 = false;
		MapLoader.region_aboslute_z = 0;
		MapLoader.region_aboslute_x = 0;
		WallObject.musicId = -1;
		ObjectNode.method336();
		GameClient.updateClientState(10);
	}

	public static boolean method327(int i, int i_17_, int i_18_, int i_19_, int i_20_, int i_21_) {
		if (i_17_ == i_18_ && i_19_ == i_20_) {
			if (!Scene.method846(i, i_17_, i_19_)) {
				return false;
			}
			int i_22_ = i_17_ << 7;
			int i_23_ = i_19_ << 7;
			if (Scene.is_culled(i_22_ + 1, Scene.current_heightmap[i][i_17_][i_19_] + i_21_, i_23_ + 1) && Scene.is_culled(i_22_ + 128 - 1, Scene.current_heightmap[i][i_17_ + 1][i_19_] + i_21_, i_23_ + 1) && Scene.is_culled(i_22_ + 128 - 1, Scene.current_heightmap[i][i_17_ + 1][i_19_ + 1] + i_21_, i_23_ + 128 - 1) && Scene.is_culled(i_22_ + 1, Scene.current_heightmap[i][i_17_][i_19_ + 1] + i_21_, i_23_ + 128 - 1)) {
				return true;
			}
			return false;
		}
		for (int i_24_ = i_17_; i_24_ <= i_18_; i_24_++) {
			for (int i_25_ = i_19_; i_25_ <= i_20_; i_25_++) {
				if (Scene.anIntArrayArrayArray1142[i][i_24_][i_25_] == -Class73.anInt1321) {
					return false;
				}
			}
		}
		int i_26_ = (i_17_ << 7) + 1;
		int i_27_ = (i_19_ << 7) + 2;
		int i_28_ = Scene.current_heightmap[i][i_17_][i_19_] + i_21_;
		if (!Scene.is_culled(i_26_, i_28_, i_27_)) {
			return false;
		}
		int i_29_ = (i_18_ << 7) - 1;
		if (!Scene.is_culled(i_29_, i_28_, i_27_)) {
			return false;
		}
		int i_30_ = (i_20_ << 7) - 1;
		if (!Scene.is_culled(i_26_, i_28_, i_30_)) {
			return false;
		}
		if (!Scene.is_culled(i_29_, i_28_, i_30_)) {
			return false;
		}
		return true;
	}

	public static RSInterface fromInterface = null;

	public static void method296(int i) {
		if (i == 1) {
			fromInterface = null;
		}
	}

	public static RSString empty_string;
	public static RSString aClass16_3499;
	public static RSString aClass16_3506;

	public static void method420(int i) {
		if (i != 0) {
			StaticMethods.method422(false, 34, (byte) -49, 97);
		}
		aClass16_3499 = null;
		empty_string = null;
		aClass16_3506 = null;
		Scene.offscreen_entities = null;
	}

	public static void method422(boolean bool, int i, byte b, int i_21_) {
		@SuppressWarnings("unused")
		int i_22_ = 88 % ((b - -24) / 59);
		if (i < 8000 || i > 48000) {
			throw new IllegalArgumentException();
		}
		FileSystem.stereo = bool;
		Keyboard.sampleRate = i;
		RSInterface.anInt1030 = i_21_;
	}

	public static int anInt3510;

	static {
		StaticMethods.aClass16_3499 = RSString.createString("Loading wordpack )2 ");
		StaticMethods.empty_string = RSString.createString("");
		StaticMethods.aClass16_3506 = StaticMethods.aClass16_3499;
		StaticMethods.anInt3510 = 0;
	}

	public static int anInt3396;
	public static int anInt3400;

	static {
		StaticMethods.anInt3400 = 0;
	}

	public static int method400(int i, int i_55_, int i_56_, int i_57_) {
		int i_58_ = 256 - i_55_;
		if (i_56_ != 1021638856) {
			StaticMethods.method397(false);
		}
		return (i_58_ * (0xff00 & i_57_) + (0xff00 & i) * i_55_ & 0xff0000) + (i_58_ * (i_57_ & 0xff00ff) - -(i_55_ * (0xff00ff & i)) & ~0xff00ff) >> 8;
	}

	public static void method397(boolean bool) {
		aClass16_3403 = null;
		if (bool == true) {
			aClass16_3388 = null;
			ContextMenu.menuActionCmd3 = null;
		}
	}

	public static RSString[] method396(int i, RSString[] class16s) {
		RSString[] class16s_38_ = new RSString[5];
		if (i != 30113) {
			method397(false);
		}
		for (int i_39_ = 0; i_39_ < 5; i_39_++) {
			class16s_38_[i_39_] = RSString.joinRsStrings(new RSString[] { RSString.valueOf(i_39_), Class23_Sub16.aClass16_2351 });
			if (class16s != null && class16s[i_39_] != null) {
				class16s_38_[i_39_] = RSString.joinRsStrings(new RSString[] { class16s_38_[i_39_], class16s[i_39_] });
			}
		}
		return class16s_38_;
	}

	public static RSInterface[][] cached_interfaces;
	public static int[][] pathVia = new int[104][104];
	public static SignLink aSignLink_3348;

	public static void method382(int i) {
		cached_interfaces = null;
		StaticMethods.aClass16_3353 = null;
		pathVia = null;
		aSignLink_3348 = null;
		if (i != 104) {
			method382(102);
		}
	}

	public static void method297(byte b) {
		if (b != 74) {
			SceneController.method298(111, 2, 35, 59L);
		}
		aClass16_3037 = null;
		StaticMethods.quadsx = null;
		GameShell.frame_timebase = null;
		aClass16_3038 = null;
	}

	public static void method299(int i, int i_4_, int i_5_, int i_6_, int i_7_, int i_8_, int i_9_, int i_10_) {
		if (AbstractTimer.hasActiveInterface(-10924, i_4_)) {
			Class47.handleItemSwitch(-1, i_9_, i, i_7_, cached_interfaces[i_4_], i_10_, i_8_, i_6_ ^ 0x33, i_5_);
		}
	}

	static {
		quadsx = new int[100];
		StaticMethods.aClass16_3038 = RSString.createString("k");
		StaticMethods.aClass16_3037 = RSString.createString("Lade Benutzeroberfl-=che )2 ");
	}
	public static int[] quadsx;
	public static int anInt3036 = 0;

	public static void method363(int i) {
		RSInterfaceList.is_dirty = null;
	}

	static {
		StaticMethods.aClass16_4483 = StaticMethods.aClass16_4484;
		StaticMethods.aClass16_4486 = StaticMethods.aClass16_4484;
	}
	public static int anInt3071;
	public static int anInt3067 = 0;

	public static void method308(byte b) {
		if (b < -67) {
			aClass16_3066 = null;
			modelCacheIndex = null;
			aClass16_3068 = null;
		}
	}

	static {
		StaticMethods.aClass16_3066 = RSString.createString("Click to switch");
		StaticMethods.aClass16_3068 = StaticMethods.aClass16_3066;
		StaticMethods.anInt3071 = 0;
	}

	public static void method1401(boolean bool) {
		if (bool == false) {
			aClass16_1861 = null;
			aClass16_1871 = null;
			aClass16_1862 = null;
			aClass16_1858 = null;
		}
	}

	public static int method306(int i, int i_2_, int i_3_) {
		int i_4_ = 1;
		while (i_2_ > 1) {
			if ((0x1 & i_2_ ^ 0xffffffff) != -1) {
				i_4_ *= i_3_;
			}
			i_2_ >>= 1;
			i_3_ *= i_3_;
		}
		if (i_2_ == 1) {
			return i_4_ * i_3_;
		}
		return i_4_;
	}

	public static void method372(int i) {
		aClass16_3294 = null;
		if (i != 5864) {
			aClass16_3294 = null;
		}
		aClass16_3296 = null;
	}

	public static SoftwarePaletteSprite[] method370(Js5 class105, boolean bool, int i) {
		if (bool != false) {
			return null;
		}
		if (!SpriteLoader.cache_sprite(class105, i)) {
			return null;
		}
		return StaticMethods.method263(111);
	}

	public static int anInt3291 = 2;
	public static int anInt3300;

	static {
		str_global_vars = new RSString[1000];
	}

	public static void method283(byte b) {
		int[] is = new int[ObjType.itemDefinitionSize];
		int i = 0;
		for (int i_35_ = 0; i_35_ < ObjType.itemDefinitionSize; i_35_++) {
			is[i++] = i_35_;
		}
		StaticMethods2.anIntArray1710 = new int[i];
		if (b <= -107) {
			for (int i_36_ = 0; i > i_36_; i_36_++) {
				StaticMethods2.anIntArray1710[i_36_] = is[i_36_];
			}
		}
	}

	public static void method280(int i) {
		StaticMethods.aClass16_2941 = null;
		LocTypeList.models_js5 = null;
		Player.modelsCache = null;
		GameClient.gameSignlink = null;
		StaticMethods.str_global_vars = null;
		LoginHandler.logoArchiveName = null;
		if (i != 0) {
			StaticMethods.aClass16_2941 = null;
		}
	}

	public static RSString[] str_global_vars;

	public static void method423(int i, int i_0_, int i_1_, int i_2_, int i_3_, byte b, int i_4_, int i_5_) {
		int i_6_ = i_3_ + i_2_;
		int i_7_ = -i_2_ + i_4_;
		int i_8_ = i_2_ + i;
		int i_9_ = -i_2_ + i_5_;
		for (int i_10_ = i; (i_10_ ^ 0xffffffff) > (i_8_ ^ 0xffffffff); i_10_++) {
			VarpDefinition.method632(i_4_, (byte) -30, i_0_, Class4.anIntArrayArray98[i_10_], i_3_);
		}
		for (int i_11_ = i_5_; (i_9_ ^ 0xffffffff) > (i_11_ ^ 0xffffffff); i_11_--) {
			VarpDefinition.method632(i_4_, (byte) -30, i_0_, Class4.anIntArrayArray98[i_11_], i_3_);
		}
		for (int i_12_ = i_8_; i_9_ >= i_12_; i_12_++) {
			int[] is = Class4.anIntArrayArray98[i_12_];
			VarpDefinition.method632(i_6_, (byte) -30, i_0_, is, i_3_);
			VarpDefinition.method632(i_7_, (byte) -30, i_1_, is, i_6_);
			VarpDefinition.method632(i_4_, (byte) -30, i_0_, is, i_7_);
		}
	}

	public static void method424(SoftwarePaletteSprite indexedSprite, byte b) {
		int i = 256;
		for (int i_13_ = 0; (Class87_Sub4.anIntArray2828.length ^ 0xffffffff) < (i_13_ ^ 0xffffffff); i_13_++) {
			Class87_Sub4.anIntArray2828[i_13_] = 0;
		}
		int i_14_ = 0;
		for (/**/; i_14_ < 5000; i_14_++) {
			int i_15_ = (int) (i * (Math.random() * 128.0));
			Class87_Sub4.anIntArray2828[i_15_] = (int) (256.0 * Math.random());
		}
		for (int i_16_ = 0; i_16_ < 20; i_16_++) {
			for (int i_17_ = 1; i_17_ < -1 + i; i_17_++) {
				for (int i_18_ = 1; i_18_ < 127; i_18_++) {
					int i_19_ = i_18_ + (i_17_ << 7);
					Class28.anIntArray425[i_19_] = (Class87_Sub4.anIntArray2828[i_19_ - 128] + Class87_Sub4.anIntArray2828[-1 + i_19_] + Class87_Sub4.anIntArray2828[1 + i_19_] + Class87_Sub4.anIntArray2828[128 + i_19_]) / 4;
				}
			}
			int[] is = Class87_Sub4.anIntArray2828;
			Class87_Sub4.anIntArray2828 = Class28.anIntArray425;
			Class28.anIntArray425 = is;
		}
		if (indexedSprite != null) {
			int i_20_ = 0;
			for (int i_21_ = 0; indexedSprite.height > i_21_; i_21_++) {
				for (int i_22_ = 0; i_22_ < indexedSprite.width; i_22_++) {
					if (indexedSprite.pixels[i_20_++] != 0) {
						int i_23_ = 16 + i_22_ + indexedSprite.offset_x;
						int i_24_ = i_21_ + 16 + indexedSprite.offset_y;
						int i_25_ = i_23_ - -(i_24_ << 7);
						Class87_Sub4.anIntArray2828[i_25_] = 0;
					}
				}
			}
		}
	}

	public static void method425(byte b) {
		QuickChatDefinition.globalQuickChatContainer = null;
		LoginHandler.enterUsernameAndPasswordText = null;
		StaticMethods.aClass98_3513 = null;
	}

	public static boolean aBoolean3516 = false;
	public static int anInt3519 = 0;
	public static Class98 aClass98_3513;
	public static boolean[] aBooleanArray3325 = new boolean[100];

	public static void method377(boolean bool) {
		aClass16_3316 = null;
		if (bool != false) {
			EntityUpdating.renderLocalNPCs(-34);
		}
		aClass16_3320 = null;
		aBooleanArray3325 = null;
		GameClient.configs = null;
	}

	public static RSString aClass16_3304;
	public static int widget_quads = 0;
	public static int anInt3309;
	public static RSString aClass16_3314;

	public static void method374(int i) {
		aClass16_3314 = null;
		CacheConstants.localQuickChatIdx = null;
		aClass16_3304 = null;
		if (i != -2) {
			SceneController.method375(119, 66, -34);
		}
	}

	static {
		StaticMethods.aClass16_3304 = RSString.createString("Fertigkeit)2");
		StaticMethods.anInt3309 = 7759444;
		StaticMethods.aClass16_3314 = RSString.createString("::autoshadow on");
	}

	public static volatile int anInt3289 = 0;
	public static int anInt3279;

	public static void method362() {
		aClass16_3288 = null;
		Scene.anIntArray3285 = null;
		aClass16_3275 = null;
		MapLoader.square_map_ids = null;
	}

	static {
		StaticMethods.aClass16_3288 = RSString.createString(")4l");
	}
	public static MemoryCache aJList_2972;

	public static boolean aBoolean2977;

	public static void method286(byte b) {
		if (b <= 88) {
			aBoolean2977 = false;
		}
		aJList_2972 = null;
	}

	static {
		StaticMethods.aJList_2972 = new MemoryCache(64);
		StaticMethods.aBoolean2977 = false;
	}

	public static int method354(int i, int i_0_, int i_1_) {
		int i_3_ = Class87.method1403(i_1_ - -45365, 4, (byte) -31, 91923 + i_0_) - (128 + -(-128 + Class87.method1403(i_1_ - -10294, 2, (byte) -46, i_0_ + 37821) >> 1) - (Class87.method1403(i_1_, 1, (byte) -63, i_0_) + -128 >> 2));
		i_3_ = 35 + (int) (0.3 * i_3_);
		if (i_3_ >= 10) {
			if (i_3_ > 60) {
				i_3_ = 60;
			}
		} else {
			i_3_ = 10;
		}
		return i_3_;
	}

	public static void method357(int i) {
		MapLoader.rotations = null;
		StaticMethods.queueY = null;
		aClass16_3270 = null;
		if (i <= 99) {
			StaticMethods.anInt3262 = 65;
		}
		aClass16_3258 = null;
	}

	static {
		queueY = new int[4096];
	}
	public static int[] queueY;
	public static int anInt3262;

	static {
		aClass16_3439 = RSString.createString("wave:");
		aClass16_3437 = aClass16_3439;
		aClass16_3445 = RSString.createString("gleiten:");
		aClass16_3440 = aClass16_3439;
		aClass16_3450 = RSString.createString("(U(Y");
	}

	public static void method409(int i) {
		StaticMethods.aClass16_3437 = null;
		StaticMethods.aClass16_3439 = null;
		GameClient.setIp(null);
		StaticMethods.aClass16_3450 = null;
		StaticMethods.aClass16_3445 = null;
		if (i != -9947) {
			StaticMethods.aClass16_3440 = null;
		}
		StaticMethods.aClass16_3440 = null;
	}

	public static volatile int anInt3449 = -1;
	public static int[] anIntArray3128;
	public static Js5 aClass105_3119;
	public static int anInt3120;

	public static RSString method322(RSString[] class16s, int i) {
		if (class16s.length < 2) {
			throw new IllegalArgumentException();
		}
		return RSString.joinRsStrings(class16s, -32768, 0, class16s.length);
	}

	static {
		StaticMethods.anInt3120 = -2;
		StaticMethods.anIntArray3128 = new int[5];
		StaticMethods.aClass16_3132 = RSString.createString("Clientscript error in: ");
		StaticMethods.friends_name = new RSString[200];
	}

	public static void method324(boolean bool) {
		aClass105_3119 = null;
		anIntArray3128 = null;
		friends_name = null;
		aClass16_3132 = null;
		if (bool != true) {
			anIntArray3128 = null;
		}
	}

	static {
		aShortArray3190 = new short[] { 960, 957, -21568, -21571, 22464 };
		aClass16_3199 = RSString.createString("Bitte warten Sie eine Minute");
	}

	public static void method338(byte b) {
		StaticMethods.aClass16_3199 = null;
		StaticMethods.anIntArray3183 = null;
		MapLoader.overlays = null;
		if (b > 9) {
			StaticMethods.aShortArray3190 = null;
		}
	}

	public static RSString aClass16_3199;
	public static short[] aShortArray3190;
	public static int[] anIntArray3183;

	static {
		aClass16_3416 = aClass16_3422;
		aBoolean3413 = true;
		aClass16_3424 = aClass16_3406;
		aClass16_3419 = aClass16_3422;
	}

	public static int method405(int i, int i_19_, int i_20_, int i_21_) {
		if (i < 47) {
			return 107;
		}
		if ((i_20_ ^ 0xffffffff) > (i_19_ ^ 0xffffffff)) {
			return i_19_;
		}
		if ((i_20_ ^ 0xffffffff) >= (i_21_ ^ 0xffffffff)) {
			return i_20_;
		}
		return i_21_;
	}

	public static void method403(int i) {
		if (i <= -93) {
			StaticMethods.aClass16_3416 = null;
			StaticMethods.aClass16_3418 = null;
			StaticMethods.aClass16_3419 = null;
			StaticMethods.aShortArray3417 = null;
			StaticMethods.aClass16_3422 = null;
			StaticMethods.aClass16_3406 = null;
			StaticMethods.aClass16_3424 = null;
		}
	}

	public static boolean aBoolean3413;
	public static RSString aClass16_3416;
	public static short[] aShortArray3417;

	static {
		StaticMethods.aClass16_3478 = RSString.createString("Wordpack geladen)3");
		StaticMethods.aClass16_3475 = aClass16_3486;
		aClass16_3496 = RSString.createString("Willkommen auf RuneScape");
		aLong3497 = 0L;
	}

	public static void method418(int i, int i_19_, int i_20_, int i_21_, int i_22_, int i_23_) {
		for (int i_24_ = i_23_; i_22_ >= i_24_; i_24_++) {
			VarpDefinition.method632(i_20_, (byte) -30, i_19_, Class4.anIntArrayArray98[i_24_], i);
		}
		if (i_21_ != 0) {
			aClass16_3475 = null;
		}
	}

	public static void method416(byte b) {
		if (b != -23) {
			StaticMethods.aClass16_3481 = null;
		}
		CacheConstants.soundEffectsCacheIdx = null;
		StaticMethods.aClass16_3496 = null;
		StaticMethods.aClass16_3486 = null;
		aClass16_3475 = null;
		aClass16_3478 = null;
		StaticMethods.aClass16_3481 = null;
	}

	public static volatile long aLong3497;

	public static int[] anIntArray3097;
	public static int anInt1357 = 0;

	static {
		StaticMethods.anIntArray3097 = new int[] { 768, 1024, 1280, 512, 1536, 256, 0, 1792 };
		StaticMethods.aClass16_3102 = RSString.createString("Unerwartete Antwort vom Anmelde)2Server)3");
		StaticMethods.aClass16_3115 = RSString.createString("3D)2Softwarebibliothek gestartet)3");
	}

	public static void method319(int i) {
		anIntArray3097 = null;
		aClass16_3102 = null;
		if (i != 0) {
			aClass16_3108 = null;
		}
		aClass16_3115 = null;
		aClass16_3108 = null;
		CacheConstants.animSequenceCacheIdx = null;
	}

	public static int anInt2989;
	public static RSString aClass16_2993;

	public static void method288(int i) {
		GameClient.data_file = null;
		if (i == -31116) {
			GameShell.setLoadingFont(null);
			aClass16_2985 = null;
			aClass16_2993 = null;
			aClass16_2982 = null;
		}
	}

	static {
		StaticMethods.anInt2989 = -1;
		InputManager.anInt2986 = 0;
		StaticMethods.aClass16_2985 = RSString.createString("yellow:");
		StaticMethods.aClass16_2982 = StaticMethods.aClass16_2985;
		StaticMethods.aClass16_2993 = StaticMethods.aClass16_2985;
	}
	public static int anInt3075 = 0;

	public static void method313(int i, boolean bool) {
		if (i != -1 && AbstractTimer.hasActiveInterface(-10924, i)) {
			RSInterface[] class64s = cached_interfaces[i];
			int i_67_ = 0;
			if (bool == true) {
				for (/**/; (class64s.length ^ 0xffffffff) < (i_67_ ^ 0xffffffff); i_67_++) {
					RSInterface inter = class64s[i_67_];
					if (inter.onStartListener != null) {
						CS2Event script = new CS2Event();
						script.scriptArguments = inter.onStartListener;
						script.component = inter;
						CS2Runtime.parseCS2Script(10000000, script, true);
					}
				}
			}
		}
	}

	public static void method269(int i) {
		if (!ClientOptions.is_removeroofs() && StaticMethods.player_height != ObjType.localHeight) {
			EntityUpdating.method342(ObjType.localHeight, GameClient.currentPlayer.waypointsX[0], 31362, GameClient.currentPlayer.waypointsY[0], RSInterface.anInt1138, anInt3279, false);
		} else if ((ComponentMinimap.last_rendered_level ^ 0xffffffff) != (ObjType.localHeight ^ 0xffffffff)) {
			if (ComponentMinimap.renderMapScene(ObjType.localHeight, (byte) 75)) {
				ComponentMinimap.last_rendered_level = ObjType.localHeight;
				RoofCulling.update_culler_type();
			}
		}
	}

	public static void method270(byte b, int i) {
		int i_20_ = 256;
		ClientInventory.anInt2372 += 128 * i;
		if ((ClientInventory.anInt2372 ^ 0xffffffff) < (Class87_Sub4.anIntArray2828.length ^ 0xffffffff)) {
			int i_21_ = (int) (Math.random() * 12.0);
			ClientInventory.anInt2372 -= Class87_Sub4.anIntArray2828.length;
			method424(CollisionMap.aIndexedSpriteArray1303[i_21_], (byte) 120);
		}
		if (b < 111) {
			method270((byte) -1, 66);
		}
		int i_22_ = 0;
		int i_23_ = (i_20_ + -i) * 128;
		int i_24_ = 128 * i;
		for (int i_25_ = 0; (i_25_ ^ 0xffffffff) > (i_23_ ^ 0xffffffff); i_25_++) {
			int i_26_ = StaticMethods.anIntArray3224[i_22_ - -i_24_] - Class87_Sub4.anIntArray2828[ClientInventory.anInt2372 + i_22_ & Class87_Sub4.anIntArray2828.length + -1] * i / 6;
			if ((i_26_ ^ 0xffffffff) > -1) {
				i_26_ = 0;
			}
			StaticMethods.anIntArray3224[i_22_++] = i_26_;
		}
		for (int i_27_ = i_20_ - i; i_20_ > i_27_; i_27_++) {
			int i_28_ = i_27_ * 128;
			for (int i_29_ = 0; i_29_ < 128; i_29_++) {
				int i_30_ = (int) (100.0 * Math.random());
				if (i_30_ >= 50 || i_29_ <= 10 || i_29_ >= 118) {
					StaticMethods.anIntArray3224[i_29_ - -i_28_] = 0;
				} else {
					StaticMethods.anIntArray3224[i_28_ + i_29_] = 255;
				}
			}
		}
		if (LocType.anInt3784 > 0) {
			LocType.anInt3784 -= i * 4;
		}
		if (StaticMethods.anInt2281 > 0) {
			StaticMethods.anInt2281 -= i * 4;
		}
		if (LocType.anInt3784 == 0 && (StaticMethods.anInt2281 ^ 0xffffffff) == -1) {
			int i_31_ = (int) (Math.random() * (2000 / i));
			if (i_31_ == 0) {
				LocType.anInt3784 = 1024;
			}
			if (i_31_ == 1) {
				StaticMethods.anInt2281 = 1024;
			}
		}
		for (int i_32_ = 0; -i + i_20_ > i_32_; i_32_++) {
			Keyboard.anIntArray501[i_32_] = Keyboard.anIntArray501[i_32_ - -i];
		}
		for (int i_33_ = -i + i_20_; i_33_ < i_20_; i_33_++) {
			Keyboard.anIntArray501[i_33_] = (int) (Math.sin(BufferedRequest.anInt4305 / 14.0) * 16.0 + 14.0 * Math.sin(BufferedRequest.anInt4305 / 15.0) + 12.0 * Math.sin(BufferedRequest.anInt4305 / 16.0));
			BufferedRequest.anInt4305++;
		}
		VarpDefinition.anInt3734 += i;
		int i_34_ = ((0x1 & GameClient.timer) + i) / 2;
		if (i_34_ > 0) {
			for (int i_35_ = 0; i_35_ < VarpDefinition.anInt3734 * 100; i_35_++) {
				int i_36_ = (int) (124.0 * Math.random()) + 2;
				int i_37_ = 128 + (int) (128.0 * Math.random());
				StaticMethods.anIntArray3224[(i_37_ << 7) + i_36_] = 192;
			}
			VarpDefinition.anInt3734 = 0;
			for (int i_38_ = 0; i_38_ < i_20_; i_38_++) {
				int i_39_ = 128 * i_38_;
				int i_40_ = 0;
				for (int i_41_ = -i_34_; i_41_ < 128; i_41_++) {
					if (i_34_ + i_41_ < 128) {
						i_40_ += StaticMethods.anIntArray3224[i_34_ + i_41_ - -i_39_];
					}
					if ((-i_34_ + -1 + i_41_ ^ 0xffffffff) <= -1) {
						i_40_ -= StaticMethods.anIntArray3224[i_39_ + i_41_ - (i_34_ + 1)];
					}
					if ((i_41_ ^ 0xffffffff) <= -1) {
						Class71_Sub2_Sub1.anIntArray4473[i_39_ + i_41_] = i_40_ / (1 + 2 * i_34_);
					}
				}
			}
			for (int i_42_ = 0; i_42_ < 128; i_42_++) {
				int i_43_ = 0;
				for (int i_44_ = -i_34_; (i_44_ ^ 0xffffffff) > (i_20_ ^ 0xffffffff); i_44_++) {
					int i_45_ = i_44_ * 128;
					if (i_20_ > i_34_ + i_44_) {
						i_43_ += Class71_Sub2_Sub1.anIntArray4473[128 * i_34_ + i_42_ - -i_45_];
					}
					if ((i_44_ + -i_34_ - 1 ^ 0xffffffff) <= -1) {
						i_43_ -= Class71_Sub2_Sub1.anIntArray4473[i_42_ + i_45_ + -128 + -(128 * i_34_)];
					}
					if ((i_44_ ^ 0xffffffff) <= -1) {
						StaticMethods.anIntArray3224[i_42_ - -i_45_] = i_43_ / (2 * i_34_ - -1);
					}
				}
			}
		}
	}

	public static void method273(RSInterface class64, byte b, int i, int i_50_) {
		if (fromInterface == null && !ContextMenu.menuOpen) {
			if (b > -92) {
				aClass16_2906 = null;
			}
			if (class64 != null && PositionedGraphicNode.method690(class64, 38) != null) {
				fromInterface = class64;
				RSInterface.aClass64_1102 = PositionedGraphicNode.method690(class64, -36);
				StaticMethods.anInt3059 = i_50_;
				AbstractTimer.anInt304 = 0;
				GameTimer.aBoolean2003 = false;
				QuickChatDefinition.anInt4034 = i;
			}
		}
	}

	public static void method272(byte b) {
		StaticMedia.mod_icons = null;
		aClass16_2901 = null;
		aClass16_2907 = null;
		if (b >= 36) {
			aClass16_2885 = null;
			aClass16_2898 = null;
			aClass16_2896 = null;
			aClass16_2906 = null;
			SpriteLoader.palette = null;
		}
	}

	static {
		StaticMethods.aClass16_2885 = RSString.createString("Please wait )2 attempting to reestablish)3");
		StaticMethods.aClass16_2901 = RSString.createString("Ein kostenloses Spielkonto erstellen)3");
		StaticMethods.aClass16_2898 = StaticMethods.aClass16_2885;
		StaticMethods.aClass16_2907 = RSString.createString("Play Now");
		StaticMethods.aClass16_2906 = StaticMethods.aClass16_2907;
	}

	public static void method304(int i) {
		StaticMethods.aClass16_3057 = null;
		StaticMethods.aClass16_3048 = null;
		if (i != 4096) {
			StaticMethods.anIntArray3062 = null;
		}
		StaticMethods.anIntArray3062 = null;
		StaticMethods.aClass16_3050 = null;
		StaticMethods.aClass16_3045 = null;
		StaticMethods.aClass16_3047 = null;
	}

	public static int thisPlayerIndex;

	public static int anInt3055;
	public static int anInt3059;
	public static int removedEntities;
	public static int[] anIntArray3062;

	static {
		StaticMethods.thisPlayerIndex = -1;
		StaticMethods.aClass16_3057 = StaticMethods.aClass16_3045;
		StaticMethods.aClass16_3048 = RSString.createString("(Y");
		StaticMethods.anInt3059 = 0;
		StaticMethods.anInt3055 = 1;
		StaticMethods.removedEntities = 0;
		StaticMethods.anIntArray3062 = new int[] { 16, 32, 64, 128 };
	}

	public static boolean aBoolean3380 = false;

	static {
		StaticMethods.aClass16_3376 = RSString.createString("Ok");
		StaticMethods.aClass16_3378 = RSString.createString("null");
		StaticMethods.optionOK = StaticMethods.aClass16_3376;
	}

	public static void method393(int i, int i_16_, int i_17_, int i_18_, int i_19_) {
		if ((i_17_ ^ 0xffffffff) <= (Class88.anInt1503 ^ 0xffffffff) && i_17_ <= StaticMethods.anInt3435) {
			i_16_ = method405(49, VarpDefinition.anInt3728, i_16_, Class35.anInt554);
			i_18_ = method405(75, VarpDefinition.anInt3728, i_18_, Class35.anInt554);
			Class56.method1187(false, i_19_, i_18_, i_16_, i_17_);
		}
		if (i != 1) {
			aClass16_3378 = null;
		}
	}

	public static void method392(boolean bool) {
		PlayerRelations.ignoreList = null;
		aClass16_3378 = null;
		if (bool != true) {
			method393(91, -23, 22, -123, -28);
		}
		optionOK = null;
		aClass16_3376 = null;
	}

	public static int player_height;
	public static int regionChunkX;
	public static int anInt3011 = 0;
	public static boolean aBoolean3012;
	public static boolean aBoolean3018;
	public static int anInt3027;

	static {
		StaticMethods.clanChatName = null;
		StaticMethods.aClass16_3020 = RSString.createString("Weiter");
		StaticMethods.aBoolean3012 = false;
		StaticMethods.player_height = 0;
		StaticMethods.anInt3027 = -1;
	}

	public static void method294(int i, int i_15_, int i_16_, int i_17_, int i_18_, int i_19_, int i_20_, int i_21_) {
		if ((i_15_ ^ 0xffffffff) > (VarpDefinition.anInt3728 ^ 0xffffffff) || i_17_ > Class35.anInt554 || (i_20_ ^ 0xffffffff) > (Class88.anInt1503 ^ 0xffffffff) || i_21_ > StaticMethods.anInt3435) {
			StaticMethods2.method888(i_18_, i_17_, i_19_ ^ 0x801, i_15_, i_21_, i_16_, i, i_20_);
		} else {
			method423(i_20_, i_18_, i_16_, i, i_15_, (byte) 88, i_17_, i_21_);
		}
	}

	public static PaletteSprite[] getSprites(String archiveName, Js5 class105, String class101, boolean bool) {

		int archiveId = class105.get_groupid(archiveName);
		if (archiveName.equals("mod_icons")) {
			archiveId = 1455;
		}
		int i_13_ = 0;
		if (bool != true) {
			return null;
		}
		return PaletteSprite.load_all(class105, archiveId, i_13_);
	}

	public static void method290(int i) {
		aClass16_3022 = null;
		aClass16_3020 = null;
		clanChatName = null;
		aClass16_3016 = null;
		ClientInventoryList.clientInvList = null;
	}

	static {
		anInt3240 = 50;
		aClass16_3245 = RSString.createString("flash2:");
		aClass16_3253 = aClass16_3245;
		aClass16_3244 = aClass16_3245;
	}

	public static void method352(int i) {
		StaticMethods.aClass16_3253 = null;
		StaticMethods.aClass16_3245 = null;
		StaticMethods.aClass16_3244 = null;
		StaticMethods.aClass16_3248 = null;
	}

	public static int anInt3240;

	public static void method329(byte b) {
		FloTypeList.typeCache.clear();
	}

	public static void method330(short[] ses, byte b, RSString[] strings) {
		GroundItem.method1034(0, strings, -1 + strings.length, ses);
	}

	public static byte[][] aByteArrayArray3163;
	public static byte[] aByteArray3161;

	public static int anInt3162;

	public static void method331(int i) {
		aByteArrayArray3163 = null;
		aByteArray3161 = null;
		aClass16_3159 = null;
		ColourImageCache.SLOT_USED = null;
		if (i < 12) {
			aByteArrayArray3163 = null;
		}
	}

	public static void method333(int i, int i_18_) {
		MusicPlayer.musicId = -1;
		GroundObjEntity.anInt708 = 1;
		if (i == -257) {
			LocResult.anInt3720 = i_18_;
			CS2Event.anInt2257 = 0;
			MusicPlayer.musicContainer = null;
			CullingCluster.anInt931 = -1;
			aBoolean3018 = false;
		}
	}

	static {
		StaticMethods.aByteArray3161 = new byte[32896];
		int i = 0;
		for (int i_19_ = 0; i_19_ < 256; i_19_++) {
			for (int i_20_ = 0; (i_20_ ^ 0xffffffff) >= (i_19_ ^ 0xffffffff); i_20_++) {
				StaticMethods.aByteArray3161[i++] = (byte) (int) (255.0 / Math.sqrt((i_19_ * i_19_ + i_20_ * i_20_ + 65535) / 65535.0F));
			}
		}
		StaticMethods.anInt3162 = 0;
		StaticMethods.aByteArrayArray3163 = new byte[50][];
	}
	static {
		aClass16_3219 = RSString.createString("m-Ochte mit Ihnen handeln)3");
	}

	public static void method345(int i) {
		StaticMethods.aClass16_3200 = null;
		StaticMethods.anIntArray3224 = null;
		if (i >= -73) {
			MusicPlayer.playMusic(-20, -85, -61, true, -92, null, -92);
		}
		StaticMethods.aClass16_3219 = null;
		StaticMethods.aClass16_3220 = null;
		StaticMethods.aClass16_3201 = null;
	}

	public static boolean method343(byte b) {
		if (GroundObjEntity.anInt708 != 0) {
			return true;
		}
		return ModelList.aSomeSoundClass_1437.method570(-119);
	}

	public static int[] anIntArray3224;
	public static NodeDeque aClass89_3436 = new NodeDeque();
	public static int anInt3435 = 100;

	public static void method407(boolean bool) {
		aClass89_3436 = null;
		MapLoader.square_loc_ids = null;
		Scene.anIntArray3427 = null;
		if (bool == false) {
			aClass16_3434 = null;
		}
	}

	public static int[][] method269(byte b) {
		StaticMethods.method493(b);
		return new int[b][b];
	}

	public static int[][] method493(byte b) {
		RSString aClass234 = null;
		if (!ComponentCanvas.method4983()) {
			aClass234 = ClanChatMember.method3434();
		} else {
			aClass234 = GrandExchangeOffer.method334(-80);
		}
		Class71_Sub2.aClass16_27333 = aClass234;
		return new int[b][b];
	}

	public static InstrumentDefinition aInstrumentDefinition_2911;
	public static int currentLength = 0;
	public static int soundPreference3;

	public static int anInt2923;

	static {
		StaticMethods.aClass16_2919 = RSString.createString("Welcome to Zaros RSPS. You can");
		StaticMethods.anInt2923 = 0;
		StaticMethods.aClass16_2913 = StaticMethods.aClass16_2919;
		StaticMethods.aClass16_2931 = RSString.createString("Loaded config");
		StaticMethods.soundPreference3 = 127;
		StaticMethods.aClass16_2929 = StaticMethods.aClass16_2931;
		GameClient.localPlayers = new Player[2048];
	}

	public static void method279(byte b) {
		aInstrumentDefinition_2911 = null;
		if (b != 2) {
			EntityUpdating.setEntityActions(-15, 51, null, true, 42);
		}
		aClass16_2913 = null;
		GameClient.localPlayers = null;
		aClass16_2931 = null;
		aClass16_2929 = null;
		aClass16_2919 = null;
	}

	public static byte[] method1460(int seed) {
		Class23_Sub13_Sub2 class23_sub13_sub2 = (Class23_Sub13_Sub2) Class42.aJList_661.get(seed);
		if (class23_sub13_sub2 == null) {
			byte[] bs = new byte[512];
			Random random = new Random(seed);
			for (int i = 0; i < 255; i++) {
				bs[i] = (byte) i;
			}
			for (int i = 0; i < 255; i++) {
				int i_20_ = -i + 255;
				int i_21_ = MathTools.getRandom(random, i_20_);
				byte b = bs[i_21_];
				bs[i_21_] = bs[i_20_];
				bs[i_20_] = bs[-i + 511] = b;
			}
			class23_sub13_sub2 = new Class23_Sub13_Sub2(bs);
			Class42.aJList_661.put(seed, class23_sub13_sub2);
		}
		return class23_sub13_sub2.aByteArray3683;
	}

	public static int[] anIntArray2114 = { -1, -1, 1, 1 };

	public static void method259(Entity class38_sub7, byte b) {
		if ((class38_sub7.forceDirection ^ 0xffffffff) == -1) {
			class38_sub7.faceDirection = 1024;
		}
		class38_sub7.anInt2632 = 0;
		int i = -GameClient.timer + class38_sub7.forceCommenceSpeed;
		int i_6_ = class38_sub7.forceStartX * 128 + class38_sub7.size * 64;
		if (class38_sub7.forceDirection == 1) {
			class38_sub7.faceDirection = 1536;
		}
		int i_7_ = 128 * class38_sub7.forceStartY + class38_sub7.size * 64;
		class38_sub7.bound_extents_x += (-class38_sub7.bound_extents_x + i_6_) / i;
		class38_sub7.bound_extents_z += (-class38_sub7.bound_extents_z + i_7_) / i;
		if (class38_sub7.forceDirection == 2) {
			class38_sub7.faceDirection = 0;
		}
		if (class38_sub7.forceDirection == 3) {
			class38_sub7.faceDirection = 512;
		}
	}

	public static SoftwarePaletteSprite[] method263(int i) {
		SoftwarePaletteSprite[] indexedSprites = new SoftwarePaletteSprite[SpriteLoader.num_sprites];
		for (int i_11_ = 0; SpriteLoader.num_sprites > i_11_; i_11_++) {
			indexedSprites[i_11_] = new SoftwarePaletteSprite(SpriteLoader.trimmed_width, SpriteLoader.trimmed_height, SpriteLoader.sprites_offsetx[i_11_], SpriteLoader.sprites_offsety[i_11_], SpriteLoader.sprites_width[i_11_], SpriteLoader.sprites_height[i_11_], SpriteLoader.sprites_pixels[i_11_], SpriteLoader.palette);
		}
		SpriteLoader.reset();
		return indexedSprites;
	}

	static {
		Scene.rendered_models_uid = new long[1000];
		Scene.num_offscreen_entities = 0;
	}

	public static void method257(boolean bool) {
		aClass16_2105 = null;
		if (bool != true) {
			method259(null, (byte) 99);
		}
		Scene.rendered_models_uid = null;
		anIntArray2114 = null;
	}

	public static boolean aBoolean1867 = false;

	public static void method44(int i) {
		aClass16_65 = null;
		NPCType.npcMap = null;
		aClass16_66 = null;
	}

	static {
		StaticMethods.aClass16_65 = RSString.createString("Gegenstand f-Ur Mitglieder");
	}
	public static Random aRandom4110;

	public static RSInterface withInterface = null;

	public static void method802(int i) {
		aClass16_4117 = null;
		withInterface = null;
		aClass16_4113 = null;
		GrandExchangeOffer.offers = null;
		aRandom4110 = null;
		CacheConstants.locCacheIdx = null;
	}

	public static void method804(int i, int i_11_, int i_12_, int i_13_, int i_14_, int i_15_, int i_16_, int[] is, int i_17_, int i_18_, boolean bool) {
		if (Rasterizer2D.clip_left > i_13_) {
			i_18_ -= -i_13_ + Rasterizer2D.clip_left;
			i_13_ = Rasterizer2D.clip_left;
		}
		if ((i_11_ ^ 0xffffffff) > (Rasterizer2D.clip_top ^ 0xffffffff)) {
			i_15_ -= Rasterizer2D.clip_top + -i_11_;
			i_11_ = Rasterizer2D.clip_top;
		}
		if (i_17_ == 9) {
			i_17_ = 1;
			i = 0x3 & i + 1;
		}
		if (i_17_ == 10) {
			i_17_ = 1;
			i = 3 + i & 0x3;
		}
		if (i_17_ == 11) {
			i = 0x3 & i + 3;
			i_17_ = 8;
		}
		if (Rasterizer2D.clip_bottom < i_15_ + i_11_) {
			i_15_ = Rasterizer2D.clip_bottom + -i_11_;
		}
		if ((i_18_ + i_13_ ^ 0xffffffff) < (Rasterizer2D.clip_right ^ 0xffffffff)) {
			i_18_ = -i_13_ + Rasterizer2D.clip_right;
		}
		int i_20_ = i_13_ + i_11_ * Rasterizer2D.width;
		int i_21_ = -i_18_ + Rasterizer2D.width;
		if (i_17_ == 1) {
			if (i == 0) {
				for (int i_22_ = 0; (i_22_ ^ 0xffffffff) > (i_15_ ^ 0xffffffff); i_22_++) {
					for (int i_23_ = 0; i_18_ > i_23_; i_23_++) {
						if (i_23_ > i_22_) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 1) {
				for (int i_24_ = i_15_ + -1; i_24_ >= 0; i_24_--) {
					for (int i_25_ = 0; (i_18_ ^ 0xffffffff) < (i_25_ ^ 0xffffffff); i_25_++) {
						if ((i_24_ ^ 0xffffffff) > (i_25_ ^ 0xffffffff)) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 2) {
				for (int i_26_ = 0; i_26_ < i_15_; i_26_++) {
					for (int i_27_ = 0; (i_27_ ^ 0xffffffff) > (i_18_ ^ 0xffffffff); i_27_++) {
						if (i_27_ < i_26_) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 3) {
				for (int i_28_ = -1 + i_15_; (i_28_ ^ 0xffffffff) <= -1; i_28_--) {
					for (int i_29_ = 0; i_29_ < i_18_; i_29_++) {
						if ((i_29_ ^ 0xffffffff) <= (i_28_ ^ 0xffffffff)) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			}
		} else if (i_17_ == 2) {
			if (i == 0) {
				for (int i_30_ = i_15_ + -1; i_30_ >= 0; i_30_--) {
					for (int i_31_ = 0; (i_31_ ^ 0xffffffff) > (i_18_ ^ 0xffffffff); i_31_++) {
						if (i_30_ >> 1 >= i_31_) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 1) {
				for (int i_32_ = 0; (i_15_ ^ 0xffffffff) < (i_32_ ^ 0xffffffff); i_32_++) {
					for (int i_33_ = 0; i_33_ < i_18_; i_33_++) {
						if ((i_33_ ^ 0xffffffff) <= (i_32_ << 1 ^ 0xffffffff)) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 2) {
				for (int i_34_ = 0; (i_15_ ^ 0xffffffff) < (i_34_ ^ 0xffffffff); i_34_++) {
					for (int i_35_ = -1 + i_18_; (i_35_ ^ 0xffffffff) <= -1; i_35_--) {
						if (i_34_ >> 1 >= i_35_) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 3) {
				for (int i_36_ = i_15_ - 1; (i_36_ ^ 0xffffffff) <= -1; i_36_--) {
					for (int i_37_ = -1 + i_18_; i_37_ >= 0; i_37_--) {
						if (i_36_ << 1 > i_37_) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			}
		} else if (i_17_ == 3) {
			if (i == 0) {
				for (int i_38_ = i_15_ - 1; i_38_ >= 0; i_38_--) {
					for (int i_39_ = -1 + i_18_; (i_39_ ^ 0xffffffff) <= -1; i_39_--) {
						if (i_38_ >> 1 >= i_39_) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 1) {
				for (int i_40_ = i_15_ - 1; (i_40_ ^ 0xffffffff) <= -1; i_40_--) {
					for (int i_41_ = 0; (i_18_ ^ 0xffffffff) < (i_41_ ^ 0xffffffff); i_41_++) {
						if (i_40_ << 1 <= i_41_) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 2) {
				for (int i_42_ = 0; (i_15_ ^ 0xffffffff) < (i_42_ ^ 0xffffffff); i_42_++) {
					for (int i_43_ = 0; (i_18_ ^ 0xffffffff) < (i_43_ ^ 0xffffffff); i_43_++) {
						if ((i_42_ >> 1 ^ 0xffffffff) <= (i_43_ ^ 0xffffffff)) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 3) {
				for (int i_44_ = 0; i_15_ > i_44_; i_44_++) {
					for (int i_45_ = i_18_ + -1; i_45_ >= 0; i_45_--) {
						if ((i_45_ ^ 0xffffffff) > (i_44_ << 1 ^ 0xffffffff)) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			}
		} else if (i_17_ == 4) {
			if ((i ^ 0xffffffff) == -1) {
				for (int i_46_ = -1 + i_15_; i_46_ >= 0; i_46_--) {
					for (int i_47_ = 0; i_18_ > i_47_; i_47_++) {
						if (i_46_ >> 1 > i_47_) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 1) {
				for (int i_48_ = 0; i_15_ > i_48_; i_48_++) {
					for (int i_49_ = 0; (i_18_ ^ 0xffffffff) < (i_49_ ^ 0xffffffff); i_49_++) {
						if (i_49_ <= i_48_ << 1) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 2) {
				for (int i_50_ = 0; (i_50_ ^ 0xffffffff) > (i_15_ ^ 0xffffffff); i_50_++) {
					for (int i_51_ = i_18_ + -1; i_51_ >= 0; i_51_--) {
						if (i_51_ >= i_50_ >> 1) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 3) {
				for (int i_52_ = -1 + i_15_; i_52_ >= 0; i_52_--) {
					for (int i_53_ = i_18_ - 1; i_53_ >= 0; i_53_--) {
						if ((i_52_ << 1 ^ 0xffffffff) > (i_53_ ^ 0xffffffff)) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			}
		} else if (i_17_ == 5) {
			if ((i ^ 0xffffffff) == -1) {
				for (int i_54_ = i_15_ - 1; i_54_ >= 0; i_54_--) {
					for (int i_55_ = -1 + i_18_; (i_55_ ^ 0xffffffff) <= -1; i_55_--) {
						if (i_54_ >> 1 <= i_55_) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 1) {
				for (int i_56_ = i_15_ - 1; (i_56_ ^ 0xffffffff) <= -1; i_56_--) {
					for (int i_57_ = 0; i_18_ > i_57_; i_57_++) {
						if ((i_56_ << 1 ^ 0xffffffff) > (i_57_ ^ 0xffffffff)) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 2) {
				for (int i_58_ = 0; i_15_ > i_58_; i_58_++) {
					for (int i_59_ = 0; i_18_ > i_59_; i_59_++) {
						if (i_58_ >> 1 <= i_59_) {
							is[i_20_] = i_14_;
						} else if (bool) {
							is[i_20_] = i_16_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			} else if (i == 3) {
				for (int i_60_ = 0; (i_15_ ^ 0xffffffff) < (i_60_ ^ 0xffffffff); i_60_++) {
					for (int i_61_ = i_18_ - 1; i_61_ >= 0; i_61_--) {
						if (i_60_ << 1 < i_61_) {
							if (bool) {
								is[i_20_] = i_16_;
							}
						} else {
							is[i_20_] = i_14_;
						}
						i_20_++;
					}
					i_20_ += i_21_;
				}
			}
		} else {
			if (i_17_ == 6) {
				if ((i ^ 0xffffffff) == -1) {
					for (int i_62_ = 0; i_15_ > i_62_; i_62_++) {
						for (int i_63_ = 0; i_63_ < i_18_; i_63_++) {
							if (i_63_ > i_18_ / 2) {
								if (bool) {
									is[i_20_] = i_16_;
								}
							} else {
								is[i_20_] = i_14_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
				if (i == 1) {
					for (int i_64_ = 0; i_15_ > i_64_; i_64_++) {
						for (int i_65_ = 0; (i_65_ ^ 0xffffffff) > (i_18_ ^ 0xffffffff); i_65_++) {
							if ((i_15_ / 2 ^ 0xffffffff) <= (i_64_ ^ 0xffffffff)) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
				if (i == 2) {
					for (int i_66_ = 0; (i_15_ ^ 0xffffffff) < (i_66_ ^ 0xffffffff); i_66_++) {
						for (int i_67_ = 0; i_67_ < i_18_; i_67_++) {
							if (i_18_ / 2 <= i_67_) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
				if (i == 3) {
					for (int i_68_ = 0; i_68_ < i_15_; i_68_++) {
						for (int i_69_ = 0; i_18_ > i_69_; i_69_++) {
							if ((i_68_ ^ 0xffffffff) <= (i_15_ / 2 ^ 0xffffffff)) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
			}
			if (i_17_ == 7) {
				if ((i ^ 0xffffffff) == -1) {
					for (int i_70_ = 0; (i_15_ ^ 0xffffffff) < (i_70_ ^ 0xffffffff); i_70_++) {
						for (int i_71_ = 0; (i_71_ ^ 0xffffffff) > (i_18_ ^ 0xffffffff); i_71_++) {
							if (i_71_ <= -(i_15_ / 2) + i_70_) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
				if (i == 1) {
					for (int i_72_ = -1 + i_15_; i_72_ >= 0; i_72_--) {
						for (int i_73_ = 0; (i_73_ ^ 0xffffffff) > (i_18_ ^ 0xffffffff); i_73_++) {
							if (i_73_ <= i_72_ + -(i_15_ / 2)) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
				if (i == 2) {
					for (int i_74_ = i_15_ + -1; (i_74_ ^ 0xffffffff) <= -1; i_74_--) {
						for (int i_75_ = i_18_ - 1; (i_75_ ^ 0xffffffff) <= -1; i_75_--) {
							if ((-(i_15_ / 2) + i_74_ ^ 0xffffffff) <= (i_75_ ^ 0xffffffff)) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
				if (i == 3) {
					for (int i_76_ = 0; (i_76_ ^ 0xffffffff) > (i_15_ ^ 0xffffffff); i_76_++) {
						for (int i_77_ = i_18_ + -1; (i_77_ ^ 0xffffffff) <= -1; i_77_--) {
							if (i_76_ - i_15_ / 2 >= i_77_) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
					return;
				}
			}
			if (i_17_ == 8) {
				if (i == 0) {
					for (int i_78_ = 0; i_78_ < i_15_; i_78_++) {
						for (int i_79_ = 0; i_18_ > i_79_; i_79_++) {
							if ((i_79_ ^ 0xffffffff) <= (i_78_ + -(i_15_ / 2) ^ 0xffffffff)) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
				} else if (i == 1) {
					for (int i_80_ = -1 + i_15_; i_80_ >= 0; i_80_--) {
						for (int i_81_ = 0; i_81_ < i_18_; i_81_++) {
							if (i_81_ < i_80_ + -(i_15_ / 2)) {
								if (bool) {
									is[i_20_] = i_16_;
								}
							} else {
								is[i_20_] = i_14_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
				} else if (i == 2) {
					for (int i_82_ = i_15_ - 1; i_82_ >= 0; i_82_--) {
						for (int i_83_ = -1 + i_18_; i_83_ >= 0; i_83_--) {
							if ((-(i_15_ / 2) + i_82_ ^ 0xffffffff) < (i_83_ ^ 0xffffffff)) {
								if (bool) {
									is[i_20_] = i_16_;
								}
							} else {
								is[i_20_] = i_14_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
				} else if (i == 3) {
					for (int i_84_ = 0; i_15_ > i_84_; i_84_++) {
						for (int i_85_ = -1 + i_18_; (i_85_ ^ 0xffffffff) <= -1; i_85_--) {
							if (i_84_ - i_15_ / 2 <= i_85_) {
								is[i_20_] = i_14_;
							} else if (bool) {
								is[i_20_] = i_16_;
							}
							i_20_++;
						}
						i_20_ += i_21_;
					}
				}
			}
		}
	}

	public static boolean method811(int i, int i_135_, int i_136_, int i_137_) {
		if (!Scene.method846(i, i_135_, i_136_)) {
			return false;
		}
		int i_138_ = i_135_ << TileConstants.SIZE_BITS;
		int i_139_ = i_136_ << TileConstants.SIZE_BITS;
		if (Scene.is_culled(i_138_ + 1, Scene.current_heightmap[i][i_135_][i_136_] + i_137_, i_139_ + 1) && Scene.is_culled(i_138_ + 128 - 1, Scene.current_heightmap[i][i_135_ + 1][i_136_] + i_137_, i_139_ + 1) && Scene.is_culled(i_138_ + 128 - 1, Scene.current_heightmap[i][i_135_ + 1][i_136_ + 1] + i_137_, i_139_ + 128 - 1) && Scene.is_culled(i_138_ + 1, Scene.current_heightmap[i][i_135_][i_136_ + 1] + i_137_, i_139_ + 128 - 1)) {
			return true;
		}
		return false;
	}

	static {
		StaticMethods.aClass16_4117 = RSString.createString("Einloggen");
		StaticMethods.aRandom4110 = new Random();
	}

	static {
		StaticMethods.aClass16_1861 = RSString.createString(" zuerst von Ihrer Ignorieren)2Liste(Q");
		StaticMethods.aClass16_1871 = RSString.createString("glow1:");
		StaticMethods.aClass16_1858 = StaticMethods.aClass16_1871;
		StaticMethods.aClass16_1862 = StaticMethods.aClass16_1871;
	}

	public static void method996(int i) {
		StaticMethods.anIntArray596 = null;
		StaticMethods.aClass16_586 = null;
		SpotType.gfxMap = null;
		LocTypeList.somecache = null;
	}

	static {
		aShort594 = (short) 32767;
		anIntArray596 = new int[128];
	}

	static RSString aClass16_586 = RSString.createString("-5berpr-Ufen Sie Ihr Mitteilungsfach)3");
	static short aShort594;
	public static int[] anIntArray596;

	static {
		StaticMethods2.aClass16_4212 = RSString.createString("Unerwartete Antwort vom Anmelde)2Server");
	}
	static {
		aClass16_2589 = RSString.createString("rot:");
	}

	public static RSString aClass16_2589;
	public static int[] anIntArray2597 = new int[5];
	public static int[] anIntArray874 = new int[] { 0, -1, 0, 1 };
	public static int[] anIntArray2441 = new int[] { 1, 0, -1, 0 };

	static {
		aClass16_2283 = RSString.createString(")matrixftw)3com)4l=");
		anIntArray2286 = new int[100];
		aClass16_2288 = RSString.createString("Benutzername: ");
	}

	static RSString aClass16_2279 = RSString.createString("Mem:");
	public static int anInt2281 = 0;
	public static int loginButtonArchiveID;
	static RSString aClass16_2283;
	static RSString aClass16_2284 = RSString.createString("::fps ");
	public static int[] anIntArray2286;
	static Js5 soundEffectsContainer;
	static RSString aClass16_2288;
	static RSString aClass94_2765 = RSString.createString(" ");
	static RSString aClass94_2168 = RSString.createString("<br>");

	public static void method596(boolean bool) {
		soundEffectsContainer = null;
		anIntArray2286 = null;
		aClass16_2279 = null;
		aClass16_2284 = null;
		aClass16_2283 = null;
		aClass16_2288 = null;
		if (bool != true) {
			method596(false);
		}
	}

	static final void method892(int i, int i_0_, int i_1_, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_) {
		if (i >= 1 && i_1_ >= 1 && i <= 102 && i_1_ <= 102) {
			if (!ClientOptions.is_removeroofs() && (0x2 & MapLoader.settings[0][i][i_1_] ^ 0xffffffff) == -1) {
				int logical_level = i_5_;
				if ((MapLoader.settings[i_5_][i][i_1_] & 0x8 ^ 0xffffffff) != -1) {
					logical_level = 0;
				}
				if ((player_height ^ 0xffffffff) != (logical_level ^ 0xffffffff)) {
					return;
				}
			}
			int visual_level = i_5_;
			if (visual_level < 3 && (MapLoader.settings[1][i][i_1_] & 0x2) == 2) {
				visual_level++;
			}
			ModelNode.method857(i, MapLoader.collision_maps[i_5_], i_4_, visual_level, i_1_, i_5_);
			if ((i_0_ ^ 0xffffffff) <= -1) {
				boolean var9 = ClientOptions.clientoption_grounddecor;
				ClientOptions.clientoption_grounddecor = true;
				MapRegion.addGameObject(i_0_, i_5_, i, i_1_, i_6_, MapLoader.collision_maps[i_5_], false, i_2_, visual_level, false);
				ClientOptions.clientoption_grounddecor = var9;
			}
		}
	}

	static final void method890(byte b) {
		ObjType.itemSprites.clear();
	}

	public static void method889(int i) {
		CS2Runtime.int_arrays = null;
	}
}
