package snowball.embroider.component.architecture.data;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;

import java.util.Collection;
import java.util.Collections;

public class Item extends NativeComponent {
	float time;
	
	public Item(float decayTime) {
		this.time = decayTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("ITEM;decayTime;" + time);
	}

	@Override
	public int getId() {
		return 45;
	}
}
