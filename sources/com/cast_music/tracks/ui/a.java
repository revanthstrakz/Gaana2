package com.cast_music.tracks.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.gaana.R;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.List;

public class a extends ArrayAdapter<MediaTrack> implements OnClickListener {
    private final List<MediaTrack> a;
    private final Context b;
    private int c = -1;

    private class a {
        private final TextView b;
        private final RadioButton c;

        private a(TextView textView, RadioButton radioButton) {
            this.b = textView;
            this.c = radioButton;
        }
    }

    public a(Context context, int i, List<MediaTrack> list, int i2) {
        super(context, i);
        this.b = context;
        this.a = new ArrayList();
        this.a.addAll(list);
        this.c = i2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        boolean z = false;
        if (view == null) {
            view = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.tracks_row_layout, viewGroup, false);
            aVar = new a((TextView) view.findViewById(R.id.text), (RadioButton) view.findViewById(R.id.radio));
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.c.setTag(Integer.valueOf(i));
        RadioButton a = aVar.c;
        if (this.c == i) {
            z = true;
        }
        a.setChecked(z);
        view.setOnClickListener(this);
        aVar.b.setText(((MediaTrack) this.a.get(i)).getName());
        return view;
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public void onClick(View view) {
        this.c = ((Integer) ((a) view.getTag()).c.getTag()).intValue();
        notifyDataSetChanged();
    }

    public MediaTrack a() {
        return this.c >= 0 ? (MediaTrack) this.a.get(this.c) : null;
    }
}
