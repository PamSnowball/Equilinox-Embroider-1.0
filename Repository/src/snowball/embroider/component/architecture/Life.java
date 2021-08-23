package snowball.embroider.component.architecture;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.util.component.Breed;
import snowball.embroider.util.component.IDeath;
import snowball.embroider.util.component.IEnvironment;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Life extends NativeComponent {
	Breed breed;
	
	float population;
	float lifespan;
	
	float[] factors;
	
	IDeath death;
	IEnvironment[] environments;
	
	int points = 0;
	
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed, IDeath death, 
			IEnvironment[] lifeRequirements) {
		this.population = averagePopulation;
		this.lifespan = averageLifespan;
		this.factors = populationFactors;
		this.breed = breed;
		this.death = death;
		this.environments = lifeRequirements;
	}
	
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed, IDeath death, 
			IEnvironment[] lifeRequirements, int defencePoints) {
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
		for (IEnvironment environment : environments) life.addAll(environment.requirement());
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
