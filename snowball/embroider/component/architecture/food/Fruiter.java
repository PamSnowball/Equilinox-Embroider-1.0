package com.snowball.embroider.component.architecture.food;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fruiter extends NativeComponent {
	int initialStage;

	float time = 5.0F;
	
	int stageCount;

	/**
	 * Constructs the FRUITER component which is used by plants that provide any renewable source of food to set
	 * food production.
	 *
	 * @param initialStage stage which food starts to be produced
	 * @param stageCount amount of stages food source has
	 */
	public Fruiter(int initialStage, int stageCount) {
		this.stageCount = Math.max(stageCount, 0);
		this.initialStage = initialStage;
	}

	/**
	 * Constructs the FRUITER component which is used by plants that provide any renewable source of food to set
	 * food production. <br>
	 * Fruit production time increases the lower the environmental satisfaction is,
	 * {@code fruitTime} is the time it takes the fruit to grow at the perfect environment,
	 * but it can take up to two and a half times the time.
	 *
	 * @param fruitTime optimal time in seconds fruit advance a state
	 * @param initialStage stage which food starts to be produced
	 * @param stageCount amount of stages food source has
	 */
	public Fruiter(float fruitTime, int initialStage, int stageCount) {
		this.stageCount = Math.max(stageCount, 0);
		this.initialStage = initialStage;
		this.time = fruitTime;
	}
	
	public Collection<String> load(Entity entity) {
		List<String> fruiter = new ArrayList<>();

		fruiter.add(Utils.value("FRUITER;modelStage", initialStage, "stageCount", stageCount));
		if (time != 5.0F) fruiter.add("time;" + time);

		return fruiter;
	}

	@Override
	public int getId() {
		return 37;
	}
}
