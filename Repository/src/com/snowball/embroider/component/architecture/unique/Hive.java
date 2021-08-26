package com.snowball.embroider.component.architecture.unique;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Hive extends NativeComponent {
	int honey;
	int count;
	int start;

	/**
	 * Constructs the HIVE component which is used by hives to set their den dependency. <br>
	 * Hives have a max honey of 300, a start stage and a stage count of 4 <p>
	 *
	 * Snowball's tip:<i> would be a good idea to use this component as a container of a property
	 * and ignore that the name of the component in "HIVE" then use a custom component which interacts with this
	 * component. As an example, an apple basket which can contain apples. </i>
	 * @param maxHoney max honey
	 * @param startStage stage which content starts being produced
	 * @param stageCount stages past {@code startStage} that content is produced
	 */
	public Hive(int maxHoney, int startStage, int stageCount) {
		this.count = Math.min(stageCount, 1);
		this.start = Math.min(startStage, 1);
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
