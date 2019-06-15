package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import com.google.api.client.http.HttpMethods;
import java.util.Collections;
import java.util.Map;

@zzark
public final class zzbif {
    private final String method;
    public final Uri uri;
    public final String url;
    public final Map<String, String> zzab;

    @TargetApi(21)
    public zzbif(WebResourceRequest webResourceRequest) {
        this(webResourceRequest.getUrl().toString(), webResourceRequest.getUrl(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders());
    }

    public zzbif(String str) {
        this(str, Uri.parse(str), null, null);
    }

    private zzbif(String str, Uri uri, String str2, Map<String, String> map) {
        Map map2;
        this.url = str;
        this.uri = uri;
        if (str2 == null) {
            str2 = HttpMethods.GET;
        }
        this.method = str2;
        if (map2 == null) {
            map2 = Collections.emptyMap();
        }
        this.zzab = map2;
    }
}
