package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.ImageRequest.Callback;
import com.facebook.internal.WorkQueue.WorkItem;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static Handler handler;
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    private static class CacheReadWorkItem implements Runnable {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        CacheReadWorkItem(Context context, RequestKey requestKey, boolean z) {
            this.context = context;
            this.key = requestKey;
            this.allowCachedRedirects = z;
        }

        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }

    private static class DownloadImageWorkItem implements Runnable {
        private Context context;
        private RequestKey key;

        DownloadImageWorkItem(Context context, RequestKey requestKey) {
            this.context = context;
            this.key = requestKey;
        }

        public void run() {
            ImageDownloader.download(this.key, this.context);
        }
    }

    private static class DownloaderContext {
        boolean isCancelled;
        ImageRequest request;
        WorkItem workItem;

        private DownloaderContext() {
        }

        /* synthetic */ DownloaderContext(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private static class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        Uri uri;

        RequestKey(Uri uri, Object obj) {
            this.uri = uri;
            this.tag = obj;
        }

        public int hashCode() {
            return ((1073 + this.uri.hashCode()) * 37) + this.tag.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) obj;
            if (requestKey.uri == this.uri && requestKey.tag == this.tag) {
                return true;
            }
            return false;
        }
    }

    public static void downloadAsync(ImageRequest imageRequest) {
        if (imageRequest != null) {
            RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
            synchronized (pendingRequests) {
                DownloaderContext downloaderContext = (DownloaderContext) pendingRequests.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = imageRequest;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                } else {
                    enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
                }
            }
        }
    }

    public static boolean cancelRequest(ImageRequest imageRequest) {
        boolean z;
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = (DownloaderContext) pendingRequests.get(requestKey);
            z = true;
            if (downloaderContext == null) {
                z = false;
            } else if (downloaderContext.workItem.cancel()) {
                pendingRequests.remove(requestKey);
            } else {
                downloaderContext.isCancelled = true;
            }
        }
        return z;
    }

    public static void prioritizeRequest(ImageRequest imageRequest) {
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = (DownloaderContext) pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }

    private static void enqueueCacheRead(ImageRequest imageRequest, RequestKey requestKey, boolean z) {
        enqueueRequest(imageRequest, requestKey, cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, z));
    }

    private static void enqueueDownload(ImageRequest imageRequest, RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }

    private static void enqueueRequest(ImageRequest imageRequest, RequestKey requestKey, WorkQueue workQueue, Runnable runnable) {
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = imageRequest;
            pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }

    private static void issueResponse(RequestKey requestKey, Exception exception, Bitmap bitmap, boolean z) {
        DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            final ImageRequest imageRequest = removePendingRequest.request;
            final Callback callback = imageRequest.getCallback();
            if (callback != null) {
                final Exception exception2 = exception;
                final boolean z2 = z;
                final Bitmap bitmap2 = bitmap;
                getHandler().post(new Runnable() {
                    public void run() {
                        callback.onCompleted(new ImageResponse(imageRequest, exception2, z2, bitmap2));
                    }
                });
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
    private static void readFromCache(com.facebook.internal.ImageDownloader.RequestKey r2, android.content.Context r3, boolean r4) {
        /*
        r0 = 0;
        r1 = 0;
        if (r4 == 0) goto L_0x0014;
    L_0x0004:
        r4 = r2.uri;
        r4 = com.facebook.internal.UrlRedirectCache.getRedirectedUri(r4);
        if (r4 == 0) goto L_0x0014;
    L_0x000c:
        r4 = com.facebook.internal.ImageResponseCache.getCachedImageStream(r4, r3);
        if (r4 == 0) goto L_0x0015;
    L_0x0012:
        r0 = 1;
        goto L_0x0015;
    L_0x0014:
        r4 = r1;
    L_0x0015:
        if (r0 != 0) goto L_0x001d;
    L_0x0017:
        r4 = r2.uri;
        r4 = com.facebook.internal.ImageResponseCache.getCachedImageStream(r4, r3);
    L_0x001d:
        if (r4 == 0) goto L_0x002a;
    L_0x001f:
        r3 = android.graphics.BitmapFactory.decodeStream(r4);
        com.facebook.internal.Utility.closeQuietly(r4);
        issueResponse(r2, r1, r3, r0);
        goto L_0x0039;
    L_0x002a:
        r3 = removePendingRequest(r2);
        if (r3 == 0) goto L_0x0039;
    L_0x0030:
        r4 = r3.isCancelled;
        if (r4 != 0) goto L_0x0039;
    L_0x0034:
        r3 = r3.request;
        enqueueDownload(r3, r2);
    L_0x0039:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.readFromCache(com.facebook.internal.ImageDownloader$RequestKey, android.content.Context, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00af A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0014} */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c8  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:42:0x00af, code skipped:
            r9 = th;
     */
    /* JADX WARNING: Missing block: B:43:0x00b1, code skipped:
            r10 = e;
     */
    /* JADX WARNING: Missing block: B:44:0x00b2, code skipped:
            r4 = null;
     */
    private static void download(com.facebook.internal.ImageDownloader.RequestKey r9, android.content.Context r10) {
        /*
        r0 = 0;
        r1 = 0;
        r2 = 1;
        r3 = new java.net.URL;	 Catch:{ IOException -> 0x00bd, all -> 0x00b4 }
        r4 = r9.uri;	 Catch:{ IOException -> 0x00bd, all -> 0x00b4 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x00bd, all -> 0x00b4 }
        r3.<init>(r4);	 Catch:{ IOException -> 0x00bd, all -> 0x00b4 }
        r3 = r3.openConnection();	 Catch:{ IOException -> 0x00bd, all -> 0x00b4 }
        r3 = (java.net.HttpURLConnection) r3;	 Catch:{ IOException -> 0x00bd, all -> 0x00b4 }
        r3.setInstanceFollowRedirects(r1);	 Catch:{ IOException -> 0x00b1, all -> 0x00af }
        r4 = r3.getResponseCode();	 Catch:{ IOException -> 0x00b1, all -> 0x00af }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 == r5) goto L_0x0096;
    L_0x001f:
        switch(r4) {
            case 301: goto L_0x0063;
            case 302: goto L_0x0063;
            default: goto L_0x0022;
        };	 Catch:{ IOException -> 0x00b1, all -> 0x00af }
    L_0x0022:
        r4 = r3.getErrorStream();	 Catch:{ IOException -> 0x00b1, all -> 0x00af }
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r5.<init>();	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        if (r4 == 0) goto L_0x0045;
    L_0x002d:
        r10 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r10.<init>(r4);	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r6 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r6 = new char[r6];	 Catch:{ IOException -> 0x0060, all -> 0x005c }
    L_0x0036:
        r7 = r6.length;	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r7 = r10.read(r6, r1, r7);	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        if (r7 <= 0) goto L_0x0041;
    L_0x003d:
        r5.append(r6, r1, r7);	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        goto L_0x0036;
    L_0x0041:
        com.facebook.internal.Utility.closeQuietly(r10);	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        goto L_0x004e;
    L_0x0045:
        r6 = com.facebook.R.string.com_facebook_image_download_unknown_error;	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r10 = r10.getString(r6);	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r5.append(r10);	 Catch:{ IOException -> 0x0060, all -> 0x005c }
    L_0x004e:
        r10 = new com.facebook.FacebookException;	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r5 = r5.toString();	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r10.<init>(r5);	 Catch:{ IOException -> 0x0060, all -> 0x005c }
        r8 = r0;
        r0 = r10;
        r10 = r4;
        r4 = r8;
        goto L_0x009e;
    L_0x005c:
        r9 = move-exception;
        r0 = r4;
        goto L_0x00b6;
    L_0x0060:
        r10 = move-exception;
        goto L_0x00c0;
    L_0x0063:
        r10 = "location";
        r10 = r3.getHeaderField(r10);	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        r2 = com.facebook.internal.Utility.isNullOrEmpty(r10);	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        if (r2 != 0) goto L_0x008e;
    L_0x006f:
        r10 = android.net.Uri.parse(r10);	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        r2 = r9.uri;	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        com.facebook.internal.UrlRedirectCache.cacheUriRedirect(r2, r10);	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        r2 = removePendingRequest(r9);	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        if (r2 == 0) goto L_0x008e;
    L_0x007e:
        r4 = r2.isCancelled;	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        if (r4 != 0) goto L_0x008e;
    L_0x0082:
        r2 = r2.request;	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        r4 = new com.facebook.internal.ImageDownloader$RequestKey;	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        r5 = r9.tag;	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        r4.<init>(r10, r5);	 Catch:{ IOException -> 0x0092, all -> 0x00af }
        enqueueCacheRead(r2, r4, r1);	 Catch:{ IOException -> 0x0092, all -> 0x00af }
    L_0x008e:
        r10 = r0;
        r4 = r10;
        r2 = r1;
        goto L_0x009e;
    L_0x0092:
        r10 = move-exception;
        r4 = r0;
        r2 = r1;
        goto L_0x00c0;
    L_0x0096:
        r10 = com.facebook.internal.ImageResponseCache.interceptAndCacheImageStream(r10, r3);	 Catch:{ IOException -> 0x00b1, all -> 0x00af }
        r4 = android.graphics.BitmapFactory.decodeStream(r10);	 Catch:{ IOException -> 0x00aa, all -> 0x00a7 }
    L_0x009e:
        com.facebook.internal.Utility.closeQuietly(r10);
        com.facebook.internal.Utility.disconnectQuietly(r3);
        r10 = r0;
        r0 = r4;
        goto L_0x00c6;
    L_0x00a7:
        r9 = move-exception;
        r0 = r10;
        goto L_0x00b6;
    L_0x00aa:
        r4 = move-exception;
        r8 = r4;
        r4 = r10;
        r10 = r8;
        goto L_0x00c0;
    L_0x00af:
        r9 = move-exception;
        goto L_0x00b6;
    L_0x00b1:
        r10 = move-exception;
        r4 = r0;
        goto L_0x00c0;
    L_0x00b4:
        r9 = move-exception;
        r3 = r0;
    L_0x00b6:
        com.facebook.internal.Utility.closeQuietly(r0);
        com.facebook.internal.Utility.disconnectQuietly(r3);
        throw r9;
    L_0x00bd:
        r10 = move-exception;
        r3 = r0;
        r4 = r3;
    L_0x00c0:
        com.facebook.internal.Utility.closeQuietly(r4);
        com.facebook.internal.Utility.disconnectQuietly(r3);
    L_0x00c6:
        if (r2 == 0) goto L_0x00cb;
    L_0x00c8:
        issueResponse(r9, r10, r0, r1);
    L_0x00cb:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.download(com.facebook.internal.ImageDownloader$RequestKey, android.content.Context):void");
    }

    private static synchronized Handler getHandler() {
        Handler handler;
        synchronized (ImageDownloader.class) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler = handler;
        }
        return handler;
    }

    private static DownloaderContext removePendingRequest(RequestKey requestKey) {
        DownloaderContext downloaderContext;
        synchronized (pendingRequests) {
            downloaderContext = (DownloaderContext) pendingRequests.remove(requestKey);
        }
        return downloaderContext;
    }
}
