package com.jagex.graphics.runetek4.media;

import com.jagex.AnimBase;
import com.jagex.AnimFrame;
import com.jagex.AnimFrameset;
import com.jagex.SceneNode;
import com.rs2.client.scene.Scene;

public abstract class Model extends SceneNode {
	public boolean renders_in_one_tile = false;

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {
		// NOOP
	}

	// public abstract void draw(int var1, int var2, int var3, int var4, int var5, int var6, int var7, long var8);

	@Override
	public abstract void draw2(int i, int i_40_, int i_41_, int i_42_, int i_43_, int i_44_, int i_45_, int i_46_, long l, int bufferOffset);

	public abstract void draw(int i, int i_47_, int i_48_, int i_49_, int i_50_, int i_51_, int i_52_);

	/************************************************** animation start **************************************************/
	public abstract boolean animate_start();

	public abstract void animate_end();

	public abstract void animate_transform(int var1, int[] var2, int var3, int var4, int var5, boolean var6);

	public abstract void animate_partialtransform(int var1, int[] var2, int var3, int var4, int var5, boolean var6, int var7, int[] var8);

	public abstract void animate_shadowtransform(int var1, int var2, int var3, int var4);

	public void animate_shadow_frame(AnimFrameset frameset, int frameid) {
		if (frameid != -1) {
			if (animate_start()) {
				AnimFrame frame = frameset.frames[frameid];
				AnimBase base = frame.base;
				for (int transformation = 0; transformation < frame.num_transformations; ++transformation) {
					short var6 = frame.base_indices[transformation];
					if (base.shadowed[var6]) {
						if (frame.labels[transformation] != -1) {
							animate_shadowtransform(0, 0, 0, 0);
						}
						animate_shadowtransform(base.transformation_type[var6], frame.transformation_x[transformation], frame.transformation_y[transformation], frame.transformation_z[transformation]);
					}
				}
				animate_end();
			}
		}
	}

	public void animate_model_frame(AnimFrameset var1, int var2, AnimFrameset var3, int var4, int var5, int var6, boolean var7) {
		if (var2 != -1) {
			if (animate_start()) {
				AnimFrame var8 = var1.frames[var2];
				AnimBase var9 = var8.base;
				AnimFrame var10 = null;
				if (var3 != null) {
					var10 = var3.frames[var4];
					if (var10.base != var9) {
						var10 = null;
					}
				}
				interpolate_frames(var9, var8, var10, var5, var6, null, false, var7, 0xffff, null);
				animate_end();
			}
		}
	}

	public void method1887(AnimFrameset var1, int var2, AnimFrameset var3, int var4, int var5, int var6, int var7, boolean var8, int[] var9) {
		if (var2 != -1) {
			if (animate_start()) {
				AnimFrame var10 = var1.frames[var2];
				AnimBase var11 = var10.base;
				AnimFrame var12 = null;
				if (var3 != null) {
					var12 = var3.frames[var4];
					if (var12.base != var11) {
						var12 = null;
					}
				}

				interpolate_frames(var11, var10, var12, var5, var6, null, false, var8, var7, var9);
				animate_end();
			}
		}
	}

	public void method1892(AnimFrameset var1, int var2, AnimFrameset var3, int var4, int var5, int var6, AnimFrameset var7, int var8, AnimFrameset var9, int var10, int var11, int var12, boolean[] var13, boolean var14) {
		if (var2 != -1) {
			if (var13 != null && var8 != -1) {
				if (animate_start()) {
					AnimFrame var15 = var1.frames[var2];
					AnimBase var16 = var15.base;
					AnimFrame var17 = null;
					if (var3 != null) {
						var17 = var3.frames[var4];
						if (var17.base != var16) {
							var17 = null;
						}
					}

					AnimFrame var18 = var7.frames[var8];
					AnimFrame var19 = null;
					if (var9 != null) {
						var19 = var9.frames[var10];
						if (var19.base != var16) {
							var19 = null;
						}
					}

					interpolate_frames(var16, var15, var17, var5, var6, var13, false, var14, 0xffff, null);
					animate_transform(0, new int[0], 0, 0, 0, var14);
					interpolate_frames(var16, var18, var19, var11, var12, var13, true, var14, 0xffff, null);
					animate_end();
				}
			} else {
				animate_model_frame(var1, var2, var3, var4, var5, var6, var14);
			}
		}
	}

	private void interpolate_frames(AnimBase var1, AnimFrame var2, AnimFrame var3, int var4, int var5, boolean[] var6, boolean var7, boolean var8, int var9, int[] var10) {
		int var11;
		if (var3 != null && var4 != 0) {
			var11 = 0;
			int var34 = 0;

			for (int var35 = 0; var35 < var1.num_transformation; ++var35) {
				boolean var36 = false;
				if (var11 < var2.num_transformations && var2.base_indices[var11] == var35) {
					var36 = true;
				}

				boolean var15 = false;
				if (var34 < var3.num_transformations && var3.base_indices[var34] == var35) {
					var15 = true;
				}

				if (var36 || var15) {
					if (var6 != null && var6[var35] != var7 && var1.transformation_type[var35] != 0) {
						if (var36) {
							++var11;
						}

						if (var15) {
							++var34;
						}
					} else {
						short var16 = 0;
						int var17 = var1.transformation_type[var35];
						if (var17 == 3) {
							var16 = 128;
						}

						short var19;
						short var18;
						short var21;
						short var20;
						byte current_interpolation;
						if (var36) {
							var18 = var2.transformation_x[var11];
							var19 = var2.transformation_y[var11];
							var20 = var2.transformation_z[var11];
							var21 = var2.labels[var11];
							current_interpolation = var2.tweening_properties[var11];
							++var11;
						} else {
							var18 = var16;
							var19 = var16;
							var20 = var16;
							var21 = -1;
							current_interpolation = 0;
						}

						short var23;
						short var25;
						short var24;
						byte next_interpolation;
						short var26;
						if (var15) {
							var23 = var3.transformation_x[var34];
							var24 = var3.transformation_y[var34];
							var25 = var3.transformation_z[var34];
							var26 = var3.labels[var34];
							next_interpolation = var3.tweening_properties[var34];
							++var34;
						} else {
							var23 = var16;
							var24 = var16;
							var25 = var16;
							var26 = -1;
							next_interpolation = 0;
						}

						int var29;
						int var28;
						int var31;
						int var30;
						if ((current_interpolation & 2) == 0 && (next_interpolation & 1) == 0) {
							if (var17 == 2) {
								var31 = var23 - var18 & 0x3fff;
								int var32 = var24 - var19 & 0x3fff;
								int var33 = var25 - var20 & 0x3fff;
								if (var31 >= 8192) {
									var31 -= 16384;
								}

								if (var32 >= 8192) {
									var32 -= 16384;
								}

								if (var33 >= 8192) {
									var33 -= 16384;
								}

								var28 = var18 + var31 * var4 / var5 & 0x3fff;
								var29 = var19 + var32 * var4 / var5 & 0x3fff;
								var30 = var20 + var33 * var4 / var5 & 0x3fff;
							} else if (var17 == 7) {
								var31 = var23 - var18 & 0x3f;
								if (var31 >= 32) {
									var31 -= 64;
								}

								var28 = var18 + var31 * var4 / var5 & 0x3f;
								var29 = var19 + (var24 - var19) * var4 / var5;
								var30 = var20 + (var25 - var20) * var4 / var5;
							} else {
								var28 = var18 + (var23 - var18) * var4 / var5;
								var29 = var19 + (var24 - var19) * var4 / var5;
								var30 = var20 + (var25 - var20) * var4 / var5;
							}
						} else {
							var28 = var18;
							var29 = var19;
							var30 = var20;
						}

						if (var21 != -1) {
							var31 = var9 & var1.submeshes[var21];
							if (var31 != 0xffff) {
								animate_partialtransform(0, var1.labels[var21], 0, 0, 0, var8, var31, var10);
							} else {
								animate_transform(0, var1.labels[var21], 0, 0, 0, var8);
							}
						} else if (var26 != -1) {
							var31 = var9 & var1.submeshes[var26];
							if (var31 != 0xffff) {
								animate_partialtransform(0, var1.labels[var26], 0, 0, 0, var8, var31, var10);
							} else {
								animate_transform(0, var1.labels[var26], 0, 0, 0, var8);
							}
						}

						var31 = var9 & var1.submeshes[var35];
						if (var31 != 0xffff) {
							animate_partialtransform(var17, var1.labels[var35], var28, var29, var30, var8, var31, var10);
						} else {
							animate_transform(var17, var1.labels[var35], var28, var29, var30, var8);
						}
					}
				}
			}

		} else {
			for (var11 = 0; var11 < var2.num_transformations; ++var11) {
				short var12 = var2.base_indices[var11];
				if (var6 == null || var6[var12] == var7 || var1.transformation_type[var12] == 0) {
					short var13 = var2.labels[var11];
					int var14;
					if (var13 != -1) {
						var14 = var9 & var1.submeshes[var13];
						if (var14 != 0xffff) {
							animate_partialtransform(0, var1.labels[var13], 0, 0, 0, var8, var14, var10);
						} else {
							animate_transform(0, var1.labels[var13], 0, 0, 0, var8);
						}
					}

					var14 = var9 & var1.submeshes[var12];
					if (var14 != 0xffff) {
						animate_partialtransform(var1.transformation_type[var12], var1.labels[var12], var2.transformation_x[var11], var2.transformation_y[var11], var2.transformation_z[var11], var8, var14, var10);
					} else {
						animate_transform(var1.transformation_type[var12], var1.labels[var12], var2.transformation_x[var11], var2.transformation_y[var11], var2.transformation_z[var11], var8);
					}
				}
			}

		}
	}

	/************************************************** animation end **************************************************/
	public abstract void xaxis_rotate_without_normals(int angle);

	public abstract void yaxis_rotate_without_normals(int angle);

	public abstract void zaxis_rotate_without_normals(int angle);

	public abstract void translate(int offset_x, int offset_y, int offset_z);

	public abstract void scale(int scale_x, int scale_y, int scale_z);

	public abstract int get_minx();

	public abstract int get_maxx();

	@Override
	public abstract int get_miny();

	public abstract int get_minz();

	public abstract int get_maxz();

	public abstract int get_size2d();

	public abstract void rotate90_without_normals();

	public abstract void rotate270_without_normals();

	public abstract void rotate180_without_normals();

	public abstract Model copy3(boolean reference_alphas, boolean reference_colors, boolean reference_normals);

	public abstract Model copy2(boolean reference_alphas, boolean reference_colors, boolean reference_normals);

	public abstract Model copy1(boolean reference_alphas, boolean reference_colors, boolean reference_normals);

	public void hillchange(int[][] ground, int x, int y, int z, int width, int length, int xan, int zan) {
		int i_80_ = -width / 2;
		int i_81_ = -length / 2;
		int southwest = Scene.get_average_height_clamp(ground, x + i_80_, z + i_81_);
		int i_83_ = width / 2;
		int i_84_ = -length / 2;
		int southeast = Scene.get_average_height_clamp(ground, x + i_83_, z + i_84_);
		int i_86_ = -width / 2;
		int i_87_ = length / 2;
		int northwest = Scene.get_average_height_clamp(ground, x + i_86_, z + i_87_);
		int i_89_ = width / 2;
		int i_90_ = length / 2;
		int northeast = Scene.get_average_height_clamp(ground, x + i_89_, z + i_90_);
		int loweset_south = southwest < southeast ? southwest : southeast;
		int lowest_north = northwest < northeast ? northwest : northeast;
		int lowest_east = southeast < northeast ? southeast : northeast;
		int lowest_west = southwest < northwest ? southwest : northwest;
		if (length != 0) {
			int rotation = (int) (Math.atan2(loweset_south - lowest_north, length) * 325.95) & 0x7ff;
			if (rotation != 0) {
				if (xan != 0) {
					if (rotation > 2048) {
						int inverse = 4096 - xan;
						if (rotation < inverse) {
							rotation = inverse;
						}
					} else if (rotation > xan) {
						rotation = xan;
					}
				}
				xaxis_rotate_without_normals(rotation);
			}
		}
		if (width != 0) {
			int rotation = (int) (Math.atan2(lowest_west - lowest_east, width) * 325.95) & 0x7ff;
			if (rotation != 0) {
				if (zan != 0) {
					if (rotation > 2048) {
						int inverse = 4096 - zan;
						if (rotation < inverse) {
							rotation = inverse;
						}
					} else if (rotation > zan) {
						rotation = zan;
					}
				}
				zaxis_rotate_without_normals(rotation);
			}
		}
		int height = southwest + northeast;
		if (southeast + northwest < height) {
			height = southeast + northwest;
		}
		height = (height >> 1) - y;
		if (height != 0) {
			translate(0, height, 0);
		}
	}

}
