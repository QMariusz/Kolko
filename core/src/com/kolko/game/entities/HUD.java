package com.kolko.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kolko.game.Application;
import com.kolko.game.handlers.MyInput;
import com.kolko.game.handlers.MyInputProcessor;
import com.kolko.game.handlers.Play;

public class HUD {

    private Player player;
	private BitmapFont font = new BitmapFont();
	private boolean endStage, upgrades;
	private Stage stage;
	private Image endStageImage;
	private Image upgradeImage, resetImage;
	private UpgradePlayer upgradePlayer;
	private MyLabel labelPoints, labelDistance, labelSum, myLabel;
	private MyButton speedButton, energyButton, smallButton, mediumButton, bigButton, turboButton, bombButton, powerButton,
					 birdButton, teleportButton, jumpButton;
	private ArrayList<MyButton> myButtons;
	private Play play;
	private Label label;
	private ArrayList<Rectangle> rectangles;
	private Rectangle rectangle;
	private ShapeRenderer shapeRenderer;
	private ArrayList<MyLabel> myLabels;
	private ArrayList<CostLabel> costLabels;
	private CostLabel energyCost;
	
	public HUD(Player player, Stage stage, Play play) {
		
		this.stage = stage;
		this.play = play;
		this.player = player;
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setColor(Color.GREEN);
		
		myButtons = new ArrayList<MyButton>();
		initEndStage();
		
		rectangles = new ArrayList<Rectangle>();
		myLabels = new ArrayList<MyLabel>();
		costLabels = new ArrayList<CostLabel>();
		initCostLabel();
		
		createMyLabel();

		MyInputProcessor inputProcessorOne = new MyInputProcessor();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(inputProcessorOne);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		font = new BitmapFont(Gdx.files.internal("res/font/gra.fnt"),false);
	}
	
	private void initCostLabel() {
		LabelStyle lStyle = new LabelStyle();
		BitmapFont font2 = new BitmapFont(Gdx.files.internal("res/font/test.fnt"),false);
	//	font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	//	font.getData().setScale(1.4f, 1.4f);
		lStyle.font = font2;
		createCostLabel(lStyle, "speed", 870, 522);
		createCostLabel(lStyle, "energy", 870, 461);
		createCostLabel(lStyle, "small", 440, 364);
		createCostLabel(lStyle, "medium", 440, 314);
		createCostLabel(lStyle, "big", 440, 266);
		createCostLabel(lStyle, "bomb", 440, 221);
		createCostLabel(lStyle, "turbo", 440, 175);
		createCostLabel(lStyle, "bird", 838, 319);
		createCostLabel(lStyle, "teleport", 838, 273);
		createCostLabel(lStyle, "jump", 838, 226);
		createCostLabel(lStyle, "power", 838, 363);
	}

	private void createCostLabel(LabelStyle lStyle, String string, int i, int j) {
		energyCost = new CostLabel("", upgradePlayer, lStyle, string, i, j);
		costLabels.add(energyCost);
		stage.addActor(energyCost);
	}

	private void createMyLabel() {
		LabelStyle lStyle = new LabelStyle();
		BitmapFont font2 = new BitmapFont(Gdx.files.internal("res/font/upgrade.fnt"),false);
		lStyle.font = font2;
		createLabel(lStyle, "points", 210, 108);
		createLabel(lStyle, "currentPoints", 210, 75);
		createLabel(lStyle, "distance", 550, 108);
		createLabel(lStyle, "currentDistance", 550, 75);
		createLabel(lStyle, "energy", 210, 44);
		createLabel(lStyle, "speed", 550, 44);
	}

	private void createLabel(LabelStyle lStyle, String string, int i, int j) {
		myLabel = new MyLabel("", player, lStyle, string, i, j);
		myLabels.add(myLabel);
		stage.addActor(myLabel);
	}

	public void addRectangle(int x, int y) {
		rectangle = new Rectangle();
		rectangle.setPosition(x , y);
		rectangle.height=30;
		rectangle.width=30;
		rectangles.add(rectangle);
	}

	private void initLabel() {
		LabelStyle lStyle = new LabelStyle();
		BitmapFont font = new BitmapFont();
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().setScale(1.2f, 1.2f);
		lStyle.font = font;
		label = new Label("Witam w grze Kolka", lStyle);
		label.setPosition(300, 100);
		label.setVisible(false);
		stage.addActor(label);
	}

	public void initEndStage() {
		upgradePlayer = new UpgradePlayer(player, this);
		endStageImage= new Image(Application.res.getTexture("end_stage"));
		endStageImage.setX(300);
		endStageImage.setY(200);
		endStageImage.setWidth(400);
        endStageImage.setHeight(250);
        endStageImage.setVisible(false);
		endStageImage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				Application.res.playSound("tree");
				showUpgrades();
				return true;
			}
		});
		LabelStyle lStyle = new LabelStyle();
		BitmapFont font3 = new BitmapFont(Gdx.files.internal("res/font/end.fnt"),false);
		lStyle.font = font3;
		labelPoints = new MyLabel("", player, lStyle, "currentPoints", 520, 348);
		labelDistance = new MyLabel("", player, lStyle, "currentDistance", 520, 295);
		labelSum = new MyLabel("", player, lStyle, "sum", 520, 231);
		stage.addActor(endStageImage);
		stage.addActor(labelPoints);
		stage.addActor(labelDistance);
		stage.addActor(labelSum);
		
		upgradeImage = new Image(Application.res.getTexture("upgrade"));
		upgradeImage.setPosition(0, 0);
		upgradeImage.setWidth(Application.WIDTH);
		upgradeImage.setHeight(Application.HEIGHT);
		upgradeImage.setVisible(false);
		stage.addActor(upgradeImage);
		
        BitmapFont font2 = new BitmapFont(Gdx.files.internal("res/font/test.fnt"),false);
		TextButtonStyle style = new TextButtonStyle(); //** Button properties **//
        style.up =  new TextureRegionDrawable(new TextureRegion(Application.res.getTexture("button_up")));
        style.down = new TextureRegionDrawable(new TextureRegion(Application.res.getTexture("button_down")));

        style.font = font2;
		speedButton = new MyButton(upgradePlayer, "speed", 850, 511, style);
		energyButton = new MyButton(upgradePlayer, "energy", 850, 450, style);
		smallButton = new MyButton(upgradePlayer, "small", 420, 353, style);
		mediumButton = new MyButton(upgradePlayer, "medium", 420, 303, style);
		bigButton = new MyButton(upgradePlayer, "big", 420, 255, style);
		bombButton = new MyButton(upgradePlayer, "bomb", 420, 210, style);
		turboButton = new MyButton(upgradePlayer, "turbo", 420, 164, style);
		powerButton = new MyButton(upgradePlayer, "power", 818, 352, style);
		birdButton = new MyButton(upgradePlayer, "bird", 818, 308, style);
		teleportButton = new MyButton(upgradePlayer, "teleport", 818, 262, style);
		jumpButton = new MyButton(upgradePlayer, "jump", 818, 215, style);
		myButtons.add(speedButton);
		myButtons.add(energyButton);
		myButtons.add(smallButton);
		myButtons.add(mediumButton);
		myButtons.add(bigButton);
		myButtons.add(bigButton);
		myButtons.add(turboButton);
		myButtons.add(bombButton);
		myButtons.add(powerButton);
		myButtons.add(birdButton);
		myButtons.add(teleportButton);
		myButtons.add(jumpButton);
		stage.addActor(powerButton);
		stage.addActor(birdButton);
		stage.addActor(teleportButton);
		stage.addActor(jumpButton);
		stage.addActor(bombButton);
		stage.addActor(turboButton);
		stage.addActor(smallButton);
		stage.addActor(mediumButton);
		stage.addActor(bigButton);
		stage.addActor(speedButton);
		stage.addActor(energyButton);
		
		
		
		resetImage = new Image(Application.res.getTexture("resetImage"));
		resetImage.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
		//		Application.res.playSound("tree");
				unPause();
				return true;
			}
		});
		resetImage.setPosition(710, 30);
		resetImage.setWidth(225);
		resetImage.setHeight(120);
		resetImage.setVisible(false);
	//	resetImage.setDebug(true);
		stage.addActor(resetImage);
	}

	protected void unPause() {
		player.getBody().setTransform(1, 2, player.getBody().getAngle());
		player.resetSpeed();
		player.getBody().setLinearVelocity(player.getSpeed(),0);
		upgradeImage.setVisible(false);
		resetImage.setVisible(false);
		
		this.upgrades=false;
//		label.setVisible(false);
		for(MyButton element: myButtons) {
			element.setVisible(false);
		}
		for(MyLabel element: myLabels) {
			element.setVisible(false);
		}
		for(CostLabel element: costLabels) {
			element.setVisible(false);
		}
		player.resetCurrentPoints();
		play.resetMap();
		this.endStage = false;
	}

	public void showEndStage() {
		//player.getBody().setLinearVelocity(0,-2);
		player.addPoints((int)player.getBody().getPosition().x/10);
		player.setDistance((double)player.getBody().getPosition().x);
		play.clearElements();
		labelDistance.update();
		labelPoints.update();
		labelSum.update();
		this.endStageImage.setVisible(true);
		this.labelDistance.setVisible(true);
		this.labelPoints.setVisible(true);
		this.labelSum.setVisible(true);
		player.addPoints(player.getCurrentPoints());
	}
	
	protected void showUpgrades() {
		this.endStageImage.setVisible(false);
		this.labelDistance.setVisible(false);
		this.labelPoints.setVisible(false);
		this.labelSum.setVisible(false);
		upgradeImage.setVisible(true);
		this.upgrades=true;
	//	label.setVisible(true);
		player.setEnergy(player.getMaxEnergy());
		for(MyLabel element: myLabels) {
			element.update();
			element.setVisible(true);
		}
		resetImage.setVisible(true);
		for(MyButton element: myButtons) {
			element.setVisible(true);
		}
		for(CostLabel element: costLabels) {
			element.setVisible(true);
		}
		
//		this.endStage = false;
//		endStageImage.setX(-1000);

	}

	public void render(SpriteBatch sb) {
		sb.begin();
		font.draw(sb, "Energy: " + Math.round(player.getEnergy()*100)/100.0 + "/" + player.getMaxEnergy(), 10, 630);
		font.draw(sb, "Speed: " + Math.round(player.getBody().getLinearVelocity().x*100)/100.0, 10, 610);
		font.draw(sb, "Points: " + player.getPoints(), 10, 590);
		font.draw(sb, "Current Points: " + player.getCurrentPoints(), 10, 570);
		sb.end();
		if(endStage) {
			stage.act();
			sb.begin();
			stage.draw();
			sb.end();		
		update();
		if(upgrades) {
		shapeRenderer.begin(ShapeType.Filled);
		for(Rectangle element: rectangles) {
		shapeRenderer.rect(element.getX(),element.getY(),20,20);
		}
		shapeRenderer.end();
		}
		}
		handleInput();
	}
	
	private void update() {
		for(MyLabel element: myLabels) {
			element.update();
		}
		for(CostLabel element: costLabels) {
			element.update();
		}
	}

	public void setEndStage(boolean a) {
		this.endStage = a;
	}
	
	public boolean getEndStage() {
		return this.endStage;
	}
	
	public void handleInput() {
		
		if(MyInput.isPressed(MyInput.BUTTON1)) {
		//	if(c1.isPLayerOnGround()) {
	
			//	player.getBody().applyForceToCenter(0, 250, true);
		//		  }
			}
		
		//switch button
		if(MyInput.isPressed(MyInput.BUTTON2)) {
	//		switchBlocks();
		}
	}

	public UpgradePlayer getUpgradePlayer() {
		return upgradePlayer;
	}
	
	
}
