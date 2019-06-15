package com.gaana.view;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.managers.ap;
import com.managers.w;
import com.services.l.af;
import com.utilities.Util;
import java.util.Map.Entry;

public class HomeSettingsItemView extends BaseItemView {
    public static final String SETTINGS_TAG_DISPLAY_LANGUAGE = "display_language";
    public static final String SETTINGS_TAG_IDENTIFY_SONGS = "identify_song";
    public static final String SETTINGS_TAG_SONG_LANGUAGE = "song_language";
    private a mDynamicView;
    private String mSettingsTag;

    public static class HomeSettingsItemViewHolder extends ViewHolder {
        public TextView mTxtHeader;
        public TextView mTxtSubHeader;

        public HomeSettingsItemViewHolder(View view) {
            super(view);
            this.mTxtHeader = (TextView) view.findViewById(R.id.txt_header);
            this.mTxtSubHeader = (TextView) view.findViewById(R.id.txt_sub_header);
        }
    }

    public HomeSettingsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public HomeSettingsItemView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public HomeSettingsItemView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeSettingsItemViewHolder(getNewView(R.layout.item_home_settings, viewGroup));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        final HomeSettingsItemViewHolder homeSettingsItemViewHolder = (HomeSettingsItemViewHolder) viewHolder;
        homeSettingsItemViewHolder.itemView.setOnClickListener(this);
        String str = "";
        String str2 = "";
        if ((this.mDynamicView.j() != null ? this.mDynamicView.j().size() : 0) > 0) {
            for (Entry entry : this.mDynamicView.j().entrySet()) {
                str2 = (String) entry.getValue();
                str = (String) entry.getKey();
            }
        }
        this.mSettingsTag = str;
        if (str.equals(SETTINGS_TAG_DISPLAY_LANGUAGE)) {
            setSubHeader(homeSettingsItemViewHolder, PreferenceManager.getDefaultSharedPreferences(this.mContext).getString("PREFERENCE_APP_DISPLAY_LANGUAGE", "English"));
        } else if (str.equals(SETTINGS_TAG_SONG_LANGUAGE)) {
            w.a(this.mAppState).a(this.mContext, new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(final Object obj) {
                    if (HomeSettingsItemView.this.mFragment.isAdded()) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                String str = (String) obj;
                                if (!com.helpshift.util.w.a(str) && str.contains(",")) {
                                    str = str.replaceAll(",", ", ");
                                }
                                HomeSettingsItemView.this.setSubHeader(homeSettingsItemViewHolder, str);
                            }
                        });
                    }
                }
            });
        } else if (this.mSettingsTag.equals(SETTINGS_TAG_IDENTIFY_SONGS)) {
            setSubHeader(homeSettingsItemViewHolder, " ");
        }
        homeSettingsItemViewHolder.mTxtHeader.setText(Constants.b(str2));
        return viewHolder.itemView;
    }

    private void setSubHeader(HomeSettingsItemViewHolder homeSettingsItemViewHolder, String str) {
        homeSettingsItemViewHolder.mTxtSubHeader.setText(str);
        Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ic_arrow_right);
        if (Constants.l) {
            drawable.setColorFilter(ViewCompat.MEASURED_STATE_MASK, Mode.SRC_IN);
        } else {
            drawable.setColorFilter(-1, Mode.SRC_IN);
        }
        drawable.setAlpha(179);
        homeSettingsItemViewHolder.mTxtSubHeader.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    public void onClick(View view) {
        if (Util.j(this.mContext)) {
            if (this.mSettingsTag.equals(SETTINGS_TAG_SONG_LANGUAGE)) {
                ((GaanaActivity) this.mContext).changeFragment(R.id.LeftSongLanguage, null, null);
            } else if (this.mSettingsTag.equals(SETTINGS_TAG_IDENTIFY_SONGS)) {
                ((GaanaActivity) this.mContext).changeFragment(R.id.LeftIdentifySong, null, null);
            } else if (this.mSettingsTag.equals(SETTINGS_TAG_DISPLAY_LANGUAGE)) {
                ((GaanaActivity) this.mContext).changeFragment(R.id.LeftDispLanguage, null, null);
            }
            return;
        }
        ap.a().f(this.mContext);
    }
}
