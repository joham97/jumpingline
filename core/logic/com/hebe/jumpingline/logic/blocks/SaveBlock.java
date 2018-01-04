package com.hebe.jumpingline.logic.blocks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SaveBlock extends Block{

	public SaveBlock(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLACK);
		shape.rect(this.x, this.y, this.width, this.height);
		shape.end();
	}
	
}
