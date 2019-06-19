package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import net.hockeyapp.android.d.d;
import net.hockeyapp.android.d.i;
import net.hockeyapp.android.objects.CrashManagerUserInput;

public class b {
    private static String a;
    private static String b;
    private static boolean c;
    private static long d;
    private static boolean e;

    public static void a(Context context, String str, c cVar) {
        a(context, "https://sdk.hockeyapp.net/", str, cVar);
    }

    public static void a(Context context, String str, String str2, c cVar) {
        a(context, str, str2, cVar, false);
        a(context, cVar);
    }

    public static void a(Context context, c cVar) {
        boolean z = true;
        boolean z2 = cVar != null && cVar.ignoreDefaultHandler();
        Boolean valueOf = Boolean.valueOf(z2);
        WeakReference weakReference = new WeakReference(context);
        int a = a(weakReference);
        if (a == 1) {
            e = true;
            if (context instanceof Activity) {
                z = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z);
            Boolean valueOf3 = Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("always_send_crash_reports", false) | valueOf2.booleanValue());
            if (cVar != null) {
                valueOf3 = Boolean.valueOf(Boolean.valueOf(valueOf3.booleanValue() | cVar.shouldAutoUploadCrashes()).booleanValue() | cVar.onCrashesFound());
                cVar.onNewCrashesFound();
            }
            if (valueOf3.booleanValue()) {
                b(weakReference, cVar, valueOf.booleanValue());
            } else {
                a(weakReference, cVar, valueOf.booleanValue());
            }
        } else if (a == 2) {
            if (cVar != null) {
                cVar.onConfirmedCrashesFound();
            }
            b(weakReference, cVar, valueOf.booleanValue());
        } else {
            c(weakReference, cVar, valueOf.booleanValue());
        }
    }

    public static int a(WeakReference<Context> weakReference) {
        String[] c = c();
        int i = 0;
        if (c == null || c.length <= 0) {
            return 0;
        }
        List d;
        try {
            d = d(weakReference);
        } catch (Exception unused) {
            d = null;
        }
        if (d != null) {
            int length = c.length;
            while (i < length) {
                if (d.contains(c[i])) {
                    i++;
                }
            }
            return 2;
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01ab  */
    public static void a(java.lang.ref.WeakReference<android.content.Context> r17, net.hockeyapp.android.c r18, net.hockeyapp.android.objects.b r19) {
        /*
        r1 = r17;
        r4 = c();
        r5 = 0;
        r6 = java.lang.Boolean.valueOf(r5);
        if (r4 == 0) goto L_0x01bf;
    L_0x000d:
        r7 = r4.length;
        if (r7 <= 0) goto L_0x01bf;
    L_0x0010:
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "Found ";
        r7.append(r8);
        r8 = r4.length;
        r7.append(r8);
        r8 = " stacktrace(s).";
        r7.append(r8);
        r7 = r7.toString();
        net.hockeyapp.android.d.d.a(r7);
        r7 = r6;
        r6 = r5;
    L_0x002c:
        r8 = r4.length;
        if (r6 >= r8) goto L_0x01bf;
    L_0x002f:
        r9 = r4[r6];	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r10 = b(r1, r9);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r11 = r10.length();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        if (r11 <= 0) goto L_0x0113;
    L_0x003b:
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r11.<init>();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r12 = "Transmitting crash data: \n";
        r11.append(r12);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r11.append(r10);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r11 = r11.toString();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        net.hockeyapp.android.d.d.a(r11);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r11 = ".stacktrace";
        r12 = ".user";
        r11 = r9.replace(r11, r12);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r11 = b(r1, r11);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r12 = ".stacktrace";
        r13 = ".contact";
        r12 = r9.replace(r12, r13);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r12 = b(r1, r12);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        if (r19 == 0) goto L_0x007f;
    L_0x0069:
        r13 = r19.c();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r14 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        if (r14 != 0) goto L_0x0074;
    L_0x0073:
        r11 = r13;
    L_0x0074:
        r13 = r19.b();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r14 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        if (r14 != 0) goto L_0x007f;
    L_0x007e:
        r12 = r13;
    L_0x007f:
        r13 = ".stacktrace";
        r14 = ".description";
        r9 = r9.replace(r13, r14);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = b(r1, r9);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        if (r19 == 0) goto L_0x0092;
    L_0x008d:
        r13 = r19.a();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        goto L_0x0094;
    L_0x0092:
        r13 = "";
    L_0x0094:
        r14 = android.text.TextUtils.isEmpty(r9);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r15 = 1;
        if (r14 != 0) goto L_0x00b9;
    L_0x009b:
        r14 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        if (r14 != 0) goto L_0x00af;
    L_0x00a1:
        r14 = "%s\n\nLog:\n%s";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r8[r5] = r13;	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r8[r15] = r9;	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r13 = java.lang.String.format(r14, r8);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        goto L_0x00b9;
    L_0x00af:
        r8 = "Log:\n%s";
        r13 = new java.lang.Object[r15];	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r13[r5] = r9;	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r13 = java.lang.String.format(r8, r13);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
    L_0x00b9:
        r8 = new java.util.HashMap;	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r8.<init>();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = "raw";
        r8.put(r9, r10);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = "userID";
        r8.put(r9, r11);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = "contact";
        r8.put(r9, r12);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = "description";
        r8.put(r9, r13);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = "sdk";
        r10 = "HockeySDK";
        r8.put(r9, r10);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = "sdk_version";
        r10 = "4.1.1";
        r8.put(r9, r10);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = new net.hockeyapp.android.d.e;	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r10 = b();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9.<init>(r10);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r10 = "POST";
        r9 = r9.a(r10);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r8 = r9.a(r8);	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r8 = r8.a();	 Catch:{ Exception -> 0x0150, all -> 0x014c }
        r9 = r8.getResponseCode();	 Catch:{ Exception -> 0x010f, all -> 0x010b }
        r10 = 202; // 0xca float:2.83E-43 double:1.0E-321;
        if (r9 == r10) goto L_0x0105;
    L_0x00ff:
        r10 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        if (r9 != r10) goto L_0x0104;
    L_0x0103:
        goto L_0x0105;
    L_0x0104:
        r15 = r5;
    L_0x0105:
        r9 = java.lang.Boolean.valueOf(r15);	 Catch:{ Exception -> 0x010f, all -> 0x010b }
        r7 = r9;
        goto L_0x0114;
    L_0x010b:
        r0 = move-exception;
        r3 = r0;
        goto L_0x0187;
    L_0x010f:
        r0 = move-exception;
        r9 = r8;
        r8 = r0;
        goto L_0x0153;
    L_0x0113:
        r8 = 0;
    L_0x0114:
        if (r8 == 0) goto L_0x0119;
    L_0x0116:
        r8.disconnect();
    L_0x0119:
        r8 = r7.booleanValue();
        if (r8 == 0) goto L_0x0138;
    L_0x011f:
        r8 = "Transmission succeeded";
        net.hockeyapp.android.d.d.a(r8);
        r8 = r4[r6];
        a(r1, r8);
        if (r18 == 0) goto L_0x0180;
    L_0x012b:
        r18.onCrashesSent();
        r8 = r4[r6];
    L_0x0130:
        r9 = r18.getMaxRetryAttempts();
        b(r1, r8, r9);
        goto L_0x0180;
    L_0x0138:
        r8 = "Transmission failed, will retry on next register() call";
        net.hockeyapp.android.d.d.a(r8);
        if (r18 == 0) goto L_0x0180;
    L_0x013f:
        r18.onCrashesNotSent();
        r8 = r4[r6];
    L_0x0144:
        r9 = r18.getMaxRetryAttempts();
        a(r1, r8, r9);
        goto L_0x0180;
    L_0x014c:
        r0 = move-exception;
        r3 = r0;
        r8 = 0;
        goto L_0x0187;
    L_0x0150:
        r0 = move-exception;
        r8 = r0;
        r9 = 0;
    L_0x0153:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);	 Catch:{ all -> 0x0184 }
        if (r9 == 0) goto L_0x015b;
    L_0x0158:
        r9.disconnect();
    L_0x015b:
        r8 = r7.booleanValue();
        if (r8 == 0) goto L_0x0173;
    L_0x0161:
        r8 = "Transmission succeeded";
        net.hockeyapp.android.d.d.a(r8);
        r8 = r4[r6];
        a(r1, r8);
        if (r18 == 0) goto L_0x0180;
    L_0x016d:
        r18.onCrashesSent();
        r8 = r4[r6];
        goto L_0x0130;
    L_0x0173:
        r8 = "Transmission failed, will retry on next register() call";
        net.hockeyapp.android.d.d.a(r8);
        if (r18 == 0) goto L_0x0180;
    L_0x017a:
        r18.onCrashesNotSent();
        r8 = r4[r6];
        goto L_0x0144;
    L_0x0180:
        r6 = r6 + 1;
        goto L_0x002c;
    L_0x0184:
        r0 = move-exception;
        r3 = r0;
        r8 = r9;
    L_0x0187:
        if (r8 == 0) goto L_0x018c;
    L_0x0189:
        r8.disconnect();
    L_0x018c:
        r5 = r7.booleanValue();
        if (r5 == 0) goto L_0x01ab;
    L_0x0192:
        r5 = "Transmission succeeded";
        net.hockeyapp.android.d.d.a(r5);
        r5 = r4[r6];
        a(r1, r5);
        if (r18 == 0) goto L_0x01be;
    L_0x019e:
        r18.onCrashesSent();
        r4 = r4[r6];
        r2 = r18.getMaxRetryAttempts();
        b(r1, r4, r2);
        goto L_0x01be;
    L_0x01ab:
        r5 = "Transmission failed, will retry on next register() call";
        net.hockeyapp.android.d.d.a(r5);
        if (r18 == 0) goto L_0x01be;
    L_0x01b2:
        r18.onCrashesNotSent();
        r4 = r4[r6];
        r2 = r18.getMaxRetryAttempts();
        a(r1, r4, r2);
    L_0x01be:
        throw r3;
    L_0x01bf:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.b.a(java.lang.ref.WeakReference, net.hockeyapp.android.c, net.hockeyapp.android.objects.b):void");
    }

    public static void b(WeakReference<Context> weakReference) {
        String[] c = c();
        if (c != null && c.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Found ");
            stringBuilder.append(c.length);
            stringBuilder.append(" stacktrace(s).");
            d.a(stringBuilder.toString());
            for (int i = 0; i < c.length; i++) {
                if (weakReference != null) {
                    try {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Delete stacktrace ");
                        stringBuilder2.append(c[i]);
                        stringBuilder2.append(".");
                        d.a(stringBuilder2.toString());
                        a((WeakReference) weakReference, c[i]);
                        Context context = (Context) weakReference.get();
                        if (context != null) {
                            context.deleteFile(c[i]);
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }
        }
    }

    public static boolean a(CrashManagerUserInput crashManagerUserInput, net.hockeyapp.android.objects.b bVar, c cVar, WeakReference<Context> weakReference, boolean z) {
        switch (crashManagerUserInput) {
            case CrashManagerUserInputDontSend:
                if (cVar != null) {
                    cVar.onUserDeniedCrashes();
                }
                b(weakReference);
                c(weakReference, cVar, z);
                return true;
            case CrashManagerUserInputAlwaysSend:
                Context context = null;
                if (weakReference != null) {
                    context = (Context) weakReference.get();
                }
                if (context == null) {
                    return false;
                }
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("always_send_crash_reports", true).apply();
                a((WeakReference) weakReference, cVar, z, bVar);
                return true;
            case CrashManagerUserInputSend:
                a((WeakReference) weakReference, cVar, z, bVar);
                return true;
            default:
                return false;
        }
    }

    private static void a(Context context, String str, String str2, c cVar, boolean z) {
        if (context != null) {
            if (d == 0) {
                d = System.currentTimeMillis();
            }
            b = str;
            a = i.c(str2);
            boolean z2 = false;
            e = false;
            a.a(context);
            if (a == null) {
                a = a.d;
            }
            if (z) {
                if (cVar != null && cVar.ignoreDefaultHandler()) {
                    z2 = true;
                }
                c(new WeakReference(context), cVar, Boolean.valueOf(z2).booleanValue());
            }
        }
    }

    private static void a(final WeakReference<Context> weakReference, final c cVar, final boolean z) {
        Context context = weakReference != null ? (Context) weakReference.get() : null;
        if (context != null) {
            if (cVar == null || !cVar.onHandleAlertView()) {
                Builder builder = new Builder(context);
                builder.setTitle(a(context));
                builder.setMessage(i.d.hockeyapp_crash_dialog_message);
                builder.setNegativeButton(i.d.hockeyapp_crash_dialog_negative_button, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        b.a(CrashManagerUserInput.CrashManagerUserInputDontSend, null, cVar, weakReference, z);
                    }
                });
                builder.setNeutralButton(i.d.hockeyapp_crash_dialog_neutral_button, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        b.a(CrashManagerUserInput.CrashManagerUserInputAlwaysSend, null, cVar, weakReference, z);
                    }
                });
                builder.setPositiveButton(i.d.hockeyapp_crash_dialog_positive_button, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        b.a(CrashManagerUserInput.CrashManagerUserInputSend, null, cVar, weakReference, z);
                    }
                });
                builder.create().show();
            }
        }
    }

    private static String a(Context context) {
        String b = i.b(context);
        return String.format(context.getString(i.d.hockeyapp_crash_dialog_title), new Object[]{b});
    }

    private static void b(WeakReference<Context> weakReference, c cVar, boolean z) {
        a((WeakReference) weakReference, cVar, z, null);
    }

    private static void a(final WeakReference<Context> weakReference, final c cVar, boolean z, final net.hockeyapp.android.objects.b bVar) {
        c(weakReference);
        c(weakReference, cVar, z);
        Context context = (Context) weakReference.get();
        if ((context == null || i.a(context)) && !c) {
            c = true;
            new Thread() {
                public void run() {
                    b.a(weakReference, cVar, bVar);
                    b.c = false;
                }
            }.start();
        }
    }

    private static void c(WeakReference<Context> weakReference, c cVar, boolean z) {
        if (TextUtils.isEmpty(a.b) || TextUtils.isEmpty(a.d)) {
            d.a("Exception handler not set because version or package is null.");
            return;
        }
        UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Current handler class = ");
            stringBuilder.append(defaultUncaughtExceptionHandler.getClass().getName());
            d.a(stringBuilder.toString());
        }
        if (defaultUncaughtExceptionHandler instanceof d) {
            ((d) defaultUncaughtExceptionHandler).a(cVar);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new d(defaultUncaughtExceptionHandler, cVar, z));
        }
    }

    private static String b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append("api/2/apps/");
        stringBuilder.append(a);
        stringBuilder.append("/crashes/");
        return stringBuilder.toString();
    }

    private static void a(WeakReference<Context> weakReference, String str, int i) {
        if (!(i == -1 || weakReference == null)) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("HockeySDK", 0);
                Editor edit = sharedPreferences.edit();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("RETRY_COUNT: ");
                stringBuilder.append(str);
                int i2 = sharedPreferences.getInt(stringBuilder.toString(), 0);
                if (i2 >= i) {
                    a((WeakReference) weakReference, str);
                    b((WeakReference) weakReference, str, i);
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("RETRY_COUNT: ");
                    stringBuilder2.append(str);
                    edit.putInt(stringBuilder2.toString(), i2 + 1);
                    edit.apply();
                }
            }
        }
    }

    private static void b(WeakReference<Context> weakReference, String str, int i) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                Editor edit = context.getSharedPreferences("HockeySDK", 0).edit();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("RETRY_COUNT: ");
                stringBuilder.append(str);
                edit.remove(stringBuilder.toString());
                edit.apply();
            }
        }
    }

    private static void a(WeakReference<Context> weakReference, String str) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                context.deleteFile(str);
                context.deleteFile(str.replace(".stacktrace", ".user"));
                context.deleteFile(str.replace(".stacktrace", ".contact"));
                context.deleteFile(str.replace(".stacktrace", ".description"));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x004d A:{SYNTHETIC, Splitter:B:29:0x004d} */
    /* JADX WARNING: Missing block: B:25:0x0045, code skipped:
            if (r0 != null) goto L_0x0047;
     */
    /* JADX WARNING: Missing block: B:27:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:33:0x0051, code skipped:
            if (r0 != null) goto L_0x0047;
     */
    private static java.lang.String b(java.lang.ref.WeakReference<android.content.Context> r4, java.lang.String r5) {
        /*
        r0 = 0;
        if (r4 == 0) goto L_0x0059;
    L_0x0003:
        r4 = r4.get();
        r4 = (android.content.Context) r4;
        if (r4 == 0) goto L_0x0059;
    L_0x000b:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x0041 }
        r3 = new java.io.InputStreamReader;	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x0041 }
        r4 = r4.openFileInput(r5);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x0041 }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x0041 }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0051, IOException -> 0x0041 }
    L_0x001e:
        r4 = r2.readLine();	 Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003a, all -> 0x0037 }
        if (r4 == 0) goto L_0x0031;
    L_0x0024:
        r1.append(r4);	 Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003a, all -> 0x0037 }
        r4 = "line.separator";
        r4 = java.lang.System.getProperty(r4);	 Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003a, all -> 0x0037 }
        r1.append(r4);	 Catch:{ FileNotFoundException -> 0x003d, IOException -> 0x003a, all -> 0x0037 }
        goto L_0x001e;
    L_0x0031:
        if (r2 == 0) goto L_0x0054;
    L_0x0033:
        r2.close();	 Catch:{ IOException -> 0x0054 }
        goto L_0x0054;
    L_0x0037:
        r4 = move-exception;
        r0 = r2;
        goto L_0x004b;
    L_0x003a:
        r4 = move-exception;
        r0 = r2;
        goto L_0x0042;
    L_0x003d:
        r0 = r2;
        goto L_0x0051;
    L_0x003f:
        r4 = move-exception;
        goto L_0x004b;
    L_0x0041:
        r4 = move-exception;
    L_0x0042:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x0054;
    L_0x0047:
        r0.close();	 Catch:{ IOException -> 0x0054 }
        goto L_0x0054;
    L_0x004b:
        if (r0 == 0) goto L_0x0050;
    L_0x004d:
        r0.close();	 Catch:{ IOException -> 0x0050 }
    L_0x0050:
        throw r4;
    L_0x0051:
        if (r0 == 0) goto L_0x0054;
    L_0x0053:
        goto L_0x0047;
    L_0x0054:
        r4 = r1.toString();
        return r4;
    L_0x0059:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.b.b(java.lang.ref.WeakReference, java.lang.String):java.lang.String");
    }

    private static void c(WeakReference<Context> weakReference) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                try {
                    String[] c = c();
                    Editor edit = context.getSharedPreferences("HockeySDK", 0).edit();
                    edit.putString("ConfirmedFilenames", a(c, "|"));
                    edit.apply();
                } catch (Exception unused) {
                }
            }
        }
    }

    private static String a(String[] strArr, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            stringBuffer.append(strArr[i]);
            if (i < strArr.length - 1) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    private static String[] c() {
        if (a.a != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Looking for exceptions in: ");
            stringBuilder.append(a.a);
            d.a(stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(a.a);
            stringBuilder2.append("/");
            File file = new File(stringBuilder2.toString());
            if (file.mkdir() || file.exists()) {
                return file.list(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        return str.endsWith(".stacktrace");
                    }
                });
            }
            return new String[0];
        }
        d.a("Can't search for exception as file path is null.");
        return null;
    }

    private static List<String> d(WeakReference<Context> weakReference) {
        if (weakReference != null) {
            Context context = (Context) weakReference.get();
            if (context != null) {
                return Arrays.asList(context.getSharedPreferences("HockeySDK", 0).getString("ConfirmedFilenames", "").split("\\|"));
            }
        }
        return null;
    }

    public static long a() {
        return d;
    }
}
