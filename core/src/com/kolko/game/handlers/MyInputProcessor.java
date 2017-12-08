package com.kolko.game.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter{
	public boolean keyDown(int k) {
		if(k == Keys.Z) {
			MyInput.setKeys(MyInput.BUTTON1,  true);
		}
		if(k == Keys.X) {
			MyInput.setKeys(MyInput.BUTTON2,  true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) {
			MyInput.setKeys(MyInput.BUTTON1,  false);
		}
		if(k == Keys.X) {
			MyInput.setKeys(MyInput.BUTTON2,  false);
		}
		return true;
	}
}
