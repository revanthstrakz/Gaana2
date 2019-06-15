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
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.ACTION_DETAILS;
import com.managers.GaanaSearchManager.ACTION_TYPE;
import com.managers.an;
import com.managers.u;
import com.services.d;
import com.utilities.Util;

public class SearchActionBar extends LinearLayout implements OnClickListener {
    int[] a = new int[]{R.attr.actionbar_back, R.attr.search_cross, R.attr.first_line_color, R.attr.first_line_color_50, R.attr.edit_text};
    public EditText b;
    private a c = null;
    private Context d;
    private LayoutInflater e;
    private SearchView f;
    private SearchAutoComplete g;
    private ImageView h = null;
    private ImageView i = null;
    private com.managers.GaanaSearchManager.b j;
    private Drawable k;
    private View l;
    private View m;
    private b n;
    private ImageView o;

    public interface a {
        void a();
    }

    public interface b {
        void a();
    }

    public SearchActionBar(Context context) {
        super(context);
        a(context);
    }

    public SearchActionBar(Context context, a aVar) {
        super(context);
        a(context);
        this.c = aVar;
    }

    public SearchActionBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void setSearchInterface(com.managers.GaanaSearchManager.b bVar) {
        this.j = bVar;
    }

    public void setOnSearchFocused(b bVar) {
        this.n = bVar;
    }

    private void a(Context context) {
        this.d = context;
        this.k = ContextCompat.getDrawable(getContext(), this.d.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(83, -1));
        this.e = LayoutInflater.from(this.d);
        TypedArray obtainStyledAttributes = this.d.obtainStyledAttributes(this.a);
        setLayoutParams(new LayoutParams(-1, -2));
        this.e.inflate(R.layout.actionbar_search, this);
        if (this.d instanceof GaanaActivity) {
            ((GaanaActivity) this.d).closeDrawers();
        }
        this.o = (ImageView) findViewById(R.id.menu_icon_back);
        this.o.setOnClickListener(this);
        this.i = (ImageView) findViewById(R.id.menu_icon_voice);
        this.m = findViewById(R.id.menu_icon_voice_container);
        this.f = (SearchView) findViewById(R.id.searchview_actionbar);
        this.l = findViewById(R.id.menu_icon);
        this.f.setIconified(false);
        this.f.clearFocus();
        EditText editText = (EditText) this.f.findViewById(R.id.search_src_text);
        editText.setHintTextColor(obtainStyledAttributes.getColor(3, -1));
        editText.setTextColor(obtainStyledAttributes.getColor(2, -1));
        editText.setFilters(new InputFilter[]{new LengthFilter(255)});
        obtainStyledAttributes.recycle();
        e();
        d();
    }

    public void setSearchInnerActionBarVisibility(boolean z) {
        if (z) {
            this.f.setFocusable(true);
            ((RelativeLayout) findViewById(R.id.innerActionBar)).setVisibility(0);
            Util.b(this.d, this.f);
            return;
        }
        this.f.setFocusable(false);
        ((RelativeLayout) findViewById(R.id.innerActionBar)).setVisibility(8);
        Util.a(this.d, this.f);
    }

    public SearchView getSearchView() {
        return this.f;
    }

    private void d() {
        final ImageView imageView = (ImageView) this.f.findViewById(R.id.search_close_btn);
        this.b = (EditText) this.f.findViewById(R.id.search_src_text);
        this.b.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    if (SearchActionBar.this.n != null) {
                        SearchActionBar.this.n.a();
                    }
                    imageView.setImageDrawable(SearchActionBar.this.k);
                    if (SearchActionBar.this.b.getText().toString().isEmpty()) {
                        imageView.setImageDrawable(null);
                        ((GaanaActivity) SearchActionBar.this.d).setCrossButtonVisibility(false);
                    } else {
                        imageView.setVisibility(0);
                        ((GaanaActivity) SearchActionBar.this.d).setCrossButtonVisibility(true);
                        ((GaanaActivity) SearchActionBar.this.d).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
                    }
                } else if (SearchActionBar.this.b.getText().toString().isEmpty()) {
                    imageView.setImageDrawable(null);
                    ((GaanaActivity) SearchActionBar.this.d).setCrossButtonVisibility(false);
                }
                if (Constants.au) {
                    GaanaSearchManager.a().a(ACTION_TYPE.VS_EXIT.ordinal(), 0, 0, "", 0, "");
                    Constants.au = false;
                }
            }
        });
    }

    private void e() {
        this.g = (SearchAutoComplete) this.f.findViewById(R.id.search_src_text);
        this.h = (ImageView) this.f.findViewById(R.id.search_close_btn);
        ImageView imageView = (ImageView) this.f.findViewById(R.id.search_voice_btn);
        imageView.setImageDrawable(null);
        imageView.setEnabled(false);
        this.h.setImageDrawable(this.k);
        this.h.setImageDrawable(null);
        this.f.findViewById(R.id.search_plate).setBackgroundColor(0);
        this.f.findViewById(R.id.submit_area).setBackgroundColor(0);
        this.m.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchActionBar.this.b();
                GaanaSearchManager.a().a(ACTION_TYPE.SEARCH_BEGIN.ordinal(), ACTION_DETAILS.VOICE_SEARCH_TAP.ordinal(), 0, "", 0, "");
                ((EditText) SearchActionBar.this.f.findViewById(R.id.search_src_text)).clearFocus();
                an.a().a("click", "ac", "", "SEARCH", "", "VOICE_SEARCH", "", "");
            }
        });
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GaanaSearchManager.a().a(ACTION_TYPE.SEARCH_CANCEL.ordinal(), ACTION_DETAILS.SEARCH_CROSS_TAP.ordinal(), 0, "", 0, "");
                SearchActionBar.this.a();
            }
        });
        this.f.setSearchableInfo(((SearchManager) this.d.getSystemService("search")).getSearchableInfo(((GaanaActivity) this.d).getComponentName()));
        this.f.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                ((InputMethodManager) SearchActionBar.this.d.getSystemService("input_method")).hideSoftInputFromWindow(SearchActionBar.this.f.findViewById(R.id.search_src_text).getWindowToken(), 0);
                GaanaSearchManager.a().a((Activity) SearchActionBar.this.d, str.trim());
                SearchActionBar.this.j.a(str.trim(), "0");
                return true;
            }

            public boolean onQueryTextChange(String str) {
                if (SearchActionBar.this.h != null) {
                    if (TextUtils.isEmpty(str)) {
                        SearchActionBar.this.h.setImageDrawable(null);
                        SearchActionBar.this.h.setVisibility(8);
                        SearchActionBar.this.m.setVisibility(0);
                    } else {
                        SearchActionBar.this.h.setImageDrawable(SearchActionBar.this.k);
                        SearchActionBar.this.h.setVisibility(0);
                        SearchActionBar.this.m.setVisibility(8);
                    }
                }
                SearchActionBar.this.j.a(str.trim(), "0");
                return false;
            }
        });
        this.f.setOnCloseListener(new OnCloseListener() {
            public boolean onClose() {
                SearchActionBar.this.j.a("", "0");
                return false;
            }
        });
        if (this.h != null) {
            this.h.setVisibility(8);
        }
        findViewById(R.id.menu_icon).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchActionBar.this.c();
            }
        });
    }

    public void a() {
        this.g.setText("");
        this.h.setVisibility(8);
        c();
    }

    public void b() {
        ((GaanaActivity) this.d).onBottomMenuLongClick();
        ((GaanaActivity) this.d).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
        d.a().a("PREFERENCE_VOICE_SEARCH_COACHMARK", true, true);
        u.a().b("VoiceInteraction", "SearchMic");
    }

    public void onClick(View view) {
        if (view.getId() == R.id.menu_icon_back) {
            this.c.a();
        }
    }

    public View getSearchIcon() {
        return this.l;
    }

    public ImageView getBackIcon() {
        return this.o;
    }

    public void c() {
        if (this.f != null) {
            this.f.requestFocus();
            Util.b(this.d, (EditText) this.f.findViewById(R.id.search_src_text));
        }
    }

    public void setSearchText(String str) {
        if (this.b != null) {
            this.b.setText(str);
        }
    }
}
