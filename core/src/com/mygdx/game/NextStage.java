package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class NextStage {
    Stage stage;
    TopDoor topDoor;
    BottomDoor bottomDoor;
    Start game;
    public void goNext (Stage nowStage, Start game, Screen screen){
        stage = nowStage;

        topDoor = new TopDoor();
        bottomDoor = new BottomDoor(game, screen, stage);

        this.game = game;

        stage.addActor(topDoor);
        stage.addActor(bottomDoor);
    }

}
