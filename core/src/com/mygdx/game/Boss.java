package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

import sun.rmi.runtime.Log;

public class Boss extends Actor {
    Sprite gunLeft, gunRight, spinLeft, spinRight, main;
    Texture gunLeftt, gunRightt, spinLeftt, spinRightt, maint;
    float x, y, nextX, nextY, step, stepY, test1, test2;
    float nRLeft, nRRight;
    Ship ship;
    Vector2 v1, v2;
    BitmapFont font = new BitmapFont();
    BitmapFont font2 = new BitmapFont();
    BitmapFont font3 = new BitmapFont();
   // BitmapFont font4 = new BitmapFont();
    String str = "";
    String str2 = "";
    String str3 = "";
   // String str4 = "";
    WeaponRotator wR, wL;

    public Boss(Ship ship) {
        gunLeftt = new Texture("boss/shot.png");
        gunRightt = new Texture("boss/shot.png");
        spinLeftt = new Texture("boss/spin.png");
        spinRightt = new Texture("boss/spin.png");
        maint = new Texture("boss/boss1.png");

        v1 = new Vector2();
        v2 = new Vector2();

        this.ship = ship;

        gunLeft = new Sprite(gunLeftt);
        gunRight = new Sprite(gunRightt);
        spinLeft = new Sprite(spinLeftt);
        spinRight = new Sprite(spinRightt);
        main = new Sprite(maint);

        x = 100;
        y = 400;
        nextX = 0;
        nextY = 0;
        step = (float) 0.7;
        stepY = (float) 0.5;
        nRLeft = 0;
        nRRight = 0;

        spinLeft.setOrigin(25, 25);
        spinRight.setOrigin(25, 25);
        gunLeft.setOrigin(12, 16);
        gunRight.setOrigin(12, 16);
        main.setOrigin(79,0);

        spinRight.setBounds(x + 26, y + 91, 50, 50);
        spinLeft.setBounds(x + 4 - 79, y + 91, 50, 50);
        gunRight.setBounds(x + 94 - 79, y + 2, 20, 26);
        gunLeft.setBounds(x + 45 - 79, y + 2, 20, 26);
        main.setBounds(x, y, 160, 200);

        wL = new WeaponRotator(gunLeft);
        wR = new WeaponRotator(gunRight);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        gunRight.draw(batch);
        gunLeft.draw(batch);
        spinRight.draw(batch);
        spinLeft.draw(batch);
        main.draw(batch);

        font.draw(batch, str, 100, 100);
        font2.draw(batch, str2, 100, 80);
        font3.draw(batch, str3, 100, 60);

    }

    @Override
    public void act(float delta) {
        spinLeft.rotate(7);
        spinRight.rotate(7);

        if(nextX > 100) step -= (float) 0.05;
        if(nextX < -10) step += (float) 0.05;
        nextX += step;
        if(nextY > 40) stepY -= (float) 0.02;
        if(nextY < -20) stepY += (float) 0.02;
        nextY += stepY;

        spinRight.setBounds(x + 26 + nextX, y + 91 + nextY, 50, 50);
        spinLeft.setBounds(x + 4 + nextX - 79, y + 91 + nextY, 50, 50);
        gunRight.setBounds(x + 94 + nextX - 79, y + 2 + nextY, 20, 26);
        gunLeft.setBounds(x + 45 + nextX - 79, y + 2 + nextY, 20, 26);
        main.setBounds(x + nextX - 79, y + nextY, 160, 200);

        wL.rtt(x + 45 + nextX - 79, y + 2 + nextY, ship.x + 20, ship.y + 30, -45, 15);
        wR.rtt(x + 94 + nextX - 79, y + 2 + nextY, ship.x + 20, ship.y + 30, -15, 45);

       //str2 = String.valueOf(ship.y + -20);
       // str3 = String.valueOf(x + nextX);
    }
    float strike(float bx, float by){
       // str = String.valueOf(Math.pow(by - y - nextY - 91, 2));
        if(by >= y + nextY){
            if(bx > x + nextX - 9 && bx < x + nextX + 9) return y + nextY;
            if(bx >= x + nextX + 9 && bx <= x + nextX + 16 && by >= y + nextY - 27 + 3 * (bx - x - nextX)) return (float) (y + nextY - 27 + 3 * (bx - x - nextX));
            if(by >= y + nextY + 20){
                if(bx > x + nextX + 16 && bx < x + nextX + 39) return (float)(y + nextY + 20);
                if(bx >= x + nextX + 39 && bx <= x + nextX + 55  && by >= y + nextY + 13 + (bx - x - nextX - 36) * 2.7) return (float)(y + nextY + 13 + (bx - x - nextX - 36) * 2.7);
                if(bx > x + nextX + 55 && Math.pow(bx - x - nextX - 50, 2) + Math.pow(by - y - nextY - 114, 2) <= 1000) return (float)by;
            }
            if(bx <= x + nextX - 9 && bx >= x + nextX - 16 && by >= y + nextY - 25 - 3.2 * (bx - x - nextX)) return (float) (y + nextY - 25 - 3.2 * (bx - x - nextX));
            if(by >= y + nextY + 20){
               if(bx < x + nextX - 16 && bx > x + nextX - 37) return (float)(y + nextY + 20);
                if(bx <= x + nextX - 37 && bx >= x + nextX - 55  && by >= y + nextY + 17 - (bx - x - nextX + 37) * 2.7) return (float)(y + nextY + 17 - (bx - x - nextX + 37) * 2.7);
                if(bx < x + nextX - 55 && Math.pow(bx - x - nextX + 50, 2) + Math.pow(by - y - nextY - 114, 2) <= 1000) return (float)by;
            }

        }

        return -1;
    }
}
