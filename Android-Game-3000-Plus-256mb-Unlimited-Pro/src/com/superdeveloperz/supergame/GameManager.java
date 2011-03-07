package com.superdeveloperz.supergame;

import java.util.Arrays;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.SurfaceHolder;

public class GameManager extends Thread {
	private static final int FIELD_WIDTH = 300;
	private static final int FIELD_HEIGHT = 250;
	// private static final String FONT_PATH = "Verdana.ttf";

	private static final String TEXT = "Я дрочистый изумруд пососи мои яйца";
	/** Область, на которой будем рисовать */
	private SurfaceHolder mSurfaceHolder;

	/**
	 * Состояние потока (выполняется или нет. Нужно, чтобы было удобнее
	 * прибивать поток, когда потребуется)
	 */
	private boolean mRunning;

	/** Стили рисования */
	private Paint mPaint;

	// private Pizdarik pizdarik;
	// private Paint mScorePaint;
	// private Paint mScorePaint1;
	public GameModel model;
	private GameListener gameListener;
	private Paint gameCompletedText;
	private Bitmap eBlack;

	// public void collide(MotionEvent event) {
	// Log.e("GameManager", "collide");
	// float rawX = event.getX();
	// float rawY = event.getY();
	// Log.e("GameManager", "collide rawX = "+rawX +" rawY = "+rawY+
	// " pizdarik.pt.x = "+pizdarik.pt.x+ " pizdarik.pt.y "+pizdarik.pt.y);
	// if(rawX > pizdarik.pt.x && rawX < (pizdarik.pt.x + pizdarik.w)){
	// if(rawY > pizdarik.pt.y && rawY < (pizdarik.pt.y + pizdarik.h)){
	// Log.e("GameManager", "collide true");
	// pizdarik.move(0,10);
	// //mRunning = false;
	// }
	// }
	// //pizdarik.pt.y = pizdarik.pt.y + 10;
	// Log.e("GameManager", "collide false");
	// }
	//
	/**
	 * Конструктор
	 * 
	 * @param surfaceHolder
	 *            Область рисования
	 * @param context
	 *            Контекст приложения
	 */
	public GameManager(SurfaceHolder surfaceHolder, Context context) {
		mSurfaceHolder = surfaceHolder;
		mRunning = false;

		String[] words = TEXT.split(" ");
		model = new GameModel();
		gameListener = new GameListener(words);
		
		
		gameCompletedText = new Paint();
		gameCompletedText.setTextSize(32);
		gameCompletedText.setStrokeWidth(1);
		gameCompletedText.setStyle(Style.FILL);
		gameCompletedText.setTextAlign(Paint.Align.CENTER);
		gameCompletedText.setColor(Color.GREEN);
		gameCompletedText.setAntiAlias(true);
		Typeface tf = Typeface.createFromAsset(context.getAssets(), "Verdana.ttf");

		gameCompletedText.setTypeface(tf);
		//int i = 0;
		Random rand = new Random(System.currentTimeMillis());
		for (String s : words) {
			//i += 40;
			int nextX = 1+rand.nextInt(10);
			int nextY = 1+rand.nextInt(10);
			TextObject object = new TextObject(context, gameListener,
					new Point(20 * nextX, 20 * nextY), s);
			model.addGameObject(object);
			gameListener.addtObject(object);
			
		}
		 Resources res = context.getResources();
		eBlack = BitmapFactory.decodeResource(res, R.drawable.black);
	}

	/**
	 * Задание состояния потока
	 * 
	 * @param running
	 */
	public void setRunning(boolean running) {
		mRunning = running;
	}

	@Override
	/** Действия, выполняемые в потоке */
	public void run() {
		while (mRunning) { 
			Canvas canvas = null;
			try {
				// подготовка Canvas-а
				canvas = mSurfaceHolder.lockCanvas();
				synchronized (mSurfaceHolder) {
					if(!gameListener.isCompleted){
					  model.drawModel(canvas);
					}else{
						canvas.drawColor(Color.BLACK);
						canvas.drawBitmap(eBlack, canvas.getWidth()/2, 0,null);
						canvas.drawText("Пошел на хуй, злодей!", canvas.getWidth()/2, canvas.getHeight()/2, gameCompletedText);
					}
				}
			} catch (Exception e) {
			} finally {
				if (canvas != null) {
					mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
}
