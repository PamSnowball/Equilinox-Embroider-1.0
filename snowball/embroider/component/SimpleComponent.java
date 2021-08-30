package com.snowball.embroider.component;

import com.snowball.embroider.CustomEntity;

import java.util.Collection;
import java.util.Collections;

public class SimpleComponent  extends NativeComponent {
	String name;
	int id;
	
	public SimpleComponent(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton(name);
	}

	@Override
	public int getId() {
		return id;
	}
}
