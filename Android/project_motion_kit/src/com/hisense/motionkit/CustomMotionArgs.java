package com.hisense.motionkit;

public class CustomMotionArgs {
    
    
    public static int MAX_POINTERS = 10;
    
    // int32_t in native
    public  int deviceId;
    
    public  int action;
 
    public  int actionButton;
    
    public  int flags;
    
    public  int metaState;
    
    public  int buttonState;
    
    public  int edgeFlags;
    
    public  int displayId;
    
    
    //uint32_t in native
    public int source;
    
    public int policyFlags;
    
    public int pointerCount;
    
    //nsecs_t in native 
    //time related
    public long downTime;
    
    
    public long eventTime;
    
    
    //float in native
    public float xPrecision;
   
   
    public float yPrecision;
    
    public CustomPointerProperty[] pointerProperties;
    
    public CustomPointerCoord[] pointerCoords;

    @Override
    public String toString() {
       StringBuilder buf = new StringBuilder("MotionArgs [deviceId=" + deviceId + ", action=" + action
                + ", actionButton=" + actionButton + ", flags=" + flags
                + ", metaState=" + metaState + ", buttonState=" + buttonState
                + ", edgeFlags=" + edgeFlags + ", displayId=" + displayId
                + ", source=" + source + ", policyFlags=" + policyFlags
                + ", pointerCount=" + pointerCount + ", downTime=" + downTime
                + ", eventTime=" + eventTime + ", xPrecision=" + xPrecision
                + ", yPrecision=" + yPrecision+"]");
       
       if(pointerCount != 0){
           for (int i = 0; i < pointerCount; i++) {
               buf.append(pointerProperties[i].toString());
               buf.append(pointerCoords[i].toString());
           }
       }
       
       return buf.toString();
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getActionButton() {
        return actionButton;
    }

    public void setActionButton(int actionButton) {
        this.actionButton = actionButton;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getMetaState() {
        return metaState;
    }

    public void setMetaState(int metaState) {
        this.metaState = metaState;
    }

    public int getButtonState() {
        return buttonState;
    }

    public void setButtonState(int buttonState) {
        this.buttonState = buttonState;
    }

    public int getEdgeFlags() {
        return edgeFlags;
    }

    public void setEdgeFlags(int edgeFlags) {
        this.edgeFlags = edgeFlags;
    }

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getPolicyFlags() {
        return policyFlags;
    }

    public void setPolicyFlags(int policyFlags) {
        this.policyFlags = policyFlags;
    }

    public int getPointerCount() {
        return pointerCount;
    }

    public void setPointerCount(int pointerCount) {
        this.pointerCount = pointerCount;
    }

    public long getDownTime() {
        return downTime;
    }

    public void setDownTime(long downTime) {
        this.downTime = downTime;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public float getxPrecision() {
        return xPrecision;
    }

    public void setxPrecision(float xPrecision) {
        this.xPrecision = xPrecision;
    }

    public float getyPrecision() {
        return yPrecision;
    }

    public void setyPrecision(float yPrecision) {
        this.yPrecision = yPrecision;
    }

    public CustomPointerProperty[] getPointerProperties() {
        return pointerProperties;
    }

    public void setPointerProperties(CustomPointerProperty[] pointerProperties) {
        this.pointerProperties = pointerProperties;
    }

    public CustomPointerCoord[] getPointerCoords() {
        return pointerCoords;
    }

    public void setPointerCoords(CustomPointerCoord[] pointerCoords) {
        this.pointerCoords = pointerCoords;
    }
    
    
}
