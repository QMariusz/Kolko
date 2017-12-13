package com.kolko.game.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.kolko.game.Application;

public class Upgrade extends Image{

	private final static int WIDTH = 386;
	private final static int HEIGHT = 600;
	
	private final static int STARTING_X = 230;
	private final static int STARTING_Y = 0;
	
	private Player player;
	private TextButton textButton;
	
	public Upgrade(Player player) {
		super(Application.res.getTexture("upgrade"));
		
		this.player = player;
		this.setOrigin(WIDTH/2, HEIGHT/2);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		
		//starting position
		this.setPosition(STARTING_X, STARTING_Y);
	}

}
