package com.snowball.embroider.component.architecture.special;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Nesting extends NativeComponent {
	boolean model;
	
	IClassifier classification;
	
	int stage;

	/**
	 * Constructs the NESTING component which is used by flying birds and beavers to set nest sitting.
	 *
	 * @param classification nest classification
	 * @param hatchingStage stage nest must be so the entity can nest in it
	 * @param decreasesModel if true, nest is a bit consumed when entities nest it
	 */
	public Nesting(IClassifier classification, int hatchingStage, boolean decreasesModel) {
		this.classification = classification;
		this.model = decreasesModel;
		this.stage = hatchingStage;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton(Utils.value("NESTING;index", classification.getClassification(), "hatchStage", stage, "decreasesModel", model ? 1 : 0));
	}

	@Override
	public int getId() {
		return 10;
	}
}
