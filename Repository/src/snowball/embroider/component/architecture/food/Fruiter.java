package snowball.embroider.component.architecture.food;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fruiter extends NativeComponent {
	IClassifier fruit;

	float time = 5.0F;
	
	int count = 0;

	public Fruiter(IClassifier fruit, int stageCount) {
		this.fruit = fruit;
		this.count = stageCount;
	}
	
	public Fruiter(IClassifier fruit, int stageCount, float fruitTime) {
		if (stageCount > -1) this.count = stageCount;
		this.fruit = fruit;
		this.time = fruitTime;
	}
	
	public Collection<String> load(Entity entity) {
		boolean isFruit = fruit.getType().startsWith("ef");

		List<String> fruiter = new ArrayList<>();

		if (isFruit) {
			fruiter.add(Utils.value("FRUITER;modelStage", fruit.getId(), "stageCount", count));
			if (time != 5.0F) fruiter.add("time;" + time);
		}

		return fruiter;
	}

	@Override
	public int getId() {
		return 37;
	}
}
