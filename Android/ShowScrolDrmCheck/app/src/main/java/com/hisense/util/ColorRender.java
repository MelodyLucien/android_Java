package com.hisense.util;

import android.content.Context;
import android.graphics.Color;

public class ColorRender {
    
    private int verboseColor, debugColor, errorColor, infoColor, warningColor, consoleColor,passed;

    public int getVerboseColor() {
        return verboseColor;
    }

    public void setVerboseColor(int verboseColor) {
        this.verboseColor = verboseColor;
    }

    public int getDebugColor() {
        return debugColor;
    }

    public void setDebugColor(int debugColor) {
        this.debugColor = debugColor;
    }

    public int getErrorColor() {
        return errorColor;
    }

    public void setErrorColor(int errorColor) {
        this.errorColor = errorColor;
    }

    public int getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(int infoColor) {
        this.infoColor = infoColor;
    }

    public int getWarningColor() {
        return warningColor;
    }

    public void setWarningColor(int warningColor) {
        this.warningColor = warningColor;
    }

    public int getConsoleColor() {
        return consoleColor;
    }

    public void setConsoleColor(int consoleColor) {
        this.consoleColor = consoleColor;
    }
    
    
    public void initColor(Context context) {
        setConsoleColor(Color.GREEN);
        setDebugColor(Color.GRAY);
        setErrorColor(Color.RED);
        setInfoColor(Color.WHITE);
        setWarningColor(Color.YELLOW);
        setVerboseColor(Color.GRAY);
        setPassed(Color.GREEN);
    }
    
    
    
    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public String appendColor2String(String line) {

        StringBuffer buffer = new StringBuffer();
        int lineColor = getVerboseColor();

        if (line.contains("PASSED")) {
            lineColor = getConsoleColor();
        } else if (line.contains("FAILED")) {
            lineColor = getErrorColor();
        }else{
            lineColor = getInfoColor();
        }
        
        buffer.append("<font color=\"#"
                + Integer.toHexString(lineColor).toUpperCase()
                        .substring(2) + "\">" + line
                + "</font>");

        return buffer.toString()+"\n";
    }
    
    

}
