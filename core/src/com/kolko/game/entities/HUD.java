package com.kolko.game.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class HUD {

    private Player player;
	private Label label;
	private TextureRegion[] blocks;
	private BitmapFont font = new BitmapFont();
	
	public HUD(Player player) {
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		label = new Label("", labelStyle);
		
		this.player = player;
		
//		Texture tex = Application.res.getTexture("hud");
//		
//		blocks = new TextureRegion[3];
//		for(int i=0; i< blocks.length; i++) {
//			blocks[i] = new TextureRegion(tex, 32 + i *16, 0 , 16 , 16);
//		}
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		font.draw(sb, "Energy: " + Math.round(player.getEnergy()*100)/100.0 + "/" + player.getMaxEnergy(), 10, 710);
		font.draw(sb, "Speed: " + Math.round(player.getBody().getLinearVelocity().x*100)/100.0, 10, 690);
		font.draw(sb, "Points: " + player.getPoints(), 10, 670);
		sb.end();
	}
}
