package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ai extends NativeComponent {
	String id;

	private Ai(String id) {
		this.id = id;
	}

	public Collection<String> load(CustomEntity entity) {
		return Collections.singleton("AI;" + id);
	}

	@Override
	public int getId() {
		return 30;
	}

	public static class PatrolAi extends Ai {
		float minIdleTime = 5.0F;
		float maxIdleTime = 10.0F;

		/**
		 * Constructs the PATROL AI which is used by most land animals, it consists of
		 * following parents when young and wandering around when grown. <p>
		 * <b>Entity must have two life stages.</b>
		 */
		public PatrolAi() {
			super("PATROL");
		}

		/** Sets minimum and maximum time in seconds the entity can be idol. */
		public PatrolAi setIdleTime(float minIdleTime, float maxIdleTime) {
			this.maxIdleTime = maxIdleTime;
			this.minIdleTime = minIdleTime;
			return this;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			if (entity.getStages() == 2 && (minIdleTime != 5.0F || maxIdleTime != 10.0F)) {
				brain.add(Utils.value(";minIdleTime", minIdleTime, "maxIdleTime") + maxIdleTime);
			}

			return brain;
		}
	}

	public static class SwimAi extends Ai {
		/** Constructs the SWIM AI which is used by most aquatic animals, it consists of swimming around aimlessly. */
		public SwimAi() {
			super("SWIM");
		}
	}
	
	public static class BirdAi extends Ai {
		float circleRot = 50.0F;
		float circleTime = 2.0F;

		/**
		 * Constructs the BIRD AI which is used by flying birds, it consists of, if the entity has PERCHER,
		 * stay idle when young, otherwise wander around aimlessly.
		 * When grown, entity wanders around and if it has PERCHER, perch now and then.<p>
		 * <b>Entity must have two life stages.</b>
		 */
		public BirdAi() {
			super("BIRD");
		}

		/**
		 * Sets average rotation angle and perching minimum time.
		 *
		 * @param circleRot average rotation angle variation
		 * @param circleTime perching minimum time in seconds
		 */
		public BirdAi setCircleBehaviour(float circleRot, float circleTime) {
			this.circleTime = Math.max(circleTime, 0);
			this.circleRot = circleRot;
			return this;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			if (entity.getStages() == 2 && (circleRot != 50.0F || circleTime != 2.0F)) {
				brain.add(Utils.value(";circleRot", circleRot, "circleTime") + circleTime);
			}

			return brain;
		}
	}
	
	public static class WalkingBirdAi extends Ai {
		float minIdleTime;
		float maxIdleTime;

		boolean swims;

		/**
		 * Constructs the WALKING BIRD AI which is used by walking birds, it consists of staying idle while egg,
		 * following parents when young and stay wandering around aimlessly when grown.<p>
		 * <b>Entity must have three life stages.</b>
		 *
		 * @param minIdleTime minimum idle time
		 * @param maxIdleTime maximum idle time
		 * @param swims if true, entity swims
		 */
		public WalkingBirdAi(float minIdleTime, float maxIdleTime, boolean swims) {
			super("WALKING_BIRD");

			this.minIdleTime = Math.max(minIdleTime, 0);
			this.maxIdleTime = Math.max(maxIdleTime, 0);

			this.swims = !swims;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			entity.setHasEggStage();
			if (entity.getStages() == 3) {
				brain.add(Utils.value("minIdle", minIdleTime, "maxIdle", maxIdleTime, "stayOnLand", (swims ? 0 : 1)));
			}

			return brain;
		}
	}
	
	public static class BeeAi extends Ai {
		/** Constructs the BEE AI which is used by bees, it consists of wandering around aimlessly. */
		public BeeAi() {
			super("BEE");
		}
	}

	public static class PatrolWithSwimAi extends PatrolAi {
		/**
		 * Constructs the PATROL WITH SWIM AI which is used by beavers, it consists of
		 * following parents when young and wandering and swimming around when grown. <p>
		 * <b>Entity must have two life stages.</b>
		 */
		public PatrolWithSwimAi() {
			this.id = "PATROL_WITH_SWIM";
		}
	}
	
	public static class TortoiseAi extends Ai {
		/**
		 * Constructs the TORTOISE AI which is used by tortoises, it consists of staying idle while egg,
		 * following parents when young and stay wandering and swimming around aimlessly when grown. <p>
		 * <b>Entity must have two life stages.</b>
		 */
		public TortoiseAi() {
			super("TORTOISE");
		}
	}
	
	public static class MeerkatAi extends Ai {
		float minIdleTime = 7.0F;
		float maxIdleTime = 15.0F;

		/**
		 * Constructs the MEERKAT AI which is used by meerkats, it consists of
		 * following parents when young and jumping around when grown. <p>
		 * <b>Entity must have two life stages.</b>
		 */
		public MeerkatAi() {
			super("MEERKAT");
		}

		/** Sets minimum time in seconds the entity can be idol */
		public MeerkatAi setIdleTime(float minIdleTime, float maxIdleTime) {
			this.maxIdleTime = maxIdleTime;
			this.minIdleTime = minIdleTime;
			return this;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> brain = new ArrayList<>(super.load(entity));

			entity.setHasEggStage();
			if (entity.getStages() == 2 && (minIdleTime != 7.0F || maxIdleTime != 15.0F)) {
				brain.add(Utils.value(";minIdleTime", minIdleTime, "maxIdleTime") + maxIdleTime);
			}

			return brain;
		}
	}
	
	public static class DolphinAi extends Ai {
		/** Constructs the DOLPHIN AI which is used by dolphins, it consists of jumping and swimming around. */
		public DolphinAi() {
			super("DOLPHIN");
		}
	}
}
