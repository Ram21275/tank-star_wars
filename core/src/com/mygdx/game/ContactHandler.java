package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactHandler implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Collidable A = (Collidable) contact.getFixtureA().getUserData();
        Collidable B = (Collidable) contact.getFixtureB().getUserData();
        if (A != null && B != null) {
            A.beginCollide(B);
            B.beginCollide(A);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Collidable A = (Collidable) contact.getFixtureA().getUserData();
        Collidable B = (Collidable) contact.getFixtureB().getUserData();
        if (A != null && B != null) {
            A.endCollide(B);
            B.endCollide(A);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
