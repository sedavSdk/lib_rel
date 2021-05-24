package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TopDoorOut extends Actor {
    Sprite sprite;
    TopDoorOut(){
        setBounds(0, 0, 300, 300);

        sprite = new Sprite(new Texture("top_door.png"));
        sprite.setBounds(0, 260,300,350);
        setZIndex(10);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if(sprite.getY() < 600) sprite.setY(sprite.getY() + 7);
        else remove();
    }
}
