package com.snowball.embroider.component.architecture.special;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Equip extends NativeComponent {
	Vector[] positions;

	/**
	 * Constructs the EQUIP component which is used by carnivore animals and beavers to set pick up AI.
	 *
	 * @param equippingPositions position grabbed entity stays at each life stage
	 */
	public Equip(Vector[] equippingPositions) {
		this.positions = equippingPositions;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		Vector[] stages = new Vector[entity.getStages()];

		System.arraycopy(positions, 0, stages, 0, stages.length);

		List<String> perch = new ArrayList<>();

		if (positions != null) {
			perch.add("EQUIP;count;" + stages.length);
			
			for (Vector position : stages) perch.add("pos;" + position.toString() + ';');
		}

		return perch;
	}

	@Override
	public int getId() {
		return 24;
	}
}
