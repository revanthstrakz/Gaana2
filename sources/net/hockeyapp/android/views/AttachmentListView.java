package net.hockeyapp.android.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import net.hockeyapp.android.d.d;

public class AttachmentListView extends ViewGroup {
    private int a;

    public AttachmentListView(Context context) {
        super(context);
    }

    public AttachmentListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ArrayList<Uri> getAttachments() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            arrayList.add(((AttachmentView) getChildAt(i)).getAttachmentUri());
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        if (MeasureSpec.getMode(i) == 0) {
            d.b("AttachmentListView", "Width is unspecified");
        }
        i = MeasureSpec.getSize(i);
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int i3 = 0;
        int paddingTop = getPaddingTop();
        int i4 = 0;
        int i5 = paddingLeft;
        paddingLeft = i4;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            AttachmentView attachmentView = (AttachmentView) childAt;
            int paddingTop2 = attachmentView.getPaddingTop() + attachmentView.getEffectiveMaxHeight();
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = childAt.getLayoutParams();
                childAt.measure(MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(paddingTop2, Integer.MIN_VALUE));
                int measuredWidth = childAt.getMeasuredWidth();
                paddingLeft = Math.max(paddingLeft, childAt.getMeasuredHeight() + layoutParams.height);
                if (i5 + measuredWidth > i) {
                    i5 = getPaddingLeft();
                    paddingTop += paddingLeft;
                }
                i5 += measuredWidth + layoutParams.width;
            }
            i3++;
            i4 = paddingTop2;
        }
        this.a = paddingLeft;
        if (MeasureSpec.getMode(i2) == 0) {
            i4 = (paddingTop + paddingLeft) + getPaddingBottom();
        } else if (MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            paddingTop += paddingLeft;
            if (getPaddingBottom() + paddingTop < i4) {
                i4 = paddingTop + getPaddingBottom();
            }
        }
        setMeasuredDimension(i, i4);
    }

    /* Access modifiers changed, original: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1, 1);
    }

    /* Access modifiers changed, original: protected */
    public boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        i3 -= i;
        i = getPaddingLeft();
        i2 = getPaddingTop();
        for (i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                childAt.invalidate();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                LayoutParams layoutParams = childAt.getLayoutParams();
                if (i + measuredWidth > i3) {
                    i = getPaddingLeft();
                    i2 += this.a;
                }
                childAt.layout(i, i2, i + measuredWidth, measuredHeight + i2);
                i += (measuredWidth + layoutParams.width) + ((AttachmentView) childAt).getGap();
            }
        }
    }
}
