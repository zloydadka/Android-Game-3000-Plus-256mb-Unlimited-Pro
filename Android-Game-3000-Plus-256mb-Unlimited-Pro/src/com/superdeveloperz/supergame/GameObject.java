package com.superdeveloperz.supergame;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public abstract class GameObject {
	public Point pt;
	public Rect bounds;
	View.OnClickListener listener;
	
	public View.OnClickListener getListener() {
		return listener;
	}

	public void setListener(View.OnClickListener listener) {
		this.listener = listener;
	}
	
	public GameObject(Point pt) {
		Log.e("GameObject", "created");
		this.pt = pt;
		bounds = new Rect();
	}

	public abstract void draw(Canvas can);
	
	
}
