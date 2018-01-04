package com.hebe.jumpingline.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.hebe.gameutils.collision.MyRectangle;

public class Block extends MyRectangle{
	
	public Block(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update(float delta){		
		
	}
		
	public void render(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLACK);
		shape.rect(x, y, width, height);
		shape.end();
	}
	
}
