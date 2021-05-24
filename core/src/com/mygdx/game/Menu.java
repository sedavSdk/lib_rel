package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Menu implements Screen {

    Start game;
    OrthographicCamera camera;
    ImageButton button, leave_button;
    Stage stage;
    Skin skin = new Skin();
    TextureAtlas atlas;
    ImageButton.ImageButtonStyle but;
    Batch batch;
    PreGame preGame;
    Sound sound;
    Logo logo;

    public Menu(final Start game) {
        this.game = game;
        camera = new OrthographicCamera();
        stage = new Stage(new StretchViewport(300, 600, camera));
        camera.position.set(new Vector3(150, 300,3));


        atlas = new TextureAtlas(Gdx.files.internal("texture/n_start.atlas"));

        batch = new SpriteBatch();

        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("start_up");
        but.down = skin.getDrawable("start_down");

        button = new ImageButton(but);
        button.setBounds(75, 275, 150, 75);

        atlas = new TextureAtlas(Gdx.files.internal("texture/n_out.atlas"));

        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("out_up");
        but.down = skin.getDrawable("out_down");

        leave_button = new ImageButton(but);
        leave_button.setBounds(100, 200, 100, 50);

        stage.addActor(new Backk(new Texture("nfon.png")));
        stage.addActor(button);
        stage.addActor(leave_button);
       // stage.addActor(new BottomDoorOut(stage));
       // stage.addActor(new TopDoorOut());
        logo = new Logo();
        stage.addActor(logo);
       // Gdx.input.setInputProcessor(stage);
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/nbutton.mp3"));

        button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                super.clicked(event, x, y);
                NextStage n = new NextStage();
                preGame = new PreGame(game);
                n.goNext(stage, game, preGame);
            }
        });

        leave_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.getGL20().glClearColor( 0, 0, 0, 1 );
        if(logo.a2 == 0) Gdx.input.setInputProcessor(stage);
        stage.act(delta);
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
        stage.dispose();
    }
}
