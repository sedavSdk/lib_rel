package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PrimitiveBullet extends Actor {
    float x, y;
    Sprite sprite;
    float angle;
    Ship ship;
    int dmg;
    public Vector2 speed;
    Texture texture, ex;

    public PrimitiveBullet(/*float x, float y, float angle, */Ship ship, Texture texture, Texture ex) {
        //this.angle = (float)(angle / 180 * 3.14);
        this.texture = texture;
       // this.x = x;
       // this.y = y;
        this.ex = ex;
        this.ship = ship;
        sprite = new Sprite(texture);
        //sprite.setBounds(x, y, 3, 3);
        sprite.setBounds(-10, -10, 3, 3);
        dmg = 10;
        if(Buff.dmg == 1) dmg = 15;
        speed = new Vector2();
        //speed.set((float)(Math.sin(this.angle) * 5), (float)(Math.cos(this.angle) * 5));
    }

    public void begin(float x, float y, float angle){
        this.x = x;
        this.y = y;
        this.angle = (float)(angle / 180 * 3.14);
        sprite.setBounds(x, y, 3, 3);
        speed.set((float)(Math.sin(this.angle) * 5), (float)(Math.cos(this.angle) * 5));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(y > -5)
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        y -= speed.y;
        x += speed.x;
        if(Buff.auto == 1){
            if(Ship.nowX + 20 < x) speed.add((float)-0.1, 0);
            else speed.add((float)0.1, 0);
            speed.scl((float)5 / speed.len());
        }
        sprite.setBounds(x, y, 3, 3);
        Pos tr = ship.strike(x, y);
        if(tr.x != -1 && tr.y != -1){
            getStage().addActor(new ExploadAgainstShip(tr.x, tr.y, 5, ship, ex));
            x = -20;
            y = -20;
            ship.now_damage += dmg;
            float dop = (float)ship.now_damage / ship.max_hp;
            if(dop >= 1) {
                dop = 1;
                ship.gameScreen.lose();
            }
            ship.line.setBounds(8, 211, 10, 188 - 188 * dop);
            speed.set(0, 0);
            sprite.setX(-20);
            sprite.setY(-20);
            //remove();
        }
    }
}
