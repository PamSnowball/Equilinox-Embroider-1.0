package snowball.embroider.component.architecture;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.EnumAnimation;
import snowball.embroider.enumerator.EnumEat;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Eating extends NativeComponent {
	public static class Eat {
		EnumAnimation animation;
		IClassifier prey;
		EnumEat eating;

		public Eat(EnumAnimation animation, IClassifier prey, EnumEat eat) {
			this.animation = animation;
			this.prey = prey;
			this.eating = eat;
		}
	}

	EnumAnimation[] animations;
	IClassifier[] preys;
	EnumEat[] eats;
	
	float radius = 1;
	float hunger = 1;

	public Eating(int maxHunger, float hunger, float radius, Eat[] eats) {
		if (maxHunger > 0) this.maxHunger = maxHunger;
		if (hunger > 0) this.hunger = hunger;
		if (radius > 0) this.radius = radius;

		if (eats != null) {
			this.animations = Arrays.stream(eats).map(eat -> eat.animation).toArray(EnumAnimation[]::new);
			this.preys = Arrays.stream(eats).map(eat -> eat.prey).toArray(IClassifier[]::new);
			this.eats = Arrays.stream(eats).map(eat -> eat.eating).toArray(EnumEat[]::new);
		}
	}

	int maxHunger = 1;

	@Override
	public Collection<String> load(Entity entity) {
		List<String> eat = new ArrayList<>();

		if (animations != null && preys != null && eats != null) {
			eat.add(Utils.value("maxHunger", maxHunger, "hungerPerHour", hunger, "eatRadius", radius));
			eat.add(Utils.value("eatAnims", animations.length));
			for (EnumAnimation anim : animations) eat.add(anim.getId() + ";");
			eat.add(Utils.value("dietOptionCount", preys.length));
			for (int i = 0; i < preys.length; i++) eat.add(Utils.value(preys[i], eats[i], animations[i]));
		}

		return eat;
	}
	
	@Override
	public int getId() {
		return 20;
	}
}
