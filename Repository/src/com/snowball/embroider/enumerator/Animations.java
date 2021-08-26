package com.snowball.embroider.enumerator;

public enum Animations {
	COMMON(0),
	THROWING(1),
	DIGGING(2),
	DIVING(3),
	INSTANT(4);
	
	private final int id;
	
	Animations(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}