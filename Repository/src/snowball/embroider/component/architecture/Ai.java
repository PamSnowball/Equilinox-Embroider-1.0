package snowball.embroider.component.architecture;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ai {
	private Ai() {
		throw new IllegalStateException("Utility Class");
	}

	public static class BaseAi extends NativeComponent {
		String id;
		
		protected BaseAi(String id) {
			this.id = id;
		}
		
		public Collection<String> load(Entity entity) {
			return Collections.singleton("AI;" + id);
		}

		@Override
		public int getId() {
			return 31;
		}
	}
	
	public static class PatrolAi extends BaseAi {
		float minIdleTime = 5.0F;
		float maxIdleTime = 10.0F;
		
		public PatrolAi() { super("PATROL"); }
		
		public PatrolAi(float minIdleTime, float maxIdleTime) {
			super("PATROL");
			if (minIdleTime > 0) this.minIdleTime = minIdleTime;
			if (maxIdleTime > 0) this.maxIdleTime = maxIdleTime;
		}
		
		@Override
		public Collection<String> load(Entity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			if (minIdleTime != 5.0F || maxIdleTime != 10.0F) brain.add(Utils.value(";minIdleTime", minIdleTime, "maxIdleTime") + maxIdleTime);

			return brain;
		}
	}
	
	public static class SwimAi extends BaseAi { public SwimAi() { super("SWIM"); } }
	
	public static class BirdAi extends BaseAi {
		float circleRot = 50.0F;
		float circleTime = 2.0F;
		
		public BirdAi() { super("BIRD"); }

		public BirdAi(float circleRot, float circleMinTime) {
			super("BIRD");
			if (circleMinTime > 0) this.circleTime = circleMinTime;
			if (circleRot > 0) this.circleRot = circleRot;
		}

		@Override
		public Collection<String> load(Entity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			if (circleRot != 50.0F || circleTime != 2.0F) brain.add(Utils.value(";circleRot", circleRot, "circleTime") + circleTime);

			return brain;
		}
	}
	
	public static class WalkingBirdAi extends BaseAi {
		float minIdle;
		float maxIdle;
		boolean stay;
		
		public WalkingBirdAi() { super("WALKING_BIRD"); }
		
		public WalkingBirdAi(float minIdleTime, float maxIdleTime, boolean stayOnLand) {
			super("WALKING_BIRD");
			if (minIdleTime > 0) this.minIdle = minIdleTime;
			if (maxIdleTime > 0) this.maxIdle = maxIdleTime;
			this.stay = stayOnLand;
		}

		@Override
		public Collection<String> load(Entity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			brain.add(Utils.value("minIdle", minIdle, "maxIdle", maxIdle, "stayOnLand", (stay ? 1 : 0)));

			return brain;
		}
	}
	
	public static class BeeAi extends BaseAi { public BeeAi() { super("BEE"); } }
	
	public static class PatrolWithSwimAi extends PatrolAi {
		public PatrolWithSwimAi() {
			this.id = "PATROL_WITH_SWIM";
		}
		
		public PatrolWithSwimAi(float minIdleTime, float maxIdleTime) {
			super(minIdleTime, maxIdleTime);
			this.id = "PATROL_WITH_SWIM";
		}
	}
	
	public static class TortoiseAi extends BaseAi { public TortoiseAi() { super("TORTOISE"); } }
	
	public static class MeerkatAi extends BaseAi {
		float minIdleTime = 7.0F;
		float maxIdleTime = 15.0F;
		
		public MeerkatAi() { super("MEERKAT"); }
		
		public MeerkatAi(float minIdleTime, float maxIdleTime) {
			super("MEERKAT");
			if (minIdleTime > 0) this.minIdleTime = minIdleTime;
			if (maxIdleTime > 0) this.maxIdleTime = maxIdleTime;
		}

		@Override
		public Collection<String> load(Entity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			if (minIdleTime != 7.0F || maxIdleTime != 15.0F) brain.add(Utils.value(";minIdleTime", minIdleTime, "maxIdleTime") + maxIdleTime);

			return brain;
		}
	}
	
	public static class DolphinAi extends BaseAi { public DolphinAi() { super("DOLPHIN"); } }
}
