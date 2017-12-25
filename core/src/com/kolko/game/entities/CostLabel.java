package com.kolko.game.entities;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class CostLabel extends Label{

	private String name;
	private UpgradePlayer player;
	
	public CostLabel(CharSequence text, UpgradePlayer player, LabelStyle style,  String name, int x, int y) {
		super(text, style);
		this.setSize(20, 20);
		this.setPosition(x, y);
		this.name = name;
		this.player = player;
		this.setVisible(false);
		this.setTouchable(Touchable.disabled);
	}
	
	public void update() {
		if(name.equals("energy")) {
			if(player.getEnergyCost()<1700)
				this.setText("" + player.getEnergyCost());
			else
				this.setText("MAX");	
		}
		else if(name.equals("speed")) {
			if(player.getSpeedCost()<1700)
				this.setText("" + player.getSpeedCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("small")) {
			if(player.getSmallCost()<1500)
				this.setText("" + player.getSmallCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("medium")) {
			if(player.getMediumCost()<1500)
			this.setText("" + player.getMediumCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("big")) {
			if(player.getBigCost()<1500)
			this.setText("" + player.getBigCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("bomb")) {
			if(player.getBombCost()<1500)
			this.setText("" + player.getBombCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("turbo")) {
			if(player.getTurboCost()<1500)
			this.setText("" + player.getTurboCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("bird")) {
			if(player.getBirdCost()<1500)
				this.setText("" + player.getBirdCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("teleport")) {
			if(player.getTeleportCost()<1500)
			this.setText("" + player.getTeleportCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("jump")) {
			if(player.getJumpCost()<1500)
			this.setText("" + player.getJumpCost());
			else
				this.setText("MAX");
		}
		else if(name.equals("power")) {
			if(player.getPowerCost()<1500)
			this.setText("" + player.getPowerCost());
			else
				this.setText("MAX");
		}
	}
}
