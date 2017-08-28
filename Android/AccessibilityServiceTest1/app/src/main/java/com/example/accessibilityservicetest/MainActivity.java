package com.example.accessibilityservicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    Button startButton;
    Button click;

    public final static char ACCESSIBILITY_SEPARATER = ':';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startAccessi);
        click= (Button) findViewById(R.id.button);
        click.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
        startButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("shltest", "action="+Settings.ACTION_ACCESSIBILITY_SETTINGS);
                Intent intent = new Intent(
                        Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "start " + intent.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        
        //enableAccessibilityServiceInfo();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       // Log.i(TAG, "zhoiuhao2 onKeyDown: "+event.toString());
        return super.onKeyDown(keyCode, event);
    }

    public void enableAccessibilityServiceInfo(){
        Log.i(TAG, "enableAccessibilityServiceInfo: start");
        final String settingsValue = Settings.Secure.getString(getContentResolver(),Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        if(settingsValue!=null){
            Log.i(TAG,"settingsValue:"+settingsValue);
        }

        Set<ComponentName> enabledServices=getEnabledServicesFromSettings(getApplicationContext());
        StringBuilder tmpStr= new StringBuilder("");

        for (ComponentName cn:enabledServices) {
            tmpStr.append(cn.flattenToString());
            Log.i(TAG, "flattenToString :"+cn.flattenToString());
            tmpStr.append(ACCESSIBILITY_SEPARATER);
        }

        Log.i(TAG, "getAccessibilityServiceInfo: myservicename is: "+getMyServiceName());

        String myAccessibilityServiceName=getMyServiceName();

        tmpStr.append(myAccessibilityServiceName);

        Log.i(TAG, "enableAccessibilityServiceInfo: ACCESSIBILITY_ENABLED : 1 ");
        Settings.Secure.putString(getContentResolver(),Settings.Secure.ACCESSIBILITY_ENABLED,"1");

        Log.d(TAG, "enableAccessibilityServiceInfo: ENABLED_ACCESSIBILITY_SERVICES :"+tmpStr.toString());
        Settings.Secure.putString(getContentResolver(),Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,tmpStr.toString());
    }

    public static String getMyServiceName(){
        return "com.example.accessibilityservicetest/com.example.accessibilityservicetest.MyAccessibilityService"+ "";
    }

    /**
     * @return the set of enabled accessibility services. If there are not services
     * it returned the unmodifiable {@link Collections#emptySet()}.
     */
    public static Set<ComponentName> getEnabledServicesFromSettings(Context context) {

        final String enabledServicesSetting = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);

        final Set<ComponentName> enabledServices = new HashSet<ComponentName>();
        final TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(ACCESSIBILITY_SEPARATER);
        colonSplitter.setString(enabledServicesSetting);

        while (colonSplitter.hasNext()) {
            final String componentNameString = colonSplitter.next();
            final ComponentName enabledService = ComponentName.unflattenFromString(
                    componentNameString);
            if (enabledService != null) {
                enabledServices.add(enabledService);
            }
        }
        return enabledServices;
    }
}
