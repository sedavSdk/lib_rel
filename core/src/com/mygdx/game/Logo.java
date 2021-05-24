package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Logo extends Actor {
    Sprite s1, s2, s3, s4;
    float a1, a2;
    Logo(){
        s1 = new Sprite(new Texture("logo.png"));
        s2 = new Sprite(new Texture("wtf.png"));
        s3 = new Sprite(new Texture("wtf.png"));
        s4 = new Sprite(new Texture("name.png"));
        s2.setBounds(0, 0, 300, 600);
        s3.setBounds(0, 0, 300, 600);
        s1.setBounds(100, 430, 100, 100);
        s4.setBounds(65, 350, 170, 75);
        a1 = 1;
        a2 = 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        s4.draw(batch);
        s3.draw(batch);
        s1.draw(batch);
        s2.draw(batch);
    }

    @Override
    public void act(float delta) {
        if(a1 > 0) a1 -= (float)0.01;
        else if(a2 > 0) a2 -= (float)0.01;
        if(a1 <= 0) a1 = 0;
        if(a2 <= 0) a2 = 0;
        s2.setAlpha(a1);
        s3.setAlpha(a2);
        super.act(delta);
    }
}
