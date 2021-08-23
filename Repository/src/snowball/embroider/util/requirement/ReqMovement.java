package snowball.embroider.util.requirement;

import snowball.embroider.util.component.IRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqMovement implements IRequirement {
	float target;

	public ReqMovement(float target) {
		this.target = target;
	}
	
	public Collection<String> requirement() {
		return Collections.singleton("MOVEMENT;" + target + ';');
	}
}
