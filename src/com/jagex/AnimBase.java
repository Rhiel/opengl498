package com.jagex;

public class AnimBase extends Linkable {

	public int id;
	public int num_transformation;
	public int[] transformation_type;
	public boolean[] shadowed;
	public int[] submeshes;
	public int[][] labels;

	public AnimBase(int id, byte[] data) {
		this.id = id;
		Packet buffer = new Packet(data);
		num_transformation = buffer.g1();
		shadowed = new boolean[num_transformation];
		submeshes = new int[num_transformation];
		transformation_type = new int[num_transformation];
		labels = new int[num_transformation][];
		for (int transfomration = 0; transfomration < num_transformation; transfomration++) {
			transformation_type[transfomration] = buffer.g1();
			if (transformation_type[transfomration] == 6) {
				transformation_type[transfomration] = 2;
			}
		}
		for (int transformation = 0; transformation < num_transformation; transformation++) {
			shadowed[transformation] = buffer.g1() == 1;
		}
		for (int transformation = 0; num_transformation > transformation; transformation++) {
			submeshes[transformation] = buffer.g2();
		}
		for (int transformation = 0; num_transformation > transformation; transformation++) {
			labels[transformation] = new int[buffer.g1()];
		}
		for (int transformation = 0; transformation < num_transformation; transformation++) {
			for (int label = 0; label < labels[transformation].length; label++) {
				labels[transformation][label] = buffer.g1();
			}
		}
	}

}
