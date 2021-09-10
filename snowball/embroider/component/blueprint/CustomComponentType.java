package com.snowball.embroider.component.blueprint;

import componentArchitecture.ComponentLoader;
import componentArchitecture.ComponentType;

public class CustomComponentType extends ComponentType {
	final boolean dynamic;
	final boolean active;

	final String component;

	public CustomComponentType(boolean dynamic, boolean active, String component) {
		this.dynamic = dynamic;
		this.active = active;
		this.component = "CUSTOM_" + component;
	}

	public void set(ComponentLoader loader) {
		this.loader = loader;
	}
}
