package com.snowball.embroider.util.component;

import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Death {
	public static class Fade implements CompDeath {
		float fadeTime;
			
		public Fade(float fadeTime) {
			this.fadeTime = fadeTime;
		}

		public Collection<String> death() {
			return Collections.singleton(Utils.value("FADE_DEATH", fadeTime + ';'));
		}
	}
	
	public static class Fall implements CompDeath {
		Particle particle;
		
		boolean hasParticleEffect = false;
		boolean useEntityColour;
		
		float explodeTime;
		float fallTime;
		float totalTime;
		float angle;
		
		int[] modelCount;
				
		public Fall(float fallTime, float totalTime, float fallAngle) {
			this.fallTime = fallTime;
			this.totalTime = totalTime;
			this.angle = fallAngle;
		}

		public Fall setParticle(float explodeTime, Particle particle, boolean useEntityColour) {
			this.explodeTime = explodeTime;
			this.useEntityColour = useEntityColour;
			this.particle = particle;
			hasParticleEffect = true;
			return this;
		}

		public Collection<String> death() {
			List<String> death = new ArrayList<>();
			
			death.add(Utils.value("FALL_DEATH", "fallTime", fallTime, "totalTime", totalTime, "fallAngle", angle, "hasParticle", hasParticleEffect ? 1 : 0));
			if (hasParticleEffect) {
				death.add(Utils.value("explodeTime", explodeTime, "useMaterial", useEntityColour ? 1 : 0));
				particle.deathPrint(death);
				death.add(Utils.value("Stages", modelCount.length));
				for (int model : modelCount) death.add(Utils.value(model));
			}

			return death;
		}
	}
	
	public static class Float implements CompDeath {
		float deathRot = 180;
		
		public Float() {}
		
		public Float(float deathRotation) {
			this.deathRot = deathRotation;
		}
			
		public Collection<String> death() {
			return Collections.singleton("FLOAT_DEATH;deathRot;" + deathRot + ';');
		}
	}

	public static class Spawn implements CompDeath {
		boolean growth;
		int min;
		int max;
		int id;
			
		public Spawn(int entityId, int minCount, int maxCount, boolean growth) {
			this.growth = growth;
			this.min = minCount;
			this.max = maxCount;
			this.id = entityId;
		}
			
		public Collection<String> death() {
			return Collections.singleton(Utils.value(id, min, max, growth ? 1 : 0));
		}
	}
	
	public static class UpDown implements CompDeath {
		Particle particle;
		
		float speed;
		
		public UpDown(float speed, Particle particle) {
			this.speed = speed;
			this.particle = particle;
		}
			
		public Collection<String> death() {
			List<String> death = new ArrayList<>();
			
			death.add(Utils.value("UP_DOWN_DEATH", "speed", speed));
			particle.deathPrint(death);

			return death;
		}
	}
	
	public static class ParticleDeath implements CompDeath {
		Particle particle;
		
		public ParticleDeath(Particle particle) {
			this.particle = particle;
		}
		
		public Collection<String> death() {
		 	List<String> death = new ArrayList<>();
			
			death.add("PARTICLE_DEATH;");
			particle.deathPrint(death);
			
			return death;
		}
	}
}
