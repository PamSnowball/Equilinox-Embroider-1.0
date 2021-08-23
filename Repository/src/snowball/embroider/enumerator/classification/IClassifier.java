package snowball.embroider.enumerator.classification;

public interface IClassifier {
	String getClassification();
	
	default int getId() {
		return Integer.parseInt(this.getClassification().replace("[\\D]", ""));
	}
	
	default String getType() {
		return this.getClassification().replace("[0-9]", "");
	}

	default boolean needsSuperReplacement() {
		return false;
	}
}
