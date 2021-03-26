package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.swing.Spring;

public class Backk extends Actor {
    Sprite sprite;
    Backk(){

        setBounds(0, 0, 300, 600);

        sprite = new Sprite(new Texture("fon.png"));
        sprite.setBounds(0, 0,300,600);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
