package com.jagex.core.natives;

/**
 * @author Walied K. Yassen
 */
public interface NativeManager {

	boolean load(String library);

	boolean isReady(String library);

	boolean isPresent(String library);

	boolean destroy();

	int getProgress(String library);
}
