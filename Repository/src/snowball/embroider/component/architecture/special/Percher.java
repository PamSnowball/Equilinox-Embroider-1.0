package snowball.embroider.component.architecture.special;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;

import java.util.Collection;
import java.util.Collections;

public class Percher extends NativeComponent {
	boolean die;
	
	public Percher(boolean removeOnRemoval) {
		this.die = removeOnRemoval;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("PERCHER;dieWhenPerchRemoved;" + (die ? 1 : 0));
	}

	@Override
	public int getId() {
		return 36;
	}
}
