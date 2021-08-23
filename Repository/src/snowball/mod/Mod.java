package snowball.mod;

import snowball.mod.load.Initializer;

public interface Mod {
	void init(Initializer init);
	
	default ModInfo getModInfo() {
		return this.getClass().getDeclaredAnnotation(ModInfo.class);
	}
}
