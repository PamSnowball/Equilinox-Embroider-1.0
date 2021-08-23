package snowball.embroider.util.requirement;

import snowball.embroider.util.component.IRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqFruitFall implements IRequirement {
	float target;

	public ReqFruitFall(float target) {
		this.target = target;
	}
	
	public Collection<String> requirement() {
		return Collections.singleton("FRUIT_FALL;" + target + ';');
	}
}
