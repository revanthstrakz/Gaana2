package com.til.colombia.android.commons;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Display;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.am;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public final class CommonUtil {
    public static float a = 1.7777778f;
    private static final double b = 70.0d;
    private static final double c = 30.0d;
    private static final double d = 20.0d;
    private static double e = 0.0d;
    private static int f = 0;
    private static final String g = "com.android.chrome";
    private static final String h = "com.UCMobile.intl";
    private static final String i = "com.uc.browser.en";
    private static final String j = "org.mozilla.firefox";

    public enum AudioMode {
        MUTE,
        UNMUTE
    }

    public enum AutoPlay {
        OFF,
        ON
    }

    public enum InlineVideoVisiblity {
        NONE,
        VISIBLE,
        OUT_OF_VIEW
    }

    public enum MediaSource {
        NONE,
        VAST_URL,
        VPAID_URL,
        SCRIPT,
        VAST_XML
    }

    public enum VideoPauseMode {
        NONE,
        AUTO_PAUSE,
        USER_PAUSE,
        BUFFERING
    }

    public static boolean c(int i, int i2, int i3, int i4) {
        return (((double) i) / ((double) i2)) / (((double) i3) / ((double) i4)) == 1.0d;
    }

    /* JADX WARNING: Missing block: B:23:0x0085, code skipped:
            if (r0.width > r1) goto L_0x0094;
     */
    public static void a(android.content.Context r4, android.widget.RelativeLayout r5) {
        /*
        r0 = r5.getLayoutParams();
        r1 = r0.width;
        if (r1 <= 0) goto L_0x000d;
    L_0x0008:
        r1 = r0.height;
        if (r1 <= 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r1 = r5.getWidth();
        if (r1 <= 0) goto L_0x0029;
    L_0x0013:
        r1 = r5.getHeight();
        if (r1 <= 0) goto L_0x0029;
    L_0x0019:
        r4 = r5.getWidth();
        r0.width = r4;
        r4 = r5.getHeight();
        r0.height = r4;
        r5.setLayoutParams(r0);
        return;
    L_0x0029:
        r4 = (android.app.Activity) r4;
        r1 = r4.getWindowManager();
        r1 = r1.getDefaultDisplay();
        r1 = r1.getWidth();
        r4 = r4.getWindowManager();
        r4 = r4.getDefaultDisplay();
        r4 = r4.getHeight();
        r2 = r5.getWidth();
        if (r2 <= 0) goto L_0x006a;
    L_0x0049:
        r2 = r5.getWidth();
        r0.width = r2;
        r2 = r5.getWidth();
        r2 = (float) r2;
        r3 = a;
        r2 = r2 / r3;
        r2 = (int) r2;
        r0.height = r2;
        if (r1 <= r4) goto L_0x009d;
    L_0x005c:
        r1 = r0.height;
        if (r1 <= r4) goto L_0x009d;
    L_0x0060:
        r1 = (float) r4;
        r2 = a;
        r1 = r1 * r2;
        r1 = (int) r1;
        r0.width = r1;
        r0.height = r4;
        goto L_0x009d;
    L_0x006a:
        r2 = r5.getHeight();
        if (r2 <= 0) goto L_0x0088;
    L_0x0070:
        r2 = r5.getHeight();
        r2 = (float) r2;
        r3 = a;
        r2 = r2 * r3;
        r2 = (int) r2;
        r0.width = r2;
        r2 = r5.getHeight();
        r0.height = r2;
        if (r4 <= r1) goto L_0x009d;
    L_0x0083:
        r4 = r0.width;
        if (r4 <= r1) goto L_0x009d;
    L_0x0087:
        goto L_0x0094;
    L_0x0088:
        if (r1 <= r4) goto L_0x0094;
    L_0x008a:
        r1 = (float) r4;
        r2 = a;
        r1 = r1 * r2;
        r1 = (int) r1;
        r0.width = r1;
        r0.height = r4;
        goto L_0x009d;
    L_0x0094:
        r0.width = r1;
        r4 = (float) r1;
        r1 = a;
        r4 = r4 / r1;
        r4 = (int) r4;
        r0.height = r4;
    L_0x009d:
        r5.setLayoutParams(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.CommonUtil.a(android.content.Context, android.widget.RelativeLayout):void");
    }

    public static void a(FrameLayout frameLayout, TextureView textureView, am amVar, ImageView imageView) {
        int width = frameLayout.getWidth();
        int height = frameLayout.getHeight();
        float videoWidth = (float) amVar.getVideoWidth();
        float videoHeight = (float) amVar.getVideoHeight();
        float f = (float) width;
        float f2 = f / videoWidth;
        float f3 = (float) height;
        float f4 = f3 / videoHeight;
        videoWidth /= videoHeight;
        LayoutParams layoutParams = textureView.getLayoutParams();
        if (f2 > f4) {
            layoutParams.width = (int) (f3 * videoWidth);
            layoutParams.height = height;
        } else {
            layoutParams.width = width;
            layoutParams.height = (int) (f / videoWidth);
        }
        textureView.setLayoutParams(layoutParams);
        if (imageView != null) {
            imageView.setLayoutParams(layoutParams);
        }
    }

    private static void a(Context context, SurfaceView surfaceView, am amVar, ImageView imageView) {
        Activity activity = (Activity) context;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        float videoWidth = (float) amVar.getVideoWidth();
        float videoHeight = (float) amVar.getVideoHeight();
        float f = (float) width;
        float f2 = f / videoWidth;
        float f3 = (float) height;
        float f4 = f3 / videoHeight;
        videoWidth /= videoHeight;
        LayoutParams layoutParams = surfaceView.getLayoutParams();
        if (f2 > f4) {
            layoutParams.width = (int) (f3 * videoWidth);
            layoutParams.height = height;
        } else {
            layoutParams.width = width;
            layoutParams.height = (int) (f / videoWidth);
        }
        surfaceView.setLayoutParams(layoutParams);
        if (imageView != null) {
            imageView.setLayoutParams(layoutParams);
        }
    }

    private static void a(Context context, TextureView textureView, am amVar, ImageView imageView) {
        Activity activity = (Activity) context;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        float videoWidth = (float) amVar.getVideoWidth();
        float videoHeight = (float) amVar.getVideoHeight();
        float f = (float) width;
        float f2 = f / videoWidth;
        float f3 = (float) height;
        float f4 = f3 / videoHeight;
        videoWidth /= videoHeight;
        LayoutParams layoutParams = textureView.getLayoutParams();
        if (f2 > f4) {
            layoutParams.width = (int) (f3 * videoWidth);
            layoutParams.height = height;
        } else {
            layoutParams.width = width;
            layoutParams.height = (int) (f / videoWidth);
        }
        textureView.setLayoutParams(layoutParams);
        if (imageView != null) {
            imageView.setLayoutParams(layoutParams);
        }
    }

    public static void a(View view) {
        if (view != null) {
            FrameLayout.LayoutParams layoutParams;
            if (a.a().getResources().getConfiguration().orientation == 1) {
                layoutParams = new FrameLayout.LayoutParams(-1, -2);
            } else {
                layoutParams = new FrameLayout.LayoutParams(-2, -1);
            }
            layoutParams.gravity = 17;
            view.setLayoutParams(layoutParams);
        }
    }

    public static int a() {
        return ((WindowManager) a.a().getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int b() {
        return ((WindowManager) a.a().getSystemService("window")).getDefaultDisplay().getHeight();
    }

    private static boolean b(String str) {
        if (h.a(str)) {
            return false;
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static boolean a(Bitmap bitmap, ImageView imageView) {
        if (bitmap == null) {
            imageView.setBackgroundResource(17170432);
            return false;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        float width2 = (float) bitmap.getWidth();
        float height2 = (float) bitmap.getHeight();
        float f = (float) width;
        float f2 = f / width2;
        float f3 = (float) height;
        float f4 = f3 / height2;
        width2 /= height2;
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (f2 > f4) {
            layoutParams.width = (int) (f3 * width2);
            layoutParams.height = height;
        } else {
            layoutParams.width = width;
            layoutParams.height = (int) (f / width2);
        }
        imageView.setLayoutParams(layoutParams);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, layoutParams.width, layoutParams.height, false));
        return true;
    }

    private static float c(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private static float c(float f, Context context) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int b(float f, Context context) {
        return Math.round(f * ((float) (context.getResources().getDisplayMetrics().densityDpi / MoEHelperUtils.BASELINE_SCREEN_DPI)));
    }

    private static int a(int i, Context context) {
        return Math.round((float) (i / (context.getResources().getDisplayMetrics().densityDpi / MoEHelperUtils.BASELINE_SCREEN_DPI)));
    }

    private static Bitmap c(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (IOException e) {
            Log.a(i.f, "", e);
            return null;
        }
    }

    @TargetApi(14)
    public static Bitmap a(String str) {
        if (VERSION.SDK_INT >= 14) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(str, new HashMap());
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException e) {
                    Log.a(i.f, "", e);
                }
                return frameAtTime;
            } catch (IllegalArgumentException e2) {
                Log.a(i.f, "", e2);
                mediaMetadataRetriever.release();
            } catch (Exception e3) {
                Log.a(i.f, "", e3);
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException e4) {
                    Log.a(i.f, "", e4);
                }
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException e5) {
                    Log.a(i.f, "", e5);
                }
                throw th;
            }
        }
        return null;
    }

    public static boolean a(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("status", -1);
        boolean z = intExtra == 2 || intExtra == 5;
        int intExtra2 = registerReceiver.getIntExtra("level", -1);
        double intExtra3 = (double) registerReceiver.getIntExtra("scale", -1);
        double d = -1.0d;
        if (intExtra2 >= 0 && intExtra3 > 0.0d) {
            d = ((double) (intExtra2 * 100)) / intExtra3;
        }
        return d <= d && !z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0030 A:{SYNTHETIC, Splitter:B:19:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0043 A:{SYNTHETIC, Splitter:B:26:0x0043} */
    public static java.lang.String a(java.io.InputStream r4) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = 0;
        r2 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0026 }
        r3 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0026 }
        r3.<init>(r4);	 Catch:{ IOException -> 0x0026 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x0026 }
    L_0x0010:
        r4 = r2.readLine();	 Catch:{ IOException -> 0x0021, all -> 0x001e }
        if (r4 == 0) goto L_0x001a;
    L_0x0016:
        r0.append(r4);	 Catch:{ IOException -> 0x0021, all -> 0x001e }
        goto L_0x0010;
    L_0x001a:
        r2.close();	 Catch:{ IOException -> 0x0034 }
        goto L_0x003c;
    L_0x001e:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0041;
    L_0x0021:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0027;
    L_0x0024:
        r4 = move-exception;
        goto L_0x0041;
    L_0x0026:
        r4 = move-exception;
    L_0x0027:
        r2 = "Col:aos:4.0.0";
        r3 = "";
        com.til.colombia.android.internal.Log.a(r2, r3, r4);	 Catch:{ all -> 0x0024 }
        if (r1 == 0) goto L_0x003c;
    L_0x0030:
        r1.close();	 Catch:{ IOException -> 0x0034 }
        goto L_0x003c;
    L_0x0034:
        r4 = move-exception;
        r1 = "Col:aos:4.0.0";
        r2 = "";
        com.til.colombia.android.internal.Log.a(r1, r2, r4);
    L_0x003c:
        r4 = r0.toString();
        return r4;
    L_0x0041:
        if (r1 == 0) goto L_0x004f;
    L_0x0043:
        r1.close();	 Catch:{ IOException -> 0x0047 }
        goto L_0x004f;
    L_0x0047:
        r0 = move-exception;
        r1 = "Col:aos:4.0.0";
        r2 = "";
        com.til.colombia.android.internal.Log.a(r1, r2, r0);
    L_0x004f:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.CommonUtil.a(java.io.InputStream):java.lang.String");
    }

    private static byte[] d(String str) {
        return Base64.decode(str, 0);
    }

    private static String a(byte[] bArr) {
        return Base64.encodeToString(bArr, 0);
    }

    public static Point a(int i, int i2) {
        Display defaultDisplay = ((WindowManager) a.a().getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        return a(i, i2, Math.max(width, height), Math.min(width, height));
    }

    public static Point a(int i, int i2, int i3, int i4) {
        Point point = new Point(i, i2);
        i = b((float) i, a.a());
        i2 = b((float) i2, a.a());
        if (i <= i3 && i2 <= i4) {
            return point;
        }
        float f = (float) i;
        float f2 = f / ((float) i3);
        float f3 = (float) i2;
        float f4 = f3 / ((float) i4);
        Point point2 = new Point();
        if (f2 >= f4) {
            point2.x = i3 - 16;
            point2.y = ((int) (f3 / f2)) - 16;
        } else {
            point2.x = ((int) (f / f4)) - 16;
            point2.y = i4 - 16;
        }
        if (point2.x < 0 || point2.y < 0) {
            return point;
        }
        point2.x = a(point2.x, a.a());
        point2.y = a(point2.y, a.a());
        return point2;
    }

    public static double b(int i, int i2, int i3, int i4) {
        double d = ((double) i) / ((double) i2);
        i *= i2;
        float f = a.a().getResources().getDisplayMetrics().density;
        if (f <= 0.0f) {
            f = 1.0f;
        }
        return (b * Math.abs(Math.log(d / (((double) i3) / ((double) i4))))) + (c * Math.abs(Math.log(((double) i) / ((double) ((int) ((((float) i3) / f) * (((float) i4) / f)))))));
    }

    private static int d() {
        return f;
    }

    private static double e() {
        return e;
    }

    private static void a(double d) {
        e = d;
    }

    private static void b(int i) {
        f = i;
    }

    public static void c() {
        float f = a.a().getResources().getDisplayMetrics().density;
        if (f <= 0.0f) {
            f = 1.0f;
        }
        int a = a();
        int b = b();
        e = ((double) a) / ((double) b);
        f = (int) ((((float) a) / f) * (((float) b) / f));
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0038 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0020 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0050 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|(2:6|7)|8|9|(2:13|14)|15|16|(2:20|21)|22|23|(2:27|28)|29|31) */
    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|(2:6|7)|8|9|(2:13|14)|15|16|(2:20|21)|22|23|(2:27|28)|29|31) */
    /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|(2:6|7)|8|9|(2:13|14)|15|16|(2:20|21)|22|23|(2:27|28)|29|31) */
    public static java.lang.String b(android.content.Context r4) {
        /*
        r4 = r4.getPackageManager();
        r0 = 3;
        r1 = 2;
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r3 = "com.android.chrome";
        r4.getPackageInfo(r3, r2);	 Catch:{ NameNotFoundException -> 0x0020 }
        r3 = "com.android.chrome";
        r3 = r4.getApplicationEnabledSetting(r3);	 Catch:{ NameNotFoundException -> 0x0020 }
        if (r3 == r1) goto L_0x0020;
    L_0x0015:
        r3 = "com.android.chrome";
        r3 = r4.getApplicationEnabledSetting(r3);	 Catch:{ NameNotFoundException -> 0x0020 }
        if (r3 == r0) goto L_0x0020;
    L_0x001d:
        r3 = "com.android.chrome";
        return r3;
    L_0x0020:
        r3 = "com.UCMobile.intl";
        r4.getPackageInfo(r3, r2);	 Catch:{ NameNotFoundException -> 0x0038 }
        r3 = "com.UCMobile.intl";
        r3 = r4.getApplicationEnabledSetting(r3);	 Catch:{ NameNotFoundException -> 0x0038 }
        if (r3 == r1) goto L_0x0038;
    L_0x002d:
        r3 = "com.UCMobile.intl";
        r3 = r4.getApplicationEnabledSetting(r3);	 Catch:{ NameNotFoundException -> 0x0038 }
        if (r3 == r0) goto L_0x0038;
    L_0x0035:
        r3 = "com.UCMobile.intl";
        return r3;
    L_0x0038:
        r3 = "com.uc.browser.en";
        r4.getPackageInfo(r3, r2);	 Catch:{ NameNotFoundException -> 0x0050 }
        r3 = "com.uc.browser.en";
        r3 = r4.getApplicationEnabledSetting(r3);	 Catch:{ NameNotFoundException -> 0x0050 }
        if (r3 == r1) goto L_0x0050;
    L_0x0045:
        r3 = "com.uc.browser.en";
        r3 = r4.getApplicationEnabledSetting(r3);	 Catch:{ NameNotFoundException -> 0x0050 }
        if (r3 == r0) goto L_0x0050;
    L_0x004d:
        r3 = "com.uc.browser.en";
        return r3;
    L_0x0050:
        r3 = "org.mozilla.firefox";
        r4.getPackageInfo(r3, r2);	 Catch:{ NameNotFoundException -> 0x0068 }
        r2 = "org.mozilla.firefox";
        r2 = r4.getApplicationEnabledSetting(r2);	 Catch:{ NameNotFoundException -> 0x0068 }
        if (r2 == r1) goto L_0x0068;
    L_0x005d:
        r1 = "org.mozilla.firefox";
        r4 = r4.getApplicationEnabledSetting(r1);	 Catch:{ NameNotFoundException -> 0x0068 }
        if (r4 == r0) goto L_0x0068;
    L_0x0065:
        r4 = "org.mozilla.firefox";
        return r4;
    L_0x0068:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.CommonUtil.b(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0037 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0050 A:{SYNTHETIC, Splitter:B:27:0x0050} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            com.til.colombia.android.internal.Log.a(com.til.colombia.android.internal.i.f, "Error reading InputStream");
     */
    /* JADX WARNING: Missing block: B:19:0x003e, code skipped:
            if (r2 != null) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:22:0x0044, code skipped:
            com.til.colombia.android.internal.Log.a(com.til.colombia.android.internal.i.f, "Error closing InputStream");
     */
    /* JADX WARNING: Missing block: B:25:0x004d, code skipped:
            r4 = th;
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            return null;
     */
    public static java.lang.String a(java.net.HttpURLConnection r4) {
        /*
        r0 = new java.lang.StringBuffer;
        r0.<init>();
        r1 = 0;
        r2 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r4 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0037 }
        r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0037 }
        r3.<init>(r2);	 Catch:{ Exception -> 0x0037 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0037 }
    L_0x0019:
        r3 = r4.readLine();	 Catch:{ Exception -> 0x0037 }
        if (r3 == 0) goto L_0x0023;
    L_0x001f:
        r0.append(r3);	 Catch:{ Exception -> 0x0037 }
        goto L_0x0019;
    L_0x0023:
        r4 = r0.toString();	 Catch:{ Exception -> 0x0037 }
        r2.close();	 Catch:{ IOException -> 0x002b }
        goto L_0x004c;
    L_0x002b:
        r0 = "Col:aos:4.0.0";
        r1 = "Error closing InputStream";
        com.til.colombia.android.internal.Log.a(r0, r1);
        goto L_0x004c;
    L_0x0033:
        r4 = move-exception;
        r2 = r1;
        goto L_0x004e;
    L_0x0036:
        r2 = r1;
    L_0x0037:
        r4 = "Col:aos:4.0.0";
        r0 = "Error reading InputStream";
        com.til.colombia.android.internal.Log.a(r4, r0);	 Catch:{ all -> 0x004d }
        if (r2 == 0) goto L_0x004b;
    L_0x0040:
        r2.close();	 Catch:{ IOException -> 0x0044 }
        goto L_0x004b;
    L_0x0044:
        r4 = "Col:aos:4.0.0";
        r0 = "Error closing InputStream";
        com.til.colombia.android.internal.Log.a(r4, r0);
    L_0x004b:
        r4 = r1;
    L_0x004c:
        return r4;
    L_0x004d:
        r4 = move-exception;
    L_0x004e:
        if (r2 == 0) goto L_0x005b;
    L_0x0050:
        r2.close();	 Catch:{ IOException -> 0x0054 }
        goto L_0x005b;
    L_0x0054:
        r0 = "Col:aos:4.0.0";
        r1 = "Error closing InputStream";
        com.til.colombia.android.internal.Log.a(r0, r1);
    L_0x005b:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.CommonUtil.a(java.net.HttpURLConnection):java.lang.String");
    }

    public static String a(int i) {
        i %= 60;
        return String.format("%02d:%02d", new Object[]{Integer.valueOf((i % 3600) / 60), Integer.valueOf(i)});
    }

    public static int a(float f, Context context) {
        return (int) (TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    public static double b(int i, int i2) {
        return (b * Math.abs(Math.log((((double) i) / ((double) i2)) / e))) + (c * Math.abs(Math.log(((double) (i * i2)) / ((double) f))));
    }
}
