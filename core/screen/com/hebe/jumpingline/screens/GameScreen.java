package com.hebe.jumpingline.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hebe.jumpingline.JumpingLine;
import com.hebe.jumpingline.hud.HUD;
import com.hebe.jumpingline.logic.World;

public class GameScreen implements Screen{

	private JumpingLine game;

	private OrthographicCamera hudCam;
	private Viewport hudViewport;

	private OrthographicCamera cam;
	private Viewport viewport;
	
	private World world;
	
	private HUD hud;
	
	public GameScreen(JumpingLine game) {
		this.game = game;

		this.hud = new HUD();
				
		this.hudCam = new OrthographicCamera();
		this.hudCam.position.set(JumpingLine.GAME_WIDTH / 2, JumpingLine.GAME_HEIGHT / 2, 0);
		this.hudViewport = new FitViewport(JumpingLine.GAME_WIDTH, JumpingLine.GAME_HEIGHT, this.hudCam);
		this.hudViewport.apply();
		
		this.cam = new OrthographicCamera();
		this.viewport = new FitViewport(JumpingLine.GAME_WIDTH, JumpingLine.GAME_HEIGHT, this.cam);
		this.viewport.apply();

		world = new World();
	}

	private void update(float delta) {
		world.update(delta);
	}

	@Override
	public void render(float delta) {
		update(delta);

		// Camera Position
		this.viewport.getCamera().position.set(world.getCamX(), world.getCamY(), 0);
		this.cam.zoom = 1;
		this.viewport.apply();

		// Clean Display
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Attach Viewport to SpriteBatch and Shaperenderer
		this.game.getSpriteBatch().setProjectionMatrix(this.viewport.getCamera().combined);
		this.game.getShapeRenderer().setProjectionMatrix(this.viewport.getCamera().combined);
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		world.render(this.game.getSpriteBatch(), this.game.getShapeRenderer(), this.game.getFont());
		
		// Attach Viewport to SpriteBatch and Shaperenderer
		this.game.getSpriteBatch().setProjectionMatrix(this.hudViewport.getCamera().combined);
		this.game.getShapeRenderer().setProjectionMatrix(this.hudViewport.getCamera().combined);

		this.hud.draw(this.game.getSpriteBatch(), this.game.getShapeRenderer(), this.game.getFont());
		
		Gdx.gl.glDisable(GL20.GL_BLEND);

	}
	
	public HUD getHUD() {
		return this.hud;
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void show() {
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
