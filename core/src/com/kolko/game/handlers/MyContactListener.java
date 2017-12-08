package com.kolko.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener{

	private int numFootContacts;
	
	public MyContactListener() {
		super();
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
		
	}
	
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		System.out.println("decontact");
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
