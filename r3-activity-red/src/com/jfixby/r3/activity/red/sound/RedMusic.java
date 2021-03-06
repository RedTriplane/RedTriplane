
package com.jfixby.r3.activity.red.sound;

import com.jfixby.r3.activity.api.ComponentsFactory;
import com.jfixby.r3.activity.api.audio.Music;
import com.jfixby.r3.activity.red.RedComponentsFactory;
import com.jfixby.r3.engine.api.sound.AudioSample;
import com.jfixby.r3.engine.api.sound.SoundMachine;
import com.jfixby.r3.engine.api.sound.VocalEventState;
import com.jfixby.r3.engine.api.sound.Vocalizable;
import com.jfixby.scarabei.api.names.ID;

public class RedMusic implements Music, Vocalizable {

	@Override
	public ComponentsFactory getComponentsFactory () {
		return this.master;
	}

	private final ID asset_id;
	private final RedComponentsFactory master;
	private final int hashid;
	static int idspawner = 0;

	@Override
	public int hashCode () {
		return this.hashid;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedMusic other = (RedMusic)obj;
		if (this.hashid != other.hashid) {
			return false;
		}
		return true;
	}

	public RedMusic (final RedComponentsFactory master, final AudioSample data) {
		this.master = master;
		this.asset_id = data.getAssetID();
		this.hashid = idspawner++;
	}

	@Override
	public String toString () {
		return "Music[" + this.name + "] <" + this.asset_id + ">";
	}

	@Override
	public ID getAssetID () {
		return this.asset_id;
	}

	@Override
	public Music copy () {
		final Music event = this.master.getSoundFactory().newMusic(this.asset_id);

		// copy settings...

		return event;
	}

	VocalEventState state = new VocalEventState();
	private String name;

	@Override
	public void doVocalize () {
		SoundMachine.component().VocalizeMusic(this.asset_id, this, this.state);
	}

	@Override
	public void setName (final String name) {
		this.name = name;
	}

	@Override
	public String getName () {
		return this.name;
	}

	@Override
	public void setVolume (final float volume) {
		this.state.volume = volume;
	}

	@Override
	public float getVolume () {
		return this.state.volume;
	}

	@Override
	public float loopsComplete () {
		return this.state.loopsComplete;
	}

	@Override
	public void setLooping (final Boolean is_looped) {
		this.state.isLooping = is_looped;
	}

}
