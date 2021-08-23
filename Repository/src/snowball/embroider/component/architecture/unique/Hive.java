package snowball.embroider.component.architecture.unique;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Hive extends NativeComponent {
	int honey;
	int count;
	int start;
	
	public Hive(int maxHoney, int startStage, int stageCount) {
		this.count = stageCount;
		this.start = startStage;
		this.honey = maxHoney;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("HIVE", honey, start, count));
	}

	@Override
	public int getId() {
		return 44;
	}
}
