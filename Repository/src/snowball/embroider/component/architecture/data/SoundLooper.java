package snowball.embroider.component.architecture.data;

import snowball.embroider.component.NativeComponent;
import snowball.embroider.entity.Entity;
import snowball.embroider.enumerator.sound.ISound;
import snowball.embroider.utils.Utils;

import java.util.Collection;
import java.util.Collections;

public class SoundLooper extends NativeComponent {
	ISound sound;
	
	float volume;
	float range;
	
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
