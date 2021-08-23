package snowball.embroider.util.component;

import snowball.embroider.enumerator.EnumBiome;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Environment {
	private static final String I = "reqId";

	private static final String IN = "influence;";
	
	public static class EnvironmentAltitude implements IEnvironment {
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
			return Collections.singleton(Utils.value(I, 1, "min", min, "max", max, "influence", influence + ';'));
		}
	}	
	
	public static class EnvironmentLikedBiome implements IEnvironment {
		boolean barren;
		
		EnumBiome[] biomes;
		
		float ideal;
		float influence;

		public EnvironmentLikedBiome(boolean growsBarren, EnumBiome[] likedBiomes, float ideal, float healthInfluence) {
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
				for (EnumBiome biome : biomes) environment.add(biome.getId() + ";");
				environment.add(Utils.value("idealFactor", ideal, "influence", influence + ';'));
			}
			
			return environment;
		}
	}
	
	public static class EnvironmentUnsuitableBiome implements IEnvironment {

		EnumBiome[] biomes;
		
		float influence;
		
		public EnvironmentUnsuitableBiome(EnumBiome[] dislikedBiomes, float healthInfluence) {
			this.biomes = dislikedBiomes;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			List<String> environment = new ArrayList<>();
			
			environment.add(I + 3 + ";dislikedBiomes;" + biomes.length);
			for (EnumBiome biome : biomes) environment.add(biome.getId() + ";");
			environment.add(IN + influence + ';');
			
			return environment;
		}
	}
	
	public static class EnvironmentFavouriteBiome implements IEnvironment {
		EnumBiome biome;
		
		float influence;
		
		public EnvironmentFavouriteBiome(EnumBiome favouriteBiome, float healthInfluence) {
			this.biome = favouriteBiome;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			return Collections.singleton(I + 4 + ";faveBiomes;" + biome.getId() + ";influence" + influence + ';');
		}
	}
	
	public static class EnvironmentLikedSpecies implements IEnvironment {
		IClassifier[] species;
		
		float influence;
		
		public EnvironmentLikedSpecies(IClassifier[] likedSpeciesClassification, float healthInfluence) {
			this.species = likedSpeciesClassification;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			List<String> environment = new ArrayList<>();
			
			environment.add(I + 5 + ";likedSpecies;" + species.length + ';');
			for (IClassifier specie : species) environment.add(specie.getClassification() + ";");
			environment.add(IN + influence + ';');

			return environment;
		}
	}
	
	public static class EnvironmentDislikedSpecies implements IEnvironment {
		IClassifier[] species;
		
		float influence;
		
		public EnvironmentDislikedSpecies(IClassifier[] dislikedSpeciesClassification, float healthInfluence) {
			this.species = dislikedSpeciesClassification;
			this.influence = healthInfluence;
		}
		
		@Override
		public Collection<String> requirement() {
			List<String> environment = new ArrayList<>();
			
			environment.add(I + 6 + ";likedSpecies;" + species.length + ';');
			for (IClassifier specie : species) environment.add(specie.getClassification() + ";");
			environment.add(IN + influence + ';');

			return environment;
		}
	}
}
