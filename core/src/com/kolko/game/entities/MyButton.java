package com.kolko.game.entities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kolko.game.Application;

public class MyButton extends Button{

	private String name;
	private UpgradePlayer upgradePlayer;
	private int x, y;
	
	public MyButton(UpgradePlayer upgradePlayer, String s, int x , int y, TextButtonStyle styl){
		super(styl);
		this.name = s;
		this.x = x;
		this.y = y;
		this.upgradePlayer = upgradePlayer;
		init();	
		
	}

	private void init() {
		this.setWidth(80);
		this.setHeight(42);
		this.setX(x);
		this.setY(y);
	//	this.setDebug(true);
		this.setVisible(false);
		this.addListener(new ClickListener() {

	         @Override
	         public boolean touchDown(InputEvent event, float x, float y,
	               int pointer, int button) {
	        	   reactOnClick();
		           return true;
	         }
	         

		});
	}

	protected void reactOnClick() {
		Application.res.playSound("tree");
		if(name.equals("speed")) {
			upgradePlayer.upgradeSpeed();
		}
		else if(name.equals("energy")) {
			upgradePlayer.upgradeEnergy();
		}
		else if(name.equals("medium")) {
			upgradePlayer.upgradeMedium();
		}
		else if(name.equals("small")) {
			upgradePlayer.upgradeSmall();
		}
		else if(name.equals("big")) {
			upgradePlayer.upgradeBig();
		}
		else if(name.equals("bomb")) {
			upgradePlayer.upgradeBomb();
		}
		else if(name.equals("turbo")) {
			upgradePlayer.upgradeTurbo();
		}
		else if(name.equals("power")) {
			upgradePlayer.upgradePower();
		}
		else if(name.equals("teleport")) {
			upgradePlayer.upgradeTeleport();
		}
		else if(name.equals("bird")) {
			upgradePlayer.upgradeBird();
		}
		else if(name.equals("jump")) {
			upgradePlayer.upgradeJump();
		}
	}
}