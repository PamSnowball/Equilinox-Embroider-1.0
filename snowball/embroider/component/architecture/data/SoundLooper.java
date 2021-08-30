package com.snowball.embroider.component.architecture.data;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.CustomEntity;
import com.snowball.embroider.util.component.CompSound;
import com.snowball.embroider.util.Utils;

import java.util.Collection;
import java.util.Collections;

public class SoundLooper extends NativeComponent {
	CompSound sound;
	
	float volume;
	float range;

	/**
	 * Constructs the SOUND_LOOPER component which is used by hives to have that buzz sound.
	 *
	 * @param sound Sound played
	 * @param range range of sound
	 * @param volume sounds volume multiplier
	 * @see RandomSounder.SoundFile
	 */
	public SoundLooper(CompSound sound, float range, float volume) {
		this.volume = volume;
		this.range = range;
		this.sound = sound;
	}
	
	@Override
	public Collection<String> load(CustomEntity entity) {
		sound.loadSound();
		return Collections.singleton(Utils.value("SOUND_LOOPER;sound", sound.getSound(), "range", range, "volume", volume));
	}
	
	@Override
	public int getId() {
		return 36;
	}
}
