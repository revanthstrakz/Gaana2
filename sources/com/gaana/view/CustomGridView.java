package com.gaana.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.GridActivityFragment;
import com.gaana.R;
import com.gaana.adapter.CustomGridViewAdapter;
import com.gaana.adapter.CustomGridViewAdapter.OnGetViewCalledListner;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Items;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.aq;
import com.services.l.l;
import com.services.l.p;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CustomGridView implements a, b<Object>, l {
    private boolean hasDataEnded = false;
    private boolean isRefreshing = false;
    public boolean isVideoAdLoaded = false;
    private boolean loadMoreProcessing = false;
    private CustomGridViewAdapter mAdapter;
    private GaanaApplication mAppState;
    private String mBannerAdImage = "";
    private BaseItemView mBaseItemView;
    private BusinessObject mBusinessObject;
    private int mColumnCount = 0;
    private Context mContext;
    BaseGaanaFragment mFragment;
    private View mGridFooterView = null;
    private GridLayoutManager mGridLayoutManager;
    private RecyclerView mGridView;
    private String mHeaderText;
    private LayoutInflater mInflater;
    private p mOnAdRefreshListener = null;
    private OnBusinessObjectRetrievedCallback mOnBusinessObjectRetrievedCallback;
    private OnGetViewCallback mOnGetViewCallback = null;
    private LinearLayout mParentListViewTabs;
    private ProgressBar mParentLoading;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private URLManager mURLManager = null;
    private View mView;
    private String mViewTypeName = "";
    b<Object> onLoadMoreDataFinished = new b<Object>() {
        public void onResponse(Object obj) {
            if (CustomGridView.this.loadMoreProcessing) {
                CustomGridView.this.loadMoreProcessing = false;
                CustomGridView.this.mParentListViewTabs.removeAllViews();
                if (obj instanceof BusinessObject) {
                    BusinessObject businessObject = (BusinessObject) obj;
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                        CustomGridView.this.hasDataEnded = true;
                    } else if (CustomGridView.this.mOnBusinessObjectRetrievedCallback != null) {
                        CustomGridView.this.mOnBusinessObjectRetrievedCallback.onBusinessObjectRetrieved(businessObject, true);
                    } else {
                        ArrayList arrListBusinessObj = CustomGridView.this.mBusinessObject.getArrListBusinessObj();
                        if (businessObject.getArrListBusinessObj().size() < 20) {
                            CustomGridView.this.hasDataEnded = true;
                        }
                        arrListBusinessObj.addAll(businessObject.getArrListBusinessObj());
                        CustomGridView.this.updateGridContent(CustomGridView.this.mBusinessObject.getArrListBusinessObj().size());
                    }
                } else {
                    CustomGridView.this.hasDataEnded = true;
                }
            }
        }
    };
    private int tagSize = 0;

    public interface OnBusinessObjectRetrievedCallback {
        void onBusinessObjectRetrieved(BusinessObject businessObject, boolean z);
    }

    public interface OnGetViewCallback {
        View onGetViewCalled(ViewHolder viewHolder, View view, BusinessObject businessObject, ViewGroup viewGroup, int i);
    }

    public void loadMoreData(int i, Object obj) {
    }

    public RecyclerView getmGridView() {
        return this.mGridView;
    }

    public CustomGridView(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mView = this.mInflater.inflate(R.layout.view_custom_grid_view, null);
        this.mAppState = GaanaApplication.getInstance();
        this.mGridView = (RecyclerView) this.mView.findViewById(R.id.listViewHome);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) this.mView.findViewById(R.id.grid_swipe_refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                CustomGridView.this.isVideoAdLoaded = false;
                CustomGridView.this.refreshList();
            }
        });
        this.mGridLayoutManager = new GridLayoutManager(this.mContext, 2);
        this.mGridView.setLayoutManager(this.mGridLayoutManager);
        this.mParentListViewTabs = (LinearLayout) this.mView.findViewById(R.id.llCustomListViewTabs);
        this.mParentLoading = (ProgressBar) this.mView.findViewById(R.id.llParentLoading);
        this.mGridFooterView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
    }

    public CustomGridView(Context context, BaseGaanaFragment baseGaanaFragment, ViewGroup viewGroup) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mView = this.mInflater.inflate(R.layout.view_custom_grid_view, viewGroup, false);
        this.mAppState = GaanaApplication.getInstance();
        this.mGridView = (RecyclerView) this.mView.findViewById(R.id.listViewHome);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) this.mView.findViewById(R.id.grid_swipe_refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                CustomGridView.this.isVideoAdLoaded = false;
                CustomGridView.this.refreshList();
            }
        });
        this.mGridLayoutManager = new GridLayoutManager(this.mContext, 2);
        this.mGridView.setLayoutManager(this.mGridLayoutManager);
        this.mParentListViewTabs = (LinearLayout) this.mView.findViewById(R.id.llCustomListViewTabs);
        this.mParentLoading = (ProgressBar) this.mView.findViewById(R.id.llParentLoading);
        this.mGridFooterView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.list_loading_row, null);
    }

    public void setHeaderImageItem(String str) {
        this.mBannerAdImage = str;
    }

    public GridLayoutManager getGridLayoutManager() {
        return this.mGridLayoutManager;
    }

    public void setViewTypeName(String str) {
        this.mViewTypeName = str;
    }

    public void setOnAdRefreshListener(p pVar) {
        this.mOnAdRefreshListener = pVar;
    }

    public void setNumColumns(int i) {
        this.mColumnCount = i;
        this.mGridLayoutManager.setSpanCount(i);
    }

    public void setOnBusinessObjectRetrievedCallback(OnBusinessObjectRetrievedCallback onBusinessObjectRetrievedCallback) {
        this.mOnBusinessObjectRetrievedCallback = onBusinessObjectRetrievedCallback;
    }

    public void seOnGetViewCallback(OnGetViewCallback onGetViewCallback) {
        this.mOnGetViewCallback = onGetViewCallback;
    }

    public void setTagCount(int i) {
        this.tagSize = i;
    }

    public void setViewClassName(String str) {
        try {
            this.mBaseItemView = (BaseItemView) Class.forName(str).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this.mFragment});
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchMethodException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (IllegalArgumentException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (InstantiationException e4) {
            ThrowableExtension.printStackTrace(e4);
        } catch (IllegalAccessException e5) {
            ThrowableExtension.printStackTrace(e5);
        } catch (InvocationTargetException e6) {
            ThrowableExtension.printStackTrace(e6);
        }
    }

    public void setAdapterParams(int i, OnGetViewCalledListner onGetViewCalledListner) {
        boolean z;
        if ((this.mFragment instanceof GridActivityFragment) || (this.mFragment instanceof DiscoverDetailFragment)) {
            z = true;
        } else {
            z = false;
        }
        this.mAdapter = new CustomGridViewAdapter(i, this.mColumnCount, onGetViewCalledListner, this.mContext, this.mFragment, z, this.mGridView);
        this.mAdapter.setAdSectionName(getSectionName());
        if (this.mBusinessObject != null && (this.mBusinessObject instanceof Items)) {
            this.mAdapter.setSectionType(((Items) this.mBusinessObject).getTagDescription());
            this.mAdapter.insertAdSpots();
        }
        this.mAdapter.initializeAdPositions(0, i);
        if (this.mURLManager != null && this.mURLManager.e().booleanValue()) {
            this.mAdapter.setOnLoadMoreListener(this);
        }
        this.mGridView.setLayoutManager(this.mGridLayoutManager);
        this.mGridView.setAdapter(this.mAdapter);
    }

    public void setIsVideoAdLoaded(boolean z) {
        this.isVideoAdLoaded = z;
        if (this.mAdapter != null) {
            this.mAdapter.setIsVideoAdLoaded(z);
        }
    }

    private String getSectionName() {
        if (!(this.mFragment instanceof GridActivityFragment)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("VIEW_ALL_");
        stringBuilder.append(((GridActivityFragment) this.mFragment).d());
        return stringBuilder.toString();
    }

    public void updateGridContent(int i) {
        if (this.mAdapter != null) {
            if (this.isVideoAdLoaded) {
                i++;
            }
            this.mAdapter.setCount(i);
        }
    }

    public void updateGridContent() {
        if (this.mBusinessObject != null && this.mBusinessObject.getArrListBusinessObj() != null) {
            updateGridContent(this.mBusinessObject.getArrListBusinessObj().size());
        }
    }

    public void disablePulltoRefresh() {
        if (this.mSwipeRefreshLayout != null) {
            this.mSwipeRefreshLayout.setEnabled(false);
        }
    }

    public void updateGridView(URLManager uRLManager) {
        this.mURLManager = uRLManager;
        this.mParentLoading.setVisibility(0);
        this.mSwipeRefreshLayout.setRefreshing(false);
        if (this.mURLManager.j() == null || this.mURLManager.j() != UserRecentActivity.class) {
            i.a().a(uRLManager, this.mFragment.toString(), (b) this, (a) this);
        } else if (this.mViewTypeName.equals(DynamicViewType.user_radio_activity.name())) {
            aq.a().b(this.mURLManager, new aq.a() {
                public void OnUserRecentActivityFetched(UserRecentActivity userRecentActivity) {
                    CustomGridView.this.onResponse(userRecentActivity);
                }

                public void OnUserRecentActivityErrorResponse(VolleyError volleyError) {
                    CustomGridView.this.onErrorResponse(volleyError);
                }
            });
        } else {
            aq.a().a(this.mURLManager, new aq.a() {
                public void OnUserRecentActivityFetched(UserRecentActivity userRecentActivity) {
                    CustomGridView.this.onResponse(userRecentActivity);
                }

                public void OnUserRecentActivityErrorResponse(VolleyError volleyError) {
                    CustomGridView.this.onErrorResponse(volleyError);
                }
            });
        }
    }

    private void populateGridContent(int i) {
        setAdapterParams(i, new OnGetViewCalledListner() {
            public View onGetViewCalled(ViewHolder viewHolder, View view, int i, ViewGroup viewGroup) {
                if (viewHolder instanceof ItemAdViewHolder) {
                    return viewHolder.itemView;
                }
                if (CustomGridView.this.mOnGetViewCallback == null || i >= CustomGridView.this.mBusinessObject.getArrListBusinessObj().size()) {
                    if (CustomGridView.this.mBusinessObject instanceof Items) {
                        return CustomGridView.this.mBaseItemView.getPoplatedView(viewHolder, (BusinessObject) ((Items) CustomGridView.this.mBusinessObject).getArrListBusinessObj().get(i), viewGroup);
                    }
                    return CustomGridView.this.mBaseItemView.getPoplatedView(viewHolder, (BusinessObject) CustomGridView.this.mBusinessObject.getArrListBusinessObj().get(i), viewGroup);
                } else if (CustomGridView.this.mBusinessObject instanceof Items) {
                    return CustomGridView.this.mOnGetViewCallback.onGetViewCalled(viewHolder, view, (BusinessObject) ((Items) CustomGridView.this.mBusinessObject).getArrListBusinessObj().get(i), viewGroup, i);
                } else {
                    return CustomGridView.this.mOnGetViewCallback.onGetViewCalled(viewHolder, view, (BusinessObject) CustomGridView.this.mBusinessObject.getArrListBusinessObj().get(i), viewGroup, i);
                }
            }

            public void onBindVideoAd(ViewHolder viewHolder, View view, int i, ViewGroup viewGroup) {
                CustomGridView.this.mOnGetViewCallback.onGetViewCalled(viewHolder, view, null, viewGroup, CustomGridView.this.mAdapter.getActualItemIndex(i));
            }
        });
    }

    public void populateGrid(final ArrayList<BusinessObject> arrayList, boolean z) {
        if (this.mURLManager.m().booleanValue() || z) {
            updateGridContent(arrayList.size());
        } else {
            setAdapterParams(arrayList.size(), new OnGetViewCalledListner() {
                public void onBindVideoAd(ViewHolder viewHolder, View view, int i, ViewGroup viewGroup) {
                }

                public View onGetViewCalled(ViewHolder viewHolder, View view, int i, ViewGroup viewGroup) {
                    if (viewHolder instanceof ItemAdViewHolder) {
                        return viewHolder.itemView;
                    }
                    if (i >= arrayList.size()) {
                        View view2 = new View(CustomGridView.this.mContext);
                        view2.setVisibility(8);
                        return view2;
                    } else if (CustomGridView.this.mOnGetViewCallback == null) {
                        return CustomGridView.this.mBaseItemView.getPoplatedView(viewHolder, (BusinessObject) arrayList.get(i), viewGroup);
                    } else {
                        return CustomGridView.this.mOnGetViewCallback.onGetViewCalled(viewHolder, view, (BusinessObject) arrayList.get(i), viewGroup, i);
                    }
                }
            });
        }
    }

    public CustomGridViewAdapter getPagerAdapter() {
        return this.mAdapter;
    }

    public View getPopulatedView() {
        return this.mView;
    }

    public void refreshList() {
        if (this.mURLManager != null) {
            if (this.mOnAdRefreshListener != null) {
                this.mOnAdRefreshListener.onAdRefresh();
            }
            this.loadMoreProcessing = false;
            this.isRefreshing = true;
            this.hasDataEnded = false;
            this.mParentListViewTabs.removeAllViews();
            this.mURLManager.c(Boolean.valueOf(true));
            if (this.mURLManager.k() != null) {
                this.mURLManager.a(getNewUrl(this.mURLManager.k(), 0, 20));
            }
            this.mSwipeRefreshLayout.setRefreshing(true);
            if (this.mURLManager.j() == null || this.mURLManager.j() != UserRecentActivity.class) {
                i.a().a(this.mURLManager, this.mFragment.toString(), (b) this, (a) this);
            } else if (this.mViewTypeName.equals(DynamicViewType.user_radio_activity.name())) {
                aq.a().b(this.mURLManager, new aq.a() {
                    public void OnUserRecentActivityFetched(UserRecentActivity userRecentActivity) {
                        CustomGridView.this.onResponse(userRecentActivity);
                    }

                    public void OnUserRecentActivityErrorResponse(VolleyError volleyError) {
                        CustomGridView.this.onErrorResponse(volleyError);
                    }
                });
            } else {
                aq.a().a(this.mURLManager, new aq.a() {
                    public void OnUserRecentActivityFetched(UserRecentActivity userRecentActivity) {
                        CustomGridView.this.onResponse(userRecentActivity);
                    }

                    public void OnUserRecentActivityErrorResponse(VolleyError volleyError) {
                        CustomGridView.this.onErrorResponse(volleyError);
                    }
                });
            }
        }
    }

    /* JADX WARNING: Missing block: B:30:0x00b0, code skipped:
            return;
     */
    public void loadMoreData(int r4) {
        /*
        r3 = this;
        r0 = r3.mAppState;
        r0 = r0.isAppInOfflineMode();
        if (r0 != 0) goto L_0x00b0;
    L_0x0008:
        r0 = r3.mContext;
        r0 = com.utilities.Util.j(r0);
        if (r0 != 0) goto L_0x0012;
    L_0x0010:
        goto L_0x00b0;
    L_0x0012:
        r0 = r3.mURLManager;
        if (r0 == 0) goto L_0x00af;
    L_0x0016:
        r0 = r3.mURLManager;
        r0 = r0.k();
        if (r0 == 0) goto L_0x00af;
    L_0x001e:
        r0 = r3.mURLManager;
        r0 = r0.g();
        r1 = 1;
        if (r0 != r1) goto L_0x0029;
    L_0x0027:
        goto L_0x00af;
    L_0x0029:
        r0 = r3.mURLManager;
        r0 = r0.i();
        r2 = com.managers.URLManager.BusinessObjectType.Discover;
        if (r0 != r2) goto L_0x0036;
    L_0x0033:
        r0 = r3.tagSize;
        r4 = r4 + r0;
    L_0x0036:
        r0 = r3.loadMoreProcessing;
        if (r0 != 0) goto L_0x00ae;
    L_0x003a:
        r0 = r3.hasDataEnded;
        if (r0 != 0) goto L_0x00ae;
    L_0x003e:
        r0 = 20;
        if (r4 < r0) goto L_0x00ae;
    L_0x0042:
        r3.loadMoreProcessing = r1;
        r1 = r3.mParentListViewTabs;
        r1.removeAllViews();
        r1 = r3.mParentListViewTabs;
        r2 = r3.mGridFooterView;
        r1.addView(r2);
        r1 = r3.mURLManager;
        r2 = r3.mURLManager;
        r2 = r2.k();
        r4 = r3.getNewUrl(r2, r4, r0);
        r1.a(r4);
        r4 = r3.mURLManager;
        r4 = r4.j();
        if (r4 == 0) goto L_0x009d;
    L_0x0067:
        r4 = r3.mURLManager;
        r4 = r4.j();
        r0 = com.gaana.models.UserRecentActivity.class;
        if (r4 != r0) goto L_0x009d;
    L_0x0071:
        r4 = r3.mViewTypeName;
        r0 = com.dynamicview.DynamicViewManager.DynamicViewType.user_radio_activity;
        r0 = r0.name();
        r4 = r4.equals(r0);
        if (r4 != 0) goto L_0x008e;
    L_0x007f:
        r4 = com.managers.aq.a();
        r0 = r3.mURLManager;
        r1 = new com.gaana.view.CustomGridView$10;
        r1.<init>();
        r4.a(r0, r1);
        goto L_0x00ae;
    L_0x008e:
        r4 = com.managers.aq.a();
        r0 = r3.mURLManager;
        r1 = new com.gaana.view.CustomGridView$11;
        r1.<init>();
        r4.b(r0, r1);
        goto L_0x00ae;
    L_0x009d:
        r4 = com.i.i.a();
        r0 = r3.mURLManager;
        r1 = r3.mFragment;
        r1 = r1.toString();
        r2 = r3.onLoadMoreDataFinished;
        r4.a(r0, r1, r2, r3);
    L_0x00ae:
        return;
    L_0x00af:
        return;
    L_0x00b0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.CustomGridView.loadMoreData(int):void");
    }

    private String getNewUrl(String str, int i, int i2) {
        StringBuilder stringBuilder;
        if (str.contains("limit")) {
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

    public void onErrorResponse(VolleyError volleyError) {
        if (this.mGridView != null) {
            this.mParentLoading.setVisibility(8);
            this.mSwipeRefreshLayout.setRefreshing(false);
            this.loadMoreProcessing = false;
            this.mParentListViewTabs.removeAllViews();
        }
    }

    public void onResponse(Object obj) {
        if (this.mGridView != null) {
            if (this.isRefreshing) {
                this.isRefreshing = false;
                this.mURLManager.c(Boolean.valueOf(false));
            }
            if (this.mParentLoading != null) {
                this.mParentLoading.setVisibility(8);
            }
            this.mSwipeRefreshLayout.setRefreshing(false);
            if (obj != null && (obj instanceof BusinessObject)) {
                setGridLayout();
                BusinessObject businessObject = (BusinessObject) obj;
                if (this.mOnBusinessObjectRetrievedCallback != null) {
                    this.mOnBusinessObjectRetrievedCallback.onBusinessObjectRetrieved(businessObject, false);
                } else if (businessObject == null || businessObject.getArrListBusinessObj() == null) {
                    populateGridContent(0);
                } else {
                    this.mBusinessObject = businessObject;
                    businessObject.getArrListBusinessObj().size();
                    if ((this.mFragment instanceof GridActivityFragment) && !TextUtils.isEmpty(((GridActivityFragment) this.mFragment).e())) {
                        businessObject.getArrListBusinessObj().add(0, null);
                    }
                    int size = businessObject.getArrListBusinessObj().size();
                    if (this.mURLManager.m().booleanValue()) {
                        updateGridContent(size);
                    } else {
                        populateGridContent(size);
                    }
                    setHeader();
                }
            }
        }
    }

    private void setHeader() {
        if (!TextUtils.isEmpty(this.mHeaderText)) {
            TextView textView = (TextView) this.mView.findViewById(R.id.headerText);
            textView.setText(this.mHeaderText);
            textView.setVisibility(0);
        }
    }

    private void setGridLayout() {
        this.mGridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(int i) {
                if (i == 0 && CustomGridView.this.isVideoAdLoaded) {
                    return 2;
                }
                if ((i == 0 && (CustomGridView.this.mFragment instanceof GridActivityFragment) && !TextUtils.isEmpty(((GridActivityFragment) CustomGridView.this.mFragment).e())) || CustomGridView.this.mAdapter.getBannerAdPositions().contains(Integer.valueOf(i))) {
                    return 2;
                }
                return 1;
            }
        });
    }

    public void playAll(String str, String str2) {
        if (this.mBusinessObject != null && this.mBusinessObject.getArrListBusinessObj() != null && !this.mBusinessObject.getArrListBusinessObj().isEmpty()) {
            PlayerManager.a(this.mContext).a(this.mContext, this.mBusinessObject.getArrListBusinessObj(), this.mBusinessObject.getBusinessObjId(), str, str2);
        }
    }

    public void setTextHeader(String str) {
        this.mHeaderText = str;
    }
}
