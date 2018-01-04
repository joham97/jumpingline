package com.hebe.jumpingline.logic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class World {

	private List<Block> blocks;
	private Line line;
	
	private float camX, camY;
	
	public World() {
		blocks = new ArrayList<Block>();
		blocks.add(new Block(-100, -200, 200, 100));
		line = new Line(blocks);
	}
	
	public void update(float delta){
		for(Block block : blocks) {
			block.update(delta);
		}
		line.update(delta);
		camX = line.getHead().x;
		camY = line.getHead().y;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
		for(Block block : blocks) {
			block.render(batch, shape, font);
		}
		line.render(batch, shape, font);
	}
	
	public float getCamX() {
		return camX;
	}
	
	public float getCamY() {
		return camY;
	}
	
}
