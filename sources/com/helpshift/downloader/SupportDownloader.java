package com.helpshift.downloader;

public interface SupportDownloader {

    public enum StorageDirType {
        INTERNAL_ONLY,
        EXTERNAL_ONLY,
        EXTERNAL_OR_INTERNAL
    }

    void a(String str, StorageDirType storageDirType, a aVar);
}
