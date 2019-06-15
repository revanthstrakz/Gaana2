package com.til.colombia.android.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.i;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map.Entry;

public final class a {
    private static int c = 15000;
    HashMap<b, String> a = new HashMap();
    public c b;

    class a extends AsyncTask<Void, Void, Bitmap> {
        WeakReference<a> a;
        WeakReference<b> b;
        String c;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        a(b bVar, String str, a aVar) {
            this.a = new WeakReference(aVar);
            this.b = new WeakReference(bVar);
            this.c = str;
        }

        /* Access modifiers changed, original: protected|final */
        public final void onCancelled() {
            super.onCancelled();
            this.b.clear();
            this.a.clear();
        }

        /* JADX WARNING: Removed duplicated region for block: B:36:0x0089  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0078 A:{Catch:{ all -> 0x0086 }} */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x007d A:{Catch:{ all -> 0x0086 }} */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
        private android.graphics.Bitmap a() {
            /*
            r7 = this;
            r0 = r7.isCancelled();
            r1 = 0;
            if (r0 == 0) goto L_0x0008;
        L_0x0007:
            return r1;
        L_0x0008:
            r0 = android.os.Build.VERSION.SDK_INT;
            r2 = 8;
            if (r0 >= r2) goto L_0x0015;
        L_0x000e:
            r0 = "http.keepAlive";
            r2 = "false";
            java.lang.System.setProperty(r0, r2);
        L_0x0015:
            r0 = r7.b;
            r0 = r0.get();
            r0 = (com.til.colombia.android.utils.a.b) r0;
            r2 = r7.a;
            r2 = r2.get();
            r2 = (com.til.colombia.android.utils.a) r2;
            r3 = r7.c;	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r3 = com.til.colombia.android.internal.a.d.a(r3);	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r7.c = r3;	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r3 = new java.net.URL;	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r4 = r7.c;	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r3.<init>(r4);	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r3 = r3.openConnection();	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r3 = (java.net.HttpURLConnection) r3;	 Catch:{ Exception -> 0x006d, all -> 0x006a }
            r4 = com.til.colombia.android.utils.a.c;	 Catch:{ Exception -> 0x0068 }
            r3.setReadTimeout(r4);	 Catch:{ Exception -> 0x0068 }
            r4 = 1;
            r3.setUseCaches(r4);	 Catch:{ Exception -> 0x0068 }
            r4 = r3.getContent();	 Catch:{ Exception -> 0x0068 }
            r4 = (java.io.InputStream) r4;	 Catch:{ Exception -> 0x0068 }
            r5 = android.graphics.BitmapFactory.decodeStream(r4);	 Catch:{ Exception -> 0x0068 }
            r4.close();	 Catch:{ Exception -> 0x0068 }
            if (r0 == 0) goto L_0x005d;
        L_0x0054:
            if (r5 != 0) goto L_0x005a;
        L_0x0056:
            r0.a();	 Catch:{ Exception -> 0x0068 }
            goto L_0x005d;
        L_0x005a:
            r0.a(r5);	 Catch:{ Exception -> 0x0068 }
        L_0x005d:
            if (r2 == 0) goto L_0x0062;
        L_0x005f:
            r2.a(r0);	 Catch:{ Exception -> 0x0068 }
        L_0x0062:
            if (r3 == 0) goto L_0x0067;
        L_0x0064:
            r3.disconnect();
        L_0x0067:
            return r5;
        L_0x0068:
            r4 = move-exception;
            goto L_0x006f;
        L_0x006a:
            r0 = move-exception;
            r3 = r1;
            goto L_0x0087;
        L_0x006d:
            r4 = move-exception;
            r3 = r1;
        L_0x006f:
            r5 = "Col:aos:4.0.0";
            r6 = "";
            com.til.colombia.android.internal.Log.a(r5, r6, r4);	 Catch:{ all -> 0x0086 }
            if (r0 == 0) goto L_0x007b;
        L_0x0078:
            r0.a();	 Catch:{ all -> 0x0086 }
        L_0x007b:
            if (r2 == 0) goto L_0x0080;
        L_0x007d:
            r2.a(r0);	 Catch:{ all -> 0x0086 }
        L_0x0080:
            if (r3 == 0) goto L_0x0085;
        L_0x0082:
            r3.disconnect();
        L_0x0085:
            return r1;
        L_0x0086:
            r0 = move-exception;
        L_0x0087:
            if (r3 == 0) goto L_0x008c;
        L_0x0089:
            r3.disconnect();
        L_0x008c:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.utils.a$a.a():android.graphics.Bitmap");
        }
    }

    public interface b {
        void a();

        void a(Bitmap bitmap);
    }

    public interface c {
        void a();
    }

    private static void a(int i) {
        c = i;
    }

    public final void a(b bVar, String str) {
        if (!h.a(str)) {
            this.a.put(bVar, str);
        }
    }

    public final void a(c cVar) {
        this.b = cVar;
    }

    public final void a(b bVar) {
        if (this.a != null && this.a.containsKey(bVar)) {
            this.a.remove(bVar);
            if (this.a.size() == 0) {
                this.b.a();
                Log.a(i.f, "Images downloading finished.");
                c();
            }
        }
    }

    private void c() {
        this.a = null;
        this.b = null;
    }

    @TargetApi(11)
    public final void a() {
        if (this.b == null) {
            c();
        } else if (this.a == null || this.a.isEmpty()) {
            this.b.a();
            c();
        } else {
            for (Entry entry : this.a.entrySet()) {
                a aVar = new a((b) entry.getKey(), (String) entry.getValue(), this);
                String str = i.f;
                StringBuilder stringBuilder = new StringBuilder("Downloading image from url: ");
                stringBuilder.append(entry.getValue());
                Log.a(str, stringBuilder.toString());
                if (VERSION.SDK_INT >= 11) {
                    aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    aVar.execute(new Void[0]);
                }
            }
        }
    }

    public static Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
    }
}
