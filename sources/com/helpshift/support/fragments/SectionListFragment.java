package com.helpshift.support.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.support.FaqTagFilter;
import com.helpshift.support.a.e;
import com.helpshift.support.c.b;
import com.helpshift.support.c.c;
import java.util.ArrayList;

public class SectionListFragment extends MainFragment {
    public boolean b() {
        return false;
    }

    public static SectionListFragment a(@Nullable Bundle bundle) {
        SectionListFragment sectionListFragment = new SectionListFragment();
        sectionListFragment.setArguments(bundle);
        return sectionListFragment;
    }

    public c a() {
        return ((b) getParentFragment()).a();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__section_list_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        final ArrayList parcelableArrayList = getArguments().getParcelableArrayList("sections");
        final FaqTagFilter faqTagFilter = (FaqTagFilter) getArguments().getSerializable("withTagsMatching");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(f.section_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new e(parcelableArrayList, new OnClickListener() {
            public void onClick(View view) {
                String str = (String) view.getTag();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("sections", parcelableArrayList);
                bundle.putString("sectionPublishId", str);
                bundle.putSerializable("withTagsMatching", faqTagFilter);
                SectionListFragment.this.a().a(bundle);
            }
        }));
    }
}
