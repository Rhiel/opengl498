package com.jagex;

/**
 * @author Walied K. Yassen
 */
public final class ObjTypeList {

	public static final MemoryCache cache = new MemoryCache(64);
	public static Js5 objs_js5;

	public static final ObjType list(int itemId) {
		ObjType def = (ObjType) ObjTypeList.cache.get(itemId);
		if (def != null) {
			return def;
		}
		byte[] bs = ObjTypeList.objs_js5.get_file(ObjType.getArchive(itemId), ObjType.getFile(itemId));
		def = new ObjType();
		def.itemId = itemId;
		if (bs != null) {
			def.parse(new Packet(bs));
		}
		if (def.manwearxoff != 0 || def.manwearyoff != 0 || def.manwearzoff != 0) {
		}
		if (def.notedTemplateId != -1) {
			def.noteItem(127, list(def.notedTemplateId), list(def.noteInfoId));
		}
		if (def.lendTemplateID != -1) {
			def.copyLendTemplate(list(def.lendTemplateID), list(def.lendID), (byte) -22);
		}
		if (def.actions[0] == RSString.create("Wield")) {
			System.out.println("fixing female wield offset");
		}
		if (!ObjType.isMember && def.membersItem) {
			def.unnoted = false;
			def.team = 0;
			def.groundActions = null;
			def.name = ObjType.membersString;
			def.actions = null;
		}
		ObjTypeList.cache.put(itemId, def);
		return def;
	}

	private ObjTypeList() {

	}
}
