/*
 * Copyright (C) 2011 Hisense Electric Co., Ltd.
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY HISENSE ELECTRIC CO., LTD. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH HISENSE ELECTRIC CO., LTD. IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.example.accessibilityservicetest;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;


public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = MyAccessibilityService.class
            .getSimpleName();

    private static final String INTENT_INTERESTED_TEXT = "com.hisense.intent.INTERESTED_TEXT";

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        Log.i(TAG, "eventType=" + eventType);
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_FOCUSED  :
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED  :
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED  :
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED  :
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER  :
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT  :
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START  :
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END  :
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED  :
            case AccessibilityEvent.TYPE_VIEW_SCROLLED  :
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED  :
            case AccessibilityEvent.TYPE_ANNOUNCEMENT  :
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED  :
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED  :
            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY  :
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START  :
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END :
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START  :
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END  :
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED  :
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_SUBTREE  :
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_TEXT  :
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION  :
            case AccessibilityEvent.TYPES_ALL_MASK:
               // Log.i(TAG, "===TYPE_VIEW_FOCUSED===");
               // AccessibilityNodeInfo noteInfo2 = event.getSource();
               //  walkThrough(noteInfo2);
                break;
        }
    }

    @Override
    public void onInterrupt() {

    }

    private void walkThrough(AccessibilityNodeInfo node) {
        Log.d(TAG, "===walkThrough===");
        if(node != null) {
            for (int i = 0; i < node.getChildCount(); i++) {
                AccessibilityNodeInfo childNode = node.getChild(i);
                if (null == childNode) {
                    continue;
                }

                CharSequence str=childNode.getText();
                Log.d(TAG, "childNode.getText()=" + str);

                Log.i(TAG, "walkThrough: check if my word!");
                if(str != null ) {
                    Log.i(TAG, "&& str.toString() :"+str.toString());
                    if(str.toString().equalsIgnoreCase("click")){
                        childNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Log.i(TAG, "click button");
                    }
                }

                Log.d(TAG, "childNode.isSelected()=" + childNode.isSelected());

                walkThrough(node.getChild(i));

            }
        }
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.i(TAG, "zhouhao2 onKeyEvent: "+event.toString());
        return false;
    }


}
