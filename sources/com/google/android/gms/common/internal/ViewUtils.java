package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ViewUtils {
    private ViewUtils() {
    }

    @KeepForSdk
    public static String getXmlAttributeString(String str, String str2, Context context, AttributeSet attributeSet, boolean z, boolean z2, String str3) {
        StringBuilder stringBuilder;
        str = attributeSet == null ? null : attributeSet.getAttributeValue(str, str2);
        if (str != null && str.startsWith("@string/") && z) {
            String substring = str.substring(8);
            String packageName = context.getPackageName();
            TypedValue typedValue = new TypedValue();
            try {
                Resources resources = context.getResources();
                StringBuilder stringBuilder2 = new StringBuilder((8 + String.valueOf(packageName).length()) + String.valueOf(substring).length());
                stringBuilder2.append(packageName);
                stringBuilder2.append(":string/");
                stringBuilder2.append(substring);
                resources.getValue(stringBuilder2.toString(), typedValue, true);
            } catch (NotFoundException unused) {
                stringBuilder = new StringBuilder((30 + String.valueOf(str2).length()) + String.valueOf(str).length());
                stringBuilder.append("Could not find resource for ");
                stringBuilder.append(str2);
                stringBuilder.append(": ");
                stringBuilder.append(str);
                Log.w(str3, stringBuilder.toString());
            }
            if (typedValue.string != null) {
                str = typedValue.string.toString();
            } else {
                String valueOf = String.valueOf(typedValue);
                StringBuilder stringBuilder3 = new StringBuilder((28 + String.valueOf(str2).length()) + String.valueOf(valueOf).length());
                stringBuilder3.append("Resource ");
                stringBuilder3.append(str2);
                stringBuilder3.append(" was not a string: ");
                stringBuilder3.append(valueOf);
                Log.w(str3, stringBuilder3.toString());
            }
        }
        if (z2 && str == null) {
            stringBuilder = new StringBuilder(33 + String.valueOf(str2).length());
            stringBuilder.append("Required XML attribute \"");
            stringBuilder.append(str2);
            stringBuilder.append("\" missing");
            Log.w(str3, stringBuilder.toString());
        }
        return str;
    }
}
