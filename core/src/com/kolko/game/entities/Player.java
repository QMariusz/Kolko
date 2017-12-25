package com.kolko.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kolko.game.Application;

public class Player extends B2DSprite{

	private float energy = 3;
	private float maxEnergy = 3;
	private int points=10000, small=1, medium=1, big=1 ,bomb=1, turbo=1, power=1, bird=1, teleport=1, jump=1;
	private int currentPoints = 0 ;
	private float speed = 3.0f;
	private float multiply = 1.0f;
	private double distance;

	public Player(Body body) {
		super(body);
		
		Texture tex = Application.res.getTexture("kolo");
		TextureRegion[] sprites = TextureRegion.split(tex, 46, 46)[0];   
		setAnimation(sprites, 1 / 12f);
	}
	
	@Override
	public void update(float dt) {
		animation.update(dt);
		if(energy>0)
			energy -= 0.003f;
		else
			energy=0;
		if(body.getLinearVelocity().x<0.5f && energy>0){
			body.setLinearVelocity(0.25f, body.getLinearVelocity().y);
		}
		if(body.getLinearVelocity().x>30) {
			  body.setLinearVelocity(body.getLinearVelocity().x-0.1f, body.getLinearVelocity().y);
		}
		else if(body.getLinearVelocity().x>15) {
			  body.setLinearVelocity(body.getLinearVelocity().x-0.05f, body.getLinearVelocity().y);
		}
		else if(body.getLinearVelocity().x>6) {
			  body.setLinearVelocity(body.getLinearVelocity().x-0.01f, body.getLinearVelocity().y);
		}
		else if(body.getLinearVelocity().x>3) {
		  body.setLinearVelocity(body.getLinearVelocity().x-0.005f, body.getLinearVelocity().y);
		  
		}
		else {
		  body.setLinearVelocity(body.getLinearVelocity().x-0.001f, body.getLinearVelocity().y);
		}

	}
	
	public float getEnergy() {
		return energy;
	}
	
	public void setPoints(int p) {
		this.points += p;
	}
	
	public float getMaxEnergy() {
		return maxEnergy;
	}

	public int getPoints() {
		return points;
	}

	public int getCurrentPoints() {
		return currentPoints;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void addEnergy(int points) {
		if(energy+points<=maxEnergy) {
			this.energy += points;
		}
		else {
			this.energy = maxEnergy;
		}
			
	}
	
	public void addCurrentPoints(int points) {
		this.currentPoints += points;
	}
	
	public void addSpeed(float f) {
		body.setLinearVelocity(body.getLinearVelocity().x+f, body.getLinearVelocity().y);
	}
	
	public float getMultiply() {
		return this.multiply;
	}
	
	public void resetSpeed() {
		this.energy = maxEnergy;
	}
	
	public void resetCurrentPoints() {
		this.currentPoints = 0;
	}
	
	public void upgradeSpeed() {
		this.speed+=1;
	}
	
	public void upgradeEnergy() {
		this.maxEnergy+=1;
	}
	
	public void upgradeSmall() {
		this.small+=1;
	}
	
	public void upgradeMedium() {
		this.medium+=1;
	}
	
	public void upgradeBig() {
		this.big+=1;
	}
	
	public void upgradeBomb() {
		this.bomb+=1;
	}
	
	public void upgradeTurbo() {
		this.turbo+=1;
	}
	
	public void upgradePower() {
		this.power+=1;
	}
	
	public void upgradeBird() {
		this.bird+=1;
	}
	
	public void upgradeTeleport() {
		this.teleport+=1;
	}
	
	public void upgradeJump() {
		this.jump+=1;
	}
	
	public float getSpeed() {
		return this.speed;
	}

	public int getSmall() {
		return small;
	}

	public int getMedium() {
		return medium;
	}

	public int getBig() {
		return big;
	}

	public int getBomb() {
		return bomb;
	}

	public int getTurbo() {
		return turbo;
	}

	public int getPower() {
		return power;
	}

	public int getBird() {
		return bird;
	}

	public int getTeleport() {
		return teleport;
	}

	public int getJump() {
		return jump;
	}
	
	public void setEnergy(float t) {
		energy = t;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance += distance;
	}
	
	
}
