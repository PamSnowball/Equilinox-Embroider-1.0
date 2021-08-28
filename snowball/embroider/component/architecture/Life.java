package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
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
	
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed, CompDeath death,
			CompEnvironment[] lifeRequirements) {
		this.population = averagePopulation;
		this.lifespan = averageLifespan;
		this.factors = populationFactors;
		this.breed = breed;
		this.death = death;
		this.environments = lifeRequirements;
	}
	
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed, CompDeath death,
				CompEnvironment[] lifeRequirements, int defencePoints) {
		this.population = averagePopulation;
		this.lifespan = averageLifespan;
		this.factors = populationFactors;
		this.breed = breed;
		this.death = death;
		this.environments = lifeRequirements;
		this.points = defencePoints;
	}

	public Collection<String> load(Entity entity) {
		List<String> life = new ArrayList<>();
		
		life.add(Utils.value("LIFE;averagePopulation", population, "averageLife", lifespan, "popFactors", factors.length));
		if (factors.length != 0) for (float factor : factors) life.add(factor + ";");
		life.addAll(breed.breed());
		life.addAll(death.death());
		life.add("enviroReqCount;" + environments.length + ';');
		for (CompEnvironment environment : environments) life.addAll(environment.requirement());
		if (points != 0) {
			life.add("defencePoints;" + points);
		}
		
		return life;
	}
	
	@Override
	public int getId() {
		return 43;
	}
}
