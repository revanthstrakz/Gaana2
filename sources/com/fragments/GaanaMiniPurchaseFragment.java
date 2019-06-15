package com.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.constants.Constants;
import com.constants.Constants.REVAMPED_DETAIL_TYPE;
import com.constants.c.c;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.GaanaMiniSubDetails;
import com.gaana.login.UserInfo;
import com.gaana.models.AlbumDetail;
import com.gaana.models.BusinessObject;
import com.gaana.models.PlaylistDetail;
import com.gaana.view.item.GaanaPlusPurchaseItemView;
import com.i.d;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ag;
import com.managers.ag.a;
import com.managers.aj;
import com.managers.o;
import com.models.GaanaMiniProduct;
import com.models.GaanaMiniProduct.GaanaMiniProductDetail;
import java.util.ArrayList;
import java.util.Iterator;

public class GaanaMiniPurchaseFragment extends BaseGaanaFragment implements a {
    private GaanaMiniProduct a;
    private View b = null;
    private LinearLayout c;
    private String d;
    private String e;
    private b<Object> f;

    public static Bundle a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("entity_id", str);
        bundle.putString("entity_type", str2);
        return bundle;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.b = setContentView(R.layout.fragment_gaana_mini, viewGroup);
        Bundle arguments = getArguments();
        Toolbar toolbar = (Toolbar) this.b.findViewById(R.id.main_toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        GenericBackActionBar genericBackActionBar = new GenericBackActionBar(this.mContext, this.mContext.getResources().getString(R.string.GAANA_PLUS_MINI_PACKS));
        toolbar.removeAllViews();
        toolbar.addView(genericBackActionBar);
        if (arguments != null) {
            this.d = arguments.getString("entity_id");
            this.e = arguments.getString("entity_type");
            a();
        }
        setGAScreenName("GaanaMiniProduct", "GaanaMiniProduct");
        return this.b;
    }

    public void a() {
        a(null);
    }

    public void a(b<Object> bVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/packsubscription.php?type=pack_subscribe&entity_type=");
        stringBuilder.append(this.e);
        stringBuilder.append("&entity_id=");
        stringBuilder.append(this.d);
        String stringBuilder2 = stringBuilder.toString();
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&token=");
            stringBuilder3.append(currentUser.getAuthToken());
            stringBuilder2 = stringBuilder3.toString();
        }
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.a(GaanaMiniProduct.class);
        uRLManager.b(Boolean.valueOf(false));
        this.f = bVar;
        i.a().a(uRLManager, "GaanaMiniPurchase", (b) this, (com.android.volley.i.a) this);
    }

    private void c() {
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) this.b.findViewById(R.id.gaana_mini_top_left_section_image);
        TextView textView = (TextView) this.b.findViewById(R.id.gaana_mini_top_left_section_price);
        TextView textView2 = (TextView) this.b.findViewById(R.id.gaana_mini_top_left_section_duaration);
        TextView textView3 = (TextView) this.b.findViewById(R.id.gaana_mini_top_right_section_item_name);
        TextView textView4 = (TextView) this.b.findViewById(R.id.gaana_mini_top_right_section_item_desc);
        TextView textView5 = (TextView) this.b.findViewById(R.id.gaana_mini_top_right_section_item_offers);
        GaanaMiniProductDetail b = this.a.b();
        crossFadeImageView.bindImage(b.c());
        textView.setText(b.g());
        textView2.setText(b.f());
        textView3.setText(b.a());
        textView4.setText(b.b());
        textView5.setText(b.h());
        this.b.findViewById(R.id.scrollView).setVisibility(0);
        this.c = (LinearLayout) this.b.findViewById(R.id.product_layout);
        if (this.a == null || this.a.a() == null) {
            this.c.setVisibility(8);
            return;
        }
        this.c.setVisibility(0);
        this.c.removeAllViews();
        ArrayList a = this.a.a();
        if (a == null || a.size() <= 0) {
            this.c.setVisibility(8);
            return;
        }
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != null) {
                this.c.addView(new GaanaPlusPurchaseItemView(this.mContext, this, false, null).getPopulatedViewGaanaMini(this.c, (BusinessObject) a.get(i), this, i));
            }
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onResponse(Object obj) {
        super.onResponse(obj);
        ((BaseActivity) this.mContext).hideProgressDialog();
        if (!(obj == null || this.b == null)) {
            if (obj instanceof GaanaMiniProduct) {
                this.a = (GaanaMiniProduct) obj;
                if (this.a.b() != null) {
                    c();
                } else {
                    if (TextUtils.isEmpty(this.a.c())) {
                        aj.a().a(this.mContext, "No product found");
                    } else {
                        aj.a().a(this.mContext, this.a.c());
                    }
                    try {
                        if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof GaanaMiniPurchaseFragment) {
                            ((GaanaActivity) this.mContext).popBackStack();
                        }
                    } catch (Exception unused) {
                    }
                }
            } else {
                boolean z = obj instanceof AlbumDetail;
                if (z || (obj instanceof PlaylistDetail)) {
                    BaseGaanaFragment albumDetailsMaterialListing = new AlbumDetailsMaterialListing();
                    BusinessObject businessObject = null;
                    if (z) {
                        businessObject = ((AlbumDetail) obj).getAlbum();
                        businessObject.setBusinessObjType(BusinessObjectType.Albums);
                    } else if (obj instanceof PlaylistDetail) {
                        businessObject = ((PlaylistDetail) obj).getPlaylist();
                        businessObject.setBusinessObjType(BusinessObjectType.Playlists);
                    }
                    try {
                        if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof GaanaMiniPurchaseFragment) {
                            ((GaanaActivity) this.mContext).popBackStackImmediate();
                        }
                    } catch (Exception unused2) {
                    }
                    albumDetailsMaterialListing.setArguments(AlbumDetailsMaterialListing.a(businessObject, "download"));
                    ((GaanaActivity) this.mContext).displayFragment(albumDetailsMaterialListing);
                }
            }
        }
        if (this.f != null) {
            this.f.onResponse(this.a);
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        ((BaseActivity) this.mContext).hideProgressDialog();
        super.onErrorResponse(volleyError);
        aj.a().a(this.mContext, volleyError.getMessage());
        if (this.f != null) {
            this.f.onResponse(null);
        }
    }

    public void onDestroyView() {
        if (!(this.b == null || this.b.getParent() == null)) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
        ag.a(this.mContext).a(null);
    }

    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
        if (this.a != null) {
            String e = this.a.b().e();
            String d = this.a.b().d();
            this.a.b().c();
            this.a.b().a();
            URLManager uRLManager = new URLManager();
            StringBuilder stringBuilder;
            if (d.equalsIgnoreCase(c.b)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(com.constants.c.o);
                stringBuilder.append(e);
                uRLManager.a(stringBuilder.toString());
                uRLManager.a(AlbumDetail.class);
                uRLManager.b(Boolean.valueOf(true));
                i.a().a(uRLManager, "ItemDetail", (b) this, (com.android.volley.i.a) this);
            } else if (d.equalsIgnoreCase(c.a)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(com.constants.c.p);
                stringBuilder.append(e);
                uRLManager.a(stringBuilder.toString());
                uRLManager.a(PlaylistDetail.class);
                uRLManager.b(Boolean.valueOf(true));
                i.a().a(uRLManager, "ItemDetail", (b) this, (com.android.volley.i.a) this);
            } else if (d.equalsIgnoreCase(c.d)) {
                d();
            }
            ((GaanaActivity) this.mContext).updateSideBar();
            this.f = null;
        }
    }

    public void onFailure(String str, String str2) {
        aj.a().a(this.mContext, str);
    }

    public GaanaMiniProduct b() {
        return this.a;
    }

    private void d() {
        d.a(new Runnable() {
            public void run() {
                BusinessObject a = o.a().a(Constants.a(BusinessObjectType.Artists, GaanaMiniPurchaseFragment.this.d, false));
                if (a.getArrListBusinessObj() == null || a.getArrListBusinessObj().size() <= 0) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            ((BaseActivity) GaanaMiniPurchaseFragment.this.mContext).hideProgressDialog();
                            aj.a().a(GaanaMiniPurchaseFragment.this.mContext, GaanaMiniPurchaseFragment.this.mContext.getResources().getString(R.string.some_error_occured));
                        }
                    });
                    return;
                }
                a = (BusinessObject) a.getArrListBusinessObj().get(0);
                a.setBusinessObjType(BusinessObjectType.Artists);
                URLManager uRLManager = new URLManager();
                String str = "";
                Iterator it = GaanaMiniPurchaseFragment.this.mAppState.getCurrentUser().getUserSubscriptionData().getGaanaMiniSubDetails().iterator();
                while (it.hasNext()) {
                    GaanaMiniSubDetails gaanaMiniSubDetails = (GaanaMiniSubDetails) it.next();
                    if (gaanaMiniSubDetails.getEntityId().equals(GaanaMiniPurchaseFragment.this.d)) {
                        str = gaanaMiniSubDetails.getPlaylistId();
                        break;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(com.constants.c.p);
                stringBuilder.append(str);
                uRLManager.a(stringBuilder.toString());
                uRLManager.a(PlaylistDetail.class);
                uRLManager.b(Boolean.valueOf(true));
                uRLManager.a(BusinessObjectType.PlaylistDetails);
                BusinessObject a2 = o.a().a(uRLManager);
                if (a2 != null) {
                    PlaylistDetail playlistDetail = (PlaylistDetail) a2;
                    BusinessObject playlist = playlistDetail.getPlaylist();
                    if (playlist != null) {
                        playlist.setBusinessObjType(BusinessObjectType.Playlists);
                        PlaylistSyncManager.getInstance().addToGaanaTable(playlist, playlistDetail.getArrListBusinessObj());
                        DownloadManager.c().a(playlist, new ArrayList());
                    }
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        BaseGaanaFragment artistDetailsMaterialListing;
                        Bundle a;
                        ((BaseActivity) GaanaMiniPurchaseFragment.this.mContext).hideProgressDialog();
                        if (Constants.Z) {
                            artistDetailsMaterialListing = new ArtistDetailsMaterialListing();
                            a = ArtistDetailsMaterialListing.a(a, "mini_purchase");
                        } else {
                            artistDetailsMaterialListing = new RevampedDetailListing();
                            a = RevampedDetailListing.a(a, "mini_purchase", REVAMPED_DETAIL_TYPE.ARTIST.getNumVal());
                        }
                        artistDetailsMaterialListing.setArguments(a);
                        try {
                            if (((GaanaActivity) GaanaMiniPurchaseFragment.this.mContext).getCurrentFragment() instanceof GaanaMiniPurchaseFragment) {
                                ((GaanaActivity) GaanaMiniPurchaseFragment.this.mContext).popBackStackImmediate();
                            }
                        } catch (Exception unused) {
                        }
                        ((GaanaActivity) GaanaMiniPurchaseFragment.this.mContext).displayFragment(artistDetailsMaterialListing);
                    }
                });
            }
        });
    }
}
