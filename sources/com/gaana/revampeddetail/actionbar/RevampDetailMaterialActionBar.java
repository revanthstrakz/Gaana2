package com.gaana.revampeddetail.actionbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.actionbar.BaseContextualActionBar;
import com.b.i;
import com.fragments.BaseGaanaFragment;
import com.fragments.RevampedDetailListing;
import com.fragments.SearchEnchancedFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.PopupWindowView;
import com.managers.al;
import com.managers.an;

public class RevampDetailMaterialActionBar extends BaseContextualActionBar implements OnMenuItemClickListener, OnClickListener {
    private BusinessObject mBusinessObject;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private LayoutInflater mLayoutInflater;
    private Toolbar mToolbar;
    TextView titleTextView;

    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    public RevampDetailMaterialActionBar(Context context) {
        this(context, null);
    }

    public RevampDetailMaterialActionBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RevampDetailMaterialActionBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutInflater.inflate(R.layout.revampdetail_action_bar, this);
    }

    public void setParams(BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject) {
        this.mFragment = baseGaanaFragment;
        this.mBusinessObject = businessObject;
        super.setParams(baseGaanaFragment, businessObject);
        initActionBarViews();
    }

    private void initActionBarViews() {
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.titleTextView.setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        String name = (this.mBusinessObject == null || TextUtils.isEmpty(this.mBusinessObject.getName())) ? "" : this.mBusinessObject.getName();
        updateTitle(name);
        findViewById(R.id.menu_back).setOnClickListener(this);
        findViewById(R.id.overflow_menu).setRotation(90.0f);
        findViewById(R.id.overflow_menu).setOnClickListener(this);
        findViewById(R.id.searchview_actionbar).setOnClickListener(this);
        findViewById(R.id.menu_close).setOnClickListener(this);
    }

    public void updateTitle(String str) {
        if (this.titleTextView != null) {
            this.titleTextView.setText(str);
            this.titleTextView.setVisibility(0);
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        an.a().a("click", "ac", this.mBusinessObject.getBusinessObjId(), this.mBusinessObject.getName(), "", "three dot menu", "", "");
        switch (view.getId()) {
            case R.id.menu_back /*2131297699*/:
                ((GaanaActivity) this.mContext).homeIconClick();
                return;
            case R.id.menu_close /*2131297700*/:
                ((RevampedDetailListing) this.mFragment).k();
                return;
            case R.id.overflow_menu /*2131297901*/:
                if (this.mFragment instanceof RevampedDetailListing) {
                    ((RevampedDetailListing) this.mFragment).a("Context Menu ", true);
                }
                PopupWindowView instance = PopupWindowView.getInstance(this.mContext, this.mFragment);
                if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof RevampedDetailListing) {
                    an.a().a("click", "ac", this.mBusinessObject.getBusinessObjId(), "", "", "three dot menu", "", "");
                    instance.contextPopupWindow(this.mBusinessObject, false, (RevampedDetailListing) ((GaanaActivity) this.mContext).getCurrentFragment(), false);
                    return;
                }
                return;
            case R.id.searchview_actionbar /*2131298327*/:
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Action Bar Click", "Search");
                an.a().a("click", "ac", this.mBusinessObject.getBusinessObjId(), "", "", "search", "", "");
                BaseGaanaFragment searchEnchancedFragment = new SearchEnchancedFragment();
                searchEnchancedFragment.setArguments(new Bundle());
                ((GaanaActivity) this.mContext).clearStackForSearch();
                ((GaanaActivity) this.mContext).displayFragment(searchEnchancedFragment);
                return;
            default:
                return;
        }
    }

    public void showContextMenu(boolean z) {
        al.a = z;
        if (z) {
            findViewById(R.id.overflow_menu).setVisibility(4);
            findViewById(R.id.menu_back).setVisibility(8);
            findViewById(R.id.menu_close).setVisibility(0);
            return;
        }
        findViewById(R.id.overflow_menu).setVisibility(0);
        findViewById(R.id.menu_back).setVisibility(0);
        findViewById(R.id.menu_close).setVisibility(8);
        updateTitle(this.mBusinessObject.getName());
    }

    public void updateSelectedCountinContextMode(int i) {
        if (al.a().d()) {
            Context context;
            int i2;
            i = al.a().f();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(i));
            stringBuilder.append(" ");
            if (i > 1) {
                context = this.mContext;
                i2 = R.string.songs_selected;
            } else {
                context = this.mContext;
                i2 = R.string.song_selected;
            }
            stringBuilder.append(context.getString(i2));
            updateTitle(stringBuilder.toString());
        }
    }
}
