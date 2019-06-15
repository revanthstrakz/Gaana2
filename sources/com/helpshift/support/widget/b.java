package com.helpshift.support.widget;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.Fragment;
import com.helpshift.common.platform.Device;
import com.helpshift.common.platform.Device.PermissionType;
import com.helpshift.conversation.dto.c;
import com.helpshift.util.h;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.p;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;

public class b<T extends Fragment & a> {
    private final String a = "key_extra_data";
    private Device b = o.c().d();
    private WeakReference<T> c;
    private Bundle d;

    public interface a {
        void a(int i, Long l);

        void a(c cVar, Bundle bundle);

        void i();
    }

    public b(T t) {
        this.c = new WeakReference(t);
    }

    public void a(Bundle bundle) {
        l.a("Helpshift_ImagePicker", "Checking permission before launching attachment picker");
        switch (this.b.a(PermissionType.READ_STORAGE)) {
            case AVAILABLE:
                b(bundle);
                return;
            case UNAVAILABLE:
                l.a("Helpshift_ImagePicker", "READ_STORAGE permission is not granted and can't be requested, starting alternate flow");
                a(bundle, 2);
                return;
            case REQUESTABLE:
                l.a("Helpshift_ImagePicker", "READ_STORAGE permission is not granted but can be requested");
                Fragment fragment = (Fragment) this.c.get();
                if (fragment != null) {
                    ((a) fragment).i();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void b(Bundle bundle) {
        a(bundle, 1);
    }

    private void a(Bundle bundle, int i) {
        Intent intent;
        this.d = bundle;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Launching attachment picker now, flowRequestCode: ");
        stringBuilder.append(i);
        l.a("Helpshift_ImagePicker", stringBuilder.toString());
        Context b = o.b();
        int d = o.c().d().d();
        if (i != 2 || d < 19) {
            Intent intent2 = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
            if (intent2.resolveActivity(b.getPackageManager()) != null) {
                a(intent2, i);
                return;
            }
            intent2 = new Intent("android.intent.action.GET_CONTENT");
            intent2.setType("image/*");
            if (d >= 11) {
                intent2.putExtra("android.intent.extra.LOCAL_ONLY", true);
            }
            intent = intent2;
        } else {
            intent = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent.setType("image/*");
            intent.addFlags(1);
            intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
        }
        if (intent.resolveActivity(b.getPackageManager()) == null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("No app found for handling image pick intent ");
            stringBuilder2.append(intent);
            l.c("Helpshift_ImagePicker", stringBuilder2.toString());
            a(-4, null);
        } else {
            a(intent, i);
        }
    }

    public void a(int i, Intent intent) {
        Uri data = intent.getData();
        if (i == 1) {
            a(data);
        } else if (i == 2) {
            i = intent.getFlags() & 1;
            if (VERSION.SDK_INT >= 19) {
                o.b().getContentResolver().takePersistableUriPermission(data, i);
            }
            b(data);
        }
    }

    private void a(Uri uri) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Processing image uri with flow when permissions are available: ");
        stringBuilder.append(uri);
        l.a("Helpshift_ImagePicker", stringBuilder.toString());
        if (d(uri)) {
            File e = e(uri);
            if (e == null || !e.exists()) {
                l.a("Helpshift_ImagePicker", "Image picker file reading error, returning failure");
                a(-1, null);
                return;
            }
            c a = a(e.getPath());
            if (a.d.longValue() <= 26214400 || p.b(e.getPath())) {
                String path = e.getPath();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Image picker result success, path: ");
                stringBuilder2.append(path);
                l.a("Helpshift_ImagePicker", stringBuilder2.toString());
                a(a, this.d);
                return;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Image picker file size limit exceeded (in bytes): ");
            stringBuilder.append(a.c);
            stringBuilder.append(", returning failure");
            l.a("Helpshift_ImagePicker", stringBuilder.toString());
            a(-3, Long.valueOf(26214400));
            return;
        }
        l.a("Helpshift_ImagePicker", "Image picker file invalid mime type, returning failure");
        a(-2, null);
    }

    private void b(Uri uri) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Processing image uri with flow when permissions are not available: ");
        stringBuilder.append(uri);
        l.a("Helpshift_ImagePicker", stringBuilder.toString());
        if (d(uri)) {
            Context b = o.b();
            if (h.a(uri, b)) {
                c c = c(uri);
                Long l = c.d;
                if (l == null || l.longValue() <= 26214400 || p.a(uri, b)) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Image picker result success, path: ");
                    stringBuilder2.append(uri);
                    l.a("Helpshift_ImagePicker", stringBuilder2.toString());
                    a(c, this.d);
                    return;
                }
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Image picker file size limit exceeded (in bytes): ");
                stringBuilder3.append(l);
                stringBuilder3.append(", returning failure");
                l.a("Helpshift_ImagePicker", stringBuilder3.toString());
                a(-3, Long.valueOf(26214400));
                return;
            }
            l.a("Helpshift_ImagePicker", "Image picker file reading error, returning failure");
            a(-1, null);
            return;
        }
        l.a("Helpshift_ImagePicker", "Image picker file invalid mime type, returning failure");
        a(-2, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    private com.helpshift.conversation.dto.c c(android.net.Uri r8) {
        /*
        r7 = this;
        r0 = com.helpshift.util.o.b();
        r1 = r0.getContentResolver();
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r2 = r8;
        r0 = r1.query(r2, r3, r4, r5, r6);
        r1 = 0;
        if (r0 == 0) goto L_0x0069;
    L_0x0014:
        r2 = r0.moveToFirst();	 Catch:{ all -> 0x0062 }
        if (r2 == 0) goto L_0x0069;
    L_0x001a:
        r2 = "_display_name";
        r2 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0062 }
        r2 = r0.getString(r2);	 Catch:{ all -> 0x0062 }
        r3 = com.helpshift.common.c.a(r2);	 Catch:{ all -> 0x0062 }
        if (r3 == 0) goto L_0x0032;
    L_0x002a:
        r2 = java.util.UUID.randomUUID();	 Catch:{ all -> 0x0062 }
        r2 = r2.toString();	 Catch:{ all -> 0x0062 }
    L_0x0032:
        r3 = "_size";
        r3 = r0.getColumnIndex(r3);	 Catch:{ all -> 0x0062 }
        r4 = r0.isNull(r3);	 Catch:{ all -> 0x0062 }
        if (r4 != 0) goto L_0x006a;
    L_0x003e:
        r3 = r0.getString(r3);	 Catch:{ all -> 0x0062 }
        if (r3 == 0) goto L_0x006a;
    L_0x0044:
        r3 = java.lang.Long.valueOf(r3);	 Catch:{ NumberFormatException -> 0x004a }
        r1 = r3;
        goto L_0x006a;
    L_0x004a:
        r3 = move-exception;
        r4 = "Helpshift_ImagePicker";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0062 }
        r5.<init>();	 Catch:{ all -> 0x0062 }
        r6 = "Error getting size due to ";
        r5.append(r6);	 Catch:{ all -> 0x0062 }
        r5.append(r3);	 Catch:{ all -> 0x0062 }
        r3 = r5.toString();	 Catch:{ all -> 0x0062 }
        com.helpshift.util.l.a(r4, r3);	 Catch:{ all -> 0x0062 }
        goto L_0x006a;
    L_0x0062:
        r8 = move-exception;
        if (r0 == 0) goto L_0x0068;
    L_0x0065:
        r0.close();
    L_0x0068:
        throw r8;
    L_0x0069:
        r2 = r1;
    L_0x006a:
        if (r0 == 0) goto L_0x006f;
    L_0x006c:
        r0.close();
    L_0x006f:
        r0 = new com.helpshift.conversation.dto.c;
        r0.<init>(r8, r2, r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.widget.b.c(android.net.Uri):com.helpshift.conversation.dto.c");
    }

    private c a(String str) {
        return new c(str, com.helpshift.support.util.a.a(str), str != null ? Long.valueOf(new File(str).length()) : null);
    }

    private boolean d(Uri uri) {
        return new HashSet(Arrays.asList(new String[]{"image/jpeg", "image/png", "image/gif", "image/x-png", "image/x-citrix-pjpeg", "image/x-citrix-gif", "image/pjpeg"})).contains(o.b().getContentResolver().getType(uri));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    private java.io.File e(android.net.Uri r9) {
        /*
        r8 = this;
        r0 = 1;
        r3 = new java.lang.String[r0];
        r0 = "_data";
        r1 = 0;
        r3[r1] = r0;
        r0 = 0;
        r1 = com.helpshift.util.o.b();	 Catch:{ all -> 0x003b }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x003b }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r2 = r9;
        r9 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x003b }
        if (r9 == 0) goto L_0x0035;
    L_0x001b:
        r1 = r9.moveToFirst();	 Catch:{ all -> 0x0033 }
        if (r1 == 0) goto L_0x0035;
    L_0x0021:
        r1 = "_data";
        r1 = r9.getColumnIndexOrThrow(r1);	 Catch:{ all -> 0x0033 }
        r1 = r9.getString(r1);	 Catch:{ all -> 0x0033 }
        if (r1 == 0) goto L_0x0035;
    L_0x002d:
        r0 = new java.io.File;	 Catch:{ all -> 0x0033 }
        r0.<init>(r1);	 Catch:{ all -> 0x0033 }
        goto L_0x0035;
    L_0x0033:
        r0 = move-exception;
        goto L_0x003f;
    L_0x0035:
        if (r9 == 0) goto L_0x003a;
    L_0x0037:
        r9.close();
    L_0x003a:
        return r0;
    L_0x003b:
        r9 = move-exception;
        r7 = r0;
        r0 = r9;
        r9 = r7;
    L_0x003f:
        if (r9 == 0) goto L_0x0044;
    L_0x0041:
        r9.close();
    L_0x0044:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.widget.b.e(android.net.Uri):java.io.File");
    }

    private void a(c cVar, Bundle bundle) {
        Fragment fragment = (Fragment) this.c.get();
        if (fragment != null) {
            ((a) fragment).a(cVar, bundle);
        }
    }

    private void a(int i, Long l) {
        Fragment fragment = (Fragment) this.c.get();
        if (fragment != null) {
            ((a) fragment).a(i, l);
        }
    }

    private void a(Intent intent, int i) {
        try {
            Fragment fragment = (Fragment) this.c.get();
            if (fragment != null && fragment.getActivity() != null) {
                fragment.startActivityForResult(intent, i);
            }
        } catch (ActivityNotFoundException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error occurred while starting app for handling image pick intent ");
            stringBuilder.append(e);
            l.c("Helpshift_ImagePicker", stringBuilder.toString());
            a(-4, null);
        }
    }

    public void c(Bundle bundle) {
        bundle.putBundle("key_extra_data", this.d);
    }

    public void d(Bundle bundle) {
        if (bundle.containsKey("key_extra_data")) {
            this.d = bundle.getBundle("key_extra_data");
        }
    }
}
