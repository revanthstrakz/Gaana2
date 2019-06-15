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
import com.helpshift.e.k;
import com.helpshift.support.Faq;
import com.helpshift.support.a.d;
import com.helpshift.support.c.e;
import java.util.List;

public class SearchResultFragment extends MainFragment {
    e a;
    RecyclerView b;
    private OnClickListener d;
    private OnClickListener e;

    public boolean b() {
        return true;
    }

    public static SearchResultFragment a(Bundle bundle, e eVar) {
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        searchResultFragment.setArguments(bundle);
        searchResultFragment.a = eVar;
        return searchResultFragment;
    }

    public void a(e eVar) {
        this.a = eVar;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__search_result_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = (RecyclerView) view.findViewById(f.search_result);
        this.b.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.d = new OnClickListener() {
            public void onClick(View view) {
                String str = (String) view.getTag();
                Faq a = ((d) SearchResultFragment.this.b.getAdapter()).a(str);
                SearchResultFragment.this.a.a(str, a != null ? a.h : null);
            }
        };
        this.e = new OnClickListener() {
            public void onClick(View view) {
                SearchResultFragment.this.a.c();
            }
        };
    }

    public void onResume() {
        super.onResume();
        b(getString(k.hs__search_result_title));
        a();
    }

    private void a() {
        List parcelableArrayList = getArguments().getParcelableArrayList("search_fragment_results");
        if (parcelableArrayList != null && parcelableArrayList.size() > 3) {
            parcelableArrayList = parcelableArrayList.subList(0, 3);
        }
        this.b.setAdapter(new d(parcelableArrayList, this.d, this.e));
    }
}
