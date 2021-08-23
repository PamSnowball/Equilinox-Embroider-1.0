package snowball.embroider.util.requirement;

import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.util.component.IRequirement;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class ReqNearbySpecies implements IRequirement {
	IClassifier classification;
		
	int count;
		
	public ReqNearbySpecies(IClassifier nearbySpecieClassification, int entityCount) {
		this.classification = nearbySpecieClassification;
		this.count = entityCount;
	}
		
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;0;specie", classification, "count", count));
	}
}