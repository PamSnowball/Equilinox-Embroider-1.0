package com.snowball.embroider.component.architecture.unique;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Vector;

import java.util.Collection;
import java.util.Collections;

public class FlyTrap extends NativeComponent {
	Vector position;

	/** Constructs the FLY_TRAP component which is used by carnivore plants to set their fly eating AI.
	 *
	 * @param position tongue position
	 */
	public FlyTrap(Vector position) {
		this.position = position;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton("FLY_TRAP;pos;" + position.toString());
	}

	@Override
	public int getId() {
		return 35;
	}
}
