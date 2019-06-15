package com.gaana.view.prescreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.constants.c.c;
import com.fragments.BaseGaanaFragment;
import com.fragments.PreScreenFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.PreScreens.PreScreen;
import com.gaana.models.RecentSearches;
import com.gaana.view.BaseItemView;
import com.i.i;
import com.library.controls.RoundedCornerImageView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.u;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.List;

public class SquareCardView extends BaseItemView implements a, b<Object> {
    private final int mIntialPosition;
    private final PreScreen mPreScreens;

    public static class SquareCardViewHolder extends ViewHolder {
        public final RoundedCornerImageView mArtworkBg;
        private final ImageView mPlayIcon;
        private final TextView mTopHeading;

        public SquareCardViewHolder(View view) {
            super(view);
            this.mArtworkBg = (RoundedCornerImageView) view.findViewById(R.id.square_card_bg);
            this.mTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.mPlayIcon = (ImageView) view.findViewById(R.id.play_icon);
        }
    }

    public SquareCardView(Context context, BaseGaanaFragment baseGaanaFragment, PreScreen preScreen, int i) {
        super(context, baseGaanaFragment);
        this.mPreScreens = preScreen;
        this.mIntialPosition = i;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SquareCardViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_square_card, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.page_left_right_margin);
        int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.view_square_card_size);
        SquareCardViewHolder squareCardViewHolder = (SquareCardViewHolder) viewHolder;
        Item item = (Item) this.mPreScreens.getItems().get(i - this.mIntialPosition);
        if (item.getEntityType().equalsIgnoreCase(c.l)) {
            int indexForRecentlyPlayedTrack = getIndexForRecentlyPlayedTrack(item) - 1;
            RecentSearches f = GaanaSearchManager.a().f();
            if (indexForRecentlyPlayedTrack < 0 || f == null || f.getRecentSearchTrackItems() == null || indexForRecentlyPlayedTrack >= f.getRecentSearchTrackItems().size()) {
                viewHolder.itemView.setPadding(0, 0, 0, 0);
                squareCardViewHolder.itemView.getLayoutParams().height = 0;
            } else {
                AutoComplete autoComplete = (AutoComplete) f.getRecentSearchTrackItems().get(indexForRecentlyPlayedTrack);
                String f2 = Util.f(this.mContext, autoComplete.getArtwork());
                if (f2 != null && f2.contains("80x80")) {
                    f2 = f2.replace("80x80", "480x480");
                } else if (f2 != null && f2.contains("175x175")) {
                    f2 = f2.replace("175x175", "480x480");
                }
                squareCardViewHolder.mArtworkBg.bindImage(f2);
                squareCardViewHolder.mTopHeading.setText(autoComplete.getTitle());
                squareCardViewHolder.itemView.getLayoutParams().height = dimension2 + dimension;
                viewHolder.itemView.setPadding(0, dimension, 0, 0);
                item.setEntityId(autoComplete.getBusinessObjectId());
                item.setName(autoComplete.getTitle());
            }
        } else {
            squareCardViewHolder.mArtworkBg.bindImage(Util.f(this.mContext, item.getAtw()));
            squareCardViewHolder.mTopHeading.setText(item.getName());
            viewHolder.itemView.setPadding(0, dimension, 0, 0);
            squareCardViewHolder.itemView.getLayoutParams().height = dimension2 + dimension;
        }
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setTag(item);
        squareCardViewHolder.mPlayIcon.setOnClickListener(this);
        squareCardViewHolder.mPlayIcon.setTag(item);
        return viewHolder.itemView;
    }

    public void onClick(View view) {
        if (Util.j(this.mContext)) {
            Item item = (Item) view.getTag();
            if (item.getEntityType().equals(c.j)) {
                CharSequence charSequence = null;
                List entityInfo = item.getEntityInfo();
                if (entityInfo != null) {
                    int size = entityInfo.size();
                    int i = 0;
                    while (i < size) {
                        if (((EntityInfo) entityInfo.get(i)).getKey().equals("url")) {
                            try {
                                charSequence = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                                break;
                            } catch (Exception unused) {
                            }
                        } else {
                            i++;
                        }
                    }
                }
                if (!TextUtils.isEmpty(charSequence)) {
                    if (view.getId() == R.id.play_icon) {
                        u.a().a("ForYou", "MFY Mixes", "Play");
                        fetchAndPlay(charSequence, false);
                    } else {
                        u.a().a("ForYou", "MFY Mixes", "Click");
                        openDetailGridFragment(item.getName(), charSequence, false);
                    }
                }
            } else if (item.getEntityType().equals(c.l)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://rec.gaana.com/recommendation/recommendedTracks/");
                stringBuilder.append(item.getBusinessObjId());
                String stringBuilder2 = stringBuilder.toString();
                if (view.getId() == R.id.play_icon) {
                    u.a().a("ForYou", "Search Mixes", "Play");
                    fetchAndPlay(stringBuilder2, true);
                } else {
                    openDetailGridFragment(item.getName(), stringBuilder2, true);
                    u.a().a("ForYou", "Search Mixes", "Click");
                }
            }
            return;
        }
        ap.a().f(this.mContext);
    }

    private int getIndexForRecentlyPlayedTrack(Item item) {
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("index")) {
                    return ((Double) ((EntityInfo) arrayList.get(i)).getValue()).intValue();
                }
            }
        }
        return -1;
    }

    private void openDetailGridFragment(String str, String str2, boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(1440);
        uRLManager.a(str2);
        uRLManager.a(z ^ 1);
        uRLManager.a(z ? BusinessObjectType.Tracks : BusinessObjectType.GenericItems);
        ((PreScreenFragment) this.mFragment).a(uRLManager, str, PAGE_SORCE_NAME.FOR_YOU.name(), this.mPreScreens.getGaSourceName());
    }

    private void fetchAndPlay(String str, boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(1440);
        uRLManager.a(str);
        uRLManager.a(z ^ 1);
        uRLManager.a(z ? BusinessObjectType.Tracks : BusinessObjectType.GenericItems);
        i.a().a(uRLManager, this.mFragment.toString(), (b) this, (a) this);
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.getting_details));
    }

    public void onResponse(Object obj) {
        playAll((BusinessObject) obj);
        ((BaseActivity) this.mContext).hideProgressDialog();
    }

    public void onErrorResponse(VolleyError volleyError) {
        ((BaseActivity) this.mContext).hideProgressDialog();
    }

    public void playAll(BusinessObject businessObject) {
        if (businessObject != null && businessObject.getArrListBusinessObj() != null && !businessObject.getArrListBusinessObj().isEmpty()) {
            PlayerManager.a(this.mContext).a(this.mContext, businessObject.getArrListBusinessObj(), this.mPreScreens.getUid(), PAGE_SORCE_NAME.FOR_YOU.name(), this.mPreScreens.getGaSourceName());
        }
    }
}
