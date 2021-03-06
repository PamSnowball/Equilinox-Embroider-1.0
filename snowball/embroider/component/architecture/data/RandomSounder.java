package com.snowball.embroider.component.architecture.data;

import com.snowball.embroider.component.NativeComponent;
import com.snowball.embroider.entity.CustomEntity;
import com.snowball.embroider.util.Utils;
import com.snowball.embroider.util.component.CompSound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RandomSounder extends NativeComponent {
	public static class SoundFile {
		CompSound sound;
		float range;

		/**
		 * Range of sound guide: <br>
		 * sounds made by small creatures have five to six range; <br>
		 * sounds made by medium creatures have eight range; <br>
		 * sheep baa has ten range; <br>
		 * bear growl has fifteen range; <br>
		 * eagle screech has seventeen range; <br>
		 *
		 * @param sound sound played
		 * @param range range of sound
		 */
		public SoundFile(CompSound sound, float range) {
			this.sound = sound;
			this.range = range;
		}
	}

	CompSound[] sounds;

	float[] ranges;

	float volume = 0;
	float random = 0;
	float time = 0;
	
	int stage = 0;

	/** @param volume sounds volume multiplier */
	public RandomSounder setVolume(float volume) {
		this.volume = volume;
		return this;
	}

	/** @param stage stage required to start making sounds */
	public RandomSounder setStageRequirement(int stage) {
		this.stage = stage;
		return this;
	}

	/**
	 * Constructs the RANDOM_SOUNDER component which is used by many animals to make them do sounds. <br>
	 * MUST have SOUND component.
	 *
	 * @param waitTime time in seconds sound loops
	 * @param random random extra time added to {@code waitTime}
	 * @param sounds sounds played
	 */
	public RandomSounder(float waitTime, float random, SoundFile... sounds) {
		if (waitTime + random > 0) {
			this.time = waitTime;
			this.random = random;
		}

		if (sounds != null) {
			CompSound[] sound = new CompSound[sounds.length];
			float[] range = new float[sounds.length];

			for (int i = 0; i < sounds.length; i++) {
				sounds[i].sound.loadSound();

				sound[i] = sounds[i].sound;
				range[i] = sounds[i].range;
			}

			this.sounds = sound;
			this.ranges = range;
		}
	}

	public Collection<String> load(CustomEntity entity) {
		List<String> sounder = new ArrayList<>();

		if (entity.hasComponent(Sound.class) && ranges != null && sounds != null) {
			sounder.add(Utils.value("RANDOM_SOUNDER", time, random, sounds.length));

			for (int i = 0; i < sounds.length; i++) {
				sounder.add(sounds[i].getSound() + ';');
				sounder.add(ranges[i] + ";");
			}

			if (volume != 0) sounder.add(volume + ";");
			if (stage > 0) sounder.add(stage + ";");
		}

		return sounder;
	}

	@Override
	public int getId() {
		return 0;
	}
}
