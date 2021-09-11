package com.snowball.embroider.util.component;

import com.snowball.embroider.util.Utils;
import com.snowball.embroider.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Particle {
	boolean isColour = false;
	boolean isAdditive;
	boolean isCircle;
	boolean hasDir;
	
	float[] particleArray;
	float radius = 0.0F;
	
	int atlas;
	
	Object[] hasRot;
	
	Vector v = new Vector(0, 0, 0);
	Vector colour;
	
	public Particle(float[] particle, int atlas, Object[] hasRotation) {
		this.atlas = atlas;
		if (particle.length == 11) this.particleArray = particle;
		else if (particle.length == 15)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public Particle(float[] particle, Vector colour, boolean isAdditive, Object[] hasRotation) {
		this.isColour = true;
		this.isAdditive = isAdditive;
		this.colour = colour;
		if (particle.length == 14) this.particleArray = particle;
		else if (particle.length == 18)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public Particle(float radius, float[] particle, int atlas, Object[] hasRotation) {
		this.radius = radius;
		this.atlas = atlas;
		if (particle.length == 11) this.particleArray = particle;
		else if (particle.length == 15)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public Particle(float radius, float[] particle, Vector colour, boolean isAdditive, Object[] hasRotation) {
		this.isColour = true;
		this.isAdditive = isAdditive;
		this.colour = colour;
		this.radius = radius;
		if (particle.length == 14) this.particleArray = particle;
		else if (particle.length == 18)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public Particle(float radius, Vector coordinates, float[] particle, int atlas, Object[] hasRotation) {
		this.isCircle = true;
		this.radius = radius;
		this.v = coordinates;
		this.atlas = atlas;
		if (particle.length == 11) this.particleArray = particle;
		else if (particle.length == 15)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public Particle(boolean isCircle, float radius, Vector coordinates, float[] particle, Vector colour, boolean isAdditive, 
			Object[] hasRotation) {
		this.isColour = true;
		this.isAdditive = isAdditive;
		this.isCircle = isCircle;
		this.colour = colour;
		this.radius = radius;
		this.v = coordinates;
		if (particle.length == 14) this.particleArray = particle;
		else if (particle.length == 18)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public void deathPrint(List<String> component) {
		List<String> death = new ArrayList<>();

		String color = "isColour";
		if (Utils.check(0.0F, radius, v.getX(), v.getY(), v.getZ())) death.add(Utils.value(color, isColour ? 1 : 0, "spawn;0"));
		else if (Utils.check(0.0F, v.getX(), v.getY(), v.getZ())) death.add(Utils.value(color, isColour ? 1 : 0, "spawn;1"));
		else death.add(Utils.value(color, isColour ? 1 : 0, "spawn", isCircle ? 3 : 2, radius, v.value()));
		death.add(Utils.value("pps", particleArray[0], "speed", particleArray[1], "gravity", particleArray[2], "life", particleArray[3]));
		death.add(Utils.value("scale", particleArray[4]));
		if (isColour) {
			death.add(Utils.value("colour", colour.value(), "additive", isAdditive ? 1 : 0));
			death.add(Utils.value("alpha", particleArray[5], "fadeIn", particleArray[6], "fadeOut", particleArray[7]));
		} else death.add(Utils.value("atlas", atlas));
		loadArray(death);
		
		Utils.append(death, component);
	}
	
	private void loadArray(List<String> death) {
		int j = 6;
		
		death.add(Utils.value("hasDir", hasDir ? 1 : 0));
		if (hasDir) {
			if (isColour) j = 9;
			death.add(Utils.value("direction", particleArray[j], particleArray[j+1], particleArray[j+2], "deviation", particleArray[j+3]));
			j += 4;
		}
		
		death.add(Utils.value("offset", particleArray[j], particleArray[j+1], particleArray[j+2]));
		death.add(Utils.value("lifeError", particleArray[j+3], "scaleError", particleArray[j+4], "speedError", particleArray[j+5]));
		death.add(Utils.value("hasRot", (boolean) hasRot[0] ? 1 : 0));
		if (hasRot[1] != null) death.add(Utils.value(";hasXRot;1;rotation", hasRot[1]));
		else death.add(";hasXRot;0");
	}
}