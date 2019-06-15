package com.fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.volley.Request.Priority;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.facebook.GraphResponse;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.MyProfile;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.GsonBuilder;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.ap;
import com.managers.q;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.services.f;
import com.services.g;
import com.services.g.a;
import com.services.k;
import com.services.l.af;
import com.services.l.r;
import com.services.m;
import com.services.m.b;
import com.services.o;
import com.utilities.Util;
import java.io.FileNotFoundException;
import java.util.HashMap;
import org.json.JSONObject;

public class EditProfileActivityFragment extends BaseGaanaFragment implements OnClickListener, a {
    private boolean A = false;
    private int B;
    b a = new b() {
        public void onPhoneLoginSuccess(String str) {
            EditProfileActivityFragment.this.b(str);
        }

        public void onPhoneLoginFailed(String str) {
            EditProfileActivityFragment.this.v.setVisibility(8);
            EditProfileActivityFragment.this.x.setVisibility(8);
            EditProfileActivityFragment.this.t.setVisibility(0);
            aj.a().a(EditProfileActivityFragment.this.mContext, EditProfileActivityFragment.this.getResources().getString(R.string.login_failed));
        }

        public void onPhoneLoginCancel(String str) {
            EditProfileActivityFragment.this.v.setVisibility(8);
            EditProfileActivityFragment.this.x.setVisibility(8);
            EditProfileActivityFragment.this.t.setVisibility(0);
        }
    };
    private EditText b;
    private EditText c;
    private RadioButton d;
    private RadioButton e;
    private int f = -1;
    private EditText g;
    private ImageView h;
    private TextView i;
    private TextView j;
    private f k;
    private HashMap<String, String> l;
    private String m = null;
    private CrossFadeImageView n = null;
    private Bitmap o = null;
    private Bitmap p = null;
    private Drawable q;
    private EditText r;
    private TextView s;
    private TextView t;
    private ProgressBar u;
    private ProgressBar v;
    private CheckBox w;
    private CheckBox x;
    private boolean y = false;
    private boolean z = false;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.containerView = setContentView(R.layout.activity_edit_profile, viewGroup);
        a();
        updateView();
        setGAScreenName("EditProfileScreen", "EditProfileScreen");
        MoEngage.getInstance().reportSectionViewedEvent("EditProfile");
        new int[1][0] = R.attr.ic_action_accept;
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(R.styleable.VectorDrawables);
        this.q = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(19, -1));
        obtainStyledAttributes.recycle();
        View inflate = this.layoutInflater.inflate(R.layout.view_top_tabbar_buttons, null);
        setActionBar(this.containerView, inflate, false);
        inflate.findViewById(R.id.btnLeft).setOnClickListener(this);
        inflate.findViewById(R.id.btnRight).setOnClickListener(this);
        ((ImageView) inflate.findViewById(R.id.btnRight)).setImageDrawable(this.q);
        TextView textView = (TextView) inflate.findViewById(R.id.tvCurrentViewTag);
        textView.setText(R.string.edit_profile);
        if (Constants.l) {
            textView.setTextColor(getResources().getColor(R.color.black));
        } else {
            textView.setTextColor(getResources().getColor(R.color.white));
        }
        if (getActivity() != null) {
            this.B = ((GaanaActivity) getActivity()).getSlidingPanelLayout().a();
            getActivity().getWindow().setSoftInputMode(34);
        }
        return this.containerView;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void a() {
        this.containerView.setOnClickListener(this);
        this.b = (EditText) this.containerView.findViewById(R.id.editTxtEmailAddress);
        this.c = (EditText) this.containerView.findViewById(R.id.editTxtName);
        this.r = (EditText) this.containerView.findViewById(R.id.editTxtPhoneNumber);
        this.r.setOnClickListener(this);
        this.s = (TextView) this.containerView.findViewById(R.id.mail_verify_text);
        this.s.setOnClickListener(this);
        this.t = (TextView) this.containerView.findViewById(R.id.phone_verify_text);
        this.t.setOnClickListener(this);
        this.u = (ProgressBar) this.containerView.findViewById(R.id.mail_progressbar);
        this.v = (ProgressBar) this.containerView.findViewById(R.id.phone_progressbar);
        this.w = (CheckBox) this.containerView.findViewById(R.id.mail_checkbox);
        this.x = (CheckBox) this.containerView.findViewById(R.id.phone_checkbox);
        this.d = (RadioButton) this.containerView.findViewById(R.id.male_gender);
        this.e = (RadioButton) this.containerView.findViewById(R.id.female_gender);
        this.j = (TextView) this.containerView.findViewById(R.id.pull_from_facebook_text);
        this.j.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.i = (TextView) this.containerView.findViewById(R.id.editprofile_deactivateaccount);
        this.i.setOnClickListener(this);
        this.h = (ImageView) this.containerView.findViewById(R.id.dateOfBirthButton);
        this.g = (EditText) this.containerView.findViewById(R.id.editTextDob);
        this.g.setKeyListener(null);
        this.n = (CrossFadeImageView) this.containerView.findViewById(R.id.uploadPhoto);
        this.n.setOnClickListener(this);
        ((TextView) this.containerView.findViewById(R.id.upload_photo_text)).setOnClickListener(this);
        Util.a(getActivity(), this.g, this.h);
        UserInfo currentUser = this.mAppState.getCurrentUser();
        if (!(currentUser == null || currentUser.getUserProfile() == null || !currentUser.getLoginStatus())) {
            this.c.setText(currentUser.getUserProfile().getFullname());
            this.g.setText(currentUser.getUserProfile().getDob());
            if ("Male".equalsIgnoreCase(currentUser.getUserProfile().getSex())) {
                this.f = 0;
            } else if ("Female".equalsIgnoreCase(currentUser.getUserProfile().getSex())) {
                this.f = 1;
            }
            this.n.bindImage(currentUser.getUserProfile().getImg(), this.mAppState.isAppInOfflineMode());
            this.b.setEnabled(true);
        }
        if (currentUser == null || currentUser.getUserProfile() == null || !currentUser.getLoginStatus() || TextUtils.isEmpty(currentUser.getUserProfile().getEmail())) {
            this.b.setEnabled(true);
            this.w.setVisibility(8);
            this.s.setVisibility(0);
        } else {
            this.b.setText(currentUser.getUserProfile().getEmail());
            if (currentUser.getUserProfile().getEmail_status() == null || !currentUser.getUserProfile().getEmail_status().equalsIgnoreCase("1")) {
                this.b.setEnabled(true);
                this.w.setVisibility(8);
                this.s.setVisibility(0);
            } else {
                this.b.setEnabled(false);
                this.w.setVisibility(0);
                this.s.setVisibility(8);
            }
        }
        if (currentUser == null || currentUser.getUserProfile() == null || !currentUser.getLoginStatus() || currentUser.getUserProfile().getPhoneNumber() == null || TextUtils.isEmpty(currentUser.getUserProfile().getPhoneNumber())) {
            this.x.setVisibility(8);
            this.t.setVisibility(0);
        } else {
            String str = "";
            if (currentUser.getUserProfile().getMobileCountryPrefix() != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("+");
                stringBuilder.append(currentUser.getUserProfile().getMobileCountryPrefix());
                str = stringBuilder.toString();
            }
            EditText editText = this.r;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("-");
            stringBuilder2.append(currentUser.getUserProfile().getPhoneNumber());
            editText.setText(stringBuilder2.toString());
            this.x.setVisibility(0);
            this.t.setVisibility(8);
            this.r.setClickable(false);
            this.r.setOnClickListener(null);
        }
        b();
    }

    private void b() {
        if (this.f == 0) {
            this.d.setChecked(true);
            this.e.setChecked(false);
        } else if (this.f == 1) {
            this.d.setChecked(false);
            this.e.setChecked(true);
        }
    }

    private void c() {
        String trim = this.g.getText().toString().trim();
        this.l = new HashMap();
        this.l.put("type", "nxtgen_update_profile");
        this.l.put(LoginManager.TAG_FULL_NAME, this.c.getText().toString().trim());
        this.l.put("email", this.b.getText().toString().trim());
        this.l.put(LoginManager.TAG_DOB, Util.a(trim, false));
        this.l.put("mobile_number", this.r.getText().toString());
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            this.l.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, currentUser.getAuthToken());
        }
        switch (this.f) {
            case 0:
                this.l.put("gender", MoEHelperConstants.GENDER_MALE);
                break;
            case 1:
                this.l.put("gender", MoEHelperConstants.GENDER_FEMALE);
                break;
        }
        a(true);
        BaseActivity baseActivity = (BaseActivity) this.mContext;
        Boolean valueOf = Boolean.valueOf(true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mContext.getString(R.string.updating));
        stringBuilder.append("\t\t\t\t\t");
        baseActivity.showProgressDialog(valueOf, stringBuilder.toString());
        URLManager uRLManager = new URLManager();
        uRLManager.a(String.class);
        uRLManager.a(Priority.HIGH);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.c(1);
        uRLManager.a(this.l);
        uRLManager.a("https://api.gaana.com/user.php?");
        uRLManager.i(false);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                try {
                    EditProfileActivityFragment.this.m = (String) obj;
                    EditProfileActivityFragment.this.a(false);
                    ((BaseActivity) EditProfileActivityFragment.this.mContext).hideProgressDialog();
                    if (EditProfileActivityFragment.this.d().booleanValue()) {
                        q.a().a(EditProfileActivityFragment.this.mAppState.getCurrentUser());
                        com.managers.f.v().a(EditProfileActivityFragment.this.mAppState.getCurrentUser());
                        Constants.a(EditProfileActivityFragment.this.mAppState.getCurrentUser());
                        aj.a().a(EditProfileActivityFragment.this.mContext, EditProfileActivityFragment.this.mContext.getString(R.string.profile_updated));
                        if (EditProfileActivityFragment.this.o != null) {
                            new k(EditProfileActivityFragment.this.getActivity(), "https://api.gaana.com/updateuserimage.php", EditProfileActivityFragment.this.o).execute(new Void[0]);
                        }
                        if (EditProfileActivityFragment.this.mContext instanceof GaanaActivity) {
                            ((GaanaActivity) EditProfileActivityFragment.this.mContext).updateSidebarUserDetails();
                        }
                        EditProfileActivityFragment.this.getFragmentManager().popBackStack();
                        return;
                    }
                    aj.a().a(EditProfileActivityFragment.this.mContext, EditProfileActivityFragment.this.mContext.getString(R.string.authentication_error));
                } catch (Exception unused) {
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) EditProfileActivityFragment.this.mContext).handleError(businessObject.getVolleyError().getMessage());
            }
        }, uRLManager);
    }

    private Boolean d() {
        if (this.m != null) {
            try {
                JSONObject jSONObject = new JSONObject(this.m);
                if (jSONObject.has("status") && jSONObject.has("data")) {
                    String string = jSONObject.getString("status");
                    MyProfile myProfile = (MyProfile) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(jSONObject.getString("data"), MyProfile.class);
                    if (myProfile == null || !"1".equalsIgnoreCase(string)) {
                        aj.a().a(this.mContext, this.mContext.getString(R.string.error_occured_in_updating));
                    } else {
                        this.mAppState.getCurrentUser().setUserProfile(myProfile);
                        LoginManager.getInstance().saveUserInfoInSharedPreff();
                        return Boolean.valueOf(true);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return Boolean.valueOf(false);
    }

    private void a(boolean z) {
        this.h.setEnabled(z ^ 1);
        this.n.setEnabled(z ^ 1);
        this.i.setEnabled(z ^ 1);
        this.j.setEnabled(z ^ 1);
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        switch (view.getId()) {
            case R.id.btnLeft /*2131296535*/:
                ((GaanaActivity) getActivity()).onBackPressedHandling();
                return;
            case R.id.btnRight /*2131296537*/:
                e();
                return;
            case R.id.editTxtPhoneNumber /*2131297014*/:
                g();
                return;
            case R.id.female_gender /*2131297138*/:
                this.f = 1;
                b();
                return;
            case R.id.mail_verify_text /*2131297663*/:
                this.z = false;
                h();
                return;
            case R.id.male_gender /*2131297674*/:
                this.f = 0;
                b();
                return;
            case R.id.phone_verify_text /*2131297969*/:
                g();
                return;
            case R.id.pull_from_facebook_text /*2131298124*/:
                j();
                return;
            case R.id.uploadPhoto /*2131298799*/:
                k();
                return;
            case R.id.upload_photo_text /*2131298801*/:
                k();
                return;
            default:
                return;
        }
    }

    private void e() {
        boolean f = f();
        if (f) {
            c();
        } else if (!f && this.z) {
            h();
        }
    }

    private boolean f() {
        String trim = this.g.getText().toString().trim();
        this.k = new f(getActivity());
        if (!o.a(this.c.getText().toString().trim())) {
            this.k.a(this.mContext.getString(R.string.use_only_alphabets));
            return false;
        } else if (Util.e(trim) == -1) {
            this.k.a(this.mContext.getString(R.string.enter_valid_dob));
            return false;
        } else if (Util.e(trim) == 0) {
            this.k.a(this.mContext.getString(R.string.more_than_13_year));
            return false;
        } else if (!Util.j(getActivity())) {
            ap.a().f(getActivity());
            return false;
        } else if (TextUtils.isEmpty(this.b.getText().toString().trim())) {
            return true;
        } else {
            if (this.mAppState.getCurrentUser().getUserProfile().getEmail_status() != null && TextUtils.isEmpty(this.mAppState.getCurrentUser().getUserProfile().getEmail()) && this.mAppState.getCurrentUser().getUserProfile().getEmail_status().equalsIgnoreCase("0")) {
                this.z = true;
                return false;
            } else if (TextUtils.isEmpty(this.mAppState.getCurrentUser().getUserProfile().getEmail()) || this.mAppState.getCurrentUser().getUserProfile().getEmail().equalsIgnoreCase(this.b.getText().toString())) {
                return true;
            } else {
                this.z = true;
                return false;
            }
        }
    }

    private void g() {
        this.v.setVisibility(0);
        this.x.setVisibility(8);
        this.t.setVisibility(8);
        m.a().a(getActivity(), this.a, true);
    }

    private void h() {
        String obj = this.b.getText().toString();
        if (TextUtils.isEmpty(obj) || !o.b(obj).booleanValue()) {
            aj.a().a(this.mContext, getContext().getString(R.string.error_msg_incorrect_emailid));
            return;
        }
        this.u.setVisibility(0);
        this.w.setVisibility(8);
        this.s.setVisibility(8);
        i();
    }

    private void i() {
        if (Util.j(getActivity())) {
            this.l = new HashMap();
            this.l.put("type", "nxtgen_update_profile_email");
            this.l.put("email", this.b.getText().toString().trim());
            UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
            if (currentUser != null && currentUser.getLoginStatus()) {
                this.l.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, currentUser.getAuthToken());
            }
            BaseActivity baseActivity = (BaseActivity) this.mContext;
            Boolean valueOf = Boolean.valueOf(true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.updating));
            stringBuilder.append("\t\t\t\t\t");
            baseActivity.showProgressDialog(valueOf, stringBuilder.toString());
            URLManager uRLManager = new URLManager();
            uRLManager.a(String.class);
            uRLManager.a(Priority.HIGH);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.c(1);
            uRLManager.a(this.l);
            uRLManager.a("https://api.gaana.com/user.php?");
            uRLManager.i(false);
            i.a().a(new af() {
                /* JADX WARNING: Removed duplicated region for block: B:18:0x00c8  */
                /* JADX WARNING: Removed duplicated region for block: B:15:0x0085  */
                public void onRetreivalComplete(java.lang.Object r6) {
                    /*
                    r5 = this;
                    r0 = com.fragments.EditProfileActivityFragment.this;
                    r0 = r0.mContext;
                    r0 = (com.gaana.BaseActivity) r0;
                    r0.hideProgressDialog();
                    r6 = (java.lang.String) r6;
                    if (r6 == 0) goto L_0x0029;
                L_0x000d:
                    r0 = new com.google.gson.Gson;
                    r0.<init>();
                    if (r6 == 0) goto L_0x0029;
                L_0x0014:
                    r1 = com.fragments.EditProfileActivityFragment.this;
                    r1 = r1.a(r6);
                    if (r1 == 0) goto L_0x0029;
                L_0x001c:
                    r6 = r6.trim();
                    r1 = com.gaana.login.UpdateEmailIdInfo.class;
                    r6 = r0.fromJson(r6, r1);
                    r6 = (com.gaana.login.UpdateEmailIdInfo) r6;
                    goto L_0x002a;
                L_0x0029:
                    r6 = 0;
                L_0x002a:
                    r0 = 0;
                    r1 = 8;
                    if (r6 == 0) goto L_0x007a;
                L_0x002f:
                    r2 = r6.getStatus();
                    r2 = r2.intValue();
                    r3 = 1;
                    if (r2 != r3) goto L_0x007a;
                L_0x003a:
                    r2 = com.managers.aj.a();
                    r3 = com.fragments.EditProfileActivityFragment.this;
                    r3 = r3.getActivity();
                    r4 = r6.getMsg();
                    r2.a(r3, r4);
                    r2 = com.fragments.EditProfileActivityFragment.this;
                    r2 = r2.u;
                    r2.setVisibility(r1);
                    r2 = com.fragments.EditProfileActivityFragment.this;
                    r2 = r2.w;
                    r2.setVisibility(r0);
                    r2 = com.fragments.EditProfileActivityFragment.this;
                    r2 = r2.s;
                    r2.setVisibility(r1);
                    r1 = com.fragments.EditProfileActivityFragment.this;
                    r1 = r1.b;
                    r6 = r6.getEmail();
                    r1.setText(r6);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6.z = r0;
                    goto L_0x0103;
                L_0x007a:
                    r2 = r6.getStatus();
                    r2 = r2.intValue();
                    r3 = 2;
                    if (r2 != r3) goto L_0x00c8;
                L_0x0085:
                    r6 = com.managers.aj.a();
                    r2 = com.fragments.EditProfileActivityFragment.this;
                    r2 = r2.getActivity();
                    r3 = com.fragments.EditProfileActivityFragment.this;
                    r3 = r3.getResources();
                    r4 = 2131822800; // 0x7f1108d0 float:1.9278382E38 double:1.0532604085E-314;
                    r3 = r3.getString(r4);
                    r6.a(r2, r3);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6 = r6.u;
                    r6.setVisibility(r1);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6 = r6.w;
                    r6.setVisibility(r1);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6 = r6.s;
                    r6.setVisibility(r0);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6 = r6.z;
                    if (r6 == 0) goto L_0x0103;
                L_0x00c2:
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6.c();
                    goto L_0x0103;
                L_0x00c8:
                    r2 = r6.getStatus();
                    r2 = r2.intValue();
                    if (r2 != 0) goto L_0x0103;
                L_0x00d2:
                    r2 = com.managers.aj.a();
                    r3 = com.fragments.EditProfileActivityFragment.this;
                    r3 = r3.getActivity();
                    r6 = r6.getError();
                    r2.a(r3, r6);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6 = r6.u;
                    r6.setVisibility(r1);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6 = r6.w;
                    r6.setVisibility(r1);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6 = r6.s;
                    r6.setVisibility(r0);
                    r6 = com.fragments.EditProfileActivityFragment.this;
                    r6.z = r0;
                L_0x0103:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.fragments.EditProfileActivityFragment$AnonymousClass2.onRetreivalComplete(java.lang.Object):void");
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    ((BaseActivity) EditProfileActivityFragment.this.mContext).handleError(businessObject.getVolleyError().getMessage());
                }
            }, uRLManager);
            return;
        }
        ap.a().f(getActivity());
    }

    private void b(String str) {
        if (Util.j(getActivity())) {
            this.l = new HashMap();
            this.l.put("type", "nxtgen_update_mobile_number");
            this.l.put(LoginManager.TAG_FB_ACCESS_TOKEN, str);
            UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
            if (currentUser != null && currentUser.getLoginStatus()) {
                this.l.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, currentUser.getAuthToken());
            }
            BaseActivity baseActivity = (BaseActivity) this.mContext;
            Boolean valueOf = Boolean.valueOf(true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.updating));
            stringBuilder.append("\t\t\t\t\t");
            baseActivity.showProgressDialog(valueOf, stringBuilder.toString());
            URLManager uRLManager = new URLManager();
            uRLManager.a(String.class);
            uRLManager.a(Priority.HIGH);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.c(1);
            uRLManager.a(this.l);
            uRLManager.a("https://api.gaana.com/user.php?");
            uRLManager.i(false);
            i.a().a(new af() {
                /* JADX WARNING: Removed duplicated region for block: B:18:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:15:0x00a0  */
                public void onRetreivalComplete(java.lang.Object r7) {
                    /*
                    r6 = this;
                    r0 = com.fragments.EditProfileActivityFragment.this;
                    r0 = r0.mContext;
                    r0 = (com.gaana.BaseActivity) r0;
                    r0.hideProgressDialog();
                    r7 = (java.lang.String) r7;
                    if (r7 == 0) goto L_0x0029;
                L_0x000d:
                    r0 = new com.google.gson.Gson;
                    r0.<init>();
                    if (r7 == 0) goto L_0x0029;
                L_0x0014:
                    r1 = com.fragments.EditProfileActivityFragment.this;
                    r1 = r1.a(r7);
                    if (r1 == 0) goto L_0x0029;
                L_0x001c:
                    r7 = r7.trim();
                    r1 = com.gaana.login.UpdateMobileNumberInfo.class;
                    r7 = r0.fromJson(r7, r1);
                    r7 = (com.gaana.login.UpdateMobileNumberInfo) r7;
                    goto L_0x002a;
                L_0x0029:
                    r7 = 0;
                L_0x002a:
                    r0 = 0;
                    r1 = 8;
                    if (r7 == 0) goto L_0x0096;
                L_0x002f:
                    r2 = r7.getStatus();
                    r2 = r2.intValue();
                    r3 = 1;
                    if (r2 != r3) goto L_0x0096;
                L_0x003a:
                    r2 = com.managers.aj.a();
                    r4 = com.fragments.EditProfileActivityFragment.this;
                    r4 = r4.getActivity();
                    r5 = r7.getMsg();
                    r2.a(r4, r5);
                    r2 = com.fragments.EditProfileActivityFragment.this;
                    r2.y = r3;
                    r2 = com.fragments.EditProfileActivityFragment.this;
                    r2 = r2.v;
                    r2.setVisibility(r1);
                    r2 = com.fragments.EditProfileActivityFragment.this;
                    r2 = r2.x;
                    r2.setVisibility(r0);
                    r0 = com.fragments.EditProfileActivityFragment.this;
                    r0 = r0.t;
                    r0.setVisibility(r1);
                    r0 = com.fragments.EditProfileActivityFragment.this;
                    r0 = r0.r;
                    r1 = new java.lang.StringBuilder;
                    r1.<init>();
                    r2 = "+";
                    r1.append(r2);
                    r2 = r7.getMobile_country_prefix();
                    r1.append(r2);
                    r2 = "-";
                    r1.append(r2);
                    r7 = r7.getMobileNumber();
                    r1.append(r7);
                    r7 = r1.toString();
                    r0.setText(r7);
                    goto L_0x00cc;
                L_0x0096:
                    r2 = r7.getStatus();
                    r2 = r2.intValue();
                    if (r2 != 0) goto L_0x00cc;
                L_0x00a0:
                    r2 = com.managers.aj.a();
                    r3 = com.fragments.EditProfileActivityFragment.this;
                    r3 = r3.getActivity();
                    r7 = r7.getError();
                    r2.a(r3, r7);
                    r7 = com.fragments.EditProfileActivityFragment.this;
                    r7 = r7.v;
                    r7.setVisibility(r1);
                    r7 = com.fragments.EditProfileActivityFragment.this;
                    r7 = r7.x;
                    r7.setVisibility(r1);
                    r7 = com.fragments.EditProfileActivityFragment.this;
                    r7 = r7.t;
                    r7.setVisibility(r0);
                L_0x00cc:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.fragments.EditProfileActivityFragment$AnonymousClass4.onRetreivalComplete(java.lang.Object):void");
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    ((BaseActivity) EditProfileActivityFragment.this.mContext).handleError(businessObject.getVolleyError().getMessage());
                    EditProfileActivityFragment.this.v.setVisibility(8);
                    EditProfileActivityFragment.this.x.setVisibility(8);
                    EditProfileActivityFragment.this.t.setVisibility(0);
                }
            }, uRLManager);
            return;
        }
        ap.a().f(getActivity());
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0006 */
    /* JADX WARNING: Failed to process nested try/catch */
    public boolean a(java.lang.String r2) {
        /*
        r1 = this;
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0006 }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x0006 }
        goto L_0x000b;
    L_0x0006:
        r0 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x000d }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x000d }
    L_0x000b:
        r2 = 1;
        return r2;
    L_0x000d:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.EditProfileActivityFragment.a(java.lang.String):boolean");
    }

    private void j() {
        final g a = g.a();
        a.a(getActivity(), new a() {
            public String OnAuthrizationSuccess() {
                String e = a.e();
                if (!e.trim().equalsIgnoreCase("") && e.trim().length() >= 2) {
                    EditProfileActivityFragment.this.c.setText(a.c());
                    EditProfileActivityFragment.this.g.setText(Util.a(a.g(), true));
                    EditProfileActivityFragment.this.f = MoEHelperConstants.GENDER_MALE.equalsIgnoreCase(a.f()) ^ 1;
                    EditProfileActivityFragment.this.b();
                    i a = i.a();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("https://graph.facebook.com/");
                    stringBuilder.append(e);
                    stringBuilder.append("/picture?type=normal");
                    a.a(stringBuilder.toString(), new r() {
                        public void onSuccessfulResponse(Bitmap bitmap) {
                            EditProfileActivityFragment.this.p = bitmap;
                            ((BaseActivity) EditProfileActivityFragment.this.mContext).hideProgressDialog();
                            if (EditProfileActivityFragment.this.p != null) {
                                EditProfileActivityFragment.this.o = EditProfileActivityFragment.this.p;
                                EditProfileActivityFragment.this.n.setImageBitmap(EditProfileActivityFragment.this.o);
                                return;
                            }
                            aj.a().a(EditProfileActivityFragment.this.mContext, EditProfileActivityFragment.this.mContext.getString(R.string.unable_to_get_photo_frm_fb));
                        }

                        public void onErrorResponse(VolleyError volleyError) {
                            aj.a().a(EditProfileActivityFragment.this.mContext, EditProfileActivityFragment.this.mContext.getString(R.string.unable_to_get_photo_frm_fb));
                        }
                    }, false);
                }
                return null;
            }

            public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                ap.a().a(EditProfileActivityFragment.this.mContext, EditProfileActivityFragment.this.mContext.getResources().getString(R.string.error_msg_no_connection));
            }
        }, false);
    }

    public void a(int i, int i2, Intent intent) {
        if (i2 == -1) {
            try {
                this.o = Constants.a(this.mContext, intent.getData());
                this.n.setImageBitmap(this.o);
                return;
            } catch (FileNotFoundException e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
        this.o = null;
    }

    private void k() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        ((GaanaActivity) this.mContext).startActivityForResult(intent, 706);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getWindow().setSoftInputMode(48);
    }
}
