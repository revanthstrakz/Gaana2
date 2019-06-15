package com.actionbar;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.b.i;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.utilities.d;

public class GenericSearchActionBar extends LinearLayout implements OnClickListener {
    private Context a;
    private LayoutInflater b;
    private a c = null;
    private SearchView d;
    private boolean e = false;
    private Drawable f;
    private Drawable g;
    private Drawable h;
    private Drawable i;
    private int[] j = new int[]{R.attr.actionbar_cancel, R.attr.actionbar_search, R.attr.actionbar_search, R.attr.actionbar_back, R.attr.edit_text, R.attr.list_selector, R.attr.first_line_color};
    private TypedArray k;

    public interface a {
        void a();

        void b();
    }

    public GenericSearchActionBar(Context context, String str) {
        super(context);
        a(context, str, null);
    }

    public void setTitle(String str) {
        TextView textView = (TextView) findViewById(R.id.actionbar_title);
        textView.setTypeface(i.a(this.a.getAssets(), "fonts/Roboto-Medium.ttf"));
        textView.setText(str);
    }

    private void a(Context context, String str, a aVar) {
        this.k = context.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.f = ContextCompat.getDrawable(getContext(), this.k.getResourceId(1, -1));
        this.g = ContextCompat.getDrawable(getContext(), this.k.getResourceId(3, -1));
        this.h = ContextCompat.getDrawable(getContext(), this.k.getResourceId(3, -1));
        this.i = ContextCompat.getDrawable(getContext(), this.k.getResourceId(0, -1));
        this.a = context;
        this.c = aVar;
        this.b = LayoutInflater.from(this.a);
        setLayoutParams(new LayoutParams(-1, -2));
        this.b.inflate(R.layout.action_backgrid, this);
        findViewById(R.id.menu_icon).setOnClickListener(this);
        if (this.c != null) {
            findViewById(R.id.accept_icon).setVisibility(0);
            findViewById(R.id.accept_icon).setOnClickListener(this);
        } else {
            findViewById(R.id.accept_icon).setVisibility(4);
        }
        setTitle(str);
    }

    public void a() {
        this.d = (SearchView) findViewById(R.id.searchview_actionbar);
        this.d.setVisibility(0);
        this.d.setFocusable(false);
        this.d.clearFocus();
        final ImageView imageView = (ImageView) this.d.findViewById(R.id.search_close_btn);
        imageView.setImageDrawable(this.f);
        ((ImageView) this.d.findViewById(R.id.search_mag_icon)).setImageDrawable(this.g);
        ((ImageView) this.d.findViewById(R.id.search_button)).setImageDrawable(this.g);
        ((TextView) this.d.findViewById(R.id.search_src_text)).setTextColor(this.k.getColor(6, -1));
        final SearchAutoComplete searchAutoComplete = (SearchAutoComplete) this.d.findViewById(R.id.search_src_text);
        this.d.setSearchableInfo(((SearchManager) this.a.getSystemService("search")).getSearchableInfo(((GaanaActivity) this.a).getComponentName()));
        this.d.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                ((GaanaActivity) GenericSearchActionBar.this.a).performSearch(str.trim());
                ((InputMethodManager) GenericSearchActionBar.this.a.getSystemService("input_method")).hideSoftInputFromWindow(GenericSearchActionBar.this.d.findViewById(R.id.search_src_text).getWindowToken(), 0);
                return true;
            }

            public boolean onQueryTextChange(String str) {
                if (imageView != null) {
                    if (TextUtils.isEmpty(str)) {
                        imageView.setVisibility(8);
                    } else {
                        imageView.setVisibility(0);
                    }
                }
                ((GaanaActivity) GenericSearchActionBar.this.a).performSearch(str.trim());
                return false;
            }
        });
        this.d.setOnSearchClickListener(new OnClickListener() {
            public void onClick(View view) {
                GenericSearchActionBar.this.e = true;
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                if (GenericSearchActionBar.this.a instanceof GaanaActivity) {
                    ((GaanaActivity) GenericSearchActionBar.this.a).closeDrawers();
                }
                if (d.g()) {
                    GenericSearchActionBar.this.d.setBackground(GenericSearchActionBar.this.k.getDrawable(4));
                }
                ((ImageView) GenericSearchActionBar.this.findViewById(R.id.menu_icon)).setImageDrawable(GenericSearchActionBar.this.i);
                GenericSearchActionBar.this.findViewById(R.id.actionbar_title).setVisibility(8);
            }
        });
        this.d.setOnCloseListener(new OnCloseListener() {
            public boolean onClose() {
                searchAutoComplete.setText("");
                GenericSearchActionBar.this.findViewById(R.id.menu_icon).setVisibility(0);
                GenericSearchActionBar.this.findViewById(R.id.actionbar_title).setVisibility(0);
                GenericSearchActionBar.this.e = false;
                GenericSearchActionBar.this.setBackgroundColor(GenericSearchActionBar.this.getResources().getColor(17170445));
                return false;
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.accept_icon) {
            if (id == R.id.menu_icon) {
                if (this.e) {
                    c();
                    return;
                }
                if (this.c != null) {
                    this.c.a();
                }
                if (this.a instanceof WebViewActivity) {
                    if (this.c == null) {
                        ((Activity) this.a).finish();
                    }
                } else if (this.a instanceof GaanaActivity) {
                    ((GaanaActivity) this.a).homeIconClick();
                } else {
                    ((Activity) this.a).finish();
                }
            }
        } else if (this.c != null) {
            this.c.b();
        }
    }

    private void c() {
        this.e = false;
        this.d.onActionViewCollapsed();
        findViewById(R.id.menu_icon).setVisibility(0);
        findViewById(R.id.actionbar_title).setVisibility(0);
        if (d.g()) {
            this.d.setBackground(this.k.getDrawable(5));
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.setVisibility(8);
        }
    }
}
