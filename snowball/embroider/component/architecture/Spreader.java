package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Biomes;

import java.util.Collection;
import java.util.Collections;

public class Spreader extends NativeComponent {
	Biomes biome;
	
	float strength;
	
	int distance;
	
	Vector colour;

	/**
	 * Constructs the SPREADER component which is used by most plants to set biome spreading.
	 *
	 * @param biome
	 * @param colour
	 * @param strength
	 * @param distance
	 */
	public Spreader(Biomes biome, Vector colour, float strength, int distance) {
		this.biome = biome;
		this.colour = colour;
		this.strength = strength;
		this.distance = distance;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton(Utils.value("SPREADER", biome.getId(), colour.value(), strength, distance));
	}
	
	@Override
	public int getId() {
		return 50;
	}
}
