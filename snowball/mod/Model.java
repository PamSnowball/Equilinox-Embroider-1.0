package com.snowball.mod;

import com.snowball.embroider.util.Utils;
import com.snowball.embroider.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
	private final List<List<Integer>> pointer = new ArrayList<>();
	private final List<List<String>> faces = new ArrayList<>();
	private final List<List<Float>> vertex = new ArrayList<>();
	private final List<List<Float>> normal = new ArrayList<>();
	private final List<List<Float>> colour = new ArrayList<>();

	private final String entity;

	public Model(List<ModelConverter.EquiliModel> models, Entity rawEntity) {
		for (ModelConverter.EquiliModel model : models) {
			pointer.add(model.getPointer());
			vertex.add(model.getVertex());
			normal.add(model.getNormal());
			colour.add(model.getColour());
			faces.add(model.getFaces());
		}
		
		List<String> rawEntities = rawEntity.load();
		List<String> components = rawEntity.loadComponents();
		
		Map<String, Integer> compMap = new HashMap<>();
		
		for (int i = 0; i < components.size(); i++) {
			compMap.put(components.get(i), rawEntity.getComponentIds().get(i));
		}
		
		Map<String, Integer> map = Utils.organizeList(compMap);
		
		List<String> componentList = new ArrayList<>(map.keySet());
		
		rawEntities.add(String.valueOf(models.size()));
		rawEntities.add("\n");
		
		this.entity = convert(models.size(), rawEntities, componentList);
	}

	private String convert(int size, List<String> entities, List<String> components) {
		int j = 1;
		
		for (int k = 0; k < size; k++) {
			int point = colour.get(k).size() / 3 - 1;
			
			entities.add(insert(String.format("%.4f", getMin(vertex.get(k), 0))) + ";");
			entities.add(insert(String.format("%.4f", getMin(vertex.get(k), 1))) + ";");
			entities.add(insert(String.format("%.4f", getMin(vertex.get(k), 2))) + ";");
			
			entities.add(insert(String.format("%.4f", getMax(vertex.get(k), 0))) + ";");
			entities.add(insert(String.format("%.4f", getMax(vertex.get(k), 1))) + ";");
			entities.add(insert(String.format("%.4f", getMax(vertex.get(k), 2))) + ";");
			
			entities.add("1");
			entities.add("\n");
			
			int pointerSize = pointer.get(k).size() - 1;
			String facesSize = faces.get(k).size() - pointerSize + ";";
			entities.add(facesSize + pointerSize);

			for(int i = 0; i < faces.get(k).size(); i++) {	
				if (faces.get(k).get(i).contains("//")) {
					String[] convertedFaces = faces.get(k).get(i).split("//");
					int faces0 = Integer.parseInt(convertedFaces[0]) - 1;
					int faces1 = Integer.parseInt(convertedFaces[1]) - 1;
					entities.add(insert(String.format("%.4f", vertex.get(k).get(faces0 * 3))) + ";");
					entities.add(insert(String.format("%.4f", vertex.get(k).get(faces0 * 3 + 1))) + ";");
					entities.add(insert(String.format("%.4f", vertex.get(k).get(faces0 * 3 + 2))) + ";");
					entities.add(insert(String.format("%.4f", normal.get(k).get(faces1 * 3))) + ";");
					entities.add(insert(String.format("%.4f", normal.get(k).get(faces1 * 3 + 1))) + ";");
					entities.add(insert(String.format("%.4f", normal.get(k).get(faces1 * 3 + 2))));
					if (i + 1 < faces.get(k).size() && !faces.get(k).get(i + 1).contains("pointer"))
						entities.add(";");
					}
			
				if (faces.get(k).get(i).contains("pointer")) {
					entities.add("\n");
					entities.add(pointer.get(k).get(j) - pointer.get(k).get(j - 1) + ";");
					entities.add(colour.get(k).get(point * 3).toString() + ";");
					entities.add(colour.get(k).get(point * 3 + 1).toString() + ";");
					entities.add(colour.get(k).get(point * 3 + 2).toString());
					entities.add("\n");
					point--;
					j++;
				}
			}
			
			j = 1;
			entities.add("\n");
		}
		
		String outputEntity = entities.toString().replace(",", "").replace("[", "").replace("]", "");
		
		entities.clear();
		
		entities.add(String.valueOf(components.size()));
		entities.add("\n");
		components.forEach(component -> { entities.add(component); entities.add("\n"); });

		StringBuilder builder = new StringBuilder();
		
		entities.forEach(builder::append);
		
		String componentText = builder.toString();

		return outputEntity + componentText.replace("[", "").replace("]", "");
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
