package com.snowball.mod.load;

import com.snowball.embroider.CustomEntity;

import java.util.ArrayList;
import java.util.List;

public class Initializer {
	Initializer() {}

	public void addEntity(CustomEntity entity) {
		Embroider.addEntity(entity);
		PreInitializer.map.put(entity, PreInitializer.getModFromEntity(entity));
	}

	public static List<CustomEntity> getEntities() {
		return new ArrayList<>(PreInitializer.map.keySet());
	}
}
