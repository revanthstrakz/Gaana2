package com.google.ads.interactivemedia.v3.api.player;

import java.util.HashMap;
import java.util.List;

public interface VideoStreamPlayer extends ContentProgressProvider, VolumeProvider {

    public interface VideoStreamPlayerCallback {
        void onUserTextReceived(String str);

        void onVolumeChanged(int i);
    }

    void addCallback(VideoStreamPlayerCallback videoStreamPlayerCallback);

    int getVolume();

    void loadUrl(String str, List<HashMap<String, String>> list);

    void onAdBreakEnded();

    void onAdBreakStarted();

    void removeCallback(VideoStreamPlayerCallback videoStreamPlayerCallback);
}
