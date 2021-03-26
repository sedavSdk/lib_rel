package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class Menu implements Screen {

    Start game;
    OrthographicCamera camera;
    ImageButton button;
    Stage stage;
    Skin skin = new Skin();
    TextureAtlas atlas;
    ImageButton.ImageButtonStyle but;
    Batch batch;


    public Menu(final Start game) {
        this.game = game;
        camera = new OrthographicCamera();
        stage = new Stage(new FillViewport(300, 600, camera));
        camera.position.set(new Vector3(150, 300,3));

        atlas = new TextureAtlas(Gdx.files.internal("texture/button.atlas"));

        batch = new SpriteBatch();

        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("b_up");
        but.down = skin.getDrawable("b_down");

        button = new ImageButton(but);
        button.setBounds(100, 275, 100, 50);

        stage.addActor(new Backk());
        //stage.addActor(new Boss());
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);

        button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
               // stage.addActor(new TopDoor());
               // stage.addActor(new BottomDoor());
                NextStage n = new NextStage();
                n.goNext(stage, game);
               // game.setScreen(new GameScreen());
            }
        });
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
      //  Gdx.gl.glClearColor(0, 1, 0, 1);
      //  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        //Gdx.gl.glClearColor(0, 1, 0, 1);
       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
