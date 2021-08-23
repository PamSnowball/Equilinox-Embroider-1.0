package snowball.embroider.enumerator.classification.specific;

import snowball.embroider.enumerator.classification.IClassifier;

public enum LargePlantClassification implements IClassifier {
	//Forest Tree Classifications
	JUNIPER_TREE("ptf4"),
	ACER_TREE("ptf5"),
	TALL_TREE("ptf28"),
	LARGE_TREE("ptf54"),
	CEDAR_TREE("ptf81"),
	
	//Grass Tree Classifications
	BIRCH_TREE("ptf25"),
	EUCALYPTUS_TREE("ptf66"),
	WOBBLY_TREE("ptg113"),
	RED_MAPLE_TREE("ptg135"),
	PAGODA_TREE("ptg155"),
	AUTUMNAL_TREE("ptg156"),
	
	//Woodland Tree Classifications
	OAK_TREE("ptw2"),
	APPLE_TREE("ptw33"),
	SYCAMORE_TREE("ptw69"),
	ELM_TREE("ptw112"),
	NUT_TREE("ptw137"),
	ASH_TREE("ptw150"),
	
	//Lush Tree Classifications
	PINK_TREE("ptl26"),
	CHERRY_TREE("ptl30"),
	RED_TREE("ptl42"),
	SPIRAL_TREE("ptl67"),
	
	//Desert Tree Classifications
	UMBRELLA_TREE("ptd46"),
	DESERT_TREE("ptd122"),
	
	//Snow Tree Classifications
	SPRUCE_TREE("ptm83"),
	PINE_TREE("ptm131"),
	FIR_TREE("ptm132"),
	
	//Swamp Tree Classifications
	SLIMY_TREE("pts58"),
	WILLOW_TREE("pts110"),
	DEAD_TREE("pts146"),
	
	//Jungle Tree Classifications
	VINE_TREE("ptj37"),
	FICUS_TREE("ptj124"),
	CANOPY_TREE("ptj125"),
	WITCHWOOD_TREE("ptj159"),
	
	//Tropical Tree Classifications
	PALM_TREE("ptt27"),
	BANANA_TREE("ptt43"),
	ORANGE_TREE("ptt47"),
	MANGO_TREE("ptt64"),
	FLOWER_TREE("ptt109");
	
	String classification;
	
	LargePlantClassification(String classification) {
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