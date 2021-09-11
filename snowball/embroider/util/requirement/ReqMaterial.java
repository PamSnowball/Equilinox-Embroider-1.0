package com.snowball.embroider.util.requirement;

import com.snowball.embroider.enumerator.Colours;
import com.snowball.embroider.util.component.CompRequirement;

import java.util.Collection;
import java.util.Collections;

public class ReqMaterial implements CompRequirement {
	Colours colour;
	
	public ReqMaterial(Colours colour) {
		this.colour = colour;
	}

	@Override
	public Collection<String> req() {
		return Collections.singleton("MATERIAL;col;" + colour);
	}
}
