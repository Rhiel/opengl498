package com.jagex.launcher;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;

/**
 * Holds client related properties.
 * @author Emperor
 *
 */
public final class Properties {

	/**
	 * If the modern hotkey layout should be used.
	 */
	public boolean modernHotkeys;
	
	/**
	 * If the theme song should be played.
	 */
	public boolean playThemeSong = true;
	
	/**
	 * If we have notepad wrapping.
	 */
	public boolean notepadWrap = true;
	
	/**
	 * The properties instance.
	 */
	public static Properties singleton;
	
	/**
	 * If we should remember the player.
	 */
	public boolean rememberMe;
	
	/**
	 * Constructs a new {@code Properties} {@code Object}.
	 */
	public Properties() {
		/*
		 * empty.
		 */
	}
	
	/**
	 * Loads the properties.
	 * @param file The file.
	 */
	public void load(File file) {
		try {
			RandomAccessFile f = new RandomAccessFile(file, "rw");
			ByteBuffer buffer = f.getChannel().map(MapMode.READ_WRITE, 0, f.length());
			int opcode;
			while ((opcode = buffer.get() & 0xFF) != 0) {
				switch (opcode) {
				case 1:
					modernHotkeys = true;
					break;
				case 2:
					playThemeSong = false;
					break;
				case 3:
					notepadWrap = false;
					break;
				case 4:
					rememberMe = true;
					break;
				}
			}
			f.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the properties.
	 */
	public void save() {
		ByteBuffer buffer = ByteBuffer.allocate(512);
		if (modernHotkeys) {
			buffer.put((byte) 1);
		}
		if (!playThemeSong) {
			buffer.put((byte) 2);
		}
		if (!notepadWrap) {
			buffer.put((byte) 3);
		}
		if (rememberMe) {
			buffer.put((byte) 4);
		}
		buffer.put((byte) 0);//EOF
		buffer.flip();
		try {
			RandomAccessFile f = new RandomAccessFile(new File(Configurations.getCachePath() + "/client.props"), "rw");
			f.getChannel().write(buffer);
			f.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the instance.
	 * @return The instance.
	 */
	public static Properties get() {
		if (singleton == null) {
			singleton = new Properties();
			File file = new File(Configurations.getCachePath() + "/client.props");
			if (file.exists()) {
				singleton.load(file);
			}
		}
		return singleton;
	}

	/**
	 * @return the modernHotkeys
	 */
	public boolean isModernHotkeys() {
		return modernHotkeys;
	}

	/**
	 * @param modernHotkeys the modernHotkeys to set
	 */
	public void setModernHotkeys(boolean modernHotkeys) {
		this.modernHotkeys = modernHotkeys;
	}

	/**
	 * @return the playThemeSong
	 */
	public boolean isPlayThemeSong() {
		return playThemeSong;
	}

	/**
	 * @param playThemeSong the playThemeSong to set
	 */
	public void setPlayThemeSong(boolean playThemeSong) {
		this.playThemeSong = playThemeSong;
	}

	/**
	 * Gets the notepadWrap.
	 * @return the notepadWrap.
	 */
	public boolean isNotepadWrap() {
		return notepadWrap;
	}

	/**
	 * Sets the notepadWrap.
	 * @param notepadWrap the notepadWrap to set
	 */
	public void setNotepadWrap(boolean notepadWrap) {
		this.notepadWrap = notepadWrap;
	}

	/**
	 * Gets the rememberMe.
	 * @return the rememberMe.
	 */
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * Sets the rememberMe.
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
}