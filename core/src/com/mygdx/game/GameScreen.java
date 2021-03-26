package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class GameScreen implements Screen {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	Stage stage;
	Tocher touch;
	int x, y, flag;
	Ship ship;
	MThread mThread;
	static int fl2 = 0;
	Boss b;

	GameScreen () {
		ship = new Ship();
		camera = new OrthographicCamera();
		stage = new Stage(new FillViewport(300, 600, camera));
		camera.position.set(new Vector3(150, 300,0));
		stage.addActor(new Backk());
		stage.addActor(ship);

		touch = new Tocher();
		stage.addListener(touch);
		b = new Boss(ship);
		stage.addActor(b);

		stage.addActor(new BottomDoorOut(stage));
		stage.addActor(new TopDoorOut());

		Gdx.input.setInputProcessor(stage);

		mThread = new MThread(stage);
		mThread.start();
	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ship.x = touch.x - 20;
		ship.y = touch.y - 30;
		ship.flag = touch.flag;

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

		if(fl2 == 1){
			stage.addActor(new Bullet(b));
			fl2 = 0;
		}
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

	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
