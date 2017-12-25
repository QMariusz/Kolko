package com.kolko.game.entities;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyLabel extends Label{

	private String name;
	private Player player;
	private String result;
	
	public MyLabel(CharSequence text, Player player, LabelStyle style,  String name, int x, int y) {
		super(text, style);
		this.setSize(20, 20);
		this.setPosition(x, y);
		this.name = name;
		this.player = player;
		this.setVisible(false);
		this.setTouchable(Touchable.disabled);
	}
	
	public void update() {
		if(name.equals("points")) {
			this.setText("" + player.getPoints());
		}
		else if(name.equals("distance")) {
			result = String.format("%.2f", player.getDistance()/10);
			this.setText(result);
		}
		else if(name.equals("sum")) {
			this.setText("" + ((int)player.getBody().getPosition().x/10+player.getCurrentPoints()));
		}
		else if(name.equals("currentPoints")) {
			this.setText(""+ player.getCurrentPoints());
		}
		else if(name.equals("energy")) {
			this.setText("" + player.getMaxEnergy());
		}
		else if(name.equals("currentDistance")) {
			result = String.format("%.2f", player.getBody().getPosition().x/10);
			this.setText(result);
		}
		else if(name.equals("speed")) {
			this.setText("" + player.getSpeed());
		}
	}
}
