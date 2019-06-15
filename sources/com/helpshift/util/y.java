package com.helpshift.util;

import android.content.Context;
import android.content.res.XmlResourceParser;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class y {
    public static int a(Context context) {
        int a = a(context, "AndroidManifest.xml", MimeTypes.BASE_TYPE_APPLICATION, "logo");
        return a == 0 ? context.getApplicationInfo().icon : a;
    }

    private static int a(Context context, String str, String str2, String str3) {
        int i;
        Throwable e;
        try {
            XmlResourceParser openXmlResourceParser = context.createPackageContext(context.getApplicationInfo().packageName, 0).getAssets().openXmlResourceParser(str);
            i = 0;
            for (int eventType = openXmlResourceParser.getEventType(); eventType != 1; eventType = openXmlResourceParser.nextToken()) {
                if (eventType == 2) {
                    try {
                        if (str2.equals(openXmlResourceParser.getName())) {
                            for (eventType = openXmlResourceParser.getAttributeCount() - 1; eventType >= 0; eventType--) {
                                if (str3.equals(openXmlResourceParser.getAttributeName(eventType))) {
                                    i = openXmlResourceParser.getAttributeResourceValue(eventType, 0);
                                    break;
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        ThrowableExtension.printStackTrace(e);
                        return i;
                    }
                }
            }
        } catch (Exception e3) {
            e = e3;
            i = 0;
            ThrowableExtension.printStackTrace(e);
            return i;
        }
        return i;
    }
}
