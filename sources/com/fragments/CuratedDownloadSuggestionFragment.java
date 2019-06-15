package com.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Tracks.Track;
import com.gaana.view.BaseItemView;
import com.gaana.view.GaanaListView.OnDataLoadedListener;
import com.gaana.view.item.CuratedDownloadScrollView;
import com.gaana.view.item.PopupWindowView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.ColombiaManager;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.TrackSelectionForDownload;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aa;
import com.managers.aj;
import com.managers.ap;
import com.managers.g;
import com.managers.u;
import com.services.f;
import com.services.f.b;
import com.services.l.ar;
import com.services.l.as;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class CuratedDownloadSuggestionFragment extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, d, IAddListItemView, OnDataLoadedListener {
    private boolean a = false;
    private GaanaApplication b;
    private boolean c = false;
    private ObservableRecyclerView d = null;
    private CustomListAdapter e = null;
    private DisplayMetrics f;
    private View g = null;
    private ArrayList<BaseItemView> h = null;
    private DetailsMaterialActionBar i;
    private Toolbar j;
    private boolean k = false;
    private boolean l = false;
    private g m;

    private void e() {
    }

    public int getItemViewType(int i) {
        return i;
    }

    public void onDataLoaded(BusinessObject businessObject, BusinessObjectType businessObjectType) {
    }

    public void onDownMotionEvent() {
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    public void showHideEmtpyView(boolean z) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.f);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.l = arguments.getBoolean("isFromDownloads", false);
        } else {
            this.l = false;
        }
        this.k = false;
        if (this.g == null || this.loginStatus != this.b.getCurrentUser().getLoginStatus()) {
            CharSequence string;
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.g = setContentView(R.layout.curated_suggestion_fragment, viewGroup);
            this.a = true;
            this.b = GaanaApplication.getInstance();
            this.mContext = getActivity();
            this.m = g.a();
            this.j = (Toolbar) this.g.findViewById(R.id.main_toolbar);
            this.i = new DetailsMaterialActionBar(this.mContext);
            this.i.setParams(this, new BusinessObject());
            this.i.showContextMenu(false);
            this.j.addView(this.i);
            this.j.setContentInsetsAbsolute(0, 0);
            c();
            this.i.setToolbar(this.j);
            if (Constants.l) {
                this.i.getTitleTextView().setTextColor(ViewCompat.MEASURED_STATE_MASK);
                ((ImageView) this.i.findViewById(R.id.menu_icon)).setImageResource(R.drawable.vector_ab_back);
                this.j.getMenu().findItem(R.id.searchview_actionbar).setIcon(R.drawable.vector_bottom_nav_search);
            }
            final TextView textView = (TextView) this.g.findViewById(R.id.download_songs_view);
            int d = TrackSelectionForDownload.a().d();
            String string2 = this.mContext.getResources().getString(R.string.download_camelcase);
            StringBuilder stringBuilder;
            if (d == 0) {
                string = this.mContext.getString(R.string.select_song_txt);
            } else if (d == 1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(string2);
                stringBuilder.append(" ");
                stringBuilder.append(d);
                stringBuilder.append(this.mContext.getResources().getString(R.string.song_text));
                string = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(string2);
                stringBuilder.append(" ");
                stringBuilder.append(d);
                stringBuilder.append(" ");
                stringBuilder.append(this.mContext.getResources().getString(R.string.songs_text));
                string = stringBuilder.toString();
            }
            textView.setText(string);
            textView.setOnClickListener(this);
            TrackSelectionForDownload.a().a(new ar() {
                public void onTrackSelectionChanged(int i) {
                    CharSequence string;
                    String string2 = CuratedDownloadSuggestionFragment.this.mContext.getResources().getString(R.string.download_camelcase);
                    StringBuilder stringBuilder;
                    if (i == 0) {
                        string = CuratedDownloadSuggestionFragment.this.mContext.getString(R.string.select_song_txt);
                    } else if (i == 1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(string2);
                        stringBuilder.append(" ");
                        stringBuilder.append(i);
                        stringBuilder.append(CuratedDownloadSuggestionFragment.this.mContext.getResources().getString(R.string.song_text));
                        string = stringBuilder.toString();
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(string2);
                        stringBuilder.append(" ");
                        stringBuilder.append(i);
                        stringBuilder.append(" ");
                        stringBuilder.append(CuratedDownloadSuggestionFragment.this.mContext.getResources().getString(R.string.songs_text));
                        string = stringBuilder.toString();
                    }
                    textView.setText(string);
                }
            });
            this.d = (ObservableRecyclerView) this.g.findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
            linearLayoutManager.setAutoMeasureEnabled(false);
            linearLayoutManager.setItemPrefetchEnabled(false);
            this.d.setHasFixedSize(true);
            this.d.setLayoutManager(linearLayoutManager);
            this.d.setItemViewCacheSize(7);
            this.d.setScrollViewCallbacks(this);
            this.e = new CustomListAdapter(this.mContext, null);
            this.h = this.m.a(this.mContext, this);
            TrackSelectionForDownload.a();
        } else if (!(this.d == null || this.d.getAdapter() == null)) {
            this.d.getAdapter().notifyDataSetChanged();
        }
        this.b.setNetworkExtrasBundle();
        setGAScreenName("CuratedDownload_Screen", "Suggestion");
        aa.a().b(this.a);
        return this.g;
    }

    private void c() {
        this.j.getMenu().clear();
        this.j.inflateMenu(R.menu.cast_menu_generic_back);
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void a() {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            if (view instanceof CuratedDownloadScrollView) {
                ((CuratedDownloadScrollView) view).notifyDataSetChanged();
            }
        }
    }

    public void b() {
        if (!this.c) {
            ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
        }
        d();
    }

    private void d() {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            ((BaseItemView) it.next()).setIsToBeRefreshed(this.c);
        }
        ((GaanaActivity) this.mContext).hideProgressDialog();
        if (!this.c) {
            ((BaseActivity) this.mContext).resetLoginStatus();
            this.e.setParamaters(a(this.mContext, (BaseGaanaFragment) this), this);
            this.d.setAdapter(this.e);
        }
    }

    public void onResume() {
        if (((BaseActivity) this.mContext).hasLoginChanged().booleanValue() || this.a) {
            b();
            this.a = false;
        }
        if (!TextUtils.isEmpty(this.b.getPromorUrl())) {
            Intent intent = new Intent(this.mContext, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", this.b.getPromorUrl());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            this.mContext.startActivity(intent);
            this.b.setPromoUrl(null);
        }
        updateView();
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        if (this.g.getParent() != null) {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        }
        super.onDestroyView();
        if (this.h != null) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                BaseItemView baseItemView = (BaseItemView) it.next();
                if (baseItemView != null) {
                    baseItemView.setFirstCall(true);
                }
            }
        }
        TrackSelectionForDownload.b();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return a(this.mContext, this, i).getPopulatedView(i, viewHolder, (ViewGroup) viewHolder.itemView.getParent());
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        return a(this.mContext, this, i).onCreateViewHolder(viewGroup, i);
    }

    public void onRefresh() {
        if (this.d != null && this.d.getAdapter() != null) {
            this.c = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
            }
            b();
            e();
            this.c = false;
        }
    }

    private BaseItemView a(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        if (this.h == null) {
            this.h = this.m.a(context, baseGaanaFragment);
        }
        return (BaseItemView) this.h.get(i);
    }

    private int a(Context context, BaseGaanaFragment baseGaanaFragment) {
        if (this.h == null) {
            this.h = this.m.a(context, baseGaanaFragment);
        }
        return this.h.size();
    }

    public void notifyItemChanged(int i) {
        if (this.e != null) {
            this.e.notifyItemChanged(i);
        }
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.CURATED_DOWNLOAD_SUGGESTION.name();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.download_songs_view && !this.k) {
            final ArrayList arrayList = (ArrayList) TrackSelectionForDownload.a().e();
            if (arrayList == null || arrayList.size() <= 0) {
                aj.a().a(this.mContext, this.mContext.getString(R.string.select_atleast_one_track));
            } else if (ap.a().j()) {
                a(arrayList);
            } else {
                Util.b(this.mContext, "pl", null, new as() {
                    public void onTrialSuccess() {
                        CuratedDownloadSuggestionFragment.this.a(arrayList);
                    }
                });
            }
        }
    }

    public void a(final ArrayList<BusinessObject> arrayList) {
        Util.i(this.mContext, "Download");
        final ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            boolean b = com.services.d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            if (Util.k(GaanaApplication.getContext()) == 0) {
                if (!com.services.d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                    ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_over_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) CuratedDownloadSuggestionFragment.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                            ((GaanaActivity) CuratedDownloadSuggestionFragment.this.mContext).displayFragment(settingsDetailFragment);
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
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            PopupWindowView.getInstance(CuratedDownloadSuggestionFragment.this.mContext, CuratedDownloadSuggestionFragment.this).dismiss(true);
                            ((GaanaActivity) CuratedDownloadSuggestionFragment.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                }
            }
            ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getResources().getString(R.string.loading));
            com.i.d.a(new Runnable() {
                public void run() {
                    if (!CuratedDownloadSuggestionFragment.this.k) {
                        CuratedDownloadSuggestionFragment.this.k = true;
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
                                    CuratedDownloadSuggestionFragment.this.k = false;
                                    ((GaanaActivity) CuratedDownloadSuggestionFragment.this.mContext).popBackStackImmediate();
                                    if (!CuratedDownloadSuggestionFragment.this.l) {
                                        ((GaanaActivity) CuratedDownloadSuggestionFragment.this.mContext).displayDownload(R.id.my_downloads, "0", null, SortOrder.DownloadTime, null);
                                    }
                                    ((BaseActivity) CuratedDownloadSuggestionFragment.this.mContext).hideProgressDialog();
                                }
                            });
                        }
                    }
                }
            });
        }
    }
}
