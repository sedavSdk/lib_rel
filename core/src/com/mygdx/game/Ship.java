package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ship extends Actor {
    Sprite sprite;
    float x, y, flag;
    float nowSpeed;
    int i;
    Texture t1, t2, t3, t4, t5;
    static float nowX, nowY;
    float anX, anY;
    Ship(){
        setBounds(150, 230, 40, 60);

        t1 = new Texture("s1.png");
        t2 = new Texture("s2.png");
        t3 = new Texture("s3.png");
        t4 = new Texture("s4.png");
        t5 = new Texture("s5.png");


        x = 150;
        y = 230;
        nowSpeed = 1;
        i = 1;

        sprite = new Sprite(new Texture("s1.png"));
        sprite.setBounds(150, 230,41,60);

        nowX = 150;
        nowY = 230;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        nowX = sprite.getX();
        nowY = sprite.getY();
        i++;
        if(i == 6) i = 1;
        switch (i){
            case 1:
                sprite.setTexture(t1);
                break;
            case 2:
                sprite.setTexture(t2);
                break;
            case 3:
                sprite.setTexture(t3);
                break;
            case 4:
                sprite.setTexture(t4);
                break;
            case 5:
                sprite.setTexture(t5);
                break;
        }
        if (flag == 1) {
            double le = Math.sqrt((x - nowX) * (x - nowX) + (y - nowY) * (y - nowY));
            float le2 = (float) le;
            if (le < 4) {
                le2 = 0;
                nowSpeed = 1;
            }
            le2 = le2 / nowSpeed;
            if (le > nowSpeed && nowSpeed < 5) nowSpeed += 0.5;
            if (le < 25 && nowSpeed > 1) nowSpeed -= 0.5;
            if (le2 != 0) {
                anX = (x - nowX) / le2 + nowX;
                anY = (y - nowY) / le2 + nowY;
                sprite.setX(anX);
                sprite.setY(anY);
            }
        }
    }

}
