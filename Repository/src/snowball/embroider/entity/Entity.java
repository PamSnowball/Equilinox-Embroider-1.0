package snowball.embroider.entity;

import snowball.embroider.component.IComponent;
import snowball.embroider.enumerator.classification.BaseClassification;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.util.Vector;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	private final WaterData water;
	protected String overrideName = null;

	private final String classification = BaseClassification.STRUCTURE.getClassification();
	private final String print = this.getClass().getTypeName();
	private final String name;

	private String entityName = print;

	protected boolean alwaysVisible = false;
	protected boolean randomize = false;
	protected boolean hasCustomIcon;
	protected boolean material;
	protected boolean grows;

	protected float iconSize;
	protected float iconY;
	
	protected int mainStage = -1;
	private int growthStages = 1;
	private int id = 184;
	
	private final List<IComponent> comps = new ArrayList<>();
	
	private final List<Integer> ids = new ArrayList<>();
	
	protected List<String> components = new ArrayList<>();
	protected List<String> newEntity = new ArrayList<>();
	
	public Entity(int id, String name, IClassifier classification, float size, WaterData water, SpecialData special, IconData icon) {
		if (special != null) {
			if (special.name != null) overrideName = special.name;
			if (special.stage > 0) mainStage = special.stage;
			
			this.alwaysVisible = special.visible;
			this.randomize = special.random;
		}
		
		if (icon != null) {
			if (icon.size > 0.1F) iconSize = icon.size;
			if (icon.y > 0.1F) iconY = icon.y;
			
			this.hasCustomIcon = icon.hasCustomIcon;
		}
		
		if (classification == null) classification = BaseClassification.STRUCTURE;
		
		if (size <= 0.5F) size = 0.5F;
		if (name == null) name = "";
		if (id > 183) this.id = id;
		
		this.name = name;
		this.water = water;

		firstLine(size);
		
		pastLines(classification.toString(), water.height);
	}
	
	public Entity(int id, String name, IClassifier classification, float size, WaterData water) {
		if (classification == null) classification = BaseClassification.STRUCTURE;

		if (size <= 0.5F) size = 0.5F;
		if (name == null) name = "";
		if (id > 183) this.id = id;
		
		this.name = id + "_" + name;
		this.water = water;

		firstLine(size);
		
		pastLines(classification.getClassification(), water.height);
	}

	public List<String> load() {
		return newEntity;
	}

	public static class IconData {
		final boolean hasCustomIcon;

		final float size;
		final float y;
		
		public IconData(float iconSize, float iconY) {
			this.hasCustomIcon = true;
			this.size = iconSize;
			this.y = iconY;
		}
	}
	
	public static class SpecialData {
		final boolean visible;
		final boolean random;
		
		final int stage;
		
		final String name;
		
		public SpecialData(String overrideName, int mainStage, boolean randomize, boolean alwaysVisible) {
			this.visible = alwaysVisible;
			this.name = overrideName;
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
		if (overrideName != null) newEntity.add("overrideName;" + overrideName);
		
		newEntity.add("\n");
		
		System.out.println("First Line of " + getName());
		
		String entity = "Entity";
		
		if (print.contains(entity)) {
			int index = print.indexOf(entity);
			
			entityName = print.substring(index + entity.length());
		}
		
		System.out.println("Loaded " + entityName + " Super");
	}
	
	protected final void pastLines(String classification, float height) {
		newEntity.add(classification);
		newEntity.add("\n");
		
		newEntity.add(Utils.value(height <= 0 && water.aquatic ? 1 : 0, height >= 0 ? 1 : 0, height != 0 ? height : null));

		newEntity.add("\n");
		
		System.out.println("Past Lines of " + getName());
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
	
	public int getGrowthStages() {
		return growthStages;
	}
	
	public void setGrowthStages(int growthStages) {
		if (growthStages >= 1) this.growthStages = growthStages;
	}

	public int getId() {
		return id;
	}

	public boolean isAquatic() {
		return water.aquatic && water.height <= 0;
	}

	public boolean isLand() {
		return water.height >= 0;
	}

	public String getClassification() {
		return classification + id;
	}
	
	@Override
	public String toString() {
		return classification;
	}

	public static String getClassification(int id) {
		for (Entity entity : EntityLoader.ENTITIES) if (entity.getId() == id) return entity.getClassification();
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
