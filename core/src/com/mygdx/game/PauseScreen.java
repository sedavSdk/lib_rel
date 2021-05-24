package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseScreen extends Actor {
    Stage stage;
    Sprite fon;
    ImageButton button_continue, button_out;
    TextureAtlas atlas;
    Skin skin = new Skin();
    ImageButton.ImageButtonStyle but;
    PauseScreen pauseScreen;
    PreGame preGame;
    Start game;
    NextStage n;
    Sound sound;

    public PauseScreen(final Stage stage, final GameScreen gameScreen, final Start game) {
        this.stage = stage;
        pauseScreen = this;

        fon = new Sprite(new Texture("pause_fon.png"));
        fon.setBounds(0, 0, 300, 600);

        atlas = new TextureAtlas(Gdx.files.internal("texture/continue.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("continue_up");
        but.down = skin.getDrawable("continue_down");

        button_continue = new ImageButton(but);
        button_continue.setBounds(100, 350, 100, 50);

        atlas = new TextureAtlas(Gdx.files.internal("texture/out.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("out_up");
        but.down = skin.getDrawable("out_down");

        button_out = new ImageButton(but);
        button_out.setBounds(100, 270, 100, 50);

        stage.addActor(button_continue);
        stage.addActor(button_out);

        sound = Gdx.audio.newSound(Gdx.files.internal("sound/nbutton.mp3"));

        button_continue.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                gameScreen.state = GameScreen.State.RUN;
                pauseScreen.remove();
                button_continue.remove();
                button_out.remove();
                stage.addActor(gameScreen.button);
            }
        });

        button_out.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                super.clicked(event, x, y);
                n = new NextStage();
                preGame = new PreGame(game);
                n.goNext(stage, game, preGame);
                gameScreen.stop_time();
            }
        });
    }

    void doors(float y){
        if(n!=null) {
            if (n.bottomDoor != null) n.bottomDoor.act(y);
            if (n.topDoor != null) n.topDoor.act(y);
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        fon.draw(batch);
    }
}
