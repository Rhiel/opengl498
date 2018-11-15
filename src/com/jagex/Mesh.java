package com.jagex;

import com.jagex.graphics.runetek4.media.Model;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.model.OpenGLModel;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

/* Class38_Sub4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Mesh extends SceneNode {
	public static final int[] FAST_SINE = Rasterizer.SINE;
	public static final int[] FAST_COSINE = Rasterizer.COSINE;
	public static final int[] anIntArray2542 = new int[10000];
	public static int[] anIntArray2564;
	public static int anInt2548 = 0;

	/* model */
	public int num_vertices;
	public int num_faces;
	public int num_textures;
	public int version;
	public byte priority;
	public int[][] faces_by_label;
	public int[][] vertices_by_label;

	/* vertices */
	public int[] vertices_x;
	public int[] vertices_y;
	public int[] vertices_z;
	public int[] vertices_label;

	/* faces */
	public int[] faces_a;
	public int[] faces_b;
	public int[] faces_c;
	public byte[] faces_type;
	public byte[] faces_layer;
	public int[] faces_label;
	public byte[] faces_alpha;
	public short[] faces_colour;
	public short[] faces_material;
	public byte[] faces_texture;

	/* textures */
	public byte[] textures_mapping;
	public short[] textures_mapping_p;
	public short[] textures_mapping_m;
	public short[] textures_mapping_n;
	public short[] textures_scale_x;
	public short[] textures_scale_y;
	public short[] textures_scale_z;
	public byte[] textures_speed;
	public byte[] textures_rotation;
	public byte[] textures_direction;
	public byte[] textures_utrans;
	public byte[] textures_vtrans;

	/* bounds */
	public short min_x;
	public short max_x;
	public short min_y;
	public short max_y;
	public short min_z;
	public short max_z;
	public boolean bounds_updated;

	/* lighting */
	public VertexNormal[] vertices_normals_1;
	public VertexNormal[] vertices_normals_2;
	public FaceNormal[] faces_normal;

	/* unknown */
	public short contrast;
	public short ambient;

	/* submeshes */
	public short[] faces_submeshes;
	public short[] vertices_submeshes;

	public Mesh(byte[] modelData) {
		num_faces = 0;
		num_vertices = 0;
		bounds_updated = false;
		if (modelData[modelData.length - 1] == -1 && modelData[modelData.length - 2] == -1) {
			decode(modelData);
		} else {
			decodeLegacy(modelData);
		}
	}

	final Mesh method1999(int i, int i_0_, int[][] is, int[][] is_1_, int i_2_, int i_3_, int i_4_, boolean bool, boolean bool_5_) {
		calculate_bounds();
		int i_6_ = i_2_ + min_x;
		int i_7_ = i_2_ + max_x;
		int i_8_ = i_4_ + min_z;
		int i_9_ = i_4_ + max_z;
		if ((i == 1 || i == 2 || i == 3 || i == 5) && (i_6_ < 0 || i_7_ + 128 >> 7 >= is.length || i_8_ < 0 || i_9_ + 128 >> 7 >= is[0].length)) {
			return this;
		}
		if (i == 4 || i == 5) {
			if (is_1_ == null) {
				return this;
			}
			if (i_6_ < 0 || i_7_ + 128 >> 7 >= is_1_.length || i_8_ < 0 || i_9_ + 128 >> 7 >= is_1_[0].length) {
				return this;
			}
		} else {
			i_6_ >>= 7;
			i_7_ = i_7_ + 127 >> 7;
			i_8_ >>= 7;
			i_9_ = i_9_ + 127 >> 7;
			if (is[i_6_][i_8_] == i_3_ && is[i_7_][i_8_] == i_3_ && is[i_6_][i_9_] == i_3_ && is[i_7_][i_9_] == i_3_) {
				return this;
			}
		}
		Mesh class38_sub4_10_;
		if (bool) {
			class38_sub4_10_ = new Mesh();
			class38_sub4_10_.num_vertices = num_vertices;
			class38_sub4_10_.num_faces = num_faces;
			class38_sub4_10_.num_textures = num_textures;
			class38_sub4_10_.faces_a = faces_a;
			class38_sub4_10_.faces_b = faces_b;
			class38_sub4_10_.faces_c = faces_c;
			class38_sub4_10_.faces_type = faces_type;
			class38_sub4_10_.faces_layer = faces_layer;
			class38_sub4_10_.faces_alpha = faces_alpha;
			class38_sub4_10_.faces_texture = faces_texture;
			class38_sub4_10_.faces_colour = faces_colour;
			class38_sub4_10_.faces_material = faces_material;
			class38_sub4_10_.priority = priority;
			class38_sub4_10_.textures_mapping = textures_mapping;
			class38_sub4_10_.textures_mapping_p = textures_mapping_p;
			class38_sub4_10_.textures_mapping_m = textures_mapping_m;
			class38_sub4_10_.textures_mapping_n = textures_mapping_n;
			class38_sub4_10_.textures_scale_x = textures_scale_x;
			class38_sub4_10_.textures_scale_y = textures_scale_y;
			class38_sub4_10_.textures_scale_z = textures_scale_z;
			class38_sub4_10_.textures_rotation = textures_rotation;
			class38_sub4_10_.textures_direction = textures_direction;
			class38_sub4_10_.textures_speed = textures_speed;
			class38_sub4_10_.textures_utrans = textures_utrans;
			class38_sub4_10_.textures_vtrans = textures_vtrans;
			class38_sub4_10_.vertices_label = vertices_label;
			class38_sub4_10_.faces_label = faces_label;
			class38_sub4_10_.vertices_by_label = vertices_by_label;
			class38_sub4_10_.faces_by_label = faces_by_label;
			class38_sub4_10_.ambient = ambient;
			class38_sub4_10_.contrast = contrast;
			class38_sub4_10_.vertices_normals_1 = vertices_normals_1;
			class38_sub4_10_.faces_normal = faces_normal;
			class38_sub4_10_.vertices_normals_2 = vertices_normals_2;
			if (i == 3) {
				class38_sub4_10_.vertices_x = CollisionMap.method1291(0, vertices_x);
				class38_sub4_10_.vertices_y = CollisionMap.method1291(0, vertices_y);
				class38_sub4_10_.vertices_z = CollisionMap.method1291(0, vertices_z);
			} else {
				class38_sub4_10_.vertices_x = vertices_x;
				class38_sub4_10_.vertices_y = new int[class38_sub4_10_.num_vertices];
				class38_sub4_10_.vertices_z = vertices_z;
			}
		} else {
			class38_sub4_10_ = this;
		}
		if (i == 1) {
			for (int i_11_ = 0; i_11_ < class38_sub4_10_.num_vertices; i_11_++) {
				int i_12_ = vertices_x[i_11_] + i_2_;
				int i_13_ = vertices_z[i_11_] + i_4_;
				int i_14_ = i_12_ & 0x7f;
				int i_15_ = i_13_ & 0x7f;
				int i_16_ = i_12_ >> 7;
				int i_17_ = i_13_ >> 7;
				int i_18_ = is[i_16_][i_17_] * (128 - i_14_) + is[i_16_ + 1][i_17_] * i_14_ >> 7;
				int i_19_ = is[i_16_][i_17_ + 1] * (128 - i_14_) + is[i_16_ + 1][i_17_ + 1] * i_14_ >> 7;
				int i_20_ = i_18_ * (128 - i_15_) + i_19_ * i_15_ >> 7;
				class38_sub4_10_.vertices_y[i_11_] = vertices_y[i_11_] + i_20_ - i_3_;
			}
		} else if (i == 2) {
			for (int i_21_ = 0; i_21_ < class38_sub4_10_.num_vertices; i_21_++) {
				int i_22_ = (vertices_y[i_21_] << 16) / min_y;
				if (i_22_ < i_0_) {
					int i_23_ = vertices_x[i_21_] + i_2_;
					int i_24_ = vertices_z[i_21_] + i_4_;
					int i_25_ = i_23_ & 0x7f;
					int i_26_ = i_24_ & 0x7f;
					int i_27_ = i_23_ >> 7;
					int i_28_ = i_24_ >> 7;
					int i_29_ = is[i_27_][i_28_] * (128 - i_25_) + is[i_27_ + 1][i_28_] * i_25_ >> 7;
					int i_30_ = is[i_27_][i_28_ + 1] * (128 - i_25_) + is[i_27_ + 1][i_28_ + 1] * i_25_ >> 7;
					int i_31_ = i_29_ * (128 - i_26_) + i_30_ * i_26_ >> 7;
					class38_sub4_10_.vertices_y[i_21_] = vertices_y[i_21_] + (i_31_ - i_3_) * (i_0_ - i_22_) / i_0_;
				} else {
					class38_sub4_10_.vertices_y[i_21_] = vertices_y[i_21_];
				}
			}
		} else if (i == 3) {
			int i_32_ = (i_0_ & 0xff) * 4;
			int i_33_ = (i_0_ >> 8 & 0xff) * 4;
			method1054(is, i_2_, i_3_, i_4_, i_32_, i_33_);
		} else if (i == 4) {
			int i_34_ = max_y - min_y;
			for (int i_35_ = 0; i_35_ < num_vertices; i_35_++) {
				int i_36_ = vertices_x[i_35_] + i_2_;
				int i_37_ = vertices_z[i_35_] + i_4_;
				int i_38_ = i_36_ & 0x7f;
				int i_39_ = i_37_ & 0x7f;
				int i_40_ = i_36_ >> 7;
				int i_41_ = i_37_ >> 7;
				int i_42_ = is_1_[i_40_][i_41_] * (128 - i_38_) + is_1_[i_40_ + 1][i_41_] * i_38_ >> 7;
				int i_43_ = is_1_[i_40_][i_41_ + 1] * (128 - i_38_) + is_1_[i_40_ + 1][i_41_ + 1] * i_38_ >> 7;
				int i_44_ = i_42_ * (128 - i_39_) + i_43_ * i_39_ >> 7;
				class38_sub4_10_.vertices_y[i_35_] = vertices_y[i_35_] + i_44_ - i_3_ + i_34_;
			}
		} else if (i == 5) {
			int i_45_ = max_y - min_y;
			for (int i_46_ = 0; i_46_ < num_vertices; i_46_++) {
				int i_47_ = vertices_x[i_46_] + i_2_;
				int i_48_ = vertices_z[i_46_] + i_4_;
				int i_49_ = i_47_ & 0x7f;
				int i_50_ = i_48_ & 0x7f;
				int i_51_ = i_47_ >> 7;
				int i_52_ = i_48_ >> 7;
				int i_53_ = is[i_51_][i_52_] * (128 - i_49_) + is[i_51_ + 1][i_52_] * i_49_ >> 7;
				int i_54_ = is[i_51_][i_52_ + 1] * (128 - i_49_) + is[i_51_ + 1][i_52_ + 1] * i_49_ >> 7;
				int i_55_ = i_53_ * (128 - i_50_) + i_54_ * i_50_ >> 7;
				i_53_ = is_1_[i_51_][i_52_] * (128 - i_49_) + is_1_[i_51_ + 1][i_52_] * i_49_ >> 7;
				i_54_ = is_1_[i_51_][i_52_ + 1] * (128 - i_49_) + is_1_[i_51_ + 1][i_52_ + 1] * i_49_ >> 7;
				int i_56_ = i_53_ * (128 - i_50_) + i_54_ * i_50_ >> 7;
				int i_57_ = i_55_ - i_56_;
				class38_sub4_10_.vertices_y[i_46_] = ((vertices_y[i_46_] << 8) / i_45_ * i_57_ >> 8) - (i_3_ - i_55_);
			}
		}
		if (bool_5_) {
			class38_sub4_10_.invalidate();
		} else {
			bounds_updated = false;
		}
		return class38_sub4_10_;
	}

	public void scaleDown(int bits) {
		for (int vertex = 0; vertex < num_vertices; vertex++) {
			vertices_x[vertex] >>= bits;
			vertices_y[vertex] >>= bits;
			vertices_z[vertex] >>= bits;

		}
	}

	@Override
	public final int get_miny() {
		if (!bounds_updated) {
			calculate_bounds();
		}
		return min_y;
	}

	final void translate(int i, int i_120_, int i_121_) {
		for (int i_122_ = 0; i_122_ < num_vertices; i_122_++) {
			vertices_x[i_122_] += i;
			vertices_y[i_122_] += i_120_;
			vertices_z[i_122_] += i_121_;
		}
		invalidate();
	}

	@Override
	public void update_shadows(int var1, int var2, int var3, int var4, int var5) {
	}

	@Override
	public final void draw2(int i, int i_123_, int i_124_, int i_125_, int i_126_, int i_127_, int i_128_, int i_129_, long l, int bufferOffset) {
		/* empty */
	}

	final void retexture(short src, short dst) {
		if (faces_material != null) {
			for (int i = 0; i < num_faces; i++) {
				if (faces_material[i] == src) {
					faces_material[i] = dst;
				}
			}
		}
	}

	public void tint(int hue, int saturation, int lightness, int opacity) {
		for (int face = 0; face < num_faces; face++) {
			int src_colour = faces_colour[face] & 0xffff;
			int src_hue = src_colour >> 10 & 0x3f;
			int src_saturation = src_colour >> 7 & 0x7;
			int src_lighntess = src_colour & 0x7f;
			if (hue != -1) {
				src_hue += (hue - src_hue) * opacity >> 7;
			}
			if (saturation != -1) {
				src_saturation += (saturation - src_saturation) * opacity >> 7;
			}
			if (lightness != -1) {
				src_lighntess += (lightness - src_lighntess) * opacity >> 7;
			}
			faces_colour[face] = (short) (src_hue << 10 | src_saturation << 7 | src_lighntess);
		}
	}

	public static void method1044() {
		anIntArray2564 = null;
	}

	@Override
	public final SceneNode method994(int i, int i_131_, int i_132_) {
		return method2008(ambient, contrast, i, i_131_, i_132_);
	}

	final void rotate270() {
		for (int i = 0; i < num_vertices; i++) {
			int i_133_ = vertices_z[i];
			vertices_z[i] = vertices_x[i];
			vertices_x[i] = -i_133_;
		}
		invalidate();
	}

	final SoftwareModel light(int ambient, int contrast, int globalsun_anglex, int globalsun_angley, int globalsun_anglez) {
		return new SoftwareModel(this, ambient, contrast, globalsun_anglex, globalsun_angley, globalsun_anglez);
	}

	public final void invalidate() {
		vertices_normals_1 = null;
		vertices_normals_2 = null;
		faces_normal = null;
		bounds_updated = false;
	}

	final void calculate_bonetables() {
		if (vertices_label != null) {
			int[] labels = new int[256];
			int highestLabel = 0;
			for (int i_138_ = 0; i_138_ < num_vertices; i_138_++) {
				int label = vertices_label[i_138_];
				if (label >= 0) {
					labels[label]++;
					if (label > highestLabel) {
						highestLabel = label;
					}
				}
			}
			vertices_by_label = new int[highestLabel + 1][];
			for (int i_140_ = 0; i_140_ <= highestLabel; i_140_++) {
				vertices_by_label[i_140_] = new int[labels[i_140_]];
				labels[i_140_] = 0;
			}
			for (int i_141_ = 0; i_141_ < num_vertices; i_141_++) {
				int label = vertices_label[i_141_];
				if (label >= 0) {
					vertices_by_label[label][labels[label]++] = i_141_;
				}
			}
			vertices_label = null;
		}
		if (faces_label != null) {
			int[] is = new int[256];
			int i = 0;
			for (int i_143_ = 0; i_143_ < num_faces; i_143_++) {
				int i_144_ = faces_label[i_143_];
				if (i_144_ >= 0) {
					is[i_144_]++;
					if (i_144_ > i) {
						i = i_144_;
					}
				}
			}
			faces_by_label = new int[i + 1][];
			for (int i_145_ = 0; i_145_ <= i; i_145_++) {
				faces_by_label[i_145_] = new int[is[i_145_]];
				is[i_145_] = 0;
			}
			for (int i_146_ = 0; i_146_ < num_faces; i_146_++) {
				int i_147_ = faces_label[i_146_];
				if (i_147_ >= 0) {
					faces_by_label[i_147_][is[i_147_]++] = i_146_;
				}
			}
			faces_label = null;
		}
	}

	final void yaxis_rotate(int i) {
		int i_148_ = FAST_SINE[i];
		int i_149_ = FAST_COSINE[i];
		for (int i_150_ = 0; i_150_ < num_vertices; i_150_++) {
			int i_151_ = vertices_z[i_150_] * i_148_ + vertices_x[i_150_] * i_149_ >> 16;
			vertices_z[i_150_] = vertices_z[i_150_] * i_149_ - vertices_x[i_150_] * i_148_ >> 16;
			vertices_x[i_150_] = i_151_;
		}
		invalidate();
	}

	public final void zaxis_rotate(int i) {
		int i_152_ = FAST_SINE[i];
		int i_153_ = FAST_COSINE[i];
		for (int i_154_ = 0; i_154_ < num_vertices; i_154_++) {
			int i_155_ = vertices_y[i_154_] * i_152_ + vertices_x[i_154_] * i_153_ >> 16;
			vertices_y[i_154_] = vertices_y[i_154_] * i_153_ - vertices_x[i_154_] * i_152_ >> 16;
			vertices_x[i_154_] = i_155_;
		}
		invalidate();
	}

	final Mesh copy() {
		Mesh class38_sub4_156_ = new Mesh();
		if (faces_type != null) {
			class38_sub4_156_.faces_type = new byte[num_faces];
			for (int i = 0; i < num_faces; i++) {
				class38_sub4_156_.faces_type[i] = faces_type[i];
			}
		}
		class38_sub4_156_.num_vertices = num_vertices;
		class38_sub4_156_.num_faces = num_faces;
		class38_sub4_156_.num_textures = num_textures;
		class38_sub4_156_.vertices_x = vertices_x;
		class38_sub4_156_.vertices_y = vertices_y;
		class38_sub4_156_.vertices_z = vertices_z;
		class38_sub4_156_.faces_a = faces_a;
		class38_sub4_156_.faces_b = faces_b;
		class38_sub4_156_.faces_c = faces_c;
		class38_sub4_156_.faces_layer = faces_layer;
		class38_sub4_156_.faces_alpha = faces_alpha;
		class38_sub4_156_.faces_texture = faces_texture;
		class38_sub4_156_.faces_colour = faces_colour;
		class38_sub4_156_.faces_material = faces_material;
		class38_sub4_156_.priority = priority;
		class38_sub4_156_.textures_mapping = textures_mapping;
		class38_sub4_156_.textures_mapping_p = textures_mapping_p;
		class38_sub4_156_.textures_mapping_m = textures_mapping_m;
		class38_sub4_156_.textures_mapping_n = textures_mapping_n;
		class38_sub4_156_.textures_scale_x = textures_scale_x;
		class38_sub4_156_.textures_scale_y = textures_scale_y;
		class38_sub4_156_.textures_scale_z = textures_scale_z;
		class38_sub4_156_.textures_rotation = textures_rotation;
		class38_sub4_156_.textures_direction = textures_direction;
		class38_sub4_156_.textures_speed = textures_speed;
		class38_sub4_156_.textures_utrans = textures_utrans;
		class38_sub4_156_.textures_vtrans = textures_vtrans;
		class38_sub4_156_.vertices_label = vertices_label;
		class38_sub4_156_.faces_label = faces_label;
		class38_sub4_156_.vertices_by_label = vertices_by_label;
		class38_sub4_156_.faces_by_label = faces_by_label;
		class38_sub4_156_.vertices_normals_1 = vertices_normals_1;
		class38_sub4_156_.faces_normal = faces_normal;
		class38_sub4_156_.ambient = ambient;
		class38_sub4_156_.contrast = contrast;
		return class38_sub4_156_;
	}

	public final void calculate_bounds() {
		if (!bounds_updated) {
			int min_x = 32767;
			int min_y = 32767;
			int min_z = 32767;
			int max_x = -32768;
			int max_y = -32768;
			int max_z = -32768;
			for (int vertex = 0; vertex < num_vertices; vertex++) {
				int x = vertices_x[vertex];
				int y = vertices_y[vertex];
				int z = vertices_z[vertex];
				if (x < min_x) {
					min_x = x;
				}
				if (x > max_x) {
					max_x = x;
				}
				if (y < min_y) {
					min_y = y;
				}
				if (y > max_y) {
					max_y = y;
				}
				if (z < min_z) {
					min_z = z;
				}
				if (z > max_z) {
					max_z = z;
				}
			}
			this.min_x = (short) min_x;
			this.max_x = (short) max_x;
			this.min_y = (short) min_y;
			this.max_y = (short) max_y;
			this.min_z = (short) min_z;
			this.max_z = (short) max_z;
			bounds_updated = true;
		}
	}

	public final void method1054(int[][] is, int i, int i_173_, int i_174_, int i_175_, int i_176_) {
		int i_179_ = -i_175_ / 2;
		int i_180_ = -i_176_ / 2;
		int i_181_ = Scene.method1052(is, i + i_179_, i_174_ + i_180_);
		int i_182_ = i_175_ / 2;
		int i_183_ = -i_176_ / 2;
		int i_184_ = Scene.method1052(is, i + i_182_, i_174_ + i_183_);
		int i_185_ = -i_175_ / 2;
		int i_186_ = i_176_ / 2;
		int i_187_ = Scene.method1052(is, i + i_185_, i_174_ + i_186_);
		int i_188_ = i_175_ / 2;
		int i_189_ = i_176_ / 2;
		int i_190_ = Scene.method1052(is, i + i_188_, i_174_ + i_189_);
		int i_191_ = i_181_ < i_184_ ? i_181_ : i_184_;
		int i_192_ = i_187_ < i_190_ ? i_187_ : i_190_;
		int i_193_ = i_184_ < i_190_ ? i_184_ : i_190_;
		int i_194_ = i_181_ < i_187_ ? i_181_ : i_187_;
		int i_195_ = (int) (Math.atan2(i_191_ - i_192_, i_176_) * 325.95) & 0x7ff;
		if (i_195_ != 0) {
			method1059(i_195_);
		}
		int i_196_ = (int) (Math.atan2(i_194_ - i_193_, i_175_) * 325.95) & 0x7ff;
		if (i_196_ != 0) {
			zaxis_rotate(i_196_);
		}
		int i_197_ = i_181_ + i_190_;
		if (i_184_ + i_187_ < i_197_) {
			i_197_ = i_184_ + i_187_;
		}
		i_197_ = (i_197_ >> 1) - i_173_;
		if (i_197_ != 0) {
			translate(0, i_197_, 0);
		}
	}

	final void method1055(int i, int i_198_, int i_199_) {
		if (i_199_ != 0) {
			int i_200_ = FAST_SINE[i_199_];
			int i_201_ = FAST_COSINE[i_199_];
			for (int i_202_ = 0; i_202_ < num_vertices; i_202_++) {
				int i_203_ = vertices_y[i_202_] * i_200_ + vertices_x[i_202_] * i_201_ >> 16;
				vertices_y[i_202_] = vertices_y[i_202_] * i_201_ - vertices_x[i_202_] * i_200_ >> 16;
				vertices_x[i_202_] = i_203_;
			}
		}
		if (i != 0) {
			int i_204_ = FAST_SINE[i];
			int i_205_ = FAST_COSINE[i];
			for (int i_206_ = 0; i_206_ < num_vertices; i_206_++) {
				int i_207_ = vertices_y[i_206_] * i_205_ - vertices_z[i_206_] * i_204_ >> 16;
				vertices_z[i_206_] = vertices_y[i_206_] * i_204_ + vertices_z[i_206_] * i_205_ >> 16;
				vertices_y[i_206_] = i_207_;
			}
		}
		if (i_198_ != 0) {
			int i_208_ = FAST_SINE[i_198_];
			int i_209_ = FAST_COSINE[i_198_];
			for (int i_210_ = 0; i_210_ < num_vertices; i_210_++) {
				int i_211_ = vertices_z[i_210_] * i_208_ + vertices_x[i_210_] * i_209_ >> 16;
				vertices_z[i_210_] = vertices_z[i_210_] * i_209_ - vertices_x[i_210_] * i_208_ >> 16;
				vertices_x[i_210_] = i_211_;
			}
		}
	}

	final void resize(int i, int i_212_, int i_213_) {
		for (int i_214_ = 0; i_214_ < num_vertices; i_214_++) {
			vertices_x[i_214_] = vertices_x[i_214_] * i / 128;
			vertices_y[i_214_] = vertices_y[i_214_] * i_212_ / 128;
			vertices_z[i_214_] = vertices_z[i_214_] * i_213_ / 128;
		}
		invalidate();
	}

	final void mirror_z() {
		for (int i = 0; i < num_vertices; i++) {
			vertices_z[i] = -vertices_z[i];
		}
		for (int i = 0; i < num_faces; i++) {
			int i_215_ = faces_a[i];
			faces_a[i] = faces_c[i];
			faces_c[i] = i_215_;
		}
		invalidate();
	}

	final void rotation90() {
		for (int i = 0; i < num_vertices; i++) {
			int i_216_ = vertices_x[i];
			vertices_x[i] = vertices_z[i];
			vertices_z[i] = -i_216_;
		}
		invalidate();
	}

	public final void method1059(int i) {
		int i_217_ = FAST_SINE[i];
		int i_218_ = FAST_COSINE[i];
		for (int i_219_ = 0; i_219_ < num_vertices; i_219_++) {
			int i_220_ = vertices_y[i_219_] * i_218_ - vertices_z[i_219_] * i_217_ >> 16;
			vertices_z[i_219_] = vertices_y[i_219_] * i_217_ + vertices_z[i_219_] * i_218_ >> 16;
			vertices_y[i_219_] = i_220_;
		}
		invalidate();
	}

	static final Mesh fromJs5(Js5 modelJs5, int modelId, int fileId) {
		byte[] bs = modelJs5.get_file(modelId, fileId);
		if (bs == null) {
			return null;
		}
		return new Mesh(bs);
	}

	@Override
	public final boolean method998() {
		return true;
	}

	final void rotate180() {
		for (int i = 0; i < num_vertices; i++) {
			vertices_x[i] = -vertices_x[i];
			vertices_z[i] = -vertices_z[i];
		}
		invalidate();
	}

	public final int method1062(Mesh class38_sub4_222_, int i, short mesh_index) {
		int i_224_ = class38_sub4_222_.vertices_x[i];
		int i_225_ = class38_sub4_222_.vertices_y[i];
		int i_226_ = class38_sub4_222_.vertices_z[i];
		for (int i_227_ = 0; i_227_ < num_vertices; i_227_++) {
			if (i_224_ == vertices_x[i_227_] && i_225_ == vertices_y[i_227_] && i_226_ == vertices_z[i_227_]) {
				vertices_submeshes[i_227_] |= mesh_index;
				return i_227_;

			}
		}
		vertices_x[num_vertices] = i_224_;
		vertices_y[num_vertices] = i_225_;
		vertices_z[num_vertices] = i_226_;
		vertices_submeshes[num_vertices] = mesh_index;
		if (class38_sub4_222_.vertices_label != null) {
			vertices_label[num_vertices] = class38_sub4_222_.vertices_label[i];
		}
		return num_vertices++;
	}

	@Override
	public final void sharelight(SceneNode abstractModel, int i, int i_228_, int i_229_, boolean bool) {
		Mesh class38_sub4_230_ = (Mesh) abstractModel;
		class38_sub4_230_.calculate_bounds();
		class38_sub4_230_.calculate_normals();
		anInt2548++;
		int i_231_ = 0;
		int[] is = class38_sub4_230_.vertices_x;
		int i_232_ = class38_sub4_230_.num_vertices;
		for (int i_233_ = 0; i_233_ < num_vertices; i_233_++) {
			VertexNormal vertex = vertices_normals_1[i_233_];
			if (vertex.magnitude != 0) {
				int i_234_ = vertices_y[i_233_] - i_228_;
				if (i_234_ >= class38_sub4_230_.min_y && i_234_ <= class38_sub4_230_.max_y) {
					int i_235_ = vertices_x[i_233_] - i;
					if (i_235_ >= class38_sub4_230_.min_x && i_235_ <= class38_sub4_230_.max_x) {
						int i_236_ = vertices_z[i_233_] - i_229_;
						if (i_236_ >= class38_sub4_230_.min_z && i_236_ <= class38_sub4_230_.max_z) {
							for (int i_237_ = 0; i_237_ < i_232_; i_237_++) {
								VertexNormal class92_238_ = class38_sub4_230_.vertices_normals_1[i_237_];
								if (i_235_ == is[i_237_] && i_236_ == class38_sub4_230_.vertices_z[i_237_] && i_234_ == class38_sub4_230_.vertices_y[i_237_] && class92_238_.magnitude != 0) {
									if (vertices_normals_2 == null) {
										vertices_normals_2 = new VertexNormal[num_vertices];
									}
									if (class38_sub4_230_.vertices_normals_2 == null) {
										class38_sub4_230_.vertices_normals_2 = new VertexNormal[i_232_];
									}
									VertexNormal vertex1 = vertices_normals_2[i_233_];
									if (vertex1 == null) {
										vertex1 = vertices_normals_2[i_233_] = new VertexNormal(vertex);
									}
									VertexNormal vertex2 = class38_sub4_230_.vertices_normals_2[i_237_];
									if (vertex2 == null) {
										vertex2 = class38_sub4_230_.vertices_normals_2[i_237_] = new VertexNormal(class92_238_);
									}
									vertex1.x += class92_238_.x;
									vertex1.y += class92_238_.y;
									vertex1.z += class92_238_.z;
									vertex1.magnitude += class92_238_.magnitude;
									vertex2.x += vertex.x;
									vertex2.y += vertex.y;
									vertex2.z += vertex.z;
									vertex2.magnitude += vertex.magnitude;
									i_231_++;
									anIntArray2542[i_233_] = anInt2548;
									anIntArray2564[i_237_] = anInt2548;
								}
							}
						}
					}
				}
			}
		}
		if (i_231_ >= 3 && bool) {
			for (int i_241_ = 0; i_241_ < num_faces; i_241_++) {
				if (anIntArray2542[faces_a[i_241_]] == anInt2548 && anIntArray2542[faces_b[i_241_]] == anInt2548 && anIntArray2542[faces_c[i_241_]] == anInt2548) {
					if (faces_type == null) {
						faces_type = new byte[num_faces];
					}
					faces_type[i_241_] = (byte) 2;
				}
			}
			for (int i_242_ = 0; i_242_ < class38_sub4_230_.num_faces; i_242_++) {
				if (anIntArray2564[class38_sub4_230_.faces_a[i_242_]] == anInt2548 && anIntArray2564[class38_sub4_230_.faces_b[i_242_]] == anInt2548 && anIntArray2564[class38_sub4_230_.faces_c[i_242_]] == anInt2548) {
					if (class38_sub4_230_.faces_type == null) {
						class38_sub4_230_.faces_type = new byte[class38_sub4_230_.num_faces];
					}
					class38_sub4_230_.faces_type[i_242_] = (byte) 2;
				}
			}
		}
	}

	final void method1063() {
		vertices_label = null;
		faces_label = null;
		vertices_by_label = null;
		faces_by_label = null;
	}

	public Model method2008(int ambient, int contrast, int sun_position_x, int sun_position_y, int sun_position_z) {
		if (GLManager.opengl_mode) {
			OpenGLModel model = new OpenGLModel(this, ambient, contrast, true);
			model.generate_labels_lookup_table();
			return model;
		} else {
			return new SoftwareModel(this, ambient, contrast, sun_position_x, sun_position_y, sun_position_z);
		}
	}

	final void recolor(short original, short newColor) {
		for (int i = 0; i < num_faces; i++) {
			if (faces_colour[i] == original) {
				faces_colour[i] = newColor;
			}
		}
	}

	public void calculate_normals() {
		if (vertices_normals_1 == null) {
			vertices_normals_1 = new VertexNormal[num_vertices];
			for (int i = 0; i < num_vertices; i++) {
				vertices_normals_1[i] = new VertexNormal();
			}
			for (int i = 0; i < num_faces; i++) {
				int index_a = faces_a[i];
				int index_b = faces_b[i];
				int index_c = faces_c[i];
				int i_251_ = vertices_x[index_b] - vertices_x[index_a];
				int i_252_ = vertices_y[index_b] - vertices_y[index_a];
				int i_253_ = vertices_z[index_b] - vertices_z[index_a];
				int i_254_ = vertices_x[index_c] - vertices_x[index_a];
				int i_255_ = vertices_y[index_c] - vertices_y[index_a];
				int i_256_ = vertices_z[index_c] - vertices_z[index_a];
				int i_257_ = i_252_ * i_256_ - i_255_ * i_253_;
				int i_258_ = i_253_ * i_254_ - i_256_ * i_251_;
				int i_259_;
				for (i_259_ = i_251_ * i_255_ - i_254_ * i_252_; i_257_ > 8192 || i_258_ > 8192 || i_259_ > 8192 || i_257_ < -8192 || i_258_ < -8192 || i_259_ < -8192; i_259_ >>= 1) {
					i_257_ >>= 1;
					i_258_ >>= 1;
				}
				int length = (int) Math.sqrt(i_257_ * i_257_ + i_258_ * i_258_ + i_259_ * i_259_);
				if (length <= 0) {
					length = 1;
				}
				i_257_ = i_257_ * 256 / length;
				i_258_ = i_258_ * 256 / length;
				i_259_ = i_259_ * 256 / length;
				byte face_type;
				if (faces_type == null) {
					face_type = (byte) 0;
				} else {
					face_type = faces_type[i];
				}
				if (face_type == 0) {
					VertexNormal normal = vertices_normals_1[index_a];
					normal.x += i_257_;
					normal.y += i_258_;
					normal.z += i_259_;
					normal.magnitude++;
					normal = vertices_normals_1[index_b];
					normal.x += i_257_;
					normal.y += i_258_;
					normal.z += i_259_;
					normal.magnitude++;
					normal = vertices_normals_1[index_c];
					normal.x += i_257_;
					normal.y += i_258_;
					normal.z += i_259_;
					normal.magnitude++;
				} else if (face_type == 1) {
					if (faces_normal == null) {
						faces_normal = new FaceNormal[num_faces];
					}
					FaceNormal normal = faces_normal[i] = new FaceNormal();
					normal.x = i_257_;
					normal.y = i_258_;
					normal.z = i_259_;
				}
			}
		}
	}

	public Mesh() {
		num_faces = 0;
		num_vertices = 0;
		bounds_updated = false;
	}

	public Mesh(int num_vertices, int num_faces, int num_textures) {
		vertices_x = new int[num_vertices];
		vertices_y = new int[num_vertices];
		vertices_z = new int[num_vertices];
		vertices_label = new int[num_vertices];
		faces_a = new int[num_faces];
		faces_b = new int[num_faces];
		faces_c = new int[num_faces];
		faces_type = new byte[num_faces];
		faces_layer = new byte[num_faces];
		faces_alpha = new byte[num_faces];
		faces_colour = new short[num_faces];
		faces_material = new short[num_faces];
		faces_texture = new byte[num_faces];
		faces_label = new int[num_faces];
		if (num_textures > 0) {
			textures_mapping = new byte[num_textures];
			textures_mapping_p = new short[num_textures];
			textures_mapping_m = new short[num_textures];
			textures_mapping_n = new short[num_textures];
			textures_scale_x = new short[num_textures];
			textures_scale_y = new short[num_textures];
			textures_scale_z = new short[num_textures];
			textures_rotation = new byte[num_textures];
			textures_direction = new byte[num_textures];
			textures_speed = new byte[num_textures];
			textures_utrans = new byte[num_textures];
			textures_vtrans = new byte[num_textures];
		}

	}

	Mesh(Mesh[] class38_sub4s, int i) {
		num_faces = 0;
		num_vertices = 0;
		bounds_updated = false;
		boolean bool = false;
		boolean bool_261_ = false;
		boolean bool_262_ = false;
		boolean bool_263_ = false;
		boolean bool_264_ = false;
		boolean bool_265_ = false;
		num_vertices = 0;
		num_faces = 0;
		num_textures = 0;
		priority = (byte) -1;
		for (int i_266_ = 0; i_266_ < i; i_266_++) {
			Mesh class38_sub4_267_ = class38_sub4s[i_266_];
			if (class38_sub4_267_ != null) {
				num_vertices += class38_sub4_267_.num_vertices;
				num_faces += class38_sub4_267_.num_faces;
				num_textures += class38_sub4_267_.num_textures;
				if (class38_sub4_267_.faces_layer != null) {
					bool_261_ = true;
				} else {
					if (priority == -1) {
						priority = class38_sub4_267_.priority;
					}
					if (priority != class38_sub4_267_.priority) {
						bool_261_ = true;
					}
				}
				bool = bool | class38_sub4_267_.faces_type != null;
				bool_262_ = bool_262_ | class38_sub4_267_.faces_alpha != null;
				bool_263_ = bool_263_ | class38_sub4_267_.faces_label != null;
				bool_264_ = bool_264_ | class38_sub4_267_.faces_material != null;
				bool_265_ = bool_265_ | class38_sub4_267_.faces_texture != null;
			}
		}
		vertices_x = new int[num_vertices];
		vertices_y = new int[num_vertices];
		vertices_z = new int[num_vertices];
		vertices_label = new int[num_vertices];
		vertices_submeshes = new short[num_vertices];
		faces_a = new int[num_faces];
		faces_b = new int[num_faces];
		faces_c = new int[num_faces];
		if (bool) {
			faces_type = new byte[num_faces];
		}
		if (bool_261_) {
			faces_layer = new byte[num_faces];
		}
		if (bool_262_) {
			faces_alpha = new byte[num_faces];
		}
		if (bool_263_) {
			faces_label = new int[num_faces];
		}
		if (bool_264_) {
			faces_material = new short[num_faces];
		}
		if (bool_265_) {
			faces_texture = new byte[num_faces];
		}
		faces_colour = new short[num_faces];
		faces_submeshes = new short[num_faces];
		if (num_textures > 0) {
			textures_mapping = new byte[num_textures];
			textures_mapping_p = new short[num_textures];
			textures_mapping_m = new short[num_textures];
			textures_mapping_n = new short[num_textures];
			textures_scale_x = new short[num_textures];
			textures_scale_y = new short[num_textures];
			textures_scale_z = new short[num_textures];
			textures_rotation = new byte[num_textures];
			textures_direction = new byte[num_textures];
			textures_speed = new byte[num_textures];
			textures_utrans = new byte[num_textures];
			textures_vtrans = new byte[num_textures];
		}
		num_vertices = 0;
		num_faces = 0;
		num_textures = 0;
		for (int mesh_index = 0; mesh_index < i; mesh_index++) {
			short mesh_flag = (short) (1 << mesh_index);
			Mesh class38_sub4_269_ = class38_sub4s[mesh_index];
			if (class38_sub4_269_ != null) {
				for (int i_270_ = 0; i_270_ < class38_sub4_269_.num_faces; i_270_++) {
					if (bool && class38_sub4_269_.faces_type != null) {
						faces_type[num_faces] = class38_sub4_269_.faces_type[i_270_];
					}
					if (bool_261_) {
						if (class38_sub4_269_.faces_layer != null) {
							faces_layer[num_faces] = class38_sub4_269_.faces_layer[i_270_];
						} else {
							faces_layer[num_faces] = class38_sub4_269_.priority;
						}
					}
					if (bool_262_ && class38_sub4_269_.faces_alpha != null) {
						faces_alpha[num_faces] = class38_sub4_269_.faces_alpha[i_270_];
					}
					if (bool_263_ && class38_sub4_269_.faces_label != null) {
						faces_label[num_faces] = class38_sub4_269_.faces_label[i_270_];
					}
					if (bool_264_) {
						if (class38_sub4_269_.faces_material != null) {
							faces_material[num_faces] = class38_sub4_269_.faces_material[i_270_];
						} else {
							faces_material[num_faces] = (short) -1;
						}
					}
					if (bool_265_) {
						if (class38_sub4_269_.faces_texture != null && class38_sub4_269_.faces_texture[i_270_] != -1) {
							faces_texture[num_faces] = (byte) (class38_sub4_269_.faces_texture[i_270_] + num_textures);
						} else {
							faces_texture[num_faces] = (byte) -1;
						}
					}
					faces_colour[num_faces] = class38_sub4_269_.faces_colour[i_270_];
					faces_submeshes[num_faces] = mesh_flag;
					faces_a[num_faces] = method1062(class38_sub4_269_, class38_sub4_269_.faces_a[i_270_], mesh_flag);
					faces_b[num_faces] = method1062(class38_sub4_269_, class38_sub4_269_.faces_b[i_270_], mesh_flag);
					faces_c[num_faces] = method1062(class38_sub4_269_, class38_sub4_269_.faces_c[i_270_], mesh_flag);
					num_faces++;
				}
				for (int i_271_ = 0; i_271_ < class38_sub4_269_.num_textures; i_271_++) {
					byte b = textures_mapping[num_textures] = class38_sub4_269_.textures_mapping[i_271_];
					if (b == 0) {
						textures_mapping_p[num_textures] = (short) method1062(class38_sub4_269_, class38_sub4_269_.textures_mapping_p[i_271_], mesh_flag);
						textures_mapping_m[num_textures] = (short) method1062(class38_sub4_269_, class38_sub4_269_.textures_mapping_m[i_271_], mesh_flag);
						textures_mapping_n[num_textures] = (short) method1062(class38_sub4_269_, class38_sub4_269_.textures_mapping_n[i_271_], mesh_flag);
					}
					if (b >= 1 && b <= 3) {
						textures_mapping_p[num_textures] = class38_sub4_269_.textures_mapping_p[i_271_];
						textures_mapping_m[num_textures] = class38_sub4_269_.textures_mapping_m[i_271_];
						textures_mapping_n[num_textures] = class38_sub4_269_.textures_mapping_n[i_271_];
						textures_scale_x[num_textures] = class38_sub4_269_.textures_scale_x[i_271_];
						textures_scale_y[num_textures] = class38_sub4_269_.textures_scale_y[i_271_];
						textures_scale_z[num_textures] = class38_sub4_269_.textures_scale_z[i_271_];
						textures_rotation[num_textures] = class38_sub4_269_.textures_rotation[i_271_];
						textures_direction[num_textures] = class38_sub4_269_.textures_direction[i_271_];
						textures_speed[num_textures] = class38_sub4_269_.textures_speed[i_271_];
					}
					if (b == 2) {
						textures_utrans[num_textures] = class38_sub4_269_.textures_utrans[i_271_];
						textures_vtrans[num_textures] = class38_sub4_269_.textures_vtrans[i_271_];
					}
					num_textures++;
				}
			}
		}
	}

	Mesh(Mesh class38_sub4_272_, boolean bool, boolean bool_273_, boolean bool_274_, boolean bool_275_) {
		num_faces = 0;
		num_vertices = 0;
		bounds_updated = false;
		num_vertices = class38_sub4_272_.num_vertices;
		num_faces = class38_sub4_272_.num_faces;
		num_textures = class38_sub4_272_.num_textures;
		if (bool) {
			vertices_x = class38_sub4_272_.vertices_x;
			vertices_y = class38_sub4_272_.vertices_y;
			vertices_z = class38_sub4_272_.vertices_z;
		} else {
			vertices_x = new int[num_vertices];
			vertices_y = new int[num_vertices];
			vertices_z = new int[num_vertices];
			for (int i = 0; i < num_vertices; i++) {
				vertices_x[i] = class38_sub4_272_.vertices_x[i];
				vertices_y[i] = class38_sub4_272_.vertices_y[i];
				vertices_z[i] = class38_sub4_272_.vertices_z[i];
			}
		}
		if (bool_273_) {
			faces_colour = class38_sub4_272_.faces_colour;
		} else {
			faces_colour = new short[num_faces];
			for (int i = 0; i < num_faces; i++) {
				faces_colour[i] = class38_sub4_272_.faces_colour[i];
			}
		}
		if (bool_274_ || class38_sub4_272_.faces_material == null) {
			faces_material = class38_sub4_272_.faces_material;
		} else {
			faces_material = new short[num_faces];
			for (int i = 0; i < num_faces; i++) {
				faces_material[i] = class38_sub4_272_.faces_material[i];
			}
		}
		if (bool_275_) {
			faces_alpha = class38_sub4_272_.faces_alpha;
		} else {
			faces_alpha = new byte[num_faces];
			if (class38_sub4_272_.faces_alpha == null) {
				for (int i = 0; i < num_faces; i++) {
					faces_alpha[i] = (byte) 0;
				}
			} else {
				for (int i = 0; i < num_faces; i++) {
					faces_alpha[i] = class38_sub4_272_.faces_alpha[i];
				}
			}
		}
		faces_a = class38_sub4_272_.faces_a;
		faces_b = class38_sub4_272_.faces_b;
		faces_c = class38_sub4_272_.faces_c;
		faces_type = class38_sub4_272_.faces_type;
		faces_layer = class38_sub4_272_.faces_layer;
		faces_texture = class38_sub4_272_.faces_texture;
		priority = class38_sub4_272_.priority;
		textures_mapping = class38_sub4_272_.textures_mapping;
		textures_mapping_p = class38_sub4_272_.textures_mapping_p;
		textures_mapping_m = class38_sub4_272_.textures_mapping_m;
		textures_mapping_n = class38_sub4_272_.textures_mapping_n;
		textures_scale_x = class38_sub4_272_.textures_scale_x;
		textures_scale_y = class38_sub4_272_.textures_scale_y;
		textures_scale_z = class38_sub4_272_.textures_scale_z;
		textures_rotation = class38_sub4_272_.textures_rotation;
		textures_direction = class38_sub4_272_.textures_direction;
		textures_speed = class38_sub4_272_.textures_speed;
		textures_utrans = class38_sub4_272_.textures_utrans;
		textures_vtrans = class38_sub4_272_.textures_vtrans;
		vertices_label = class38_sub4_272_.vertices_label;
		faces_label = class38_sub4_272_.faces_label;
		vertices_by_label = class38_sub4_272_.vertices_by_label;
		faces_by_label = class38_sub4_272_.faces_by_label;
		vertices_normals_1 = class38_sub4_272_.vertices_normals_1;
		faces_normal = class38_sub4_272_.faces_normal;
		vertices_normals_2 = class38_sub4_272_.vertices_normals_2;
		ambient = class38_sub4_272_.ambient;
		contrast = class38_sub4_272_.contrast;
	}

	public void decode(byte[] data) {
		Packet first = new Packet(data);
		Packet second = new Packet(data);
		Packet third = new Packet(data);
		Packet fourth = new Packet(data);
		Packet fifth = new Packet(data);
		Packet sixth = new Packet(data);
		Packet seventh = new Packet(data);
		first.index = data.length - 23;
		num_vertices = first.g2();
		num_faces = first.g2();
		num_textures = first.g1();
		int footerFlags = first.g1();
		boolean hasFaceTypes = (footerFlags & 0x1) == 1;
		boolean hasParticleEffects = (footerFlags & 0x2) == 2;
		boolean hasBillboards = (footerFlags & 0x4) == 4;
		boolean hasVersion = (footerFlags & 0x8) == 8;
		boolean hasExtendedVertexSkins = (footerFlags & 0x10) == 16;
		boolean hasExtendedFaceSkins = (footerFlags & 0x20) == 32;
		boolean hasExtendedBillboards = (footerFlags & 0x40) == 64;
		if (hasVersion) {
			first.index -= 7;
			version = first.g1();
			first.index += 6;
		}
		int modelPriority = first.g1();
		int hasFaceAlpha = first.g1();
		int hasFaceSkins = first.g1();
		int hasFaceTextures = first.g1();
		int hasVertexSkins = first.g1();

		int modelVerticesX = first.g2();
		int modelVerticesY = first.g2();
		int modelVerticesZ = first.g2();
		int faceIndices = first.g2();
		int textureIndices = first.g2();
		int numVertexSkins;
		if (hasExtendedVertexSkins) {
			numVertexSkins = first.g2();
		} else if (hasVertexSkins == 1) {
			numVertexSkins = num_vertices;
		} else {
			numVertexSkins = 0;
		}
		int numFaceSkins;
		if (hasExtendedFaceSkins) {
			numFaceSkins = first.g2();
		} else if (hasFaceSkins == 1) {
			numFaceSkins = num_faces;
		} else {
			numFaceSkins = 0;
		}

		int simpleTextureFaceCount = 0;
		int complexTextureFaceCount = 0;
		int cubeTextureFaceCount = 0;
		if (num_textures > 0) {
			textures_mapping = new byte[num_textures];
			first.index = 0;
			for (int texture = 0; texture < num_textures; texture++) {
				byte type = textures_mapping[texture] = first.g1s();
				if (type == 0) {
					simpleTextureFaceCount++;
				}
				if (type >= 1 && type <= 3) {
					complexTextureFaceCount++;
				}
				if (type == 2) {
					cubeTextureFaceCount++;
				}
			}
		}
		int index = num_textures;
		int vertexFlagsOffset = index;
		index += num_vertices;
		int faceTypesOffset = index;
		if (hasFaceTypes) {
			index += num_faces;
		}
		int faceIndexTypesOffset = index;
		index += num_faces;
		int facePrioritiesOffset = index;
		if (modelPriority == 255) {
			index += num_faces;
		}
		int faceSkinsOffset = index;
		index += numFaceSkins;
		int vertexSkinsOffset = index;
		index += numVertexSkins;
		int faceAlphasOffset = index;
		if (hasFaceAlpha == 1) {
			index += num_faces;
		}
		int faceIndicesOffset = index;
		index += faceIndices;
		int faceMaterialsOffset = index;
		if (hasFaceTextures == 1) {
			index += num_faces * 2;
		}
		int faceTextureIndicesOffset = index;
		index += textureIndices;
		int faceColorsOffset = index;
		index += num_faces * 2;
		int vertexXOffsetOffset = index;
		index += modelVerticesX;
		int vertexYOffsetOffset = index;
		index += modelVerticesY;
		int vertexZOffsetOffset = index;
		index += modelVerticesZ;
		int simpleTexturesOffset = index;
		index += simpleTextureFaceCount * 6;
		int complexTexturesOffset = index;
		index += complexTextureFaceCount * 6;
		int textureBytes = 6;
		if (version == 14) {
			textureBytes = 7;
		} else if (version >= 15) {
			textureBytes = 9;
		}
		int texturesScaleOffset = index;
		index += complexTextureFaceCount * textureBytes;
		int texturesRotationOffset = index;
		index += complexTextureFaceCount;
		int texturesDirectionOffset = index;
		index += complexTextureFaceCount;
		int texturesTranslationOffset = index;
		index += complexTextureFaceCount + cubeTextureFaceCount * 2;
		int particleEffectsOffset = index;
		vertices_x = new int[num_vertices];
		vertices_y = new int[num_vertices];
		vertices_z = new int[num_vertices];
		faces_a = new int[num_faces];
		faces_b = new int[num_faces];
		faces_c = new int[num_faces];
		if (hasVertexSkins == 1) {
			vertices_label = new int[num_vertices];
		}
		if (hasFaceTypes) {
			faces_type = new byte[num_faces];
		}
		if (modelPriority == 255) {
			faces_layer = new byte[num_faces];
		} else {
			priority = (byte) modelPriority;
		}
		if (hasFaceAlpha == 1) {
			faces_alpha = new byte[num_faces];
		}
		if (hasFaceSkins == 1) {
			faces_label = new int[num_faces];
		}
		if (hasFaceTextures == 1) {
			faces_material = new short[num_faces];
		}
		if (hasFaceTextures == 1 && num_textures > 0) {
			faces_texture = new byte[num_faces];
		}
		faces_colour = new short[num_faces];
		if (num_textures > 0) {
			textures_mapping_p = new short[num_textures];
			textures_mapping_m = new short[num_textures];
			textures_mapping_n = new short[num_textures];
			if (complexTextureFaceCount > 0) {
				textures_scale_x = new short[complexTextureFaceCount];
				textures_scale_y = new short[complexTextureFaceCount];
				textures_scale_z = new short[complexTextureFaceCount];
				textures_rotation = new byte[complexTextureFaceCount];
				textures_direction = new byte[complexTextureFaceCount];
				textures_speed = new byte[complexTextureFaceCount];
			}
			if (cubeTextureFaceCount > 0) {
				textures_utrans = new byte[cubeTextureFaceCount];
				textures_vtrans = new byte[cubeTextureFaceCount];
			}
		}
		first.index = vertexFlagsOffset;
		second.index = vertexXOffsetOffset;
		third.index = vertexYOffsetOffset;
		fourth.index = vertexZOffsetOffset;
		fifth.index = vertexSkinsOffset;
		int baseX = 0;
		int baseY = 0;
		int baseZ = 0;
		for (int vertex = 0; vertex < num_vertices; vertex++) {
			int pflag = first.g1();
			int xOffset = 0;
			if ((pflag & 0x1) != 0) {
				xOffset = second.gSmart1or2s();
			}
			int yOffset = 0;
			if ((pflag & 0x2) != 0) {
				yOffset = third.gSmart1or2s();
			}
			int zOffset = 0;
			if ((pflag & 0x4) != 0) {
				zOffset = fourth.gSmart1or2s();
			}
			vertices_x[vertex] = baseX + xOffset;
			vertices_y[vertex] = baseY + yOffset;
			vertices_z[vertex] = baseZ + zOffset;
			baseX = vertices_x[vertex];
			baseY = vertices_y[vertex];
			baseZ = vertices_z[vertex];
			if (hasVertexSkins == 1) {
				if (hasExtendedVertexSkins) {
					vertices_label[vertex] = fifth.gSmart1or2n();
				} else {
					vertices_label[vertex] = fifth.g1();
					if (vertices_label[vertex] == 255) {
						vertices_label[vertex] = -1;
					}
				}
			}
		}
		first.index = faceColorsOffset;
		second.index = faceTypesOffset;
		third.index = facePrioritiesOffset;
		fourth.index = faceAlphasOffset;
		fifth.index = faceSkinsOffset;
		sixth.index = faceMaterialsOffset;
		seventh.index = faceTextureIndicesOffset;
		for (int face = 0; face < num_faces; face++) {
			faces_colour[face] = (short) first.g2();
			if (hasFaceTypes) {
				faces_type[face] = second.g1s();
			}
			if (modelPriority == 255) {
				faces_layer[face] = third.g1s();
			}
			if (hasFaceAlpha == 1) {
				faces_alpha[face] = fourth.g1s();
			}
			if (hasFaceSkins == 1) {
				if (hasExtendedFaceSkins) {
					faces_label[face] = fifth.gSmart1or2n();
				} else {
					faces_label[face] = fifth.g1();
					if (faces_label[face] == 255) {
						faces_label[face] = -1;
					}
				}
			}
			if (hasFaceTextures == 1) {
				faces_material[face] = (short) (sixth.g2() - 1);
			}
			if (faces_texture != null) {
				if (faces_material[face] != -1) {
					faces_texture[face] = (byte) (seventh.g1() - 1);
				} else {
					faces_texture[face] = (byte) -1;
				}
			}
		}
		// highestFaceIndex = -1;
		first.index = faceIndicesOffset;
		second.index = faceIndexTypesOffset;
		decodeIndices(first, second);
		first.index = simpleTexturesOffset;
		second.index = complexTexturesOffset;
		third.index = texturesScaleOffset;
		fourth.index = texturesRotationOffset;
		fifth.index = texturesDirectionOffset;
		sixth.index = texturesTranslationOffset;
		decodeMapping(first, second, third, fourth, fifth, sixth);
		first.index = particleEffectsOffset;
		if (hasParticleEffects) {
			int numEmitters = first.g1();
			if (numEmitters > 0) {
				// emitters = new EmissiveTriangle[numEmitters];
				for (int emitter_index = 0; emitter_index < numEmitters; emitter_index++) {
					int emitter = first.g2();
					int triangle = first.g2();
					byte pri;
					if (modelPriority == 255) {
						pri = faces_layer[triangle];
					} else {
						pri = (byte) modelPriority;
					}
					// emitters[index] = new EmissiveTriangle(emitter, triangle, facesA[triangle], facesB[triangle], facesC[triangle], pri);
				}
			}
			int numEffectors = first.g1();
			if (numEffectors > 0) {
				// effectors = new EffectiveVertex[numEffectors];
				for (int face = 0; face < numEffectors; face++) {
					int effector = first.g2();
					int vertex = first.g2();
					// effectors[face] = new EffectiveVertex(effector, vertex);
				}
			}
		}
		if (hasBillboards) {
			int numBillboards = first.g1();
			if (numBillboards > 0) {
				// billboards = new Billboard[numBillboards];
				for (int vertex = 0; vertex < numBillboards; vertex++) {
					int id = first.g2();
					int face = first.g2();
					int depth;
					if (hasExtendedBillboards) {
						depth = first.gSmart1or2n();
					} else {
						depth = first.g1();
						if (depth == 255) {
							depth = -1;
						}
					}
					byte distance = first.g1s();
					// billboards[vertex] = new Billboard(id, face, depth, distance);
				}
			}
		}
		if (version >= 13) {
			scaleDown(2);
		}
	}

	public void decodeLegacy(byte[] is) {
		boolean usesFacesLighting = false;
		boolean usesMaterials = false;
		Packet first = new Packet(is);
		Packet second = new Packet(is);
		Packet third = new Packet(is);
		Packet fourth = new Packet(is);
		Packet fifth = new Packet(is);
		first.index = is.length - 18;
		num_vertices = first.g2();
		num_faces = first.g2();
		num_textures = first.g1();
		int hasFacesLighting = first.g1();
		int modelPriority = first.g1();
		int hasFacesAlpha = first.g1();
		int hasFacesSkins = first.g1();
		int hasVerticesSkins = first.g1();
		int modelVerticesX = first.g2();
		int modelVerticesY = first.g2();
		int modelVerticesZ = first.g2();
		int faces = first.g2();
		int offset = 0;
		int vertexFlagsOffset = offset;
		offset += num_vertices;
		int facesCompressTypeOffset = offset;
		offset += num_faces;
		int facePrioritiesOffset = offset;
		if (modelPriority == 255) {
			offset += num_faces;
		}
		int faceSkinsOffset = offset;
		if (hasFacesSkins == 1) {
			offset += num_faces;
		}
		int faceTypesOffset = offset;
		if (hasFacesLighting == 1) {
			offset += num_faces;
		}
		int vertexSkinsOffset = offset;
		if (hasVerticesSkins == 1) {
			offset += num_vertices;
		}
		int faceAlphasOffset = offset;
		if (hasFacesAlpha == 1) {
			offset += num_faces;
		}
		int faceIndicesOffset = offset;
		offset += faces;
		int faceColorsOffset = offset;
		offset += num_faces * 2;
		int faceMappingsOffset = offset;
		offset += num_textures * 6;
		int vertexXOffsetOffset = offset;
		offset += modelVerticesX;
		int vertexYOffsetOffset = offset;
		offset += modelVerticesY;
		int vertexZOffsetOffset = offset;
		offset += modelVerticesZ;
		vertices_x = new int[num_vertices];
		vertices_y = new int[num_vertices];
		vertices_z = new int[num_vertices];
		faces_a = new int[num_faces];
		faces_b = new int[num_faces];
		faces_c = new int[num_faces];
		if (num_textures > 0) {
			textures_mapping = new byte[num_textures];
			textures_mapping_p = new short[num_textures];
			textures_mapping_m = new short[num_textures];
			textures_mapping_n = new short[num_textures];
		}
		if (hasVerticesSkins == 1) {
			vertices_label = new int[num_vertices];
		}
		if (hasFacesLighting == 1) {
			faces_type = new byte[num_faces];
			faces_texture = new byte[num_faces];
			faces_material = new short[num_faces];
		}
		if (modelPriority == 255) {
			faces_layer = new byte[num_faces];
		} else {
			priority = (byte) modelPriority;
		}
		if (hasFacesAlpha == 1) {
			faces_alpha = new byte[num_faces];
		}
		if (hasFacesSkins == 1) {
			faces_label = new int[num_faces];
		}
		faces_colour = new short[num_faces];
		first.index = vertexFlagsOffset;
		second.index = vertexXOffsetOffset;
		third.index = vertexYOffsetOffset;
		fourth.index = vertexZOffsetOffset;
		fifth.index = vertexSkinsOffset;
		int baseX = 0;
		int baseY = 0;
		int baseZ = 0;
		for (int vertex = 0; vertex < num_vertices; vertex++) {
			int pflag = first.g1();
			int xOffset = 0;
			if ((pflag & 0x1) != 0) {
				xOffset = second.gSmart1or2s();
			}
			int yOffset = 0;
			if ((pflag & 0x2) != 0) {
				yOffset = third.gSmart1or2s();
			}
			int zOffset = 0;
			if ((pflag & 0x4) != 0) {
				zOffset = fourth.gSmart1or2s();
			}
			vertices_x[vertex] = baseX + xOffset;
			vertices_y[vertex] = baseY + yOffset;
			vertices_z[vertex] = baseZ + zOffset;
			baseX = vertices_x[vertex];
			baseY = vertices_y[vertex];
			baseZ = vertices_z[vertex];
			if (hasVerticesSkins == 1) {
				vertices_label[vertex] = fifth.g1();
			}
		}
		first.index = faceColorsOffset;
		second.index = faceTypesOffset;
		third.index = facePrioritiesOffset;
		fourth.index = faceAlphasOffset;
		fifth.index = faceSkinsOffset;
		for (int face = 0; face < num_faces; face++) {
			faces_colour[face] = (short) first.g2();
			if (hasFacesLighting == 1) {
				int flag = second.g1();
				if ((flag & 0x1) == 1) {
					faces_type[face] = (byte) 1;
					usesFacesLighting = true;
				} else {
					faces_type[face] = (byte) 0;
				}
				if ((flag & 0x2) == 2) {
					faces_texture[face] = (byte) (flag >> 2);
					faces_material[face] = faces_colour[face];
					faces_colour[face] = (short) 127;
					if (faces_material[face] != -1) {
						usesMaterials = true;
					}
				} else {
					faces_texture[face] = (byte) -1;
					faces_material[face] = (short) -1;
				}
			}
			if (modelPriority == 255) {
				faces_layer[face] = third.g1s();
			}
			if (hasFacesAlpha == 1) {
				faces_alpha[face] = fourth.g1s();
			}
			if (hasFacesSkins == 1) {
				faces_label[face] = fifth.g1();
			}
		}
		// highestFaceIndex = -1;
		first.index = faceIndicesOffset;
		second.index = facesCompressTypeOffset;
		short a = 0;
		short b = 0;
		short c = 0;
		int acc = 0;
		for (int face = 0; face < num_faces; face++) {
			int type = second.g1();
			if (type == 1) {
				a = (short) (first.gSmart1or2s() + acc);
				acc = a;
				b = (short) (first.gSmart1or2s() + acc);
				acc = b;
				c = (short) (first.gSmart1or2s() + acc);
				acc = c;
				faces_a[face] = a;
				faces_b[face] = b;
				faces_c[face] = c;
				// if (a > highestFaceIndex) {
				// highestFaceIndex = a;
				// }
				// if (b > highestFaceIndex) {
				// highestFaceIndex = b;
				// }
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
			if (type == 2) {
				b = c;
				c = (short) (first.gSmart1or2s() + acc);
				acc = c;
				faces_a[face] = a;
				faces_b[face] = b;
				faces_c[face] = c;
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
			if (type == 3) {
				a = c;
				c = (short) (first.gSmart1or2s() + acc);
				acc = c;
				faces_a[face] = a;
				faces_b[face] = b;
				faces_c[face] = c;
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
			if (type == 4) {
				short i_149_ = a;
				a = b;
				b = i_149_;
				c = (short) (first.gSmart1or2s() + acc);
				acc = c;
				faces_a[face] = a;
				faces_b[face] = b;
				faces_c[face] = c;
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
		}
		// highestFaceIndex++;
		first.index = faceMappingsOffset;
		for (int texture = 0; texture < num_textures; texture++) {
			textures_mapping[texture] = (byte) 0;
			textures_mapping_p[texture] = (short) first.g2();
			textures_mapping_m[texture] = (short) first.g2();
			textures_mapping_n[texture] = (short) first.g2();
		}
		if (faces_texture != null) {
			boolean usesMapping = false;
			for (int face = 0; face < num_faces; face++) {
				int texture = faces_texture[face] & 0xff;
				if (texture != 255) {
					if ((textures_mapping_p[texture] & 0xffff) == faces_a[face] && (textures_mapping_m[texture] & 0xffff) == faces_b[face] && (textures_mapping_n[texture] & 0xffff) == faces_c[face]) {
						faces_texture[face] = (byte) -1;
					} else {
						usesMapping = true;
					}
				}
			}
			if (!usesMapping) {
				faces_texture = null;
			}
		}
		if (!usesMaterials) {
			faces_material = null;
		}
		if (!usesFacesLighting) {
			faces_type = null;
		}
	}

	public void decodeIndices(Packet indicesBuffer, Packet typesBuffer) {
		short a = 0;
		short b = 0;
		short c = 0;
		int pacc = 0;
		for (int faceIndex = 0; faceIndex < num_faces; faceIndex++) {
			int type = typesBuffer.g1();
			if (type == 1) {
				a = (short) (indicesBuffer.gSmart1or2s() + pacc);
				pacc = a;
				b = (short) (indicesBuffer.gSmart1or2s() + pacc);
				pacc = b;
				c = (short) (indicesBuffer.gSmart1or2s() + pacc);
				pacc = c;
				faces_a[faceIndex] = a;
				faces_b[faceIndex] = b;
				faces_c[faceIndex] = c;
				// if (a > highestFaceIndex) {
				// highestFaceIndex = a;
				// }
				// if (b > highestFaceIndex) {
				// highestFaceIndex = b;
				// }
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
			if (type == 2) {
				b = c;
				c = (short) (indicesBuffer.gSmart1or2s() + pacc);
				pacc = c;
				faces_a[faceIndex] = a;
				faces_b[faceIndex] = b;
				faces_c[faceIndex] = c;
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
			if (type == 3) {
				a = c;
				c = (short) (indicesBuffer.gSmart1or2s() + pacc);
				pacc = c;
				faces_a[faceIndex] = a;
				faces_b[faceIndex] = b;
				faces_c[faceIndex] = c;
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
			if (type == 4) {
				short bk = a;
				a = b;
				b = bk;
				c = (short) (indicesBuffer.gSmart1or2s() + pacc);
				pacc = c;
				faces_a[faceIndex] = a;
				faces_b[faceIndex] = b;
				faces_c[faceIndex] = c;
				// if (c > highestFaceIndex) {
				// highestFaceIndex = c;
				// }
			}
		}
		// highestFaceIndex++;
	}

	public void decodeMapping(Packet simpleBuffer, Packet complexBuffer, Packet scaleBuffer, Packet rotationBuffer, Packet directionBuffer, Packet translationBuffer) {
		for (int face = 0; face < num_textures; face++) {
			int type = textures_mapping[face] & 0xff;
			if (type == 0) {
				textures_mapping_p[face] = (short) simpleBuffer.g2();
				textures_mapping_m[face] = (short) simpleBuffer.g2();
				textures_mapping_n[face] = (short) simpleBuffer.g2();
			}
			if (type == 1) {
				textures_mapping_p[face] = (short) complexBuffer.g2();
				textures_mapping_m[face] = (short) complexBuffer.g2();
				textures_mapping_n[face] = (short) complexBuffer.g2();
				if (version < 15) {
					textures_scale_x[face] = (short) scaleBuffer.g2();
					if (version < 14) {
						textures_scale_y[face] = (short) scaleBuffer.g2();
					} else {
						textures_scale_y[face] = (short) scaleBuffer.g3();
					}
					textures_scale_z[face] = (short) scaleBuffer.g2();
				} else {
					textures_scale_x[face] = (short) scaleBuffer.g3();
					textures_scale_y[face] = (short) scaleBuffer.g3();
					textures_scale_z[face] = (short) scaleBuffer.g3();
				}
				textures_rotation[face] = rotationBuffer.g1s();
				textures_direction[face] = directionBuffer.g1s();
				textures_speed[face] = translationBuffer.g1s();
			}
			if (type == 2) {
				textures_mapping_p[face] = (short) complexBuffer.g2();
				textures_mapping_m[face] = (short) complexBuffer.g2();
				textures_mapping_n[face] = (short) complexBuffer.g2();
				if (version < 15) {
					textures_scale_x[face] = (short) scaleBuffer.g2();
					if (version < 14) {
						textures_scale_y[face] = (short) scaleBuffer.g2();
					} else {
						textures_scale_y[face] = (short) scaleBuffer.g3();
					}
					textures_scale_z[face] = (short) scaleBuffer.g2();
				} else {
					textures_scale_x[face] = (short) scaleBuffer.g3();
					textures_scale_y[face] = (short) scaleBuffer.g3();
					textures_scale_z[face] = (short) scaleBuffer.g3();
				}
				textures_rotation[face] = rotationBuffer.g1s();
				textures_direction[face] = directionBuffer.g1s();
				textures_speed[face] = translationBuffer.g1s();
				textures_utrans[face] = translationBuffer.g1s();
				textures_vtrans[face] = translationBuffer.g1s();
			}
			if (type == 3) {
				textures_mapping_p[face] = (short) complexBuffer.g2();
				textures_mapping_m[face] = (short) complexBuffer.g2();
				textures_mapping_n[face] = (short) complexBuffer.g2();
				if (version < 15) {
					textures_scale_x[face] = (short) scaleBuffer.g2();
					if (version < 14) {
						textures_scale_y[face] = (short) scaleBuffer.g2();
					} else {
						textures_scale_y[face] = (short) scaleBuffer.g3();
					}
					textures_scale_z[face] = (short) scaleBuffer.g2();
				} else {
					textures_scale_x[face] = (short) scaleBuffer.g3();
					textures_scale_y[face] = (short) scaleBuffer.g3();
					textures_scale_z[face] = (short) scaleBuffer.g3();
				}
				textures_rotation[face] = rotationBuffer.g1s();
				textures_direction[face] = directionBuffer.g1s();
				textures_speed[face] = translationBuffer.g1s();
			}
		}
	}

	public int add_face(int v0, int v1, int v2, byte normalType, short color, byte var6) {
		faces_a[num_faces] = v0;
		faces_b[num_faces] = v1;
		faces_c[num_faces] = v2;
		faces_type[num_faces] = normalType;
		faces_texture[num_faces] = -1;
		faces_colour[num_faces] = color;
		faces_material[num_faces] = -1;
		faces_alpha[num_faces] = var6;
		return num_faces++;
	}

	public int add_vertex(int x, int y, int z) {
		for (int var4 = 0; var4 < num_vertices; ++var4) {
			if (vertices_x[var4] == x && vertices_y[var4] == y && vertices_z[var4] == z) {
				return var4;
			}
		}
		vertices_x[num_vertices] = x;
		vertices_y[num_vertices] = y;
		vertices_z[num_vertices] = z;
		return num_vertices++;
	}

	static {
		anIntArray2564 = new int[10000];
	}
}
