package com.comscore.streaming;

import java.util.HashMap;

public interface StreamSenseListener {
    void onStateChange(StreamSenseState streamSenseState, StreamSenseState streamSenseState2, HashMap<String, String> hashMap, long j);
}
