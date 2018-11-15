package com.jagex.core.natives;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.jagex.GameClient;
import com.jagex.Js5;
import com.jagex.core.tools.FileTools;

public class Js5NativeManager implements NativeManager {

	public Js5 nativeJs5;
	public String nativeJs5Path;
	public Hashtable<String, File> fileTable = new Hashtable<>();
	public Hashtable<String, Class<?>> classTable = new Hashtable<>();

	public Js5NativeManager(Js5 js5) {
		nativeJs5 = js5;
		String dirName = "";
		if (GameClient.os_name.startsWith("win") || GameClient.os_name.startsWith("windows 7")) {
			dirName += "windows/";
		} else if (GameClient.os_name.startsWith("linux")) {
			dirName += "linux/";
		} else if (GameClient.os_name.startsWith("mac")) {
			dirName += "macos/";
		}
		if (GameClient.os_arch.startsWith("amd64") || GameClient.os_arch.startsWith("x86_64")) {
			dirName += "x86_64/";
		} else if (GameClient.os_arch.startsWith("i386") || GameClient.os_arch.startsWith("i486") || GameClient.os_arch.startsWith("i586") || GameClient.os_arch.startsWith("x86")) {
			dirName += "x86/";
		} else if (GameClient.os_arch.startsWith("ppc")) {
			dirName += "ppc/";
		} else {
			dirName += "universal/";
		}
		nativeJs5Path = dirName;
	}

	@Override
	public boolean load(String string) {
		return load(string, NativeLibrary.class);
	}

	public boolean load(String libName, Class<?> representer) {
		Class<?> clazz = classTable.get(libName);
		if (clazz != null) {
			if (clazz.getClassLoader() != representer.getClassLoader()) {
				return false;
			}
			return true;
		}
		File file = null;
		if (null == file) {
			file = fileTable.get(libName);
		}

		do {
			if (file != null) {
				boolean successed;
				try {
					file = new File(file.getCanonicalPath());
					Class<?> runtimeClass = Class.forName("java.lang.Runtime");
					Class<?> accessClass = Class.forName("java.lang.reflect.AccessibleObject");
					Method accessMethod = accessClass.getDeclaredMethod("setAccessible", new Class[] { Boolean.TYPE });
					Method loadMethod = runtimeClass.getDeclaredMethod("load0", new Class[] { Class.forName("java.lang.Class"), Class.forName("java.lang.String") });
					accessMethod.invoke(loadMethod, new Object[] { Boolean.TRUE });
					loadMethod.invoke(Runtime.getRuntime(), new Object[] { representer, file.getPath() });
					accessMethod.invoke(loadMethod, new Object[] { Boolean.FALSE });
					classTable.put(libName, representer);
					successed = true;
				} catch (NoSuchMethodException nosuchmethodexception) {
					nosuchmethodexception.printStackTrace();
					System.load(file.getPath());
					classTable.put(libName, NativeLibrary.class);
					return true;
				} catch (Throwable throwable) {
					throwable.printStackTrace();
					break;
				}
				return successed;
			}
		} while (false);
		return false;
	}

	@Override
	public int getProgress(String lib) {
		if (fileTable.containsKey(lib)) {
			return 100;
		}
		String libName = Js5NativeManager.getFileName(lib);
		if (null == libName) {
			return -1;
		}
		String libPath = null;
		if (libPath == null) {
			libPath = nativeJs5Path + libName;
			if (!nativeJs5.is_valid_file(libPath, "")) {
				return -1;
			}
		}
		if (!nativeJs5.is_group_cached(libPath)) {
			return nativeJs5.get_group_progress(libPath);
		}
		byte[] cacheBytes = nativeJs5.get_file(libPath, "");
		File cacheNativeFile;
		try {
			cacheNativeFile = GameClient.get_cache_file(libName);
		} catch (RuntimeException runtimeexception) {
			return -1;
		}
		if (null != cacheBytes && null != cacheNativeFile) {
			boolean bytesMatch = true;
			byte[] fileBytes = FileTools.getBytes(cacheNativeFile);
			if (null != fileBytes && cacheBytes.length == fileBytes.length) {
				for (int index = 0; index < fileBytes.length; index++) {
					if (cacheBytes[index] != fileBytes[index]) {
						bytesMatch = false;
						break;
					}
				}
			} else {
				bytesMatch = false;
			}
			try {
				if (!bytesMatch) {
					try {
						FileOutputStream stream = new FileOutputStream(cacheNativeFile);
						stream.write(cacheBytes, 0, cacheBytes.length);
						stream.close();
					} catch (IOException ioexception) {
						throw new RuntimeException();
					}
				}
			} catch (Throwable throwable) {
				return -1;
			}
			storeNativeFile(lib, cacheNativeFile);
			return 100;
		}
		return -1;
	}

	public void storeNativeFile(String libName, File cacheFile) {
		fileTable.put(libName, cacheFile);
	}

	@Override
	public boolean isPresent(String string) {
		return fileTable.containsKey(string);
	}

	@Override
	public boolean isReady(String name) {
		return classTable.containsKey(name);

	}

	@Override
	public boolean destroy() {
		Hashtable<String, Class<?>> removeTable = new Hashtable<>();
		Enumeration<String> enumeration = classTable.keys();
		while (enumeration.hasMoreElements()) {
			String string = enumeration.nextElement();
			removeTable.put(string, classTable.get(string));
		}
		try {
			Class<?> accessClass = Class.forName("java.lang.reflect.AccessibleObject");
			Class<?> loaderClass = Class.forName("java.lang.ClassLoader");
			Field librariesField = loaderClass.getDeclaredField("nativeLibraries");
			Method accessMethod = accessClass.getDeclaredMethod("setAccessible", new Class[] { Boolean.TYPE });
			accessMethod.invoke(librariesField, new Object[] { Boolean.TRUE });
			try {
				enumeration = classTable.keys();
				while (enumeration.hasMoreElements()) {
					String myName = enumeration.nextElement();
					try {
						File file = fileTable.get(myName);
						Class<?> representer = classTable.get(myName);
						Vector<?> vector = (Vector<?>) librariesField.get(representer.getClassLoader());
						for (int index = 0; index < vector.size(); index++) {
							try {
								Object nativeClass = vector.elementAt(index);
								Field nameField = nativeClass.getClass().getDeclaredField("name");
								accessMethod.invoke(nameField, new Object[] { Boolean.TRUE });
								try {
									String name = (String) nameField.get(nativeClass);
									if (null != name && name.equalsIgnoreCase(file.getCanonicalPath())) {
										Field handleField = nativeClass.getClass().getDeclaredField("handle");
										Method finalizeMethod = nativeClass.getClass().getDeclaredMethod("finalize", new Class[0]);
										accessMethod.invoke(handleField, new Object[] { Boolean.TRUE });
										accessMethod.invoke(finalizeMethod, new Object[] { Boolean.TRUE });
										try {
											finalizeMethod.invoke(nativeClass, new Object[0]);
											handleField.set(nativeClass, new Integer(0));
											removeTable.remove(myName);
										} catch (Throwable throwable) {
										}
										accessMethod.invoke(finalizeMethod, new Object[] { Boolean.FALSE });
										accessMethod.invoke(handleField, new Object[] { Boolean.FALSE });
									}
								} catch (Throwable throwable) {
								}
								accessMethod.invoke(nameField, new Object[] { Boolean.FALSE });
							} catch (Throwable throwable) {
							}
						}
					} catch (Throwable throwable) {
					}
				}
			} catch (Throwable throwable) {
			}
			accessMethod.invoke(librariesField, new Object[] { Boolean.FALSE });
		} catch (Throwable throwable) {
		}
		classTable = removeTable;
		return classTable.isEmpty();
	}

	public static String getFileName(String baseName) {
		if (GameClient.os_name.startsWith("win")) {
			return baseName + ".dll";
		}
		if (GameClient.os_name.startsWith("linux")) {
			return "lib" + baseName + ".so";
		}
		if (GameClient.os_name.startsWith("mac")) {
			return "lib" + baseName + ".dylib";
		}
		return null;
	}
}
