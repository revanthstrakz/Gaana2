package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.fragments.BaseGaanaFragment;
import com.fragments.PersonaDedicationFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.Tracks.Track;
import com.library.controls.CrossFadeImageView;
import com.managers.PlayerManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.models.PlayerTrack;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Map;

public class ShareableSongsView extends SongsItemView {
    private String pageTitle;

    public static class ShareableSongsHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public TextView imgShare;
        public TextView txtSubtitle;
        public TextView txtTitle;

        public ShareableSongsHolder(View view) {
            super(view);
            this.txtTitle = (TextView) view.findViewById(R.id.txt_title);
            this.txtSubtitle = (TextView) view.findViewById(R.id.txt_subtitle);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.img_artwork);
            this.imgShare = (TextView) view.findViewById(R.id.img_share);
        }
    }

    public ShareableSongsView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.pageTitle = "";
        this.mLayoutId = R.layout.view_shareable_songs;
    }

    public ShareableSongsView(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        super(context, baseGaanaFragment);
        this.pageTitle = "";
        this.mLayoutId = R.layout.view_shareable_songs;
    }

    public void setPageTitle(String str) {
        this.pageTitle = str;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        return getDataFilledView(viewHolder, businessObject);
    }

    public View getDataFilledView(ViewHolder viewHolder, BusinessObject businessObject) {
        ShareableSongsHolder shareableSongsHolder = (ShareableSongsHolder) viewHolder;
        this.mBusinessObject = businessObject;
        this.mView = shareableSongsHolder.itemView;
        String str = "";
        boolean z = false;
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            shareableSongsHolder.txtTitle.setText(item.getName());
            shareableSongsHolder.txtSubtitle.setText(getArtistNames(item));
            shareableSongsHolder.crossFadeImageView.bindImage(item.getArtwork());
            str = item.getEntityId();
            if (item.getEntityType().equals(c.c) && item.getEntityInfo() != null) {
                int i = 0;
                boolean z2 = i;
                while (i < item.getEntityInfo().size()) {
                    if (((EntityInfo) item.getEntityInfo().get(i)).getKey().equals("parental_warning")) {
                        z2 = Double.compare(((Double) ((EntityInfo) item.getEntityInfo().get(i)).getValue()).doubleValue(), 1.0d) == 0;
                    }
                    i++;
                }
                z = z2;
            }
        } else if (businessObject instanceof AutoComplete) {
            AutoComplete autoComplete = (AutoComplete) businessObject;
            shareableSongsHolder.txtTitle.setText(autoComplete.getTitle());
            shareableSongsHolder.txtSubtitle.setText(autoComplete.getSubtitle());
            if (!TextUtils.isEmpty(autoComplete.getArtwork())) {
                if (autoComplete.isLocalMedia()) {
                    shareableSongsHolder.crossFadeImageView.bindImageForLocalMedia(autoComplete.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
                } else {
                    shareableSongsHolder.crossFadeImageView.bindImage(autoComplete.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
                }
            }
            z = autoComplete.isParentalWarningEnabled();
        } else {
            Track track = (Track) businessObject;
            shareableSongsHolder.txtTitle.setText(track.getName());
            shareableSongsHolder.txtSubtitle.setText(track.getArtistNames());
            shareableSongsHolder.crossFadeImageView.bindImage(track.getArtwork());
            str = businessObject.getBusinessObjId();
            z = track.isParentalWarningEnabled();
        }
        PlayerTrack i2 = PlayerManager.a(this.mContext).i();
        if (i2 == null || i2.b() == null || !str.equalsIgnoreCase(i2.h())) {
            shareableSongsHolder.txtTitle.setTextAppearance(this.mContext, R.style.list_download_item_first_line);
            if (this.isPlayerQueue) {
                if (Constants.l) {
                    this.mView.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.selector_btn_global_bg_grey_white));
                } else {
                    this.mView.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.selector_btn_global_bg_grey));
                }
            }
        } else {
            shareableSongsHolder.txtTitle.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
            if (this.isPlayerQueue) {
                if (Constants.l) {
                    this.mView.setBackgroundColor(this.mContext.getResources().getColor(R.color.tab_layout_background_white));
                } else {
                    this.mView.setBackgroundColor(this.mContext.getResources().getColor(R.color.tab_layout_background));
                }
            }
        }
        int i3 = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
        if (z) {
            shareableSongsHolder.txtSubtitle.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(i3), null, null, null);
        } else {
            shareableSongsHolder.txtSubtitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        shareableSongsHolder.imgShare.setOnClickListener(this);
        return this.mView;
    }

    public String getArtistNames(Item item) {
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        String str = "";
        String language = item.getLanguage();
        if (arrayList == null) {
            return str;
        }
        int size = arrayList.size();
        String str2 = str;
        for (int i = 0; i < size; i++) {
            if (((EntityInfo) arrayList.get(i)).getKey().equals("artist")) {
                ArrayList arrayList2 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                if (arrayList2 != null && arrayList2.size() > 0) {
                    String str3 = str2;
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        Map map = (Map) arrayList2.get(i2);
                        StringBuilder stringBuilder;
                        if (i2 == 0) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(str3);
                            stringBuilder.append(Constants.a((String) map.get("name"), language));
                            str3 = stringBuilder.toString();
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(str3);
                            stringBuilder.append(", ");
                            stringBuilder.append(Constants.a((String) map.get("name"), language));
                            str3 = stringBuilder.toString();
                        }
                    }
                    str2 = str3;
                }
            }
        }
        return str2;
    }

    public void onClick(View view) {
        BusinessObject businessObject;
        String string;
        String string2;
        if (view instanceof TextView) {
            businessObject = (BusinessObject) ((View) view.getParent()).getTag();
        } else {
            businessObject = (BusinessObject) view.getTag();
        }
        if (this.mFragment instanceof PersonaDedicationFragment) {
            Util.a(this.mContext, view);
        }
        if (businessObject instanceof AutoComplete) {
            businessObject = convertAutoSuggestToTrack((AutoComplete) businessObject);
            string = getResources().getString(R.string.share_text_search);
            string2 = getResources().getString(R.string.share_text_search_twitter);
        } else {
            string = getResources().getString(R.string.share_text_simple, new Object[]{this.pageTitle});
            string2 = getResources().getString(R.string.share_text_simple_twitter, new Object[]{this.pageTitle});
        }
        String str = string;
        BusinessObject businessObject2 = businessObject;
        String str2 = string2;
        String string3 = getResources().getString(R.string.share_text_subject);
        BaseActivity baseActivity = (BaseActivity) this.mContext;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
        stringBuilder.append(" Detail");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
        stringBuilder2.append("- Detail  ");
        stringBuilder2.append(((BaseActivity) this.mContext).currentItem);
        stringBuilder2.append("- Share");
        baseActivity.sendGAEvent(stringBuilder.toString(), "Share", stringBuilder2.toString());
        ap.a().a(this.mContext, businessObject2, str, str2, string3);
    }

    private Track convertAutoSuggestToTrack(AutoComplete autoComplete) {
        String rawTitle = autoComplete.getRawTitle();
        String language = autoComplete.getLanguage();
        String artwork = autoComplete.getArtwork();
        String rawSubtitle = autoComplete.getRawSubtitle();
        String businessObjectId = autoComplete.getBusinessObjectId();
        Track track = new Track();
        track.setBusinessObjType(BusinessObjectType.Tracks);
        track.setArtwork(artwork);
        track.setBusinessObjId(businessObjectId);
        track.setName(rawTitle);
        track.setLanguage(language);
        track.setLocalMedia(autoComplete.isLocalMedia());
        track.setAlbumName(rawSubtitle);
        return track;
    }
}
