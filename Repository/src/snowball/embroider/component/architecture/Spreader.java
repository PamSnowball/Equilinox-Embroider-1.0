package snowball.embroider.component.architecture;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.EnumBiome;
import snowball.embroider.util.Vector;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Spreader extends NativeComponent {
	EnumBiome biome;
	
	float strength;
	
	int distance;
	
	Vector colour;
	
	public Spreader(EnumBiome biome, Vector colour, float strength, int distance) {
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
