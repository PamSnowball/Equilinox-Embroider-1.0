package com.snowball.embroider.component.architecture.food;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FruitFall extends NativeComponent {
	IClassifier fruit;

	float height;
	float radius;
	float time;

	/**
	 * Constructs the FRUIT_FALL component which is used by fruit trees. <br>
	 * Fruit production time increases the lower the environmental satisfaction is,
	 * {@code averageFruitTime} is the average fruits that fall each minute at the perfect environment,
	 * but up to a fifth of the fruits can be produced if the environment is poor.
	 *
	 * @param fruit fruit produced
	 * @param averageFruitTime optimal fruit rate per minute
	 * @param spawnHeight height of the fruit
	 * @param spawnRadius radius fruit can fall
	 */
	public FruitFall(IClassifier fruit, float averageFruitTime, float spawnHeight, float spawnRadius) {
		this.time = Math.min(averageFruitTime, 0) / 30;
		this.fruit = fruit;
		this.height = spawnHeight;
		this.radius = spawnRadius;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		boolean isFruit = fruit.getType().startsWith("ef");

		if (isFruit) return Collections.singleton(Utils.value("FRUIT_FALL;fruitID", fruit.getId(), "spawnTime", time, "spawnHeight", height, "spawnRadius", radius));

		return new ArrayList<>();
	}
	
	@Override
	public int getId() {
		return 28;
	}
}
