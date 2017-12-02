package com.hisense.motionkit;

import android.content.res.Configuration;
import android.util.Log;

/**
 * control the start and stop and define the interface of callback so compile
 * command g++ -I/home/zhouhao2/jdk/jdk1.8.0_45/include/
 * -I/home/zhouhao2/jdk/jdk1.8.0_45/include/linux/ -I. -fPIC -shared -o
 * libmotion.so motiontest.cpp sudo cp libmotion.so /usr/lib/
 * 
 * @author zhouhao2
 * 
 */
public class MotionMonitorController {
    
    public static final String TAG=MotionMonitorController.class.getSimpleName();
    
    public static NotifyMotionCallback callback = null;
    
    public static CustomConfiguration configuration = new CustomConfiguration();
    
    public static boolean registerCallBack(NotifyMotionCallback callback2){
        callback = callback2;
        return true;
    }
    
    public static int MAX_POINTERS = 10;
    
    /**
     * 10*2 
     * public int id; 
     * public int toolType;
     */
    public static int[][] pointProperties;

    /**
     * 10*10
     * 
     * public float x;
     * 
     * public float y;
     * 
     * public float pressure;
     * 
     * public float size;
     * 
     * public float touchMajor;
     * 
     * public float touchMinor;
     * 
     * public float toolMajor;
     * 
     * public float toolMinor;
     * 
     * public float orientation;
     */
    public static float[][] pointCoords;

    
    // int32_t in native
    public static int deviceId;

    public static int action;

    public static int actionButton;

    public static int flags;

    public static int metaState;

    public static int buttonState;

    public static int edgeFlags;

    public static int displayId;

    // uint32_t in native
    public static int source;

    public static int policyFlags;

    public static int pointerCount;

    // nsecs_t in native
    // time related
    public static long downTime;

    public static long eventTime;

    // float in native
    public static float xPrecision;

    public static float yPrecision;

    // native callback
    private static void notifyMotionEvent() {
        
        Log.i(TAG, "deviceId:"+deviceId + " ,source: " + source + ",action: " + action + " ,pointerCount: "
                + pointerCount);
        
        CustomMotionArgs mMotionEvent = new CustomMotionArgs();
        
        mMotionEvent.setDeviceId(deviceId);        
        mMotionEvent.setAction(action);
        mMotionEvent.setActionButton(actionButton);
        mMotionEvent.setFlags(flags);
        mMotionEvent.setMetaState(metaState);
        mMotionEvent.setButtonState(buttonState);
        mMotionEvent.setEdgeFlags(edgeFlags);       
        mMotionEvent.setDisplayId(displayId);
        mMotionEvent.setSource(source);
        mMotionEvent.setPolicyFlags(policyFlags);
        mMotionEvent.setPointerCount(pointerCount);
        
        mMotionEvent.setDownTime(downTime);
        mMotionEvent.setEventTime(eventTime);

        mMotionEvent.setxPrecision(xPrecision);
        mMotionEvent.setyPrecision(yPrecision);
        
        StringBuffer buf = new StringBuffer("");
        
        if(pointerCount!= 0){
        
            CustomPointerProperty[] customPointerProperties = new CustomPointerProperty[pointerCount];
            CustomPointerCoord[] customPointerCoords = new CustomPointerCoord[pointerCount];

    
            if ((pointProperties != null) && (pointCoords != null)) {

                int wholelength = pointProperties.length;
                Log.i(TAG,"pointProperties.length :" + wholelength);
                
                for (int i = 0; i < pointerCount; i++) {
                    
                    CustomPointerProperty property = new CustomPointerProperty();
                    property.setId(pointProperties[i][0]);
                    property.setToolType(pointProperties[i][1]);
                    
                    customPointerProperties[i] = property;
                }
                
                mMotionEvent.setPointerProperties(customPointerProperties);

                for (int i = 0; i < pointerCount; i++) {
                    
                    CustomPointerCoord coord = new CustomPointerCoord();
                    coord.setX(pointCoords[i][0]);
                    coord.setY(pointCoords[i][1]);
                    coord.setPressure(pointCoords[i][2]);
                    coord.setSize(pointCoords[i][3]);
                    coord.setTouchMajor(pointCoords[i][4]);
                    coord.setTouchMinor(pointCoords[i][5]);
                    coord.setToolMajor(pointCoords[i][6]);
                    coord.setToolMinor(pointCoords[i][7]);
                    coord.setOrientation(pointCoords[i][8]);
                    
                    customPointerCoords[i]=coord;
                }
                
                mMotionEvent.setPointerCoords(customPointerCoords);
                
            }else{
                Log.i(TAG,"pointProperties and pointCroods == null");
            }
            
            callback.onNotifyMotionCallback(mMotionEvent);
        }

    }

    
    private MotionMonitorController() {
    }

    private static boolean isStarted = false;

    private static boolean isStop = false;

    static {
        System.loadLibrary("motion");
    }
    

    private static native void startMonitor(CustomConfiguration configuration);

    public static synchronized void start() {
        Log.i(TAG, " start native thread to read");
        if (!isStarted) {
            isStarted = true;
            startMonitor(configuration);
        }
    }

    public static synchronized void setStop() {
        Log.i(TAG," i set setstop");
        isStop = true;
        isStarted = false;
    }


    public static boolean isStarted() {
        return isStarted;
    }

    public static void setStarted(boolean isStarted) {
        MotionMonitorController.isStarted = isStarted;
    }

    public static int getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(int deviceId) {
        MotionMonitorController.deviceId = deviceId;
    }

    public static int getAction() {
        return action;
    }

    public static void setAction(int action) {
        MotionMonitorController.action = action;
    }

    public static int getActionButton() {
        return actionButton;
    }

    public static void setActionButton(int actionButton) {
        MotionMonitorController.actionButton = actionButton;
    }

    public static int getFlags() {
        return flags;
    }

    public static void setFlags(int flags) {
        MotionMonitorController.flags = flags;
    }

    public static int getMetaState() {
        return metaState;
    }

    public static void setMetaState(int metaState) {
        MotionMonitorController.metaState = metaState;
    }

    public static int getButtonState() {
        return buttonState;
    }

    public static void setButtonState(int buttonState) {
        MotionMonitorController.buttonState = buttonState;
    }

    public static int getEdgeFlags() {
        return edgeFlags;
    }

    public static void setEdgeFlags(int edgeFlags) {
        MotionMonitorController.edgeFlags = edgeFlags;
    }

    public static int getDisplayId() {
        return displayId;
    }

    public static void setDisplayId(int displayId) {
        MotionMonitorController.displayId = displayId;
    }

    public static int getSource() {
        return source;
    }

    public static void setSource(int source) {
        MotionMonitorController.source = source;
    }

    public static int getPolicyFlags() {
        return policyFlags;
    }

    public static void setPolicyFlags(int policyFlags) {
        MotionMonitorController.policyFlags = policyFlags;
    }

    public static int getPointerCount() {
        return pointerCount;
    }

    public static void setPointerCount(int pointerCount) {
        MotionMonitorController.pointerCount = pointerCount;
    }

    public static long getDownTime() {
        return downTime;
    }

    public static void setDownTime(long downTime) {
        MotionMonitorController.downTime = downTime;
    }

    public static long getEventTime() {
        return eventTime;
    }

    public static void setEventTime(long eventTime) {
        MotionMonitorController.eventTime = eventTime;
    }

    public static float getxPrecision() {
        return xPrecision;
    }

    public static void setxPrecision(float xPrecision) {
        MotionMonitorController.xPrecision = xPrecision;
    }

    public static float getyPrecision() {
        return yPrecision;
    }

    public static void setyPrecision(float yPrecision) {
        MotionMonitorController.yPrecision = yPrecision;
    }

    public static int[][] getPointProperties() {
        return pointProperties;
    }

    public static void setPointProperties(int[][] pointProperties) {
        MotionMonitorController.pointProperties = pointProperties;
    }

    public static float[][] getPointCoords() {
        return pointCoords;
    }

    public static void setPointCoords(float[][] pointCoords) {
        MotionMonitorController.pointCoords = pointCoords;
    }
    
}
