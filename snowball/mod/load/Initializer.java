package com.snowball.mod.load;

import aiComponent.AiProgramType;
import biomes.Biome;
import com.snowball.embroider.component.blueprint.Comp;
import com.snowball.embroider.component.blueprint.CustomAi;
import com.snowball.mod.Mod;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initializer {
	private static final Map<CustomEntity, Mod> map = new HashMap<>();

	Mod mod;

	Initializer(Mod mod) {
		System.out.println("Initializing " + mod.getModInfo().modName());
		this.mod = mod;
	}

	public void addEntity(CustomEntity entity) {
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

	public void addCustomBiome(Biome biome) {
		Embroider.addBiome(biome);
	}

	public static List<CustomEntity> getEntities() {
		return new ArrayList<>(map.keySet());
	}
	
	public static Mod getModFromEntity(CustomEntity entity) {
		for (Map.Entry<CustomEntity, Mod> entry : map.entrySet()) {
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
