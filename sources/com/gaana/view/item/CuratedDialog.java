package com.gaana.view.item;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.View.OnClickListener;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.managers.ap;
import com.managers.g;
import com.managers.u;
import com.services.d;
import com.utilities.Util;

public class CuratedDialog extends BaseItemView implements OnClickListener {
    private CustomDialogView mDialog;

    public CuratedDialog(Context context) {
        super(context, null);
    }

    public void showCuratedDialog() {
        if (this.mView == null) {
            this.mView = super.createNewBaseView(R.layout.curated__download_view, this.mView, null);
        }
        this.mView.findViewById(R.id.curated_download_cta).setOnClickListener(this);
        this.mDialog = new CustomDialogView(this.mContext, this.mView);
        this.mDialog.setCancelable(true);
        this.mDialog.setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                Constants.aj = Constants.al;
                d.a().a("PREFERENCE_SESSION_TRIAL_COUNT", GaanaApplication.sessionHistoryCount, true);
                d.a().b("PREFERENCE_CURATED_DIALOG_CLOSED", true);
                d.a().a("PREFERENCE_CURATED_DIALOG_CLOSED", true, true);
                u.a().a("Curated", "Curated Pop Up", "Closed");
            }
        });
        this.mDialog.show();
    }

    public CustomDialogView isCuratedDialogShowing() {
        return (this.mDialog == null || !this.mDialog.isShowing()) ? null : this.mDialog;
    }

    public void dismissDialog() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            this.mDialog.dismiss();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.curated_download_cta) {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
            } else if (Util.j(this.mContext)) {
                u.a().a("Curated", "Curated Pop Up", "Download Now Click");
                d.a().b("PREFERENCE_CURATED_DIALOG_SHOWN", true);
                d.a().a("PREFERENCE_CURATED_DIALOG_SHOWN", true, true);
                g.a(this.mContext, null, null);
            } else {
                ap.a().f(this.mContext);
            }
        }
    }
}
