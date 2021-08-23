package snowball.embroider.utils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

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
	
	public static <T> Map<T, Integer> organizeList(Map<T, Integer> compMap) {
		return compMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (pastValue, newValue) -> pastValue, LinkedHashMap::new));
	}
}
