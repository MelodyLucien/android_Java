package com.hisense.checkdrm;


import com.hisense.checkdrm.R;
import com.hisense.util.ColorRender;


import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultActivity extends RoboActivity {

    @InjectView(R.id.tv_result)
    TextView tv;
    ColorRender colorRender = new ColorRender();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_main);
        colorRender.initColor(ResultActivity.this);
        
        Intent in=getIntent();
        String res= in.getStringExtra("result");
        
        if(res !=null && !res.equals("")){
            if(res.equals(TestState.STR_OK)){
                tv.setTextColor(Color.GREEN);
            }else if(res.equals(TestState.STR_FAILED)){
                tv.setTextColor(Color.RED);
            }
            tv.setText(res);
        }
    }



  

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       // Log.i(TAG, "down keycode is :" + keyCode, new Throwable("hello"));
        return super.onKeyDown(keyCode, event);
    }
}
