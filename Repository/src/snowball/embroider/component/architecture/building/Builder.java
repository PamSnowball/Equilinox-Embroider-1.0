package snowball.embroider.component.architecture.building;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Builder extends NativeComponent {
	IClassifier building;

	float early = 0;
	float time;
	float age;

	int speed;

	boolean perch;

	public Builder(IClassifier building, int buildSpeed, boolean needsPerch, float buildingTime, float buildAgeFactor, float earlyBuildTime) {
		this.building = building;
		this.early = earlyBuildTime;
		this.age = buildAgeFactor;
		this.time = buildingTime;
		this.speed = buildSpeed;
		this.perch = needsPerch;
	}
	
	public Builder(IClassifier building, int buildSpeed, boolean needsPerch, float buildingTime, float buildAgeFactor) {
		this.building = building;
		this.speed = buildSpeed;
		this.time = buildingTime;
		this.age = buildAgeFactor;
		this.perch = needsPerch;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		boolean isBuilding = building.getType().startsWith("es");

		List<String> builder = new ArrayList<>();

		if (isBuilding) {
			builder.add(Utils.value("BUILDER;buildModel", building.getId(), "buildPoints", speed, "needsPerch"));
			builder.add(Utils.value(perch ? 1 : 0, "buildTime", time, "buildAge", age));
			
			if (early != 0) builder.add("buildStartTime;" + early);
		}

		return builder;
	}

	@Override
	public int getId() {
		return 21;
	}
}
