package com.snowball.embroider.component.architecture.data;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.util.component.Particle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Particles extends NativeComponent {
	Particle particle;
	
	float range;
	
	int[] stages = new int[] {};
	
	boolean material;

	/**
	 * Constructs the PARTICLE component which is used by some trees and flowers to set beautiful particles.
	 *
	 * @param particle particle type
	 * @param particleRange how far the camera renders the particles
	 * @param stages life stages in which particles are shown
	 * @param takesMaterial true if particle match material color of entity
	 */
	public Particles(Particle particle, float particleRange, int[] stages, boolean takesMaterial) {
		if (stages != null) this.stages = stages;
		this.material = takesMaterial;
		this.range = particleRange;
		this.particle = particle;
	}

	public Collection<String> load(CustomEntity entity) {
		List<String> particles = new ArrayList<>();
		
		if (particle != null) {
			particle.deathPrint(particles);
			
			particles.add(Utils.value(";range", range, "stages", stages.length));
			
			if (stages.length > 0) {
				for (int stage : stages) particles.add(stage + ";");
			} else {
				particles.add("0;");
			}
			
			particles.add(Utils.value("takesMaterial", material ? 1 : 0));
		}
		
		return particles;
	}
	

	@Override
	public int getId() {
		return 26;
	}
}
