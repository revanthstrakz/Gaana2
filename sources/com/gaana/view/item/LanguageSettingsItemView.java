package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dynamicview.DynamicViewManager;
import com.fragments.BaseGaanaFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.j;
import com.managers.aj;
import com.managers.ap;
import com.managers.f;
import com.managers.o;
import com.managers.w;
import com.managers.w.a;
import com.managers.w.b;
import com.services.l.y;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class LanguageSettingsItemView extends BaseItemView implements OnClickListener {
    private ArrayList<Language> mArrListLanguages;
    private ArrayList<Language> mArrListSelectedLanguages;
    private Button mBtnSaveLanguages;
    private LinearLayout mLayoutLanguageChooser;
    private ProgressBar mProgressBar;

    public LanguageSettingsItemView(Context context) {
        super(context, null);
        this.mLayoutLanguageChooser = null;
        this.mArrListLanguages = null;
        this.mArrListSelectedLanguages = null;
        this.mProgressBar = null;
        this.mLayoutId = R.layout.view_language_settings;
    }

    public LanguageSettingsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutLanguageChooser = null;
        this.mArrListLanguages = null;
        this.mArrListSelectedLanguages = null;
        this.mProgressBar = null;
        this.mLayoutId = R.layout.view_language_settings;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        return getDataFilledView(super.getPoplatedView(view, businessObject), businessObject);
    }

    public View getDataFilledView(View view, BusinessObject businessObject) {
        this.mLayoutLanguageChooser = (LinearLayout) view.findViewById(R.id.llLanguageChooser);
        this.mBtnSaveLanguages = (Button) view.findViewById(R.id.btnSaveLanguages);
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.mBtnSaveLanguages.setVisibility(8);
        this.mProgressBar.setVisibility(0);
        this.mLayoutLanguageChooser.removeAllViews();
        w.a(this.mAppState).a(this.mContext, new a() {
            public void onLanguagesFetched(Languages languages) {
                LanguageSettingsItemView.this.mProgressBar.setVisibility(8);
                if (languages != null) {
                    LanguageSettingsItemView.this.mArrListLanguages = languages.getArrListBusinessObj();
                    MoEngage.getInstance().reportLanguagesScreenViewed();
                    Iterator it = LanguageSettingsItemView.this.mArrListLanguages.iterator();
                    while (it.hasNext()) {
                        Language language = (Language) it.next();
                        View inflate = LanguageSettingsItemView.this.mInflater.inflate(R.layout.view_settings_listitem_checkbox, null, false);
                        final TextView textView = (TextView) inflate.findViewById(R.id.headerText);
                        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkbox);
                        textView.setText(language.getLanguage());
                        boolean z = language.isPrefered() != 0;
                        textView.setSelected(z);
                        checkBox.setChecked(z);
                        inflate.setTag(Boolean.valueOf(z));
                        checkBox.setId(-1);
                        checkBox.setSaveEnabled(true);
                        checkBox.setClickable(false);
                        inflate.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                if (LanguageSettingsItemView.this.mAppState.isAppInOfflineMode()) {
                                    ((BaseActivity) LanguageSettingsItemView.this.mContext).displayFeatureNotAvailableOfflineDialog("");
                                } else if (Util.j(LanguageSettingsItemView.this.mContext)) {
                                    boolean booleanValue = ((Boolean) view.getTag()).booleanValue();
                                    checkBox.setChecked(booleanValue ^ 1);
                                    textView.setSelected(booleanValue ^ 1);
                                    view.setTag(Boolean.valueOf(booleanValue ^ 1));
                                } else {
                                    ap.a().f(LanguageSettingsItemView.this.mContext);
                                }
                            }
                        });
                        LanguageSettingsItemView.this.mLayoutLanguageChooser.addView(inflate);
                    }
                    LanguageSettingsItemView.this.mBtnSaveLanguages.setVisibility(0);
                    LanguageSettingsItemView.this.mBtnSaveLanguages.setOnClickListener(LanguageSettingsItemView.this);
                } else if (LanguageSettingsItemView.this.mFragment != null && (LanguageSettingsItemView.this.mFragment instanceof SettingsDetailFragment)) {
                    LanguageSettingsItemView.this.mFragment.getFragmentManager().popBackStack();
                }
            }
        });
        return view;
    }

    private void saveLanguageSettings() {
        this.mArrListSelectedLanguages = new ArrayList();
        Languages languages = new Languages();
        ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true));
        try {
            if (this.mLayoutLanguageChooser instanceof ViewGroup) {
                LinearLayout linearLayout = this.mLayoutLanguageChooser;
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    View childAt = linearLayout.getChildAt(i);
                    if (childAt.getId() == R.id.parentView) {
                        TextView textView = (TextView) childAt.findViewById(R.id.headerText);
                        boolean isSelectedView = isSelectedView(textView);
                        if (textView != null) {
                            this.mArrListSelectedLanguages.add(languages.getLanguage(String.valueOf(textView.getText()), isSelectedView));
                        }
                    }
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        w.a(this.mAppState).a(this.mContext, this.mArrListSelectedLanguages, new b() {
            public void onLanguageSavedOnServer(final String str, boolean z) {
                if (z) {
                    j.a().a("https://apiv2.gaana.com/radio/metadata");
                    DynamicViewManager.a().a(new y() {
                        public void OnDynamicViewDataFetched() {
                            ((GaanaActivity) LanguageSettingsItemView.this.mContext).hideProgressDialog();
                            MoEngage.getInstance().reportLanguageSet(LanguageSettingsItemView.this.mArrListSelectedLanguages);
                            o.a().b();
                            aj.a().a(LanguageSettingsItemView.this.mContext, str);
                            LanguageSettingsItemView.this.mAppState.setSidebarActiveBtn(R.id.GaanaHome);
                            Intent intent = new Intent(LanguageSettingsItemView.this.mContext, GaanaActivity.class);
                            intent.setFlags(71303168);
                            LanguageSettingsItemView.this.mContext.startActivity(intent);
                            f.v().a(GaanaApplication.getInstance().getCurrentUser());
                        }
                    }, LanguageSettingsItemView.this.mContext);
                    return;
                }
                ((GaanaActivity) LanguageSettingsItemView.this.mContext).hideProgressDialog();
                ap.a().a(LanguageSettingsItemView.this.mContext, str);
            }
        });
    }

    private boolean isSelectedView(View view) {
        return view.isSelected();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("");
        } else if (Util.j(this.mContext)) {
            if (view.getId() == R.id.btnSaveLanguages) {
                saveLanguageSettings();
            }
        } else {
            ap.a().f(this.mContext);
        }
    }
}
