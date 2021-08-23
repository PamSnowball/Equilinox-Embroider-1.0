package snowball.embroider.component.blueprint;

import aiComponent.Ai;
import aiComponent.AiProgramBlueprint;
import aiComponent.AiProgramType;
import componentArchitecture.ComponentBundle;
import snowball.embroider.component.architecture.Ai.BaseAi;
import utils.CSVReader;

import java.util.Map;

public abstract class CustomAi extends BaseAi implements AiProgramBlueprint {
	protected Map<Object, Boolean> map;

	private final AiProgramType type;

	private final String name;


	protected CustomAi(String name) {
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
	public Ai createInstance(ComponentBundle bundle) {
		return this.getInstance();
	}
	
	public abstract Map<Object, Boolean> setMap();
	
	public abstract Ai getInstance();

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
