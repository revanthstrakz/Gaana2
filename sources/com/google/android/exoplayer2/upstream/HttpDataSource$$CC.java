package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.util.Util;

public abstract /* synthetic */ class HttpDataSource$$CC {
    public static void $$triggerInterfaceInit() {
        Predicate predicate = HttpDataSource.REJECT_PAYWALL_TYPES;
    }

    static /* synthetic */ boolean lambda$static$0$HttpDataSource$$CC(String str) {
        str = Util.toLowerInvariant(str);
        return (TextUtils.isEmpty(str) || ((str.contains(MimeTypes.BASE_TYPE_TEXT) && !str.contains(MimeTypes.TEXT_VTT)) || str.contains("html") || str.contains("xml"))) ? false : true;
    }
}
