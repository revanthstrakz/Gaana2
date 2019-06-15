package com.gaana.view.item;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.AppUpdateData;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.aj;
import com.player_framework.o;
import com.services.d;
import com.services.l.af;
import com.services.n;
import com.utilities.Util;
import org.json.JSONObject;

public class AppUpdaterView extends BaseItemView implements OnClickListener {
    private static final String APP_PNAME = "com.gaana";
    private AppUpdateData mAppUpdateData;
    private CustomDialogView mDialog;
    private boolean refreshUpdateMessage;

    public AppUpdaterView(Context context) {
        super(context, null);
    }

    public void checkAppUpdate() {
        String c = d.a().c("PREF_APP_UPDATE_DEATILS", false);
        this.refreshUpdateMessage = false;
        if (TextUtils.isEmpty(c)) {
            this.refreshUpdateMessage = true;
        } else {
            this.mAppUpdateData = (AppUpdateData) n.a(c);
            if (((int) ((System.currentTimeMillis() - this.mAppUpdateData.getLastUpdatedTime()) / 1000)) > 86400) {
                this.refreshUpdateMessage = true;
            }
        }
        if (this.refreshUpdateMessage) {
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/index.php?type=check_app_update");
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(String.class);
            if (Util.j(this.mContext)) {
                i.a().a(new af() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(Object obj) {
                        String str = (String) obj;
                        if (str != null) {
                            AppUpdaterView.this.mAppUpdateData = AppUpdaterView.this.getAppUpdate(str);
                            if (!TextUtils.isEmpty(AppUpdaterView.this.mAppUpdateData.getUpdatedFlag())) {
                                AppUpdaterView.this.mAppUpdateData.setLastUpdatedTime(System.currentTimeMillis());
                                d.a().a("PREF_APP_UPDATE_DEATILS", n.a(AppUpdaterView.this.mAppUpdateData), false);
                            }
                        }
                        if (AppUpdaterView.this.mContext != null) {
                            AppUpdaterView.this.showDialog(AppUpdaterView.this.refreshUpdateMessage);
                        }
                    }
                }, uRLManager);
                return;
            }
            return;
        }
        showDialog(this.refreshUpdateMessage);
    }

    private void showDialog(boolean z) {
        if (this.mAppUpdateData != null) {
            String msg = this.mAppUpdateData.getMsg();
            String updatedFlag = this.mAppUpdateData.getUpdatedFlag();
            if (!TextUtils.isEmpty(updatedFlag) && updatedFlag.compareTo(InternalAvidAdSessionContext.AVID_API_LEVEL) == 0) {
                showDialogForHardUpdate(msg);
            }
            if (!TextUtils.isEmpty(updatedFlag) && updatedFlag.compareTo("1") == 0 && z) {
                showDialogForSoftUpdate(msg);
            }
        }
    }

    private void showDialogForSoftUpdate(String str) {
        if (str == null || str.isEmpty()) {
            str = AppUpdateData.SOFT_UPDATE_DEFAULT_MSG;
        }
        String str2 = str;
        if (this.mContext != null && !((Activity) this.mContext).isFinishing()) {
            this.mDialog = new CustomDialogView(this.mContext, str2, this.mContext.getString(R.string.dlg_update_text), (int) R.layout.dialog_soft_update, new OnButtonClickListener() {
                public void onNegativeButtonClick() {
                }

                public void onPositiveButtonClick() {
                    if (AppUpdaterView.this.mDialog != null && AppUpdaterView.this.mDialog.isShowing()) {
                        AppUpdaterView.this.mDialog.dismiss();
                    }
                    try {
                        AppUpdaterView.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.gaana")));
                    } catch (ActivityNotFoundException unused) {
                        aj.a().a(AppUpdaterView.this.mContext, AppUpdaterView.this.mContext.getString(R.string.android_market_not_available));
                    }
                }
            });
            this.mDialog.setCancelable(true);
            this.mDialog.show();
        }
    }

    public void showDialogForHardUpdate(String str) {
        if (this.mContext != null && Constants.l) {
            this.mContext.setTheme(R.style.GaanaAppThemeWhite);
        }
        if (this.mView == null) {
            this.mView = super.createNewBaseView(R.layout.view_hard_update, this.mView, null);
        }
        this.mView.findViewById(R.id.hardUpdateButton).setOnClickListener(this);
        TextView textView = (TextView) this.mView.findViewById(R.id.hardTextMsg);
        if (str == null || str.isEmpty()) {
            str = AppUpdateData.HARD_UPDATE_DEFAULT_MSG;
        }
        textView.setText(Html.fromHtml(str));
        if (this.mContext != null && !((Activity) this.mContext).isFinishing()) {
            this.mDialog = new CustomDialogView(this.mContext, this.mView);
            this.mDialog.setCancelable(false);
            LoginManager.getInstance().setUserInfo(new UserInfo());
            DownloadManager.c().e();
            o.d(this.mContext);
            this.mDialog.show();
        }
    }

    public void showDialogForTermsandConditions(String str) {
        this.mView = super.createNewBaseView(R.layout.dialog_terms_condtitions, this.mView, null);
        this.mView.findViewById(R.id.image_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AppUpdaterView.this.mDialog != null && AppUpdaterView.this.mDialog.isShowing()) {
                    AppUpdaterView.this.mDialog.dismiss();
                }
            }
        });
        ((TextView) this.mView.findViewById(R.id.dialog_header_msg)).setText(Html.fromHtml(str));
        this.mDialog = new CustomDialogView(this.mContext, this.mView);
        this.mDialog.setCancelable(true);
        this.mDialog.show();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.hardUpdateButton) {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            try {
                this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.gaana")));
            } catch (ActivityNotFoundException unused) {
                aj.a().a(this.mContext, this.mContext.getString(R.string.android_market_not_available));
            }
            ((Activity) this.mContext).finish();
        }
    }

    private AppUpdateData getAppUpdate(String str) {
        this.mAppUpdateData = new AppUpdateData();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("upd_flag")) {
                str = jSONObject.getString("upd_flag");
                this.mAppUpdateData.setUpdatedFlag(str);
                if (str.compareTo(InternalAvidAdSessionContext.AVID_API_LEVEL) == 0) {
                    this.mAppUpdateData.setAppVer(Constants.cz);
                }
            }
            if (jSONObject.has("msg")) {
                this.mAppUpdateData.setMsg(jSONObject.getString("msg"));
            }
            return this.mAppUpdateData;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return this.mAppUpdateData;
        }
    }
}
