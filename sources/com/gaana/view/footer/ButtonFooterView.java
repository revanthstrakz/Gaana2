package com.gaana.view.footer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Tracks.Track;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.PopupWindowView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.TrackSelectionForDownload;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.l.ar;
import com.services.l.as;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class ButtonFooterView extends BaseItemView {
    private ArrayList<String> arrTracksToBeAdded = null;
    private BaseGaanaFragment baseGaana = null;
    public boolean isVisible = false;

    public ButtonFooterView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.baseGaana = baseGaanaFragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemAdViewHolder(this.mInflater.inflate(R.layout.button_footer_view, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return populateView(i, viewHolder.itemView, new BusinessObject(), "", "");
    }

    public View populateView(int i, View view, BusinessObject businessObject, String str, String str2) {
        final TextView textView = (TextView) view.findViewById(R.id.download_songs_view);
        textView.setText(String.format(this.mContext.getResources().getString(R.string.download_songs_message), new Object[]{Integer.valueOf(TrackSelectionForDownload.a().d())}));
        TrackSelectionForDownload.a().a(new ar() {
            public void onTrackSelectionChanged(int i) {
                textView.setText(String.format(ButtonFooterView.this.mContext.getResources().getString(R.string.download_songs_message), new Object[]{Integer.valueOf(i)}));
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                final ArrayList arrayList = (ArrayList) TrackSelectionForDownload.a().e();
                if (arrayList == null || arrayList.size() <= 0) {
                    aj.a().a(ButtonFooterView.this.mContext, ButtonFooterView.this.mContext.getString(R.string.select_atleast_one_track));
                } else if (ap.a().j()) {
                    ButtonFooterView.this.startDownload(arrayList);
                } else {
                    Util.b(ButtonFooterView.this.mContext, "pl", null, new as() {
                        public void onTrialSuccess() {
                            ButtonFooterView.this.startDownload(arrayList);
                        }
                    });
                }
            }
        });
        return view;
    }

    public void startDownload(final ArrayList<BusinessObject> arrayList) {
        final ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            boolean b = d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            if (Util.k(GaanaApplication.getContext()) == 0) {
                if (!d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                    ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_over_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) ButtonFooterView.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                            ((GaanaActivity) ButtonFooterView.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                    return;
                } else if (b) {
                    if (!Constants.V) {
                        aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_songs_queue_msg));
                        Constants.V = true;
                    }
                } else if (!Constants.W) {
                    Constants.W = true;
                    aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_cta_text), this.mContext.getString(R.string.schedule_download_msg), new OnClickListener() {
                        public void onClick(View view) {
                            if ((ButtonFooterView.this.mFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) ButtonFooterView.this.mFragment).a() == 1) {
                                PopupWindowView.getInstance(ButtonFooterView.this.mContext, ButtonFooterView.this.mFragment).dismiss(true);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            PopupWindowView.getInstance(ButtonFooterView.this.mContext, ButtonFooterView.this.mFragment).dismiss(true);
                            ((GaanaActivity) ButtonFooterView.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                }
            }
            ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getResources().getString(R.string.loading));
            com.i.d.a(new Runnable() {
                public void run() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Track track;
                        BusinessObject businessObject = (BusinessObject) it.next();
                        if (businessObject instanceof Item) {
                            track = (Track) Util.g((Item) businessObject);
                        } else {
                            track = (Track) businessObject;
                        }
                        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId()));
                        if (e == null || e == DownloadStatus.TRIED_BUT_FAILED || e == DownloadStatus.PAUSED) {
                            arrayList2.add(track);
                        }
                    }
                    String str = "";
                    if (arrayList2 != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(arrayList2.size());
                        stringBuilder.append("");
                        str = stringBuilder.toString();
                    }
                    u.a().a("CuratedDownloadsPersonalized", "DownloadButtonClick", str);
                    if (arrayList2.size() > 0) {
                        if (!DownloadManager.c().a(arrayList2, -100, false)) {
                            DownloadManager.c().f();
                            DownloadManager.c().d();
                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                ((GaanaActivity) ButtonFooterView.this.mContext).popBackStackImmediate();
                                ((GaanaActivity) ButtonFooterView.this.mContext).displayDownload(R.id.my_downloads, "0", null);
                                ((BaseActivity) ButtonFooterView.this.mContext).hideProgressDialog();
                            }
                        });
                    }
                }
            });
        }
    }
}
