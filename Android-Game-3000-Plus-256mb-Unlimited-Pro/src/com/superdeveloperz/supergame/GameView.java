package com.superdeveloperz.supergame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.util.Log;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	GameManager gm;
	
	/**
	 * Область рисования
	 */
	private SurfaceHolder mSurfaceHolder;

	private final Context context;

	

	/**
	 * Конструктор
	 * 
	 * @param context
	 * @param attrs
	 */
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		// подписываемся на события Surface
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		System.out.println("GameView.surfaceChanged() "+width+" "+height);
		
	}

	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		Log.e("GameView", "onTouchEvent");
//		return super.onTouchEvent(event);
//	}
	


	public void surfaceCreated(SurfaceHolder holder) {
		gm.setRunning(true);
		gm.start();
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	    boolean retry = true;
	    gm.setRunning(false);
	    while (retry)
	    {
	        try
	        {
	            // ожидание завершение потока
	            gm.join();
	            retry = false;
	        }
	        catch (InterruptedException e) { }
	    }
		
	}

	public void setPornoText(String string) {
		gm = new GameManager(mSurfaceHolder, context,string);
		setOnTouchListener(gm.model);
	}



}
