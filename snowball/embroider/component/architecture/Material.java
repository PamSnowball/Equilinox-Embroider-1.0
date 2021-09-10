package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.enumerator.Colours;

import java.util.*;
import java.util.stream.IntStream;

public class Material extends NativeComponent {
	Vector[] colors;

	int[] prices;

	boolean hasSecond;

	/**
	 * Constructs the MATERIAL component which is used by most living entities to set their colours.
	 *
	 * @param hasSecondNatural if true, entity can spawn with the second color of {@code colors}
	 * @param colors colors that entity has and their prices
	 */
	public Material(boolean hasSecondNatural, CustomEntity.MaterialColor... colors) {
		this.hasSecond = hasSecondNatural && colors.length > 1;
		if (colors != null) {
			colors = Arrays.stream(colors).sorted(Comparator.comparingInt(CustomEntity.MaterialColor::getPrice)).toArray(CustomEntity.MaterialColor[]::new);

			this.prices = Arrays.stream(colors).flatMapToInt(color -> IntStream.of(color.price)).toArray();
			this.colors = Arrays.stream(colors).map(color -> color.color).toArray(Vector[]::new);
		}
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		List<String> colour = new ArrayList<>();

		if (prices.length > 0) {
			entity.setHasMaterial();

			colour.add(Utils.value("MATERIAL;second", hasSecond ? 1 : 0, "count", colors.length));
			for (int i = 0; i < colors.length; i++) {
				colour.add(Colours.checkForColour(colors[i]) + ";" + prices[i] + ";");
			}
		}

		return colour;
	}
	
	@Override
	public int getId() {
		return 48;
	}
}
