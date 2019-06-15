package com.fragments;

import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.constants.Constants;
import com.facebook.share.internal.ShareConstants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ListAdapter.IAddListItemView;
import com.gaana.adapter.ListAdapterSectionIndexer;
import com.gaana.adapter.ViewPagerAdapter;
import com.gaana.adapter.ViewPagerAdapter.AddItemListner;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.User.LoginType;
import com.gaana.models.UserMessage;
import com.gaana.view.CustomListView;
import com.gaana.view.item.BaseItemView.ItemEmptyMessageHolder;
import com.gaana.view.item.GaanaPlusItemView;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingButton.a;
import com.models.ListingComponents;
import com.services.d;
import java.util.ArrayList;
import java.util.Iterator;

public class ProfileFragment extends BaseGaanaFragment implements OnPageChangeListener, AddItemListner, a {
    private String a = null;
    private LinearLayout b;
    private ListingComponents c;
    private ViewPager d;
    private TabLayout e;
    private ViewPagerAdapter f;
    private CollapsingToolbarLayout g;
    private GaanaPlusItemView h = null;
    private BusinessObject i;
    private Button j;
    private ArrayList<CustomListView> k = null;
    private String l = "";
    private View m = null;
    private TypedValue n = new TypedValue();
    private int o = 0;
    private PopupMenu p = null;

    public void a(BusinessObject businessObject) {
    }

    public Object addItem(ViewGroup viewGroup, ListingButton listingButton) {
        return null;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.m == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.m = setContentView(R.layout.profile_fragment, viewGroup);
            GaanaApplication.getInstance().setPlayoutSectionName(getSectionName());
            getActivity().getTheme().resolveAttribute(R.attr.first_line_color, this.n, true);
            this.b = (LinearLayout) this.m.findViewById(R.id.llParentHeaderOfList);
            this.e = (TabLayout) this.m.findViewById(R.id.sliding_tabs);
            this.d = (ViewPager) this.m.findViewById(R.id.viewpager);
            this.j = (Button) this.m.findViewById(R.id.followButton);
            this.j.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((BaseActivity) ProfileFragment.this.mContext).sendGAEvent("Profile", "Edit", "Profile - Edit");
                    Bundle bundle = new Bundle();
                    BaseGaanaFragment editProfileActivityFragment = new EditProfileActivityFragment();
                    editProfileActivityFragment.setArguments(bundle);
                    ((GaanaActivity) ProfileFragment.this.mContext).displayFragment(editProfileActivityFragment);
                }
            });
            this.g = (CollapsingToolbarLayout) this.m.findViewById(R.id.collapsing_toolbar);
            ((CrossFadeImageView) this.m.findViewById(R.id.imgArtwork)).bindImage(this.mAppState.getCurrentUser().getUserProfile().getImg(), this.mAppState.isAppInOfflineMode());
            if (getArguments() != null) {
                this.a = getArguments().getString("EXTRA_PROFILE_ORIGIN_MYPROFILE");
            }
            this.l = this.mAppState.getCurrentUser().getUserProfile().getFullname();
            this.h = new GaanaPlusItemView(this.mContext, this);
            this.h.setSourceType("CARD");
            this.c = Constants.a((a) this);
            this.mAppState.setListingComponents(this.c);
            updateView();
        } else if (!(this.mAppState.getCurrentUser() == null || this.mAppState.getCurrentUser().getUserProfile() == null)) {
            this.l = this.mAppState.getCurrentUser().getUserProfile().getFullname();
        }
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        if (this.h != null) {
            this.h.setFragment(this.mContext, this);
        }
        if (this.c == null) {
            if (this.i == null) {
                this.c = Constants.a((a) this);
                this.d.setCurrentItem(0);
            }
            this.mAppState.setListingComponents(this.c);
        }
        Toolbar toolbar = (Toolbar) this.m.findViewById(R.id.toolbar);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.actionbar_back, R.attr.first_line_color});
        TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(0, -1)));
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_profile);
        this.p = new PopupMenu(this.mContext, (ImageView) this.m.findViewById(R.id.dummy_hidden_anchor_menu_option), GravityCompat.END);
        this.p.inflate(R.menu.menu_profile_submenu);
        toolbar.getMenu().findItem(R.id.action_edit).setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                ProfileFragment.this.a();
                return true;
            }
        });
        if (!ShareConstants.PEOPLE_IDS.equalsIgnoreCase(this.a)) {
            TypedArray obtainStyledAttributes3 = this.mContext.obtainStyledAttributes(new int[]{R.attr.social_following_attr, R.attr.first_line_color});
            this.j.setBackgroundDrawable(obtainStyledAttributes3.getDrawable(0));
            this.j.setTextColor(obtainStyledAttributes3.getColor(1, -1));
            obtainStyledAttributes3.recycle();
            this.j.setText(this.mContext.getResources().getString(R.string.edit));
            MenuItem findItem = toolbar.getMenu().findItem(R.id.action_edit);
            if (this.mAppState.getCurrentUser().getLoginType() == LoginType.GAANA) {
                findItem.setVisible(true);
            } else {
                findItem.setVisible(false);
            }
        }
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((GaanaActivity) ProfileFragment.this.mContext).onBackPressedHandling();
            }
        });
        a(toolbar);
        this.g.setTitleEnabled(true);
        int b = (int) (((float) d.a().b()) / 2.5f);
        if (!TextUtils.isEmpty(this.l)) {
            this.g.setTitle(this.l);
        }
        this.g.setExpandedTitleMarginStart(b);
        this.g.setExpandedTitleTextAppearance(R.style.black_bold_20);
        this.g.setCollapsedTitleTextColor(obtainStyledAttributes.getColor(1, -1));
        this.g.setExpandedTitleColor(obtainStyledAttributes.getColor(1, -1));
        obtainStyledAttributes.recycle();
        obtainStyledAttributes2.recycle();
        return this.m;
    }

    private void a() {
        if (!ShareConstants.PEOPLE_IDS.equalsIgnoreCase(this.a)) {
            this.p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem menuItem) {
                    u.a().b("Profile", "More Option");
                    if (menuItem.getItemId() != R.id.change_password) {
                        return false;
                    }
                    ((GaanaActivity) ProfileFragment.this.mContext).displayFragment(new ChangePassWordFragment());
                    return true;
                }
            });
        }
        this.p.show();
    }

    private void a(Toolbar toolbar) {
        ((BaseActivity) this.mContext).initializeMediaRouterButton(toolbar.getMenu(), R.id.media_route_menu_item);
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onResume() {
        super.onResume();
        c();
        if (this.k != null && this.k.size() > this.d.getCurrentItem() - 1 && this.d.getCurrentItem() > 0) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((CustomListView) this.k.get(this.d.getCurrentItem() - 1)).getCustomListView().getLayoutManager();
            if (linearLayoutManager != null) {
                linearLayoutManager.scrollToPosition(this.o);
            }
        }
        this.o = 0;
    }

    public void onStart() {
        super.onStart();
        if ("MYPROFILE".equalsIgnoreCase(this.a)) {
            UserInfo currentUser = this.mAppState.getCurrentUser();
            this.l = this.mAppState.getCurrentUser().getUserProfile().getFullname();
            this.g.setTitle(this.l);
            if (currentUser != null && currentUser.getUserProfile() != null && currentUser.getLoginStatus()) {
                ((CrossFadeImageView) this.m.findViewById(R.id.imgArtwork)).bindImage(currentUser.getUserProfile().getImg(), this.mAppState.isAppInOfflineMode());
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateView() {
        super.updateView();
        getResources().getColor(R.color.f17gaana.red);
        this.e.setTabTextColors(this.n.data, this.n.data);
        if (ShareConstants.PEOPLE_IDS.equalsIgnoreCase(this.a)) {
            this.e.setTabMode(1);
            this.e.setTabGravity(0);
        } else if (this.mAppState.getCurrentUser().isSocialEnabled()) {
            this.e.setTabMode(0);
            this.e.setTabGravity(1);
        } else {
            this.e.setTabMode(1);
            this.e.setTabGravity(0);
        }
        this.k = new ArrayList();
        this.d.setOnPageChangeListener(this);
        this.f = new ViewPagerAdapter();
        if (this.c.b().booleanValue() && this.c.c().size() > 0) {
            this.f.setAdapterParams(this.c.c().size(), this, this.c);
        }
        this.d.setAdapter(this.f);
        this.e.setupWithViewPager(this.d);
        setGAScreenName("Profile", "Profile");
    }

    public void refreshListView() {
        if (this.k != null) {
            Iterator it = this.k.iterator();
            while (it.hasNext()) {
                CustomListView customListView = (CustomListView) it.next();
                if (customListView.getListAdapter() != null) {
                    customListView.getListAdapter().notifyDataSetChanged();
                }
            }
        }
        UserInfo currentUser = this.mAppState.getCurrentUser();
        if (currentUser != null && currentUser.getUserProfile() != null && currentUser.getLoginStatus()) {
            ((CrossFadeImageView) this.m.findViewById(R.id.imgArtwork)).bindImage(currentUser.getUserProfile().getImg(), this.mAppState.isAppInOfflineMode());
        }
    }

    public Object addItem(ViewGroup viewGroup, int i) {
        if ("MYPROFILE".equalsIgnoreCase(this.a) && i == 0) {
            RecyclerView b = b();
            if (b.getParent() != null) {
                ((ViewGroup) b.getParent()).removeView(b);
            }
            viewGroup.addView(b);
            return b;
        }
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.gaana_background});
        CustomListView customListView = new CustomListView(this.mContext, this);
        this.k.add(customListView);
        customListView.setUpdateListView((ListingButton) this.c.c().get(i));
        viewGroup.addView(customListView.getListView(), 0);
        customListView.getCustomListView().setBackgroundColor(obtainStyledAttributes.getColor(0, -1));
        obtainStyledAttributes.recycle();
        return customListView.getListView();
    }

    public void onPageSelected(int i) {
        ((BaseActivity) this.mContext).currentPagerView = ((ListingButton) this.c.c().get(i)).d();
        if (i == 0 && "MYPROFILE".equalsIgnoreCase(this.a) && this.h != null && this.h.getIfUpSellPage()) {
            ((BaseActivity) this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Profile");
        }
    }

    public void onDestroyView() {
        if (this.m.getParent() != null) {
            ((ViewGroup) this.m.getParent()).removeView(this.m);
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.OTHER_PROFILE.name();
    }

    private RecyclerView b() {
        RecyclerView recyclerView = new RecyclerView(this.mContext);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UserMessage());
        ListAdapterSectionIndexer listAdapterSectionIndexer = new ListAdapterSectionIndexer(this.mContext, this);
        listAdapterSectionIndexer.setParamaters(arrayList, new IAddListItemView() {
            public int getItemViewType(int i) {
                return 12;
            }

            public void showHideEmtpyView(boolean z) {
            }

            public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
                return viewHolder.itemView;
            }

            public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                return new ItemEmptyMessageHolder(ProfileFragment.this.h.getPoplatedView(viewGroup));
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.bottomMargin = 110;
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapterSectionIndexer);
        return recyclerView;
    }

    private void c() {
        float f = this.mContext.getResources().getDisplayMetrics().density;
        Paint paint = new Paint();
        paint.setTextSize(20.0f * f);
        if (TextUtils.isEmpty(this.l)) {
            this.l = "";
        }
        f = paint.measureText(this.l);
        float b = (float) (((int) (((float) d.a().b()) - (((float) d.a().b()) / 2.5f))) - 50);
        if (f > b) {
            int measureText = ((int) ((f - b) / paint.measureText("A"))) + 2;
            CollapsingToolbarLayout collapsingToolbarLayout = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.l.substring(0, this.l.length() - measureText));
            stringBuilder.append("...");
            collapsingToolbarLayout.setTitle(stringBuilder.toString());
        }
    }

    public void onPause() {
        super.onPause();
        if (this.k != null && this.k.size() > this.d.getCurrentItem() - 1 && this.d.getCurrentItem() > 0) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((CustomListView) this.k.get(this.d.getCurrentItem() - 1)).getCustomListView().getLayoutManager();
            if (linearLayoutManager != null) {
                this.o = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            }
        }
    }

    public String getPageName() {
        int currentItem = (this.d == null || this.d.getCurrentItem() <= 0) ? 0 : this.d.getCurrentItem();
        if (("MYPROFILE".equalsIgnoreCase(this.a) && currentItem == 1) || (ShareConstants.PEOPLE_IDS.equalsIgnoreCase(this.a) && currentItem == 0)) {
            return PAGE_SORCE_NAME.ACTIVITY.name();
        }
        if (("MYPROFILE".equalsIgnoreCase(this.a) && currentItem == 2) || (ShareConstants.PEOPLE_IDS.equalsIgnoreCase(this.a) && currentItem == 1)) {
            return PAGE_SORCE_NAME.SOCIAL_ACTIVITY.name();
        }
        return super.getPageName();
    }
}
