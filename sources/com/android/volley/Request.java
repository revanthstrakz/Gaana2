package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.a.a;
import com.gaana.application.GaanaApplication;
import com.utilities.Util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private boolean dataToBeRefreshed;
    private boolean dataToBeRefreshedAfterCacheResponse;
    private String hashValue;
    private boolean isCacheOnly;
    private boolean isSecureCall;
    private a mCacheEntry;
    private boolean mCanceled;
    private final int mDefaultTrafficStatsTag;
    private final i.a mErrorListener;
    private final a mEventLog;
    private final int mMethod;
    private String mRedirectUrl;
    private h mRequestQueue;
    private boolean mResponseDelivered;
    private k mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private Object mTag;
    private String mUrl;

    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public abstract void deliverResponse(T t);

    public abstract void deliverResponse(T t, boolean z);

    /* Access modifiers changed, original: protected */
    public Map<String, String> getParams() throws AuthFailureError {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String getParamsEncoding() {
        return "UTF-8";
    }

    /* Access modifiers changed, original: protected */
    public VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    public abstract i<T> parseNetworkResponse(g gVar);

    public void setSecureCall(boolean z) {
        this.isSecureCall = z;
    }

    public boolean isSecureCall() {
        return this.isSecureCall;
    }

    public boolean isDataToBeRefreshed() {
        return this.dataToBeRefreshed;
    }

    public void setIsToBeRefreshed(boolean z) {
        this.dataToBeRefreshed = z;
    }

    public void setDataToBeRefreshedAfterCacheResponse(boolean z) {
        this.dataToBeRefreshedAfterCacheResponse = z;
    }

    public boolean isDataToBeRefreshedAfterCacheResponse() {
        return this.dataToBeRefreshedAfterCacheResponse;
    }

    public void setCacheOnly(boolean z) {
        this.isCacheOnly = z;
    }

    public void setRedirectedUrl(String str) {
        this.mRedirectUrl = str;
    }

    public boolean isCacheOnly() {
        return this.isCacheOnly || !Util.j(GaanaApplication.getContext()) || GaanaApplication.getInstance().isAppInOfflineMode();
    }

    public void setHashValue(String str) {
        this.hashValue = str;
    }

    public String getHashValue() {
        return this.hashValue;
    }

    @Deprecated
    public Request(String str, i.a aVar) {
        this(-1, str, aVar);
    }

    public Request(int i, String str, i.a aVar) {
        this.dataToBeRefreshed = false;
        this.isSecureCall = true;
        this.dataToBeRefreshedAfterCacheResponse = false;
        this.mEventLog = a.a ? new a() : null;
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mCacheEntry = null;
        this.mMethod = i;
        this.mUrl = str;
        this.mErrorListener = aVar;
        setRetryPolicy(new c());
        this.mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(str);
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Request<?> setTag(Object obj) {
        this.mTag = obj;
        return this;
    }

    public Object getTag() {
        return this.mTag;
    }

    public i.a getErrorListener() {
        return this.mErrorListener;
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    private static int findDefaultTrafficStatsTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                str = parse.getHost();
                if (str != null) {
                    return str.hashCode();
                }
            }
        }
        return 0;
    }

    public Request<?> setRetryPolicy(k kVar) {
        this.mRetryPolicy = kVar;
        return this;
    }

    public void addMarker(String str) {
        if (a.a) {
            this.mEventLog.a(str, Thread.currentThread().getId());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void finish(final String str) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.b(this);
        }
        if (a.a) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        Request.this.mEventLog.a(str, id);
                        Request.this.mEventLog.a(toString());
                    }
                });
            } else {
                this.mEventLog.a(str, id);
                this.mEventLog.a(toString());
            }
        }
    }

    public Request<?> setRequestQueue(h hVar) {
        this.mRequestQueue = hVar;
        return this;
    }

    public final Request<?> setSequence(int i) {
        this.mSequence = Integer.valueOf(i);
        return this;
    }

    public final int getSequence() {
        if (this.mSequence != null) {
            return this.mSequence.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public String getUrl() {
        return this.mRedirectUrl != null ? this.mRedirectUrl : this.mUrl;
    }

    public String getOriginUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getCacheKey() {
        return getUrl();
    }

    public Request<?> setCacheEntry(a aVar) {
        this.mCacheEntry = aVar;
        return this;
    }

    public a getCacheEntry() {
        return this.mCacheEntry;
    }

    public void cancel() {
        this.mCanceled = true;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return Collections.emptyMap();
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public Map<String, String> getPostParams() throws AuthFailureError {
        return getParams();
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public String getPostParamsEncoding() {
        return getParamsEncoding();
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    public byte[] getPostBody() throws AuthFailureError {
        Map postParams = getPostParams();
        return (postParams == null || postParams.size() <= 0) ? null : encodeParameters(postParams, getPostParamsEncoding());
    }

    public String getBodyContentType() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("application/x-www-form-urlencoded; charset=");
        stringBuilder.append(getParamsEncoding());
        return stringBuilder.toString();
    }

    public byte[] getBody() throws AuthFailureError {
        Map params = getParams();
        return (params == null || params.size() <= 0) ? null : encodeParameters(params, getParamsEncoding());
    }

    private byte[] encodeParameters(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Encoding not supported: ");
            stringBuilder2.append(str);
            throw new RuntimeException(stringBuilder2.toString(), e);
        }
    }

    public final Request<?> setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public final int getTimeoutMs() {
        return this.mRetryPolicy.a();
    }

    public k getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public void markDelivered() {
        this.mResponseDelivered = true;
    }

    public boolean hasHadResponseDelivered() {
        return this.mResponseDelivered;
    }

    public void deliverError(VolleyError volleyError) {
        if (this.mErrorListener != null) {
            this.mErrorListener.onErrorResponse(volleyError);
        }
    }

    public int compareTo(Request<T> request) {
        Priority priority = getPriority();
        Priority priority2 = request.getPriority();
        if (priority == priority2) {
            return this.mSequence.intValue() - request.mSequence.intValue();
        }
        return priority2.ordinal() - priority.ordinal();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(getTrafficStatsTag()));
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(this.mCanceled ? "[X] " : "[ ] ");
        stringBuilder3.append(getUrl());
        stringBuilder3.append(" ");
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(" ");
        stringBuilder3.append(getPriority());
        stringBuilder3.append(" ");
        stringBuilder3.append(this.mSequence);
        return stringBuilder3.toString();
    }
}
