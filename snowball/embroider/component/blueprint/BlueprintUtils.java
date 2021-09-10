package com.snowball.embroider.component.blueprint;

import com.snowball.embroider.util.Vector;
import utils.CSVReader;

import java.util.*;

public class BlueprintUtils {
	private BlueprintUtils() {}

	static Collection<String> load(Comp comp) {
		List<String> component = new ArrayList<>();

		if (comp == null) {
			return component;
		}

		component.add(comp.name + ";");
		for (Map.Entry<String, Object> entry : comp.map.entrySet()) {
			boolean optional = false;
			String label = entry.getKey();
			if (label.startsWith("optional")) {
				label = label.substring(8);
				optional = true;
			}

			component.add(writeType(entry.getValue(), label, optional) + ";");
		}

		return component;
	}


	private static String writeType(Object o, String label, boolean b) {
		if (o instanceof Boolean) {
			return label + ';' + ((boolean) o ? "0" : "1");
		} else {
			float[] fs;
			if (o instanceof float[]) {
				fs = (float[]) o;
			} else if (o instanceof int[]) {
				int[] is = (int[]) o;
				fs = new float[is.length];
				for (int i = 0; i < is.length; i++) {
					fs[i] = is[i];
				}
			} else return elseZero(o, label, b);

			StringBuilder builder = new StringBuilder();
			builder.append(fs.length).append(";");
			for (float f : fs) builder.append(f);
			return builder.toString();
		}
	}

	private static String elseZero(Object o, String label, boolean b) {
		if (o == null) {
			return "";
		}

		if (b) {
			if (o instanceof Number && ((Number) o).floatValue() != 0) {
				return label + ';' + o;
			} else {
				return "";
			}
		}

		return label + ';' + o;
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

	public static void read(Map<String, Object> map, CSVReader reader) {
		String label;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			label = entry.getKey();

			if (label.startsWith("optional")) {
				if (!reader.isEndOfLine()) {
					readEntry(entry.getValue(), reader);
				}
			} else {
				readEntry(entry.getValue(), reader);
			}
 		}
	}

	private static void readEntry(Object o, CSVReader reader) {
		if (!(o instanceof Long)) reader.getNextString();

		if (o instanceof float[]) reader.getNextLabelFloatArray();
		else if (o instanceof int[]) reader.getNextLabelIntArray();
		else if (o instanceof Vector) reader.getNextLabelVector();
		else if (o instanceof String) reader.getNextLabelString();
		else if (o instanceof Boolean) reader.getNextLabelBool();
		else if (o instanceof Float) reader.getNextLabelFloat();
		else if (o instanceof Integer) reader.getNextLabelInt();
		else if (o instanceof Long) reader.getNextLong();
	}
}
