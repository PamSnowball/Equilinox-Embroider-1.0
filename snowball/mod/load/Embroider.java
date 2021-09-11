package com.snowball.mod.load;

import biomes.Biome;
import classification.Classifier;
import com.snowball.embroider.component.blueprint.Comp;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.enumerator.classification.IClassifier;
import componentArchitecture.ComponentType;
import resourceManagement.BlueprintRepository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Embroider {
	private Embroider() {}
	
	static <T extends Enum<T> & IClassifier> void addClassification(Class<T> classifier) {
		Method[] methods = classifier.getDeclaredMethods();
		T[] constants = classifier.getEnumConstants();
		
		for (Method method : methods) if (method.getName().equals("toString")) {
			Classifier.addCustomClassifications(toString(constants));
		}
	}
	
	private static <T extends Enum<T> & IClassifier> List<String> toString(T[] constants) {
		List<String> classifications = new ArrayList<>();
		
		for (T constant : constants) {
			String classification = constant.name().toLowerCase();
			String[] names = classification.split("_");
			
			StringBuilder builder = new StringBuilder();
			for (String name : names) {
				String first = String.valueOf(name.charAt(0)).toUpperCase();
				
				if (builder.length() > 0) builder.append(' ');
				builder.append(first).append(name.substring(1));
			}
			
			classifications.add(constant.getType() + ';' + builder + ';' + constant.needsSuperReplacement());
		}

		return classifications;
	}

	static void addEntity(CustomEntity entity) {
		System.out.println("Adding custom entity " + entity.getName());
		if (CustomEntity.isValid(entity)) {
			BlueprintRepository.addCustomEntity(entity);
		}
	}

	static void addComponent(Comp comp) {
		ComponentType.addCustomComponent("CUSTOM_" + comp.name(), comp.getType());
	}

	static void addBiome(Biome biome) {
		Biome.addCustomComponent(biome.toString(), biome);
	}
}
