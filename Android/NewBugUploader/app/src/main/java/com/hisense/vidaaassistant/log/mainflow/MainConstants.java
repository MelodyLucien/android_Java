package com.hisense.vidaaassistant.log.mainflow;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;

import com.hisense.vidaaassistant.log.newuploader.BugReport2Ftp;
import com.jamdeo.tv.IManagerStateListener;
import com.jamdeo.tv.internal.FactoryManager;

public class MainConstants {

    public static final String TAG = BugReport2Ftp.class.getSimpleName();
    public static final String DEFAULT_FANGCHUANHUOHAO = "888888888888888888888888";
    public static String ZIP_FILE_NAME = null;
    public static String SERIALNUMBER = null;

    // delete all stage files
    public static final int DELETE_ALL_FILES = 0;

    // message tag to deal with
    public static final int FILE_DUMP = 1;

    // tag for dump
    public static final int FILE_DUMP_SUCCESSFUL = 3;
    public static final int FILE_DUMP_ERROR = 4;

    // tag for zip and upload
    public static final int FILE_ZIP = 6;
    public static final int FILE_ZIP_SUCCESS = 7;
    public static final int FILE_UPLOAD = 8;
    public static final int UPLOAD_SUCCESS = 9;
    public static final int UPLOAD_ERROR = 5;
    public static final int BUGFILE_NOT_EXIST = 10;
    public static final int FILE_ZIP_ERROR = 11;

    // some file's path
    public static final String PACKAGE_NAME="com.hisense.vidaaassistant";
    public static final String COMPRESSED_TO_PATH_DIR = "/data/data/"+PACKAGE_NAME+"/";
    public static final String APP_HOME_BUG_DIR = "/data/data/"+PACKAGE_NAME+"/"+"bug/";
    public static final String COMPRESSED_RESULT_FILE_NAME = "bug.zip";
    public static final String COMPRESSED_FROM_PATH_DIR = APP_HOME_BUG_DIR;
    public static final String CHECK_PROP = "sys.hisense.dumplog";
    public final static int MIN_INTERVAL_BUGREPORT = 30000; // 30 seconds
    public static boolean mIsDumping = false;

    // the cmds needed by dump function
    // use first mkdir cmds
    public static String[] DUMP_CMDS_MK = new String[] {
            "mkdir " + APP_HOME_BUG_DIR + "recovery",
            "mkdir " + APP_HOME_BUG_DIR + "anr",
            "mkdir " + APP_HOME_BUG_DIR + "tombstones",
            "mkdir " + APP_HOME_BUG_DIR + "wifi",
            "mkdir " + APP_HOME_BUG_DIR + "bluetooth" };

    // use secondly for cp function
    public static String[] DUMP_CMDS_CP_AND_DYNAMIC_CREATE = new String[] {
            "cp /cache/recovery/* " + APP_HOME_BUG_DIR + "recovery/",
            "cp /data/log.txt* " + APP_HOME_BUG_DIR,
            "cp  /data/anr/* " + APP_HOME_BUG_DIR + "anr/",
            "cp /data/tombstones/* " + APP_HOME_BUG_DIR + "tombstones/",
            // "cp /data/aosp_bugreport "+APP_HOME_DIR+"",
            "cp /data/core_dump* " + APP_HOME_BUG_DIR
                    + " && rm /data/core_dump*",
            "cp /etc/bluetooth/bt_stack.conf " + APP_HOME_BUG_DIR + "bluetooth",
            "cp /data/misc/bluetooth/btsnoop_hci.log* " + APP_HOME_BUG_DIR
                    + "bluetooth",
            "cp /data/misc/bluedroid/bt_config.xml " + APP_HOME_BUG_DIR
                    + "bluetooth",
            "procrank > " + APP_HOME_BUG_DIR + "procrank",
            "/system/bin/top -n 1 -d 1 -m 30 -t > " + APP_HOME_BUG_DIR + "top",
            "dmesg > " + APP_HOME_BUG_DIR + "dmesg",
            "service list > " + APP_HOME_BUG_DIR + "service",
            "bugreport > " + APP_HOME_BUG_DIR + "aosp_bugreport",
            "cat /sys/kernel/debug/usb/devices > " + APP_HOME_BUG_DIR + "usb",
            "busybox ifconfig > " + APP_HOME_BUG_DIR + "ifconfig",
            "wl status > " + APP_HOME_BUG_DIR + "wifi/wl_status",
            "wl rate > " + APP_HOME_BUG_DIR + "wifi/wl_rate",
            "wl phy_rssi_ant > " + APP_HOME_BUG_DIR + "wifi/phy_rssi_ant",
            "wpa_cli IFNAME=wlan0 scan > " + APP_HOME_BUG_DIR + "wifi/scan",
            "wpa_cli IFNAME=wlan0 scan_results > " + APP_HOME_BUG_DIR
                    + "wifi/scan_results",
            "md5 /system/lib/hw/bluetooth.default.so > " + APP_HOME_BUG_DIR
                    + "bluetooth/version.txt" };

    public static String[] CMD_DELETE_ALL_STAGE_FILES = new String[] {
            "rm -rf " + APP_HOME_BUG_DIR,
            "rm -rf " + COMPRESSED_TO_PATH_DIR + COMPRESSED_RESULT_FILE_NAME };

    public static ArrayList<String> createMkCmds(String[] strs) {

        if (strs == null || strs.length == 0) {
            return null;
        }

        ArrayList<String> cmdsList = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            cmdsList.add(strs[i]);
        }
        return cmdsList;
    }

    /**
     * we use "serialNumber_uploadTime.zip" as the filename note : we should
     * call this method before the time it 's needed immediately in that the
     * factorymanagerService need time to give our feedback. but if you do you
     * will get null serialNumber at the first time
     * 
     * @param context
     * @return
     */
    public static String getUploadFileName(Context context) {
        // get serialNumber and time as filename
        if (SERIALNUMBER == null || SERIALNUMBER.equals("")) {
            final FactoryManager factoryManager = FactoryManager
                    .getInstance(context);
            factoryManager.addManagerStateListener(new IManagerStateListener() {

                @Override
                public void onServiceUnavailable() {
                    Log.i(TAG, "FactoryManagerService is not avialable");
                }

                @Override
                public void onServiceAvailable() {
                    Log.i(TAG, "FactoryManagerService is avialable");
                    String serialNumberTemp = null;
                    try {
                        Method method = FactoryManager.class.getDeclaredMethod(
                                "getSerialNewNumber",null);
                        if (method != null) {
                            System.out.println("ok!! it is exist!!!");
                            serialNumberTemp = factoryManager
                                    .getSerialNewNumber();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        Log.i(TAG, "no such old method!!!");
                        serialNumberTemp = factoryManager.getSerialNumber();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }

                    if (serialNumberTemp == null || serialNumberTemp.equals("")) {
                        Log.i(TAG, " serialNumber can not get from layer");
                        serialNumberTemp = DEFAULT_FANGCHUANHUOHAO;
                    }
                    SERIALNUMBER = serialNumberTemp;
                }
            });
        }else if(ZIP_FILE_NAME == null || ZIP_FILE_NAME.equals("")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String timeNow = df.format(new Date());
            ZIP_FILE_NAME = SERIALNUMBER + "_" + timeNow + ".zip";
            return ZIP_FILE_NAME;
        }else{
            return ZIP_FILE_NAME;
        }
        return null;
    }

    public static void reset(){
        ZIP_FILE_NAME = null;
        SERIALNUMBER = null;
    }
}
