package com.snowball.embroider.component.blueprint;

import blueprints.Blueprint;
import com.snowball.embroider.component.IComponent;
import com.snowball.embroider.entity.CustomEntity;
import componentArchitecture.Component;
import componentArchitecture.ComponentBlueprint;
import componentArchitecture.ComponentLoader;
import componentArchitecture.ComponentType;
import speciesInformation.SpeciesInfoLine;
import speciesInformation.SpeciesInfoType;
import utils.CSVReader;

import java.util.*;

public abstract class Comp extends ComponentBlueprint implements ComponentLoader, IComponent {
	public static class CompData {
		boolean optional = false;

		final String label;
		final Object value;

		public CompData(String label, Object value) {
			this.label = label;
			this.value = value;
		}

		public CompData setOptional() {
			this.optional = true;
			return this;
		}
	}

	final List<CompData> list = new ArrayList<>();

	CustomComponentType type;
	Component component;

	String name;

	int id;

	protected void setData(CompData data) {
		list.add(data);
	}

	protected Comp(int id, CustomComponentType type) {
		super(type);

		this.id = id;

		this.type = type;
		this.component = createInstance();

		type.set(this);

		this.name = type.component.toUpperCase(Locale.ROOT).replaceAll("[^A-Z_ ]", "").replace(" ", "_");
	}

	public ComponentType getType() {
		return type;
	}

	@Override
	public Collection<String> load(CustomEntity entity) {
		return BlueprintUtils.load(name, list);
	}

	@Override
	public final int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public ComponentBlueprint load(CSVReader reader, Blueprint blueprint) {
		BlueprintUtils.read(list, reader);
		return this;
	}

	@Override public void delete() {}

	@Override public void getInfo(Map<SpeciesInfoType, List<SpeciesInfoLine>> map) {}
}
