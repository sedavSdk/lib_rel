package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen implements Screen {
	public enum State{
		PAUSE,
		RUN,
		START,
		WIN,
		LOSE,
		LOADING
	}
	State state = State.LOADING;
	SpriteBatch batch;
	Texture img, ex = new Texture("b2.png");
	OrthographicCamera camera;
	Stage stage;
	NextStage n;
	Tocher touch;
	Ship ship;
	static int fl2 = 0;
	Boss b;
	ImageButton.ImageButtonStyle but;
	ImageButton button, win_button;
	Skin skin;
	GameScreen gameScreen;
	Start game;
	PauseScreen pauseScreen;
	float y;
	Sound sound;
	ExploadAgainstBoss ex1, ex2, ex3, ex4;
	ExploadAgainstShip exS;
	static public int f1, f2, f3, fend, fend1;
	Sound exp;
	PreGame preGame;

	GameScreen (final Start game) {
		ship = new Ship(this, ex);
		camera = new OrthographicCamera();
		stage = new Stage(new StretchViewport(300, 600, camera));
		camera.position.set(new Vector3(150, 300,0));
		stage.addActor(new Backk(new Texture("nnfon.png")));
		stage.addActor(ship);
		gameScreen = this;
		this.game = game;
		f1 = 0;
		f2 = 0;
		f3 = 0;
		fend = 0;
		fend1 = 0;
		exp = Gdx.audio.newSound(Gdx.files.internal("sound/ex.mp3"));

		b = new Boss(ship, stage, this);

		ship.boss = b;

		stage.addActor(b);

		touch = new Tocher();
		stage.addListener(touch);
		Gdx.input.setInputProcessor(stage);

		skin = new Skin();
		skin.addRegions(new TextureAtlas(Gdx.files.internal("texture/npause.atlas")));

		but = new ImageButton.ImageButtonStyle();
		but.up = skin.getDrawable("npause_up");
		but.down = skin.getDrawable("npause_down");

		button = new ImageButton(but);

		button.setBounds(40, 550, 30, 30);

		sound = Gdx.audio.newSound(Gdx.files.internal("sound/button.mp3"));

		button.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				sound.play();
				if(state == State.RUN) {
					pauseScreen = new PauseScreen(stage, gameScreen, game);
					stage.addActor(pauseScreen);
					state = State.PAUSE;
					button.remove();
				}
			}
		});
		stage.addActor(button);
		stage.addActor(new BottomDoorOut(stage));
		stage.addActor(new TopDoorOut());
		Timer i = new Timer();
		state = State.LOADING;
		i.schedule(new TimerTask() {
			@Override
			public void run() {
				state = State.START;
			}
		}, 1000);
		//state = State.START;
	}

	public void stop_time(){
		ship.timer.cancel();
		b.gunL.timer.cancel();
		b.gunR.timer.cancel();
		b.gunL.flag = 0;
		b.gunR.flag = 0;
		ship.flag_s = 0;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		switch (state) {
			case WIN:
				if(f1 == 1){
					ex2 = new ExploadAgainstBoss(b.x + b.nextX - 50, b.y + b.nextY + 70, 50, b, ex);
					stage.addActor(ex2);
					exp.play();
					f1 = 0;
				}
				if(f2 == 1){
					ex3 = new ExploadAgainstBoss(b.x + b.nextX + 10, b.y + b.nextY + 90, 50, b, ex);
					stage.addActor(ex3);
					exp.play();
					f2 = 0;
				}
				if(f3 == 1){
					ex4 = new ExploadAgainstBoss(b.x + b.nextX - 150, b.y + b.nextY - 100, 300, b, ex);
					stage.addActor(ex4);
					exp.play();
					f3 = 0;
				}
				if(ex4 != null && ex4.s < 0){
					b.gunR.remove();
					b.gunL.remove();
					b.remove();

				}
				if(fend == 1){
					fend = 0;
					stage.addActor(new Backk(new Texture("win.png")));
					skin.addRegions(new TextureAtlas(Gdx.files.internal("texture/continue.atlas")));

					but = new ImageButton.ImageButtonStyle();
					but.up = skin.getDrawable("continue_up");
					but.down = skin.getDrawable("continue_down");

					win_button = new ImageButton(but);
					win_button.setBounds(100, 250, 100, 50);
					stage.addActor(win_button);

					win_button.addListener(new ClickListener(){

						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event, x, y);
							sound.play();
							n = new NextStage();
							preGame = new PreGame(game);
							n.goNext(stage, game, preGame);
							gameScreen.stop_time();
						}
					});
				}
				resume();
				stage.act(delta);
				break;
			case LOSE:
				if(fend1 == 1){
					fend1 = 0;
					stage.addActor(new Backk(new Texture("lose.png")));
					skin.addRegions(new TextureAtlas(Gdx.files.internal("texture/continue.atlas")));

					but = new ImageButton.ImageButtonStyle();
					but.up = skin.getDrawable("continue_up");
					but.down = skin.getDrawable("continue_down");

					win_button = new ImageButton(but);
					win_button.setBounds(100, 250, 100, 50);
					stage.addActor(win_button);

					win_button.addListener(new ClickListener(){

						@Override
						public void clicked(InputEvent event, float x, float y) {
							super.clicked(event, x, y);
							sound.play();
							n = new NextStage();
							preGame = new PreGame(game);
							n.goNext(stage, game, preGame);
							gameScreen.stop_time();
						}
					});
				}
			case RUN:
			case START:
				resume();
				stage.act(delta);
				break;
			case PAUSE:
				y = delta;
				if(pauseScreen!=null) pauseScreen.doors(y);
				break;
			case LOADING:
		}
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}



	@Override
	public void pause() {
	}

	public void win(){
		if(state != State.WIN){
		state = State.WIN;
		ex1 = new ExploadAgainstBoss(b.x + b.nextX, b.y + b.nextY, 50, b, ex);
		b.step = 0;
		b.stepY = 0;
		stop_time();
		stage.addActor(ex1);
		exp.play();

		Timer t1 = new Timer();
		t1.schedule(new TimerTask() {
			@Override
			public void run() {
				GameScreen.f1 = 1;
				Timer t2 = new Timer();
				t2.schedule(new TimerTask() {
					@Override
					public void run() {
						f2 = 1;
						Timer t2 = new Timer();
						t2.schedule(new TimerTask() {
							@Override
							public void run() {
								f3 = 1;
							}
						}, 180);
					}
				}, 180);
			}
		}, 180);
		Timer end = new Timer();
		end.schedule(new TimerTask() {
			@Override
			public void run() {
				fend = 1;
			}
		}, 2000);}
	}

	public void lose(){
		if(state != State.WIN && state != State.LOSE) {
			if (state != State.LOSE) {
				exS = new ExploadAgainstShip(Ship.nowX - 30, Ship.nowY - 20, 100, ship, ex);
				stage.addActor(exS);
				exp.play();
			}
			state = State.LOSE;
			Ship.nowY = -200;
			Ship.nowX = -300;
			b.step = 0;
			b.stepY = 0;
			stop_time();
			Timer tt = new Timer();
			tt.schedule(new TimerTask() {
				@Override
				public void run() {
					ship.remove();
				}
			}, 60);
			Timer end = new Timer();
			end.schedule(new TimerTask() {
				@Override
				public void run() {
					fend1 = 1;
				}
			}, 2000);
		}
	}

	@Override
	public void resume() {
		ship.x = touch.x - 20;
		ship.y = touch.y - 30;
		ship.flag = touch.flag;
	}

	public void start(){
		b.start();
		ship.start();
		state = State.RUN;
	}

	@Override
	public void hide() {

	}

	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
