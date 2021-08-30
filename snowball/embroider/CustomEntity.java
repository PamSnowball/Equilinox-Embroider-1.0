package com.snowball.embroider;

import com.snowball.embroider.component.IComponent;
import com.snowball.mod.load.Initializer;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.Utils;
import resourceManagement.BlueprintRepository;
import com.snowball.embroider.enumerator.classification.IClassifier;

import java.util.*;
import java.util.stream.Collectors;

public class CustomEntity implements IClassifier {
	private boolean aquatic;

	private final String classification;

	private final String name;

	private final float size;

	protected boolean alwaysVisible = false;
	protected boolean hasCustomIcon = false;
	protected boolean randomize = false;
	protected boolean material;

	protected float iconSize;
	protected float iconY;
	protected float height;
	
	protected int mainStage = -1;

	private final int stages;
	private final int id;
	
	private final List<IComponent> comps = new ArrayList<>();
	
	protected List<String> components = new ArrayList<>();
	protected List<String> newEntity = new ArrayList<>();

	public CustomEntity(int id, String name, IClassifier classification, float size, int stages) {
		this.stages = stages;

		this.classification = classification.toString();

		this.id = 10000 + id;

		this.name = name;
		this.aquatic = false;

		this.size = size;
	}

	void init() {
		firstLine(Math.max(size, 0));

		pastLines(classification, height);
	}

	protected final void setAquatic(float height) {
		this.height = height;
		this.aquatic = true;
	}

	public final void setSpecialData(int stage, boolean random, boolean visible) {
		mainStage = Math.max(stage, 0);

		this.alwaysVisible = visible;
		this.randomize = random;
	}

	public final void setIconData(float size, float y) {
		iconSize = Math.max(size, 0);
		iconY = Math.max(y, 0);

		this.hasCustomIcon = true;
	}

	public List<String> load() {
		return newEntity;
	}

	public static boolean isValid(CustomEntity entity) {
		boolean valid = true;

		if (entity.id <= 10000) {
			System.err.println(entity.getName() + " error: Id must be positive");
			valid = false;
		}

		for (CustomEntity custom : BlueprintRepository.getCustomEntities()) {
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

	private List<String> organize() {
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < comps.size(); i++) {
			map.put(comps.get(i).getId(), components.get(i));
		}

		return new ArrayList<>(map.values());
	}

	public int getStages() {
		return stages;
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
		
		newEntity.add(Utils.value(height <= 0 && aquatic ? 1 : 0, height >= 0 ? 1 : 0, height != 0 ? height : ""));

		newEntity.add("\n");
	}

	public void setEntity(List<String> entity) {
		this.newEntity = entity;
	}
	
	public String getName() {
		return name;
	}
	
	protected final CustomEntity componentLoader(IComponent component) {
		comps.add(component); Utils.append(new ArrayList<>(component.load(this)), components);
		return this;
	}
	
	public List<String> loadComponents() {
		return organize();
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
		return aquatic && height <= 0;
	}

	public boolean isLand() {
		return height >= 0;
	}

	public static String getClassification(int id) {
		for (CustomEntity entity : Initializer.getEntities()) if (entity.getId() == id) return entity.getClassification();
		return null;
	}

	public <T extends IComponent> boolean hasComponent(Class<T> clazz) {
		for (IComponent comp : comps) if (comp.getClass() == clazz) return true;
		return false;
	}
}
