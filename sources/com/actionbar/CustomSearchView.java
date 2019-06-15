package com.actionbar;

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
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.managers.GaanaSearchManager.b;

public class CustomSearchView extends LinearLayout {
    int[] a = new int[]{R.attr.searchIcon, R.attr.actionbar_cancel, R.attr.first_line_color, R.attr.first_line_color_90, R.attr.search_bottom_underline};
    private Context b;
    private LayoutInflater c;
    private SearchView d;
    private SearchAutoComplete e;
    private ImageView f = null;
    private b g;
    private Drawable h;
    private Drawable i;

    public CustomSearchView(Context context) {
        super(context);
        a(context);
    }

    public CustomSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CustomSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setSearchInterface(b bVar) {
        this.g = bVar;
    }

    private void a(Context context) {
        this.b = context;
        this.h = ContextCompat.getDrawable(getContext(), this.b.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(1, -1));
        TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(this.a);
        this.i = obtainStyledAttributes.getDrawable(4);
        this.c = LayoutInflater.from(this.b);
        setLayoutParams(new LayoutParams(-1, -2));
        this.c.inflate(R.layout.songs_selection_search_bar, this);
        this.d = (SearchView) findViewById(R.id.searchview_actionbar);
        this.d.setIconified(false);
        ImageView imageView = (ImageView) this.d.findViewById(R.id.search_voice_btn);
        if (imageView != null) {
            imageView.setImageDrawable(null);
            imageView.setVisibility(8);
        }
        this.d.findViewById(R.id.search_plate).setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.d.setBackgroundDrawable(this.i);
        EditText editText = (EditText) this.d.findViewById(R.id.search_src_text);
        editText.setHintTextColor(obtainStyledAttributes.getColor(3, -1));
        editText.setTextColor(obtainStyledAttributes.getColor(2, -1));
        obtainStyledAttributes.recycle();
        a();
    }

    private void a() {
        this.e = (SearchAutoComplete) this.d.findViewById(R.id.search_src_text);
        this.f = (ImageView) this.d.findViewById(R.id.search_close_btn);
        this.f.setImageDrawable(this.h);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CustomSearchView.this.e.setText("");
                if (CustomSearchView.this.f != null) {
                    CustomSearchView.this.f.setVisibility(8);
                }
            }
        });
        this.d.setSearchableInfo(((SearchManager) this.b.getSystemService("search")).getSearchableInfo(((GaanaActivity) this.b).getComponentName()));
        this.d.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                ((InputMethodManager) CustomSearchView.this.b.getSystemService("input_method")).hideSoftInputFromWindow(CustomSearchView.this.d.findViewById(R.id.search_src_text).getWindowToken(), 0);
                if (CustomSearchView.this.g != null) {
                    CustomSearchView.this.g.a(str, "0");
                }
                return true;
            }

            public boolean onQueryTextChange(String str) {
                if (TextUtils.isEmpty(str)) {
                    if (CustomSearchView.this.f != null) {
                        CustomSearchView.this.f.setVisibility(8);
                    }
                } else if (CustomSearchView.this.f != null) {
                    CustomSearchView.this.f.setVisibility(0);
                }
                if (CustomSearchView.this.g != null) {
                    CustomSearchView.this.g.a(str, "0");
                }
                return false;
            }
        });
        this.d.setOnCloseListener(new OnCloseListener() {
            public boolean onClose() {
                CustomSearchView.this.g.a("", "0");
                return false;
            }
        });
        if (this.f != null) {
            this.f.setVisibility(8);
        }
    }
}
