package com.f;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaana.R;
import java.util.List;

public class b extends Adapter<ViewHolder> {
    private List<d> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int[] g;
    private int[] h;

    public class a extends ViewHolder implements OnClickListener {
        private LinearLayout b;
        private ImageView c;
        private TextView d;

        a(View view) {
            super(view);
            this.b = (LinearLayout) view.findViewById(R.id.equalizer_list_item_container);
            this.c = (ImageView) view.findViewById(R.id.equalizer_list_item_icon);
            this.d = (TextView) view.findViewById(R.id.equalizer_list_item_name);
            this.b.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (view.getId() != this.b.getId()) {
                return;
            }
            if (((d) b.this.a.get(getAdapterPosition())).c()) {
                a();
            } else {
                a(getAdapterPosition());
            }
        }

        private void a() {
            int adapterPosition = getAdapterPosition();
            b.this.b = -1;
            ((d) b.this.a.get(adapterPosition)).a(false);
            c.a().b();
            b.this.notifyItemChanged(adapterPosition);
        }

        private void a(int i) {
            int b = b.this.b;
            b.this.b = i;
            c.a().a((short) ((d) b.this.a.get(b.this.b)).a(), "onListItemClickedOfEqualizer()");
            if (b != -1) {
                ((d) b.this.a.get(b)).a(false);
            }
            ((d) b.this.a.get(b.this.b)).a(true);
            if (b != -1) {
                b.this.notifyItemChanged(b);
            }
            b.this.notifyItemChanged(b.this.b);
        }

        public void a(d dVar) {
            if (dVar.c()) {
                this.d.setText(dVar.b());
                this.d.setTextColor(ContextCompat.getColor(this.d.getContext(), b.this.f));
                this.b.setBackgroundResource(b.this.d);
                this.c.setImageResource(b.this.h[dVar.d()]);
                return;
            }
            this.d.setText(dVar.b());
            this.d.setTextColor(ContextCompat.getColor(this.d.getContext(), b.this.e));
            this.b.setBackgroundResource(b.this.c);
            this.c.setImageResource(b.this.g[dVar.d()]);
        }
    }

    public b(List<d> list, int i, int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2) {
        this.a = list;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = iArr;
        this.h = iArr2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.equalizer_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ((a) viewHolder).a((d) this.a.get(i));
    }

    public int getItemCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }
}
