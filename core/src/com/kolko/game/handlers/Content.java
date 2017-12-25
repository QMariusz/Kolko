package com.kolko.game.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Content {

	private HashMap<String, Texture> textures;
	
	private HashMap<String, Music> musics;
	private HashMap<String, Sound> sounds;
	
	public Content() {
		textures = new HashMap<String, Texture>();
		sounds = new HashMap<String, Sound>();
		musics = new HashMap<String, Music>();
	}
	
	public void loadTexture(String path, String key) {
		Texture tex = new Texture(Gdx.files.internal(path));
		textures.put(key, tex);
	}
	
	public void loadSound(String path, String key) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
		sounds.put(key, sound);
	}
	
	public void loadMusic(String path, String key) {
		Music sound = Gdx.audio.newMusic(Gdx.files.internal(path));
		musics.put(key, sound);
	}
	
	public void playSound(String key) {
		sounds.get(key).play(0.05f);
	}
	
	public void playMusic(String key) {
		Music music = musics.get(key);
		music.setVolume(0.05f);
		music.play();
		music.setLooping(true);
	}
	
	public Texture getTexture(String key) {
	return textures.get(key);
	}
	
	public void disposetexture(String key) {
		Texture tex = textures.get(key);
		if(tex != null) tex.dispose();
	}
	
	public void disposesound(String key) {
		Sound tex = sounds.get(key);
		if(tex != null) tex.dispose();
	}
	
	public void disposemusic(String key) {
		Music tex = musics.get(key);
		if(tex != null) tex.dispose();
	}
}
