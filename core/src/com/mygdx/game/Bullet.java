package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    Sprite sprite;
    float x , y;
    Boss enemy;

    Bullet(Boss enemy){
        this.enemy = enemy;
        sprite = new Sprite();

        x = Ship.nowX + 19;
        y = Ship.nowY + 60;

        sprite.setTexture(new Texture("nb.png"));
        sprite.setBounds(x, y, 3, 6);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        y += 6;
        sprite.setY(y);
        if(y > 610) remove();
        float dop = enemy.strike(x + 1, y + 6);
        if(dop != -1){
            getStage().addActor(new Expload((int)x + 1, dop, enemy));
            remove();
        }
    }

}
