package com.gaana.view.item;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.managers.u;
import com.services.d;

public class DownloadSyncPopupItemView extends BaseItemView implements OnClickListener {
    private CustomDialogView mDialog;

    public DownloadSyncPopupItemView(Context context) {
        super(context, null);
    }

    public void showDownloadSyncWelcomeScreenDialog() {
        if (this.mView == null) {
            this.mView = super.createNewBaseView(R.layout.view_download_sync_welcome_popup, this.mView, null);
        }
        u.a().a("Auto Sync", "Auto Sync Pop Up", "Shown");
        d.a().b("PREFERENCE_KEY_AUTO_SYNC_LAST_SHOWN", false);
        d.a().a(System.currentTimeMillis(), "PREFERENCE_KEY_AUTO_SYNC_LAST_SHOWN", false);
        this.mView.findViewById(R.id.manageSyncSettingsNow).setOnClickListener(this);
        this.mView.findViewById(R.id.close_button).setOnClickListener(this);
        this.mDialog = new CustomDialogView(this.mContext, this.mView);
        this.mDialog.show();
    }

    public void showDownloadSyncSuccessDialog(int i, int i2) {
        if (this.mView == null) {
            this.mView = super.createNewBaseView(R.layout.view_download_sync_success_popup, this.mView, null);
        }
        this.mView.findViewById(R.id.goToDownloads).setOnClickListener(this);
        this.mView.findViewById(R.id.close_button).setOnClickListener(this);
        ((TextView) this.mView.findViewById(R.id.textDownloadSyncCount)).setText(String.format(this.mContext.getResources().getString(R.string.download_sync_count_message), new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        this.mDialog = new CustomDialogView(this.mContext, this.mView);
        this.mDialog.show();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.close_button) {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            u.a().a("Auto Sync", "Auto Sync Pop Up", "Closed");
        } else if (id == R.id.goToDownloads) {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            this.mAppState.setSidebarActiveBtn(R.id.MyMusicMenuDownloads);
            ((GaanaActivity) this.mContext).displayDownload(-1, null, null);
        } else if (id == R.id.manageSyncSettingsNow) {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            if (!((GaanaActivity) this.mContext).isDownloadSyncReceiverRegistered()) {
                ((GaanaActivity) this.mContext).addDownloadSyncReceiver();
            }
            u.a().a("Auto Sync", "Auto Sync Pop Up", "Clicked to Activate");
            Bundle bundle = new Bundle();
            bundle.putBoolean("isFromAutoSyncPopup", true);
            bundle.putInt("KEY_SETTINGS", 1);
            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
            settingsDetailFragment.setArguments(bundle);
            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
        }
    }
}
