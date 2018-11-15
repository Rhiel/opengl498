package com.jagex;

public class PlayerIdentityKit extends Queuable {
	static int identityKitAmount;
	static Js5 identityKitContainer;
	static MemoryCache identityKitMap;
	public int[] body_parts;
	static RSString aClass16_4080;
	public short[] recol_dst;
	public short[] retex_src;
	public int[] head_parts = { -1, -1, -1, -1, -1 };
	static RSString aClass16_4093;
	public boolean customisation;
	static RSString aClass16_4097;
	public short[] retex_dst;
	public short[] recol_src;
	public int anInt4102 = -1;

	static final PlayerIdentityKit getIdentityKit(int clothId) {
		PlayerIdentityKit def = (PlayerIdentityKit) identityKitMap.get(clothId);
		if (def != null) {
			return def;
		}
		byte[] bs = identityKitContainer.get_file(3, clothId);
		def = new PlayerIdentityKit();
		if (bs != null) {
			def.readValueLoop(new Packet(bs));
		}
		identityKitMap.put(clothId, def);
		return def;
	}

	final Mesh fetchHeadModels() {
		int modelCount = 0;
		Mesh[] modelDef = new Mesh[5];
		for (int part = 0; part < 5; part++) {
			if (head_parts[part] != -1) {
				modelDef[modelCount++] = Mesh.fromJs5(Queuable.aClass105_2312, head_parts[part], 0);
			}
		}
		Mesh combinedModelDef = new Mesh(modelDef, modelCount);
		if (recol_src != null) {
			for (int i_2_ = 0; i_2_ < recol_src.length; i_2_++) {
				combinedModelDef.recolor(recol_src[i_2_], recol_dst[i_2_]);
			}
		}
		if (retex_src != null) {
			for (int i_3_ = 0; i_3_ < retex_src.length; i_3_++) {
				combinedModelDef.retexture(retex_src[i_3_], retex_dst[i_3_]);
			}
		}
		return combinedModelDef;
	}

	final void readValueLoop(Packet buffer) {
		for (;;) {
			int i_4_ = buffer.g1();
			if (i_4_ == 0) {
				break;
			}
			parseOpcode(i_4_, buffer);
		}
	}

	public final void parseOpcode(int opcode, Packet buffer) {
		if (opcode == 1) {
			anInt4102 = buffer.g1();
		} else if (opcode == 2) {
			int i_33_ = buffer.g1();
			body_parts = new int[i_33_];
			for (int i_34_ = 0; i_33_ > i_34_; i_34_++) {
				body_parts[i_34_] = buffer.g2();
			}
		} else if (opcode == 3) {
			customisation = true;
		} else if (opcode == 40) {
			int num_recols = buffer.g1();
			recol_src = new short[num_recols];
			recol_dst = new short[num_recols];
			for (int index = 0; index < num_recols; index++) {
				recol_src[index] = (short) buffer.g2();
				recol_dst[index] = (short) buffer.g2();
			}
		} else if (opcode == 41) {
			int num_retexs = buffer.g1();
			retex_src = new short[num_retexs];
			retex_dst = new short[num_retexs];
			for (int index = 0; index < num_retexs; index++) {
				retex_src[index] = (short) buffer.g2();
				retex_dst[index] = (short) buffer.g2();
			}
		} else if (opcode >= 60 && opcode < 70) {
			head_parts[opcode - 60] = buffer.g2();
		}
	}

	final boolean isIdentityKitCached() {
		if (body_parts == null) {
			return true;
		}
		boolean bool_6_ = true;
		for (int i = 0; (body_parts.length ^ 0xffffffff) < (i ^ 0xffffffff); i++) {
			if (!Queuable.aClass105_2312.is_file_cached(body_parts[i], 0)) {
				bool_6_ = false;
			}
		}
		return bool_6_;
	}

	final Mesh fetchBodyModels() {
		if (body_parts == null) {
			return null;
		}
		Mesh[] modelArray = new Mesh[body_parts.length];
		for (int index = 0; body_parts.length > index; index++) {
			modelArray[index] = Mesh.fromJs5(Queuable.aClass105_2312, body_parts[index], 0);
		}
		Mesh combinedModel;
		if (modelArray.length == 1) {
			combinedModel = modelArray[0];
		} else {
			combinedModel = new Mesh(modelArray, modelArray.length);
		}
		if (recol_src != null) {
			for (int i_26_ = 0; (i_26_ ^ 0xffffffff) > (recol_src.length ^ 0xffffffff); i_26_++) {
				combinedModel.recolor(recol_src[i_26_], recol_dst[i_26_]);
			}
		}
		if (retex_src != null) {
			for (int i_27_ = 0; (retex_src.length ^ 0xffffffff) < (i_27_ ^ 0xffffffff); i_27_++) {
				combinedModel.retexture(retex_src[i_27_], retex_dst[i_27_]);
			}
		}
		return combinedModel;
	}

	public static void destruct() {
		aClass16_4080 = null;
		aClass16_4097 = null;
		aClass16_4093 = null;
	}

	final boolean method801() {
		boolean bool = true;
		int i = 0;
		for (/**/; i < 5; i++) {
			if (head_parts[i] != -1 && !Queuable.aClass105_2312.is_file_cached(head_parts[i], 0)) {
				bool = false;
			}
		}
		return bool;
	}

	public PlayerIdentityKit() {
		customisation = false;
	}

	static {
		ComponentMinimap.flag_y = 0;
		aClass16_4080 = RSString.createString("::gc");
		aClass16_4093 = RSString.createString("Clientscript error )2 check log for details");
		aClass16_4097 = RSString.createString("Ihre Ignorieren)2Liste ist voll)1 Sie k-Onnen nur 100 Spieler darauf eintragen)3");
	}
}
