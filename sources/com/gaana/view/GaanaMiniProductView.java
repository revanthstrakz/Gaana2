package com.gaana.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.login.GaanaMiniSubDetails;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.view.item.BaseItemView;
import com.library.controls.CrossFadeImageView;
import java.text.SimpleDateFormat;

public class GaanaMiniProductView extends BaseItemView implements OnClickListener {
    public CrossFadeImageView mImgProductIcon;
    private int mLayoutId = R.layout.view_gaana_mini_product;
    public TextView mTxtDescription;
    public TextView mTxtExpiryInfo;
    public TextView mTxtInfo;
    private View mView;

    public GaanaMiniProductView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        init();
    }

    private void init() {
        this.mView = inflate(getContext(), this.mLayoutId, this);
        this.mView.setOnClickListener(this);
        this.mTxtInfo = (TextView) this.mView.findViewById(R.id.product_info);
        this.mTxtDescription = (TextView) this.mView.findViewById(R.id.product_description);
        this.mTxtExpiryInfo = (TextView) this.mView.findViewById(R.id.txt_expire_description);
        this.mImgProductIcon = (CrossFadeImageView) this.mView.findViewById(R.id.imgProductIcon);
    }

    public View populate(GaanaMiniSubDetails gaanaMiniSubDetails) {
        this.mTxtInfo.setText(gaanaMiniSubDetails.getTitle());
        Long valueOf = Long.valueOf(1000 * Long.parseLong(gaanaMiniSubDetails.getValidUpto()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getContext().getResources().getString(R.string.expiring_on));
        stringBuilder.append(" ");
        stringBuilder.append(SimpleDateFormat.getDateInstance().format(valueOf));
        this.mTxtExpiryInfo.setText(stringBuilder.toString());
        this.mImgProductIcon.bindImage(gaanaMiniSubDetails.getArtwork());
        this.mView.setTag(gaanaMiniSubDetails);
        return this.mView;
    }

    public void onClick(View view) {
        GaanaMiniSubDetails gaanaMiniSubDetails = (GaanaMiniSubDetails) view.getTag();
        if (gaanaMiniSubDetails != null) {
            Item item = new Item();
            item.setName(gaanaMiniSubDetails.getTitle());
            item.setEntityId(gaanaMiniSubDetails.getEntityId());
            item.setEntityType(gaanaMiniSubDetails.getEntityType());
            item.setArtwork(gaanaMiniSubDetails.getArtwork());
            if (gaanaMiniSubDetails.getEntityType().equals(c.a)) {
                BusinessObject businessObject = (Playlist) populatePlaylistClicked(item);
                this.mListingComponents = Constants.e();
                this.mListingComponents.a(businessObject);
                populatePlaylistListing(businessObject);
            } else if (gaanaMiniSubDetails.getEntityType().equals(c.d)) {
                Artist artist = (Artist) populateArtistClicked(item);
                this.mListingComponents = Constants.a("", artist.isLocalMedia());
                this.mAppState.setListingComponents(this.mListingComponents);
                populateArtistListing(artist);
            } else if (gaanaMiniSubDetails.getEntityType().equals(c.b)) {
                populateAlbumListing((Album) populateAlbumClicked(item));
            }
        }
    }
}
