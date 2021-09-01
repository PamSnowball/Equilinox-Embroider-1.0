package com.snowball.embroider;

import com.snowball.embroider.util.Utils;
import com.snowball.mod.load.ModLoader;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
	private ModelConverter() {}

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

			if (entity.hasMaterial()) {
				colour.set(0, -1F);
				colour.set(1, 0F);
				colour.set(2, 0F);
			}

			models.add(new EquiliModel(pointer, vertex, normal, colour, faces));
		}

		return new Model(models, entity).get();
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
			faces.add("pointer");
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
}
