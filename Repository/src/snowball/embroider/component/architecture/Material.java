package snowball.embroider.component.architecture;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.entity.Entity.MaterialColor;
import snowball.embroider.enumerator.EnumColours;
import snowball.embroider.util.Vector;
import snowball.embroider.utils.Utils;

import java.util.*;
import java.util.stream.IntStream;

public class Material extends NativeComponent {
	Vector[] colors;

	int[] prices;

	boolean hasSecond;

	Entity entity;
	
	public Material(Entity entity, boolean hasSecondNatural, MaterialColor[] colors) {
		this.hasSecond = hasSecondNatural;
		if (colors != null) {
			colors = Arrays.stream(colors).sorted(Comparator.comparingInt(MaterialColor::getPrice)).toArray(MaterialColor[]::new);

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
				colour.add(EnumColours.checkForColour(colors[i]) + ";" + prices[i] + ";");
			}
		}

		return colour;
	}
	
	@Override
	public int getId() {
		return 47;
	}
}
