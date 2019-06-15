package com.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.actionbar.GenericBackActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.RecyclerListAdapter;
import com.gaana.adapter.RecyclerListAdapter.IAddListItemView;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.UserActivities.UserActivity;
import com.gaana.view.item.ActivityItemView;
import com.i.i;
import com.i.j;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.services.l.l;
import com.services.l.s;
import java.util.ArrayList;

public class ActivityFeedActivityFragment extends BaseGaanaFragment implements l {
    s a = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            if (ActivityFeedActivityFragment.this.j) {
                ActivityFeedActivityFragment.this.j = false;
                ActivityFeedActivityFragment.this.l.removeAllViews();
                if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                    ActivityFeedActivityFragment.this.k = true;
                } else if (!(ActivityFeedActivityFragment.this.g == null || ActivityFeedActivityFragment.this.b == null || ActivityFeedActivityFragment.this.b.getArrListBusinessObj() == null || ActivityFeedActivityFragment.this.b.getArrListBusinessObj().size() <= 0)) {
                    ArrayList arrListBusinessObj = ActivityFeedActivityFragment.this.b.getArrListBusinessObj();
                    if (businessObject.getArrListBusinessObj().size() < 20) {
                        ActivityFeedActivityFragment.this.k = true;
                    }
                    arrListBusinessObj.addAll(businessObject.getArrListBusinessObj());
                    ActivityFeedActivityFragment.this.g.setAdapterArrayList(ActivityFeedActivityFragment.this.b.getArrListBusinessObj());
                }
            }
        }
    };
    private BusinessObject b = null;
    private boolean c = false;
    private final int d = 0;
    private final int e = 1;
    private RecyclerView f;
    private RecyclerListAdapter g;
    private SwipeRefreshLayout h;
    private URLManager i = null;
    private boolean j = false;
    private boolean k = false;
    private LinearLayout l;
    private View m = null;
    private boolean n = false;
    private View o = null;

    public void loadMoreData(int i) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.n = false;
        if (this.o == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.o = setContentView(R.layout.layout_mymusic, viewGroup);
            this.f = (RecyclerView) this.o.findViewById(R.id.listViewHome);
            this.l = (LinearLayout) this.o.findViewById(R.id.loadMoreView);
            this.m = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
            a(0);
            a(false);
            this.h = (SwipeRefreshLayout) this.o.findViewById(R.id.swipe_refresh_layout);
            this.h.setOnRefreshListener(new OnRefreshListener() {
                public void onRefresh() {
                    ActivityFeedActivityFragment.this.c = true;
                    ActivityFeedActivityFragment.this.a(true);
                }
            });
            ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading_string_text));
        }
        setGAScreenName("Activity Feed", "FriendsActivityScreen");
        MoEngage.getInstance().reportSectionViewedEvent("FriendsActivity");
        setActionBar(this.o, new GenericBackActionBar(this.mContext, this.mContext.getString(R.string.friends_activity)));
        return this.o;
    }

    public void a(final int i) {
        ArrayList arrayList;
        if (i == 1) {
            arrayList = new ArrayList();
            arrayList.add(new Object());
        } else {
            arrayList = new ArrayList();
        }
        if (this.g == null) {
            this.g = new RecyclerListAdapter(this.mContext, arrayList);
        }
        this.g.setLoadMoreListner(this);
        this.g.setParamaters(i, arrayList, new IAddListItemView() {
            public int getItemViewType(int i) {
                return 1;
            }

            public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
                if (i == 0) {
                    if (obj instanceof UserActivity) {
                        return new ActivityItemView(ActivityFeedActivityFragment.this.mContext, ActivityFeedActivityFragment.this).getPoplatedView(viewHolder, (BusinessObject) obj);
                    }
                    return null;
                } else if (i == 1) {
                    return new ActivityItemView(ActivityFeedActivityFragment.this.mContext, ActivityFeedActivityFragment.this).bindEmptyView(viewHolder, ActivityFeedActivityFragment.this.mContext.getString(R.string.no_friends_activities));
                } else {
                    return null;
                }
            }
        });
        this.f.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        this.f.setItemAnimator(new DefaultItemAnimator());
        this.f.setAdapter(this.g);
    }

    public void onResponse(Object obj) {
        if (!this.n) {
            super.onResponse(obj);
            this.b = (BusinessObject) obj;
            ((GaanaActivity) this.mContext).hideProgressDialog();
            if (this.c && this.h != null) {
                this.h.setRefreshing(false);
                this.c = false;
                this.j = false;
                this.k = false;
                this.l.removeAllViews();
            }
            if (this.g == null || this.b == null || this.b.getArrListBusinessObj() == null || this.b.getArrListBusinessObj().size() <= 0) {
                a(1);
            } else {
                this.g.setAdapterArrayList(this.b.getArrListBusinessObj());
            }
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        this.c = false;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void a(boolean z) {
        i.a().a(b(z), toString(), (b) this, (a) this);
    }

    private URLManager b(boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Activities);
        uRLManager.c(Boolean.valueOf(z));
        uRLManager.a(Boolean.valueOf(true));
        uRLManager.a("https://api.gaana.com/mytimes.php?type=get_my_friends_activities&size=20");
        this.i = uRLManager;
        return uRLManager;
    }

    public void onResume() {
        super.onResume();
        updateView();
        GaanaApplication.getInstance().setCurrentPageName(getPageName());
    }

    public void onDestroyView() {
        if (this.o.getParent() != null) {
            ((ViewGroup) this.o.getParent()).removeView(this.o);
        }
        super.onDestroyView();
        this.n = true;
        j.a().a(toString());
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX WARNING: Missing block: B:18:0x005c, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:19:0x005d, code skipped:
            return;
     */
    public void loadMoreData(int r3, java.lang.Object r4) {
        /*
        r2 = this;
        r4 = r2.mAppState;
        r4 = r4.isAppInOfflineMode();
        if (r4 != 0) goto L_0x005d;
    L_0x0008:
        r4 = r2.mContext;
        r4 = com.utilities.Util.j(r4);
        if (r4 != 0) goto L_0x0011;
    L_0x0010:
        goto L_0x005d;
    L_0x0011:
        r4 = r2.i;
        if (r4 == 0) goto L_0x005c;
    L_0x0015:
        r4 = r2.i;
        r4 = r4.k();
        if (r4 == 0) goto L_0x005c;
    L_0x001d:
        r4 = r2.i;
        r4 = r4.g();
        r0 = 1;
        if (r4 != r0) goto L_0x0027;
    L_0x0026:
        goto L_0x005c;
    L_0x0027:
        r4 = r2.j;
        if (r4 != 0) goto L_0x005b;
    L_0x002b:
        r4 = r2.k;
        if (r4 != 0) goto L_0x005b;
    L_0x002f:
        r4 = 20;
        if (r3 < r4) goto L_0x005b;
    L_0x0033:
        r2.j = r0;
        r0 = r2.l;
        r0.removeAllViews();
        r0 = r2.l;
        r1 = r2.m;
        r0.addView(r1);
        r0 = r2.i;
        r1 = r2.i;
        r1 = r1.k();
        r3 = r2.a(r1, r3, r4);
        r0.a(r3);
        r3 = com.i.i.a();
        r4 = r2.a;
        r0 = r2.i;
        r3.a(r4, r0);
    L_0x005b:
        return;
    L_0x005c:
        return;
    L_0x005d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.ActivityFeedActivityFragment.loadMoreData(int, java.lang.Object):void");
    }

    private String a(String str, int i, int i2) {
        StringBuilder stringBuilder;
        if (str.contains("size")) {
            str = str.split("&size")[0];
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else if (str.contains("limit")) {
            if (str.contains("?limit")) {
                str = str.split("\\?limit")[0];
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("?limit=");
                stringBuilder.append(i);
                stringBuilder.append(",");
                stringBuilder.append(i2);
                return stringBuilder.toString();
            }
            str = str.split("&limit")[0];
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else if (str.contains("?")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("?limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        }
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.FRIEND_ACTIVITY.name();
    }
}
