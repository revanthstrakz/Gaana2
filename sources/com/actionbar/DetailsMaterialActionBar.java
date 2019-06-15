package com.actionbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.a.a;
import com.android.volley.Request.Priority;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.c;
import com.dynamicview.DynamicOccasionFragment;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.MoreInfoFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.fragments.SearchEnchancedFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.Albums;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.view.item.PopupWindowView;
import com.i.i;
import com.managers.URLManager;
import com.managers.an;
import com.managers.n;
import com.managers.u;
import com.services.l.af;

public class DetailsMaterialActionBar extends BaseContextualActionBar implements OnMenuItemClickListener, OnClickListener {
    PopupWindowView a;
    private Context b;
    private LayoutInflater c;
    private BusinessObject d;
    private BaseGaanaFragment e;
    private Toolbar f;

    public DetailsMaterialActionBar(Context context) {
        this(context, null);
    }

    public DetailsMaterialActionBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DetailsMaterialActionBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        this.c = LayoutInflater.from(context);
        this.c.inflate(R.layout.action_details, this);
        findViewById(R.id.menu_icon).setOnClickListener(this);
    }

    public void setParams(BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject) {
        this.e = baseGaanaFragment;
        this.d = businessObject;
        super.setParams(baseGaanaFragment, businessObject);
        a();
    }

    public void setAlbumInfo() {
        a(this.d.getBusinessObjId());
    }

    private void a(String str) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c.t);
        stringBuilder.append(str);
        uRLManager.a(stringBuilder.toString());
        uRLManager.c(0);
        uRLManager.a(Albums.class);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Priority.IMMEDIATE);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                Albums albums = (Albums) obj;
                ((Album) DetailsMaterialActionBar.this.d).setPrimaryartist(((Album) albums.getArrListBusinessObj().get(0)).getPrimaryartist());
                ((Album) DetailsMaterialActionBar.this.d).setArtistArray(((Album) albums.getArrListBusinessObj().get(0)).getArtist());
                if (DetailsMaterialActionBar.this.a != null) {
                    DetailsMaterialActionBar.this.a.getArtistItemListener().ChangeArtistItemStatus();
                }
            }
        }, uRLManager);
    }

    private void a() {
        TextView textView = (TextView) findViewById(R.id.title);
        textView.setTypeface(com.b.i.a(this.b.getAssets(), "fonts/Roboto-Medium.ttf"));
        if (!(textView == null || this.d == null)) {
            textView.setText(this.d.getName());
            textView.setVisibility(0);
        }
        findViewById(R.id.menu_icon).setOnClickListener(this);
        if ((this.e instanceof ArtistDetailsMaterialListing) || (this.e instanceof RadioDetailsMaterialListing) || (this.e instanceof SongParallexListingFragment)) {
            findViewById(R.id.menu_icon).setVisibility(0);
        }
    }

    public TextView getTitleTextView() {
        return (TextView) findViewById(R.id.title);
    }

    public void setToolbar(Toolbar toolbar) {
        this.f = toolbar;
        toolbar.setOnMenuItemClickListener(this);
        Menu menu = toolbar.getMenu();
        if (this.d != null) {
            if ((this.d instanceof Artist) && this.d.isLocalMedia()) {
                menu.findItem(R.id.menu_option).setVisible(false);
            }
            if ((this.d instanceof Artist) || (this.d instanceof Radio) || this.d.isLocalMedia()) {
                menu.findItem(R.id.menu_download).setVisible(false);
            }
            final MenuItem findItem = menu.findItem(R.id.menu_favourite);
            if (findItem != null) {
                if ((this.d instanceof Playlist) && ((Playlist) this.d).getAutomated() != null && ((Playlist) this.d).getAutomated().equalsIgnoreCase("1")) {
                    findItem.setVisible(false);
                    return;
                }
                ImageView imageView = (ImageView) findItem.getActionView();
                if (imageView != null) {
                    imageView.setPadding(this.b.getResources().getDimensionPixelSize(R.dimen.dp12), 0, this.b.getResources().getDimensionPixelSize(R.dimen.dp12), 0);
                    imageView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            DetailsMaterialActionBar.this.a(findItem.getItemId());
                            if (DetailsMaterialActionBar.this.d != null && n.a().a(DetailsMaterialActionBar.this.d)) {
                                Animation loadAnimation = AnimationUtils.loadAnimation(DetailsMaterialActionBar.this.b, R.anim.favorite_tap_animation);
                                loadAnimation.setInterpolator(new a(0.2d, 20.0d));
                                view.startAnimation(loadAnimation);
                            }
                        }
                    });
                }
            }
        }
    }

    public void showContextMenu(boolean z) {
        Menu menu = this.f != null ? this.f.getMenu() : null;
        if (z) {
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_detail, false);
            }
            findViewById(R.id.menu_icon).setVisibility(8);
            findViewById(R.id.action_details).setVisibility(8);
        } else {
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_detail, true);
                if (this.e instanceof SongParallexListingFragment) {
                    menu.findItem(R.id.menu_download).setVisible(false);
                    menu.findItem(R.id.menu_option).setVisible(false);
                }
                if (this.d != null && this.d.isLocalMedia()) {
                    menu.findItem(R.id.menu_favourite).setVisible(false);
                    menu.findItem(R.id.menu_download).setVisible(false);
                } else if ((this.d instanceof Playlist) && ((Playlist) this.d).isUserCreatedPlaylist()) {
                    menu.findItem(R.id.menu_favourite).setVisible(false);
                }
            }
            findViewById(R.id.menu_icon).setVisibility(0);
            findViewById(R.id.action_details).setVisibility(0);
        }
        super.showContextMenu(z);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.menu_icon) {
            ((GaanaActivity) this.b).homeIconClick();
        }
    }

    public void a(int i) {
        switch (i) {
            case R.id.media_route_menu_item /*2131297687*/:
                u.a().b("Chromecast: Coach-mark", "Clicked on Chromecast icon");
                return;
            case R.id.menu_download /*2131297701*/:
                com.managers.af.a(this.b, this.e).a((int) R.id.downloadMenu, this.d);
                return;
            case R.id.menu_favourite /*2131297702*/:
                com.managers.af a = com.managers.af.a(this.b, this.e);
                if (((GaanaActivity) this.b).getCurrentFragment() instanceof AlbumDetailsMaterialListing) {
                    a.a("Playlist Detail");
                    a.b(this.d.getBusinessObjId());
                    a.a((int) R.id.favoriteMenu, this.d);
                    ((AlbumDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment()).b();
                    return;
                } else if (((GaanaActivity) this.b).getCurrentFragment() instanceof GaanaSpecialDetailsMaterialListing) {
                    a.a("Gaana Special");
                    a.b(this.d.getBusinessObjId());
                    a.a((int) R.id.favoriteMenu, this.d);
                    ((GaanaSpecialDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment()).b();
                    return;
                } else if (((GaanaActivity) this.b).getCurrentFragment() instanceof RadioDetailsMaterialListing) {
                    a.a("Radio Detail");
                    a.b(this.d.getBusinessObjId());
                    a.a((int) R.id.favoriteMenu, this.d);
                    ((RadioDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment()).c();
                    return;
                } else if (((GaanaActivity) this.b).getCurrentFragment() instanceof ArtistDetailsMaterialListing) {
                    a.a("Artist Detail");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Artist");
                    stringBuilder.append(this.d.getBusinessObjId());
                    a.b(stringBuilder.toString());
                    a.a((int) R.id.favoriteMenu, this.d);
                    ((ArtistDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment()).b();
                    return;
                } else if (((GaanaActivity) this.b).getCurrentFragment() instanceof MoreInfoFragment) {
                    a.a((int) R.id.favoriteMenu, this.d);
                    ((MoreInfoFragment) ((GaanaActivity) this.b).getCurrentFragment()).d();
                    return;
                } else {
                    return;
                }
            case R.id.menu_icon /*2131297709*/:
                ((GaanaActivity) this.b).homeIconClick();
                return;
            case R.id.menu_option /*2131297716*/:
                if (this.e instanceof AlbumDetailsMaterialListing) {
                    ((AlbumDetailsMaterialListing) this.e).e();
                }
                PopupWindowView instance = PopupWindowView.getInstance(this.b, this.e);
                this.a = instance;
                if (((GaanaActivity) this.b).getCurrentFragment() instanceof AlbumDetailsMaterialListing) {
                    instance.contextPopupWindow(this.d, false, (AlbumDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment(), false);
                    return;
                } else if (((GaanaActivity) this.b).getCurrentFragment() instanceof ArtistDetailsMaterialListing) {
                    instance.contextPopupWindow(this.d, false, (ArtistDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment(), false);
                    return;
                } else if (((GaanaActivity) this.b).getCurrentFragment() instanceof GaanaSpecialDetailsMaterialListing) {
                    instance.contextPopupWindow(this.d, false, (GaanaSpecialDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment(), false);
                    return;
                } else if (((GaanaActivity) this.b).getCurrentFragment() instanceof RadioDetailsMaterialListing) {
                    instance.contextPopupWindow(this.d, false, (RadioDetailsMaterialListing) ((GaanaActivity) this.b).getCurrentFragment(), false);
                    return;
                } else {
                    instance.contextPopupWindow(this.d, false, false);
                    return;
                }
            case R.id.menu_search /*2131297720*/:
            case R.id.searchview_actionbar /*2131298327*/:
                ((BaseActivity) this.b).sendGAEvent(((BaseActivity) this.b).currentScreen, "Action Bar Click", "Search");
                if (this.e instanceof DynamicOccasionFragment) {
                    an.a().a("click", "ac", "", "Occasion Detail", "", "search", "", "");
                } else {
                    an.a().a("click", "ac", "", "", "", "search", "", "");
                }
                BaseGaanaFragment searchEnchancedFragment = new SearchEnchancedFragment();
                searchEnchancedFragment.setArguments(new Bundle());
                ((GaanaActivity) this.b).clearStackForSearch();
                ((GaanaActivity) this.b).displayFragment(searchEnchancedFragment);
                return;
            default:
                return;
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        a(menuItem.getItemId());
        return false;
    }
}
