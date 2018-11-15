package com.jagex;

/**
 * Created by Chris on 3/16/2017.
 */
public class CacheConstants {

	public static Js5 animFramesCacheIdx;
	public static Js5 animSkinsCacheIdx;
	public static Js5 configs_js5;
	public static Js5 interfaceCacheIdx;
	public static Js5 soundsCacheIdx;
	public static Js5 map_js5;
	public static Js5 musicCacheIdx;
	public static Js5 modelCacheIdx;
	public static Js5 sprites_js5;
	public static Js5 textureCacheIdx;
	public static Js5 materialsCacheIdx;
	public static Js5 huffmanCacheIdx;
	public static Js5 extraMusicCacheIdx;
	public static Js5 scriptCacheIdx;
	public static Js5 fonts_js5;
	public static Js5 instrumentCacheIdx;
	public static Js5 worldMapCacheIdx;
	public static Js5 soundEffectsCacheIdx;
	public static Js5 locCacheIdx;
	public static Js5 npcCacheIdx;
	public static Js5 scriptMapIdx;
	public static Js5 itemCacheIdx;
	public static Js5 animSequenceCacheIdx;
	public static Js5 graphicCacheIdx;
	public static Js5 varbitCacheIdx;
	public static Js5 localQuickChatIdx;
	public static Js5 globalQuickChatIdx;
	public static Js5 dllsJs5;

	public static void destruct() {
		scriptCacheIdx = null;
		modelCacheIdx = null;
		animFramesCacheIdx = null;
		animSkinsCacheIdx = null;
		configs_js5 = null;
		worldMapCacheIdx = null;
		interfaceCacheIdx = null;
		soundsCacheIdx = null;
		map_js5 = null;
		musicCacheIdx = null;
		sprites_js5 = null;
		textureCacheIdx = null;
		huffmanCacheIdx = null;
		extraMusicCacheIdx = null;
		fonts_js5 = null;
		instrumentCacheIdx = null;
	}

}
