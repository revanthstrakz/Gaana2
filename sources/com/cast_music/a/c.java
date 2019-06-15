package com.cast_music.a;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.TextTrackStyle;
import java.util.List;
import java.util.Locale;

public interface c extends a {
    void onApplicationConnected(ApplicationMetadata applicationMetadata, String str, boolean z);

    void onApplicationConnectionFailed(int i);

    void onApplicationDisconnected(int i);

    void onApplicationStatusChanged(String str);

    void onApplicationStopFailed(int i);

    void onDataMessageReceived(String str);

    void onMediaLoadResult(int i);

    void onMediaQueueUpdated(List<MediaQueueItem> list, MediaQueueItem mediaQueueItem, int i, boolean z);

    void onRemoteMediaPlayerMetadataUpdated();

    void onRemoteMediaPlayerStatusUpdated();

    void onRemoteMediaPreloadStatusUpdated(MediaQueueItem mediaQueueItem);

    void onTextTrackEnabledChanged(boolean z);

    void onTextTrackLocaleChanged(Locale locale);

    void onTextTrackStyleChanged(TextTrackStyle textTrackStyle);

    void onVolumeChanged(double d, boolean z);
}
