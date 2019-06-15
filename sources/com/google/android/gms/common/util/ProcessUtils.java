package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

@KeepForSdk
public class ProcessUtils {
    private static String zzhe;
    private static int zzhf;

    private ProcessUtils() {
    }

    @KeepForSdk
    public static String getMyProcessName() {
        if (zzhe == null) {
            if (zzhf == 0) {
                zzhf = Process.myPid();
            }
            zzhe = zzd(zzhf);
        }
        return zzhe;
    }

    private static String zzd(int i) {
        Closeable closeable;
        Throwable th;
        String str = null;
        if (i <= 0) {
            return null;
        }
        Closeable zzj;
        try {
            StringBuilder stringBuilder = new StringBuilder(25);
            stringBuilder.append("/proc/");
            stringBuilder.append(i);
            stringBuilder.append("/cmdline");
            zzj = zzj(stringBuilder.toString());
            try {
                String trim = zzj.readLine().trim();
                IOUtils.closeQuietly(zzj);
                str = trim;
            } catch (IOException unused) {
                IOUtils.closeQuietly(zzj);
                return str;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                closeable = zzj;
                th = th3;
                IOUtils.closeQuietly(closeable);
                throw th;
            }
        } catch (IOException unused2) {
            zzj = null;
            IOUtils.closeQuietly(zzj);
            return str;
        } catch (Throwable th4) {
            th = th4;
            IOUtils.closeQuietly(closeable);
            throw th;
        }
        return str;
    }

    private static BufferedReader zzj(String str) throws IOException {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            return bufferedReader;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
