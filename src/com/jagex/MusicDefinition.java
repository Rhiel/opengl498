package com.jagex;


/* Class23_Sub18 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class MusicDefinition extends Linkable
{
	public byte[] aByteArray2374;
	public HashTable anOa2375;
	
	final void method874() {
		if (anOa2375 == null) {
			anOa2375 = new HashTable(16);
			int[] is = new int[16];
			int[] is_0_ = new int[16];
			is[9] = is_0_[9] = 128;
			CustomRSByteBuffer customRSByteBuffer = new CustomRSByteBuffer(aByteArray2374);
			int i = customRSByteBuffer.method1479();
			for (int i_1_ = 0; i_1_ < i; i_1_++) {
				customRSByteBuffer.method1471(i_1_);
				customRSByteBuffer.method1485(i_1_);
				customRSByteBuffer.method1473(i_1_);
			}
		while_62_:
			for (;;) {
				int i_2_ = customRSByteBuffer.method1475();
				int i_3_ = customRSByteBuffer.anIntArray1625[i_2_];
				while (customRSByteBuffer.anIntArray1625[i_2_] == i_3_) {
					customRSByteBuffer.method1471(i_2_);
					int i_4_ = customRSByteBuffer.method1481(i_2_);
					if (i_4_ == 1) {
						customRSByteBuffer.method1478();
						customRSByteBuffer.method1473(i_2_);
						if (!customRSByteBuffer.method1477()) {
							break;
						}
						break while_62_;
					}
					int i_5_ = i_4_ & 0xf0;
					if (i_5_ == 176) {
						int i_6_ = i_4_ & 0xf;
						int i_7_ = i_4_ >> 8 & 0x7f;
						int i_8_ = i_4_ >> 16 & 0x7f;
						if (i_7_ == 0) {
							is[i_6_] = (is[i_6_] & ~0x1fc000) + (i_8_ << 14);
						}
						if (i_7_ == 32) {
							is[i_6_] = (is[i_6_] & ~0x3f80) + (i_8_ << 7);
						}
					}
					if (i_5_ == 192) {
						int i_9_ = i_4_ & 0xf;
						int i_10_ = i_4_ >> 8 & 0x7f;
						is_0_[i_9_] = is[i_9_] + i_10_;
					}
					if (i_5_ == 144) {
						int i_11_ = i_4_ & 0xf;
						int i_12_ = i_4_ >> 8 & 0x7f;
						int i_13_ = i_4_ >> 16 & 0x7f;
						if (i_13_ > 0) {
							int i_14_ = is_0_[i_11_];
							Class23_Sub16 class23_sub16 = (Class23_Sub16) anOa2375.get(i_14_);
							if (class23_sub16 == null) {
								class23_sub16 = new Class23_Sub16(new byte[128]);
								anOa2375.put(i_14_, class23_sub16);
							}
							class23_sub16.aByteArray2359[i_12_] = (byte) 1;
						}
					}
					customRSByteBuffer.method1485(i_2_);
					customRSByteBuffer.method1473(i_2_);
				}
			}
		}
	}
	
	static final MusicDefinition getMusicDefinition(Js5 container, int musicId, int archive) {
		byte[] bs = container.get_file(musicId, archive);
//		File f = new File(Configurations.getCachePath() + "/5.mid");
//		ByteBuffer buf = ByteBuffer.allocate(20000000);
//		try {
//			FileReader fr = new FileReader(f);
//			int val = -1;
//			while ((val = fr.read()) != -1) {
//				buf.put((byte) val);
//			}
//			buf.flip();
//			fr.close();
//		} catch (Throwable sound_adjusts) {
//			sound_adjusts.printStackTrace();
//		}
//		byte[] bs = new byte[buf.remaining()];
//		buf.a(bs);
		if (bs == null) {
			return null;
		}
		return new MusicDefinition(new Packet(bs));
	}
	
	public MusicDefinition(Packet buffer) {
		buffer.index = buffer.byteBuffer.length - 3;
		int i = buffer.g1();
		int i_16_ = buffer.g2();
		int i_17_ = 14 + i * 10;
		buffer.index = 0;
		int i_18_ = 0;
		int i_19_ = 0;
		int i_20_ = 0;
		int i_21_ = 0;
		int i_22_ = 0;
		int i_23_ = 0;
		int i_24_ = 0;
		int i_25_ = 0;
	while_60_:
		for (int i_26_ = 0; i_26_ < i; i_26_++) {
			int i_27_ = -1;
			for (;;) {
				int i_28_ = buffer.g1();
				if (i_28_ != i_27_) {
					i_17_++;
				}
				i_27_ = i_28_ & 0xf;
				if (i_28_ == 7) {
					continue while_60_;
				}
				if (i_28_ == 23) {
					i_18_++;
				} else if (i_27_ == 0) {
					i_20_++;
				} else if (i_27_ == 1) {
					i_21_++;
				} else if (i_27_ == 2) {
					i_19_++;
				} else if (i_27_ == 3) {
					i_22_++;
				} else if (i_27_ == 4) {
					i_23_++;
				} else if (i_27_ == 5) {
					i_24_++;
				} else {
					if (i_27_ != 6) {
						System.out.println("Roaaaarrr " + i_27_);
						break;
					}
					i_25_++;
				}
			}
			throw new RuntimeException("here7251");
		}
		i_17_ += 5 * i_18_;
		i_17_ += 2 * (i_20_ + i_21_ + i_19_ + i_22_ + i_24_);
		i_17_ += i_23_ + i_25_;
		int i_29_ = buffer.index;
		int i_30_ = i + i_18_ + i_19_ + i_20_ + i_21_ + i_22_ + i_23_ + i_24_ + i_25_;
		for (int i_31_ = 0; i_31_ < i_30_; i_31_++) {
			buffer.method437(false);
		}
		i_17_ += buffer.index - i_29_;
		int i_32_ = buffer.index;
		int i_33_ = 0;
		int i_34_ = 0;
		int i_35_ = 0;
		int i_36_ = 0;
		int i_37_ = 0;
		int i_38_ = 0;
		int i_39_ = 0;
		int i_40_ = 0;
		int i_41_ = 0;
		int i_42_ = 0;
		int i_43_ = 0;
		int i_44_ = 0;
		int i_45_ = 0;
		for (int i_46_ = 0; i_46_ < i_19_; i_46_++) {
			i_45_ = i_45_ + buffer.g1() & 0x7f;
			if (i_45_ == 0 || i_45_ == 32) {
				i_25_++;
			} else if (i_45_ == 1) {
				i_33_++;
			} else if (i_45_ == 33) {
				i_34_++;
			} else if (i_45_ == 7) {
				i_35_++;
			} else if (i_45_ == 39) {
				i_36_++;
			} else if (i_45_ == 10) {
				i_37_++;
			} else if (i_45_ == 42) {
				i_38_++;
			} else if (i_45_ == 99) {
				i_39_++;
			} else if (i_45_ == 98) {
				i_40_++;
			} else if (i_45_ == 101) {
				i_41_++;
			} else if (i_45_ == 100) {
				i_42_++;
			} else if (i_45_ == 64 || i_45_ == 65 || i_45_ == 120 || i_45_ == 121 || i_45_ == 123) {
				i_43_++;
			} else {
				i_44_++;
			}
		}
		int i_47_ = 0;
		int i_48_ = buffer.index;
		buffer.index += i_43_;
		int i_49_ = buffer.index;
		buffer.index += i_24_;
		int i_50_ = buffer.index;
		buffer.index += i_23_;
		int i_51_ = buffer.index;
		buffer.index += i_22_;
		int i_52_ = buffer.index;
		buffer.index += i_33_;
		int i_53_ = buffer.index;
		buffer.index += i_35_;
		int i_54_ = buffer.index;
		buffer.index += i_37_;
		int i_55_ = buffer.index;
		buffer.index += i_20_ + i_21_ + i_24_;
		int i_56_ = buffer.index;
		buffer.index += i_20_;
		int i_57_ = buffer.index;
		buffer.index += i_44_;
		int i_58_ = buffer.index;
		buffer.index += i_21_;
		int i_59_ = buffer.index;
		buffer.index += i_34_;
		int i_60_ = buffer.index;
		buffer.index += i_36_;
		int i_61_ = buffer.index;
		buffer.index += i_38_;
		int i_62_ = buffer.index;
		buffer.index += i_25_;
		int i_63_ = buffer.index;
		buffer.index += i_22_;
		int i_64_ = buffer.index;
		buffer.index += i_39_;
		int i_65_ = buffer.index;
		buffer.index += i_40_;
		int i_66_ = buffer.index;
		buffer.index += i_41_;
		int i_67_ = buffer.index;
		buffer.index += i_42_;
		int i_68_ = buffer.index;
		buffer.index += i_18_ * 3;
		aByteArray2374 = new byte[i_17_];
		Packet buf = new Packet(aByteArray2374);
		buf.p4(1297377380);
		buf.p4(6);
		buf.putShort(i > 1 ? 1 : 0);
		buf.putShort(i);
		buf.putShort(i_16_);
		buffer.index = i_29_;
		int i_70_ = 0;
		int i_71_ = 0;
		int i_72_ = 0;
		int i_73_ = 0;
		int i_74_ = 0;
		int i_75_ = 0;
		int i_76_ = 0;
		int[] is = new int[128];
		i_45_ = 0;
		for (int i_77_ = 0; i_77_ < i; i_77_++) {
			buf.p4(1297379947);
			buf.index += 4;
			int i_78_ = buf.index;
			int i_79_ = -1;
		while_61_:
			do {
				for (;;) {
					int i_80_ = buffer.method437(false);
					buf.method479(i_80_, 128);
					int i_81_ = buffer.byteBuffer[i_47_++] & 0xff;
					boolean bool = i_81_ != i_79_;
					i_79_ = i_81_ & 0xf;
					if (i_81_ == 7) {
						if (bool) {
							buf.p1(255);
						}
						buf.p1(47);
						buf.p1(0);
						break while_61_;
					}
					if (i_81_ == 23) {
						if (bool) {
							buf.p1(255);
						}
						buf.p1(81);
						buf.p1(3);
						buf.p1(buffer.byteBuffer[i_68_++]);
						buf.p1(buffer.byteBuffer[i_68_++]);
						buf.p1(buffer.byteBuffer[i_68_++]);
					} else {
						i_70_ ^= i_81_ >> 4;
						if (i_79_ == 0) {
							if (bool) {
								buf.p1(144 + i_70_);
							}
							i_71_ += buffer.byteBuffer[i_55_++];
							i_72_ += buffer.byteBuffer[i_56_++];
							buf.p1(i_71_ & 0x7f);
							buf.p1(i_72_ & 0x7f);
						} else if (i_79_ == 1) {
							if (bool) {
								buf.p1(128 + i_70_);
							}
							i_71_ += buffer.byteBuffer[i_55_++];
							i_73_ += buffer.byteBuffer[i_58_++];
							buf.p1(i_71_ & 0x7f);
							buf.p1(i_73_ & 0x7f);
						} else if (i_79_ == 2) {
							if (bool) {
								buf.p1(176 + i_70_);
							}
							i_45_ = i_45_ + buffer.byteBuffer[i_32_++] & 0x7f;
							buf.p1(i_45_);
							int i_82_;
							if (i_45_ == 0 || i_45_ == 32) {
								i_82_ = buffer.byteBuffer[i_62_++];
							} else if (i_45_ == 1) {
								i_82_ = buffer.byteBuffer[i_52_++];
							} else if (i_45_ == 33) {
								i_82_ = buffer.byteBuffer[i_59_++];
							} else if (i_45_ == 7) {
								i_82_ = buffer.byteBuffer[i_53_++];
							} else if (i_45_ == 39) {
								i_82_ = buffer.byteBuffer[i_60_++];
							} else if (i_45_ == 10) {
								i_82_ = buffer.byteBuffer[i_54_++];
							} else if (i_45_ == 42) {
								i_82_ = buffer.byteBuffer[i_61_++];
							} else if (i_45_ == 99) {
								i_82_ = buffer.byteBuffer[i_64_++];
							} else if (i_45_ == 98) {
								i_82_ = buffer.byteBuffer[i_65_++];
							} else if (i_45_ == 101) {
								i_82_ = buffer.byteBuffer[i_66_++];
							} else if (i_45_ == 100) {
								i_82_ = buffer.byteBuffer[i_67_++];
							} else if (i_45_ == 64 || i_45_ == 65 || i_45_ == 120 || i_45_ == 121 || i_45_ == 123) {
								i_82_ = buffer.byteBuffer[i_48_++];
							} else {
								i_82_ = buffer.byteBuffer[i_57_++];
							}
							i_82_ += is[i_45_];
							is[i_45_] = i_82_;
							buf.p1(i_82_ & 0x7f);
						} else if (i_79_ == 3) {
							if (bool) {
								buf.p1(224 + i_70_);
							}
							i_74_ += buffer.byteBuffer[i_63_++];
							i_74_ += buffer.byteBuffer[i_51_++] << 7;
							buf.p1(i_74_ & 0x7f);
							buf.p1(i_74_ >> 7 & 0x7f);
						} else if (i_79_ == 4) {
							if (bool) {
								buf.p1(208 + i_70_);
							}
							i_75_ += buffer.byteBuffer[i_50_++];
							buf.p1(i_75_ & 0x7f);
						} else if (i_79_ == 5) {
							if (bool) {
								buf.p1(160 + i_70_);
							}
							i_71_ += buffer.byteBuffer[i_55_++];
							i_76_ += buffer.byteBuffer[i_49_++];
							buf.p1(i_71_ & 0x7f);
							buf.p1(i_76_ & 0x7f);
						} else {
							if (i_79_ != 6) {
								System.out.println("Music unhandled opcode " + i_79_);
								break;
							}
							if (bool) {
								buf.p1(192 + i_70_);
							}
							buf.p1(buffer.byteBuffer[i_62_++]);
						}
					}
				}
				throw new RuntimeException("here130981");
			} while (false);
			buf.method466(buf.index - i_78_);
		}
	}
	
	final void method876() {
		anOa2375 = null;
	}
}
