package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.util.Random;

import sun.rmi.runtime.Log;

public class Boss extends Actor {
    Sprite  spinLeft, spinRight, main, shield, test;
    Texture  spinLeftt, spinRightt, maint;
    float x, y, nextX, nextY, step, stepY;
    float nRLeft, nRRight;
    Ship ship;
    Vector2 v1, v2;
    PrimitiveGun gunR, gunL;
    int max_hp = 100;
    int now_dmg = 0;
    Sprite hp_line;
    float s_al, hp_start;
    GameScreen gameScreen;
    Sound ex;

    public Boss(Ship ship, Stage stage, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        ex = Gdx.audio.newSound(Gdx.files.internal("sound/ex.mp3"));

        spinLeftt = new Texture("boss/spin.png");
        spinRightt = new Texture("boss/spin.png");
        maint = new Texture("boss/n_state.png");

        v1 = new Vector2();
        v2 = new Vector2();

        this.ship = ship;

        spinLeft = new Sprite(spinLeftt);
        spinRight = new Sprite(spinRightt);
        main = new Sprite(maint);
        shield = new Sprite(new Texture("boss/shield.png"));
        shield.setBounds(x, y, 600, 600);

        x = 100;
        y = 400;
        nextX = 50;
        nextY = 200;
        step = (float) 0;
        stepY = (float) 0;
        nRLeft = 0;
        nRRight = 0;
        hp_start = 0;

        gunR = new PrimitiveGun(this, -15, 45, x + 94 - 79 , y + 2, ship, 20, 26);
        stage.addActor(gunR);
        gunL = new PrimitiveGun(this, -45, 15, x + 45 - 79 , y + 2, ship, 20, 26);
        stage.addActor(gunL);

        spinLeft.setOrigin(25, 25);
        spinRight.setOrigin(25, 25);
        main.setOrigin(79,0);

        spinRight.setBounds(x + 26, y + 91, 50, 50);
        spinLeft.setBounds(x + 4 - 79, y + 91, 50, 50);
        main.setBounds(x, y, 160, 200);

        hp_line = new Sprite(new Texture("nb.png"));
        s_al = 1;
        if(Buff.hp == 1) max_hp = 150;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spinRight.draw(batch);
        spinLeft.draw(batch);
        main.draw(batch);
        hp_line.draw(batch);
        shield.draw(batch);
    }

    @Override
    public void act(float delta) {
        spinLeft.rotate(-7);
        spinRight.rotate(7);

        gunR.sprite.setBounds(x + 94 - 79 + nextX, y + 2 + nextY, 20, 26);
        gunL.sprite.setBounds(x + 45 - 79 + nextX, y + 2 + nextY, 20, 26);

        spinRight.setBounds(x + 26 + nextX, y + 91 + nextY, 50, 50);
        spinLeft.setBounds(x + 4 + nextX - 79, y + 91 + nextY, 50, 50);
        main.setBounds(x + nextX - 79, y + nextY, 160, 200);
        shield.setBounds(x + nextX - 340, y + nextY - 50, 700, 350);

        if (s_al > 0) s_al -= 0.05;
        shield.setAlpha(s_al);

        if (Math.pow(ship.sprite.getX() - x - nextX, 2) + Math.pow(ship.sprite.getY() - y - nextY - 230, 2) < 350 * 350)
            crash();
        //    str = String.valueOf(Math.pow(ship.sprite.getX() - x - nextX, 2) + Math.pow(ship.sprite.getY() - y - nextY - 230, 2));
        if(gameScreen.state == GameScreen.State.RUN) {
            hp_line.setBounds(75, 590, 150 * (max_hp - now_dmg) / max_hp, 10);
            if(x + nextX - 20 < ship.sprite.getX() && Math.abs(x + nextX - 20 - ship.sprite.getX()) > 50 && step < 0.8) step += (float) 0.05;
            else if(step > -0.8 && Math.abs(x + nextX - 20 - ship.sprite.getX()) > 50 ) step -= 0.05;
            nextX += step;
            if (nextY > 50) stepY -= (float) 0.01;
            if (nextY < 50) stepY += (float) 0.01;
            nextY += stepY;
        }
        if(gameScreen.state == GameScreen.State.START){
            nextY-=1;
            hp_line.setBounds(75, 590, hp_start, 10);
            if(hp_start < 150) hp_start+=1;
            if(nextY < 30) gameScreen.start();
        }
        if(gameScreen.state == GameScreen.State.LOSE){
            nextY+=1;
        }
    }

    private void crash() {
        ship.speed.add(0, 20);
        s_al = 1;
    }

    public void start(){
        gunR.start();
        gunL.start();
    }

    float strike(float bx, float by){
        if(by >= y + nextY){
            if(bx > x + nextX - 9 && bx < x + nextX + 9){
                ex.play();
                return y + nextY;
            }
            if(bx >= x + nextX + 9 && bx <= x + nextX + 16 && by >= y + nextY - 27 + 3 * (bx - x - nextX)){
                ex.play();
                return (float) (y + nextY - 27 + 3 * (bx - x - nextX));
            }
            if(by >= y + nextY + 20){
                if(bx > x + nextX + 16 && bx < x + nextX + 39){
                    ex.play();
                    return (float)(y + nextY + 20);
                }
                if(bx >= x + nextX + 39 && bx <= x + nextX + 55  && by >= y + nextY + 13 + (bx - x - nextX - 36) * 2.7){
                    ex.play();
                    return (float)(y + nextY + 13 + (bx - x - nextX - 36) * 2.7);
                }
                if(bx > x + nextX + 55 && Math.pow(bx - x - nextX - 50, 2) + Math.pow(by - y - nextY - 114, 2) <= 1000){
                    ex.play();
                    return (float)by;
                }
            }
            if(bx <= x + nextX - 9 && bx >= x + nextX - 16 && by >= y + nextY - 25 - 3.2 * (bx - x - nextX)){
                ex.play();
                return (float) (y + nextY - 25 - 3.2 * (bx - x - nextX));
            }
            if(by >= y + nextY + 20){
               if(bx < x + nextX - 16 && bx > x + nextX - 37){
                   ex.play();
                   return (float)(y + nextY + 20);
               }
                if(bx <= x + nextX - 37 && bx >= x + nextX - 55  && by >= y + nextY + 19 - (bx - x - nextX + 37) * 2.7){
                    ex.play();
                    return (float)(y + nextY + 19 - (bx - x - nextX + 37) * 2.7);
                }
                if(bx < x + nextX - 55 && Math.pow(bx - x - nextX + 50, 2) + Math.pow(by - y - nextY - 114, 2) <= 1000){
                    ex.play();
                    return (float)by;
                }
            }

        }

        return -1;
    }
}
