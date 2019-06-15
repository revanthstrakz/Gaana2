package com.helpshift.support.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.support.Faq;
import com.helpshift.support.FaqTagFilter;
import com.helpshift.support.HSSearch.HS_SEARCH_OPTIONS;
import com.helpshift.support.c.b;
import com.helpshift.support.c.c;
import com.helpshift.support.d;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends MainFragment {
    d a;
    FaqTagFilter b;
    RecyclerView d;
    String e;
    private OnClickListener f;
    private OnClickListener g;
    private String h;
    private final Handler i = new Handler() {
        public void handleMessage(Message message) {
            if (message != null && message.getData() != null && message.obj != null) {
                String string = message.getData().getString("key_search_query");
                if (SearchFragment.this.e != null && SearchFragment.this.e.equals(string)) {
                    List list = (List) message.obj;
                    if (list == null) {
                        list = new ArrayList();
                    }
                    SearchFragment.this.a(list);
                }
            }
        }
    };

    private class a implements Runnable {
        private String b;
        private boolean c;
        private String d;
        private Handler e;

        public a(String str, boolean z, String str2, Handler handler) {
            this.b = str;
            this.c = z;
            this.d = str2;
            this.e = handler;
        }

        public void run() {
            Object a;
            if (TextUtils.isEmpty(this.b) || (this.b.length() < 3 && !this.c)) {
                a = SearchFragment.this.a.a(SearchFragment.this.b);
            } else {
                a = SearchFragment.this.a.a(this.b, HS_SEARCH_OPTIONS.FULL_SEARCH, SearchFragment.this.b);
            }
            if (!TextUtils.isEmpty(this.d)) {
                ArrayList arrayList = new ArrayList();
                for (Faq faq : r0) {
                    if (faq.d.equals(this.d)) {
                        arrayList.add(faq);
                    }
                }
                a = arrayList;
            }
            Message message = new Message();
            message.obj = a;
            Bundle bundle = new Bundle();
            bundle.putString("key_search_query", this.b);
            message.setData(bundle);
            this.e.sendMessage(message);
        }
    }

    public boolean b() {
        return true;
    }

    public static SearchFragment a(Bundle bundle) {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    public c a() {
        return ((b) getParentFragment()).a();
    }

    public String c() {
        return this.e;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.b = (FaqTagFilter) bundle.getSerializable("withTagsMatching");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__search_fragment, viewGroup, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.a = new d(context);
        this.a.i();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.d = (RecyclerView) view.findViewById(f.search_list);
        this.d.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.f = new OnClickListener() {
            public void onClick(View view) {
                String str = (String) view.getTag();
                Faq a = ((com.helpshift.support.a.c) SearchFragment.this.d.getAdapter()).a(str);
                SearchFragment.this.a().a(str, a != null ? a.h : null);
            }
        };
        this.g = new OnClickListener() {
            public void onClick(View view) {
                SearchFragment.this.a().a(SearchFragment.this.e);
            }
        };
        if (getArguments() != null) {
            this.h = getArguments().getString("sectionPublishId");
        }
        a(this.e, this.h);
    }

    public void a(String str, String str2) {
        this.h = str2;
        if (this.d != null) {
            boolean z = false;
            String c = o.d().m().c("sdkLanguage");
            if (TextUtils.isEmpty(c)) {
                c = Locale.getDefault().getLanguage();
            }
            if (c.startsWith("zh") || c.equals("ja") || c.equals("ko")) {
                z = true;
            }
            boolean z2 = z;
            if (str == null) {
                str = "";
            } else {
                str = str.trim();
            }
            String str3 = str;
            this.e = str3;
            new Thread(new a(str3, z2, str2, this.i), "HS-search-query").start();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Performing search : Query : ");
            stringBuilder.append(this.e);
            l.a("Helpshift_SearchFrag", stringBuilder.toString());
        }
    }

    public int d() {
        com.helpshift.support.a.c cVar = (com.helpshift.support.a.c) this.d.getAdapter();
        return cVar != null ? cVar.getItemCount() - cVar.a() : -1;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(@NonNull List<Faq> list) {
        com.helpshift.support.a.c cVar = new com.helpshift.support.a.c(this.e, list, this.f, this.g);
        cVar.setHasStableIds(true);
        if (this.d.getAdapter() == null) {
            this.d.setAdapter(cVar);
        } else {
            this.d.swapAdapter(new com.helpshift.support.a.c(this.e, list, this.f, this.g), true);
        }
    }
}
