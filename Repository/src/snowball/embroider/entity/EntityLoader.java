package snowball.embroider.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityLoader {
	private EntityLoader() { throw new IllegalStateException("Utility Class!"); }

	protected static final List<Entity> ENTITIES = new ArrayList<>();
	
	public static Entity WHALE_SHARK = new EntityWhaleShark();
}
