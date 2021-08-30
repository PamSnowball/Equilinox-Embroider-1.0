package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Animations;
import com.snowball.embroider.enumerator.FoodTypes;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Eating extends NativeComponent {
	public static class Eat {
		Animations animation;
		IClassifier prey;
		FoodTypes food;

		/**
		 * Eating behaviour data.
		 *
		 * @param animation animation played when eating
		 * @param prey entities that are eaten
		 * @param food type of food eaten
		 */
		public Eat(Animations animation, IClassifier prey, FoodTypes food) {
			this.animation = animation;
			this.prey = prey;
			this.food = food;
		}

		public static Eat[] get(Eat[] eats) {
			for (Eat eat : eats) {
				if (eat.prey == null || eat.animation == null || eat.food == null) {
					System.err.println(Eat.class.getName() + ": null parameter");

					return new Eat[] {};
				}
			}
			return eats;
		}
	}

	Eat[] eats;

	int maxHunger;
	int ai;

	float radius;
	float hunger;

	boolean runs;
	boolean egg;

	/**
	 * Constructs the EATING component which is used by most animals to set eating variables.
	 * The EATING component is set up using hunger points, the hunger of the entity is represented by those points,
	 * when they reach {@code 0} entity starves.
	 *
	 * @param maxHunger maximum hunger points
	 * @param hunger hunger lost per hour
	 * @param radius radius of search
	 * @param eats sets food eaten
	 * @see Eat
	 */
	public Eating(int maxHunger, float hunger, float radius, Eat[] eats) {
		this.maxHunger = Math.max(maxHunger, 0);
		this.hunger = Math.max(hunger, 0);
		this.radius = Math.max(radius, 0);

		this.eats = Eat.get(eats);
	}

	/**
	 * Sets movement data.
	 *
	 * @param runsToFood if true, entity runs to food
	 * @param eatingAi if it is {@code 0} then it is standard eating, <br>
	 *                    if {@code 1} then it is fish eating AI, otherwise it is bird eating AI.
	 */
	public Eating movementData(boolean runsToFood, int eatingAi) {
		runs = runsToFood;
		ai = eatingAi;
		return this;
	}

	/** Use it if it has an egg stage, it makes so hunger is ignored while egg */
	public Eating hasEggStage() {
		egg = true;
		return this;
	}

	@Override
	public Collection<String> load(CustomEntity entity) {
		List<String> eat = new ArrayList<>();

		eat.add(Utils.value("maxHunger", maxHunger, "hungerPerHour", hunger, "eatRadius", radius));
		eat.add(Utils.value("eatAnims", eats.length));
		for (Eat value : eats) eat.add(value.animation.getId() + ";");
		eat.add(Utils.value("dietOptionCount", eats.length));

		for (Eat value : eats) eat.add(Utils.value(value.prey, value.food, value.animation));

		if (runs || ai != 0) eat.add(Utils.value("runsToFood", runs, "aiId", ai));
		if (egg) eat.add("hasEggStage;true");

		return eat;
	}
	
	@Override
	public int getId() {
		return 19;
	}
}
