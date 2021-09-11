package com.snowball.embroider.util.requirement;

import com.snowball.embroider.enumerator.Colours;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.util.component.CompRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqNearbyColoredSpecies implements CompRequirement {
	IClassifier classification;
	Colours colour;
		
	public ReqNearbyColoredSpecies(IClassifier nearbySpecieClassification, Colours entityColour) {
		this.classification = nearbySpecieClassification;
		this.colour = entityColour;
	}
		
	@Override
	public Collection<String> req() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;6;specie", classification, "colour", colour.name()));
	}
}
