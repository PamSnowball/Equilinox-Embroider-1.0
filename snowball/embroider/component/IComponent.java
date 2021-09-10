package com.snowball.embroider.component;

import com.snowball.embroider.entity.CustomEntity;

import java.util.Collection;

public interface IComponent {
	Collection<String> load(CustomEntity entity);

	int getId();

	String name();
}
