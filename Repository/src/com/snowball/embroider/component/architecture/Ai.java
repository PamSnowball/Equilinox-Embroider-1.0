package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ai {
	private Ai() {}

	protected static class BaseAi extends NativeComponent {
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

	public static class PatrolBaseAi extends BaseAi {
		float minIdleTime = 5.0F;
		float maxIdleTime = 10.0F;
		
		public PatrolBaseAi() { super("PATROL"); }
		
		public PatrolBaseAi(float minIdleTime, float maxIdleTime) {
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
	
	public static class SwimBaseAi extends BaseAi { public SwimBaseAi() { super("SWIM"); } }
	
	public static class BirdBaseAi extends BaseAi {
		float circleRot = 50.0F;
		float circleTime = 2.0F;

		/**
		 *
		 * @param circleRot
		 * @param circleMinTime
		 */
		public BirdBaseAi(float circleRot, float circleMinTime) {
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
	
	public static class WalkingBirdBaseAi extends BaseAi {
		float minIdle;
		float maxIdle;
		boolean stay;

		public WalkingBirdBaseAi(float minIdleTime, float maxIdleTime, boolean stayOnLand) {
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
	
	public static class BeeBaseAi extends BaseAi { public BeeBaseAi() { super("BEE"); } }
	
	public static class PatrolWithSwimBaseAi extends PatrolBaseAi {
		public PatrolWithSwimBaseAi(float minIdleTime, float maxIdleTime) {
			super(minIdleTime, maxIdleTime);
			this.id = "PATROL_WITH_SWIM";
		}
	}
	
	public static class TortoiseBaseAi extends BaseAi { public TortoiseBaseAi() { super("TORTOISE"); } }
	
	public static class MeerkatBaseAi extends BaseAi {
		float minIdleTime = 7.0F;
		float maxIdleTime = 15.0F;

		public MeerkatBaseAi(float minIdleTime, float maxIdleTime) {
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
	
	public static class DolphinBaseAi extends BaseAi {
		public DolphinBaseAi() {
			super("DOLPHIN");
		}
	}
}
