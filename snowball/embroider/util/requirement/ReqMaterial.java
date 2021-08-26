package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.IRequirement;
import com.snowball.embroider.enumerator.Colours;

import java.util.Collection;
import java.util.Collections;

public class ReqMaterial implements IRequirement {
	Colours colour;
	
	public ReqMaterial(Colours colour) {
		this.colour = colour;
	}

	@Override
	public Collection<String> requirement() {
		return Collections.singleton("MATERIAL;col;" + colour);
	}
}
