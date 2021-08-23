package snowball.embroider.enumerator.sound;

public enum EnumSound implements ISound {
	BEAR_ROAR_2("bearRoar2"),
	SHEEP_BAA_1("sheepBaa2"),
	SHEEP_BAA_0("sheepBaa"),
	BEAR_ROAR("bearRoar"),
	BIRDS_2("birds2"),
	BOUNCE("bounce"),
	EAGLE_0("eagle0"),
	EAGLE_1("eagle1"),
	BIRDS("birds"),
	CHICHKEN_0("chic0"),
	CHICKEN_1("chic1"),
	CHICKEN_2("chic2"),
	CHICKEN_3("chic3"),
	CHICKEN_4("chic4"),
	DUCK_0("duck0"),
	DUCK_1("duck1"),
	DUCK_2("duck2"),
	FROG_0("frog0"),
	FROG_1("frog1"),
	GOAT_0("goat0"),
	GOAT_1("goat1"),
	GOAT_2("goat2"),
	GOAT_3("goat3"),
	PIG_0("gPig0"),
	PIG_1("gPig1"),
	PIG_2("gPig2"),
	PIG_3("gPig3"),
	PIG_4("gPig4"),
	PIG_5("pig0"),
	PIG_6("pig1"),
	PIG_7("pig2"),
	PIG_8("pig3"),
	BEES("bees"),
	
	//Place Sounds
	BEAR("bearPlace"),
	GRASS("grassPlace"),
	THUD("thud"),
	GOAT("goat0"),
	SPLASH("splash");
	
	private String sound;
		
	EnumSound(String sound) {
		this.sound = sound;
	}

	@Override
	public String getSound() {
		return sound;
	}

	@Override
	public CustomSound loadSound() {
		return null;
	}
}
