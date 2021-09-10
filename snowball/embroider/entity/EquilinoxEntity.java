package com.snowball.embroider.entity;

import com.snowball.embroider.component.architecture.Information;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.component.IComponent;
import com.snowball.embroider.util.component.CompSound;
import languages.GameText;

class EquilinoxEntity extends CustomEntity {
    String name;

    EquilinoxEntity(Enum<? extends IClassifier> classification) {
        super(0, null, (IClassifier) classification, 0, 0);

        this.name = ((IClassifier) classification).getId() + "_" + classification.name();

        this.setBase();
    }

    EquilinoxEntity comp(IComponent component) {
        this.comp(component);
        return this;
    }

    EquilinoxEntity info(int id, int price, int dp, int range, CompSound sound) {
        this.comp(new Information(GameText.getText(id++), GameText.getText(id), price, dp, range, sound));
        return this;
    }
}
