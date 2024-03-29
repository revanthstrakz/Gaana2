package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.api.client.http.HttpMethods;

@zzark
public class zzbie extends WebViewClient {
    private static final String[] zzext = new String[]{CARD.UNKNOWN, "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", HttpMethods.CONNECT, "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzexu = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private zzbij zzfbe;

    public void zza(zzbif zzbif) {
    }

    public void zzb(zzbif zzbif) {
    }

    public boolean zzc(zzbif zzbif) {
        return false;
    }

    @Nullable
    public WebResourceResponse zzd(zzbif zzbif) {
        return null;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzbij zzbij) {
        this.zzfbe = zzbij;
    }

    public final void onPageFinished(WebView webView, String str) {
        if (str != null) {
            zzbif zzbif = new zzbif(str);
            if (this.zzfbe != null) {
                this.zzfbe.zza(zzbif);
            } else {
                zza(zzbif);
            }
        }
    }

    public final void onLoadResource(WebView webView, String str) {
        if (str != null) {
            String str2 = "Loading resource: ";
            String valueOf = String.valueOf(str);
            zzaxz.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            zzb(new zzbif(str));
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return str == null ? false : zzc(new zzbif(str));
    }

    @TargetApi(24)
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return (webResourceRequest == null || webResourceRequest.getUrl() == null) ? false : zzc(new zzbif(webResourceRequest));
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return str == null ? null : zzd(new zzbif(str));
    }

    @TargetApi(24)
    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : zzd(new zzbif(webResourceRequest));
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        if (i >= 0 || (-i) - 1 >= zzext.length) {
            String.valueOf(i);
        }
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            if (primaryError < 0 || primaryError >= zzexu.length) {
                String.valueOf(primaryError);
            }
            sslError.getUrl();
        }
    }

    public final boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (!(keyCode == 79 || keyCode == 222)) {
            switch (keyCode) {
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                    break;
                default:
                    switch (keyCode) {
                        case 126:
                        case 127:
                        case 128:
                        case TsExtractor.TS_STREAM_TYPE_AC3 /*129*/:
                        case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /*130*/:
                            break;
                        default:
                            return false;
                    }
            }
        }
        return true;
    }

    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        if (webView.getParent() instanceof ViewGroup) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        webView.destroy();
        return true;
    }
}
