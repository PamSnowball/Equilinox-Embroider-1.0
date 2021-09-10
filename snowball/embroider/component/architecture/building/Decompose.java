package com.snowball.embroider.component.architecture.building;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;

import java.util.Collection;
import java.util.Collections;

public class Decompose extends NativeComponent {
	float loss;

	/**
	 * Constructs the DECOMPOSE component which is used by dens to set building variables. <br>
	 * The DECOMPOSE component is set up using build points, the building loses points throughout the time.
	 *
	 * @param timesPerLoss time in seconds building lose a build point
	 */
	public Decompose(float timesPerLoss) {
		this.loss = timesPerLoss;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton("DECOMPOSE;timePerLoss;" + loss);
	}

	@Override
	public int getId() {
		return 41;
	}
}
