package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.comscore.android.id.IdHelperAndroid;
import java.util.Locale;

public class az extends ak {

    static class a extends al {
        protected int l;
        protected String m;
        protected int n;
        protected String[] o;
        int p;

        public a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, @NonNull String str3, @NonNull String str4, @Nullable ba baVar) {
            String[] strArr = new String[]{IdHelperAndroid.NO_ID_AVAILABLE};
            this(i, i2, i3, i4, i5, i6, i7, i8, str, str2, str3, str4, 12, 0, Integer.MAX_VALUE, "#ff000000", strArr, baVar);
        }

        public a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, @NonNull String str3, @NonNull String str4, int i9, int i10, int i11, @NonNull String str5, String[] strArr, @Nullable ba baVar) {
            Object obj = strArr;
            super(i, i2, i3, i4, i5, i6, i7, i8, str, str2, str3, str4, baVar);
            this.l = i9;
            this.m = str5.length() == 0 ? "#ff000000" : str5;
            this.n = i11;
            int min = Math.min(obj.length, 4);
            this.o = new String[min];
            this.p = i10;
            System.arraycopy(obj, 0, this.o, 0, min);
        }

        public final int h() {
            return this.l;
        }

        public final String i() {
            return this.m.toLowerCase(Locale.US);
        }

        public final String[] j() {
            return this.o;
        }

        public final String e() {
            return this.j.toLowerCase(Locale.US);
        }
    }

    public az(String str, String str2, al alVar, String str3) {
        super(str, str2, "TEXT", alVar);
        this.e = str3;
    }

    public az(String str, String str2, String str3, al alVar, String str4) {
        super(str, str2, str3, alVar);
        this.e = str4;
    }
}
