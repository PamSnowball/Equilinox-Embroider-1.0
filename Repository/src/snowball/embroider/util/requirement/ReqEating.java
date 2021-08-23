package snowball.embroider.util.requirement;

import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.util.component.IRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqEating implements IRequirement {
	IClassifier classification;

	public ReqEating(IClassifier classification) {
		this.classification = classification;
	}
	
	public Collection<String> requirement() {
		return Collections.singleton("EATING;" + classification + ';');
	}
}