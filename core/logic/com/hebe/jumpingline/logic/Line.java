package com.hebe.jumpingline.logic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.hebe.gameutils.collision.MyLine;
import com.hebe.gameutils.collision.MyVector;
import com.hebe.jumpingline.JumpingLine;
import com.hebe.jumpingline.logic.consts.JumpingConsts;

public class Line {

	private MyVector head;
	private List<MyVector> parts;
	
	private List<Block> blocks;
	
	private MyVector vel;
	
	public Line(List<Block> blocks) {
		this.blocks = blocks;
		head = new MyVector(0, 0);
		parts = new ArrayList<MyVector>();
		parts.add(head);
		vel = new MyVector(0, 0);
	}
	
	public void update(float delta){		
		vel.x = JumpingConsts.MOVE * ((Gdx.input.getX() / (float)JumpingLine.GAME_WIDTH)*2f-1f);
		vel.y -= JumpingConsts.GRAVITY * delta;
		
		float tempX = head.x + vel.x * delta;
		float tempY = head.y + vel.y * delta;
		
		for(Block block : blocks) {
			MyVector vec = block.collisionIntersection(new MyLine(head.x, head.y, tempX, tempY)); 
			if(vec != null) {
				tempX = vec.x;
				tempY = vec.y;
				vel.y = JumpingConsts.JUMP;
			}
		}
		
		head = new MyVector(tempX, tempY);
		parts.add(head);
		while(getLength() > 800) {
			parts.remove(0);
		}
	}
	
	private float getLength() {
		float length = 0;
		for(int i = 0; i < parts.size() - 1; i++) {
			length += new MyVector(parts.get(i).x, parts.get(i).y).dst(new MyVector(parts.get(i+1).x, parts.get(i+1).y));
		}
		return length;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLACK);
		for(int i = 0; i < parts.size() - 1; i++) {
			shape.rectLine(parts.get(i).x, parts.get(i).y, parts.get(i+1).x, parts.get(i+1).y, 5);
		}
		shape.end();
	}
	
	public MyVector getHead() {
		return head;
	}
	
}
