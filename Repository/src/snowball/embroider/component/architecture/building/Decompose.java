package snowball.embroider.component.architecture.building;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;

import java.util.Collection;
import java.util.Collections;

public class Decompose extends NativeComponent {
	float loss;
	
	public Decompose(float timesPerLoss) {
		this.loss = timesPerLoss;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton("DECOMPOSE;timePerLoss;" + loss);
	}

	@Override
	public int getId() {
		return 39;
	}
}
