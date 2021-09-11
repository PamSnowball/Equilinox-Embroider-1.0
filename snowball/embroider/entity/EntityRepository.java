package com.snowball.embroider.entity;

import biomes.Biome;
import com.snowball.embroider.component.architecture.*;
import com.snowball.embroider.component.architecture.data.Name;
import com.snowball.embroider.component.architecture.data.RandomSounder;
import com.snowball.embroider.component.architecture.data.Sound;
import com.snowball.embroider.component.architecture.food.Drop;
import com.snowball.embroider.component.architecture.special.Sleep;
import com.snowball.embroider.enumerator.*;
import com.snowball.embroider.enumerator.classification.BaseClassification;
import com.snowball.embroider.enumerator.classification.specific.AnimalClassification;
import com.snowball.embroider.enumerator.classification.specific.NonLivingClassification;
import com.snowball.embroider.enumerator.classification.specific.SmallPlantClassifications;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.component.Breed;
import com.snowball.embroider.util.component.Death;
import com.snowball.embroider.util.component.Environment;
import com.snowball.embroider.util.requirement.ReqNearbySpecies;
import com.snowball.embroider.util.requirement.ReqSpecieInBiome;

public enum EntityRepository {
    SHEEP() {
        @Override
        public EquilinoxEntity loadEntity() {
            return new EquilinoxEntity(AnimalClassification.SHEEP)
                    .comp(new Material(true,
                            Colours.WHITE.setPrice(2000),
                            Colours.craft(new Vector(0.35F), 2000),
                            Colours.LIGHT_BROWN.setPrice(10000),
                            Colours.GREY.setPrice(6000),
                            Colours.BEIGE.setPrice(80000),
                            Colours.LILAC.setPrice(500000),
                            Colours.CYAN.setPrice(1500000),
                            Colours.RED.setPrice(2000000),
                            Colours.LIGHT_PINK.setPrice(2000000),
                            Colours.GOLD.setPrice(2000000),
                            Colours.DARK_PURPLE.setPrice(5000000)))
                    .info(228, 750, 52, 2, Sounds.SHEEP_BAA_1)
                    .comp(new Life(4.3F, 43, null,
                            new Breed(21.5F, 15.551813F),
                            new Death.Fall(1.5F, 2.5F, 60),
                            new Environment.EnvironmentLikedBiome(false, 100, 0.5F, Biomes.GRASSLAND.getBiome(), Biomes.FOREST.getBiome(),
                                    Biomes.SNOW.getBiome(), Biomes.SWAMP.getBiome(), Biomes.LUSH.getBiome(), Biomes.WOODLAND.getBiome()),
                            new Environment.EnvironmentLikedSpecies(0.25F, BaseClassification.TREE)))
                    .comp(new Growth(14.333333F)).comp(new Movement.SheepMovement(0.4F, 180, 2)).comp(new Ai.PatrolAi().setIdleTime(10, 20))
                    .comp(new Eating(50, 3, 0.2F,
                            new Eating.Eat(Animations.COMMON, FoodTypes.HONEY, NonLivingClassification.HIVE),
                            new Eating.Eat(Animations.COMMON, FoodTypes.WHOLE, BaseClassification.FRUIT),
                            new Eating.Eat(Animations.COMMON, FoodTypes.FRUIT, BaseClassification.FRUIT_BUSH),
                            new Eating.Eat(Animations.COMMON, FoodTypes.WHOLE, BaseClassification.GRASSES)))
                    .comp(new Name()).comp(new Sound()).comp(new RandomSounder(15, 30,
                            new RandomSounder.SoundFile(Sounds.SHEEP_BAA_0, 10),
                            new RandomSounder.SoundFile(Sounds.SHEEP_BAA_1, 10)))
                    .comp(new Sleep(0, 2, 3, 5))
                    .comp(new Flee(3)).comp(new Drop(NonLivingClassification.MEAT));
        }
    },
    TORTOISE() {
        @Override
        public EquilinoxEntity loadEntity() {
            return new EquilinoxEntity(AnimalClassification.TURTLE)
                    .info(393, 35750, 120, 2, Sounds.THUD)
                    .comp(new Life(3.75F, 52, null,
                            new Breed(26, 18.810873F).setEvolves(AnimalClassification.LIZARD, 290,
                                    new ReqSpecieInBiome(SmallPlantClassifications.LEAFY_PLANT, 2),
                                    new ReqNearbySpecies(SmallPlantClassifications.TROPICAL_SEAWEED, 2)),
                            new Death.Fall(1.5F, 2.5F, 180),
                            new Environment.EnvironmentLikedBiome(false, 80, 0.9F, Biome.TROPICAL, Biome.RIVER_BED)))
                    .comp(new Growth(17.333334F))
                    .comp(new Movement.AxisMovement(0.16F, 2, -15, 15, 0.5F).setSwimmingHeight(0.3F).setHasEgg().setFactor(3.5F))
                    .comp(new Ai.TortoiseAi())
                    .comp(new Name())
                    .comp(new Eating(30, 2.5F, 0.15F,
                            new Eating.Eat(Animations.INSTANT, FoodTypes.WHOLE, BaseClassification.FRUIT),
                            new Eating.Eat(Animations.COMMON, FoodTypes.WHOLE, BaseClassification.INSECT))
                            .hasEggStage())
                    .comp(new Flee(2.5F));
        }
    };

/*
INFO;NameId;393;DescId;394;price;35750;dpPerMin;120;range;2;placeSound;thud
LIFE;averagePop;3.75;averageLife;52.0;popFactors;0;breedMat;26.0;breedTime;18.810873;parentId;75;count;290;
reqs;2;LIFE;type;2;enviroType;44;classification;pbl130;count;2;LIFE;type;2;enviroType;0;classification;pnw136;
count;2;FALL_DEATH;fallTime;1.5;totalTime;2.5;angle;180;particles;0;enviroReqCount;2;reqId;2;barren;0;
goodBiomes;2;2;9;idealFactor;80;influence;0.9;reqId;5;likedSpecies;3;pnw;png;pnf;influence;0.8
GROWTH;dynamic;1;growthTime;17.333334;modelStages;3
MOVEMENT;9;speed;0.16;xRot;2;minRot;-15;maxRot;15;rockSpeed;0.5;swimHeight;0.3;hasEgg;1;swimFactor;3.5
AI;TORTOISE
NAME
EATING;MaxHunger;30;HungerPerHour;2.5;eatRadius;0.15;Anims;2;0;3;DietOptions;2;pnw;WHOLE;1;ef;WHOLE;0;runs;0;ai;0;hasEgg;1
FLEE;TURTLE;2.5;land;1;swim;1
*/

    EntityRepository() {}

    public abstract EquilinoxEntity loadEntity();
}
