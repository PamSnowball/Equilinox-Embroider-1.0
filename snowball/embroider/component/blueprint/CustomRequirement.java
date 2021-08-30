package com.snowball.embroider.component.blueprint;

import breedingTrees.ReqInfo;
import com.snowball.embroider.component.IComponent;
import componentArchitecture.Requirement;
import instances.Entity;
import com.snowball.embroider.util.component.CompRequirement;
import com.snowball.mod.ModelConverter;
import utils.CSVReader;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class CustomRequirement implements Requirement, CompRequirement {
	private final Map<Boolean, String> labels;
	private Map<Object, Boolean> req;
	private final List<Object> os;
	
	private final boolean secret;

	private final CSVReader reader;

	private final String compName;
	private final String reqName;
	private final String reqInfo;
	
	protected CustomRequirement(CSVReader reader, String requirementName, String requirementInfo, boolean isSecret, Map<Object, Boolean> requirements, List<Object> objectList, List<String> labels, IComponent comp) {
		this.labels = BlueprintUtils.mapLabels(requirements, labels);
		this.compName = comp.name();

		this.reqInfo = requirementInfo;
		this.reqName = requirementName;
		this.secret = isSecret;
		this.reader = reader;
		this.os = objectList;

		if (requirements.size() >= labels.size()) {
			this.req = requirements;
		} else {
			ModelConverter.log("There are less labels than objects in your custom component");
		}
	}
	
	@Override
	public Collection<String> req() {
		return BlueprintUtils.load(os, labels, compName);
	}
	
	@Override
	public boolean isSecret() {
		return secret;
	}
			
	@Override
	public void getGuiInfo(List<ReqInfo> components) {
		components.add(new ReqInfo(reqName, reqInfo));
	}

	@Override
	public boolean check(Entity entity) {
		return checkEntity(BlueprintUtils.readMap(req, reader));
	}

	public abstract boolean checkEntity(List<Object> os);
}
