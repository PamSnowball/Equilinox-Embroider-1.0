package com.snowball.embroider.component.architecture.special;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;

import java.util.Collection;
import java.util.Collections;

public class Percher extends NativeComponent {
	boolean die;

	/**
	 * Constructs the PERCHER component which is used by flying birds and nests to set nest sitting.
	 *
	 * @param isNest if true, entity will die when perch is removed
	 */
	public Percher(boolean isNest) {
		this.die = isNest;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton("PERCHER;dieWhenPerchRemoved;" + (die ? 1 : 0));
	}

	@Override
	public int getId() {
		return 36;
	}
}
