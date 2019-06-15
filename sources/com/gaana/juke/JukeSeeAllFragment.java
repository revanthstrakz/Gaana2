package com.gaana.juke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.b.i;
import com.collapsible_header.ObservableRecyclerView;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.PopupShareitemView;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.i.j;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.ap;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;

public class JukeSeeAllFragment extends BaseGaanaFragment implements OnClickListener {
    public static final String EXTRA_ARG_IS_MY_PLAYLIST = "extra_my_playlist";
    public static final String EXTRA_ARG_SEE_ALL_URL = "extra_url";
    public static final String EXTRA_ARG_TITLE = "extra_title";
    public static final String EXTRA_TYPE = "extra_type";
    private boolean isMyPlaylist;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<BusinessObject> mBusinessObjects = new ArrayList();
    private ObservableRecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FrameLayout mToolbar;
    private TextView mTxtHeading;
    private URLManager mUrlManager;

    public static class ItemGridHolder extends ViewHolder {
        public CrossFadeImageView mCrossFadeImageView;
        public TextView mTitle;

        public ItemGridHolder(View view) {
            super(view);
            this.mCrossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.img_artwork);
            this.mTitle = (TextView) view.findViewById(R.id.txt_title);
        }
    }

    class RecyclerViewAdapter extends Adapter<ViewHolder> implements OnClickListener {
        private static final int VIEW_TYPE_FOOTER = 1;
        private static final int VIEW_TYPE_LIST = 0;

        RecyclerViewAdapter() {
        }

        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View inflate;
            if (i == 0) {
                inflate = LayoutInflater.from(JukeSeeAllFragment.this.mContext).inflate(R.layout.view_item_grid, viewGroup, false);
                inflate.setOnClickListener(this);
                return new ItemGridHolder(inflate);
            }
            inflate = LayoutInflater.from(JukeSeeAllFragment.this.mContext).inflate(R.layout.view_create_party_playlist, viewGroup, false);
            inflate.setOnClickListener(this);
            return new ItemAdViewHolder(inflate);
        }

        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            if (viewHolder.getItemViewType() == 0) {
                viewHolder.itemView.setTag(JukeSeeAllFragment.this.mBusinessObjects.get(i));
                ItemGridHolder itemGridHolder = (ItemGridHolder) viewHolder;
                itemGridHolder.mCrossFadeImageView.bindImage(((BusinessObject) JukeSeeAllFragment.this.mBusinessObjects.get(i)).getAtw());
                itemGridHolder.mTitle.setText(((BusinessObject) JukeSeeAllFragment.this.mBusinessObjects.get(i)).getName());
            }
        }

        public int getItemCount() {
            return JukeSeeAllFragment.this.mBusinessObjects.size() + 1;
        }

        public int getItemViewType(int i) {
            return i < JukeSeeAllFragment.this.mBusinessObjects.size() ? 0 : 1;
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id != R.id.ll_grid_item) {
                if (id == R.id.ll_parent_layout) {
                    if (Util.j(JukeSeeAllFragment.this.mContext)) {
                        JukePlaylist jukePlaylist = new JukePlaylist();
                        jukePlaylist.setName(JukeSeeAllFragment.this.mContext.getResources().getString(R.string.opt_my_party));
                        ((GaanaActivity) JukeSeeAllFragment.this.mContext).displayFragment(JukePartyFragment.newInstance(jukePlaylist, 0, "", false));
                    } else {
                        ap.a().f(JukeSeeAllFragment.this.mContext);
                    }
                }
            } else if (Util.j(JukeSeeAllFragment.this.mContext)) {
                BusinessObject businessObject = (BusinessObject) view.getTag();
                if (businessObject instanceof JukePlaylist) {
                    ((GaanaActivity) JukeSeeAllFragment.this.mContext).displayFragment(JukePartyFragment.newInstance(businessObject, -1, "", false));
                } else if (businessObject instanceof Item) {
                    businessObject = (Playlist) Util.b((Item) businessObject);
                    if (JukeSeeAllFragment.this.getArguments() != null) {
                        businessObject.setPartySource(JukeSeeAllFragment.this.getArguments().getString(JukeSeeAllFragment.EXTRA_ARG_TITLE, ""));
                    }
                    af.a(JukeSeeAllFragment.this.mContext, JukeSeeAllFragment.this).a((int) R.id.jukePlaylistMenu, businessObject);
                } else if (businessObject instanceof Playlist) {
                    if (JukeSeeAllFragment.this.getArguments() != null) {
                        ((Playlist) businessObject).setPartySource(JukeSeeAllFragment.this.getArguments().getString(JukeSeeAllFragment.EXTRA_ARG_TITLE, ""));
                    }
                    af.a(JukeSeeAllFragment.this.mContext, JukeSeeAllFragment.this).a((int) R.id.jukePlaylistMenu, businessObject);
                }
            } else {
                ap.a().f(JukeSeeAllFragment.this.mContext);
            }
        }
    }

    public static BaseGaanaFragment newInstance(String str, String str2, String str3, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ARG_SEE_ALL_URL, str);
        bundle.putString(EXTRA_ARG_TITLE, str2);
        bundle.putString(EXTRA_TYPE, str3);
        bundle.putBoolean(EXTRA_ARG_IS_MY_PLAYLIST, z);
        JukeSeeAllFragment jukeSeeAllFragment = new JukeSeeAllFragment();
        jukeSeeAllFragment.setArguments(bundle);
        return jukeSeeAllFragment;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.containerView == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.containerView = setContentView(R.layout.fragment_juke_see_all, viewGroup);
        }
        return this.containerView;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (ObservableRecyclerView) this.containerView.findViewById(R.id.recycler_view);
        this.mToolbar = (FrameLayout) this.containerView.findViewById(R.id.main_toolbar);
        this.mTxtHeading = (TextView) this.containerView.findViewById(R.id.txt_header);
        this.mToolbar.findViewById(R.id.back_button).setOnClickListener(this);
        this.mToolbar.findViewById(R.id.share_icon).setOnClickListener(this);
        TextView textView = (TextView) this.mToolbar.findViewById(R.id.ab_title);
        textView.setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        textView.setText(getArguments().getString(EXTRA_ARG_TITLE, ""));
        this.mTxtHeading.setText(String.format(this.mContext.getResources().getString(R.string.start_party_ideas), new Object[]{getArguments().getString(EXTRA_ARG_TITLE, "")}));
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) this.containerView.findViewById(R.id.swipe_refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                JukeSeeAllFragment.this.fetchRepositoryData(true);
                JukeSeeAllFragment.this.mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        ((TextView) this.containerView.findViewById(R.id.txt_header)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        this.isMyPlaylist = getArguments().getBoolean(EXTRA_ARG_IS_MY_PLAYLIST, false);
        this.mAdapter = new RecyclerViewAdapter();
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mContext.getResources().getDimensionPixelSize(R.dimen.dimen_8dp);
        this.mContext.getResources().getDimensionPixelSize(R.dimen.dimen_8dp);
        this.mUrlManager = getUrlManager(getArguments().getString(EXTRA_ARG_SEE_ALL_URL, ""));
        fetchRepositoryData(false);
    }

    private void fetchRepositoryData(boolean z) {
        if (this.isMyPlaylist) {
            PlaylistSyncManager.getInstance().getMyPlaylistAsync(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    JukeSeeAllFragment.this.onResponse(businessObject);
                }
            }, true);
            return;
        }
        this.mUrlManager.c(Boolean.valueOf(z));
        com.i.i.a().a(this.mUrlManager, toString(), (b) this, (a) this);
    }

    private URLManager getUrlManager(String str) {
        String string = getArguments().getString(EXTRA_TYPE, "");
        URLManager uRLManager = new URLManager();
        if (string.equals(BusinessObjectType.JukePlayLists.name())) {
            uRLManager.a(BusinessObjectType.JukePlayLists);
        } else {
            uRLManager.a(BusinessObjectType.GenericItems);
        }
        uRLManager.a(str);
        uRLManager.a((int) PsExtractor.VIDEO_STREAM_MASK);
        return uRLManager;
    }

    public void onDestroyView() {
        if (this.containerView.getParent() != null) {
            ((ViewGroup) this.containerView.getParent()).removeView(this.containerView);
        }
        j.a().a(toString());
        super.onDestroyView();
    }

    public void onResponse(Object obj) {
        if (obj instanceof BusinessObject) {
            BusinessObject businessObject = (BusinessObject) obj;
            if (businessObject.getArrListBusinessObj() != null) {
                this.mBusinessObjects.clear();
                this.mBusinessObjects.addAll(businessObject.getArrListBusinessObj());
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_button) {
            ((GaanaActivity) this.mContext).onBackPressedHandling();
        } else if (id == R.id.share_icon) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getResources().getString(R.string.party_play_friends));
            stringBuilder.append(" ");
            stringBuilder.append("https://gaana.com/");
            stringBuilder.append("view/jukepage");
            new PopupShareitemView(this.mContext, stringBuilder.toString()).shareOnOther();
        }
    }
}
