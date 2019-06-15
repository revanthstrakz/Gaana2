package com.services;

import android.app.IntentService;
import android.content.Intent;
import com.managers.DownloadManager;

public class DownloadedTracksMetaUpdateService extends IntentService {
    public DownloadedTracksMetaUpdateService() {
        super("DownloadedTracksMetaUpdateService");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        if (intent.getAction().equals("update_meta")) {
            DownloadManager.c().L();
        }
    }
}
