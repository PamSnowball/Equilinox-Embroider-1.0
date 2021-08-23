package snowball.embroider.component.architecture.food;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FruitFall extends NativeComponent {
	IClassifier fruit;

	float height;
	float radius;
	float time;

	public FruitFall(IClassifier fruit, float averageFruitTime, float spawnHeight, float spawnRadius) {
		if (averageFruitTime > 0) this.time = averageFruitTime;
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
