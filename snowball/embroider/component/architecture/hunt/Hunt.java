package com.snowball.embroider.component.architecture.hunt;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.enumerator.classification.IClassifier;
import com.snowball.embroider.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hunt extends NativeComponent {
	boolean young;
	boolean old;
	
	IClassifier[] classifications;
	
	int range;

	/**
	 * Constructs the HUNT component which is used by foxes and wolves to set terrestrial hunt AI.
	 *
	 * @param range range of entity detection
	 * @param classifications targets
	 * @param huntsYoung if true, entity hunts animals that are in the first half of their lives
	 * @param huntsOld if true, entity hunts animals that are in the last half of their lives
	 */
	public Hunt(int range, IClassifier[] classifications, boolean huntsYoung, boolean huntsOld) {
		this.classifications = classifications;
		this.range = Math.max(range, 1);
		this.young = huntsYoung;
		this.old = huntsOld;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		List<String> hunt = new ArrayList<>();
		
		hunt.add(Utils.value("HUNT;range", range, "count", classifications.length, "prey"));
	
		for (IClassifier classification : classifications) hunt.add(classification.getClassification());
	
		hunt.add((young ? 1 : 0) + ";" + (old ? 1 : 0));

		return hunt;
	}

	@Override
	public int getId() {
		return 5;
	}
}
