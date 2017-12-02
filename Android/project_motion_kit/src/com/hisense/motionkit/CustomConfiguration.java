package com.hisense.motionkit;

public class CustomConfiguration {
    
    public static int virtualKeyQuietTime = 0;
    
    public static int pointerGesturesEnabled = 1;
    
    public static int pointerGestureQuietInterval=100000000;
    
    public static float pointerGestureDragMinSwitchSpeed = 50.0f;
    
    public static float pointerGestureTapInterval = 50.0f;
    
    public static int  pointerGestureTapDragInterval = 300000000;
    
    public static int pointerGestureTapSlop = -2132035878;
    
    public static int pointerGestureMultitouchSettleInterval = 100000000;
    
    public static float pointerGestureMultitouchMinDistance = 15.0f;
    
    public static int pointerGestureSwipeTransitionAngleCosine = -2132035878;
    
    public static float pointerGestureSwipeMaxWidthRatio = 0.25f;
    
    public static float pointerGestureMovementSpeedRatio = 0.8f;
    
    public static float pointerGestureZoomSpeedRatio = 0.3f;
    
    public static int showTouches = 0;
    
    //pointerVelocityControllerParameters
     
    public static float pointerParametersScale=1.0f;
    
    public static float pointerParametersLowThreshold=500.0f;
    
    public static float pointerParametersHighThreshold=3000.0f;
    
    public static float pointerParametersAcceleration=3.0f;
    
    //wheelVelocityControllerParameters
    
    public static float wheelParametersScale=1.0f;
    
    public static float wheelParametersLowThreshold=15.0f;
    
    public static float wheelParametersHighThreshold=50.0f;
    
    public static float wheelParametersAcceleration=4.0f;
    

    //displayInfo  keep internal and external dispaly info same
    public static int displayId=0;
    public static int orientation=0;
    public static int logicalLeft=0;
    public static int logicalTop=0;
    public static int logicalRight=1920;
    public static int logicalBottom=1080;
    public static int physicalLeft=0;
    public static int physicalTop=0;
    public static int physicalRight=1920;
    public static int physicalBottom=1080;
    public static int deviceWidth=1920;
    public static int deviceHeight=1080;

}
