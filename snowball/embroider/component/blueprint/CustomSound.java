package com.snowball.embroider.component.blueprint;

import com.snowball.embroider.util.component.CompSound;
import resourceManagement.SoundCache;

import java.io.InputStream;

public class CustomSound implements CompSound {
	boolean ogg;
	
	String name;
	
	public CustomSound(String name, boolean isOgg) {
		this.name = name;
		this.ogg = isOgg;
	}

	@Override
	public String getSound() {
		return name;
	}

	public void loadSound() {
		String extension = ogg ? ".ogg" : ".wav";
		
		InputStream stream = this.getClass().getResourceAsStream("/sounds/" + name + extension);
	
		SoundCache.CACHE.requestSound(name, stream);
	}
}
