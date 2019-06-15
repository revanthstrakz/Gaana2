package com.gaana;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.GDPRCantUseAppFragment;
import com.fragments.WebViewsFragment.WebViewContent;
import com.gaana.application.GaanaApplication;
import com.gaana.models.CountryData;
import com.gaana.models.CountryData.Consent;
import com.managers.aj;
import com.managers.ap;
import com.services.f;
import com.services.f.b;
import com.services.l.e;
import com.services.o;
import com.utilities.Util;
import com.utilities.d;
import com.views.TncWebViewFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ConsentActivity extends BaseLaunchActivity {
    private static final String LOG_TAG = "ConsentScreen";
    private boolean blockingScreen = false;
    private ArrayList<Consent> consentArrayList;
    OnClickListener consentItemClickListener = new OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.agree_button) {
                int i = 0;
                int i2 = 1;
                while (i < ConsentActivity.this.consentArrayList.size()) {
                    if (((Consent) ConsentActivity.this.consentArrayList.get(i)).getManadatory() == 1 && !((Boolean) ConsentActivity.this.consentStatus.get(Integer.valueOf(((Consent) ConsentActivity.this.consentArrayList.get(i)).getTncKey()))).booleanValue()) {
                        i2 = 0;
                    }
                    i++;
                }
                if (i2 != 0) {
                    Constants.ek = 1;
                    Constants.em = ConsentActivity.this.consentStatus;
                    ConsentActivity.this.handleAfterConsentFlow();
                    return;
                }
                aj.a().a(ConsentActivity.this, ConsentActivity.this.getResources().getString(R.string.consent_not_checked_error));
            } else if (id == R.id.dont_agree_button) {
                Builder message = new Builder(ConsentActivity.this).setTitle(null).setMessage(ConsentActivity.this.getString(R.string.not_agree_dialog_text));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<b>");
                stringBuilder.append(ConsentActivity.this.getString(R.string.negative_button_text));
                stringBuilder.append("<b>");
                message.setPositiveButton(Html.fromHtml(stringBuilder.toString()), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setNegativeButton(ConsentActivity.this.getString(R.string.positive_button_text), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ConsentActivity.this.initiateDeleteFlow();
                    }
                }).setIcon(17301543).show();
            }
        }
    };
    private LinearLayout consentLayout;
    private HashMap<Integer, Boolean> consentStatus;
    private int consentStatusCount = 0;
    private int currentViewType = 0;
    private GDPRCantUseAppFragment gdprCantUseAppFragment;
    private boolean isLaunchedFromLogout = false;
    f mDialog;
    OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                ConsentActivity.this.consentStatus.put(Integer.valueOf(((Integer) compoundButton.getTag()).intValue()), Boolean.valueOf(true));
                ConsentActivity.this.consentStatusCount = ConsentActivity.this.consentStatusCount + 1;
                return;
            }
            ConsentActivity.this.consentStatus.put(Integer.valueOf(((Integer) compoundButton.getTag()).intValue()), Boolean.valueOf(false));
            ConsentActivity.this.consentStatusCount = ConsentActivity.this.consentStatusCount - 1;
        }
    };
    LinearLayout settingsContainer;

    public void setCurrentFragment(Fragment fragment) {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        if (d.d()) {
            com.utilities.f.a((Context) this);
        }
        setTheme(R.style.ConsentScreenTheme);
        Constants.r = 0;
        overridePendingTransition(0, 0);
        if (Constants.l) {
            setTheme(R.style.ConsentScreenThemeWhite);
        }
        super.onCreate(bundle);
        getSupportActionBar().hide();
        setContentView((int) R.layout.consent_layout);
        this.settingsContainer = (LinearLayout) findViewById(R.id.container);
        this.consentStatus = new HashMap();
        View inflate = this.mInflater.inflate(R.layout.consent_layout_main, this.settingsContainer, false);
        changeConsentScreenView(0, inflate);
        this.mDialog = new f(this.mContext);
        this.shouldDisplayAd = false;
        this.loginAndConsentUpdate = false;
        this.isLaunchedFromLogout = getIntent().getBooleanExtra("IS_LAUNCHED_FROM_LOGOUT", false);
        this.blockingScreen = getIntent().getBooleanExtra("BLOCKING_SCREEN", false);
        if (this.blockingScreen) {
            this.gdprCantUseAppFragment = new GDPRCantUseAppFragment();
            e anonymousClass1;
            try {
                this.gdprCantUseAppFragment.show(getSupportFragmentManager(), "GDPRCantUseAppFragment");
                if (Constants.en != 1) {
                    anonymousClass1 = new e() {
                        public void onDataRetrieved(int i) {
                            switch (i) {
                                case 0:
                                case 1:
                                case 2:
                                    if (!ConsentActivity.this.isFinishing()) {
                                        ConsentActivity.this.handleCountryDataResponse();
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        }
                    };
                    Util.a((Context) this, anonymousClass1);
                }
            } catch (Exception unused) {
                if (Constants.en != 1) {
                    anonymousClass1 = /* anonymous class already generated */;
                }
            } catch (Throwable th) {
                if (Constants.en != 1) {
                    Util.a((Context) this, /* anonymous class already generated */);
                }
            }
        } else {
            changeConsentScreenView(0, inflate);
            displayConsentView();
            Constants.en = 3;
            Util.y(this);
        }
    }

    private void handleCountryDataResponse() {
        CountryData countryData = this.mAppState.getCountryData();
        if (countryData == null || countryData.getEuRegion() != 1) {
            finishSetup();
        } else if (countryData.getUserStatus() == 0 && Constants.ek == 0) {
            if (this.gdprCantUseAppFragment != null) {
                this.gdprCantUseAppFragment.dismiss();
            }
            changeConsentScreenView(0, this.mInflater.inflate(R.layout.consent_layout_main, this.settingsContainer, false));
            displayConsentView();
        } else if (countryData.getUserStatus() != 1) {
            finishSetup();
        }
    }

    public void displayFragment(Fragment fragment) {
        if (fragment != null) {
            View inflate = this.mInflater.inflate(R.layout.fragment_parent, this.settingsContainer, false);
            this.settingsContainer.removeAllViews();
            this.settingsContainer.addView(inflate);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.setCustomAnimations(17432576, 17432577);
            beginTransaction.replace(R.id.frame, fragment);
            beginTransaction.addToBackStack(fragment.getClass().getName());
            beginTransaction.commitAllowingStateLoss();
        }
    }

    private void changeConsentScreenView(int i, View view) {
        if (this.settingsContainer == null) {
            this.settingsContainer = (LinearLayout) findViewById(R.id.container);
        }
        this.currentViewType = i;
        this.settingsContainer.removeAllViews();
        this.settingsContainer.addView(view);
        switch (i) {
            case 0:
                this.consentLayout = (LinearLayout) view.findViewById(R.id.consent_list);
                return;
            default:
                return;
        }
    }

    private void displayConsentView() {
        CountryData countryData = this.mAppState.getCountryData();
        ((TextView) findViewById(R.id.tnc_header)).setText(countryData.getConsentHeader());
        ((TextView) findViewById(R.id.tnc_subheader)).setText(countryData.getConsentText());
        this.consentArrayList = countryData.getConsent();
        if (this.consentArrayList == null || this.consentArrayList.size() <= 0) {
            handleAfterConsentFlow();
            return;
        }
        Iterator it = this.consentArrayList.iterator();
        while (it.hasNext()) {
            Consent consent = (Consent) it.next();
            LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.consent_item, null);
            CheckBox checkBox = (CheckBox) linearLayout.findViewById(R.id.consent_checkBox);
            checkBox.setTag(Integer.valueOf(consent.getTncKey()));
            checkBox.setOnCheckedChangeListener(this.onCheckedChangeListener);
            this.consentStatus.put(Integer.valueOf(consent.getTncKey()), Boolean.valueOf(false));
            TextView textView = (TextView) linearLayout.findViewById(R.id.consent_text);
            String tncValue = consent.getTncValue();
            if (consent.getManadatory() == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(tncValue);
                stringBuilder.append("**");
                tncValue = stringBuilder.toString();
            }
            textView.setText(Html.fromHtml(tncValue));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            this.consentLayout.addView(linearLayout);
            ((LayoutParams) linearLayout.getLayoutParams()).setMargins(0, 0, 0, Util.b(11));
        }
        this.consentStatusCount = 0;
        this.consentLayout.setVisibility(0);
        findViewById(R.id.agree_button).setOnClickListener(this.consentItemClickListener);
        findViewById(R.id.dont_agree_button).setOnClickListener(this.consentItemClickListener);
    }

    private void handleAfterConsentFlow() {
        if (!this.isLaunchedFromLogout) {
            Util.a((Context) this, getResources().getString(R.string.update_policy));
            this.shouldDisplayAd = false;
            this.loginAndConsentUpdate = true;
            finishSetup();
        } else if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            finish();
        } else {
            Intent intent = new Intent(this, Login.class);
            intent.putExtra("ONBOARD_SIGNUP", false);
            intent.addFlags(603979776);
            intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", true);
            intent.putExtra("IS_LAUNCHED_FROM_CONSENT_SCREEN", true);
            startActivity(intent);
        }
    }

    private void initiateDeleteFlow() {
        View inflate = this.mInflater.inflate(R.layout.gdpr_deletedata_screen, this.settingsContainer, false);
        Button button = (Button) inflate.findViewById(R.id.button_agree);
        TextView textView = (TextView) inflate.findViewById(R.id.tnc_text_layout);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.agreeTnC);
        TextView textView2 = (TextView) inflate.findViewById(R.id.message_text);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.delete_data_gaana_account));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_erase_history));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_tracksdata));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_gaana_subs));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_gaana_recommendations));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_search_history));
        textView2.setText(stringBuilder.toString());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = getString(R.string.tnc_part_1);
        String string2 = getString(R.string.string_tnc);
        String string3 = getString(R.string.string_and);
        String string4 = getString(R.string.privacy_policy);
        String string5 = getString(R.string.tnc_part_2);
        spannableStringBuilder.append(string);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(string2);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(ConsentActivity.this.mContext)) {
                    TncWebViewFragment tncWebViewFragment = new TncWebViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.TERMS_CONDITIONS.toString());
                    tncWebViewFragment.setArguments(bundle);
                    ((ConsentActivity) ConsentActivity.this.mContext).displayFragment(tncWebViewFragment);
                    return;
                }
                ap.a().f(ConsentActivity.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string3);
        length = spannableStringBuilder.length();
        spannableStringBuilder.append(string4);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(ConsentActivity.this.mContext)) {
                    TncWebViewFragment tncWebViewFragment = new TncWebViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.PRIVACY_POLICY.toString());
                    tncWebViewFragment.setArguments(bundle);
                    ((ConsentActivity) ConsentActivity.this.mContext).displayFragment(tncWebViewFragment);
                    return;
                }
                ap.a().f(ConsentActivity.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string5);
        spannableStringBuilder.append("\n");
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    CharSequence charSequence = "";
                    if (!(GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null)) {
                        charSequence = GaanaApplication.getInstance().getCurrentUser().getUserProfile().getEmail();
                    }
                    if (TextUtils.isEmpty(charSequence)) {
                        ConsentActivity.this.initEmailPromptUI(ConsentActivity.this.getString(R.string.provide_email_to_deletedata_msg), ConsentActivity.this.getString(R.string.delete_data));
                    } else {
                        ConsentActivity.this.showDeleteDataConfirmDialog(charSequence);
                    }
                    return;
                }
                aj.a().a(ConsentActivity.this.mContext, ConsentActivity.this.getString(R.string.agree_terms_conditions));
            }
        });
        changeConsentScreenView(1, inflate);
    }

    private void showDeleteDataConfirmDialog(final String str) {
        this.mDialog.a(getString(R.string.delete_data_dialog_title), getString(R.string.delete_data_dialog_msg), Boolean.valueOf(true), getString(R.string.yes_text), getString(R.string.dlg_msg_cancel_cap), new b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                Util.k(ConsentActivity.this.mContext, str);
                ConsentActivity.this.mDialog.a("", ConsentActivity.this.getString(R.string.delete_data_confirm_msg), Boolean.valueOf(false), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        ConsentActivity.this.changeConsentScreenView(0, ConsentActivity.this.mInflater.inflate(R.layout.consent_layout_main, ConsentActivity.this.settingsContainer, false));
                        ConsentActivity.this.displayConsentView();
                    }
                });
            }
        }, false);
    }

    private void initEmailPromptUI(String str, String str2) {
        View inflate = this.mInflater.inflate(R.layout.gdpr_downloadmydata_email_prompt, this.settingsContainer, false);
        TextView textView = (TextView) inflate.findViewById(R.id.tnc_text_layout);
        ((TextView) inflate.findViewById(R.id.message_text)).setText(str);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.agreeTnC);
        Button button = (Button) inflate.findViewById(R.id.button_agree);
        final EditText editText = (EditText) inflate.findViewById(R.id.email_address);
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.email_address_layout);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = getString(R.string.tnc_part_1);
        String string2 = getString(R.string.string_tnc);
        String string3 = getString(R.string.string_and);
        String string4 = getString(R.string.privacy_policy);
        String string5 = getString(R.string.tnc_part_2);
        spannableStringBuilder.append(string);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(string2);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(ConsentActivity.this.mContext)) {
                    TncWebViewFragment tncWebViewFragment = new TncWebViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.TERMS_CONDITIONS.toString());
                    tncWebViewFragment.setArguments(bundle);
                    ((ConsentActivity) ConsentActivity.this.mContext).displayFragment(tncWebViewFragment);
                    return;
                }
                ap.a().f(ConsentActivity.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string3);
        length = spannableStringBuilder.length();
        spannableStringBuilder.append(string4);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(ConsentActivity.this.mContext)) {
                    TncWebViewFragment tncWebViewFragment = new TncWebViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.PRIVACY_POLICY.toString());
                    tncWebViewFragment.setArguments(bundle);
                    ((ConsentActivity) ConsentActivity.this.mContext).displayFragment(tncWebViewFragment);
                    return;
                }
                ap.a().f(ConsentActivity.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string5);
        spannableStringBuilder.append("\n");
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = editText.getText().toString();
                if (TextUtils.isEmpty(obj) || !o.b(obj).booleanValue()) {
                    textInputLayout.setError(ConsentActivity.this.mContext.getString(R.string.invalid_email_id));
                } else if (checkBox.isChecked()) {
                    ConsentActivity.this.showDeleteDataConfirmDialog(obj);
                } else {
                    aj.a().a(ConsentActivity.this.mContext, ConsentActivity.this.getString(R.string.agree_terms_conditions));
                }
            }
        });
        changeConsentScreenView(2, inflate);
    }

    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount > 1) {
            getSupportFragmentManager().popBackStack();
        } else if (backStackEntryCount == 1) {
            getSupportFragmentManager().popBackStack();
            switch (this.currentViewType) {
                case 0:
                    changeConsentScreenView(0, this.mInflater.inflate(R.layout.consent_layout_main, this.settingsContainer, false));
                    displayConsentView();
                    return;
                case 1:
                    initiateDeleteFlow();
                    return;
                case 2:
                    initEmailPromptUI(getString(R.string.provide_email_to_deletedata_msg), getString(R.string.delete_data));
                    return;
                default:
                    return;
            }
        } else {
            switch (this.currentViewType) {
                case 0:
                    finish();
                    return;
                case 1:
                    changeConsentScreenView(0, this.mInflater.inflate(R.layout.consent_layout_main, this.settingsContainer, false));
                    displayConsentView();
                    return;
                case 2:
                    initiateDeleteFlow();
                    return;
                default:
                    return;
            }
        }
    }
}
