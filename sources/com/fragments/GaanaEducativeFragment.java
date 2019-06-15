package com.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.i.i;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.g;
import com.managers.u;
import com.services.l.af;
import java.util.ArrayList;

public class GaanaEducativeFragment extends BaseGaanaFragment implements OnClickListener, a {
    private a a;
    private ProgressBar b;

    private class a extends Adapter<AlbumDetailItemHolder> {
        private final DownloadSongsItemView b;
        private final int c;
        private ArrayList<Item> d;

        a() {
            this.b = new DownloadSongsItemView(GaanaEducativeFragment.this.mContext, GaanaEducativeFragment.this);
            this.c = (int) GaanaEducativeFragment.this.mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_small);
        }

        /* renamed from: a */
        public AlbumDetailItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            AlbumDetailItemHolder albumDetailItemHolder = new AlbumDetailItemHolder(this.b.createViewHolder(viewGroup, i));
            albumDetailItemHolder.clickoptionImage.getLayoutParams().width = (int) GaanaEducativeFragment.this.mContext.getResources().getDimension(R.dimen.page_left_right_margin);
            return albumDetailItemHolder;
        }

        /* renamed from: a */
        public void onBindViewHolder(AlbumDetailItemHolder albumDetailItemHolder, int i) {
            this.b.getPoplatedView((ViewHolder) albumDetailItemHolder, (BusinessObject) this.d.get(i), null);
            albumDetailItemHolder.itemView.setOnClickListener(null);
            albumDetailItemHolder.downloadPulse.setVisibility(8);
            if (i == 0) {
                DownloadStatus e = DownloadManager.c().e(Integer.parseInt(((Item) this.d.get(i)).getBusinessObjId()));
                if (e == null || e == DownloadStatus.PAUSED) {
                    albumDetailItemHolder.downloadImage.setImageResource(Constants.l ? R.drawable.download_red_highlight : R.drawable.download_highlight_red_white);
                    albumDetailItemHolder.downloadImage.setPadding(0, 0, 0, 0);
                } else {
                    albumDetailItemHolder.downloadImage.setPadding(this.c, this.c, this.c, this.c);
                }
            } else {
                albumDetailItemHolder.downloadImage.setPadding(this.c, this.c, this.c, this.c);
            }
            albumDetailItemHolder.clickoptionImage.setVisibility(4);
        }

        public int getItemCount() {
            if (this.d == null) {
                return 0;
            }
            return this.d.size() > 3 ? 3 : this.d.size();
        }

        public void a(ArrayList<Item> arrayList) {
            this.d = arrayList;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.fragment_gaana_educative, viewGroup, false);
        a(inflate);
        setGAScreenName("BenefitScreen", "BenefitScreen");
        return inflate;
    }

    private void a(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.gaana_edu_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.a = new a();
        a(recyclerView, this.a);
        view.findViewById(R.id.download_more_songs).setOnClickListener(this);
        view.findViewById(R.id.fragment_edu_back).setOnClickListener(this);
        this.b = (ProgressBar) view.findViewById(R.id.fragment_edu_progress_bar);
        if (ap.a().l()) {
            view.findViewById(R.id.fragment_edu_noads).setVisibility(8);
        }
    }

    private void a(final RecyclerView recyclerView, final a aVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/home/curated/download");
        uRLManager.a(BusinessObjectType.GenericItems);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Priority.IMMEDIATE);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                if (obj != null) {
                    aVar.a(((Items) obj).getArrListBusinessObj());
                    recyclerView.setAdapter(aVar);
                }
                GaanaEducativeFragment.this.b.setVisibility(8);
            }

            public void onErrorResponse(BusinessObject businessObject) {
                Log.e("Error", "businessObject");
                GaanaEducativeFragment.this.b.setVisibility(8);
            }
        }, uRLManager);
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.download_more_songs) {
            ((GaanaActivity) this.mContext).popBackStackImmediate();
            u.a().a("Benefits", "Click", "Download more songs");
            g.a(this.mContext, null, null);
        } else if (id == R.id.fragment_edu_back) {
            ((GaanaActivity) this.mContext).onBackPressed();
        }
    }

    public void refreshListView() {
        super.refreshListView();
        this.a.notifyDataSetChanged();
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        super.refreshListView(businessObjectType);
        this.a.notifyDataSetChanged();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        this.a.notifyDataSetChanged();
    }

    public void onDestroyView() {
        u.a().b("Benefits", "Close");
        super.onDestroyView();
    }
}
