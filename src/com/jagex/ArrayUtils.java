package com.jagex;
/* ArrayUtils - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class ArrayUtils {
	public static final void arrayset(int[] is, int i, int i_0_, int i_1_) {
		i_0_ = i + i_0_ - 7;
		while (i < i_0_) {
			is[i++] = i_1_;
			is[i++] = i_1_;
			is[i++] = i_1_;
			is[i++] = i_1_;
			is[i++] = i_1_;
			is[i++] = i_1_;
			is[i++] = i_1_;
			is[i++] = i_1_;
		}
		i_0_ += 7;
		while (i < i_0_) {
			is[i++] = i_1_;
		}
	}

	static final void clear_array(int[] is, int i, int i_2_) {
		i_2_ = i + i_2_ - 7;
		while (i < i_2_) {
			is[i++] = 0;
			is[i++] = 0;
			is[i++] = 0;
			is[i++] = 0;
			is[i++] = 0;
			is[i++] = 0;
			is[i++] = 0;
			is[i++] = 0;
		}
		i_2_ += 7;
		while (i < i_2_) {
			is[i++] = 0;
		}
	}

	public static void arraycopy(Object[] src, int src_ptr, Object[] dst, int dst_ptr, int length) {
		if (src == dst) {
			if (src_ptr == dst_ptr) {
				return;
			}
			if (dst_ptr > src_ptr && dst_ptr < src_ptr + length) {
				length--;
				src_ptr += length;
				dst_ptr += length;
				length = src_ptr - length;
				length += 7;
				while (src_ptr >= length) {
					dst[dst_ptr--] = src[src_ptr--];
					dst[dst_ptr--] = src[src_ptr--];
					dst[dst_ptr--] = src[src_ptr--];
					dst[dst_ptr--] = src[src_ptr--];
					dst[dst_ptr--] = src[src_ptr--];
					dst[dst_ptr--] = src[src_ptr--];
					dst[dst_ptr--] = src[src_ptr--];
					dst[dst_ptr--] = src[src_ptr--];
				}
				length -= 7;
				while (src_ptr >= length) {
					dst[dst_ptr--] = src[src_ptr--];
				}
				return;
			}
		}
		length += src_ptr;
		length -= 7;
		while (src_ptr < length) {
			dst[dst_ptr++] = src[src_ptr++];
			dst[dst_ptr++] = src[src_ptr++];
			dst[dst_ptr++] = src[src_ptr++];
			dst[dst_ptr++] = src[src_ptr++];
			dst[dst_ptr++] = src[src_ptr++];
			dst[dst_ptr++] = src[src_ptr++];
			dst[dst_ptr++] = src[src_ptr++];
			dst[dst_ptr++] = src[src_ptr++];
		}
		length += 7;
		while (src_ptr < length) {
			dst[dst_ptr++] = src[src_ptr++];
		}
	}

	public static final void arraycopy_int(int[] source, int src_ptr, int[] dst, int dst_ptr, int length) {
		if (source == dst) {
			if (src_ptr == dst_ptr) {
				return;
			}
			if (dst_ptr > src_ptr && dst_ptr < src_ptr + length) {
				length--;
				src_ptr += length;
				dst_ptr += length;
				length = src_ptr - length;
				length += 7;
				while (src_ptr >= length) {
					dst[dst_ptr--] = source[src_ptr--];
					dst[dst_ptr--] = source[src_ptr--];
					dst[dst_ptr--] = source[src_ptr--];
					dst[dst_ptr--] = source[src_ptr--];
					dst[dst_ptr--] = source[src_ptr--];
					dst[dst_ptr--] = source[src_ptr--];
					dst[dst_ptr--] = source[src_ptr--];
					dst[dst_ptr--] = source[src_ptr--];
				}
				length -= 7;
				while (src_ptr >= length) {
					dst[dst_ptr--] = source[src_ptr--];
				}
				return;
			}
		}
		length += src_ptr;
		length -= 7;
		while (src_ptr < length) {
			dst[dst_ptr++] = source[src_ptr++];
			dst[dst_ptr++] = source[src_ptr++];
			dst[dst_ptr++] = source[src_ptr++];
			dst[dst_ptr++] = source[src_ptr++];
			dst[dst_ptr++] = source[src_ptr++];
			dst[dst_ptr++] = source[src_ptr++];
			dst[dst_ptr++] = source[src_ptr++];
			dst[dst_ptr++] = source[src_ptr++];
		}
		length += 7;
		while (src_ptr < length) {
			dst[dst_ptr++] = source[src_ptr++];
		}
	}

	public static final void copy(byte[] src, int src_off, byte[] dst, int dst_off, int count) {
		if (src == dst) {
			if (src_off == dst_off) {
				return;
			}
			if (dst_off > src_off && dst_off < src_off + count) {
				count--;
				src_off += count;
				dst_off += count;
				count = src_off - count;
				count += 7;
				while (src_off >= count) {
					dst[dst_off--] = src[src_off--];
					dst[dst_off--] = src[src_off--];
					dst[dst_off--] = src[src_off--];
					dst[dst_off--] = src[src_off--];
					dst[dst_off--] = src[src_off--];
					dst[dst_off--] = src[src_off--];
					dst[dst_off--] = src[src_off--];
					dst[dst_off--] = src[src_off--];
				}
				count -= 7;
				while (src_off >= count) {
					dst[dst_off--] = src[src_off--];
				}
				return;
			}
		}
		count += src_off;
		count -= 7;
		while (src_off < count) {
			dst[dst_off++] = src[src_off++];
			dst[dst_off++] = src[src_off++];
			dst[dst_off++] = src[src_off++];
			dst[dst_off++] = src[src_off++];
			dst[dst_off++] = src[src_off++];
			dst[dst_off++] = src[src_off++];
			dst[dst_off++] = src[src_off++];
			dst[dst_off++] = src[src_off++];
		}
		count += 7;
		while (src_off < count) {
			dst[dst_off++] = src[src_off++];
		}
	}

	static final byte[] clone(byte[] bs, byte b) {
		int i = bs.length;
		byte[] bs_17_ = new byte[i];
		copy(bs, 0, bs_17_, 0, i);
		return bs_17_;
	}

	public static void quicksort(Object[] values, long[] order) {
		ArrayUtils.quicksort(values, order, 0, order.length - 1);
	}

	public static void quicksort(Object[] values, long[] order, int start, int end) {
		if (~start > ~end) {
			int var6 = start;
			int var5 = (start - -end) / 2;
			long var7 = order[var5];
			order[var5] = order[end];
			order[end] = var7;
			Object var9 = values[var5];
			values[var5] = values[end];
			values[end] = var9;
			for (int var10 = start; end > var10; ++var10) {
				if (var7 + (1 & var10) > order[var10]) {
					long var11 = order[var10];
					order[var10] = order[var6];
					order[var6] = var11;
					Object var13 = values[var10];
					values[var10] = values[var6];
					values[var6++] = var13;
				}
			}

			order[end] = order[var6];
			order[var6] = var7;
			values[end] = values[var6];
			values[var6] = var9;
			quicksort(values, order, start, -1 + var6);
			quicksort(values, order, var6 - -1, end);
		}
	}

	public static void quicksort(int[] var2, long[] var1) {
		ArrayUtils.quicksort(var1, 0, -1 + var1.length, var2);
	}

	public static void quicksort(long[] var0, int var1, int var2, int[] var3) {

		if (~var1 > ~var2) {
			int var6 = var1;
			int var5 = (var2 + var1) / 2;
			long var7 = var0[var5];
			var0[var5] = var0[var2];
			var0[var2] = var7;
			int var9 = var3[var5];
			var3[var5] = var3[var2];
			var3[var2] = var9;

			for (int var10 = var1; var2 > var10; ++var10) {
				if (var0[var10] < var7 - -((long) (1 & var10))) {
					long var11 = var0[var10];
					var0[var10] = var0[var6];
					var0[var6] = var11;
					int var13 = var3[var10];
					var3[var10] = var3[var6];
					var3[var6++] = var13;
				}
			}

			var0[var2] = var0[var6];
			var0[var6] = var7;
			var3[var2] = var3[var6];
			var3[var6] = var9;
			quicksort(var0, var1, -1 + var6, var3);
			quicksort(var0, 1 + var6, var2, var3);
		}
	}

	public static final void copy(short[] var0, int var1, short[] var2, int var3, int var4) {
		if (var0 == var2) {
			if (var1 == var3) {
				return;
			}

			if (var3 > var1 && var3 < var1 + var4) {
				--var4;
				var1 += var4;
				var3 += var4;
				var4 = var1 - var4;

				for (var4 += 7; var1 >= var4; var2[var3--] = var0[var1--]) {
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
				}

				for (var4 -= 7; var1 >= var4; var2[var3--] = var0[var1--]) {
				}

				return;
			}
		}

		var4 += var1;

		for (var4 -= 7; var1 < var4; var2[var3++] = var0[var1++]) {
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
		}

		for (var4 += 7; var1 < var4; var2[var3++] = var0[var1++]) {
		}

	}

	public static void copy(float[] var0, int var1, float[] var2, int var3, int var4) {
		if (var0 == var2) {
			if (var1 == var3) {
				return;
			}

			if (var3 > var1 && var3 < var1 + var4) {
				--var4;
				var1 += var4;
				var3 += var4;
				var4 = var1 - var4;

				for (var4 += 7; var1 >= var4; var2[var3--] = var0[var1--]) {
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
				}

				for (var4 -= 7; var1 >= var4; var2[var3--] = var0[var1--]) {
				}

				return;
			}
		}

		var4 += var1;

		for (var4 -= 7; var1 < var4; var2[var3++] = var0[var1++]) {
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
		}

		for (var4 += 7; var1 < var4; var2[var3++] = var0[var1++]) {
		}

	}

	public static void copy(int[] var0, int var1, int[] var2, int var3, int var4) {
		if (var0 == var2) {
			if (var1 == var3) {
				return;
			}

			if (var3 > var1 && var3 < var1 + var4) {
				--var4;
				var1 += var4;
				var3 += var4;
				var4 = var1 - var4;

				for (var4 += 7; var1 >= var4; var2[var3--] = var0[var1--]) {
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
					var2[var3--] = var0[var1--];
				}

				for (var4 -= 7; var1 >= var4; var2[var3--] = var0[var1--]) {
				}

				return;
			}
		}

		var4 += var1;

		for (var4 -= 7; var1 < var4; var2[var3++] = var0[var1++]) {
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
			var2[var3++] = var0[var1++];
		}

		for (var4 += 7; var1 < var4; var2[var3++] = var0[var1++]) {
		}

	}


	public static float[] copy(float[] var0) {
		if (var0 == null) {
			return null;
		}
		float[] var2 = new float[var0.length];
		copy(var0, 0, var2, 0, var0.length);
		return var2;
	}

	public static short[] copy(short[] src) {
		if (null == src) {
			return null;
		}
		short[] dst = new short[src.length];
		copy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static byte[] copy(byte[] var1) {
		if (var1 == null) {
			return null;
		}
		byte[] var2 = new byte[var1.length];
		copy(var1, 0, var2, 0, var1.length);
		return var2;
	}

	public static int[] copy(int[] var0) {
		if (null != var0) {
			int[] var2 = new int[var0.length];
			copy(var0, 0, var2, 0, var0.length);
			return var2;
		} else {
			return null;
		}
	}
}
