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

    public abstract void setAsset();

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
}

class AbramsPassive extends TankPassive {

    public AbramsPassive()
    {
        this.setAsset();
    }
    @Override
    public void setAsset() {

    }
}

class FrostPassive extends TankPassive {
    public FrostPassive()
    {
        this.setAsset();
    }
    @Override
    public void setAsset() {

    }
}

class BuggyPassive extends TankPassive {
    public BuggyPassive()
    {
        this.setAsset();
    }
    @Override
    public void setAsset() {

    }
}