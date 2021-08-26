package com.snowball.mod.load;

import com.snowball.mod.Mod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
	private ModLoader() {
		throw new IllegalStateException("Utility Class!");
	}

	protected static final List<File> modJars = new ArrayList<>();

	static final List<Mod> loadedMods = new ArrayList<>();

	private static boolean isMod = false;
	
	public static void load() {
		loadMods();
		initializeMods();
	}
	
	private static void loadMods() {
		try {
			URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			
			Method url = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);

			File mods = new File("mods");

			mods.mkdir();

			for (File file : Objects.requireNonNull(mods.listFiles())) {
				try (JarFile jar = new JarFile(file)) {
					String name = file.getName();

					if (name.endsWith(".jar")) {
						System.out.println("Getting file " + name);

						url.invoke(loader, file.toURI().toURL());

						for (JarEntry entry : Collections.list(jar.entries())) addMods(entry);

						if (isMod) modJars.add(file);
					}
				}
			}
		} catch (IOException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	private static void addMods(JarEntry entry) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String name = entry.getName();
		
		if (name.endsWith("class")) {
			Class<?> clazz = Class.forName(name.replace(".class", "").replace("/", "."));
			
			if (clazz.getSuperclass().equals(Mod.class)) {
				Mod mod = createInstance(clazz);

				if (mod != null) {
					isMod = true;

					System.out.println("Loading " + mod.getModInfo());
				
					loadedMods.add(mod);
				}
			}
		}
	}

	private static Mod createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		for (Constructor<?> constructor : clazz.getConstructors()) {
			if (constructor.getParameterCount() == 0) {
				return (Mod) constructor.newInstance();
			} else {
				System.out.println(clazz.getName() + " must have no constructors");
			}
		}
		
		return null;
	}
	
	private static void initializeMods() {
		loadedMods.forEach(mod -> mod.init(new Initializer(mod)));
	}
}
