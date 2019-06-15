package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.api.client.http.HttpStatusCodes;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzark
public final class zzbbh implements zzbaw {
    @Nullable
    private final String zzeiz;

    public zzbbh() {
        this(null);
    }

    public zzbbh(@Nullable String str) {
        this.zzeiz = str;
    }

    @WorkerThread
    public final void zzed(String str) {
        StringBuilder stringBuilder;
        String str2;
        HttpURLConnection httpURLConnection;
        try {
            str2 = "Pinging URL: ";
            String valueOf = String.valueOf(str);
            zzbbd.zzdn(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzwu.zzpv();
            zzbat.zza(true, httpURLConnection, this.zzeiz);
            zzbax zzbax = new zzbax();
            zzbax.zza(httpURLConnection, null);
            int responseCode = httpURLConnection.getResponseCode();
            zzbax.zza(httpURLConnection, responseCode);
            if (responseCode < 200 || responseCode >= HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES) {
                StringBuilder stringBuilder2 = new StringBuilder(65 + String.valueOf(str).length());
                stringBuilder2.append("Received non-success response code ");
                stringBuilder2.append(responseCode);
                stringBuilder2.append(" from pinging URL: ");
                stringBuilder2.append(str);
                zzbbd.zzeo(stringBuilder2.toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            String message = e.getMessage();
            stringBuilder = new StringBuilder((32 + String.valueOf(str).length()) + String.valueOf(message).length());
            stringBuilder.append("Error while parsing ping URL: ");
            stringBuilder.append(str);
            stringBuilder.append(". ");
            stringBuilder.append(message);
            zzbbd.zzeo(stringBuilder.toString());
        } catch (IOException e2) {
            str2 = e2.getMessage();
            stringBuilder = new StringBuilder((27 + String.valueOf(str).length()) + String.valueOf(str2).length());
            stringBuilder.append("Error while pinging URL: ");
            stringBuilder.append(str);
            stringBuilder.append(". ");
            stringBuilder.append(str2);
            zzbbd.zzeo(stringBuilder.toString());
        } catch (RuntimeException e3) {
            str2 = e3.getMessage();
            stringBuilder = new StringBuilder((27 + String.valueOf(str).length()) + String.valueOf(str2).length());
            stringBuilder.append("Error while pinging URL: ");
            stringBuilder.append(str);
            stringBuilder.append(". ");
            stringBuilder.append(str2);
            zzbbd.zzeo(stringBuilder.toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
