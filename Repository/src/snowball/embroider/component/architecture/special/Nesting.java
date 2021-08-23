package snowball.embroider.component.architecture.special;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Nesting extends NativeComponent {
	boolean model;
	
	IClassifier classification;
	
	int stage;
	
	public Nesting(IClassifier classification, int hatchingStage, boolean decreasesModel) {
		this.classification = classification;
		this.model = decreasesModel;
		this.stage = hatchingStage;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("NESTING;index", classification.getClassification(), "hatchStage", stage, "decreasesModel", model ? 1 : 0));
	}

	@Override
	public int getId() {
		return 10;
	}
}
