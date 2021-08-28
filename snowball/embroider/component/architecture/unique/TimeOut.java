package com.snowball.embroider.component.architecture.unique;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;

import java.util.Collection;
import java.util.Collections;

public class TimeOut extends NativeComponent {
	float time;

	/**
	 * Constructs the TIME_OUT component which is used by burrows to set dying after certain time.
	 *
	 * @param time time in seconds entity dies
	 */
	public TimeOut(float time) {
		this.time = time;
	}

	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("TIME_OUT;" + time);
	}

	@Override
	public int getId() {
		return 51;
	}
}
