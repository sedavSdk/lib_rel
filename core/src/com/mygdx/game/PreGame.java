package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

public class PreGame implements Screen {

    OrthographicCamera camera;
    Stage stage;
    ImageButton.ImageButtonStyle but;
    ImageButton button_back, button_battle, insane1, insane2, update;
    Skin skin = new Skin();
    TextureAtlas atlas;
    final public Start game;
    Sound sound;
    Diod on1, on2;
    int insane_boss, insane_ship;
    PreGame preGame = this;


    public PreGame(final Start game) {
        this.game = game;
        camera = new OrthographicCamera();
        stage = new Stage(new StretchViewport(300, 600, camera));
        camera.position.set(new Vector3(150, 300,6));
        stage.addActor(new Backk(new Texture("nn_pr.png")));

        insane_boss = 0;
        insane_ship = 0;

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas(Gdx.files.internal("texture/n_back.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("back_up");
        but.down = skin.getDrawable("back_down");

        button_back = new ImageButton(but);
        button_back.setBounds(20, 530, 100, 50);

        atlas = new TextureAtlas(Gdx.files.internal("texture/n_battle.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("battle_up");
        but.down = skin.getDrawable("battle_down");

        button_battle = new ImageButton(but);
        button_battle.setBounds(100, 20, 100, 50);

        atlas = new TextureAtlas(Gdx.files.internal("texture/insane1.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("insane1_up");
        but.down = skin.getDrawable("insane1_down");

        insane1 = new ImageButton(but);
        insane1.setBounds(180, 400, 100, 50);

        atlas = new TextureAtlas(Gdx.files.internal("texture/insane2.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("insane2_up");
        but.down = skin.getDrawable("insane2_down");

        insane2 = new ImageButton(but);
        insane2.setBounds(180, 460, 100, 50);

        atlas = new TextureAtlas(Gdx.files.internal("texture/update_but.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("update_up");
        but.down = skin.getDrawable("update_down");

        update = new ImageButton(but);
        update.setBounds(180, 127, 100, 50);

        on1 = new Diod();
        on2 = new Diod();
        on1.sprite.setBounds(150, 415, 20, 20);
        on2.sprite.setBounds(150, 475, 20, 20);
        stage.addActor(on1);
        stage.addActor(on2);

        stage.addActor(button_battle);
        stage.addActor(button_back);
        stage.addActor(insane1);
        stage.addActor(insane2);
        stage.addActor(update);
        stage.addActor(new BottomDoorOut(stage));
        stage.addActor(new TopDoorOut());

        sound = Gdx.audio.newSound(Gdx.files.internal("sound/nbutton.mp3"));

        update.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                NextStage n = new NextStage();
                Update update = new Update(game, preGame);
                n.goNext(stage, game, update);
            }
        });

        button_battle.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                NextStage n = new NextStage();
                GameScreen gameScreen = new GameScreen(game);
                n.goNext(stage, game, gameScreen);
            }
        });

        button_back.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                super.clicked(event, x, y);
                NextStage n = new NextStage();
                Menu gameScreen = new Menu(game);
                n.goNext(stage, game, gameScreen);
            }
        });

        insane1.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                super.clicked(event, x, y);
                if(insane_boss == 0){
                    insane_boss = 1;
                    on1.sprite.setTexture(new Texture("on.png"));
                }else{
                    insane_boss = 0;
                    on1.sprite.setTexture(new Texture("off.png"));
                }
            }
        });

        insane2.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                super.clicked(event, x, y);
                if(Buff.insane_ship == 0){
                    Buff.insane_ship = 1;
                    on2.sprite.setTexture(new Texture("on.png"));
                }else{
                    Buff.insane_ship = 0;
                    on2.sprite.setTexture(new Texture("off.png"));
                }
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        resume();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        stage.draw();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
