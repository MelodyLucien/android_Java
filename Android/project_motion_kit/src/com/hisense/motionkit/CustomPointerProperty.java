package com.hisense.motionkit;

public class CustomPointerProperty {
    
    public static final int pointerproperty_number = 2;
    public int id;
    public int toolType;
    
    
    @Override
    public String toString() {
        return "CustomPointerProperties [id=" + id + ", toolType=" + toolType
                + "]";
    }


    public int getId() {
        return id;
    }


    public int getToolType() {
        return toolType;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setToolType(int toolType) {
        this.toolType = toolType;
    }

    
}
