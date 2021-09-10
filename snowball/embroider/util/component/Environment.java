package com.snowball.embroider.util.component;

import biomes.Biome;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Biomes;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Environment {
	private static final String I = "reqId";

	private static final String IN = "influence";
	
	public static class EnvironmentAltitude implements CompEnvironment {
		float influence;
		
		int min;
		int max;
		
		public EnvironmentAltitude(int minimumAltitude, int maximumAltitude, float healthInfluence) {
			this.influence = healthInfluence;
			this.min = minimumAltitude;
			this.max = maximumAltitude;
		}
		
		@Override
		public Collection<String> requirement() {
			return Collections.singleton(Utils.value(I, 1, "min", min, "max", max, IN, influence + ';'));
		}
	}	
	
	public static class EnvironmentLikedBiome implements CompEnvironment {
		boolean barren;
		
		Biome[] biomes;
		
		float ideal;
		float influence;

		public EnvironmentLikedBiome(boolean growsBarren, float ideal, float healthInfluence, Biome... likedBiomes) {
			this.influence = healthInfluence;
			this.barren = growsBarren;
			this.biomes = likedBiomes;
			this.ideal = ideal;
		}
		
		@Override
		public Collection<String> requirement() {
			List<String> environment = new ArrayList<>();
			
			if (biomes != null) { 
				environment.add(Utils.value(I, 2, "barren", barren ? 1 : 0, "likedBiomes", biomes.length));
				for (Biome biome : biomes) environment.add(biome.getId() + ";");
				environment.add(Utils.value("idealFactor", ideal, IN, influence));
			}
			
			return environment;
		}
	}
	
	public static class EnvironmentUnsuitableBiome implements CompEnvironment {
		Biome[] biomes;
		
		float influence;
		
		public EnvironmentUnsuitableBiome(Biome[] dislikedBiomes, float healthInfluence) {
			this.biomes = dislikedBiomes;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			List<String> environment = new ArrayList<>();
			
			environment.add(I + ';' + 3 + ";dislikedBiomes;" + biomes.length);
			for (Biome biome : biomes) environment.add(biome.getId() + ";");
			environment.add(IN + ';' + influence + ';');
			
			return environment;
		}
	}
	
	public static class EnvironmentFavouriteBiome implements CompEnvironment {
		Biome biome;
		
		float influence;
		
		public EnvironmentFavouriteBiome(Biome favouriteBiome, float healthInfluence) {
			this.biome = favouriteBiome;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			return Collections.singleton(Utils.value(I, 4, "faveBiomes", biome.getId(), IN, influence));
		}
	}
	
	public static class EnvironmentLikedSpecies implements CompEnvironment {
		IClassifier[] species;
		
		float influence;
		
		public EnvironmentLikedSpecies(float healthInfluence, IClassifier... classification) {
			this.species = classification;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			List<String> environment = new ArrayList<>();
			
			environment.add(I + ';' + 5 + ";likedSpecies;" + species.length + ';');
			for (IClassifier specie : species) environment.add(specie.getClassification() + ";");
			environment.add(IN + ';' + influence + ';');

			return environment;
		}
	}
	
	public static class EnvironmentDislikedSpecies implements CompEnvironment {
		IClassifier[] species;
		
		float influence;
		
		public EnvironmentDislikedSpecies(float healthInfluence, IClassifier... dislikedSpeciesClassification) {
			this.species = dislikedSpeciesClassification;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			List<String> environment = new ArrayList<>();
			
			environment.add(I + ';' + 6 + ";likedSpecies;" + species.length + ';');
			for (IClassifier specie : species) environment.add(specie.getClassification() + ";");
			environment.add(IN + ';' + influence + ';');

			return environment;
		}
	}
}
