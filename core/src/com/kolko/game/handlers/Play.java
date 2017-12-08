package com.kolko.game.handlers;

import static com.kolko.game.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kolko.game.Application;
import com.kolko.game.entities.Player;
import com.kolko.game.states.GameState;

public class Play extends GameState{

	private World world;
	private OrthographicCamera b2dCam;
	
	private Player player;
	
	protected Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.82f), true);
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Application.WIDTH / PPM, Application.HEIGHT / PPM);
		createPlayer();
	}

	private void createPlayer() {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape cshape = new CircleShape();
		
		bdef.position.set(100 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(1f, 0);
		Body body = world.createBody(bdef);
		
		cshape.setRadius(100/ PPM);
		fdef.shape = cshape;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_CRYSTALS;
//		fdef.restitution = 1f;
		body.createFixture(fdef).setUserData("player");
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(13 / PPM,  2 / PPM);
		fdef.shape = shape;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
		
		
		player = new Player(body);
		
		//cicrular Data
		body.setUserData(player);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		
		player.update(dt);
		
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.position.set(player.getPosition().x * PPM + Application.WIDTH/4, Application.HEIGHT /2 , 0);
		cam.update();
		
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
