package com.superdeveloperz.supergame;

import java.util.ArrayList;
import java.util.Collection;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameModel implements View.OnTouchListener {
	Collection<GameObject> gameObjects;

	public GameModel() {
		gameObjects = new ArrayList<GameObject>();
		
	}

	public void drawModel(Canvas canvas) {
		clearCanvas();
		Collection<GameObject> objects = gameObjects;
		for (GameObject gameObject : objects) {
			gameObject.draw(canvas);
		}
	}

	public void addGameObject(GameObject object) {
		gameObjects.add(object);
	}

	private void clearCanvas() {
		// TODO Auto-generated method stub
	}

	public void onClick(View v) {

		Log.e("GameModel", "onClick");
		Collection<GameObject> objects = gameObjects;
		for (GameObject gameObject : objects) {
			gameObject.getListener().onClick(v);
		}
	}

	public boolean onTouch(View view, MotionEvent event) {
		Collection<GameObject> objects = gameObjects;
		if (event.getAction() != MotionEvent.ACTION_DOWN) return false;
		for (GameObject gameObject : objects) {
			if(isIntercept(gameObject,event)){
				gameObject.getListener().onClick(view);
			}
		}
		return true;
	}

	private boolean isIntercept(GameObject gameObject, MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
//		Log.e("GameModel", String.format(" %1$s  %2$s (%3$s %4$s %5$s %6$s)", 
//				x, y, gameObject.bounds.left, gameObject.bounds.top,gameObject.bounds.right, gameObject.bounds.bottom));
		return gameObject.bounds.contains(x, y);
	}
}
