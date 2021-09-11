package com.snowball.embroider.component.architecture.special;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Perch extends NativeComponent {
	Vector[] slots;

	/**
	 * Constructs the PERCH component which is used by trees to set perching and nesting positions.
	 *
	 * @param perchingPositions position birds and nests stay
	 */
	public Perch(Vector[] perchingPositions) {
		this.slots = perchingPositions;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		List<String> perch = new ArrayList<>();

		if (slots != null) {
			perch.add(Utils.value("PERCH", slots.length));
			
			for (Vector slot : slots) perch.add(slot.toString() + ';');
		}

		return perch;
	}
	
	@Override
	public int getId() {
		return 25;
	}
}
