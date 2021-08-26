package com.snowball.embroider.mod;

import com.snowball.embroider.mod.load.Initializer;

public interface Mod {
	void init(Initializer init);
	
	default ModInfo getModInfo() {
		return this.getClass().getDeclaredAnnotation(ModInfo.class);
	}
}
