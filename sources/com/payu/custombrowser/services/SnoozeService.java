package com.payu.custombrowser.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.payu.custombrowser.Bank;
import com.payu.custombrowser.CBActivity;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.d.g;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

public class SnoozeService extends Service {
    private static int b;
    private String A = "";
    private String B = "";
    private String C = "";
    private String D;
    private boolean E;
    private boolean F = true;
    private boolean G;
    private boolean H;
    private boolean I;
    private long J;
    private boolean K;
    private String L = "https://info.payu.in/merchant/postservice?form=2";
    private CustomBrowserConfig M;
    private String N;
    private CBUtil O;
    private String P;
    private HashMap<String, String> Q;
    private String R = null;
    private String S = null;
    private Runnable T = new Runnable() {
        public void run() {
            try {
                String c;
                String f;
                String g;
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(SnoozeService.this.L).openConnection();
                SnoozeService.this.O;
                String str = null;
                if (TextUtils.isEmpty(CBUtil.getCookie(CBConstant.PAYUID, SnoozeService.this.getApplicationContext()))) {
                    c = !TextUtils.isEmpty(SnoozeService.this.R) ? SnoozeService.this.R : null;
                } else {
                    SnoozeService.this.O;
                    c = CBUtil.getCookie(CBConstant.PAYUID, SnoozeService.this.getApplicationContext());
                }
                SnoozeService.this.O;
                if (!TextUtils.isEmpty(CBUtil.getCookie(CBConstant.PHPSESSID, SnoozeService.this.getApplicationContext()))) {
                    SnoozeService.this.O;
                    str = CBUtil.getCookie(CBConstant.PHPSESSID, SnoozeService.this.getApplicationContext());
                } else if (!TextUtils.isEmpty(SnoozeService.this.S)) {
                    str = SnoozeService.this.S;
                } else if (TextUtils.isEmpty(SnoozeService.this.S)) {
                    str = "123456";
                }
                if (TextUtils.isEmpty(SnoozeService.this.x)) {
                    f = SnoozeService.this.B;
                    g = SnoozeService.this.C;
                } else {
                    f = SnoozeService.this.O.getDataFromPostData(SnoozeService.this.x, CBConstant.KEY);
                    g = SnoozeService.this.O.getDataFromPostData(SnoozeService.this.x, CBConstant.TXN_ID);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("command=verifyTxnStatus&var1=");
                stringBuilder.append(g);
                stringBuilder.append("&key=");
                stringBuilder.append(f);
                stringBuilder.append("&priorityParam=");
                stringBuilder.append(SnoozeService.this.P);
                f = stringBuilder.toString();
                httpsURLConnection.setRequestMethod(HttpMethods.POST);
                httpsURLConnection.setConnectTimeout(CBConstant.VERIFY_HTTP_TIMEOUT);
                httpsURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
                httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(f.length()));
                stringBuilder = new StringBuilder();
                stringBuilder.append("PHPSESSID=");
                stringBuilder.append(str);
                stringBuilder.append("; PAYUID=");
                stringBuilder.append(c);
                httpsURLConnection.setRequestProperty("Cookie", stringBuilder.toString());
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.getOutputStream().write(f.getBytes());
                byte[] bArr = new byte[1024];
                if (httpsURLConnection.getResponseCode() != 200) {
                    SnoozeService.this.b("{\"api_status\":\"0\",\"message\":\"Some error occurred\"}");
                } else if (httpsURLConnection.getInputStream() != null) {
                    StringBuffer stringBufferFromInputStream = CBUtil.getStringBufferFromInputStream(httpsURLConnection.getInputStream());
                    if (stringBufferFromInputStream != null) {
                        JSONObject jSONObject = new JSONObject(stringBufferFromInputStream.toString());
                        SnoozeService.this.N = stringBufferFromInputStream.toString();
                        SnoozeService.this.b(stringBufferFromInputStream.toString());
                    }
                }
            } catch (Exception e) {
                SnoozeService.this.b("{\"api_status\":\"0\",\"message\":\"Some exception occurred\"}");
                ThrowableExtension.printStackTrace(e);
            }
        }
    };
    String a = CBConstant.MERCHANT_CHECKOUT_ACTIVITY;
    private int c = 1800000;
    private final int d = 500;
    private final String e = "webview_status_action";
    private final String f = "snooze_broad_cast_message";
    private final String g = CBConstant.CURRENT_URL;
    private final String h = CBConstant.S2S_RETRY_URL;
    private final IBinder i = new b();
    private Handler j;
    private Runnable k;
    private Handler l;
    private HandlerThread m;
    private CountDownTimer n;
    private Looper o;
    private a p;
    private long q;
    private long r;
    private int s = 1000;
    private int t = 60000;
    private long u;
    private String v = "";
    private String w = "";
    private String x = "";
    private String y = "";
    private String z = "";

    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            SnoozeService.this.F = true;
            SnoozeService.this.n = new CountDownTimer((long) SnoozeService.this.c, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
                public void onTick(long j) {
                    SnoozeService.this.J = (((long) SnoozeService.this.c) - j) / 1000;
                }

                public void onFinish() {
                    if (!SnoozeService.this.H && CBActivity.b == 2) {
                        SnoozeService.this.e();
                        SnoozeService.this.a("internet_not_restored_notification", "-1");
                    } else if (!SnoozeService.this.H && CBActivity.b == 1) {
                        SnoozeService.this.a("internet_not_restored_dialog_foreground", "-1");
                    }
                    if (SnoozeService.this.G && !SnoozeService.this.H) {
                        Intent intent = new Intent("webview_status_action");
                        intent.putExtra(CBConstant.SNOOZE_SERVICE_STATUS, CBConstant.SNOOZE_SERVICE_DEAD);
                        LocalBroadcastManager.getInstance(SnoozeService.this).sendBroadcast(intent);
                    }
                    SnoozeService.this.a();
                }
            };
            SnoozeService.this.n.start();
            SnoozeService.this.l = new Handler();
            SnoozeService.this.l.postDelayed(new Runnable() {
                public void run() {
                    if (SnoozeService.this.F) {
                        if (SnoozeService.this.v.contentEquals(SnoozeService.this.w)) {
                            SnoozeService.this.G = true;
                        } else {
                            SnoozeService.this.G = false;
                            if (SnoozeService.this.F && SnoozeService.this.K && SnoozeService.this.H && SnoozeService.this.I) {
                                SnoozeService.this.c(SnoozeService.this.N);
                            } else if (SnoozeService.this.F && SnoozeService.this.H && SnoozeService.this.I) {
                                SnoozeService.this.a(SnoozeService.this.G);
                            }
                        }
                        SnoozeService.this.l.postDelayed(this, 500);
                        Intent intent = new Intent("webview_status_action");
                        SnoozeService snoozeService = SnoozeService.this;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("");
                        stringBuilder.append(System.currentTimeMillis());
                        snoozeService.v = stringBuilder.toString();
                        intent.putExtra("snooze_broad_cast_message", SnoozeService.this.v);
                        LocalBroadcastManager.getInstance(SnoozeService.this).sendBroadcast(intent);
                    }
                }
            }, 500);
            SnoozeService.this.c();
        }
    }

    public class b extends Binder {
        public SnoozeService a() {
            return SnoozeService.this;
        }
    }

    private void b(String str) {
        try {
            String valueOfJSONKey = this.O.getValueOfJSONKey(str, getString(g.cb_snooze_verify_api_status));
            if (CBActivity.b == 2) {
                if (valueOfJSONKey.contentEquals("1")) {
                    a("transaction_verified_notification", "-1");
                } else {
                    a("transaction_not_verified_notification", "-1");
                }
                c(str);
                return;
            }
            if (valueOfJSONKey.contentEquals("1")) {
                a("transaction_verified_dialog_foreground", "-1");
            } else {
                a("transaction_not_verified_dialog_foreground", "-1");
            }
            a(CBConstant.BACKWARD_JOURNEY_STATUS, str, false);
            a();
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            if (CBActivity.b == 2) {
                a("transaction_not_verified_notification", "-1");
                c(str);
                return;
            }
            a("transaction_not_verified_dialog_foreground", "-1");
            a(CBConstant.BACKWARD_JOURNEY_STATUS, str, false);
            a();
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        this.G = true;
        return this.i;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.O = new CBUtil();
        this.D = intent.getStringExtra(this.a);
        this.M = (CustomBrowserConfig) intent.getParcelableExtra(CBConstant.CB_CONFIG);
        this.c = this.M.getSurePayBackgroundTTL();
        this.Q = this.O.getDataFromPostData(this.M.getPayuPostData());
        b = Bank.snoozeImageDownloadTimeout > 0 ? Bank.snoozeImageDownloadTimeout : 10000;
        if (intent.getExtras().containsKey(CBConstant.VERIFICATION_MSG_RECEIVED) && intent.getExtras().getBoolean(CBConstant.VERIFICATION_MSG_RECEIVED)) {
            this.K = true;
            if (intent.getExtras().containsKey(CBConstant.VERIFY_ADDON_PARAMS)) {
                this.P = intent.getExtras().getString(CBConstant.VERIFY_ADDON_PARAMS);
            }
            this.x = this.M.getPayuPostData();
            this.y = this.M.getPostURL();
            this.B = intent.getStringExtra(CBConstant.MERCHANTKEY);
            this.C = intent.getStringExtra(CBConstant.TXN_ID);
            this.R = intent.getStringExtra(CBConstant.PAYUID);
        } else {
            this.K = false;
            this.z = intent.getStringExtra(CBConstant.CURRENT_URL);
            this.A = intent.getStringExtra(CBConstant.S2S_RETRY_URL);
        }
        Message obtainMessage = this.p.obtainMessage();
        obtainMessage.arg1 = i2;
        this.p.sendMessage(obtainMessage);
        return Bank.hasToStart ? 3 : 2;
    }

    public void onCreate() {
        this.m = new HandlerThread("SnoozeServiceHandlerThread", 10);
        this.m.start();
        this.o = this.m.getLooper();
        this.p = new a(this.o);
    }

    public void a() {
        this.F = false;
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
        this.m.interrupt();
        stopSelf();
    }

    public void a(String str) {
        this.w = str;
    }

    private void c() {
        this.j = new Handler(this.o);
        this.k = new Runnable() {
            public void run() {
                if (SnoozeService.this.F) {
                    SnoozeService.this.d();
                }
            }
        };
        this.j.postDelayed(this.k, (long) Math.min(this.s, this.t));
    }

    private void d() {
        this.E = false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CBConstant.SNOOZE_IMAGE_DOWNLOAD_END_POINT);
        stringBuilder.append(CBConstant.SNOOZE_IMAGE_COLLECTIONS[new Random().nextInt(2)]);
        final String stringBuilder2 = stringBuilder.toString();
        final AnonymousClass3 anonymousClass3 = new CountDownTimer((long) b, 1000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                cancel();
                SnoozeService.this.E = true;
            }
        };
        anonymousClass3.start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    SnoozeService.this.O;
                    if (CBUtil.isNetworkAvailable(SnoozeService.this.getApplicationContext())) {
                        SnoozeService.this.q = System.currentTimeMillis();
                        URLConnection openConnection = new URL(stringBuilder2).openConnection();
                        openConnection.setUseCaches(false);
                        openConnection.connect();
                        openConnection.getContentLength();
                        InputStream inputStream = openConnection.getInputStream();
                        byte[] bArr = new byte[1024];
                        while (!SnoozeService.this.E && inputStream.read(bArr) != -1) {
                        }
                        if (SnoozeService.this.E) {
                            anonymousClass3.cancel();
                            inputStream.close();
                            SnoozeService.this.u = (long) (SnoozeService.b + 1);
                        } else {
                            anonymousClass3.cancel();
                            SnoozeService.this.r = System.currentTimeMillis();
                            inputStream.close();
                            SnoozeService.this.u = SnoozeService.this.r - SnoozeService.this.q;
                        }
                        if (SnoozeService.this.u > ((long) SnoozeService.b)) {
                            SnoozeService.this.s = SnoozeService.this.s + SnoozeService.this.s;
                            SnoozeService.this.j.postDelayed(SnoozeService.this.k, (long) Math.min(SnoozeService.this.s, SnoozeService.this.t));
                            return;
                        } else if (!SnoozeService.this.F) {
                            return;
                        } else {
                            if (SnoozeService.this.K) {
                                SnoozeService.this.a("snooze_verify_api_status", "snooze_verify_api_called");
                                new Thread(SnoozeService.this.T).start();
                                return;
                            } else if (CBActivity.b == 1) {
                                SnoozeService.this.a(SnoozeService.this.getString(g.internet_restored), SnoozeService.this.getString(g.resuming_your_transaction), true);
                                SnoozeService.this.a("internet_restored_dialog_foreground", "-1");
                                SnoozeService.this.a();
                                return;
                            } else {
                                SnoozeService.this.a(SnoozeService.this.G);
                                SnoozeService.this.a("internet_restored_notification", "-1");
                                return;
                            }
                        }
                    }
                    SnoozeService.this.j.postDelayed(SnoozeService.this.k, (long) Math.min(SnoozeService.this.s, SnoozeService.this.t));
                } catch (MalformedURLException e) {
                    SnoozeService.this.u = -1;
                    anonymousClass3.cancel();
                    ThrowableExtension.printStackTrace(e);
                } catch (SSLException e2) {
                    SnoozeService.this.j.postDelayed(SnoozeService.this.k, (long) Math.min(SnoozeService.this.s, SnoozeService.this.t));
                    ThrowableExtension.printStackTrace(e2);
                } catch (IOException e3) {
                    SnoozeService.this.u = -1;
                    anonymousClass3.cancel();
                    ThrowableExtension.printStackTrace(e3);
                } catch (Exception unused) {
                    SnoozeService.this.u = -1;
                    anonymousClass3.cancel();
                }
            }
        }).start();
    }

    private void a(boolean z) {
        Builder builder = new Builder(this);
        Builder defaults = builder.setContentTitle(this.M.getSurePayNotificationGoodNetworkTitle()).setContentText(this.M.getSurePayNotificationGoodNetWorkHeader()).setSmallIcon(this.M.getSurePayNotificationIcon()).setAutoCancel(true).setPriority(1).setDefaults(2);
        BigTextStyle bigTextStyle = new BigTextStyle();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.M.getSurePayNotificationGoodNetWorkHeader());
        stringBuilder.append("\n\n");
        stringBuilder.append(this.M.getSurePayNotificationGoodNetWorkBody());
        defaults.setStyle(bigTextStyle.bigText(stringBuilder.toString()));
        if (VERSION.SDK_INT >= 23) {
            builder.setColor(getResources().getColor(com.payu.custombrowser.d.b.cb_blue_button, null));
        } else {
            builder.setColor(getResources().getColor(com.payu.custombrowser.d.b.cb_blue_button));
        }
        this.H = true;
        Intent intent = new Intent();
        intent.putExtra(CBConstant.CURRENT_URL, this.z);
        intent.putExtra(CBConstant.S2S_RETRY_URL, this.A);
        intent.putExtra(CBConstant.SENDER, CBConstant.SNOOZE_SERVICE);
        if (z) {
            this.I = true;
            intent.setFlags(C.ENCODING_PCM_A_LAW);
            intent.putExtra(CBConstant.CURRENT_URL, this.z);
            intent.putExtra(CBConstant.CB_CONFIG, this.M);
            intent.setClass(getApplicationContext(), CBActivity.class);
            z = true;
        } else {
            Intent intent2 = new Intent();
            intent2.setClassName(getApplicationContext(), this.D == null ? "" : this.D);
            if (intent2.resolveActivityInfo(getPackageManager(), 0) != null) {
                intent.setClassName(getApplicationContext(), this.D);
                intent.putExtra(CBConstant.POST_TYPE, "sure_pay_payment_data");
                intent.putExtra(CBConstant.POST_DATA, this.M.getPayuPostData());
                z = true;
            } else {
                z = false;
            }
            a("snooze_notification_expected_action", "merchant_checkout_page");
            this.I = false;
            a();
        }
        if (z) {
            builder.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, intent, 134217728));
            ((NotificationManager) getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).notify(CBConstant.SNOOZE_NOTIFICATION_ID, builder.build());
            a(CBConstant.GOOD_NETWORK_NOTIFICATION_LAUNCHED, "true", true);
            return;
        }
        try {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("The Activity ");
            stringBuilder2.append(this.D);
            stringBuilder2.append(" is not found, Please set valid activity ");
            throw new ActivityNotFoundException(stringBuilder2.toString());
        } catch (ActivityNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void a(String str, String str2) {
        Intent intent = new Intent("webview_status_action");
        intent.putExtra("BROAD_CAST_FROM_SNOOZE_SERVICE", true);
        intent.putExtra("event_key", str);
        intent.putExtra("event_value", str2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void c(String str) {
        try {
            String valueOfJSONKey = this.O.getValueOfJSONKey(str, getString(g.cb_snooze_verify_api_status));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(valueOfJSONKey);
            stringBuilder.append("");
            a("snooze_verify_api_response_received", stringBuilder.toString());
            Builder builder = new Builder(this);
            if (valueOfJSONKey.contentEquals("1")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.M.getSurePayNotificationTransactionVerifiedHeader());
                stringBuilder.append("\n\n");
                stringBuilder.append(this.M.getSurePayNotificationTransactionVerifiedBody());
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.M.getSurePayNotificationTransactionNotVerifiedHeader());
                stringBuilder.append("\n\n");
                stringBuilder.append(this.M.getSurePayNotificationTransactionNotVerifiedBody());
            }
            boolean z = true;
            builder.setContentTitle(valueOfJSONKey.contentEquals("1") ? this.M.getSurePayNotificationTransactionVerifiedTitle() : this.M.getSurePayNotificationTransactionNotVerifiedTitle()).setContentText(valueOfJSONKey.contentEquals("1") ? this.M.getSurePayNotificationTransactionVerifiedHeader() : this.M.getSurePayNotificationTransactionNotVerifiedHeader()).setSmallIcon(this.M.getSurePayNotificationIcon()).setAutoCancel(true).setPriority(1).setDefaults(2).setStyle(new BigTextStyle().bigText(stringBuilder.toString()));
            Intent intent = new Intent();
            intent.putExtra(CBConstant.CB_CONFIG, this.M);
            this.H = true;
            intent.putExtra(CBConstant.PAYU_RESPONSE, str);
            if (this.G) {
                intent.setFlags(805306368);
                this.I = true;
                intent.putExtra(CBConstant.SENDER, CBConstant.SNOOZE_SERVICE);
                intent.putExtra(CBConstant.VERIFICATION_MSG_RECEIVED, true);
                intent.setClass(getApplicationContext(), CBActivity.class);
            } else {
                Intent intent2 = new Intent();
                intent2.setClassName(getApplicationContext(), this.D == null ? "" : this.D);
                if (intent2.resolveActivityInfo(getPackageManager(), 0) != null) {
                    intent.putExtra(CBConstant.POST_DATA, str);
                    intent.setClassName(getApplicationContext(), this.D);
                    intent.putExtra(CBConstant.POST_TYPE, "verify_response_post_data");
                } else {
                    z = false;
                }
                a("snooze_notification_expected_action", "merchant_checkout_page");
                this.I = false;
                a();
            }
            if (z) {
                builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
                ((NotificationManager) getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).notify(CBConstant.TRANSACTION_STATUS_NOTIFICATION_ID, builder.build());
                a(CBConstant.GOOD_NETWORK_NOTIFICATION_LAUNCHED, str, false);
                return;
            }
            try {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("The Activity ");
                stringBuilder2.append(this.D);
                stringBuilder2.append(" is not found, Please set valid activity ");
                throw new ActivityNotFoundException(stringBuilder2.toString());
            } catch (ActivityNotFoundException e) {
                ThrowableExtension.printStackTrace(e);
            }
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    private void e() {
        a("snooze_notification_expected_action", "merchant_checkout_page");
        Builder builder = new Builder(this);
        Builder defaults = builder.setContentTitle(this.M.getSurePayNotificationPoorNetWorkTitle()).setContentText(this.M.getSurePayNotificationPoorNetWorkHeader()).setSmallIcon(this.M.getSurePayNotificationIcon()).setAutoCancel(true).setPriority(1).setDefaults(2);
        BigTextStyle bigTextStyle = new BigTextStyle();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.M.getSurePayNotificationPoorNetWorkHeader());
        stringBuilder.append(this.M.getSurePayNotificationPoorNetWorkBody());
        defaults.setStyle(bigTextStyle.bigText(stringBuilder.toString()));
        if (VERSION.SDK_INT >= 23) {
            builder.setColor(getResources().getColor(com.payu.custombrowser.d.b.cb_blue_button, null));
        } else {
            builder.setColor(getResources().getColor(com.payu.custombrowser.d.b.cb_blue_button));
        }
        Intent intent = new Intent();
        intent.setClassName(getApplicationContext(), this.D == null ? "" : this.D);
        if (intent.resolveActivityInfo(getPackageManager(), 0) != null) {
            intent = new Intent();
            intent.setClassName(getApplicationContext(), this.D);
            intent.putExtra(CBConstant.POST_TYPE, "sure_pay_payment_data");
            intent.putExtra(CBConstant.POST_DATA, this.M.getPayuPostData());
            builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
            ((NotificationManager) getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION)).notify(CBConstant.SNOOZE_NOTIFICATION_ID, builder.build());
            return;
        }
        try {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("The Activity ");
            stringBuilder2.append(this.D);
            stringBuilder2.append(" is not found, Please set valid activity ");
            throw new ActivityNotFoundException(stringBuilder2.toString());
        } catch (ActivityNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void a(String str, String str2, boolean z) {
        Intent intent = new Intent("webview_status_action");
        intent.putExtra(CBConstant.BROADCAST_FROM_SERVICE_UPDATE_UI, true);
        intent.putExtra(CBConstant.KEY, str);
        intent.putExtra("value", str2);
        intent.putExtra(CBConstant.CURRENT_URL, this.z);
        intent.putExtra(CBConstant.S2S_RETRY_URL, this.A);
        intent.putExtra(CBConstant.CB_CONFIG, this.M);
        intent.putExtra(CBConstant.IS_FORWARD_JOURNEY, z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
