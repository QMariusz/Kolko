package com.kolko.game.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class Regen extends B2DSprite{

	public Regen(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("regen");
		TextureRegion[] sprites = TextureRegion.split(tex, 32, 32)[0];
		setAnimation(sprites, 1 / 4f);
	}
}
