package com.kolko.game.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class Power extends B2DSprite{

	public Power(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("power");
		TextureRegion[] sprites = TextureRegion.split(tex, 20, 38)[0];
		setAnimation(sprites, 1 / 12f);
	}
}
