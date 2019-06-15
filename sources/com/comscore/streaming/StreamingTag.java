package com.comscore.streaming;

import com.comscore.utils.Date;
import com.comscore.utils.Utils;
import com.til.colombia.android.internal.e;
import java.util.HashMap;

public class StreamingTag {
    private String[] a = new String[]{"ns_st_ci", "c3", "c4", "c6", "ns_st_st", "ns_st_pu", "ns_st_pr", "ns_st_ep", "ns_st_sn", "ns_st_en", "ns_st_ct"};
    private StreamSense b = new StreamSense();
    private long c = 0;
    private long d = 0;
    private int e = 0;
    private HashMap<String, String> f = null;
    private p g = p.None;
    private boolean h = false;

    public StreamingTag() {
        this.b.setLabel("ns_st_it", e.o);
    }

    private HashMap<String, String> a(HashMap<String, String> hashMap) {
        HashMap mapOfStrings = Utils.mapOfStrings(hashMap);
        for (String str : this.a) {
            if (!mapOfStrings.containsKey(str)) {
                mapOfStrings.put(str, str == "ns_st_ci" ? "0" : "*null");
            }
        }
        return mapOfStrings;
    }

    private void a(long j) {
        if (this.b.getState() == StreamSenseState.IDLE || this.b.getState() == StreamSenseState.PAUSED) {
            if (this.b.getState() == StreamSenseState.PAUSED) {
                this.b.notify(StreamSenseEventType.END, this.d);
            }
            return;
        }
        this.b.notify(StreamSenseEventType.END, b(j));
    }

    private void a(long j, HashMap<String, String> hashMap) {
        a(j);
        this.e++;
        if (!hashMap.containsKey("ns_st_cn")) {
            hashMap.put("ns_st_cn", String.valueOf(this.e));
        }
        if (!hashMap.containsKey("ns_st_pn")) {
            hashMap.put("ns_st_pn", "1");
        }
        if (!hashMap.containsKey("ns_st_tp")) {
            hashMap.put("ns_st_tp", "0");
        }
        this.b.setClip(hashMap);
        this.f = hashMap;
        this.c = j;
        this.d = 0;
        this.b.notify(StreamSenseEventType.PLAY, this.d);
    }

    private void a(HashMap<String, String> hashMap, p pVar) {
        long unixTime = Date.unixTime();
        HashMap a = a(Utils.mapOfStrings(hashMap));
        if (this.g == p.None) {
            this.g = pVar;
        }
        if (this.h && this.g == pVar && c(a)) {
            this.b.getClip().setLabels(a);
            if (this.b.getState() != StreamSenseState.PLAYING) {
                this.c = unixTime;
                this.b.notify(StreamSenseEventType.PLAY, this.d);
            }
        } else {
            a(unixTime, a);
        }
        this.h = true;
        this.g = pVar;
    }

    private boolean a(String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        if (str != null) {
            int i = 1;
            int i2 = hashMap != null ? 1 : 0;
            if (hashMap2 == null) {
                i = 0;
            }
            if ((i & i2) != 0) {
                String str2 = (String) hashMap.get(str);
                str = (String) hashMap2.get(str);
                if (!(str2 == null || str == null)) {
                    return str2.equals(str);
                }
            }
        }
        return false;
    }

    private long b(long j) {
        if (this.c <= 0 || j < this.c) {
            this.d = 0;
        } else {
            this.d += j - this.c;
        }
        return this.d;
    }

    private void b(HashMap<String, String> hashMap) {
        long unixTime = Date.unixTime();
        a(unixTime);
        this.e++;
        HashMap a = a(Utils.mapOfStrings(hashMap));
        if (!a.containsKey("ns_st_cn")) {
            a.put("ns_st_cn", String.valueOf(this.e));
        }
        if (!a.containsKey("ns_st_pn")) {
            a.put("ns_st_pn", "1");
        }
        if (!a.containsKey("ns_st_tp")) {
            a.put("ns_st_tp", "1");
        }
        if (!a.containsKey("ns_st_ad")) {
            a.put("ns_st_ad", "1");
        }
        this.b.setClip(a);
        this.d = 0;
        this.b.notify(StreamSenseEventType.PLAY, this.d);
        this.c = unixTime;
        this.h = false;
    }

    private boolean c(HashMap<String, String> hashMap) {
        HashMap a = a(Utils.mapOfStrings(hashMap));
        for (String a2 : this.a) {
            if (!a(a2, this.f, a)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public void playAdvertisement() {
        HashMap hashMap = new HashMap();
        hashMap.put("ns_st_ct", "va");
        b(hashMap);
    }

    @Deprecated
    public void playAudioAdvertisement() {
        playAudioAdvertisement(new HashMap());
    }

    @Deprecated
    public void playAudioAdvertisement(HashMap<String, String> hashMap) {
        playAudioAdvertisement(hashMap, null);
    }

    public void playAudioAdvertisement(HashMap<String, String> hashMap, AdType adType) {
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        if (!hashMap2.containsKey("ns_st_ct")) {
            Object stringBuilder;
            String str = "a";
            if (adType == null) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("a");
                stringBuilder = stringBuilder2.toString();
            } else {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append(adType.a());
                stringBuilder = stringBuilder3.toString();
            }
            hashMap2.put("ns_st_ct", stringBuilder);
        }
        b(hashMap2);
    }

    @Deprecated
    public void playAudioContentPart(HashMap<String, String> hashMap) {
        playAudioContentPart(hashMap, null);
    }

    public void playAudioContentPart(HashMap<String, String> hashMap, ContentType contentType) {
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        if (!hashMap2.containsKey("ns_st_ct")) {
            Object stringBuilder;
            String str = "a";
            if (contentType == null) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("c");
                stringBuilder = stringBuilder2.toString();
            } else {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append(contentType.a());
                stringBuilder = stringBuilder3.toString();
            }
            hashMap2.put("ns_st_ct", stringBuilder);
        }
        a(hashMap2, p.AudioContent);
    }

    @Deprecated
    public void playContentPart(HashMap<String, String> hashMap) {
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        if (!hashMap2.containsKey("ns_st_ct")) {
            hashMap2.put("ns_st_ct", "vc");
        }
        a(hashMap2, p.VideoContent);
    }

    @Deprecated
    public void playVideoAdvertisement() {
        playVideoAdvertisement(new HashMap());
    }

    @Deprecated
    public void playVideoAdvertisement(HashMap<String, String> hashMap) {
        playVideoAdvertisement(hashMap, null);
    }

    public void playVideoAdvertisement(HashMap<String, String> hashMap, AdType adType) {
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        if (!hashMap2.containsKey("ns_st_ct")) {
            Object stringBuilder;
            String str = "v";
            if (adType == null) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("a");
                stringBuilder = stringBuilder2.toString();
            } else {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append(adType.a());
                stringBuilder = stringBuilder3.toString();
            }
            hashMap2.put("ns_st_ct", stringBuilder);
        }
        b(hashMap2);
    }

    @Deprecated
    public void playVideoContentPart(HashMap<String, String> hashMap) {
        playVideoContentPart(hashMap, null);
    }

    public void playVideoContentPart(HashMap<String, String> hashMap, ContentType contentType) {
        HashMap hashMap2 = new HashMap();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        if (!hashMap2.containsKey("ns_st_ct")) {
            Object stringBuilder;
            String str = "v";
            if (contentType == null) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("c");
                stringBuilder = stringBuilder2.toString();
            } else {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append(contentType.a());
                stringBuilder = stringBuilder3.toString();
            }
            hashMap2.put("ns_st_ct", stringBuilder);
        }
        a(hashMap2, p.VideoContent);
    }

    public void stop() {
        this.b.notify(StreamSenseEventType.PAUSE, b(Date.unixTime()));
    }
}
