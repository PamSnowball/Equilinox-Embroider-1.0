package snowball.embroider.component.architecture.hunt;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class Fight extends NativeComponent {
	boolean revenge;

	boolean lunges;
	
	float range;
	float pause;
	
	int damage;
	
	public Fight(int attackDamage, boolean doesRevenge, boolean lunges, float biteRange, float pauseTime) {
		this.damage = attackDamage;
		this.revenge = doesRevenge;
		this.lunges = lunges;
		this.range = biteRange;
		this.pause = pauseTime;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		return Collections.singleton(Utils.value("FIGHT;damage", damage, "revenge", revenge ? 1 : 0, "anim", lunges ? 1 : 0, "biteRange", range, "pause", pause));
	}

	@Override
	public int getId() {
		return 8;
	}
}
