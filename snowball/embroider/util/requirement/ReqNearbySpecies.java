package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.Collection;
import java.util.Collections;

public class ReqNearbySpecies implements CompRequirement {
	IClassifier classification;
		
	int count;
		
	public ReqNearbySpecies(IClassifier classification, int count) {
		this.classification = classification;
		this.count = count;
	}
		
	@Override
	public Collection<String> req() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;0;specie", classification, "count", count));
	}
}