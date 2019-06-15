package com.til.colombia.android.service;

import android.content.Context;
import android.os.AsyncTask;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.network.ErrorCode;
import java.io.IOException;
import java.net.ProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

final class cm extends AsyncTask<bl, Integer, m[]> {
    private static final String b = "Col:aos:4.0.0NativeAdRequest";
    private static final m c = new m(true);
    public final cc a;
    private final Context d = a.a();

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        bl[] blVarArr = (bl[]) objArr;
        Log.a(b, "Inside doInBackGround");
        if (a.b(this.d)) {
            Log.a(b, "Network is available and request sent.");
            int i = 0;
            m[] mVarArr = new m[blVarArr.length];
            int length = blVarArr.length;
            int i2 = 0;
            int i3 = i2;
            while (i2 < length) {
                mVarArr[i3] = a(blVarArr[i2]);
                i3++;
                i2++;
            }
            if (this.a != null) {
                int length2 = mVarArr.length;
                while (i < length2) {
                    m mVar = mVarArr[i];
                    if (mVar == null) {
                        this.a.a(new Exception(ErrorCode.NETWORK_ERROR.toString()));
                    } else if (mVar.a || mVar.b != null) {
                        this.a.a(mVar.b);
                    } else {
                        String str = b;
                        StringBuilder stringBuilder = new StringBuilder("Success response :");
                        stringBuilder.append(mVar);
                        stringBuilder.append(", Requester : ");
                        stringBuilder.append(this.a);
                        Log.b(str, stringBuilder.toString());
                        this.a.a(mVar);
                    }
                    i++;
                }
            } else if (this.a != null) {
                this.a.a(new Exception(ErrorCode.NETWORK_ERROR.toString()));
            } else {
                Log.b(b, "REQUESTER is Not available.");
            }
            return mVarArr;
        }
        Log.a(b, "No network connectivity");
        if (this.a != null) {
            this.a.a(new Exception(ErrorCode.NETWORK_ERROR.toString()));
        }
        return null;
    }

    public cm(cc ccVar) {
        this.a = ccVar;
    }

    private m[] a(bl... blVarArr) {
        Log.a(b, "Inside doInBackGround");
        if (a.b(this.d)) {
            Log.a(b, "Network is available and request sent.");
            int i = 0;
            m[] mVarArr = new m[blVarArr.length];
            int length = blVarArr.length;
            int i2 = 0;
            int i3 = i2;
            while (i2 < length) {
                mVarArr[i3] = a(blVarArr[i2]);
                i3++;
                i2++;
            }
            if (this.a != null) {
                int length2 = mVarArr.length;
                while (i < length2) {
                    m mVar = mVarArr[i];
                    if (mVar == null) {
                        this.a.a(new Exception(ErrorCode.NETWORK_ERROR.toString()));
                    } else if (mVar.a || mVar.b != null) {
                        this.a.a(mVar.b);
                    } else {
                        String str = b;
                        StringBuilder stringBuilder = new StringBuilder("Success response :");
                        stringBuilder.append(mVar);
                        stringBuilder.append(", Requester : ");
                        stringBuilder.append(this.a);
                        Log.b(str, stringBuilder.toString());
                        this.a.a(mVar);
                    }
                    i++;
                }
            } else if (this.a != null) {
                this.a.a(new Exception(ErrorCode.NETWORK_ERROR.toString()));
            } else {
                Log.b(b, "REQUESTER is Not available.");
            }
            return mVarArr;
        }
        Log.a(b, "No network connectivity");
        if (this.a != null) {
            this.a.a(new Exception(ErrorCode.NETWORK_ERROR.toString()));
        }
        return null;
    }

    private m a(bl blVar) {
        try {
            return new m(b(blVar));
        } catch (ProtocolException e) {
            Log.a(b, "ClientProtocolException", e);
            return new m(true, e);
        } catch (ConnectTimeoutException e2) {
            Log.a(b, "ConnectTimeoutException", e2);
            return new m(true, e2);
        } catch (IOException e3) {
            Log.a(b, "IOException", e3);
            return new m(true, e3);
        } catch (SecurityException e4) {
            Log.a(b, "permission internet", e4);
            return new m(true, e4);
        } catch (IllegalArgumentException e5) {
            Log.a(b, "IllegalArgumentException", e5);
            return new m(true, e5);
        } catch (Exception e6) {
            Log.a(b, "Exception", e6);
            return new m(true, e6);
        } catch (OutOfMemoryError e7) {
            Log.a(b, "Exception", e7);
            return new m(true, new Exception(e7.getMessage()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x001f  */
    private static java.lang.String b(com.til.colombia.android.service.bl r2) throws java.io.IOException, java.net.URISyntaxException {
        /*
        r0 = 0;
        r2 = com.til.colombia.android.network.q.a(r2);	 Catch:{ all -> 0x001c }
        r2 = com.til.colombia.android.internal.HttpClient.a.a(r2);	 Catch:{ all -> 0x001c }
        r0 = r2.getInputStream();	 Catch:{ all -> 0x0017 }
        r0 = com.til.colombia.android.commons.CommonUtil.a(r0);	 Catch:{ all -> 0x0017 }
        if (r2 == 0) goto L_0x0016;
    L_0x0013:
        r2.disconnect();
    L_0x0016:
        return r0;
    L_0x0017:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r1;
        goto L_0x001d;
    L_0x001c:
        r2 = move-exception;
    L_0x001d:
        if (r0 == 0) goto L_0x0022;
    L_0x001f:
        r0.disconnect();
    L_0x0022:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.cm.b(com.til.colombia.android.service.bl):java.lang.String");
    }
}
