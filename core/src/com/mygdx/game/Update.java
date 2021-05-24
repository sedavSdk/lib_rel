package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Update implements Screen {
    PreGame preGame;
    Stage stage;
    OrthographicCamera camera;
    Diod on1, on2, on3, on4;
    ImageButton but1, but2, but3, but4, button_back, button_use;
    ImageButton.ImageButtonStyle but;
    TextureAtlas atlas;
    Skin skin = new Skin();
    Sound sound;

    Update(final Start game, PreGame preGame){
        this.preGame = preGame;
        camera = new OrthographicCamera();
        stage = new Stage(new StretchViewport(300, 600, camera));
        camera.position.set(new Vector3(150, 300,6));
        stage.addActor(new Backk(new Texture("update.png")));
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/nbutton.mp3"));

        on1 = new Diod();
        on2 = new Diod();
        on1.sprite.setBounds(180, 535, 10, 10);
        on2.sprite.setBounds(180, 411, 10, 10);
        on3 = new Diod();
        on4 = new Diod();
        on3.sprite.setBounds(180, 287, 10, 10);
        on4.sprite.setBounds(180, 163, 10, 10);

        atlas = new TextureAtlas(Gdx.files.internal("texture/instal.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("instal_up");
        but.down = skin.getDrawable("instal_down");

        but1 = new ImageButton(but);
        but1.setBounds(195, 515, 90, 45);

        but2 = new ImageButton(but);
        but2.setBounds(195, 392, 90, 45);

        but3 = new ImageButton(but);
        but3.setBounds(195, 269, 90, 45);

        but4 = new ImageButton(but);
        but4.setBounds(195, 146, 90, 45);

        atlas = new TextureAtlas(Gdx.files.internal("texture/n_back.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("back_up");
        but.down = skin.getDrawable("back_down");

        button_back = new ImageButton(but);
        button_back.setBounds(100, 10, 100, 50);

        atlas = new TextureAtlas(Gdx.files.internal("texture/use.atlas"));
        skin.addRegions(atlas);

        but = new ImageButton.ImageButtonStyle();
        but.up = skin.getDrawable("use_up");
        but.down = skin.getDrawable("use_down");

        button_use = new ImageButton(but);
        button_use.setBounds(175, 10, 100, 50);

        stage.addActor(but1);
        stage.addActor(but2);
        stage.addActor(but3);
        stage.addActor(but4);
        stage.addActor(button_back);
       // stage.addActor(button_use);

        stage.addActor(on1);
        stage.addActor(on2);
        stage.addActor(on3);
        stage.addActor(on4);

        stage.addActor(new BottomDoorOut(stage));
        stage.addActor(new TopDoorOut());

        if(Buff.och == 1) on1.sprite.setTexture(new Texture("on.png"));
        if(Buff.auto == 1) on2.sprite.setTexture(new Texture("on.png"));
        if(Buff.dmg == 1) on3.sprite.setTexture(new Texture("on.png"));
        if(Buff.hp == 1) on4.sprite.setTexture(new Texture("on.png"));

        but1.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                if(Buff.och == 0) {
                    Buff.och = 1;
                    on1.sprite.setTexture(new Texture("on.png"));
                } else {
                    Buff.och = 0;
                    on1.sprite.setTexture(new Texture("off.png"));
                }
            }
        });
        but2.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                if(Buff.auto == 0) {
                    Buff.auto= 1;
                    on2.sprite.setTexture(new Texture("on.png"));
                } else {
                    Buff.auto = 0;
                    on2.sprite.setTexture(new Texture("off.png"));
                }
            }
        });
        but3.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                if(Buff.dmg == 0) {
                    Buff.dmg = 1;
                    on3.sprite.setTexture(new Texture("on.png"));
                } else {
                    Buff.dmg = 0;
                    on3.sprite.setTexture(new Texture("off.png"));
                }
            }
        });
        but4.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                if(Buff.hp == 0) {
                    Buff.hp = 1;
                    on4.sprite.setTexture(new Texture("on.png"));
                } else {
                    Buff.hp = 0;
                    on4.sprite.setTexture(new Texture("off.png"));
                }
            }
        });

        button_back.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sound.play();
                NextStage n = new NextStage();
                PreGame preGame1 = new PreGame(game);
                n.goNext(stage, game, preGame1);
            }
        });
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
        sound.dispose();
    }
}
