package com.snowball.embroider.util.requirement;

import biomes.Biome;
import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class ReqBiome implements CompRequirement {
	Biome biome;
		
	float amount;

	public ReqBiome(Biome biome, float amount) {
		this.biome = biome;
		this.amount = amount;
	}

	@Override
	public Collection<String> req() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;1;biome", biome.name(), "amount", amount));
	}
}