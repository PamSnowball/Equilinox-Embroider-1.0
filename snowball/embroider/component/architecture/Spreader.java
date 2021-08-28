package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
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
	
	public Spreader(Biomes biome, Vector colour, float strength, int distance) {
		this.biome = biome;
		this.colour = colour;
		this.strength = strength;
		this.distance = distance;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("SPREADER", biome.getId(), colour.value(), strength, distance));
	}
	
	@Override
	public int getId() {
		return 49;
	}
}
