package com.hisense.checkdrm;

import java.util.HashMap;

import com.hisense.checkdrm.R;


import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends RoboActivity {

    public static final String TAG = "DrmCheck";

    private String[] mCommandLables = new String[] { "check drmkey", "check windervine key" };
    
    private String[] cmds= new String[]{"chinadrmstorage checkkey ","hs_oemcrypto_test "};
    
    HashMap<Integer,String> map = new HashMap<>();
    
    public static String PREVIOUS_COMMAND_LABLE = "";
    
    private void loadmap() {
        for (int i = 0; i < cmds.length; i++) {
            map.put(i, cmds[i]);
        }
    }

    @InjectView(R.id.listView1)
    ListView lsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initsView();
        loadmap();
    }

    private ArrayAdapter<String> getArrayAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mCommandLables);
        return arrayAdapter;
    }

    private OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            
            if(ShowActivity.conCurCountDownWorker.isRunning == false){
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                String command = map.get(position);
                intent.putExtra("cmd", command);
                PREVIOUS_COMMAND_LABLE = mCommandLables[position];
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "  "+PREVIOUS_COMMAND_LABLE+"  \n is running in background¡­¡­",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void initsView() {

        lsView.setOnItemClickListener(listener);

        lsView.setAdapter(getArrayAdapter());
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
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    }
}
