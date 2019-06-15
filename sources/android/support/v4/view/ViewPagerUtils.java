package android.support.v4.view;

import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;

public class ViewPagerUtils {
    public static View getCurrentView(ViewPager viewPager) {
        int currentItem = viewPager.getCurrentItem();
        for (int i = 0; i < viewPager.getChildCount(); i++) {
            View childAt = viewPager.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (!layoutParams.isDecor && currentItem == layoutParams.position) {
                return childAt;
            }
        }
        return null;
    }

    public static View getNextView(ViewPager viewPager) {
        int currentItem = viewPager.getCurrentItem();
        for (int i = 0; i < viewPager.getChildCount(); i++) {
            View childAt = viewPager.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (!layoutParams.isDecor && layoutParams.position == currentItem + 1) {
                return childAt;
            }
        }
        return null;
    }

    public static View getPreviousView(ViewPager viewPager) {
        int currentItem = viewPager.getCurrentItem();
        for (int i = 0; i < viewPager.getChildCount(); i++) {
            View childAt = viewPager.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (!layoutParams.isDecor && layoutParams.position == currentItem - 1) {
                return childAt;
            }
        }
        return null;
    }
}
