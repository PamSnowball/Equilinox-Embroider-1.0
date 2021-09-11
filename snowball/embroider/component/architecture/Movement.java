package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** Constructs the MOVEMENT component which is used by all animals to set movement. */
public class Movement {
	private Movement() {
		throw new IllegalStateException("Utility Class!");
	}

	private static final String MOVE = "MOVEMENT";
	private static final String BOUNCE = "bounce";
	private static final String HEIGHT = "height";
	private static final String ROT = "rotSpeed";
	private static final String SPEED = "speed";

	public static class BaseMovement extends NativeComponent {
		@Override
		public Collection<String> load(CustomEntity entity) {
			return new ArrayList<>();
		}

		@Override
		public int getId() {
			return 38;
		}
	}

	private static class BasicMovement extends BaseMovement {
		float speed;
		float rotation;
		float bouncePower;
		int id;

		private BasicMovement(float speed, float rotation, float bounce, int id) {
			this.speed = speed;
			this.rotation = rotation;
			this.bouncePower = bounce;
			this.id = id;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> list = new ArrayList<>();

			list.add(Utils.value(MOVE, id, SPEED, speed));
			if (rotation > 0) list.add(ROT + ";" + rotation + ";");
			list.add(BOUNCE + ";" + bouncePower + ";");

			return list;
		}
	}
	
	public static class FrogMovement extends BasicMovement {
		float waitTime;
		float bounciness;

		/**
		 * Constructs the frogs movement which is used by frogs and toads, it consists of move jumping.
		 *
		 * @param speed movement speed
		 * @param bounce bouncing height
		 * @param wait time in seconds entity bounces while moving
		 * @param bounciness bouncing speed
		 */
		public FrogMovement(float speed, float bounce, float wait, float bounciness) {
			super(speed, 0, bounce, 6);
			this.waitTime = wait;
			this.bounciness = bounciness;
		}
		
		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> move = new ArrayList<>(super.load(entity));
			
			move.add(Utils.value("wait", waitTime, "bounciness", bounciness));
			
			return move;
		}
	}
	
	public static class RabbitMovement extends BasicMovement {
		float upRotSpeed;
		float downRotSpeed;
		float[] fronts;
		float[] backs;

		/**
		 * Constructs the rabbit movement which is used by rabbits, it consists of move jumping fastly.
		 *
		 * @param speed movement speed
		 * @param bounce bouncing height
		 * @param upRot bouncing up speed
		 * @param downRot bouncing down speed
		 * @param fronts fronts of the models of each life stage
		 * @param backs backs of the models of each life stage
		 */
		public RabbitMovement(float speed, float bounce, float upRot, float downRot, float[] fronts, float[] backs) {
			super(speed, 0, bounce, 7);
			this.upRotSpeed = upRot;
			this.downRotSpeed = downRot;
			this.fronts = fronts;
			this.backs = backs;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> move = new ArrayList<>(super.load(entity));

			if (fronts != null && backs != null && fronts.length == entity.getStages() && backs.length == entity.getStages()) {
				move.add(Utils.value("upRot", upRotSpeed, "downRot", downRotSpeed));
				move.add("frontZ;" + fronts.length);
				for (float front : fronts) move.add(";" + front);
				move.add(";backZ;" + backs.length);
				for (float back : backs) move.add(";" + back);
			}

			return move;
		}
	}
	
	public static class FlouncerMovement extends BasicMovement {
		float rotationSpeed;
		float bounceRotation;
		float height;

		/**
		 * Constructs the flouncer movement which is used by goats and boars, it consists of move jumping and smashing the ground.
		 *
		 * @param speed movement speed
		 * @param rotSpeed rotation speed
		 * @param bounce bouncing height
		 * @param bounceRot body x and z rotation max angle
		 * @param height {@code bounceRot} multiplier at the bounce apex
		 */
		public FlouncerMovement(float speed, float rotSpeed, float bounce, float bounceRot, float height) {
			super(speed, rotSpeed, bounce, 8);
			this.rotationSpeed = rotSpeed;
			this.bounceRotation = bounceRot;
			this.height = 1 / height;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> move = new ArrayList<>(super.load(entity));
			
			move.add(Utils.value("bounceRot", bounceRotation, HEIGHT, height));

			return move;
		}
	}
	
	public static class AxisMovement extends BaseMovement {
		int xRot;
		int id;

		float speed;
		float minRot;
		float maxRot;
		float rotationSpeed;

		boolean egg = false;

		float height = -5;

		float swimFactor = 0;
		float inertia = 0;

		/**
		 * Constructs the axis movement which is used by all entities that swim, all walking birds and some small vertebrates,
		 * it consists of constantly rotating while moving. <br>
		 * The rotation axis is defined by {@code rotationType}, it can be {@code 0}, {@code 1} or {@code 2}
		 * which represents the rotation axis that are X, Y and Z respectively.
		 * @param speed movement speed
		 * @param rotationType rotation axis
		 * @param minRot minimum rotation angle
		 * @param maxRot maximum rotation angle
		 * @param rotSpeed rotation speed
		 */
		public AxisMovement(float speed, int rotationType, float minRot, float maxRot, float rotSpeed) {
			this.speed = speed;
			this.xRot = rotationType;
			this.minRot = minRot;
			this.maxRot = maxRot;
			this.rotationSpeed = rotSpeed;
		}

		/** Sets swimming height */
		public AxisMovement setSwimmingHeight(float height) {
			this.height = height;
			return this;
		}

		/** <b> Must use it if entity has egg </b> */
		public AxisMovement setHasEgg() {
			this.egg = true;
			return this;
		}

		/** Sets speed multiplayer when swimming */
		public AxisMovement setFactor(float factor) {
			this.swimFactor = factor;
			return this;
		}

		/** Sets swim agility, should be 0.25F if it is a fish */
		public AxisMovement setInertia(float inertia) {
			this.inertia = inertia;
			return this;
		}
		
		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> move = new ArrayList<>();

			move.add(Utils.value(MOVE, 9, SPEED, speed, "xRot", xRot, "minRot", minRot, "maxRot", maxRot, ROT, rotationSpeed));

			if (height > -5) {
				move.add("height;" + height);
				if (swimFactor > 0 || egg) {
					if (egg) entity.setHasEggStage();
					move.add(";eggStage;" + (egg ? 1 : 0));
					move.add(";swimFactor;" + swimFactor);
					if (inertia != 0) move.add(";inertia;" + inertia);
				}
			}

			return move;
		}
	}

	/** Constructs the butterfly movement which is used by butterflies, it consists of flying up and down */
	public static class ButterflyMovement extends BaseMovement {
		@Override
		public Collection<String> load(CustomEntity entity) {
			return Collections.singleton(MOVE + ';' + 10);
		}
	}

	public static class BeeMovement extends BaseMovement {
		float height;

		/**
		 * Constructs the bee movement which is used by flies and bees, it consists of constantly flying forward.
		 *
		 * @param height flying height
		 */
		public BeeMovement(float height) {
			this.height = height;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			return Collections.singleton(Utils.value(MOVE, 11, HEIGHT, height));
		}

		@Override
		public int getId() {
			return 38;
		}
	}
	
	public static class FlyingBirdMovement extends BaseMovement {
		private float glideDown;

		/** Constructs the flying bird movement which is used by all flying birds, it consists of flying. */
		public FlyingBirdMovement() {
			this.glideDown = -0.6F;
		}

		/** Velocity multiplier when gliding down. */
		public NativeComponent setDownFactor(float downFactor) {
			this.glideDown = downFactor;
			return this;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> move = new ArrayList<>();
			
			move.add(MOVE + ';' + 12);
			if (glideDown != -0.6F) move.add(";glideDown;" + glideDown);

			return move;
		}
	}
	
	public static class GallopMovement extends BasicMovement {
		float upRotSpeed;
		float gravityFactor;
		float[] fronts;
		float[] backs;


		/**
		 * Constructs the gallop movement which is used by wolves, foxes, squirrels and meerkats, it consists of galloping.
		 *
		 * @param speed movement speed
		 * @param bounce bouncing height
		 * @param upRot bouncing up speed
		 * @param gravFactor gravity multiplier
		 * @param fronts fronts of the models of each life stage
		 * @param backs backs of the models of each life stage
		 */
		public GallopMovement(float speed, float bounce, float upRot, float gravFactor, float[] fronts, float[] backs) {
			super(speed, 0, bounce, 13);
			this.upRotSpeed = upRot;
			this.gravityFactor = gravFactor;
			this.fronts = fronts;
			this.backs = backs;
		}

		@Override
		public Collection<String> load(CustomEntity entity) {
			List<String> move = new ArrayList<>(super.load(entity));

			if (fronts != null && backs != null && fronts.length == entity.getStages() && backs.length == entity.getStages()) {
				move.add(Utils.value("upRot", upRotSpeed, "gravFactor", gravityFactor));
				move.add("frontZ;" + fronts.length);
				for (float front : fronts) move.add(";" + front);
				move.add(";backZ;" + backs.length);
				for (float back : backs) move.add(";" + back);
			}

			return move;
		}
	}
	
	public static class SheepMovement extends BasicMovement {
		/**
		 * Constructs the sheep movement which is used by sheep, it consists of jumping high while walking.
		 *
		 * @param speed movement speed
		 * @param bounce bouncing height
		 * @param rotSpeed rotation speed
		 */
		public SheepMovement(float speed, float bounce, float rotSpeed) {
			super(speed, rotSpeed, bounce, 14);
		}
	}
	
	public static class WaddleMovement extends SheepMovement {

		/**
		 * Constructs the waddle movement which is used by bears and foxes, it consists of constantly jumping and rotating X axis.
		 *
		 * @param speed movement speed
		 * @param bounce bouncing height
		 * @param rotSpeed rotation speed
		 */
		public WaddleMovement(float speed, float bounce, float rotSpeed) {
			super(speed, bounce, rotSpeed);
			this.id = 15;
		}
	}

	/** Constructs the jellyfish movement which is used by jellyfish, it consists of swimming around then being idol for some seconds */
	public static class JellyfishMovement extends BaseMovement {
		@Override
		public Collection<String> load(CustomEntity entity) {
			return Collections.singleton(MOVE + ';' + 21);
		}
	}
	
	public static class DolphinMovement extends AxisMovement {

		/**
		 * Constructs the dolphin movement which is used by dolphins, it consists of jumping sometimes while swimming. <br>
		 * The rotation axis is defined by {@code rotationType}, it can be {@code 0}, {@code 1} or {@code 2}
		 * which represents the rotation axis that are X, Y and Z respectively.
		 * @param speed movement speed
		 * @param rotationType rotation axis
		 * @param minRot minimum rotation angle
		 * @param maxRot maximum rotation angle
		 * @param rotSpeed rotation speed
		 */
		public DolphinMovement(float speed, int rotationType, float minRot, float maxRot, float rotSpeed) {
			super(speed, rotationType, minRot, maxRot, rotSpeed);
			this.id = 45;
		}
	}
}
