package com.snowball.embroider.enumerator;

import biomes.Biome;

public enum Biomes {
	GRASSLAND(Biome.GRASSLAND),
	FOREST(Biome.FOREST),
	RIVER_BED(Biome.RIVER_BED),
	DESERT(Biome.DESERT),
	SNOW(Biome.SNOW),
	JUNGLE(Biome.JUNGLE),
	SWAMP(Biome.SWAMP),
	LUSH(Biome.LUSH),
	WOODLAND(Biome.WOODLAND),
	TROPICAL(Biome.TROPICAL);
	
	private final Biome biome;
	
	Biomes(Biome biome) {
		this.biome = biome;
	}

	public Biome getBiome() {
		return biome;
	}

	public int getId() {
		return biome.getId();
	}

	@Override
	public String toString() {
		return getBiome().toString();
	}
}