package com.snowball.mod;

import com.snowball.mod.load.Initializer;
import com.snowball.mod.load.PreInitializer;

public abstract class Mod {
	public abstract void preInit(PreInitializer preInit);

	public abstract void init(Initializer init);

	public ModInfo getModInfo() {
		return this.getClass().getDeclaredAnnotation(ModInfo.class);
	}
}
