package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class
Expload extends Actor {
    float x, y;
    Sprite sprite;
    float s;
    Boss enemy;
    public Expload(float x, float y, Boss enemy) {
        this.x = x;
        this.y = y;
        s = (float) 0.04;
        this.enemy = enemy;

        sprite = new Sprite(new Texture("b2.png"));
        sprite.setBounds(x, y, 1, 1);
        //sprite.setOrigin(5, 5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        x += enemy.step;
        y += enemy.stepY;
        sprite.setY(y);
        sprite.setX(x);
        //sprite.scale(s);
        //s -= (float) 0.01;
        if(s < -0.1) remove();
    }
}
