package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer.VideoStreamPlayerCallback;
import com.google.ads.interactivemedia.v3.impl.data.p;
import com.google.ads.interactivemedia.v3.impl.data.s;
import com.google.ads.interactivemedia.v3.internal.ji.b;
import java.util.HashMap;
import java.util.Map;

public class jn implements VideoStreamPlayerCallback, b, jo {
    private VideoStreamPlayer a;
    private jd b;
    private final jl c;
    private boolean d;
    private iv e;
    private il f;
    private final String g;
    private String h;

    /* renamed from: com.google.ads.interactivemedia.v3.internal.jn$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[jc.b.values().length];

        static {
            try {
                a[jc.b.loadStream.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    static abstract class a {
        a() {
        }

        public abstract String TXXX();

        public static a create(String str) {
            return new ip(str);
        }
    }

    public jn(String str, jf jfVar, jd jdVar, jl jlVar, StreamDisplayContainer streamDisplayContainer, String str2, Context context) throws AdError {
        this(str, jfVar, jdVar, jlVar, streamDisplayContainer, str2, null, null, context);
    }

    public boolean b(jc.b bVar, p pVar) {
        return false;
    }

    public void d() {
    }

    public boolean f() {
        return true;
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
    }

    public jn(String str, jf jfVar, jd jdVar, jl jlVar, StreamDisplayContainer streamDisplayContainer, String str2, iv ivVar, il ilVar, Context context) throws AdError {
        this.d = false;
        this.a = streamDisplayContainer.getVideoStreamPlayer();
        if (this.a == null) {
            throw new AdError(AdErrorType.LOAD, AdErrorCode.INVALID_ARGUMENTS, "Server-side ad insertion player was not provided.");
        }
        this.c = jlVar;
        this.g = str;
        this.b = jdVar;
        this.h = str2;
        this.d = false;
        this.e = ivVar;
        if (this.e == null) {
            this.e = new iv(this.a, jfVar.a());
        }
        this.f = ilVar;
        if (this.f == null) {
            try {
                this.f = new il(jdVar, streamDisplayContainer);
            } catch (AdError e) {
                Log.e("IMASDK", "Error creating ad UI: ", e);
            }
        }
    }

    public void a(boolean z) {
        this.e.a(this);
    }

    public VideoProgressUpdate getAdProgress() {
        return this.a.getContentProgress();
    }

    public void a(jc.b bVar, p pVar) {
        if (AnonymousClass1.a[bVar.ordinal()] == 1) {
            if (pVar == null || pVar.streamUrl == null) {
                this.c.a((AdErrorEvent) new id(new AdError(AdErrorType.LOAD, AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
                return;
            }
            this.d = false;
            this.e.b();
            this.a.loadUrl(a(pVar.streamUrl), pVar.subtitles);
        }
    }

    public void a(com.google.ads.interactivemedia.v3.impl.data.b bVar) {
        this.f.a(bVar);
    }

    public void a() {
        this.f.a();
    }

    public void e() {
        Log.d("SDK_DEBUG", "Destroying StreamVideoDisplay");
        h();
        this.a = null;
        this.b = null;
        if (this.e != null) {
            this.e.c();
            this.e.b(this);
        }
        this.e = null;
        this.f.b();
        this.f = null;
    }

    public void onUserTextReceived(String str) {
        a(jc.b.timedMetadata, a.create(str));
    }

    public void onVolumeChanged(int i) {
        a(jc.b.volumeChange, s.builder().volumePercentage(i).build());
    }

    public void a(VideoProgressUpdate videoProgressUpdate) {
        if (!this.d) {
            a(jc.b.start, s.builder().volumePercentage(this.a.getVolume()).build());
            this.d = true;
        }
        a(jc.b.timeupdate, (Object) videoProgressUpdate);
    }

    public void b() {
        this.a.onAdBreakStarted();
    }

    public void c() {
        this.a.onAdBreakEnded();
    }

    public void g() {
        this.a.addCallback(this);
    }

    public void h() {
        this.a.removeCallback(this);
    }

    private void a(jc.b bVar, Object obj) {
        this.b.b(new jc(com.google.ads.interactivemedia.v3.internal.jc.a.videoDisplay, bVar, this.g, obj));
    }

    public String a(String str) {
        if (str == null || this.h == null || this.h.length() == 0) {
            return str;
        }
        String replaceAll = this.h.trim().replaceAll("\\s+", "");
        if (replaceAll.charAt(0) == '?') {
            replaceAll = replaceAll.substring(1);
        }
        if (replaceAll.length() == 0) {
            return str;
        }
        Map a = ju.a(Uri.parse(str));
        Map hashMap = new HashMap();
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.clearQuery();
        String str2 = "http://www.dom.com/path?";
        replaceAll = String.valueOf(replaceAll);
        Map a2 = ju.a(Uri.parse(replaceAll.length() != 0 ? str2.concat(replaceAll) : new String(str2)));
        hashMap.putAll(a2);
        if (!a.isEmpty()) {
            for (String str3 : a.keySet()) {
                if (!a2.containsKey(str3)) {
                    hashMap.put(str3, (String) a.get(str3));
                }
            }
        }
        buildUpon.encodedQuery(ju.a(hashMap));
        return buildUpon.build().toString();
    }
}
