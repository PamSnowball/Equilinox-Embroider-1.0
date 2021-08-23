package snowball.embroider.component.architecture.hunt;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Hostile extends NativeComponent {
	boolean notify;
	
	IClassifier enemy;
	
	float time;
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("HOSTILE;time", time, "enemy", enemy.getClassification(), "notifyPrey", notify ? 1 : 0));
	}

	@Override
	public int getId() {
		return 1;
	}
}
