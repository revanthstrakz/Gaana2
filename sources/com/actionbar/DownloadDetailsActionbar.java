package com.actionbar;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.b.i;
import com.constants.Constants.SortOrder;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.fragments.ListingFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.managers.DownloadManager;
import com.managers.al;
import com.managers.ap;
import com.managers.u;
import com.utilities.Util;

public class DownloadDetailsActionbar extends BaseContextualActionBar implements OnClickListener {
    private Context a;
    private LayoutInflater b;
    private PopupMenu c = null;
    private boolean d = false;
    private String e = "downloads";
    private a f;
    private Toolbar g;
    private SortOrder h = SortOrder.TrackName;
    private int i = -1;
    private boolean j = true;
    private int k = -1;
    private boolean l = false;
    private boolean m = false;

    public interface a {
        void a();

        void a(SortOrder sortOrder, int i);

        boolean a(int i);

        void b();
    }

    public DownloadDetailsActionbar(Context context, boolean z, String str) {
        super(context);
        a(context, z, str);
    }

    public DownloadDetailsActionbar(Context context, boolean z) {
        super(context);
        this.e = context.getString(R.string.mymusic_downloads);
        a(context, z, this.e);
    }

    public void setCustomMenuId(int i) {
        this.k = i;
    }

    public void showContextMenu(boolean z) {
        Menu menu = this.g != null ? this.g.getMenu() : null;
        if (z) {
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_search, false);
            }
            findViewById(R.id.action_download_details).setVisibility(8);
        } else {
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_search, true);
            }
            findViewById(R.id.action_download_details).setVisibility(0);
        }
        super.showContextMenu(z);
    }

    public void setToolBar(Toolbar toolbar) {
        this.g = toolbar;
    }

    public void hideContextMenu(boolean z) {
        Menu menu = this.g != null ? this.g.getMenu() : null;
        if (al.a) {
            super.hideContextMenu(z);
            if (z) {
                if (menu != null) {
                    menu.setGroupVisible(R.id.cast_menu_search, true);
                }
                findViewById(R.id.action_download_details).setVisibility(0);
                return;
            }
            if (menu != null) {
                menu.setGroupVisible(R.id.cast_menu_search, false);
            }
            findViewById(R.id.action_download_details).setVisibility(8);
        }
    }

    public void a(boolean z) {
        this.l = z;
        findViewById(R.id.menu_sort_option).setVisibility(z ? 0 : 8);
    }

    public void b(boolean z) {
        this.j = z;
    }

    public void setPagerPosition(int i) {
        this.i = i;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.h = sortOrder;
    }

    private void a(Context context, boolean z, String str) {
        this.e = str;
        this.a = context;
        this.b = LayoutInflater.from(context);
        this.b.inflate(R.layout.action_download_details, this);
        findViewById(R.id.menu_icon).setOnClickListener(this);
        findViewById(R.id.menu_option).setOnClickListener(this);
        findViewById(R.id.menu_add_playlist).setOnClickListener(this);
        ((TextView) findViewById(R.id.action_title)).setText(str);
        ((TextView) findViewById(R.id.action_title)).setTypeface(i.a(this.a.getAssets(), "fonts/Roboto-Medium.ttf"));
        int i = 8;
        if (z) {
            findViewById(R.id.menu_option).setVisibility(0);
        } else {
            findViewById(R.id.menu_option).setVisibility(8);
        }
        if (str.equalsIgnoreCase("my playlist")) {
            str = this.a.getString(R.string.playlists);
        }
        if (str.equalsIgnoreCase("Music on my phone")) {
            this.a.getString(R.string.local_music);
        }
        View findViewById = findViewById(R.id.menu_sort_option);
        if (this.l) {
            i = 0;
        }
        findViewById.setVisibility(i);
        findViewById(R.id.menu_sort_option).setOnClickListener(this);
    }

    public void c(boolean z) {
        this.m = z;
        findViewById(R.id.menu_add_playlist).setVisibility(z ? 8 : 0);
    }

    public DownloadDetailsActionbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setDownloadActionbarClickListener(a aVar) {
        this.f = aVar;
        if ((this.f instanceof ListingFragment) && (((ListingFragment) this.f).e() instanceof MyMusicFragment)) {
            this.h = SortOrder.Default;
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.menu_add_playlist) {
            Util.A(this.a);
        } else if (id != R.id.menu_icon) {
            if (id == R.id.menu_option) {
                this.c = new PopupMenu(this.a, findViewById(R.id.menu_option), GravityCompat.END);
                this.c.inflate(R.menu.downloads_menu);
                if (this.a instanceof GaanaActivity) {
                    ((GaanaActivity) this.a).closeDrawers();
                }
                final DownloadManager c = DownloadManager.c();
                this.c.getMenu().findItem(R.id.queued).setChecked(c.E());
                this.c.getMenu().findItem(R.id.smart_downloads).setChecked(c.F());
                this.c.getMenu().findItem(R.id.downloaded).setChecked(c.G());
                this.c.getMenu().findItem(R.id.gaana_mini).setChecked(c.I());
                this.c.getMenu().findItem(R.id.queued).setVisible(this.j);
                this.c.getMenu().findItem(R.id.smart_downloads).setVisible(this.j);
                this.c.getMenu().findItem(R.id.downloaded).setVisible(this.j);
                if (ap.a().k()) {
                    this.c.getMenu().findItem(R.id.expired_downloads).setVisible(this.j);
                }
                this.c.getMenu().findItem(R.id.expired_downloads).setChecked(c.H());
                if (ap.a().m()) {
                    this.c.getMenu().findItem(R.id.gaana_mini).setVisible(this.j);
                }
                this.c.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.cancelActionBar) {
                            DownloadDetailsActionbar.this.f.a();
                            DownloadDetailsActionbar.this.d(true);
                        } else if (itemId == R.id.deleteActionBar) {
                            DownloadDetailsActionbar.this.f.a(R.id.deleteActionBar);
                        } else if (itemId != R.id.editActionbar) {
                            boolean isChecked = menuItem.isChecked();
                            if (menuItem.getItemId() == R.id.downloaded) {
                                c.e(isChecked ^ 1);
                            }
                            if (menuItem.getItemId() == R.id.queued) {
                                c.g(isChecked ^ 1);
                            }
                            if (menuItem.getItemId() == R.id.smart_downloads) {
                                c.h(isChecked ^ 1);
                            }
                            if (menuItem.getItemId() == R.id.gaana_mini) {
                                c.f(isChecked ^ 1);
                            }
                            if (menuItem.getItemId() == R.id.expired_downloads) {
                                c.i(isChecked ^ 1);
                            }
                            if (DownloadDetailsActionbar.this.f != null) {
                                DownloadDetailsActionbar.this.f.b();
                            }
                        } else {
                            DownloadDetailsActionbar.this.d(false);
                            DownloadDetailsActionbar.this.f.a(R.id.editActionbar);
                        }
                        return true;
                    }
                });
                d(com.managers.i.a().f() ^ 1);
                this.c.show();
            } else if (id == R.id.menu_sort_option) {
                PopupMenu popupMenu = new PopupMenu(this.a, findViewById(R.id.menu_sort_option), GravityCompat.END);
                if (this.f != null && this.k != -1) {
                    popupMenu.inflate(this.k);
                } else if (this.f instanceof DownloadDetailsFragment) {
                    popupMenu.inflate(R.menu.filter_menu_download_items);
                    u.a().a("Filter", "Filter", "Download");
                } else if (this.f instanceof FavoritesFragment) {
                    if (this.i != 0) {
                        popupMenu.inflate(R.menu.filter_menu_favorites_items);
                    } else {
                        popupMenu.inflate(R.menu.filter_menu_favorites_items);
                    }
                    u.a().a("Filter", "Filter", "Favorite");
                } else if (this.f instanceof MyMusicItemFragment) {
                    if (this.i == 0) {
                        popupMenu.inflate(R.menu.filter_menu_favorites_items);
                    } else {
                        popupMenu.inflate(R.menu.filter_menu_download_items);
                    }
                } else if (this.f instanceof ListingFragment) {
                    popupMenu.inflate(R.menu.filter_menu_listing_items);
                    popupMenu.getMenu().setGroupVisible(R.id.menu_filter_group, false);
                } else {
                    return;
                }
                int color = getResources().getColor(R.color.f17gaana.red);
                SpannableString spannableString;
                if (this.h == SortOrder.TrackName) {
                    spannableString = new SpannableString(popupMenu.getMenu().findItem(R.id.menu_filter_by_name).getTitle());
                    spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), 0);
                    popupMenu.getMenu().findItem(R.id.menu_filter_by_name).setTitle(spannableString);
                } else if (this.h == SortOrder.DownloadTime) {
                    spannableString = new SpannableString(popupMenu.getMenu().findItem(R.id.menu_filter_by_download_time).getTitle());
                    spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), 0);
                    popupMenu.getMenu().findItem(R.id.menu_filter_by_download_time).setTitle(spannableString);
                } else if (this.h == SortOrder.Popularity) {
                    spannableString = new SpannableString(popupMenu.getMenu().findItem(R.id.menu_filter_by_popularity).getTitle());
                    spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), 0);
                    popupMenu.getMenu().findItem(R.id.menu_filter_by_popularity).setTitle(spannableString);
                } else if (this.h == SortOrder.Default) {
                    MenuItem findItem = popupMenu.getMenu().findItem(R.id.menu_filter_default);
                    if (findItem == null) {
                        findItem = popupMenu.getMenu().findItem(R.id.menu_filter_by_name);
                    }
                    SpannableString spannableString2 = new SpannableString(findItem.getTitle());
                    spannableString2.setSpan(new ForegroundColorSpan(color), 0, spannableString2.length(), 0);
                    findItem.setTitle(spannableString2);
                }
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_filter_by_download_time /*2131297704*/:
                                DownloadDetailsActionbar.this.h = SortOrder.DownloadTime;
                                break;
                            case R.id.menu_filter_by_name /*2131297705*/:
                                DownloadDetailsActionbar.this.h = SortOrder.TrackName;
                                break;
                            case R.id.menu_filter_by_popularity /*2131297706*/:
                                DownloadDetailsActionbar.this.h = SortOrder.Popularity;
                                break;
                            case R.id.menu_filter_default /*2131297707*/:
                                DownloadDetailsActionbar.this.h = SortOrder.Default;
                                break;
                        }
                        DownloadDetailsActionbar.this.f.a(DownloadDetailsActionbar.this.h, DownloadDetailsActionbar.this.i);
                        return true;
                    }
                });
                popupMenu.show();
            }
        } else if (this.d) {
            a();
        } else {
            ((GaanaActivity) this.a).homeIconClick();
        }
    }

    public void d(boolean z) {
        if (this.c != null) {
            if (z) {
                this.c.getMenu().findItem(R.id.editActionbar).setVisible(true);
                this.c.getMenu().findItem(R.id.deleteActionBar).setVisible(false);
                this.c.getMenu().findItem(R.id.cancelActionBar).setVisible(false);
            } else {
                this.c.getMenu().findItem(R.id.editActionbar).setVisible(false);
                this.c.getMenu().findItem(R.id.deleteActionBar).setVisible(true);
                this.c.getMenu().findItem(R.id.cancelActionBar).setVisible(true);
            }
            this.c.getMenu().findItem(R.id.queued).setVisible(this.j);
            this.c.getMenu().findItem(R.id.smart_downloads).setVisible(this.j);
            this.c.getMenu().findItem(R.id.downloaded).setVisible(this.j);
            if (ap.a().m()) {
                this.c.getMenu().findItem(R.id.gaana_mini).setVisible(this.j);
            }
            if (ap.a().k()) {
                this.c.getMenu().findItem(R.id.expired_downloads).setVisible(this.j);
            }
        }
    }

    private void a() {
        this.d = false;
        findViewById(R.id.menu_icon).setVisibility(0);
        findViewById(R.id.action_title).setVisibility(0);
        BaseGaanaFragment currentFragment = ((GaanaActivity) this.a).getCurrentFragment();
        if (currentFragment instanceof MyMusicItemFragment) {
            ((MyMusicItemFragment) currentFragment).d();
        }
    }
}
