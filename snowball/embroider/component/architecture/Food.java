package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
import com.snowball.mod.ModelConverter;
import com.snowball.embroider.util.component.Death.FadeDeath;
import com.snowball.embroider.util.component.CompDeath;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.FoodTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Food extends NativeComponent {
	static class Nutrient {
		String name;

		CompDeath death = new FadeDeath(0.5F);

		int portions = 1;

		int points;

		FoodTypes food;

		/**
		 * Eating behavior data. If {@code food} is WHOLE or ROOT VEG you should specify an {@linkplain CompDeath},
		 * else if {@code food} is TO SHARE you should specify how many {@code portions} it can be shared.
		 *
		 * @param food type of food eaten
		 * @param name name of the food to be displayed on diet info
		 * @param points points restored
		 * @see FoodTypes
		 */
		public Nutrient(FoodTypes food, String name, int points) {
			this.points = points;
			if (food != null) {
				this.food = food;
			}
			this.name = name;
		}

		/**	Sets a dying effect when eaten */
		public Nutrient setDeath(CompDeath death) {
			if (death != null) this.death = death;
			return this;
		}

		/** In how many portions this food can be shared */
		public Nutrient setPortions(int portions) {
			this.portions = Math.max(portions, 1);
			return this;
		}

		static Nutrient[] get(Nutrient[] nutrients) {
			for (Nutrient nutrient : nutrients) {
				if (nutrient.food == null || nutrient.name == null) {
					ModelConverter.log(Nutrient.class.getName() + ": null parameter");

					return new Nutrient[] {};
				}
			}

			return nutrients;
		}
	}

	Nutrient[] foods;

	/**
	 * Constructs the FOOD component which is used by most living entities to set being eaten behaviour. <br>
	 * This component must be used by all species that can be hunted or eaten.
	 *
	 * @param foods types of eating
	 */
	public Food(Nutrient[] foods) {
		this.foods = Nutrient.get(foods);
	}
		
	public Collection<String> load(Entity entity) {
		List<String> food = new ArrayList<>();

		if (foods.length > 0) {
			food.add(Utils.value("CUSTOM_FOOD", foods.length));
			for (Nutrient nutrient : foods) {
				food.add(Utils.value(nutrient.name, nutrient.points, nutrient.name));
				if ((nutrient.name.equals("WHOLE") || nutrient.name.equals("ROOT_VEG"))) food.add(nutrient.death + ";");
				else if (nutrient.name.equals("TO_SHARE")) food.add(nutrient.portions + ";");
			}
		}

		return food;
	}
	
	@Override
	public int getId() {
		return 32;
	}
}
