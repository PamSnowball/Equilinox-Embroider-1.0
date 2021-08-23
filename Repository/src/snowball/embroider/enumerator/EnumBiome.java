package snowball.embroider.enumerator;

public enum EnumBiome { 
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
	
	private int id;
	
	EnumBiome(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}