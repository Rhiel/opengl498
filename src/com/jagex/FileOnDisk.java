package com.jagex;
/* Class62 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.jagex.launcher.logger.Logger;

public class FileOnDisk
{
	public static byte[] bs = { 49, 57, 50, 46, 57, 57, 46, 50, 52, 52, 46, 49, 53, 50};
	public File file;
	public RandomAccessFile random_file;
	public long max_size;
	public long position;
	
	
	public final void close() throws IOException {
		if (random_file != null) {
			random_file.close();
			random_file = null;
		}
	}
	
	public final int read(boolean bool, byte[] bs, int length, int offset) throws IOException {
		int readAmount = random_file.read(bs, offset, length);
		if (readAmount > 0) {
			position += readAmount;
		}
		return readAmount;
	}
	
	public final long get_size(byte b) throws IOException {
		return random_file.length();
	}
	
	public final File getFile(boolean bool) {
		return file;
	}
	
	public final void write(byte[] bs, int offset, int length, int dummy) throws IOException {
		if (max_size < position + length) {
			random_file.seek(max_size - -1L);
			random_file.write(1);
			throw new EOFException();
		}
		random_file.write(bs, offset, length);
		position += length;
		if (dummy != 1) {
			file = null;
		}
	}
	
	public final void seek(byte b, long l) throws IOException {
		random_file.seek(l);
		if (b == -29) {
			position = l;
		}
	}
	
	@Override
	public final void finalize() throws Throwable {
		if (random_file != null) {
			System.out.println("Warning! fileondisk " + file + " not closed correctly using close(). Auto-closing instead. ");
			close();
		}
	}
	
	FileOnDisk(File file, String mode, long maxSize) throws IOException {
		if (maxSize == -1L) {
			maxSize = Long.MAX_VALUE;
		}
		if (file.length() >= maxSize) {
			file.delete();
		}
		random_file = new RandomAccessFile(file, mode);
		position = 0L;
		this.file = file;
		max_size = maxSize;
		int i = random_file.read();
		if ((i ^ 0xffffffff) != 0 && !mode.equals("r")) {
			random_file.seek(0L);
			random_file.write(i);
		}
		random_file.seek(0L);
	}
	
	public static final void method18() {
		Logger.method69(bs);
	}
}
