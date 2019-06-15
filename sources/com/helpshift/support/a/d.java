package com.helpshift.support.a;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.support.ContactUsFilter;
import com.helpshift.support.ContactUsFilter.LOCATION;
import com.helpshift.support.Faq;
import com.helpshift.util.v;
import java.util.ArrayList;
import java.util.List;

public class d extends Adapter<ViewHolder> {
    private List<Faq> a;
    private OnClickListener b;
    private OnClickListener c;

    protected static class a extends ViewHolder {
        LinearLayout a;
        Button b;

        public a(LinearLayout linearLayout) {
            super(linearLayout);
            this.a = linearLayout;
            this.b = (Button) linearLayout.findViewById(f.send_anyway_button);
        }
    }

    protected static class b extends ViewHolder {
        public b(TextView textView) {
            super(textView);
        }
    }

    protected static class c extends ViewHolder {
        TextView a;

        public c(TextView textView) {
            super(textView);
            this.a = textView;
        }
    }

    public d(List<Faq> list, OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.a = list;
        this.b = onClickListener;
        this.c = onClickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__search_result_header, viewGroup, false));
            case 2:
                return new a((LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__search_result_footer, viewGroup, false));
            default:
                return new c((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs_simple_recycler_view_item, viewGroup, false));
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof a) {
            a((a) viewHolder);
        } else if (viewHolder instanceof c) {
            a((c) viewHolder, i);
        }
    }

    private void a(a aVar) {
        if (ContactUsFilter.a(LOCATION.SEARCH_FOOTER)) {
            aVar.a.setVisibility(0);
            aVar.b.setOnClickListener(this.c);
            return;
        }
        aVar.a.setVisibility(8);
    }

    private void a(c cVar, int i) {
        c cVar2 = cVar;
        Faq faq = (Faq) this.a.get(i - 1);
        ArrayList<String> arrayList = faq.h;
        String str = faq.b;
        if (arrayList == null || arrayList.size() <= 0) {
            cVar2.a.setText(str);
        } else {
            int a = v.a(cVar2.a.getContext(), com.helpshift.e.b.hs__searchHighlightColor);
            SpannableString spannableString = new SpannableString(str);
            String toLowerCase;
            int indexOf;
            if (str.equals(com.helpshift.support.util.d.a(str))) {
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
                    String a2 = com.helpshift.support.util.d.a(stringBuilder.toString());
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
            cVar2.a.setText(spannableString);
        }
        cVar2.a.setOnClickListener(this.b);
        cVar2.a.setTag(faq.c);
    }

    public Faq a(String str) {
        if (this.a != null) {
            for (Faq faq : this.a) {
                if (faq.c.equals(str)) {
                    return faq;
                }
            }
        }
        return null;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        return a(i) ? 2 : 3;
    }

    public long getItemId(int i) {
        if (i == 0) {
            return 1;
        }
        if (a(i)) {
            return 2;
        }
        return Long.valueOf(((Faq) this.a.get(i - 1)).c).longValue();
    }

    public int getItemCount() {
        return this.a.size() + 2;
    }

    private boolean a(int i) {
        return i == getItemCount() - 1;
    }
}
