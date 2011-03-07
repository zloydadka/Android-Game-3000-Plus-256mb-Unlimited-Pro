package com.superdeveloperz.supergame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	GameManager gm;
	
	/**
	 * Область рисования
	 */
	private SurfaceHolder mSurfaceHolder;

	/**
	 * Конструктор
	 * 
	 * @param context
	 * @param attrs
	 */
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// подписываемся на события Surface
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		
		gm = new GameManager(mSurfaceHolder, context);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

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



}
