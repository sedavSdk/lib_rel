package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class MThread extends Thread{
    Stage stage;
    MThread(Stage stage){
        this.stage = stage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GameScreen.fl2 = 1;

        }
        }
}
