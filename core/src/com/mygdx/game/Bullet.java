package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    Sprite sprite;
    float x , y;
    Boss enemy;
    int dmg;
    Texture texture, ex;

    Bullet(Boss enemy, Texture texture, Texture ex){
        this.enemy = enemy;
        this.texture = texture;
        this.ex = ex;
        sprite = new Sprite();
        dmg = 5;

        x = Ship.nowX + 19;
        y = Ship.nowY + 60;

        sprite.setTexture(texture);
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
            enemy.now_dmg += dmg;
            getStage().addActor(new ExploadAgainstBoss((int)x + 1, dop, 10, enemy, ex));
            if(enemy.max_hp <= enemy.now_dmg){
                enemy.gameScreen.win();
            }
            remove();
        }
    }

}
