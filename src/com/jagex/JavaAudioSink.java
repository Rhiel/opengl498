package com.jagex;
/* Class6_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.SourceDataLine;

import com.jagex.core.tools.MathTools;

public class JavaAudioSink extends Stereo
{
	public AudioFormat format;
	public byte[] buffer;
	public boolean using_soundmax_card = false;
	public int bufferSize;
	public SourceDataLine line;
	@SuppressWarnings("rawtypes")
	static Class lineClass;
	
	@Override
	final int get_buffer_used_size() {
		return bufferSize - (line.available() >> (!FileSystem.stereo ? 1 : 2));
	}
	
	@Override
	final void close_line() {
		if (null != line) {
			line.close();
			line = null;
		}
	}
	
	@Override
	final void initialize(Component component) {
		Mixer.Info[] infos = AudioSystem.getMixerInfo();
		if (infos != null) {
			Mixer.Info[] inf = infos;
			for (Info info : inf) {
				if (null != info) {
					String string = info.getName();
					if (null != string && (string.toLowerCase().indexOf("soundmax") ^ 0xffffffff) <= -1) {
						using_soundmax_card = true;
					}
				}
			}
		}
		format = new AudioFormat(Keyboard.sampleRate, 16, FileSystem.stereo ? 2 : 1, true, false);
		buffer = new byte[256 << (FileSystem.stereo ? 2 : 1)];
	}
	
	@Override
	final void open_line(int size) throws LineUnavailableException {
		try {
			DataLine.Info info = new DataLine.Info(lineClass == null ? lineClass = getClass("javax.sound.sampled.SourceDataLine") : lineClass, format, size << (FileSystem.stereo ? 2 : 1));
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open();
			line.start();
			bufferSize = size;
		} catch (LineUnavailableException lineunavailableexception) {
			if (Class98.method1492((byte) 40, size) != 1) {
				open_line(MathTools.get_greater_pow2(size));
			} else {
				line = null;
				throw lineunavailableexception;
			}
		}
	}
	
	@Override
	final void flush_line() throws LineUnavailableException {
		line.flush();
		if (using_soundmax_card) {
			line.close();
			line = null;
			DataLine.Info info = new DataLine.Info(lineClass == null ? lineClass = getClass("javax.sound.sampled.SourceDataLine") : lineClass, format, bufferSize << (!FileSystem.stereo ? 1 : 2));
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open();
			line.start();
		}
	}
	
	JavaAudioSink() {
		/* empty */
	}
	
	@Override
	final void copy_buffers() {
		int i = 256;
		if (FileSystem.stereo) {
			i <<= 1;
		}
		for (int write_ptr = 0; write_ptr < i; write_ptr++) {
			int sample = samples[write_ptr];
			if ((sample + 8388608 & ~0xffffff) != 0) {
				sample = 0x7fffff ^ sample >> 31;
			}
			buffer[write_ptr * 2] = (byte) (sample >> 8);
			buffer[write_ptr * 2 + 1] = (byte) (sample >> 16);
		}
		line.write(buffer, 0, i << 1);
	}
	
	@SuppressWarnings("rawtypes")
	static Class getClass(String string) {
		Class var_class;
		try {
			var_class = Class.forName(string);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw (NoClassDefFoundError) new NoClassDefFoundError("Class " + string + " wasn't found!").initCause(classnotfoundexception);
		}
		return var_class;
	}
}
