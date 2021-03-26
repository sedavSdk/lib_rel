package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Start extends Game {
    Menu menu;
    @Override
    public void create() {
        setScreen(new Menu(this));
    }
}
