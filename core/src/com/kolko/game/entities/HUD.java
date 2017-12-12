package com.kolko.game.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.kolko.game.Application;
import com.kolko.game.handlers.MyInput;

public class HUD {

    private Player player;
	private Label label;
	private TextureRegion[] blocks;
	private BitmapFont font = new BitmapFont();
	private boolean endStage;
	private Stage stage;
	private Image endStageImage;
	
	public HUD(Player player, Stage stage) {
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		label = new Label("", labelStyle);
		this.stage = stage;
		
		this.player = player;
		
//		Texture tex = Application.res.getTexture("hud");
//		
//		blocks = new TextureRegion[3];
//		for(int i=0; i< blocks.length; i++) {
//			blocks[i] = new TextureRegion(tex, 32 + i *16, 0 , 16 , 16);
//		}
	}
	
	public void initImage() {
		endStageImage= new Image(Application.res.getTexture("end_stage"));
		endStageImage.setX(7);
		endStageImage.setY(7);
		endStageImage.setWidth(50);
        endStageImage.setHeight(26);
		endStageImage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				clickUpgrades();
				return true;
			}
		});
		stage.addActor(endStageImage);
	}

	protected void clickUpgrades() {
		this.endStage = false;
		endStageImage.setX(-1000);
		player.getBody().setTransform(1, 2, player.getBody().getAngle());
		player.resetSpeed();
		player.getBody().setLinearVelocity(3,0);
		for(Actor actor : stage.getActors())
		{
		    if(actor.getX() < 0)
		        actor.remove();
		}
		
	}

	public void render(SpriteBatch sb) {
		sb.begin();
		font.draw(sb, "Energy: " + Math.round(player.getEnergy()*100)/100.0 + "/" + player.getMaxEnergy(), 10, 710);
		font.draw(sb, "Speed: " + Math.round(player.getBody().getLinearVelocity().x*100)/100.0, 10, 690);
		font.draw(sb, "Points: " + player.getPoints(), 10, 670);
		sb.end();
		if(endStage) {
	//		Gdx.input.setInputProcessor(stage);
			stage.act();
			sb.begin();
			stage.draw();
			sb.end();		
		}
		handleInput();
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
			System.out.println("dziala");
		}
	}
}
