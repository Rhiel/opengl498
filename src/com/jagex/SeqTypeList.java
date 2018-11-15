package com.jagex;

/**
 * @author Walied K. Yassen
 */
public class SeqTypeList {

	public static final boolean force_tweening = false;

	public static final MemoryCache typesCache = new MemoryCache(64);
	public static final MemoryCache frame_sets_cache = new MemoryCache(100);
	public static Js5 seqsJs5;


	public static void initialise(Js5 seqsJs5, Js5 basesJs5, Js5 framesJs5) {
		SeqTypeList.seqsJs5 = seqsJs5;
		AnimFrameset.basesJs5 = basesJs5;
		AnimFrameset.framesJs5 = framesJs5;
	}

	public static SeqType list(int id) {
		SeqType type = (SeqType) typesCache.get(id);
		if (type != null) {
			return type;
		}
		byte[] data = seqsJs5.get_file(getGroupId(id), getFileId(id));
		type = new SeqType(id);
		if (data != null) {
			type.decode(new Packet(data));
		} else {
			DebugMissing.notify_sequence(id);
		}
		type.postDecode();
		typesCache.put(id, type);
		return type;
	}

	public static final AnimFrameset load_frameset(int frameGroup) {
		AnimFrameset frames;
		synchronized (frame_sets_cache) {
			frames = (AnimFrameset) frame_sets_cache.get(frameGroup);
			if (frames == null) {
				frames = new AnimFrameset(frameGroup);
				frame_sets_cache.put(frameGroup, frames);
			}
			if (!frames.isReady()) {
				return null;
			}
		}
		return frames;
	}

	public static final void method1244(int i) {
		typesCache.clear();
		frame_sets_cache.clear();
	}

	public static int getGroupId(int i) {
		return i >>> 7;
	}

	public static int getFileId(int id) {
		return id & 0x7f;
	}

}
