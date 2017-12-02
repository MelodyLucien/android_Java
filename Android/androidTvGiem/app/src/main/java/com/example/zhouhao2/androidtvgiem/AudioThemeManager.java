package com.example.zhouhao2.androidtvgiem;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

import com.jamdeo.tv.AudioManager;
import com.jamdeo.tv.IManagerStateListener;
import com.jamdeo.tv.TvManager;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager for audio theme playing.
 * @author brldorlu
 */

public class AudioThemeManager {
    public static final String TAG = AudioThemeManager.class.getSimpleName();
    public static final boolean DEBUG = false;

    // Per IXD in fast focus scroll cases, output audio should decrease by 30%
    public static final float AUDIO_DECREASED_VOLUME_PERCENTAGE = 0.7f;
    private WeakReference<Context> mContextRef;
    private MessageHandler mHandler;
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
    // left and right channels volume range is float 0~1,
    // audio theme volume read from Settings is int 0~10,
    // this rate is used to map audio theme value to left/right
    // channels volume. 1 means play sounds with system volume
    private static final float VOLUME_RATE = 0.1f;
    // Save audio volume
    private int mAudioThemeVolume = DEFAULT_VOLUME_VALUE;
    // the default mode is 1(t1-scifi)
    private int mAudioThemeMode = DEFAULT_MODE_VALUE;
    // the audio theme switch status, 0-OFF, 1-ON
    private int mAudioThemeSwitch = DEFAULT_SWITCHER_STATE;
    // Lock for sound pool
    private final Object mSoundPoolLock = new Object();
    /* Audio theme identifiers */
    /**
     * Dpad keypress - focus change audio effect
     * @see #playAudioEffect(int)
     */
    public static final int FOCUS_CHANGED_AUDIO = 0;
    /**
     * Select keypress audio effect
     * @see #playAudioEffect(int)
     */
    public static final int SELECTION_AUDIO = 1;
    /**
     * Back keypress audio effect
     * @see #playAudioEffect(int)
     */
    public static final int BACK_KEY_AUDIO = 2;
    /**
     * Home keypress audio effect
     * @see #playAudioEffect(int)
     */
    public static final int HOME_KEY_AUDIO = 3;
    /**
     * Launch conversense audio effect
     * @see #playAudioEffect(int)
     */
    public static final int LAUNCH_CONVERSENSE_AUDIO = 4;
    /**
     * Alerts Notification - Displayed audio effect
     * @see #playAudioEffect(int)
     */
    public static final int NOTIFICATION_DISPLAYED_AUDIO = 5;
    /**
     * Screen on - Transitions from Display Off to Display On
     * @see #playAudioEffect(int)
     */
    public static final int SCREEN_ON = 6;
    /**
     * Error Bonk audio effect(e.g focus bounds/end back stack/unsuccessful touch)
     * @see #playAudioEffect(int)
     */
    public static final int ERROR_BONK_AUDIO = 7;
    /**
     * Transitions from Launcher to application audio effect
     * @see #playAudioEffect(int)
     */
    public static final int LAUNCH_APP_AUDIO = 8;
    /**
     * Capacitive Touch - Start audio effect
     * @see #playAudioEffect(int)
     */
    public static final int CAPACITIVE_TOUCH_START_AUDIO = 9;
    /**
     * Capacitive Touch - Complete audio effect
     * @see #playAudioEffect(int)
     */
    public static final int CAPACITIVE_TOUCH_COMPLETE_AUDIO = 10;
    /**
     * Number of audio effects
     */
    public static final int NUM_AUDIO_EFFECTS = 11;
    /**
     * Audio effect loading states enum.
     */
    private enum LOADING_STATE {
        IDLE, LOADING, FINISHED
    }
    private LOADING_STATE mLoadingState = LOADING_STATE.IDLE;
    private static final int NUM_SOUNDPOOL_CHANNELS = 3;
    private final int[][] AUDIO_EFFECT_FILES_MAP = new int[NUM_AUDIO_EFFECTS][2];
    private SoundPool mSoundPool = null;
    // loading time out time is 20s
    private static final long AUDIO_EFFECTS_LOAD_TIMEOUT_MS = 20000;
    /* Audio effect file names  */
    private static final List<String> AUDIO_EFFECTS_PATH = new ArrayList<String>();
    static {
        AUDIO_EFFECTS_PATH.add("audiotheme/ogg/t1-scifi/"); // Default path and audio
        AUDIO_EFFECTS_PATH.add("/media/audio/hisense/ogg/scifi/"); // First mode
        AUDIO_EFFECTS_PATH.add("/media/audio/hisense/ogg/playful/"); // Second mode
        AUDIO_EFFECTS_PATH.add("/media/audio/hisense/ogg/typewriter/"); // Third mode
    }
    // keep the position order below the same as audio effect type int
    private static final List<String> AUDIO_EFFECT_FILES = new ArrayList<String>();
    static {
        AUDIO_EFFECT_FILES.add("focus.ogg"); // focus(0)
        AUDIO_EFFECT_FILES.add("select.ogg"); // selection(1)
        AUDIO_EFFECT_FILES.add("dismiss.ogg"); // back(2)
        AUDIO_EFFECT_FILES.add("home.ogg"); // home(3)
        AUDIO_EFFECT_FILES.add("launch-conversense.ogg"); //launch conversense(4)
        AUDIO_EFFECT_FILES.add("notification-display.ogg"); // notification displayed(5)
        AUDIO_EFFECT_FILES.add("display-on.ogg"); // screen on(6)
        AUDIO_EFFECT_FILES.add("bonk.ogg"); // bonk things(7)
        AUDIO_EFFECT_FILES.add("launch.ogg"); // transition to app(8)
        AUDIO_EFFECT_FILES.add("touch.ogg"); // touch start(9)
        AUDIO_EFFECT_FILES.add("touch2.ogg"); // touch complete(10)
    }
    private ContentResolver mContentResolver;
    private AudioManager mAudioManager = null;
    private boolean mAudioManagerReady = false;

    /**
     * Returns the singleton instance of the AudioThemeManager
     *
     * @param context
     * @return
     */
    public static AudioThemeManager getInstance(Context context) {
        if (context != null && SingletonHolder.sInstance.mContextRef == null) {
            SingletonHolder.sInstance.mContextRef = new WeakReference<Context>(
                    context.getApplicationContext());
            SingletonHolder.sInstance.init();
        }
        return SingletonHolder.sInstance;
    }

    // singleton instance holder
    private static class SingletonHolder {
        public static AudioThemeManager sInstance = new AudioThemeManager();
    }

    private Context getContext() {
        return mContextRef != null ? mContextRef.get() : null;
    }

    private void init() {
        Log.i(TAG, "init called",new Throwable());
        mContentResolver = getContext().getContentResolver();
        final HandlerThread handlerThread = new HandlerThread("AudioThemeManager Thread");
        handlerThread.start();
        mHandler = new MessageHandler(handlerThread.getLooper(), this);
        mHandler.sendEmptyMessage(MessageHandler.MSG_INIT_VALUES);
        if (mAudioManager == null) {
            mAudioManager = TvManager.getInstance().getAudioManager(getContext());
            mAudioManager.addManagerStateListener(mAudioManagerStateListener);
        }
        registerObservers();
    }

    private void initAudioEffectsMapValues() {
        for (int i = 0; i < NUM_AUDIO_EFFECTS; i++) {
            AUDIO_EFFECT_FILES_MAP[i][0] = -1;
            AUDIO_EFFECT_FILES_MAP[i][1] = -1;
        }
    }
    private IManagerStateListener mAudioManagerStateListener = new IManagerStateListener() {
        @Override
        public void onServiceAvailable() {
            mAudioManagerReady = true;
            if (DEBUG) {
                Log.d(TAG, "AudioManger is available");
            }
        }

        @Override
        public void onServiceUnavailable() {
            mAudioManagerReady = false;
            if (DEBUG) {
                Log.d(TAG, "AudioManager is unavailable");
            }
        }
    };

    /**
     * Register observer to listen to Audio Theme Selection change.
     */
    public void registerObservers() {
        if (mContentResolver != null) {
            mContentResolver.registerContentObserver(AUDIO_THEME_SWITCHER_SETTING_URI,
                    false, mContentObserver);
            mContentResolver.registerContentObserver(AUDIO_THEME_MODE_SETTING_URI,
                    false, mContentObserver);
            mContentResolver.registerContentObserver(AUDIO_THEME_VOLUME_SETTING_URI,
                    false, mContentObserver);
        }
    }

    private ContentObserver mContentObserver = new ContentObserver(mHandler) {
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            if (AUDIO_THEME_SWITCHER_SETTING_URI.equals(uri)) {
                updateAudioThemeSwitchState();
            } else if (AUDIO_THEME_MODE_SETTING_URI.equals(uri)) {
                updateAudioThemeMode();
                // TODO: May need delay to reload assets, in case value change quickly
                mHandler.sendEmptyMessage(MessageHandler.MSG_LOAD_AUDIO_ASSETS);
            } else if (AUDIO_THEME_VOLUME_SETTING_URI.equals(uri)) {
                updateAudioThemeVolumeValue();
            }
        }
    };

    private static class MessageHandler extends Handler {
        public static final int MSG_INIT_VALUES = 1;
        public static final int MSG_LOAD_AUDIO_ASSETS = 2;
        // public static final int MSG_RELOAD_AUDIO_ASSETS = 3;
        public static final int MSG_PLAY_AUDIO_EFFECT = 3;
        public static final int MSG_CHECK_AUDIO_ASSETS = 4;
        private WeakReference<AudioThemeManager> mAudioThemeManager;

        public MessageHandler(Looper looper, AudioThemeManager audioThemeManager) {
            super(looper);
            mAudioThemeManager = new WeakReference<AudioThemeManager>(audioThemeManager);
        }

        public void handleMessage(Message msg) {
            if (mAudioThemeManager != null) {
                final AudioThemeManager manager = mAudioThemeManager.get();
                if (manager != null) {
                    switch (msg.what) {
                        case MSG_INIT_VALUES:
                            manager.initValues();
                            break;
                        case MSG_LOAD_AUDIO_ASSETS:
                            manager.onLoadAudioAssets();
                            break;
                        case MSG_PLAY_AUDIO_EFFECT:
                            if (msg.obj != null) {
                                manager.onPlayAudioEffect(msg.arg1, msg.arg2, Float.parseFloat(msg.obj.toString()));
                            }
                            break;
                        case MSG_CHECK_AUDIO_ASSETS:
                            manager.checkAudioAssets((SoundPoolCallback) msg.obj);
                            break;
                        default:
                            Log.w(TAG, "undefined case.");
                            break;
                    }
                }
            }
        }
    }

    private void initValues() {
        initAudioEffectsMapValues();
        updateAudioThemeSwitchState();
        updateAudioThemeMode();
        updateAudioThemeVolumeValue();
        mHandler.sendEmptyMessage(MessageHandler.MSG_LOAD_AUDIO_ASSETS);
    }

    private void updateAudioThemeSwitchState() {
        if (mContentResolver != null) {
            mAudioThemeSwitch = Settings.System.getInt(mContentResolver,
                    AUDIO_THEME_SWITCHER_SETTING, DEFAULT_SWITCHER_STATE);
        }
        Log.i(TAG, " updateAudioThemeSwitchState mAudioThemeSwitch = " + mAudioThemeSwitch);
    }

    private void updateAudioThemeMode() {
        if (mContentResolver != null) {
            mAudioThemeMode = Settings.System.getInt(mContentResolver,
                    AUDIO_THEME_MODE_SETTING, DEFAULT_MODE_VALUE);
        }
        Log.i(TAG, " updateAudioThemeMode mAudioThemeMode = " + mAudioThemeMode);
    }

    private void updateAudioThemeVolumeValue() {
        if (mContentResolver != null) {
            mAudioThemeVolume = Settings.System.getInt(mContentResolver,
                    AUDIO_THEME_VOLUME_SETTING, DEFAULT_VOLUME_VALUE);
        }
        Log.i(TAG, " updateAudioThemeMode mAudioThemeVolume = " + mAudioThemeVolume);
    }

    private void onLoadAudioAssets() {
        synchronized (mSoundPoolLock) {
            mHandler.removeMessages(MessageHandler.MSG_CHECK_AUDIO_ASSETS);
            mLoadingState = LOADING_STATE.LOADING;
            if (mSoundPool != null) {
                // only execute load audio assets when mSoundPool is null
                Log.w(TAG, "Sound pool is NOT null, unload assets first.");
                onUnloadAudioAssets();
            }
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(NUM_SOUNDPOOL_CHANNELS)
                    .setAudioAttributes(new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build())
                    .build();
            SoundPoolCallback soundPoolCallback = new SoundPoolCallback();
            mSoundPool.setOnLoadCompleteListener(soundPoolCallback);
            int numSamples = 0;
            int[] poolId = new int[NUM_AUDIO_EFFECTS];
            for (int effect = 0; effect < NUM_AUDIO_EFFECTS; effect++) {
                int sampleId = -1;
                try {
                    if (mAudioThemeMode < 0 || mAudioThemeMode >= AUDIO_EFFECTS_PATH.size()) {
                        mAudioThemeMode = 0;
                    }
                    String filePath = Environment.getRootDirectory() + AUDIO_EFFECTS_PATH.get(mAudioThemeMode) +
                            AUDIO_EFFECT_FILES.get(effect);
                    Log.i(TAG, "onLoadAudioAssets file path = " + filePath);
                    File file = new File(filePath);
                    if (file.exists()) {
                        sampleId = mSoundPool.load(filePath, 0);
                    } else {
                        // load default audio under assets/
                        AssetFileDescriptor fileDescriptor = getContext().getAssets()
                                .openFd(AUDIO_EFFECTS_PATH.get(0) + AUDIO_EFFECT_FILES.get(effect));
                        Log.i(TAG, "onLoadAudioAssets fileDescriptor getFileDescriptor = " + fileDescriptor.getFileDescriptor());
                        if (fileDescriptor != null) {
                            sampleId = mSoundPool.load(fileDescriptor, 0);
                        }
                    }
                } catch (IOException ex) {
                    Log.w(TAG, "onLoadAudioAssets get assets() IOException: " + ex);
                }

                if (sampleId <= 0) {
                    Log.w(TAG, "Sound pool could not load file: " + effect);
                    AUDIO_EFFECT_FILES_MAP[effect][0] = -1;
                    poolId[effect] = -1;
                } else {
                    AUDIO_EFFECT_FILES_MAP[effect][0] = sampleId;
                    poolId[effect] = sampleId;
                    numSamples++;
                }
            }
            // wait for all samples to be loaded
            if (numSamples > 0) {
                soundPoolCallback.setSamples(poolId);
            } else {
                Log.w(TAG, "no samples to load as Sound pool numSamples is 0");
                mSoundPool.release();
                mSoundPool = null;
                mLoadingState = LOADING_STATE.IDLE;
                return;
            }
            mHandler.sendMessageDelayed(mHandler.obtainMessage(
                    MessageHandler.MSG_CHECK_AUDIO_ASSETS, soundPoolCallback), AUDIO_EFFECTS_LOAD_TIMEOUT_MS);
        }
    }

    private void checkAudioAssets(SoundPoolCallback callback) {
        synchronized (mSoundPoolLock) {
            if (callback != null && callback.status() == 0) {
                Log.i(TAG, "Sound pool load finished");
                mLoadingState = LOADING_STATE.FINISHED;
            } else {
                Log.w(TAG,
                        "Sound pool loading failed release resources");
                onUnloadAudioAssets();
                mLoadingState = LOADING_STATE.IDLE;
            }
        }
    }

    private void onUnloadAudioAssets() {
        synchronized (mSoundPoolLock) {
            Log.i(TAG, "unload audio assets release sound pool");
            for (int effect = 0; effect < NUM_AUDIO_EFFECTS; effect++) {
                if (AUDIO_EFFECT_FILES_MAP[effect][0] > 0) {
                    mSoundPool.unload(AUDIO_EFFECT_FILES_MAP[effect][0]);
                    AUDIO_EFFECT_FILES_MAP[effect][0] = -1;
                    AUDIO_EFFECT_FILES_MAP[effect][1] = -1;
                }
            }
            if (mSoundPool != null) {
                mSoundPool.release();
                mSoundPool = null;
            }
        }
    }

    private final class SoundPoolCallback implements
            SoundPool.OnLoadCompleteListener {

        int mStatus = 1; // 1 means neither error nor last sample loaded yet
        List<Integer> mSamples = new ArrayList<Integer>();

        public int status() {
            return mStatus;
        }

        public void setSamples(int[] samples) {
            for (int i = 0; i < samples.length; i++) {
                // do not wait ack for samples rejected upfront by SoundPool
                if (samples[i] > 0) {
                    mSamples.add(samples[i]);
                }
            }
        }

        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            synchronized (mSoundPoolLock) {
                Log.i(TAG, "onLoadComplete(), sampleId = " + sampleId + ", status = " + status);
                int i = mSamples.indexOf(sampleId);
                if (i >= 0) {
                    mSamples.remove(i);
                }
                for (int j = 0; j < NUM_AUDIO_EFFECTS; j++) {
                    if (AUDIO_EFFECT_FILES_MAP[j][0] == sampleId) {
                        AUDIO_EFFECT_FILES_MAP[j][1] = status;
                        break;
                    }
                }
                if ((status != 0) || mSamples.isEmpty()) {
                    mStatus = status;
                    // mSoundPoolLock.notify();
                    mHandler.removeMessages(MessageHandler.MSG_CHECK_AUDIO_ASSETS);
                    mHandler.sendMessage(mHandler.obtainMessage(
                            MessageHandler.MSG_CHECK_AUDIO_ASSETS, this));
                }
            }
        }
    }


    private void onPlayAudioEffect(int effectType, int volume, float percentage) {
        if (!queryAudioEffectsEnabled()) {
            Log.w(TAG, "audio effects is disabled return");
            return;
        }
        // calculate volume, VOLUME_RATE is used to map the volume(0~10) to [0,1],
        // it is the actual audio volume to play sounds, the percentage value
        // means play current sounds should be played with percentage
        // of current audio theme value
        float actualVolume = volume * VOLUME_RATE * percentage;
        if (actualVolume <= 0 || actualVolume > 1) {
            Log.w(TAG, "actual volume is incorrect, set 1.0f instead");
            actualVolume = 1.0f;
        }
        Log.i(TAG, "onPlayAudioEffect the actual volume is " + actualVolume);
        if (mLoadingState != LOADING_STATE.FINISHED) {
            Log.w(TAG, "Loading assets NOT finish");
            // Note: use media player play audio effect, may cause time delay
            // however, sound pool load all audio effects cost about 15s,
            // we need to check if the effectType is loading finish, if not
            // the type would choose media player to play
            chooseToolToPlay(effectType, actualVolume);
            if (mLoadingState == LOADING_STATE.IDLE) {
                // try to load audio assets again
                mHandler.sendEmptyMessage(MessageHandler.MSG_LOAD_AUDIO_ASSETS);
            }
        } else {
            chooseToolToPlay(effectType, actualVolume);
        }
    }

    private void chooseToolToPlay(int effectType, float actualVolume) {
        synchronized (mSoundPoolLock) {
            // the condition of the effectType loading finish is
            // sampleId([effectType][0]) > 0 & status([effectType][1]) = 0
            if (mSoundPool != null && AUDIO_EFFECT_FILES_MAP[effectType][0] > 0
                    && AUDIO_EFFECT_FILES_MAP[effectType][1] == 0) {
                Log.i(TAG, "play audio effect with Sound Pool");
                mSoundPool.play(AUDIO_EFFECT_FILES_MAP[effectType][0],
                        actualVolume, actualVolume, 0, 0, 1.0f);
                return;
            }
        }
        Log.i(TAG, "Media Player play audio effect");
        playAudioEffectByMediaPlayer(effectType, actualVolume);
    }

    private void playAudioEffectByMediaPlayer(int effectType, float volume) {
        // TODO: Add play lock here, as completion/error listener is callback
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            if (mAudioThemeMode < 0 || mAudioThemeMode >= AUDIO_EFFECTS_PATH.size()) {
                mAudioThemeMode = 0;
            }
            String filePath = Environment.getRootDirectory() + AUDIO_EFFECTS_PATH.get(mAudioThemeMode) +
                    AUDIO_EFFECT_FILES.get(effectType);
            Log.i(TAG, "MediaPlayer file path = " + filePath);
            File file = new File(filePath);
            if (file.exists()) {
                mediaPlayer.setDataSource(filePath);
            } else {
                // load default audio under assets/
                AssetFileDescriptor fileDescriptor = getContext().getAssets()
                        .openFd(AUDIO_EFFECTS_PATH.get(0) + AUDIO_EFFECT_FILES.get(effectType));
                Log.i(TAG, "MediaPlayer fileDescriptor getFileDescriptor = " + fileDescriptor.getFileDescriptor());
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            }
            mediaPlayer.setAudioStreamType(android.media.AudioManager.STREAM_SYSTEM);
            mediaPlayer.prepare();
            mediaPlayer.setVolume(volume, volume);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    Log.i(TAG, "media player onCompletion callback");
                    cleanupPlayer(mp);
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.i(TAG, "media player onError callback, what = " + what + ", extra = " + extra);
                    cleanupPlayer(mp);
                    return true;
                }
            });
            mediaPlayer.start();
        } catch (IOException ex) {
            Log.w(TAG, "MediaPlayer IOException: " + ex);
        } catch (IllegalArgumentException ex) {
            Log.w(TAG, "MediaPlayer IllegalArgumentException: " + ex);
        } catch (IllegalStateException ex) {
            Log.w(TAG, "MediaPlayer IllegalStateException: " + ex);
        }
    }

    private void cleanupPlayer(MediaPlayer mp) {
        if (mp != null) {
            try {
                mp.stop();
                mp.release();
            } catch (IllegalStateException ex) {
                Log.w(TAG, "MediaPlayer IllegalStateException: "+ex);
            }
        }
    }

    /**
     * Plays a audio effect (Key press/Capacitive touch/Transitions/Alerts/Errors)
     *
     * @param type The type of audio effect. One of
     *             {@link #FOCUS_CHANGED_AUDIO},
     *             {@link #SELECTION_AUDIO},
     *             {@link #BACK_KEY_AUDIO},
     *             {@link #HOME_KEY_AUDIO},
     *             {@link #CAPACITIVE_TOUCH_START_AUDIO},
     *             {@link #CAPACITIVE_TOUCH_COMPLETE_AUDIO},
     *             {@link #TRANSITION_TO_APP_AUDIO},
     *             {@link #NOTIFICATION_DISPLAYED_AUDIO},
     *             {@link #SCREEN_ON},
     *             {@link #ERROR_BONK_AUDIO},
     *             NOTE: Uses the audio theme settings to determine
     *             whether sounds are heard or not.
     */
    public void playAudioEffect(int type) {
        // if there is no percentage parameter, take
        // 100% of audio volume to play the effect
        playAudioEffect(type, 1.0f);
    }

    /**
     * Plays a audio effect (Key press/Capacitive touch/Transitions/Alerts/Errors)
     *
     * @param type The type of audio effect. One of
     *             {@link #FOCUS_CHANGED_AUDIO},
     *             {@link #SELECTION_AUDIO},
     *             {@link #BACK_KEY_AUDIO},
     *             {@link #HOME_KEY_AUDIO},
     *             {@link #CAPACITIVE_TOUCH_START_AUDIO},
     *             {@link #CAPACITIVE_TOUCH_COMPLETE_AUDIO},
     *             {@link #TRANSITION_TO_APP_AUDIO},
     *             {@link #NOTIFICATION_DISPLAYED_AUDIO},
     *             {@link #SCREEN_ON},
     *             {@link #ERROR_BONK_AUDIO},
     *             NOTE: Uses the audio theme settings to determine
     *             whether sounds are heard or not.
     * @param percentage current audio volume percentage 70
     */
    public void playAudioEffect(int type, float percentage) {
        Log.i(TAG, "playAudioEffect, type = " + type + ", percentage = " + percentage);
        if (type < 0 || type >= NUM_AUDIO_EFFECTS) {
            Log.w(TAG, "wrong audio effect type return");
            return;
        }
        if (percentage <= 0f || percentage > 1.0f) {
            Log.w(TAG, "invalid percentage return");
            return;
        }
        mHandler.sendMessage(mHandler.obtainMessage(
                MessageHandler.MSG_PLAY_AUDIO_EFFECT, type, mAudioThemeVolume, percentage));
    }

    /**
     * Query if audio effects enabled.
     * @return true unmuted& AudioTheme enable& AudioTheme volume is NOT 0
     */
    private boolean queryAudioEffectsEnabled() {
        // 1. Audio Theme Switch disabled
        // 2. Audio Theme Volume is 0
        // 3. Muted
        // 4. System Volume is 0
        final long time1 = SystemClock.uptimeMillis();
        final boolean isMuted = mAudioManager.getMuteFlag();
        final long time2 = SystemClock.uptimeMillis();
        // keep this log to debug AudioManager interface time cost
        Log.i(TAG, "get mute flag cost time " + (time2 - time1) + " ms");
        final int systemVolume = mAudioManager.getVolume();
        Log.i(TAG, "get system volume cost time "
                + (SystemClock.uptimeMillis() - time2) + " ms");
        if (mAudioThemeSwitch == 0 || mAudioThemeVolume == 0
                || (mAudioManager != null && mAudioManagerReady
                && (isMuted || systemVolume == 0))) {
            Log.w(TAG, "audio effects can NOT meet enabled conditions");
            return false;
        }
        return true;
    }

    private static void sendMessage(int msg) {

    }
}
