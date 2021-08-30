package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Biomes;
import com.snowball.embroider.util.component.IBiome;

import java.util.Collection;
import java.util.Collections;

public class ReqBiome implements CompRequirement {
	IBiome biome;
		
	float amount;
		
	public ReqBiome(IBiome biome, float amount) {
		this.biome = biome;
		this.amount = amount;
	}
		
	@Override
	public Collection<String> req() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;1;biome", biome.name(), "amount", amount));
	}
}