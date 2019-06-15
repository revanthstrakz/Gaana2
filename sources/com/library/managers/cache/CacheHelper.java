package com.library.managers.cache;

import android.graphics.Bitmap;

public class CacheHelper {
    public static final int CACHE_RESPONSE_IO_ERROR = 3;
    public static final int CACHE_RESPONSE_RESULT_OK = 0;
    public static final int CACHE_RESPONSE_STORAGE_NOT_MOUNTED = 2;
    public static final int CACHE_RESPONSE_STORGAE_INSUFFICIENT = 1;

    public interface OnCacheOpFinishedListner {
        void onCacheOpFinishedListner(CacheResult cacheResult, Bitmap bitmap);
    }
}
