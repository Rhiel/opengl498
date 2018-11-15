package com.jagex;
import java.awt.FontMetrics;
import java.io.EOFException;

public class FileSystem
{
	static byte[] cacheFileBuffer = new byte[520];
	public SeekableFile dataFile = null;
	public int indexID;
	static RSString aClass16_248;
	public SeekableFile indexFile = null;
	public static int anInt250;
	static RSString aClass16_251;
    public int maxSize = 65000;
	public static boolean stereo;
	static RSString aClass16_261;
	public static RSString aClass16_262;
	static Js5 aClass105_265;
	static FontMetrics fontMetrics;
	
	public static void method118(int i) {
		aClass16_261 = null;
		InputManager.mouse = null;
		aClass16_248 = null;
		LobbyWorld.country_flags_sprites = null;
		aClass105_265 = null;
		aClass16_251 = null;
		fontMetrics = null;
		aClass16_262 = null;
		FontCache.b12_full = null;
	}
	
	@Override
	public final String toString() {
		return "Cache:" + indexID;
	}
	
	public static final boolean method119(int i, int i_0_) {
		if ((i & 0x1) == 0) {
			return false;
		}
		return true;
	}

	public final boolean put(int file_id, byte[] buffer, int length, boolean exists) {
		synchronized (dataFile) {
			try {
				int i_3_;
				if (exists) {
					if (6 + file_id * 6 > indexFile.get_size(true)) {
						return false;
					}
					indexFile.seek(file_id * 6, -11320);
					indexFile.read(6, cacheFileBuffer, 11524, 0);
					i_3_ = (cacheFileBuffer[4] << 8 & 0xff) + (0xff & cacheFileBuffer[3] << 16) + (0xff & cacheFileBuffer[5]);
					if (i_3_ <= 0 || i_3_ > dataFile.get_size(true) / 520L) {
						return false;
					}
				} else {
					i_3_ = (int) ((dataFile.get_size(true) + 519L) / 520L);
					if (i_3_ == 0) {
						i_3_ = 1;
					}
				}
				cacheFileBuffer[3] = (byte) (i_3_ >> 16);
				cacheFileBuffer[4] = (byte) (i_3_ >> 8);
				cacheFileBuffer[0] = (byte) (length >> 16);
				cacheFileBuffer[5] = (byte) i_3_;
				cacheFileBuffer[2] = (byte) length;
				cacheFileBuffer[1] = (byte) (length >> 8);
				int i_4_ = 0;
				indexFile.seek(6 * file_id, -11320);
				indexFile.method955(0, cacheFileBuffer, -1, 6);
				int i_6_ = 0;
				while (i_4_ < length) {
					int i_8_ = 0;
					if (exists) {
						dataFile.seek(520 * i_3_, -11320);
						int i_9_;
						int i_10_;
						int i_11_;
						if (file_id > 65535) {
							try {
								dataFile.read(10, cacheFileBuffer, 11524, 0);
							} catch (EOFException eofexception) {
								break;
							}
							i_9_ = ((cacheFileBuffer[2] & 0xff) << 8) + ((cacheFileBuffer[0] & 0xff) << 24) + ((cacheFileBuffer[1] & 0xff) << 16) + (cacheFileBuffer[3] & 0xff);
							i_10_ = (cacheFileBuffer[5] & 0xff) + ((cacheFileBuffer[4] & 0xff) << 8);
							i_8_ = ((cacheFileBuffer[7] & 0xff) << 8) + ((cacheFileBuffer[6] & 0xff) << 16) + (cacheFileBuffer[8] & 0xff);
							i_11_ = cacheFileBuffer[9] & 0xff;
						} else {
							try {
								dataFile.read(8, cacheFileBuffer, 11524, 0);
							} catch (EOFException eofexception) {
								break;
							}
							i_9_ = (cacheFileBuffer[1] & 0xff) + ((cacheFileBuffer[0] & 0xff) << 8);
							i_10_ = ((cacheFileBuffer[2] & 0xff) << 8) + (cacheFileBuffer[3] & 0xff);
							i_8_ = (cacheFileBuffer[6] & 0xff) + ((cacheFileBuffer[5] & 0xff) << 8) + ((cacheFileBuffer[4] & 0xff) << 16);
							i_11_ = cacheFileBuffer[7] & 0xff;
						}
						if (i_9_ != file_id || i_6_ != i_10_ || indexID != i_11_) {
							return false;
						}
						if (i_8_ < 0 || dataFile.get_size(true) / 520L < i_8_) {
							return false;
						}
					}
					if (i_8_ == 0) {
						exists = false;
						i_8_ = (int) ((519L + dataFile.get_size(true)) / 520L);
						if (i_8_ == 0) {
							i_8_++;
						}
						if (i_8_ == i_3_) {
							i_8_++;
						}
					}
					if (length - i_4_ <= 512) {
						i_8_ = 0;
					}
					if (file_id > 65535) {
						cacheFileBuffer[0] = (byte) (file_id >> 24);
						cacheFileBuffer[1] = (byte) (file_id >> 16);
						cacheFileBuffer[2] = (byte) (file_id >> 8);
						cacheFileBuffer[3] = (byte) file_id;
						cacheFileBuffer[4] = (byte) (i_6_ >> 8);
						cacheFileBuffer[5] = (byte) i_6_;
						cacheFileBuffer[6] = (byte) (i_8_ >> 16);
						cacheFileBuffer[7] = (byte) (i_8_ >> 8);
						cacheFileBuffer[8] = (byte) i_8_;
						cacheFileBuffer[9] = (byte) indexID;
						dataFile.seek(520 * i_3_, -11320);
						dataFile.method955(0, cacheFileBuffer, -1, 10);
						int i_14_ = length - i_4_;
						if (i_14_ > 510) {
							i_14_ = 510;
						}
						dataFile.method955(i_4_, buffer, -1, i_14_);
						i_4_ += i_14_;
					} else {
						cacheFileBuffer[0] = (byte) (file_id >> 8);
						cacheFileBuffer[1] = (byte) file_id;
						cacheFileBuffer[2] = (byte) (i_6_ >> 8);
						cacheFileBuffer[3] = (byte) i_6_;
						cacheFileBuffer[4] = (byte) (i_8_ >> 16);
						cacheFileBuffer[5] = (byte) (i_8_ >> 8);
						cacheFileBuffer[6] = (byte) i_8_;
						cacheFileBuffer[7] = (byte) indexID;
						dataFile.seek(520 * i_3_, -11320);
						dataFile.method955(0, cacheFileBuffer, -1, 8);
						int i_15_ = length - i_4_;
						if (i_15_ > 512) {
							i_15_ = 512;
						}
						dataFile.method955(i_4_, buffer, -1, i_15_);
						i_4_ += i_15_;
					}
					i_3_ = i_8_;
					i_6_++;
				}
				return true;
			} catch (java.io.IOException ioexception) {
				return false;
			}
		}
	}
	
	public final byte[] get(byte b, int file) {
		synchronized (dataFile) {
			try {
				if (6 + 6 * file > indexFile.get_size(true)) {
					return null;
				}
				indexFile.seek(6 * file, -11320);
				indexFile.read(6, cacheFileBuffer, 11524, 0);
				int dataRead = (cacheFileBuffer[0] & 0xff) << 16 | (cacheFileBuffer[1] & 0xff) << 8 | cacheFileBuffer[2] & 0xff;
				int block = (cacheFileBuffer[3] & 0xff) << 16 | (cacheFileBuffer[4] & 0xff) << 8 | cacheFileBuffer[5] & 0xff;
				if ((dataRead ^ 0xffffffff) > -1 || (maxSize ^ 0xffffffff) > (dataRead ^ 0xffffffff)) {
					return null;
				}
				if (block <= 0 || dataFile.get_size(true) / 520L < block) {
					//System.out.println(i);
					return null;
				}
				int fileSize = 0;
				int chunk = 0;
				byte[] bs = new byte[dataRead];
				while (fileSize < dataRead) {
					if (block == 0) {
						return null;
					}
					dataFile.seek(520 * block, -11320);
					int remaining = dataRead - fileSize;
					int start;
					int currentFile;
					int currentChunk;
					int nextBlock;
					int currentIndex;
					if (file > 65535) {
						if (remaining > 510) {
							remaining = 510;
						}
						start = 10;
						dataFile.read(start + remaining, cacheFileBuffer, 11524, 0);
						currentFile = ((cacheFileBuffer[0] & 0xff) << 24) + ((cacheFileBuffer[1] & 0xff) << 16) + ((cacheFileBuffer[2] & 0xff) << 8) + (cacheFileBuffer[3] & 0xff);
						currentChunk = ((cacheFileBuffer[4] & 0xff) << 8) + (cacheFileBuffer[5] & 0xff);
						nextBlock = ((cacheFileBuffer[6] & 0xff) << 16) + ((cacheFileBuffer[7] & 0xff) << 8) + (cacheFileBuffer[8] & 0xff);
						currentIndex = cacheFileBuffer[9] & 0xff;
					} else {
						if (remaining > 512) {
							remaining = 512;
						}
						start = 8;
						dataFile.read(start + remaining, cacheFileBuffer, 11524, 0);
						currentFile = ((cacheFileBuffer[0] & 0xff) << 8) + (cacheFileBuffer[1] & 0xff) & 0xffff;
						currentChunk = ((cacheFileBuffer[2] & 0xff) << 8) + (cacheFileBuffer[3] & 0xff) & 0xffff;
						nextBlock = (cacheFileBuffer[4] & 0xff) << 16 | (cacheFileBuffer[5] & 0xff) << 8 | cacheFileBuffer[6] & 0xff;
						currentIndex = cacheFileBuffer[7] & 0xff;
					}
					if (currentFile != file || chunk != currentChunk || indexID != currentIndex) {
						return null;
					}
					if (nextBlock < 0 || (dataFile.get_size(true) / 520L ^ 0xffffffffffffffffL) > (nextBlock ^ 0xffffffffffffffffL)) {
						return null;
					}
					int rem = remaining + start;
					for (int i = start; i < rem; i++) {
						bs[fileSize++] = cacheFileBuffer[i];
					}
					chunk++;
					block = nextBlock;
				}
				return bs;
			} catch (java.io.IOException ioexception) {
				ioexception.printStackTrace();
				return null;
			}
		}
	}
	
	public final boolean put(int file_id, byte[] buffer, int length) {
		synchronized (dataFile) {
			if (length < 0 || length > maxSize) {
				throw new IllegalArgumentException();
			}
			boolean result = put(file_id, buffer, length, true);
			if (!result) {
				result = put(file_id, buffer, length, false);
			}
			return result;
		}
	}
	
	FileSystem(int indexId, SeekableFile dataFile, SeekableFile indexFile, int size) {
		this.dataFile = dataFile;
		indexID = indexId;
		maxSize = size;
		this.indexFile = indexFile;
	}
	
	static final boolean method124(int i, RSString class16) {
		if (class16 == null) {
			return false;
		}
		for (int i_25_ = 0; i_25_ < PlayerRelations.ignoreListSize; i_25_++) {
			if (class16.equalsIgnoreCase(PlayerRelations.ignoreListNames[i_25_])) {
				return true;
			}
		}
		return false;
	}
	
	static {
		aClass16_248 = RSString.createString("Benutzen");
		aClass16_262 = RSString.createString("scroll:");
		aClass16_251 = aClass16_262;
		aClass16_261 = aClass16_262;
	}
}
