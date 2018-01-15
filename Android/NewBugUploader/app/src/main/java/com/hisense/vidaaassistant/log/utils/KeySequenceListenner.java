package com.hisense.vidaaassistant.log.utils;

import android.util.Log;
import android.view.KeyEvent;
import java.util.Arrays;

/**
 * Created by zhouhao2 on 17-9-18.
 */

public class KeySequenceListenner {

    private static final int KEYS_LENGTH = 8;
    private int[] mKeyHistory = new int[KEYS_LENGTH];
    private int mNextIndex = 0;
    private static final int[] TRIGER_SEQUENCE = new int[]{
            KeyEvent.KEYCODE_DPAD_UP,
            KeyEvent.KEYCODE_DPAD_UP,
            KeyEvent.KEYCODE_DPAD_DOWN,
            KeyEvent.KEYCODE_DPAD_DOWN,
            KeyEvent.KEYCODE_DPAD_LEFT,
            KeyEvent.KEYCODE_DPAD_LEFT,
            KeyEvent.KEYCODE_DPAD_RIGHT,
            KeyEvent.KEYCODE_DPAD_RIGHT
    };

    private static  final String TAG = KeySequenceListenner.class.getSimpleName();


    public KeySequenceListenner(){
        for (int i = 0; i < mKeyHistory.length; i++) {
            mKeyHistory[i]=0;
        }
    }

    public boolean shouldPopWindow(int keycode){
        pushKey(keycode);
        return checkKeys();
    }

    private void pushKey(int keycode){
        if(mNextIndex > KEYS_LENGTH-1){
            for (int i = 0; i < KEYS_LENGTH - 1 ; i++) {
                mKeyHistory[i] = mKeyHistory[i+1];
            }
            mKeyHistory[ KEYS_LENGTH-1] = keycode;
        } else {
            mKeyHistory[mNextIndex] = keycode;
            mNextIndex ++ ;
        }
    }

    private boolean checkKeys(){
        boolean res = Arrays.equals(mKeyHistory,TRIGER_SEQUENCE);
        Log.i(TAG, "checkKeys: "+res);
        printArrays(mKeyHistory,"ZHOUHAO KEYS ");
        return res;
    }

    private void printArrays(int[] keys,String tag){
        for (int i = 0; i < keys.length; i++) {
            Log.i(TAG, tag+"  printArrays: "+keys[i]);
        }
        Log.i(TAG, "printArrays: index "+ mNextIndex);
    }
}