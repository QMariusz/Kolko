package com.kolko.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;

public class Player extends B2DSprite{

	private float energy = 5;
	private float maxEnergy = 10;
	private int points;

	public Player(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("kolo");
		TextureRegion[] sprites = TextureRegion.split(tex, 46, 46)[0];
		setAnimation(sprites, 1 / 12f);
	}
	
	@Override
	public void update(float dt) {
		animation.update(dt);
		energy -= 0.01;
	}
	
	public float getEnergy() {
		return energy;
	}
	
	public float getMaxEnergy() {
		return maxEnergy;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}
	
	public void addSpeed(float f) {
		body.setLinearVelocity(body.getLinearVelocity().x+f, body.getLinearVelocity().y);
	}
	
	
}
