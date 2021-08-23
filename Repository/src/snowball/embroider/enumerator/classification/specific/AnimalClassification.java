package snowball.embroider.enumerator.classification.specific;

import snowball.embroider.enumerator.classification.IClassifier;

public enum AnimalClassification implements IClassifier {
	//Small Fish Classifications
	TROUT("afs14"),
	RED_FISH("afs15"),
	CLOWN_FISTH("afs76"),
	
	//Big Fish Classifications
	PIKE("afb23"),
	
	//Weird Fish Classifications
	JELLY_FISH("afw172"),
	
	//Large Herbivore Classifications
	BEAR("ahl78"),
	CAMEL("ahl85"),
	
	//Medium Herbivore Classifications
	SHEEP("ahm1"),
	WILD_BOAR("ahm50"),
	WARTHOG("ahm79"),
	GOAT("ahm92"),
	DEER("ahm99"),
	
	//Small Herbivore Classifications
	RABBIT("ahs41"),
	SQUIRREL("ahs49"),
	GUINEA_PIG("ahs52"),
	DESERT_HARE("ahs73"),
	BEAVER("ahs89"),
	
	//Small Bird Classifications
	CHICKEN("abs8"),
	TOUCAN("abs63"),
	SPARROW("abs64"),
	DUCK("abs65"),
	PEACOCK("abs145"),
	DOVE("abs161"),
	
	//Bird of Prey Classifications
	EAGLE("abp171"),
	
	//Small Carnivore Classifications
	FOX("acs84"),
	
	//Large Carnivore Classifications
	WOLF("acl72"),
	
	//Insect Classifications
	BUTTERFLY("ai55"),
	BEE("ai56"),
	
	
	//Reptile Classifications
	TURTLE("ar12"),
	FROG("ar38"),
	TOAD("ar74"),
	LIZARD("ar75");
	
	String classification;
	String text;
	
	AnimalClassification(String classification) {
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
