package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.engine.a.e;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class t {
    private static final Paint a = new Paint(6);
    private static final Paint b = new Paint(7);
    private static final Paint c = new Paint(7);
    private static final List<String> d = Arrays.asList(new String[]{"XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098"});
    private static final Lock e;

    private static final class a implements Lock {
        public void lock() {
        }

        public void lockInterruptibly() throws InterruptedException {
        }

        public boolean tryLock() {
            return true;
        }

        public boolean tryLock(long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
            return true;
        }

        public void unlock() {
        }

        a() {
        }

        @NonNull
        public Condition newCondition() {
            throw new UnsupportedOperationException("Should not be called");
        }
    }

    public static int a(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static boolean b(int i) {
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    static {
        Lock reentrantLock = (d.contains(Build.MODEL) && VERSION.SDK_INT == 22) ? new ReentrantLock() : new a();
        e = reentrantLock;
        c.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    }

    public static Lock a() {
        return e;
    }

    public static Bitmap a(@NonNull e eVar, @NonNull Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        float height;
        float width;
        Matrix matrix = new Matrix();
        float f = 0.0f;
        if (bitmap.getWidth() * i2 > bitmap.getHeight() * i) {
            height = ((float) i2) / ((float) bitmap.getHeight());
            width = (((float) i) - (((float) bitmap.getWidth()) * height)) * 0.5f;
        } else {
            height = ((float) i) / ((float) bitmap.getWidth());
            f = (((float) i2) - (((float) bitmap.getHeight()) * height)) * 0.5f;
            width = 0.0f;
        }
        matrix.setScale(height, height);
        matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (f + 0.5f)));
        Bitmap a = eVar.a(i, i2, a(bitmap));
        a(bitmap, a);
        a(bitmap, a, matrix);
        return a;
    }

    public static Bitmap b(@NonNull e eVar, @NonNull Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size matches input, returning input");
            }
            return bitmap;
        }
        float min = Math.min(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        int round = Math.round(((float) bitmap.getWidth()) * min);
        int round2 = Math.round(((float) bitmap.getHeight()) * min);
        if (bitmap.getWidth() == round && bitmap.getHeight() == round2) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "adjusted target size matches input, returning input");
            }
            return bitmap;
        }
        Bitmap a = eVar.a((int) (((float) bitmap.getWidth()) * min), (int) (((float) bitmap.getHeight()) * min), a(bitmap));
        a(bitmap, a);
        if (Log.isLoggable("TransformationUtils", 2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("request: ");
            stringBuilder.append(i);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(i2);
            Log.v("TransformationUtils", stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("toFit:   ");
            stringBuilder2.append(bitmap.getWidth());
            stringBuilder2.append(AvidJSONUtil.KEY_X);
            stringBuilder2.append(bitmap.getHeight());
            Log.v("TransformationUtils", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("toReuse: ");
            stringBuilder2.append(a.getWidth());
            stringBuilder2.append(AvidJSONUtil.KEY_X);
            stringBuilder2.append(a.getHeight());
            Log.v("TransformationUtils", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("minPct:   ");
            stringBuilder2.append(min);
            Log.v("TransformationUtils", stringBuilder2.toString());
        }
        Matrix matrix = new Matrix();
        matrix.setScale(min, min);
        a(bitmap, a, matrix);
        return a;
    }

    public static Bitmap c(@NonNull e eVar, @NonNull Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() > i || bitmap.getHeight() > i2) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size too big for input, fit centering instead");
            }
            return b(eVar, bitmap, i, i2);
        }
        if (Log.isLoggable("TransformationUtils", 2)) {
            Log.v("TransformationUtils", "requested target size larger or equal to input, returning input");
        }
        return bitmap;
    }

    public static void a(Bitmap bitmap, Bitmap bitmap2) {
        bitmap2.setHasAlpha(bitmap.hasAlpha());
    }

    public static Bitmap a(@NonNull e eVar, @NonNull Bitmap bitmap, int i) {
        if (!b(i)) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        a(i, matrix);
        RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        matrix.mapRect(rectF);
        Bitmap a = eVar.a(Math.round(rectF.width()), Math.round(rectF.height()), a(bitmap));
        matrix.postTranslate(-rectF.left, -rectF.top);
        a(bitmap, a, matrix);
        return a;
    }

    public static Bitmap d(@NonNull e eVar, @NonNull Bitmap bitmap, int i, int i2) {
        i = Math.min(i, i2);
        float f = (float) i;
        float f2 = f / 2.0f;
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        float max = Math.max(f / width, f / height);
        width *= max;
        max *= height;
        height = (f - width) / 2.0f;
        f = (f - max) / 2.0f;
        RectF rectF = new RectF(height, f, width + height, max + f);
        Bitmap a = a(eVar, bitmap);
        Bitmap a2 = eVar.a(i, i, Config.ARGB_8888);
        a2.setHasAlpha(true);
        e.lock();
        Object bitmap2;
        try {
            Canvas canvas = new Canvas(a2);
            canvas.drawCircle(f2, f2, f2, b);
            canvas.drawBitmap(a, null, rectF, c);
            a(canvas);
            if (!a.equals(bitmap2)) {
                eVar.a(a);
            }
            return a2;
        } finally {
            bitmap2 = e;
            bitmap2.unlock();
        }
    }

    private static Bitmap a(@NonNull e eVar, @NonNull Bitmap bitmap) {
        if (Config.ARGB_8888.equals(bitmap.getConfig())) {
            return bitmap;
        }
        Bitmap a = eVar.a(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        new Canvas(a).drawBitmap(bitmap, 0.0f, 0.0f, null);
        return a;
    }

    public static Bitmap b(@NonNull e eVar, @NonNull Bitmap bitmap, int i) {
        h.a(i > 0, "roundingRadius must be greater than 0.");
        Bitmap a = a(eVar, bitmap);
        Bitmap a2 = eVar.a(a.getWidth(), a.getHeight(), Config.ARGB_8888);
        a2.setHasAlpha(true);
        BitmapShader bitmapShader = new BitmapShader(a, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        RectF rectF = new RectF(0.0f, 0.0f, (float) a2.getWidth(), (float) a2.getHeight());
        e.lock();
        Object bitmap2;
        try {
            Canvas canvas = new Canvas(a2);
            canvas.drawColor(0, Mode.CLEAR);
            float f = (float) i;
            canvas.drawRoundRect(rectF, f, f, paint);
            a(canvas);
            if (!a.equals(bitmap2)) {
                eVar.a(a);
            }
            return a2;
        } finally {
            bitmap2 = e;
            bitmap2.unlock();
        }
    }

    private static void a(Canvas canvas) {
        canvas.setBitmap(null);
    }

    private static Config a(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888;
    }

    private static void a(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2, Matrix matrix) {
        e.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawBitmap(bitmap, matrix, a);
            a(canvas);
        } finally {
            e.unlock();
        }
    }

    static void a(int i, Matrix matrix) {
        switch (i) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return;
            case 3:
                matrix.setRotate(180.0f);
                return;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 6:
                matrix.setRotate(90.0f);
                return;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 8:
                matrix.setRotate(-90.0f);
                return;
            default:
                return;
        }
    }
}
