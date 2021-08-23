package snowball.embroider.component.architecture.data;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.sound.ISound;
import snowball.embroider.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RandomSounder extends NativeComponent {
	public static class SoundFile {
		ISound sound;
		float range;

		public SoundFile(ISound sound, float range) {
			this.sound = sound;
			this.range = range;
		}
	}

	ISound[] sounds;

	float[] ranges;

	float volume = 0;
	float random = 0;
	float time = 0;
	
	int stage = 0;
	
	public RandomSounder(float waitTime, float random, SoundFile[] sounds) {
		if (waitTime + random > 0) {
			this.time = waitTime;
			this.random = random;
		}

		if (sounds != null) {
			ISound[] sound = new ISound[sounds.length];
			float[] range = new float[sounds.length];

			for (int i = 0; i < sounds.length; i++) {
				sounds[i].sound.loadSound().loadSound();

				sound[i] = sounds[i].sound;
				range[i] = sounds[i].range;
			}

			this.sounds = sound;
			this.ranges = range;
		}
	}
	
	public RandomSounder(float waitTime, float random, SoundFile[] sounds, int stageRequirement) {
		if (waitTime + random > 0) {
			this.time = waitTime;
			this.random = random;
		}
		
		if (stageRequirement > 0) this.stage = stageRequirement;

		if (sounds != null) {
			ISound[] sound = new ISound[sounds.length];
			float[] range = new float[sounds.length];

			for (int i = 0; i < sounds.length; i++) {
				sounds[i].sound.loadSound().loadSound();

				sound[i] = sounds[i].sound;
				range[i] = sounds[i].range;
			}

			this.sounds = sound;
			this.ranges = range;
		}
	}
	
	public RandomSounder(float waitTime, float random, SoundFile[] sounds, int stageRequirement, float volume) {
		if (waitTime + random > 0) {
			this.time = waitTime;
			this.random = random;
		}
		
		if (stageRequirement > 0) this.stage = stageRequirement;

		if (sounds != null) {
			ISound[] sound = new ISound[sounds.length];
			float[] range = new float[sounds.length];

			for (int i = 0; i < sounds.length; i++) {
				sounds[i].sound.loadSound().loadSound();

				sound[i] = sounds[i].sound;
				range[i] = sounds[i].range;
			}

			this.sounds = sound;
			this.ranges = range;
		}

		if (volume > 0.1F) this.volume = volume;
	}
	
	public Collection<String> load(Entity entity) {
		List<String> sounder = new ArrayList<>();

		if (ranges != null && sounds != null) {
			sounder.add(Utils.value("RANDOM_SOUNDER", time, random, sounds.length));

			for (int i = 0; i < sounds.length; i++) {
				sounder.add(sounds[i].getSound()  + ';');
				sounder.add(ranges[i] + ";");
			}
		
			if (volume != 0) sounder.add(volume + ";");
			if (stage != 0) sounder.add(stage + ";");
		}
		
		return sounder;
	}

	@Override
	public int getId() {
		return 2;
	}
}
