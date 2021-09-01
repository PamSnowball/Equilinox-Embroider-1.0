package com.snowball.embroider;

import com.snowball.embroider.component.IComponent;
import com.snowball.mod.load.Initializer;
import com.snowball.embroider.util.Vector;
import com.snowball.embroider.util.Utils;
import javafx.scene.web.WebHistory;
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
	protected StringBuilder newEntity = new StringBuilder();

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

	public final void setAquatic(float height) {
		this.height = height;
		this.aquatic = true;
	}

	protected final void setSpecialData(int stage, boolean random, boolean visible) {
		mainStage = Math.max(stage, 0);

		this.alwaysVisible = visible;
		this.randomize = random;
	}

	protected final void setIconData(float size, float y) {
		iconSize = 1 / Math.max(size, 0.01F);
		iconY = Math.max(y, 0);

		this.hasCustomIcon = true;
	}

	public String load() {
		return newEntity.toString();
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

		return map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).map(Map.Entry::getValue)
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					Collections.reverse(list);
					return list;
				}));
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


	private void firstLine(float size) {
		newEntity.append(size).append(";");
		
		newEntity.append("name;-1;");
		
		newEntity.append(Utils.value("id", mainStage, "randomize", randomize ? 1 : 0, "alwaysVis", alwaysVisible ? 1 : 0));
		
		if (hasCustomIcon) newEntity.append(Utils.value("size", iconSize, "y", iconY));
		
		newEntity.append("\n");
	}

	private void pastLines(String classification, float height) {
		newEntity.append(classification);
		newEntity.append("\n");
		
		newEntity.append(Utils.value(height <= 0 && aquatic ? 1 : 0, height >= 0 ? 1 : 0, height != 0 ? height : ""));

		newEntity.append("\n");

		System.out.println(newEntity);
	}
	
	public String getName() {
		return name;
	}
	
	protected final void componentLoader(IComponent component) {
		comps.add(component); Utils.append(new ArrayList<>(component.load(this)), components);
	}
	
	public List<String> loadComponents() {
		System.out.println();
		List<String> list = organize();
		list.forEach(System.out::println);
		return list;
	}

	public boolean hasMaterial() {
		return this.material;
	}
	
	public void setHasMaterial() {
		this.material = true;
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
