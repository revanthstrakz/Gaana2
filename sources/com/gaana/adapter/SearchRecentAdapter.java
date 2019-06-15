package com.gaana.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.d;
import com.dynamicview.DynamicOccasionFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.SearchTabFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.library.controls.RoundedCustomImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.GaanaSearchManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.services.c;
import com.services.l.ag;
import com.utilities.Util;
import java.util.ArrayList;

public class SearchRecentAdapter extends Adapter<SearchRecentItemViewHolder> implements OnClickListener {
    private final Context mContext;
    private final BaseGaanaFragment mFragment;
    private ArrayList<AutoComplete> mRecentSearch;

    public class SearchRecentItemViewHolder extends ViewHolder {
        private final RoundedCustomImageView imageView;
        private final TextView titleTextView;

        public SearchRecentItemViewHolder(View view) {
            super(view);
            this.imageView = (RoundedCustomImageView) view.findViewById(R.id.image);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }

    public SearchRecentAdapter(Context context, SearchTabFragment searchTabFragment, ArrayList<AutoComplete> arrayList) {
        this.mRecentSearch = arrayList;
        this.mFragment = searchTabFragment;
        this.mContext = context;
    }

    public void updateAdapter(ArrayList<AutoComplete> arrayList) {
        this.mRecentSearch = arrayList;
        notifyDataSetChanged();
    }

    public SearchRecentItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SearchRecentItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_recent_item_view, null));
    }

    public void onBindViewHolder(SearchRecentItemViewHolder searchRecentItemViewHolder, int i) {
        if (i == 0) {
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.leftMargin = (int) this.mContext.getResources().getDimension(R.dimen.dp10);
            searchRecentItemViewHolder.itemView.setLayoutParams(layoutParams);
        }
        searchRecentItemViewHolder.imageView.bindImage(((AutoComplete) this.mRecentSearch.get(i)).getArtwork());
        searchRecentItemViewHolder.titleTextView.setText(((AutoComplete) this.mRecentSearch.get(i)).getTitle());
        searchRecentItemViewHolder.itemView.setTag(this.mRecentSearch.get(i));
        if ("Occasion".equalsIgnoreCase(((AutoComplete) this.mRecentSearch.get(i)).getType())) {
            searchRecentItemViewHolder.imageView.setRightIconVisibility(false);
        } else {
            searchRecentItemViewHolder.imageView.setRightIconVisibility(true);
        }
        searchRecentItemViewHolder.itemView.setOnClickListener(this);
    }

    public int getItemCount() {
        return this.mRecentSearch != null ? this.mRecentSearch.size() : 0;
    }

    public void onClick(View view) {
        handleClick(view);
    }

    private void handleClick(View view) {
        GaanaApplication instance = GaanaApplication.getInstance();
        AutoComplete autoComplete = (AutoComplete) view.getTag();
        if (autoComplete.getType() == null && autoComplete.isRecentSearch()) {
            GaanaSearchManager.a().a(view);
            return;
        }
        BusinessObject a = Util.a(autoComplete, instance);
        if (a.getBusinessObjType() == BusinessObjectType.Radios) {
            af.a(this.mContext, this.mFragment).a((int) R.id.radioMenu, a);
        } else if (a.getBusinessObjType() == BusinessObjectType.Occasion) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/home/occasion/meta/v2/");
            stringBuilder.append(autoComplete.getOccasionUrl());
            loadOccaionPage(stringBuilder.toString());
        } else {
            if (a.getBusinessObjType() == BusinessObjectType.Tracks) {
                if ("1".equalsIgnoreCase(a.getLocationAvailability()) && "0".equalsIgnoreCase(a.getDeviceAvailability())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.mContext);
                    }
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                    return;
                } else if ("0".equalsIgnoreCase(a.getLocationAvailability()) && "1".equalsIgnoreCase(a.getDeviceAvailability())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.mContext);
                    }
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                    return;
                } else if (!(DownloadManager.c().e(Integer.parseInt(a.getBusinessObjId())) != DownloadStatus.DOWNLOADED || !Util.v() || DownloadManager.c().f(Integer.parseInt(a.getBusinessObjId())) || ap.a().o() || DownloadManager.c().j(a.getBusinessObjId()).booleanValue())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.mContext);
                    }
                    aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.downloaded_songs_stream_online));
                }
            }
            if ((!instance.isAppInOfflineMode() && Util.j(this.mContext)) || a.isLocalMedia() || Util.c(a) || isMyPlaylist(a)) {
                ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
                if (!autoComplete.isRecentSearch() && autoComplete.isRecommendedSearch()) {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_RECOMMENDED.name());
                    u.a().b("SEARCH_RECOMMENDED", "Search_Recommended_Clicks");
                } else if (GaanaSearchManager.a().m()) {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_VOICE.name());
                } else if (Constants.au) {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICE_AUTO_SUGGEST.name());
                } else {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name());
                }
                ((GaanaActivity) this.mContext).setCurrentSongSelectedView(view);
                c.a(this.mContext).b(this.mContext, a, SOURCE_TYPE.SEARCH.ordinal());
            } else {
                if (GaanaSearchManager.a().g() != null) {
                    GaanaSearchManager.a().g().a(this.mContext);
                }
                ap.a().f(this.mContext);
                return;
            }
        }
        String str;
        if (autoComplete.isRecentSearch()) {
            GaanaSearchManager.a().b((Activity) this.mContext, "RecentSearch", autoComplete.getEnglishTitle());
            GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.RECENT_SEARCH.name());
            GaanaSearchManager.a().b(true);
            str = "";
            if (a instanceof Track) {
                str = "TRACK";
            } else if (a instanceof Playlist) {
                str = "PLAYLIST";
            } else if (a instanceof Album) {
                str = "ALBUM";
            } else if (a instanceof Artist) {
                str = "ARTIST";
            } else if (a instanceof Radio) {
                str = "RADIO";
            }
            an.a().a("click", "ac", "", "RECENT SEARCH", a.getBusinessObjId(), str, "", "");
        } else if (!autoComplete.isRecommendedSearch()) {
            String str2;
            GaanaSearchManager.a().a(autoComplete);
            str = autoComplete.getType();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Tap-");
            stringBuilder2.append(str);
            str = stringBuilder2.toString();
            String type = autoComplete.getType();
            if (!TextUtils.isEmpty(autoComplete.getSectionType())) {
                type = autoComplete.getSectionType();
                str = autoComplete.getSectionType().equals("MY_DOWNLOADS") ? "Tap-Downloads" : autoComplete.getSectionType().equals("SEARCH_TOP_RESULT") ? "Tap-Top Result" : "Tap-Local Files";
            }
            String str3 = str;
            if (type.equalsIgnoreCase("Radio")) {
                str2 = ((Radio) a).getType().equalsIgnoreCase(d.c) ? "RADIO_MIRCHI" : "GAANA_RADIO";
            } else {
                str2 = type;
            }
            GaanaSearchManager.a().a((Activity) this.mContext, str3, autoComplete.getPosition(), autoComplete.getTitle(), false, str2, autoComplete.getBusinessObjectId());
            GaanaSearchManager a2 = GaanaSearchManager.a();
            type = autoComplete.getTitle();
            String type2 = autoComplete.getType();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Util.c(a.getBusinessObjType()));
            stringBuilder3.append(autoComplete.getBusinessObjectId());
            a2.a(type, type2, stringBuilder3.toString());
        }
    }

    private void loadOccaionPage(final String str) {
        com.dynamicview.c.a().a(new ag() {
            public void onOccasionResponse() {
                BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("OCCASION_URL", str);
                bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                dynamicOccasionFragment.setArguments(bundle);
                ((GaanaActivity) SearchRecentAdapter.this.mContext).displayFragment(dynamicOccasionFragment);
            }

            public void onOccasionError() {
                aj.a().a(SearchRecentAdapter.this.mContext, SearchRecentAdapter.this.mContext.getResources().getString(R.string.error_download_no_internet));
            }
        }, str, null, false);
    }

    private boolean isMyPlaylist(BusinessObject businessObject) {
        return businessObject.getBusinessObjType() == BusinessObjectType.Playlists && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) businessObject);
    }
}
