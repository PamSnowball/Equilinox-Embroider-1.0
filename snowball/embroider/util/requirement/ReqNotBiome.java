package com.snowball.embroider.util.requirement;

import biomes.Biome;
import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class ReqNotBiome implements CompRequirement {
	Biome biome;
		
	float amount = 0.1F;
	
	public ReqNotBiome(Biome biome) {
		this.biome = biome;
	}

	@Override
	public Collection<String> req() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;4;biome", biome.name(), "amount", amount));
	}
}
