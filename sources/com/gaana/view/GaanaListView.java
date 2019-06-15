package com.gaana.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.adapter.GaanaViewAdapterNew;
import com.gaana.adapter.GaanaViewAdapterNew.OnGetViewCalledListener;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.UserActivities.UserActivity;
import com.gaana.view.item.BaseItemView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.services.l.ap;
import com.services.l.l;
import com.services.l.s;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GaanaListView implements l {
    private boolean hasDataEnded = false;
    private int listViewRowCount;
    private GaanaViewAdapterNew mAdapter = null;
    private GaanaApplication mAppState;
    private BaseItemView mBaseItemView;
    private BusinessObject mBusinessObject;
    private ArrayList<BusinessObject> mBusinessObjectList;
    private String mClassName;
    private int mColumnCount = 0;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private LayoutInflater mInflater = null;
    public boolean mIsQueryDone = true;
    private RecyclerView mListViewHome;
    public View mLoadMoreProgressBar;
    private View mLoadingBar;
    private LinearLayout mLoadingLayout;
    private int mStartIndex = 0;
    private ap mSwipeRefreshListener = null;
    private View mView = null;
    s onLoadMoreDataFinished = new s() {
        public void onRetreivalComplete(BusinessObject businessObject) {
            GaanaListView.this.onLoadMoreComplete();
            GaanaListView.this.mIsQueryDone = true;
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                GaanaListView.this.hasDataEnded = true;
                return;
            }
            if (businessObject.getArrListBusinessObj().size() < 20) {
                GaanaListView.this.hasDataEnded = true;
            }
            GaanaListView.this.updateGridViewContent(businessObject);
        }

        public void onErrorResponse(BusinessObject businessObject) {
            GaanaListView.this.onLoadMoreComplete();
            GaanaListView.this.mIsQueryDone = true;
        }
    };
    private SwipeRefreshLayout swipeRefreshLayout = null;
    boolean updatingGridViewContent = false;

    public interface OnDataLoadedListener {
        void onDataLoaded(BusinessObject businessObject, BusinessObjectType businessObjectType);
    }

    public void loadMoreData(int i) {
    }

    public GaanaListView(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mView = this.mInflater.inflate(R.layout.view_gaana_recycler_listview, null);
        this.mListViewHome = (RecyclerView) this.mView.findViewById(R.id.vertical_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        linearLayoutManager.setAutoMeasureEnabled(false);
        this.mListViewHome.setHasFixedSize(true);
        this.mListViewHome.setLayoutManager(linearLayoutManager);
        this.mLoadingLayout = (LinearLayout) this.mView.findViewById(R.id.ll_loading_row);
        this.mLoadingBar = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
        this.swipeRefreshLayout = (SwipeRefreshLayout) this.mView.findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                GaanaListView.this.mSwipeRefreshListener.onSwipeRefresh();
            }
        });
        this.mAppState = GaanaApplication.getInstance();
    }

    public void onRefreshCompleted() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    public void setSwipeRefreshListener(ap apVar) {
        this.mSwipeRefreshListener = apVar;
        this.swipeRefreshLayout.setEnabled(true);
    }

    public void setBusinessObject(BusinessObject businessObject) {
        this.mBusinessObject = businessObject;
    }

    public void updateGridViewContent(BusinessObject businessObject) {
        int size = (this.mBusinessObject.getArrListBusinessObj().size() / this.mColumnCount) + (this.mBusinessObject.getArrListBusinessObj().size() % this.mColumnCount);
        this.mBusinessObjectList = this.mBusinessObject.getArrListBusinessObj();
        this.listViewRowCount = this.mStartIndex + size;
        this.updatingGridViewContent = true;
        this.mAdapter.updateGaanaAdapter(this.listViewRowCount);
        this.updatingGridViewContent = false;
    }

    public void onLoadMoreStarted() {
        this.mLoadingLayout.removeAllViews();
        this.mLoadingLayout.addView(this.mLoadingBar);
    }

    public void onLoadMoreComplete() {
        this.mLoadingLayout.removeAllViews();
    }

    /* JADX WARNING: Missing block: B:12:0x004c, code skipped:
            return;
     */
    public void loadMoreData(int r3, java.lang.Object r4) {
        /*
        r2 = this;
        r0 = r2.mAppState;
        r0 = r0.isAppInOfflineMode();
        if (r0 != 0) goto L_0x004c;
    L_0x0008:
        r0 = r2.mContext;
        r0 = com.utilities.Util.j(r0);
        if (r0 != 0) goto L_0x0011;
    L_0x0010:
        goto L_0x004c;
    L_0x0011:
        r0 = r2.hasDataEnded;
        if (r0 != 0) goto L_0x004b;
    L_0x0015:
        r0 = r2.mIsQueryDone;
        if (r0 == 0) goto L_0x004b;
    L_0x0019:
        r0 = r2.getUrlManager();
        r0 = r0.k();
        r1 = 20;
        r3 = r2.getNewUrl(r0, r3, r1, r4);
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x004b;
    L_0x002d:
        r2.onLoadMoreStarted();
        r4 = 0;
        r2.mIsQueryDone = r4;
        r0 = r2.getUrlManager();
        r0.a(r3);
        r3 = com.i.i.a();
        r0 = r2.onLoadMoreDataFinished;
        r1 = r2.getUrlManager();
        r4 = java.lang.Boolean.valueOf(r4);
        r3.a(r0, r1, r4);
    L_0x004b:
        return;
    L_0x004c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.GaanaListView.loadMoreData(int, java.lang.Object):void");
    }

    public View getPopulatedView() {
        return this.mView;
    }

    public GaanaViewAdapterNew getListAdapter() {
        return this.mAdapter;
    }

    public void setAdapterParams(Context context, int i, OnGetViewCalledListener onGetViewCalledListener) {
        this.listViewRowCount = i;
        this.mAdapter = new GaanaViewAdapterNew(this.mContext, i, onGetViewCalledListener);
        this.mListViewHome.setAdapter(this.mAdapter);
    }

    public URLManager getUrlManager() {
        return this.mBusinessObject.getUrlManager();
    }

    private String getNewUrl(String str, int i, int i2, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        str = removeTokenFromUrl(str);
        StringBuilder stringBuilder;
        if (obj != null && (obj instanceof UserActivity)) {
            UserActivity userActivity = (UserActivity) obj;
            StringBuilder stringBuilder2;
            if (str.contains("last_seen_id")) {
                str = str.substring(0, str.lastIndexOf("&"));
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("&last_seen_id=");
                stringBuilder2.append(userActivity.getActivityId());
                return stringBuilder2.toString();
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("&last_seen_id=");
            stringBuilder2.append(userActivity.getActivityId());
            return stringBuilder2.toString();
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

    private String removeTokenFromUrl(String str) {
        String str2 = null;
        for (String str3 : str.split("&")) {
            String str4 = str3.split("=")[0];
            if (str4.compareToIgnoreCase(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE) == 0) {
                str2 = str3.split("=")[1];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("&");
                stringBuilder.append(str4);
                stringBuilder.append("=");
                stringBuilder.append(str2);
                str2 = str.replace(stringBuilder.toString(), " ").trim();
            }
        }
        return str2 == null ? str : str2;
    }

    public BusinessObject getBusinessObject() {
        return this.mBusinessObject;
    }

    public void addGridListContent(BusinessObject businessObject, int i, int i2, String str) {
        this.mClassName = str;
        this.mBusinessObject = businessObject;
        this.mBusinessObjectList = this.mBusinessObject.getArrListBusinessObj();
        this.mColumnCount = i2;
        this.mIsQueryDone = true;
        this.hasDataEnded = false;
        this.mBaseItemView = null;
        try {
            this.mBaseItemView = (BaseItemView) Class.forName(this.mClassName).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this.mFragment});
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (InvocationTargetException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (NoSuchMethodException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (InstantiationException e4) {
            ThrowableExtension.printStackTrace(e4);
        } catch (IllegalAccessException e5) {
            ThrowableExtension.printStackTrace(e5);
        }
        this.mStartIndex = i;
        if (this.mBusinessObjectList != null && this.mBusinessObjectList.size() > 0) {
            int size = (this.mBusinessObjectList.size() / this.mColumnCount) + (this.mBusinessObjectList.size() % this.mColumnCount);
            if (this.mAdapter != null) {
                this.mAdapter.updateGaanaAdapter(this.mStartIndex + size);
            }
            this.mLoadMoreProgressBar = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
        }
    }

    public void populateGridItem(int i, View view, ViewHolder viewHolder) {
        ArrayList arrayList = new ArrayList();
        int i2 = i - this.mStartIndex;
        if (this.mBusinessObjectList != null && this.mBusinessObjectList.size() != 0) {
            int i3 = i2 * 2;
            if (i3 < this.mBusinessObjectList.size()) {
                BusinessObject businessObject = new BusinessObject();
                arrayList.add(0, this.mBusinessObjectList.get(i3));
                i3++;
                if (i3 < this.mBusinessObjectList.size()) {
                    arrayList.add(1, this.mBusinessObjectList.get(i3));
                }
                businessObject.setArrList(arrayList);
                this.mBaseItemView.setItemPosition(i2 + 1);
                this.mBaseItemView.getPoplatedView(viewHolder, businessObject, null, i == this.mStartIndex, Boolean.valueOf(false));
            }
            if (this.mAdapter != null && i == this.mAdapter.getItemCount() - 1 && this.mBusinessObject.getUrlManager().e().booleanValue()) {
                loadMoreData(this.mBusinessObjectList.size(), this.mBusinessObjectList.get(this.mBusinessObjectList.size() - 1));
            }
        }
    }
}
