package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Start extends Game {
    @Override
    public void create() {
        setScreen(new Menu(this));
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sound/music.mp3"));
        music.setLooping(true);
        music.play();
    }
}
