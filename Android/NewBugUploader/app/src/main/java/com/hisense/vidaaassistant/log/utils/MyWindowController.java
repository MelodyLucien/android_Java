package com.hisense.vidaaassistant.log.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by zhouhao2 on 17-9-18.
 */

public class MyWindowController{

    private static final String TAG = MyWindowController.class.getSimpleName();

    private static final int WIDTH = 756;
    private static final int HEIGHT = 520;

    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams mLay = null;

    private  int mLayoutId = 0;
    private View mMainView = null;
    public boolean isShow = false;


    public MyWindowController(Context context, int layoutId){
        init(context,layoutId);
    }

    private void init(Context context,int layoutId){

        mLayoutId = layoutId;

        mMainView = LayoutInflater.from(context).inflate(mLayoutId,null);

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        //set custom width and height and alpha
        // mLay = new LayoutParams(LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,0,0,PixelFormat.TRANSPARENT);
        mLay = new WindowManager.LayoutParams();

        updateLayout(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, Gravity.CENTER,0,0);
    }

    public void showWindow(){
        if(mWindowManager != null){
            if(mMainView != null && mLay != null ){
                Log.i(TAG, "showWindow: ");
                //set default parameters
                mWindowManager.addView(mMainView,mLay);
                isShow = true;
            }
        }
    }

    public void dismissWindow(){
        if(mWindowManager != null){
            if(mMainView != null && mLay != null ){
                if(isShow) {
                    Log.i(TAG, "dimiss window: ");
                    //set default parameters
                    mWindowManager.removeView(mMainView);
                    isShow = false;
                }
            }
        }
    }

    public boolean isWindowShowing(){
        return isShow;
    }

    public void updateLayout(int type,int flag,int gravity,int x,int y) {
        mLay.type = type;
        mLay.flags= flag;
        mLay.gravity = gravity;
        mLay.width = WIDTH;
        mLay.height = HEIGHT;
        mLay.format = PixelFormat.UNKNOWN;
        mLay.x = x;
        mLay.y = y;
    }

    public interface UiUpdater{
        void initFromParent(View parentView,Context content);
    }

    public void setUiUpdater(UiUpdater uiUpdater,Context content){
        if(mMainView != null) {
            uiUpdater.initFromParent(mMainView,content);
        }
    }
}