package com.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemNormalViewHolder;
import com.gaana.view.item.BaseItemView.TwoGridItemHolder;
import com.gaana.view.item.DiscoverItemView;
import com.i.i;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.e;
import com.managers.u;
import com.services.l.f;
import com.services.l.v;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;

public class GridRecommendationFragment extends BaseGaanaFragment implements IAddListItemView, f, v {
    private View a = null;
    private BusinessObject b;
    private RecyclerView c;
    private CustomListAdapter d;
    private BaseItemView e;
    private int f = 0;
    private ArrayList<BusinessObject> g = new ArrayList();
    private int h = 0;
    private DisplayMetrics i;
    private URLManager j;
    private int k = 0;
    private int l = 1;
    private int m = 2;

    private class a extends ViewHolder {
        private TextView b;
        private TextView c;
        private ImageView d;

        public a(View view) {
            super(view);
            this.b = (TextView) view.findViewById(R.id.video_title);
            this.c = (TextView) view.findViewById(R.id.video_album_artist);
            this.d = (ImageView) view.findViewById(R.id.share_icon);
        }
    }

    public void showHideEmtpyView(boolean z) {
    }

    private URLManager a(com.dynamicview.f.a aVar, int i) {
        URLManager uRLManager = new URLManager();
        String o = aVar.o();
        if (!TextUtils.isEmpty(aVar.y()) && aVar.y().equalsIgnoreCase("X5X")) {
            int i2 = 0;
            StringBuilder stringBuilder;
            if (o.contains("?")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(o);
                stringBuilder.append("&trend=");
                if (GaanaApplication.sessionHistoryCount > 3) {
                    i2 = 1;
                }
                stringBuilder.append(i2);
                o = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(o);
                stringBuilder.append("?trend=");
                if (GaanaApplication.sessionHistoryCount > 3) {
                    i2 = 1;
                }
                stringBuilder.append(i2);
                o = stringBuilder.toString();
            }
        }
        uRLManager.a(o);
        if (i != -1 && o.contains("<entity_Parent_Id>")) {
            uRLManager.a(o.replace("<entity_Parent_Id>", String.valueOf(i)));
        }
        uRLManager.a(BusinessObjectType.GenericItems);
        return uRLManager;
    }

    private boolean a(ViewGroup viewGroup) {
        this.j = a(com.dynamicview.a.b(com.constants.c.a.D), 0);
        this.j.c(Boolean.valueOf(false));
        a(DiscoverItemView.class.getName());
        b(viewGroup);
        a();
        return true;
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.b != null) {
            this.b.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.b);
        }
    }

    public final void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.i = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.i);
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.b = (BusinessObject) getArguments().getSerializable("BUSINESS_OBJECT");
        if (viewGroup == null) {
            return null;
        }
        a(viewGroup);
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        return this.a;
    }

    public void onResume() {
        super.onResume();
    }

    private void b(ViewGroup viewGroup) {
        this.a = setContentView(R.layout.video_rec_grid_fragment, viewGroup);
        this.c = (RecyclerView) this.a.findViewById(R.id.grid_recycler);
        this.c.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.c.setHasFixedSize(false);
        this.d = new CustomListAdapter(this.mContext, null);
        this.d.setDFPBannerAdCode(e.K);
        this.d.setParamaters(0, this);
        this.c.setAdapter(this.d);
    }

    private void a() {
        i.a().a(this.j, toString(), (b) this, (com.android.volley.i.a) this);
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        try {
            this.e = (BaseItemView) Class.forName(str).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
        } catch (Exception unused) {
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (i == 0) {
            return a(viewHolder);
        }
        if (i == 1) {
            return viewHolder.itemView;
        }
        return this.e.getPoplatedView(viewHolder, a(i), viewGroup, false, Boolean.valueOf(false), this);
    }

    public void notifyItemChanged(int i) {
        if (this.d != null) {
            this.d.notifyItemChanged(i);
        }
    }

    public BusinessObject a(int i) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        i = (i - this.h) * 2;
        for (int i2 = 0; i2 < 2; i2++) {
            if (i2 < this.g.size()) {
                int i3 = i + i2;
                if (i3 < this.g.size()) {
                    arrayList.add(i2, this.g.get(i3));
                }
            }
        }
        businessObject.setArrListBusinessObj(arrayList);
        return businessObject;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        if (i == this.k) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_details_layout, viewGroup, false));
        }
        if (i == this.l) {
            return new ItemNormalViewHolder(b());
        }
        return i == this.m ? new TwoGridItemHolder(this.e.createViewHolder(viewGroup, i, R.layout.grid_twoitem_view)) : null;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return this.k;
        }
        if (i == 1) {
            return this.l;
        }
        return this.m;
    }

    public void onDestroyView() {
        if (!(this.a == null || this.a.getParent() == null)) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        }
        super.onDestroyView();
    }

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        showNetworkErrorView(null);
    }

    public void onResponse(Object obj) {
        BusinessObject businessObject = (BusinessObject) obj;
        if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
            this.c.setItemAnimator(new DefaultItemAnimator());
            this.b.setCount(String.valueOf(businessObject.getArrListBusinessObj().size()));
            this.g = businessObject.getArrListBusinessObj();
            if (this.g.size() % 2 == 0) {
                this.f = this.g.size() / 2;
            } else {
                this.f = (this.g.size() / 2) + 1;
            }
            this.h = 2;
            this.d.updateAdapterCount(this.f + this.h);
        }
    }

    private View a(ViewHolder viewHolder) {
        a aVar = (a) viewHolder;
        if (this.b instanceof Track) {
            aVar.b.setText(((Track) this.b).getName());
            aVar.c.setVisibility(0);
            TextView b = aVar.c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((Track) this.b).getAlbumTitle());
            stringBuilder.append(" - ");
            stringBuilder.append(((Track) this.b).getArtistNames());
            b.setText(stringBuilder.toString());
        } else if (this.b instanceof YouTubeVideo) {
            aVar.b.setText(((YouTubeVideo) this.b).b());
            aVar.c.setVisibility(8);
        } else {
            aVar.b.setText(this.b.getName());
            aVar.c.setVisibility(8);
        }
        aVar.d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GridRecommendationFragment.this.b.setBusinessObjType(BusinessObjectType.YouTubeVideos);
                ap.a().a(GridRecommendationFragment.this.mContext, GridRecommendationFragment.this.b, GridRecommendationFragment.this);
            }
        });
        return viewHolder.itemView;
    }

    public void a(BusinessObject businessObject, boolean z) {
        this.b = businessObject;
        if (!z) {
            u.a().a("VideoPlayerEvents", "Tap to Play", "Popular Video Click");
        }
        notifyItemChanged(0);
    }

    private View b() {
        TextView textView = new TextView(this.mContext);
        textView.setLayoutParams(new LayoutParams(-1, -2));
        float f = getResources().getDisplayMetrics().density;
        textView.setPadding((int) ((8.0f * f) + 0.5f), (int) ((24.0f * f) + 0.5f), 0, (int) ((10.0f * f) + 0.5f));
        textView.setText(this.mContext.getResources().getString(R.string.popular_videos));
        textView.setTextSize(2, 16.0f);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.header_title_color, R.attr.tab_layout_background_attr});
        textView.setTextColor(obtainStyledAttributes.getColor(0, -1));
        textView.setBackgroundColor(obtainStyledAttributes.getColor(1, -1));
        obtainStyledAttributes.recycle();
        return textView;
    }
}
