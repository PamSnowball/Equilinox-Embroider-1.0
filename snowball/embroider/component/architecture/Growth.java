package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Growth extends NativeComponent {
	public final float time;

	public final int stages;

	boolean dynamic = false;

	int sub;

	//Must First
	public Growth(Entity entity, float averageGrowthTime, int modelStages, int subStages) {
		this.time = averageGrowthTime;
		this.stages = modelStages;
		this.sub = subStages;

		entity.setGrows(true);
	}

	public Growth(Entity entity, float averageGrowthTime, int modelStages) {
		this.time = averageGrowthTime;
		this.stages = modelStages;
		this.dynamic = true;

		entity.setGrows(true);
	}
	
	public Collection<String> load(Entity entity) {
		List<String> grow = new ArrayList<>();
		
		grow.add(Utils.value("GROWTH;dynamic", dynamic ? 1 : 0, "growthTime", time, "modelStages", stages));
		if (dynamic) grow.add("subStages;" + sub);

		return grow;
	}
	
	@Override
	public int getId() {
		return 42;
	}
}
