package com.superdeveloperz.supergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class GameManager extends Thread
{
    private static final int FIELD_WIDTH = 300;
    private static final int FIELD_HEIGHT = 250;

   /** Область, на которой будем рисовать */
    private SurfaceHolder mSurfaceHolder;

    /** Состояние потока (выполняется или нет. Нужно, чтобы было удобнее прибивать поток, когда потребуется) */
    private boolean mRunning;

    /** Стили рисования */
    private Paint mPaint;

    /** Прямоугольник игрового поля */
    private Rect mField;

    /**
     * Конструктор
     * @param surfaceHolder Область рисования
     * @param context Контекст приложения
     */
    public GameManager(SurfaceHolder surfaceHolder, Context context)
    {
        mSurfaceHolder = surfaceHolder;
        mRunning = false;

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Style.STROKE);

        int left = 10;
        int top = 50;
        mField = new Rect(left, top, left + FIELD_WIDTH, top + FIELD_HEIGHT);
    }

    /**
     * Задание состояния потока
     * @param running
     */
    public void setRunning(boolean running)
    {
        mRunning = running;
    }

    @Override
    /** Действия, выполняемые в потоке */
    public void run()
    {
        while (mRunning)
        {
            Canvas canvas = null;
            try
            {
                // подготовка Canvas-а
                canvas = mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder)
                {
                    // собственно рисование
                    canvas.drawRect(mField, mPaint);
                }
            }
            catch (Exception e) { }
            finally
            {
                if (canvas != null)
                {
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
