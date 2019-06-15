package com.google.android.exoplayer2.source.hls.playlist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.offline.StreamKey;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

public final class HlsMediaPlaylist extends HlsPlaylist {
    public static final int PLAYLIST_TYPE_EVENT = 2;
    public static final int PLAYLIST_TYPE_UNKNOWN = 0;
    public static final int PLAYLIST_TYPE_VOD = 1;
    public final int discontinuitySequence;
    public final long durationUs;
    public final boolean hasDiscontinuitySequence;
    public final boolean hasEndTag;
    public final boolean hasProgramDateTime;
    public final long mediaSequence;
    public final int playlistType;
    @Nullable
    public final DrmInitData protectionSchemes;
    public final List<Segment> segments;
    public final long startOffsetUs;
    public final long startTimeUs;
    public final long targetDurationUs;
    public final int version;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaylistType {
    }

    public static final class Segment implements Comparable<Long> {
        public final long byterangeLength;
        public final long byterangeOffset;
        @Nullable
        public final DrmInitData drmInitData;
        public final long durationUs;
        @Nullable
        public final String encryptionIV;
        @Nullable
        public final String fullSegmentEncryptionKeyUri;
        public final boolean hasGapTag;
        @Nullable
        public final Segment initializationSegment;
        public final int relativeDiscontinuitySequence;
        public final long relativeStartTimeUs;
        public final String title;
        public final String url;

        public Segment(String str, long j, long j2) {
            this(str, null, "", 0, -1, C.TIME_UNSET, null, null, null, j, j2, false);
        }

        public Segment(String str, @Nullable Segment segment, String str2, long j, int i, long j2, @Nullable DrmInitData drmInitData, @Nullable String str3, @Nullable String str4, long j3, long j4, boolean z) {
            this.url = str;
            this.initializationSegment = segment;
            this.title = str2;
            this.durationUs = j;
            this.relativeDiscontinuitySequence = i;
            this.relativeStartTimeUs = j2;
            this.drmInitData = drmInitData;
            this.fullSegmentEncryptionKeyUri = str3;
            this.encryptionIV = str4;
            this.byterangeOffset = j3;
            this.byterangeLength = j4;
            this.hasGapTag = z;
        }

        public int compareTo(@NonNull Long l) {
            if (this.relativeStartTimeUs > l.longValue()) {
                return 1;
            }
            return this.relativeStartTimeUs < l.longValue() ? -1 : 0;
        }
    }

    public HlsMediaPlaylist copy(List<StreamKey> list) {
        return this;
    }

    public HlsMediaPlaylist(int i, String str, List<String> list, long j, long j2, boolean z, int i2, long j3, int i3, long j4, boolean z2, boolean z3, boolean z4, @Nullable DrmInitData drmInitData, List<Segment> list2) {
        long j5;
        super(str, list, z2);
        this.playlistType = i;
        this.startTimeUs = j2;
        this.hasDiscontinuitySequence = z;
        this.discontinuitySequence = i2;
        this.mediaSequence = j3;
        this.version = i3;
        this.targetDurationUs = j4;
        this.hasEndTag = z3;
        this.hasProgramDateTime = z4;
        this.protectionSchemes = drmInitData;
        this.segments = Collections.unmodifiableList(list2);
        if (list2.isEmpty()) {
            this.durationUs = 0;
        } else {
            Segment segment = (Segment) list2.get(list2.size() - 1);
            this.durationUs = segment.relativeStartTimeUs + segment.durationUs;
        }
        if (j == C.TIME_UNSET) {
            j5 = C.TIME_UNSET;
        } else if (j >= 0) {
            j5 = j;
        } else {
            j5 = this.durationUs + j;
        }
        this.startOffsetUs = j5;
    }

    public boolean isNewerThan(HlsMediaPlaylist hlsMediaPlaylist) {
        boolean z = true;
        if (hlsMediaPlaylist == null || this.mediaSequence > hlsMediaPlaylist.mediaSequence) {
            return true;
        }
        if (this.mediaSequence < hlsMediaPlaylist.mediaSequence) {
            return false;
        }
        int size = this.segments.size();
        int size2 = hlsMediaPlaylist.segments.size();
        if (size <= size2 && !(size == size2 && this.hasEndTag && !hlsMediaPlaylist.hasEndTag)) {
            z = false;
        }
        return z;
    }

    public long getEndTimeUs() {
        return this.startTimeUs + this.durationUs;
    }

    public HlsMediaPlaylist copyWith(long j, int i) {
        int i2 = this.playlistType;
        String str = this.baseUri;
        List list = this.tags;
        long j2 = this.startOffsetUs;
        long j3 = this.mediaSequence;
        int i3 = this.version;
        long j4 = this.targetDurationUs;
        boolean z = this.hasIndependentSegments;
        boolean z2 = this.hasEndTag;
        boolean z3 = this.hasProgramDateTime;
        boolean z4 = z3;
        boolean z5 = z2;
        boolean z6 = z;
        return new HlsMediaPlaylist(i2, str, list, j2, j, true, i, j3, i3, j4, z6, z5, z4, this.protectionSchemes, this.segments);
    }

    public HlsMediaPlaylist copyWithEndTag() {
        if (this.hasEndTag) {
            return this;
        }
        int i = this.playlistType;
        String str = this.baseUri;
        List list = this.tags;
        long j = this.startOffsetUs;
        long j2 = this.startTimeUs;
        boolean z = this.hasDiscontinuitySequence;
        int i2 = this.discontinuitySequence;
        long j3 = this.mediaSequence;
        int i3 = this.version;
        long j4 = this.targetDurationUs;
        boolean z2 = this.hasIndependentSegments;
        long j5 = j4;
        boolean z3 = this.hasProgramDateTime;
        boolean z4 = z3;
        long j6 = j5;
        return new HlsMediaPlaylist(i, str, list, j, j2, z, i2, j3, i3, j6, z2, true, z4, this.protectionSchemes, this.segments);
    }
}
