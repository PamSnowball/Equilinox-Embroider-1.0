package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqFruitFall implements CompRequirement {
	float target;

	public ReqFruitFall(float target) {
		this.target = target;
	}
	
	public Collection<String> req() {
		return Collections.singleton("FRUIT_FALL;" + target + ';');
	}
}
