package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.CompRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqTransform implements CompRequirement {
	float size;

	public ReqTransform(float targetSize) {
		this.size = targetSize;
	}
	
	public Collection<String> req() {
		return Collections.singleton("TRANSFORM;" + size + ';');
	}
}
