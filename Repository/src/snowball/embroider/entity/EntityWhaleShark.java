package snowball.embroider.entity;

import snowball.embroider.component.architecture.*;
import snowball.embroider.enumerator.EnumBiome;
import snowball.embroider.enumerator.EnumColours;
import snowball.embroider.enumerator.classification.BaseClassification;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.enumerator.sound.EnumSound;
import snowball.embroider.util.Vector;
import snowball.embroider.util.component.Breed;
import snowball.embroider.util.component.Death.FloatDeath;
import snowball.embroider.util.component.Environment;
import snowball.embroider.util.component.IEnvironment;
import snowball.embroider.util.component.IRequirement;
import snowball.embroider.util.requirement.ReqBiome;
import snowball.embroider.util.requirement.ReqTransform;

//This is my Entity Class :D
public class EntityWhaleShark extends Entity {
	//My Entity Constructor :D
	@SuppressWarnings("unused")
	public EntityWhaleShark() {
		
		/*
		 * The first parameter of the superclass is the name of the entity, it must match [name of the entity ("2000_Shark_Whale") + _ + model stage (0, 1, 2) + .obj]
		 * in this case the .obj file is "2004_Shark_Whale_0.obj".
		 * The second parameter is the classification, it must match EnumClassifier.
		 * The third parameter is the size multiplier of the model, if you made an absurdly giant model, don't panic!
		 * The last parameter is the water requirement, it matches how deep the water must be for your entity to be placed on, the lower the value,
		 * the deeper it must be. If your entity doesn't go underwater, leave it 0.
		 */
		super(2004, "Shark_Whale", BaseClassification.BIG_FISH, 6, new WaterData(true, -0.5F), null, new IconData(1, 2.4F));
		
		//These are the component loaders, they are what makes the model a creature.
		
		//These are the colors for my Whale Shark, you can put them as RGB like here or
		Vector[] unusedColors = { new Vector(60, 64, 66), new Vector(90, 66, 45), new Vector(96, 96, 96), new Vector(64, 60, 66) };
		
		//You can make put them as hex or
		Vector[] colors = { new Vector("#3c454a"), new Vector("#5a422d"), new Vector("#606060"), new Vector("#4e4254") };
		
		//You can pick colors from snowball.embroider.EnumColours enum.
		Vector[] enumColors = { EnumColours.DARK_BLUE.getColour(), EnumColours.DARK_BROWN.getColour(), EnumColours.DARK_GREY.getColour(), EnumColours.DARK_PURPLE.getColour()};
		
		/*
		 * There are the prices of each color. if the entity has more prices than colors, it prints "Entity [entity name] has more colors than prices" and ignores the extra prices.
		 * Now if the entity has more colors than prices, it prints "Entity [entity name] has more prices than colors" 
		 * and your entity is printed with no colors other than the base color.
		 */
		int[] prices = { 1750, 15300, 53600, 117500 };

		MaterialColor purple = new MaterialColor(EnumColours.DARK_GREY.getColour(), 117500);
		MaterialColor brown = new MaterialColor(EnumColours.DARK_BROWN.getColour(), 15300);
		MaterialColor grey = new MaterialColor(EnumColours.DARK_GREY.getColour(), 53600);
		MaterialColor blue = new MaterialColor(EnumColours.DARK_BLUE.getColour(), 1750);
		
		/*
		 * This component is the color of the creature.
		 * The first parameter is an entity, your entity!
		 * The second parameter is a boolean, if true, the second color of the color array is also natural.
		 * The third parameter is an array of vectors with the RGB color information.
		 * The fourth parameter is an array of prices, it must have a price for each color.
		 */
		this.componentLoader(new Material(this, false, new MaterialColor[] { purple, brown, grey, blue }));
		
		//DAMN, look at this giant description.
		String description = "Whale sharks are slow-moving, filter-feeding sharks, just like vacuum cleaners, they clean the oceans of krill, plankton and small fish";
		
		/*
		 * This component is a must for creatures, it loads the information of the entity.
		 * The first parameter is the entity name in game.
		 * The second parameter is the description of it, I extracted it to a string up there for style.
		 * The third parameter is the price of the entity in dps.
		 * The fourth parameter is the dp per minute it generates.
		 * The fifth parameter is the range this creature walk, the average range is 2, Whale Sharks swim a lot further.
		 * The sixth parameter is the sound it makes when you put the creature down, marine creatures do a splash sound effect, SPLASH!
		 */
		this.componentLoader(new Information("Whale Shark", description, 273000, 775, 4, EnumSound.SPLASH));

		/*
		 * Requirements to evolve.
		 * ReqTransform is the size needed, in this case 1.15x the normal species count.
		 * ReqBiome is the biome the creature must be at to evolve and the biome % required.
		 */
		IRequirement[] evolveRequirements = { new ReqTransform(1.15F), new ReqBiome(EnumBiome.TROPICAL, 0.35F) };

		/*
		 * Habitat specification, the requirements for having a healthy and mighty life.
		 * The first requirement is the liked biomes.
		 * The first parameter of it is a boolean, true if it can grow in a barren land.
		 * The second parameter of it is an array of suitable biomes.
		 * The third parameter is the ideal % of liked biomes.
		 * The last parameter is how much it is important for the creature health, in this case it composes half of its health.
		 * The second requirement is the liked species in range of the creature.
		 * The first parameter of it is an array of liked species classification.
		 * The second parameter is the health influence in its life, in this case large rocks are tremendously important for our entity.
		 */
		IEnvironment[] environmentRequirements = { 
				new Environment.EnvironmentLikedBiome(false, new EnumBiome[] { EnumBiome.TROPICAL }, 80F, 0.5F),
				new Environment.EnvironmentLikedSpecies(new IClassifier[] { BaseClassification.LARGE_ROCK }, 0.8F)
		};

		/*
		 * This component is the most important one for our living being.
		 * The first parameter is the average population of this species.
		 * The second parameter is the average lifespan of the species, in this case it is a very long life, 90 days which represents 90 years.
		 * The third parameter is an array of population factors, this is how much the parent classifications affect the health of our entity.
		 * In this case, the parent of Whale Sharks is Animal Fish, cause its classification is Animal Fish *Big*.
		 * The fourth parameter is a Breed Blueprint.
		 * The first parameter of it is the breed maturity age in days.
		 * The second parameter is the average breed time, it is a broken number to make it float.
		 * The third parameter is the id of the entity it evolves from, in this case it is 23 which is the id of the pike.
		 * The fourth parameter is the time it takes to evolve progress bar get filled.
		 * The fifth parameter is the requirements for the evolution.
		 * The sixth parameter is the death type, in this case they float to the surface when died.
		 * The last parameter is the requirements for being healthy and live longer.
		 */
		this.componentLoader(new Life(3, 90, null, new Breed(30, 30.385346F, 23, 150, evolveRequirements), 
				new FloatDeath(), environmentRequirements));
		
		/*
		 * This is an important component
		 * The first parameter is an entity, your entity!
		 * The second parameter is the time it takes for your entity to grow up a stage.
		 * The third parameter is how many stages your entity has, this parameter is extremely important, because it will make so the system searches for obj files like:
		 * [name of the entity ("2000_Shark_Whale") + _ + model stage (0, 1, 2) + .obj]
		 */ 
		this.componentLoader(new Growth(this, 15, 3));
		
		/*
		 * Moving is pretty important for an animal, am I right?
		 * The first parameter is the speed it moves! The usual speed for fish is 0.6F, Whale Sharks are slow.
		 * The second parameter is the rotation of your entity while swimming, 
		 * 2 is rotating vertically like fish and 1 is rotating horizontally like a marine mammal.
		 * The third parameter and fourth parameters are the minimum and the maximum degrees it can rotate. The minimum must be negative.
		 * The fifth parameter is the rotation speed, its usually 2, Whale Sharks rotate slowly.
		 * The sixth parameter is the swim height of your entity, I know we already set it up there but, now we got to set it up again with different numbers,
		 * the higher the number is, deeper your entity swims, the usual height 4.
		 * The seventh parameter is a boolean, it is true if your entity lay eggs.
		 * The last parameter is the swim factor is important for creatures that also get out of the water, it determines how faster compared to how fast they walk, as an example, 
		 * beavers go at a speed of 0.5F and their factor is 1.8, it means that, on land they walk in 0.5 speed and when swimming they go at 0.5 * 1.8 = 0.9 speed, pretty fast.
		 */
		this.componentLoader(new Movement.SwimmerMovement(0.2F, 2, -1, 1, 1.5F, 3, false, 1));
		
		//This is the AI of our beautiful entity, this is the last and simplest component.
		this.componentLoader(new Ai.SwimAi());
		
		/*
		 * Oof! Finally, we finished our creature, was it easy? I do think so, the last step is to put it at the entity list, otherwise the game
		 * won't load it and all our work.
		 */
		EntityLoader.ENTITIES.add(this);
	}
}
