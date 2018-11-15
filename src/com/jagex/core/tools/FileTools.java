package com.jagex.core.tools;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTools {

	public static byte[] getBytes(File file) {
		return getBytes(file, (int) file.length());
	}

	public static byte[] getBytes(File file, int length) {
		byte[] data;
		try {
			byte[] readData = new byte[length];
			getBytes(file, readData, length);
			data = readData;
		} catch (IOException ioexception) {
			return null;
		}
		return data;
	}

	public static void getBytes(File file, byte[] data, int length) throws IOException {
		DataInputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		try {
			stream.readFully(data, 0, length);
		} catch (EOFException e) {
			// NOOP
		}
		stream.close();
	}

}
