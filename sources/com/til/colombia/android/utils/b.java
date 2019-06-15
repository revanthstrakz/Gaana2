package com.til.colombia.android.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public final class b {

    static class a extends BitmapDrawable {
        final WeakReference<b> a;

        public a(String str, b bVar) {
            super(str);
            this.a = new WeakReference(bVar);
        }

        public final b a() {
            return (b) this.a.get();
        }
    }

    public class b extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> b;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Bitmap bitmap = (Bitmap) obj;
            b bVar = null;
            if (isCancelled()) {
                bitmap = null;
            }
            if (this.b != null && bitmap != null) {
                ImageView imageView = (ImageView) this.b.get();
                if (imageView != null) {
                    Drawable drawable = imageView.getDrawable();
                    if (drawable instanceof a) {
                        bVar = (b) ((a) drawable).a.get();
                    }
                }
                if (this == bVar && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        public b(ImageView imageView) {
            this.b = new WeakReference(imageView);
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0053  */
        private static android.graphics.Bitmap a(java.lang.String... r5) {
            /*
            r0 = 0;
            r1 = r5[r0];
            r2 = 0;
            r1 = com.til.colombia.android.internal.a.d.a(r1);	 Catch:{ Exception -> 0x0041, all -> 0x003e }
            r3 = new java.net.URL;	 Catch:{ Exception -> 0x0041, all -> 0x003e }
            r3.<init>(r1);	 Catch:{ Exception -> 0x0041, all -> 0x003e }
            r1 = r3.openConnection();	 Catch:{ Exception -> 0x0041, all -> 0x003e }
            r1 = (java.net.HttpURLConnection) r1;	 Catch:{ Exception -> 0x0041, all -> 0x003e }
            r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
            r1.setReadTimeout(r3);	 Catch:{ Exception -> 0x003c }
            r3 = 1;
            r1.setUseCaches(r3);	 Catch:{ Exception -> 0x003c }
            r3 = r1.getContent();	 Catch:{ Exception -> 0x003c }
            r3 = (java.io.InputStream) r3;	 Catch:{ Exception -> 0x003c }
            r4 = android.graphics.BitmapFactory.decodeStream(r3);	 Catch:{ Exception -> 0x003c }
            r3.close();	 Catch:{ Exception -> 0x003c }
            r5 = r5[r0];	 Catch:{ Exception -> 0x003c }
            r0 = com.til.colombia.android.commons.a.h.a(r5);	 Catch:{ Exception -> 0x003c }
            if (r0 != 0) goto L_0x0036;
        L_0x0031:
            r0 = com.til.colombia.android.commons.a.h.a;	 Catch:{ Exception -> 0x003c }
            r0.put(r5, r4);	 Catch:{ Exception -> 0x003c }
        L_0x0036:
            if (r1 == 0) goto L_0x003b;
        L_0x0038:
            r1.disconnect();
        L_0x003b:
            return r4;
        L_0x003c:
            r5 = move-exception;
            goto L_0x0043;
        L_0x003e:
            r5 = move-exception;
            r1 = r2;
            goto L_0x0051;
        L_0x0041:
            r5 = move-exception;
            r1 = r2;
        L_0x0043:
            r0 = "Col:aos:4.0.0";
            r3 = "";
            com.til.colombia.android.internal.Log.a(r0, r3, r5);	 Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x004f;
        L_0x004c:
            r1.disconnect();
        L_0x004f:
            return r2;
        L_0x0050:
            r5 = move-exception;
        L_0x0051:
            if (r1 == 0) goto L_0x0056;
        L_0x0053:
            r1.disconnect();
        L_0x0056:
            throw r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.utils.b$b.a(java.lang.String[]):android.graphics.Bitmap");
        }

        private void a(Bitmap bitmap) {
            b bVar = null;
            if (isCancelled()) {
                bitmap = null;
            }
            if (this.b != null && bitmap != null) {
                ImageView imageView = (ImageView) this.b.get();
                if (imageView != null) {
                    Drawable drawable = imageView.getDrawable();
                    if (drawable instanceof a) {
                        bVar = (b) ((a) drawable).a.get();
                    }
                }
                if (this == bVar && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public final void a(ImageView imageView, String str) {
        b bVar = new b(imageView);
        imageView.setImageDrawable(new a(str, bVar));
        if (VERSION.SDK_INT >= 11) {
            bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
            return;
        }
        bVar.execute(new String[]{str});
    }

    private static b b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof a) {
                return (b) ((a) drawable).a.get();
            }
        }
        return null;
    }

    static /* synthetic */ b a(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof a) {
                return (b) ((a) drawable).a.get();
            }
        }
        return null;
    }
}
