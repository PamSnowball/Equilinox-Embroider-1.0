package com.snowball.embroider.component.architecture.data;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;

import java.util.Collection;
import java.util.Collections;

public class Item extends NativeComponent {
	float time;

	/**
	 * Constructs the ITEM component which is used by meat to set up decaying properties.
	 *
	 * @param decayTime average time in seconds item decays
	 */
	public Item(float decayTime) {
		this.time = decayTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("ITEM;decayTime;" + time);
	}

	@Override
	public int getId() {
		return 45;
	}
}
