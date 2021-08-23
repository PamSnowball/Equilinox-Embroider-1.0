package snowball.embroider.enumerator.classification;

public enum BaseClassification implements IClassifier {
	//Plant classifications.
	PLANT("p"),
	
	//Tree classifications
	TREE("pt"),
	
	FOREST_TREE("ptf"),
	GRASS_TREE("ptg"),
	WOODLAND_TREE("ptw"),
	LUSH_TREE("ptl"),
	DESERT_TREE("ptd"),
	SNOW_TREE("ptm"),
	SWAMP_TREE("pts"),
	JUNGLE_TREE("ptj"),
	TROPICAL_TREE("ptt"),
	
	//Bush classifications.
	BUSH("pb"),
	
	FRUIT_BUSH("pbf"),
	LEAFY_BUSH("pbl"),

	CACTUS("pc"),
	
	//Small plant classifications.
	SMALL_PLANT("pn"),
	
	WATER_PLANT("pnw"),
	GRASSES("png"),
	ROOT_VEGETABLE("pnv"),
	VEGETABLE_PLANT("pnp"),
	FERN("pnx"),
	FLOWER("pnf"),
	HERB("pnh"),
	MUSHROOM("pmm"),
	
	//Animal classifications.
	ANIMAL("a"),
	
	//Fish classifications.
	FISH("af"),
	
	SMALL_FISH("afs"),
	BIG_FISH("afb"),
	WEIRD_FISH("afw"),
	
	//Herbivore classifications.
	HERBIVORE("ah"),
	
	LARGE_HERBIVORE("ahl"),
	MEDIUM_HERBIVORE("ahm"),
	SMALL_HERBIVORE("ahs"),
	
	//Bird classifications.
	BIRD("ab"),
	
	SMALL_BIRD("abs"),
	BIRD_OF_PREY("abp"),
	
	//Carnivore classifications.
	CARNIVORE("ac"),
	
	SMALL_CARNIVORE("acs"),
	LARGE_CARNIVORE("acl"),
	
	INSECT("ai"),
	
	REPTILE("ar"),
	
	//Non-Living classifications.
	NON_LIVING("e"),
	
	//Rock classifications
	ROCK("er"),
	
	STONE("ers"),
	LARGE_ROCK("erl"),
	
	//Food classifications.
	FRUIT("ef"),
	VEGETABLE("ev"),
	NUT("en"),
	MEAT("em"),
	
	STRUCTURE("es");

	private final String classification;
	
	BaseClassification(String classification) {
		this.classification = classification;
	}
	
	@Override
	public String getClassification() {
		return this.classification;
	}
	
	@Override
	public String toString() {
		return getType();
	}
}
