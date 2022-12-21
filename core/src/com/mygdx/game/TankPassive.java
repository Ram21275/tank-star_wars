package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/*  TODO: get animation assets and image
*   TODO: write the set asset function of Abrams, Frost, Buggy
*/

public abstract class TankPassive {


    Animation<TextureRegion> animation;
    Sprite sprite;
    Image image;
    String name;
    PassivePlayer owner;
    TankPassive(PassivePlayer owner) {
        setAsset();
        this.owner = owner;
    }
    public abstract void setAsset();
    public abstract TankActive getTankActive();
    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PassivePlayer getOwner() {
        return owner;
    }

    public void setOwner(PassivePlayer owner) {
        this.owner = owner;
    }
}

class AbramsPassive extends TankPassive {


    AbramsPassive(PassivePlayer owner) {
        super(owner);
    }

    @Override
    public void setAsset() {
        animation = null; sprite = null; //todo do after asmit gives photos
        image = null;
        name = "Abrams";
    }

    @Override
    public TankActive getTankActive() {
        TankActive t  = new AbramsActive(this);
        return t;
    }
}

class FrostPassive extends TankPassive {


    FrostPassive(PassivePlayer owner) {
        super(owner);
    }

    @Override
    public void setAsset() {
        animation = null; sprite = null; //todo do after asmit gives photos
        image = null;
        name = "Frost";
    }

    @Override
    public TankActive getTankActive() {
        TankActive t  = new FrostActive(this);
        return t;
    }
}

class BuratinoPassive extends TankPassive {
    BuratinoPassive(PassivePlayer owner) {
        super(owner);
    }

    @Override
    public void setAsset() {
        animation = null; sprite = null; //todo do after asmit gives photos
        image = null;
        name = "Buratino";
    }

    @Override
    public TankActive getTankActive() {
        TankActive t  = new BuratinoActive(this);
        return t;
    }
}