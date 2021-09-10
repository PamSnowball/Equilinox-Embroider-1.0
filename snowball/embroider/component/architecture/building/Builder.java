package com.snowball.embroider.component.architecture.building;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Builder extends NativeComponent {
	IClassifier building;

	float early = 0;

	float age;
	float time;

	int speed;

	boolean perch;

	/**
	 * Constructs the BUILDER component which is used by most flying animals to set building variables. <br>
	 * The BUILDER component is set up using build points, the builder entity put a determined amount of build points you must set up.
	 *
	 * @param building building classification
	 * @param buildSpeed amount of point added by builder each visit
	 * @param needsPerch true if building is a nest
	 * @param buildingTime average time in seconds before the entity starts building
	 * @param buildAgeFactor life stage which entity can build (can be decimal)
	 */
	public Builder(IClassifier building, int buildSpeed, boolean needsPerch, float buildingTime, float buildAgeFactor) {
		this.building = building;
		this.age = Math.max(buildAgeFactor - 0.5F, 0);
		this.time = buildingTime;
		this.speed = buildSpeed;
		this.perch = needsPerch;
	}

	public Builder setWorkShift(int startBuildingTime) {
		this.early = startBuildingTime;
		return this;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		boolean isBuilding = building.getType().startsWith("es");

		List<String> builder = new ArrayList<>();

		if (isBuilding) {
			builder.add(Utils.value("BUILDER;buildModel", building.getId(), "buildPoints", speed, "needsPerch"));
			builder.add(Utils.value(perch ? 1 : 0, "buildTime", time, "buildAge", age / entity.getStages() - 1));
			
			if (early > 0) builder.add("buildStartTime;" + early);
		}

		return builder;
	}

	@Override
	public int getId() {
		return 21;
	}
}
