package com.snowball.embroider.enumerator;

import com.snowball.embroider.util.component.IBiome;

public enum Biomes implements IBiome {
	GRASSLAND(0),
	FOREST(1),
	RIVER_BED(2),
	DESERT(3),
	SNOW(4),
	JUNGLE(5),
	SWAMP(6),
	LUSH(7),
	WOODLAND(8),
	TROPICAL(9);
	
	private final int id;
	
	Biomes(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}