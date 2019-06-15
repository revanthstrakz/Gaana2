package com.comscore.utils;

import java.io.File;
import java.io.FilenameFilter;

final class b implements FilenameFilter {
    b() {
    }

    public boolean accept(File file, String str) {
        return str.startsWith(Constants.CACHE_FILENAME);
    }
}
