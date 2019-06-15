package com.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.constants.Constants;
import com.dynamicview.DynamicHomeFragment.a;
import com.dynamicview.DynamicViewManager;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.models.BusinessObject;
import com.gaana.models.PreScreens;
import com.gaana.models.PreScreens.PreScreen;
import com.gaana.view.BaseItemView;
import com.gaana.view.CustomGridView;
import com.gaana.view.CustomGridView.OnGetViewCallback;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.ItemNormalViewHolder;
import com.gaana.view.item.DiscoverItemView;
import com.gaana.view.item.TrackItemView;
import com.i.i;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.services.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreScreenFragment extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, IAddListItemView {
    private ArrayList<BaseItemView> a = null;
    private Map<Integer, a> b = new HashMap();
    private boolean c;
    private CustomListAdapter d;
    private SwipeRefreshLayout e;
    private RecyclerView f;
    private String g;
    private BottomSheetDialog h;
    private URLManager i;
    private String j;
    private boolean k;

    public void setGAScreenName(String str, String str2) {
    }

    public boolean shouldHandleLoginChange() {
        return false;
    }

    public void showHideEmtpyView(boolean z) {
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mContext = getActivity();
        return layoutInflater.inflate(R.layout.fragment_pre_screen, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        i.a().a(a(false), "pre_screen", (b) this, (com.android.volley.i.a) this);
        this.d = new CustomListAdapter(this.mContext, null);
        this.f = (RecyclerView) view.findViewById(R.id.prescreen_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        linearLayoutManager.setAutoMeasureEnabled(false);
        this.f.setHasFixedSize(true);
        this.f.setLayoutManager(linearLayoutManager);
        this.e = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        this.e.setOnRefreshListener(this);
        view.findViewById(R.id.menu_back).setOnClickListener(this);
    }

    private void c() {
        if (this.c) {
            for (int i = 0; i < this.a.size(); i++) {
                ((BaseItemView) this.a.get(i)).setIsToBeRefreshed(this.c);
            }
        }
        if (this.c) {
            this.e.setRefreshing(false);
            this.c = false;
            this.d.updateAdapterCount(this.a.size() + 1);
            return;
        }
        this.d.setParamaters(this.a.size() + 1, this);
        this.f.setAdapter(this.d);
    }

    private URLManager a(boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(1440);
        uRLManager.a("https://apiv2.gaana.com/pre-screen/metadata");
        uRLManager.c(Boolean.valueOf(z));
        uRLManager.a(BusinessObjectType.PreScreens);
        return uRLManager;
    }

    public void onResponse(Object obj) {
        super.onResponse(obj);
        if (obj != null) {
            PreScreens preScreens = (PreScreens) obj;
            if (!preScreens.getPreScreens().isEmpty()) {
                a(preScreens.getPreScreens(), this.mContext, this);
            }
            d();
            c();
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
    }

    private void d() {
        if (this.a != null) {
            this.b.clear();
            for (int i = 0; i < this.a.size(); i++) {
                Integer valueOf = Integer.valueOf(((BaseItemView) this.a.get(i)).getItemViewType());
                a aVar = (a) this.b.get(valueOf);
                if (aVar == null) {
                    this.b.put(valueOf, new a((BaseItemView) this.a.get(i), 1));
                } else {
                    aVar.b++;
                }
            }
        }
    }

    private List<BaseItemView> a(List<PreScreen> list, Context context, BaseGaanaFragment baseGaanaFragment) {
        if (this.a == null || this.c) {
            this.a = DynamicViewManager.a().c(list, context, baseGaanaFragment);
        }
        return this.a;
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.FOR_YOU.name();
    }

    public String getSectionName() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public void onRefresh() {
        this.c = true;
        i.a().a(a(true), "pre_screen", (b) this, (com.android.volley.i.a) this);
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (viewHolder.getItemViewType() == 0) {
            return viewHolder.itemView;
        }
        i--;
        return ((BaseItemView) this.a.get(i)).getPopulatedView(i, viewHolder, viewGroup);
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return ((a) this.b.get(Integer.valueOf(i))).a.onCreateViewHolder(viewGroup, i);
        }
        ItemNormalViewHolder itemNormalViewHolder = new ItemNormalViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.prescreen_header_item, viewGroup, false));
        ((TextView) itemNormalViewHolder.itemView.findViewById(R.id.txt_section_header)).setTypeface(com.b.i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        return itemNormalViewHolder;
    }

    public int getItemViewType(int i) {
        return i == 0 ? 0 : ((BaseItemView) this.a.get(i - 1)).getItemViewType();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.menu_back) {
            ((GaanaActivity) this.mContext).onBackPressed();
        }
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        super.refreshListView(businessObjectType);
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }

    public void a(URLManager uRLManager, String str, final String str2, final String str3) {
        this.j = str;
        this.i = uRLManager;
        this.h = new BottomSheetDialog(this.mContext);
        final CustomGridView customGridView = new CustomGridView(this.mContext, this);
        customGridView.disablePulltoRefresh();
        this.g = str3;
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.grid_header_rounded, null, false);
        ((TextView) inflate.findViewById(R.id.grid_header_title)).setText(str);
        inflate.findViewById(R.id.grid_header_pin).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PreScreenFragment.this.h.dismiss();
            }
        });
        inflate.findViewById(R.id.grid_header_play).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (customGridView != null) {
                    customGridView.playAll(str2, str3);
                }
            }
        });
        customGridView.setOnAdRefreshListener(this);
        customGridView.setNumColumns(2);
        customGridView.setViewClassName((uRLManager.i().equals(BusinessObjectType.Tracks) ? TrackItemView.class : DiscoverItemView.class).getName());
        View populatedView = customGridView.getPopulatedView();
        View linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.addView(inflate);
        linearLayout.addView(populatedView);
        this.h.setContentView(linearLayout);
        final BottomSheetBehavior from = BottomSheetBehavior.from((View) linearLayout.getParent());
        ((View) linearLayout.getParent()).setBackgroundResource(Constants.l ? R.drawable.rounded_grid_header_white : R.drawable.rounded_grid_header);
        from.setBottomSheetCallback(new BottomSheetCallback() {
            public void onSlide(@NonNull View view, float f) {
            }

            public void onStateChanged(@NonNull View view, int i) {
                if (i == 3) {
                    from.setPeekHeight(d.a().c());
                } else if (i == 5) {
                    PreScreenFragment.this.h.dismiss();
                }
            }
        });
        from.setPeekHeight(d.a().c() - (d.a().c() / 4));
        customGridView.getmGridView().addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (from.getState() != 3) {
                    from.setState(3);
                }
            }
        });
        this.h.show();
        customGridView.seOnGetViewCallback(new OnGetViewCallback() {
            public View onGetViewCalled(ViewHolder viewHolder, View view, BusinessObject businessObject, ViewGroup viewGroup, int i) {
                if (viewHolder instanceof ItemAdViewHolder) {
                    return viewHolder.itemView;
                }
                TrackItemView trackItemView = new TrackItemView(PreScreenFragment.this.mContext, PreScreenFragment.this);
                trackItemView.setItemPosition(i);
                return trackItemView.getPoplatedViewForGrid(viewHolder, businessObject, viewGroup);
            }
        });
        uRLManager.a(Boolean.valueOf(false));
        customGridView.updateGridView(uRLManager);
    }

    public void a() {
        if (this.h != null && this.h.isShowing()) {
            this.h.dismiss();
            this.k = true;
        }
    }

    public void b() {
        if (this.i != null && this.k) {
            a(this.i, this.j, getPageName(), getSectionName());
        }
        this.k = false;
    }
}
