package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Timer;
import java.util.TimerTask;

public class Ship extends Actor {
    Sprite sprite, hp_up, hp_down, line;
    float x, y, flag;
    float nowSpeed;
    int i;
    static public int flag_s = 0;
    Texture t1, t2, t3, t4, t5;
    static float nowX, nowY, divX, divY;
    float anX, anY;
    Vector2 v1, v2, speed, ax, anty_speed;
    Boss boss;
    Timer timer;
    float max_speed, axeleration;
    String s;
    BitmapFont font;
    int max_hp, now_damage;
    GameScreen gameScreen;
    Sound sound, ex;
    Texture texture, p;

    Ship(GameScreen gameScreen, Texture ex){
        this.gameScreen = gameScreen;
        texture = new Texture("nb.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/ship_shoot.mp3"));
        this.p = ex;
        this.ex = Gdx.audio.newSound(Gdx.files.internal("sound/ex.mp3"));

        max_hp = 100;
        now_damage = 0;

        speed = new Vector2();
        ax = new Vector2();
        v1 = new Vector2();
        v2 = new Vector2();
        anty_speed = new Vector2();

        setBounds(150, 230, 40, 60);

        t1 = new Texture("s1.png");
        t2 = new Texture("s2.png");
        t3 = new Texture("s3.png");
        t4 = new Texture("s4.png");
        t5 = new Texture("s5.png");


        x = 125;
        y = 230;
        nowSpeed = 0;
        i = 1;

        sprite = new Sprite(new Texture("s1.png"));
        sprite.setBounds(125, 230,41,60);

        nowX = 125;
        nowY = 230;

        v1.set(0, -100);
        speed.set(0, 0);
        axeleration = (float)0.25;
        max_speed = 5;

        line = new Sprite(new Texture("hp.png"));
        hp_up = new Sprite(new Texture("hp_up.png"));
        hp_down = new Sprite(new Texture("hp_down.png"));

        hp_up.setBounds(0, 400, 20, 10);
        hp_down.setBounds(0, 200, 20, 10);
        line.setBounds(8, 211, 10, 188);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        hp_down.draw(batch);
        hp_up.draw(batch);
        line.draw(batch);
    }

    public void start(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                flag_s = 1;
            }
        }, 1000, 1000);
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
            v2.set(x - nowX, y - nowY);
            float angle = v2.angle(v1);
            angle = (float) (angle / 180 * 3.14);
            ax.set((float) (axeleration * Math.sin(angle)), (float) (axeleration * Math.cos(angle)));
            float length = (float) Math.sqrt(((x - nowX) * (x - nowX) + (y - nowY) * (y - nowY)));
            if(length > 5)
            speed.add(ax);
            if(length < 50) speed.scl((float)0.92);
        }
        anty_speed.set((float)(-speed.x * 0.05), (float)(-speed.y * 0.05));
        speed.add(anty_speed);
        nowX -= speed.x;
        nowY -= speed.y;
        sprite.setX(nowX);
        sprite.setY(nowY);
        divX = speed.x;
        divY = speed.y;

        if(flag_s == 1) {
            shoot();
            flag_s = 0;
        }
    }
    public void shoot(){
        //sound.play();
        Bullet bullet = new Bullet(boss, texture, p);
        this.getStage().addActor(bullet);
        if(Buff.insane_boss == 1) bullet.dmg = 0;
    }
    public Pos strike(float bx, float by){
        if(by < nowY + 60 && by > nowY - 15)
        if(bx < nowX + 20 && bx >= nowX) {
            if (by - nowY < 3 * (bx - nowX)){
                ex.play();
                return new Pos(bx, 3 * (bx - nowX) + nowY);
            }
        }
         else if(bx >= nowX + 20 && bx < nowX + 40){
            if(by - nowY < -3 * (bx - nowX) + 110) {
                ex.play();
                return new Pos(bx, -3 * (bx - nowX) + 110 + nowY);
            };
        }
        return new Pos(-1, -1);
    }

}


