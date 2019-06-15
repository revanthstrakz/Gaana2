package com.fragments;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.PlaylistSyncManager.PLAYLIST_STATUS;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.EditTextDialog.OnButtonClickListener;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.aj;
import com.managers.ap;
import com.managers.o;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.services.d;
import com.services.h;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class SongsSelectionFragment extends BaseGaanaFragment implements OnClickListener, a {
    private ViewPager a;
    private TabLayout b;
    private FragmentStatePagerAdapter c;
    private View d = null;
    private int[] e = new int[]{R.string.search_caps, R.string.favourites_caps, R.string.queue_caps};
    private Bundle f;
    private int g = -1;
    private BaseGaanaFragment[] h = new BaseGaanaFragment[3];
    private Drawable i;
    private BusinessObject j;
    private PLAYLIST_STATUS k;
    private int l = 1;
    private boolean m = false;

    class a extends FragmentStatePagerAdapter {
        private FragmentManager b;

        public int getCount() {
            return 3;
        }

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public Fragment getItem(int i) {
            Fragment songsSelectionSearchFragment;
            switch (i) {
                case 0:
                    Bundle bundle;
                    songsSelectionSearchFragment = new SongsSelectionSearchFragment();
                    int i2 = 1;
                    if (SongsSelectionFragment.this.f != null) {
                        SongsSelectionFragment.this.f.putBoolean("is_child_fragment", true);
                        songsSelectionSearchFragment.setArguments(SongsSelectionFragment.this.f);
                    } else {
                        bundle = new Bundle();
                        bundle.putBoolean("is_child_fragment", true);
                        songsSelectionSearchFragment.setArguments(bundle);
                    }
                    bundle = SongsSelectionFragment.this.f;
                    String str = "search_type";
                    if (SongsSelectionFragment.this.l == 1) {
                        i2 = 0;
                    }
                    bundle.putInt(str, i2);
                    break;
                case 1:
                    songsSelectionSearchFragment = SongsSelectionFragment.this.d();
                    break;
                case 2:
                    songsSelectionSearchFragment = SongsSelectionFragment.this.c();
                    break;
                default:
                    songsSelectionSearchFragment = null;
                    break;
            }
            SongsSelectionFragment.this.h[i] = songsSelectionSearchFragment;
            return songsSelectionSearchFragment;
        }

        public CharSequence getPageTitle(int i) {
            return SongsSelectionFragment.this.getString(SongsSelectionFragment.this.e[i]);
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
            try {
                super.restoreState(parcelable, classLoader);
                if (parcelable != null) {
                    Bundle bundle = (Bundle) parcelable;
                    for (String str : bundle.keySet()) {
                        if (str.startsWith("f")) {
                            int parseInt = Integer.parseInt(str.substring(1));
                            Fragment fragment = this.b.getFragment(bundle, str);
                            if (fragment != null) {
                                fragment.setMenuVisibility(false);
                                SongsSelectionFragment.this.h[parseInt] = (ListingFragment) fragment;
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public boolean a() {
        return this.m;
    }

    public boolean b() {
        return this.l == 2;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.d = setContentView(R.layout.layout_tab_container_fragment, viewGroup);
            this.f = getArguments();
            if (this.f != null && this.g == -1) {
                boolean z = false;
                this.g = this.f.getInt("tab_position", 0);
                this.j = (BusinessObject) this.f.getSerializable("BUSINESS_OBJECT");
                this.l = this.f.getInt("source_type", 1);
                if (this.l == 2) {
                    this.e[2] = R.string.opt_suggestions;
                    this.j = JukeSessionManager.getInstance().getCurrentBusinessObject();
                }
                if (this.l == 0) {
                    z = true;
                }
                this.m = z;
            }
            a(this.d);
        } else if (!(this.h == null || this.h[this.g] == null)) {
            this.h[this.g].refreshListView();
        }
        if (this.l == 2) {
            setGAScreenName("Party_AddSongs", "Party_AddSongs");
        }
        updateView();
        return this.d;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void a(View view) {
        this.a = (ViewPager) view.findViewById(R.id.viewpager);
        new int[1][0] = R.attr.ic_action_accept;
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(R.styleable.VectorDrawables);
        this.i = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(19, -1));
        obtainStyledAttributes.recycle();
        View inflate = this.layoutInflater.inflate(R.layout.view_top_tabbar_buttons, null);
        setActionBar(view, inflate, false);
        inflate.findViewById(R.id.btnLeft).setOnClickListener(this);
        inflate.findViewById(R.id.btnRight).setOnClickListener(this);
        ((ImageView) inflate.findViewById(R.id.btnRight)).setImageDrawable(this.i);
        ((TextView) inflate.findViewById(R.id.tvCurrentViewTag)).setText(R.string.add_to_playlist);
        this.c = new a(getChildFragmentManager());
        this.a.setAdapter(this.c);
        this.a.setOffscreenPageLimit(2);
        this.b = (TabLayout) view.findViewById(R.id.sliding_tabs);
        this.b.setupWithViewPager(this.a);
        this.b.getTabAt(this.g).select();
        this.b.setOnTabSelectedListener(new OnTabSelectedListener() {
            public void onTabReselected(Tab tab) {
            }

            public void onTabUnselected(Tab tab) {
            }

            public void onTabSelected(Tab tab) {
                SongsSelectionFragment.this.a.setCurrentItem(tab.getPosition());
                SongsSelectionFragment.this.g = tab.getPosition();
                if (SongsSelectionFragment.this.h != null && SongsSelectionFragment.this.h[SongsSelectionFragment.this.g] != null) {
                    SongsSelectionFragment.this.h[SongsSelectionFragment.this.g].refreshListView();
                }
            }
        });
    }

    public void onStop() {
        super.onStop();
    }

    public BaseGaanaFragment c() {
        ListingComponents b;
        ListingParams listingParams = new ListingParams();
        String[] strArr = null;
        if (this.l != 2) {
            ArrayList arrListBusinessObj;
            listingParams.e(false);
            listingParams.f(true);
            listingParams.d(false);
            listingParams.i(false);
            listingParams.a(false);
            if (((this.j instanceof Playlist) || (this.j instanceof Tracks)) && this.j != null && this.j.getArrListBusinessObj() != null && this.j.getArrListBusinessObj().size() > 0) {
                arrListBusinessObj = this.j.getArrListBusinessObj();
            }
            b = Constants.b(SearchType.Playlist_Search, arrListBusinessObj);
            listingParams.a((ListingButton) b.c().get(0));
            listingParams.a(SearchType.Playlist_Search);
        } else {
            listingParams.e(false);
            listingParams.f(true);
            listingParams.d(false);
            listingParams.i(false);
            listingParams.a(false);
            ArrayList arrListBusinessObj2 = (((this.j instanceof Playlist) || (this.j instanceof Tracks)) && this.j != null && this.j.getArrListBusinessObj() != null && this.j.getArrListBusinessObj().size() > 0) ? this.j.getArrListBusinessObj() : null;
            BusinessObject currentBusinessObject = JukeSessionManager.getInstance().getCurrentBusinessObject();
            if (!(currentBusinessObject == null || currentBusinessObject.getArrListBusinessObj() == null)) {
                int size = currentBusinessObject.getArrListBusinessObj().size();
                if (size > 5) {
                    strArr = new String[5];
                    Random random = new Random();
                    strArr[0] = ((BusinessObject) currentBusinessObject.getArrListBusinessObj().get(size - 1)).getBusinessObjId();
                    strArr[1] = ((BusinessObject) currentBusinessObject.getArrListBusinessObj().get(size - 2)).getBusinessObjId();
                    size -= 3;
                    strArr[2] = ((BusinessObject) currentBusinessObject.getArrListBusinessObj().get(size)).getBusinessObjId();
                    strArr[3] = ((BusinessObject) currentBusinessObject.getArrListBusinessObj().get(random.nextInt(size))).getBusinessObjId();
                    strArr[4] = ((BusinessObject) currentBusinessObject.getArrListBusinessObj().get(random.nextInt(size))).getBusinessObjId();
                } else if (size > 0) {
                    strArr = new String[size];
                    for (size--; size >= 0; size--) {
                        strArr[size] = ((BusinessObject) currentBusinessObject.getArrListBusinessObj().get(size)).getBusinessObjId();
                    }
                }
            }
            b = Constants.a(strArr, arrListBusinessObj2);
            listingParams.a((ListingButton) b.c().get(0));
            listingParams.a(SearchType.Playlist_Search);
        }
        ListingFragment listingFragment = new ListingFragment();
        listingFragment.a(listingParams);
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_child_fragment", true);
        listingFragment.setArguments(bundle);
        GaanaApplication.getInstance().setListingComponents(b);
        return listingFragment;
    }

    public BaseGaanaFragment d() {
        ListingParams listingParams = new ListingParams();
        listingParams.e(false);
        listingParams.f(true);
        listingParams.d(false);
        listingParams.i(false);
        listingParams.a(false);
        ArrayList arrListBusinessObj = (((this.j instanceof Playlist) || (this.j instanceof Tracks)) && this.j != null && this.j.getArrListBusinessObj() != null && this.j.getArrListBusinessObj().size() > 0) ? this.j.getArrListBusinessObj() : null;
        ListingComponents a = Constants.a(SearchType.Playlist_Search, arrListBusinessObj);
        ListingButton listingButton = (ListingButton) a.c().get(0);
        listingButton.c().a(Track.class);
        listingParams.a(listingButton);
        listingParams.a(SearchType.Playlist_Search);
        ListingFragment listingFragment = new ListingFragment();
        listingFragment.a(listingParams);
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_child_fragment", true);
        listingFragment.setArguments(bundle);
        GaanaApplication.getInstance().setListingComponents(a);
        return listingFragment;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLeft) {
            Util.a(this.mContext, view);
            if (this.mAppState.getArrListForTrackIds() != null && this.mAppState.getArrListForTrackIds().size() > 0) {
                this.mAppState.getArrListForTrackIds().clear();
            }
            if (this.mAppState.getArrListForPlaylistIds() != null && this.mAppState.getArrListForPlaylistIds().size() > 0) {
                this.mAppState.getArrListForPlaylistIds().clear();
            }
            this.mAppState.setArrListTracksForPlaylist(null);
            if (((GaanaActivity) getActivity()) != null) {
                ((GaanaActivity) getActivity()).onBackPressedHandling();
            }
        } else if (id == R.id.btnRight) {
            Util.a(this.mContext, view);
            if (this.j != null && ((this.j instanceof Playlist) || (this.j instanceof JukePlaylist))) {
                a(this.j);
            }
        }
    }

    private void b(ArrayList<Track> arrayList) {
        if (this.j != null) {
            if (this.j.getArrListBusinessObj() == null) {
                if (this.j instanceof JukePlaylist) {
                    ((JukePlaylist) this.j).setPartyTracks(new ArrayList());
                } else {
                    this.j.setArrListBusinessObj(new ArrayList());
                }
            }
            ArrayList arrListBusinessObj = this.j.getArrListBusinessObj();
            if (arrListBusinessObj != null) {
                arrListBusinessObj.addAll(JukeSessionManager.getJukeTrackList(arrayList));
                if (JukeSessionManager.getInstance().getCurrentSessionType() != 0) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        JukeSessionManager.getInstance().addDeleteTracks((JukePlaylist) this.j, ((Track) arrayList.get(i)).getBusinessObjId(), true);
                    }
                }
            }
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.songs_added_party));
            ((GaanaActivity) this.mContext).onBackPressedHandling();
        }
    }

    private void a(BusinessObject businessObject) {
        final ArrayList arrListTracksForPlaylist = this.mAppState.getArrListTracksForPlaylist();
        if (arrListTracksForPlaylist == null || arrListTracksForPlaylist.size() == 0) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.select_atleas_a_track));
            return;
        }
        boolean z = false;
        if (b()) {
            u a = u.a();
            String str = "PartyHub";
            String str2 = "SongsAdded";
            String str3 = ((this.j instanceof JukePlaylist) && ((JukePlaylist) this.j).getAdmin()) ? "Admin" : "Joinee";
            a.a(str, str2, str3);
            CharSequence b = d.a().b("pref_juke_nick", "", false);
            if (TextUtils.isEmpty(b) && TextUtils.isEmpty(JukeSessionManager.getInstance().getUserNick())) {
                z = true;
            } else if (TextUtils.isEmpty(b)) {
                d.a().a("pref_juke_nick", JukeSessionManager.getInstance().getUserNick(), false);
                b = JukeSessionManager.getInstance().getUserNick();
            } else if (TextUtils.isEmpty(JukeSessionManager.getInstance().getUserNick())) {
                JukeSessionManager.getInstance().setUserNick(b);
            }
            if (z) {
                b = "";
            }
            if (JukeSessionManager.getInstance().getCurrentSessionType() == 0 || !TextUtils.isEmpty(r8)) {
                b(arrListTracksForPlaylist);
            } else {
                JukeSessionManager.getNickDialog(this.mContext, "", new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick(String str) {
                        d.a().a("pref_juke_nick", str, false);
                        JukeSessionManager.getInstance().renameNickName(str, new s() {
                            public void onRetreivalComplete(BusinessObject businessObject) {
                                SongsSelectionFragment.this.b(arrListTracksForPlaylist);
                            }

                            public void onErrorResponse(BusinessObject businessObject) {
                                aj.a().a(SongsSelectionFragment.this.mContext, SongsSelectionFragment.this.mContext.getResources().getString(R.string.some_error_occurred));
                            }
                        });
                    }
                });
            }
            return;
        }
        final Playlist playlist = (Playlist) businessObject;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(arrListTracksForPlaylist.size());
        u.a().a("Add to Playlist", "Find more", stringBuilder.toString());
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), getString(R.string.adding_to_playlist_text));
        h.a().a(100);
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                SongsSelectionFragment.this.mAppState.setArrListTracksForPlaylist(null);
                o a = o.a();
                String[] strArr = new String[2];
                strArr[0] = "type=playlist&subtype=playlist_detail";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("playlist_id=");
                stringBuilder.append(playlist.getBusinessObjId());
                strArr[1] = stringBuilder.toString();
                a.a(strArr);
                if (ap.a().o()) {
                    int a2 = Util.a(playlist.getBusinessObjId());
                    if (a2 != 0 && DownloadManager.c().b(playlist).booleanValue()) {
                        DownloadManager.c().b(arrListTracksForPlaylist, a2, true);
                    }
                }
                if (SongsSelectionFragment.this.k == PLAYLIST_STATUS.SUCCESS) {
                    PlaylistSyncManager.getInstance().updatePlaylistMemCache(Util.a(playlist.getBusinessObjId()));
                }
                SongsSelectionFragment.this.e();
            }

            public void doBackGroundTask() {
                SongsSelectionFragment.this.a(arrListTracksForPlaylist);
                SongsSelectionFragment.this.k = PlaylistSyncManager.getInstance().addToPlaylist((Activity) SongsSelectionFragment.this.mContext, playlist, arrListTracksForPlaylist);
            }
        }, 100);
    }

    public void a(ArrayList<Track> arrayList) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(arrayList);
        arrayList.clear();
        arrayList.addAll(linkedHashSet);
    }

    private void e() {
        String str = "";
        switch (this.k) {
            case SUCCESS:
                str = this.mContext.getString(R.string.songs_added_to_playlist);
                u.a().b("Playlist", "Add Songs");
                break;
            case FAILED:
                str = this.mContext.getString(R.string.songs_add_failed);
                break;
            case ALREADY_ADDED:
                str = this.mContext.getString(R.string.songs_already_in_playlist);
                break;
        }
        if (this.mAppState.getArrListForTrackIds() != null && this.mAppState.getArrListForTrackIds().size() > 0) {
            this.mAppState.getArrListForTrackIds().clear();
        }
        if (this.mAppState.getArrListForPlaylistIds() != null && this.mAppState.getArrListForPlaylistIds().size() > 0) {
            this.mAppState.getArrListForPlaylistIds().clear();
        }
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).hideProgressDialog();
        }
        aj.a().a(this.mContext, str);
        ((GaanaActivity) this.mContext).setRefreshData(true);
        try {
            if (((GaanaActivity) this.mContext).getCurrentFragment() != null && (((GaanaActivity) this.mContext).getCurrentFragment() instanceof SongsSelectionFragment) && ((GaanaActivity) this.mContext).getCurrentFragment().isVisible()) {
                ((GaanaActivity) this.mContext).popBackStack();
            }
        } catch (Exception unused) {
        }
    }

    public void onDestroy() {
        if (this.mAppState.getArrListForTrackIds() != null && this.mAppState.getArrListForTrackIds().size() > 0) {
            this.mAppState.getArrListForTrackIds().clear();
        }
        if (this.mAppState.getArrListForPlaylistIds() != null && this.mAppState.getArrListForPlaylistIds().size() > 0) {
            this.mAppState.getArrListForPlaylistIds().clear();
        }
        this.mAppState.setArrListTracksForPlaylist(null);
        super.onDestroy();
    }

    public void onDestroyView() {
        if (((ViewGroup) this.d.getParent()) != null) {
            ((ViewGroup) this.d.getParent()).removeView(this.d);
        }
        super.onDestroyView();
    }
}
