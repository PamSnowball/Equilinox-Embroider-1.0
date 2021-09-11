package com.snowball.embroider.util.requirement;

import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.util.component.CompRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqSpecieInBiome implements CompRequirement {
	IClassifier classification;
		
	int count;
		
	public ReqSpecieInBiome(IClassifier nearbySpecieClassification, int entityCount) {
		this.classification = nearbySpecieClassification;
		this.count = entityCount;
	}
		
	@Override
	public Collection<String> req() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;44;specie", classification, "count", count));
	}
}
