package com.helpshift.support.a;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.ContactUsFilter;
import com.helpshift.support.ContactUsFilter.LOCATION;
import com.helpshift.support.Faq;
import com.helpshift.support.util.d;
import com.helpshift.util.v;
import java.util.ArrayList;
import java.util.List;

public class c extends Adapter<ViewHolder> {
    private final String a;
    private List<Faq> b;
    private OnClickListener c;
    private OnClickListener d;
    private final int e = 1;

    private static class a extends ViewHolder {
        LinearLayout a;
        TextView b;
        Button c;
        TextView d;
        View e;

        a(LinearLayout linearLayout) {
            super(linearLayout);
            this.a = (LinearLayout) linearLayout.findViewById(f.contact_us_view);
            this.b = (TextView) linearLayout.findViewById(f.contact_us_hint_text);
            this.c = (Button) linearLayout.findViewById(f.report_issue);
            this.d = (TextView) linearLayout.findViewById(f.no_faqs_view);
            this.e = linearLayout.findViewById(f.search_list_footer_divider);
        }
    }

    private static class b extends ViewHolder {
        TextView a;

        public b(TextView textView) {
            super(textView);
            this.a = textView;
        }
    }

    public int a() {
        return 1;
    }

    public c(String str, List<Faq> list, OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.a = str;
        this.b = list;
        this.c = onClickListener;
        this.d = onClickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs_simple_recycler_view_item, viewGroup, false));
        }
        return new a((LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__search_list_footer, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (a(i)) {
            a((a) viewHolder);
        } else {
            a((b) viewHolder, i);
        }
    }

    private void a(a aVar) {
        Context context = aVar.c.getContext();
        String string = context.getResources().getString(k.hs__search_footer);
        String string2 = context.getResources().getString(k.hs__no_search_results_message);
        if (ContactUsFilter.a(LOCATION.SEARCH_FOOTER)) {
            if (getItemCount() == 1) {
                string = SearchIntents.EXTRA_QUERY;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" \"");
                stringBuilder.append(this.a);
                stringBuilder.append("\"");
                aVar.b.setText(string2.replaceFirst(string, stringBuilder.toString()));
                aVar.e.setVisibility(8);
            } else {
                aVar.e.setVisibility(0);
                aVar.b.setText(string);
            }
            aVar.a.setVisibility(0);
            aVar.d.setVisibility(8);
            aVar.c.setOnClickListener(this.d);
            return;
        }
        aVar.a.setVisibility(8);
        if (getItemCount() == 1) {
            aVar.d.setVisibility(0);
        } else {
            aVar.d.setVisibility(8);
        }
    }

    private void a(b bVar, int i) {
        b bVar2 = bVar;
        Faq faq = (Faq) this.b.get(i);
        ArrayList<String> arrayList = faq.h;
        String str = faq.b;
        if (arrayList == null || arrayList.size() <= 0) {
            bVar2.a.setText(str);
        } else {
            int a = v.a(bVar2.a.getContext(), com.helpshift.e.b.hs__searchHighlightColor);
            SpannableString spannableString = new SpannableString(str);
            String toLowerCase;
            int indexOf;
            if (str.equals(d.a(str))) {
                str = str.toLowerCase();
                for (String toLowerCase2 : arrayList) {
                    if (toLowerCase2.length() >= 3) {
                        for (indexOf = TextUtils.indexOf(str, toLowerCase2, 0); indexOf >= 0; indexOf = TextUtils.indexOf(str, toLowerCase2, indexOf + toLowerCase2.length())) {
                            spannableString.setSpan(new BackgroundColorSpan(a), indexOf, toLowerCase2.length() + indexOf, 33);
                        }
                    }
                }
            } else {
                int length = str.length();
                ArrayList arrayList2 = new ArrayList();
                String str2 = "";
                indexOf = 0;
                while (indexOf < length) {
                    char charAt = str.charAt(indexOf);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(charAt);
                    stringBuilder.append("");
                    String a2 = d.a(stringBuilder.toString());
                    String str3 = str2;
                    for (int i2 = 0; i2 < a2.length(); i2++) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str3);
                        stringBuilder2.append(a2.charAt(i2));
                        str3 = stringBuilder2.toString();
                        arrayList2.add(Integer.valueOf(indexOf));
                    }
                    indexOf++;
                    str2 = str3;
                }
                str = str2.toLowerCase();
                for (String toLowerCase22 : arrayList) {
                    toLowerCase22 = toLowerCase22.toLowerCase();
                    if (toLowerCase22.length() >= 3) {
                        for (int indexOf2 = TextUtils.indexOf(str, toLowerCase22, 0); indexOf2 >= 0; indexOf2 = TextUtils.indexOf(str, toLowerCase22, indexOf2 + toLowerCase22.length())) {
                            spannableString.setSpan(new BackgroundColorSpan(a), ((Integer) arrayList2.get(indexOf2)).intValue(), ((Integer) arrayList2.get((toLowerCase22.length() + indexOf2) - 1)).intValue() + 1, 33);
                        }
                    }
                }
            }
            bVar2.a.setText(spannableString);
        }
        bVar2.a.setOnClickListener(this.c);
        bVar2.a.setTag(faq.c);
    }

    public Faq a(String str) {
        if (this.b != null) {
            for (Faq faq : this.b) {
                if (faq.c.equals(str)) {
                    return faq;
                }
            }
        }
        return null;
    }

    public int getItemViewType(int i) {
        return a(i) ? 0 : 1;
    }

    public long getItemId(int i) {
        if (a(i)) {
            return 0;
        }
        return Long.valueOf(((Faq) this.b.get(i)).c).longValue();
    }

    public int getItemCount() {
        return this.b.size() + 1;
    }

    private boolean a(int i) {
        return i == getItemCount() - 1;
    }
}
