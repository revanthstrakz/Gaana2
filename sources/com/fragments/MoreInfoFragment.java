package com.fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.MoreInfoListAdapter;
import com.gaana.adapter.MoreInfoListAdapter.IAddListItemView;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.MoreInfo;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserMessage;
import com.gaana.view.MoreInfoListing;
import com.gaana.view.item.BaseItemView.MoreInfoListingItemHolder;
import com.gaana.view.item.PopupWindowView;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.n;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.l.af;
import com.services.l.as;
import com.utilities.Util;
import java.util.Calendar;
import java.util.HashMap;

public class MoreInfoFragment extends BaseGaanaFragment implements IAddListItemView {
    private LayoutInflater a;
    private View b = null;
    private MoreInfoListAdapter c;
    private String d = "";
    private String e = "0";
    private URLManager f = null;
    private MoreInfo g;
    private BusinessObjectType h;
    private BusinessObject i;
    private BusinessObject j;
    private LinearLayout k;
    private MenuItem l;
    private DetailsMaterialActionBar m;
    private Toolbar n;
    private Menu o;
    private BusinessObject p;
    private ProgressBar q;

    public int getItemViewType(int i) {
        return 0;
    }

    public void showHideEmtpyView(boolean z) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.a = getActivity().getLayoutInflater();
            this.b = setContentView(R.layout.layout_more_info, viewGroup);
            this.q = (ProgressBar) this.b.findViewById(R.id.progressbar);
            TextView textView = (TextView) this.b.findViewById(R.id.other_details);
            String[] split = this.mContext.getString(R.string.radio_details_title).split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.mode_other));
            stringBuilder.append(" ");
            stringBuilder.append(split[split.length - 1]);
            textView.setText(stringBuilder.toString());
            ((TextView) this.b.findViewById(R.id.albumInfoTitle)).setText(this.mContext.getString(R.string.album_text).trim());
            this.q.setVisibility(0);
            f();
        }
        i();
        return this.b;
    }

    private void f() {
        String string = getArguments().getString("TRACKID");
        String string2 = getArguments().getString("ALBUMID");
        String string3 = getArguments().getString("PLAYLISTID");
        this.p = (BusinessObject) getArguments().getSerializable("BUSINESS_OBJECT");
        if (string != null) {
            this.h = BusinessObjectType.Tracks;
            c();
            a(string);
            g();
        } else if (string2 != null) {
            this.h = BusinessObjectType.Albums;
            a();
            b(string2);
            g();
        } else if (string3 != null) {
            this.h = BusinessObjectType.Playlists;
            b();
            c(string3);
            g();
        }
    }

    private void g() {
        this.k = (LinearLayout) this.b.findViewById(R.id.sections_container);
        h();
    }

    private void h() {
        this.n = (Toolbar) this.b.findViewById(R.id.main_toolbar);
        this.n.setContentInsetsAbsolute(0, 0);
        this.n.getMenu().clear();
        this.n.inflateMenu(R.menu.cast_menu_detail);
        ((BaseActivity) this.mContext).initializeMediaRouterButton(this.n.getMenu(), R.id.media_route_menu_item);
        this.o = this.n.getMenu();
        this.o.findItem(R.id.menu_search).setVisible(false);
        this.o.findItem(R.id.menu_option).setVisible(false);
        this.o.findItem(R.id.menu_favourite).setVisible(true);
        this.m = new DetailsMaterialActionBar(this.mContext);
        this.m.setParams(this, this.j);
        this.m.showContextMenu(false);
        this.m.setToolbar(this.n);
        ImageView imageView = (ImageView) this.m.findViewById(R.id.menu_icon);
        new int[1][0] = R.attr.actionbar_back;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        Drawable drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(0, -1));
        obtainStyledAttributes.recycle();
        imageView.setImageDrawable(drawable);
        this.n.addView(this.m);
        TextView textView = (TextView) this.m.findViewById(R.id.title);
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
        textView.setTextColor(typedValue.data);
        textView.setTextSize(2, 20.0f);
        switch (this.h) {
            case Albums:
                textView.setText(getResources().getString(R.string.more_info_album_info));
                break;
            case Tracks:
                textView.setText(getResources().getString(R.string.more_info_song_info));
                break;
            case Playlists:
                textView.setText(getResources().getString(R.string.more_info_playlist_info));
                break;
        }
        this.l = this.n.getMenu().findItem(R.id.menu_download);
        e();
        d();
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void a(String str) {
        URLManager uRLManager = new URLManager();
        HashMap hashMap = new HashMap();
        hashMap.put("track_id", str);
        uRLManager.a(hashMap);
        uRLManager.a("https://api.gaana.com/track/info?");
        uRLManager.c(0);
        uRLManager.a(MoreInfo.class);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Priority.IMMEDIATE);
        uRLManager.i(true);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                MoreInfo moreInfo = (MoreInfo) obj;
                if (MoreInfoFragment.this.isAdded() && moreInfo != null) {
                    MoreInfoFragment.this.g = moreInfo;
                    MoreInfoFragment.this.i = (BusinessObject) obj;
                    MoreInfoFragment.this.b(moreInfo);
                    MoreInfoFragment.this.a(moreInfo);
                    MoreInfoFragment.this.c(moreInfo);
                    MoreInfoFragment.this.a(MoreInfoFragment.this.getArguments().getString("TRACK_TITLE"), moreInfo.getAlbumTitle());
                    MoreInfoFragment.this.q.setVisibility(8);
                }
            }
        }, uRLManager);
    }

    private void b(String str) {
        URLManager uRLManager = new URLManager();
        HashMap hashMap = new HashMap();
        hashMap.put("album_id", str);
        uRLManager.a(hashMap);
        uRLManager.a("https://api.gaana.com/album/info?");
        uRLManager.c(0);
        uRLManager.a(MoreInfo.class);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Priority.IMMEDIATE);
        uRLManager.i(true);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                MoreInfo moreInfo = (MoreInfo) obj;
                if (moreInfo != null && MoreInfoFragment.this.isAdded()) {
                    MoreInfoFragment.this.i = (BusinessObject) obj;
                    MoreInfoFragment.this.g = moreInfo;
                    MoreInfoFragment.this.b(moreInfo);
                    MoreInfoFragment.this.a(moreInfo);
                    MoreInfoFragment moreInfoFragment = MoreInfoFragment.this;
                    String title = moreInfo.getTitle();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(String.valueOf(moreInfo.getTrackcount()));
                    stringBuilder.append(" tracks");
                    moreInfoFragment.a(title, stringBuilder.toString());
                    MoreInfoFragment.this.d(moreInfo.getVendor());
                    MoreInfoFragment.this.q.setVisibility(8);
                }
            }
        }, uRLManager);
    }

    private void c(String str) {
        URLManager uRLManager = new URLManager();
        HashMap hashMap = new HashMap();
        hashMap.put("playlist_id", str);
        uRLManager.a(hashMap);
        uRLManager.a("https://api.gaana.com/playlist/info?");
        uRLManager.c(0);
        uRLManager.a(MoreInfo.class);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Priority.IMMEDIATE);
        uRLManager.i(true);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                MoreInfo moreInfo = (MoreInfo) obj;
                if (moreInfo != null && MoreInfoFragment.this.isAdded()) {
                    MoreInfoFragment.this.i = (BusinessObject) obj;
                    MoreInfoFragment.this.g = moreInfo;
                    MoreInfoFragment.this.b(moreInfo);
                    MoreInfoFragment.this.a(moreInfo);
                    MoreInfoFragment moreInfoFragment = MoreInfoFragment.this;
                    String title = moreInfo.getTitle();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(String.valueOf(moreInfo.getTrackcount()));
                    stringBuilder.append(" tracks");
                    moreInfoFragment.a(title, stringBuilder.toString());
                    MoreInfoFragment.this.q.setVisibility(8);
                }
            }
        }, uRLManager);
    }

    private void a(MoreInfo moreInfo) {
        if (moreInfo.getSingers() != null && moreInfo.getSingers().size() > 0) {
            a(getResources().getString(R.string.more_info_singers), "Singer", moreInfo.getSingers().size());
        }
        if (moreInfo.getComposers() != null && moreInfo.getComposers().size() > 0) {
            a(getResources().getString(R.string.more_info_composers), "Composer", moreInfo.getComposers().size());
        }
        if (moreInfo.getLyricist() != null && moreInfo.getLyricist().size() > 0) {
            a(getResources().getString(R.string.more_info_lyricists), "Lyricist", moreInfo.getLyricist().size());
        }
        if (moreInfo.getCast() != null && moreInfo.getCast().size() > 0) {
            a(getResources().getString(R.string.more_info_cast), "Cast", moreInfo.getCast().size());
        }
    }

    private void a(String str, String str2, int i) {
        MoreInfoListing moreInfoListing = new MoreInfoListing(this.mContext, str);
        RecyclerView recyclerView = moreInfoListing.getRecyclerView();
        recyclerView.setHasFixedSize(false);
        this.c = new MoreInfoListAdapter(this.mContext);
        recyclerView.setAdapter(this.c);
        this.c.setParamaters(i, this, str2);
        this.k.addView(moreInfoListing);
    }

    private void b(MoreInfo moreInfo) {
        TextView textView = (TextView) this.b.findViewById(R.id.languages);
        TextView textView2 = (TextView) this.b.findViewById(R.id.tracks);
        TextView textView3 = (TextView) this.b.findViewById(R.id.duration);
        TextView textView4 = (TextView) this.b.findViewById(R.id.createdon);
        TextView textView5 = (TextView) this.b.findViewById(R.id.modifiedon);
        TextView textView6 = (TextView) this.b.findViewById(R.id.release_on_key);
        TextView textView7 = (TextView) this.b.findViewById(R.id.languages_key);
        TextView textView8 = (TextView) this.b.findViewById(R.id.tracks_key);
        TextView textView9 = (TextView) this.b.findViewById(R.id.duration_key);
        TextView textView10 = (TextView) this.b.findViewById(R.id.createdon_key);
        TextView textView11 = (TextView) this.b.findViewById(R.id.modifiedon_key);
        a(textView6, (TextView) this.b.findViewById(R.id.release_on), moreInfo.getReleaseDate());
        a(textView7, textView, moreInfo.getLanguage());
        if (moreInfo.getTrackcount() != 0) {
            a(textView8, textView2, String.valueOf(moreInfo.getTrackcount()));
        }
        if (moreInfo.getDuration() != null) {
            StringBuilder stringBuilder;
            int intValue = Float.valueOf(moreInfo.getDuration()).intValue();
            int i = (intValue % 3600) / 60;
            String valueOf = String.valueOf(intValue % 60);
            if (valueOf.length() == 1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("0");
                stringBuilder.append(valueOf);
                valueOf = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(":");
            stringBuilder.append(valueOf);
            a(textView9, textView3, stringBuilder.toString());
        }
        a(textView10, textView4, moreInfo.getCreatedOn());
        a(textView11, textView5, moreInfo.getModifiedOn());
    }

    private void c(MoreInfo moreInfo) {
        ((RelativeLayout) this.b.findViewById(R.id.albumInfo)).setVisibility(0);
        ((CrossFadeImageView) this.b.findViewById(R.id.albumThumbnail)).bindImage(moreInfo.getAlbumArtwork());
        ((TextView) this.b.findViewById(R.id.album_title)).setText(moreInfo.getAlbumTitle());
        ((TextView) this.b.findViewById(R.id.artist_title)).setText(getArguments().getString("ARTIST_NAMES"));
        d(moreInfo.getVendor());
        this.b.findViewById(R.id.top_divider).setVisibility(0);
        final BusinessObject album = new Album();
        album.setBusinessObjId(moreInfo.getAlbumId());
        album.setName(moreInfo.getAlbumTitle());
        album.setBusinessObjType(BusinessObjectType.Albums);
        album.setArtwork(moreInfo.getArtwork());
        a(album);
        ((ImageView) this.b.findViewById(R.id.favorite)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                com.managers.af a = com.managers.af.a(MoreInfoFragment.this.mContext, MoreInfoFragment.this);
                a.a("Song Info");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Album ");
                stringBuilder.append(album.getBusinessObjId());
                a.b(stringBuilder.toString());
                a.a((int) R.id.favoriteMenu, album);
                MoreInfoFragment.this.a(album);
            }
        });
        ((RelativeLayout) this.b.findViewById(R.id.albumInfo)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                com.managers.af.a(MoreInfoFragment.this.mContext, MoreInfoFragment.this).a((int) R.id.albumMenu, album);
                an.a().a("click", "ac", "", "", album.getBusinessObjId(), "album detail", "", "");
            }
        });
        TextView textView = (TextView) this.b.findViewById(R.id.goToLyrics);
        if (!TextUtils.isEmpty(getArguments().getString("LYRICS_URL"))) {
            textView.setVisibility(0);
            this.b.findViewById(R.id.bottom_divider).setVisibility(0);
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String string = MoreInfoFragment.this.getArguments().getString("LYRICS_URL");
                    Intent intent = new Intent(MoreInfoFragment.this.mContext, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", string);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", "Lyrics");
                    an.a().a("click", "ac", "", "", "", "lyrics", "", "");
                    MoreInfoFragment.this.mContext.startActivity(intent);
                }
            });
        }
    }

    private void d(String str) {
        TextView textView = (TextView) this.b.findViewById(R.id.vendor);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("© ");
        stringBuilder.append(Calendar.getInstance().get(1));
        stringBuilder.append(" ");
        stringBuilder.append(str);
        textView.setText(stringBuilder.toString());
        textView.setVisibility(0);
    }

    public void a(String str, String str2) {
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) this.b.findViewById(R.id.bgHeaderImg);
        ((CrossFadeImageView) this.b.findViewById(R.id.headerImg)).bindImage(this.g.getArtwork());
        crossFadeImageView.bindImage(this.g.getArtwork(), ScaleType.CENTER_CROP);
        TextView textView = (TextView) this.b.findViewById(R.id.entity_subtitle);
        ((TextView) this.b.findViewById(R.id.entity_title)).setText(str);
        textView.setText(str2);
    }

    public void a() {
        this.j = new Album();
        this.j.setBusinessObjId(getArguments().getString("ALBUMID"));
        this.j.setBusinessObjType(BusinessObjectType.Albums);
        this.j.setName(getArguments().getString("ALBUM_NAME"));
    }

    public void b() {
        this.j = new Playlist();
        this.j.setBusinessObjId(getArguments().getString("PLAYLISTID"));
        this.j.setBusinessObjType(BusinessObjectType.Playlists);
        this.j.setName(getArguments().getString("PLAYLIST_NAME"));
    }

    public void c() {
        this.j = new Track();
        this.j.setBusinessObjId(getArguments().getString("TRACKID"));
        this.j.setBusinessObjType(BusinessObjectType.Tracks);
        this.j.setName(getArguments().getString("TRACK_TITLE"));
        ((Track) this.j).setAlbumId(getArguments().getString("ALBUMID"));
        ((Track) this.j).setArtist(((Track) this.p).getArtists());
    }

    public void a(BusinessObject businessObject) {
        ImageView imageView = (ImageView) this.b.findViewById(R.id.favorite);
        TypedArray obtainStyledAttributes;
        if (n.a().a(businessObject)) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(51, -1)));
            obtainStyledAttributes.recycle();
            return;
        }
        new int[1][0] = R.attr.moreoptions_favorite;
        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(49, -1)));
        obtainStyledAttributes.recycle();
    }

    public void d() {
        if (this.n != null) {
            Menu menu = this.n.getMenu();
            if (menu != null) {
                MenuItem findItem = menu.findItem(R.id.menu_favourite);
                if (n.a().a(this.j)) {
                    findItem.setIcon(R.drawable.vector_more_option_favorited);
                } else if (Constants.l) {
                    findItem.setIcon(R.drawable.vector_ab_favorite);
                } else {
                    findItem.setIcon(R.drawable.vector_ab_favorite_white);
                }
            }
        }
    }

    private void a(TextView textView, TextView textView2, String str) {
        if (str != "" && str != null) {
            textView2.setText(str);
            textView2.setVisibility(0);
            textView.setVisibility(0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004e  */
    public android.view.View addListItemView(int r4, android.support.v7.widget.RecyclerView.ViewHolder r5, android.view.ViewGroup r6, java.lang.String r7) {
        /*
        r3 = this;
        r6 = r5;
        r6 = (com.gaana.view.item.BaseItemView.MoreInfoListingItemHolder) r6;
        r0 = r7.hashCode();
        r1 = -1818398820; // 0xffffffff939d6f9c float:-3.9742427E-27 double:NaN;
        if (r0 == r1) goto L_0x003a;
    L_0x000c:
        r1 = -1607858197; // 0xffffffffa02a07eb float:-1.440218E-19 double:NaN;
        if (r0 == r1) goto L_0x0030;
    L_0x0011:
        r1 = -534698688; // 0xffffffffe0212540 float:-4.644703E19 double:NaN;
        if (r0 == r1) goto L_0x0026;
    L_0x0016:
        r1 = 2092895; // 0x1fef5f float:2.93277E-39 double:1.0340275E-317;
        if (r0 == r1) goto L_0x001c;
    L_0x001b:
        goto L_0x0044;
    L_0x001c:
        r0 = "Cast";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x0044;
    L_0x0024:
        r7 = 3;
        goto L_0x0045;
    L_0x0026:
        r0 = "Composer";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x0044;
    L_0x002e:
        r7 = 1;
        goto L_0x0045;
    L_0x0030:
        r0 = "Lyricist";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x0044;
    L_0x0038:
        r7 = 2;
        goto L_0x0045;
    L_0x003a:
        r0 = "Singer";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x0044;
    L_0x0042:
        r7 = 0;
        goto L_0x0045;
    L_0x0044:
        r7 = -1;
    L_0x0045:
        r0 = 8;
        r1 = 0;
        switch(r7) {
            case 0: goto L_0x00c6;
            case 1: goto L_0x0095;
            case 2: goto L_0x0072;
            case 3: goto L_0x004e;
            default: goto L_0x004b;
        };
    L_0x004b:
        r4 = r1;
        goto L_0x00fd;
    L_0x004e:
        r7 = r3.g;
        r7 = r7.getCast();
        r4 = r7.get(r4);
        r4 = (com.gaana.models.MoreInfo.Cast) r4;
        r7 = r3.g;
        r7 = r7.getLanguage();
        r4.setLanguage(r7);
        r1 = r4.getArtwork();
        r4 = r4.getName();
        r7 = r6.favoriteItem;
        r7.setVisibility(r0);
        goto L_0x00fd;
    L_0x0072:
        r7 = r3.g;
        r7 = r7.getLyricist();
        r4 = r7.get(r4);
        r4 = (com.gaana.models.MoreInfo.Lyricist) r4;
        r7 = r3.g;
        r7 = r7.getLanguage();
        r4.setLanguage(r7);
        r1 = r4.getArtwork();
        r4 = r4.getName();
        r7 = r6.favoriteItem;
        r7.setVisibility(r0);
        goto L_0x00fd;
    L_0x0095:
        r7 = r3.g;
        r7 = r7.getComposers();
        r4 = r7.get(r4);
        r4 = (com.gaana.models.MoreInfo.Composer) r4;
        r7 = r3.g;
        r7 = r7.getLanguage();
        r4.setLanguage(r7);
        r1 = r4.getArtwork();
        r7 = r4.getName();
        r3.a(r6, r4);
        r0 = r6.itemImg;
        r0.setTag(r4);
        r0 = r6.itemImg;
        r2 = new com.fragments.MoreInfoFragment$14;
        r2.<init>(r4);
        r0.setOnClickListener(r2);
        r4 = r7;
        goto L_0x00fd;
    L_0x00c6:
        r7 = r3.g;
        r7 = r7.getSingers();
        r7 = r7.get(r4);
        r7 = (com.gaana.models.MoreInfo.Singer) r7;
        r0 = r3.g;
        r0 = r0.getLanguage();
        r7.setLanguage(r0);
        r1 = r7.getArtwork();
        r0 = r7.getName();
        r4 = java.lang.String.valueOf(r4);
        r7.setIndex(r4);
        r3.a(r6, r7);
        r4 = r6.itemImg;
        r4.setTag(r7);
        r4 = r6.itemImg;
        r2 = new com.fragments.MoreInfoFragment$13;
        r2.<init>(r7);
        r4.setOnClickListener(r2);
        r4 = r0;
    L_0x00fd:
        r7 = r6.title;
        r7.setText(r4);
        r4 = r6.itemImg;
        r4.bindImage(r1);
        r4 = r5.itemView;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.MoreInfoFragment.addListItemView(int, android.support.v7.widget.RecyclerView$ViewHolder, android.view.ViewGroup, java.lang.String):android.view.View");
    }

    private void a(MoreInfoListingItemHolder moreInfoListingItemHolder, BusinessObject businessObject) {
        boolean a = n.a().a(b(businessObject));
        moreInfoListingItemHolder.favoriteItem.setTag(businessObject);
        if (a) {
            moreInfoListingItemHolder.favoriteItem.setImageDrawable(getResources().getDrawable(R.drawable.moreinfo_favorited));
        } else {
            moreInfoListingItemHolder.favoriteItem.setImageDrawable(getResources().getDrawable(R.drawable.moreinfo_un_fav));
        }
        moreInfoListingItemHolder.favoriteItem.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MoreInfoFragment.this.b(view);
            }
        });
    }

    public void a(final boolean z) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            if (ap.a().a(this.p, null)) {
                b(z);
            } else {
                String str = "";
                if (ap.a().l()) {
                    str = this.p instanceof Track ? "tr" : "pl";
                }
                Util.b(this.mContext, str, null, new as() {
                    public void onTrialSuccess() {
                        MoreInfoFragment.this.b(z);
                        MoreInfoFragment.this.refreshDataandAds();
                        MoreInfoFragment.this.showSnackbartoOpenMyMusic();
                        ((GaanaActivity) MoreInfoFragment.this.mContext).updateSideBar();
                    }
                });
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void b(boolean z) {
        DownloadStatus e;
        Util.i(this.mContext, "Download");
        final BusinessObject businessObject = this.p;
        final boolean z2 = businessObject instanceof Track;
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        boolean b = d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
        if (z2) {
            e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
        } else {
            e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
        }
        if (e == null || e == DownloadStatus.TRIED_BUT_FAILED || e == DownloadStatus.PAUSED || e == DownloadStatus.PARTIALLY_DOWNLOADED) {
            if (Util.k(GaanaApplication.getContext()) == 0) {
                if (!d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                    ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_over_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) MoreInfoFragment.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                            ((GaanaActivity) MoreInfoFragment.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                    return;
                } else if (b) {
                    if (!Constants.V) {
                        aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_songs_queue_msg));
                        Constants.V = true;
                    }
                } else if (!Constants.W) {
                    Constants.W = true;
                    aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_cta_text), this.mContext.getString(R.string.schedule_download_msg), new OnClickListener() {
                        public void onClick(View view) {
                            if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                                PopupWindowView.getInstance(MoreInfoFragment.this.mContext, currentFragment).dismiss(true);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            PopupWindowView.getInstance(MoreInfoFragment.this.mContext, currentFragment).dismiss(true);
                            ((GaanaActivity) MoreInfoFragment.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                }
            }
            if (e == null) {
                DownloadManager.c().a(businessObject, this.mContext);
            } else {
                DownloadManager.c().c(businessObject);
            }
            a(Boolean.valueOf(false));
            this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            int resourceId = obtainStyledAttributes.getResourceId(9, -1);
            obtainStyledAttributes.recycle();
            this.l.setIcon(resourceId);
        } else if (z) {
            if (e == DownloadStatus.QUEUED || e == DownloadStatus.DOWNLOADING) {
                if (!z2 || e != DownloadStatus.DOWNLOADING) {
                    new f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(z2 ? R.string.toast_remove_queue_song : R.string.do_you_want_pause_this_album_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            if (z2) {
                                DownloadManager.c().d(businessObject.getBusinessObjId());
                                MoreInfoFragment.this.a(Boolean.valueOf(false));
                            } else {
                                DownloadManager.c().r(Integer.parseInt(MoreInfoFragment.this.p.getBusinessObjId()));
                                DownloadManager.c().d();
                            }
                            new int[1][0] = z2 ? R.attr.download_button_paused : R.attr.button_resume;
                            TypedArray obtainStyledAttributes = MoreInfoFragment.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                            Drawable drawable = ContextCompat.getDrawable(MoreInfoFragment.this.mContext, obtainStyledAttributes.getResourceId(z2 ? 13 : 10, -1));
                            obtainStyledAttributes.recycle();
                            MoreInfoFragment.this.l.setIcon(drawable);
                        }
                    }, false);
                }
            } else if (e == DownloadStatus.DOWNLOADED) {
                new f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(z2 ? R.string.toast_delete_downloaded_song : R.string.do_you_want_to_remove_this_album_from_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        Drawable drawable;
                        if (z2) {
                            DownloadManager.c().d(businessObject.getBusinessObjId());
                        } else {
                            DownloadManager.c().p(Integer.parseInt(MoreInfoFragment.this.p.getBusinessObjId()));
                            DownloadManager.c().d(Integer.parseInt(MoreInfoFragment.this.p.getBusinessObjId()));
                            DownloadManager.c().d();
                        }
                        MoreInfoFragment.this.a(Boolean.valueOf(false));
                        int i = z2 ? R.attr.download_button_paused : R.attr.download_all;
                        TypedArray obtainStyledAttributes = MoreInfoFragment.this.mContext.obtainStyledAttributes(new int[]{i});
                        if (z2) {
                            obtainStyledAttributes = MoreInfoFragment.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                            drawable = ContextCompat.getDrawable(MoreInfoFragment.this.mContext, obtainStyledAttributes.getResourceId(13, -1));
                        } else {
                            drawable = obtainStyledAttributes.getDrawable(0);
                        }
                        obtainStyledAttributes.recycle();
                        MoreInfoFragment.this.l.setIcon(drawable);
                    }
                }, false);
            }
        }
    }

    private void a(Boolean bool) {
        if (this.p != null) {
            ((BaseActivity) this.mContext).refreshListView();
            a(this.l, this.p);
        }
    }

    public void e() {
        if (this.p != null) {
            a(this.l, this.p);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(MenuItem menuItem, BusinessObject businessObject) {
        if (!this.p.isLocalMedia()) {
            DownloadStatus downloadStatus = null;
            if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                downloadStatus = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
            } else if (businessObject instanceof Track) {
                downloadStatus = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            }
            a(downloadStatus, businessObject instanceof Track);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(DownloadStatus downloadStatus, boolean z) {
        if (this.l != null) {
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (downloadStatus == DownloadStatus.DOWNLOADING) {
                if (DownloadManager.c().w()) {
                    new int[1][0] = R.attr.button_downloding;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(8, -1));
                    obtainStyledAttributes.recycle();
                    this.l.setIcon(drawable);
                } else {
                    new int[1][0] = R.attr.button_inqueue;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(9, -1));
                    obtainStyledAttributes.recycle();
                    this.l.setIcon(drawable);
                }
            } else if (downloadStatus == DownloadStatus.DOWNLOADED) {
                new int[1][0] = R.attr.button_downloded;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(7, -1));
                obtainStyledAttributes.recycle();
                this.l.setIcon(drawable);
            } else if (downloadStatus == DownloadStatus.PAUSED || downloadStatus == DownloadStatus.PARTIALLY_DOWNLOADED) {
                new int[1][0] = z ? R.attr.download_button_paused : R.attr.button_resume;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(z ? 13 : 10, -1));
                obtainStyledAttributes.recycle();
                this.l.setIcon(drawable);
            } else if (downloadStatus == DownloadStatus.QUEUED) {
                new int[1][0] = R.attr.button_inqueue;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(9, -1));
                obtainStyledAttributes.recycle();
                this.l.setIcon(drawable);
            } else if (downloadStatus == DownloadStatus.TRIED_BUT_FAILED) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(91, -1));
                obtainStyledAttributes.recycle();
                this.l.setIcon(drawable);
            } else {
                new int[1][0] = R.attr.menu_download_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(29, -1));
                obtainStyledAttributes.recycle();
                this.l.setIcon(drawable);
            }
        }
    }

    private void a(View view) {
        BusinessObject businessObject = (BusinessObject) view.getTag();
        com.managers.af.a(this.mContext, (BaseGaanaFragment) this).a(businessObject.getBusinessObjId(), businessObject.getName(), b(businessObject));
    }

    private void b(View view) {
        BusinessObject b = b((BusinessObject) view.getTag());
        com.managers.af a = com.managers.af.a(this.mContext, (BaseGaanaFragment) this);
        a.a("Song Info");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Artist ");
        stringBuilder.append(b.getBusinessObjId());
        a.b(stringBuilder.toString());
        a.a((int) R.id.favoriteMenu, b);
        ImageView imageView = (ImageView) view;
        if (n.a().a(b)) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.moreinfo_favorited));
        } else {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.moreinfo_un_fav));
        }
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.mContext != null) {
            com.views.i slidingPanelLayout = ((GaanaActivity) this.mContext).getSlidingPanelLayout();
            if (slidingPanelLayout == null || slidingPanelLayout.a() != 1) {
                i();
            }
            d();
        }
    }

    private void i() {
        if (this.p != null) {
            if (this.c != null) {
                this.c.notifyDataSetChanged();
            }
            if (!this.p.isLocalMedia()) {
                e();
            }
            d();
        }
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        i();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        i();
    }

    private Artist b(BusinessObject businessObject) {
        Artist artist = new Artist();
        artist.setName(businessObject.getName());
        artist.setLanguage(businessObject.getLanguage());
        artist.setBusinessObjId(businessObject.getBusinessObjId());
        artist.setBusinessObjType(BusinessObjectType.Artists);
        return artist;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        new UserMessage().setEmptyMsg(this.mContext.getResources().getString(R.string.NO_DATA));
        return new MoreInfoListingItemHolder(LayoutInflater.from(this.mContext).inflate(R.layout.moreinfo_item_view, viewGroup, false));
    }

    public void onDestroyView() {
        if (this.b.getParent() != null) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
    }
}
