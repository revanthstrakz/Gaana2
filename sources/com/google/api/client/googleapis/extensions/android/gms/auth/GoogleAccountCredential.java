package com.google.api.client.googleapis.extensions.android.gms.auth;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.api.client.googleapis.extensions.android.accounts.GoogleAccountManager;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.BackOff;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.io.IOException;
import java.util.Collection;

@Beta
public class GoogleAccountCredential implements HttpRequestInitializer {
    private final GoogleAccountManager accountManager;
    private String accountName;
    private BackOff backOff;
    final Context context;
    final String scope;
    private Account selectedAccount;
    private Sleeper sleeper = Sleeper.DEFAULT;

    @Beta
    class RequestHandler implements HttpExecuteInterceptor, HttpUnsuccessfulResponseHandler {
        boolean received401;
        String token;

        RequestHandler() {
        }

        public void intercept(HttpRequest httpRequest) throws IOException {
            try {
                this.token = GoogleAccountCredential.this.getToken();
                HttpHeaders headers = httpRequest.getHeaders();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Bearer ");
                stringBuilder.append(this.token);
                headers.setAuthorization(stringBuilder.toString());
            } catch (GooglePlayServicesAvailabilityException e) {
                throw new GooglePlayServicesAvailabilityIOException(e);
            } catch (UserRecoverableAuthException e2) {
                throw new UserRecoverableAuthIOException(e2);
            } catch (GoogleAuthException e3) {
                throw new GoogleAuthIOException(e3);
            }
        }

        public boolean handleResponse(HttpRequest httpRequest, HttpResponse httpResponse, boolean z) {
            if (httpResponse.getStatusCode() != 401 || this.received401) {
                return false;
            }
            this.received401 = true;
            GoogleAuthUtil.invalidateToken(GoogleAccountCredential.this.context, this.token);
            return true;
        }
    }

    public GoogleAccountCredential(Context context, String str) {
        this.accountManager = new GoogleAccountManager(context);
        this.context = context;
        this.scope = str;
    }

    public static GoogleAccountCredential usingOAuth2(Context context, Collection<String> collection) {
        boolean z = collection != null && collection.iterator().hasNext();
        Preconditions.checkArgument(z);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("oauth2: ");
        stringBuilder.append(Joiner.on(' ').join(collection));
        return new GoogleAccountCredential(context, stringBuilder.toString());
    }

    public static GoogleAccountCredential usingAudience(Context context, String str) {
        Preconditions.checkArgument(str.length() != 0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("audience:");
        stringBuilder.append(str);
        return new GoogleAccountCredential(context, stringBuilder.toString());
    }

    public final GoogleAccountCredential setSelectedAccountName(String str) {
        this.selectedAccount = this.accountManager.getAccountByName(str);
        if (this.selectedAccount == null) {
            str = null;
        }
        this.accountName = str;
        return this;
    }

    public final GoogleAccountCredential setSelectedAccount(Account account) {
        String str;
        this.selectedAccount = account;
        if (account == null) {
            str = null;
        } else {
            str = account.name;
        }
        this.accountName = str;
        return this;
    }

    public void initialize(HttpRequest httpRequest) {
        RequestHandler requestHandler = new RequestHandler();
        httpRequest.setInterceptor(requestHandler);
        httpRequest.setUnsuccessfulResponseHandler(requestHandler);
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getScope() {
        return this.scope;
    }

    public final GoogleAccountManager getGoogleAccountManager() {
        return this.accountManager;
    }

    public final Account[] getAllAccounts() {
        return this.accountManager.getAccounts();
    }

    public final Account getSelectedAccount() {
        return this.selectedAccount;
    }

    public BackOff getBackOff() {
        return this.backOff;
    }

    public GoogleAccountCredential setBackOff(BackOff backOff) {
        this.backOff = backOff;
        return this;
    }

    public final Sleeper getSleeper() {
        return this.sleeper;
    }

    public final GoogleAccountCredential setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }

    public final String getSelectedAccountName() {
        return this.accountName;
    }

    public final Intent newChooseAccountIntent() {
        return AccountPicker.newChooseAccountIntent(this.selectedAccount, null, new String[]{"com.google"}, true, null, null, null, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0009 A:{LOOP_START, SYNTHETIC, LOOP:0: B:3:0x0009->B:13:0x0009, Splitter:B:3:0x0009} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0009 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:3|4|14|5) */
    /* JADX WARNING: Missing block: B:6:0x0014, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:9:0x0017, code skipped:
            if (r3.backOff == null) goto L_0x0023;
     */
    public java.lang.String getToken() throws java.io.IOException, com.google.android.gms.auth.GoogleAuthException {
        /*
        r3 = this;
        r0 = r3.backOff;
        if (r0 == 0) goto L_0x0009;
    L_0x0004:
        r0 = r3.backOff;
        r0.reset();
    L_0x0009:
        r0 = r3.context;	 Catch:{ IOException -> 0x0014 }
        r1 = r3.accountName;	 Catch:{ IOException -> 0x0014 }
        r2 = r3.scope;	 Catch:{ IOException -> 0x0014 }
        r0 = com.google.android.gms.auth.GoogleAuthUtil.getToken(r0, r1, r2);	 Catch:{ IOException -> 0x0014 }
        return r0;
    L_0x0014:
        r0 = move-exception;
        r1 = r3.backOff;	 Catch:{ InterruptedException -> 0x0009 }
        if (r1 == 0) goto L_0x0023;
    L_0x0019:
        r1 = r3.sleeper;	 Catch:{ InterruptedException -> 0x0009 }
        r2 = r3.backOff;	 Catch:{ InterruptedException -> 0x0009 }
        r1 = com.google.api.client.util.BackOffUtils.next(r1, r2);	 Catch:{ InterruptedException -> 0x0009 }
        if (r1 != 0) goto L_0x0009;
    L_0x0023:
        throw r0;	 Catch:{ InterruptedException -> 0x0009 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential.getToken():java.lang.String");
    }
}
