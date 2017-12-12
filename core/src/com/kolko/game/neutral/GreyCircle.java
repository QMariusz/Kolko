package com.kolko.game.neutral;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;
import com.kolko.game.entities.B2DSprite;

public class GreyCircle extends B2DSprite{

	public GreyCircle(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("g_circle");
		TextureRegion[] sprites = TextureRegion.split(tex, 64, 64)[0];
		setAnimation(sprites, 1 / 12f);
}
}
