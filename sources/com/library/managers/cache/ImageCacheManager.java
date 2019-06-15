package com.library.managers.cache;

import android.graphics.Bitmap;
import com.library.managers.cache.CacheHelper.OnCacheOpFinishedListner;
import com.library.util.StorageUtils;

public interface ImageCacheManager {

    public static class ImageDBCache implements ImageCacheManager {
        public Bitmap getBitmap(String str) {
            return null;
        }

        public void getBitmap(String str, OnCacheOpFinishedListner onCacheOpFinishedListner) {
        }

        public CacheResult putBitmapInCache(String str, Bitmap bitmap) {
            return null;
        }
    }

    public static class ImageDiskCache implements ImageCacheManager {
        private static String DIR_NAME = "App_Images";
        private static long IMAGE_CACHE_SIZE = 10485760;
        private static int PERCENTAGE_TO_BE_DELETED = 25;
        private static long imageDirSize;
        private final Object lock = new Object();

        public void getBitmap(String str, OnCacheOpFinishedListner onCacheOpFinishedListner) {
            Bitmap bitmapFromDisk = StorageUtils.getBitmapFromDisk(str, DIR_NAME);
            if (bitmapFromDisk != null) {
                onCacheOpFinishedListner.onCacheOpFinishedListner(new CacheResult(0), bitmapFromDisk);
            } else {
                onCacheOpFinishedListner.onCacheOpFinishedListner(new CacheResult(3), null);
            }
        }

        public CacheResult putBitmapInCache(String str, Bitmap bitmap) {
            if (!StorageUtils.isSdCardWrittenable()) {
                return new CacheResult(2);
            }
            if (StorageUtils.getAvailableStorage() < IMAGE_CACHE_SIZE) {
                return new CacheResult(1);
            }
            if (imageDirSize == 0) {
                imageDirSize = StorageUtils.getFileSize(DIR_NAME);
            }
            synchronized (this.lock) {
                if (imageDirSize > IMAGE_CACHE_SIZE) {
                    StorageUtils.checkDeletePercentageContent(DIR_NAME, PERCENTAGE_TO_BE_DELETED);
                    imageDirSize = StorageUtils.getFileSize(DIR_NAME);
                }
            }
            FileProperites fileProperites = new FileProperites();
            if (!StorageUtils.putBitmapInDisk(bitmap, str, DIR_NAME, fileProperites).booleanValue()) {
                return new CacheResult(3);
            }
            imageDirSize += fileProperites.getSize();
            return new CacheResult(0);
        }

        public Bitmap getBitmap(String str) {
            return StorageUtils.getBitmapFromDisk(str, DIR_NAME);
        }
    }

    Bitmap getBitmap(String str);

    void getBitmap(String str, OnCacheOpFinishedListner onCacheOpFinishedListner);

    CacheResult putBitmapInCache(String str, Bitmap bitmap);
}
