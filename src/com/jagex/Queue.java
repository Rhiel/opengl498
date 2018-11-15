package com.jagex;
/* Queue - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Queue {
	static RSString aClass16_403;
	static RSString aClass16_409 = RSString.createString("Null");
	public static int anInt411;
	public Queuable head = new Queuable();
	public Queuable current;
	static RSString aClass16_413 = RSString.createString("(U1");
	static RSString aClass16_414;
	public static int anInt415;
	public static int[] chatTypes = new int[100];
	static long[] aLongArray421;

	public static final void updateHintIcons(int layout_x, int layout_y, int layout_width, int layout_height, int i, int i_5_) {
		HintIcon[] class10s = ReflectionRequest.currentHintIcons;
		for (int i_6_ = 0; (i_6_ ^ 0xffffffff) > (class10s.length ^ 0xffffffff); i_6_++) {
			HintIcon class10 = class10s[i_6_];
			if (class10 != null && class10.targetType == 2) {
				BZIPContext.method1314(layout_height >> 1, 2 * class10.height, i_5_, class10.offsetX + (class10.targetX + -MapLoader.region_aboslute_z << 7), i, -16734, layout_width >> 1, class10.offsetY + (-MapLoader.region_aboslute_x + class10.targetY << 7));
				if ((StaticMethods.anInt2989 ^ 0xffffffff) < 0 && GameClient.timer % 20 < 10) {
					StaticMethods2.hint_headicons[class10.arrowId].draw_clipped_left_anchor(StaticMethods.anInt2989 + layout_x + -12, layout_y - (-Class97.anInt1645 - -28));
				}
			}
		}
	}

	static final void method928(byte b, Entity entity, int i) {
		if ((entity.forceCommenceSpeed ^ 0xffffffff) >= (GameClient.timer ^ 0xffffffff)) {
			if (entity.forcePathSpeed >= GameClient.timer) {
				ForceMovement.handleForceMovement(entity, 106);
			} else {
				ModelNode.method855(2, entity);
			}
		} else {
			StaticMethods.method259(entity, (byte) 12);
		}
		if (entity.bound_extents_x < 128 || entity.bound_extents_z < 128 || entity.bound_extents_x >= 13184 || entity.bound_extents_z >= 13184) {
			entity.forceCommenceSpeed = 0;
			entity.bound_extents_x = entity.waypointsX[0] * 128 + entity.size * 64;
			entity.current_performing_seqid = -1;
			entity.current_spotanimid = -1;
			entity.bound_extents_z = entity.waypointsY[0] * 128 + entity.size * 64;
			entity.forcePathSpeed = 0;
			entity.method1083(-84);
		}
		if (entity == GameClient.currentPlayer && (entity.bound_extents_x < 1536 || entity.bound_extents_z < 1536 || entity.bound_extents_x >= 11776 || entity.bound_extents_z >= 11776)) {
			entity.bound_extents_z = entity.size * 64 + 128 * entity.waypointsY[0];
			entity.bound_extents_x = entity.waypointsX[0] * 128 + entity.size * 64;
			entity.current_spotanimid = -1;
			entity.forcePathSpeed = 0;
			entity.current_performing_seqid = -1;
			entity.forceCommenceSpeed = 0;
			entity.method1083(-82);
		}
		Class56.method1184((byte) -77, entity);
		Js5.process_entity_animation(1, entity);
	}

	public final Queuable remove() {
		Queuable n = head.queue_next;
		if (head == n) {
			return null;
		}
		n.unlink_queue();
		return n;
	}

	public final void add_last(Queuable n) {
		if (n.queue_prev != null) {
			n.unlink_queue();
		}
		n.queue_prev = head.queue_prev;
		n.queue_next = head;
		n.queue_prev.queue_next = n;
		n.queue_next.queue_prev = n;
	}

	public static void main(String args[]) {// 1376
		System.out.println(0 + (true ? 0 : 512) + (false ? 0 : 1024) + (false ? 0 : 2048) + (false ? 0 : 4096));
	}

	public final Queuable get_first() {
		Queuable class23_sub13 = head.queue_next;
		if (head == class23_sub13) {
			current = null;
			return null;
		}
		current = class23_sub13.queue_next;
		return class23_sub13;
	}

	public final Queuable get_next() {
		Queuable queueNode = current;
		if (head == queueNode) {
			current = null;
			return null;
		}
		current = queueNode.queue_next;
		return queueNode;
	}

	public final int size() {
		int count = 0;
		Queuable node = head.queue_next;
		while (head != node) {
			node = node.queue_next;
			count++;
		}
		return count;
	}

	final void insert(int i, Queuable class23_sub13) {
		if (class23_sub13.queue_prev != null) {
			class23_sub13.unlink_queue();
		}
		class23_sub13.queue_next = head.queue_next;
		class23_sub13.queue_prev = head;
		class23_sub13.queue_prev.queue_next = class23_sub13;
		class23_sub13.queue_next.queue_prev = class23_sub13;
	}

	public static void method935(int i) {
		chatTypes = null;
		aClass16_409 = null;
		aClass16_413 = null;
		aClass16_414 = null;
		aLongArray421 = null;
		if (i == 0) {
			aClass16_403 = null;
		}
	}

	public Queue() {
		head.queue_prev = head;
		head.queue_next = head;
	}

	static final int getIdleKeyTicks(boolean bool) {
		if (bool != true) {
			MapLoader.decode_client_side(null, null, -126, -113, 64, true, 49, -2);
		}
		return Class79.idleKeyTicks;
	}

	static {
		GameShell.setKillTime(0L);
		aClass16_414 = RSString.createString("(Z");
		aClass16_403 = RSString.createString("::fpsoff");
		anInt415 = 500;
		aLongArray421 = new long[32];
	}
}
