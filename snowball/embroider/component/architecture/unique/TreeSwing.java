package com.snowball.embroider.component.architecture.unique;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.util.Vector;

import java.util.Collection;
import java.util.Collections;

public class TreeSwing extends NativeComponent {
	Vector position;

	/**
	 * Constructs the TREE_SWING component which is used by squirrel to set tree swinging AI.
	 *
	 * @param handPosition model position hand is when entity swings
	 */
	public TreeSwing(Vector handPosition) {
		this.position = handPosition;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton("TREE_SWING;handPos;" + position.toString());
	}

	@Override
	public int getId() {
		return 18;
	}
}
