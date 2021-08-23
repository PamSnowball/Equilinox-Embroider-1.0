package snowball.embroider.component.architecture.special;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;

import java.util.Collection;
import java.util.Collections;

public class Flinging extends NativeComponent {
	float max;
	float min;
	
	public Flinging(float minimumTime, float maximumTime) {
		this.max = maximumTime;
		this.min = minimumTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("FLINGING;minTime;" + min + ";maxTime;" + max);
	}
	
	@Override
	public int getId() {
		return 22;
	}
}
