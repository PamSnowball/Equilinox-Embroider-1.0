package com.snowball.embroider.util.component;

import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Breed {
	int parentId = -1;
	int count;
	
	float maturity;
	float time;
	
	CompRequirement[] requirements;
	
	public Breed(float breedMaturity, float averageBreedTime) {
		this.maturity = breedMaturity;
		this.time = averageBreedTime;
	}
	
	public Breed(float breedMaturity, float averageBreedTime, IClassifier classification, int evolveLength, CompRequirement[] requirements) {
		this.maturity = breedMaturity;
		this.time = averageBreedTime;
		this.parentId = classification.getId();
		this.count = evolveLength;
		this.requirements = requirements;	
	}
	
	public Collection<String> breed() {
		List<String> breed = new ArrayList<>();
		
		breed.add(Utils.value("breedMat" + maturity + "breedTime" + time + "parentId" + parentId));
		if (parentId >= 0) {
			breed.add("time;" + count + ';');
			for (CompRequirement requirement : requirements) {
				breed.addAll(requirement.req());
				breed.add(";");
			}
		}

		return breed;
	}
}
