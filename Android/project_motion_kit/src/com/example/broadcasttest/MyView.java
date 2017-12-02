package com.example.broadcasttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;


import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class MyView extends View {

    Paint mPaint = null;
    Path mPath = new Path();

    public MyView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("ZHOUHAO2"," onDraw");

        canvas.drawColor(Color.WHITE);

        canvas.drawPath(mPath, mPaint);
    }


   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();   
        int y = (int) event.getY();   
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mPath.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:

                mPath.lineTo(x, y);
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();
        return true;
    }*/
    
    public boolean updatePoints(float x,float y,int action){
    	
    	Log.i("zhouhao2"," update x:"+x+" y:"+y+" action:"+action);
    	  switch (action) {
          case MotionEvent.ACTION_DOWN:

              mPath.moveTo(x, y);
              break;

          case MotionEvent.ACTION_MOVE:

              mPath.lineTo(x, y);
              break;

          case MotionEvent.ACTION_UP:
              break;
      }
        invalidate();
    	return false;
    }
}


