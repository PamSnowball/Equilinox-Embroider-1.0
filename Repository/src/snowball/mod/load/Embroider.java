package snowball.mod.load;

import classification.Classifier;
import componentArchitecture.ComponentType;
import resourceManagement.BlueprintRepository;
import snowball.embroider.component.blueprint.Comp;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Embroider {
	private Embroider() {}

	static int id = 10000;
	
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
		BlueprintRepository.addCustomEntity(id, entity);
	}

	public static void addComponent(Comp comp) {
		ComponentType.addCustomComponent(comp.toString(), comp.getType());
	}
}
