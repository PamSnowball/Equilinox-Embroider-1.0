package snowball.embroider.enumerator.classification.specific;

import snowball.embroider.enumerator.classification.IClassifier;

public enum SmallPlantClassifications implements IClassifier {
	//Berry Bush Classifications
	BERRY_BUSH("pbf3"),
	TOMATO_PLANT("pbf62"),
	BLUEBERRY_BUSH("pbf71"),
	HOLY_BUSH("pbf133"),
	
	//Leafy Bush Classifications
	JUNGLE_PLANT("pbl36"),
	BAMBOO("pbl70"),
	TROPICAL_TREE("pbl129"),
	LEAFY_PLANT("pbl130"),
	
	//Cactus Classifications
	CACTUS("pc18"),
	PRICKLY_PEAR("pc19"),
	YUCCA("pc21"),
	SMALL_CACTUS("pc120"),
	GIANT_CACTUS("pc121"),
	
	//Water Plants Classifications
	KELP("pnw13"),
	WATER_LILY("pnw16"),
	SEAWEED("pnw17"),
	TROPICAL_SEAWEED("pnw136"),
	BULRUSH("pnw143"),
	CORAL("pnw165"),
	
	//Grasses Classifications
	WHEAT("png11"),
	GRASS_TUFT("png20"),
	SWAMP_GRASS("png60"),
	JUNGLE_GRASS("png123"),
	FLOWERY_GRASS("png128"),
	BARLEY("png148"),
	LUSH_GRASS("png149"),
	
	//Root Vegetable Classifications
	CARROT("pnv45"),
	POTATO_PLANT("pnv51"),
	TURNIP("pnv142"),
	
	//Fern Classifications
	FERN("pnx10"),
	
	//Flower Classifications
	HEATHER("pnf7"),
	TULIP("pnf80"),
	SNAP_DRAGON("pnf103"),
	JUNGLE_FLOWER("pnf111"),
	DAISY("pnf114"),
	BUTTERCUP("pnf115"),
	POPPY("pnf116"),
	TROPICAL_FLOWER("pnf117"),
	BLUEBELL("pnf118"),
	PRIMROSE("pnf127"),
	SWAMP_FLOWER("pnf144"),
	ROSE("pnf151"),
	LILY("pnf152"),
	SUN_FLOWER("pnf153"),
	PANSIES("pnf154"),
	HEALBLOOM("pnf157"),
	
	//Herb Classifications
	WILD_MINT("pnh105"),
	SAGE("pnh106"),
	OREGANO("pnh107"),
	ROSEMARY("pnh108"),
	
	//Mushroom Classifications
	TALL_MUSHROOM("pnm31"),
	JUNGLE_MUSHROOM("pnm39"),
	RED_MUSHROOM("pnm59"),
	BUTTON_MUSHROOM("pnm119"),
	TROPICAL_MUSHROOM("pnm126");
	
	String classification;
	
	SmallPlantClassifications(String classification) {
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
