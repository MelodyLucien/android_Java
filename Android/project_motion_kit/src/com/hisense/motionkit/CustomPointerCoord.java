package com.hisense.motionkit;

public class CustomPointerCoord {
    
    public static final int pointercoord_number = 9;

    public float x;
    
    public float y;
    
    public float pressure;
    
    public float size;
    
    public float touchMajor;
    
    public float touchMinor;
    
    public float toolMajor;
    
    public float toolMinor;
    
    public float orientation;
    
    
    public float getX() {
        return x;
    }




    public float getY() {
        return y;
    }




    public float getPressure() {
        return pressure;
    }




    public float getSize() {
        return size;
    }




    public float getTouchMajor() {
        return touchMajor;
    }




    public float getTouchMinor() {
        return touchMinor;
    }




    public float getToolMajor() {
        return toolMajor;
    }




    public float getToolMinor() {
        return toolMinor;
    }


    public float getOrientation() {
        return orientation;
    }

    
    @Override
    public String toString() {
        return "CustomPointerCoord [x=" + x + ", y=" + y + ", pressure="
                + pressure + ", size=" + size + ", touchMajor=" + touchMajor
                + ", touchMinor=" + touchMinor + ", toolMajor=" + toolMajor
                + ", toolMinor=" + toolMinor + ", orientation=" + orientation
                + "]";
    }




    public void setX(float x) {
        this.x = x;
    }




    public void setY(float y) {
        this.y = y;
    }




    public void setPressure(float pressure) {
        this.pressure = pressure;
    }




    public void setSize(float size) {
        this.size = size;
    }




    public void setTouchMajor(float touchMajor) {
        this.touchMajor = touchMajor;
    }




    public void setTouchMinor(float touchMinor) {
        this.touchMinor = touchMinor;
    }




    public void setToolMajor(float toolMajor) {
        this.toolMajor = toolMajor;
    }




    public void setToolMinor(float toolMinor) {
        this.toolMinor = toolMinor;
    }




    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }
    
    
    
}
