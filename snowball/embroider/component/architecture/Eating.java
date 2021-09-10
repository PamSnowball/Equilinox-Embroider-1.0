package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Animations;
import com.snowball.embroider.enumerator.FoodTypes;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Eating extends NativeComponent {
	public static class Eat {
		Animations animation;
		IClassifier[] preys;
		FoodTypes food;

		/**
		 * Eating behaviour data.
		 *
		 * @param animation animation played when eating
		 * @param food type of food eaten
		 * @param preys entities that are eaten
		 */
		public Eat(Animations animation, FoodTypes food, IClassifier... preys) {
			this.animation = animation;
			this.preys = preys;
			this.food = food;
		}

		public static Eat[] get(Eat[] eats) {
			Eat[] newEats = null;

			for (int i = 0, eatsLength = eats.length; i < eatsLength; i++) {
				Eat eat = eats[i];
				if (eat.preys == null || eat.animation == null || eat.food == null) {
					System.err.println(Eat.class.getName() + ": null parameter");

					return new Eat[]{};
				} else {
					Eat[] preys = Arrays.stream(eat.preys).map(classifier -> new Eat(eat.animation, eat.food, classifier)).toArray(Eat[]::new);
					newEats = new Eat[eats.length];

					System.arraycopy(eats, 0, newEats, i + preys.length, eats.length - i);
				}
			}

			if (newEats != null) {
				return newEats;
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
	public Eating(int maxHunger, float hunger, float radius, Eat... eats) {
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
		Animations[] animations = Arrays.stream(eats).map(value -> value.animation).distinct().sorted().toArray(Animations[]::new);
		int[] ids = Arrays.stream(animations).mapToInt(Animations::ordinal).toArray();
		eat.add(Utils.value("eatAnims", ids.length));
		for (int id : ids) eat.add(id + ";");

		eat.add(Utils.value("dietOptionCount", Arrays.stream(eats).mapToInt(food -> food.preys.length).count()));
		for (Eat value : eats) for (IClassifier prey : value.preys) eat.add(Utils.value(prey, value.food, Arrays.asList(animations).indexOf(value.animation)));

		if (runs || ai != 0) eat.add(Utils.value("runsToFood", runs, "aiId", ai));
		if (egg) {
			entity.setHasEggStage();
			eat.add("hasEggStage;true");
		}

		return eat;
	}
	
	@Override
	public int getId() {
		return 19;
	}
}
