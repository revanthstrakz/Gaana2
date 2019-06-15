package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.d;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Radios.Radio;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.ad;
import com.managers.ap;
import com.services.l.s;
import com.utilities.Util;

public class RadioItemView extends BaseItemView {
    private CrossFadeImageView mCrossFadeImageIcon;
    private String mSearchQuery;
    private int radioPosition;
    private TextView tvTopHeading;

    public static class RadioItemHolder extends ViewHolder {
        private CrossFadeImageView mCrossFadeImageIcon;
        private ImageView optionImageView;
        private TextView tvBottomHeading;
        private TextView tvTopHeading;

        public RadioItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
            this.optionImageView = (ImageView) view.findViewById(R.id.clickoptionImage);
        }
    }

    public int getRadioPosition() {
        return this.radioPosition;
    }

    public void setRadioPosition(int i) {
        this.radioPosition = i;
    }

    public RadioItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.radioPosition = -1;
        this.mLayoutId = R.layout.view_item_radio_listing;
    }

    public void setSearchQuery(String str) {
        this.mSearchQuery = str;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(R.layout.view_item_radio_listing, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        view.findViewById(R.id.clickoptionImage).setTag(businessObject);
        view.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RadioItemView.this.showOptionMenu(view);
            }
        });
        return getDataFilledView(view, businessObject);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        RadioItemHolder radioItemHolder = (RadioItemHolder) viewHolder;
        this.mView = radioItemHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        radioItemHolder.optionImageView.setTag(businessObject);
        radioItemHolder.optionImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RadioItemView.this.showOptionMenu(view);
            }
        });
        return getDataFilledView(radioItemHolder, businessObject);
    }

    private View getDataFilledView(RadioItemHolder radioItemHolder, BusinessObject businessObject) {
        Radio radio = (Radio) businessObject;
        this.mCrossFadeImageIcon = radioItemHolder.mCrossFadeImageIcon;
        this.tvTopHeading = radioItemHolder.tvTopHeading;
        this.mCrossFadeImageIcon.bindImage(radio.getArtwork(), this.mAppState.isAppInOfflineMode());
        this.tvTopHeading.setText(Util.c(this.mSearchQuery, radio.getName()));
        if (radioItemHolder.tvBottomHeading != null) {
            radioItemHolder.tvBottomHeading.setVisibility(8);
        }
        return this.mView;
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        Radio radio = (Radio) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
        this.mCrossFadeImageIcon.bindImage(radio.getArtwork(), this.mAppState.isAppInOfflineMode());
        this.tvTopHeading.setText(Util.c(this.mSearchQuery, radio.getName()));
        if (findViewById(R.id.tvBottomHeading) != null) {
            findViewById(R.id.tvBottomHeading).setVisibility(8);
        }
        return view;
    }

    private View getDataFilledView(PlaylistGridHolder playlistGridHolder, BusinessObject businessObject) {
        Radio radio = (Radio) businessObject;
        this.mCrossFadeImageIcon = playlistGridHolder.crossFadeImageView;
        this.tvTopHeading = playlistGridHolder.tvTopHeading;
        playlistGridHolder.play_icon.setVisibility(4);
        this.mCrossFadeImageIcon.bindImage(radio.getArtwork(), this.mAppState.isAppInOfflineMode());
        this.tvTopHeading.setText(Util.c(this.mSearchQuery, radio.getName()));
        return this.mView;
    }

    public View getPoplatedViewForGrid(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        this.mView = playlistGridHolder.itemView;
        this.mView.setTag(businessObject);
        this.mView.setOnClickListener(this);
        super.getPoplatedView(this.mView, businessObject, viewGroup);
        return getDataFilledView(playlistGridHolder, businessObject);
    }

    public void onClick(final View view) {
        Radio radio = (Radio) view.getTag();
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(getContext().getString(R.string.this_radio));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (Constants.cY) {
            JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                public void onNegativeButtonClick() {
                }

                public void onPositiveButtonClick() {
                    JukeSessionManager.getInstance().stopJukeSession(new s() {
                        public void onErrorResponse(BusinessObject businessObject) {
                        }

                        public void onRetreivalComplete(BusinessObject businessObject) {
                            if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                RadioItemView.this.onClick(view);
                            }
                        }
                    });
                }
            });
        } else {
            this.mBusinessObject = radio;
            super.onClick(view);
            StringBuilder stringBuilder;
            if (radio.getType().equals(d.c)) {
                if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Position ");
                    stringBuilder.append(getRadioPosition());
                    stringBuilder.append(" - RadioMirchi - ");
                    stringBuilder.append(radio.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", "radio mirchi click ", stringBuilder.toString());
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                    stringBuilder.append(" - RadioMirchi - ");
                    stringBuilder.append(radio.getEnglishName());
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder.toString());
                }
                ad.a(this.mContext).a((BusinessObject) radio);
            } else {
                if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Position");
                    stringBuilder.append(getRadioPosition());
                    stringBuilder.append(" - GaanaRadio - ");
                    stringBuilder.append(radio.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", "gaana radios click", stringBuilder.toString());
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                    stringBuilder.append(" - GaanaRadio - ");
                    stringBuilder.append(radio.getEnglishName());
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder.toString());
                }
                ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", radio.getBusinessObjId()).replace("<radio_type>", radio.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), (BusinessObject) radio);
            }
            this.mListingComponents = Constants.a(radio);
            this.mListingComponents.a((Radio) view.getTag());
            populateRadioListing(radio);
        }
    }
}
