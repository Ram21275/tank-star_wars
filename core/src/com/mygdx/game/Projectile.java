package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

// TODO: 09/12/22 re-draw y interfaces in uml
interface Projectile extends Collidable {
    void spawn(Vector2 pos, int power, int angle);
}

interface Collidable extends Disposable {
    void beginCollide(Collidable collide_with);

    void colide(Collidable collide_with);
    void endCollide(Collidable collide_with);
}

interface Disposable {
    void dispose();
}

interface Renderable {
    void render();
}
