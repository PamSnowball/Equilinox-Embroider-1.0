package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Flee extends NativeComponent {
	IClassifier classification;
	
	float range;

	/**
	 * Constructs the FLEE component which is used by many animals to set danger avoiding behaviour.
	 *
	 * @param safeRange range of danger detection
	 */
	public Flee(float safeRange) {
		this.range = safeRange;
	}

	/** Use if entity hides into an entity to avoid attacks */
	public Flee setsHiddenSpot(IClassifier classification) {
		if (classification != null) {
			this.classification = classification;
		}
		return this;
	}

	@Override
	public Collection<String> load(CustomEntity entity) {
		List<String> flee = new ArrayList<>();
		
		flee.add(Utils.value("FLEE;"));
		
		if (entity.hasComponent(Ai.TortoiseAi.class)) flee.add("TURTLE;");
		else if (entity.hasComponent(Ai.MeerkatAi.class)) flee.add("MEERKAT;");
		else flee.add("safeRange;");
		
		flee.add(Utils.value(range, "land", entity.isLand() ? 1 : 0, "swim", entity.isAquatic() ? 1 : 0));

		if (classification != null) flee.add(classification.toString());

		return flee;
	}	
	
	@Override
	public int getId() {
		return 20;
	}
}
