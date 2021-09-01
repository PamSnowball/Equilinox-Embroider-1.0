package com.snowball.mod.load;

import aiComponent.AiProgramType;
import biomes.Biome;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.component.blueprint.Comp;
import com.snowball.embroider.component.blueprint.CustomAi;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.mod.Mod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreInitializer {
    static final Map<CustomEntity, Mod> map = new HashMap<>();

    Mod mod;

    PreInitializer(Mod mod) {
        this.mod = mod;
    }

    public static Mod getModFromEntity(CustomEntity entity) {
        for (Map.Entry<CustomEntity, Mod> entry : map.entrySet()) {
            if (entry.getKey().equals(entity)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public <T extends Enum<T> & IClassifier> void addCustomClassifications(Class<T> classifier) {
        Embroider.addClassification(classifier);
    }

    public void addCustomAi(CustomAi ai) {
        AiProgramType.addCustomAi(ai.getName(), ai.getType());
    }

    public void addCustomComponent(Comp components) {
        Embroider.addComponent(components);
    }

    public void addCustomBiome(Biome biome) {
        Embroider.addBiome(biome);
    }

    public static List<Mod> getMods() {
        return ModLoader.loadedMods;
    }

    public static boolean isModPresent(Mod mod) {
        return ModLoader.loadedMods.contains(mod);
    }
}
