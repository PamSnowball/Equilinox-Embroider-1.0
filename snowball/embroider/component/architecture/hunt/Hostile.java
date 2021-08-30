package com.snowball.embroider.component.architecture.hunt;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Hostile extends NativeComponent {
	final boolean notify;
	
	final IClassifier enemy;
	
	final float time;

	/**
	 * Constructs the HOSTILE component which is used by bees and wolves to set territorial AI.
	 *
	 * @param notify if true, prey flees when attacked
	 * @param enemy target of entity
	 * @param time average time before attacking invading entities
	 */
	public Hostile(boolean notify, IClassifier enemy, float time) {
		this.notify = notify;
		this.enemy = enemy;
		this.time = time;
	}


	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton(Utils.value("HOSTILE;time", time, "enemy", enemy.getClassification(), "notifyPrey", notify ? 1 : 0));
	}

	@Override
	public int getId() {
		return 2;
	}
}
