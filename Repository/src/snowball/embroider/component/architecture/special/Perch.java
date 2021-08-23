package snowball.embroider.component.architecture.special;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.Vector;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Perch extends NativeComponent {
	Vector[] slots;
	
	public Perch(Vector[] perchingPositions) {
		this.slots = perchingPositions;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> perch = new ArrayList<>();

		if (slots != null) {
			perch.add(Utils.value("PERCH", slots.length));
			
			for (Vector slot : slots) perch.add(slot.toString() + ';');
		}

		return perch;
	}
	
	@Override
	public int getId() {
		return 25;
	}
}
