package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;
import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a = e.class)
public abstract class m {

    public static abstract class a {
        public abstract m build();

        public abstract a height(int i);

        public abstract a left(int i);

        public abstract a top(int i);

        public abstract a width(int i);

        public a locationOnScreenOfView(View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return left(iArr[0]).top(iArr[1]).height(view.getHeight()).width(view.getWidth());
        }
    }

    public abstract int height();

    public abstract int left();

    public abstract int top();

    public abstract int width();

    public static a builder() {
        return new a();
    }
}
