package com.jagex;

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.rs2.client.scene.Scene;

import me.waliedyassen.graphics.rasterizer.Rasterizer;

public class Camera {
	public static int ZOOM = 600;
	public static boolean cameraViewChanged;
	public static int xCameraCurve;
	public static int yCameraPos;
	public static int xCameraPos;
	public static int zCameraPos;
	public static int yCameraCurve;
	public static int cameraRotationZ = 128;
	public static int currentCameraDisplayX;
	public static int currentCameraDisplayY;

	public static final void set_camera_pos(int var6, int ycurve, int var3, int xcurve, int var4, int var0, int var2) {
		if (GLManager.opengl_mode) {
			int var8 = -334 + var2;
			if (var8 >= 0) {
				if (-101 > ~var8) {
					var8 = 100;
				}
			} else {
				var8 = 0;
			}
			int var9 = var8 * (-Scene.zoomx + Scene.zoomy) / 100 + Scene.zoomx;
			var4 = var9 * var4 >> 8;
		}
		int l1 = 2048 + -ycurve & 0x7ff;
		int i2 = 0x7ff & 2048 - xcurve;
		int var11 = 0;
		int var10 = 0;
		int var12 = var4;
		if (l1 != 0) {
			int i_12_ = Rasterizer.SINE[l1];
			int i_13_ = Rasterizer.COSINE[l1];
			int i_14_ = i_12_ * -var12 >> 16;
			var12 = var12 * i_13_ >> 16;
			var11 = i_14_;
		}
		if (i2 != 0) {
			int i_15_ = Rasterizer.SINE[i2];
			int i_16_ = Rasterizer.COSINE[i2];
			int i_17_ = var12 * i_15_ >> 16;
			var10 = i_17_;
			var12 = var12 * i_16_ >> 16;
		}
		xCameraCurve = xcurve;
		yCameraCurve = ycurve;
		xCameraPos = var0 - var10;
		yCameraPos = var6 - var12;
		zCameraPos = var3 - var11;
	}

	public static final void method311(boolean bool) {
		if (!StaticMethods2.keysPressed[96]) {
			if (!StaticMethods2.keysPressed[97]) {
				Class23_Sub10_Sub3.anInt3660 /= 2;
			} else {
				Class23_Sub10_Sub3.anInt3660 += (24 - Class23_Sub10_Sub3.anInt3660) / 2;
			}
		} else {
			Class23_Sub10_Sub3.anInt3660 += (-24 + -Class23_Sub10_Sub3.anInt3660) / 2;
		}
		Class35.cameraDirection += Class23_Sub10_Sub3.anInt3660 / 2;
		if (!StaticMethods2.keysPressed[98]) {
			if (StaticMethods2.keysPressed[99]) {
				ObjectNode.anInt2251 += (-12 + -ObjectNode.anInt2251) / 2;
			} else {
				ObjectNode.anInt2251 /= 2;
			}
		} else {
			ObjectNode.anInt2251 += (-ObjectNode.anInt2251 + 12) / 2;
		}
		cameraRotationZ += ObjectNode.anInt2251 / 2;
		int i = Class87_Sub4.anInt2841 + GameClient.currentPlayer.bound_extents_x;
		int i_64_ = Huffman.anInt1815 + GameClient.currentPlayer.bound_extents_z;
		if (-i + currentCameraDisplayX < -500 || -i + currentCameraDisplayX > 500 || -i_64_ + currentCameraDisplayY < -500 || -i_64_ + currentCameraDisplayY > 500) {
			currentCameraDisplayY = i_64_;
			currentCameraDisplayX = i;
		}
		if (i != currentCameraDisplayX) {
			currentCameraDisplayX += (-currentCameraDisplayX + i) / 16;
		}
		if (i_64_ != currentCameraDisplayY) {
			currentCameraDisplayY += (-currentCameraDisplayY + i_64_) / 16;
		}
		Huffman.method1576((byte) 50);
		if (bool != true) {
			Scene.surface_heightmap = null;
		}
	}

	public static int xCurveSine;
	public static int xCurveCosine;
	public static int yCurveSine;
	public static int yCurveCosine;
	public static int xCameraPosition;
	public static int yCameraPosition;
	public static int zCameraPosition;
	public static int xCameraGridPosition;
	public static int yCameraGridPosition;
}
