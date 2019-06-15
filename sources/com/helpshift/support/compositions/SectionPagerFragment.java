package com.helpshift.support.compositions;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.helpshift.e.e;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.support.FaqTagFilter;
import com.helpshift.support.Section;
import com.helpshift.support.c.b;
import com.helpshift.support.c.c;
import com.helpshift.support.fragments.MainFragment;
import com.helpshift.support.fragments.SupportFragment;
import com.helpshift.util.v;
import java.util.ArrayList;
import java.util.List;

public class SectionPagerFragment extends MainFragment implements b {
    private TabLayout a;
    private FrameLayout b;
    private int d = 0;

    public boolean b() {
        return true;
    }

    public static SectionPagerFragment a(Bundle bundle) {
        SectionPagerFragment sectionPagerFragment = new SectionPagerFragment();
        sectionPagerFragment.setArguments(bundle);
        return sectionPagerFragment;
    }

    public c a() {
        return ((b) getParentFragment()).a();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__section_pager_fragment, viewGroup, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.d = (int) v.a(context, 48.0f);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ArrayList parcelableArrayList = getArguments().getParcelableArrayList("sections");
        ViewPager viewPager = (ViewPager) view.findViewById(f.section_pager);
        viewPager.setAdapter(new a(getChildFragmentManager(), parcelableArrayList, (FaqTagFilter) getArguments().getSerializable("withTagsMatching")));
        this.a = (TabLayout) view.findViewById(f.pager_tabs);
        this.a.getChildAt(0).setPadding(this.d, 0, this.d, 0);
        this.a.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(a(parcelableArrayList, getArguments().getString("sectionPublishId")));
        this.b = (FrameLayout) view.findViewById(f.view_pager_container);
    }

    private int a(List<Section> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (((Section) list.get(i)).a().equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public void onResume() {
        super.onResume();
        a(false);
        c();
    }

    public void onStop() {
        a(true);
        super.onStop();
    }

    private void a(boolean z) {
        SupportFragment a = com.helpshift.support.util.c.a((Fragment) this);
        if (a != null) {
            a.c(z);
        }
    }

    private void c() {
        if (VERSION.SDK_INT >= 21) {
            this.a.setElevation(v.a(getContext(), 4.0f));
        } else {
            this.b.setForeground(getResources().getDrawable(e.hs__actionbar_compat_shadow));
        }
    }
}
