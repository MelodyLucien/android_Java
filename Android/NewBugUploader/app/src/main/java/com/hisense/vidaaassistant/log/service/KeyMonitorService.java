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

package com.hisense.vidaaassistant.log.service;

import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.hisense.vidaaassistant.log.R;
import com.hisense.vidaaassistant.log.newuploader.BugReport2Ftp;
import com.hisense.vidaaassistant.log.utils.KeySequenceListenner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class KeyMonitorService extends AccessibilityService {

    private static final String TAG = KeyMonitorService.class
            .getSimpleName();

    public final static char ACCESSIBILITY_SEPARATER = ':';

    private static final String INTENT_INTERESTED_TEXT = "com.hisense.intent.INTERESTED_TEXT";

    private static final String MYSERVICENAME = "com.hisense.vidaaassistant.log/com.hisense.vidaaassistant.log.service.KeyMonitorService";

    private static int ACCESSIBILITY_ENABLED = 1;
    private static int ACCESSIBILITY_DISABLED = 0;

    private static boolean isShowing = false;

    //listenner for trigering poping window
    private KeySequenceListenner mKeySequenceListenner = new KeySequenceListenner();;


    private Handler mH = null;
    private HandlerThread localThread = null;

    public static void enableMySelf(Context context) {
        enableAccessibilityServiceInfo(context);
    }

    @Override
    public void onCreate() {
        enableMySelf(getApplicationContext());
        startLoop();
        super.onCreate();
    }

    public static boolean isShowing() {
        return isShowing;
    }

    public  static void setShownState(boolean isShown){
        isShowing = isShown;
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.i(TAG, "zhouhao2 onKeyEvent: " + event.toString(), new Throwable());
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mKeySequenceListenner.shouldPopWindow(event.getKeyCode())) {
                   if(!isShowing()){
                       mH.sendEmptyMessage(0);
                   }else{
                       Log.i(TAG, "onKeyEvent: i have been already showing,don't bother me! ");
                   }
            }
        }
        return false;
    }

    private void startLoop() {
        localThread = new HandlerThread(TAG, Process.THREAD_PRIORITY_FOREGROUND);
        localThread.start();
        mH = new MyHandler(localThread.getLooper());
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    @Override
    public void onInterrupt() {

    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
    }

    private static void enableAccessibilityServiceInfo(Context context) {

        Log.i(TAG, "enableAccessibilityServiceInfo: start");
        final String settingsValue = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        if (settingsValue != null) {
            Log.i(TAG, "settingsValue:" + settingsValue);
        }

        Set<ComponentName> enabledServices = getEnabledServicesFromSettings(context.getApplicationContext());

        StringBuilder tmpStr = new StringBuilder("");

        if (enabledServices != null && enabledServices.size() != 0) {
            for (ComponentName cn : enabledServices) {
                tmpStr.append(cn.flattenToString());
                Log.i(TAG, "flattenToString :" + cn.flattenToString());
                tmpStr.append(ACCESSIBILITY_SEPARATER);
            }
        }
        Log.i(TAG, "getAccessibilityServiceInfo: myservicename is: " + getMyServiceName());

        String myAccessibilityServiceName = getMyServiceName();

        tmpStr.append(myAccessibilityServiceName);

        if (!Settings.Secure.putString(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, "" + ACCESSIBILITY_ENABLED + "")) {
            Log.i(TAG, ": ACCESSIBILITY_ENABLED set fail");
        } else {
            Log.i(TAG, "enableAccessibilityServiceInfo: ACCESSIBILITY_ENABLED : 1 ");
        }

        if (!Settings.Secure.putString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, tmpStr.toString())) {
            Log.i(TAG, ": ENABLED_ACCESSIBILITY_SERVICES set fail");
        } else {
            Log.d(TAG, "enableAccessibilityServiceInfo: ENABLED_ACCESSIBILITY_SERVICES :" + tmpStr.toString());
        }

        Log.i(TAG, "after enable : ACCESSIBILITY_ENABLED is " + Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED));
        Log.i(TAG, "after enable : ENABLED_ACCESSIBILITY_SERVICES are " + Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES));
    }

    private static String getMyServiceName() {
        return MYSERVICENAME + "";
    }

    /**
     * @return the set of enabled accessibility services. If there are not services
     * it returned the unmodifiable {@link Collections#emptySet()}.
     */
    private static Set<ComponentName> getEnabledServicesFromSettings(Context context) {

        final String enabledServicesSetting = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);

        final Set<ComponentName> enabledServices = new HashSet<ComponentName>();
        final TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(ACCESSIBILITY_SEPARATER);
        if(colonSplitter == null || colonSplitter.toString().equals("")||
                enabledServicesSetting == null || enabledServicesSetting.equals("")){
            return null;
        }else {
            colonSplitter.setString(enabledServicesSetting);
            while (colonSplitter.hasNext()) {
                final String componentNameString = colonSplitter.next();
                final ComponentName enabledService = ComponentName.unflattenFromString(
                        componentNameString);
                if (enabledService != null) {
                    enabledServices.add(enabledService);
                }
            }
        }
        return enabledServices;
    }

    /* 显示Dialog的method */
    public void showDialog(final Context context, final String title) {

        AlertDialog dialog =  new AlertDialog.Builder(context).setTitle("提示")
                .setMessage("\n　　"+title)
                .setNegativeButton(context.getResources().getText(R.string.cancel_upload), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "　cancel ");
                    }
                })
                .setPositiveButton(context.getResources().getText(R.string.start_approval),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, " start approval ");
                        gotoSetting();
                    }

                    private void gotoSetting() {
                        Log.i(TAG, "gotoSetting: ");
                    }
                }).create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }


class MyHandler extends  Handler{

    public MyHandler(Looper looper){
        super(looper);
    }
    @Override
    public void handleMessage(Message msg) {
       /* Intent in = new Intent();
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.setClass(getApplicationContext(), BugReport2Ftp.class);
        startActivity(in);*/
       showDialog(KeyMonitorService.this,"zhouhao2");
    }
  }
}
