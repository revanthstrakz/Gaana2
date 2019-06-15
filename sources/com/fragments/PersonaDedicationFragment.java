package com.fragments;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.collapsible_header.f;
import com.collapsible_header.i;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.adapter.NextGenAutoSuggestAdapter;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages;
import com.gaana.models.PersonaDedications;
import com.gaana.models.UserMessage;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.ItemEmptyMessageHolder;
import com.gaana.view.item.TwoLineView;
import com.gaana.view.item.TwoLineView.TwoLineHolder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.library.controls.CrossFadeImageView;
import com.library.controls.CrossFadeImageView.ImageLoadingCompeletedListener;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager.c;
import com.managers.ColombiaManager;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.GaanaSearchManager.b;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.models.ListingComponents;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.services.l.q;
import com.services.n;
import com.utilities.Util;
import com.views.ColumbiaAdItemview;
import java.util.ArrayList;

public class PersonaDedicationFragment extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, d, IAddListItemView, c, b, q {
    private static float d = 1.2f;
    private boolean A = false;
    private ColumbiaAdItemview B = null;
    private LinearLayout C;
    private Button D;
    private Button E;
    private String F = MoEHelperConstants.GENDER_FEMALE;
    private CrossFadeImageView G;
    private View H;
    private TextView I;
    private boolean J = true;
    private SearchTabFragment K;
    private SearchView L;
    private View M;
    private int N = 0;
    private PublisherAdView O;
    private LinearLayout P;
    LinearLayout a = null;
    FragmentTransaction b;
    FragmentManager c;
    private View e = null;
    private ListingComponents f;
    private BusinessObject g;
    private ObservableRecyclerView h;
    private CrossFadeImageView i;
    private CrossFadeImageView j;
    private CustomListAdapter k;
    private BaseItemView l;
    private SwipeRefreshLayout m;
    private boolean n = false;
    private int o = 0;
    private DetailsMaterialActionBar p;
    private Toolbar q;
    private ProgressBar r;
    private int s;
    private View t;
    private ArrayList<BusinessObject> u = new ArrayList();
    private ArrayList<BusinessObject> v = new ArrayList();
    private String w = "";
    private int x = 0;
    private boolean y = false;
    private DisplayMetrics z;

    public void a(View view) {
    }

    public void e() {
    }

    public void l() {
    }

    public void onDownMotionEvent() {
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    public void showHideEmtpyView(boolean z) {
    }

    private boolean a(ViewGroup viewGroup) {
        this.f = Constants.m();
        a(TwoLineView.class.getName());
        b(viewGroup);
        this.mAppState.setListingComponents(this.f);
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.g != null) {
            this.g.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.g);
        }
    }

    public final void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.DEDICATIONS.name();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.A = false;
        this.z = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.z);
        GaanaApplication.getInstance().setPlayoutSectionName(getSectionName());
        if (this.e == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            a(viewGroup);
            this.r.setVisibility(0);
            if (GaanaApplication.getInstance().getCurrentUser().getUserProfile() != null) {
                String sex = GaanaApplication.getInstance().getCurrentUser().getUserProfile().getSex();
                if (!TextUtils.isEmpty(sex) && sex.equals(MoEHelperConstants.GENDER_FEMALE)) {
                    this.F = MoEHelperConstants.GENDER_MALE;
                }
            }
            b(this.F);
        } else {
            this.mAppState.setListingComponents(this.f);
            if (((GaanaActivity) this.mContext).getRefreshData()) {
                ((GaanaActivity) this.mContext).setRefreshData(false);
                d();
            } else if (!(this.h == null || this.h.getAdapter() == null)) {
                this.h.getAdapter().notifyDataSetChanged();
            }
        }
        if (this.m == null) {
            ((GaanaActivity) this.mContext).popBackStack();
        } else if (this.n) {
            this.m.setRefreshing(true);
        } else {
            this.m.setRefreshing(false);
        }
        setGAScreenName("DedicationPersonaScreen", "DedicationPersonaScreen");
        return this.e;
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setFragment(this);
        if (this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            this.loginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        }
        if (this.O != null) {
            this.O.resume();
        }
    }

    public void onPause() {
        if (this.O != null) {
            this.O.pause();
        }
        super.onPause();
    }

    private void b(ViewGroup viewGroup) {
        this.e = setContentView(R.layout.fragment_persona_dedication, viewGroup);
        this.s = com.services.d.a().a(235);
        this.m = (SwipeRefreshLayout) this.e.findViewById(R.id.swipe_refresh_layout);
        this.m.setOnRefreshListener(this);
        this.h = (ObservableRecyclerView) this.e.findViewById(R.id.scroll);
        this.i = (CrossFadeImageView) this.e.findViewById(R.id.details_artwork);
        this.G = (CrossFadeImageView) this.e.findViewById(R.id.img_logo);
        this.j = (CrossFadeImageView) this.e.findViewById(R.id.details_artwork_footer);
        this.H = this.e.findViewById(R.id.overlay);
        this.H.getLayoutParams().height = this.s;
        i.g(this.H, (float) this.s);
        this.h.setScrollViewCallbacks(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setItemPrefetchEnabled(false);
        this.h.setLayoutManager(linearLayoutManager);
        this.h.setHasFixedSize(false);
        this.t = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header, null);
        this.L = (SearchView) this.e.findViewById(R.id.search_view);
        EditText editText = (EditText) this.L.findViewById(R.id.search_src_text);
        editText.setGravity(17);
        editText.setTextColor(getResources().getColor(R.color.second_line_color_white));
        editText.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.default_text_size));
        this.t.setLayoutParams(new LayoutParams(-1, this.s));
        com.services.d.a().a(195);
        this.t.getLayoutParams().height = this.s;
        this.k = new CustomListAdapter(this.mContext, this.t);
        this.k.setParamaters(0, this);
        this.h.setAdapter(this.k);
        this.q = (Toolbar) this.e.findViewById(R.id.main_toolbar);
        this.q.setContentInsetsAbsolute(0, 0);
        this.p = new DetailsMaterialActionBar(this.mContext);
        this.q.addView(this.p);
        this.p.setParams(this, this.g);
        this.p.showContextMenu(false);
        this.p.setToolbar(this.q);
        this.p.findViewById(R.id.menu_icon).setOnClickListener(this);
        this.r = (ProgressBar) this.e.findViewById(R.id.progressbar);
    }

    private void a(@NonNull PersonaDedications personaDedications) {
        this.i.bindImage(personaDedications.getMobHeaderImage(), new ImageLoadingCompeletedListener() {
            public void onError() {
            }

            public void onImageLoadingCompeleted(Bitmap bitmap) {
            }
        }, this.i.getScaleType());
        this.j.bindImage(personaDedications.getMobFooterImage(), new ImageLoadingCompeletedListener() {
            public void onError() {
            }

            public void onImageLoadingCompeleted(Bitmap bitmap) {
            }
        }, this.j.getScaleType());
        this.G.bindImage(ap.a().d() ? personaDedications.getMobLogoPaid() : personaDedications.getMobLogoFree(), new ImageLoadingCompeletedListener() {
            public void onError() {
            }

            public void onImageLoadingCompeleted(Bitmap bitmap) {
            }
        }, this.G.getScaleType());
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.mContext != null) {
            com.views.i slidingPanelLayout = ((GaanaActivity) this.mContext).getSlidingPanelLayout();
            if (slidingPanelLayout == null || slidingPanelLayout.a() != 1) {
                f();
            }
        }
    }

    private void f() {
        if (this.g != null && this.k != null) {
            this.k.notifyDataSetChanged();
        }
    }

    public String[] a() {
        return new String[]{((PersonaDedications) this.g).getMobHeaderImage(), ((PersonaDedications) this.g).getMobFooterImage()};
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        f();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        f();
    }

    /* Access modifiers changed, original: protected */
    public int c() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    private void b(String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/home/personas?gender=<which>".replace("<which>", str));
        uRLManager.c(Boolean.valueOf(this.n));
        uRLManager.a(BusinessObjectType.PersonaDedications);
        this.r.setVisibility(0);
        com.i.i.a().a(uRLManager, toString(), (com.android.volley.i.b) this, (a) this);
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        try {
            this.l = (BaseItemView) Class.forName(str).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
        } catch (Exception unused) {
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void d() {
        if (this.f != null && this.f.c() != null) {
            b(this.F);
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (i == 0) {
            return a(viewHolder);
        }
        if (viewHolder.getItemViewType() == 5) {
            return a(viewHolder, i);
        }
        if (i != 3) {
            if (i > 3) {
                i--;
            }
            return this.l.getPoplatedView(viewHolder, (BusinessObject) this.u.get(i - 1), viewGroup);
        } else if (!ap.a().b(this.mContext)) {
            return new View(this.mContext);
        } else {
            if (this.B == null) {
                this.B = new ColumbiaAdItemview(this.mContext, this);
                this.B.setTransparentLayout(true);
            }
            return this.B.getPopulatedView(i, viewHolder.itemView, (ViewGroup) viewHolder.itemView.getParent(), this.g);
        }
    }

    private View a(ViewHolder viewHolder) {
        this.M = viewHolder.itemView;
        this.D = (Button) this.M.findViewById(R.id.btn_male);
        this.D.setOnClickListener(this);
        this.E = (Button) this.M.findViewById(R.id.btn_female);
        this.E.setOnClickListener(this);
        this.C = (LinearLayout) this.M.findViewById(R.id.ll_header_search);
        this.I = (TextView) this.M.findViewById(R.id.txt_gender_like);
        this.C.setOnClickListener(this);
        this.C.setVisibility(0);
        a(this.F.equals(MoEHelperConstants.GENDER_MALE));
        this.M.setVisibility(0);
        return this.M;
    }

    public void notifyItemChanged(int i) {
        if (this.k != null) {
            this.k.notifyItemChanged(i + 1);
        }
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        if (i == 3) {
            UserMessage userMessage = new UserMessage();
            userMessage.setEmptyMsg(this.mContext.getResources().getString(R.string.NO_DATA));
            return new ItemEmptyMessageHolder(this.l.getEmptyMsgView(userMessage, viewGroup));
        } else if (i == 2) {
            return new ItemAdViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_persona_header, viewGroup, false));
        } else {
            if (i == 4) {
                if (this.B == null) {
                    this.B = new ColumbiaAdItemview(this.mContext, this);
                    this.B.setTransparentLayout(true);
                }
                return new ItemAdViewHolder(this.B.getNewView(0, viewGroup));
            } else if (i == 5) {
                return new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_dfp_ad, viewGroup, false));
            } else {
                return new TwoLineHolder(this.l.createViewHolder(viewGroup, i));
            }
        }
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 2;
        }
        if (i == 3) {
            return 4;
        }
        return i == this.k.getItemCount() - 2 ? 5 : 1;
    }

    public void onRefresh() {
        if (!this.n) {
            this.m.setRefreshing(true);
            this.n = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
                if (this.B != null) {
                    this.B.a();
                }
                this.J = true;
            }
            b(this.F);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_female) {
            this.F = MoEHelperConstants.GENDER_FEMALE;
            b(this.F);
            a(false);
        } else if (id == R.id.btn_male) {
            this.F = MoEHelperConstants.GENDER_MALE;
            b(this.F);
            a(true);
        } else if (id == R.id.ll_header_search) {
            int[] iArr = new int[2];
            r6 = new int[2];
            this.p.getLocationOnScreen(iArr);
            iArr[1] = iArr[1] + this.p.getHeight();
            this.C.getLocationOnScreen(r6);
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (iArr[1] - r6[1]));
            translateAnimation.setDuration(300);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    PersonaDedicationFragment.this.a(animation);
                }
            });
            this.C.startAnimation(translateAnimation);
        } else if (id == R.id.menu_icon) {
            b();
        }
    }

    private void a(Animation animation) {
        this.M.setVisibility(8);
        this.C.clearAnimation();
        this.e.findViewById(R.id.ll_search_view_header).setVisibility(0);
        g();
        GaanaSearchManager.a().a((Activity) this.mContext, "", "0");
        this.K = new SearchTabFragment();
        this.K.setShouldShowKeyboard(true);
        Bundle bundle = new Bundle();
        bundle.putBoolean("ADD_MORE_SONGS", true);
        this.K.setArguments(bundle);
        this.L.setIconified(false);
        Object a = n.a(com.services.d.a().c("PREFERENCE_LANGUAGE_SETTINGS", false));
        if (a != null && (a instanceof Languages)) {
            GaanaSearchManager.a().a(((Languages) a).getArrListBusinessObj());
        }
        a(this.K, (int) R.id.ll_fragment_id);
        this.e.findViewById(R.id.coordinator_layout).setVisibility(8);
    }

    private void a(boolean z) {
        TypedArray obtainStyledAttributes;
        int color;
        if (z) {
            this.D.setBackgroundResource(R.drawable.red_rounded_button);
            this.D.setTextColor(-1);
            this.E.setBackgroundDrawable(null);
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.second_line_color});
            color = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.second_line_color));
            obtainStyledAttributes.recycle();
            this.E.setTextColor(color);
            this.I.setText(getString(R.string.whats_like, getString(R.string.he)));
            return;
        }
        this.E.setBackgroundResource(R.drawable.red_rounded_button);
        this.E.setTextColor(-1);
        this.D.setBackgroundDrawable(null);
        obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.second_line_color});
        color = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.second_line_color));
        obtainStyledAttributes.recycle();
        this.D.setTextColor(color);
        this.I.setText(getString(R.string.whats_like, getString(R.string.she)));
    }

    private void g() {
        this.L.setSearchableInfo(((SearchManager) this.mContext.getSystemService("search")).getSearchableInfo(((GaanaActivity) this.mContext).getComponentName()));
        this.L.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                ((InputMethodManager) PersonaDedicationFragment.this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(PersonaDedicationFragment.this.L.findViewById(R.id.search_src_text).getWindowToken(), 0);
                PersonaDedicationFragment.this.a(str, "0");
                return true;
            }

            public boolean onQueryTextChange(String str) {
                PersonaDedicationFragment.this.a(str, "0");
                return false;
            }
        });
        this.L.setOnCloseListener(new OnCloseListener() {
            public boolean onClose() {
                PersonaDedicationFragment.this.a("", "0");
                return false;
            }
        });
    }

    private void a(BaseGaanaFragment baseGaanaFragment, int i) {
        GaanaSearchManager.a().a((b) this);
        GaanaSearchManager.a().a(SearchType.OnlySongs);
        this.c = getChildFragmentManager();
        if (baseGaanaFragment != null) {
            this.b = this.c.beginTransaction();
            this.b.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            this.b.replace(i, baseGaanaFragment);
            this.b.addToBackStack(baseGaanaFragment.toString());
            try {
                this.b.commitAllowingStateLoss();
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void onDestroyView() {
        if (this.e.getParent() != null) {
            ((ViewGroup) this.e.getParent()).removeView(this.e);
        }
        super.onDestroyView();
        this.A = true;
        if (this.O != null) {
            this.O.destroy();
            this.O = null;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.n = false;
        super.onErrorResponse(volleyError);
        showNetworkErrorView(null);
        this.r.setVisibility(8);
        this.o = 1;
        this.k.updateAdapterCount(this.o);
    }

    public void onResponse(Object obj) {
        if (!this.A) {
            this.n = false;
            this.m.setRefreshing(false);
            this.r.setVisibility(8);
            BusinessObject businessObject = (BusinessObject) obj;
            this.g = businessObject;
            this.g.setBusinessObjType(BusinessObjectType.PersonaDedications);
            this.f.a(this.g);
            this.mAppState.setListingComponents(this.f);
            a((PersonaDedications) this.g);
            this.o = 1;
            this.u.clear();
            String b = Constants.b(this.g.getName());
            if (!TextUtils.isEmpty(b)) {
                this.p.getTitleTextView().setText(b);
            }
            if (!(businessObject == null || businessObject.getArrListBusinessObj() == null)) {
                ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                if (arrListBusinessObj.size() > 2) {
                    this.o = arrListBusinessObj.size() + 3;
                } else {
                    this.o = arrListBusinessObj.size() + 2;
                }
                this.u.addAll(arrListBusinessObj);
            }
            this.k.updateAdapterCount(this.o);
        }
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        if (i > this.z.widthPixels) {
            onScrollChanged(this.z.widthPixels, false, false);
        } else if (((LinearLayoutManager) this.h.getLayoutManager()).findFirstVisibleItemPosition() != 0) {
            i = (int) (-this.t.getY());
        }
        float c = (float) (this.s - c());
        float a = f.a((float) (-i), (float) (c() - this.H.getHeight()), 0.0f);
        i.g(this.H, a);
        i.g(this.i, a);
        i.g(this.G, a);
        float f = ((float) i) / c;
        a = f.a(f, 0.0f, 1.0f);
        i.a(this.H, a);
        i.a(this.p.getTitleTextView(), a);
        i.a(this.p.getTitleTextView(), f.a(f, 0.0f, 1.0f));
    }

    public void refreshDataandAds() {
        onRefresh();
    }

    public void b() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            GaanaSearchManager.a().a(null);
            getChildFragmentManager().popBackStack();
            this.e.findViewById(R.id.coordinator_layout).setVisibility(0);
            this.L.setQuery("", false);
            this.e.findViewById(R.id.ll_search_view_header).setVisibility(8);
            this.k.notifyItemChanged(1);
            return;
        }
        ((GaanaActivity) this.mContext).homeIconClick();
    }

    public void a(NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter) {
        if (nextGenAutoSuggestAdapter != null) {
            nextGenAutoSuggestAdapter.shouldHideHeaderText(true);
        }
        this.K.a(nextGenAutoSuggestAdapter);
    }

    public void a(String str, String str2) {
        this.K.a(str);
        this.K.a(false);
        GaanaSearchManager.a().a((Activity) this.mContext, str, str2);
    }

    public void a(boolean z, boolean z2) {
        this.K.b(z);
    }

    public void a(Context context) {
        if (!(this.e == null || this.e.getWindowToken() == null)) {
            Util.a(this.mContext, this.e);
        }
        if (this.L != null && this.L.hasFocus()) {
            this.L.clearFocus();
        }
    }

    public View a(final ViewHolder viewHolder, int i) {
        this.P = (LinearLayout) viewHolder.itemView.findViewById(R.id.llNativeAdSlot);
        if (!ap.a().b(this.mContext)) {
            return new View(this.mContext);
        }
        if (this.O == null) {
            this.O = new PublisherAdView(this.mContext);
        }
        if (this.J && !e.M.equals("0")) {
            if (this.O.getAdUnitId() == null) {
                this.O.setAdUnitId(e.M);
            }
            AdSize[] adSizeArr = new AdSize[]{new AdSize(ModuleDescriptor.MODULE_VERSION, 50)};
            final AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName("DedicationPersonaScreen");
            adsUJData.setAdUnitCode(this.w);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
            this.O.setAdSizes(adSizeArr);
            this.O.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        PersonaDedicationFragment.this.P.removeAllViews();
                        PersonaDedicationFragment.this.P.setPadding(PersonaDedicationFragment.this.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin), PersonaDedicationFragment.this.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin_half), PersonaDedicationFragment.this.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin), PersonaDedicationFragment.this.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin_half));
                        PersonaDedicationFragment.this.P.addView(PersonaDedicationFragment.this.O);
                        PersonaDedicationFragment.this.P.setVisibility(0);
                        viewHolder.itemView.setVisibility(0);
                        PersonaDedicationFragment.this.J = false;
                        if (adsUJData != null) {
                            an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            if (adsUJData != null) {
                try {
                    an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                } catch (Exception unused) {
                }
            }
            Location location = ((GaanaActivity) this.mContext).getLocation();
            if (location != null) {
                Builder builder = new Builder();
                if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                    builder.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Util.l(GaanaApplication.getContext()));
                stringBuilder.append("Gaana ");
                builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                Location location2 = new Location("");
                location2.setLatitude(location.getLatitude());
                location2.setLongitude(location.getLongitude());
                this.O.loadAd(builder.setLocation(location2).build());
            } else {
                Builder builder2 = new Builder();
                if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                    builder2.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Util.l(GaanaApplication.getContext()));
                stringBuilder2.append("Gaana ");
                builder2.setPublisherProvidedId(Util.c(stringBuilder2.toString()));
                this.O.loadAd(builder2.build());
            }
        }
        return this.P;
    }
}
