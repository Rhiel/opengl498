package com.jagex;
/* Class23_Sub10_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Class23_Sub10_Sub1 extends Class23_Sub10
{
	public int anInt3549;
	public int anInt3550;
	public int anInt3551;
	public int anInt3552;
	public int anInt3553;
	public int anInt3554;
	public int anInt3555;
	public int anInt3556;
	public int anInt3557;
	public int anInt3558;
	public boolean aBoolean3559;
	public int anInt3560;
	public int anInt3561;
	public int anInt3562;
	public int anInt3563;
	
	@Override
	final synchronized void generate_samples(int[] is, int i, int i_0_) {
		if (anInt3549 == 0 && anInt3563 == 0) {
			method507(i_0_);
		} else {
			SomeSoundClass2 someSoundClass2 = (SomeSoundClass2) aClass23_Sub6_2278;
			int i_1_ = anInt3554 << 8;
			int i_2_ = anInt3558 << 8;
			int i_3_ = someSoundClass2.aByteArray3544.length << 8;
			int i_4_ = i_2_ - i_1_;
			if (i_4_ <= 0) {
				anInt3562 = 0;
			}
			int i_5_ = i;
			i_0_ += i;
			if (anInt3553 < 0) {
				if (anInt3550 > 0) {
					anInt3553 = 0;
				} else {
					method532();
					this.unlink();
					return;
				}
			}
			if (anInt3553 >= i_3_) {
				if (anInt3550 < 0) {
					anInt3553 = i_3_ - 1;
				} else {
					method532();
					this.unlink();
					return;
				}
			}
			if (anInt3562 < 0) {
				if (aBoolean3559) {
					if (anInt3550 < 0) {
						i_5_ = method521(is, i_5_, i_1_, i_0_, someSoundClass2.aByteArray3544[anInt3554]);
						if (anInt3553 >= i_1_) {
							return;
						}
						anInt3553 = i_1_ + i_1_ - 1 - anInt3553;
						anInt3550 = -anInt3550;
					}
					for (;;) {
						i_5_ = method520(is, i_5_, i_2_, i_0_, someSoundClass2.aByteArray3544[anInt3558 - 1]);
						if (anInt3553 < i_2_) {
							break;
						}
						anInt3553 = i_2_ + i_2_ - 1 - anInt3553;
						anInt3550 = -anInt3550;
						i_5_ = method521(is, i_5_, i_1_, i_0_, someSoundClass2.aByteArray3544[anInt3554]);
						if (anInt3553 >= i_1_) {
							break;
						}
						anInt3553 = i_1_ + i_1_ - 1 - anInt3553;
						anInt3550 = -anInt3550;
					}
				} else if (anInt3550 < 0) {
					for (;;) {
						i_5_ = method521(is, i_5_, i_1_, i_0_, someSoundClass2.aByteArray3544[anInt3558 - 1]);
						if (anInt3553 >= i_1_) {
							break;
						}
						anInt3553 = i_2_ - 1 - (i_2_ - 1 - anInt3553) % i_4_;
					}
				} else {
					for (;;) {
						i_5_ = method520(is, i_5_, i_2_, i_0_, someSoundClass2.aByteArray3544[anInt3554]);
						if (anInt3553 < i_2_) {
							break;
						}
						anInt3553 = i_1_ + (anInt3553 - i_1_) % i_4_;
					}
				}
			} else {
				do {
					if (anInt3562 > 0) {
						if (aBoolean3559) {
							if (anInt3550 < 0) {
								i_5_ = method521(is, i_5_, i_1_, i_0_, someSoundClass2.aByteArray3544[anInt3554]);
								if (anInt3553 >= i_1_) {
									return;
								}
								anInt3553 = i_1_ + i_1_ - 1 - anInt3553;
								anInt3550 = -anInt3550;
								if (--anInt3562 == 0) {
									break;
								}
							}
							do {
								i_5_ = method520(is, i_5_, i_2_, i_0_, someSoundClass2.aByteArray3544[anInt3558 - 1]);
								if (anInt3553 < i_2_) {
									return;
								}
								anInt3553 = i_2_ + i_2_ - 1 - anInt3553;
								anInt3550 = -anInt3550;
								if (--anInt3562 == 0) {
									break;
								}
								i_5_ = method521(is, i_5_, i_1_, i_0_, someSoundClass2.aByteArray3544[anInt3554]);
								if (anInt3553 >= i_1_) {
									return;
								}
								anInt3553 = i_1_ + i_1_ - 1 - anInt3553;
								anInt3550 = -anInt3550;
							} while (--anInt3562 != 0);
						} else if (anInt3550 < 0) {
							for (;;) {
								i_5_ = method521(is, i_5_, i_1_, i_0_, someSoundClass2.aByteArray3544[anInt3558 - 1]);
								if (anInt3553 >= i_1_) {
									return;
								}
								int i_6_ = (i_2_ - 1 - anInt3553) / i_4_;
								if (i_6_ >= anInt3562) {
									anInt3553 += i_4_ * anInt3562;
									anInt3562 = 0;
									break;
								}
								anInt3553 += i_4_ * i_6_;
								anInt3562 -= i_6_;
							}
						} else {
							for (;;) {
								i_5_ = method520(is, i_5_, i_2_, i_0_, someSoundClass2.aByteArray3544[anInt3554]);
								if (anInt3553 < i_2_) {
									return;
								}
								int i_7_ = (anInt3553 - i_1_) / i_4_;
								if (i_7_ >= anInt3562) {
									anInt3553 -= i_4_ * anInt3562;
									anInt3562 = 0;
									break;
								}
								anInt3553 -= i_4_ * i_7_;
								anInt3562 -= i_7_;
							}
						}
					}
				} while (false);
				if (anInt3550 < 0) {
					method521(is, i_5_, 0, i_0_, 0);
					if (anInt3553 < 0) {
						anInt3553 = -1;
						method532();
						this.unlink();
					}
				} else {
					method520(is, i_5_, i_3_, i_0_, 0);
					if (anInt3553 >= i_3_) {
						anInt3553 = i_3_;
						method532();
						this.unlink();
					}
				}
			}
		}
	}
	
	final synchronized int method508() {
		if (anInt3550 < 0) {
			return -anInt3550;
		}
		return anInt3550;
	}
	
	public static final int method509(byte[] bs, int[] is, int i, int i_8_, int i_9_, int i_10_, int i_11_, int i_12_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i >>= 8;
		i_12_ >>= 8;
		i_9_ <<= 2;
		if ((i_10_ = i_8_ + i_12_ - i) > i_11_) {
			i_10_ = i_11_;
		}
		i_10_ -= 3;
		while (i_8_ < i_10_) {
			is[i_8_++] += bs[i++] * i_9_;
			is[i_8_++] += bs[i++] * i_9_;
			is[i_8_++] += bs[i++] * i_9_;
			is[i_8_++] += bs[i++] * i_9_;
		}
		i_10_ += 3;
		while (i_8_ < i_10_)
			is[i_8_++] += bs[i++] * i_9_;
		class23_sub10_sub1.anInt3553 = i << 8;
		return i_8_;
	}
	
	static final Class23_Sub10_Sub1 method510(SomeSoundClass2 someSoundClass2, int i, int i_13_) {
		if (someSoundClass2.aByteArray3544 == null || someSoundClass2.aByteArray3544.length == 0) {
			return null;
		}
		return new Class23_Sub10_Sub1(someSoundClass2, (int) (someSoundClass2.anInt3547 * 256L * i / (100 * Keyboard.sampleRate)), i_13_ << 6);
	}
	
	@Override
	final synchronized void method507(int i) {
		if (anInt3563 > 0) {
			if (i >= anInt3563) {
				if (anInt3549 == -2147483648) {
					anInt3549 = 0;
					anInt3552 = anInt3551 = anInt3556 = 0;
					this.unlink();
					i = anInt3563;
				}
				anInt3563 = 0;
				method546();
			} else {
				anInt3552 += anInt3555 * i;
				anInt3551 += anInt3557 * i;
				anInt3556 += anInt3561 * i;
				anInt3563 -= i;
			}
		}
		SomeSoundClass2 someSoundClass2 = (SomeSoundClass2) aClass23_Sub6_2278;
		int i_14_ = anInt3554 << 8;
		int i_15_ = anInt3558 << 8;
		int i_16_ = someSoundClass2.aByteArray3544.length << 8;
		int i_17_ = i_15_ - i_14_;
		if (i_17_ <= 0) {
			anInt3562 = 0;
		}
		if (anInt3553 < 0) {
			if (anInt3550 > 0) {
				anInt3553 = 0;
			} else {
				method532();
				this.unlink();
				return;
			}
		}
		if (anInt3553 >= i_16_) {
			if (anInt3550 < 0) {
				anInt3553 = i_16_ - 1;
			} else {
				method532();
				this.unlink();
				return;
			}
		}
		anInt3553 += anInt3550 * i;
		if (anInt3562 < 0) {
			if (aBoolean3559) {
				if (anInt3550 < 0) {
					if (anInt3553 >= i_14_) {
						return;
					}
					anInt3553 = i_14_ + i_14_ - 1 - anInt3553;
					anInt3550 = -anInt3550;
				}
				while (anInt3553 >= i_15_) {
					anInt3553 = i_15_ + i_15_ - 1 - anInt3553;
					anInt3550 = -anInt3550;
					if (anInt3553 >= i_14_) {
						break;
					}
					anInt3553 = i_14_ + i_14_ - 1 - anInt3553;
					anInt3550 = -anInt3550;
				}
			} else if (anInt3550 < 0) {
				if (anInt3553 < i_14_) {
					anInt3553 = i_15_ - 1 - (i_15_ - 1 - anInt3553) % i_17_;
				}
			} else if (anInt3553 >= i_15_) {
				anInt3553 = i_14_ + (anInt3553 - i_14_) % i_17_;
			}
		} else {
			do {
				if (anInt3562 > 0) {
					if (aBoolean3559) {
						if (anInt3550 < 0) {
							if (anInt3553 >= i_14_) {
								return;
							}
							anInt3553 = i_14_ + i_14_ - 1 - anInt3553;
							anInt3550 = -anInt3550;
							if (--anInt3562 == 0) {
								break;
							}
						}
						do {
							if (anInt3553 < i_15_) {
								return;
							}
							anInt3553 = i_15_ + i_15_ - 1 - anInt3553;
							anInt3550 = -anInt3550;
							if (--anInt3562 == 0) {
								break;
							}
							if (anInt3553 >= i_14_) {
								return;
							}
							anInt3553 = i_14_ + i_14_ - 1 - anInt3553;
							anInt3550 = -anInt3550;
						} while (--anInt3562 != 0);
					} else if (anInt3550 < 0) {
						if (anInt3553 >= i_14_) {
							return;
						}
						int i_18_ = (i_15_ - 1 - anInt3553) / i_17_;
						if (i_18_ >= anInt3562) {
							anInt3553 += i_17_ * anInt3562;
							anInt3562 = 0;
						} else {
							anInt3553 += i_17_ * i_18_;
							anInt3562 -= i_18_;
							return;
						}
					} else {
						if (anInt3553 < i_15_) {
							return;
						}
						int i_19_ = (anInt3553 - i_14_) / i_17_;
						if (i_19_ >= anInt3562) {
							anInt3553 -= i_17_ * anInt3562;
							anInt3562 = 0;
						} else {
							anInt3553 -= i_17_ * i_19_;
							anInt3562 -= i_19_;
							return;
						}
					}
				}
			} while (false);
			if (anInt3550 < 0) {
				if (anInt3553 < 0) {
					anInt3553 = -1;
					method532();
					this.unlink();
				}
			} else if (anInt3553 >= i_16_) {
				anInt3553 = i_16_;
				method532();
				this.unlink();
			}
		}
	}
	
	public final boolean method511() {
		int i = anInt3549;
		int i_20_;
		int i_21_;
		if (i == -2147483648) {
			i = i_20_ = i_21_ = 0;
		} else {
			i_20_ = method534(i, anInt3560);
			i_21_ = method531(i, anInt3560);
		}
		if (anInt3552 != i || anInt3551 != i_20_ || anInt3556 != i_21_) {
			if (anInt3552 < i) {
				anInt3555 = 1;
				anInt3563 = i - anInt3552;
			} else if (anInt3552 > i) {
				anInt3555 = -1;
				anInt3563 = anInt3552 - i;
			} else {
				anInt3555 = 0;
			}
			if (anInt3551 < i_20_) {
				anInt3557 = 1;
				if (anInt3563 == 0 || anInt3563 > i_20_ - anInt3551) {
					anInt3563 = i_20_ - anInt3551;
				}
			} else if (anInt3551 > i_20_) {
				anInt3557 = -1;
				if (anInt3563 == 0 || anInt3563 > anInt3551 - i_20_) {
					anInt3563 = anInt3551 - i_20_;
				}
			} else {
				anInt3557 = 0;
			}
			if (anInt3556 < i_21_) {
				anInt3561 = 1;
				if (anInt3563 == 0 || anInt3563 > i_21_ - anInt3556) {
					anInt3563 = i_21_ - anInt3556;
				}
			} else if (anInt3556 > i_21_) {
				anInt3561 = -1;
				if (anInt3563 == 0 || anInt3563 > anInt3556 - i_21_) {
					anInt3563 = anInt3556 - i_21_;
				}
			} else {
				anInt3561 = 0;
			}
			return false;
		}
		if (anInt3549 == -2147483648) {
			anInt3549 = 0;
			anInt3552 = anInt3551 = anInt3556 = 0;
			this.unlink();
			return true;
		}
		method546();
		return false;
	}
	
	final synchronized int method512() {
		if (anInt3549 == -2147483648) {
			return 0;
		}
		return anInt3549;
	}
	
	final synchronized int method513() {
		if (anInt3560 < 0) {
			return -1;
		}
		return anInt3560;
	}
	
	public static final int method514(byte[] bs, int[] is, int i, int i_22_, int i_23_, int i_24_, int i_25_, int i_26_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i >>= 8;
		i_26_ >>= 8;
		i_23_ <<= 2;
		if ((i_24_ = i_22_ + i - (i_26_ - 1)) > i_25_) {
			i_24_ = i_25_;
		}
		i_24_ -= 3;
		while (i_22_ < i_24_) {
			is[i_22_++] += bs[i--] * i_23_;
			is[i_22_++] += bs[i--] * i_23_;
			is[i_22_++] += bs[i--] * i_23_;
			is[i_22_++] += bs[i--] * i_23_;
		}
		i_24_ += 3;
		while (i_22_ < i_24_)
			is[i_22_++] += bs[i--] * i_23_;
		class23_sub10_sub1.anInt3553 = i << 8;
		return i_22_;
	}
	
	final synchronized void method515(int i, int i_27_) {
		method547(i, i_27_, method513());
	}
	
	public static final int method516(int i, int i_28_, byte[] bs, int[] is, int i_29_, int i_30_, int i_31_, int i_32_, int i_33_, int i_34_, int i_35_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_36_, int i_37_) {
		class23_sub10_sub1.anInt3551 -= class23_sub10_sub1.anInt3557 * i_30_;
		class23_sub10_sub1.anInt3556 -= class23_sub10_sub1.anInt3561 * i_30_;
		if (i_36_ == 0 || (i_33_ = i_30_ + (i_35_ - i_29_ + i_36_ - 257) / i_36_) > i_34_) {
			i_33_ = i_34_;
		}
		while (i_30_ < i_33_) {
			i_28_ = i_29_ >> 8;
			i = bs[i_28_];
			is[i_30_++] += ((i << 8) + (bs[i_28_ + 1] - i) * (i_29_ & 0xff)) * i_31_ >> 6;
			i_31_ += i_32_;
			i_29_ += i_36_;
		}
		if (i_36_ == 0 || (i_33_ = i_30_ + (i_35_ - i_29_ + i_36_ - 1) / i_36_) > i_34_) {
			i_33_ = i_34_;
		}
		i_28_ = i_37_;
		while (i_30_ < i_33_) {
			i = bs[i_29_ >> 8];
			is[i_30_++] += ((i << 8) + (i_28_ - i) * (i_29_ & 0xff)) * i_31_ >> 6;
			i_31_ += i_32_;
			i_29_ += i_36_;
		}
		class23_sub10_sub1.anInt3551 += class23_sub10_sub1.anInt3557 * i_30_;
		class23_sub10_sub1.anInt3556 += class23_sub10_sub1.anInt3561 * i_30_;
		class23_sub10_sub1.anInt3552 = i_31_;
		class23_sub10_sub1.anInt3553 = i_29_;
		return i_30_;
	}
	
	public static final int method517(int i, int i_38_, byte[] bs, int[] is, int i_39_, int i_40_, int i_41_, int i_42_, int i_43_, int i_44_, int i_45_, int i_46_, int i_47_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_48_, int i_49_) {
		class23_sub10_sub1.anInt3552 -= class23_sub10_sub1.anInt3555 * i_40_;
		if (i_48_ == 0 || (i_45_ = i_40_ + (i_47_ - i_39_ + i_48_ - 257) / i_48_) > i_46_) {
			i_45_ = i_46_;
		}
		i_40_ <<= 1;
		i_45_ <<= 1;
		while (i_40_ < i_45_) {
			i_38_ = i_39_ >> 8;
			i = bs[i_38_];
			i = (i << 8) + (bs[i_38_ + 1] - i) * (i_39_ & 0xff);
			is[i_40_++] += i * i_41_ >> 6;
			i_41_ += i_43_;
			is[i_40_++] += i * i_42_ >> 6;
			i_42_ += i_44_;
			i_39_ += i_48_;
		}
		if (i_48_ == 0 || (i_45_ = (i_40_ >> 1) + (i_47_ - i_39_ + i_48_ - 1) / i_48_) > i_46_) {
			i_45_ = i_46_;
		}
		i_45_ <<= 1;
		i_38_ = i_49_;
		while (i_40_ < i_45_) {
			i = bs[i_39_ >> 8];
			i = (i << 8) + (i_38_ - i) * (i_39_ & 0xff);
			is[i_40_++] += i * i_41_ >> 6;
			i_41_ += i_43_;
			is[i_40_++] += i * i_42_ >> 6;
			i_42_ += i_44_;
			i_39_ += i_48_;
		}
		i_40_ >>= 1;
		class23_sub10_sub1.anInt3552 += class23_sub10_sub1.anInt3555 * i_40_;
		class23_sub10_sub1.anInt3551 = i_41_;
		class23_sub10_sub1.anInt3556 = i_42_;
		class23_sub10_sub1.anInt3553 = i_39_;
		return i_40_;
	}
	
	public static final int method518(byte[] bs, int[] is, int i, int i_50_, int i_51_, int i_52_, int i_53_, int i_54_, int i_55_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i >>= 8;
		i_55_ >>= 8;
		i_51_ <<= 2;
		i_52_ <<= 2;
		if ((i_53_ = i_50_ + i - (i_55_ - 1)) > i_54_) {
			i_53_ = i_54_;
		}
		class23_sub10_sub1.anInt3551 += class23_sub10_sub1.anInt3557 * (i_53_ - i_50_);
		class23_sub10_sub1.anInt3556 += class23_sub10_sub1.anInt3561 * (i_53_ - i_50_);
		i_53_ -= 3;
		while (i_50_ < i_53_) {
			is[i_50_++] += bs[i--] * i_51_;
			i_51_ += i_52_;
			is[i_50_++] += bs[i--] * i_51_;
			i_51_ += i_52_;
			is[i_50_++] += bs[i--] * i_51_;
			i_51_ += i_52_;
			is[i_50_++] += bs[i--] * i_51_;
			i_51_ += i_52_;
		}
		i_53_ += 3;
		while (i_50_ < i_53_) {
			is[i_50_++] += bs[i--] * i_51_;
			i_51_ += i_52_;
		}
		class23_sub10_sub1.anInt3552 = i_51_ >> 2;
		class23_sub10_sub1.anInt3553 = i << 8;
		return i_50_;
	}
	
	final synchronized void method519(int i) {
		if (i == 0) {
			method544(0);
			this.unlink();
		} else if (anInt3551 == 0 && anInt3556 == 0) {
			anInt3563 = 0;
			anInt3549 = 0;
			anInt3552 = 0;
			this.unlink();
		} else {
			int i_56_ = -anInt3552;
			if (anInt3552 > i_56_) {
				i_56_ = anInt3552;
			}
			if (-anInt3551 > i_56_) {
				i_56_ = -anInt3551;
			}
			if (anInt3551 > i_56_) {
				i_56_ = anInt3551;
			}
			if (-anInt3556 > i_56_) {
				i_56_ = -anInt3556;
			}
			if (anInt3556 > i_56_) {
				i_56_ = anInt3556;
			}
			if (i > i_56_) {
				i = i_56_;
			}
			anInt3563 = i;
			anInt3549 = -2147483648;
			anInt3555 = -anInt3552 / i;
			anInt3557 = -anInt3551 / i;
			anInt3561 = -anInt3556 / i;
		}
	}
	
	public final int method520(int[] is, int i, int i_57_, int i_58_, int i_59_) {
		while (anInt3563 > 0) {
			int i_60_ = i + anInt3563;
			if (i_60_ > i_58_) {
				i_60_ = i_58_;
			}
			anInt3563 += i;
			if (anInt3550 == 256 && (anInt3553 & 0xff) == 0) {
				if (FileSystem.stereo) {
					i = method533(0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, anInt3557, anInt3561, 0, i_60_, i_57_, this);
				} else {
					i = method540(((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, anInt3555, 0, i_60_, i_57_, this);
				}
			} else if (FileSystem.stereo) {
				i = method517(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, anInt3557, anInt3561, 0, i_60_, i_57_, this, anInt3550, i_59_);
			} else {
				i = method516(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, anInt3555, 0, i_60_, i_57_, this, anInt3550, i_59_);
			}
			anInt3563 -= i;
			if (anInt3563 != 0) {
				return i;
			}
			if (method511()) {
				return i_58_;
			}
		}
		if (anInt3550 == 256 && (anInt3553 & 0xff) == 0) {
			if (FileSystem.stereo) {
				return method545(0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, 0, i_58_, i_57_, this);
			}
			return method509(((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, 0, i_58_, i_57_, this);
		}
		if (FileSystem.stereo) {
			return method525(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, 0, i_58_, i_57_, this, anInt3550, i_59_);
		}
		return method530(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, 0, i_58_, i_57_, this, anInt3550, i_59_);
	}
	
	public final int method521(int[] is, int i, int i_61_, int i_62_, int i_63_) {
		while (anInt3563 > 0) {
			int i_64_ = i + anInt3563;
			if (i_64_ > i_62_) {
				i_64_ = i_62_;
			}
			anInt3563 += i;
			if (anInt3550 == -256 && (anInt3553 & 0xff) == 0) {
				if (FileSystem.stereo) {
					i = method539(0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, anInt3557, anInt3561, 0, i_64_, i_61_, this);
				} else {
					i = method518(((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, anInt3555, 0, i_64_, i_61_, this);
				}
			} else if (FileSystem.stereo) {
				i = method535(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, anInt3557, anInt3561, 0, i_64_, i_61_, this, anInt3550, i_63_);
			} else {
				i = method537(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, anInt3555, 0, i_64_, i_61_, this, anInt3550, i_63_);
			}
			anInt3563 -= i;
			if (anInt3563 != 0) {
				return i;
			}
			if (method511()) {
				return i_62_;
			}
		}
		if (anInt3550 == -256 && (anInt3553 & 0xff) == 0) {
			if (FileSystem.stereo) {
				return method522(0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, 0, i_62_, i_61_, this);
			}
			return method514(((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, 0, i_62_, i_61_, this);
		}
		if (FileSystem.stereo) {
			return method542(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3551, anInt3556, 0, i_62_, i_61_, this, anInt3550, i_63_);
		}
		return method541(0, 0, ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544, is, anInt3553, i, anInt3552, 0, i_62_, i_61_, this, anInt3550, i_63_);
	}
	
	public static final int method522(int i, byte[] bs, int[] is, int i_65_, int i_66_, int i_67_, int i_68_, int i_69_, int i_70_, int i_71_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i_65_ >>= 8;
		i_71_ >>= 8;
		i_67_ <<= 2;
		i_68_ <<= 2;
		if ((i_69_ = i_66_ + i_65_ - (i_71_ - 1)) > i_70_) {
			i_69_ = i_70_;
		}
		i_66_ <<= 1;
		i_69_ <<= 1;
		i_69_ -= 6;
		while (i_66_ < i_69_) {
			i = bs[i_65_--];
			is[i_66_++] += i * i_67_;
			is[i_66_++] += i * i_68_;
			i = bs[i_65_--];
			is[i_66_++] += i * i_67_;
			is[i_66_++] += i * i_68_;
			i = bs[i_65_--];
			is[i_66_++] += i * i_67_;
			is[i_66_++] += i * i_68_;
			i = bs[i_65_--];
			is[i_66_++] += i * i_67_;
			is[i_66_++] += i * i_68_;
		}
		i_69_ += 6;
		while (i_66_ < i_69_) {
			i = bs[i_65_--];
			is[i_66_++] += i * i_67_;
			is[i_66_++] += i * i_68_;
		}
		class23_sub10_sub1.anInt3553 = i_65_ << 8;
		return i_66_ >> 1;
	}
	
	final synchronized void method523(boolean bool) {
		anInt3550 = (anInt3550 ^ anInt3550 >> 31) + (anInt3550 >>> 31);
		if (bool) {
			anInt3550 = -anInt3550;
		}
	}
	
	final synchronized void method524(int i) {
		int i_72_ = ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544.length << 8;
		if (i < -1) {
			i = -1;
		}
		if (i > i_72_) {
			i = i_72_;
		}
		anInt3553 = i;
	}
	
	public static final int method525(int i, int i_73_, byte[] bs, int[] is, int i_74_, int i_75_, int i_76_, int i_77_, int i_78_, int i_79_, int i_80_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_81_, int i_82_) {
		if (i_81_ == 0 || (i_78_ = i_75_ + (i_80_ - i_74_ + i_81_ - 257) / i_81_) > i_79_) {
			i_78_ = i_79_;
		}
		i_75_ <<= 1;
		i_78_ <<= 1;
		while (i_75_ < i_78_) {
			i_73_ = i_74_ >> 8;
			i = bs[i_73_];
			i = (i << 8) + (bs[i_73_ + 1] - i) * (i_74_ & 0xff);
			is[i_75_++] += i * i_76_ >> 6;
			is[i_75_++] += i * i_77_ >> 6;
			i_74_ += i_81_;
		}
		if (i_81_ == 0 || (i_78_ = (i_75_ >> 1) + (i_80_ - i_74_ + i_81_ - 1) / i_81_) > i_79_) {
			i_78_ = i_79_;
		}
		i_78_ <<= 1;
		i_73_ = i_82_;
		while (i_75_ < i_78_) {
			i = bs[i_74_ >> 8];
			i = (i << 8) + (i_73_ - i) * (i_74_ & 0xff);
			is[i_75_++] += i * i_76_ >> 6;
			is[i_75_++] += i * i_77_ >> 6;
			i_74_ += i_81_;
		}
		class23_sub10_sub1.anInt3553 = i_74_;
		return i_75_ >> 1;
	}
	
	final boolean method526() {
		if (anInt3563 == 0) {
			return false;
		}
		return true;
	}
	
	final boolean method527() {
		if (anInt3553 >= 0 && anInt3553 < ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544.length << 8) {
			return false;
		}
		return true;
	}
	
	final synchronized void method528(int i) {
		if (anInt3550 < 0) {
			anInt3550 = -i;
		} else {
			anInt3550 = i;
		}
	}
	
	static final Class23_Sub10_Sub1 method529(SomeSoundClass2 someSoundClass2, int i, int i_83_, int i_84_) {
		if (someSoundClass2.aByteArray3544 == null || someSoundClass2.aByteArray3544.length == 0) {
			return null;
		}
		return new Class23_Sub10_Sub1(someSoundClass2, i, i_83_, i_84_);
	}
	
	public static final int method530(int i, int i_85_, byte[] bs, int[] is, int i_86_, int i_87_, int i_88_, int i_89_, int i_90_, int i_91_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_92_, int i_93_) {
		if (i_92_ == 0 || (i_89_ = i_87_ + (i_91_ - i_86_ + i_92_ - 257) / i_92_) > i_90_) {
			i_89_ = i_90_;
		}
		while (i_87_ < i_89_) {
			i_85_ = i_86_ >> 8;
			i = bs[i_85_];
			is[i_87_++] += ((i << 8) + (bs[i_85_ + 1] - i) * (i_86_ & 0xff)) * i_88_ >> 6;
			i_86_ += i_92_;
		}
		if (i_92_ == 0 || (i_89_ = i_87_ + (i_91_ - i_86_ + i_92_ - 1) / i_92_) > i_90_) {
			i_89_ = i_90_;
		}
		i_85_ = i_93_;
		while (i_87_ < i_89_) {
			i = bs[i_86_ >> 8];
			is[i_87_++] += ((i << 8) + (i_85_ - i) * (i_86_ & 0xff)) * i_88_ >> 6;
			i_86_ += i_92_;
		}
		class23_sub10_sub1.anInt3553 = i_86_;
		return i_87_;
	}
	
	@Override
	final int method501() {
		if (anInt3549 == 0 && anInt3563 == 0) {
			return 0;
		}
		return 1;
	}
	
	public static final int method531(int i, int i_94_) {
		if (i_94_ < 0) {
			return -i;
		}
		return (int) (i * Math.sqrt(i_94_ * 1.220703125E-4) + 0.5);
	}
	
	public final void method532() {
		if (anInt3563 != 0) {
			if (anInt3549 == -2147483648) {
				anInt3549 = 0;
			}
			anInt3563 = 0;
			method546();
		}
	}
	
	public static final int method533(int i, byte[] bs, int[] is, int i_95_, int i_96_, int i_97_, int i_98_, int i_99_, int i_100_, int i_101_, int i_102_, int i_103_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i_95_ >>= 8;
		i_103_ >>= 8;
		i_97_ <<= 2;
		i_98_ <<= 2;
		i_99_ <<= 2;
		i_100_ <<= 2;
		if ((i_101_ = i_96_ + i_103_ - i_95_) > i_102_) {
			i_101_ = i_102_;
		}
		class23_sub10_sub1.anInt3552 += class23_sub10_sub1.anInt3555 * (i_101_ - i_96_);
		i_96_ <<= 1;
		i_101_ <<= 1;
		i_101_ -= 6;
		while (i_96_ < i_101_) {
			i = bs[i_95_++];
			is[i_96_++] += i * i_97_;
			i_97_ += i_99_;
			is[i_96_++] += i * i_98_;
			i_98_ += i_100_;
			i = bs[i_95_++];
			is[i_96_++] += i * i_97_;
			i_97_ += i_99_;
			is[i_96_++] += i * i_98_;
			i_98_ += i_100_;
			i = bs[i_95_++];
			is[i_96_++] += i * i_97_;
			i_97_ += i_99_;
			is[i_96_++] += i * i_98_;
			i_98_ += i_100_;
			i = bs[i_95_++];
			is[i_96_++] += i * i_97_;
			i_97_ += i_99_;
			is[i_96_++] += i * i_98_;
			i_98_ += i_100_;
		}
		i_101_ += 6;
		while (i_96_ < i_101_) {
			i = bs[i_95_++];
			is[i_96_++] += i * i_97_;
			i_97_ += i_99_;
			is[i_96_++] += i * i_98_;
			i_98_ += i_100_;
		}
		class23_sub10_sub1.anInt3551 = i_97_ >> 2;
		class23_sub10_sub1.anInt3556 = i_98_ >> 2;
		class23_sub10_sub1.anInt3553 = i_95_ << 8;
		return i_96_ >> 1;
	}
	
	public static final int method534(int i, int i_104_) {
		if (i_104_ < 0) {
			return i;
		}
		return (int) (i * Math.sqrt((16384 - i_104_) * 1.220703125E-4) + 0.5);
	}
	
	public static final int method535(int i, int i_105_, byte[] bs, int[] is, int i_106_, int i_107_, int i_108_, int i_109_, int i_110_, int i_111_, int i_112_, int i_113_, int i_114_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_115_, int i_116_) {
		class23_sub10_sub1.anInt3552 -= class23_sub10_sub1.anInt3555 * i_107_;
		if (i_115_ == 0 || (i_112_ = i_107_ + (i_114_ + 256 - i_106_ + i_115_) / i_115_) > i_113_) {
			i_112_ = i_113_;
		}
		i_107_ <<= 1;
		i_112_ <<= 1;
		while (i_107_ < i_112_) {
			i_105_ = i_106_ >> 8;
			i = bs[i_105_ - 1];
			i = (i << 8) + (bs[i_105_] - i) * (i_106_ & 0xff);
			is[i_107_++] += i * i_108_ >> 6;
			i_108_ += i_110_;
			is[i_107_++] += i * i_109_ >> 6;
			i_109_ += i_111_;
			i_106_ += i_115_;
		}
		if (i_115_ == 0 || (i_112_ = (i_107_ >> 1) + (i_114_ - i_106_ + i_115_) / i_115_) > i_113_) {
			i_112_ = i_113_;
		}
		i_112_ <<= 1;
		i_105_ = i_116_;
		while (i_107_ < i_112_) {
			i = (i_105_ << 8) + (bs[i_106_ >> 8] - i_105_) * (i_106_ & 0xff);
			is[i_107_++] += i * i_108_ >> 6;
			i_108_ += i_110_;
			is[i_107_++] += i * i_109_ >> 6;
			i_109_ += i_111_;
			i_106_ += i_115_;
		}
		i_107_ >>= 1;
		class23_sub10_sub1.anInt3552 += class23_sub10_sub1.anInt3555 * i_107_;
		class23_sub10_sub1.anInt3551 = i_108_;
		class23_sub10_sub1.anInt3556 = i_109_;
		class23_sub10_sub1.anInt3553 = i_106_;
		return i_107_;
	}
	
	final synchronized void method536(int i) {
		anInt3562 = i;
	}
	
	public static final int method537(int i, int i_117_, byte[] bs, int[] is, int i_118_, int i_119_, int i_120_, int i_121_, int i_122_, int i_123_, int i_124_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_125_, int i_126_) {
		class23_sub10_sub1.anInt3551 -= class23_sub10_sub1.anInt3557 * i_119_;
		class23_sub10_sub1.anInt3556 -= class23_sub10_sub1.anInt3561 * i_119_;
		if (i_125_ == 0 || (i_122_ = i_119_ + (i_124_ + 256 - i_118_ + i_125_) / i_125_) > i_123_) {
			i_122_ = i_123_;
		}
		while (i_119_ < i_122_) {
			i_117_ = i_118_ >> 8;
			i = bs[i_117_ - 1];
			is[i_119_++] += ((i << 8) + (bs[i_117_] - i) * (i_118_ & 0xff)) * i_120_ >> 6;
			i_120_ += i_121_;
			i_118_ += i_125_;
		}
		if (i_125_ == 0 || (i_122_ = i_119_ + (i_124_ - i_118_ + i_125_) / i_125_) > i_123_) {
			i_122_ = i_123_;
		}
		i = i_126_;
		i_117_ = i_125_;
		while (i_119_ < i_122_) {
			is[i_119_++] += ((i << 8) + (bs[i_118_ >> 8] - i) * (i_118_ & 0xff)) * i_120_ >> 6;
			i_120_ += i_121_;
			i_118_ += i_117_;
		}
		class23_sub10_sub1.anInt3551 += class23_sub10_sub1.anInt3557 * i_119_;
		class23_sub10_sub1.anInt3556 += class23_sub10_sub1.anInt3561 * i_119_;
		class23_sub10_sub1.anInt3552 = i_120_;
		class23_sub10_sub1.anInt3553 = i_118_;
		return i_119_;
	}
	
	public final synchronized void method538(int i, int i_127_) {
		anInt3549 = i;
		anInt3560 = i_127_;
		anInt3563 = 0;
		method546();
	}
	
	public static final int method539(int i, byte[] bs, int[] is, int i_128_, int i_129_, int i_130_, int i_131_, int i_132_, int i_133_, int i_134_, int i_135_, int i_136_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i_128_ >>= 8;
		i_136_ >>= 8;
		i_130_ <<= 2;
		i_131_ <<= 2;
		i_132_ <<= 2;
		i_133_ <<= 2;
		if ((i_134_ = i_129_ + i_128_ - (i_136_ - 1)) > i_135_) {
			i_134_ = i_135_;
		}
		class23_sub10_sub1.anInt3552 += class23_sub10_sub1.anInt3555 * (i_134_ - i_129_);
		i_129_ <<= 1;
		i_134_ <<= 1;
		i_134_ -= 6;
		while (i_129_ < i_134_) {
			i = bs[i_128_--];
			is[i_129_++] += i * i_130_;
			i_130_ += i_132_;
			is[i_129_++] += i * i_131_;
			i_131_ += i_133_;
			i = bs[i_128_--];
			is[i_129_++] += i * i_130_;
			i_130_ += i_132_;
			is[i_129_++] += i * i_131_;
			i_131_ += i_133_;
			i = bs[i_128_--];
			is[i_129_++] += i * i_130_;
			i_130_ += i_132_;
			is[i_129_++] += i * i_131_;
			i_131_ += i_133_;
			i = bs[i_128_--];
			is[i_129_++] += i * i_130_;
			i_130_ += i_132_;
			is[i_129_++] += i * i_131_;
			i_131_ += i_133_;
		}
		i_134_ += 6;
		while (i_129_ < i_134_) {
			i = bs[i_128_--];
			is[i_129_++] += i * i_130_;
			i_130_ += i_132_;
			is[i_129_++] += i * i_131_;
			i_131_ += i_133_;
		}
		class23_sub10_sub1.anInt3551 = i_130_ >> 2;
		class23_sub10_sub1.anInt3556 = i_131_ >> 2;
		class23_sub10_sub1.anInt3553 = i_128_ << 8;
		return i_129_ >> 1;
	}
	
	public static final int method540(byte[] bs, int[] is, int i, int i_137_, int i_138_, int i_139_, int i_140_, int i_141_, int i_142_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i >>= 8;
		i_142_ >>= 8;
		i_138_ <<= 2;
		i_139_ <<= 2;
		if ((i_140_ = i_137_ + i_142_ - i) > i_141_) {
			i_140_ = i_141_;
		}
		class23_sub10_sub1.anInt3551 += class23_sub10_sub1.anInt3557 * (i_140_ - i_137_);
		class23_sub10_sub1.anInt3556 += class23_sub10_sub1.anInt3561 * (i_140_ - i_137_);
		i_140_ -= 3;
		while (i_137_ < i_140_) {
			is[i_137_++] += bs[i++] * i_138_;
			i_138_ += i_139_;
			is[i_137_++] += bs[i++] * i_138_;
			i_138_ += i_139_;
			is[i_137_++] += bs[i++] * i_138_;
			i_138_ += i_139_;
			is[i_137_++] += bs[i++] * i_138_;
			i_138_ += i_139_;
		}
		i_140_ += 3;
		while (i_137_ < i_140_) {
			is[i_137_++] += bs[i++] * i_138_;
			i_138_ += i_139_;
		}
		class23_sub10_sub1.anInt3552 = i_138_ >> 2;
		class23_sub10_sub1.anInt3553 = i << 8;
		return i_137_;
	}
	
	public static final int method541(int i, int i_143_, byte[] bs, int[] is, int i_144_, int i_145_, int i_146_, int i_147_, int i_148_, int i_149_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_150_, int i_151_) {
		if (i_150_ == 0 || (i_147_ = i_145_ + (i_149_ + 256 - i_144_ + i_150_) / i_150_) > i_148_) {
			i_147_ = i_148_;
		}
		while (i_145_ < i_147_) {
			i_143_ = i_144_ >> 8;
			i = bs[i_143_ - 1];
			is[i_145_++] += ((i << 8) + (bs[i_143_] - i) * (i_144_ & 0xff)) * i_146_ >> 6;
			i_144_ += i_150_;
		}
		if (i_150_ == 0 || (i_147_ = i_145_ + (i_149_ - i_144_ + i_150_) / i_150_) > i_148_) {
			i_147_ = i_148_;
		}
		i = i_151_;
		i_143_ = i_150_;
		while (i_145_ < i_147_) {
			is[i_145_++] += ((i << 8) + (bs[i_144_ >> 8] - i) * (i_144_ & 0xff)) * i_146_ >> 6;
			i_144_ += i_143_;
		}
		class23_sub10_sub1.anInt3553 = i_144_;
		return i_145_;
	}
	
	public static final int method542(int i, int i_152_, byte[] bs, int[] is, int i_153_, int i_154_, int i_155_, int i_156_, int i_157_, int i_158_, int i_159_, Class23_Sub10_Sub1 class23_sub10_sub1, int i_160_, int i_161_) {
		if (i_160_ == 0 || (i_157_ = i_154_ + (i_159_ + 256 - i_153_ + i_160_) / i_160_) > i_158_) {
			i_157_ = i_158_;
		}
		i_154_ <<= 1;
		i_157_ <<= 1;
		while (i_154_ < i_157_) {
			i_152_ = i_153_ >> 8;
			i = bs[i_152_ - 1];
			i = (i << 8) + (bs[i_152_] - i) * (i_153_ & 0xff);
			is[i_154_++] += i * i_155_ >> 6;
			is[i_154_++] += i * i_156_ >> 6;
			i_153_ += i_160_;
		}
		if (i_160_ == 0 || (i_157_ = (i_154_ >> 1) + (i_159_ - i_153_ + i_160_) / i_160_) > i_158_) {
			i_157_ = i_158_;
		}
		i_157_ <<= 1;
		i_152_ = i_161_;
		while (i_154_ < i_157_) {
			i = (i_152_ << 8) + (bs[i_153_ >> 8] - i_152_) * (i_153_ & 0xff);
			is[i_154_++] += i * i_155_ >> 6;
			is[i_154_++] += i * i_156_ >> 6;
			i_153_ += i_160_;
		}
		class23_sub10_sub1.anInt3553 = i_153_;
		return i_154_ >> 1;
	}
	
	final synchronized void method543(int i) {
		method538(i << 6, method513());
	}
	
	@Override
	final Class23_Sub10 method502() {
		return null;
	}
	
	public final synchronized void method544(int i) {
		method538(i, method513());
	}
	
	public static final int method545(int i, byte[] bs, int[] is, int i_162_, int i_163_, int i_164_, int i_165_, int i_166_, int i_167_, int i_168_, Class23_Sub10_Sub1 class23_sub10_sub1) {
		i_162_ >>= 8;
		i_168_ >>= 8;
		i_164_ <<= 2;
		i_165_ <<= 2;
		if ((i_166_ = i_163_ + i_168_ - i_162_) > i_167_) {
			i_166_ = i_167_;
		}
		i_163_ <<= 1;
		i_166_ <<= 1;
		i_166_ -= 6;
		while (i_163_ < i_166_) {
			i = bs[i_162_++];
			is[i_163_++] += i * i_164_;
			is[i_163_++] += i * i_165_;
			i = bs[i_162_++];
			is[i_163_++] += i * i_164_;
			is[i_163_++] += i * i_165_;
			i = bs[i_162_++];
			is[i_163_++] += i * i_164_;
			is[i_163_++] += i * i_165_;
			i = bs[i_162_++];
			is[i_163_++] += i * i_164_;
			is[i_163_++] += i * i_165_;
		}
		i_166_ += 6;
		while (i_163_ < i_166_) {
			i = bs[i_162_++];
			is[i_163_++] += i * i_164_;
			is[i_163_++] += i * i_165_;
		}
		class23_sub10_sub1.anInt3553 = i_162_ << 8;
		return i_163_ >> 1;
	}
	
	@Override
	final int method505() {
		int i = anInt3552 * 3 >> 6;
		i = (i ^ i >> 31) + (i >>> 31);
		if (anInt3562 == 0) {
			i -= i * anInt3553 / (((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544.length << 8);
		} else if (anInt3562 >= 0) {
			i -= i * anInt3554 / ((SomeSoundClass2) aClass23_Sub6_2278).aByteArray3544.length;
		}
		if (i > 255) {
			return 255;
		}
		return i;
	}
	
	public final void method546() {
		anInt3552 = anInt3549;
		anInt3551 = method534(anInt3549, anInt3560);
		anInt3556 = method531(anInt3549, anInt3560);
	}
	
	final synchronized void method547(int i, int i_169_, int i_170_) {
		if (i == 0) {
			method538(i_169_, i_170_);
		} else {
			int i_171_ = method534(i_169_, i_170_);
			int i_172_ = method531(i_169_, i_170_);
			if (anInt3551 == i_171_ && anInt3556 == i_172_) {
				anInt3563 = 0;
			} else {
				int i_173_ = i_169_ - anInt3552;
				if (anInt3552 - i_169_ > i_173_) {
					i_173_ = anInt3552 - i_169_;
				}
				if (i_171_ - anInt3551 > i_173_) {
					i_173_ = i_171_ - anInt3551;
				}
				if (anInt3551 - i_171_ > i_173_) {
					i_173_ = anInt3551 - i_171_;
				}
				if (i_172_ - anInt3556 > i_173_) {
					i_173_ = i_172_ - anInt3556;
				}
				if (anInt3556 - i_172_ > i_173_) {
					i_173_ = anInt3556 - i_172_;
				}
				if (i > i_173_) {
					i = i_173_;
				}
				anInt3563 = i;
				anInt3549 = i_169_;
				anInt3560 = i_170_;
				anInt3555 = (i_169_ - anInt3552) / i;
				anInt3557 = (i_171_ - anInt3551) / i;
				anInt3561 = (i_172_ - anInt3556) / i;
			}
		}
	}
	
	public Class23_Sub10_Sub1(SomeSoundClass2 someSoundClass2, int i, int i_174_) {
		aClass23_Sub6_2278 = someSoundClass2;
		anInt3554 = someSoundClass2.anInt3548;
		anInt3558 = someSoundClass2.anInt3546;
		aBoolean3559 = someSoundClass2.aBoolean3545;
		anInt3550 = i;
		anInt3549 = i_174_;
		anInt3560 = 8192;
		anInt3553 = 0;
		method546();
	}
	
	@Override
	final Class23_Sub10 method503() {
		return null;
	}
	
	public Class23_Sub10_Sub1(SomeSoundClass2 someSoundClass2, int i, int i_175_, int i_176_) {
		aClass23_Sub6_2278 = someSoundClass2;
		anInt3554 = someSoundClass2.anInt3548;
		anInt3558 = someSoundClass2.anInt3546;
		aBoolean3559 = someSoundClass2.aBoolean3545;
		anInt3550 = i;
		anInt3549 = i_175_;
		anInt3560 = i_176_;
		anInt3553 = 0;
		method546();
	}
}
