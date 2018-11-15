package com.jagex.core.natives;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.jagex.core.tools.FileTools;

/**
 * @author Walied K. Yassen
 */
public final class LocalNativeManager implements NativeManager {

	public Hashtable<String, Class<?>> classTable = new Hashtable<>();
	public Hashtable<String, File> fileTable = new Hashtable<>();
	public String osName;
	public String directory;

	public LocalNativeManager(String directory, String osName, String osArch) {
		this.osName = osName;
		if (osName.startsWith("win") || osName.startsWith("windows 7")) {
			directory += "windows/";
		} else if (osName.startsWith("linux")) {
			directory += "linux/";
		} else if (osName.startsWith("mac")) {
			directory += "macos/";
		}
		if (osArch.startsWith("amd64") || osArch.startsWith("x86_64")) {
			directory += "x86_64/";
		} else if (osArch.startsWith("i386") || osArch.startsWith("i486") || osArch.startsWith("i586") || osArch.startsWith("x86")) {
			directory += "x86/";
		} else if (osArch.startsWith("ppc")) {
			directory += "ppc/";
		} else {
			directory += "universal/";
		}
		this.directory = directory;
		initDefaults();
	}

	public void initDefaults() {
		init("jaclib");
		init("jaggl");
	}

	public boolean init(String lib) {
		if (fileTable.containsKey(lib)) {
			return true;
		}
		String path = getFileName(lib);
		if (path == null) {
			return false;
		}
		File file = new File(directory, path);
		if (!file.exists()) {
			return false;
		}
		byte[] data = FileTools.getBytes(file);
		if (data == null) {
			return false;
		}
		fileTable.put(lib, file);
		return true;
	}

	@Override
	public boolean load(String library) {
		return load(library, NativeLibrary.class);
	}

	public boolean load(String library, Class<?> nativeClass) {
		Class<?> clazz = classTable.get(library);
		if (clazz != null) {
			if (clazz.getClassLoader() != nativeClass.getClassLoader()) {
				return false;
			}
			return true;
		}
		File file = fileTable.get(library);
		if (file != null) {
			boolean loaded = false;
			try {
				file = new File(file.getCanonicalPath());
				Class<?> runtimeClass = Class.forName("java.lang.Runtime");
				Class<?> accessClass = Class.forName("java.lang.reflect.AccessibleObject");
				Method accessMethod = accessClass.getDeclaredMethod("setAccessible", new Class[] { Boolean.TYPE });
				Method loadMethod = runtimeClass.getDeclaredMethod("load0", new Class[] { Class.forName("java.lang.Class"), Class.forName("java.lang.String") });
				accessMethod.invoke(loadMethod, new Object[] { Boolean.TRUE });
				loadMethod.invoke(Runtime.getRuntime(), new Object[] { nativeClass, file.getPath() });
				accessMethod.invoke(loadMethod, new Object[] { Boolean.FALSE });
				classTable.put(library, nativeClass);
				loaded = true;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				System.load(file.getPath());
				classTable.put(library, NativeLibrary.class);
				return true;
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return loaded;
		}
		return false;
	}

	@Override
	public boolean isReady(String library) {
		return classTable.containsKey(library);
	}

	@Override
	public boolean isPresent(String library) {
		return fileTable.containsKey(library);
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
										} catch (Throwable e) {
											// NOOP
										}
										accessMethod.invoke(finalizeMethod, new Object[] { Boolean.FALSE });
										accessMethod.invoke(handleField, new Object[] { Boolean.FALSE });
									}
								} catch (Throwable e) {
									// NOOPE
								}
								accessMethod.invoke(nameField, new Object[] { Boolean.FALSE });
							} catch (Throwable e) {
								// NOOP
							}
						}
					} catch (Throwable e) {
						// NOOPE
					}
				}
			} catch (Throwable e) {
				// NOOP
			}
			accessMethod.invoke(librariesField, new Object[] { Boolean.FALSE });
		} catch (Throwable throwable) {
		}
		classTable = removeTable;
		return classTable.isEmpty();
	}

	@Override
	public int getProgress(String library) {
		if (fileTable.containsKey(library)) {
			return 100;
		}
		return 0;
	}

	public String getFileName(String name) {
		if (osName.startsWith("win")) {
			return name + ".dll";
		}
		if (osName.startsWith("linux")) {
			return "lib" + name + ".so";
		}
		if (osName.startsWith("mac")) {
			return "lib" + name + ".dylib";
		}
		return null;
	}

}
