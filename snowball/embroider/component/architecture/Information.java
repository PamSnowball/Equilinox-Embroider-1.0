package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.sound.ISound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Information extends NativeComponent {
	ISound sound;
	
	int price;
	int dpMin;
	int range;

	String name;
	String desc;
	
	public Information(String name, String description, int price, int cashPerMin, int range, ISound sound) {
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
		
		sound.loadSound().loadSound();
		
		if (!desc.endsWith(".")) desc += '.';
		if (name.endsWith(".")) name = name.substring(0, name.length() - 1);
		
		info.add(Utils.value("CUSTOM_INFO;Name", name, "Desc", desc, "price", price, "dpPerMin", dpMin, "range", range));
		info.add("placeSound;" + sound.getSound());
		
		return info;
	}
	
	@Override
	public int getId() {
		return 46;
	}
}
