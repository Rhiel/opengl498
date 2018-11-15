package me.waliedyassen.textures.mapping;


public class TextureTransformationMatrix {
	
	public int[] faceCenterX;
	public int[] faceCenterY;
	public int[] faceCenterZ;
	public float[][] textureMatrix;
	
	public TextureTransformationMatrix(int[] verticesX, int[] verticesY, int[] verticesZ, float[][] matrix) {
		faceCenterX = verticesX;
		faceCenterY = verticesY;
		faceCenterZ = verticesZ;
		textureMatrix = matrix;
	}
}
