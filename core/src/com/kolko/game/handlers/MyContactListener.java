package com.kolko.game.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.kolko.game.entities.Player;

public class MyContactListener implements ContactListener{

	private int numFootContacts;
	private Array<Body> bodiesToRemove;
	private Play play;
	
	public MyContactListener() {
		super();
		bodiesToRemove = new Array<Body>();
	}
	
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
		//	System.out.println("fa is foot");
			numFootContacts++;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
		//	System.out.println("fb is foot");
			numFootContacts++;
		}
		
		if(fa.getUserData() != null &&( fa.getUserData().equals("s_circle") || fa.getUserData().equals("s_square") ||
				fa.getUserData().equals("m_circle") || fa.getUserData().equals("m_square") || fa.getUserData().equals("b_square")
				|| fa.getUserData().equals("b_circle") || fa.getUserData().equals("bomb") || fa.getUserData().equals("turbo")
				|| fa.getUserData().equals("bird") || fa.getUserData().equals("power") || fa.getUserData().equals("points")
				|| fa.getUserData().equals("regen")) && fb.getUserData() != null &&
				fb.getUserData().equals("player")) {
			//remove crystal
			bodiesToRemove.add(fa.getBody());
		}
		if(fb.getUserData() != null && (fb.getUserData().equals("s_circle") || fb.getUserData().equals("s_square") || 
				fb.getUserData().equals("m_circle") || fb.getUserData().equals("m_square") || fb.getUserData().equals("b_square")
				 || fb.getUserData().equals("b_circle") || fb.getUserData().equals("bomb")  || fb.getUserData().equals("turbo")
				 || fb.getUserData().equals("bird") || fb.getUserData().equals("power") || fb.getUserData().equals("points")
				 || fb.getUserData().equals("regen"))&& fa.getUserData() != null && fa.getUserData().equals("player")) {
			//	System.out.println("fb is foot");
			bodiesToRemove.add(fb.getBody());
		}
		
	}
	
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		//if(fa == null || fb == null) return;
		
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
		//	System.out.println("fa is foot");
			numFootContacts--;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
		//	System.out.println("fb is foot");
			numFootContacts--;
		}
	}
	public Array<Body> getBodiesToRemove(){ return bodiesToRemove; } 
	public void clearBodiesToRemove(){ bodiesToRemove.clear();; } 

	public boolean isPLayerOnGround() { return numFootContacts > 0; }

	// collision detection
	// collision handlig
	public void preSolve(Contact contact, Manifold oldManifold) {
			
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
}
