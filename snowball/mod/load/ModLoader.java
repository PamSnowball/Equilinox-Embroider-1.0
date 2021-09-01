package com.snowball.mod.load;

import com.snowball.mod.Mod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
	static final List<Mod> loadedMods = new ArrayList<>();

	private ModLoader() {}
	
	public static void load() {
		loadMods();
		preInitializeMods();
	}

	public static void init() {
		initializeMods();
	}

	private static final String C = ".class";

	private static void loadMods() {
		try {
			URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			
			Method url = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			url.setAccessible(true);

			File mods = new File("mods");

			mods.mkdir();

			for (File file : Objects.requireNonNull(mods.listFiles())) {
				try (JarFile jar = new JarFile(file)) {
					String name = file.getName();

					if (name.endsWith(".jar")) {
						url.invoke(loader, file.toURI().toURL());

						for (JarEntry entry : Collections.list(jar.entries())) addMods(entry);
					}
				}
			}
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private static void addMods(JarEntry entry) throws ClassNotFoundException {
		if (entry.getName().endsWith(C)) {
			Class<?> clazz = Class.forName(entry.getName().replace(C, "").replace("/", "."));

			//If Mod was an interface, then replace it by Mod.class.isAssignableFrom(clazz).
			if (clazz.getSuperclass().equals(Mod.class)) {
				Mod mod = createInstance(clazz);

				if (mod != null && mod.getModInfo() != null && !mod.getModInfo().id().isEmpty()) {
					System.out.println("Loaded " + mod.getModInfo().modName());

					loadedMods.add(mod);
				}
			}
		}
	}

	private static Mod createInstance(Class<?> clazz) {
		try {
			for (Constructor<?> constructor : clazz.getConstructors()) {
				if (constructor.getParameterCount() == 0) {
					constructor.setAccessible(true);
					return (Mod) constructor.newInstance();
				} else {
					System.out.println(clazz.getName() + " must have zero-args constructor or no constructor");
				}
			}
		} catch(IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	static void preInitializeMods() {
		loadedMods.forEach(mod -> mod.preInit(new PreInitializer(mod)));
	}

	static void initializeMods() {
		loadedMods.forEach(mod -> mod.init(new Initializer()));
	}
}
