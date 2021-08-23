package snowball.embroider.component.architecture.special;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Equip extends NativeComponent {
	Vector[] positions;
	
	public Equip(Vector[] equippingPositions) {
		this.positions = equippingPositions;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> perch = new ArrayList<>();

		if (positions != null) {
			perch.add("EQUIP;count;" + positions.length);
			
			for (Vector position : positions) perch.add("pos;" + position.toString() + ';');
		}

		return perch;
	}

	@Override
	public int getId() {
		return 24;
	}
}
