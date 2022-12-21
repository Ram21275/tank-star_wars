package com.mygdx.game;


///TODO: IMPLEMENT THIS, rewrite ground uml

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Null;

import java.util.Arrays;

public class Ground implements Collidable , Renderable {
    private Mesh shape;
    private ShaderProgram shader;
    private Vector2 position;
    private Texture ground_texture;
    private Body physics_body;
    private float[][] ground_coord; // (x = 0, y = 1);
    private Fixture f;

    public Ground() {
        this.ground_coord = new float[100][2];
        this.generateTerrain();

        BodyDef bd2 = new BodyDef(); bd2.type = BodyDef.BodyType.StaticBody; bd2.position.set(0,0); bd2.fixedRotation = true;
        physics_body = MyGame.handle.getGscreen().physics_world.createBody(bd2);
        Vector2[] vertices = new Vector2[102];
        for (int i = 1; i < vertices.length - 1; i++) {
            vertices[i] = new Vector2(ground_coord[i -1][0] * Gdx.graphics.getWidth()/32f,ground_coord[i -1][1] * Gdx.graphics.getHeight()/32f);
        }
        vertices[0] = new Vector2(0.01f,Gdx.graphics.getHeight()/32f);
        vertices[100].x = Gdx.graphics.getWidth()/32f;
        vertices[101] = new Vector2(Gdx.graphics.getWidth()/32f,Gdx.graphics.getHeight()/32f);
        ChainShape ps2  = new ChainShape(); ps2.createChain(vertices);
        physics_body.createFixture(ps2,1.0f).setUserData(this); ps2.dispose();
        this.generateAssets();

    }
    public void generateAssets()
    {

        //Call after generate terrain;
        float[] vertices = new float[102*5];
        vertices[0] = 0f;
        vertices[0 + 1] = 0f;
        vertices[0 + 2] = 0f;
        vertices[0 + 3] = 0f;
        vertices[0 + 4] = 1f;

        vertices[5] = 1f * Gdx.graphics.getWidth();
        vertices[5 + 1] = 0f;
        vertices[5 + 2] = 0f;
        vertices[5 + 3] = 1f;
        vertices[5 + 4] = 1f;

        for (int i = 0; i < 100; i++) {
            vertices[((i+2)*5) + 0] = ground_coord[i][0] * Gdx.graphics.getWidth(); // x
            vertices[((i+2)*5) + 1] = ground_coord[i][1] * Gdx.graphics.getHeight(); // y
            vertices[((i+2)*5) + 2] = 0; //z
            vertices[((i+2)*5) + 3] = ground_coord[i][0]; //u
            vertices[((i+2)*5) + 4] = 0f;//v
        }
        vertices[((99+2)*5) + 0] = Gdx.graphics.getWidth();

        short[] indices = new short[100 *3];
        for (int i = 0; i < 100 -1; i++) {
            indices[(i*3) + 0] = 0;
            indices[(i*3) + 1] = (short) (i + 2);
            indices[(i*3) + 2] = (short) (i + 3);
        }
        indices[299] = 0;
        indices[298] = 1;
        indices[297] = 101;
        String vertexShader = Gdx.files.internal("vertex.glsl").readString();
        String fragmentShader = Gdx.files.internal("fragment.glsl").readString();

        shader = new ShaderProgram(vertexShader,fragmentShader);
        ground_texture = new Texture(Gdx.files.internal("pixil-frame-0.png"));

        shape = new Mesh(true,ground_coord.length + 2,indices.length,
                new VertexAttribute(VertexAttributes.Usage.Position,3,ShaderProgram.POSITION_ATTRIBUTE),
                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates,2,ShaderProgram.TEXCOORD_ATTRIBUTE + "0"));
        shape.setVertices(vertices);
        shape.setIndices(indices);
    }

    public void generateTerrain()
    {
        float max = 0.625f;
        float min = 0.375f;
        float[] c = new float[ground_coord.length];
        Arrays.fill(c, -1.0f);
        float x = ((float)(ground_coord.length - 1))/8f;

        for (int i = 0; (int)(x * i)< c.length; i++) {
            c[(int)(x * i)] = (float) Math.random() * (max - min ) + min;
        }
        do {
            x = x / 2;
            int i = 0;
            while (i * x < ground_coord.length)
            {
                if(c[(int) (x * i)] < 0) {
                    max = c[(int) (x * (i + 1))];
                    min = c[(int) (x * (i - 1))];
                    c[(int) (x * i)] = (float) Math.random() * (max - min) + min;
                }
                i++;
            }
        } while ( x > 1 );
        for (int i = 0; i < ground_coord.length; i++) {
            ground_coord[i][0] = 1f/((float)(ground_coord.length)) * i;
            ground_coord[i][1] = c[i];
        }

    }
    public float getSlope(Vector2 position)
    {
        // TODO: 17/12/22 write this function
        float n_x = position.x/Gdx.graphics.getWidth();
        n_x = n_x / 0.01f;
        return (float) Math.atan((ground_coord[((int) n_x) + 1][1] - ground_coord[((int) n_x)][1])/0.01f);
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
        {
            this.ground_coord = ground_coord;
            physics_body.destroyFixture(physics_body.getFixtureList().first());
            Vector2[] vertices = new Vector2[102];
            for (int i = 1; i < vertices.length - 1; i++) {
                vertices[i] = new Vector2(ground_coord[i - 1][0] * Gdx.graphics.getWidth() / 32f, ground_coord[i - 1][1] * Gdx.graphics.getHeight() / 32f);
            }
            vertices[0] = new Vector2(0.01f, Gdx.graphics.getHeight() / 32f);
            vertices[100].x = Gdx.graphics.getWidth() / 32f;
            vertices[101] = new Vector2(Gdx.graphics.getWidth() / 32f, Gdx.graphics.getHeight() / 32f);
            ChainShape ps2 = new ChainShape();
            ps2.createChain(vertices);
            physics_body.createFixture(ps2, 1.0f).setUserData(this);
            ps2.dispose();
        }
        float[] vertices = new float[102*5];
        vertices[0] = 0f;
        vertices[0 + 1] = 0f;
        vertices[0 + 2] = 0f;
        vertices[0 + 3] = 0f;
        vertices[0 + 4] = 1f;

        vertices[5] = 1f * Gdx.graphics.getWidth();
        vertices[5 + 1] = 0f;
        vertices[5 + 2] = 0f;
        vertices[5 + 3] = 1f;
        vertices[5 + 4] = 1f;

        for (int i = 0; i < 100; i++) {
            vertices[((i+2)*5) + 0] = ground_coord[i][0] * Gdx.graphics.getWidth(); // x
            vertices[((i+2)*5) + 1] = ground_coord[i][1] * Gdx.graphics.getHeight(); // y
            vertices[((i+2)*5) + 2] = 0; //z
            vertices[((i+2)*5) + 3] = ground_coord[i][0]; //u
            vertices[((i+2)*5) + 4] = 0f;//v
        }
        vertices[((99+2)*5) + 0] = Gdx.graphics.getWidth();

        short[] indices = new short[100 *3];
        for (int i = 0; i < 100 -1; i++) {
            indices[(i*3) + 0] = 0;
            indices[(i*3) + 1] = (short) (i + 2);
            indices[(i*3) + 2] = (short) (i + 3);
        }
        indices[299] = 0;
        indices[298] = 1;
        indices[297] = 101;

        shape.dispose();

        shape = new Mesh(true,ground_coord.length + 2,indices.length,
                new VertexAttribute(VertexAttributes.Usage.Position,3,ShaderProgram.POSITION_ATTRIBUTE),
                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates,2,ShaderProgram.TEXCOORD_ATTRIBUTE + "0"));
        shape.setVertices(vertices);
        shape.setIndices(indices);
    }

    @Override
    public void beginCollide(Collidable collide_with) {

    }

    @Override
    public void colide(Collidable collide_with) {

    }

    @Override
    public void endCollide(Collidable collide_with) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {
//        texture.bind();
//        shaderProgram.bind();
//        shaderProgram.setUniformMatrix("u_projTrans", batch.getProjectionMatrix());
//        shaderProgram.setUniformi("u_texture", 0);
//        mesh.render(shaderProgram, GL20.GL_TRIANGLES);
//
        update(Gdx.graphics.getDeltaTime());
        ground_texture.bind();
        shader.bind();
        shader.setUniformMatrix("u_projTrans", MyGame.handle.getGscreen().camera.combined);
        shader.setUniformi("u_texture", 0);
        shape.render(shader, GL20.GL_TRIANGLES);
    }

    private void update(float delta) {

    }
}
