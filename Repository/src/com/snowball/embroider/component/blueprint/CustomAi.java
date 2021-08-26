package com.snowball.embroider.component.blueprint;

import aiComponent.AiProgramBlueprint;
import aiComponent.AiProgramType;
import com.snowball.embroider.component.architecture.Ai;
import componentArchitecture.ComponentBundle;
import utils.CSVReader;

import java.util.Map;

public abstract class CustomBaseAi extends Ai implements AiProgramBlueprint {
	protected Map<Object, Boolean> map;

	private final AiProgramType type;

	private final String name;

	protected CustomBaseAi(String name) {
		super(name);

		AiProgramBlueprint blueprint = this;
		this.type = new AiProgramType() {
			@Override
			protected AiProgramBlueprint createProgramBlueprint() {
				return blueprint;
			}
		};

		this.map = this.setMap();
		this.name = name;
	}
	
	@Override
	public aiComponent.Ai createInstance(ComponentBundle bundle) {
		return this.getInstance();
	}
	
	public abstract Map<Object, Boolean> setMap();
	
	public abstract aiComponent.Ai getInstance();

	public String getName() {
		return name;
	}

	@Override
	public void loadSettings(CSVReader reader) {
		BlueprintUtils.readMap(map, reader);
	}

	public AiProgramType getType() {
		return type;
	}
}
