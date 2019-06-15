package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "MediaStatusCreator")
@Reserved({1})
@VisibleForTesting
public class MediaStatus extends AbstractSafeParcelable {
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_BACKWARD = 32;
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final Creator<MediaStatus> CREATOR = new zzav();
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
    @Field(getter = "getMediaInfo", id = 2)
    @VisibleForTesting
    private MediaInfo zzdo;
    @Field(getter = "getPlaybackRate", id = 5)
    @VisibleForTesting
    private double zzdr;
    @Field(getter = "getActiveTrackIds", id = 12)
    @VisibleForTesting
    private long[] zzds;
    @Field(getter = "getMediaSessionId", id = 3)
    @VisibleForTesting
    private long zzen;
    @Field(getter = "getCurrentItemId", id = 4)
    @VisibleForTesting
    private int zzeo;
    @Field(getter = "getPlayerState", id = 6)
    @VisibleForTesting
    private int zzep;
    @Field(getter = "getIdleReason", id = 7)
    @VisibleForTesting
    private int zzeq;
    @Field(getter = "getStreamPosition", id = 8)
    @VisibleForTesting
    private long zzer;
    @Field(id = 9)
    private long zzes;
    @Field(getter = "getStreamVolume", id = 10)
    @VisibleForTesting
    private double zzet;
    @Field(getter = "isMute", id = 11)
    @VisibleForTesting
    private boolean zzeu;
    @Field(getter = "getLoadingItemId", id = 13)
    @VisibleForTesting
    private int zzev;
    @Field(getter = "getPreloadedItemId", id = 14)
    @VisibleForTesting
    private int zzew;
    @Field(id = 16)
    private int zzex;
    @Field(id = 17)
    private final ArrayList<MediaQueueItem> zzey;
    @Field(getter = "isPlayingAd", id = 18)
    @VisibleForTesting
    private boolean zzez;
    @Field(getter = "getAdBreakStatus", id = 19)
    @VisibleForTesting
    private AdBreakStatus zzfa;
    @Field(getter = "getVideoInfo", id = 20)
    @VisibleForTesting
    private VideoInfo zzfb;
    private final SparseArray<Integer> zzfc;
    @Field(id = 15)
    private String zzj;
    @VisibleForTesting
    private JSONObject zzp;

    @Constructor
    MediaStatus(@Param(id = 2) MediaInfo mediaInfo, @Param(id = 3) long j, @Param(id = 4) int i, @Param(id = 5) double d, @Param(id = 6) int i2, @Param(id = 7) int i3, @Param(id = 8) long j2, @Param(id = 9) long j3, @Param(id = 10) double d2, @Param(id = 11) boolean z, @Param(id = 12) long[] jArr, @Param(id = 13) int i4, @Param(id = 14) int i5, @Param(id = 15) String str, @Param(id = 16) int i6, @Param(id = 17) List<MediaQueueItem> list, @Param(id = 18) boolean z2, @Param(id = 19) AdBreakStatus adBreakStatus, @Param(id = 20) VideoInfo videoInfo) {
        List<MediaQueueItem> list2 = list;
        this.zzey = new ArrayList();
        this.zzfc = new SparseArray();
        this.zzdo = mediaInfo;
        this.zzen = j;
        this.zzeo = i;
        this.zzdr = d;
        this.zzep = i2;
        this.zzeq = i3;
        this.zzer = j2;
        this.zzes = j3;
        this.zzet = d2;
        this.zzeu = z;
        this.zzds = jArr;
        this.zzev = i4;
        this.zzew = i5;
        this.zzj = str;
        if (this.zzj != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
        this.zzex = i6;
        if (!(list2 == null || list.isEmpty())) {
            zza((MediaQueueItem[]) list2.toArray(new MediaQueueItem[list.size()]));
        }
        this.zzez = z2;
        this.zzfa = adBreakStatus;
        this.zzfb = videoInfo;
    }

    private static boolean zza(int i, int i2, int i3, int i4) {
        if (i != 1) {
            return false;
        }
        switch (i2) {
            case 1:
            case 3:
                return i3 == 0;
            case 2:
                return i4 != 2;
            default:
                return true;
        }
    }

    public MediaStatus(JSONObject jSONObject) throws JSONException {
        this(null, 0, 0, 0.0d, 0, 0, 0, 0, 0.0d, false, null, 0, 0, null, 0, null, false, null, null);
        zza(jSONObject, 0);
    }

    public final long zzp() {
        return this.zzen;
    }

    public int getPlayerState() {
        return this.zzep;
    }

    public int getIdleReason() {
        return this.zzeq;
    }

    public double getPlaybackRate() {
        return this.zzdr;
    }

    public MediaInfo getMediaInfo() {
        return this.zzdo;
    }

    public long getStreamPosition() {
        return this.zzer;
    }

    public boolean isMediaCommandSupported(long j) {
        return (this.zzes & j) != 0;
    }

    public double getStreamVolume() {
        return this.zzet;
    }

    public boolean isMute() {
        return this.zzeu;
    }

    public long[] getActiveTrackIds() {
        return this.zzds;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public int getCurrentItemId() {
        return this.zzeo;
    }

    public int getLoadingItemId() {
        return this.zzev;
    }

    public int getPreloadedItemId() {
        return this.zzew;
    }

    public int getQueueRepeatMode() {
        return this.zzex;
    }

    public List<MediaQueueItem> getQueueItems() {
        return this.zzey;
    }

    public int getQueueItemCount() {
        return this.zzey.size();
    }

    public MediaQueueItem getQueueItemById(int i) {
        return getItemById(i);
    }

    public MediaQueueItem getQueueItem(int i) {
        return getItemByIndex(i);
    }

    public boolean isPlayingAd() {
        return this.zzez;
    }

    public final void zzf(boolean z) {
        this.zzez = z;
    }

    public AdBreakStatus getAdBreakStatus() {
        return this.zzfa;
    }

    public VideoInfo getVideoInfo() {
        return this.zzfb;
    }

    /* JADX WARNING: Removed duplicated region for block: B:83:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0285  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0316  */
    public final int zza(org.json.JSONObject r14, int r15) throws org.json.JSONException {
        /*
        r13 = this;
        r0 = "mediaSessionId";
        r0 = r14.getLong(r0);
        r2 = r13.zzen;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        r2 = 0;
        r3 = 1;
        if (r4 == 0) goto L_0x0012;
    L_0x000e:
        r13.zzen = r0;
        r0 = r3;
        goto L_0x0013;
    L_0x0012:
        r0 = r2;
    L_0x0013:
        r1 = "playerState";
        r1 = r14.has(r1);
        r4 = 3;
        r5 = 2;
        if (r1 == 0) goto L_0x0095;
    L_0x001d:
        r1 = "playerState";
        r1 = r14.getString(r1);
        r6 = "IDLE";
        r6 = r1.equals(r6);
        r7 = 4;
        if (r6 == 0) goto L_0x002e;
    L_0x002c:
        r1 = r3;
        goto L_0x004d;
    L_0x002e:
        r6 = "PLAYING";
        r6 = r1.equals(r6);
        if (r6 == 0) goto L_0x0038;
    L_0x0036:
        r1 = r5;
        goto L_0x004d;
    L_0x0038:
        r6 = "PAUSED";
        r6 = r1.equals(r6);
        if (r6 == 0) goto L_0x0042;
    L_0x0040:
        r1 = r4;
        goto L_0x004d;
    L_0x0042:
        r6 = "BUFFERING";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004c;
    L_0x004a:
        r1 = r7;
        goto L_0x004d;
    L_0x004c:
        r1 = r2;
    L_0x004d:
        r6 = r13.zzep;
        if (r1 == r6) goto L_0x0055;
    L_0x0051:
        r13.zzep = r1;
        r0 = r0 | 2;
    L_0x0055:
        if (r1 != r3) goto L_0x0095;
    L_0x0057:
        r1 = "idleReason";
        r1 = r14.has(r1);
        if (r1 == 0) goto L_0x0095;
    L_0x005f:
        r1 = "idleReason";
        r1 = r14.getString(r1);
        r6 = "CANCELLED";
        r6 = r1.equals(r6);
        if (r6 == 0) goto L_0x006f;
    L_0x006d:
        r7 = r5;
        goto L_0x008d;
    L_0x006f:
        r6 = "INTERRUPTED";
        r6 = r1.equals(r6);
        if (r6 == 0) goto L_0x0079;
    L_0x0077:
        r7 = r4;
        goto L_0x008d;
    L_0x0079:
        r6 = "FINISHED";
        r6 = r1.equals(r6);
        if (r6 == 0) goto L_0x0083;
    L_0x0081:
        r7 = r3;
        goto L_0x008d;
    L_0x0083:
        r6 = "ERROR";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x008c;
    L_0x008b:
        goto L_0x008d;
    L_0x008c:
        r7 = r2;
    L_0x008d:
        r1 = r13.zzeq;
        if (r7 == r1) goto L_0x0095;
    L_0x0091:
        r13.zzeq = r7;
        r0 = r0 | 2;
    L_0x0095:
        r1 = "playbackRate";
        r1 = r14.has(r1);
        if (r1 == 0) goto L_0x00ad;
    L_0x009d:
        r1 = "playbackRate";
        r6 = r14.getDouble(r1);
        r8 = r13.zzdr;
        r1 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r1 == 0) goto L_0x00ad;
    L_0x00a9:
        r13.zzdr = r6;
        r0 = r0 | 2;
    L_0x00ad:
        r1 = "currentTime";
        r1 = r14.has(r1);
        if (r1 == 0) goto L_0x00ce;
    L_0x00b5:
        r1 = "currentTime";
        r6 = r14.getDouble(r1);
        r8 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r6 = r6 * r8;
        r6 = (long) r6;
        r8 = r13.zzer;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 == 0) goto L_0x00cc;
    L_0x00c8:
        r13.zzer = r6;
        r0 = r0 | 2;
    L_0x00cc:
        r0 = r0 | 128;
    L_0x00ce:
        r1 = "supportedMediaCommands";
        r1 = r14.has(r1);
        if (r1 == 0) goto L_0x00e6;
    L_0x00d6:
        r1 = "supportedMediaCommands";
        r6 = r14.getLong(r1);
        r8 = r13.zzes;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 == 0) goto L_0x00e6;
    L_0x00e2:
        r13.zzes = r6;
        r0 = r0 | 2;
    L_0x00e6:
        r1 = "volume";
        r1 = r14.has(r1);
        if (r1 == 0) goto L_0x0115;
    L_0x00ee:
        r15 = r15 & r3;
        if (r15 != 0) goto L_0x0115;
    L_0x00f1:
        r15 = "volume";
        r15 = r14.getJSONObject(r15);
        r1 = "level";
        r6 = r15.getDouble(r1);
        r8 = r13.zzet;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 == 0) goto L_0x0107;
    L_0x0103:
        r13.zzet = r6;
        r0 = r0 | 2;
    L_0x0107:
        r1 = "muted";
        r15 = r15.getBoolean(r1);
        r1 = r13.zzeu;
        if (r15 == r1) goto L_0x0115;
    L_0x0111:
        r13.zzeu = r15;
        r0 = r0 | 2;
    L_0x0115:
        r15 = "activeTrackIds";
        r15 = r14.has(r15);
        r1 = 0;
        if (r15 == 0) goto L_0x0159;
    L_0x011e:
        r15 = "activeTrackIds";
        r15 = r14.getJSONArray(r15);
        r6 = r15.length();
        r7 = new long[r6];
        r8 = r2;
    L_0x012b:
        if (r8 >= r6) goto L_0x0136;
    L_0x012d:
        r9 = r15.getLong(r8);
        r7[r8] = r9;
        r8 = r8 + 1;
        goto L_0x012b;
    L_0x0136:
        r15 = r13.zzds;
        if (r15 != 0) goto L_0x013c;
    L_0x013a:
        r15 = r3;
        goto L_0x0154;
    L_0x013c:
        r15 = r13.zzds;
        r15 = r15.length;
        if (r15 == r6) goto L_0x0142;
    L_0x0141:
        goto L_0x013a;
    L_0x0142:
        r15 = r2;
    L_0x0143:
        if (r15 >= r6) goto L_0x0153;
    L_0x0145:
        r8 = r13.zzds;
        r9 = r8[r15];
        r11 = r7[r15];
        r8 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1));
        if (r8 == 0) goto L_0x0150;
    L_0x014f:
        goto L_0x013a;
    L_0x0150:
        r15 = r15 + 1;
        goto L_0x0143;
    L_0x0153:
        r15 = r2;
    L_0x0154:
        if (r15 == 0) goto L_0x0162;
    L_0x0156:
        r13.zzds = r7;
        goto L_0x0162;
    L_0x0159:
        r15 = r13.zzds;
        if (r15 == 0) goto L_0x0160;
    L_0x015d:
        r7 = r1;
        r15 = r3;
        goto L_0x0162;
    L_0x0160:
        r7 = r1;
        r15 = r2;
    L_0x0162:
        if (r15 == 0) goto L_0x0168;
    L_0x0164:
        r13.zzds = r7;
        r0 = r0 | 2;
    L_0x0168:
        r15 = "customData";
        r15 = r14.has(r15);
        if (r15 == 0) goto L_0x017c;
    L_0x0170:
        r15 = "customData";
        r15 = r14.getJSONObject(r15);
        r13.zzp = r15;
        r13.zzj = r1;
        r0 = r0 | 2;
    L_0x017c:
        r15 = "media";
        r15 = r14.has(r15);
        if (r15 == 0) goto L_0x01ad;
    L_0x0184:
        r15 = "media";
        r15 = r14.getJSONObject(r15);
        r6 = new com.google.android.gms.cast.MediaInfo;
        r6.<init>(r15);
        r7 = r13.zzdo;
        if (r7 == 0) goto L_0x019f;
    L_0x0193:
        r7 = r13.zzdo;
        if (r7 == 0) goto L_0x01a3;
    L_0x0197:
        r7 = r13.zzdo;
        r7 = r7.equals(r6);
        if (r7 != 0) goto L_0x01a3;
    L_0x019f:
        r13.zzdo = r6;
        r0 = r0 | 2;
    L_0x01a3:
        r6 = "metadata";
        r15 = r15.has(r6);
        if (r15 == 0) goto L_0x01ad;
    L_0x01ab:
        r0 = r0 | 4;
    L_0x01ad:
        r15 = "currentItemId";
        r15 = r14.has(r15);
        if (r15 == 0) goto L_0x01c3;
    L_0x01b5:
        r15 = "currentItemId";
        r15 = r14.getInt(r15);
        r6 = r13.zzeo;
        if (r6 == r15) goto L_0x01c3;
    L_0x01bf:
        r13.zzeo = r15;
        r0 = r0 | 2;
    L_0x01c3:
        r15 = "preloadedItemId";
        r15 = r14.optInt(r15, r2);
        r6 = r13.zzew;
        if (r6 == r15) goto L_0x01d1;
    L_0x01cd:
        r13.zzew = r15;
        r0 = r0 | 16;
    L_0x01d1:
        r15 = "loadingItemId";
        r15 = r14.optInt(r15, r2);
        r6 = r13.zzev;
        if (r6 == r15) goto L_0x01df;
    L_0x01db:
        r13.zzev = r15;
        r0 = r0 | 2;
    L_0x01df:
        r15 = r13.zzdo;
        r6 = -1;
        if (r15 != 0) goto L_0x01e6;
    L_0x01e4:
        r15 = r6;
        goto L_0x01ec;
    L_0x01e6:
        r15 = r13.zzdo;
        r15 = r15.getStreamType();
    L_0x01ec:
        r7 = r13.zzep;
        r8 = r13.zzeq;
        r9 = r13.zzev;
        r15 = zza(r7, r8, r9, r15);
        if (r15 != 0) goto L_0x0319;
    L_0x01f8:
        r15 = "repeatMode";
        r15 = r14.has(r15);
        if (r15 == 0) goto L_0x027c;
    L_0x0200:
        r15 = "repeatMode";
        r15 = r14.getString(r15);
        if (r15 == 0) goto L_0x025f;
    L_0x0208:
        r7 = r15.hashCode();
        r8 = -1118317585; // 0xffffffffbd57d3ef float:-0.05269235 double:NaN;
        if (r7 == r8) goto L_0x023f;
    L_0x0211:
        r8 = -962896020; // 0xffffffffc69b5f6c float:-19887.71 double:NaN;
        if (r7 == r8) goto L_0x0235;
    L_0x0216:
        r8 = 1645938909; // 0x621b08dd float:7.14971E20 double:8.1320187E-315;
        if (r7 == r8) goto L_0x022b;
    L_0x021b:
        r8 = 1645952171; // 0x621b3cab float:7.159042E20 double:8.132084224E-315;
        if (r7 == r8) goto L_0x0221;
    L_0x0220:
        goto L_0x0248;
    L_0x0221:
        r7 = "REPEAT_OFF";
        r15 = r15.equals(r7);
        if (r15 == 0) goto L_0x0248;
    L_0x0229:
        r6 = r2;
        goto L_0x0248;
    L_0x022b:
        r7 = "REPEAT_ALL";
        r15 = r15.equals(r7);
        if (r15 == 0) goto L_0x0248;
    L_0x0233:
        r6 = r3;
        goto L_0x0248;
    L_0x0235:
        r7 = "REPEAT_SINGLE";
        r15 = r15.equals(r7);
        if (r15 == 0) goto L_0x0248;
    L_0x023d:
        r6 = r5;
        goto L_0x0248;
    L_0x023f:
        r7 = "REPEAT_ALL_AND_SHUFFLE";
        r15 = r15.equals(r7);
        if (r15 == 0) goto L_0x0248;
    L_0x0247:
        r6 = r4;
    L_0x0248:
        switch(r6) {
            case 0: goto L_0x025b;
            case 1: goto L_0x0256;
            case 2: goto L_0x0251;
            case 3: goto L_0x024c;
            default: goto L_0x024b;
        };
    L_0x024b:
        goto L_0x025f;
    L_0x024c:
        r1 = java.lang.Integer.valueOf(r4);
        goto L_0x025f;
    L_0x0251:
        r1 = java.lang.Integer.valueOf(r5);
        goto L_0x025f;
    L_0x0256:
        r1 = java.lang.Integer.valueOf(r3);
        goto L_0x025f;
    L_0x025b:
        r1 = java.lang.Integer.valueOf(r2);
    L_0x025f:
        if (r1 != 0) goto L_0x0264;
    L_0x0261:
        r15 = r13.zzex;
        goto L_0x0268;
    L_0x0264:
        r15 = r1.intValue();
    L_0x0268:
        r15 = java.lang.Integer.valueOf(r15);
        r1 = r13.zzex;
        r4 = r15.intValue();
        if (r1 == r4) goto L_0x027c;
    L_0x0274:
        r15 = r15.intValue();
        r13.zzex = r15;
        r15 = r3;
        goto L_0x027d;
    L_0x027c:
        r15 = r2;
    L_0x027d:
        r1 = "items";
        r1 = r14.has(r1);
        if (r1 == 0) goto L_0x0314;
    L_0x0285:
        r1 = "items";
        r1 = r14.getJSONArray(r1);
        r4 = r1.length();
        r5 = new android.util.SparseArray;
        r5.<init>();
        r6 = r2;
    L_0x0295:
        if (r6 >= r4) goto L_0x02ab;
    L_0x0297:
        r7 = r1.getJSONObject(r6);
        r8 = "itemId";
        r7 = r7.getInt(r8);
        r7 = java.lang.Integer.valueOf(r7);
        r5.put(r6, r7);
        r6 = r6 + 1;
        goto L_0x0295;
    L_0x02ab:
        r6 = new com.google.android.gms.cast.MediaQueueItem[r4];
        r7 = r15;
        r15 = r2;
    L_0x02af:
        if (r15 >= r4) goto L_0x0306;
    L_0x02b1:
        r8 = r5.get(r15);
        r8 = (java.lang.Integer) r8;
        r9 = r1.getJSONObject(r15);
        r10 = r8.intValue();
        r10 = r13.getItemById(r10);
        if (r10 == 0) goto L_0x02dc;
    L_0x02c5:
        r9 = r10.zzf(r9);
        r7 = r7 | r9;
        r6[r15] = r10;
        r8 = r8.intValue();
        r8 = r13.getIndexById(r8);
        r8 = r8.intValue();
        if (r15 == r8) goto L_0x0303;
    L_0x02da:
        r7 = r3;
        goto L_0x0303;
    L_0x02dc:
        r7 = r8.intValue();
        r8 = r13.zzeo;
        if (r7 != r8) goto L_0x02fb;
    L_0x02e4:
        r7 = r13.zzdo;
        if (r7 == 0) goto L_0x02fb;
    L_0x02e8:
        r7 = new com.google.android.gms.cast.MediaQueueItem$Builder;
        r8 = r13.zzdo;
        r7.<init>(r8);
        r7 = r7.build();
        r6[r15] = r7;
        r7 = r6[r15];
        r7.zzf(r9);
        goto L_0x02da;
    L_0x02fb:
        r7 = new com.google.android.gms.cast.MediaQueueItem;
        r7.<init>(r9);
        r6[r15] = r7;
        goto L_0x02da;
    L_0x0303:
        r15 = r15 + 1;
        goto L_0x02af;
    L_0x0306:
        r15 = r13.zzey;
        r15 = r15.size();
        if (r15 == r4) goto L_0x0310;
    L_0x030e:
        r15 = r3;
        goto L_0x0311;
    L_0x0310:
        r15 = r7;
    L_0x0311:
        r13.zza(r6);
    L_0x0314:
        if (r15 == 0) goto L_0x0335;
    L_0x0316:
        r0 = r0 | 8;
        goto L_0x0335;
    L_0x0319:
        r13.zzeo = r2;
        r13.zzev = r2;
        r13.zzew = r2;
        r15 = r13.zzey;
        r15 = r15.isEmpty();
        if (r15 != 0) goto L_0x0335;
    L_0x0327:
        r13.zzex = r2;
        r15 = r13.zzey;
        r15.clear();
        r15 = r13.zzfc;
        r15.clear();
        r0 = r0 | 8;
    L_0x0335:
        r15 = "breakStatus";
        r15 = r14.optJSONObject(r15);
        r15 = com.google.android.gms.cast.AdBreakStatus.zzc(r15);
        r1 = r13.zzfa;
        if (r1 != 0) goto L_0x0345;
    L_0x0343:
        if (r15 != 0) goto L_0x0351;
    L_0x0345:
        r1 = r13.zzfa;
        if (r1 == 0) goto L_0x035a;
    L_0x0349:
        r1 = r13.zzfa;
        r1 = r1.equals(r15);
        if (r1 != 0) goto L_0x035a;
    L_0x0351:
        if (r15 == 0) goto L_0x0354;
    L_0x0353:
        r2 = r3;
    L_0x0354:
        r13.zzez = r2;
        r13.zzfa = r15;
        r0 = r0 | 32;
    L_0x035a:
        r15 = "videoInfo";
        r15 = r14.optJSONObject(r15);
        r15 = com.google.android.gms.cast.VideoInfo.zzi(r15);
        r1 = r13.zzfb;
        if (r1 != 0) goto L_0x036a;
    L_0x0368:
        if (r15 != 0) goto L_0x0376;
    L_0x036a:
        r1 = r13.zzfb;
        if (r1 == 0) goto L_0x037a;
    L_0x036e:
        r1 = r13.zzfb;
        r1 = r1.equals(r15);
        if (r1 != 0) goto L_0x037a;
    L_0x0376:
        r13.zzfb = r15;
        r0 = r0 | 64;
    L_0x037a:
        r15 = "breakInfo";
        r15 = r14.has(r15);
        if (r15 == 0) goto L_0x0393;
    L_0x0382:
        r15 = r13.zzdo;
        if (r15 == 0) goto L_0x0393;
    L_0x0386:
        r15 = r13.zzdo;
        r1 = "breakInfo";
        r14 = r14.getJSONObject(r1);
        r15.zzd(r14);
        r0 = r0 | 2;
    L_0x0393:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaStatus.zza(org.json.JSONObject, int):int");
    }

    public final boolean zzq() {
        return zza(this.zzep, this.zzeq, this.zzev, this.zzdo == null ? -1 : this.zzdo.getStreamType());
    }

    public MediaQueueItem getItemById(int i) {
        Integer num = (Integer) this.zzfc.get(i);
        if (num == null) {
            return null;
        }
        return (MediaQueueItem) this.zzey.get(num.intValue());
    }

    public MediaQueueItem getItemByIndex(int i) {
        return (i < 0 || i >= this.zzey.size()) ? null : (MediaQueueItem) this.zzey.get(i);
    }

    public Integer getIndexById(int i) {
        return (Integer) this.zzfc.get(i);
    }

    private final void zza(MediaQueueItem[] mediaQueueItemArr) {
        this.zzey.clear();
        this.zzfc.clear();
        for (int i = 0; i < mediaQueueItemArr.length; i++) {
            Object obj = mediaQueueItemArr[i];
            this.zzey.add(obj);
            this.zzfc.put(obj.getItemId(), Integer.valueOf(i));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        if ((this.zzp == null) == (mediaStatus.zzp == null) && this.zzen == mediaStatus.zzen && this.zzeo == mediaStatus.zzeo && this.zzdr == mediaStatus.zzdr && this.zzep == mediaStatus.zzep && this.zzeq == mediaStatus.zzeq && this.zzer == mediaStatus.zzer && this.zzet == mediaStatus.zzet && this.zzeu == mediaStatus.zzeu && this.zzev == mediaStatus.zzev && this.zzew == mediaStatus.zzew && this.zzex == mediaStatus.zzex && Arrays.equals(this.zzds, mediaStatus.zzds) && zzdk.zza(Long.valueOf(this.zzes), Long.valueOf(mediaStatus.zzes)) && zzdk.zza(this.zzey, mediaStatus.zzey) && zzdk.zza(this.zzdo, mediaStatus.zzdo)) {
            boolean z = this.zzp == null || mediaStatus.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, mediaStatus.zzp);
            return z && this.zzez == mediaStatus.isPlayingAd() && zzdk.zza(this.zzfa, mediaStatus.zzfa) && zzdk.zza(this.zzfb, mediaStatus.zzfb) && zzdk.zza(null, null) && Objects.equal(null, null);
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.zzdo, Long.valueOf(this.zzen), Integer.valueOf(this.zzeo), Double.valueOf(this.zzdr), Integer.valueOf(this.zzep), Integer.valueOf(this.zzeq), Long.valueOf(this.zzer), Long.valueOf(this.zzes), Double.valueOf(this.zzet), Boolean.valueOf(this.zzeu), Integer.valueOf(Arrays.hashCode(this.zzds)), Integer.valueOf(this.zzev), Integer.valueOf(this.zzew), String.valueOf(this.zzp), Integer.valueOf(this.zzex), this.zzey, Boolean.valueOf(this.zzez), this.zzfa, this.zzfb, null, null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMediaInfo(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzen);
        SafeParcelWriter.writeInt(parcel, 4, getCurrentItemId());
        SafeParcelWriter.writeDouble(parcel, 5, getPlaybackRate());
        SafeParcelWriter.writeInt(parcel, 6, getPlayerState());
        SafeParcelWriter.writeInt(parcel, 7, getIdleReason());
        SafeParcelWriter.writeLong(parcel, 8, getStreamPosition());
        SafeParcelWriter.writeLong(parcel, 9, this.zzes);
        SafeParcelWriter.writeDouble(parcel, 10, getStreamVolume());
        SafeParcelWriter.writeBoolean(parcel, 11, isMute());
        SafeParcelWriter.writeLongArray(parcel, 12, getActiveTrackIds(), false);
        SafeParcelWriter.writeInt(parcel, 13, getLoadingItemId());
        SafeParcelWriter.writeInt(parcel, 14, getPreloadedItemId());
        SafeParcelWriter.writeString(parcel, 15, this.zzj, false);
        SafeParcelWriter.writeInt(parcel, 16, this.zzex);
        SafeParcelWriter.writeTypedList(parcel, 17, this.zzey, false);
        SafeParcelWriter.writeBoolean(parcel, 18, isPlayingAd());
        SafeParcelWriter.writeParcelable(parcel, 19, getAdBreakStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 20, getVideoInfo(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public AdBreakInfo getCurrentAdBreak() {
        if (this.zzfa == null || this.zzdo == null) {
            return null;
        }
        String breakId = this.zzfa.getBreakId();
        if (TextUtils.isEmpty(breakId)) {
            return null;
        }
        List<AdBreakInfo> adBreaks = this.zzdo.getAdBreaks();
        if (adBreaks == null || adBreaks.isEmpty()) {
            return null;
        }
        for (AdBreakInfo adBreakInfo : adBreaks) {
            if (breakId.equals(adBreakInfo.getId())) {
                return adBreakInfo;
            }
        }
        return null;
    }

    public AdBreakClipInfo getCurrentAdBreakClip() {
        if (this.zzfa == null || this.zzdo == null) {
            return null;
        }
        String breakClipId = this.zzfa.getBreakClipId();
        if (TextUtils.isEmpty(breakClipId)) {
            return null;
        }
        List<AdBreakClipInfo> adBreakClips = this.zzdo.getAdBreakClips();
        if (adBreakClips == null || adBreakClips.isEmpty()) {
            return null;
        }
        for (AdBreakClipInfo adBreakClipInfo : adBreakClips) {
            if (breakClipId.equals(adBreakClipInfo.getId())) {
                return adBreakClipInfo;
            }
        }
        return null;
    }
}
