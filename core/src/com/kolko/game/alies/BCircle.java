package com.kolko.game.alies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class BCircle extends B2DSprite{

	public BCircle(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("b_circle");
		TextureRegion[] sprites = TextureRegion.split(tex, 48, 48)[0];
		setAnimation(sprites, 1 / 6f);
	}
}
