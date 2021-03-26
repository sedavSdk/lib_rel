package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TopDoorOut extends Actor {
    Sprite sprite;
    TopDoorOut(){
        setZIndex(0);

        setBounds(0, 0, 300, 300);

        sprite = new Sprite(new Texture("top_door.png"));
        sprite.setBounds(0, 260,300,350);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if(sprite.getY() < 600) sprite.setY(sprite.getY() + 5);
        else remove();
    }
}
