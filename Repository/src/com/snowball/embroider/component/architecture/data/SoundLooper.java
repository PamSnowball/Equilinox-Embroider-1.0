package com.snowball.embroider.component.architecture.data;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.Entity;
import com.snowball.utils.Utils;
import com.snowball.embroider.enumerator.sound.ISound;

import java.util.Collection;
import java.util.Collections;

public class SoundLooper extends NativeComponent {
	ISound sound;
	
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
	public SoundLooper(ISound sound, float range, float volume) {
		this.volume = volume;
		this.range = range;
		this.sound = sound;
	}
	
	@Override
	public Collection<String> load(Entity entity) {
		sound.loadSound().loadSound();
		return Collections.singleton(Utils.value("SOUND_LOOPER;sound", sound.getSound(), "range", range, "volume", volume));
	}
	
	@Override
	public int getId() {
		return 35;
	}
}