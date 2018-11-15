package me.waliedyassen.textures.mapping;

import com.jagex.Mesh;

public final class TextureMapper {

	public static final float PI = 3.1415927F;
	public static final float PI2 = PI * 2;

	public static TextureTransformationMatrix create_texture_matrix(Mesh mesh, int[] faces, int num_faces) {
		int[] centerX = null;
		int[] centerY = null;
		int[] centerZ = null;
		float[][] matrix = null;
		if (mesh.faces_texture != null) {
			int num_textured_faces = mesh.num_textures;
			int[] minX = new int[num_textured_faces];
			int[] maxX = new int[num_textured_faces];
			int[] minY = new int[num_textured_faces];
			int[] maxY = new int[num_textured_faces];
			int[] minZ = new int[num_textured_faces];
			int[] maxZ = new int[num_textured_faces];
			for (int i_30_ = 0; i_30_ < num_textured_faces; i_30_++) {
				minX[i_30_] = Integer.MAX_VALUE;
				maxX[i_30_] = -Integer.MAX_VALUE;
				minY[i_30_] = Integer.MAX_VALUE;
				maxY[i_30_] = -Integer.MAX_VALUE;
				minZ[i_30_] = Integer.MAX_VALUE;
				maxZ[i_30_] = -Integer.MAX_VALUE;
			}
			for (int face_index = 0; face_index < num_faces; face_index++) {
				int face = faces[face_index];
				short index = mesh.faces_texture[face];
				if (index != -1) {
					for (int axis = 0; axis < 3; axis++) {
						int point;
						if (axis == 0) {
							point = mesh.faces_a[face];
						} else if (axis == 1) {
							point = mesh.faces_b[face];
						} else {
							point = mesh.faces_c[face];
						}
						int x = mesh.vertices_x[point];
						int y = mesh.vertices_y[point];
						int z = mesh.vertices_z[point];
						if (x < minX[index]) {
							minX[index] = x;
						}
						if (x > maxX[index]) {
							maxX[index] = x;
						}
						if (y < minY[index]) {
							minY[index] = y;
						}
						if (y > maxY[index]) {
							maxY[index] = y;
						}
						if (z < minZ[index]) {
							minZ[index] = z;
						}
						if (z > maxZ[index]) {
							maxZ[index] = z;
						}
					}
				}
			}
			centerX = new int[num_textured_faces];
			centerY = new int[num_textured_faces];
			centerZ = new int[num_textured_faces];
			matrix = new float[num_textured_faces][];
			for (int face_index = 0; face_index < num_textured_faces; face_index++) {
				byte mapping = mesh.textures_mapping[face_index];
				if (mapping > 0) {
					centerX[face_index] = (minX[face_index] + maxX[face_index]) / 2;
					centerY[face_index] = (minY[face_index] + maxY[face_index]) / 2;
					centerZ[face_index] = (minZ[face_index] + maxZ[face_index]) / 2;
					float scaleX;
					float scaleY;
					float scaleZ;
					if (mapping == 1) {
						int x = mesh.textures_scale_x[face_index];
						if (x == 0) {
							scaleX = 1.0F;
							scaleZ = 1.0F;
						} else if (x > 0) {
							scaleX = 1.0F;
							scaleZ = x / 1024.0F;
						} else {
							scaleZ = 1.0F;
							scaleX = -x / 1024.0F;
						}
						scaleY = 64.0F / mesh.textures_scale_y[face_index];
					} else if (mapping == 2) {
						scaleX = 64.0F / mesh.textures_scale_x[face_index];
						scaleY = 64.0F / mesh.textures_scale_y[face_index];
						scaleZ = 64.0F / mesh.textures_scale_z[face_index];
					} else {
						scaleX = mesh.textures_scale_x[face_index] / 1024.0F;
						scaleY = mesh.textures_scale_y[face_index] / 1024.0F;
						scaleZ = mesh.textures_scale_z[face_index] / 1024.0F;
					}
					matrix[face_index] = create_texture_matrix(mesh.textures_mapping_p[face_index], mesh.textures_mapping_m[face_index], mesh.textures_mapping_n[face_index], mesh.textures_rotation[face_index] & 0xff, scaleX, scaleY, scaleZ);
				}
			}
		}
		return new TextureTransformationMatrix(centerX, centerY, centerZ, matrix);
	}

	public static float[] create_texture_matrix(int p, int m, int n, int rotation, float scale_x, float scale_y, float scale_z) {
		float[] rotm = new float[9];
		float[] result = new float[9];
		float cos = (float) Math.cos(rotation * 0.024543693F);
		float sin = (float) Math.sin(rotation * 0.024543693F);
		float omc = 1.0F - cos;
		rotm[0] = cos;
		rotm[1] = 0.0F;
		rotm[2] = sin;
		rotm[3] = 0.0F;
		rotm[4] = 1.0F;
		rotm[5] = 0.0F;
		rotm[6] = -sin;
		rotm[7] = 0.0F;
		rotm[8] = cos;
		float[] matrix33 = new float[9];
		float x = 1.0F;
		float y = 0.0F;
		cos = m / 32767.0F;
		sin = -(float) Math.sqrt(1.0F - cos * cos);
		omc = 1.0F - cos;
		float size = (float) Math.sqrt(p * p + n * n);
		if (size == 0.0F && cos == 0.0F) {
			result = rotm;
		} else {
			if (size != 0.0F) {
				x = -n / size;
				y = p / size;
			}
			matrix33[0] = cos + x * x * omc;
			matrix33[1] = y * sin;
			matrix33[2] = y * x * omc;
			matrix33[3] = -y * sin;
			matrix33[4] = cos;
			matrix33[5] = x * sin;
			matrix33[6] = x * y * omc;
			matrix33[7] = -x * sin;
			matrix33[8] = cos + y * y * omc;
			result[0] = rotm[0] * matrix33[0] + rotm[1] * matrix33[3] + rotm[2] * matrix33[6];
			result[1] = rotm[0] * matrix33[1] + rotm[1] * matrix33[4] + rotm[2] * matrix33[7];
			result[2] = rotm[0] * matrix33[2] + rotm[1] * matrix33[5] + rotm[2] * matrix33[8];
			result[3] = rotm[3] * matrix33[0] + rotm[4] * matrix33[3] + rotm[5] * matrix33[6];
			result[4] = rotm[3] * matrix33[1] + rotm[4] * matrix33[4] + rotm[5] * matrix33[7];
			result[5] = rotm[3] * matrix33[2] + rotm[4] * matrix33[5] + rotm[5] * matrix33[8];
			result[6] = rotm[6] * matrix33[0] + rotm[7] * matrix33[3] + rotm[8] * matrix33[6];
			result[7] = rotm[6] * matrix33[1] + rotm[7] * matrix33[4] + rotm[8] * matrix33[7];
			result[8] = rotm[6] * matrix33[2] + rotm[7] * matrix33[5] + rotm[8] * matrix33[8];
		}
		result[0] *= scale_x;
		result[1] *= scale_x;
		result[2] *= scale_x;
		result[3] *= scale_y;
		result[4] *= scale_y;
		result[5] *= scale_y;
		result[6] *= scale_z;
		result[7] *= scale_z;
		result[8] *= scale_z;
		return result;
	}

	public static void rectangularUV(int x, int y, int z, int sx, int sy, int sz, int face, float[] sc, int direction, float zOff, float yOff, float xOff, float[] out) {
		x -= sx;
		y -= sy;
		z -= sz;
		float xp = x * sc[0] + y * sc[1] + z * sc[2];
		float yp = x * sc[3] + y * sc[4] + z * sc[5];
		float zp = x * sc[6] + y * sc[7] + z * sc[8];
		float u;
		float v;
		if (face == 0) {
			u = xp + zOff + 0.5F;
			v = -zp + xOff + 0.5F;
		} else if (face == 1) {
			u = xp + zOff + 0.5F;
			v = zp + xOff + 0.5F;
		} else if (face == 2) {
			u = -xp + zOff + 0.5F;
			v = -yp + yOff + 0.5F;
		} else if (face == 3) {
			u = xp + zOff + 0.5F;
			v = -yp + yOff + 0.5F;
		} else if (face == 4) {
			u = zp + xOff + 0.5F;
			v = -yp + yOff + 0.5F;
		} else {
			u = -zp + xOff + 0.5F;
			v = -yp + yOff + 0.5F;
		}
		if (direction == 1) {
			float tu = u;
			u = -v;
			v = tu;
		} else if (direction == 2) {
			u = -u;
			v = -v;
		} else if (direction == 3) {
			float tu = u;
			u = v;
			v = -tu;
		}
		out[0] = u;
		out[1] = v;
	}

	public static void sphericalUV(int x, int y, int z, int sx, int sy, int sz, float[] sc, int direction, float speed, float[] out) {
		x -= sx;
		y -= sy;
		z -= sz;
		float ex = x * sc[0] + y * sc[1] + z * sc[2];
		float ey = x * sc[3] + y * sc[4] + z * sc[5];
		float ez = x * sc[6] + y * sc[7] + z * sc[8];
		float distance = (float) Math.sqrt(ex * ex + ey * ey + ez * ez);
		float u = (float) Math.atan2(ex, ez) / PI2 + 0.5F;
		float v = (float) Math.asin(ey / distance) / PI + 0.5F + speed;
		if (direction == 1) {
			float f_13_ = u;
			u = -v;
			v = f_13_;
		} else if (direction == 2) {
			u = -u;
			v = -v;
		} else if (direction == 3) {
			float f_14_ = u;
			u = v;
			v = -f_14_;
		}
		out[0] = u;
		out[1] = v;
	}

	public static void cylindricalUV(int x, int y, int z, int cx, int cy, int cz, float[] m, float scale, int direction, float f_6_, float[] out) {
		x -= cx;
		y -= cy;
		z -= cz;
		float xp = x * m[0] + y * m[1] + z * m[2];
		float yp = x * m[3] + y * m[4] + z * m[5];
		float zp = x * m[6] + y * m[7] + z * m[8];
		float u = (float) Math.atan2(xp, zp) / 6.2831855F + 0.5F;
		if (scale != 1.0F) {
			u *= scale;
		}
		float v = yp + 0.5F + f_6_;
		if (direction == 1) {
			float bku = u;
			u = -v;
			v = bku;
		} else if (direction == 2) {
			u = -u;
			v = -v;
		} else if (direction == 3) {
			float bku = u;
			u = v;
			v = -bku;
		}
		out[0] = u;
		out[1] = v;
	}

	public static int getCubeFace(float x, float y, float z) {
		float f_17_ = x < 0.0F ? -x : x;
		float f_18_ = y < 0.0F ? -y : y;
		float f_19_ = z < 0.0F ? -z : z;
		if (f_18_ > f_17_ && f_18_ > f_19_) {
			if (y > 0.0F) {
				return 0;
			}
			return 1;
		}
		if (f_19_ > f_17_ && f_19_ > f_18_) {
			if (z > 0.0F) {
				return 2;
			}
			return 3;
		}
		if (x > 0.0F) {
			return 4;
		}
		return 5;
	}

}
