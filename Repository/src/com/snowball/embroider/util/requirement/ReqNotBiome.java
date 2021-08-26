package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.IRequirement;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Biomes;

import java.util.Collection;
import java.util.Collections;

public class ReqNotBiome implements IRequirement {
	Biomes biome;
		
	float amount = 0.1F;
	
	public ReqNotBiome(Biomes biome) {
		this.biome = biome;
	}
		
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;4;biome", biome.name(), "amount", amount));
	}
}
