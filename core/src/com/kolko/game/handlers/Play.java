package com.kolko.game.handlers;

import static com.kolko.game.handlers.B2DVars.PPM;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kolko.game.Application;
import com.kolko.game.alies.BCircle;
import com.kolko.game.alies.MCircle;
import com.kolko.game.alies.SCircle;
import com.kolko.game.enemies.BSquare;
import com.kolko.game.enemies.MSquare;
import com.kolko.game.enemies.SSquare;
import com.kolko.game.entities.B2DSprite;
import com.kolko.game.entities.HUD;
import com.kolko.game.entities.Player;
import com.kolko.game.neutral.GreyCircle;
import com.kolko.game.powerups.Bird;
import com.kolko.game.powerups.Bomb;
import com.kolko.game.powerups.Points;
import com.kolko.game.powerups.Power;
import com.kolko.game.powerups.Regen;
import com.kolko.game.powerups.Turbo;
import com.kolko.game.states.GameState;

public class Play extends GameState{

	private World world;
	private Box2DDebugRenderer b2dr;
	
	private OrthographicCamera b2dCam;
	private MyContactListener c1;
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private B2DSprite test;
	private Stage stage;
	private Player player;
	private SCircle s_circle;
	private float tileSize;
	private Body b, body;
	private MCircle m_circle;
	private BCircle b_circle;
	private GreyCircle g_circle;
	private SSquare s_square;
	private MSquare m_square;
	private BSquare b_square;
	private Array<B2DSprite> aElements;
	private HUD hud;
	private Texture background, background2, background3, background4, background5, background6, background7,background8;
	private int sourceX = 0;
	private boolean PLAY = true;
	private float playerPosition;
	private int i, j;
	private Random random;
	private BodyDef bdefToCreate;
	private FixtureDef fdefToCreate;
	private CircleShape cshapeToCreate;
	private PolygonShape pshapeToCreate;
	
	private boolean debug = false;
	
	protected Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.82f), true);
		c1 = new MyContactListener();
		world.setContactListener(c1);
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Application.WIDTH / PPM, Application.HEIGHT / PPM);
		b2dr = new Box2DDebugRenderer();
		stage = new Stage(new StretchViewport(Application.WIDTH, Application.HEIGHT, hudCam));
		aElements= new Array<B2DSprite>();
		random = new Random();
		createPlayer();
		bdefToCreate = new BodyDef();
		cshapeToCreate = new CircleShape();
		fdefToCreate = new FixtureDef();
		pshapeToCreate = new PolygonShape();
		
		
		hud = new HUD(player, stage, this);
		
		createTiles();
	
		createGround();
		
		createElements();
		
		createBackground();
		}

	private void createElements() {
	
		i = random.nextInt(100)+1;
		j = random.nextInt(500)+1;

		System.out.println(i);
		//99-88(96)
		if(i>(99-3*hud.getUpgradePlayer().getlMedium())) {
			createMCircle();
	//		System.out.println("medium");
		}
		
		//89-63(76)
		else if((i>(78-4*hud.getUpgradePlayer().getlSmall())&&i<90)||(i>30 && i<39)) {
			createSCircle();
	//		System.out.println("small");
		}
		
		//64-55
		else if(i>54&&i<65) {
			createSSquare();
	//		System.out.println("s-square");
		}
		
		//54-51(61)
		else if(i>(55-hud.getUpgradePlayer().getlBig())&&i<55) {
			createBCircle();
	//		System.out.println("big");

		}
		//50-47
		else if(i>46&&i<51) {
			createBSquare();
	//		System.out.println("bsquare");

		}
		//46-43
		else if(i>42&&i<47) {
			createMSquare();
	//		System.out.println("m-square");

		}
		//42-39(45)
		else if(i>(42-hud.getUpgradePlayer().getlBomb())&&i<43) {
			createBomb();
	//		System.out.println("bomb");

		}
		//30-27(38)
		else if(i>(30-hud.getUpgradePlayer().getlTurbo())&&i<31) {
			createTurbo();
	//		System.out.println("turbo");

		}
		//26-19(33)
		else if(i>(26-2*hud.getUpgradePlayer().getlPower())&&i<27) {
			createPower();
	//		System.out.println("power");

		}
		//18-11
		else if(i>(18-2*hud.getUpgradePlayer().getlBird())&&i<19) {
			createBird();
	//		System.out.println("bird");

		}
		//10-5
		else if(i>(10-1.5*hud.getUpgradePlayer().getlTeleport())&&i<11) {
			createPoints();
	//		System.out.println("pints");

		}
		//4-1
		else if(i>(4-hud.getUpgradePlayer().getlJump())&&i<5) {
			createRegen();
	//		System.out.println("regen");

		}
		
		
		}

	private void createBackground() {
	    background= Application.res.getTexture("background");
	    background2= Application.res.getTexture("background2");
	    background3= Application.res.getTexture("background3");
	    background4= Application.res.getTexture("background4");
	    background5= Application.res.getTexture("background5");
	    background6= Application.res.getTexture("background6");
	    background7= Application.res.getTexture("background7");
	    background8= Application.res.getTexture("background8");
	    background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	    background8.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	    background2.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	    background3.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	    background4.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	    background5.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	    background6.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	    background7.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	}
	
	private void createBomb() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.2f,0);
		cshapeToCreate.setRadius(20/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 0.7f;
		body.createFixture(fdefToCreate).setUserData("bomb");
		test = new Bomb(body);
		aElements.add(test);
		body.setUserData(test);
	}
	
	private void createTurbo() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.2f,0);
		cshapeToCreate.setRadius(23/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 0.7f;
		body.createFixture(fdefToCreate).setUserData("turbo");
		test = new Turbo(body);
		aElements.add(test);
		body.setUserData(test);
	}
	
	private void createPower() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.2f,0);
		
		pshapeToCreate.setAsBox(10/PPM, 19/PPM);
		fdefToCreate.shape = pshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 0.7f;
		body.createFixture(fdefToCreate).setUserData("power");
		test = new Power(body);
		aElements.add(test);
		body.setUserData(test);
	}
	
	private void createBird() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.2f,0);
		cshapeToCreate.setRadius(16/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 1f;
		body.createFixture(fdefToCreate).setUserData("bird");
		test = new Bird(body);
		aElements.add(test);
		body.setUserData(test);
	}
	

	private void createPoints() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.2f,0);
		cshapeToCreate.setRadius(16/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 0.7f;
		body.createFixture(fdefToCreate).setUserData("points");
		test = new Points(body);
		aElements.add(test);
		body.setUserData(test);
	}
	

	private void createRegen() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.2f,0);
		cshapeToCreate.setRadius(16/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 0.7f;
		body.createFixture(fdefToCreate).setUserData("regen");
		test = new Regen(body);
		aElements.add(test);
		body.setUserData(test);
	}
	private void createGreyCircle() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.2f,0);
		cshapeToCreate.setRadius(32/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 0.7f;
		body.createFixture(fdefToCreate).setUserData("g_circle");
		g_circle = new GreyCircle(body);
		aElements.add(g_circle);
		body.setUserData(g_circle);
	
	}

	private void createMCircle() {
			
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j )/ PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.5f,0);
		
		cshapeToCreate.setRadius(16/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.restitution = 0.7f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		body.createFixture(fdefToCreate).setUserData("m_circle");
		m_circle = new MCircle(body);
		aElements.add(m_circle);
		body.setUserData(m_circle);
	}
	
	private void createBCircle() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j )/ PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		body.setLinearVelocity(-0.5f,0);
		
		cshapeToCreate.setRadius(24/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.restitution = 0.7f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		body.createFixture(fdefToCreate).setUserData("b_circle");
		b_circle = new BCircle(body);
		aElements.add(b_circle);
		body.setUserData(b_circle);
	}
	
	private void createSCircle() {
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j)/ PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
	//	body.setLinearVelocity(-0.8f,0);
		cshapeToCreate.setRadius(8/ PPM);
		fdefToCreate.shape = cshapeToCreate;
		fdefToCreate.density = 0.1f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		fdefToCreate.restitution = 0.7f;
		body.createFixture(fdefToCreate).setUserData("s_circle");
		s_circle = new SCircle(body);
		aElements.add(s_circle);
		body.setUserData(s_circle);
	}

	
	private void createMSquare() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j)/ PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		
		pshapeToCreate.setAsBox(16/PPM, 16/PPM);
		fdefToCreate.shape = pshapeToCreate;
		fdefToCreate.density = 0.01f;
		fdefToCreate.restitution = 0.7f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		body.createFixture(fdefToCreate).setUserData("m_square");
			
		m_square = new MSquare(body);
		aElements.add(m_square);
		body.setUserData(m_square);
	}

	private void createBSquare() {
		
		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (105 + j)/ PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		
		pshapeToCreate.setAsBox(24/PPM, 24/PPM);
		fdefToCreate.shape = pshapeToCreate;
		fdefToCreate.density = 0.01f;
		fdefToCreate.restitution = 0.7f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		body.createFixture(fdefToCreate).setUserData("b_square");
			
		b_square = new BSquare(body);
		aElements.add(b_square);
		body.setUserData(b_square);
	}

	private void createSSquare() {

		bdefToCreate.position.set((player.getBody().getPosition().x*100+750)/ PPM, (135 + j) / PPM);
		bdefToCreate.type = BodyType.DynamicBody;
		body = world.createBody(bdefToCreate);
		
		pshapeToCreate.setAsBox(8/PPM, 8/PPM);
		fdefToCreate.shape = pshapeToCreate;
		fdefToCreate.density = 0.01f;
		fdefToCreate.restitution = 0.7f;
		fdefToCreate.filter.categoryBits = B2DVars.BIT_BALL;
		fdefToCreate.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_WALL;
		body.createFixture(fdefToCreate).setUserData("s_square");
			
		s_square = new SSquare(body);
		aElements.add(s_square);
		body.setUserData(s_square);
	}

	private void createGround(){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape cshape = new PolygonShape();
		
		bdef.position.set(10/ PPM, 60 / PPM);
		bdef.type = BodyType.StaticBody;
		body = world.createBody(bdef);
		
		cshape.setAsBox(400000/PPM, 1/PPM);
		fdef.shape = cshape;
		fdef.restitution = 0.5f;
		fdef.friction = 0;
		fdef.isSensor = false;
		fdef.filter.categoryBits = B2DVars.BIT_WALL;
		fdef.filter.maskBits = B2DVars.BIT_PLAYER | B2DVars.BIT_BALL;
		body.createFixture(fdef).setUserData("ground");
			
		body.setUserData(s_square);
	}

	private void createTiles() {
		tileMap = new TmxMapLoader().load("res/maps/kolko_1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		
		TiledMapTileLayer layer;
		
		layer = (TiledMapTileLayer) tileMap.getLayers().get("down");
	//	createLayer(layer);
	
	}


	private void createPlayer() {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape cshape = new CircleShape();
		
		bdef.position.set(100 / PPM, 100 / PPM);
		bdef.type = BodyType.DynamicBody;
	//	bdef.linearVelocity.set(5f, 0);
		Body body = world.createBody(bdef);
		
		cshape.setRadius(23/ PPM);
		fdef.shape = cshape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_BALL | B2DVars.BIT_WALL;
	    fdef.restitution = 0.1f;
		body.createFixture(fdef).setUserData("player");
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(13 / PPM,  2 / PPM);
		fdef.shape = shape;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
		
		
		player = new Player(body);
		body.setLinearVelocity(player.getSpeed(),0);
		//cicrular Data
		body.setUserData(player);
	}

	@Override
	public void handleInput() {
		
		if(MyInput.isPressed(MyInput.BUTTON1)) {
		//	if(c1.isPLayerOnGround()) {
			if(!hud.getEndStage())
				Application.res.playSound("click");
				player.getBody().applyForceToCenter(0, 550, true);
		//		  }
			}
		
		//switch button
		if(MyInput.isPressed(MyInput.BUTTON2)) {
	//		switchBlocks();
		}
		if(Gdx.input.justTouched()) {
			if(!hud.getEndStage())
			Application.res.playSound("click");
			player.getBody().applyForceToCenter(0, 300, true);
		}
	}
	
	public void reset() {
		player.getBody().setTransform(new Vector2(0,0),player.getBody().getAngle());
	}


	@Override
	public void update(float dt) {
		
		handleInput();
		if((player.getBody().getPosition().x-playerPosition)>1) {
			playerPosition = player.getBody().getPosition().x;
			createElements();
		}
		if((player.getEnergy()<=0 && player.getBody().getLinearVelocity().x<=0)&& !hud.getEndStage()) {
		hud.showEndStage();
		hud.setEndStage(true);
	    PLAY=false;
		}
		if((player.getEnergy()<=0 && player.getBody().getLinearVelocity().x>=0)&& !hud.getEndStage()) {
			player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x-0.01f, player.getBody().getLinearVelocity().y);
		}
		
		
		if(PLAY) {
		Array<Body> bodies = c1.getBodiesToRemove();
		for(int i=0;i < bodies.size; i++) {
			b = bodies.get(i);
			
			if(b.getUserData() instanceof SCircle){
				player.addCurrentPoints(1);
				player.addSpeed(0.3f);
			}
			else if(b.getUserData() instanceof SSquare) {
				player.addSpeed(-0.3f);
			}
			else if(b.getUserData() instanceof MSquare) {
				player.addSpeed(-0.7f);
			}
			else if(b.getUserData() instanceof BSquare) {
				player.addSpeed(-1.5f);
			}
			else if(b.getUserData() instanceof MCircle) {
				player.addSpeed(0.7f);
				player.addCurrentPoints(3);
			}
			else if(b.getUserData() instanceof BCircle) {
				player.addSpeed(1.5f);
				player.addCurrentPoints(10);
			}
			else if(b.getUserData() instanceof Bomb) {
				player.addSpeed(2);
				player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, player.getBody().getLinearVelocity().y+8);
			}
			else if(b.getUserData() instanceof Turbo) {
				player.addSpeed(4);
			}
			else if(b.getUserData() instanceof Power) {
				player.addEnergy(1);
			}
			else if(b.getUserData() instanceof Bird) {
				player.addSpeed(1);
				player.addPoints(5);
				player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, player.getBody().getLinearVelocity().y-4);
			}
			else if(b.getUserData() instanceof Points) {
				player.addCurrentPoints(10);
			}
			else if(b.getUserData() instanceof Regen) {
				player.addEnergy((int)player.getMaxEnergy()/2);
			}
			aElements.removeValue((B2DSprite)b.getUserData(), true);
			Application.res.playSound("click");
			world.destroyBody(b);

		}
		bodies.clear();
		}
		if((player.getEnergy()>0 || player.getBody().getLinearVelocity().x>0 ) && !hud.getEndStage() ) {
		player.update(dt);
		PLAY = true;
		}
		else {
			player.getBody().setLinearVelocity(0,player.getBody().getLinearVelocity().y);
		}
		
		for(B2DSprite el: aElements) {
			if((el.getBody().getPosition().x+(2.5))<player.getBody().getPosition().x) {
				aElements.removeValue((B2DSprite)el.getBody().getUserData(), true);
				b = el.getBody();
				world.destroyBody(b);
			}
		}
		for(B2DSprite el: aElements) {
			el.update(dt);
		}
//		if(Gdx.input.isKeyPressed(Keys.SPACE))
		world.step(dt, 6, 2);		
	}

	@Override 
	public void render() {

		sourceX += player.getBody().getLinearVelocity().x;
	//	Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.begin();
		
		if(player.getBody().getPosition().x<162.5)
		sb.draw(background, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
		else if(player.getBody().getPosition().x<354.9)
		sb.draw(background2, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
		else if(player.getBody().getPosition().x<483.4)
		sb.draw(background3, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
		else if(player.getBody().getPosition().x<642.6)
		sb.draw(background4, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
		else if(player.getBody().getPosition().x<802.5)
		sb.draw(background5, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
		else if(player.getBody().getPosition().x<963)
		sb.draw(background6, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
		else if(player.getBody().getPosition().x<1122.7)
		sb.draw(background7, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
		else
		sb.draw(background8, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);

		sb.end();
		
		cam.position.set(player.getPosition().x * PPM + Application.WIDTH/4, Application.HEIGHT /2 , 0);
		cam.update();
		
		tmr.setView(cam);
		tmr.render();
		
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);
	
		for(B2DSprite element:  aElements) {
			element.render(sb);
		}
		
		sb.setProjectionMatrix(hudCam.combined);
		hud.render(sb);
	
		
		
		if(debug) {
			b2dr.render(world, b2dCam.combined);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void resetMap() {
	for(B2DSprite el: aElements) {
		world.destroyBody(el.getBody());	
	}
	aElements.clear();
	playerPosition=0.0f;
	c1.clearBodiesToRemove();
	}
	
	public void clearElements() {
	for(B2DSprite el: aElements) {
		el.getBody().setLinearVelocity(0,0);
	}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	

}
