package com.snowball.embroider.component.architecture.hunt;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class Fight extends NativeComponent {
	boolean revenge;

	boolean lunges;
	
	float range;
	float pause;
	
	int damage;


	/**
	 * Constructs the FIGHT component which is used by aggressive animals to set fighting AI. <p>
	 *
	 * Range of fight guide: <br>
	 * bees deal seven damage; <br>
	 * goats deal eight damage; <br>
	 * foxes deal eleven damage; <br>
	 * wolves deal fifty damage; <p>
	 * All fighters have a bite range of 0.4 but bees, that have a bite range of 0.25 because it is smaller.
	 *
	 * @param attackDamage damage
	 * @param doesRevenge if true, entity fights back
	 * @param lunges if true, entity lunges
	 * @param biteRange range of bite
	 * @param pauseTime time in seconds of pause fighting after animation
	 */
	public Fight(int attackDamage, boolean doesRevenge, boolean lunges, float biteRange, float pauseTime) {
		this.damage = attackDamage;
		this.revenge = doesRevenge;
		this.lunges = !lunges;
		this.range = biteRange;
		this.pause = pauseTime;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton(Utils.value("FIGHT;damage", damage, "revenge", revenge ? 1 : 0, "anim", lunges ? 1 : 0, "biteRange", range, "pause", pause));
	}

	@Override
	public int getId() {
		return 7;
	}
}
