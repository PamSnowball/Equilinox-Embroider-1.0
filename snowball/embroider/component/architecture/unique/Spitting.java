package com.snowball.embroider.component.architecture.unique;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Spitting  extends NativeComponent {
	Vector position;

	/**
	 * Constructs the SPITTING component which is used by camels to set the spitting AI.
	 *
	 * @param spittingPosition position in the model projectile come from
	 */
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
