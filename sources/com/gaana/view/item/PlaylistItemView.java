package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.PartyFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.library.controls.CrossFadeImageView;
import java.util.List;

public class PlaylistItemView extends BaseItemView {
    private String mSourceName;

    public PlaylistItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_item_playlist;
        ((BaseActivity) this.mContext).currentItem = "Playlist";
    }

    public void setSourceName(String str) {
        this.mSourceName = str;
    }

    public View getPopulatedViewForLastHeard(ViewHolder viewHolder, List<Item> list, int i) {
        ((CrossFadeImageView) viewHolder.itemView.findViewById(R.id.imgProductIcon)).bindImage(((Item) list.get(i)).getArtwork(), true);
        return viewHolder.itemView;
    }

    public View getPopulatedView(ViewHolder viewHolder, Playlist playlist) {
        View view = viewHolder.itemView;
        view.setOnClickListener(this);
        view.setTag(playlist);
        ((CrossFadeImageView) view.findViewById(R.id.imgProductIcon)).bindImage(playlist.getArtwork(), true);
        ((TextView) view.findViewById(R.id.tvTopHeading)).setText(playlist.getName());
        return viewHolder.itemView;
    }

    public void onClick(View view) {
        super.onClick(view);
        BusinessObject businessObject = (Playlist) view.getTag();
        this.mBusinessObject = (BusinessObject) view.getTag();
        if (TextUtils.isEmpty(businessObject.getChannelPageAdCode())) {
            Constants.i = false;
            Constants.j = "";
        } else {
            Constants.i = true;
            Constants.j = businessObject.getChannelPageAdCode();
        }
        if (this.mFragment instanceof PartyFragment) {
            this.mListingComponents = Constants.h();
            this.mListingComponents.a(businessObject);
            populateJukePlaylistListing(businessObject, this.mSourceName);
            return;
        }
        BaseActivity baseActivity = (BaseActivity) this.mContext;
        String str = ((BaseActivity) this.mContext).currentScreen;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Playlist Detail : ");
        stringBuilder.append(businessObject.getEnglishName());
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
        stringBuilder3.append(" - ");
        stringBuilder3.append(((BaseActivity) this.mContext).currentFavpage);
        stringBuilder3.append(" - Playlist Detail");
        baseActivity.sendGAEvent(str, stringBuilder2, stringBuilder3.toString());
        this.mListingComponents = Constants.e();
        this.mListingComponents.a(businessObject);
        if (businessObject.isGaanaSpecial()) {
            populateSpecialGaanaListing(businessObject);
        } else {
            populatePlaylistListing(businessObject);
        }
    }
}
