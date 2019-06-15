package com.comscore.streaming.plugin;

import android.util.Log;
import com.comscore.streaming.StreamSense;
import com.comscore.streaming.StreamSenseClip;
import com.comscore.streaming.StreamSenseEventType;
import com.google.api.client.http.HttpStatusCodes;
import com.moengage.inapp.InAppMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamSensePlugin extends StreamSense {
    private static final float[][] e = new float[][]{new float[]{-1.0f, 1.0f}, new float[]{-0.5f, 0.0f, 0.5f}, new float[]{-0.3f, -0.1f, 0.1f, 0.3f}, new float[]{-0.2f, -0.1f, 0.0f, 0.1f, 0.2f}, new float[]{-0.14286f, -0.08571f, -0.02857f, 0.02857f, 0.08571f, 0.14286f}, new float[]{-0.10714f, -0.07143f, -0.03571f, 0.0f, 0.03571f, 0.07143f, 0.10714f}, new float[]{-0.08333f, -0.05952f, -0.03571f, -0.0119f, 0.0119f, 0.03571f, 0.05952f, 0.08333f}, new float[]{-0.06667f, -0.05f, -0.03333f, -0.01667f, 0.0f, 0.01667f, 0.03333f, 0.05f, 0.06667f}, new float[]{-0.05455f, -0.04242f, -0.0303f, -0.01818f, -0.00606f, 0.00606f, 0.01818f, 0.0303f, 0.04242f, 0.05455f}, new float[]{-0.04545f, -0.03636f, -0.02727f, -0.01818f, -0.00909f, 0.0f, 0.00909f, 0.01818f, 0.02727f, 0.03636f, 0.04545f}, new float[]{-0.03846f, -0.03147f, -0.02448f, -0.01748f, -0.01049f, -0.0035f, 0.0035f, 0.01049f, 0.01748f, 0.02448f, 0.03147f, 0.03846f}, new float[]{-0.03297f, -0.02747f, -0.02198f, -0.01648f, -0.01099f, -0.00549f, 0.0f, 0.00549f, 0.01099f, 0.01648f, 0.02198f, 0.02747f, 0.03297f}};
    private HashMap<String, String> A = new HashMap();
    private boolean B = false;
    private boolean C = false;
    private boolean D = false;
    private final String E = "_all_";
    private String[] F = null;
    private int G = -1;
    private String[] H = null;
    private int I = -1;
    private HashMap<String, String> J = new HashMap();
    private String b = "1.0.1";
    private int c = 10;
    private int d = 500;
    private float f = 1.25f;
    private int g = 2;
    private int h = HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES;
    private int i = 6;
    private Runnable j;
    private List<Long> k = new ArrayList();
    private List<Long> l = new ArrayList();
    private long m = 0;
    private boolean n = false;
    private List<StreamSensePluginListener> o = new ArrayList();
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private StreamSensePlayer u;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private HashMap<String, String> y = new HashMap();
    private HashMap<String, String> z = new HashMap();

    public StreamSensePlugin(HashMap<String, String> hashMap, String str, String str2, String str3) {
        if (hashMap != null && hashMap.size() > 0) {
            b((HashMap) hashMap);
        }
        setLabel("ns_st_mp", str, true);
        setLabel("ns_st_pv", str2, true);
        setLabel("ns_st_mv", str3, true);
    }

    private long a(int i) {
        return ((Long) this.k.get((this.k.size() - 1) - i)).longValue();
    }

    private HashMap<String, String> a(HashMap<String, HashMap<String, String>> hashMap, HashMap<String, String> hashMap2) {
        HashMap<String, String> hashMap3 = new HashMap();
        if (hashMap2 != null && hashMap2.size() > 0) {
            hashMap3 = new HashMap(hashMap2);
        }
        int i = 1;
        if (!(this.H != null && this.H.length == 1 && this.H[0].equals("_all_"))) {
            i = 0;
        }
        if (hashMap.size() > 0) {
            if (i == 0) {
                if (hashMap3.size() > 0) {
                    hashMap3.putAll(c((HashMap) hashMap.get("")));
                } else {
                    hashMap3 = c((HashMap) hashMap.get(""));
                }
            }
            for (String str : ((HashMap) hashMap.get("")).keySet()) {
                boolean find = Pattern.compile("^([Cc][A-Da-d]_)?ns_st_.+").matcher(str).find();
                boolean find2 = Pattern.compile("^[Cc][A-Da-d]?([1-9]|1[0-9]|20)$").matcher(str).find();
                if (find || find2) {
                    hashMap3.put(str, ((HashMap) hashMap.get("")).get(str));
                }
            }
        }
        return hashMap3;
    }

    private void a(String str) {
        if (str != null && str.length() != 0) {
            Matcher matcher = Pattern.compile("([^=, ]+)\\s*=(\\s*('([^']+?)'|[a-z0-9\\._-]+)\\s*\\+?)+\\s*", 2).matcher(str);
            while (matcher.find()) {
                String[] split = str.substring(matcher.start(), matcher.end()).split("=", 2);
                if (split != null && split.length == 2) {
                    String replace = split[0].replace(" ", "");
                    if (replace != null && replace.length() > 0) {
                        this.J.put(replace, split[1]);
                    }
                }
            }
        }
    }

    private boolean a(long j) {
        return Long.parseLong(getClip().getLabel("ns_st_cl")) > 0 && (j > Long.parseLong(getClip().getLabel("ns_st_cl")) || Math.abs(j - Long.parseLong(getClip().getLabel("ns_st_cl"))) < ((long) this.d));
    }

    private long b(int i) {
        return ((Long) this.l.get((this.l.size() - 1) - i)).longValue();
    }

    private HashMap<String, String> b(HashMap<String, HashMap<String, String>> hashMap, HashMap<String, String> hashMap2) {
        HashMap hashMap3 = new HashMap();
        if (hashMap2 != null && hashMap2.size() > 0) {
            hashMap3 = new HashMap(hashMap2);
        }
        for (String str : this.J.keySet()) {
            String str2 = "";
            String str3 = "^'([^']+)'$";
            Matcher matcher = Pattern.compile("'([^']+?)'|[a-z0-9\\._-]+\\s*", 2).matcher((CharSequence) this.J.get(str));
            while (matcher.find()) {
                Object replace = ((String) this.J.get(str)).substring(matcher.start(), matcher.end()).replace(" ", "");
                if (Pattern.compile(str3, 2).matcher(replace).find()) {
                    str2 = replace.replaceFirst("^'+", "").replaceFirst("'+$", "");
                } else {
                    try {
                        Object obj = "";
                        int lastIndexOf = replace.lastIndexOf(46);
                        if (lastIndexOf >= 1 && lastIndexOf < replace.length() - 1) {
                            obj = replace.substring(0, lastIndexOf);
                            replace = replace.substring(lastIndexOf + 1, replace.length());
                        }
                        for (String str4 : hashMap.keySet()) {
                            HashMap hashMap4 = (HashMap) hashMap.get(str4);
                            if (str4.equals(obj)) {
                                if (hashMap4 != null && hashMap4.containsKey(replace)) {
                                    str2 = (String) hashMap4.get(replace);
                                }
                            }
                        }
                    } catch (Exception unused) {
                        log("Exception occurred while processing mapped labels");
                    }
                }
                hashMap3.put(str, str2);
            }
        }
        return hashMap3;
    }

    private void b(String str) {
        if (str != null && str.length() != 0) {
            for (String split : str.split(",")) {
                String split2;
                String[] split3 = split2.split("=", 2);
                if (split3.length == 2) {
                    String replace = split3[0].replace(" ", "");
                    if (replace != null && replace.length() > 0) {
                        split2 = split3[1].replaceFirst("^'+", "").replaceFirst("'+$", "");
                        setLabel(replace, split2);
                        this.A.put(replace, split2);
                    }
                }
            }
        }
    }

    private void b(HashMap<String, String> hashMap) {
        String str;
        this.D = Boolean.valueOf((String) hashMap.get(InAppConstants.RESP_ATTR_DEBUG_ENABLED)).booleanValue();
        if (hashMap.containsKey("labelmapping")) {
            a((String) hashMap.get("labelmapping"));
        }
        if (hashMap.containsKey("persistentlabels")) {
            b((String) hashMap.get("persistentlabels"));
        }
        if (hashMap.containsKey("throttling") && Boolean.valueOf((String) hashMap.get("throttling")).booleanValue()) {
            setPausePlaySwitchDelayEnabled(true);
        } else {
            setPausePlaySwitchDelayEnabled(false);
        }
        if (hashMap.containsKey("include")) {
            str = (String) hashMap.get("include");
            if (str == "_all_") {
                this.F = new String[1];
                this.F[0] = "_all_";
            } else if (str.length() > 0) {
                this.F = str.split(",");
            }
        }
        if ((this.F == null || this.F.length == 0 || !this.F[0].equals("_all_")) && hashMap.containsKey("include_prefixes")) {
            str = (String) hashMap.get("include_prefixes");
            if (str != null && str.length() > 0) {
                if (str.equals("_all_")) {
                    this.F = new String[1];
                    this.F[0] = "_all_";
                } else {
                    if (this.F == null) {
                        this.F = new String[0];
                    }
                    this.G = this.F.length;
                    ArrayList arrayList = new ArrayList(Arrays.asList(this.F));
                    arrayList.addAll(Arrays.asList(str.split(",")));
                    this.F = new String[arrayList.size()];
                    this.F = (String[]) arrayList.toArray(this.F);
                }
            }
        }
        if (this.F == null) {
            this.H = new String[1];
            this.H[0] = "_all_";
            return;
        }
        if (hashMap.containsKey("exclude")) {
            str = (String) hashMap.get("exclude");
            if (str.equals("_all_")) {
                this.H = new String[1];
                this.H[0] = "_all_";
            } else if (str.length() > 0) {
                this.H = str.split(",");
            }
        }
        if ((this.H == null || this.H.length == 0 || !this.H[0].equals("_all_")) && hashMap.containsKey("exclude_prefixes")) {
            String str2 = (String) hashMap.get("exclude_prefixes");
            if (str2.equals("_all_")) {
                this.H = new String[1];
                this.H[0] = "_all_";
                return;
            }
            if (this.H == null) {
                this.H = new String[0];
            }
            this.I = this.H.length;
            ArrayList arrayList2 = new ArrayList(Arrays.asList(this.H));
            arrayList2.addAll(Arrays.asList(str2.split(",")));
            this.H = new String[arrayList2.size()];
            this.H = (String[]) arrayList2.toArray(this.H);
        }
    }

    private HashMap<String, String> c(HashMap<String, String> hashMap) {
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        if (hashMap != null && hashMap.size() > 0) {
            hashMap3 = new HashMap(hashMap);
        }
        if (this.H != null && this.H.length == 1 && this.H[0].equals("_all_")) {
            return new HashMap();
        }
        int length;
        String str;
        boolean z;
        if (!(this.F == null || this.F.length <= 0 || this.F[0].equals("_all_"))) {
            length = this.F.length;
            int i = 0;
            while (i < length) {
                str = this.F[i];
                z = this.G >= 0 && i >= this.G;
                for (String str2 : hashMap3.keySet()) {
                    if (!hashMap2.containsKey(str2)) {
                        boolean z2;
                        if (z) {
                            z2 = false;
                            hashMap2.put(str2, Boolean.valueOf(z2));
                        } else {
                            z2 = false;
                            hashMap2.put(str2, Boolean.valueOf(z2));
                        }
                        z2 = true;
                        hashMap2.put(str2, Boolean.valueOf(z2));
                    }
                }
                i++;
            }
            for (String str3 : hashMap2.keySet()) {
                if (!((Boolean) hashMap2.get(str3)).booleanValue()) {
                    hashMap3.remove(str3);
                }
            }
            hashMap2 = new HashMap();
        }
        if (this.H != null && this.H.length > 0) {
            length = this.H.length;
            HashMap hashMap4 = hashMap2;
            int i2 = 0;
            while (i2 < length) {
                str = this.H[i2];
                z = this.I >= 0 && i2 >= this.I;
                for (String str22 : hashMap3.keySet()) {
                    if (z) {
                        if (str22.indexOf(str) != 0) {
                        }
                    } else if (!str22.equals(str)) {
                    }
                    hashMap4.put(str22, Boolean.valueOf(true));
                }
                for (String str4 : hashMap4.keySet()) {
                    if (hashMap4.containsKey(str4) && hashMap3.containsKey(str4)) {
                        hashMap3.remove(str4);
                    }
                }
                hashMap4 = new HashMap();
                i2++;
            }
        }
        return hashMap3;
    }

    private void c() {
        if (this.j != null) {
            this.a.getTaskExecutor().removeEnqueuedTask(this.j);
            this.j = null;
        }
    }

    private void d() {
        c();
        if (this.t && this.u != null) {
            this.j = e();
            this.a.getTaskExecutor().execute(this.j, 0, true, (long) this.h);
        }
    }

    private Runnable e() {
        return new a(this);
    }

    private boolean f() {
        boolean z = false;
        if (a(0) < a(1)) {
            return true;
        }
        int i = this.h;
        float f = 0.0f;
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            f += e[this.k.size() - 2][i2] * ((float) ((Long) this.k.get(i2)).longValue());
        }
        if (f / ((float) i) > this.f) {
            z = true;
        }
        return z;
    }

    public void addListener(StreamSensePluginListener streamSensePluginListener) {
        this.o.add(streamSensePluginListener);
    }

    public void clearAllListeners() {
        this.o.clear();
    }

    public void clearListener(StreamSensePluginListener streamSensePluginListener) {
        this.o.remove(streamSensePluginListener);
    }

    public String getGenericPluginVersion() {
        return this.b;
    }

    public StreamSensePlayer getPlayer() {
        return this.u;
    }

    public void log(String str) {
        if (this.D) {
            Log.d(getClass().getSimpleName(), str);
        }
    }

    public void notify(StreamSenseEventType streamSenseEventType, HashMap<String, String> hashMap, long j) {
        if (!this.B && !this.C) {
            HashMap hashMap2;
            if (hashMap2 == null) {
                hashMap2 = new HashMap();
            }
            for (StreamSensePluginListener onGetLabels : this.o) {
                onGetLabels.onGetLabels(streamSenseEventType, hashMap2);
            }
            if (j <= 0) {
                j = this.u != null ? this.u.getPosition() : 0;
            }
            if (j < 0) {
                j = 0;
            }
            if (streamSenseEventType == StreamSenseEventType.END && this.t) {
                this.k = new ArrayList();
                this.l = new ArrayList();
                this.n = false;
                this.v = true;
                this.w = true;
            } else if (streamSenseEventType == StreamSenseEventType.PLAY) {
                this.x = true;
            }
            super.notify(streamSenseEventType, hashMap2, j);
        }
    }

    public void setBitRate(long j) {
        setLabel("ns_st_br", j > 0 ? String.valueOf(j) : "0");
    }

    public Boolean setClip(HashMap<String, String> hashMap, boolean z, HashMap<String, HashMap<String, String>> hashMap2) {
        return setClip(hashMap, z, hashMap2, false);
    }

    public Boolean setClip(HashMap<String, String> hashMap, boolean z, HashMap<String, HashMap<String, String>> hashMap2, boolean z2) {
        HashMap hashMap3;
        if (!(!this.x || hashMap3 == null || hashMap3.get("ns_st_ci") == getClip().getLabel("ns_st_ci"))) {
            notify(StreamSenseEventType.END, this.u.getPosition());
            this.x = false;
        }
        if (hashMap2 != null && hashMap2.size() > 0) {
            hashMap3 = b((HashMap) hashMap2, new HashMap(a((HashMap) hashMap2, new HashMap(hashMap3))));
        }
        if (!z2) {
            this.z = new HashMap();
            if (hashMap3 != null && hashMap3.size() > 0) {
                for (String str : hashMap3.keySet()) {
                    this.z.put(str, hashMap3.get(str));
                }
            }
        } else if (this.z != null && this.z.size() > 0) {
            for (String str2 : this.z.keySet()) {
                hashMap3.put(str2, this.z.get(str2));
            }
        }
        if (hashMap3 == null || hashMap3.get("ns_st_skip") == null || !Boolean.parseBoolean((String) hashMap3.get("ns_st_skip"))) {
            this.B = false;
        } else {
            this.B = true;
        }
        return super.setClip(hashMap3, z);
    }

    public void setClipLabel(String str, String str2) {
        setClipLabel(str, str2, false);
    }

    public void setClipLabel(String str, String str2, boolean z) {
        if (!z) {
            this.z.put(str, str2);
        } else if (!(this.z.get(str) == null && this.A.get(str) == null)) {
            return;
        }
        getClip().setLabel(str, str2);
    }

    public void setDetectEnd(boolean z) {
        this.s = z;
    }

    public void setDetectPause(boolean z) {
        this.q = z;
    }

    public void setDetectPlay(boolean z) {
        this.r = z;
    }

    public void setDetectSeek(boolean z) {
        this.p = z;
    }

    public void setDuration(long j) {
        getClip().setLabel("ns_st_cl", j >= 0 ? String.valueOf(j) : "0");
    }

    public void setEndDetectionErrorMargin(int i) {
        this.d = i;
    }

    public void setIsFullScreen(boolean z) {
        setLabel("ns_st_ws", z ? InAppMessage.INAPP_ALIGN_FULL : "norm");
    }

    public void setLabel(String str, String str2) {
        setLabel(str, str2, false);
    }

    public void setLabel(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, str2);
        setLabels(hashMap, z);
    }

    public void setLabels(HashMap<String, String> hashMap) {
        setLabels(hashMap, false);
    }

    public void setLabels(HashMap<String, String> hashMap, boolean z) {
        if (!z) {
            this.A = new HashMap();
            if (hashMap != null && hashMap.size() > 0) {
                for (String str : hashMap.keySet()) {
                    this.A.put(str, hashMap.get(str));
                }
            }
        } else if (this.A != null && this.A.size() > 0) {
            for (String str2 : this.A.keySet()) {
                hashMap.put(str2, this.A.get(str2));
            }
        }
        super.setLabels(hashMap);
    }

    public void setMaximumNumberOfEntriesInHistory(int i) {
        if (i >= 2 && i <= 13) {
            this.i = i;
        }
    }

    public void setMinimumNumberOfTimeUpdateEventsBeforeSensingAnything(int i) {
        if (i >= 2 && i <= 13) {
            this.g = i;
        }
    }

    public void setPauseDetectionErrorMargin(int i) {
        this.c = i;
    }

    public void setPlayer(StreamSensePlayer streamSensePlayer) {
        this.u = streamSensePlayer;
    }

    public Boolean setPlaylist(HashMap<String, String> hashMap) {
        return setPlaylist(hashMap, false);
    }

    public Boolean setPlaylist(HashMap<String, String> hashMap, boolean z) {
        if (!z) {
            this.y = new HashMap();
            if (hashMap != null && hashMap.size() > 0) {
                for (String str : hashMap.keySet()) {
                    this.y.put(str, hashMap.get(str));
                }
            }
        } else if (this.y != null && this.y.size() > 0) {
            for (String str2 : this.y.keySet()) {
                hashMap.put(str2, this.y.get(str2));
            }
        }
        z = (hashMap == null || hashMap.get("ns_st_skip") == null || !Boolean.parseBoolean((String) hashMap.get("ns_st_skip"))) ? false : true;
        this.C = z;
        return super.setPlaylist(hashMap);
    }

    public void setPlaylistLabel(String str, String str2) {
        setPlaylistLabel(str, str2, false);
    }

    public void setPlaylistLabel(String str, String str2, boolean z) {
        if (!z || !z) {
            this.y.put(str, str2);
        } else if (!(this.y.get(str) == null && this.A.get(str) == null)) {
            return;
        }
        getPlaylist().setLabel(str, str2);
    }

    public void setPulseSamplingInterval(int i) {
        if (i > 0) {
            this.h = i;
        }
    }

    public void setSeekDetectionMinQuotient(float f) {
        if (f > 1.0f) {
            this.f = f;
        }
    }

    public void setSmartStateDetection(boolean z) {
        boolean z2 = this.t;
        this.t = z;
        if (z2 || !z) {
            if (z2 && !z) {
                c();
            }
            return;
        }
        d();
    }

    public void setVideoSize(String str) {
        StreamSenseClip clip = getClip();
        String str2 = "ns_st_cs";
        if (str == null || str.length() <= 0) {
            str = "0";
        }
        clip.setLabel(str2, str);
    }

    public void setVolume(int i) {
        String str = "ns_st_vo";
        String valueOf = (i < 0 || i > 100) ? "100" : String.valueOf(i);
        setLabel(str, valueOf);
    }
}
