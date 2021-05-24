package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BottomDoor extends Actor {
    Sprite sprite;
    Start game;
    Screen screen;
    Stage stage;
    BottomDoor(Start game, Screen screen, Stage stage){
    this.screen = screen;
        this.game = game;
        this.stage = stage;

        setBounds(0, 0, 300, 300);

        sprite = new Sprite(new Texture("bottom.png"));
        sprite.setBounds(0, -340,300,350);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if(sprite.getY() < 0) sprite.setY(sprite.getY() + 7);
        else{
            game.setScreen(screen);
            stage.dispose();
        }
    }
}
