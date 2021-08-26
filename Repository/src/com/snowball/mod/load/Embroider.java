package com.snowball.mod.load;

import classification.Classifier;
import com.snowball.embroider.component.blueprint.Comp;
import componentArchitecture.ComponentType;
import resourceManagement.BlueprintRepository;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Embroider {
	private Embroider() {}
	
	public static <T extends Enum<T> & IClassifier> void addClassification(Class<T> classifier) {
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

	static void addEntity(Entity entity) {
		if (Entity.isValid(entity)) {
			BlueprintRepository.addCustomEntity(entity);
		}
	}

	public static void addComponent(Comp comp) {
		ComponentType.addCustomComponent(comp.toString(), comp.getType());
	}
}
