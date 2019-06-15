package com.gaana.localmedia;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.ImageView;
import com.gaana.application.GaanaApplication;
import com.utilities.Util;
import com.utilities.d;
import java.io.File;
import java.util.ArrayList;

public class LocalMediaImageLoader {
    public static final String PLAYLIST_PREFIX_URL = "PLAYLIST_";
    private static final String TAG = "LocalMediaImageLoader";

    public Bitmap getBitmapFromDisk(String str, ImageView imageView) {
        try {
            if (str.contains(PLAYLIST_PREFIX_URL)) {
                return createBitmapForPlaylist(str.split(PLAYLIST_PREFIX_URL)[1], imageView);
            }
            return createBitmapForAlbum(str);
        } catch (OutOfMemoryError unused) {
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public Bitmap createBitmapForAlbum(String str) {
        str = getAlbumArtwork(str, GaanaApplication.getContext());
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        return BitmapFactory.decodeFile(str, options);
    }

    public Bitmap createBitmapForPlaylist(String str, ImageView imageView) {
        ArrayList allAlbumIdForPlaylist = LocalMediaManager.getInstance(GaanaApplication.getContext()).getAllAlbumIdForPlaylist(str);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < allAlbumIdForPlaylist.size(); i++) {
            Bitmap createBitmapForAlbum = createBitmapForAlbum((String) allAlbumIdForPlaylist.get(i));
            if (createBitmapForAlbum != null) {
                arrayList.add(createBitmapForAlbum);
                if (arrayList.size() >= 4) {
                    break;
                }
            }
        }
        return getCombinedBitmap(arrayList, imageView);
    }

    private Bitmap getCombinedBitmap(ArrayList<Bitmap> arrayList, ImageView imageView) {
        if (arrayList.size() == 0) {
            return null;
        }
        Object createScaledBitmap;
        Object obj;
        Object obj2;
        Object obj3;
        int height = imageView.getHeight();
        int width = imageView.getWidth();
        int size = arrayList.size();
        int i = 0;
        if (size == 1) {
            createScaledBitmap = Bitmap.createScaledBitmap((Bitmap) arrayList.get(0), width, height, true);
            obj = createScaledBitmap;
            obj2 = obj;
            obj3 = obj2;
        } else if (size == 2) {
            createScaledBitmap = Bitmap.createScaledBitmap((Bitmap) arrayList.get(0), width, height, true);
            obj = Bitmap.createScaledBitmap((Bitmap) arrayList.get(1), width, height, true);
            obj3 = createScaledBitmap;
            obj2 = obj;
        } else if (size == 3) {
            createScaledBitmap = resizeImageForImageView((Bitmap) arrayList.get(0), width, height);
            obj = resizeImageForImageView((Bitmap) arrayList.get(1), width, height);
            obj2 = resizeImageForImageView((Bitmap) arrayList.get(2), width, height);
            obj3 = createScaledBitmap;
        } else {
            createScaledBitmap = Bitmap.createScaledBitmap((Bitmap) arrayList.get(0), width, height, false);
            obj = Bitmap.createScaledBitmap((Bitmap) arrayList.get(1), width, height, false);
            obj2 = Bitmap.createScaledBitmap((Bitmap) arrayList.get(2), width, height, false);
            obj3 = Bitmap.createScaledBitmap((Bitmap) arrayList.get(3), width, height, false);
        }
        arrayList.clear();
        arrayList.add(0, createScaledBitmap);
        arrayList.add(1, obj);
        arrayList.add(2, obj2);
        arrayList.add(3, obj3);
        Bitmap createBitmap = Bitmap.createBitmap(width * 2, height * 2, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        while (i < arrayList.size()) {
            if (d.d()) {
                Bitmap a = Util.a((Bitmap) arrayList.get(i));
                if (a != null) {
                    canvas.drawBitmap(a, (float) ((i % 2) * width), (float) ((i / 2) * height), paint);
                    a.recycle();
                }
            } else {
                canvas.drawBitmap((Bitmap) arrayList.get(i), (float) ((i % 2) * width), (float) ((i / 2) * height), paint);
            }
            i++;
        }
        return createBitmap;
    }

    public Bitmap resizeImageForImageView(Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() > bitmap.getHeight()) {
            return Bitmap.createBitmap(bitmap, bitmap.getWidth() - bitmap.getHeight(), 0, bitmap.getHeight(), bitmap.getHeight());
        }
        if (bitmap.getWidth() < bitmap.getHeight()) {
            return Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() - bitmap.getWidth(), bitmap.getWidth(), bitmap.getWidth());
        }
        return Bitmap.createScaledBitmap(bitmap, i, i2, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARNING: Missing block: B:10:0x0035, code skipped:
            if (r7 != null) goto L_0x0045;
     */
    /* JADX WARNING: Missing block: B:19:0x0043, code skipped:
            if (r7 != null) goto L_0x0045;
     */
    /* JADX WARNING: Missing block: B:21:0x0045, code skipped:
            r7.close();
     */
    /* JADX WARNING: Missing block: B:22:0x0048, code skipped:
            return null;
     */
    public static java.lang.String getAlbumArtwork(java.lang.String r7, android.content.Context r8) {
        /*
        r0 = 0;
        r1 = r8.getContentResolver();	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r2 = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r8 = 1;
        r3 = new java.lang.String[r8];	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r4 = "album_art";
        r5 = 0;
        r3[r5] = r4;	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r4 = "_id=?";
        r8 = new java.lang.String[r8];	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r8[r5] = r7;	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r6 = 0;
        r5 = r8;
        r7 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0042, all -> 0x003b }
        r8 = r7.moveToFirst();	 Catch:{ Exception -> 0x0043, all -> 0x0038 }
        if (r8 == 0) goto L_0x0035;
    L_0x0025:
        r8 = "album_art";
        r8 = r7.getColumnIndex(r8);	 Catch:{ Exception -> 0x0043, all -> 0x0038 }
        r8 = r7.getString(r8);	 Catch:{ Exception -> 0x0043, all -> 0x0038 }
        if (r7 == 0) goto L_0x0034;
    L_0x0031:
        r7.close();
    L_0x0034:
        return r8;
    L_0x0035:
        if (r7 == 0) goto L_0x0048;
    L_0x0037:
        goto L_0x0045;
    L_0x0038:
        r8 = move-exception;
        r0 = r7;
        goto L_0x003c;
    L_0x003b:
        r8 = move-exception;
    L_0x003c:
        if (r0 == 0) goto L_0x0041;
    L_0x003e:
        r0.close();
    L_0x0041:
        throw r8;
    L_0x0042:
        r7 = r0;
    L_0x0043:
        if (r7 == 0) goto L_0x0048;
    L_0x0045:
        r7.close();
    L_0x0048:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaImageLoader.getAlbumArtwork(java.lang.String, android.content.Context):java.lang.String");
    }
}
