package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.b.i;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;

public class SectionTitleViews extends BaseItemView {
    private a dynamicView;
    private boolean mIsheading;

    public SectionTitleViews(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public SectionTitleViews(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public SectionTitleViews(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.dynamicView = aVar;
    }

    public void setDynamicView(a aVar) {
        this.dynamicView = aVar;
    }

    public void setHeading(boolean z) {
        this.mIsheading = z;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (this.dynamicView.m().equals(DynamicViewType.section_heading.name())) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.txt_home_section_header, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.txt_home_section_header_small, viewGroup, false);
        }
        ((TextView) inflate.findViewById(R.id.txt_section_header)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        ((TextView) inflate.findViewById(R.id.txt_section_header)).setTypeface(((TextView) inflate.findViewById(R.id.txt_section_header)).getTypeface(), 1);
        return new ItemAdViewHolder(inflate);
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        ((TextView) viewHolder.itemView.findViewById(R.id.txt_section_header)).setText(this.dynamicView.k());
        if (i == 0) {
            viewHolder.itemView.findViewById(R.id.horizontal_line).setVisibility(8);
        } else if (i == 1 && this.dynamicView.m().equals(DynamicViewType.section_heading_occasion.name())) {
            viewHolder.itemView.findViewById(R.id.horizontal_line).setVisibility(4);
        } else {
            viewHolder.itemView.findViewById(R.id.horizontal_line).setVisibility(0);
        }
        if (this.mIsheading) {
            viewHolder.itemView.setPadding(0, ((int) this.mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_large)) * 3, 0, 0);
        }
        return viewHolder.itemView;
    }
}
