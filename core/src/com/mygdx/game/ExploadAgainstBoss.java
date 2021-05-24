package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Boss;
import com.mygdx.game.Expload;

public class ExploadAgainstBoss extends Expload {
    Boss boss;
    public ExploadAgainstBoss(float x, float y, float max, Boss boss, Texture texture) {
        super(x, y, max, texture);
        this.boss = boss;

    }

    @Override
    public void act(float delta) {
        x += boss.step;
        y += boss.stepY;
        super.act(delta);
    }
}
