package com.gaana.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.constants.c.d;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.GaanaSearchManager;
import com.managers.af;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;

public class TrendingSearchAdapter extends Adapter<TrendingSearchItemViewHolder> {
    private ArrayList<Item> mArrListBusinessObj = null;
    private final Context mContext;
    private final BaseGaanaFragment mFragment;

    public class TrendingSearchItemViewHolder extends ViewHolder {
        private final View trendingDivider;
        public TextView trendingHeader;
        public TextView trendingSongname;

        public TrendingSearchItemViewHolder(View view) {
            super(view);
            this.trendingSongname = (TextView) view.findViewById(R.id.trendingSongName);
            this.trendingHeader = (TextView) view.findViewById(R.id.trendingHeader);
            this.trendingDivider = view.findViewById(R.id.trendingDivider);
            this.trendingHeader.setVisibility(8);
            this.trendingHeader.setTypeface(Typeface.DEFAULT_BOLD);
            view.setOnClickListener(new OnClickListener(TrendingSearchAdapter.this) {
                public void onClick(View view) {
                    int adapterPosition = TrendingSearchItemViewHolder.this.getAdapterPosition();
                    if (adapterPosition > -1 && adapterPosition < TrendingSearchAdapter.this.mArrListBusinessObj.size()) {
                        ((GaanaActivity) TrendingSearchAdapter.this.mContext).setCurrentSongSelectedView(view);
                        TrendingSearchAdapter.this.populateBusinessObject((Item) TrendingSearchAdapter.this.mArrListBusinessObj.get(adapterPosition));
                    }
                }
            });
        }
    }

    public TrendingSearchAdapter(Context context, BaseGaanaFragment baseGaanaFragment, ArrayList<Item> arrayList) {
        this.mArrListBusinessObj = arrayList;
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
    }

    public TrendingSearchItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TrendingSearchItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trending_search_view, null));
    }

    public void onBindViewHolder(TrendingSearchItemViewHolder trendingSearchItemViewHolder, int i) {
        TextView textView = trendingSearchItemViewHolder.trendingSongname;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(((Item) this.mArrListBusinessObj.get(i)).getName());
        textView.setText(stringBuilder.toString());
        if (i == 0) {
            trendingSearchItemViewHolder.trendingHeader.setVisibility(0);
            return;
        }
        trendingSearchItemViewHolder.trendingHeader.setVisibility(8);
        trendingSearchItemViewHolder.trendingDivider.setVisibility(8);
    }

    public int getItemCount() {
        return (this.mArrListBusinessObj == null || this.mArrListBusinessObj.size() <= 0) ? 0 : this.mArrListBusinessObj.size();
    }

    private void addToRecentSearch(BusinessObject businessObject, String str) {
        AutoComplete autoComplete = new AutoComplete(businessObject.getName(), "", Integer.valueOf(businessObject.getBusinessObjId()).intValue(), businessObject.getAtw());
        autoComplete.setBusinessObjType(businessObject.getBusinessObjType());
        autoComplete.setType(str);
        GaanaSearchManager.a().a(autoComplete);
    }

    /* Access modifiers changed, original: protected */
    public void populateBusinessObject(final Item item) {
        BusinessObject b;
        String entityType = item.getEntityType();
        String str = "";
        if (entityType.equals(c.a)) {
            b = Util.b(item);
            str = "Playlist";
            af.a(this.mContext, this.mFragment).a((int) R.id.playlistMenu, b);
        } else if (entityType.equals(c.b)) {
            b = Util.c(item);
            str = "Album";
            af.a(this.mContext, this.mFragment).a((int) R.id.albumMenu, b);
        } else if (entityType.equals(d.d) || entityType.equals(d.c)) {
            if (Constants.cY) {
                JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        JukeSessionManager.getInstance().stopJukeSession(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                    TrendingSearchAdapter.this.populateBusinessObject(item);
                                }
                            }
                        });
                    }
                });
                return;
            }
            b = Util.d(item);
            str = "Radio";
            af.a(this.mContext, this.mFragment).a((int) R.id.radioMenu, b);
        } else if (entityType.equals(c.d)) {
            b = Util.a(item);
            str = "Artist";
            af.a(this.mContext, this.mFragment).a((int) R.id.artistMenu, b);
        } else if (entityType.equals(c.c)) {
            b = Util.g(item);
            str = "Track";
            com.services.c.a(this.mContext).b(this.mContext, b, SOURCE_TYPE.SEARCH.ordinal());
        } else {
            b = null;
        }
        addToRecentSearch(b, str);
    }
}
