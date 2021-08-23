package snowball.embroider.component.blueprint;

import componentArchitecture.ComponentType;

public class CustomComponentType extends ComponentType {
	final boolean dynamic;
	final boolean active;

	final String component;

	public CustomComponentType(boolean dynamic, boolean active, String component) {
		this.dynamic = dynamic;
		this.active = active;
		this.component = component;
	}
}
