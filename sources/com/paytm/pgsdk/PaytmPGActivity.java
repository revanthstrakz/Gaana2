package com.paytm.pgsdk;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Iterator;
import org.json.JSONObject;

public class PaytmPGActivity extends Activity {
    private static final String CHECKSUMHASH = "CHECKSUMHASH";
    private static final String CLIENT_AUTHENTICATION_FAILED = "Client authentication failed. Please try again later.";
    private static final String CLIENT_AUTHENTICATION_FAILED_DUE_TO_SERVER_ERROR = "Client authentication failed due to server error. Please try again later.";
    private static final String PAYT_STATUS = "payt_STATUS";
    private static final String SUCCESS = "1";
    private static final String TRANSACTION_CANCELLED = "Transaction Cancelled.";
    private static final String TRANSACTION_NOT_CANCELLED = "Transaction not Cancelled.";
    private static final String UI_INITIALIZATION_ERROR_OCCURED = "Some error occured while initializing UI of Payment Gateway Activity";
    private static final String UI_WEBVIEW_ERROR_OCCURED = "Some UI error occured in WebView of Payment Gateway Activity";
    private static final String USER_CANCELED_TRANSACTION = "Transaction cancelled by user.";
    private volatile AuthenticatorTask mAuthenticator;
    private Dialog mDlg;
    private volatile Bundle mParams;
    protected volatile ProgressBar mProgress;
    private volatile PaytmWebView mWV;
    private boolean mbChecksumGenerated;
    private boolean mbHideHeader;
    private boolean mbIsCancellingRequest;
    private boolean mbSendAllChecksumResponseParametersToPGServer;

    private class AuthenticatorTask extends AsyncTask<String, Void, String> {
        private AuthenticatorTask() {
        }

        /* synthetic */ AuthenticatorTask(PaytmPGActivity paytmPGActivity, AnonymousClass1 anonymousClass1) {
            this();
        }

        /* Access modifiers changed, original: protected|varargs|declared_synchronized */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0116 A:{SYNTHETIC, Splitter:B:27:0x0116} */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0116 A:{SYNTHETIC, Splitter:B:27:0x0116} */
        public synchronized java.lang.String doInBackground(java.lang.String... r7) {
            /*
            r6 = this;
            monitor-enter(r6);
            r0 = "";
            r1 = 0;
            r2 = new java.net.URL;	 Catch:{ Exception -> 0x0108 }
            r3 = 0;
            r7 = r7[r3];	 Catch:{ Exception -> 0x0108 }
            r2.<init>(r7);	 Catch:{ Exception -> 0x0108 }
            r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0108 }
            r7.<init>();	 Catch:{ Exception -> 0x0108 }
            r3 = "URL is ";
            r7.append(r3);	 Catch:{ Exception -> 0x0108 }
            r3 = r2.toString();	 Catch:{ Exception -> 0x0108 }
            r7.append(r3);	 Catch:{ Exception -> 0x0108 }
            r7 = r7.toString();	 Catch:{ Exception -> 0x0108 }
            com.paytm.pgsdk.PaytmUtility.debugLog(r7);	 Catch:{ Exception -> 0x0108 }
            r7 = r2.openConnection();	 Catch:{ Exception -> 0x0108 }
            r1 = "New Connection is created.";
            com.paytm.pgsdk.PaytmUtility.debugLog(r1);	 Catch:{ Exception -> 0x0106 }
            r1 = r2.toString();	 Catch:{ Exception -> 0x0106 }
            r1 = android.webkit.URLUtil.isHttpsUrl(r1);	 Catch:{ Exception -> 0x0106 }
            if (r1 == 0) goto L_0x0059;
        L_0x0037:
            r1 = "Https url";
            com.paytm.pgsdk.PaytmUtility.debugLog(r1);	 Catch:{ Exception -> 0x0106 }
            r1 = "Setting SSLSocketFactory to connection...";
            com.paytm.pgsdk.PaytmUtility.debugLog(r1);	 Catch:{ Exception -> 0x0106 }
            r1 = r7;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ Exception -> 0x0106 }
            r2 = new com.paytm.pgsdk.PaytmSSLSocketFactory;	 Catch:{ Exception -> 0x0106 }
            r3 = com.paytm.pgsdk.PaytmPGActivity.this;	 Catch:{ Exception -> 0x0106 }
            r4 = com.paytm.pgsdk.PaytmPGService.getService();	 Catch:{ Exception -> 0x0106 }
            r4 = r4.mCertificate;	 Catch:{ Exception -> 0x0106 }
            r2.<init>(r3, r4);	 Catch:{ Exception -> 0x0106 }
            r1.setSSLSocketFactory(r2);	 Catch:{ Exception -> 0x0106 }
            r1 = "SSLSocketFactory is set to connection.";
            com.paytm.pgsdk.PaytmUtility.debugLog(r1);	 Catch:{ Exception -> 0x0106 }
        L_0x0059:
            r1 = 1;
            r7.setDoOutput(r1);	 Catch:{ Exception -> 0x0106 }
            r1 = r7;
            r1 = (java.net.HttpURLConnection) r1;	 Catch:{ Exception -> 0x0106 }
            r2 = "POST";
            r1.setRequestMethod(r2);	 Catch:{ Exception -> 0x0106 }
            r1 = com.paytm.pgsdk.PaytmPGActivity.this;	 Catch:{ Exception -> 0x0106 }
            r1 = r1.mParams;	 Catch:{ Exception -> 0x0106 }
            r1 = com.paytm.pgsdk.PaytmUtility.getStringFromBundle(r1);	 Catch:{ Exception -> 0x0106 }
            if (r1 == 0) goto L_0x0114;
        L_0x0071:
            r2 = r1.length();	 Catch:{ Exception -> 0x0106 }
            if (r2 <= 0) goto L_0x0114;
        L_0x0077:
            r2 = "Getting the output stream to post";
            com.paytm.pgsdk.PaytmUtility.debugLog(r2);	 Catch:{ Exception -> 0x0106 }
            r2 = new java.io.PrintWriter;	 Catch:{ Exception -> 0x0106 }
            r3 = r7.getOutputStream();	 Catch:{ Exception -> 0x0106 }
            r2.<init>(r3);	 Catch:{ Exception -> 0x0106 }
            r3 = "posting......";
            com.paytm.pgsdk.PaytmUtility.debugLog(r3);	 Catch:{ Exception -> 0x0106 }
            r2.print(r1);	 Catch:{ Exception -> 0x0106 }
            r2.close();	 Catch:{ Exception -> 0x0106 }
            r1 = "posted parameters and closing output stream";
            com.paytm.pgsdk.PaytmUtility.debugLog(r1);	 Catch:{ Exception -> 0x0106 }
            r1 = r7;
            r1 = (java.net.HttpURLConnection) r1;	 Catch:{ Exception -> 0x0106 }
            r1 = r1.getResponseCode();	 Catch:{ Exception -> 0x0106 }
            r2 = r7;
            r2 = (java.net.HttpURLConnection) r2;	 Catch:{ Exception -> 0x0106 }
            r2 = r2.getResponseMessage();	 Catch:{ Exception -> 0x0106 }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0106 }
            r3.<init>();	 Catch:{ Exception -> 0x0106 }
            r4 = "Response code is ";
            r3.append(r4);	 Catch:{ Exception -> 0x0106 }
            r3.append(r1);	 Catch:{ Exception -> 0x0106 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x0106 }
            com.paytm.pgsdk.PaytmUtility.debugLog(r3);	 Catch:{ Exception -> 0x0106 }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0106 }
            r3.<init>();	 Catch:{ Exception -> 0x0106 }
            r4 = "Response Message is ";
            r3.append(r4);	 Catch:{ Exception -> 0x0106 }
            r3.append(r2);	 Catch:{ Exception -> 0x0106 }
            r2 = r3.toString();	 Catch:{ Exception -> 0x0106 }
            com.paytm.pgsdk.PaytmUtility.debugLog(r2);	 Catch:{ Exception -> 0x0106 }
            r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r1 != r2) goto L_0x0114;
        L_0x00cf:
            r1 = "Getting the input stream to read response";
            com.paytm.pgsdk.PaytmUtility.debugLog(r1);	 Catch:{ Exception -> 0x0106 }
            r1 = new java.util.Scanner;	 Catch:{ Exception -> 0x0106 }
            r2 = r7.getInputStream();	 Catch:{ Exception -> 0x0106 }
            r1.<init>(r2);	 Catch:{ Exception -> 0x0106 }
            r2 = "reading......";
            com.paytm.pgsdk.PaytmUtility.debugLog(r2);	 Catch:{ Exception -> 0x0106 }
        L_0x00e2:
            r2 = r1.hasNextLine();	 Catch:{ Exception -> 0x0106 }
            if (r2 == 0) goto L_0x00fd;
        L_0x00e8:
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0106 }
            r2.<init>();	 Catch:{ Exception -> 0x0106 }
            r2.append(r0);	 Catch:{ Exception -> 0x0106 }
            r3 = r1.nextLine();	 Catch:{ Exception -> 0x0106 }
            r2.append(r3);	 Catch:{ Exception -> 0x0106 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x0106 }
            r0 = r2;
            goto L_0x00e2;
        L_0x00fd:
            r1.close();	 Catch:{ Exception -> 0x0106 }
            r1 = "read response and closing input stream";
            com.paytm.pgsdk.PaytmUtility.debugLog(r1);	 Catch:{ Exception -> 0x0106 }
            goto L_0x0114;
        L_0x0106:
            r1 = move-exception;
            goto L_0x010c;
        L_0x0108:
            r7 = move-exception;
            r5 = r1;
            r1 = r7;
            r7 = r5;
        L_0x010c:
            r2 = "Some exception occurred while making client authentication.";
            com.paytm.pgsdk.PaytmUtility.debugLog(r2);	 Catch:{ all -> 0x0129 }
            com.paytm.pgsdk.PaytmUtility.printStackTrace(r1);	 Catch:{ all -> 0x0129 }
        L_0x0114:
            if (r7 == 0) goto L_0x011e;
        L_0x0116:
            r7 = (java.net.HttpURLConnection) r7;	 Catch:{ Exception -> 0x011c }
            r7.disconnect();	 Catch:{ Exception -> 0x011c }
            goto L_0x011e;
        L_0x011c:
            r7 = move-exception;
            goto L_0x0124;
        L_0x011e:
            r7 = "connection is disconnected";
            com.paytm.pgsdk.PaytmUtility.debugLog(r7);	 Catch:{ Exception -> 0x011c }
            goto L_0x0127;
        L_0x0124:
            com.paytm.pgsdk.PaytmUtility.printStackTrace(r7);	 Catch:{ all -> 0x0129 }
        L_0x0127:
            monitor-exit(r6);
            return r0;
        L_0x0129:
            r7 = move-exception;
            monitor-exit(r6);
            throw r7;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.paytm.pgsdk.PaytmPGActivity$AuthenticatorTask.doInBackground(java.lang.String[]):java.lang.String");
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void onPostExecute(String str) {
            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback;
            if (str != null) {
                Exception access$400;
                try {
                    if (!str.equalsIgnoreCase("")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Response is ");
                        stringBuilder.append(str);
                        PaytmUtility.debugLog(stringBuilder.toString());
                        if (PaytmPGActivity.this.extractJSON(str)) {
                            PaytmPGActivity.this.mbChecksumGenerated = true;
                            PaytmPGActivity.this.mWV.setVisibility(0);
                            PaytmPGActivity.this.mWV.postUrl(PaytmPGService.getService().mPGURL, PaytmUtility.getURLEncodedStringFromBundle(PaytmPGActivity.this.mParams).getBytes());
                            access$400 = PaytmPGActivity.this.mWV;
                            access$400.requestFocus(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                        } else {
                            PaytmPGActivity.this.finish();
                            paytmPaymentTransactionCallback = PaytmPGService.getService().mPaymentTransactionCallback;
                            if (paytmPaymentTransactionCallback != null) {
                                paytmPaymentTransactionCallback.clientAuthenticationFailed(PaytmPGActivity.CLIENT_AUTHENTICATION_FAILED);
                            }
                        }
                    }
                } catch (Exception e) {
                    access$400 = e;
                    PaytmPGActivity.this.finish();
                    PaytmPaymentTransactionCallback paytmPaymentTransactionCallback2 = PaytmPGService.getService().mPaymentTransactionCallback;
                    if (paytmPaymentTransactionCallback2 != null) {
                        paytmPaymentTransactionCallback2.someUIErrorOccurred(PaytmPGActivity.UI_WEBVIEW_ERROR_OCCURED);
                    }
                    PaytmUtility.debugLog("Some exception occurred while posting data to PG Server.");
                    PaytmUtility.printStackTrace(access$400);
                } finally {
                }
            }
            PaytmPGActivity.this.finish();
            paytmPaymentTransactionCallback = PaytmPGService.getService().mPaymentTransactionCallback;
            if (paytmPaymentTransactionCallback != null) {
                paytmPaymentTransactionCallback.clientAuthenticationFailed(PaytmPGActivity.CLIENT_AUTHENTICATION_FAILED_DUE_TO_SERVER_ERROR);
            }
        }
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (initUI()) {
            startTransaction();
        } else {
            finish();
            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.getService().mPaymentTransactionCallback;
            if (paytmPaymentTransactionCallback != null) {
                paytmPaymentTransactionCallback.someUIErrorOccurred(UI_INITIALIZATION_ERROR_OCCURED);
            }
        }
    }

    private synchronized boolean initUI() {
        try {
            if (getIntent() != null) {
                this.mbHideHeader = getIntent().getBooleanExtra("HIDE_HEADER", false);
                this.mbSendAllChecksumResponseParametersToPGServer = getIntent().getBooleanExtra("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER", false);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Hide Header ");
            stringBuilder.append(this.mbHideHeader);
            PaytmUtility.debugLog(stringBuilder.toString());
            PaytmUtility.debugLog("Initializing the UI of Transaction Page...");
            RelativeLayout relativeLayout = new RelativeLayout(this);
            RelativeLayout relativeLayout2 = new RelativeLayout(this);
            relativeLayout2.setLayoutParams(new LayoutParams(-1, -2));
            relativeLayout2.setId(1);
            relativeLayout2.setBackgroundColor(Color.parseColor("#bdbdbd"));
            Button button = new Button(this, null, 16842825);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(15);
            layoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 5.0f);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PaytmUtility.debugLog("User pressed back button which is present in Header Bar.");
                    PaytmPGActivity.this.cancelTransaction();
                }
            });
            button.setLayoutParams(layoutParams);
            button.setText("Cancel");
            TextView textView = new TextView(this);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.addRule(13);
            textView.setLayoutParams(layoutParams2);
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            textView.setText("Paytm Payments");
            relativeLayout2.addView(button);
            relativeLayout2.addView(textView);
            RelativeLayout relativeLayout3 = new RelativeLayout(this);
            layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(3, relativeLayout2.getId());
            relativeLayout3.setLayoutParams(layoutParams);
            this.mWV = new PaytmWebView(this, this.mParams);
            this.mWV.setVisibility(8);
            this.mWV.setLayoutParams(new LayoutParams(-1, -1));
            this.mProgress = new ProgressBar(this, null, 16842873);
            LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            layoutParams3.addRule(13);
            this.mProgress.setLayoutParams(layoutParams3);
            relativeLayout3.addView(this.mWV);
            relativeLayout3.addView(this.mProgress);
            relativeLayout.addView(relativeLayout2);
            relativeLayout.addView(relativeLayout3);
            if (this.mbHideHeader) {
                relativeLayout2.setVisibility(8);
            }
            requestWindowFeature(1);
            setContentView(relativeLayout);
            PaytmUtility.debugLog("Initialized UI of Transaction Page.");
        } catch (Exception e) {
            PaytmUtility.debugLog("Some exception occurred while initializing UI.");
            PaytmUtility.printStackTrace(e);
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void onDestroy() {
        super.onDestroy();
        try {
            if (this.mAuthenticator != null) {
                this.mAuthenticator.cancel(true);
            }
            PaytmPGService.getService().stopService();
        } catch (Exception e) {
            PaytmPGService.getService().stopService();
            PaytmUtility.debugLog("Some exception occurred while destroying the PaytmPGActivity.");
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    private synchronized boolean extractJSON(String str) {
        boolean z;
        z = false;
        try {
            PaytmUtility.debugLog("Parsing JSON");
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            PaytmUtility.debugLog("Appending Key Value pairs");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Send All Checksum Response Parameters to PG ");
            stringBuilder.append(this.mbSendAllChecksumResponseParametersToPGServer);
            PaytmUtility.debugLog(stringBuilder.toString());
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                String string = jSONObject.getString(str2);
                str2 = str2.trim();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(" = ");
                stringBuilder2.append(string);
                PaytmUtility.debugLog(stringBuilder2.toString());
                if (str2.equals(CHECKSUMHASH)) {
                    this.mParams.putString(str2, string);
                } else if (this.mbSendAllChecksumResponseParametersToPGServer) {
                    this.mParams.putString(str2, string);
                }
                if (str2.equals(PAYT_STATUS) && string.equals("1")) {
                    z = true;
                }
            }
        } catch (Exception e) {
            PaytmUtility.debugLog("Some exception occurred while extracting the checksum from CAS Response.");
            PaytmUtility.printStackTrace(e);
        }
        return z;
    }

    private synchronized void startTransaction() {
        PaytmUtility.debugLog("Starting the Process...");
        if (!(getIntent() == null || getIntent().getBundleExtra("Parameters") == null)) {
            this.mParams = getIntent().getBundleExtra("Parameters");
            if (this.mParams != null && this.mParams.size() > 0) {
                PaytmUtility.debugLog("Starting the Client Authentication...");
                this.mAuthenticator = new AuthenticatorTask(this, null);
                if (PaytmPGService.getService() != null) {
                    this.mWV.setVisibility(0);
                    PaytmWebView paytmWebView = this.mWV;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(PaytmPGService.getService().mPGURL);
                    stringBuilder.append("?ORDER_ID=");
                    stringBuilder.append(((String) PaytmPGService.getService().mOrder.getRequestParamMap().get("ORDER_ID")).toString());
                    paytmWebView.postUrl(stringBuilder.toString(), PaytmUtility.getURLEncodedStringFromBundle(this.mParams).getBytes());
                    this.mWV.requestFocus(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                    if (PaytmPGService.getService().mOrder.getRequestParamMap().get("prenotificationurl") != null) {
                        Intent intent = new Intent(getApplicationContext(), IntentServicePreNotification.class);
                        intent.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("prenotificationurl"));
                        getApplicationContext().startService(intent);
                    }
                }
            }
        }
    }

    public synchronized boolean onKeyDown(int i, KeyEvent keyEvent) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User pressed key and key code is ");
        stringBuilder.append(i);
        PaytmUtility.debugLog(stringBuilder.toString());
        if (i == 4) {
            PaytmUtility.debugLog("User pressed hard key back button");
            cancelTransaction();
        }
        return super.onKeyDown(i, keyEvent);
    }

    private synchronized void displayToastNotification(final String str) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    PaytmPGActivity.this.mbIsCancellingRequest = false;
                    PaytmUtility.debugLog(str);
                    Toast.makeText(PaytmPGActivity.this, str, 0).show();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("User cancelled ");
                    stringBuilder.append(PaytmPGActivity.this.mParams);
                    PaytmUtility.debugLog(stringBuilder.toString());
                    PaytmPGService.getService().mPaymentTransactionCallback.onTransactionCancel(PaytmPGActivity.USER_CANCELED_TRANSACTION, PaytmPGActivity.this.mParams);
                    PaytmUtility.debugLog("user cancellation");
                    PaytmPGActivity.this.finish();
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
    }

    private synchronized void cancelTransaction() {
        if (!this.mbIsCancellingRequest) {
            PaytmUtility.debugLog("Displaying Confirmation Dialog");
            Builder builder = new Builder(this);
            builder.setTitle("Cancel Transaction");
            builder.setMessage("Are you sure you want to cancel transaction");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PaytmPGActivity.this.onBackPressed();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PaytmPGActivity.this.mDlg.dismiss();
                }
            });
            this.mDlg = builder.create();
            this.mDlg.show();
        }
    }

    public void onBackPressed() {
        PaytmPGService.getService().mPaymentTransactionCallback.onBackPressedCancelTransaction();
        super.onBackPressed();
    }
}
