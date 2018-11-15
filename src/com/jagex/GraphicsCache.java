/*
 * Copyright (c) 2018 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.jagex;

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.scene.Scene;
import com.rs2.client.scene.light.FlickeringEffect;
import com.rs2.client.scene.light.SceneLighting;
import com.rs2.client.scene.shadow.SceneShadowMapper;

/**
 * @author Walied K. Yassen
 */
public class GraphicsCache {

	public static void processAgeCycle() {
		// FontCache.metrics_cache.processAgeCycle(5);

	}

	public static void remmoveSoftReferences() {
		// FontCache.metrics_cache.cacheRemoveSoftReferences();
	}

	public static void reload(boolean var0) {
		Scene.reset_scene();
		ComponentMinimap.full_sprite = null;
		ComponentMinimap.last_rendered_level = -1;
		GraphicsCache.removeAll();
		LocTypeList.result = new LocResult();
		SceneLighting.num_lights = 0;
		SceneLighting.lights = new FlickeringEffect[255];
		OpenGLModel.method1929();
		SceneShadowMapper.method2043();
		WorldMap.reset(17, var0);
		// TODO: reset staticmedia
		if (GLManager.opengl_mode) {
			SceneShadowMapper.initializeShadows(104, 104);
			OpenGLModel.method1755();
		}
		FontCache.initialise(CacheConstants.fonts_js5, CacheConstants.sprites_js5);
		StaticMedia.fetch_sprites(CacheConstants.sprites_js5);
		if (GameClient.clientState == 5 || GameClient.clientState == 10 || GameClient.clientState == 20) {
			GroundItem.loginscreen_loaded = false;
			LobbyWorld.cache_sprites();
			LoginHandler.prepareLoginComponents(CacheConstants.huffmanCacheIdx, CacheConstants.sprites_js5, GameShell.canvas);
		}
	}

	public static final void removeAll() {
		StaticMethods.method329((byte) -93);
		FluTypeList.method93();
		StaticMethods2.method682(-1);
		LocTypeList.method1211(92);
		Class65.method1234((byte) 54);
		ObjType.clearCachedItems();
		SeqTypeList.method1244(-97);
		Queuable.method602(116);
		Varbit.clearVarbitList(-121);
		VarpDefinition.clearVarpList((byte) -97);
		ClanChatMember.method884(0);
		Class97.method1488(-122);
		MSITypeList.cacheReset();
		MECTypeList.cacheReset();
		GraphicTools.clean_up();

		FontCache.metrics_cache.clear();
		Class23_Sub10_Sub3.aJList_3649.clear();
		CacheConstants.animFramesCacheIdx.discard_all_unpacked();
		CacheConstants.animSkinsCacheIdx.discard_all_unpacked();
		CacheConstants.interfaceCacheIdx.discard_all_unpacked();
		CacheConstants.soundsCacheIdx.discard_all_unpacked();
		CacheConstants.map_js5.discard_all_unpacked();
		CacheConstants.musicCacheIdx.discard_all_unpacked();
		CacheConstants.modelCacheIdx.discard_all_unpacked();
		CacheConstants.sprites_js5.discard_all_unpacked();
		CacheConstants.huffmanCacheIdx.discard_all_unpacked();
		CacheConstants.extraMusicCacheIdx.discard_all_unpacked();
		CacheConstants.scriptCacheIdx.discard_all_unpacked();
		CacheConstants.materialsCacheIdx.discard_all_unpacked();
	}
}
