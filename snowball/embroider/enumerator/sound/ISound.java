package com.snowball.embroider.enumerator.sound;

import com.snowball.embroider.component.blueprint.CustomSound;

public interface ISound {
	String getSound();
	
	CustomSound loadSound();
}
