package com.snowball.embroider.component.blueprint;

import aiComponent.AiProgramBlueprint;
import aiComponent.AiProgramType;
import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import utils.CSVReader;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public abstract class CustomAi extends NativeComponent implements AiProgramBlueprint {
	protected Map<String, Object> map;

	private final AiProgramType type;

	private final String name;

	protected void setData(Comp.CompData data) {
		map.put(data.s, data.o);
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
		BlueprintUtils.read(map, reader);
	}

	public AiProgramType getType() {
		return type;
	}

	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton("AI;" + name);
	}

	@Override
	public int getId() {
		return 31;
	}
}
