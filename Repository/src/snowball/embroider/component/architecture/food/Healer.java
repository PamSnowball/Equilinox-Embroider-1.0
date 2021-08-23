package snowball.embroider.component.architecture.food;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.component.IDeath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Healer extends NativeComponent {
	IDeath death;
	
	public Healer(IDeath death) {
		this.death = death;
	}
	
	public Collection<String> load(Entity entity) {
		List<String> heal = new ArrayList<>();

		if (death != null) {
			heal.add("HEALER;");
			heal.addAll(death.death());
		}

		return heal;
	}

	@Override
	public int getId() {
		return 40;
	}
}
