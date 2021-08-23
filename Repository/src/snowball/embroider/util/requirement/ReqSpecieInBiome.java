package snowball.embroider.util.requirement;

import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.util.component.IRequirement;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class ReqSpecieInBiome implements IRequirement {
	IClassifier classification;
		
	int count;
		
	public ReqSpecieInBiome(IClassifier nearbySpecieClassification, int entityCount) {
		this.classification = nearbySpecieClassification;
		this.count = entityCount;
	}
		
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;44;specie", classification, "count", count));
	}
}
