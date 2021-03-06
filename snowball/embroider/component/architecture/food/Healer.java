package com.snowball.embroider.component.architecture.food;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.component.CompDeath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Healer extends NativeComponent {
	CompDeath death;

	/**
	 * Constructs the HEALER component which is used by WitchWood fruit to set eaten aesthetics.
	 *
	 * @param death eaten death aesthetics
	 */
	public Healer(CompDeath death) {
		this.death = death;
	}
	
	public Collection<String> load(CustomEntity entity) {
		List<String> heal = new ArrayList<>();

		if (death != null) {
			heal.add("HEALER;");
			heal.addAll(death.death());
		}

		return heal;
	}

	@Override
	public int getId() {
		return 42;
	}
}
