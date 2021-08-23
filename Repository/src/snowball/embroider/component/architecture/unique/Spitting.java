package snowball.embroider.component.architecture.unique;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.Vector;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Spitting  extends NativeComponent {
	Vector position;
	
	public Spitting(Vector spittingPosition) {
		this.position = spittingPosition;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("SPITTING;spitPosition", position));
	}

	@Override
	public int getId() {
		return 4;
	}
}
