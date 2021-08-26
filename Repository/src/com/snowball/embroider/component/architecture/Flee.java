package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.utils.Utils;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Flee extends NativeComponent {
	String classification;
	
	float range;
	
	Entity entity;
	
	public Flee(Entity entity, float safeRange, IClassifier hidingClassification) {
		this.classification = hidingClassification.getClassification();
		this.range = safeRange;
		this.entity = entity;
	}
	
	public Flee(Entity entity, float safeRange) {
		this.range = safeRange;
		this.entity = entity;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> flee = new ArrayList<>();
		
		flee.add(Utils.value("FLEE;"));
		
		if (entity.hasComponent(Ai.TortoiseAi.class)) flee.add("TURTLE;"); 
		else if (entity.hasComponent(Ai.MeerkatAi.class)) flee.add("MERKAT;"); 
		else flee.add("safeRange;");
		
		flee.add(Utils.value(range, "land", entity.isAquatic() ? 1 : 0, "swim", entity.isLand() ? 1 : 0));

		return flee;
	}	
	
	@Override
	public int getId() {
		return 19;
	}
}
