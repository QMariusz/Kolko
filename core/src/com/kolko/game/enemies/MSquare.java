package com.kolko.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class MSquare extends B2DSprite{

	public MSquare(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("m_square");
		TextureRegion[] sprites = TextureRegion.split(tex, 32, 32)[0];
		setAnimation(sprites, 1 / 12f);
	}
}
