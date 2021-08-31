package com.snowball.embroider.component.blueprint;

import blueprints.Blueprint;
import com.snowball.embroider.component.IComponent;
import componentArchitecture.ComponentLoader;
import componentArchitecture.Requirement;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.ModelConverter;
import utils.CSVReader;

import java.util.*;

public abstract class Comp implements ComponentLoader, IComponent {
	private final CustomComponentType type;
	
	private final CustomRequirement requirement;
	private final CustomComponent component;
	
	private final boolean dynamic;
	private final boolean active;
	
	private final Map<Boolean, String> labels;

	private final String name;

	private final int id;

	private Map<Object, Boolean> map = new HashMap<>();
	
	private List<Object> os = new ArrayList<>();

	protected Comp(int id, Map<Object, Boolean> map, List<String> labels, CustomComponentType type, CustomComponent component, CustomRequirement requirement) {
		this.labels = BlueprintUtils.mapLabels(map, labels);

		this.id = id;

		this.requirement = requirement;
		this.component = component;
		this.type = type;

		this.active = type.isActive();

		this.dynamic = type.dynamic;
		this.name = type.component;

		if (map.size() >= labels.size()) {
			this.map = map;
		} else {
			ModelConverter.log("There are less labels than objects in your custom component");
		}
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return BlueprintUtils.load(os, labels, name);
	}

	@Override
	public final int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean isDynamic() {
		return dynamic;
	}
	
	@Override
	public Requirement loadRequirement(CSVReader reader) {
		return requirement;
	}
	
	@Override
	public CustomComponentBlueprint load(CSVReader reader, Blueprint blueprint) {
		if (map != null) os = BlueprintUtils.readMap(map, reader);
		if (type != null && component != null) {
			return new CustomComponentBlueprint(os, type, component);
		}
		
		return null;
	}

	public CustomComponentType getType() {
		return type;
	}
}
