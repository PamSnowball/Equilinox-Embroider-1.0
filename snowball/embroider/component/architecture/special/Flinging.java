package com.snowball.embroider.component.architecture.special;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;

import java.util.Collection;
import java.util.Collections;

public class Flinging extends NativeComponent {
	float max;
	float min;

	/**
	 * Constructs the FLINGING component which is used by frogs and toads to set bug catching AI.
	 *
	 * @param minimumTime minimum time between catches
	 * @param maximumTime maximum time between catches
	 */
	public Flinging(float minimumTime, float maximumTime) {
		this.max = maximumTime;
		this.min = minimumTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("FLINGING;minTime;" + min + ";maxTime;" + max);
	}
	
	@Override
	public int getId() {
		return 22;
	}
}
