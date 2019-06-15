package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaThemeModel;
import com.gaana.models.GaanaThemeModel.GaanaTheme;
import com.gaana.models.Languages.Language;
import com.google.gson.Gson;
import com.i.i;
import com.managers.s;
import com.managers.u;
import com.services.d;
import com.services.l.r;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class ThemeSettingsItemView extends BaseItemView implements OnClickListener {
    private int drawableFromTheme;
    private ArrayList<Language> mArrListLanguages = null;
    private ArrayList<Language> mArrListSelectedLanguages = null;
    private Button mBtnSaveLanguages;
    private Context mContext;
    private int mLayoutId;
    private LinearLayout mLayoutLanguageChooser = null;
    private ProgressBar mProgressBar = null;
    OnClickListener radioButtonClickListener = new OnClickListener() {
        public void onClick(View view) {
            int childCount = ThemeSettingsItemView.this.mLayoutLanguageChooser.getChildCount();
            for (int i = 0; i != childCount; i++) {
                ((ImageView) ((ViewGroup) ((ViewGroup) ThemeSettingsItemView.this.mLayoutLanguageChooser.getChildAt(i)).getChildAt(0)).getChildAt(2)).setImageResource(ThemeSettingsItemView.this.drawableFromTheme);
            }
            ((ImageView) ((ViewGroup) ((ViewGroup) view).getChildAt(0)).getChildAt(2)).setImageResource(R.drawable.vector_download_completed);
            ThemeSettingsItemView.this.themeSelected = (GaanaTheme) view.getTag();
        }
    };
    private GaanaTheme themeSelected;

    public ThemeSettingsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mLayoutId = R.layout.view_theme_settings;
    }

    public ThemeSettingsItemView(Context context, BaseFragment baseFragment, int i) {
        super(context, baseFragment, i);
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        return getDataFilledView(super.getPoplatedView(view, businessObject), businessObject);
    }

    public View getDataFilledView(View view, BusinessObject businessObject) {
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        if (Constants.du) {
            this.themeSelected = Constants.dt;
        }
        this.mLayoutLanguageChooser = (LinearLayout) view.findViewById(R.id.llLanguageChooser);
        this.mBtnSaveLanguages = (Button) view.findViewById(R.id.btnSaveLanguages);
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.mLayoutLanguageChooser.removeAllViews();
        GaanaThemeModel b = s.a().b();
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.drawableFromTheme = obtainStyledAttributes.getResourceId(88, -1);
        if (b != null && b.getThemeArrayList() != null && b.getThemeArrayList().size() > 0) {
            inflateThemeItem(null);
            Iterator it = b.getThemeArrayList().iterator();
            while (it.hasNext()) {
                inflateThemeItem((GaanaTheme) it.next());
            }
            this.mBtnSaveLanguages.setVisibility(0);
            this.mBtnSaveLanguages.setOnClickListener(this);
        } else if (this.mFragment != null && (this.mFragment instanceof SettingsDetailFragment)) {
            this.mFragment.getFragmentManager().popBackStack();
        }
        obtainStyledAttributes.recycle();
        u.a().a("Settings:ChangeAppthemeScreen");
        return view;
    }

    private void inflateThemeItem(GaanaTheme gaanaTheme) {
        int accountType = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData() != null ? GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() : 1;
        if ((accountType != 3 && accountType != 2) || gaanaTheme == null || !gaanaTheme.isSponsored()) {
            View inflate = this.mInflater.inflate(R.layout.view_item_theme_choice, null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.language_name);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.language_selected);
            final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.language_bg_img);
            if (gaanaTheme != null) {
                String settingWhiteArtwork;
                textView.setText(gaanaTheme.getThemeName());
                if (Constants.l) {
                    settingWhiteArtwork = gaanaTheme.getSettingWhiteArtwork();
                } else {
                    settingWhiteArtwork = gaanaTheme.getSettingBlackArtwork();
                }
                i.a().a(settingWhiteArtwork, new r() {
                    public void onErrorResponse(VolleyError volleyError) {
                    }

                    public void onSuccessfulResponse(Bitmap bitmap) {
                        imageView2.setImageBitmap(bitmap);
                    }
                });
            } else {
                textView.setText(this.mContext.getString(R.string.default_filter));
                imageView2.setVisibility(8);
            }
            inflate.setTag(gaanaTheme);
            inflate.setOnClickListener(this.radioButtonClickListener);
            if (gaanaTheme != null) {
                if (Constants.du && Constants.dt != null && gaanaTheme.getThemeName().equalsIgnoreCase(Constants.dt.getThemeName())) {
                    imageView.setImageResource(R.drawable.vector_download_completed);
                } else {
                    imageView.setImageResource(this.drawableFromTheme);
                }
            } else if (Constants.du) {
                imageView.setImageResource(this.drawableFromTheme);
            } else {
                imageView.setImageResource(R.drawable.vector_download_completed);
            }
            this.mLayoutLanguageChooser.addView(inflate);
            LayoutParams layoutParams = (LayoutParams) inflate.getLayoutParams();
            accountType = Util.a(this.mContext, 10);
            layoutParams.setMargins(accountType, 0, accountType, accountType);
        }
    }

    public void onClick(View view) {
        String themeName;
        super.onClick(view);
        if (this.themeSelected != null) {
            Constants.du = true;
            if (Constants.dt == null || !this.themeSelected.getThemeName().equalsIgnoreCase(Constants.dt.getThemeName())) {
                Constants.dt = this.themeSelected;
                u a = u.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("userselected - ");
                stringBuilder.append(Constants.dt.getThemeName());
                a.a(24, stringBuilder.toString());
                d.a().a("PREFERENCE_CURRENT_THEME", new Gson().toJson(Constants.dt), false);
                d.a().a("PREFERENCE_USER_SELECTED_THEME", Constants.dt.getThemeName(), false);
                d.a().a("PREFERENCE_THEME_MODE_ON_2", Constants.du, false);
            }
        } else {
            Constants.du = false;
            d.a().a("PREFERENCE_CURRENT_THEME", null, false);
            d.a().a("PREFERENCE_USER_SELECTED_THEME", null, false);
            d.a().a("PREFERENCE_THEME_MODE_ON_2", Constants.du, false);
        }
        if (Constants.du) {
            themeName = Constants.dt.getThemeName();
        } else {
            themeName = this.mContext.getString(R.string.default_filter);
        }
        u.a().a("AppThemes", "themeSaved", themeName);
        ((GaanaActivity) this.mContext).restartApp(false);
    }
}
