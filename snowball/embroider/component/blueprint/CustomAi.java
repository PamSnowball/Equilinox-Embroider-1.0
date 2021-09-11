package com.snowball.embroider.component.blueprint;

import aiComponent.AiProgramBlueprint;
import aiComponent.AiProgramType;
import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CustomAi extends NativeComponent implements AiProgramBlueprint {
	protected List<Comp.CompData> list = new ArrayList<>();

	private final AiProgramType type;

	private final String name;

	protected void setData(Comp.CompData data) {
		list.add(data);
	}

	protected CustomAi(String name) {
		AiProgramBlueprint blueprint = this;
		this.type = new AiProgramType() {
			@Override
			protected AiProgramBlueprint createProgramBlueprint() {
				return blueprint;
			}
		};

		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void loadSettings(CSVReader reader) {
		BlueprintUtils.read(list, reader);
	}

	public AiProgramType getType() {
		return type;
	}

	@Override
	public Collection<String> load(CustomEntity entity) {
		return BlueprintUtils.load("AI;" + name, list);
	}

	@Override
	public int getId() {
		return 31;
	}
}
