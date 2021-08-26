package com.snowball.embroider.mod.load;

import aiComponent.AiProgramType;
import com.snowball.embroider.component.blueprint.Comp;
import com.snowball.embroider.component.blueprint.CustomAi;
import com.snowball.embroider.mod.Mod;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initializer {
	private static final Map<Entity, Mod> map = new HashMap<>();

	static int componentId = 2000;

	Mod mod;

	public static int getCompId() {
		return componentId;
	}

	public Initializer(Mod mod) {
		this.mod = mod;
	}

	public void setEntity(Entity entity) {
		Embroider.addEntity(entity);
		map.put(entity, mod);
	}
	
	public <T extends Enum<T> & IClassifier> void addCustomClassifications(Class<T> classifier) {
		Embroider.addClassification(classifier);
	}
	
	public void addCustomAi(CustomAi ai) {
		AiProgramType.addCustomAi(ai.getName(), ai.getType());
	}
	
	public void addCustomComponent(Comp components) {
		Embroider.addComponent(components);
	}

	public static List<Entity> getEntities() {
		return new ArrayList<>(map.keySet());
	}
	
	public static Mod getModFromEntity(Entity entity) {
		for (Map.Entry<Entity, Mod> entry : map.entrySet()) {
			if (entry.getKey().equals(entity)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public static List<Mod> getMods() {
		return ModLoader.loadedMods;
	}

	public static boolean isModPresent(Mod mod) {
		return ModLoader.loadedMods.contains(mod);
	}
}