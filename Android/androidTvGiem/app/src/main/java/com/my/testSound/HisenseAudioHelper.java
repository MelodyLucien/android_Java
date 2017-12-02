package com.my.testSound;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;

/**
 * Created by zhouhao2 on 17-10-27.
 */

public class HisenseAudioHelper {

    private static final String TAG ="HisenseAudioHelper";

    private static boolean DEBUG_AUDIO = true;

    public static final int SOUND_TYPE0 = 0 ;
    public static final int SOUND_TYPE1 = 1 ;
    public static final int SOUND_TYPE2 = 2 ;

    private static int FX_HISENSE_TH1_NAVIGATION = 10;
    private static int FX_HISENSE_TH1_BACK = 11;
    private static int FX_HISENSE_TH1_HOME = 12;

    private static int FX_HISENSE_TH2_NAVIGATION = 13;
    private static int FX_HISENSE_TH2_BACK = 14;
    private static int FX_HISENSE_TH2_HOME = 15;

    private static int FX_HISENSE_TH3_NAVIGATION = 16;
    private static int FX_HISENSE_TH3_BACK = 17;
    private static int FX_HISENSE_TH3_HOME = 18;

    private static int[][] THEME_AND_EFFECTS = {
            {FX_HISENSE_TH1_NAVIGATION,FX_HISENSE_TH1_BACK,FX_HISENSE_TH1_HOME},
            {FX_HISENSE_TH2_NAVIGATION,FX_HISENSE_TH2_BACK,FX_HISENSE_TH2_HOME},
            {FX_HISENSE_TH3_NAVIGATION,FX_HISENSE_TH3_BACK,FX_HISENSE_TH3_HOME}};

    // Keep these labels below the same with Settings apk
    // 1--ON, 0--OFF
    private static final String AUDIO_THEME_SWITCHER_SETTING = "audio_theme_switcher";
    // range from 0-10
    private static final String AUDIO_THEME_VOLUME_SETTING = "audio_theme_volume";
    // 1--t1-scifi, 2--t2-playful, t3-typewriter
    private static final String AUDIO_THEME_MODE_SETTING = "audio_theme_mode";

    private static final Uri AUDIO_THEME_SWITCHER_SETTING_URI = Settings.System.getUriFor(AUDIO_THEME_SWITCHER_SETTING);
    private static final Uri AUDIO_THEME_MODE_SETTING_URI = Settings.System.getUriFor(AUDIO_THEME_MODE_SETTING);
    private static final Uri AUDIO_THEME_VOLUME_SETTING_URI = Settings.System.getUriFor(AUDIO_THEME_VOLUME_SETTING);

    private static final int DEFAULT_SWITCHER_STATE = 1; // default is on
    private static final int DEFAULT_VOLUME_VALUE = 10;
    private static final int DEFAULT_MODE_VALUE = 1;

    // Save audio volume
    private int mAudioThemeVolume = DEFAULT_VOLUME_VALUE;
    // the default mode is 1(t1-scifi)
    private int mAudioThemeMode = DEFAULT_MODE_VALUE;
    // the audio theme switch status, 0-OFF, 1-ON
    private int mAudioThemeSwitch = DEFAULT_SWITCHER_STATE;


    private Context mContext = null;
    private Handler mHandler = null;
    private AudioManager mAudioManager = null;
    private ContentResolver mContentResolver = null;
    private ContentObserver mContentObserver =  null;

    public HisenseAudioHelper(Context context, Handler handler){
        mContext = context;
        mHandler = handler;
        registerObservers();
    }

    public  AudioManager getAudioManager(){
          if(mAudioManager == null){
              mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
          }
          return mAudioManager;
    }

    /**
     * @return  1--t1-scifi, 2--t2-playful, t3-typewriter
     */
    private int getSoundEffectsTheme(){
        int theme = DEFAULT_MODE_VALUE;
        if (mContentResolver != null) {
            mAudioThemeMode = Settings.System.getInt(mContentResolver,
                    AUDIO_THEME_MODE_SETTING, DEFAULT_MODE_VALUE);
        }
        switch (mAudioThemeMode){
            case 1 :
              theme = 0;
                break;
            case 2:
                theme =1;
                break;
            case 3:
                theme =2;
                break;
            default:
                break;
        }
        if(DEBUG_AUDIO) {
            Log.i(TAG, " get from settings system"+AUDIO_THEME_MODE_SETTING+" = " + mAudioThemeMode+";theme is "+theme);
        }
        return theme;
    }

    public void playSoundByTheme(int keycode){
        int theme = getSoundEffectsTheme();
        playSoundEffects(theme,keycode);
    }


    public void playSoundEffects(int theme,int keycode){
        int soundType = SOUND_TYPE1;
        switch(keycode){
            case KeyEvent.KEYCODE_DPAD_UP:
            case KeyEvent.KEYCODE_DPAD_DOWN:
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                soundType = SOUND_TYPE0;
                break;
            case KeyEvent.KEYCODE_BACK:
                soundType = SOUND_TYPE1;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_SETTINGS:
            case KeyEvent.KEYCODE_HOME:
            case KeyEvent.KEYCODE_MENU:
            case KeyEvent.KEYCODE_VOICE_ASSIST:
                // ADD CUSTOM  VOICE
            case KeyEvent.KEYCODE_TV_INPUT:
            case KeyEvent.KEYCODE_MUSIC:
                // four silo keys
                soundType = SOUND_TYPE2;
                break;
            default:
                return;
        }
        getAudioManager().playSoundEffect(THEME_AND_EFFECTS[theme][soundType]);
        if(DEBUG_AUDIO){
            Log.d(TAG,"playSoundEffect [theme][soundtype] :["+ theme+"]["+soundType+"]");
        }
    }

    /**
     * Register observer to listen to Audio Theme Selection change.
     */
    public void registerObservers() {
        mContentResolver = mContext.getContentResolver();
        mContentObserver = new ContentObserver(mHandler) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                if (AUDIO_THEME_SWITCHER_SETTING_URI.equals(uri)) {
                    updateAudioThemeSwitchState();
                } else if (AUDIO_THEME_MODE_SETTING_URI.equals(uri)) {
                    updateAudioThemeMode();
                } else if (AUDIO_THEME_VOLUME_SETTING_URI.equals(uri)) {
                    updateAudioThemeVolumeValue();
                }
            }
        };

        if (mContentResolver != null && mContentObserver != null ) {
            mContentResolver.registerContentObserver(AUDIO_THEME_SWITCHER_SETTING_URI,
                    false, mContentObserver);
            mContentResolver.registerContentObserver(AUDIO_THEME_MODE_SETTING_URI,
                    false, mContentObserver);
            mContentResolver.registerContentObserver(AUDIO_THEME_VOLUME_SETTING_URI,
                    false, mContentObserver);
        }else{
            if(DEBUG_AUDIO){
                Log.d(TAG,"can't registerObservers,we have null objects !!");
            }
        }
    }

    private void updateAudioThemeSwitchState() {
        if (mContentResolver != null) {
            mAudioThemeSwitch = Settings.System.getInt(mContentResolver,
                    AUDIO_THEME_SWITCHER_SETTING, DEFAULT_SWITCHER_STATE);
        }
        if(DEBUG_AUDIO) {
            Log.i(TAG, " updateAudioThemeSwitchState mAudioThemeSwitch = " + mAudioThemeSwitch);
        }
    }

    private void updateAudioThemeMode() {
        if (mContentResolver != null) {
            mAudioThemeMode = Settings.System.getInt(mContentResolver,
                    AUDIO_THEME_MODE_SETTING, DEFAULT_MODE_VALUE);
        }
        if(DEBUG_AUDIO) {
            Log.i(TAG, " updateAudioThemeMode mAudioThemeMode = " + mAudioThemeMode);
        }
    }

    private void updateAudioThemeVolumeValue() {
        if (mContentResolver != null) {
            mAudioThemeVolume = Settings.System.getInt(mContentResolver,
                    AUDIO_THEME_VOLUME_SETTING, DEFAULT_VOLUME_VALUE);
        }
        if(DEBUG_AUDIO) {
            Log.i(TAG, " updateAudioThemeMode mAudioThemeVolume = " + mAudioThemeVolume);
        }
    }
}
