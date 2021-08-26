package com.snowball.embroider.component.architecture.building;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.utils.Utils;

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

	/**
	 * Constructs the BUILD component which is used by dens, nests and hives to set building variables. <br>
	 * The BUILD component is set up using build points, the builder entity put a determined amount of build points you must set up.
	 *
	 * @param stageCount amount of build stages
	 * @param maxBuildPoints max build points
	 * @param fullyBuildAt build points required for the building to be fully built
	 */
	public Build(int stageCount, int maxBuildPoints, int fullyBuildAt) {
		this.points = maxBuildPoints;
		this.done = fullyBuildAt;
		this.count = stageCount;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> building = new ArrayList<>();

		if (entity.toString().startsWith("es")) {
			building.add(Utils.value("BUILD", Math.min(count, entity.getStages()), points));

			if (done != points) building.add(String.valueOf(done));
		}

		return building;
	}

	@Override
	public int getId() {
		return 41;
	}
}
