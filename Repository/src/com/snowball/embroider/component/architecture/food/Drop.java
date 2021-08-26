package com.snowball.embroider.component.architecture.food;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Drop extends NativeComponent {
	final IClassifier dropped;

	/**
	 * Constructs the DROP component which is used by animals to set dropped items.
	 *
	 * @param drop dropped item
	 */
	public Drop(IClassifier drop) {
		this.dropped = drop;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		boolean isNonLiving = dropped.getType().startsWith("e");

		if (isNonLiving) return Collections.singleton("DROP;itemId;" + dropped.getId());
		return new ArrayList<>();
	}

	@Override
	public int getId() {
		return 7;
	}
}
