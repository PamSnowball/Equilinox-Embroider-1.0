package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Colours;

import java.util.*;
import java.util.stream.IntStream;

public class Material extends NativeComponent {
	com.snowball.embroider.util.Vector[] colors;

	int[] prices;

	boolean hasSecond;

	Entity entity;
	
	public Material(Entity entity, boolean hasSecondNatural, Entity.MaterialColor[] colors) {
		this.hasSecond = hasSecondNatural;
		if (colors != null) {
			colors = Arrays.stream(colors).sorted(Comparator.comparingInt(Entity.MaterialColor::getPrice)).toArray(Entity.MaterialColor[]::new);

			this.prices = Arrays.stream(colors).flatMapToInt(color -> IntStream.of(color.price)).toArray();
			this.colors = Arrays.stream(colors).map(color -> color.color).toArray(Vector[]::new);
		}

		if (entity != null) this.entity = entity;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> colour = new ArrayList<>();

		if (prices.length == 0 || colors.length == 0 || entity != null) {
			entity.setHasMaterial(true);

			colour.add(Utils.value("MATERIAL;second", hasSecond ? 1 : 0, "count" + colors.length));
			for (int i = 0; i < colors.length; i++) {
				colour.add(Colours.checkForColour(colors[i]) + ";" + prices[i] + ";");
			}
		}

		return colour;
	}
	
	@Override
	public int getId() {
		return 47;
	}
}
