package com.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import com.gaana.R;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.utilities.Util;

public class CardRelativeLayout extends RelativeLayout {
    public CardRelativeLayout(Context context) {
        super(context);
    }

    public CardRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CardRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public CardRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (size == 0 && size2 == 0) {
            super.onMeasure(i, i2);
            i = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(i, i);
            return;
        }
        if (String.valueOf(findViewById(R.id.cardView).getTag()).equals("PlayerV4")) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
        } else {
            String k = Util.k();
            i = Math.min(Integer.parseInt(k.split(AvidJSONUtil.KEY_X)[1]), (Integer.parseInt(k.split(AvidJSONUtil.KEY_X)[0]) - Util.b(193)) - Util.b((int) CallbackHandler.MSG_ROUTE_VOLUME_CHANGED));
            if (VERSION.SDK_INT < 21) {
                if (i > 500) {
                    i = 500;
                } else if (i < 200) {
                    i = Util.b((int) MoEHelperUtils.BASELINE_SCREEN_DPI);
                }
            }
            super.onMeasure(MeasureSpec.makeMeasureSpec(i, 1073741824), MeasureSpec.makeMeasureSpec(i + Util.b(45), 1073741824));
        }
    }
}
