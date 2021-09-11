package com.snowball.embroider.util;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class Utils {
	private Utils() { throw new IllegalStateException("Utility Class!"); }

	static String gameJar;

	public static boolean check(Object o, Object... os) {
		for (Object obj : os) if (obj != o) return true;
		return false;
	}

	public static String value(Object... os) {
		StringBuilder builder = new StringBuilder();
		for (Object o : os) builder.append(o.toString()).append(';');
		return builder.toString();
	}

	public static void append(Collection<String> src, List<String> dest) {
		StringBuilder builder = new StringBuilder();
		for (String s : src) builder.append(s);
		dest.add(builder.toString());
	}
	
	public static void checkForEquilinoxJar() {
		File directory = new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().getFile());

		final String windows = "EquilinoxWindows.jar";
		final String input = "input.jar";
		final String mac = "EquilinoxMac.jar";

		switch (directory.getName()) {
			case input: gameJar = input; break;
			case mac: gameJar = mac; break;

			default: gameJar = windows;
		}
	}
}
