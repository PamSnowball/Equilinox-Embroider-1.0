package snowball.embroider.util.requirement;

import snowball.embroider.util.component.IRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqTransform implements IRequirement {
	float size;

	public ReqTransform(float targetSize) {
		this.size = targetSize;
	}
	
	public Collection<String> requirement() {
		return Collections.singleton("TRANSFORM;" + size + ';');
	}
}