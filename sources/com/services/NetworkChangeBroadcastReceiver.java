package com.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.managers.DownloadManager;
import com.managers.aj;
import com.managers.l;
import com.services.l.ae;
import com.utilities.Util;
import com.utilities.Util.NETWORK_TYPE;

public class NetworkChangeBroadcastReceiver extends BroadcastReceiver {
    private static NETWORK_TYPE c = NETWORK_TYPE.NETWORK_UNKNOWN;
    private int a = -100;
    private d b;
    private NETWORK_TYPE d = NETWORK_TYPE.NETWORK_UNKNOWN;
    private boolean e = false;

    public void onReceive(final Context context, Intent intent) {
        final NETWORK_TYPE d = Util.d();
        if (c != d || !DownloadManager.c().w()) {
            if (c != d) {
                Util.a(context, "APP_WIDGET_UPDATE_ACTION", null);
            }
            c = d;
            String str = "unknown";
            if (this.b == null) {
                this.b = d.a();
            }
            switch (d) {
                case NETWORK_WI_FI:
                    str = "WIFI";
                    DownloadManager.c().d();
                    final boolean b = this.b.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true);
                    FileDownloadService.a(new ae() {
                        public void OnNetworkChangeListener(boolean z) {
                            if (z) {
                                NetworkChangeBroadcastReceiver.this.e = true;
                                if (b) {
                                    aj.a().a(context, context.getResources().getString(R.string.toast_download_restart_network_reconnect));
                                } else {
                                    aj.a().a(context, context.getResources().getString(R.string.toast_download_on_wifi_network_detected));
                                }
                            }
                            FileDownloadService.a(null);
                            NetworkChangeBroadcastReceiver.this.d = d;
                        }
                    });
                    break;
                case NETWORK_2G:
                case NETWORK_3G:
                case NETWORK_4G:
                    if (d == NETWORK_TYPE.NETWORK_4G) {
                        str = "4G";
                    } else if (d == NETWORK_TYPE.NETWORK_3G) {
                        str = "3G";
                    } else if (d == NETWORK_TYPE.NETWORK_2G) {
                        Constants.dD = false;
                        str = "2G";
                    }
                    if (!this.b.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                        if (this.e) {
                            aj.a().a(GaanaApplication.getContext(), GaanaApplication.getContext().getResources().getString(R.string.toast_download_on_data_disabled));
                            FileDownloadService.a(null);
                            this.d = d;
                        }
                        DownloadManager.c().e();
                        this.e = false;
                        break;
                    }
                    FileDownloadService.a(new ae() {
                        public void OnNetworkChangeListener(boolean z) {
                            if (z && (NetworkChangeBroadcastReceiver.this.d.equals(NETWORK_TYPE.NETWORK_NO_CONNECTION) || NetworkChangeBroadcastReceiver.this.d.equals(NETWORK_TYPE.NETWORK_WI_FI))) {
                                aj.a().a(context, context.getResources().getString(R.string.toast_download_restart_network_reconnect));
                                NetworkChangeBroadcastReceiver.this.e = true;
                            }
                            FileDownloadService.a(null);
                            NetworkChangeBroadcastReceiver.this.d = d;
                        }
                    });
                    DownloadManager.c().d();
                    break;
                case NETWORK_NO_CONNECTION:
                    if (!Util.j(context)) {
                        str = "noConnection";
                        DownloadManager.c().e();
                        FileDownloadService.a(null);
                        l.a().i();
                        this.d = d;
                        break;
                    }
                    return;
                default:
                    str = "unknown";
                    DownloadManager.c().e();
                    FileDownloadService.a(null);
                    this.e = false;
                    this.d = d;
                    break;
            }
            if (Util.c() && str.equalsIgnoreCase("2G")) {
                Constants.dD = true;
                aj.a().a(context, context.getString(R.string.slow_network_msg), true);
            }
            Constants.dC = str;
        }
    }
}
