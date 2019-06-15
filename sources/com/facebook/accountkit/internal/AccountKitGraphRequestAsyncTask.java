package com.facebook.accountkit.internal;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

final class AccountKitGraphRequestAsyncTask extends AsyncTask<Void, Void, AccountKitGraphResponse> {
    private static final int BACKOFF_INTERVAL_SEC = 5;
    private static final int MAX_NUM_RETRIES = 4;
    private static final String TAG = AccountKitGraphRequestAsyncTask.class.getCanonicalName();
    private static volatile AccountKitGraphRequestAsyncTask currentAsyncTask;
    private final Callback callback;
    private final HttpURLConnection connection;
    private Exception exception;
    private final int numRetries;
    private final AccountKitGraphRequest request;

    /* synthetic */ AccountKitGraphRequestAsyncTask(HttpURLConnection httpURLConnection, AccountKitGraphRequest accountKitGraphRequest, Callback callback, int i, AnonymousClass1 anonymousClass1) {
        this(httpURLConnection, accountKitGraphRequest, callback, i);
    }

    static AccountKitGraphRequestAsyncTask getCurrentAsyncTask() {
        return currentAsyncTask;
    }

    static void setCurrentAsyncTask(AccountKitGraphRequestAsyncTask accountKitGraphRequestAsyncTask) {
        currentAsyncTask = accountKitGraphRequestAsyncTask;
    }

    static AccountKitGraphRequestAsyncTask cancelCurrentAsyncTask() {
        AccountKitGraphRequestAsyncTask accountKitGraphRequestAsyncTask = currentAsyncTask;
        if (accountKitGraphRequestAsyncTask != null) {
            accountKitGraphRequestAsyncTask.cancel(true);
        }
        return accountKitGraphRequestAsyncTask;
    }

    AccountKitGraphRequestAsyncTask(AccountKitGraphRequest accountKitGraphRequest, Callback callback) {
        this(null, accountKitGraphRequest, callback, 0);
    }

    private AccountKitGraphRequestAsyncTask(HttpURLConnection httpURLConnection, AccountKitGraphRequest accountKitGraphRequest, Callback callback, int i) {
        this.connection = httpURLConnection;
        this.request = accountKitGraphRequest;
        this.callback = callback;
        this.numRetries = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{AccountKitGraphRequestAsyncTask:  connection: ");
        stringBuilder.append(this.connection);
        stringBuilder.append(", request: ");
        stringBuilder.append(this.request);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
        if (this.request.getCallbackHandler() == null) {
            Handler handler;
            if (Thread.currentThread() instanceof HandlerThread) {
                handler = new Handler();
            } else {
                handler = new Handler(Looper.getMainLooper());
            }
            this.request.setCallbackHandler(handler);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(AccountKitGraphResponse accountKitGraphResponse) {
        super.onPostExecute(accountKitGraphResponse);
        if (accountKitGraphResponse == null || accountKitGraphResponse.getError() == null || accountKitGraphResponse.getError().getException().getError().getErrorType() != Type.NETWORK_CONNECTION_ERROR || accountKitGraphResponse.getError().getException().getError().getDetailErrorCode() == 101 || this.numRetries >= 4) {
            if (this.callback != null) {
                this.callback.onCompleted(accountKitGraphResponse);
            }
            if (this.exception != null) {
                Log.d(TAG, String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.exception.getMessage()}));
            }
            return;
        }
        new Handler(AccountKitController.getApplicationContext().getMainLooper()).post(new Runnable() {
            public void run() {
                int access$000 = AccountKitGraphRequestAsyncTask.this.numRetries + 1;
                final AccountKitGraphRequestAsyncTask accountKitGraphRequestAsyncTask = new AccountKitGraphRequestAsyncTask(null, AccountKitGraphRequestAsyncTask.this.request, AccountKitGraphRequestAsyncTask.this.callback, access$000, null);
                Utility.getBackgroundExecutor().schedule(new Runnable() {
                    public void run() {
                        if (!AccountKitGraphRequestAsyncTask.this.isCancelled() && !accountKitGraphRequestAsyncTask.isCancelled()) {
                            accountKitGraphRequestAsyncTask.executeOnExecutor(Utility.getThreadPoolExecutor(), new Void[0]);
                        }
                    }
                }, (long) (5 * access$000), TimeUnit.SECONDS);
                if (AccountKitGraphRequestAsyncTask.this.request.isLoginRequest()) {
                    AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(accountKitGraphRequestAsyncTask);
                }
            }
        });
    }

    /* Access modifiers changed, original: protected|varargs */
    public AccountKitGraphResponse doInBackground(Void... voidArr) {
        try {
            if (this.connection == null) {
                return this.request.executeAndWait();
            }
            return AccountKitGraphRequest.executeConnectionAndWait(this.connection, this.request);
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }
}
