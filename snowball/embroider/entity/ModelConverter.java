package com.snowball.embroider.entity;

import com.snowball.embroider.util.Utils;
import com.snowball.mod.load.ModLoader;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
	private ModelConverter() {}

	public static final String POINTER = "pointer";

	public static final File LOG_FILE = new File("log/");
	
	static StringBuilder output = new StringBuilder();

	private static int i;

	public static void log(String log) {
		System.err.println(log);

		output.append(log).append("\n");
	}

	public static void init() {
		Utils.checkForEquilinoxJar();

		ModLoader.init();

		LOG_FILE.mkdirs();
	}

	public static void postInit() {
		if (output.length() > 0) {
			output.append("\n");

			File outputFile = new File("log/" + LocalDate.now() + "_" +  LocalTime.now().toString().replace(':', '-').replace('.', '-') + ".txt");

			try (FileWriter writer = new FileWriter(outputFile)) {
				writer.write(output.toString().replace(",", "").replace(" ", "").replace("[", "").replace("]", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String convert(CustomEntity entity) throws IOException {
		String path = "/entities/" + entity.getName();
		System.out.println("Trying to convert " + entity.getName());

		entity.init();

		return convert(path, entity);
	}

	private static String convert(String path, CustomEntity entity) throws IOException {
    	InputStream[] objStreams = new InputStream[entity.getStages()];
		InputStream[] mtlStreams = new InputStream[entity.getStages()];
		
		for (int l = 0; l < entity.getStages(); l++) {
			InputStream obj = entity.getClass().getResourceAsStream(path + "_" + l + ".obj");
			InputStream mtl = entity.getClass().getResourceAsStream(path + "_" + l + ".mtl");

			if (obj != null) { objStreams[l] = obj; }
			if (mtl != null) { mtlStreams[l] = mtl; }

			System.out.println("Got file: " + path + "_" + l);
		}

		List<EquiliModel> models = new ArrayList<>();
		
		for (int j = 0; j < objStreams.length; j++) {
			List<Integer> pointer = new ArrayList<>();

			List<String> faces = new ArrayList<>();

			List<Float> vertex = new ArrayList<>();
			List<Float> normal = new ArrayList<>();
			List<Float> colour = new ArrayList<>();

			List<String> objLines = new ArrayList<>();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(objStreams[j]))) {
				while (reader.ready()) objLines.add(reader.readLine());
			}

			List<String> mtlLines = new ArrayList<>();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(mtlStreams[j]))) {
				while (reader.ready()) mtlLines.add(reader.readLine());
			}

			List<List<String>> stringList = new ArrayList<>();
			stringList.add(faces);
			stringList.add(mtlLines);

			List<List<Float>> floatList = new ArrayList<>();
			floatList.add(vertex);
			floatList.add(normal);
			floatList.add(colour);

			for (String objLine : objLines) lineReader(stringList, pointer, floatList, objLine);

			pointer.add(i);

			checkMaterials(entity, j, colour);

			models.add(new EquiliModel(pointer, vertex, normal, colour, faces));
		}

		return new Model(models, entity).get();
	}

	private static void checkMaterials(CustomEntity entity, int j, List<Float> colour) {
		if (entity.hasMaterial() && !(entity.hasEggStage() && j == 0)) {
			for (int i = 0; i < colour.size(); i++) {
				if (i % 6 == 0) {
					colour.set(i, -1F);
					colour.set(i + 1, 0F);
					colour.set(i + 2, 0F);
				}
			}
		}

		System.out.println(colour);
	}

	private static void lineReader(List<List<String>> stringList, List<Integer> pointer, List<List<Float>> floatList, String objLine) {
		List<String> faces = stringList.get(0);
		List<String> mtlLines = stringList.get(1);
		
		List<Float> vertex = floatList.get(0);
		List<Float> normal = floatList.get(1);
		List<Float> colour = floatList.get(2);
 		
		String[] objSplit = objLine.split(" ");
		
		if (objSplit[0].equals("v")) {
			vertex.add(Float.parseFloat(objSplit[1]));
			vertex.add(Float.parseFloat(objSplit[2]));
			vertex.add(Float.parseFloat(objSplit[3]));
		}
	
		if (objSplit[0].equals("vn")) {
			normal.add(Float.parseFloat(objSplit[1]));
			normal.add(Float.parseFloat(objSplit[2]));
			normal.add(Float.parseFloat(objSplit[3]));
		}

		if (objSplit[0].equals("usemtl")) {
			faces.add(POINTER);
			pointer.add(i);
			for (String mtlLine : mtlLines) {
				String[] mtlSplit = mtlLine.split(" ");
				if (mtlSplit[0].equals("Kd")) {
					colour.add(Float.parseFloat(mtlSplit[1]));
					colour.add(Float.parseFloat(mtlSplit[2]));
					colour.add(Float.parseFloat(mtlSplit[3]));
				}
			}
		}
	
		if (objSplit[0].equals("f")) {
			faces.add(objSplit[1]); 
			faces.add(objSplit[2]); 
			faces.add(objSplit[3]);
			i += 3;
		} 
	}

	static class EquiliModel {
		private final List<Integer> pointer;
		
		private final List<Float> vertex;
		private final List<Float> normal;
		private final List<Float> colour;
	
		private final List<String> faces;
		
		EquiliModel(List<Integer> pointer, List<Float> vertex, List<Float> normal, List<Float> colour, List<String> faces) {
			this.pointer = pointer;
			this.vertex = vertex;
			this.normal = normal;
			this.colour = colour;
			this.faces = faces;
		}

		public List<Integer> getPointer() { return pointer; }
		
		public List<Float> getVertex() { return vertex; }
		public List<Float> getNormal() { return normal; }
		public List<Float> getColour() { return colour; }
		
		public List<String> getFaces() { return faces; }
	}

	static class Model {
		private final List<List<Integer>> pointer = new ArrayList<>();
		private final List<List<String>> faces = new ArrayList<>();
		private final List<List<Float>> vertex = new ArrayList<>();
		private final List<List<Float>> normal = new ArrayList<>();
		private final List<List<Float>> colour = new ArrayList<>();

		private final String entity;

		Model(List<EquiliModel> models, CustomEntity rawEntity) {
			for (EquiliModel model : models) {
				pointer.add(model.getPointer());
				vertex.add(model.getVertex());
				normal.add(model.getNormal());
				colour.add(model.getColour());
				faces.add(model.getFaces());
			}

			this.entity = convert(models.size(), rawEntity, rawEntity.loadComponents());
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

				entities.addAll(loadModel(k));
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
			String result = outputEntity + componentText.replace("[", "").replace("]", "");
			System.out.println(result);
			return result;
		}

		private List<String> loadModel(int stage) {
			List<String> entities = new ArrayList<>();

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
					if (i + 1 < faces.get(stage).size() && !faces.get(stage).get(i + 1).contains(POINTER))
						entities.add(";");
				}

				if (faces.get(stage).get(i).contains(POINTER)) {
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
			return entities;
		}

		static float getMax(List<Float> floatList, int axis) {
			float max = floatList.get(axis);
			for (int i = axis; i < floatList.size(); i += 3)
				if (floatList.get(i) > max) max = floatList.get(i);
			return max;
		}

		static float getMin(List<Float> floatList, int axis) {
			float min = floatList.get(axis);
			for (int i = axis; i < floatList.size(); i += 3)
				if (floatList.get(i) < min) min = floatList.get(i);
			return min;
		}

		static String insert(String target) {
			int position = 1;
			if (target.contains("-")) position = 2;
			final int targetLen = target.length();
			final char[] buffer = new char[targetLen + 1];
			target.getChars(0, position, buffer, 0);
			".".getChars(0, 1, buffer, position);
			target.getChars(position, targetLen, buffer, position + 1);
			return new String(buffer);
		}

		String get() {
			return entity;
		}
	}
}
