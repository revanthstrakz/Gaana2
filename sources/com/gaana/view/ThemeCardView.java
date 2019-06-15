package com.gaana.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.BaseItemView.DetailListingItemHolder;
import com.library.controls.CrossFadeImageView;
import com.managers.s;
import com.managers.u;
import com.services.d;

public class ThemeCardView extends BaseItemView implements OnClickListener, OnCheckedChangeListener {
    private static boolean mImpressionAnalyticsRecorded = false;
    private DiwaliCardContentViewHolder loginBannerContentViewHolder;
    protected GaanaApplication mAppState = ((GaanaApplication) this.mContext.getApplicationContext());
    private BusinessObject mBusinessObject;
    private Context mContext;
    private a mDynamicView;
    private View mView;

    public class DiwaliCardContentViewHolder extends ViewHolder {
        public SwitchCompat colorSwitch;
        public TextView colorSwitchText;
        public LinearLayout firstLineLayout;
        public CrossFadeImageView homeDiwaliBackground;

        public DiwaliCardContentViewHolder(View view, boolean z) {
            super(view);
            if (z) {
                this.homeDiwaliBackground = (CrossFadeImageView) view.findViewById(R.id.home_diwali_background);
                this.colorSwitch = (SwitchCompat) view.findViewById(R.id.color_switch);
            }
        }
    }

    private void updateColorCircles() {
    }

    public void setResfreshStatus(boolean z) {
    }

    public ThemeCardView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DetailListingItemHolder(getNewView(0, viewGroup));
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.diwali_card_view, viewGroup, false);
        return this.mView;
    }

    public void setSwitchColor(SwitchCompat switchCompat) {
        int color = this.mContext.getResources().getColor(R.color.switch_oncolor);
        Drawable thumbDrawable = switchCompat.getThumbDrawable();
        r4 = new int[2][];
        r4[0] = new int[]{16842912};
        r4[1] = new int[0];
        DrawableCompat.setTintList(thumbDrawable, new ColorStateList(r4, new int[]{-1, -1}));
        Drawable trackDrawable = switchCompat.getTrackDrawable();
        r2 = new int[2][];
        r2[0] = new int[]{16842912};
        r2[1] = new int[0];
        DrawableCompat.setTintList(trackDrawable, new ColorStateList(r2, new int[]{color, -1}));
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        setSwitchColor((SwitchCompat) compoundButton);
        if (z) {
            Constants.du = true;
        } else {
            Constants.du = false;
        }
        if (s.a().b() != null) {
            d.a().a("PREFERENCE_THEME_MODE_ON_2", Constants.du, false);
            if (Constants.dt != null) {
                StringBuilder stringBuilder;
                if (Constants.du) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Theme_");
                    stringBuilder.append(Constants.dt.getThemeName());
                    u.a().a("Browse", stringBuilder.toString(), "state_ON");
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Theme_");
                    stringBuilder.append(Constants.dt.getThemeName());
                    u.a().a("Browse", stringBuilder.toString(), "state_OFF");
                }
            }
        }
        ((GaanaActivity) this.mContext).applyLightMode(Constants.du);
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return getPopulatedView(i, viewHolder.itemView, viewGroup, null);
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View getPopulatedView(int i, View view, ViewGroup viewGroup, BusinessObject businessObject) {
        if (this.mView != null) {
            if (this.loginBannerContentViewHolder == null) {
                checkAndAddView();
            }
            if (!mImpressionAnalyticsRecorded) {
                u.a().b("Browse", "Theme_impression");
                mImpressionAnalyticsRecorded = true;
            }
        }
        return view;
    }

    private void checkAndAddView() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.home_diwali_card_view, null);
        this.loginBannerContentViewHolder = new DiwaliCardContentViewHolder(inflate, true);
        this.loginBannerContentViewHolder.homeDiwaliBackground.bindImage(this.mDynamicView.d(), ScaleType.FIT_XY);
        this.loginBannerContentViewHolder.colorSwitch.setChecked(Constants.du);
        setSwitchColor(this.loginBannerContentViewHolder.colorSwitch);
        this.loginBannerContentViewHolder.colorSwitch.setOnCheckedChangeListener(this);
        ((LinearLayout) this.mView.findViewById(R.id.loginBannerSlot)).addView(inflate);
    }

    public void onClick(View view) {
        view.getId();
    }

    public void clearDiwaliBanner() {
        if (this.mView != null) {
            LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.loginBannerSlot);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
        }
    }
}
