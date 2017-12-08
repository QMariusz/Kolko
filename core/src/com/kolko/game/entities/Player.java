package com.kolko.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;

public class Player extends B2DSprite{

	public Player(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("kolo");
		TextureRegion[] sprites = TextureRegion.split(tex, 46, 46)[0];
		setAnimation(sprites, 1 / 12f);
	}
	
}
