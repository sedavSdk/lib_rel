package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class
Expload extends Actor {
    public float x, y;
    Sprite sprite;
    float s;
    float max = 10;

    public Expload(float x, float y, float max, Texture texture) {
        this.x = x;
        this.y = y;
        this.max = max;
        s = (float) 0.04;

        sprite = new Sprite(texture);
        sprite.setBounds(x, y, this.max, this.max);
        sprite.setOrigin(this.max / 2, this.max / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
      //  x += enemy.step;
      //  y += enemy.stepY;
        sprite.setY(y);
        sprite.setX(x);
        sprite.scale(s);
        s -= (float) 0.01;
        if(s < -0.1) remove();
    }
}
