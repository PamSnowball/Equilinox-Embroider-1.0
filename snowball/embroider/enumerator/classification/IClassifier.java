package com.snowball.embroider.enumerator.classification;

public interface IClassifier {
	String getClassification();
	
	default int getId() {
		return Integer.parseInt(this.getClassification().replaceAll("[^\\d.]", ""));
	}
	
	default String getType() {
		return this.getClassification().replaceAll("[0-9]", "");
	}

	default boolean needsSuperReplacement() {
		return false;
	}
}
