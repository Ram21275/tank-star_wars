package com.mygdx.game;


///TODO: IMPLEMENT THIS, rewrite ground uml

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

public class Ground implements Collidable , Renderable {
    private Mesh shape;
    private ShaderProgram shader;
    private Vector2 position;
    private float[][] ground_coord;

    public void generateAssets()
    {

    }
    public void generateTerrain()
    {

    }
    public float getSlope(Vector2 position)
    {
        return 0f;
    }


    public Mesh getShape() {
        return shape;
    }

    public void setShape(Mesh shape) {
        this.shape = shape;
    }

    public ShaderProgram getShader() {
        return shader;
    }

    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float[][] getGroundCoord() {
        return ground_coord;
    }

    public void setGroundCoord(float[][] ground_coord) {
        this.ground_coord = ground_coord;
    }

    @Override
    public void collide(Collidable collide_with) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {

    }
}
