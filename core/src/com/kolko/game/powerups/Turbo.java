package com.kolko.game.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class Turbo extends B2DSprite{

	public Turbo(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("turbo");
		TextureRegion[] sprites = TextureRegion.split(tex, 46, 46)[0];
		setAnimation(sprites, 1 / 12f);
	}
}
