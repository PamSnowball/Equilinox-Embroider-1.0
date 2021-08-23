package snowball.embroider.util.requirement;

import snowball.embroider.enumerator.EnumBiome;
import snowball.embroider.util.component.IRequirement;
import snowball.embroider.utils.Utils;

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
