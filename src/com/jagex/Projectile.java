package com.jagex;

import com.jagex.graphics.runetek4.media.Model;

public class Projectile extends SceneNode {
	public double aDouble2568;
	public int startX;
	public double aDouble2570;
	public int tick = 0;
	public double aDouble2573;
	public int angle;
	public double aDouble2575;
	public double aDouble2577;
	public int current_frameid = 0;
	public int next_frameid = -1;
	public int delay;
	public int startHeight;
	public int targetIndex;
	public int distance;
	public int speed;
	public int anInt2924;
	public double aDouble2590;
	public double aDouble2592;
	public int endHeight;
	public SeqType animation;
	public double aDouble2595;
	public int anInt2917;
	public int spotid;
	public boolean aBoolean2599;
	public int startY;
	public int anInt2912 = -32768;
	public int plane;

	Projectile(int id, int plane, int startX, int startY, int startHeight, int delay, int speed, int angle, int distance, int targetIndex, int endHeight) {
		aBoolean2599 = false;
		aBoolean2599 = false;
		this.angle = angle;
		this.startY = startY;
		spotid = id;
		this.plane = plane;
		this.startHeight = startHeight;
		this.targetIndex = targetIndex;
		this.delay = delay;
		this.startX = startX;
		this.distance = distance;
		this.speed = speed;
		this.endHeight = endHeight;
		int animId = SpotType.list(spotid).seqid;
		if (animId != -1) {
			animation = SeqTypeList.list(animId);
		} else {
			animation = null;
		}
	}

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {
		// NOOP
	}

	@Override
	public void draw2(int i, int i_10_, int i_11_, int i_12_, int i_13_, int i_14_, int i_15_, int i_16_, long l, int bufferOffset) {
		Model class38_sub1 = method1070();
		if (class38_sub1 != null) {
			class38_sub1.draw2(i, i_10_, i_11_, i_12_, i_13_, i_14_, i_15_, i_16_, l, bufferOffset);
			anInt2912 = class38_sub1.get_miny();
		}
	}

	public final void method1072(int i, int i_19_) {
		aDouble2575 += i * aDouble2590;
		aDouble2573 += aDouble2592 * i + i * (aDouble2568 * 0.5) * i;
		aBoolean2599 = true;
		aDouble2592 += aDouble2568 * i;
		aDouble2570 += aDouble2577 * i;
		anInt2924 = (int) (Math.atan2(aDouble2577, aDouble2590) * 325.949) + 1024 & 0x7ff;
		anInt2917 = (int) (Math.atan2(aDouble2592, aDouble2595) * 325.949) & 0x7ff;
		if (i_19_ == 12891) {
			if (animation != null) {
				tick += i;
				while (tick > animation.frames_durations[current_frameid]) {
					tick -= animation.frames_durations[current_frameid];
					current_frameid++;
					if (animation.frames_data.length <= current_frameid) {
						current_frameid -= animation.replay_interval;
						if ((current_frameid ^ 0xffffffff) > -1 || (animation.frames_data.length ^ 0xffffffff) >= (current_frameid ^ 0xffffffff)) {
							current_frameid = 0;
						}
					}
					next_frameid = current_frameid + 1;
					if (animation.frames_data.length <= next_frameid) {
						next_frameid -= animation.replay_interval;
						if (-1 < ~next_frameid || ~next_frameid <= ~animation.frames_data.length) {
							next_frameid = -1;
						}
					}
				}
			}
		}
	}

	public final void method1069(int i, int i_4_, int i_5_, int i_6_, int i_7_) {
		if (!aBoolean2599) {
			double d = -startY + i_4_;
			double d_8_ = i_5_ + -startX;
			double d_9_ = Math.sqrt(d * d + d_8_ * d_8_);
			aDouble2573 = startHeight;
			aDouble2570 = startX + d_8_ * distance / d_9_;
			aDouble2575 = distance * d / d_9_ + startY;
		}
		double d = -i + 1 + speed;
		aDouble2577 = (-aDouble2570 + i_5_) / d;
		aDouble2590 = (i_4_ - aDouble2575) / d;
		aDouble2595 = Math.sqrt(aDouble2577 * aDouble2577 + aDouble2590 * aDouble2590);
		if (!aBoolean2599) {
			aDouble2592 = -aDouble2595 * Math.tan(0.02454369 * angle);
		}
		aDouble2568 = (-(d * aDouble2592) + (-aDouble2573 + i_6_)) * 2.0 / (d * d);
	}

	public final Model method1070() {
		SpotType def = SpotType.list(spotid);
		Model class38_sub1 = def.get_model(current_frameid, next_frameid, tick);
		if (class38_sub1 == null) {
			return null;
		}
		class38_sub1.xaxis_rotate_without_normals(anInt2917);
		return class38_sub1;
	}

	@Override
	public final int get_miny() {
		return anInt2912;
	}

}
