package com.snowball.embroider.component.architecture;

import com.snowball.embroider.component.IComponent;
import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.Entity;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		public Collection<String> load(Entity entity) {
			return new ArrayList<>();
		}

		@Override
		public int getId() {
			return 38;
		}
	}

	public static class BasicMovement extends BaseMovement {
		float speed;
		float bouncePower;
		int id;
		
		public BasicMovement(float speed, float bounce, int id) {
			this.speed = speed;
			this.bouncePower = bounce;
			this.id = id;
		}

		@Override
		public Collection<String> load(Entity entity) {
			return Collections.singleton(MOVE + ";" + id + ";");
		}
	}
	
	public static class FrogMovement extends BasicMovement {
		float waitTime;
		float bounciness;
		
		public FrogMovement(float speed, float bounce, float wait, float bounciness) {
			super(speed, bounce, 6);
			this.waitTime = wait;
			this.bounciness = bounciness;
		}
		
		@Override
		public Collection<String> load(Entity entity) {
			List<String> move = new ArrayList<>(super.load(entity));
			
			move.add(Utils.value(SPEED, speed, BOUNCE, bouncePower, "wait", waitTime, "bounciness", bounciness));
			
			return move;
		}
	}
	
	public static class RabbitMovement extends BasicMovement {
		float upRotSpeed;
		float downRotSpeed;
		float front0;
		float front1;
		float back0;
		float back1;
		
		public RabbitMovement(float speed, float bounce, float upRot, float downRot, float front0, float front1, float back0, float back1) {
			super(speed, bounce, 7);
			this.upRotSpeed = upRot;
			this.downRotSpeed = downRot;
			this.front0 = front0;
			this.front1 = front1;
			this.back0 = back0;
			this.back1 = back1;
		}

		@Override
		public Collection<String> load(Entity entity) {
			List<String> move = new ArrayList<>(super.load(entity));
			
			move.add(Utils.value(SPEED, speed, BOUNCE, bouncePower, "upRot", upRotSpeed, "downRot", downRotSpeed));
			move.add(Utils.value("frontZ", 2, front0, front1, "backZ", 2, back0, back1));
			
			return move;
		}
	}
	
	public static class FlouncerMovement extends BasicMovement {
		float rotationSpeed;
		float bounceRotation;
		float height;
		
		public FlouncerMovement(float speed, float rotSpeed, float bounce, float bounceRot, float height) {
			super(speed, bounce, 8);
			this.rotationSpeed = rotSpeed;
			this.bounceRotation = bounceRot;
			this.height = height;
		}

		@Override
		public Collection<String> load(Entity entity) {
			List<String> move = new ArrayList<>(super.load(entity));
			
			move.add(Utils.value(SPEED, speed, ROT, rotationSpeed, BOUNCE, bouncePower, "bounceRot", bounceRotation, HEIGHT, height));

			return move;
		}
	}
	
	public static class BaseWalkingMovement extends BaseMovement {
		float speed;
		int xRot;
		float minRot;
		float maxRot;
		float rotationSpeed;
		int id;
		
		public BaseWalkingMovement(float speed, int xRot, float minRot, float maxRot, float rotSpeed) {
			this.speed = speed;
			this.xRot = xRot;
			this.minRot = minRot;
			this.maxRot = maxRot;
			this.rotationSpeed = rotSpeed;
		}
		
		@Override
		public Collection<String> load(Entity entity) {
			return Collections.singleton(Utils.value(MOVE, 9, SPEED, speed, "xRot", xRot, "minRot", minRot, "maxRot", maxRot, ROT, rotationSpeed));
		}
	}
	
	public static class SwimmerMovement extends BaseWalkingMovement {
		boolean eggStage;
		
		float swimHeight;
		float swimFactor;
		
		float swimInertia = 0.25F;
		
		public SwimmerMovement(float speed, int xRot, float minRot, float maxRot, float rotSpeed, float height, boolean hasEgg, float swimFactor) {
			super(speed, xRot, minRot, maxRot, rotSpeed);
			this.swimHeight = height;
			this.eggStage = hasEgg;
			this.swimFactor = swimFactor;
		}
		
		public IComponent setInertia(float inertia) {
			this.swimInertia = inertia;
			return this;
		}
		
		@Override
		public Collection<String> load(Entity entity) {
			return Collections.singleton(Utils.value(HEIGHT, swimHeight, "hasEgg", (eggStage ? 1 : 0), "swimFactor", swimFactor, "swimInnertia" + swimInertia));
		}
	}
	
	public static class ButterflyMovement extends BaseMovement {
		@Override
		public Collection<String> load(Entity entity) {
			return Collections.singleton(MOVE + ';' + 10);
		}
	}
	
	public static class BeeMovement extends BaseMovement {
		float height;
		
		public BeeMovement(float height) {
			this.height = height;
		}

		@Override
		public Collection<String> load(Entity entity) {
			return Collections.singleton(Utils.value(MOVE, 11, HEIGHT, height));
		}

		@Override
		public int getId() {
			return 38;
		}
	}
	
	public static class FlyingBirdMovement extends BaseMovement {
		private final float glideDown;
		
		public FlyingBirdMovement(float glideDown) {
			this.glideDown = glideDown;
		}
		
		public FlyingBirdMovement() {
			this.glideDown = -0.6F;
		}

		@Override
		public Collection<String> load(Entity entity) {
			List<String> move = new ArrayList<>();
			
			move.add(MOVE + ';' + 11);
			if (glideDown != -0.6F) move.add("glideDown" + glideDown);

			return move;
		}
	}
	
	public static class GallopMovement extends BasicMovement {
		float upRotSpeed;
		float gravityFactor;
		float front0;
		float front1;
		float back0;
		float back1;
		
		public GallopMovement(float speed, float bounce, float upRot, float gravFactor, float front0, float front1, float back0, float back1) {
			super(speed, bounce, 13);
			this.upRotSpeed = upRot;
			this.gravityFactor = gravFactor;
			this.front0 = front0;
			this.front1 = front1;
			this.back0 = back0;
			this.back1 = back1;
		}

		@Override
		public Collection<String> load(Entity entity) {
			List<String> move = new ArrayList<>(super.load(entity));
			
			move.add(Utils.value(SPEED, speed, BOUNCE, bouncePower, "upRot", upRotSpeed, "gravFactor", gravityFactor));
			move.add(Utils.value("frontZ", 2, front0, front1, "backZ", 2, back0, back1));

			return move;
		}
	}
	
	public static class SheepMovement extends BasicMovement {
		float rotationSpeed;
		
		public SheepMovement(float speed, float bounce, float rotSpeed) {
			super(speed, bounce, 14);
			this.rotationSpeed = rotSpeed;
		}

		@Override
		public Collection<String> load(Entity entity) {
			return Stream.concat(super.load(entity).stream(), Stream.of(Utils.value(SPEED, speed, ROT, rotationSpeed, BOUNCE, bouncePower))).collect(Collectors.toList());
		}
	}
	
	public static class WaddleMovement extends SheepMovement {
		BasicMovement movement;
		
		public WaddleMovement(float speed, float bounce, float rotSpeed) {
			super(speed, bounce, rotSpeed);
			this.id = 15;
		}
	}
	
	public static class JellyfishMovement extends BaseMovement {
		@Override
		public Collection<String> load(Entity entity) {
			return Collections.singleton(MOVE + ';' + 21);
		}
	}
	
	public static class DolphinMovement extends BaseWalkingMovement {
		public DolphinMovement(float speed, int xRot, float minRot, float maxRot, float rotSpeed) {
			super(speed, xRot, minRot, maxRot, rotSpeed);
			this.id = 45;
		}
	}
}
