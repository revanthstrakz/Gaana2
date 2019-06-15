package com.comscore.streaming;

import com.comscore.utils.Utils;
import java.util.HashMap;
import java.util.Set;

public class StreamSenseClip {
    HashMap<String, String> a = new HashMap();
    private int b;
    private int c;
    private long d;
    private long e;
    private long f;
    private long g;
    private String h;

    public StreamSenseClip() {
        reset();
    }

    private void a(String str, HashMap<String, String> hashMap) {
        String str2 = (String) hashMap.get(str);
        if (str2 != null) {
            this.a.put(str, str2);
        }
    }

    /* Access modifiers changed, original: protected */
    public int a() {
        return this.b;
    }

    /* Access modifiers changed, original: protected */
    public HashMap<String, String> a(StreamSenseEventType streamSenseEventType, HashMap<String, String> hashMap) {
        HashMap hashMap2;
        if (hashMap2 == null) {
            hashMap2 = new HashMap();
        }
        hashMap2.put("ns_st_cn", getClipId());
        hashMap2.put("ns_st_bt", String.valueOf(e()));
        if (streamSenseEventType == StreamSenseEventType.PLAY || streamSenseEventType == null) {
            hashMap2.put("ns_st_sq", String.valueOf(this.c));
        }
        if (streamSenseEventType == StreamSenseEventType.PAUSE || streamSenseEventType == StreamSenseEventType.END || streamSenseEventType == StreamSenseEventType.KEEP_ALIVE || streamSenseEventType == StreamSenseEventType.HEART_BEAT || streamSenseEventType == null) {
            hashMap2.put("ns_st_pt", String.valueOf(f()));
            hashMap2.put("ns_st_pc", String.valueOf(this.b));
        }
        hashMap2.putAll(getLabels());
        return hashMap2;
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
        this.b = i;
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) {
        this.d = j;
    }

    /* Access modifiers changed, original: protected */
    public void a(HashMap<String, String> hashMap, StreamSenseState streamSenseState) {
        if (hashMap != null) {
            this.a.putAll(hashMap);
        }
        b(this.a, streamSenseState);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.b++;
    }

    /* Access modifiers changed, original: protected */
    public void b(int i) {
        this.c = i;
    }

    /* Access modifiers changed, original: protected */
    public void b(long j) {
        this.f = j;
    }

    /* Access modifiers changed, original: protected */
    public void b(HashMap<String, String> hashMap, StreamSenseState streamSenseState) {
        String str = (String) hashMap.get("ns_st_cn");
        if (str != null) {
            setClipId(str);
            hashMap.remove("ns_st_cn");
        }
        str = (String) hashMap.get("ns_st_bt");
        if (str != null) {
            try {
                this.d = Long.parseLong(str);
                hashMap.remove("ns_st_bt");
            } catch (NumberFormatException unused) {
            }
        }
        a("ns_st_cl", (HashMap) hashMap);
        a("ns_st_pn", (HashMap) hashMap);
        a("ns_st_tp", (HashMap) hashMap);
        a("ns_st_ub", (HashMap) hashMap);
        a("ns_st_br", (HashMap) hashMap);
        if (streamSenseState == StreamSenseState.PLAYING || streamSenseState == null) {
            str = (String) hashMap.get("ns_st_sq");
            if (str != null) {
                try {
                    this.c = Integer.parseInt(str);
                    hashMap.remove("ns_st_sq");
                } catch (NumberFormatException unused2) {
                }
            }
        }
        if (streamSenseState != StreamSenseState.BUFFERING) {
            str = (String) hashMap.get("ns_st_pt");
            if (str != null) {
                try {
                    this.f = Long.parseLong(str);
                    hashMap.remove("ns_st_pt");
                } catch (NumberFormatException unused3) {
                }
            }
        }
        if (streamSenseState == StreamSenseState.PAUSED || streamSenseState == StreamSenseState.IDLE || streamSenseState == null) {
            String str2 = (String) hashMap.get("ns_st_pc");
            if (str2 != null) {
                try {
                    this.b = Integer.parseInt(str2);
                    hashMap.remove("ns_st_pc");
                } catch (NumberFormatException unused4) {
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int c() {
        return this.c;
    }

    /* Access modifiers changed, original: protected */
    public void c(long j) {
        this.g = j;
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        this.c++;
    }

    /* Access modifiers changed, original: protected */
    public void d(long j) {
        this.e = j;
    }

    /* Access modifiers changed, original: protected */
    public long e() {
        long j = this.d;
        return this.e >= 0 ? j + (System.currentTimeMillis() - this.e) : j;
    }

    /* Access modifiers changed, original: protected */
    public long f() {
        long j = this.f;
        return this.g >= 0 ? j + (System.currentTimeMillis() - this.g) : j;
    }

    /* Access modifiers changed, original: protected */
    public long g() {
        return this.e;
    }

    public String getClipId() {
        if (Utils.isEmpty(this.h)) {
            setClipId("1");
        }
        return this.h;
    }

    public String getLabel(String str) {
        return (String) this.a.get(str);
    }

    public HashMap<String, String> getLabels() {
        return this.a;
    }

    public long getPlaybackTimestamp() {
        return this.g;
    }

    public void reset() {
        reset(null);
    }

    public void reset(Set<String> set) {
        if (set == null || set.isEmpty()) {
            this.a.clear();
        } else {
            StreamSenseUtils.filterMap(this.a, set);
        }
        if (!this.a.containsKey("ns_st_cl")) {
            this.a.put("ns_st_cl", "0");
        }
        if (!this.a.containsKey("ns_st_pn")) {
            this.a.put("ns_st_pn", "1");
        }
        if (!this.a.containsKey("ns_st_tp")) {
            this.a.put("ns_st_tp", "1");
        }
        a(0);
        b(0);
        a(0);
        d(-1);
        b(0);
        c(-1);
    }

    public void setClipId(String str) {
        this.h = str;
    }

    public void setLabel(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, str2);
        a(hashMap, null);
    }

    public void setLabels(HashMap<String, String> hashMap) {
        a((HashMap) hashMap, null);
    }
}
