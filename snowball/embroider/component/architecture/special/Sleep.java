package com.snowball.embroider.component.architecture.special;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Sleep extends NativeComponent {
	float startMax;
	float startMin;

	float endMax;
	float endMin;

	/**
	 * Constructs the SLEEP component which is used by ruminants to set sleeping AI.
	 *
	 * @param minimumStart minimum bedtime in day hours
	 * @param maximumStart maximum bedtime in day hours
	 * @param minimumEnding maximum time to wake up in day hours
	 * @param maximumEnding maximum time to wake up in day hours
	 */
	public Sleep(float minimumStart, float maximumStart, float minimumEnding, float maximumEnding) {
		this.startMax = maximumStart;
		this.startMin = minimumStart;
		this.endMax = maximumEnding;
		this.endMin = minimumEnding;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("SLEEP;startMin", startMin, "startMax", startMax, "endMin", endMin, "endMax", endMax));
	}

	@Override
	public int getId() {
		return 9;
	}
}
