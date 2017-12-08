package com.kolko.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kolko.game.handlers.Content;
import com.kolko.game.handlers.GameStateManager;

public class Application extends ApplicationAdapter {
	
	public static final int WIDTH = 960;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Kolka-Game";
	
	private static final float STEP = 1/60f;
	private float accum;
	
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private GameStateManager gsm;
	
	public static Content res;
	
	public void create() {
		
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		res = new Content();
		res.loadTexture("res/images/kolo.png", "kolo");
		
		gsm = new GameStateManager(this);
	}
	
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}
	
	public void dispose() {
		
	}
	
	public SpriteBatch getSpriteBatch() { return batch; }
	public OrthographicCamera getCamera() { return cam; }
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}
}
