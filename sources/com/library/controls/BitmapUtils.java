package com.library.controls;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.utilities.d;

public class BitmapUtils {
    private static final int BLUE_MASK = 255;
    private static final boolean DBG = false;
    private static final int GREEN_MASK = 65280;
    private static final int GREEN_MASK_SHIFT = 8;
    private static final int RED_MASK = 16711680;
    private static final int RED_MASK_SHIFT = 16;
    private static final String TAG = "BitmapUtils";

    private static void log(String str) {
    }

    private BitmapUtils() {
    }

    public static Bitmap createBlurredBitmap(Bitmap bitmap) {
        SystemClock.uptimeMillis();
        if (bitmap == null) {
            Log.w(TAG, "createBlurredBitmap: null bitmap");
            return null;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, true);
        if (d.d() && bitmap.getConfig() == Config.HARDWARE) {
            Bitmap copy = bitmap.copy(Config.RGB_565, true);
            bitmap.recycle();
            bitmap = gaussianBlur(copy);
            SystemClock.uptimeMillis();
            return bitmap;
        }
        bitmap = gaussianBlur(bitmap);
        SystemClock.uptimeMillis();
        return bitmap;
    }

    public static Bitmap gaussianBlur(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        gaussianBlurFilter(iArr, iArr2, width, height);
        gaussianBlurFilter(iArr2, iArr, width, height);
        Bitmap createBitmap = Bitmap.createBitmap(iArr, width, height, Config.ARGB_4444);
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap getCroppedImage(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = 100 - i;
        return Bitmap.createBitmap(bitmap, (width * i2) / 400, (i2 * height) / 400, (width * i) / 200, (height * i) / 200);
    }

    private static void gaussianBlurFilter(int[] iArr, int[] iArr2, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        int[] iArr3 = new int[]{13, 23, 32, 39, 42, 39, 32, 23, 13};
        int i5 = i3 - 1;
        int i6 = 0;
        int i7 = i6;
        while (i6 < i4) {
            int i8 = i6;
            for (int i9 = 0; i9 < i3; i9++) {
                int i10 = 0;
                int i11 = i10;
                int i12 = i11;
                for (int i13 = -4; i13 <= 4; i13++) {
                    int i14 = iArr[((i9 + i13) & i5) + i7];
                    int i15 = iArr3[i13 + 4];
                    i10 += ((RED_MASK & i14) >> 16) * i15;
                    i11 += ((65280 & i14) >> 8) * i15;
                    i12 += i15 * (i14 & 255);
                }
                iArr2[i8] = ((ViewCompat.MEASURED_STATE_MASK | ((i10 >> 8) << 16)) | ((i11 >> 8) << 8)) | (i12 >> 8);
                i8 += i4;
            }
            i7 += i3;
            i6++;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
        RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        float f = (float) i;
        canvas.drawRoundRect(rectF, f, f, paint);
        return createBitmap;
    }
}
