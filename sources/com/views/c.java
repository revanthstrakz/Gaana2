package com.views;

import android.os.Build.VERSION;
import android.os.Handler;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import com.gaana.R;
import com.gaana.view.CustomBottomNavigationView;
import com.managers.an;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class c {
    private b a;

    public interface b {
        void onBottomMenuLongClick();
    }

    private final class a implements Runnable {
        private final WeakReference<CustomBottomNavigationView> b;

        protected a(CustomBottomNavigationView customBottomNavigationView) {
            this.b = new WeakReference(customBottomNavigationView);
        }

        public void run() {
            CustomBottomNavigationView customBottomNavigationView = (CustomBottomNavigationView) this.b.get();
            if (customBottomNavigationView != null) {
                c.this.b(customBottomNavigationView);
            }
        }
    }

    private void a(BottomNavigationItemView bottomNavigationItemView) {
    }

    public void a(CustomBottomNavigationView customBottomNavigationView) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) customBottomNavigationView.getChildAt(0);
        try {
            Field declaredField = bottomNavigationMenuView.getClass().getDeclaredField("mShiftingMode");
            declaredField.setAccessible(true);
            declaredField.setBoolean(bottomNavigationMenuView, false);
            declaredField.setAccessible(false);
            for (int i = 0; i < bottomNavigationMenuView.getChildCount(); i++) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) bottomNavigationMenuView.getChildAt(i);
                bottomNavigationItemView.setShiftingMode(false);
                bottomNavigationItemView.setChecked(bottomNavigationItemView.getItemData().isChecked());
                a(bottomNavigationItemView);
                b(bottomNavigationItemView);
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    public void a(CustomBottomNavigationView customBottomNavigationView, int i) {
        customBottomNavigationView.getMenu().getItem(i).setChecked(true);
        customBottomNavigationView.setSelectedPosition(i);
        if (VERSION.SDK_INT > 19) {
            b(customBottomNavigationView);
        } else {
            new Handler().postDelayed(new a(customBottomNavigationView), 500);
        }
        if (an.a().b != -1 && an.a().a != -1) {
            an.a().a(an.a().b);
            an.a().a(an.a().a);
        }
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    private void b(BottomNavigationItemView bottomNavigationItemView) {
        if (bottomNavigationItemView.getId() == R.id.action_search) {
            bottomNavigationItemView.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    c.this.a.onBottomMenuLongClick();
                    return true;
                }
            });
        }
    }

    public void b(CustomBottomNavigationView customBottomNavigationView) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) customBottomNavigationView.getChildAt(0);
        int childCount = bottomNavigationMenuView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) bottomNavigationMenuView.getChildAt(i);
            try {
                Field declaredField = bottomNavigationItemView.getClass().getDeclaredField("mSmallLabel");
                declaredField.setAccessible(true);
                ((TextView) declaredField.get(bottomNavigationItemView)).setVisibility(0);
                declaredField.setAccessible(false);
                declaredField = bottomNavigationItemView.getClass().getDeclaredField("mLargeLabel");
                declaredField.setAccessible(true);
                ((TextView) declaredField.get(bottomNavigationItemView)).setVisibility(0);
                declaredField.setAccessible(false);
                b(bottomNavigationItemView);
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e2) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e2);
            }
        }
    }
}
