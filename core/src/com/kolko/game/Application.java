package com.kolko.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kolko.game.handlers.Content;
import com.kolko.game.handlers.GameStateManager;
import com.kolko.game.handlers.MyInput;
import com.kolko.game.handlers.MyInputProcessor;

public class Application extends ApplicationAdapter {
	
	public static final int WIDTH = 960;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Kolka-Game";
	
	private static final float STEP = 1/60f;
	private float accum;
	
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	private GameStateManager gsm;
	
	public static Content res;
	
	public void create() {
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, WIDTH, HEIGHT);
		
		res = new Content();
		res.loadTexture("res/images/kolo.png", "kolo");
		res.loadTexture("res/images/s_circle.png", "s_circle");
		res.loadTexture("res/images/m_circle.png", "m_circle");
		res.loadTexture("res/images/g_circle.png", "g_circle");
		res.loadTexture("res/images/s_square.png", "s_square");
		res.loadTexture("res/images/m_square.png", "m_square");
		res.loadTexture("res/images/background4.png", "background");
		
		gsm = new GameStateManager(this);
	}
	
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			MyInput.update();
		}
	}
	
	public void dispose() {
		
	}
	
	public SpriteBatch getSpriteBatch() { return batch; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudCam; }
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}
}
