package com.snowball.embroider.component.blueprint;

import com.snowball.embroider.util.Vector;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlueprintUtils {
	private BlueprintUtils() {}

	static Collection<String> load(String name, List<Comp.CompData> list) {
		List<String> component = new ArrayList<>();

		if (name == null || list == null) {
			return component;
		}

		component.add(name + ";");
		for (Comp.CompData data : list) {
			component.add(writeType(data) + ";");
		}

		return component;
	}


	private static String writeType(Comp.CompData data) {
		final boolean optional = data.optional;

		final String label = data.label;
		final Object value = data.value;
		if (value instanceof Boolean) {
			return label + ';' + ((boolean) value ? "0" : "1");
		} else {
			float[] fs;
			if (value instanceof float[]) {
				fs = (float[]) value;
			} else if (value instanceof int[]) {
				int[] is = (int[]) value;
				fs = new float[is.length];
				for (int i = 0; i < is.length; i++) {
					fs[i] = is[i];
				}
			} else return elseZero(value, label, optional);

			StringBuilder builder = new StringBuilder();
			builder.append(fs.length).append(";");
			for (float f : fs) builder.append(f);
			return builder.toString();
		}
	}

	private static String elseZero(Object value, String label, boolean optional) {
		if (value == null) {
			return "";
		}

		if (optional) {
			if (value instanceof Number && ((Number) value).floatValue() != 0) {
				return label + ';' + value;
			} else {
				return "";
			}
		}

		return label + ';' + value;
	}

	public static void read(List<Comp.CompData> list, CSVReader reader) {
		for (Comp.CompData data : list) {
			if (data.optional) {
				if (!reader.isEndOfLine()) {
					readEntry(data.value, reader);
				} else {
					break;
				}
			} else {
				readEntry(data.value, reader);
			}
		}
	}

	private static void readEntry(Object o, CSVReader reader) {
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
