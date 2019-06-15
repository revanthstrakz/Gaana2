package com.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.actionbar.GenericBackActionBar;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.ap;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class GetReferredFragment extends BaseGaanaFragment implements OnClickListener {
    private LayoutInflater a;
    private View b = null;
    private GenericBackActionBar c;
    private EditText d;
    private String e = "";
    private String f = "0";

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.a = getActivity().getLayoutInflater();
            this.b = setContentView(R.layout.get_referred_fragment, viewGroup);
            a();
        }
        a(getString(R.string.get_referred));
        return this.b;
    }

    private void a(String str) {
        ((GaanaActivity) this.mContext).title = str;
        if (this.c == null) {
            this.c = new GenericBackActionBar(this.mContext, Util.f(str));
        }
        setActionBar(this.b, this.c);
    }

    private void a() {
        this.d = (EditText) this.b.findViewById(R.id.referral_code_input);
        ((Button) this.b.findViewById(R.id.continue_btn)).setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.continue_btn) {
            Util.a(this.mContext, view);
            URLManager uRLManager = new URLManager();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/index.php?type=referral&subtype=use_referral_code&referral_code=");
            stringBuilder.append(this.d.getText().toString().trim());
            uRLManager.a(stringBuilder.toString());
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(String.class);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    if (obj != null) {
                        String str = (String) obj;
                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                GetReferredFragment.this.e = jSONObject.getString("message");
                                GetReferredFragment.this.f = jSONObject.getString("status");
                            } catch (JSONException e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                        }
                        if (GetReferredFragment.this.e != null && GetReferredFragment.this.e.equalsIgnoreCase("Success")) {
                            ((BaseActivity) GetReferredFragment.this.mContext).updateUserStatus(new au() {
                                public void onUserStatusUpdated() {
                                    ((BaseActivity) GetReferredFragment.this.mContext).hideProgressDialog();
                                    ap.a().a(GetReferredFragment.this.mContext);
                                    Util.aa();
                                    aj.a().a(GetReferredFragment.this.mContext, GetReferredFragment.this.mContext.getString(R.string.enjoy_using_gaana_plus));
                                    Intent intent = new Intent(GetReferredFragment.this.mContext, GaanaActivity.class);
                                    intent.setFlags(71303168);
                                    GetReferredFragment.this.mContext.startActivity(intent);
                                }
                            });
                        } else if (TextUtils.isEmpty(GetReferredFragment.this.e)) {
                            aj.a().a(GetReferredFragment.this.mContext, GetReferredFragment.this.mContext.getString(R.string.try_again_later));
                        } else {
                            aj.a().a(GetReferredFragment.this.mContext, GetReferredFragment.this.e);
                        }
                    }
                }
            }, uRLManager);
        }
    }

    public void onDestroyView() {
        if (!(this.b == null || this.b.getParent() == null)) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
    }
}
