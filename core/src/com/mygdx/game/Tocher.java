package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Tocher extends InputListener {
    int x, y, flag = 0;
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.x = (int) x;
        this.y = (int) y;
        flag = 1;
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        flag = 0;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        this.x = (int) x;
        this.y = (int) y;
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
        return true;
    }
}
