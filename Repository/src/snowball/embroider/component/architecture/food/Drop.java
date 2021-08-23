package snowball.embroider.component.architecture.food;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Drop extends NativeComponent {
	final IClassifier dropped;

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
