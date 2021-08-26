package com.snowball.embroider.util.component;

import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Breed {
	int parentId = -1;
	int count;
	
	float maturity;
	float time;
	
	IRequirement[] requirements;
	
	public Breed(float breedMaturity, float averageBreedTime) {
		this.maturity = breedMaturity;
		this.time = averageBreedTime;
	}
	
	public Breed(float breedMaturity, float averageBreedTime, int parentId, int evolveLength, IRequirement[] requirements) {
		this.maturity = breedMaturity;
		this.time = averageBreedTime;
		this.parentId = parentId;
		this.count = evolveLength;
		this.requirements = requirements;	
	}
	
	public Collection<String> breed() {
		List<String> breed = new ArrayList<>();
		
		breed.add(Utils.value("breedMat" + maturity + "breedTime" + time + "parentId" + parentId));
		if (parentId >= 0) {
			breed.add("time;" + count + ';');
			for (IRequirement requirement : requirements) {
				breed.addAll(requirement.requirement());
				breed.add(";");
			}
		}

		return breed;
	}
}
