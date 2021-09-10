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

	/**
	 * Sets breeding info. <br>
	 * Average breed time is highly affected by population and entity age.
	 *
	 * @param breedMaturity breeding maturity
	 * @param averageBreedTime average breeding time.
	 */
	public Breed(float breedMaturity, float averageBreedTime) {
		this.maturity = breedMaturity;
		this.time = averageBreedTime;
	}

	public Breed setEvolves(IClassifier classification, int evolveLength, CompRequirement... requirements) {
		this.parentId = classification.getId();
		this.count = evolveLength;
		this.requirements = requirements;
		return this;
	}
	
	public Collection<String> breed() {
		List<String> breed = new ArrayList<>();
		
		breed.add(Utils.value("breedMat", maturity, "breedTime", time, "parentId", parentId));
		if (parentId > 0) {
			breed.add("count;" + count + ";reqs;" + requirements.length + ';');
			for (CompRequirement requirement : requirements) {
				breed.addAll(requirement.req());
			}
		}

		return breed;
	}
}
