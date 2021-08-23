package snowball.embroider.component;

import snowball.embroider.entity.Entity;

import java.util.Collection;

public interface IComponent {
	Collection<String> load(Entity entity);

	int getId();

	String name();
}
