package com.kolko.game.handlers;

import static com.kolko.game.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
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
import com.badlogic.gdx.utils.Array;
import com.kolko.game.Application;
import com.kolko.game.alies.MCircle;
import com.kolko.game.alies.SCircle;
import com.kolko.game.enemies.MSquare;
import com.kolko.game.enemies.SSquare;
import com.kolko.game.entities.B2DSprite;
import com.kolko.game.entities.HUD;
import com.kolko.game.entities.Player;
import com.kolko.game.neutral.GreyCircle;
import com.kolko.game.states.GameState;

public class Play extends GameState{

	private World world;
	private Box2DDebugRenderer b2dr;
	
	private OrthographicCamera b2dCam;
	private MyContactListener c1;
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private float tileSize;
	
	private Player player;
	private SCircle s_circle;
	private MCircle m_circle;
	private GreyCircle g_circle;
	private SSquare s_square;
	private MSquare m_square;
	private Array<B2DSprite> aElements;
	private HUD hud;
	private Texture background;
	private int sourceX = 0;
	private boolean energy = true;
	
	private boolean debug = false;
	
	protected Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.82f), true);
		c1 = new MyContactListener();
		world.setContactListener(c1);
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Application.WIDTH / PPM, Application.HEIGHT / PPM);
		b2dr = new Box2DDebugRenderer();
		 aElements= new Array<B2DSprite>();
		
		createPlayer();
		
		hud = new HUD(player);
		
		createTiles();
		
		for(int i=1; i<2; i++) {
		createSCircle(i);
		}
		
		for(int j=1; j<2; j++) {
		createSSquare(j*100);
		}
		
		for(int j=1; j<2; j++) {
			createMSquare(j*200);
			}
		
		for(int j=1; j<2; j++) {
			createMCircle(j*400);
			}
		
		for(int j=1; j<2; j++) {
			createGreyCircle(j*500);
			}
		
		createBackground();
		}

	private void createBackground() {
	    background= Application.res.getTexture("background");
	    background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);	
	}
	
	private void createGreyCircle(int i) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape cshape = new CircleShape();
		
		bdef.position.set((400 +i)/ PPM, 105 / PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		cshape.setRadius(30/ PPM);
		fdef.shape = cshape;
		fdef.density = 0.1f;
		fdef.restitution = 1;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_CRYSTALS;
//		fdef.restitution = 1f;
		body.createFixture(fdef).setUserData("g_circle");
		g_circle = new GreyCircle(body);
		aElements.add(g_circle);
		body.setUserData(g_circle);
	
	}

	private void createMCircle(int i) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape cshape = new CircleShape();
		
		bdef.position.set((400 +i)/ PPM, 105 / PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		cshape.setRadius(20/ PPM);
		fdef.shape = cshape;
		fdef.density = 0.01f;
		fdef.restitution = 1;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_CRYSTALS;
//		fdef.restitution = 1f;
		body.createFixture(fdef).setUserData("m_circle");
		m_circle = new MCircle(body);
		aElements.add(m_circle);
		body.setUserData(m_circle);
	}
	
	private void createSCircle(int i) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape cshape = new CircleShape();
		
		bdef.position.set((400 +i)/ PPM, 135 / PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		cshape.setRadius(6/ PPM);
		fdef.shape = cshape;
		fdef.density = 0.01f;
		fdef.restitution = 1;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_CRYSTALS;
//		fdef.restitution = 1f;
		body.createFixture(fdef).setUserData("s_circle");
		s_circle = new SCircle(body);
		aElements.add(s_circle);
		body.setUserData(s_circle);
	}

	private void createMSquare(int i) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape cshape = new PolygonShape();
		
		bdef.position.set((400 +i)/ PPM, 135 / PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		cshape.setAsBox(14/PPM, 14/PPM);
		fdef.shape = cshape;
		fdef.density = 0.01f;
		fdef.restitution = 0.1f;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_CRYSTALS;
//		fdef.restitution = 1f;
		body.createFixture(fdef).setUserData("m_square");
			
		m_square = new MSquare(body);
		aElements.add(m_square);
		body.setUserData(m_square);
	}

	private void createSSquare(int j) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape cshape = new PolygonShape();
		
		bdef.position.set((400 +j)/ PPM, 135 / PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		cshape.setAsBox(10/PPM, 10/PPM);
		fdef.shape = cshape;
		fdef.density = 0.01f;
		fdef.restitution = 0.1f;
//		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
//		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_CRYSTALS;
//		fdef.restitution = 1f;
		body.createFixture(fdef).setUserData("s_square");
			
		s_square = new SSquare(body);
		aElements.add(s_square);
		body.setUserData(s_square);
	}


	private void createTiles() {
		tileMap = new TmxMapLoader().load("res/maps/kolko_1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		
		TiledMapTileLayer layer;
		
		layer = (TiledMapTileLayer) tileMap.getLayers().get("down");
		createLayer(layer);
	
	}

	private void createLayer(TiledMapTileLayer layer) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape cs = new PolygonShape();
		cs.setAsBox(tileSize / 2 /PPM,tileSize / 2/  PPM);
	//	Vector2[] v = new Vector2[3];
	//	v[0] = new Vector2(-tileSize /2 /PPM, -tileSize / 2/ PPM);
	//	v[1] = new Vector2(-tileSize /2 /PPM, tileSize / 2/ PPM);
	//	v[2] = new Vector2(tileSize /2 /PPM, tileSize / 2/ PPM);
	//	cs2.createChain(v);
		
		for(int row = 0; row< layer.getHeight(); row++) {
			for(int col =0; col<layer.getWidth(); col++){
				// get cell
				Cell cell = layer.getCell(col, row);
				
		    	//chech if cell eist
				if(cell == null) continue;
				if(cell.getTile() == null) continue;
				//create body and fixtures fromcell
				
				
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col+0.5f) *tileSize / PPM, (row + 0.5f) * tileSize / PPM);		
				
				fdef.friction = 0;
				fdef.shape = cs;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);
			}
		}
	}

	private void createPlayer() {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		CircleShape cshape = new CircleShape();
		
		bdef.position.set(100 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(5f, 0);
		Body body = world.createBody(bdef);
		
		cshape.setRadius(20/ PPM);
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
		
		if(MyInput.isPressed(MyInput.BUTTON1)) {
		//	if(c1.isPLayerOnGround()) {
	
				player.getBody().applyForceToCenter(0, 250, true);
		//		  }
			}
		
		//switch button
		if(MyInput.isPressed(MyInput.BUTTON2)) {
	//		switchBlocks();
		}
	}

	@Override
	public void update(float dt) {
		
		handleInput();
		
		if(player.getEnergy()<=0) {
		player.getBody().setLinearVelocity(0,-2);
		}
		
		world.step(dt, 6, 2);
		
		
		Array<Body> bodies = c1.getBodiesToRemove();
		for(int i=0;i < bodies.size; i++) {
			Body b = bodies.get(i);
			
		//	if(b.getUserData().toString()) {
		//		System.out.println("tak");
		//	}
			if(b.getUserData() instanceof SCircle){
				player.addPoints(1);
				player.addSpeed(1);
			}
			else if(b.getUserData() instanceof SSquare) {
				player.addSpeed(-1);
			}
			else if(b.getUserData() instanceof MSquare) {
				player.addSpeed(-3);
			}
			else if(b.getUserData() instanceof MCircle) {
				player.addSpeed(3);
			}
			aElements.removeValue((B2DSprite)b.getUserData(), true);
			world.destroyBody(b);

		}
		bodies.clear();
		
		if(player.getEnergy()>0) {
		player.update(dt);
		}
	}

	@Override 
	public void render() {
		sourceX += player.getBody().getLinearVelocity().x;
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(background, 0, 0, sourceX, 0, Application.WIDTH, Application.HEIGHT);
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
	//	System.out.println( aElements.size);
		
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

	public Player getPlayer() {
		return player;
	}
	
	

}
