package com.google.android.gms.internal.measurement;

import com.google.android.gms.tagmanager.zzdi;
import com.google.api.client.http.HttpStatusCodes;
import com.google.firebase.appindexing.Indexable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final class zzrq implements zzrr {
    private HttpURLConnection zzbop;
    private InputStream zzboq = null;

    zzrq() {
    }

    public final InputStream zzez(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(Indexable.MAX_STRING_LENGTH);
        httpURLConnection.setConnectTimeout(Indexable.MAX_STRING_LENGTH);
        this.zzbop = httpURLConnection;
        httpURLConnection = this.zzbop;
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            this.zzboq = httpURLConnection.getInputStream();
            return this.zzboq;
        }
        StringBuilder stringBuilder = new StringBuilder(25);
        stringBuilder.append("Bad response: ");
        stringBuilder.append(responseCode);
        str = stringBuilder.toString();
        if (responseCode == 404) {
            throw new FileNotFoundException(str);
        } else if (responseCode == HttpStatusCodes.STATUS_CODE_SERVICE_UNAVAILABLE) {
            throw new zzrt(str);
        } else {
            throw new IOException(str);
        }
    }

    public final void close() {
        HttpURLConnection httpURLConnection = this.zzbop;
        try {
            if (this.zzboq != null) {
                this.zzboq.close();
            }
        } catch (IOException e) {
            String str = "HttpUrlConnectionNetworkClient: Error when closing http input stream: ";
            String valueOf = String.valueOf(e.getMessage());
            zzdi.zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
}
