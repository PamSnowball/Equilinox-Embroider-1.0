package com.snowball.embroider.component.architecture;

import biomes.Biome;
import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.enumerator.Biomes;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Spreader extends NativeComponent {
	Biome biome;
	
	float strength;

	int distance;


	/**
	 * Constructs the SPREADER component which is used by most plants to set biome spreading.
	 *
	 * @param biome biome it spreads
	 * @param strength strength of the spread
	 * @param distance spread radius
	 */
	public Spreader(Biome biome, float strength, int distance) {
		this.biome = biome;
		this.strength = strength;
		this.distance = distance;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		if (biome == null) biome = Biomes.GRASSLAND.getBiome();
		return Collections.singleton(Utils.value("SPREADER", biome.getId(), 0.1, 0.1, 0.1, strength, distance));
	}
	
	@Override
	public int getId() {
		return 50;
	}
}
