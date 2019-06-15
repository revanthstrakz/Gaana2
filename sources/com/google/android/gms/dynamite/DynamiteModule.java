package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@KeepForSdk
public final class DynamiteModule {
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzc();
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzb();
    private static Boolean zzie = null;
    private static zzi zzif = null;
    private static zzk zzig = null;
    private static String zzih = null;
    private static int zzii = -1;
    private static final ThreadLocal<zza> zzij = new ThreadLocal();
    private static final zza zzik = new zza();
    private static final VersionPolicy zzil = new zzg();
    private final Context zzim;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zza zza) {
            this(str, th);
        }
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        public static class zzb {
            public int zziq = 0;
            public int zzir = 0;
            public int zzis = 0;
        }

        zzb zza(Context context, String str, zza zza) throws LoadingException;
    }

    private static class zza {
        public Cursor zzin;

        private zza() {
        }

        /* synthetic */ zza(zza zza) {
            this();
        }
    }

    private static class zzb implements zza {
        private final int zzio;
        private final int zzip = 0;

        public zzb(int i, int i2) {
            this.zzio = i;
        }

        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        public final int getLocalVersion(Context context, String str) {
            return this.zzio;
        }
    }

    @KeepForSdk
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        zza zza = (zza) zzij.get();
        zza zza2 = new zza();
        zzij.set(zza2);
        zzb zza3;
        DynamiteModule e;
        DynamiteModule zze;
        try {
            int i;
            zza3 = versionPolicy.zza(context, str, zzik);
            int i2 = zza3.zziq;
            int i3 = zza3.zzir;
            StringBuilder stringBuilder = new StringBuilder((68 + String.valueOf(str).length()) + String.valueOf(str).length());
            stringBuilder.append("Considering local module ");
            stringBuilder.append(str);
            stringBuilder.append(":");
            stringBuilder.append(i2);
            stringBuilder.append(" and remote module ");
            stringBuilder.append(str);
            stringBuilder.append(":");
            stringBuilder.append(i3);
            Log.i("DynamiteModule", stringBuilder.toString());
            if (!(zza3.zzis == 0 || (zza3.zzis == -1 && zza3.zziq == 0))) {
                if (zza3.zzis != 1 || zza3.zzir != 0) {
                    e = zza3.zzis;
                    if (e == -1) {
                        zze = zze(context, str);
                        return zze;
                    } else if (zza3.zzis == 1) {
                        e = zza(context, str, zza3.zzir);
                        return e;
                    } else {
                        i = zza3.zzis;
                        StringBuilder stringBuilder2 = new StringBuilder(47);
                        stringBuilder2.append("VersionPolicy returned invalid code:");
                        stringBuilder2.append(i);
                        throw new LoadingException(stringBuilder2.toString(), null);
                    }
                }
            }
            i = zza3.zziq;
            int i4 = zza3.zzir;
            StringBuilder stringBuilder3 = new StringBuilder(91);
            stringBuilder3.append("No acceptable module found. Local version is ");
            stringBuilder3.append(i);
            stringBuilder3.append(" and remote version is ");
            stringBuilder3.append(i4);
            stringBuilder3.append(".");
            throw new LoadingException(stringBuilder3.toString(), null);
        } catch (LoadingException e2) {
            e = e2;
            String str2 = "DynamiteModule";
            String str3 = "Failed to load remote module: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            if (zza3.zziq == 0 || versionPolicy.zza(context, str, new zzb(zza3.zziq, 0)).zzis != -1) {
                throw new LoadingException("Remote load failed. No local fallback found.", e, null);
            }
            zze = zze(context, str);
            return zze;
        } finally {
            if (zza2.zzin != null) {
                zza2.zzin.close();
            }
            zzij.set(zza);
        }
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        StringBuilder stringBuilder;
        String valueOf;
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            stringBuilder = new StringBuilder(61 + String.valueOf(str).length());
            stringBuilder.append("com.google.android.gms.dynamite.descriptors.");
            stringBuilder.append(str);
            stringBuilder.append(".ModuleDescriptor");
            Class loadClass = classLoader.loadClass(stringBuilder.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            valueOf = String.valueOf(declaredField.get(null));
            StringBuilder stringBuilder2 = new StringBuilder((51 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
            stringBuilder2.append("Module descriptor id '");
            stringBuilder2.append(valueOf);
            stringBuilder2.append("' didn't match expected id '");
            stringBuilder2.append(str);
            stringBuilder2.append("'");
            Log.e("DynamiteModule", stringBuilder2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            stringBuilder = new StringBuilder(45 + String.valueOf(str).length());
            stringBuilder.append("Local module descriptor class for ");
            stringBuilder.append(str);
            stringBuilder.append(" not found.");
            Log.w("DynamiteModule", stringBuilder.toString());
            return 0;
        } catch (Exception e) {
            str = "DynamiteModule";
            valueOf = "Failed to load module descriptor class: ";
            String valueOf2 = String.valueOf(e.getMessage());
            Log.e(str, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x00e5 A:{Catch:{ LoadingException -> 0x00c3, Throwable -> 0x00ed }} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00be A:{SYNTHETIC, Splitter:B:56:0x00be} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x007e */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00be A:{SYNTHETIC, Splitter:B:56:0x00be} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00e5 A:{Catch:{ LoadingException -> 0x00c3, Throwable -> 0x00ed }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0035 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|18|19) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:40|41|42|43|51|52|53|54|(0)(0)) */
    /* JADX WARNING: Missing block: B:41:?, code skipped:
            r2.set(null, java.lang.ClassLoader.getSystemClassLoader());
            r2 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Missing block: B:43:0x0088, code skipped:
            r1 = r2;
     */
    public static int zza(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
        r0 = com.google.android.gms.dynamite.DynamiteModule.class;
        monitor-enter(r0);	 Catch:{ Throwable -> 0x00ed }
        r1 = zzie;	 Catch:{ all -> 0x00ea }
        if (r1 != 0) goto L_0x00b7;
    L_0x0007:
        r1 = r8.getApplicationContext();	 Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d }
        r1 = r1.getClassLoader();	 Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d }
        r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class;
        r2 = r2.getName();	 Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d }
        r1 = r1.loadClass(r2);	 Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d }
        r2 = "sClassLoader";
        r2 = r1.getDeclaredField(r2);	 Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d }
        monitor-enter(r1);	 Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d }
        r3 = 0;
        r4 = r2.get(r3);	 Catch:{ all -> 0x008a }
        r4 = (java.lang.ClassLoader) r4;	 Catch:{ all -> 0x008a }
        if (r4 == 0) goto L_0x0038;
    L_0x0029:
        r2 = java.lang.ClassLoader.getSystemClassLoader();	 Catch:{ all -> 0x008a }
        if (r4 != r2) goto L_0x0032;
    L_0x002f:
        r2 = java.lang.Boolean.FALSE;	 Catch:{ all -> 0x008a }
        goto L_0x0087;
    L_0x0032:
        zza(r4);	 Catch:{ LoadingException -> 0x0035 }
    L_0x0035:
        r2 = java.lang.Boolean.TRUE;	 Catch:{ all -> 0x008a }
        goto L_0x0087;
    L_0x0038:
        r4 = "com.google.android.gms";
        r5 = r8.getApplicationContext();	 Catch:{ all -> 0x008a }
        r5 = r5.getPackageName();	 Catch:{ all -> 0x008a }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x008a }
        if (r4 == 0) goto L_0x0052;
    L_0x0048:
        r4 = java.lang.ClassLoader.getSystemClassLoader();	 Catch:{ all -> 0x008a }
        r2.set(r3, r4);	 Catch:{ all -> 0x008a }
        r2 = java.lang.Boolean.FALSE;	 Catch:{ all -> 0x008a }
        goto L_0x0087;
    L_0x0052:
        r4 = zzc(r8, r9, r10);	 Catch:{ LoadingException -> 0x007e }
        r5 = zzih;	 Catch:{ LoadingException -> 0x007e }
        if (r5 == 0) goto L_0x007b;
    L_0x005a:
        r5 = zzih;	 Catch:{ LoadingException -> 0x007e }
        r5 = r5.isEmpty();	 Catch:{ LoadingException -> 0x007e }
        if (r5 == 0) goto L_0x0063;
    L_0x0062:
        goto L_0x007b;
    L_0x0063:
        r5 = new com.google.android.gms.dynamite.zzh;	 Catch:{ LoadingException -> 0x007e }
        r6 = zzih;	 Catch:{ LoadingException -> 0x007e }
        r7 = java.lang.ClassLoader.getSystemClassLoader();	 Catch:{ LoadingException -> 0x007e }
        r5.<init>(r6, r7);	 Catch:{ LoadingException -> 0x007e }
        zza(r5);	 Catch:{ LoadingException -> 0x007e }
        r2.set(r3, r5);	 Catch:{ LoadingException -> 0x007e }
        r5 = java.lang.Boolean.TRUE;	 Catch:{ LoadingException -> 0x007e }
        zzie = r5;	 Catch:{ LoadingException -> 0x007e }
        monitor-exit(r1);	 Catch:{ all -> 0x008a }
        monitor-exit(r0);	 Catch:{ all -> 0x00ea }
        return r4;
    L_0x007b:
        monitor-exit(r1);	 Catch:{ all -> 0x008a }
        monitor-exit(r0);	 Catch:{ all -> 0x00ea }
        return r4;
    L_0x007e:
        r4 = java.lang.ClassLoader.getSystemClassLoader();	 Catch:{ all -> 0x008a }
        r2.set(r3, r4);	 Catch:{ all -> 0x008a }
        r2 = java.lang.Boolean.FALSE;	 Catch:{ all -> 0x008a }
    L_0x0087:
        monitor-exit(r1);	 Catch:{ all -> 0x008a }
        r1 = r2;
        goto L_0x00b5;
    L_0x008a:
        r2 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x008a }
        throw r2;	 Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d, ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x008d }
    L_0x008d:
        r1 = move-exception;
        r2 = "DynamiteModule";
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x00ea }
        r3 = 30;
        r4 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x00ea }
        r4 = r4.length();	 Catch:{ all -> 0x00ea }
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ea }
        r4.<init>(r3);	 Catch:{ all -> 0x00ea }
        r3 = "Failed to load module via V2: ";
        r4.append(r3);	 Catch:{ all -> 0x00ea }
        r4.append(r1);	 Catch:{ all -> 0x00ea }
        r1 = r4.toString();	 Catch:{ all -> 0x00ea }
        android.util.Log.w(r2, r1);	 Catch:{ all -> 0x00ea }
        r1 = java.lang.Boolean.FALSE;	 Catch:{ all -> 0x00ea }
    L_0x00b5:
        zzie = r1;	 Catch:{ all -> 0x00ea }
    L_0x00b7:
        monitor-exit(r0);	 Catch:{ all -> 0x00ea }
        r0 = r1.booleanValue();	 Catch:{ Throwable -> 0x00ed }
        if (r0 == 0) goto L_0x00e5;
    L_0x00be:
        r9 = zzc(r8, r9, r10);	 Catch:{ LoadingException -> 0x00c3 }
        return r9;
    L_0x00c3:
        r9 = move-exception;
        r10 = "DynamiteModule";
        r0 = "Failed to retrieve remote module version: ";
        r9 = r9.getMessage();	 Catch:{ Throwable -> 0x00ed }
        r9 = java.lang.String.valueOf(r9);	 Catch:{ Throwable -> 0x00ed }
        r1 = r9.length();	 Catch:{ Throwable -> 0x00ed }
        if (r1 == 0) goto L_0x00db;
    L_0x00d6:
        r9 = r0.concat(r9);	 Catch:{ Throwable -> 0x00ed }
        goto L_0x00e0;
    L_0x00db:
        r9 = new java.lang.String;	 Catch:{ Throwable -> 0x00ed }
        r9.<init>(r0);	 Catch:{ Throwable -> 0x00ed }
    L_0x00e0:
        android.util.Log.w(r10, r9);	 Catch:{ Throwable -> 0x00ed }
        r8 = 0;
        return r8;
    L_0x00e5:
        r9 = zzb(r8, r9, r10);	 Catch:{ Throwable -> 0x00ed }
        return r9;
    L_0x00ea:
        r9 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00ea }
        throw r9;	 Catch:{ Throwable -> 0x00ed }
    L_0x00ed:
        r9 = move-exception;
        com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r8, r9);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    private static int zzb(Context context, String str, boolean z) {
        zzi zzj = zzj(context);
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzj.zzak() >= 2) {
                return zzj.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzj.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            str = "DynamiteModule";
            String str2 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b6  */
    private static int zzc(android.content.Context r8, java.lang.String r9, boolean r10) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
        r0 = 0;
        r1 = r8.getContentResolver();	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        if (r10 == 0) goto L_0x000a;
    L_0x0007:
        r8 = "api_force_staging";
        goto L_0x000c;
    L_0x000a:
        r8 = "api";
    L_0x000c:
        r10 = 42;
        r2 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r2 = r2.length();	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r10 = r10 + r2;
        r2 = java.lang.String.valueOf(r9);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r2 = r2.length();	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r10 = r10 + r2;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r2.<init>(r10);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r10 = "content://com.google.android.gms.chimera/";
        r2.append(r10);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r2.append(r8);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r8 = "/";
        r2.append(r8);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r2.append(r9);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r8 = r2.toString();	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r2 = android.net.Uri.parse(r8);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r8 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        if (r8 == 0) goto L_0x0092;
    L_0x0047:
        r9 = r8.moveToFirst();	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        if (r9 != 0) goto L_0x004e;
    L_0x004d:
        goto L_0x0092;
    L_0x004e:
        r9 = 0;
        r9 = r8.getInt(r9);	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        if (r9 <= 0) goto L_0x0083;
    L_0x0055:
        r10 = com.google.android.gms.dynamite.DynamiteModule.class;
        monitor-enter(r10);	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        r1 = 2;
        r1 = r8.getString(r1);	 Catch:{ all -> 0x0080 }
        zzih = r1;	 Catch:{ all -> 0x0080 }
        r1 = "loaderVersion";
        r1 = r8.getColumnIndex(r1);	 Catch:{ all -> 0x0080 }
        if (r1 < 0) goto L_0x006d;
    L_0x0067:
        r1 = r8.getInt(r1);	 Catch:{ all -> 0x0080 }
        zzii = r1;	 Catch:{ all -> 0x0080 }
    L_0x006d:
        monitor-exit(r10);	 Catch:{ all -> 0x0080 }
        r10 = zzij;	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        r10 = r10.get();	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        r10 = (com.google.android.gms.dynamite.DynamiteModule.zza) r10;	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        if (r10 == 0) goto L_0x0083;
    L_0x0078:
        r1 = r10.zzin;	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        if (r1 != 0) goto L_0x0083;
    L_0x007c:
        r10.zzin = r8;	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        r8 = r0;
        goto L_0x0083;
    L_0x0080:
        r9 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0080 }
        throw r9;	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
    L_0x0083:
        if (r8 == 0) goto L_0x0088;
    L_0x0085:
        r8.close();
    L_0x0088:
        return r9;
    L_0x0089:
        r9 = move-exception;
        r0 = r8;
        r8 = r9;
        goto L_0x00b4;
    L_0x008d:
        r9 = move-exception;
        r7 = r9;
        r9 = r8;
        r8 = r7;
        goto L_0x00a5;
    L_0x0092:
        r9 = "DynamiteModule";
        r10 = "Failed to retrieve remote module version.";
        android.util.Log.w(r9, r10);	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException;	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        r10 = "Failed to connect to dynamite module ContentResolver.";
        r9.<init>(r10, r0);	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
        throw r9;	 Catch:{ Exception -> 0x008d, all -> 0x0089 }
    L_0x00a1:
        r8 = move-exception;
        goto L_0x00b4;
    L_0x00a3:
        r8 = move-exception;
        r9 = r0;
    L_0x00a5:
        r10 = r8 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException;	 Catch:{ all -> 0x00b2 }
        if (r10 == 0) goto L_0x00aa;
    L_0x00a9:
        throw r8;	 Catch:{ all -> 0x00b2 }
    L_0x00aa:
        r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException;	 Catch:{ all -> 0x00b2 }
        r1 = "V2 version check failed";
        r10.<init>(r1, r8, r0);	 Catch:{ all -> 0x00b2 }
        throw r10;	 Catch:{ all -> 0x00b2 }
    L_0x00b2:
        r8 = move-exception;
        r0 = r9;
    L_0x00b4:
        if (r0 == 0) goto L_0x00b9;
    L_0x00b6:
        r0.close();
    L_0x00b9:
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        str = String.valueOf(str);
        Log.i(str2, str.length() != 0 ? str3.concat(str) : new String(str3));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        LoadingException e;
        try {
            Boolean bool;
            synchronized (DynamiteModule.class) {
                bool = zzie;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", null);
            } else if (bool.booleanValue()) {
                return zzb(context, str, i);
            } else {
                StringBuilder stringBuilder = new StringBuilder(51 + String.valueOf(str).length());
                stringBuilder.append("Selected remote version of ");
                stringBuilder.append(str);
                stringBuilder.append(", version >= ");
                stringBuilder.append(i);
                Log.i("DynamiteModule", stringBuilder.toString());
                zzi zzj = zzj(context);
                if (zzj == null) {
                    throw new LoadingException("Failed to create IDynamiteLoader.", null);
                }
                IObjectWrapper zzb;
                if (zzj.zzak() >= 2) {
                    zzb = zzj.zzb(ObjectWrapper.wrap(context), str, i);
                } else {
                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                    zzb = zzj.zza(ObjectWrapper.wrap(context), str, i);
                }
                if (ObjectWrapper.unwrap(zzb) != null) {
                    return new DynamiteModule((Context) ObjectWrapper.unwrap(zzb));
                }
                throw new LoadingException("Failed to load remote module.", null);
            }
        } catch (RemoteException e2) {
            throw new LoadingException("Failed to load remote module.", e2, null);
        } catch (LoadingException e3) {
            throw e3;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            e3 = new LoadingException("Failed to load remote module.", th, null);
        }
    }

    private static zzi zzj(Context context) {
        synchronized (DynamiteModule.class) {
            zzi zzi;
            if (zzif != null) {
                zzi = zzif;
                return zzi;
            } else if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                    if (iBinder == null) {
                        zzi = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                        if (queryLocalInterface instanceof zzi) {
                            zzi = (zzi) queryLocalInterface;
                        } else {
                            zzi = new zzj(iBinder);
                        }
                    }
                    if (zzi != null) {
                        zzif = zzi;
                        return zzi;
                    }
                } catch (Exception e) {
                    String str = "DynamiteModule";
                    String str2 = "Failed to load IDynamiteLoader from GmsCore: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
        }
        return null;
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzim;
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        zzk zzk;
        StringBuilder stringBuilder = new StringBuilder(51 + String.valueOf(str).length());
        stringBuilder.append("Selected remote version of ");
        stringBuilder.append(str);
        stringBuilder.append(", version >= ");
        stringBuilder.append(i);
        Log.i("DynamiteModule", stringBuilder.toString());
        synchronized (DynamiteModule.class) {
            zzk = zzig;
        }
        if (zzk == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
        }
        zza zza = (zza) zzij.get();
        if (zza == null || zza.zzin == null) {
            throw new LoadingException("No result cursor", null);
        }
        IObjectWrapper zzb;
        context = context.getApplicationContext();
        Cursor cursor = zza.zzin;
        ObjectWrapper.wrap(null);
        if (zzaj().booleanValue()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            zzb = zzk.zzb(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(cursor));
        } else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            zzb = zzk.zza(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(cursor));
        }
        context = (Context) ObjectWrapper.unwrap(zzb);
        if (context != null) {
            return new DynamiteModule(context);
        }
        throw new LoadingException("Failed to get module context", null);
    }

    private static Boolean zzaj() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zzii >= 2);
        }
        return valueOf;
    }

    private static void zza(ClassLoader classLoader) throws LoadingException {
        try {
            zzk zzk;
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzk = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzk) {
                    zzk = (zzk) queryLocalInterface;
                } else {
                    zzk = new zzl(iBinder);
                }
            }
            zzig = zzk;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, null);
        }
    }

    @KeepForSdk
    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzim.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String str2 = "Failed to instantiate module class: ";
            str = String.valueOf(str);
            throw new LoadingException(str.length() != 0 ? str2.concat(str) : new String(str2), e, null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzim = (Context) Preconditions.checkNotNull(context);
    }
}
