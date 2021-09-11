package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.util.component.CompSound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Information extends NativeComponent {
	CompSound sound;
	
	int price;
	int dpMin;
	int range;

	String name;
	String desc;

	/**
	 * Constructs the INFO component which is used the vast majority of entities to set basic information. This is definitely the most important component. <br>
	 * Range is used to determine lots of things including, moving range, entity population detection and environment preference. <br>
	 * @param name name displayed in game
	 * @param description basic description of the entity
	 * @param price dp price of the entity
	 * @param cashPerMin dp generated per minute by each entity
	 * @param range range of the entity
	 * @param sound sound played when entity is put down
	 */
	public Information(String name, String description, int price, int cashPerMin, int range, CompSound sound) {
		this.name = name;
		this.desc = description.replace(";", ",");
		this.price = price;
		this.dpMin = cashPerMin;
		this.range = range;
		this.sound = sound;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		List<String> info = new ArrayList<>();

		sound.loadSound();

		info.add(Utils.value("CUSTOM_INFO;Name", name, "Desc", desc, "price", price, "dpPerMin", dpMin, "range", range));
		info.add("placeSound;" + sound.getSound());
		
		return info;
	}
	
	@Override
	public int getId() {
		return 47;
	}
}
