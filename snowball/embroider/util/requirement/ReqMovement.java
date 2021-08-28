package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqMovement implements CompRequirement {
	float target;

	public ReqMovement(float target) {
		this.target = target;
	}
	
	public Collection<String> requirement() {
		return Collections.singleton("MOVEMENT;" + target + ';');
	}
}
