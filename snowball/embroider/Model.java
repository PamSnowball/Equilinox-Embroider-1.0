package com.snowball.embroider;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private final List<List<Integer>> pointer = new ArrayList<>();
	private final List<List<String>> faces = new ArrayList<>();
	private final List<List<Float>> vertex = new ArrayList<>();
	private final List<List<Float>> normal = new ArrayList<>();
	private final List<List<Float>> colour = new ArrayList<>();

	private final String entity;

	public Model(List<ModelConverter.EquiliModel> models, CustomEntity rawEntity) {
		for (ModelConverter.EquiliModel model : models) {
			pointer.add(model.getPointer());
			vertex.add(model.getVertex());
			normal.add(model.getNormal());
			colour.add(model.getColour());
			faces.add(model.getFaces());
		}

		List<String> components = rawEntity.loadComponents();

		this.entity = convert(models.size(), rawEntity, components);
	}

	private String convert(int size, CustomEntity entity, List<String> components) {
		List<String> entities = new ArrayList<>();

		String entityInfo = entity.load() + size + "\n";

		for (int k = 0; k < size; k++) {
			entities.add(insert(String.format("%.4f", getMin(vertex.get(k), 0))) + ";");
			entities.add(insert(String.format("%.4f", getMin(vertex.get(k), 1))) + ";");
			entities.add(insert(String.format("%.4f", getMin(vertex.get(k), 2))) + ";");
			
			entities.add(insert(String.format("%.4f", getMax(vertex.get(k), 0))) + ";");
			entities.add(insert(String.format("%.4f", getMax(vertex.get(k), 1))) + ";");
			entities.add(insert(String.format("%.4f", getMax(vertex.get(k), 2))) + ";");
			
			entities.add("1");
			entities.add("\n");

			int pointerSize = pointer.get(k).size() - 1;
			if (vertex.size() > 1000) ModelConverter.log("Model of stage " + k + " of entity " + entity.getName() + " has " + vertex.size() + " vertices, more than the allowed 1000 vertices");

			entities.add(faces.get(k).size() - pointerSize + ";" + pointerSize);

			loadModel(entities, k);
		}

		String outputEntity =
				entityInfo.replace(",", "").replace("[", "").replace("]", "") +
				entities.toString().replace(",", "").replace("[", "").replace("]", "").replace(" ", "");

		entities.clear();
		
		entities.add(String.valueOf(components.size()));
		entities.add("\n");

		components.forEach(component -> { entities.add(component); entities.add("\n"); });

		StringBuilder builder = new StringBuilder();
		
		entities.forEach(builder::append);
		
		String componentText = builder.toString();

		return outputEntity + componentText.replace("[", "").replace("]", "");
	}

	private void loadModel(List<String> entities, int stage) {
		int j = 1;

		int point = colour.get(stage).size() / 3 - 1;
		for(int i = 0; i < faces.get(stage).size(); i++) {
			if (faces.get(stage).get(i).contains("//")) {
				String[] convertedFaces = faces.get(stage).get(i).split("//");
				int faces0 = Integer.parseInt(convertedFaces[0]) - 1;
				int faces1 = Integer.parseInt(convertedFaces[1]) - 1;
				entities.add(insert(String.format("%.4f", vertex.get(stage).get(faces0 * 3))) + ";");
				entities.add(insert(String.format("%.4f", vertex.get(stage).get(faces0 * 3 + 1))) + ";");
				entities.add(insert(String.format("%.4f", vertex.get(stage).get(faces0 * 3 + 2))) + ";");
				entities.add(insert(String.format("%.4f", normal.get(stage).get(faces1 * 3))) + ";");
				entities.add(insert(String.format("%.4f", normal.get(stage).get(faces1 * 3 + 1))) + ";");
				entities.add(insert(String.format("%.4f", normal.get(stage).get(faces1 * 3 + 2))));
				if (i + 1 < faces.get(stage).size() && !faces.get(stage).get(i + 1).contains("pointer"))
					entities.add(";");
				}

			if (faces.get(stage).get(i).contains("pointer")) {
				entities.add("\n");
				entities.add(pointer.get(stage).get(j) - pointer.get(stage).get(j - 1) + ";");
				entities.add(colour.get(stage).get(point * 3).toString() + ";");
				entities.add(colour.get(stage).get(point * 3 + 1).toString() + ";");
				entities.add(colour.get(stage).get(point * 3 + 2).toString());
				entities.add("\n");
				point--;
				j++;
			}
		}

		entities.add("\n");
	}

	public static Float getMax(List<Float> floatList, int axis) {
		Float max = floatList.get(axis); 
		for(int i = 0; i < floatList.size(); i += 3)
			if(floatList.get(i) > max) max = floatList.get(i);
		return max;
	}
	
	public static Float getMin(List<Float> floatList, int axis) {
		Float min = floatList.get(axis); 
		for(int i = 0; i < floatList.size(); i += 3)
			if(floatList.get(i) < min) min = floatList.get(i);
		return min;
	}
	
	public static String insert(String target) {
	    int position = 1;
	    if (target.contains("-")) position = 2;
		final int targetLen = target.length();
	    final char[] buffer = new char[targetLen + 1];
	    target.getChars(0, position, buffer, 0);
	    ".".getChars(0, 1, buffer, position);
	    target.getChars(position, targetLen, buffer, position + 1);
	    return new String(buffer);
	}

	public String get() {
		return entity;
	}
}
