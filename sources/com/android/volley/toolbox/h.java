package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.c;
import com.android.volley.g;
import com.android.volley.i;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.android.volley.l;

public class h extends Request<Bitmap> {
    private static final Object f = new Object();
    private final b<Bitmap> a;
    private final Config b;
    private final int c;
    private final int d;
    private ScaleType e;

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void deliverResponse(Bitmap bitmap) {
    }

    public h(String str, b<Bitmap> bVar, int i, int i2, ScaleType scaleType, Config config, a aVar) {
        super(0, str, aVar);
        setRetryPolicy(new c(1000, 2, 2.0f));
        this.a = bVar;
        this.b = config;
        this.c = i;
        this.d = i2;
        this.e = scaleType;
    }

    public Priority getPriority() {
        return Priority.LOW;
    }

    private static int a(int i, int i2, int i3, int i4, ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ScaleType.FIT_XY) {
            return i == 0 ? i3 : i;
        } else {
            if (i == 0) {
                return (int) (((double) i3) * (((double) i2) / ((double) i4)));
            } else if (i2 == 0) {
                return i;
            } else {
                double d = ((double) i4) / ((double) i3);
                double d2;
                if (scaleType == ScaleType.CENTER_CROP) {
                    d2 = (double) i2;
                    if (((double) i) * d < d2) {
                        i = (int) (d2 / d);
                    }
                    return i;
                }
                d2 = (double) i2;
                if (((double) i) * d > d2) {
                    i = (int) (d2 / d);
                }
                return i;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public i<Bitmap> parseNetworkResponse(g gVar) {
        i a;
        synchronized (f) {
            try {
                a = a(gVar);
            } catch (OutOfMemoryError e) {
                l.c("Caught OOM for %d byte image, url=%s", Integer.valueOf(gVar.b.length), getUrl());
                return i.a(new ParseError(e));
            } catch (Throwable th) {
            }
        }
        return a;
    }

    private i<Bitmap> a(g gVar) {
        Object decodeByteArray;
        byte[] bArr = gVar.b;
        Options options = new Options();
        if (this.c == 0 && this.d == 0) {
            options.inPreferredConfig = this.b;
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int a = a(this.c, this.d, i, i2, this.e);
            int a2 = a(this.d, this.c, i2, i, this.e);
            options.inJustDecodeBounds = false;
            options.inSampleSize = a(i, i2, a, a2);
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray != null && (decodeByteArray.getWidth() > a || decodeByteArray.getHeight() > a2)) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeByteArray, a, a2, true);
                decodeByteArray.recycle();
                decodeByteArray = createScaledBitmap;
            }
        }
        if (decodeByteArray == null) {
            return i.a(new ParseError(gVar));
        }
        return i.a(decodeByteArray, d.a(gVar));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void deliverResponse(Bitmap bitmap, boolean z) {
        this.a.onResponse(bitmap);
    }

    static int a(int i, int i2, int i3, int i4) {
        double min = Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4));
        float f = 1.0f;
        while (true) {
            float f2 = 2.0f * f;
            if (((double) f2) > min) {
                return (int) f;
            }
            f = f2;
        }
    }

    public Object getTag() {
        return getUrl();
    }
}
