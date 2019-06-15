package com.gaana.view;

import android.content.Context;
import com.constants.Constants.SortOrder;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.ListingFragment;
import com.fragments.LocalMediaFragment;
import com.gaana.BaseActivity;
import com.gaana.FastScrollRecyclerView.FastScrollRecyclerView;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.DownloadSongListingView;
import com.gaana.view.item.SongsItemView;
import com.i.i;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.services.d;
import com.services.l.af;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class CustomListViewOffline extends CustomListView {
    s onBusinessObjectRetrievedDb = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            CustomListViewOffline.this.pullToRefreshlistView.setRefreshing(false);
            if (CustomListViewOffline.this.mListingButton.i()) {
                CustomListViewOffline.this.pullToRefreshlistView.setEnabled(true);
            } else {
                CustomListViewOffline.this.pullToRefreshlistView.setEnabled(false);
            }
            CustomListViewOffline.this.mParentLoading.setVisibility(8);
            if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                if (businessObject instanceof NextGenSearchAutoSuggests) {
                    businessObject = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
                }
                CustomListViewOffline.this.saveOriginalMyPlaylist(businessObject.getArrListBusinessObj());
                if (CustomListViewOffline.this.mSortOrder == null || CustomListViewOffline.this.mSortOrder == SortOrder.TrackName) {
                    ((FastScrollRecyclerView) CustomListViewOffline.this.mListViewHome).showHidePopup(true);
                } else {
                    ((FastScrollRecyclerView) CustomListViewOffline.this.mListViewHome).showHidePopup(false);
                }
                CustomListViewOffline.this.getTrendingSongsList(businessObject.getArrListBusinessObj(), CustomListViewOffline.this.mBusinessView, false);
                CustomListViewOffline.this.setBusinesObject(businessObject);
                CustomListViewOffline.this.showHideEmtpyViewLayout(false);
                if (CustomListViewOffline.this.mListingButton.m() && businessObject.getArrListBusinessObj().size() > 0 && businessObject.getArrListBusinessObj().size() < 10) {
                    CustomListViewOffline.this.getmLLHeaderLayout().setVisibility(0);
                }
                if ((CustomListViewOffline.this.mFragment instanceof ListingFragment) && ((ListingFragment) CustomListViewOffline.this.mFragment).e() != null && (((ListingFragment) CustomListViewOffline.this.mFragment).e() instanceof DownloadDetailsFragment)) {
                    CustomListViewOffline.this.showSearchView();
                    CustomListViewOffline.this.showDownloadedSongsEmptyView(false);
                    if (DownloadManager.c().p() <= 0 || DownloadManager.c().p() > 10) {
                        CustomListViewOffline.this.hideDownloadCuratedSongsLayout();
                    } else {
                        CustomListViewOffline.this.showDownloadCuratedSongsLayout();
                    }
                }
            } else if ((CustomListViewOffline.this.mFragment instanceof ListingFragment) && ((ListingFragment) CustomListViewOffline.this.mFragment).e() != null && (((ListingFragment) CustomListViewOffline.this.mFragment).e() instanceof DownloadDetailsFragment)) {
                boolean showDownloaded = CustomListViewOffline.this.showDownloaded();
                boolean showQueued = CustomListViewOffline.this.showQueued();
                boolean showSmartDownloads = CustomListViewOffline.this.showSmartDownloads();
                boolean showExpiredDownloads = CustomListViewOffline.this.showExpiredDownloads();
                if (!(showDownloaded && showQueued && showSmartDownloads && (!Util.v() || showExpiredDownloads)) && (showDownloaded || showQueued || showSmartDownloads || (Util.v() && showExpiredDownloads))) {
                    if (showDownloaded || showQueued || showSmartDownloads || !Util.v() || showExpiredDownloads) {
                        CustomListViewOffline.this.showNoContentScreen(true);
                        CustomListViewOffline.this.showDownloadedSongsEmptyView(false);
                        CustomListViewOffline.this.hideDownloadCuratedSongsLayout();
                    }
                } else if (Util.j(CustomListViewOffline.this.mContext)) {
                    URLManager uRLManager = new URLManager();
                    uRLManager.a(Items.class);
                    uRLManager.a("https://apiv2.gaana.com/home/curated/download");
                    uRLManager.c(Boolean.valueOf(true));
                    uRLManager.b(Boolean.valueOf(false));
                    i.a().a(new s() {
                        public void onRetreivalComplete(BusinessObject businessObject) {
                            ArrayList arrayList = new ArrayList();
                            if (!(businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0)) {
                                Iterator it = businessObject.getArrListBusinessObj().iterator();
                                while (it.hasNext()) {
                                    Object next = it.next();
                                    if (next instanceof Item) {
                                        arrayList.add(Util.g((Item) next));
                                    }
                                }
                            }
                            if (arrayList.size() > 0) {
                                CustomListViewOffline.this.showFabButton = false;
                                if (CustomListViewOffline.this.shouldShowNoDownloadView) {
                                    CustomListViewOffline.this.showDownloadedSongsEmptyView(true);
                                }
                                CustomListViewOffline.this.populateListView(arrayList, CustomListViewOffline.this.mBusinessView);
                                return;
                            }
                            CustomListViewOffline.this.showHideEmtpyViewLayout(true);
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            CustomListViewOffline.this.showHideEmtpyViewLayout(true);
                        }
                    }, uRLManager);
                    CustomListViewOffline.this.hideDownloadCuratedSongsLayout();
                } else {
                    aj.a().a(CustomListViewOffline.this.mContext, CustomListViewOffline.this.mContext.getResources().getString(R.string.error_msg_no_connection));
                    return;
                }
            } else {
                CustomListViewOffline.this.showHideEmtpyViewLayout(true);
            }
            if (CustomListViewOffline.this.mDataLoadedListener != null) {
                CustomListViewOffline.this.mDataLoadedListener.onDataLoaded(businessObject, CustomListViewOffline.this.mListingButton.c().i());
            }
        }
    };

    public CustomListViewOffline(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x011e  */
    /* JADX WARNING: Missing block: B:44:0x0118, code skipped:
            if (((java.lang.String) r0.get("type")).equals("myplaylist_favorites") != false) goto L_0x011c;
     */
    public void setUpdateListView(com.models.ListingButton r6) {
        /*
        r5 = this;
        r5.mListingButton = r6;
        r0 = r5.mListingButton;
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r5.setSortParams();
        r5.initItemView(r6);
        r0 = r5.isPullToRefresh;
        r0 = r0.booleanValue();
        r1 = 0;
        if (r0 != 0) goto L_0x001e;
    L_0x0016:
        r5.initView();
        r0 = r5.mParentLoading;
        r0.setVisibility(r1);
    L_0x001e:
        r0 = r5.adapter;
        if (r0 == 0) goto L_0x0027;
    L_0x0022:
        r0 = r5.adapter;
        r0.clearAdapter();
    L_0x0027:
        r0 = r5.mListingButton;
        r0 = r0.i();
        r2 = 1;
        if (r0 == 0) goto L_0x0036;
    L_0x0030:
        r0 = r5.pullToRefreshlistView;
        r0.setEnabled(r2);
        goto L_0x003b;
    L_0x0036:
        r0 = r5.pullToRefreshlistView;
        r0.setEnabled(r1);
    L_0x003b:
        r0 = r5.mContext;
        r0 = (com.gaana.BaseActivity) r0;
        r0 = r0.hasLoginChanged();
        r3 = r6.g();
        if (r3 == 0) goto L_0x0062;
    L_0x0049:
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x0062;
    L_0x004f:
        r0 = r5.isPullToRefresh;
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x0062;
    L_0x0057:
        r0 = r6.g();
        r2 = r5.mBusinessView;
        r5.populateListView(r0, r2);
        goto L_0x0146;
    L_0x0062:
        r0 = r6.c();
        r3 = r5.isPullToRefresh;
        r0.c(r3);
        r0 = r5.mListingButton;
        r0 = r0.l();
        if (r0 == 0) goto L_0x00ae;
    L_0x0073:
        r0 = r5.mAppState;
        r0 = r0.getListingComponents();
        r0 = r0.a();
        if (r0 == 0) goto L_0x00ae;
    L_0x007f:
        r0 = r5.mAppState;
        r0 = r0.getListingComponents();
        r0 = r0.a();
        r2 = r0.getBusinessObjId();
        r3 = r5.mListingButton;
        r3 = r3.c();
        r3 = r3.n();
        if (r3 == 0) goto L_0x009d;
    L_0x0099:
        r2 = r0.getBusinessObjId();
    L_0x009d:
        r0 = r5.mContext;
        r0 = (com.gaana.BaseActivity) r0;
        r3 = r5.onBusinessObjectRetrieved;
        r4 = r5.mListingButton;
        r4 = r4.c();
        r0.getDownloadedBusinessObject(r3, r2, r4);
        goto L_0x0146;
    L_0x00ae:
        r0 = r5.isPullToRefresh;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0139;
    L_0x00b6:
        r0 = r5.mListingButton;	 Catch:{ Exception -> 0x011b }
        r0 = r0.c();	 Catch:{ Exception -> 0x011b }
        r0 = r0.h();	 Catch:{ Exception -> 0x011b }
        if (r0 == 0) goto L_0x011b;
    L_0x00c2:
        r3 = "type";
        r3 = r0.get(r3);	 Catch:{ Exception -> 0x011b }
        if (r3 == 0) goto L_0x011b;
    L_0x00ca:
        r3 = "type";
        r3 = r0.get(r3);	 Catch:{ Exception -> 0x011b }
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x011b }
        r4 = "mysongs";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x011b }
        if (r3 != 0) goto L_0x011c;
    L_0x00da:
        r3 = "type";
        r3 = r0.get(r3);	 Catch:{ Exception -> 0x011b }
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x011b }
        r4 = "myalbums";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x011b }
        if (r3 != 0) goto L_0x011c;
    L_0x00ea:
        r3 = "type";
        r3 = r0.get(r3);	 Catch:{ Exception -> 0x011b }
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x011b }
        r4 = "myartists";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x011b }
        if (r3 != 0) goto L_0x011c;
    L_0x00fa:
        r3 = "type";
        r3 = r0.get(r3);	 Catch:{ Exception -> 0x011b }
        r3 = (java.lang.String) r3;	 Catch:{ Exception -> 0x011b }
        r4 = "myplaylists";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x011b }
        if (r3 != 0) goto L_0x011c;
    L_0x010a:
        r3 = "type";
        r0 = r0.get(r3);	 Catch:{ Exception -> 0x011b }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x011b }
        r3 = "myplaylist_favorites";
        r0 = r0.equals(r3);	 Catch:{ Exception -> 0x011b }
        if (r0 == 0) goto L_0x011b;
    L_0x011a:
        goto L_0x011c;
    L_0x011b:
        r2 = r1;
    L_0x011c:
        if (r2 != 0) goto L_0x012c;
    L_0x011e:
        r0 = r5.mContext;
        r0 = (com.gaana.BaseActivity) r0;
        r2 = r5.onBusinessObjectRetrievedDb;
        r3 = r6.c();
        r0.startDownloadDbRetreival(r2, r3);
        goto L_0x0146;
    L_0x012c:
        r0 = com.gaana.localmedia.FavouriteSyncManager.getInstance();
        r2 = new com.gaana.view.CustomListViewOffline$1;
        r2.<init>();
        r0.performSync(r2);
        goto L_0x0146;
    L_0x0139:
        r0 = r5.mContext;
        r0 = (com.gaana.BaseActivity) r0;
        r2 = r5.onBusinessObjectRetrievedDb;
        r3 = r6.c();
        r0.startDownloadDbRetreival(r2, r3);
    L_0x0146:
        r0 = r5.mListingButton;
        r0 = r0.m();
        if (r0 == 0) goto L_0x0171;
    L_0x014e:
        r0 = r6.g();
        if (r0 == 0) goto L_0x0171;
    L_0x0154:
        r0 = r6.g();
        r0 = r0.size();
        if (r0 <= 0) goto L_0x0171;
    L_0x015e:
        r6 = r6.g();
        r6 = r6.size();
        r0 = 10;
        if (r6 >= r0) goto L_0x0171;
    L_0x016a:
        r6 = r5.getmLLHeaderLayout();
        r6.setVisibility(r1);
    L_0x0171:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.CustomListViewOffline.setUpdateListView(com.models.ListingButton):void");
    }

    public void refreshListData() {
        if (this.mListingButton != null) {
            this.mListingButton.c().c(Boolean.valueOf(false));
            ((BaseActivity) this.mContext).startDownloadDbRetreival(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                        CustomListViewOffline.this.showHideEmtpyViewLayout(true);
                    } else {
                        if (businessObject instanceof NextGenSearchAutoSuggests) {
                            businessObject = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
                        }
                        CustomListViewOffline.this.saveOriginalMyPlaylist(businessObject.getArrListBusinessObj());
                        ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                        CustomListViewOffline.this.pullToRefreshlistView.setRefreshing(false);
                        if ((CustomListViewOffline.this.mBusinessView instanceof SongsItemView) && CustomListViewOffline.this.mFragment.getUserVisibleHint()) {
                            CustomListViewOffline.this.mAppState.setCurrentBusObjInListView(arrListBusinessObj);
                        }
                        if (CustomListViewOffline.this.mListingButton.g() == null || CustomListViewOffline.this.mListingButton.g().size() == 0 || CustomListViewOffline.this.adapter == null) {
                            CustomListViewOffline.this.mListingButton.a(arrListBusinessObj);
                            CustomListViewOffline.this.populateListView(arrListBusinessObj, CustomListViewOffline.this.mBusinessView);
                            if ((CustomListViewOffline.this.mFragment instanceof ListingFragment) && ((ListingFragment) CustomListViewOffline.this.mFragment).e() != null && (((ListingFragment) CustomListViewOffline.this.mFragment).e() instanceof DownloadDetailsFragment) && arrListBusinessObj.size() > 0) {
                                CustomListViewOffline.this.showSearchView();
                                CustomListViewOffline.this.showDownloadedSongsEmptyView(false);
                                if (DownloadManager.c().p() <= 0 || DownloadManager.c().p() > 10) {
                                    CustomListViewOffline.this.hideDownloadCuratedSongsLayout();
                                } else {
                                    CustomListViewOffline.this.showDownloadCuratedSongsLayout();
                                }
                            }
                        } else {
                            CustomListViewOffline.this.getTrendingSongsList(businessObject.getArrListBusinessObj(), CustomListViewOffline.this.mBusinessView, true);
                        }
                        if (CustomListViewOffline.this.mListingButton.m() && businessObject.getArrListBusinessObj().size() > 0 && businessObject.getArrListBusinessObj().size() < 10) {
                            CustomListViewOffline.this.getmLLHeaderLayout().setVisibility(0);
                        }
                    }
                    if (CustomListViewOffline.this.mDataLoadedListener != null) {
                        CustomListViewOffline.this.mDataLoadedListener.onDataLoaded(businessObject, CustomListViewOffline.this.mListingButton.c().i());
                    }
                    if ((CustomListViewOffline.this.mFragment instanceof ListingFragment) && ((ListingFragment) CustomListViewOffline.this.mFragment).e() != null && (((ListingFragment) CustomListViewOffline.this.mFragment).e() instanceof DownloadDetailsFragment) && businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                        CustomListViewOffline.this.showSearchView();
                        CustomListViewOffline.this.showDownloadedSongsEmptyView(false);
                        if (DownloadManager.c().p() <= 0 || DownloadManager.c().p() > 10) {
                            CustomListViewOffline.this.hideDownloadCuratedSongsLayout();
                        } else {
                            CustomListViewOffline.this.showDownloadCuratedSongsLayout();
                        }
                    }
                }
            }, this.mListingButton.c());
        }
    }

    public boolean showQueued() {
        return d.a().b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_QUEUED", true, true);
    }

    public boolean showSmartDownloads() {
        return d.a().b("PREF_DOWNLOAD_LIST_SHOW_SMART_DOWNLOADS", true, true);
    }

    public boolean showExpiredDownloads() {
        return d.a().b("PREF_DOWNLOAD_LIST_SHOW_EXPIRED_DOWNLOADS", true, true);
    }

    public boolean showDownloaded() {
        return d.a().b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_DOWNLOADED", true, true);
    }

    public void getTrendingSongsList(ArrayList<Object> arrayList, BaseItemView baseItemView, boolean z) {
        if ((this.mFragment.getParentFragment() instanceof LocalMediaFragment) && (baseItemView instanceof DownloadSongListingView)) {
            URLManager uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.a("https://apiv2.gaana.com/home/trending/songs");
            uRLManager.b(Boolean.valueOf(true));
            final ArrayList arrayList2 = new ArrayList();
            final ArrayList<Object> arrayList3 = arrayList;
            final boolean z2 = z;
            final BaseItemView baseItemView2 = baseItemView;
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    if (obj != null) {
                        BusinessObject businessObject = (BusinessObject) obj;
                        if (businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                            int i;
                            CustomListViewOffline.this.trendingArray = businessObject.getArrListBusinessObj();
                            int i2 = 0;
                            if (arrayList3.size() > 0) {
                                i = 0;
                                while (i2 < arrayList3.size()) {
                                    if (i2 != 0 && i2 % 9 == 0 && i < CustomListViewOffline.this.trendingArray.size()) {
                                        arrayList2.add(Util.g((Item) CustomListViewOffline.this.trendingArray.get(i)));
                                        i++;
                                    }
                                    arrayList2.add(arrayList3.get(i2));
                                    i2++;
                                }
                            } else {
                                i = 0;
                            }
                            while (i < CustomListViewOffline.this.trendingArray.size()) {
                                arrayList2.add(Util.g((Item) CustomListViewOffline.this.trendingArray.get(i)));
                                i++;
                            }
                            if (z2) {
                                CustomListViewOffline.this.sortIfRequired(arrayList2, CustomListViewOffline.this.mSortOrder);
                                CustomListViewOffline.this.mListingButton.a(arrayList2);
                                CustomListViewOffline.this.adapter.setAdapterArrayList(arrayList2);
                                return;
                            }
                            CustomListViewOffline.this.populateListView(arrayList2, baseItemView2);
                            return;
                        }
                    }
                    if (z2) {
                        CustomListViewOffline.this.sortIfRequired(arrayList3, CustomListViewOffline.this.mSortOrder);
                        CustomListViewOffline.this.mListingButton.a(arrayList3);
                        CustomListViewOffline.this.adapter.setAdapterArrayList(arrayList3);
                        return;
                    }
                    CustomListViewOffline.this.populateListView(arrayList3, baseItemView2);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (z2) {
                        CustomListViewOffline.this.sortIfRequired(arrayList3, CustomListViewOffline.this.mSortOrder);
                        CustomListViewOffline.this.mListingButton.a(arrayList3);
                        CustomListViewOffline.this.adapter.setAdapterArrayList(arrayList3);
                        return;
                    }
                    CustomListViewOffline.this.populateListView(arrayList3, baseItemView2);
                }
            }, uRLManager);
        } else if (z) {
            sortIfRequired(arrayList, this.mSortOrder);
            this.mListingButton.a((ArrayList) arrayList);
            this.adapter.setAdapterArrayList(arrayList);
        } else {
            populateListView(arrayList, baseItemView);
        }
    }
}
