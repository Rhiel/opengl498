package com.jagex;
import java.io.File;
import java.io.IOException;

public class SeekableFile
{
	public long aLong433 = -1L;
	public long aLong437 = -1L;
	public byte[] aByteArray443;
	public int anInt447;
	public int anInt449 = 0;
	static HashTable anOa451;
	public long file_size;
	public byte[] aByteArray453;
	public long curOffset;
	public FileOnDisk file;
	public long aLong466;
	static long[] messageLog = new long[100];
	public long aLong468;
    static Stereo aStereo_471;
	
	public final void method941(int i) throws IOException {
		if ((aLong437 ^ 0xffffffffffffffffL) != 0L) {
			if (aLong468 != aLong437) {
				file.seek((byte) -29, aLong437);
				aLong468 = aLong437;
			}
			long l = -1L;
			file.write(aByteArray443, 0, anInt449, 1);
			long l_0_ = -1L;
			aLong468 += anInt449;
			if ((aLong466 ^ 0xffffffffffffffffL) > (aLong468 ^ 0xffffffffffffffffL)) {
				aLong466 = aLong468;
			}
			if (aLong433 > aLong437 || (aLong437 ^ 0xffffffffffffffffL) <= (aLong433 - -(long) anInt447 ^ 0xffffffffffffffffL)) {
				if ((aLong437 ^ 0xffffffffffffffffL) >= (aLong433 ^ 0xffffffffffffffffL) && aLong437 - -(long) anInt449 > aLong433) {
					l_0_ = aLong433;
				}
			} else {
				l_0_ = aLong437;
			}
			if (aLong433 >= aLong437 + anInt449 || (anInt449 + aLong437 ^ 0xffffffffffffffffL) < (anInt447 + aLong433 ^ 0xffffffffffffffffL)) {
				if ((aLong437 ^ 0xffffffffffffffffL) > (anInt447 + aLong433 ^ 0xffffffffffffffffL) && (anInt447 + aLong433 ^ 0xffffffffffffffffL) >= (anInt449 + aLong437 ^ 0xffffffffffffffffL)) {
					l = aLong433 + anInt447;
				}
			} else {
				l = anInt449 + aLong437;
			}
			if ((l_0_ ^ 0xffffffffffffffffL) < 0L && (l ^ 0xffffffffffffffffL) < (l_0_ ^ 0xffffffffffffffffL)) {
				int i_1_ = (int) (l - l_0_);
				ArrayUtils.copy(aByteArray443, (int) (-aLong437 + l_0_), aByteArray453, (int) (l_0_ - aLong433), i_1_);
			}
			anInt449 = 0;
			aLong437 = -1L;
		}
		@SuppressWarnings("unused")
		int i_2_ = 23 % ((i - -44) / 55);
	}
	
	public final void method942(int i) throws IOException {
		if (i != -1) {
			aStereo_471 = null;
		}
		anInt447 = 0;
		if (curOffset != aLong468) {
			file.seek((byte) -29, curOffset);
			aLong468 = curOffset;
		}
		aLong433 = curOffset;
		int i_3_;
		for (/**/; (anInt447 ^ 0xffffffff) > (aByteArray453.length ^ 0xffffffff); anInt447 += i_3_) {
			int i_4_ = aByteArray453.length - anInt447;
			if ((i_4_ ^ 0xffffffff) < -200000001) {
				i_4_ = 200000000;
			}
			i_3_ = file.read(true, aByteArray453, i_4_, anInt447);
			if ((i_3_ ^ 0xffffffff) == 0) {
				break;
			}
			aLong468 += i_3_;
		}
	}
	
	public static void method943(byte b) {
		if (b < -52) {
			messageLog = null;
			anOa451 = null;
			CacheConstants.scriptMapIdx = null;
			aStereo_471 = null;
		}
	}

	final long get_size(boolean bool) {
		return file_size;
	}
	
	public final File getFile(int i) {
		if (i != 20459) {
			return null;
		}
		return file.getFile(true);
	}
	
	final void seek(long l, int i) throws IOException {
		if (i != -11320) {
			create((byte) 34);
		}
		if ((l ^ 0xffffffffffffffffL) > -1L) {
			throw new IOException("Invalid seek to " + l + " in file " + getFile(20459));
		}
		curOffset = l;
	}
	
	static final void method949(int i, int i_18_, int i_19_) {
		Ground tile = com.rs2.client.scene.Scene.current_grounds[i][i_18_][i_19_];
		if (tile != null) {
			tile.obj_entity = null;
		}
	}
	
	final void method950(byte[] bs, int dummy) throws IOException {
		read(bs.length, bs, 11524, 0);
	}
	
	static final AbstractTimer create(byte b) {
		try {
			if (b != -88) {
				anOa451 = null;
			}
			return (AbstractTimer) Class.forName("rs.zaros.jagex.NanoTimer").newInstance();
		} catch (Throwable throwable) {
			return new GameTimer();
		}
	}
	
	final void read(int length, byte[] bs, int i_21_, int offset) throws IOException {
		if (i_21_ == 11524) {
			try {
				if ((length + offset ^ 0xffffffff) < (bs.length ^ 0xffffffff)) {
					throw new ArrayIndexOutOfBoundsException(-bs.length + offset - -length);
				}
				if ((aLong437 ^ 0xffffffffffffffffL) != 0L && (aLong437 ^ 0xffffffffffffffffL) >= (curOffset ^ 0xffffffffffffffffL) && (aLong437 + anInt449 ^ 0xffffffffffffffffL) <= (length + curOffset ^ 0xffffffffffffffffL)) {
					ArrayUtils.copy(aByteArray443, (int) (curOffset - aLong437), bs, offset, length);
					curOffset += length;
					return;
				}
				int i_23_ = length;
				long l = curOffset;
				int i_24_ = offset;
				if (curOffset >= aLong433 && anInt447 + aLong433 > curOffset) {
					int i_25_ = (int) (anInt447 + aLong433 + -curOffset);
					if ((length ^ 0xffffffff) > (i_25_ ^ 0xffffffff)) {
						i_25_ = length;
					}
					length -= i_25_;
					ArrayUtils.copy(aByteArray453, (int) (-aLong433 + curOffset), bs, offset, i_25_);
					offset += i_25_;
					curOffset += i_25_;
				}
				if ((length ^ 0xffffffff) >= (aByteArray453.length ^ 0xffffffff)) {
					if ((length ^ 0xffffffff) < -1) {
						int i_26_ = length;
						method942(-1);
						if (i_26_ > anInt447) {
							i_26_ = anInt447;
						}
						ArrayUtils.copy(aByteArray453, 0, bs, offset, i_26_);
						curOffset += i_26_;
						length -= i_26_;
						offset += i_26_;
					}
				}
				else {
					file.seek((byte) -29, curOffset);
					aLong468 = curOffset;
					while ((length ^ 0xffffffff) < -1) {
						int i_27_ = file.read(true, bs, length, offset);
						if (i_27_ == -1) {
							break;
						}
						curOffset += i_27_;
						length -= i_27_;
						offset += i_27_;
						aLong468 += i_27_;
					}
				}
				if (aLong437 != -1L) {
					if ((aLong437 ^ 0xffffffffffffffffL) < (curOffset ^ 0xffffffffffffffffL) && (length ^ 0xffffffff) < -1) {
						int i_28_ = (int) (-curOffset + aLong437) + offset;
						if (i_28_ > length + offset) {
							i_28_ = length + offset;
						}
						while ((offset ^ 0xffffffff) > (i_28_ ^ 0xffffffff)) {
							length--;
							bs[offset++] = (byte) 0;
							curOffset++;
						}
					}
					long l_29_ = -1L;
					if ((l ^ 0xffffffffffffffffL) > (aLong437 + anInt449 ^ 0xffffffffffffffffL) && aLong437 + anInt449 <= l - -(long) i_23_) {
						l_29_ = anInt449 + aLong437;
					}
					else if ((aLong437 ^ 0xffffffffffffffffL) > (l + i_23_ ^ 0xffffffffffffffffL) && (l - -(long) i_23_ ^ 0xffffffffffffffffL) >= (anInt449 + aLong437 ^ 0xffffffffffffffffL)) {
						l_29_ = i_23_ + l;
					}
					long l_30_ = -1L;
					if (l <= aLong437 && (l + i_23_ ^ 0xffffffffffffffffL) < (aLong437 ^ 0xffffffffffffffffL)) {
						l_30_ = aLong437;
					}
					else if ((aLong437 ^ 0xffffffffffffffffL) >= (l ^ 0xffffffffffffffffL) && anInt449 + aLong437 > l) {
						l_30_ = l;
					}
					if (l_30_ > -1L && l_29_ > l_30_) {
						int i_31_ = (int) (l_29_ + -l_30_);
						ArrayUtils.copy(aByteArray443, (int) (l_30_ + -aLong437), bs, i_24_ + (int) (-l + l_30_), i_31_);
						if (curOffset < l_29_) {
							length -= -curOffset + l_29_;
							curOffset = l_29_;
						}
					}
				}
			} catch (IOException ioexception) {
				aLong468 = -1L;
				throw ioexception;
			}
			if (length > 0) {
				//throw new EOFException();
			}
		}
	}
	@SuppressWarnings("unused")
	final void close(byte b) throws IOException {
		int i = 30 % ((b - -79) / 35);
		method941(91);
		file.close();
	}
	
	final void method955(int i, byte[] bs, int i_32_, int i_33_) throws IOException {
		try {
			if (i_33_ + curOffset > file_size) {
				file_size = curOffset + i_33_;
			}
			if (aLong437 != -1L && ((aLong437 ^ 0xffffffffffffffffL) < (curOffset ^ 0xffffffffffffffffL) || (aLong437 + anInt449 ^ 0xffffffffffffffffL) > (curOffset ^ 0xffffffffffffffffL))) {
				method941(14);
			}
			if ((aLong437 ^ 0xffffffffffffffffL) != 0L && curOffset - -(long) i_33_ > aByteArray443.length + aLong437) {
				int i_34_ = (int) (aByteArray443.length - curOffset + aLong437);
				ArrayUtils.copy(bs, i, aByteArray443, (int) (curOffset + -aLong437), i_34_);
				i_33_ -= i_34_;
				curOffset += i_34_;
				i += i_34_;
				anInt449 = aByteArray443.length;
				method941(-106);
			}
			if (aByteArray443.length < i_33_) {
				if ((curOffset ^ 0xffffffffffffffffL) != (aLong468 ^ 0xffffffffffffffffL)) {
					file.seek((byte) -29, curOffset);
					aLong468 = curOffset;
				}
				file.write(bs, i, i_33_, i_32_ ^ ~0x1);
				long l = -1L;
				if ((curOffset ^ 0xffffffffffffffffL) <= (aLong433 ^ 0xffffffffffffffffL) && (curOffset ^ 0xffffffffffffffffL) > (aLong433 - -(long) anInt447 ^ 0xffffffffffffffffL)) {
					l = curOffset;
				} else if ((aLong433 ^ 0xffffffffffffffffL) <= (curOffset ^ 0xffffffffffffffffL) && i_33_ + curOffset > aLong433) {
					l = aLong433;
				}
				aLong468 += i_33_;
				if (aLong466 < aLong468) {
					aLong466 = aLong468;
				}
				long l_35_ = -1L;
				if (i_33_ + curOffset > aLong433 && (anInt447 + aLong433 ^ 0xffffffffffffffffL) <= (i_33_ + curOffset ^ 0xffffffffffffffffL)) {
					l_35_ = i_33_ + curOffset;
				} else if (aLong433 - -(long) anInt447 > curOffset && anInt447 + aLong433 <= i_33_ + curOffset) {
					l_35_ = aLong433 - -(long) anInt447;
				}
				if (l > -1L && (l ^ 0xffffffffffffffffL) > (l_35_ ^ 0xffffffffffffffffL)) {
					int i_36_ = (int) (l_35_ + -l);
					ArrayUtils.copy(bs, (int) (-curOffset + l + i), aByteArray453, (int) (-aLong433 + l), i_36_);
				}
				curOffset += i_33_;
			} else if (i_32_ > (i_33_ ^ 0xffffffff)) {
				if (aLong437 == -1L) {
					aLong437 = curOffset;
				}
				ArrayUtils.copy(bs, i, aByteArray443, (int) (-aLong437 + curOffset), i_33_);
				curOffset += i_33_;
				if (curOffset + -aLong437 > anInt449) {
					anInt449 = (int) (-aLong437 + curOffset);
				}
			}
		} catch (IOException ioexception) {
			aLong468 = -1L;
			throw ioexception;
		}
	}
	
	SeekableFile(FileOnDisk cacheFile, int i, int length) throws IOException {
		file = cacheFile;
		file_size = aLong466 = cacheFile.get_size((byte) -85);
		curOffset = 0L;
		aByteArray443 = new byte[length];
		aByteArray453 = new byte[i];
	}
	
	static {
		anOa451 = new HashTable(4096);
	}
}
