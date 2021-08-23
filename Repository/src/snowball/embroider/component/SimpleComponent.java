package snowball.embroider.component;

import snowball.embroider.entity.Entity;

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
	public Collection<String> load(Entity entity) {
		return Collections.singleton(name);
	}

	@Override
	public int getId() {
		return id;
	}
}
