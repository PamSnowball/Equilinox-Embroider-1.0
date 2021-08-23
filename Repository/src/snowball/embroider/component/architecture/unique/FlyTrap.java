package snowball.embroider.component.architecture.unique;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.Vector;

import java.util.Collection;
import java.util.Collections;

public class FlyTrap extends NativeComponent {
	Vector position;
	
	public FlyTrap(Vector flyPosition) {
		this.position = flyPosition;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("FLY_TRAP;pos;" + position.toString());
	}

	@Override
	public int getId() {
		return 33;
	}
}
