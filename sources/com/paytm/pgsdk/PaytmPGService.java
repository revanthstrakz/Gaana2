package com.paytm.pgsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Map.Entry;

public class PaytmPGService {
    private static final String PRODUCTION_CANCEL_TRANSACTION_URL = "https://secure.paytm.in/oltp/HANDLER_INTERNAL/CANCEL_TXN";
    private static final String PRODUCTION_CAS_URL = "https://secure.paytm.in/oltp-web/generateChecksum";
    private static final String PRODUCTION_PG_URL = "https://secure.paytm.in/oltp-web/processTransaction";
    private static final String PRODUCTION_STATUS_QUERY_URL = "https://secure.paytm.in/oltp/HANDLER_INTERNAL/TXNSTATUS";
    private static final String STAGING_CANCEL_TRANSACTION_URL = "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/CANCEL_TXN";
    private static final String STAGING_CAS_URL = "https://pguat.paytm.com:8448/CAS/ChecksumGenerator";
    private static final String STAGING_PG_URL = "https://pguat.paytm.com/oltp-web/processTransaction";
    private static final String STAGING_STATUS_QUERY_URL = "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/TXNSTATUS";
    private static volatile PaytmPGService mService;
    protected volatile String mCancelTransactionURL;
    public volatile PaytmClientCertificate mCertificate;
    public volatile PaytmOrder mOrder;
    protected volatile String mPGURL;
    protected volatile PaytmPaymentTransactionCallback mPaymentTransactionCallback;
    protected volatile PaytmRefundCallback mRefundCallback;
    protected volatile PaytmStatusQueryCallback mStatusQueryCallback;
    private volatile String mStatusQueryURL;
    private volatile boolean mbServiceRunning;

    protected static synchronized PaytmPGService getService() {
        PaytmPGService paytmPGService;
        synchronized (PaytmPGService.class) {
            try {
                if (mService == null) {
                    PaytmUtility.debugLog("Creating an instance of Paytm PG Service...");
                    mService = new PaytmPGService();
                    PaytmUtility.debugLog("Created a new instance of Paytm PG Service.");
                }
            } catch (Exception e) {
                PaytmUtility.printStackTrace(e);
            }
            paytmPGService = mService;
        }
        return paytmPGService;
    }

    public static synchronized PaytmPGService getStagingService() {
        PaytmPGService service;
        synchronized (PaytmPGService.class) {
            service = getService();
            service.mStatusQueryURL = STAGING_STATUS_QUERY_URL;
            service.mCancelTransactionURL = STAGING_CANCEL_TRANSACTION_URL;
            service.mPGURL = STAGING_PG_URL;
        }
        return service;
    }

    public static synchronized PaytmPGService getProductionService() {
        PaytmPGService service;
        synchronized (PaytmPGService.class) {
            service = getService();
            service.mStatusQueryURL = PRODUCTION_STATUS_QUERY_URL;
            service.mCancelTransactionURL = PRODUCTION_CANCEL_TRANSACTION_URL;
            service.mPGURL = PRODUCTION_PG_URL;
        }
        return service;
    }

    public synchronized void initialize(PaytmOrder paytmOrder, PaytmClientCertificate paytmClientCertificate) {
        this.mOrder = paytmOrder;
        this.mCertificate = paytmClientCertificate;
    }

    public void enableLog(Context context) {
        ApplicationInfo applicationinfo = getApplicationinfo(context);
        boolean z = false;
        if (applicationinfo != null) {
            int i = applicationinfo.flags & 2;
            applicationinfo.flags = i;
            if (i != 0) {
                z = true;
            }
            Log.setEnableDebugLog(z);
            return;
        }
        Log.setEnableDebugLog(false);
    }

    public synchronized void startPaymentTransaction(Context context, boolean z, boolean z2, PaytmPaymentTransactionCallback paytmPaymentTransactionCallback) {
        try {
            enableLog(context);
            if (!PaytmUtility.isNetworkAvailable(context)) {
                stopService();
                paytmPaymentTransactionCallback.networkNotAvailable();
            } else if (this.mbServiceRunning) {
                PaytmUtility.debugLog("Service is already running.");
            } else {
                Bundle bundle = new Bundle();
                if (this.mOrder != null) {
                    for (Entry entry : this.mOrder.getRequestParamMap().entrySet()) {
                        String str = (String) entry.getKey();
                        String str2 = (String) entry.getValue();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        stringBuilder.append(" = ");
                        stringBuilder.append(str2);
                        PaytmUtility.debugLog(stringBuilder.toString());
                        bundle.putString((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                PaytmUtility.debugLog("Starting the Service...");
                Intent intent = new Intent(context, PaytmPGActivity.class);
                intent.putExtra("Parameters", bundle);
                intent.putExtra("HIDE_HEADER", z);
                intent.putExtra("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER", z2);
                this.mbServiceRunning = true;
                this.mPaymentTransactionCallback = paytmPaymentTransactionCallback;
                ((Activity) context).startActivity(intent);
                PaytmUtility.debugLog("Service Started.");
            }
        } catch (Exception e) {
            stopService();
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void stopService() {
        mService = null;
        PaytmUtility.debugLog("Service Stopped.");
    }

    private ApplicationInfo getApplicationinfo(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}
