package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.IRequirement;
import com.snowball.utils.Utils;
import com.snowball.embroider.enumerator.EnumBiome;

import java.util.Collection;
import java.util.Collections;

public class ReqNotBiome implements IRequirement {
	EnumBiome biome;
		
	float amount = 0.1F;
	
	public ReqNotBiome(EnumBiome biome) {
		this.biome = biome;
	}
		
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;4;biome", biome.name(), "amount", amount));
	}
}
