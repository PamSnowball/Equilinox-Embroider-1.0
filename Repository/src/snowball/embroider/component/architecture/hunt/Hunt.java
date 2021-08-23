package snowball.embroider.component.architecture.hunt;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.classification.IClassifier;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hunt extends NativeComponent {
	boolean young;
	boolean old;
	
	IClassifier[] classifications;
	
	int range = 9;
	
	public Hunt(int range, IClassifier[] classifications, boolean huntsYoung, boolean huntsOld) {
		this.classifications = classifications;
		if (range > 0) this.range = range;
		this.young = huntsYoung;
		this.old = huntsOld;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		List<String> hunt = new ArrayList<>();
		
		hunt.add(Utils.value("HUNT;range", range, "count", classifications.length, "prey"));
	
		for (IClassifier classification : classifications) hunt.add(classification.getClassification());
	
		hunt.add((young ? 1 : 0) + ";" + (old ? 1 : 0));

		return hunt;
	}

	@Override
	public int getId() {
		return 6;
	}
}
