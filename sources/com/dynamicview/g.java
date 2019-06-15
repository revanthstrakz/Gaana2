package com.dynamicview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.b.i;
import com.dynamicview.DynamicViewManager.PreScreenViewType;
import com.fragments.BaseGaanaFragment;
import com.fragments.PreScreenFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.PreScreens.PreScreen;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.FavouriteSongsItemView;
import com.gaana.view.item.FavouriteSongsItemView.FavouriteSongsItemHolder;
import com.gaana.view.item.GenericItemView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.aq;
import com.managers.u;
import com.services.d;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;

class g extends BaseItemView implements OnClickListener {
    private final PreScreen a;
    private final BaseGaanaFragment b;
    private ArrayList<?> c;
    private a d;
    private boolean e;

    private class a extends Adapter {
        private final com.gaana.view.item.BaseItemView b;

        public int getItemCount() {
            return 4;
        }

        public a() {
            if (g.this.a.getViewType().equals(PreScreenViewType.list.name())) {
                this.b = new FavouriteSongsItemView(g.this.mContext, g.this.b);
                ((FavouriteSongsItemView) this.b).setSourceName(g.this.a.getGaSourceName());
                return;
            }
            this.b = new GenericItemView(g.this.mContext, g.this.b);
            ((GenericItemView) this.b).setSourceName(g.this.a.getGaSourceName());
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (g.this.a.getViewType().equals(PreScreenViewType.list.name())) {
                return new FavouriteSongsItemHolder(LayoutInflater.from(g.this.mContext).inflate(R.layout.view_item_list_row, viewGroup, false));
            }
            View inflate = LayoutInflater.from(g.this.mContext).inflate(g.this.a.getViewType().equals(PreScreenViewType.cir_grid_2x2.name()) ? R.layout.item_playlist_circular_grid_150x150 : R.layout.item_playlist_grid_150x150, viewGroup, false);
            ((MarginLayoutParams) inflate.findViewById(R.id.rl_empty_item_view).getLayoutParams()).bottomMargin = 0;
            return new PlaylistGridHolder(inflate);
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            if (g.this.c != null && i < g.this.c.size()) {
                BusinessObject businessObject = (Item) g.this.c.get(i);
                if (g.this.a.getViewType().equals(PreScreenViewType.list.name())) {
                    ((FavouriteSongsItemView) this.b).getPoplatedView(viewHolder, businessObject, null);
                    viewHolder.itemView.setOnClickListener(this.b);
                } else {
                    ((GenericItemView) this.b).getPoplatedGenericView(i, viewHolder, businessObject, null, null);
                    viewHolder.itemView.setOnClickListener(g.this);
                }
                viewHolder.itemView.setTag(businessObject);
            }
        }
    }

    public static class b extends ViewHolder {
        public TextView a;
        public TextView b;
        public RecyclerView c;
        private final View d;

        public b(View view) {
            super(view);
            this.d = view.findViewById(R.id.view_fouritems_container);
            this.a = (TextView) view.findViewById(R.id.f55header.text);
            this.b = (TextView) view.findViewById(R.id.seeall);
            this.c = (RecyclerView) view.findViewById(R.id.recycler_view);
        }
    }

    public class c extends ItemDecoration {
        private int b;

        public c(int i) {
            this.b = i;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            rect.set(this.b, (int) g.this.mContext.getResources().getDimension(R.dimen.dimen_12dp), this.b, (int) g.this.mContext.getResources().getDimension(R.dimen.dimen_12dp));
        }
    }

    public g(Context context, BaseGaanaFragment baseGaanaFragment, PreScreen preScreen) {
        super(context, baseGaanaFragment);
        this.a = preScreen;
        this.b = baseGaanaFragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.mContext).inflate(R.layout.view_four_item, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        a(viewHolder, this.e);
        this.e = false;
        return viewHolder.itemView;
    }

    private void a(final ViewHolder viewHolder, boolean z) {
        final b bVar = (b) viewHolder;
        a(bVar);
        bVar.a.setText(this.a.getEntityDescription());
        bVar.a.setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        if (this.a.getViewType().equals(PreScreenViewType.list.name())) {
            ArrayList a = aq.a().a(4);
            if (a == null || a.size() < 4) {
                viewHolder.itemView.getLayoutParams().height = 0;
                viewHolder.itemView.setVisibility(8);
                return;
            }
            b(bVar);
            this.c = a;
            this.d.notifyDataSetChanged();
            return;
        }
        com.i.i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().isEmpty() || businessObject.getArrListBusinessObj().size() < 4) {
                    viewHolder.itemView.getLayoutParams().height = 0;
                    viewHolder.itemView.setVisibility(8);
                    return;
                }
                g.this.b(bVar);
                g.this.c = businessObject.getArrListBusinessObj();
                g.this.d.notifyDataSetChanged();
            }

            public void onErrorResponse(BusinessObject businessObject) {
                viewHolder.itemView.getLayoutParams().height = 0;
                viewHolder.itemView.setVisibility(8);
            }
        }, a(z, this.a.getUrl()));
    }

    private void a(b bVar) {
        if (this.d == null) {
            this.d = new a();
            bVar.c.setAdapter(this.d);
            bVar.c.setHasFixedSize(true);
            if (this.a.getViewType().equals(PreScreenViewType.list.name())) {
                bVar.c.setLayoutManager(new LinearLayoutManager(this.mContext));
            } else {
                bVar.c.setLayoutManager(new GridLayoutManager(this.mContext, 2));
                bVar.c.addItemDecoration(new c((int) ((((float) d.a().b()) - (this.mContext.getResources().getDimension(R.dimen.dp150) * 2.0f)) / 4.0f)));
            }
            b(bVar);
        }
    }

    private void b(b bVar) {
        if (this.a.getViewType().equals(PreScreenViewType.list.name())) {
            bVar.d.getLayoutParams().height = (int) this.mContext.getResources().getDimension(R.dimen.dp300);
        } else {
            bVar.d.getLayoutParams().height = (int) this.mContext.getResources().getDimension(R.dimen.grid_2x2_container_height);
        }
        bVar.itemView.setVisibility(0);
    }

    private URLManager a(boolean z, String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(1440);
        uRLManager.a(str);
        uRLManager.k(z);
        uRLManager.a(BusinessObjectType.GenericItems);
        return uRLManager;
    }

    public void setIsToBeRefreshed(boolean z) {
        this.e = z;
    }

    public void onClick(View view) {
        if (Util.j(this.mContext)) {
            Item item = (Item) view.getTag();
            CharSequence charSequence = null;
            StringBuilder stringBuilder;
            if (item.getEntityType().equals(com.constants.c.c.d)) {
                u.a().a("ForYou", "ArtistBased Mixes", "Click");
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/index.php?type=artist&subtype=artist_track_listing&artist_id=");
                stringBuilder.append(item.getBusinessObjId());
                stringBuilder.append("&limit=0,20");
                charSequence = stringBuilder.toString();
            } else if (item.getEntityType().equals(com.constants.c.c.a)) {
                u.a().a("ForYou", "PlaylistFY Mixes", "Click");
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/index.php?type=playlist&subtype=playlist_detail&playlist_id=");
                stringBuilder.append(item.getBusinessObjId());
                charSequence = stringBuilder.toString();
            } else if (item.getEntityType().equals(com.constants.c.c.b)) {
                u.a().a("ForYou", "AlbumFY Mixes", "Click");
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/index.php?type=album&subtype=album_detail&album_id=");
                stringBuilder.append(item.getBusinessObjId());
                charSequence = stringBuilder.toString();
            } else if (item.getEntityType().equals(com.constants.c.c.c)) {
                u.a().a("ForYou", "Latest Played", "Click");
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://rec.gaana.com/recommendation/recommendedTracks/");
                stringBuilder.append(item.getBusinessObjId());
                charSequence = stringBuilder.toString();
            }
            if (!TextUtils.isEmpty(charSequence)) {
                a(item.getName(), charSequence, true, item.getBusinessObjId());
            }
            return;
        }
        ap.a().f(this.mContext);
    }

    private void a(String str, String str2, boolean z, String str3) {
        URLManager a = a(false, str2);
        a.a(z ^ 1);
        a.a(BusinessObjectType.Tracks);
        ((PreScreenFragment) this.mFragment).a(a, str, PAGE_SORCE_NAME.FOR_YOU.name(), this.a.getGaSourceName());
    }
}
