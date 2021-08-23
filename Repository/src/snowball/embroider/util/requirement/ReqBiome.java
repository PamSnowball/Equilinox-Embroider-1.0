package snowball.embroider.util.requirement;

import snowball.embroider.enumerator.EnumBiome;
import snowball.embroider.util.component.IRequirement;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class ReqBiome implements IRequirement {
	EnumBiome biome;
		
	float amount;
		
	public ReqBiome(EnumBiome biome, float amount) {
		this.biome = biome;
		this.amount = amount;
	}
		
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;1;biome", biome.name(), "amount", amount));
	}
}