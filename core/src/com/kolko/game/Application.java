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
	public static final int HEIGHT = 640;
	public static final String TITLE = "Kolka-Game";
	
	private static final float STEP = 1/120f;
	private float accum;
	
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	private GameStateManager gsm;
	
	public static Content res;
	
	public void create() {
		
	//	Gdx.input.setInputProcessor(new MyInputProcessor());
		
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, WIDTH, HEIGHT);
		
		res = new Content();
		res.loadTexture("res/images/player.png", "kolo");
		res.loadTexture("res/images/s_circle.png", "s_circle");
		res.loadTexture("res/images/m_circle.png", "m_circle");
		res.loadTexture("res/images/g_circle.png", "g_circle");
		res.loadTexture("res/images/b_circle.png", "b_circle");
		res.loadTexture("res/images/b_square.png", "b_square");
		res.loadTexture("res/images/s_square.png", "s_square");
		res.loadTexture("res/images/m_square.png", "m_square");
		res.loadTexture("res/images/background4.png", "background");
		res.loadTexture("res/images/background1.png", "background2");
		res.loadTexture("res/images/background2.png", "background3");
		res.loadTexture("res/images/background3.png", "background4");
		res.loadTexture("res/images/background5.png", "background5");
		res.loadTexture("res/images/background6.png", "background6");
		res.loadTexture("res/images/background7.png", "background7");
		res.loadTexture("res/images/background8.png", "background8");

		res.loadTexture("res/images/endStage.png", "end_stage");
		res.loadTexture("res/images/upgrades.png", "upgrade");
		res.loadTexture("res/images/button_up.png", "button_up");
		res.loadTexture("res/images/button_down.png", "button_down");
		res.loadTexture("res/images/reset.png", "resetImage");

		res.loadTexture("res/images/orbs.png", "orbs");
		
		res.loadTexture("res/images/bomb.png", "bomb");
		res.loadTexture("res/images/turbo.png", "turbo");
		res.loadTexture("res/images/bird.png", "bird");
		res.loadTexture("res/images/power.png", "power");
		res.loadTexture("res/images/points.png", "points");
		res.loadTexture("res/images/regen.png", "regen");
		
		res.loadSound("res/music/click.mp3", "click");
		res.loadSound("res/music/treeclick.mp3", "tree");
		res.loadMusic("res/music/Soliloquy.mp3", "sololoquy");
		
		res.playMusic("sololoquy");
		
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
