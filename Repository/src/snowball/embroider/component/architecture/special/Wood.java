package snowball.embroider.component.architecture.special;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.Vector;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Wood extends NativeComponent {
	float bark;
	float time;
	
	Vector colour;
	
	public Wood(float cutTime, float barkFactor, Vector colour) {
		this.bark = barkFactor;
		this.colour = colour;
		this.time = cutTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("cutTime", time, "barkFactor", bark, "colour", colour.toString()));
	}

	@Override
	public int getId() {
		return 29;
	}
}
