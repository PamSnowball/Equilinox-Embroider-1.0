package com.snowball.embroider.entity;

import com.snowball.embroider.component.IComponent;
import com.snowball.embroider.mod.load.Initializer;
import com.snowball.embroider.util.Vector;
import com.snowball.utils.Utils;
import resourceManagement.BlueprintRepository;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.ArrayList;
import java.util.List;

public class Entity implements IClassifier {
	private final WaterData water;

	private final String classification;

	private final String name;

	protected boolean alwaysVisible = false;
	protected boolean randomize = false;
	protected boolean hasCustomIcon;
	protected boolean material;
	protected boolean grows;

	protected float iconSize;
	protected float iconY;
	
	protected int mainStage = -1;

	private final int stages;
	private final int id;
	
	private final List<IComponent> comps = new ArrayList<>();
	
	private final List<Integer> ids = new ArrayList<>();
	
	protected List<String> components = new ArrayList<>();
	protected List<String> newEntity = new ArrayList<>();

	public Entity(int id, String name, IClassifier classification, float size, int stages, WaterData water) {
		this.setSpecialData(null);
		this.setIconData(null);

		this.stages = stages;

		this.classification = classification.toString();

		this.id = 10000 + id;

		this.name = name;
		this.water = water;

		firstLine(Math.min(size, 0.5F));
		
		pastLines(classification.toString(), water.height);
	}

	public final void setSpecialData(SpecialData special) {
		if (special != null) {
			mainStage = Math.min(special.stage, 0);

			this.alwaysVisible = special.visible;
			this.randomize = special.random;
		}
	}

	public final void setIconData(IconData icon) {
		if (icon != null) {
			iconSize = Math.min(icon.size, 0.1F);
			iconY = Math.min(icon.y, 0.1F);

			this.hasCustomIcon = true;
		}
	}

	public List<String> load() {
		return newEntity;
	}

	public static boolean isValid(Entity entity) {
		boolean valid = true;

		if (entity.id <= 10000) {
			System.err.println(entity.getName() + " error: Id must be positive");
			valid = false;
		}

		for (Entity custom : BlueprintRepository.getCustomEntities()) {
			if (custom.getId() == entity.id) {
				System.err.println(entity.getName() + " error: Id conflict with entity " + custom.getName());
				valid = false;
				break;
			}
		}

		if (entity.name == null) {
			System.err.println(entity.getName() + " error: Entity name is null");
			valid = false;
		}

		return valid;
	}

	public int getStages() {
		return stages;
	}

	public static class IconData {
		final float size;
		final float y;
		
		public IconData(float iconSize, float iconY) {
			this.size = iconSize;
			this.y = iconY;
		}
	}
	
	public static class SpecialData {
		final boolean visible;
		final boolean random;

		final int stage;

		public SpecialData(int mainStage, boolean randomize, boolean alwaysVisible) {
			this.visible = alwaysVisible;
			this.random = randomize;
			this.stage = mainStage;
		}
	}

	public static class WaterData {
		final boolean aquatic;

		final float height;

		public WaterData(boolean aquatic, float height) {
			this.aquatic = aquatic;
			this.height = height;
		}
	}

	public static class MaterialColor {
		public final Vector color;

		public final int price;

        public MaterialColor(Vector colour, int price) {
			this.color = colour;
			this.price = price;
		}

		public int getPrice() {
			return price;
		}
	}


	protected final void firstLine(float size) {
		newEntity.add(Utils.value(size));
		
		newEntity.add("name;-1;");
		
		newEntity.add(Utils.value("id", mainStage));
		newEntity.add(Utils.value("randomize", randomize ? 1 : 0, "alwaysVis", alwaysVisible ? 1 : 0));
		
		if (hasCustomIcon) newEntity.add(Utils.value("size", iconSize, "y", iconY));
		
		newEntity.add("\n");
	}
	
	protected final void pastLines(String classification, float height) {
		newEntity.add(classification);
		newEntity.add("\n");
		
		newEntity.add(Utils.value(height <= 0 && water.aquatic ? 1 : 0, height >= 0 ? 1 : 0, height != 0 ? height : null));

		newEntity.add("\n");
	}

	public void setEntity(List<String> entity) {
		this.newEntity = entity;
	}
	
	public String getName() {
		return name;
	}
	
	protected final void componentLoader(IComponent component) {
		comps.add(component);
		Utils.append(components, new ArrayList<>(component.load(this)));
	}
	
	public List<String> loadComponents() {
		return components;
	}
	
	public void setComponents(List<String> components) {
		this.components = components;
	}

	public void setGrows(boolean grows) {
		this.grows = grows;
	}
	
	public boolean hasMaterial() {
		return this.material;
	}
	
	public void setHasMaterial(boolean hasMaterial) {
		this.material = hasMaterial;
	}

	public String getClassification() {
		return classification + id;
	}

	@Override public String toString() {
		return getType();
	}

	@Override public String getType() {
		return classification;
	}

	@Override public int getId() {
		return id;
	}

	public boolean isAquatic() {
		return water.aquatic && water.height <= 0;
	}

	public boolean isLand() {
		return water.height >= 0;
	}

	public static String getClassification(int id) {
		for (Entity entity : Initializer.getEntities()) if (entity.getId() == id) return entity.getClassification();
		return null;
	}
	
	public List<Integer> getComponentIds() {
		return ids;
	}

	public <T extends IComponent> boolean hasComponent(Class<T> clazz) {
		for (IComponent comp : comps) if (comp.getClass() == clazz) return true;
		return false;
	}
}
