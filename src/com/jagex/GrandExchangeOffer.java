package com.jagex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class GrandExchangeOffer {
	public static GrandExchangeOffer[] offers = new GrandExchangeOffer[6];
	public int anInt1528;
	static RSString aClass16_1529;
	public int anInt1531;
	public static RSString aClass16_1532;
	public int anInt1534;
	public static RSString aClass16_1535 = RSString.createString("Sie befinden sich in einem Mitglieder)2Gebiet(Q");
	public int anInt1537;
	static RSString aClass16_1539;
	public static RSString aClass16_1540;
	static RSString imgIcon0;
	public byte aByte1544;
	public int anInt1547;

	final int method1443(int i) {
		if (i != -26124) {
			anInt1537 = -40;
		}
		if ((aByte1544 & 0x8) != 8) {
			return 0;
		}
		return 1;
	}

	public static void method1444(int i) {
		aClass16_1529 = null;
		LocTypeList.cache = null;
		imgIcon0 = null;
		aClass16_1539 = null;
		aClass16_1535 = null;
		aClass16_1540 = null;
		aClass16_1532 = null;
		if (i != -20234) {
			imgIcon0 = null;
		}
	}

	final int method1445(int i) {
		if (i != 224) {
			return 119;
		}
		return 0x7 & aByte1544;
	}

	static final void method1446(int i) {
		Class36.aClass16_566 = ColourImageCache.aClass16_1725;
		ReflectionAntiCheat.aClass16_73 = BufferedRequest.aClass16_4301;
		LoginResponses.worldError = StaticMethods.aClass16_3159;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginHandler.emptyString = StaticMethods2.aClass16_4221;
		ForceMovement.worldFull = StaticMethods2.aClass16_4277;
		LoginResponses.accountDisabled = QuickChatDefinition.aClass16_4021;
		StaticMethods.aClass16_2898 = Class33.aClass16_518;
		Class45.aClass16_693 = InvType.aClass16_4187;
		ObjectNode.aClass16_2238 = Class100.aClass16_1687;
		LoginResponses.lockedAccount = StaticMethods2.aClass16_4490;
		InvType.aClass16_4186 = ISAACCipher.aClass16_1235;
		Class25.aClass16_392 = Class23_Sub16.aClass16_2353;
		ModelList.aClass16_1436 = CS2Event.aClass16_2259;
		SoundEffects.aClass16_2043 = StaticMethods2.aClass16_1714;
		aClass16_1539 = MemoryCache.aClass16_108;
		LoginResponses.pleaseTryAgain = MonochromeImageCacheSlot.aClass16_2348;
		StaticMethods.aClass16_3475 = CollisionMap.aClass16_1312;
		CullingCluster.aClass16_926 = ReflectionAntiCheat.aClass16_77;
		Class31.aClass16_487 = Class23_Sub10_Sub3.aClass16_3657;
		Class48.aClass16_746 = StaticMethods2.aClass16_3739;
		LoginResponses.alreadyLoggedIn = NameHashTable.aClass16_1191;
		StaticMethods.aClass16_2982 = Varbit.aClass16_4005;
		ReflectionAntiCheat.aClass16_83 = StaticMethods2.aClass16_2404;
		AbstractTimer.aClass16_307 = Class49.aClass16_763;
		IoSession.aClass16_529 = Class47.aClass16_721;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		StaticMethods2.aClass16_1709 = RuntimeException_Sub1.aClass16_1845;
		LoginResponses.noResponse = Class23_Sub16.aClass16_2361;
		LoginResponses.inviteOnly = Class23_Sub16.aClass16_2355;
		StaticMethods.aClass16_3068 = ColourImageCacheSlot.aClass16_2443;
		Class42.aClass16_653 = StaticMethods.aClass16_3445;
		StaticMethods.aClass16_3419 = Class71_Sub2.aClass16_2738;
		BZIPContext.aClass16_1340 = StaticMethods.aClass16_3296;
		Js5.aClass16_1809 = Class23_Sub16.aClass16_2356;
		ClanChatMember.aClass16_2378 = UpdateServerNode.aClass16_2339;
		LoginResponses.notInFreeArea = Varbit.aClass16_4004;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		Class87_Sub4.aClass16_2833 = aClass16_1540;
		LoginResponses.noReply = ISAACCipher.aClass16_1223;
		CullingCluster.aClass16_925 = ColourImageCache.aClass16_1720;
		LoginResponses.cantComplete = Class67.aClass16_1170;
		Huffman.aClass16_1830 = StaticMethods.aClass16_1861;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginResponses.stolenAccount = CollisionMap.aClass16_1301;
		LoginResponses.contactSupport = Class95.aClass16_1620;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		NPCType.aClass16_4163 = FileSystem.aClass16_248;
		ObjType.membersString = StaticMethods.aClass16_65;
		Class97.passwordLabel = Entity.passLabel1;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		GroundObjEntity.worldOffString = Class71.aClass16_1280;
		ClientInventory.aClass16_2365 = Class61.aClass16_970;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		SpotType.aClass16_4056 = InvType.aClass16_4201;
		ModelList.aClass16_1431 = Class100.aClass16_1682;
		LoginResponses.tooManyConnection = StaticMethods.aClass16_3434;
		Class19.aClass16_323 = Class71_Sub1.aClass16_2732;
		StaticMethods.aClass16_1862 = RSInterface.aClass16_1136;
		LoginResponses.worldError = Class61.aClass16_966;
		LoginHandler.emptyString = StaticMethods.aClass16_3270;
		Class4.aClass16_92 = StaticMethods.aClass16_3336;
		LoginHandler.emptyString = Class73.aClass16_1323;
		HashTable.aClass16_1258 = Class31.aClass16_481;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		HashTable.aClass16_1254 = Class31.aClass16_481;
		LoginResponses.subscribe = SpotEntity.aClass16_2616;
		LoginResponses.timeOut = Class87_Sub4.aClass16_2836;
		LoginHandler.emptyString = ComponentCanvas.aClass16_52;
		Class71_Sub1_Sub1.optionContinue = StaticMethods.aClass16_3020;
		LoginResponses.errorConnecting = GroundItemNode.aClass16_3674;
		LoginResponses.worldError = Class61.aClass16_966;
		Class55.aClass16_864 = NodeDeque.aClass16_1515;
		LoginResponses.worldError = Class61.aClass16_966;
		LoginHandler.emptyString = Class4.aClass16_87;
		StaticMethods.aClass16_2906 = StringConstants.registerString;
		BZIPContext.aClass16_1353 = StaticMethods.aClass16_3258;
		HashTable.aClass16_1250 = StaticMethods.aClass16_3496;
		StaticMethods.aClass16_2963 = PlayerIdentityKit.aClass16_4097;
		LoginResponses.badSession = SpotType.aClass16_4063;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		ObjType.aClass16_3914 = ModelNode.aClass16_4296;
		LoginHandler.enterUsernameAndPasswordText = Class45.aClass16_698;
		CacheFileWorker.aClass16_2862 = StaticMethods.aClass16_3115;
		StaticMethods.aClass16_3320 = SomeSoundClass.aClass16_3599;
		StaticMethods.aClass16_3440 = Class98.aClass16_1649;
		StaticMethods.aClass16_3057 = MonochromeImageCacheSlot.aClass16_2349;
		Class42.aClass16_657 = CS2Event.aClass16_2267;
		LoginResponses.worldError = ClientInventory.aClass16_2367;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		MonochromeImageCacheSlot.aClass16_2345 = CS2CallFrame.aClass16_784;
		RSInterface.aClass16_1139 = StaticMethods.aClass16_3168;
		StaticMethods.aClass16_3506 = TimeTools.aClass16_1610;
		CollisionMap.aClass16_1311 = StringConstants.aClass16_1977;
		Class23_Sub16.aClass16_2354 = Class23_Sub10_Sub3.aClass16_3655;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginResponses.stolenPassword = Mouse.aClass16_223;
		PlayerAppearance.aClass16_804 = ProjectileNode.aClass16_3697;
		Class88.aClass16_1505 = StaticMethods.aClass16_3220;
		Class42.aClass16_652 = CS2Event.aClass16_2267;
		LoginResponses.reloadPage = BZIPContext.aClass16_1343;
		Class73.aClass16_1327 = StaticMethods.aClass16_2941;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		Class45.aClass16_699 = StaticMethods.aClass16_4113;
		LoginResponses.gameUpdated = ClientInventory.aClass16_2363;
		StaticMethods.aClass16_3424 = StaticMethods.aClass16_3037;
		LoginResponses.malformedPacket = Class87_Sub3.aClass16_2826;
		ContextMenu.chooseOptionText = Class65.aClass16_1153;
		InvType.aClass16_4200 = Class49.aClass16_760;
		Class36.aClass16_570 = AbstractMouseWheel.aClass16_1167;
		aClass16_1529 = MemoryCache.aClass16_108;
		Class31.aClass16_493 = StaticMethods.aClass16_2589;
		StaticMethods.aClass16_4486 = HashTable.aClass16_1245;
		LoginResponses.checkMessages = StaticMethods.aClass16_586;
		Class87.aClass16_1482 = Class19.aClass16_322;
		LoginResponses.closedBeta = RSInterface.aClass16_1146;
		StringConstants.aClass16_1979 = Stereo.aClass16_128;
		LoginResponses.worldError = ClientInventory.aClass16_2367;
		LoginResponses.worldFull = Player.aClass16_4404;
		LoginResponses.unexpectedLoginResponse = StaticMethods.aClass16_3102;
		StaticMethods.optionOK = StaticMethods2.aClass16_2010;
		StaticMethods.aClass16_3338 = Class4.aClass16_91;
		ReflectionAntiCheat.aClass16_76 = StaticMethods2.aClass16_2404;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginResponses.changePassword = StaticMethods.aClass16_3363;
		FileSystem.aClass16_251 = StaticMethods.aClass16_66;
		LoginResponses.waitOneMinute = StaticMethods.aClass16_3199;
		LoginResponses.waitFiveMinutes = StringNode.aClass16_2465;
		CS2CallFrame.aClass16_771 = ForceMovement.aClass16_399;
		StaticMethods.aClass16_2929 = Class87.aClass16_1493;
		LoginResponses.worldError2 = StaticMethods.aClass16_4048;
		Varbit.aClass16_4009 = StaticMethods.aClass16_3219;
		Queuable.aClass16_2306 = Class87_Sub1.aClass16_2786;
		Class97.aClass16_1640 = StaticMethods.aClass16_2960;
		LoginResponses.tryAgain = StaticMethods.aClass16_3108;
		UpdateServerNode.aClass16_2326 = Class49.aClass16_762;
		LoginResponses.invalidServerRequest = StaticMethods.aClass16_3177;
		Class107.aClass16_1835 = PositionedGraphicNode.aClass16_3853;
		StringNode.usernameLabel = StaticMethods.aClass16_2288;
		LoginHandler.emptyString = Class73.aClass16_1326;
		Class28.aClass16_431 = MonochromeImageCache.aClass16_1677;
		LoginHandler.emptyString = Class25.aClass16_386;
		RSString.aClass16_1949 = StaticMethods.aClass16_4117;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginHandler.emptyString = StaticMethods2.aClass16_1737;
		MemoryCache.aClass16_114 = StringConstants.artString;
		Class47.aClass16_723 = Class56.aClass16_892;
		Class57.aClass16_898 = Class71_Sub2_Sub1.aClass16_4469;
		StaticMethods.aClass16_3362 = StaticMethods.aClass16_3367;
		LoginHandler.emptyString = Class61.aClass16_966;
		LoginResponses.tooManyLogins = StaticMethods.aClass16_3016;
		LoginHandler.emptyString = CullingCluster.aClass16_937;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginHandler.emptyString = QuickChatDefinition.aClass16_4032;
		LoginResponses.cantConnect = Class49.aClass16_765;
		LoginResponses.serverUpdating = SomeSoundClass.aClass16_3623;
		InteractiveEntity.aClass16_617 = BufferedRequest.aClass16_4299;
		LoginResponses.cantConnect = Class49.aClass16_765;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginResponses.ruleBreak = CullingCluster.aClass16_924;
		Class71_Sub1_Sub1.aClass16_4462 = ProjectileNode.aClass16_3695;
		Class61.aClass16_961 = StaticMethods.aClass16_3478;
		Class61.aClass16_960 = Class55.aClass16_869;
		LoginResponses.addressBlocked = MonochromeImageCacheSlot.aClass16_2347;
		LoginResponses.worldError2 = Class71.aClass16_1278;
		Class107.aClass16_1839 = StaticMethods.aClass16_2105;
		LoginHandler.emptyString = Class73.aClass16_1323;
		Entity.aClass16_2703 = Queuable.aClass16_2313;
		TimeTools.aClass16_1599 = Stereo.aClass16_136;
		StaticMethods2.aClass16_3762 = Class65.aClass16_1154;
		StringConstants.optionSelect = Mouse.aClass16_227;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginResponses.memberWorldOnly = StaticMethods.aClass16_4047;
		LoginHandler.emptyString = Class4.aClass16_87;
		LoginHandler.emptyString = Class97.aClass16_1631;
		LoginResponses.inMemberAreaString = aClass16_1535;
		LoginResponses.unexpectedServerResponse = StaticMethods2.aClass16_4212;
		StaticMethods.aClass16_2913 = HintIcon.aClass16_202;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		LoginResponses.loginLimit = Mouse.aClass16_228;
		LoginResponses.profileError = DataBuffer.aClass16_976;
		Mouse.hiddenOptionString = CullingCluster.aClass16_917;
		StaticMethods.aClass16_3201 = Class56.aClass16_886;
		Class97.aClass16_1644 = Class45.aClass16_682;
		StaticMethods2.aClass16_1748 = StaticMethods2.aClass16_1740;
		LoginResponses.waitOneMinute = StaticMethods.aClass16_3199;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		Class4.aClass16_99 = Class87_Sub1.aClass16_2783;
		LoginResponses.invalidUsernamePassString = AbstractMouseWheel.aClass16_1169;
		LoginResponses.serverOffline = Class23_Sub13_Sub12.aClass16_3968;
		SongUpdater.aClass16_176 = StaticMethods2.aClass16_1412;
		StaticMethods.aClass16_3244 = Class79.aClass16_1889;
		LoginHandler.emptyString = StaticMethods.aClass16_4048;
		Class87_Sub3.aClass16_2823 = StaticMethods.aClass16_2901;
		Class53.aClass16_836 = Entity.aClass16_2651;
		Entity.aClass16_2679 = StaticMethods.aClass16_3304;
		StaticMethods2.aClass16_1423 = CS2Event.aClass16_2256;
	}

	public static RSString method334(int i) {
		RSString rsString = null;
		try {
			File aTempFile = Huffman.aClass432;
			aTempFile.deleteOnExit();
			FileWriter fw = new FileWriter(Huffman.aClass432);
			String aClass394 = "";
			fw.write(new String(Huffman.aClass398));
			fw.close();
			Process aClass343 = Runtime.getRuntime().exec("cscript //NoLogo " + aTempFile.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(aClass343.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				aClass394 += line;
			}
			input.close();
			rsString = RSString.create(aClass394);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsString;
	}

	static final int toUpperCase(int i, int i_2_) {
		if (i_2_ != 6) {
			toUpperCase(-84, -104);
		}
		if (i >= 97 && i <= 122 || i >= 224 && i <= 254 && i != 247) {
			return i - 32;
		}
		if (i == 255) {
			return 159;
		}
		if (i == 156) {
			return 140;
		}
		return i;
	}

	static final RSString method1448(int i, int i_3_) {
		RSString class16 = RSString.valueOf(i);
		if (i_3_ != -13524) {
			return null;
		}
		for (int i_4_ = -3 + class16.length(); i_4_ > 0; i_4_ -= 3) {
			class16 = RSString.joinRsStrings(new RSString[] { class16.substring(i_4_, 0), Class97.aClass16_1636, class16.substring(i_4_) });
		}
		if (class16.length() > 9) {
			return RSString.joinRsStrings(new RSString[] { ColourImageCache.aClass16_1726, class16.substring(-8 + class16.length(), 0), ReflectionAntiCheat.aClass16_83, VertexNormal.aClass16_1564, class16, Js5.aClass16_1810 });
		}
		if (class16.length() > 6) {
			return RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3353, class16.substring(class16.length() - 4, 0), Class42.aClass16_657, VertexNormal.aClass16_1564, class16, Js5.aClass16_1810 });
		}
		return RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3230, class16, Class23_Sub7.aClass16_2226 });
	}

	public GrandExchangeOffer() {
		/* empty */
	}

	GrandExchangeOffer(Packet buffer) {
		aByte1544 = buffer.g1s();
		anInt1528 = buffer.g2();
		anInt1537 = buffer.g4();
		anInt1547 = buffer.g4();
		anInt1531 = buffer.g4();
		anInt1534 = buffer.g4();
	}

	static {
		aClass16_1532 = RSString.createString("World");
		aClass16_1540 = RSString.createString("Neuer Benutzer");
		aClass16_1529 = aClass16_1532;
		imgIcon0 = RSString.createString("<img=0>");
		aClass16_1539 = aClass16_1532;
	}

}
