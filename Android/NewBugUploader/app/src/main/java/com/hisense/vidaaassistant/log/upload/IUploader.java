package com.hisense.vidaaassistant.log.upload;

import android.content.Context;
import android.os.Handler;

/**
 * Created by zhouhao2 on 17-9-27.
 */

public interface IUploader {
    void uploadFile(final String TAG,
                    final String compressesFileNamePath,final Context context);
}
