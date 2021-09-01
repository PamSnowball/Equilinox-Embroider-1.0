package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.util.component.Breed;
import com.snowball.embroider.util.component.CompDeath;
import com.snowball.embroider.util.component.CompEnvironment;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Life extends NativeComponent {
	Breed breed;
	
	float population;
	float lifespan;
	
	float[] factors;
	
	CompDeath death;
	CompEnvironment[] environments;
	
	int points = 0;

	/**
	 * Constructs the LIFE component which is used by all living entities to set environment behavior. <br>
	 * Lifespan has a standard deviation of twenty-five percent, it represents, in average, two real life years and a half. <p>
	 *
	 * <i> There is no need to be precise, just make it look like real life, time in equilinox is odd,
	 * some trees live dozens of times longer in real life than at equilinox while there are flowers that live hundreds of times longer at equilinox than in real life. <p>
	 *
	 * Population Factors work as following: Let's suppose we have an entity of classification ahl (animal herbivore large)
	 * with population factors of {@code [0.35, 0.15]} and there are three large herbivores in range, four other herbivores and two other animals.
	 * Then local population is:
	 * {@code 3 + 4 * 0.35 + 2 * 0.15 = 3 + 1.7 + 0.3 = 5}. <br>
	 * It means that entity behaves as if there are five of it around, if average population is lower than that, entity will lose satisfaction </i>
	 *
	 * @param averagePopulation average population in range
	 * @param averageLifespan life expectancy of the entity
	 * @param populationFactors each factor represents how much the parent species affect the entity satisfaction
	 * @param breed evolving conditions
	 * @param death death type
	 * @param lifeRequirements environment requirements
	 */
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed, CompDeath death, CompEnvironment[] lifeRequirements) {
		this.population = averagePopulation;
		this.lifespan = averageLifespan;
		this.factors = populationFactors;
		this.breed = breed;
		this.death = death;
		this.environments = lifeRequirements;
	}

	public Life setDefencePoints(int defencePoints) {
		this.points = defencePoints;
		return this;
	}

	public Collection<String> load(CustomEntity entity) {
		List<String> life = new ArrayList<>();

		life.add(Utils.value("LIFE;averagePop", population, "averageLife", lifespan, "popFactors", factors == null ? 0 : factors.length));
		if (factors != null) for (int i = 0; i < Math.min(factors.length, entity.getType().length()) - 1; i++) life.add(factors[i] + ";");

		life.addAll(breed.breed());
		life.addAll(death.death());
		life.add("enviroReqCount;" + environments.length + ';');
		for (CompEnvironment environment : environments) life.addAll(environment.requirement());
		if (points > 0) life.add("defencePoints;" + points);

		return life;
	}
	
	@Override
	public int getId() {
		return 43;
	}
}
