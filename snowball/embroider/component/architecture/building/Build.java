package com.snowball.embroider.component.architecture.building;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Build extends NativeComponent {
	int points;
	int count;
	int done;

	/**
	 * Constructs the BUILD component which is used by dens, nests and hives to set building variables.
	 * The BUILD component is set up using build points, the building progress is represented by those points.
	 *
	 * @param stageCount amount of build stages
	 * @param maxBuildPoints max build points
	 */
	public Build(int stageCount, int maxBuildPoints) {
		this.points = maxBuildPoints;
		this.done = maxBuildPoints;
		this.count = stageCount;
	}

	/** Build points required for the building to reach last model stage */
	public Build setFullyEarly(int fullyBuildAt) {
		this.done = fullyBuildAt;
		return this;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		List<String> building = new ArrayList<>();

		if (entity.toString().startsWith("es")) {
			building.add(Utils.value("BUILD", Math.max(count, entity.getStages()), points));

			if (done != points) building.add(String.valueOf(done));
		}

		return building;
	}

	@Override
	public int getId() {
		return 45;
	}
}
