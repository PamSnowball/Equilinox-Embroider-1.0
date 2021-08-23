package snowball.embroider.enumerator.classification.specific;

import snowball.embroider.enumerator.classification.IClassifier;

public enum NonLivingClassification implements IClassifier {
	//Stone Classifications
	BROWN_STONE("ers9"),
	STONES("ers162"),
	SHELL("ers166"),
	
	//Large Rock Classifications
	BROWN_ROCK("erl6"),
	JUNGLE_ROCK("erl35"),
	
	//Fruit Classifications
	APPLE("ef33"),
	COCONUT("ef40"),
	BANANA("ef44"),
	ORANGE("ef48"),
	MANGO("ef141"),
	SEED("ef158"),
	WITCHWOOD_FRUIT("ef160"),
	
	//Vegetable Classifications
	POTATO("ev53"),
	
	//Nut Classifications
	NUT("en138"),
	
	//Meat Classifications
	MEAT("em104"),
	BIRD_MEAT("em139"),
	SMALL_MEAT("em140"),
	
	//Structure Classifications
	HIVE("es57"),
	NEST("es68"),
	BEAVER_LODGE("es102");

	String classification;
	
	NonLivingClassification(String classification) {
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
