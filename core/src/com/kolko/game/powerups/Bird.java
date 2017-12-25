package com.kolko.game.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class Bird extends B2DSprite{
	
	private static boolean a = false;
	
	public Bird(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("bird");
		if(a){
		TextureRegion[] sprites = TextureRegion.split(tex, 32, 32)[0];
		setAnimation(sprites, 1 / 6f);
		}
		else {
		TextureRegion[] sprites = TextureRegion.split(tex, 32, 32)[1];
		setAnimation(sprites, 1 / 6f);
		}
		a=!a;	
		
	}
}
