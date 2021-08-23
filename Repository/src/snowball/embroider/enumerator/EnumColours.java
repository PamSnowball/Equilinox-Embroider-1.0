package snowball.embroider.enumerator;

import snowball.embroider.util.Vector;

public enum EnumColours {
	RED(new Vector(241, 146, 146)),
	RUBY_RED(new Vector(162, 79, 79)),
	YELLOW(new Vector(213, 222, 128)),
	ORANGE(new Vector(245, 180, 126)),
	DARK_ORANGE(new Vector(173, 103, 85)),
	LIGHT_BLUE(new Vector(119, 186, 196)),
	DARK_BLUE(new Vector(80, 84, 139)),
	PINK(new Vector(238, 161, 210)),
	LIGHT_PINK(new Vector(255, 198, 241)),
	PURPLE(new Vector(189, 137, 213)),
	DARK_PURPLE(new Vector(76, 55, 88)),
	LILAC(new Vector(204, 194, 247)),
	CYAN(new Vector(136, 227, 202)),
	BEIGE(new Vector(209, 192, 162)),
	MUD(new Vector(105, 102, 83)),
	LIGHT_BROWN(new Vector(156, 129, 97)),
	BROWN(new Vector(130, 102, 78)),
	DARK_BROWN(new Vector(78, 61, 61)),
	YELLOW_GREEN(new Vector(167, 187, 112)),
	DARK_GREEN(new Vector(72, 111, 68)),
	BLUE_GREEN(new Vector(88, 147, 112)),
	BRIGHT_GREEN(new Vector(149, 245, 161)),
	LIGHT_GREEN(new Vector(141, 227, 141)),
	GREEN(new Vector(93, 150, 93)),
	PALE_GREEN(new Vector(202, 222, 133)),
	MUD_GREEN(new Vector(122, 133, 83)),
	GOLD(new Vector(179, 163, 79)),
	WHITE(new Vector(230, 230, 230)),
	GREY(new Vector(128, 128, 128)),
	LIGHT_GREY(new Vector(192, 192, 192)),
	DARK_GREY(new Vector(64, 64, 64)),
	BLACK(new Vector(38, 38, 38)),
	NEON_RED(new Vector(255, 102, 102)),
	NEON_ORANGE(new Vector(255, 202, 91)),
	NEON_YELLOW(new Vector(255, 245, 102)),
	NEON_GREEN(new Vector(194, 255, 102)),
	NEON_BLUE(new Vector(102, 255, 235)),
	NEON_PURPLE(new Vector(125, 85, 255)),
	NEON_PINK(new Vector(255, 102, 235));
	
	private final Vector colour;

	EnumColours(Vector colour) {
		this.colour = colour;
	}
	
	public Vector getColour() {
		return colour;
	}
	
	public static String checkForColour(Vector colour) {
		for (EnumColours e : values()) if (e.getColour() == colour) return e.name();
		return "CUSTOM;" + colour.value();
	}
}
