package snowball.embroider.component.architecture.food;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;

import java.util.Collection;
import java.util.Collections;

public class Decay extends NativeComponent {
	float time;
	
	public Decay(float lifeTime) {
		this.time = lifeTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("DECAY;" + time);
	}

	@Override
	public int getId() {
		return 48;
	}
}
