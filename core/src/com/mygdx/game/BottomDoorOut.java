package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BottomDoorOut extends Actor {
    Sprite sprite;
    Stage stage;
    BottomDoorOut(Stage stage){

        this.stage = stage;

        setBounds(0, 0, 300, 300);

        sprite = new Sprite(new Texture("bottom.png"));
        sprite.setBounds(0, 0,300,350);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if(sprite.getY() > -340) sprite.setY(sprite.getY() - 5);
        else{
            Gdx.input.setInputProcessor(stage);
            remove();
        }
    }
}
