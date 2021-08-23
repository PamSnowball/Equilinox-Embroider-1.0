package snowball.embroider.enumerator.sound;

import resourceManagement.SoundCache;

import java.io.InputStream;

public class CustomSound {
	boolean ogg;
	
	String name;
	
	public CustomSound(String name, boolean isOgg) {
		this.name = name;
		this.ogg = isOgg;
	}
	
	public void loadSound() {
		String extension = ogg ? ".ogg" : ".wav";
		
		InputStream stream = getClass().getResourceAsStream("/sounds/" + name + extension);
	
		SoundCache.CACHE.requestSound(name, stream);
	}
}
