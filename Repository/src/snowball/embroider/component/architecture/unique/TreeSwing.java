package snowball.embroider.component.architecture.unique;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.Vector;

import java.util.Collection;
import java.util.Collections;

public class TreeSwing extends NativeComponent {
	Vector position;
	
	public TreeSwing(Vector handPosition) {
		this.position = handPosition;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("TREE_SWING;handPos;" + position.toString());
	}

	@Override
	public int getId() {
		return 15;
	}
}
