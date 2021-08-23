package snowball.embroider.component.architecture.special;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Sleep extends NativeComponent {
	float startMax;
	float startMin;
	float endMax;
	float endMin;
	
	public Sleep(float minimumStart, float maximumStart, float minimumEnding, float maximumEnding) {
		this.startMax = maximumStart;
		this.startMin = minimumStart;
		this.endMax = maximumEnding;
		this.endMin = minimumEnding;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("SLEEP;startMin", startMin, "startMax", startMax, "endMin", endMin, "endMax", endMax));
	}

	@Override
	public int getId() {
		return 9;
	}
}
