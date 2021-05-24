package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Timer;
import java.util.TimerTask;

public class PrimitiveGun extends Actor {
    Boss boss;
    WeaponRotator weaponRotator;
    float granL, granR, x, y;
    Sprite sprite, test;
    Ship ship;
    float width, height;
    Timer timer, t1;
    public int flag = 0;
    Sound sound;
    Texture texture, ex;
    int count, count1;
    PrimitiveBullet primitiveBullet[];

    public PrimitiveGun(Boss boss, float granL, float granR, float x, float y, Ship ship, float width, float height) {
        this.boss = boss;
        this.granL = granL;
        this.granR = granR;
        this.ship = ship;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        count = 0;
        count1 = 0;

        texture = new Texture("bul.png");
        ex = new Texture("b2.png");

        sound = Gdx.audio.newSound(Gdx.files.internal("sound/shoot.mp3"));

        sprite = new Sprite(new Texture("boss/shot.png"));

        sprite.setBounds(x, y, 20, 26);
        sprite.setOrigin(11, 16);

        weaponRotator = new WeaponRotator(sprite);

        t1 = new Timer();

        primitiveBullet = new PrimitiveBullet[20];
        for(int i = 0; i < 20; i++){
            primitiveBullet[i] = new PrimitiveBullet(ship, texture, ex);
           ship.getStage().addActor(primitiveBullet[i]);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public void start(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                t1 = new Timer();
                t1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        count1++;
                        flag = 1;
                        if(Buff.och == 1){
                            if(count1 == 5){
                                t1.cancel();
                                count1 = 0;
                            }

                        }
                       else if(count1 == 3){
                           t1.cancel();
                           count1 = 0;
                       }
                    }
                }, 80, 80);
            }
        }, 1000, 1000);
    }

    @Override
    public void act(float delta) {
        x += boss.step;
        y += boss.stepY;
        weaponRotator.rtt(x, y, ship.nowX - 40 , ship.nowY, granL, granR);
        if(flag == 1){
            //sound.play();
            flag = 0;
            primitiveBullet[count].begin(sprite.getX() + 8 + (float)Math.sin(sprite.getRotation() / 180 * 3.14) * 13, sprite.getY() + 13 - (float)Math.cos(sprite.getRotation() / 180 * 3.14) * 13, sprite.getRotation());
            //PrimitiveBullet primitiveBullet = new PrimitiveBullet(sprite.getX() + 8 + (float)Math.sin(sprite.getRotation() / 180 * 3.14) * 13, sprite.getY() + 13 - (float)Math.cos(sprite.getRotation() / 180 * 3.14) * 13, sprite.getRotation(), ship, texture, ex);
            //boss.getStage().addActor(primitiveBullet[count]);
            if(Buff.insane_ship == 1) primitiveBullet[count].dmg = 0;
            count++;
            if(count > 19) count = 0;
        }
    }

}
