package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.IRequirement;
import com.snowball.utils.Utils;
import com.snowball.embroider.enumerator.EnumColours;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.Collection;
import java.util.Collections;

public class ReqNearbyColoredSpecies implements IRequirement {
	IClassifier classification;
	EnumColours colour;
		
	public ReqNearbyColoredSpecies(IClassifier nearbySpecieClassification, EnumColours entityColour) {
		this.classification = nearbySpecieClassification;
		this.colour = entityColour;
	}
		
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;0;specie", classification, "colour", colour.name()));
	}
}
