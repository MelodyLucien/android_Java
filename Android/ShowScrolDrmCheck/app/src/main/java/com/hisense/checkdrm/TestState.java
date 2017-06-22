package com.hisense.checkdrm;

import android.util.Log;

public enum TestState {
    
    
    
    NOTSTARTED,
    STARTED,
    FAILED,
    OVER;
    
    public static final String TAG = TestState.class.getSimpleName();
    
    public static String STR_FAILED = "FAILED";
    
    public static String STR_OK = "OK";
    
    public static String STR_PASSED = "PASSED";
    
    public static String STR_OVERED = "all work is down";
    
    public static boolean GET_OVER_STR = false;
    
    
    
    public static TestState CURRENT_STATE = NOTSTARTED;
    
    
    public static TestState getState(){ 
        return CURRENT_STATE;
    }
    
    public static void reSet(){
        
        CURRENT_STATE = NOTSTARTED;
        GET_OVER_STR = false;
        
        Log.d(TAG, "reset state");
    }
    
    public static boolean changeState(String str){
        
        if(str.contains(STR_FAILED)){
            CURRENT_STATE = FAILED;
            Log.d(TAG, "Failed!!! ");
            return false;
        }
        
        if(!GET_OVER_STR){
            if(str.contains(STR_OVERED)){
                GET_OVER_STR = true;
                Log.d(TAG, "get over string ");
             }
        }
        
        if(CURRENT_STATE == OVER){
            return true;
        }
        
        switch (getState()) {

        case NOTSTARTED:
            if(str.contains(STR_OK)||str.contains(STR_FAILED)|| str.contains(STR_PASSED)){
                CURRENT_STATE=STARTED;
                Log.d(TAG, "change to "+ CURRENT_STATE.name());
                return false;
            }
            break;
            

        default:
            break;
        }
        
        return false;
    }
    
} 