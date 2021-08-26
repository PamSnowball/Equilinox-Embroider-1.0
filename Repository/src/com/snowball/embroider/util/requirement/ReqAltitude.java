package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.IRequirement;
import com.snowball.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class ReqAltitude implements IRequirement {
	int min;
	int max;
	
	public ReqAltitude(int minimumAltitude, int maximumAltitude) {
		this.min = minimumAltitude;
		this.max = maximumAltitude;
	}
	
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;3;min", min, "max", max));
	}
}
