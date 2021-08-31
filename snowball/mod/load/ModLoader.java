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
	
	public static void load() {
		loadMods();
		initializeMods();
	}
	
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
						System.out.println("Getting file " + name);

						url.invoke(loader, file.toURI().toURL());

						for (JarEntry entry : Collections.list(jar.entries())) {
							if (addMods(entry)) modJars.add(file);
						}
					}
				}
			}
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private static boolean addMods(JarEntry entry) throws ClassNotFoundException {
		String name = entry.getName();
		
		if (name.endsWith(".class")) {
			Class<?> clazz = Class.forName(name.replace(".class", "").replace("/", "."));

			//If Mod was an abstract class, then replace it by clazz.getSuperclass().equals(Mod.class).
			if (Mod.class.isAssignableFrom(clazz)) {
				Mod mod = createInstance(clazz);

				if (mod != null && mod.getModInfo() != null && !mod.getModInfo().id().isEmpty()) {
					System.out.println("Loading " + mod.getModInfo().modName());

					loadedMods.add(mod);

					return true;
				}
			}
		}

		return false;
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
	
	private static void initializeMods() {
		loadedMods.forEach(mod -> {
			System.out.println(mod.getModInfo().modName());
			mod.init(new Initializer(mod));
		});
	}
}
