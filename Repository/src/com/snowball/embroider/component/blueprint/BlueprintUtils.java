package com.snowball.embroider.component.blueprint;

import com.snowball.embroider.util.Vector;
import utils.CSVReader;

import java.util.*;

public class BlueprintUtils {
	private BlueprintUtils() {
		throw new IllegalStateException("Utility Class!");
	}

	static List<Object> readMap(Map<Object, Boolean> map, CSVReader reader) {
		List<Object> os = new ArrayList<>();
		for (int i = 0; i < map.size(); i++) {
			Object[] o = map.keySet().toArray();
			Boolean[] b = map.values().toArray(new Boolean[0]);
			if (Boolean.TRUE.equals(b[i]) && !(o[i] instanceof Float[]) && !(o[i] instanceof Integer[]) && !(o[i] instanceof Long))
				reader.getNextString();

			else if (o[i] instanceof float[]) {
				os.add(reader.getNextLabelFloatArray());
				map.replace(o[i], true);
			} else if (o[i] instanceof int[]) {
				os.add(reader.getNextLabelIntArray());
				map.replace(o[i], true);
			} else if (o[i] instanceof Vector) os.add(reader.getNextVector());
			else if (o[i] instanceof String) os.add(reader.getNextString());
			else if (o[i] instanceof Boolean) os.add(reader.getNextBool());
			else if (o[i] instanceof Float) os.add(reader.getNextFloat());
			else if (o[i] instanceof Integer) os.add(reader.getNextInt());
			else if (o[i] instanceof Long) {
				os.add(reader.getNextLong());
				map.replace(o[i], false);
			}
		}

		return os;
	}
	

	static Collection<String> load(List<Object> os, Map<Boolean, String> labels, String name) {
		List<String> component = new ArrayList<>();
		
		for (int i = 0; i < os.size(); i++) {
			String label = (String) labels.values().toArray()[i];

			if (Boolean.TRUE.equals(labels.keySet().toArray()[i]) && label != null) component.add(label);

			component.add(name);

			component.addAll(loadObjects(os.get(i)));
		}

		return component;
	}
	
	private static List<String> loadObjects(Object o) {
		List<String> component = new ArrayList<>();
		if (o instanceof float[]) {
			float[] floats = (float[]) o;
			component.add(((float[]) o).length + ";");
			for (float f : floats) component.add(f + ";");
		} else if (o instanceof int[]) {
			component.add(((int[]) o).length + ";");
			for (int i : (int[]) o) component.add(i + ";");
		} else if (o instanceof Boolean) {
			component.add((Boolean.TRUE.equals(o) ? 1 : 0) + ";");
		} else if (o instanceof Integer ||
				o instanceof String ||
				o instanceof Vector ||
				o instanceof Float ||
				o instanceof Long) {
			component.add(o + ";");
		}

		return component;
	}
	
	public static Map<Boolean, String> mapLabels(Map<Object, Boolean> map, List<String> labels) {
		Map<Boolean, String> labelMap = new HashMap<>();
		
		for (int i = 0; i < map.size(); i++) {
			Boolean[] b = map.values().toArray(new Boolean[0]);
			if (Boolean.TRUE.equals(b[i])) {
				labelMap.put(b[i], labels.get(i));
			} else {
				labelMap.put(b[i], null);
			}
		}

		return labelMap;
	}
}
