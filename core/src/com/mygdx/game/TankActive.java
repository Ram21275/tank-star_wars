package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
//import sun.java2d.xr.XIDGenerator;

import javax.swing.text.TabableView;
import java.security.Key;
import java.util.HashMap;

//todo: make this class, delete unnecessary setters getters;
public abstract class TankActive implements Collidable,Renderable {
    private Vector2 position;
    private float slope_angle; // radians
    private HashMap<Integer,Bullet> ammunition;
    private static int max_angle;
    private static int min_angle;
    private static int min_power;
    private static int max_power;
    private int power;
    private int angle;
    private int fuel;
    private int max_speed;
    private TankBase base;
    private TankNozzle nozzle;
    private Ground ground;
    private ActivePlayer owner;
    private Body tank_physics;
    private boolean lin;
    private boolean rin;
    private boolean lrv;
    private boolean rrv;
    private boolean input_enable = false;

    public TankActive() { //todo create a constuctor with Passive Tank as parameter
        //use this one for debug purpose only
        position = new Vector2(Gdx.graphics.getWidth()/3f,2*Gdx.graphics.getHeight()/3f);
        ammunition = new HashMap<>();
        generatePhysicsBody();
        lin = false;
        rin = false;
        lrv = false;
        rrv = false;
        ground = MyGame.handle.ground;
        MyGame.render_these.add(this);
    }
    public void generatePhysicsBody() {
        BodyDef bd = new BodyDef(); bd.type = BodyDef.BodyType.DynamicBody; bd.position.set(position.x/32f,position.y/32f); bd.fixedRotation = true;
        tank_physics = MyGame.handle.physics_world.createBody(bd);

        PolygonShape ps  = new PolygonShape(); ps.setAsBox(10f/32f,5f/32f);
        tank_physics.createFixture(ps,1.0f).setUserData(this); ps.dispose();

    }
    public void setHealth(int health){
        owner.setHealth(health);
    }
    public int getHealth(){
        return owner.getHealth();
    }

    public void update(float delta) {
        input_enable = true;// todo omit after making a contactlistener

        position.x = tank_physics.getPosition().x * 32f; // todo to normalize
        position.y = tank_physics.getPosition().y * 32f; // todo to normalize
//        slope_angle = owner.getGamescreen().getGround().getSlope(position);  // use in actual version;
        slope_angle = MyGame.handle.ground.getSlope(position); //todo to be omitted after debug

        if(input_enable) {
//            tank_physics.applyForce(new Vector2(0,-10f).rotateRad((float) (this.getSlope_angle())),tank_physics.getPosition(),false);
            moveTank(delta);
            if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
                loadAmmo();
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
                fireBullet(1);
            }
        }


    }
    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        // TODO: 18/12/22 write render class after assets have been created
    }

    public void fireBullet(int ID)
    {
        Bullet b = ammunition.get(ID);
        if(b != null)
        {
            b.spawn(tank_physics.getPosition().add(0,10/32f),100,45);
            ammunition.remove(ID);
        }
    }
    public void moveTank(float delta)
    {
        lin = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        rin = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        if (lin != lrv || rin != rrv) { tank_physics.setLinearVelocity(0,0); }
        lrv = lin; rrv = rin;
        if(lin)
        {
            Vector2 v = new Vector2(-3f,-1);
//            tank_physics.setLinearVelocity(v.rotateRad(ground.getSlope(position)));
//            tank_physics.applyForce(v,tank_physics.getPosition(),true);
            tank_physics.applyLinearImpulse(v.sub(tank_physics.getLinearVelocity()).scl(tank_physics.getMass()).rotateRad(ground.getSlope(position)),tank_physics.getPosition(),true);
            if(fuel > 0){
                fuel -= 1 * (delta);
            } else {
                fuel = 0;
            }
        }
        if (rin){

            Vector2 v = new Vector2(3f,-1);
//            tank_physics.setLinearVelocity(v.rotateRad(ground.getSlope(position)));
//            tank_physics.applyForce(v,tank_physics.getPosition(),true);
            tank_physics.applyLinearImpulse(v.sub(tank_physics.getLinearVelocity()).scl(tank_physics.getMass()).rotateRad(ground.getSlope(position)),tank_physics.getPosition(),true);
            if(fuel > 0){
                fuel -= 1 * (delta);
            } else {
                fuel = 0;
            }

        }
    }
    public void destroyTank(){

    }
    public void onHitFallBack(float accuracy) {
        System.out.println(accuracy);
    }
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getSlope_angle() {
        return slope_angle;
    }

    public void setSlope_angle(int slope_angle) {
        this.slope_angle = slope_angle;
    }

    public HashMap<Integer, Bullet> getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(HashMap<Integer, Bullet> ammunition) {
        this.ammunition = ammunition;
    }

    public static int getMax_angle() {
        return max_angle;
    }

    public static void setMax_angle(int max_angle) {
        TankActive.max_angle = max_angle;
    }

    public static int getMin_angle() {
        return min_angle;
    }

    public static void setMin_angle(int min_angle) {
        TankActive.min_angle = min_angle;
    }

    public static int getMin_power() {
        return min_power;
    }

    public static void setMin_power(int min_power) {
        TankActive.min_power = min_power;
    }

    public static int getMax_power() {
        return max_power;
    }

    public static void setMax_power(int max_power) {
        TankActive.max_power = max_power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getMax_speed() {
        return max_speed;
    }

    public  void setMax_speed(int max_speed){this.max_speed  = max_speed;};

    public TankBase getBase() {
        return base;
    }

    public void setBase(TankBase base) {
        this.base = base;
    }

    public TankNozzle getNozzle() {
        return nozzle;
    }

    public void setNozzle(TankNozzle nozzle) {
        this.nozzle = nozzle;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public ActivePlayer getOwner() {
        return owner;
    }

    public void setOwner(ActivePlayer owner) {
        this.owner = owner;
    }



    public void getAirDrop(AirDrop airdrop) {

    }
    public abstract void generateTankBase();
    public abstract void generateTankNozzle();
    public abstract void setSpeed();
    public abstract void loadAmmo();

    @Override
    public void beginCollide(Collidable collide_with) {
        if(collide_with.getClass() == ground.getClass()){
//            System.out.println("tank touched grass");
//            input_enable = true;
        } else if (collide_with.getClass() == AirDrop.class) {
            this.getAirDrop((AirDrop) collide_with);
        }
    }
    @Override
    public void endCollide(Collidable collide_with) {

        if(collide_with.getClass() == ground.getClass()){
//            tank_physics.setLinearVelocity(0,0);
//            System.out.println("tank is flying");
//            input_enable = false;
        }
    }
    @Override
    public void colide(Collidable collide_with) {

    }




}

class AbramsActive extends TankActive {
    public AbramsActive() {
        super();
        System.out.println("yolo");
    }



    @Override
    public void dispose() {

    }

    @Override
    public void generateTankBase() {

    }

    @Override
    public void generateTankNozzle() {

    }

    @Override
    public void setSpeed() {

    }

    @Override
    public void loadAmmo() {
        getAmmunition().put(1,new Bullet(this));
    }

}

class FrostActive extends TankActive {

    @Override
    public void dispose() {

    }

    @Override
    public void generateTankBase() {

    }

    @Override
    public void generateTankNozzle() {

    }

    @Override
    public void setSpeed() {

    }

    @Override
    public void loadAmmo() {
        getAmmunition().put(1,new Bullet(this));
    }
}

class BuratinoActive extends TankActive {

    @Override
    public void dispose() {

    }

    @Override
    public void generateTankBase() {

    }

    @Override
    public void generateTankNozzle() {

    }

    @Override
    public void setSpeed() {

    }

    @Override
    public void loadAmmo() {
        getAmmunition().put(1,new Bullet(this));
    }
}