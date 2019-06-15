package com.comscore.streaming.plugin;

import com.comscore.streaming.StreamSenseEventType;
import com.comscore.streaming.StreamSenseState;
import java.util.HashMap;

public interface StreamSensePluginListener {
    void onGetLabels(StreamSenseEventType streamSenseEventType, HashMap<String, String> hashMap);

    void onPostStateChange(StreamSenseState streamSenseState);

    boolean onPreStateChange(StreamSenseState streamSenseState, StreamSenseEventType streamSenseEventType, boolean z);
}
