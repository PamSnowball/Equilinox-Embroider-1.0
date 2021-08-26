package com.snowball.embroider.util;

import org.lwjgl.util.vector.Vector3f;

public class Vector {
	private final float x;
	private final float y;
	private final float z;
	
	public Vector(int x, int y, int z) {
		this.x = (float) (x / 255D);
		this.y = (float) (y / 255D);
		this.z = (float) (z / 255D);
	}
	
	public Vector(String hex) {
		Vector v = fromHex(hex);
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}
	
	public String value() { return
		   String.format("%.4f", getX()) + ";" +
		   String.format("%.4f", getY()) + ";" +
		   String.format("%.4f", getZ());
	}
	
	public static Vector fromHex(String hex) {
		return new Vector(
			Integer.valueOf(hex.substring(1, 3), 16),
			Integer.valueOf(hex.substring(3, 5), 16),
			Integer.valueOf(hex.substring(5, 7), 16)
		);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}

	public static Vector valueOf(Vector3f vector) {
		return new Vector((int) vector.getX(), (int) vector.getY(), (int) vector.getZ());
	}

	@Override
	public String toString() {
		return value();
	}
}
