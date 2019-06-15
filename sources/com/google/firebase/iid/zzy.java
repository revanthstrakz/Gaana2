package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

final class zzy {
    zzy() {
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    public final zzz zzb(Context context, String str) throws zzaa {
        zzz zzd = zzd(context, str);
        if (zzd != null) {
            return zzd;
        }
        return zzc(context, str);
    }

    /* Access modifiers changed, original: final */
    @WorkerThread
    public final zzz zzc(Context context, String str) {
        zzz zzz = new zzz(zza.zzb(), System.currentTimeMillis());
        zzz zza = zza(context, str, zzz, true);
        if (zza == null || zza.equals(zzz)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Generated new key");
            }
            zza(context, str, zzz);
            return zzz;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
        }
        return zza;
    }

    static void zza(Context context) {
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    @Nullable
    private final zzz zzd(Context context, String str) throws zzaa {
        zzaa e;
        try {
            zzz zze = zze(context, str);
            if (zze != null) {
                zza(context, str, zze);
                return zze;
            }
            e = null;
            try {
                zzz zza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (zza != null) {
                    zza(context, str, zza, false);
                    return zza;
                }
            } catch (zzaa e2) {
                e = e2;
            }
            if (e == null) {
                return null;
            }
            throw e;
        } catch (zzaa e3) {
            e = e3;
        }
    }

    private static KeyPair zzc(String str, String str2) throws zzaa {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(19 + String.valueOf(valueOf).length());
                stringBuilder.append("Invalid key stored ");
                stringBuilder.append(valueOf);
                Log.w("FirebaseInstanceId", stringBuilder.toString());
                throw new zzaa(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzaa(e2);
        }
    }

    @Nullable
    private final zzz zze(Context context, String str) throws zzaa {
        StringBuilder stringBuilder;
        File zzf = zzf(context, str);
        if (!zzf.exists()) {
            return null;
        }
        try {
            return zza(zzf);
        } catch (zzaa | IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                str = String.valueOf(e);
                stringBuilder = new StringBuilder(40 + String.valueOf(str).length());
                stringBuilder.append("Failed to read key from file, retrying: ");
                stringBuilder.append(str);
                Log.d("FirebaseInstanceId", stringBuilder.toString());
            }
            try {
                return zza(zzf);
            } catch (IOException e2) {
                String valueOf = String.valueOf(e2);
                stringBuilder = new StringBuilder(45 + String.valueOf(valueOf).length());
                stringBuilder.append("IID file exists, but failed to read from it: ");
                stringBuilder.append(valueOf);
                Log.w("FirebaseInstanceId", stringBuilder.toString());
                throw new zzaa(e2);
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x0062=Splitter:B:19:0x0062, B:31:0x00a3=Splitter:B:31:0x00a3, B:52:0x00bf=Splitter:B:52:0x00bf} */
    @android.support.annotation.Nullable
    private final com.google.firebase.iid.zzz zza(android.content.Context r9, java.lang.String r10, com.google.firebase.iid.zzz r11, boolean r12) {
        /*
        r8 = this;
        r0 = "FirebaseInstanceId";
        r1 = 3;
        r0 = android.util.Log.isLoggable(r0, r1);
        if (r0 == 0) goto L_0x0010;
    L_0x0009:
        r0 = "FirebaseInstanceId";
        r2 = "Writing key to properties file";
        android.util.Log.d(r0, r2);
    L_0x0010:
        r0 = new java.util.Properties;
        r0.<init>();
        r2 = "pub";
        r3 = r11.zzv();
        r0.setProperty(r2, r3);
        r2 = "pri";
        r3 = r11.zzw();
        r0.setProperty(r2, r3);
        r2 = "cre";
        r3 = r11.zzbs;
        r3 = java.lang.String.valueOf(r3);
        r0.setProperty(r2, r3);
        r9 = zzf(r9, r10);
        r10 = 0;
        r9.createNewFile();	 Catch:{ IOException -> 0x00c3 }
        r2 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x00c3 }
        r3 = "rw";
        r2.<init>(r9, r3);	 Catch:{ IOException -> 0x00c3 }
        r9 = r2.getChannel();	 Catch:{ Throwable -> 0x00b9, all -> 0x00b6 }
        r9.lock();	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r3 = 0;
        if (r12 == 0) goto L_0x0094;
    L_0x004e:
        r5 = r9.size();	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r12 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1));
        if (r12 <= 0) goto L_0x0094;
    L_0x0056:
        r9.position(r3);	 Catch:{ zzaa | IOException -> 0x0066, zzaa | IOException -> 0x0066 }
        r12 = zza(r9);	 Catch:{ zzaa | IOException -> 0x0066, zzaa | IOException -> 0x0066 }
        if (r9 == 0) goto L_0x0062;
    L_0x005f:
        zza(r10, r9);	 Catch:{ Throwable -> 0x00b9, all -> 0x00b6 }
    L_0x0062:
        zza(r10, r2);	 Catch:{ IOException -> 0x00c3 }
        return r12;
    L_0x0066:
        r12 = move-exception;
        r5 = "FirebaseInstanceId";
        r1 = android.util.Log.isLoggable(r5, r1);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r1 == 0) goto L_0x0094;
    L_0x006f:
        r1 = "FirebaseInstanceId";
        r12 = java.lang.String.valueOf(r12);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r5 = 64;
        r6 = java.lang.String.valueOf(r12);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6 = r6.length();	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r5 = r5 + r6;
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6.<init>(r5);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r5 = "Tried reading key pair before writing new one, but failed with: ";
        r6.append(r5);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r6.append(r12);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r12 = r6.toString();	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        android.util.Log.d(r1, r12);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
    L_0x0094:
        r9.position(r3);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r12 = java.nio.channels.Channels.newOutputStream(r9);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        r0.store(r12, r10);	 Catch:{ Throwable -> 0x00aa, all -> 0x00a7 }
        if (r9 == 0) goto L_0x00a3;
    L_0x00a0:
        zza(r10, r9);	 Catch:{ Throwable -> 0x00b9, all -> 0x00b6 }
    L_0x00a3:
        zza(r10, r2);	 Catch:{ IOException -> 0x00c3 }
        return r11;
    L_0x00a7:
        r11 = move-exception;
        r12 = r10;
        goto L_0x00b0;
    L_0x00aa:
        r11 = move-exception;
        throw r11;	 Catch:{ all -> 0x00ac }
    L_0x00ac:
        r12 = move-exception;
        r7 = r12;
        r12 = r11;
        r11 = r7;
    L_0x00b0:
        if (r9 == 0) goto L_0x00b5;
    L_0x00b2:
        zza(r12, r9);	 Catch:{ Throwable -> 0x00b9, all -> 0x00b6 }
    L_0x00b5:
        throw r11;	 Catch:{ Throwable -> 0x00b9, all -> 0x00b6 }
    L_0x00b6:
        r9 = move-exception;
        r11 = r10;
        goto L_0x00bf;
    L_0x00b9:
        r9 = move-exception;
        throw r9;	 Catch:{ all -> 0x00bb }
    L_0x00bb:
        r11 = move-exception;
        r7 = r11;
        r11 = r9;
        r9 = r7;
    L_0x00bf:
        zza(r11, r2);	 Catch:{ IOException -> 0x00c3 }
        throw r9;	 Catch:{ IOException -> 0x00c3 }
    L_0x00c3:
        r9 = move-exception;
        r11 = "FirebaseInstanceId";
        r9 = java.lang.String.valueOf(r9);
        r12 = 21;
        r0 = java.lang.String.valueOf(r9);
        r0 = r0.length();
        r12 = r12 + r0;
        r0 = new java.lang.StringBuilder;
        r0.<init>(r12);
        r12 = "Failed to write key: ";
        r0.append(r12);
        r0.append(r9);
        r9 = r0.toString();
        android.util.Log.w(r11, r9);
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzy.zza(android.content.Context, java.lang.String, com.google.firebase.iid.zzz, boolean):com.google.firebase.iid.zzz");
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static File zzf(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "com.google.InstanceId.properties";
        } else {
            try {
                str = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                StringBuilder stringBuilder = new StringBuilder(33 + String.valueOf(str).length());
                stringBuilder.append("com.google.InstanceId_");
                stringBuilder.append(str);
                stringBuilder.append(".properties");
                str = stringBuilder.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), str);
    }

    /* JADX WARNING: Missing block: B:26:0x0036, code skipped:
            zza(r10, r0);
     */
    private final com.google.firebase.iid.zzz zza(java.io.File r10) throws com.google.firebase.iid.zzaa, java.io.IOException {
        /*
        r9 = this;
        r0 = new java.io.FileInputStream;
        r0.<init>(r10);
        r10 = 0;
        r7 = r0.getChannel();	 Catch:{ Throwable -> 0x0034 }
        r2 = 0;
        r4 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r6 = 1;
        r1 = r7;
        r1.lock(r2, r4, r6);	 Catch:{ Throwable -> 0x0026, all -> 0x0023 }
        r1 = zza(r7);	 Catch:{ Throwable -> 0x0026, all -> 0x0023 }
        if (r7 == 0) goto L_0x001f;
    L_0x001c:
        zza(r10, r7);	 Catch:{ Throwable -> 0x0034 }
    L_0x001f:
        zza(r10, r0);
        return r1;
    L_0x0023:
        r1 = move-exception;
        r2 = r10;
        goto L_0x002c;
    L_0x0026:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x0028 }
    L_0x0028:
        r2 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
    L_0x002c:
        if (r7 == 0) goto L_0x0031;
    L_0x002e:
        zza(r2, r7);	 Catch:{ Throwable -> 0x0034 }
    L_0x0031:
        throw r1;	 Catch:{ Throwable -> 0x0034 }
    L_0x0032:
        r1 = move-exception;
        goto L_0x0036;
    L_0x0034:
        r10 = move-exception;
        throw r10;	 Catch:{ all -> 0x0032 }
    L_0x0036:
        zza(r10, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzy.zza(java.io.File):com.google.firebase.iid.zzz");
    }

    private static zzz zza(FileChannel fileChannel) throws zzaa, IOException {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        String property = properties.getProperty("pub");
        String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new zzaa("Invalid properties file");
        }
        try {
            return new zzz(zzc(property, property2), Long.parseLong(properties.getProperty("cre")));
        } catch (NumberFormatException e) {
            throw new zzaa(e);
        }
    }

    @Nullable
    private static zzz zza(SharedPreferences sharedPreferences, String str) throws zzaa {
        String string = sharedPreferences.getString(zzaw.zzd(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzaw.zzd(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzz(zzc(string, string2), zzb(sharedPreferences, str));
    }

    private final void zza(Context context, String str, zzz zzz) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzz.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzaa unused) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(zzaw.zzd(str, "|P|"), zzz.zzv());
        edit.putString(zzaw.zzd(str, "|K|"), zzz.zzw());
        edit.putString(zzaw.zzd(str, "cre"), String.valueOf(zzz.zzbs));
        edit.commit();
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzaw.zzd(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }
}
