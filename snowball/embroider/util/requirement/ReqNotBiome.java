package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Biomes;
import com.snowball.embroider.util.component.IBiome;

import java.util.Collection;
import java.util.Collections;

public class ReqNotBiome implements CompRequirement {
	IBiome biome;
		
	float amount = 0.1F;
	
	public ReqNotBiome(IBiome biome) {
		this.biome = biome;
	}

	@Override
	public Collection<String> req() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;4;biome", biome.name(), "amount", amount));
	}
}
