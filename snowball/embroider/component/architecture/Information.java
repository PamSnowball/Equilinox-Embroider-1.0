package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
import com.snowball.embroider.util.component.CompSound;
import com.snowball.embroider.util.Utils;

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
	 *
	 *
	 * @param name name displayed in game
	 * @param description
	 * @param price
	 * @param cashPerMin
	 * @param range
	 * @param sound
	 */
	public Information(String name, String description, int price, int cashPerMin, int range, CompSound sound) {
		this.name = name;
		this.desc = description;
		this.price = price;
		this.dpMin = cashPerMin;
		this.range = range;
		this.sound = sound;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> info = new ArrayList<>();
		
		sound.loadSound();

		info.add(Utils.value("CUSTOM_INFO;Name", name, "Desc", desc, "price", price, "dpPerMin", dpMin, "range", range));
		info.add("placeSound;" + sound.getSound());
		
		return info;
	}
	
	@Override
	public int getId() {
		return 46;
	}
}
