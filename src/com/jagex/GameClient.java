package com.jagex;
/* client - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.swing.LookAndFeel;

import com.jagex.core.natives.Js5NativeManager;
import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.game.runetek4.clientoptions.GamePreferences;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.graphics.runetek4.opengl.GLState;
import com.jagex.graphics.runetek4.software.framebuffer.SoftwareGraphicsBuffer;
import com.jagex.js5.AsyncCache;
import com.jagex.js5.Js5Archive;
import com.jagex.js5.Js5Manager;
import com.jagex.js5.Js5Shutdown;
import com.jagex.js5.prefetch.Js5PrefetchManager;
import com.jagex.launcher.Configurations;
import com.jagex.launcher.GameLaunch;
import com.jagex.launcher.GamePlayConfiguration;
import com.jagex.launcher.GameSetting;
import com.jagex.launcher.logger.Logger;
import com.jagex.window.FullscreenUtilities;
import com.jagex.window.WindowMode;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.network.ping.PingManager;
import com.rs2.client.scene.light.SceneLighting;

import jaclib.ping.Ping;
import me.waliedyassen.materials.MaterialRawList;
import me.waliedyassen.textures.TextureCache;

public class GameClient extends GameShell {

	public static final long serialVersionUID = 2782200114571486615L;
	public static final int NUM_CACHE_ARCHIVES = 28;
	public static int clientState = 0;
	public static SeekableFile data_file;
	public static int loadingStage = 0;
	public static int gamePercentage;
	public static RSString loadingMessage;
	public static SignLink gameSignlink;
	public static Player currentPlayer;
	public static int[] configs = new int[2200];
	public static NPC[] activeNPCs = new NPC[32768];
	public static int cross_index;
	public static int cross_type;
	public static int crossX = 0;
	static ISAACPacket outBuffer;
	public static Player[] localPlayers;
	public static int atInventoryLoopCycle = 0;
	static RSInterface atInventoryInterface;
	public static int atInventoryIndex = 0;
	public static int[] localPlayerPointers = new int[2048];
	static SignlinkRequest js5_connect_request;
	public static int js5_connect_stage = 0;
	public static int socket_port;
	static IoSession js5_stream;
	static long js5_last_connect_time;
	public static int js5_reconnect_timer = 0;
	static long last_ramchk;
	static long lastGC = 0L;
	public static Clipboard system_clipboard;
	public static int rights = 0;
	public static GamePreferences preferences;
	public static SeekableFile[] index_files = new SeekableFile[NUM_CACHE_ARCHIVES];
	public static Js5Archive[] js5_archives = new Js5Archive[NUM_CACHE_ARCHIVES];
	static SeekableFile master_index_file;
	static FileSystem index_datafs;
	static SeekableFile random_file;

	public static Js5Manager js5_manager;
	public static Js5Client js5_client;
	public static PingManager pingManager;
	public static AsyncCache async_cache;

	/**
	 * Represents the {@link Dimension} of the <method652>GameShell</method652>.
	 */
	public static Dimension SIZE = new Dimension(765, 503);

	/**
	 * Represents the setting of the <code>Game</code>.
	 */
	public static GameSetting setting = GameLaunch.getSetting();

	/**
	 * Represents the aff id.
	 */
	public static int affId;

	/**
	 * The game value, (0 = rs, 1 = stelar).
	 */
	public static int gameValue = 0;

	/**
	 * Rerpesents the language, 0 (english), 1(german).
	 */
	public static int language = 0;

	/**
	 * Represents if the client is members.
	 */
	public static boolean isMembers = true;

	/**
	 * Represents if the game is , live, rc, or wip.
	 */
	public static int liveRcWip = 0;

	/**
	 * Represents the game type. (local, live, office).
	 */
	public static int gameType;

	/**
	 * Represents the revision of the client.
	 */
	public static final int revision = 498;

	/**
	 * Represents the ip address to connect to of the server.
	 */
	public static String IP;

	/**
	 * The current set world.
	 */
	public static int worldId = 1;

	/**
	 * Represents if the clipping flags are currently disabled.
	 */
	public static boolean aBoolean_4_;

	/**
	 * The rs string.
	 */
	static RSString aClass16_18_;
	public static int interface_top_id;
	public static SoftwareGraphicsBuffer software_frame_buffer;
	public static int timer;
	static short[] client_palette;

	/**
	 * Constructs a new {@code Game} {@code Object}.
	 */
	public GameClient() {
		/**
		 * empty.
		 */
	}

	public static GameClient active_client;

	/**
	 * Constructs a new {@link GameClient} {@Code Object}.
	 *
	 * @param setting
	 *                the setting.
	 */
	public GameClient(GameSetting setting) {
		GameClient.setting = setting;
		setSettings(setting);
	}

	/**
	 * Method used staticly to create a game.
	 */
	public static GameClient create(GameSetting setting) {
		return active_client = new GameClient(setting);
	}

	public static final void updateClientState(int new_state) {
		if (clientState != new_state) {
			if ((clientState ^ 0xffffffff) == -1) {
				Class30.method958((byte) 11);
			}
			if (new_state == 20 || new_state == 40) {
				LoginHandler.loginConnectionState = 0;
				StaticMethods2.anInt3781 = 0;
				CacheFileWorker.anInt2854 = 0;
			}
			if (new_state != 20 && new_state != 40 && NPCType.aClass34_4150 != null) {
				NPCType.aClass34_4150.shutdown();
				NPCType.aClass34_4150 = null;
			}
			if (clientState == 25) {
				MapLoader.num_regions_failed = 0;
				MapLoader.loading_stage = 0;
				GroundItemNode.anInt3667 = 1;
				StaticMethods.anInt3036 = 0;
				StaticMethods.anInt3055 = 1;
				WorldMap.reset(102, true);
			}
			if (new_state == 5 || new_state == 10 || new_state == 20) {
				LoginHandler.prepareLoginComponents(CacheConstants.huffmanCacheIdx, CacheConstants.sprites_js5, getCanvas());
			} else {
				StaticMethods.method597(2);
			}
			if (GLManager.opengl_mode && (new_state == 25 || new_state == 28 || new_state == 40)) {
				GLManager.render_last_frame();
			}
			clientState = new_state;
		}
	}

	static final void doAction(int actionID) {
		if (actionID >= 0) {
			int slot = ContextMenu.menuActionCmd2[actionID];
			int interfaceId = ContextMenu.menuActionCmd3[actionID];
			int action = ContextMenu.menuActionID[actionID];
			int nodeId = (int) ContextMenu.menuActionCmd1[actionID];
			if (action >= 2000) {
				action -= 2000;
			}
			long l = ContextMenu.menuActionCmd1[actionID];
			if (action == 50) {
				outBuffer.putOpcode(233);
				outBuffer.putShortA(slot);
				outBuffer.putLEShortA(nodeId);
				outBuffer.p4(interfaceId);
				atInventoryLoopCycle = 0;
				atInventoryInterface = RSInterface.getInterface(interfaceId);
				atInventoryIndex = slot;
			}
			if (action == 1004) {
				crossY = Mouse.mouseClickY;
				cross_type = 2;
				cross_index = 0;
				crossX = Mouse.mouseClickX;
				NPC npc = activeNPCs[nodeId];
				if (npc != null) {
					NPCType def = npc.config;
					if (def.morphisms != null) {
						def = def.getChildNPC();
					}
					if (def != null) {
						outBuffer.putOpcode(237);
						outBuffer.putShort(def.npcid);
					}
				}
			}
			if (action == 25) {
				Player player = localPlayers[nodeId];
				if (player != null) {
					walkPath(0, player.waypointsY[0], 2, player.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					crossY = Mouse.mouseClickY;
					crossX = Mouse.mouseClickX;
					cross_index = 0;
					cross_type = 2;
					outBuffer.putOpcode(214);
					outBuffer.putShortA(nodeId);
				}
			}
			if (action == 11) {
				outBuffer.putOpcode(60);
				outBuffer.p4(interfaceId);
				outBuffer.putShort(nodeId);
				outBuffer.putShortA(slot);
				atInventoryLoopCycle = 0;
				atInventoryInterface = RSInterface.getInterface(interfaceId);
				atInventoryIndex = slot;
			}
			if (action == 39) {
				StaticMethods.method779(true, slot, l, interfaceId);
				outBuffer.putOpcode(28);
				outBuffer.putLEShort(slot - -MapLoader.region_aboslute_z);
				outBuffer.putShortA(interfaceId + MapLoader.region_aboslute_x);
				outBuffer.putLEShortA((int) (l >>> 32) & 0x7fffffff);
			}
			if (action == 10) {
				StaticMethods.method779(true, slot, l, interfaceId);
				outBuffer.putOpcode(217);
				outBuffer.putShort(MapLoader.region_aboslute_x + interfaceId);
				outBuffer.putShort(slot - -MapLoader.region_aboslute_z);
				outBuffer.putLEShort((int) (l >>> 32) & 0x7fffffff);
			}
			if (action == 1001) {
				StaticMethods.method779(true, slot, l, interfaceId);
				outBuffer.putOpcode(152);
				outBuffer.putLEShortA(0x7fffffff & (int) (l >>> 32));
				outBuffer.putLEShort(MapLoader.region_aboslute_z + slot);
				outBuffer.putLEShortA(interfaceId + MapLoader.region_aboslute_x);
			}
			if (action == 33) {
				boolean bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 0, currentPlayer.waypointsY[0], 0);
				if (!bool) {
					bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
				}
				crossY = Mouse.mouseClickY;
				cross_index = 0;
				crossX = Mouse.mouseClickX;
				cross_type = 2;
				outBuffer.putOpcode(189);
				outBuffer.putLEShortA(MapLoader.region_aboslute_z + slot);
				outBuffer.putShortA(interfaceId + MapLoader.region_aboslute_x);
				outBuffer.putLEShortA(nodeId);
			}
			if (action == 28) {
				outBuffer.putOpcode(243);
				outBuffer.p4(interfaceId);
				outBuffer.putShortA(nodeId);
				outBuffer.putLEShortA(slot);
				atInventoryLoopCycle = 0;
				atInventoryInterface = RSInterface.getInterface(interfaceId);
				atInventoryIndex = slot;
			}
			if (action == 1005) {
				cross_type = 2;
				crossY = Mouse.mouseClickY;
				crossX = Mouse.mouseClickX;
				cross_index = 0;
				outBuffer.putOpcode(88);
				outBuffer.putShortA(nodeId);
			}
			if (action == 34) {
				boolean bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 0, currentPlayer.waypointsY[0], 0);
				if (!bool) {
					bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
				}
				cross_type = 2;
				cross_index = 0;
				crossX = Mouse.mouseClickX;
				crossY = Mouse.mouseClickY;
				outBuffer.putOpcode(169);
				outBuffer.putLEShort(MapLoader.region_aboslute_z + slot);
				outBuffer.putShort(interfaceId - -MapLoader.region_aboslute_x);
				outBuffer.putLEShortA(nodeId);
			}
			if (action == 1) {
				NPC npc = activeNPCs[nodeId];
				if (npc != null) {
					walkPath(0, npc.waypointsY[0], 2, npc.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					cross_index = 0;
					cross_type = 2;
					crossX = Mouse.mouseClickX;
					crossY = Mouse.mouseClickY;
					outBuffer.putOpcode(241);
					outBuffer.putLEShortA(nodeId);
				}
			}
			if (action == 19 || action == 1002) {
				handleInterfaceAction(Class98.aClass16Array1655[actionID], (byte) -55, slot, interfaceId, nodeId);
			}
			if (action == 48) {
				NPC npc = activeNPCs[nodeId];
				if (npc != null) {
					walkPath(0, npc.waypointsY[0], 2, npc.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					cross_type = 2;
					crossY = Mouse.mouseClickY;
					cross_index = 0;
					crossX = Mouse.mouseClickX;
					outBuffer.putOpcode(239);
					outBuffer.putShort(nodeId);
				}
			}
			if (action == 2) {
				Player player = localPlayers[nodeId];
				if (player != null) {
					walkPath(0, player.waypointsY[0], 2, player.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					cross_index = 0;
					cross_type = 2;
					crossY = Mouse.mouseClickY;
					crossX = Mouse.mouseClickX;
					outBuffer.putOpcode(185);
					outBuffer.putLEShortA(nodeId);
				}
			}
			if (action == 8) {
				outBuffer.putOpcode(157);
				outBuffer.putLEShort(nodeId);
				outBuffer.putLEShort(slot);
				outBuffer.ip4(interfaceId);
				atInventoryLoopCycle = 0;
				atInventoryInterface = RSInterface.getInterface(interfaceId);
				atInventoryIndex = slot;
			}
			if (action == 47) {
				outBuffer.putOpcode(170);
				outBuffer.putLEShort(slot);
				outBuffer.putShortA(nodeId);
				outBuffer.ip4(interfaceId);
				atInventoryLoopCycle = 0;
				atInventoryInterface = RSInterface.getInterface(interfaceId);
				atInventoryIndex = slot;
			}
			if (action == 45) {
				NPC npc = activeNPCs[nodeId];
				if (npc != null) {
					walkPath(0, npc.waypointsY[0], 2, npc.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					crossX = Mouse.mouseClickX;
					cross_type = 2;
					crossY = Mouse.mouseClickY;
					cross_index = 0;
					outBuffer.putOpcode(197);
					outBuffer.putLEShortA(nodeId);
				}
			}
			if (action == 5) {
				SceneController.resetClickedTile(ObjType.localHeight, slot, interfaceId);
			}
			if (action == 20) {
				outBuffer.putOpcode(57);
				outBuffer.putShortA(nodeId);
				outBuffer.putShortA(slot);
				outBuffer.putIntA(interfaceId);
				atInventoryLoopCycle = 0;
				atInventoryInterface = RSInterface.getInterface(interfaceId);
				atInventoryIndex = slot;
			}
			if (action == 1007) {
				RSInterface inter = RSInterface.getInterface(interfaceId);
				if (inter == null || (inter.obj_counts[slot] ^ 0xffffffff) > -100001) {
					outBuffer.putOpcode(254);
					outBuffer.putShort(nodeId);
				} else {
					Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { RSString.valueOf(inter.obj_counts[slot]), Class87_Sub3.aClass16_2821, ObjTypeList.list(nodeId).name }), StaticMethods.empty_string);
				}
				atInventoryLoopCycle = 0;
				atInventoryInterface = RSInterface.getInterface(interfaceId);
				atInventoryIndex = slot;
			}
			if (action == 4) {
				Player player = localPlayers[nodeId];
				if (player != null) {
					walkPath(0, player.waypointsY[0], 2, player.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					crossX = Mouse.mouseClickX;
					cross_index = 0;
					crossY = Mouse.mouseClickY;
					cross_type = 2;
					outBuffer.putOpcode(242);
					outBuffer.putLEShort(nodeId);
				}
			}
			if (action == 57) {
				outBuffer.putOpcode(180);
				outBuffer.p4(interfaceId);
				RSInterface class64 = RSInterface.getInterface(interfaceId);
				if (class64.childBuffers != null && class64.childBuffers[0][0] == 5) {
					int i_4_ = class64.childBuffers[0][1];
					configs[i_4_] = 1 - configs[i_4_];
					Class71_Sub1_Sub1.method1276(i_4_);
				}
			}
			if (action == 17) {
				boolean bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 0, currentPlayer.waypointsY[0], 0);
				if (!bool) {
					bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
				}
				crossY = Mouse.mouseClickY;
				cross_index = 0;
				crossX = Mouse.mouseClickX;
				cross_type = 2;
				outBuffer.putOpcode(109);
				outBuffer.putShortA(MapLoader.region_aboslute_x + interfaceId);
				outBuffer.putLEShortA(nodeId);
				outBuffer.putLEShortA(slot - -MapLoader.region_aboslute_z);
			}
			if (action == 37) {
				StaticMethods.method779(true, slot, l, interfaceId);
				outBuffer.putOpcode(67);
				outBuffer.putLEShort(MapLoader.region_aboslute_z + slot);
				outBuffer.putLEShortA(interfaceId + MapLoader.region_aboslute_x);
				outBuffer.putShort(0x7fffffff & (int) (l >>> 32));
			}
			if (action == 43) {
				boolean bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 0, currentPlayer.waypointsY[0], 0);
				if (!bool) {
					bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
				}
				crossX = Mouse.mouseClickX;
				cross_index = 0;
				crossY = Mouse.mouseClickY;
				cross_type = 2;
				outBuffer.putOpcode(89);
				outBuffer.putLEShort(nodeId);
				outBuffer.putLEShort(MapLoader.region_aboslute_z + slot);
				outBuffer.putLEShort(interfaceId + MapLoader.region_aboslute_x);
				outBuffer.putLEShortA(Queue.anInt411);
				outBuffer.ip4(Class49.anInt759);
				outBuffer.putLEShort(AbstractTimer.anInt302);
			}
			if (action == 35) {
				boolean bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 0, currentPlayer.waypointsY[0], 0);
				if (!bool) {
					bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
				}
				cross_index = 0;
				crossY = Mouse.mouseClickY;
				crossX = Mouse.mouseClickX;
				cross_type = 2;
				outBuffer.putOpcode(55);
				outBuffer.putIntB(UpdateServerThread.anInt169);
				outBuffer.putLEShort(slot + MapLoader.region_aboslute_z);
				outBuffer.putShortA(MapLoader.region_aboslute_x + interfaceId);
				outBuffer.putLEShort(Varbit.anInt4013);
				outBuffer.putShort(nodeId);
			}
			if (action == 13) {
				Packet.method444();
			}
			if (action == 40) {
				Player player = localPlayers[nodeId];
				if (player != null) {
					walkPath(0, player.waypointsY[0], 2, player.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					crossY = Mouse.mouseClickY;
					cross_type = 2;
					crossX = Mouse.mouseClickX;
					cross_index = 0;
					outBuffer.putOpcode(61);
					outBuffer.p4(Class49.anInt759);
					outBuffer.putShort(Queue.anInt411);
					outBuffer.putLEShortA(AbstractTimer.anInt302);
					outBuffer.putShortA(nodeId);
				}
			}
			if (action == 32) {
				NPC npc = activeNPCs[nodeId];
				if (npc != null) {
					walkPath(0, npc.waypointsY[0], 2, npc.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					cross_index = 0;
					cross_type = 2;
					crossY = Mouse.mouseClickY;
					crossX = Mouse.mouseClickX;
					outBuffer.putOpcode(58);
					outBuffer.putLEShort(Queue.anInt411);
					outBuffer.putIntA(Class49.anInt759);
					outBuffer.putShortA(AbstractTimer.anInt302);
					outBuffer.putLEShort(nodeId);
				}
			}
			if (action == 15 && Varbit.aClass64_4007 == null) { // Action button
				UpdateServerThread.method95(slot, interfaceId);
				Varbit.aClass64_4007 = RSInterfaceList.get_dynamic_component(interfaceId, slot, (byte) -35);
				RSInterfaceList.setDirty(Varbit.aClass64_4007);
			}
			if (action == 30) {
				RSInterface class64 = RSInterface.getInterface(interfaceId);
				boolean bool = true;
				if ((class64.content_type ^ 0xffffffff) < -1) {
					bool = NPC.method1092(class64, -82 + 287);
				}
				if (bool) {
					outBuffer.putOpcode(180);
					outBuffer.p4(interfaceId);
				}
			}
			if (action == 18) {
				handleSpellAction(1);
				RSInterface inter = RSInterface.getInterface(interfaceId);
				Class49.anInt759 = interfaceId;
				AbstractTimer.anInt302 = slot;
				NPC.anInt4374 = 1;
				Queue.anInt411 = nodeId;
				RSInterfaceList.setDirty(inter);
				StaticMethods2.aClass16_4281 = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3418, ObjTypeList.list(nodeId).name, StaticMethods.aClass16_2896 });
				if (StaticMethods2.aClass16_4281 == null) {
					StaticMethods2.aClass16_4281 = StaticMethods.aClass16_3378;
				}
			} else if (action == 9) {
				RSInterface class64 = RSInterfaceList.get_dynamic_component(interfaceId, slot, (byte) -56);
				if (class64 != null) {
					handleSpellAction(-82 ^ ~0x50);
					handleSpellOnItemAction(5087, Class47.getOptionMask(class64, 117).method101(), interfaceId, slot);
					NPC.anInt4374 = 0;
					Class49.aClass16_764 = MonochromeImageCache.getSelectedActionName(class64);// Cast
					if (Class49.aClass16_764 == null) {
						Class49.aClass16_764 = Queue.aClass16_409;
					}
					if (!class64.newer_interface) {
						StaticMethods2.aClass16_3714 = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3458, class64.aClass16_1019, StaticMethods.aClass16_2896 });
					} else {
						StaticMethods2.aClass16_3714 = RSString.joinRsStrings(new RSString[] { class64.opBase, StaticMethods.aClass16_2896 });// <col=00ff00>High Level
						// Alchemy<col=ffffff>
					}
				}
			} else {
				if (action == 12) {
					boolean bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 0, currentPlayer.waypointsY[0], 0);
					if (!bool) {
						bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					}
					cross_index = 0;
					crossX = Mouse.mouseClickX;
					cross_type = 2;
					crossY = Mouse.mouseClickY;
					outBuffer.putOpcode(141);
					outBuffer.putShortA(MapLoader.region_aboslute_x + interfaceId);
					outBuffer.putShortA(nodeId);
					outBuffer.putLEShortA(MapLoader.region_aboslute_z + slot);
				}
				if (action == 44) {
					Player class38_sub7_sub2 = localPlayers[nodeId];
					if (class38_sub7_sub2 != null) {
						walkPath(0, class38_sub7_sub2.waypointsY[0], 2, class38_sub7_sub2.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						cross_type = 2;
						crossX = Mouse.mouseClickX;
						crossY = Mouse.mouseClickY;
						cross_index = 0;
						outBuffer.putOpcode(228);
						outBuffer.putLEShortA(nodeId);
					}
				}
				if (action == 58) {
					NPC npc = activeNPCs[nodeId];
					if (npc != null) {
						walkPath(0, npc.waypointsY[0], 2, npc.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						cross_type = 2;
						crossY = Mouse.mouseClickY;
						cross_index = 0;
						crossX = Mouse.mouseClickX;
						outBuffer.putOpcode(182);
						outBuffer.putShortA(nodeId);
					}
				}
				if (action == 42 && StaticMethods.method779(true, slot, l, interfaceId)) {
					outBuffer.putOpcode(191);
					outBuffer.putShort(Varbit.anInt4013);
					outBuffer.putLEShort(MapLoader.region_aboslute_x + interfaceId);
					outBuffer.p4(UpdateServerThread.anInt169);
					outBuffer.putShort(slot - -MapLoader.region_aboslute_z);
					outBuffer.putLEShort((int) (l >>> 32) & 0x7fffffff);
				}
				if (action == 1003) {
					cross_index = 0;
					crossX = Mouse.mouseClickX;
					crossY = Mouse.mouseClickY;
					cross_type = 2;
					outBuffer.putOpcode(254);
					outBuffer.putShort(nodeId);
				}
				if (action == 23) {
					outBuffer.putOpcode(180);
					outBuffer.p4(interfaceId);
					RSInterface class64 = RSInterface.getInterface(interfaceId);
					if (class64.childBuffers != null && class64.childBuffers[0][0] == 5) {
						int i_5_ = class64.childBuffers[0][1];
						if (class64.anIntArray1010[0] != configs[i_5_]) {
							configs[i_5_] = class64.anIntArray1010[0];
							Class71_Sub1_Sub1.method1276(i_5_);
						}
					}
				}
				if (action == 29) {
					StaticMethods.method779(true, slot, l, interfaceId);
					outBuffer.putOpcode(50);
					outBuffer.putLEShortA(slot + MapLoader.region_aboslute_z);
					outBuffer.putShort(0x7fffffff & (int) (l >>> 32));
					outBuffer.putShort(interfaceId + MapLoader.region_aboslute_x);
				}
				if (action == 41) {
					Player class38_sub7_sub2 = localPlayers[nodeId];
					if (class38_sub7_sub2 != null) {
						walkPath(0, class38_sub7_sub2.waypointsY[0], 2, class38_sub7_sub2.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						crossY = Mouse.mouseClickY;
						crossX = Mouse.mouseClickX;
						cross_index = 0;
						cross_type = 2;
						outBuffer.putOpcode(105);
						outBuffer.putShort(nodeId);
					}
				}
				if (action == 6) {
					outBuffer.putOpcode(20);
					outBuffer.putLEShort(nodeId);
					outBuffer.putShort(slot);
					outBuffer.putIntB(interfaceId);
					atInventoryLoopCycle = 0;
					atInventoryInterface = RSInterface.getInterface(interfaceId);
					atInventoryIndex = slot;
				}
				if (action == 49) {
					outBuffer.putOpcode(108);
					outBuffer.putShort(Varbit.anInt4013);
					outBuffer.putShortA(slot);
					outBuffer.p4(interfaceId);
					outBuffer.putIntB(UpdateServerThread.anInt169);
				}
				if (action == 16) {
					Player class38_sub7_sub2 = localPlayers[nodeId];
					if (class38_sub7_sub2 != null) {
						walkPath(0, class38_sub7_sub2.waypointsY[0], 2, class38_sub7_sub2.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						cross_index = 0;
						cross_type = 2;
						crossX = Mouse.mouseClickX;
						crossY = Mouse.mouseClickY;
						outBuffer.putOpcode(52);
						outBuffer.putLEShort(nodeId);
					}
				}
				if (action == 46) {
					boolean bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 0, currentPlayer.waypointsY[0], 0);
					if (!bool) {
						bool = walkPath(0, interfaceId, 2, slot, false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
					}
					cross_type = 2;
					crossY = Mouse.mouseClickY;
					cross_index = 0;
					crossX = Mouse.mouseClickX;
					outBuffer.putOpcode(85);
					outBuffer.putLEShort(nodeId);
					outBuffer.putLEShort(MapLoader.region_aboslute_x + interfaceId);
					outBuffer.putShort(slot - -MapLoader.region_aboslute_z);
				}
				if (action == 51) {
					outBuffer.putOpcode(145);
					outBuffer.putIntB(interfaceId);
					outBuffer.putShortA(nodeId);
					outBuffer.putLEShort(slot);
					atInventoryLoopCycle = 0;
					atInventoryInterface = RSInterface.getInterface(interfaceId);
					atInventoryIndex = slot;
				}
				if (action == 38) {
					Player player = localPlayers[nodeId];
					if (player != null) {
						walkPath(0, player.waypointsY[0], 2, player.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						cross_type = 2;
						crossX = Mouse.mouseClickX;
						crossY = Mouse.mouseClickY;
						cross_index = 0;
						outBuffer.putOpcode(139);
						outBuffer.putShort(nodeId);
						outBuffer.putIntA(UpdateServerThread.anInt169);
						outBuffer.putShort(Varbit.anInt4013);
					}
				}
				if (action == 24) {
					outBuffer.putOpcode(251);
					outBuffer.putLEShortA(slot);
					outBuffer.p4(UpdateServerThread.anInt169);
					outBuffer.putLEShortA(nodeId);
					outBuffer.putIntA(interfaceId);
					outBuffer.putShortA(Varbit.anInt4013);
					atInventoryLoopCycle = 0;
					atInventoryInterface = RSInterface.getInterface(interfaceId);
					atInventoryIndex = slot;
				}
				if (action == 26) {
					outBuffer.putOpcode(188);
					outBuffer.p4(interfaceId);
					outBuffer.putShortA(nodeId);
					outBuffer.putLEShortA(slot);
					outBuffer.putLEShort(Queue.anInt411);
					outBuffer.putShort(AbstractTimer.anInt302);
					outBuffer.ip4(Class49.anInt759);
					atInventoryLoopCycle = 0;
					atInventoryInterface = RSInterface.getInterface(interfaceId);
					atInventoryIndex = slot;
				}
				if (action == 21) {
					NPC npc = activeNPCs[nodeId];
					if (npc != null) {
						walkPath(0, npc.waypointsY[0], 2, npc.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						cross_type = 2;
						cross_index = 0;
						crossY = Mouse.mouseClickY;
						crossX = Mouse.mouseClickX;
						outBuffer.putOpcode(248);
						outBuffer.putShortA(nodeId);
						outBuffer.putShortA(Varbit.anInt4013);
						outBuffer.putIntA(UpdateServerThread.anInt169);
					}
				}
				if (action == 7) {
					outBuffer.putOpcode(236);
					outBuffer.putLEShort(slot);
					outBuffer.putLEShortA(nodeId);
					outBuffer.putIntA(interfaceId);
					atInventoryLoopCycle = 0;
					atInventoryInterface = RSInterface.getInterface(interfaceId);
					atInventoryIndex = slot;
				}
				if (action == 3) {
					Player player = localPlayers[nodeId];
					if (player != null) {
						walkPath(0, player.waypointsY[0], 2, player.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						crossX = Mouse.mouseClickX;
						cross_index = 0;
						crossY = Mouse.mouseClickY;
						cross_type = 2;
						outBuffer.putOpcode(249);
						outBuffer.putLEShortA(nodeId);
					}
				}
				if (action == 14 && StaticMethods.method779(true, slot, l, interfaceId)) {
					outBuffer.putOpcode(122);
					outBuffer.putShort(MapLoader.region_aboslute_z + slot);
					outBuffer.putShort(AbstractTimer.anInt302);
					outBuffer.putIntB(Class49.anInt759);
					outBuffer.putLEShort(Queue.anInt411);
					outBuffer.putLEShortA(interfaceId + MapLoader.region_aboslute_x);
					outBuffer.putLEShort(0x7fffffff & (int) (l >>> 32));
				}
				if (action == 31) {
					NPC npc = activeNPCs[nodeId];
					if (npc != null) {
						walkPath(0, npc.waypointsY[0], 2, npc.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						crossX = Mouse.mouseClickX;
						cross_index = 0;
						crossY = Mouse.mouseClickY;
						cross_type = 2;
						outBuffer.putOpcode(82);
						outBuffer.putLEShortA(nodeId);
					}
				}
				if (action == 22) {
					Player player = localPlayers[nodeId];
					if (player != null) {
						walkPath(0, player.waypointsY[0], 2, player.waypointsX[0], false, false, 0, 0, currentPlayer.waypointsX[0], 1, currentPlayer.waypointsY[0], 1);
						cross_type = 2;
						crossY = Mouse.mouseClickY;
						crossX = Mouse.mouseClickX;
						cross_index = 0;
						outBuffer.putOpcode(245);
						outBuffer.putShort(nodeId);
					}
				}
				if (action == 36) {
					outBuffer.putOpcode(133);
					outBuffer.ip4(interfaceId);
					outBuffer.putShort(slot);
					outBuffer.putLEShortA(nodeId);
					atInventoryLoopCycle = 0;
					atInventoryInterface = RSInterface.getInterface(interfaceId);
					atInventoryIndex = slot;
				}
				if (NPC.anInt4374 != 0) {
					NPC.anInt4374 = 0;
					RSInterfaceList.setDirty(RSInterface.getInterface(Class49.anInt759));
				}
				if (LocTypeList.aBoolean3792) {
					handleSpellAction(1);
				}
				if (atInventoryInterface != null && atInventoryLoopCycle == 0) {
					RSInterfaceList.setDirty(atInventoryInterface);
				}
			}
		}
	}

	static final boolean walkPath(int objectType, int viewportY, int pathType, int viewportX, boolean findNear, boolean dummy, int rotation, int walkingFlag, int fromX, int sizeY, int fromY, int sizeX) {
		if (dummy != false) {
			MemoryCache.method64(95);
		}
		if (Keyboard.ctrlShiftPressed) {
			Class53.processClientCommands(0, RSString.createString("::telecs " + viewportX + " " + viewportY));
			return false;
		}
		if (currentPlayer.size != 2) {
			if (currentPlayer.size > 2) {
				return Class88.findPath(pathType, rotation, -29517, fromY, fromX, currentPlayer.size, objectType, viewportY, viewportX, sizeY, sizeX, findNear, walkingFlag);
			}
		} else {
			return Class23_Sub10_Sub3.findPath(walkingFlag, viewportY, fromY, fromX, rotation, findNear, 83, sizeY, viewportX, sizeX, pathType, objectType);
		}
		return Class35.findPath(rotation, viewportX, fromY, pathType, findNear, fromX, sizeX, walkingFlag, false, sizeY, objectType, viewportY);
	}

	public static final void drawToolTip(int i, byte b, int i_1_) {
		if (ContextMenu.menuActionRow >= 2 || NPC.anInt4374 != 0 || LocTypeList.aBoolean3792) {
			RSString class16;
			if (NPC.anInt4374 == 1 && ContextMenu.menuActionRow < 2) {
				class16 = RSString.joinRsStrings(new RSString[] { NPCType.aClass16_4163, StaticMethods2.aClass16_3762, StaticMethods2.aClass16_4281, Keyboard.aClass16_494 });
			} else if (LocTypeList.aBoolean3792 && ContextMenu.menuActionRow < 2) {
				class16 = RSString.joinRsStrings(new RSString[] { Class49.aClass16_764, StaticMethods2.aClass16_3762, StaticMethods2.aClass16_3714, Keyboard.aClass16_494 });// This
																																												// last
																																												// one
																																												// is
																																												// "->"
			} else {
				class16 = ContextMenu.getMenuActionName(-1 + ContextMenu.menuActionRow);
			}
			if (ContextMenu.menuActionRow > 2) {
				class16 = RSString.joinRsStrings(new RSString[] { class16, Class28.aClass16_423, RSString.valueOf(-2 + ContextMenu.menuActionRow), StringConstants.aClass16_1979 });// Other
																																													// options,
																																													// "Cancel
																																													// etc..
			}
			int i_2_ = FontCache.b12_full.draw_bouncingx_text(class16, i_1_ + 4, 15 + i, 16777215, 0, StaticMethods.aRandom4110, Class98.anInt1650);// Draws
			if (b != -64) {
				Class71.anInt1279 = -112;
			}
			RuntimeException_Sub1.method1589(15, i_2_ + FontCache.b12_full.calculate_width(class16), 4 + i_1_, i, true);
		}
	}

	public static final void draw_cross(int width, int screenY, int height, int i_44_, int screenX) {
		if ((cross_type ^ 0xffffffff) == i_44_) {
			StaticMedia.cross[cross_index / 100].draw_clipped_left_anchor(crossX - 8, -8 + crossY);
		}
		if (cross_type == 2) {
			StaticMedia.cross[4 - -(cross_index / 100)].draw_clipped_left_anchor(crossX - 8, -8 + crossY);
		}
		GroundDecoration.method1257(true);

	}

	public static int get_size_colour(int size, int maxSize) {
		int colour = 16776960;
		if (size > maxSize) {
			colour = 16711680;
		}
		return colour;
	}

	static final void renderPlayers(int i) {
		try {
			SoundEffects.updateMaskIndex = 0;
			StaticMethods.removedEntities = 0;
			int[] indices = new int[12];
			EntityUpdating.renderLocalPlayer(-3);
			indices[0] = StaticMethods2.packet.index;
			indices[1] = StaticMethods2.packet.getBitPosition();
			indices[2] = SoundEffects.updateMaskIndex;
			EntityUpdating.renderLocalPlayers((byte) -11);
			indices[3] = StaticMethods2.packet.index;
			indices[4] = StaticMethods2.packet.getBitPosition();
			indices[5] = SoundEffects.updateMaskIndex;
			Class45.renderGlobalPlayers(0);
			indices[6] = StaticMethods2.packet.index;
			indices[7] = StaticMethods2.packet.getBitPosition();
			indices[8] = SoundEffects.updateMaskIndex;
			PlayerMasks.parsePlayerUpdateMasks();
			indices[9] = StaticMethods2.packet.index;
			indices[10] = StaticMethods2.packet.getBitPosition();
			indices[11] = SoundEffects.updateMaskIndex;
			for (int i_24_ = i; (StaticMethods.removedEntities ^ 0xffffffff) < (i_24_ ^ 0xffffffff); i_24_++) {
				int i_25_ = Class54.removedEntityIndices[i_24_];
				if ((localPlayers[i_25_].lastUpdate ^ 0xffffffff) != (GameClient.timer ^ 0xffffffff)) {
					localPlayers[i_25_] = null;
				}
			}
			if ((StaticMethods.currentLength ^ 0xffffffff) != (StaticMethods2.packet.index ^ 0xffffffff)) {
				System.out.println("Local: [byte=" + indices[0] + ", bit=" + indices[1] + ", m.byte=" + indices[2] + "].");
				System.out.println("Local players: [byte=" + indices[3] + ", bit=" + indices[4] + ", m.byte=" + indices[5] + "].");
				System.out.println("Global: [byte=" + indices[6] + ", bit=" + indices[7] + ", m.byte=" + indices[8] + "].");
				System.out.println("Mask: [byte=" + indices[9] + ", bit=" + indices[10] + ", m.byte=" + indices[11] + "].");
				throw new RuntimeException("gpp1 pos:" + StaticMethods2.packet.index + " psize:" + StaticMethods.currentLength);
			}
			for (int i_26_ = 0; (i_26_ ^ 0xffffffff) > (StaticMethods.anInt3067 ^ 0xffffffff); i_26_++) {
				if (localPlayers[localPlayerPointers[i_26_]] == null) {
					throw new RuntimeException("gpp2 pos:" + i_26_ + " size:" + StaticMethods.anInt3067);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static final void handleInterfaceAction(RSString class16, byte b, int i, int i_2_, int i_3_) {
		RSInterface class64 = RSInterfaceList.get_dynamic_component(i_2_, i, (byte) -92);
		if (class64 != null) {
			if (class64.anObjectArray1116 != null) {
				CS2Event class23_sub9 = new CS2Event();
				class23_sub9.opbase = class16;
				class23_sub9.scriptArguments = class64.anObjectArray1116;
				class23_sub9.anInt2270 = i_3_;
				class23_sub9.component = class64;
				Class91.parseCS2Script(class23_sub9, (byte) -126);
			}
			boolean bool = true;
			if (class64.content_type > 0) {
				bool = NPC.method1092(class64, 205);
			}
			if (bool && DataBuffer.method1215(13417, Class47.getOptionMask(class64, 67).anInt2452, i_3_ - 1)) {
				if (i_3_ == 1) {
					outBuffer.putOpcode(230);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 2) {
					outBuffer.putOpcode(205);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 3) {
					outBuffer.putOpcode(127);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 4) {
					outBuffer.putOpcode(211);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 5) {
					outBuffer.putOpcode(203);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 6) {
					outBuffer.putOpcode(39);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 7) {
					outBuffer.putOpcode(187);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 8) {
					outBuffer.putOpcode(156);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 9) {
					outBuffer.putOpcode(128);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
				if (i_3_ == 10) {
					outBuffer.putOpcode(235);
					outBuffer.p4(i_2_);
					outBuffer.putShort(i);
				}
			}
		}
	}

	public static final void handleSpellAction(int i) {
		if (i == 1 && LocTypeList.aBoolean3792) {
			RSInterface class64 = RSInterfaceList.get_dynamic_component(UpdateServerThread.anInt169, Varbit.anInt4013, (byte) -100);
			if (class64 != null && class64.spellUsedListener != null) {
				CS2Event class23_sub9 = new CS2Event();
				class23_sub9.scriptArguments = class64.spellUsedListener;
				class23_sub9.component = class64;
				Class91.parseCS2Script(class23_sub9, (byte) -105);
			}
			LocTypeList.aBoolean3792 = false;
			RSInterfaceList.setDirty(class64);
		}
	}

	public static final void handleSpellOnItemAction(int i, int i_4_, int i_5_, int i_6_) {
		RSInterface class64 = RSInterfaceList.get_dynamic_component(i_5_, i_6_, (byte) -27);
		if (class64 != null && class64.spellUsedOnItemListener != null) {
			CS2Event class23_sub9 = new CS2Event();
			class23_sub9.scriptArguments = class64.spellUsedOnItemListener;
			class23_sub9.component = class64;
			Class91.parseCS2Script(class23_sub9, (byte) -127);
		}
		Class71_Sub1.anInt2725 = i_4_;
		LocTypeList.aBoolean3792 = true;
		UpdateServerThread.anInt169 = i_5_;
		Varbit.anInt4013 = i_6_;
		RSInterfaceList.setDirty(class64);
	}

	public static final void drawLoadingText(RSString class16, boolean draw) {
		int i = 4;
		int i_10_ = 6 + i;
		int i_11_ = 6 + i;
		int i_12_ = FontCache.p12_full.get_paragraph_width(class16, 250);
		int i_13_ = 13 * FontCache.p12_full.perform_word_warp(class16, 250);
		if (GLManager.opengl_mode) {
			GLShapes.fill_rectangle(-i + i_10_, i_11_ - i, i + i_12_ + i, i + i_13_ + i, 0);
			GLShapes.draw_rectangle(i_10_ - i, -i + i_11_, i + i_12_ - -i, i + i + i_13_, 16777215);
		} else {
			Rasterizer2D.fill_rectangle(-i + i_10_, i_11_ - i, i + i_12_ + i, i + i_13_ + i, 0);
			Rasterizer2D.draw_rectangle(i_10_ - i, -i + i_11_, i + i_12_ - -i, i + i + i_13_, 16777215);
		}
		FontCache.p12_full.draw_text(class16, i_10_, i_11_, i_12_, i_13_, 16777215, -1, 1, 1, 0);
		RuntimeException_Sub1.method1589(i + i + i_13_, i + i_12_ - -i, i_10_ + -i, -i + i_11_, true);
		if (draw) {
			if (GLManager.opengl_mode) {
				GLManager.render_frame();
			} else {
				Canvas canvas = getCanvas();
				try {
					Graphics graphics = canvas.getGraphics();
					GameClient.software_frame_buffer.draw(graphics, 0, 0);
				} catch (Exception exception) {
					canvas.repaint();
				}
			}
		} else {
			StaticMethods2.method618(i_10_, i_12_, i_13_, i_11_);
		}
	}

	/**
	 * Method used to launch the game.
	 */
	public void launch(Frame frame) {
		setIp(getSetting().getIp());
		setWorldId(getSetting().getWorld());
		setGameType(getSetting().getEnvironment().equals("local") ? 2 : getSetting().getEnvironment().equals("office") ? 1 : 0);
		setLiveRcWip(0);
		setLanguage(0);
		setGameValue(0);
		setAffId(0);
		setMembers(true);
		construct(SIZE, frame);
	}

	public final void js5_process() {
		if (!js5_client.process()) {
			// System.out.println("connect");
			js5_connect();
		}
	}

	/**
	 * The main method to start the game.
	 */
	@Override
	public final void update(int i) {
		GameClient.timer++;
		setting = GameLaunch.getSetting();
		if (i != 45) {
			StringConstants.aClass16_1979 = null;
		}
		if (GameClient.timer % 1000 == 1) {
			GregorianCalendar gregoriancalendar = new GregorianCalendar();
			Class98.anInt1650 = gregoriancalendar.get(11) * 600 - (-(gregoriancalendar.get(12) * 10) - gregoriancalendar.get(13) / 6);
			StaticMethods.aRandom4110.setSeed(Class98.anInt1650);
		}
		js5_process();
		if (js5_manager != null) {
			js5_manager.process();
		}
		Class87_Sub3.method1417((byte) 114);
		Class48.process_audio();
		StaticMethods2.method230((byte) 122);
		Stereo.method84(true);
		if (GLManager.opengl_mode) {
			GLManager.cleanup();
		}
		Mouse.wheelConsumed = false;
		if (InputManager.mouse_wheel != null) {
			int i_1_ = InputManager.mouse_wheel.method1236(-68);
			Class48.anInt749 = i_1_;
		}
		if (DebugConsole.is_open()) {
			DebugConsole.process();
		}
		// RSInterfaceList.setAllDirty(11980);
		if (clientState != 0) {
			if (clientState != 5) {
				if (clientState != 10) {
					if (clientState == 20) {
						LoginHandler.update_loginscreen(this);
						LoginHandler.login();
					} else if (clientState == 25) {
						MapLoader.load_region();
					}
				} else {
					LoginHandler.update_loginscreen(this);
				}
			} else {
				LoginHandler.update_loginscreen(this);
				processStartup(6);
				StaticMethods.reset_frametimer();
			}
		} else {
			processStartup(6);
			StaticMethods.reset_frametimer();
		}
		if (clientState == 30) {
			Class56.method1188(-1990505050);
		} else if (clientState == 40) {
			LoginHandler.login();
		}
	}

	@Override
	final void method22(byte b) {
		if (StaticMethods.aClass98_3513 != null) {
			StaticMethods.aClass98_3513.aBoolean1656 = false;
		}
		StaticMethods.aClass98_3513 = null;
		if (ColourImageCacheSlot.session != null) {
			pingManager.disconnect();
			ColourImageCacheSlot.session.shutdown();
			ColourImageCacheSlot.session = null;
		}
		GraphicsCache.removeAll();
		InputManager.detach_keyboard(GameShell.getCanvas());
		InputManager.detach_mouse(GameShell.getCanvas());
		if (InputManager.mouse_wheel != null) {
			InputManager.mouse_wheel.detach(GameShell.getCanvas());
		}
		if (WindowMode.full_screen_frame != null) {
			FullscreenUtilities.exit_full_screen(WindowMode.full_screen_frame, gameSignlink);
			WindowMode.full_screen_frame = null;
		}
		ObjType.method726(b + -20931);
		NPC.method1093(100);
		InputManager.mouse_wheel = null;
		if (SeekableFile.aStereo_471 != null) {
			SeekableFile.aStereo_471.method72(0);
		}
		if (Class97.aStereo_1646 != null) {
			Class97.aStereo_1646.method72(0);
		}
		js5_client.close_connection();
		async_cache.shutdown();
		try {
			Ping.quit();
		} catch (Throwable e) {
			// NOOP
		}
		try {
			if (data_file != null) {
				data_file.close((byte) 69);
			}
			if (index_files != null) {
				for (SeekableFile index_file : index_files) {
					if (index_file != null) {
						index_file.close((byte) -30);
					}
				}
			}
			if (master_index_file != null) {
				master_index_file.close((byte) -115);
			}
			if (random_file == null) {
			}
			random_file.close((byte) -121);
		} catch (java.io.IOException ioexception) {
			ioexception.printStackTrace();
		}
	}

	public static void launchDesktop(Frame frame) {
		GameShell.setDesktop(true);
		GameClient game = create(GameLaunch.getSetting());
		game.launch(frame);
	}

	public static void updateGameScreen() {
		if (GameClient.software_frame_buffer == null || GameClient.software_frame_buffer.width != window_width || GameClient.software_frame_buffer.height != window_height) {
			GameClient.software_frame_buffer = SoftwareGraphicsBuffer.create(GameShell.getCanvas(), GameShell.window_width, GameShell.window_height);
		}
	}

	public static void handle_resize() {
		synchronized (active_client) {
			if (WindowMode.full_screen_frame == null) {
				Container game_container;
				if (frame != null) {
					game_container = frame;
				} else if (gameSignlink.applet != null) {
					game_container = gameSignlink.applet;
				} else {
					game_container = active_gameshell;
				}
				window_width = game_container.getSize().width;
				window_height = game_container.getSize().height;
				if (game_container == frame) {
					Insets insets = frame.getInsets();
					window_width -= insets.left + insets.right;
					window_height -= insets.bottom + insets.top;
				}
				if (WindowMode.get_wm() != 1) {
					WindowMode.update_margins();
				} else {
					frame_width = 765;
					top_margin = 0;
					frame_height = 503;
					left_margin = (window_width - 765) / 2;
				}
				if (GLManager.opengl_mode) {
					GLState.update_viewport(frame_width, frame_height);
				}
				canvas.setSize(frame_width, frame_height);
				if (frame != game_container) {
					canvas.setLocation(left_margin, top_margin);
				} else {
					Insets insets = frame.getInsets();
					canvas.setLocation(insets.left + left_margin, top_margin + insets.top);
				}
				if (GameClient.interface_top_id != -1) {
					RSInterfaceLayout.calc_layout();
				}
				if (WindowMode.get_wm() != 1) {
					// updateGameScreen(); TOOD: what is dis wrong way
				}
				clear_awt_graphics();
			}
		}
	}

	public static void clear_awt_graphics() {
		if (WindowMode.full_screen_frame == null) {
			int i_13_ = left_margin;
			int i_14_ = top_margin;
			int i_15_ = -i_13_ + window_width + -frame_width;
			int i_16_ = -frame_height + window_height - i_14_;
			do {
				if (i_13_ > 0 || i_15_ > 0 || i_14_ > 0 || i_16_ > 0) {
					try {
						Container container;
						if (frame != null) {
							container = frame;
						} else if (gameSignlink.applet == null) {
							container = active_gameshell;
						} else {
							container = gameSignlink.applet;
						}
						int i_17_ = 0;
						int i_18_ = 0;
						if (container == frame) {
							Insets insets = frame.getInsets();
							i_17_ = insets.left;
							i_18_ = insets.top;
						}
						Graphics graphics = container.getGraphics();
						graphics.setColor(Color.black);
						if (i_13_ > 0) {
							graphics.fillRect(i_17_, i_18_, i_13_, window_height);
						}
						if (i_14_ > 0) {
							graphics.fillRect(i_17_, i_18_, window_width, i_14_);
						}
						if (i_15_ > 0) {
							graphics.fillRect(-i_15_ + i_17_ + window_width, i_18_, i_15_, window_height);
						}
						if (i_16_ <= 0) {
							break;
						}
						graphics.fillRect(i_17_, -i_16_ + i_18_ + window_height, window_width, i_16_);
					} catch (Exception exception) {
						/* empty */
					}
					break;
				}
			} while (false);
		}
	}

	static final void method36(byte b) {
		for (InterfaceNode inter = (InterfaceNode) Class36.anOa565.get_first(); inter != null; inter = (InterfaceNode) Class36.anOa565.get_next()) {
			int i = inter.interfaceId;
			if (AbstractTimer.hasActiveInterface(-10924, i)) {
				boolean bool = true;
				RSInterface[] class64s = StaticMethods.cached_interfaces[i];
				for (RSInterface class64 : class64s) {
					if (class64 != null) {
						bool = class64.newer_interface;
						break;
					}
				}
				if (!bool) {
					int interfaceId = (int) inter.uid;
					RSInterface class64 = RSInterface.getInterface(interfaceId);
					if (class64 != null) {
						RSInterfaceList.setDirty(class64);
					}
				}
			}
		}
		if (b != 102) {
			FontCache.p11_full = null;
		}
	}

	public static boolean force_resize = false;

	@Override
	final void main_redraw_inner(boolean dummyTrue) {
		boolean clearScreen = false;
		boolean bool_7_ = Entity.method1087(false);
		if (bool_7_ && Js5.aBoolean1806 && SeekableFile.aStereo_471 != null) {
			SeekableFile.aStereo_471.method71(false);
		}
		if (GLManager.opengl_mode) {
			// TODO: until out scene render is added
			GLState.clear_screen();
		}
		if (clientState != 30) {
			if (last_resize_time != 0L && last_resize_time < TimeTools.getMillis()) {
				WindowMode.set_wm(WindowMode.get_wm(), false, -1, -1);
			} else if (recommend_canvas_replace) {
				RSInterfaceList.setAllDirty(11980);
			}
		}
		if (WindowMode.full_screen_frame == null) {
			java.awt.Container container;
			if (frame == null) {
				if (gameSignlink.applet == null) {
					container = active_gameshell;
				} else {
					container = gameSignlink.applet;
				}
			} else {
				container = frame;
			}
			int c_w = container.getSize().width;
			int c_h = container.getSize().height;
			if (container == frame) {
				Insets insets = frame.getInsets();
				c_w -= insets.right + insets.left;
				c_h -= insets.bottom + insets.top;
			}
			if (window_width != c_w || window_height != c_h || force_resize) {
				handle_resize();
				last_resize_time = TimeTools.getMillis() + 500L;
				force_resize = false;
			}
		}
		if (WindowMode.full_screen_frame != null && !focusIn && clientState != 30) {
			WindowMode.set_wm(GamePreferences.windowMode, false, -1, -1);
		}
		if (GameShell.fullRedraw) {
			clearScreen = true;
			GameShell.fullRedraw = false;
		}
		if (GLManager.opengl_mode) {
			RSInterfaceList.setAllDirty(11980);
		}
		if (clientState == 1000) {
			clientState = -1;
			GameShell.drawLoadingText(null, gamePercentage, clearScreen, RSString.createString("Can't connect to update server..."));
		}
		if ((clientState ^ 0xffffffff) != -1) {
			if (clientState != 5 && clientState != 10 && clientState != 20) {
				if (clientState == 25) {
					if (MapLoader.loading_stage == 1) {
						if (GroundItemNode.anInt3667 < MapLoader.num_regions_failed) {
							GroundItemNode.anInt3667 = MapLoader.num_regions_failed;
						}
						int i = 50 * (-MapLoader.num_regions_failed + GroundItemNode.anInt3667) / GroundItemNode.anInt3667;
						drawLoadingText(RSString.joinRsStrings(new RSString[] { RSInterface.aClass16_1139, Class71_Sub2.aClass16_2735, RSString.valueOf(i), StaticMethods.aClass16_3450 }), false);
					} else if (MapLoader.loading_stage == 2) {
						if (StaticMethods.anInt3036 > StaticMethods.anInt3055) {
							StaticMethods.anInt3055 = StaticMethods.anInt3036;
						}
						int i = 50 - -((-StaticMethods.anInt3036 + StaticMethods.anInt3055) * 50 / StaticMethods.anInt3055);
						drawLoadingText(RSString.joinRsStrings(new RSString[] { RSInterface.aClass16_1139, Class71_Sub2.aClass16_2735, RSString.valueOf(i), StaticMethods.aClass16_3450 }), false);
					} else {
						drawLoadingText(RSInterface.aClass16_1139, false);
					}
				} else if (clientState == 30) {
					StaticMethods2.method693(25602);
				} else if (clientState == 40) {
					drawLoadingText(RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3201, InstrumentDefinition.aClass16_276, StaticMethods.aClass16_2898 }), false);
				}
			} else {
				LoginHandler.render_loginscreen(FontCache.b12_full, FontCache.p11_full);
			}
		} else {
			GameShell.drawLoadingText(null, gamePercentage, clearScreen, loadingMessage);
		}
		if (DebugConsole.is_open()) {
			DebugConsole.draw();
		}
		if (GLManager.opengl_mode) {
			GLManager.render_frame();
			for (int i = 0; i < StaticMethods.widget_quads; i++) {
				Class36.needs_clipping[i] = false;
			}
		} else {
			Canvas canvas = GameShell.getCanvas();
			if (clientState == 30 && Class57.anInt901 == 0 && !clearScreen) {
				try {
					Graphics graphics = canvas.getGraphics();
					for (int i = 0; i < StaticMethods.widget_quads; i++) {
						if (Class36.needs_clipping[i]) {
							GameClient.software_frame_buffer.draw(graphics, StaticMethods.quadsx[i], StaticMethods.anIntArray2286[i], WallDecoration.anIntArray372[i], Class55.anIntArray865[i]);
							Class36.needs_clipping[i] = false;
						}
					}
				} catch (Exception exception) {
					canvas.repaint();
				}
			} else {
				if (clientState > 0) {
					try {
						Graphics graphics = canvas.getGraphics();
						GameClient.software_frame_buffer.draw(graphics, 0, 0);
						for (int i = 0; i < StaticMethods.widget_quads; i++) {
							Class36.needs_clipping[i] = false;
						}
					} catch (Exception exception) {
						canvas.repaint();
					}
				}
			}
		}
	}

	public final void signal_js5_error(int i, byte b) {
		js5_connect_request = null;
		js5_client.reconnect_count++;
		js5_client.status = i;
		js5_connect_stage = 0;
		js5_stream = null;
	}

	/**
	 * Method called when the applet is created.
	 */
	@Override
	public final void init() {
		setting = GameLaunch.getSetting();
		if (checkHost()) {
			GameClient.setWorldId(setting.getWorld());
			GameClient.setLiveRcWip(0);
			GameClient.setGameType(getSetting().getEnvironment().equals("local") ? 2 : getSetting().getEnvironment().equals("office") ? 1 : 0);
			String string = getParameter("lowmem");
			String string_8_ = getParameter("members");
			if (string_8_ != null && string_8_.equals("1")) {
				GameClient.setMembers(true);
			} else {
				GameClient.setMembers(false);
			}
			GameClient.setMembers(true);
			String string_9_ = getParameter("lang");
			if (string_9_ != null && string_9_.equals("1")) {
				GrandExchangeOffer.method1446(-123);
				GameClient.setLanguage(1);
			}
			String string_10_ = getParameter("game");
			if (string_10_ == null || !string_10_.equals("1")) {
				GameClient.setGameValue(0);
			} else {
				GameClient.setGameValue(1);
			}
			try {
				NPC.anInt4376 = Integer.parseInt(getParameter("js"));
				Class47.anInt741 = Integer.parseInt(getParameter("plug"));
				GameClient.setAffId(Integer.parseInt(getParameter("affid")));
			} catch (Exception exception) {
				/* empty */
			}
			CacheFileWorker.aClass16_2877 = Class71_Sub1.aClass16_2730.method172(this);
			if (CacheFileWorker.aClass16_2877 == null) {
				CacheFileWorker.aClass16_2877 = StaticMethods.empty_string;
			}
			setIp(getSetting().getIp());
			start_applet(-16273, 498, GameClient.getLiveRcWip() + 32, 503, 765);
		}
	}

	static final Js5 create_js5(int discard_unpacked, int archive_id, boolean bool, boolean prefetch) {
		FileSystem archive_datafs = null;
		if (data_file != null) {
			archive_datafs = new FileSystem(archive_id, data_file, index_files[archive_id], 1000000);
		}
		js5_archives[archive_id] = js5_manager.create_archive(archive_id, index_datafs, archive_datafs);
		if (prefetch) {
			js5_archives[archive_id].prefetch_groups();
		}
		return new Js5(js5_archives[archive_id], bool, discard_unpacked);
	}

	public static int[] archive_progress_weight = { 4, 4, 1, 2, 6, 4, 2, 44, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	public static void loadNativeLibrary(String name) {
		if (!nativeManager.load(name)) {
			throw new RuntimeException("Failed to load native library name: " + name);
		}
	}

	public final void processStartup(int i) {
		if (loadingStage == 0) {
			Runtime runtime = Runtime.getRuntime();
			int usedKBs = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
			long current_time = TimeTools.getMillis();
			if (last_ramchk == 0L) {
				last_ramchk = current_time;
			}
			if (usedKBs > 16384 && current_time - last_ramchk < 5000L) {
				if (-lastGC + current_time > 1000L) {
					System.gc();
					lastGC = current_time;
				}
				gamePercentage = 5;
				loadingMessage = StaticMethods2.aClass16_1748;
			} else {
				gamePercentage = 5;
				loadingStage = 10;
				loadingMessage = StaticMethods.aClass16_3362;
			}
		} else if (loadingStage == 10) {
			SceneLighting.initialise(4, 104, 104);
			for (int i_12_ = 0; i_12_ < 4; i_12_++) {
				MapLoader.collision_maps[i_12_] = new CollisionMap(104, 104);
			}
			if (js5_manager == null) {
				js5_manager = new Js5Manager(js5_client, async_cache);
			}
			if (!js5_manager.is_master_index_ready()) {
				loadingStage = 10;
			} else {
				loadingStage = 30;
				loadingMessage = Class73.aClass16_1327;
				gamePercentage = 10;
			}
		} else if (loadingStage == 30) {
			CacheConstants.animFramesCacheIdx = create_js5(1, 0, false, true);
			CacheConstants.animSkinsCacheIdx = create_js5(1, 1, false, true);
			CacheConstants.configs_js5 = create_js5(1, 2, false, true);
			CacheConstants.interfaceCacheIdx = create_js5(1, 3, false, true);
			CacheConstants.soundsCacheIdx = create_js5(1, 4, false, true);
			CacheConstants.map_js5 = create_js5(1, 5, true, true);
			CacheConstants.musicCacheIdx = create_js5(1, 6, true, false);
			CacheConstants.modelCacheIdx = create_js5(1, 7, false, true);
			CacheConstants.sprites_js5 = create_js5(1, 8, false, true);
			CacheConstants.textureCacheIdx = create_js5(1, 9, false, true);
			CacheConstants.huffmanCacheIdx = create_js5(1, 10, false, true);
			CacheConstants.extraMusicCacheIdx = create_js5(1, 11, false, true);
			CacheConstants.scriptCacheIdx = create_js5(1, 12, false, true);
			CacheConstants.fonts_js5 = create_js5(1, 13, false, true);
			CacheConstants.instrumentCacheIdx = create_js5(1, 14, false, false);
			CacheConstants.soundEffectsCacheIdx = create_js5(1, 15, false, true);
			CacheConstants.locCacheIdx = create_js5(1, 16, false, true);
			CacheConstants.scriptMapIdx = create_js5(1, 17, false, true);
			CacheConstants.npcCacheIdx = create_js5(1, 18, false, true);
			CacheConstants.itemCacheIdx = create_js5(1, 19, false, true);
			CacheConstants.animSequenceCacheIdx = create_js5(1, 20, false, true);
			CacheConstants.graphicCacheIdx = create_js5(1, 21, false, true);
			CacheConstants.varbitCacheIdx = create_js5(1, 22, false, true);
			CacheConstants.worldMapCacheIdx = create_js5(1, 23, true, true);
			CacheConstants.localQuickChatIdx = create_js5(1, 24, false, true);
			CacheConstants.globalQuickChatIdx = create_js5(1, 25, false, true);
			CacheConstants.materialsCacheIdx = create_js5(1, 26, true, true);
			CacheConstants.dllsJs5 = create_js5(1, 27, true, true);
			loadingStage = 40;
			gamePercentage = 15;
			loadingMessage = Class61.aClass16_960;
		} else if (loadingStage == 40) {
			int percent = 0;
			for (int i_9_ = 0; i_9_ < NUM_CACHE_ARCHIVES; i_9_++) {
				if (js5_archives[i_9_] != null) {
					percent += js5_archives[i_9_].get_initialization_progress() * archive_progress_weight[i_9_] / 100;
				}
			}
			if (percent != 95) {
				if (percent != 0) {
					loadingMessage = RSString.joinRsStrings(new RSString[] { InteractiveEntity.aClass16_617, RSString.valueOf(percent), CacheFileWorker.aClass16_2867 });
				}
				gamePercentage = 20;
			} else {
				gamePercentage = 20;
				loadingMessage = Class53.aClass16_836;
				StaticMedia.fetch_ids(CacheConstants.sprites_js5);
				LoginHandler.unpackLoginComponents(CacheConstants.musicCacheIdx, CacheConstants.huffmanCacheIdx, i + 110, CacheConstants.sprites_js5);
				loadingStage = 45;
			}
		} else if (loadingStage == 45) {
			StaticMethods.method422(ClientOptions.clientoption_stereo, 22050, (byte) 53, 2);
			SomeSoundClass someSoundClass = new SomeSoundClass();
			someSoundClass.method549((byte) -82, 9, 128);
			SeekableFile.aStereo_471 = Class87_Sub1.method1410(22050, 0, (byte) 43, gameSignlink, GameShell.getCanvas());
			SeekableFile.aStereo_471.method78(someSoundClass, -15878);
			StaticMethods2.setSoundEffectsCache(CacheConstants.soundsCacheIdx, someSoundClass, CacheConstants.instrumentCacheIdx, -114, CacheConstants.soundEffectsCacheIdx);
			Class97.aStereo_1646 = Class87_Sub1.method1410(2048, 1, (byte) 117, gameSignlink, GameShell.getCanvas());
			Class23_Sub7.aClass23_Sub10_Sub4_2201 = new Class23_Sub10_Sub4();
			Class97.aStereo_1646.method78(Class23_Sub7.aClass23_Sub10_Sub4_2201, -15878);
			WallObject.aClass45_1462 = new Class45(22050, Keyboard.sampleRate);
			loadingMessage = SongUpdater.aClass16_176;
			loadingStage = 50;
			gamePercentage = 30;
		} else if (loadingStage == 50) {
			int num_downloaded = StaticMedia.get_completed_fonts(CacheConstants.sprites_js5, CacheConstants.fonts_js5);
			int num_total = StaticMedia.get_total_fonts();
			if (num_downloaded < num_total) {
				loadingMessage = RSString.joinRsStrings(new RSString[] { Entity.aClass16_2703, RSString.valueOf(100 * num_downloaded / num_total), CacheFileWorker.aClass16_2867 });
				gamePercentage = 35;
			} else {
				FontCache.initialise(CacheConstants.fonts_js5, CacheConstants.sprites_js5);
				gamePercentage = 35;
				loadingMessage = Class25.aClass16_392;
				loadingStage = 55;
			}
		} else if (loadingStage == 55) {
			// Class105_Sub2.getArchive((byte) -108);
			loadingStage = 60;
		} else if (loadingStage == 60) {
			int num_completed = LoginHandler.get_completed_count(CacheConstants.sprites_js5);
			int num_total = GameClient.get_completions_count();
			if (num_completed < num_total) {
				loadingMessage = RSString.joinRsStrings(new RSString[] { PlayerAppearance.aClass16_804, RSString.valueOf(num_completed * 100 / num_total), CacheFileWorker.aClass16_2867 });
				gamePercentage = 40;
			} else {
				nativeManager = new Js5NativeManager(CacheConstants.dllsJs5);
				loadingStage = 66;
				gamePercentage = 40;
			}
		} else if (loadingStage == 66) {
			int percentage = Js5PrefetchManager.process();
			if (percentage < 100) {
				loadingMessage = RSString.joinRsStrings(new RSString[] { CollisionMap.loadingPrefetchData, RSString.valueOf(percentage), CacheFileWorker.aClass16_2867 });
				gamePercentage = 50;
			} else {
				loadingMessage = MonochromeImageCacheSlot.aClass16_2345;
				loadingStage = 68;
			}
		} else if (loadingStage == 68) {
			gamePercentage = 45;
			loadingMessage = CacheFileWorker.aClass16_2862;
			updateClientState(5);// login sprites shit
			loadingStage = 70;
		} else if (loadingStage == 70) {
			CacheConstants.configs_js5.is_fully_cached();
			int i_17_ = 0;
			i_17_ += CacheConstants.configs_js5.get_progress();
			CacheConstants.locCacheIdx.is_fully_cached();
			i_17_ += CacheConstants.locCacheIdx.get_progress();
			CacheConstants.scriptMapIdx.is_fully_cached();
			i_17_ += CacheConstants.scriptMapIdx.get_progress();
			CacheConstants.npcCacheIdx.is_fully_cached();
			i_17_ += CacheConstants.npcCacheIdx.get_progress();
			CacheConstants.itemCacheIdx.is_fully_cached();
			i_17_ += CacheConstants.itemCacheIdx.get_progress();
			CacheConstants.animSequenceCacheIdx.is_fully_cached();
			i_17_ += CacheConstants.animSequenceCacheIdx.get_progress();
			CacheConstants.graphicCacheIdx.is_fully_cached();
			i_17_ += CacheConstants.graphicCacheIdx.get_progress();
			CacheConstants.varbitCacheIdx.is_fully_cached();
			i_17_ += CacheConstants.varbitCacheIdx.get_progress();
			CacheConstants.localQuickChatIdx.is_fully_cached();
			i_17_ += CacheConstants.localQuickChatIdx.get_progress();
			CacheConstants.globalQuickChatIdx.is_fully_cached();
			i_17_ += CacheConstants.globalQuickChatIdx.get_progress();
			CacheConstants.materialsCacheIdx.is_fully_cached();
			i_17_ += CacheConstants.materialsCacheIdx.get_progress();
			if (i_17_ < 1000) {
				loadingMessage = RSString.joinRsStrings(new RSString[] { CollisionMap.aClass16_1311, RSString.valueOf(i_17_ / 10), CacheFileWorker.aClass16_2867 });
				gamePercentage = 50;
			} else {
				QuickChatDefinition.method771(CacheConstants.configs_js5, (byte) 110);
				FluTypeList.method562((byte) -123, CacheConstants.configs_js5);
				StaticMethods2.method233((byte) -109, CacheConstants.configs_js5, CacheConstants.modelCacheIdx);
				LocType.setObjectCache(CacheConstants.locCacheIdx, CacheConstants.modelCacheIdx, GameClient.isMembers());
				InstrumentDefinition.method130(CacheConstants.modelCacheIdx, CacheConstants.npcCacheIdx, false);
				ObjType.setItemCache(GameClient.isMembers(), CacheConstants.modelCacheIdx, CacheConstants.itemCacheIdx);
				SeqTypeList.initialise(CacheConstants.animSequenceCacheIdx, CacheConstants.animSkinsCacheIdx, CacheConstants.animFramesCacheIdx);
				SpotType.setGraphicCache(CacheConstants.modelCacheIdx, CacheConstants.graphicCacheIdx);
				Class87_Sub4.method1422(CacheConstants.varbitCacheIdx, (byte) 111);
				VarpDefinition.initializeVarpWorker(CacheConstants.configs_js5);
				RSInterfaceList.method677(CacheConstants.fonts_js5, CacheConstants.modelCacheIdx, false, CacheConstants.sprites_js5, CacheConstants.interfaceCacheIdx);
				StaticMethods.method784(CacheConstants.configs_js5);
				ParamType.setConfigCache(CacheConstants.configs_js5);
				StructType.setConfigCache(CacheConstants.configs_js5);
				MonochromeImageCacheSlot.method867(CacheConstants.scriptMapIdx, 0);
				Class30.method957((byte) 83, CacheConstants.localQuickChatIdx, new Class79(), CacheConstants.globalQuickChatIdx);
				QuickChatDefinition.initializeQuickChatWorkers(CacheConstants.globalQuickChatIdx, CacheConstants.localQuickChatIdx, 75);
				MSITypeList.initialise(CacheConstants.configs_js5, CacheConstants.sprites_js5);
				gamePercentage = 50;
				loadingMessage = StaticMethods.aClass16_2929;
				StaticMethods.method283((byte) -118);
				loadingStage = 80;
			}
		} else if (loadingStage == 80) {
			int num_loaded = StaticMedia.get_completed_sprites(CacheConstants.sprites_js5);
			int num_total = StaticMedia.get_total_sprites();
			if (num_loaded < num_total) {
				loadingMessage = RSString.joinRsStrings(new RSString[] { ObjType.aClass16_3914, RSString.valueOf(num_loaded * 100 / num_total), CacheFileWorker.aClass16_2867 });
				gamePercentage = 60;
			} else {
				StaticMedia.fetch_sprites(CacheConstants.sprites_js5);
				gamePercentage = 60;
				loadingStage = 90;
				loadingMessage = Class87.aClass16_1482;
			}
		} else if (i == 6) {
			if (loadingStage == 90) {
				if (!CacheConstants.textureCacheIdx.is_fully_cached()) {
					loadingMessage = RSString.joinRsStrings(new RSString[] { CS2CallFrame.aClass16_771, RSString.valueOf(CacheConstants.textureCacheIdx.get_progress()), CacheFileWorker.aClass16_2867 });
					gamePercentage = 70;
				} else {
					GraphicTools.initialise_rasterizer(new MaterialRawList(CacheConstants.materialsCacheIdx), new TextureCache(CacheConstants.textureCacheIdx));
					GraphicTools.change_brightness(0.7f);
					loadingStage = 110;
					gamePercentage = 70;
					loadingMessage = InvType.aClass16_4200;
				}
			} else if (loadingStage == 110) {
				StaticMethods.aClass98_3513 = new Class98();
				gameSignlink.newRunnable(10, StaticMethods.aClass98_3513, (byte) 72);
				loadingStage = 120;
				loadingMessage = Class47.aClass16_723;
				gamePercentage = 75;
			} else if (loadingStage == 120) {
				if (!CacheConstants.huffmanCacheIdx.is_group_cached("huffman")) {
					loadingMessage = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3506, CS2ScriptDefinition.aClass16_4231 });
					gamePercentage = 80;
				} else {
					Huffman huffman = new Huffman(CacheConstants.huffmanCacheIdx.get_file("huffman", ""));
					InterfaceNode.method906(i + 53, huffman);
					loadingMessage = Class61.aClass16_961;
					loadingStage = 130;
					gamePercentage = 80;
				}
			} else if (loadingStage == 130) {
				if (!CacheConstants.interfaceCacheIdx.is_fully_cached()) {
					loadingMessage = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3424, RSString.valueOf(4 * CacheConstants.interfaceCacheIdx.get_progress() / 5), CacheFileWorker.aClass16_2867 });
					gamePercentage = 85;
				} else if (!CacheConstants.scriptCacheIdx.is_fully_cached()) {
					loadingMessage = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3424, RSString.valueOf(CacheConstants.scriptCacheIdx.get_progress() / 6 + 80), CacheFileWorker.aClass16_2867 });
					gamePercentage = 85;
				} else if (!CacheConstants.fonts_js5.is_fully_cached()) {
					loadingMessage = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3424, RSString.valueOf(96 - -(CacheConstants.fonts_js5.get_progress() / 20)), CacheFileWorker.aClass16_2867 });
					gamePercentage = 85;
				} else if (!CacheConstants.worldMapCacheIdx.is_group_cached("details")) {
					loadingMessage = RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3424, RSString.valueOf(96 - -(CacheConstants.worldMapCacheIdx.get_group_progress("details") / 10)), CacheFileWorker.aClass16_2867 });
					gamePercentage = 85;
				} else {
					WorldMap.initialise(CacheConstants.worldMapCacheIdx, StaticMedia.mapfunction_raw);
					loadingStage = 140;
					gamePercentage = 100;
					loadingMessage = Class45.aClass16_699;
				}
			} else if (loadingStage == 140) {
				CacheConstants.map_js5.discard_names(true, (byte) 81, false);
				CacheConstants.musicCacheIdx.discard_names(true, (byte) 89, true);
				CacheConstants.sprites_js5.discard_names(true, (byte) 123, true);
				CacheConstants.fonts_js5.discard_names(true, (byte) 101, true);
				CacheConstants.huffmanCacheIdx.discard_names(true, (byte) 79, true);
				CacheConstants.configs_js5.discard_unpacked = 2;
				CacheConstants.scriptMapIdx.discard_unpacked = 2;
				CacheConstants.locCacheIdx.discard_unpacked = 2;
				CacheConstants.npcCacheIdx.discard_unpacked = 2;
				CacheConstants.itemCacheIdx.discard_unpacked = 2;
				CacheConstants.animSequenceCacheIdx.discard_unpacked = 2;
				CacheConstants.graphicCacheIdx.discard_unpacked = 2;
				updateClientState(10);
				WindowMode.set_wm(GamePreferences.windowMode, false, 765, 503);
			}
		}
	}

	@Override
	final void main_init(byte b) {
		Queuable.anInt2320 = Configurations.SERVER_PORT;
		if (GameClient.getGameValue() != 1) {
			Class42.aShortArray655 = Class31.aShortArray489;
			NameHashTable.aShortArrayArray1189 = GroundItem.aShortArrayArray2497;
			StaticMethods.aShortArray3417 = DataBuffer.aShortArray983;
			Class44.aShortArrayArray679 = RuntimeException_Sub1.aShortArrayArray1851;
		} else {
			NameHashTable.aShortArrayArray1189 = Class65.aShortArrayArray1158;
			StaticMethods.aShortArray3417 = StaticMethods.aShortArray3190;
			Class44.aShortArrayArray679 = Class71.aShortArrayArray1269;
			Class42.aShortArray655 = MonochromeImageCache.aShortArray1667;
		}
		socket_port = Queuable.anInt2320;
		async_cache = new AsyncCache(gameSignlink);
		js5_client = new Js5Client();
		pingManager = new PingManager(getIp());
		BZIPContext.aShortArray1339 = GameClient.client_palette = SpotType.npcColors = ObjType.itemColors = new short[256];
		GroundItem.anInt2498 = Configurations.SERVER_PORT; // (Game.getGameType() ^ 0xffffffff) == -1 ? 443 : 50000 + Game.getWorldId();
		Keyboard.setKeyEvents((byte) 103);
		InputManager.attach_keyboard(GameShell.getCanvas());
		InputManager.attach_mouse(GameShell.getCanvas());
		InputManager.mouse_wheel = Player.method1097(0);
		if (InputManager.mouse_wheel != null) {
			InputManager.mouse_wheel.attach(GameShell.getCanvas());
		}
		Class87_Sub2.anInt2794 = SignLink.anInt190;
		try {
			system_clipboard = getToolkit().getSystemClipboard();
		} catch (Exception exception) {
			/* empty */
		}
		try {
			if (gameSignlink.cache_data_file != null) {
				data_file = new SeekableFile(gameSignlink.cache_data_file, 5200, 0);
				for (int indexes = 0; indexes < NUM_CACHE_ARCHIVES; indexes++) {
					index_files[indexes] = new SeekableFile(gameSignlink.cache_index_files[indexes], 6000, 0);
				}
				master_index_file = new SeekableFile(gameSignlink.cache_i255_file, 6000, 0);
				index_datafs = new FileSystem(255, data_file, master_index_file, 500000);
				random_file = new SeekableFile(gameSignlink.random_file, 24, 0);
				gameSignlink.cache_data_file = null;
				gameSignlink.cache_index_files = null;
				gameSignlink.random_file = null;
				gameSignlink.cache_i255_file = null;
			}
		} catch (java.io.IOException ioexception) {
			ioexception.printStackTrace();
			data_file = null;
			master_index_file = null;
			random_file = null;
			index_datafs = null;
		}
		if (GameClient.getGameType() != 0) {
			GamePlayConfiguration.isFPSEnabled = true;
		}
		ClanChatMember.aClass16_2393 = StaticMethods2.aClass16_1709;
	}

	public static int js5_connection_succeeded_tries = 0;

	public static String js5_error_string = null;

	public void js5_connect() {
		if (js5_client.reconnect_count > js5_connection_succeeded_tries) {
			// server.rotate_connection_method();
			js5_reconnect_timer = (js5_client.reconnect_count * 50 - 50) * 5;
			if (js5_reconnect_timer > 3000) {
				js5_reconnect_timer = 3000;
			}
			if (js5_client.reconnect_count >= 2 && js5_client.status == 6) {
				error("js5connect_outofdate");
				clientState = 1000;
				return;
			}
			if (js5_client.reconnect_count >= 4 && js5_client.status == -1) {
				error("js5crc");
				clientState = 1000;
				return;
			}
			if (js5_client.reconnect_count >= 4 && clientState <= 5) {
				if (js5_client.status == 7 || js5_client.status == 9) {
					error("js5connect_full");
				} else if (js5_client.status > 0) {
					if (js5_error_string != null) {
						error("js5proxy_" + js5_error_string.trim());
					} else {
						error("js5connect");
					}
				} else {
					error("js5io");
				}
				clientState = 1000;
				return;
			}
		}
		js5_connection_succeeded_tries = js5_client.reconnect_count;
		if (js5_reconnect_timer > 0) {
			js5_reconnect_timer--;
		} else {
			try {
				switch (js5_connect_stage) {
					case 0:
						js5_connect_request = gameSignlink.openSocket(GameClient.getIp(), socket_port);
						js5_connect_stage++;
						break;
					case 1:
						if (js5_connect_request.status == 2) {
							if (js5_connect_request.result != null) {
								js5_error_string = (String) js5_connect_request.result;
							}
							signal_js5_error(1000, (byte) -64);
						} else if (js5_connect_request.status == 1) {
							js5_connect_stage++;
						}
						break;
					case 2: {
						js5_stream = new IoSession((Socket) js5_connect_request.result, gameSignlink, 25000);
						Packet packet = new Packet(5);
						packet.p1(15);
						packet.p4(Configurations.CLIENT_BUILD);
						js5_stream.write(5, 0, packet.byteBuffer);
						js5_connect_stage++;
						js5_last_connect_time = TimeTools.getMillis();
						break;
					}
					case 3:
						if (clientState < 5 || js5_stream.available(-80) > 0) {
							int response = js5_stream.read((byte) 10);
							if (response != 0) {
								signal_js5_error(response, (byte) 64);
							}
							js5_connect_stage++;
						} else if (TimeTools.getMillis() - js5_last_connect_time < 30000L) {
							// System.out.println("error");
							signal_js5_error(1001, (byte) -64);
						}
						break;
					case 4: {
						js5_client.connect(js5_stream, clientState > 20);
						js5_stream = null;
						js5_connect_request = null;
						ReflectionAntiCheat.anInt74 = 0;
						js5_connect_stage = 0;
						break;
					}
				}
			} catch (java.io.IOException ioexception) {
				signal_js5_error(1002, (byte) 1);
			}
		}
	}

	static final void method40(Class23_Sub10 class23_sub10, boolean bool) {
		if (class23_sub10.aClass23_Sub6_2278 != null) {
			class23_sub10.aClass23_Sub6_2278.anInt2193 = 0;
		}
		class23_sub10.aBoolean2276 = false;
		if (bool == true) {
			for (Class23_Sub10 class23_sub10_26_ = class23_sub10.method503(); class23_sub10_26_ != null; class23_sub10_26_ = class23_sub10.method502()) {
				method40(class23_sub10_26_, true);
			}
		}
	}

	public static void method41(byte b) {
		instrumentsContainer = null;
		StringConstants.aClass16_1977 = null;
		StringConstants.aClass16_1972 = null;
		MonochromeImageCache.SLOT_USED = null;
		StaticMedia.mapdots = null;
		FontCache.p11_full = null;
		MapRegion.MAP_M = null;
		StringConstants.aClass16_1975 = null;
		StringConstants.aClass16_1976 = null;
		StringConstants.aClass16_1979 = null;
		if (b <= 48) {
			method40(null, false);
		}
	}

	@Override
	final void destruct(byte b) {
		method41((byte) 101);
		RSString.method180();
		StaticMethods2.method1349(-78);
		Class98.method1493(-95);
		HintIcon.method110((byte) 125);
		Packet.method455(-81);
		Js5Shutdown.destruct();
		IoSession.method972((byte) 25);
		CacheFileWorker.method1567(false);
		SeekableFile.method943((byte) -86);
		FileSystem.method118(b + -152);
		NPC.method1091(2047);
		ISAACPacket.method484(false);
		CollisionMap.method1300(0);
		StaticMethods2.method702(3);
		StaticMethods2.method1118(true);
		RSInterface.method1219(-21558);
		Player.method1099(-1803925744);
		NodeDeque.method1432(-1);
		AbstractMouseWheel.method1239(b ^ 0x3b);
		ClanChatMember.method880(0);
		GrandExchangeOffer.method1444(-20234);
		Stereo.method75(b + -15731);
		Class45.method1131(true);
		PlayerAppearance.method1161(-121);
		StaticMethods2.method841(b ^ ~0x5085);
		Entity.method1089(-3);
		SpawnedObject.method895(109);
		NPCType.method822(b ^ ~0x6);
		InterfaceNode.method908(117);
		StaticMethods2.method229((byte) -11);
		ISAACCipher.method1260(-31834);
		Class95.method1469(b + 586448393);
		ModelList.method1371(-1);
		Class44.method1126(21150);
		RuntimeException_Sub1.method1590(8404);
		Class65.method1233(false);
		Class28.method938(85);
		StaticMethods2.destruct();
		CullingCluster.method1194(2048);
		StaticMethods.method996(128);
		WallObject.method1378();
		WallDecoration.method916(b ^ 0x38);
		GroundDecoration.method1256();
		CacheConstants.destruct();
		StaticMethods2.method1527();
		StaticMethods2.method1170();
		ReflectionAntiCheat.destruct(32);
		Class75.method1316(0);
		LocType.method637();
		Queuable.method600(-1399);
		Js5.method1553(-1);
		MemoryCache.method64(b + -152);
		Mesh.method1044();
		LocResult.method626(-66);
		ObjType.method738();
		SoftwareModel.method1028();
		Keyboard.method965(-7897);
		Mouse.method111((byte) 51);
		Class73.method1310(b ^ 0x30);
		UpdateServerThread.method91(-119);
		Class4.method59(-122);
		SomeSoundClass.method577((byte) -90);
		InstrumentDefinition.method126(49);
		Class49.method1154(-107);
		Class53.method1172(true);
		LobbyWorld.method1374(32);
		Class33.method969((byte) 88);
		Class25.method920((byte) 61);
		Queue.method935(0);
		BufferedRequest.method859(false);
		NameHashTable.method1255(0);
		StaticMethods2.method1514(-107);
		Class23_Sub7.method494(-1);
		CustomRSByteBuffer.method1472();
		Class23_Sub10_Sub3.method588(-125);
		SoundEffects.method237((byte) 120);
		Class57.method1190((byte) 107);
		Class36.method988((byte) 87);
		StaticMethods2.method622(-2903);
		PlayerMasks.destruct();
		StaticMethods2.method848((byte) -117);
		PlayerIdentityKit.destruct();
		StaticMethods.method781((byte) -83);
		SpotType.method790(0);
		Varbit.method768();
		InvType.destruct();
		Class23_Sub13_Sub12.method754(-4);
		Class79.method1360(true);
		QuickChatDefinition.method769(b + -56);
		Rasterizer2D.method221();
		StaticMethods.method1401(false);
		StaticMethods.method802(54);
		Huffman.method1574(true);
		Class21.destruct(36);
		Class100.method1505((byte) -127);
		Class19.method197(-13488);
		ClientInventory.method873(false);
		Class97.method1491(0);
		Class48.method1151((byte) 74);
		ObjectNode.method499((byte) -3);
		CS2ScriptDefinition.method845(-89);
		Class67.destruct();
		CS2Event.method500((byte) -14);
		ForceMovement.method924(-119);
		CS2CallFrame.method1155(63);
		Class42.method1113();
		SoundTrack.method1464();
		Class31.method961((byte) -128);
		Class30.method959(b + -54);
		StaticMethods2.method1073((byte) -113);
		SpotEntity.method1077((byte) 123);
		StaticMethods2.method885(b + -55);
		GroundItem.method1035(b + 35);
		Class54.method1176((byte) -124);
		Class55.method1179(-24181);
		Class61.method1200((byte) 53);
		StringNode.method911(true);
		Class91.method1456(-30192);
		ComponentCanvas.method42(1000);
		Class107.method1583((byte) -126);
		GameTimer.method191((byte) -80);
		StaticMethods2.method1357();
		FaceNormal.method1108(b + 53);
		VertexNormal.method1459((byte) -73);
		DataBuffer.method1214(-75);
		StaticMethods2.method680(70);
		StereoUtils.method88();
		SoundEngine.method112();
		SongUpdater.method97(124);
		AnimFrame.method136();
		GroundObjEntity.method1140((byte) 39);
		BZIPDecompressor.method1396();
		BZIPContext.method1315(false);
		StaticMethods.method598((byte) 49);
		UpdateServerNode.method862((byte) -93);
		Class23_Sub16.method871(100);
		Class47.method1148(-109);
		Class23_Sub3.method244();
		Class101.method1513();
		StaticMethods.method44(-96);
		StaticMethods.method257(true);
		ReflectionRequest.method913(20);
		Class35.method983(b ^ ~0x4853);
		Class88.method1429(75);
		Class56.method1183((byte) 125);
		StaticMethods.method348((byte) 59);
		ColourImageCache.method1523(118);
		MonochromeImageCache.method1501(-117);
		StaticMethods.method362();
		StaticMethods.method397(true);
		StaticMethods.method410((byte) 67);
		StaticMethods.method392(true);
		StaticMethods.method297((byte) 74);
		StaticMethods.method345(b ^ ~0x77);
		StaticMethods.method335(true);
		StaticMethods.method425((byte) -22);
		StaticMethods.method412(-22323);
		StaticMethods.method383(69);
		StaticMethods.method374(-2);
		StaticMethods.method304(b + 4040);
		StaticMethods.method284(true);
		StaticMethods.method381(true);
		StaticMethods.method308((byte) -95);
		StaticMethods.method286((byte) 104);
		StaticMethods.method416((byte) -23);
		StaticMethods.method357(111);
		StaticMethods.method272((byte) 49);
		StaticMethods.method363(-29876505);
		StaticMethods.method280(0);
		StaticMethods.method382(104);
		StaticMethods.method372(5864);
		StaticMethods.method377(false);
		StaticMethods.method296(b ^ 0x39);
		StaticMethods.method331(17);
		StaticMethods.method403(-98);
		if (b == 56) {
			StaticMethods.method338((byte) 93);
			StaticMethods.method319(0);
			StaticMethods.method420(0);
			StaticMethods.method409(b + -10003);
			StaticMethods.method352(-43);
			StaticMethods.method324(true);
			StaticMethods.method407(false);
			StaticMethods.method288(-31116);
			StaticMethods.method290(6094);
			StaticMethods.method279((byte) 2);
			Class71_Sub2.method1277(b ^ 0x7265);
			Class71.method1266((byte) -28);
			Class71_Sub2_Sub1.method1286((byte) 62);
			Class71_Sub3.method1289(-789436973);
			Class71_Sub1_Sub1.method1275((byte) 84);
			Class71_Sub1.method1270(false);
			GroundItemNode.method604();
			ProjectileNode.method615(11);
			PositionedGraphicNode.method687(true);
			ModelNode.method856((byte) -113);
			StaticMethods.method596(true);
			StaticMethods.method889(2);
			Class23_Sub13_Sub2.method607(true);
			ColourImageCacheSlot.method899((byte) -104);
			MonochromeImageCacheSlot.method868(-111);
			Class87.method1404(-13896);
			Class87_Sub3.method1420(-118);
			Class87_Sub4.method1428((byte) 65);
			Class87_Sub2.method1412((byte) 64);
			Class87_Sub1.method1411((byte) 107);
		}
	}

	public static final Hashtable<String, File> cached_files = new Hashtable<>(16);

	public static File get_cache_file(String name) {
		File cacheDirectory = new File(Configurations.getCachePath());
		File cached = cached_files.get(name);
		if (null != cached) {
			return cached;
		}
		File file = new File(cacheDirectory, name);
		RandomAccessFile random_access_file = null;
		try {
			File parent = new File(file.getParent());
			if (!parent.exists()) {
				parent.mkdirs();
			}
			random_access_file = new RandomAccessFile(file, "rw");
			int first = random_access_file.read();
			random_access_file.seek(0L);
			random_access_file.write(first);
			random_access_file.seek(0L);
			random_access_file.close();
			cached_files.put(name, file);
		} catch (Exception e) {
			try {
				if (random_access_file != null) {
					random_access_file.close();
				}
			} catch (Exception ei) {
				// NOOP
			}
			throw new RuntimeException("Cache file error", e);
		}
		return file;
	}

	static final int get_completions_count() {
		return 4;
	}

	public static final void incorrectUsage() {
		Logger.log("Usage: worldid, [live/office/local], [live/rc/wip], [lowmem/highmem], [free/members], [english/german], [game0/game1]");
		System.exit(1);
	}

	/**
	 * @return the revision
	 */
	public static int getRevision() {
		return revision;
	}

	/**
	 * @return the isMembers
	 */
	public static boolean isMembers() {
		return isMembers;
	}

	/**
	 * @param isMembers
	 *                  the isMembers to set
	 */
	static void setMembers(boolean isMembers) {
		GameClient.isMembers = isMembers;
	}

	/**
	 * @return the liveRcWip
	 */
	public static int getLiveRcWip() {
		return liveRcWip;
	}

	/**
	 * @param liveRcWip
	 *                  the liveRcWip to set
	 */
	public static void setLiveRcWip(int liveRcWip) {
		GameClient.liveRcWip = liveRcWip;
	}

	/**
	 * @return the iP
	 */
	public static String getIp() {
		return IP;
	}

	/**
	 * @param iP
	 *           the iP to set
	 */
	public static void setIp(String iP) {
		IP = iP;
	}

	/**
	 * @return the noClip
	 */
	public static boolean isNoClip() {
		return aBoolean_4_;
	}

	/**
	 * @param noClip
	 *               the noClip to set
	 */
	public static void setNoClip(boolean noClip) {
		GameClient.aBoolean_4_ = noClip;
	}

	/**
	 * @return the gameType
	 */
	public static int getGameType() {
		return gameType;
	}

	/**
	 * @param gameType
	 *                 the gameType to set
	 */
	public static void setGameType(int gameType) {
		GameClient.gameType = gameType;
	}

	/**
	 * @return the worldId
	 */
	public static int getWorldId() {
		return worldId;
	}

	/**
	 * @param worldId
	 *                the worldId to set
	 */
	public static void setWorldId(int worldId) {
		GameClient.worldId = worldId;
	}

	/**
	 * @return the language
	 */
	public static int getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *                 the language to set
	 */
	static void setLanguage(int language) {
		GameClient.language = language;
	}

	/**
	 * @return the gameValue
	 */
	public static int getGameValue() {
		return gameValue;
	}

	/**
	 * @param gameValue
	 *                  the gameValue to set
	 */
	static void setGameValue(int gameValue) {
		GameClient.gameValue = gameValue;
	}

	/**
	 * @return the affId
	 */
	public static int getAffId() {
		return affId;
	}

	/**
	 * @param affId
	 *              the affId to set
	 */
	static void setAffId(int affId) {
		GameClient.affId = affId;
	}

	/**
	 * @return the setting
	 */
	public static GameSetting getSetting() {
		return setting;
	}

	/**
	 * @param setting
	 *                the setting to set
	 */
	public static void setSetting(GameSetting setting) {
		GameClient.setting = setting;
	}

	/**
	 * A field which is not refactored.
	 */
	public static Js5 instrumentsContainer;

	public static LookAndFeel lookAndFeel;

	static {
		Viewport.anInt1973 = 0;
		StringConstants.aClass16_1979 = StringConstants.aClass16_1972;
	}
}