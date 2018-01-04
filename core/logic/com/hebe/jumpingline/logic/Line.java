package com.hebe.jumpingline.logic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.hebe.gameutils.collision.MyLine;
import com.hebe.gameutils.collision.MyVector;
import com.hebe.jumpingline.JumpingLine;
import com.hebe.jumpingline.logic.blocks.Block;
import com.hebe.jumpingline.logic.blocks.SaveBlock;
import com.hebe.jumpingline.logic.consts.JumpingConsts;
import com.hebe.jumpingline.screens.InputHandler;

public class Line {

	private static final int LINE_WEIGHT = 4;
	
	private MyVector head;
	private List<MyVector> parts;

	private List<Block> blocks;

	private MyVector vel;

	private InputHandler inputHandler;

	public Line(List<Block> blocks, InputHandler inputHandler) {
		this.blocks = blocks;
		this.inputHandler = inputHandler;
		this.head = new MyVector(0, 0);
		this.parts = new ArrayList<MyVector>();
		this.parts.add(this.head);
		this.vel = new MyVector(0, 0);
		
		for(Block block : this.blocks) {
			if(block instanceof SaveBlock){
				this.head.x = block.x + block.width / 2;
				this.head.y = block.y + block.height + 300;
				return;
			}
		}
	}

	public void update(float delta) {
		this.vel.x = JumpingConsts.MOVE * ((this.inputHandler.getUnprojectedX() / (float) JumpingLine.GAME_WIDTH) * 2f - 1f);
		this.vel.y -= JumpingConsts.GRAVITY * delta;

		float tempX = this.head.x + this.vel.x * delta;
		float tempY = this.head.y + this.vel.y * delta;

		for (Block block : this.blocks) {
			for (int i = 0; i < block.getEdges().length; i++) {
				MyVector vec = block.getEdges()[i].collides(new MyLine(this.head.x, this.head.y, tempX, tempY));
				if (vec != null) {
					if (i == 0) { 			//LEFT
						tempX = vec.x - 1;
					} else if (i == 1) { 	//RIGHT
						tempX = vec.x + 1;
					} else if (i == 2) { 	//BOTTOM
						tempX = vec.x;
						tempY = vec.y - 1;
						this.vel.y = 0;
					} else if (i == 3) { 	//TOP
						tempX = vec.x;
						tempY = vec.y + 1;
						this.vel.y = JumpingConsts.JUMP;
					}
				}
			}
		}

		this.head = new MyVector(tempX, tempY);
		this.parts.add(this.head);
		while (getLength() > 800) {
			this.parts.remove(0);
		}
	}

	private float getLength() {
		float length = 0;
		for (int i = 0; i < this.parts.size() - 1; i++) {
			length += new MyVector(this.parts.get(i).x, this.parts.get(i).y).dst(new MyVector(this.parts.get(i + 1).x, this.parts.get(i + 1).y));
		}
		return length;
	}

	public void render(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLACK);
		for (int i = 0; i < this.parts.size() - 1; i++) {
			shape.circle(this.parts.get(i).x, this.parts.get(i).y, LINE_WEIGHT);
			shape.rectLine(this.parts.get(i).x, this.parts.get(i).y, this.parts.get(i + 1).x, this.parts.get(i + 1).y, LINE_WEIGHT * 2);
			shape.circle(this.parts.get(i + 1).x, this.parts.get(i + 1).y, LINE_WEIGHT);
		}
		shape.end();
	}

	public MyVector getHead() {
		return this.head;
	}

}
