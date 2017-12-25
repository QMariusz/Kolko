package com.kolko.game.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class Bomb extends B2DSprite{

	public Bomb(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("bomb");
		TextureRegion[] sprites = TextureRegion.split(tex, 40, 46)[0];
		setAnimation(sprites, 1 / 12f);
	}
}
