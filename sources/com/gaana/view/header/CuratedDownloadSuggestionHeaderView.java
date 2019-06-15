package com.gaana.view.header;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.managers.u;
import com.services.d;

public class CuratedDownloadSuggestionHeaderView extends BaseItemView implements OnClickListener {
    private BaseGaanaFragment baseGaana = null;
    public boolean isVisible = false;

    public CuratedDownloadSuggestionHeaderView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.baseGaana = baseGaanaFragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemAdViewHolder(this.mInflater.inflate(R.layout.curated_download_suggestion_header, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return populateView(i, viewHolder.itemView, new BusinessObject(), "", "");
    }

    public View populateView(int i, View view, BusinessObject businessObject, String str, String str2) {
        view.findViewById(R.id.cross_big_curated).setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.cross_big_curated) {
            ((GaanaActivity) this.mContext).onBackPressedHandling();
            d.a().a("PREFERENCE_CURATED_DOWNLOAD_CROSS_CLOSE", true, false);
            u.a().b("CuratedDownloadsPersonalized", "PopUpCrossClick");
        }
    }
}
