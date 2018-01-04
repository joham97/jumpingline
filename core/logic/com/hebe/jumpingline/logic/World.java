package com.hebe.jumpingline.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hebe.jumpingline.logic.blocks.Block;
import com.hebe.jumpingline.logic.blocks.SaveBlock;
import com.hebe.jumpingline.screens.InputHandler;

public class World {

	private List<Block> blocks;
	private Line line;
	
	private float camX, camY;
	
	private InputHandler inputHandler;
	
	public World(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		this.blocks = new ArrayList<Block>();
		importWorld();
		this.line = new Line(this.blocks, this.inputHandler);
	}
	
	public void update(float delta){
		for(Block block : this.blocks) {
			block.update(delta);
		}
		this.line.update(delta);
		this.camX = this.line.getHead().x;
		this.camY = this.line.getHead().y;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
		for(Block block : this.blocks) {
			block.render(batch, shape, font);
		}
		this.line.render(batch, shape, font);
	}
	
	public void importWorld(){
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(Gdx.files.internal("../core/assets/World.txt").file()));
			String line = null;
			while((line = bufferedReader.readLine())!= null){
				if(line.equals("Rect")){
					int x1 = 2 * Integer.parseInt(bufferedReader.readLine());
					int y1 = -2 * Integer.parseInt(bufferedReader.readLine());
					int x2 = 2 * Integer.parseInt(bufferedReader.readLine());
					int y2 = -2 * Integer.parseInt(bufferedReader.readLine());
					this.blocks.add(new Block(x1, y2, x2-x1, y1-y2));
				}else if(line.equals("Save Rect")){
					int x1 = 2 * Integer.parseInt(bufferedReader.readLine());
					int y1 = -2 * Integer.parseInt(bufferedReader.readLine());
					int x2 = 2 * Integer.parseInt(bufferedReader.readLine());
					int y2 = -2 * Integer.parseInt(bufferedReader.readLine());
					this.blocks.add(new SaveBlock(x1, y2, x2-x1, y1-y2));
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public float getCamX() {
		return this.camX;
	}
	
	public float getCamY() {
		return this.camY;
	}
	
}
