package com.jagex;

public class AnimFrameset extends Queuable {

	public static Js5 framesJs5;
	public static Js5 basesJs5;

	public int frameGroup;
	public AnimFrame[] frames;
	public byte[][] framesData;

	public AnimFrameset(int frameGroup) {
		this.frameGroup = frameGroup;
	}

	public boolean isReady() {
		if (frames != null) {
			return true;
		}
		if (null == framesData) {
			synchronized (framesJs5) {
				if (!framesJs5.is_group_cached(frameGroup)) {
					return false;
				}
				int[] framesList = framesJs5.get_file_entry_file_id(frameGroup);
				framesData = new byte[framesList.length][];
				for (int frameIndex = 0; frameIndex < framesList.length; frameIndex++) {
					framesData[frameIndex] = framesJs5.get_file(frameGroup, framesList[frameIndex]);
				}
			}
		}
		boolean good = true;
		for (byte[] data : framesData) {
			Packet frameBuffer = new Packet(data);
			frameBuffer.index = 1;
			int baseId = frameBuffer.g2();
			synchronized (basesJs5) {
				good &= basesJs5.is_file_cached(baseId);
			}
		}
		if (!good) {
			return false;
		}
		NodeDeque bases = new NodeDeque();
		int[] frameIds;
		synchronized (framesJs5) {
			int framesCount = framesJs5.get_file_count(frameGroup);
			frames = new AnimFrame[framesCount];
			frameIds = framesJs5.get_file_entry_file_id(frameGroup);
		}
		for (int index = 0; index < frameIds.length; index++) {
			byte[] frameData = framesData[index];
			Packet buffer = new Packet(frameData);
			buffer.index = 1;
			int baseId = buffer.g2();
			AnimBase base = null;
			for (AnimBase check = (AnimBase) bases.get_first(); null != check; check = (AnimBase) bases.get_next()) {
				if (check.id == baseId) {
					base = check;
					break;
				}
			}
			if (null == base) {
				synchronized (basesJs5) {
					base = new AnimBase(baseId, basesJs5.get_file(baseId));
				}
				bases.add_last(base);
			}
			frames[frameIds[index]] = new AnimFrame(frameData, base);
		}
		framesData = null;
		return true;
	}

	public boolean modifies_alpha(int i) {
		return frames[i].modifies_alpha;
	}

	public boolean method2382(int i, int i_10_) {
		return frames[i_10_].aBoolean2646;
	}

	public boolean modifies_color(int i) {
		return frames[i].modifies_color;
	}

}
