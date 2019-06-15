package com.comscore.streaming;

import com.comscore.utils.CSLog;
import java.util.HashMap;
import java.util.Set;

public class StreamSensePlaylist {
    private StreamSenseClip a;
    private String b;
    private int c;
    private int d;
    private int e;
    private long f;
    private long g;
    private HashMap<String, String> h;
    private int i;
    private boolean j;

    public StreamSensePlaylist() {
        this.a = null;
        this.j = false;
        this.a = new StreamSenseClip();
        this.h = new HashMap();
        reset();
    }

    /* Access modifiers changed, original: protected */
    public int a() {
        return this.c;
    }

    /* Access modifiers changed, original: protected */
    public HashMap<String, String> a(StreamSenseEventType streamSenseEventType, HashMap<String, String> hashMap) {
        HashMap hashMap2;
        if (hashMap2 == null) {
            hashMap2 = new HashMap();
        }
        hashMap2.put("ns_st_bp", String.valueOf(c()));
        hashMap2.put("ns_st_sp", String.valueOf(this.c));
        hashMap2.put("ns_st_id", String.valueOf(this.b));
        if (this.e > 0) {
            hashMap2.put("ns_st_bc", String.valueOf(this.e));
        }
        if (streamSenseEventType == StreamSenseEventType.PAUSE || streamSenseEventType == StreamSenseEventType.END || streamSenseEventType == StreamSenseEventType.KEEP_ALIVE || streamSenseEventType == StreamSenseEventType.HEART_BEAT || streamSenseEventType == null) {
            hashMap2.put("ns_st_pa", String.valueOf(d()));
            hashMap2.put("ns_st_pp", String.valueOf(this.d));
        }
        if ((streamSenseEventType == StreamSenseEventType.PLAY || streamSenseEventType == null) && !j()) {
            hashMap2.put("ns_st_pb", "1");
            a(true);
        }
        hashMap2.putAll(getLabels());
        return hashMap2;
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
        this.c = i;
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addPlaybackTime(");
        stringBuilder.append(j);
        stringBuilder.append(")");
        CSLog.d((Object) this, stringBuilder.toString());
        if (this.a.getPlaybackTimestamp() >= 0) {
            long playbackTimestamp = j - this.a.getPlaybackTimestamp();
            this.a.c(-1);
            this.a.b(this.a.f() + playbackTimestamp);
            d(d() + playbackTimestamp);
            stringBuilder = new StringBuilder();
            stringBuilder.append("addPlaybackTime(");
            stringBuilder.append(j);
            stringBuilder.append(") ->");
            stringBuilder.append(playbackTimestamp);
            CSLog.d((Object) this, stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(HashMap<String, String> hashMap, StreamSenseState streamSenseState) {
        if (hashMap != null) {
            this.h.putAll(hashMap);
        }
        b(this.h, streamSenseState);
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        this.j = z;
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.c++;
    }

    /* Access modifiers changed, original: protected */
    public void b(int i) {
        this.d = i;
    }

    /* Access modifiers changed, original: protected */
    public void b(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addBufferingTime(");
        stringBuilder.append(j);
        stringBuilder.append(")");
        CSLog.d((Object) this, stringBuilder.toString());
        if (this.a.g() >= 0) {
            long g = j - this.a.g();
            this.a.d(-1);
            this.a.a(this.a.e() + g);
            c(c() + g);
            stringBuilder = new StringBuilder();
            stringBuilder.append("addBufferingTime(");
            stringBuilder.append(j);
            stringBuilder.append(") ->");
            stringBuilder.append(g);
            CSLog.d((Object) this, stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(HashMap<String, String> hashMap, StreamSenseState streamSenseState) {
        String str = (String) hashMap.get("ns_st_sp");
        if (str != null) {
            try {
                this.c = Integer.parseInt(str);
                hashMap.remove("ns_st_sp");
            } catch (NumberFormatException unused) {
            }
        }
        str = (String) hashMap.get("ns_st_bc");
        if (str != null) {
            try {
                this.e = Integer.parseInt(str);
                hashMap.remove("ns_st_bc");
            } catch (NumberFormatException unused2) {
            }
        }
        str = (String) hashMap.get("ns_st_bp");
        if (str != null) {
            try {
                this.f = Long.parseLong(str);
                hashMap.remove("ns_st_bp");
            } catch (NumberFormatException unused3) {
            }
        }
        str = (String) hashMap.get("ns_st_id");
        if (str != null) {
            this.b = str;
            hashMap.remove("ns_st_id");
        }
        if (streamSenseState != StreamSenseState.BUFFERING) {
            str = (String) hashMap.get("ns_st_pa");
            if (str != null) {
                try {
                    this.g = Long.parseLong(str);
                    hashMap.remove("ns_st_pa");
                } catch (NumberFormatException unused4) {
                }
            }
        }
        if (streamSenseState == StreamSenseState.PAUSED || streamSenseState == StreamSenseState.IDLE || streamSenseState == null) {
            String str2 = (String) hashMap.get("ns_st_pp");
            if (str2 != null) {
                try {
                    this.d = Integer.parseInt(str2);
                    hashMap.remove("ns_st_pp");
                } catch (NumberFormatException unused5) {
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public long c() {
        long j = this.f;
        return this.a.g() >= 0 ? j + (System.currentTimeMillis() - this.a.g()) : j;
    }

    /* Access modifiers changed, original: protected */
    public void c(int i) {
        this.e = i;
    }

    /* Access modifiers changed, original: protected */
    public void c(long j) {
        this.f = j;
    }

    /* Access modifiers changed, original: protected */
    public long d() {
        long j = this.g;
        return this.a.getPlaybackTimestamp() >= 0 ? j + (System.currentTimeMillis() - this.a.getPlaybackTimestamp()) : j;
    }

    /* Access modifiers changed, original: protected */
    public void d(int i) {
        this.i = i;
    }

    /* Access modifiers changed, original: protected */
    public void d(long j) {
        this.g = j;
    }

    /* Access modifiers changed, original: protected */
    public int e() {
        return this.d;
    }

    /* Access modifiers changed, original: protected */
    public void f() {
        this.d++;
        this.a.b();
    }

    /* Access modifiers changed, original: protected */
    public int g() {
        return this.e;
    }

    public StreamSenseClip getClip() {
        return this.a;
    }

    public String getLabel(String str) {
        return (String) this.h.get(str);
    }

    public HashMap<String, String> getLabels() {
        return this.h;
    }

    public String getPlaylistId() {
        return this.b;
    }

    /* Access modifiers changed, original: protected */
    public void h() {
        this.e++;
    }

    /* Access modifiers changed, original: protected */
    public void i() {
        this.i++;
    }

    /* Access modifiers changed, original: protected */
    public boolean j() {
        return this.j;
    }

    public void reset() {
        reset(null);
    }

    public void reset(Set<String> set) {
        if (set == null || set.isEmpty()) {
            this.h.clear();
        } else {
            StreamSenseUtils.filterMap(this.h, set);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append("_");
        stringBuilder.append(this.i);
        setPlaylistId(stringBuilder.toString());
        c(0);
        d(0);
        b(0);
        a(0);
        c(0);
        this.j = false;
    }

    public void setLabel(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, str2);
        a(hashMap, null);
    }

    public void setLabels(HashMap<String, String> hashMap) {
        a((HashMap) hashMap, null);
    }

    public void setPlaylistId(String str) {
        this.b = str;
    }
}
