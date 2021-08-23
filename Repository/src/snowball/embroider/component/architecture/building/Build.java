package snowball.embroider.component.architecture.building;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Build extends NativeComponent {
	int points;
	int count;
	int done;

	/**
	 * Constructs the BUILD component.
	 * BUILD Component is used by dens
	 * @param stageCount
	 * @param maxBuildPoints
	 */
	public Build(int stageCount, int maxBuildPoints) {
		this.points = maxBuildPoints;
		this.done = maxBuildPoints;
		this.count = stageCount;
	}
	
	public Build(int stageCount, int maxBuildPoints, int fullyBuildAt) {
		this.points = maxBuildPoints;
		this.done = fullyBuildAt;
		this.count = stageCount;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> building = new ArrayList<>();
		
		building.add(Utils.value("BUILD", count, points));
		
		if (done != points) building.add(String.valueOf(done));

		return building;
	}

	@Override
	public int getId() {
		return 41;
	}
}
