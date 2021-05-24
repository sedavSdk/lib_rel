package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class ExploadAgainstShip extends Expload{
    Ship ship;
    public ExploadAgainstShip(float x, float y, float max, Ship ship, Texture texture) {
        super(x, y, max, texture);
        this.ship = ship;
    }

    @Override
    public void act(float delta) {
        x -= Ship.divX;
        y -= Ship.divY;
        super.act(delta);
    }
}
