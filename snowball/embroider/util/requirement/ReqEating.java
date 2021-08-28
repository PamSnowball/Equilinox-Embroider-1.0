package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.Collection;
import java.util.Collections;

public class ReqEating implements CompRequirement {
	IClassifier classification;

	public ReqEating(IClassifier classification) {
		this.classification = classification;
	}
	
	public Collection<String> requirement() {
		return Collections.singleton("EATING;" + classification + ';');
	}
}