package com.cast_music;

import com.google.android.gms.cast.MediaQueueItem;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class d {
    private List<MediaQueueItem> a = new CopyOnWriteArrayList();
    private MediaQueueItem b;
    private boolean c;
    private int d;

    public d(List<MediaQueueItem> list, MediaQueueItem mediaQueueItem, boolean z, int i) {
        this.a = list;
        this.b = mediaQueueItem;
        this.c = z;
        this.d = i;
    }
}
