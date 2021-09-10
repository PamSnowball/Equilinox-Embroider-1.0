package com.snowball.embroider.component.architecture.food;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;

import java.util.Collection;
import java.util.Collections;

public class Decay extends NativeComponent {
	float time;

	/**
	 * Constructs the DECAY component which is used by fruits and materials to set decaying time.
	 *
	 * @param lifeTime time in hours entity decays
	 */
	public Decay(float lifeTime) {
		this.time = lifeTime;
	}

	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton("DECAY;" + time);
	}

	@Override
	public int getId() {
		return 49;
	}
}
