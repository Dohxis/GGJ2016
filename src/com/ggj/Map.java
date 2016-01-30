package com.ggj;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class Map {
    public int width = Gdx.graphics.getWidth();
    public int height = Gdx.graphics.getHeight();
    public int numOfHouses = 4;
    public int houseSizeX = 31;
    public int houseSizeY = 25;

    Array<House> houses;

    World world;
    RayHandler rayHandler;

    public Map(World world, RayHandler rayHandler){
        this.world = world;
        houses = new Array<House>();
        this.rayHandler = rayHandler;
    }


    //                           Min + (int)(Math.random() * ((Max - Min) + 1))
    public void generate(){

        // boundaries
        createCollision(0, height, width, 0);  //top
        createCollision(0, 0, width, 0);       //down
        createCollision(0, 0, 0, height);      //left
        createCollision(width, 0, 0, height);  //right

        // houses

            for(int i=0; i < numOfHouses; i++){
                int houseX = (10) + (int)(Math.random() * (((width - houseSizeX - 10) - (10)) + 1));
                int houseY = 10 + (int)(Math.random() * ((houseX - 10) + 1));
                if(!isOccupiedByHouses(i, houseX, houseY)){
                    createCollision(houseX, houseY, houseSizeX, houseSizeY);
                    houses.add(new House(houseX, houseY));
                    PointLight light = new PointLight(rayHandler, 100, new Color(1, 1, 0, .5f), 150, houseX, houseY);
            }
            else{
                //System.out.println("Failed");
                i--;
            }
        }

        // fires

    }

    void createCollision(int x, int y, int width, int height){
        BodyDef def = new BodyDef();
        def.position.set(new Vector2(x, y));

        Body body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        body.createFixture(shape, 0.0f);

        shape.dispose();
    }

    boolean isOccupiedByHouses(int untilWhich, int houseX, int houseY){
        boolean occupied = false;
        for(int i=0; i < untilWhich; i++){
            if(Math.abs(houseX - houses.get(i).x) < houseSizeX*3 && Math.abs(houseY - houses.get(i).y) < houseSizeY*3) occupied = true;
        }
        return occupied;
    }

    public void renderAll(SpriteBatch batch){
        for(int i = 0; i < houses.size; i++){
            houses.get(i).render(batch);
        }
    }
}
