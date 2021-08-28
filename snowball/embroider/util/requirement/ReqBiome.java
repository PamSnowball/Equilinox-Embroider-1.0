package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Biomes;

import java.util.Collection;
import java.util.Collections;

public class ReqBiome implements CompRequirement {
	Biomes biome;
		
	float amount;
		
	public ReqBiome(Biomes biome, float amount) {
		this.biome = biome;
		this.amount = amount;
	}
		
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;1;biome", biome.name(), "amount", amount));
	}
}