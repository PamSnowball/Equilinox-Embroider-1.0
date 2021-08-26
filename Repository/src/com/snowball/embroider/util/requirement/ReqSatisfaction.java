package com.snowball.embroider.util.requirement;

import com.snowball.embroider.util.component.IRequirement;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class ReqSatisfaction implements IRequirement {
	float satisfaction;
	
	public ReqSatisfaction(float satisfaction) {
		this.satisfaction = satisfaction;
	}
	
	@Override
	public Collection<String> requirement() {
		return Collections.singleton(Utils.value("LIFE;type;2;enviroType;2;satisfaction", satisfaction));
	}
}
