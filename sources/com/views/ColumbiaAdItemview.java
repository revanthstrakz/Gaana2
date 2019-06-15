package com.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.collapsible_header.ListingFragmentMaterial;
import com.collapsible_header.SongParallexListingFragment;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.GridActivityFragment;
import com.fragments.ListingFragment;
import com.fragments.PersonaDedicationFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.revampeddetail.manager.RevampDetailAdManager;
import com.gaana.view.BaseItemView;
import com.google.android.gms.ads.AdListener;
import com.managers.ColombiaManager;
import com.managers.ColombiaManager.ADSTATUS;
import com.managers.ColombiaManager.a;
import com.managers.URLManager.BusinessObjectType;
import com.managers.e;
import com.managers.h;
import com.managers.t;
import com.services.d;
import com.services.l.c;
import com.til.colombia.android.service.ColombiaAdManager.AD_NTWK;
import com.til.colombia.android.service.ColombiaAdRequest.Builder;
import com.til.colombia.android.service.Item;
import com.views.ColombiaMediationAdView.AdViewType;
import java.util.HashMap;

public class ColumbiaAdItemview extends BaseItemView implements OnClickListener {
    View a;
    private BaseGaanaFragment b;
    private HashMap<Integer, ADSTATUS> c;
    private int d;
    private int e;
    private c f;
    private boolean g;
    private String h;
    private boolean i = false;

    public void setGridItem(boolean z) {
        this.i = z;
    }

    public void setTransparentLayout(boolean z) {
        this.g = z;
    }

    public void setCustomGridAdListener(c cVar) {
        this.f = cVar;
    }

    public void setAdType(String str) {
        this.h = str;
    }

    public ColumbiaAdItemview(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.b = baseGaanaFragment;
        this.c = new HashMap();
        this.d = d.a().b();
        this.e = this.mContext.getResources().getDimensionPixelSize(R.dimen.item_two_line_bar_height);
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        if (i != RevampDetailAdManager.REVAMPED_DFP_AD) {
            return LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad, viewGroup, false);
        }
        return LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad_list, viewGroup, false);
    }

    private boolean a(int i) {
        ADSTATUS adstatus = (ADSTATUS) this.c.get(Integer.valueOf(i));
        return adstatus == null || adstatus == ADSTATUS.FAILED || adstatus == ADSTATUS.REFRESH;
    }

    public View getPopulatedView(int i, View view, ViewGroup viewGroup, BusinessObject businessObject) {
        final int i2 = i;
        final View view2 = view;
        ViewGroup viewGroup2 = viewGroup;
        BusinessObject businessObject2 = businessObject;
        if (!a(i) || (this.b.getActivity() != null && ((GaanaActivity) this.b.getActivity()).isSlidingPanelExpanded())) {
            return view2;
        }
        if (this.i) {
            this.a = LayoutInflater.from(this.mContext).inflate(R.layout.colombia_detail_grid_content_ad, viewGroup2, false);
            this.e = this.mContext.getResources().getDimensionPixelSize(R.dimen.home_first_item_view_width_height);
        } else if (this.g) {
            this.a = LayoutInflater.from(this.mContext).inflate(R.layout.colombia_detail_list_content_ad_one_line, viewGroup2, false);
            this.a.findViewById(R.id.rl_parent_list_content).setBackgroundColor(0);
        } else {
            this.a = LayoutInflater.from(this.mContext).inflate(R.layout.colombia_detail_list_content_ad, viewGroup2, false);
        }
        String simpleName = this.b.getClass().getSimpleName();
        Builder f = ColombiaManager.b().f();
        boolean z = true;
        if (!TextUtils.isEmpty(e.Q) && a(businessObject2, this.b)) {
            if (!(view2 == null || view2.findViewById(R.id.llNativeAdSlot) == null || view2.findViewById(R.id.llNativeAdSlot).getVisibility() == 0)) {
                final int i3 = view.getLayoutParams().height;
                view.getLayoutParams().height = 1;
                view.requestLayout();
                h.a().a(this.mContext, e.Q, 31, view2, false, new AdListener() {
                    public void onAdFailedToLoad(int i) {
                        view2.setVisibility(8);
                        view2.getLayoutParams().height = 0;
                        view2.requestLayout();
                        ColumbiaAdItemview.this.c.put(Integer.valueOf(i2), ADSTATUS.FAILED);
                    }

                    public void onAdLoaded() {
                        view2.setVisibility(0);
                        view2.getLayoutParams().height = i3;
                        view2.requestLayout();
                        ColumbiaAdItemview.this.c.put(Integer.valueOf(i2), ADSTATUS.LOADED);
                    }
                });
            }
            return view2;
        } else if (f == null) {
            return view2;
        } else {
            String currentSponsoredOccassion = GaanaApplication.getInstance().getCurrentSponsoredOccassion();
            if (currentSponsoredOccassion != null) {
                f.addCustomAudience("OC", currentSponsoredOccassion);
            } else if (GaanaApplication.getInstance().getListingComponents() != null && (GaanaApplication.getInstance().getListingComponents().a() instanceof Artist)) {
                f.addCustomAudience("AR", GaanaApplication.getInstance().getListingComponents().a().getBusinessObjId());
            } else if (businessObject2 instanceof Album) {
                f.addCustomAudience("AL", businessObject.getBusinessObjId());
            } else if (businessObject2 instanceof Playlist) {
                f.addCustomAudience("PL", businessObject.getBusinessObjId());
            }
            f.addAdSize(this.d, this.e);
            if (((this.b instanceof AlbumDetailsMaterialListing) || (this.b instanceof RevampedDetailListing)) && businessObject2 != null ? businessObject.getBusinessObjType() == BusinessObjectType.Albums : (this.b instanceof ListingFragment) && businessObject2 != null && businessObject.getBusinessObjType() == BusinessObjectType.Tracks && this.b.getTitle().equalsIgnoreCase("favorites")) {
                z = false;
            }
            boolean z2 = this.b instanceof PersonaDedicationFragment;
            currentSponsoredOccassion = "";
            if (businessObject2 != null) {
                if (businessObject2 instanceof Album) {
                    currentSponsoredOccassion = "AlbumDetail";
                    t.a().a("AL", businessObject.getBusinessObjId());
                } else if (businessObject2 instanceof Playlist) {
                    currentSponsoredOccassion = "PlaylistDetail";
                    t.a().a("PL", businessObject.getBusinessObjId());
                } else if (businessObject2 instanceof Track) {
                    currentSponsoredOccassion = "Track";
                    t.a().a("TR", businessObject.getBusinessObjId());
                } else if (businessObject2 instanceof Radio) {
                    currentSponsoredOccassion = "RadioDetail";
                    t.a().a("RD", businessObject.getBusinessObjId());
                } else if (businessObject2 instanceof Artist) {
                    currentSponsoredOccassion = "ArtistDetail";
                    t.a().a("AR", businessObject.getBusinessObjId());
                }
            }
            Builder builder = f;
            int i4 = i2;
            View view3 = view2;
            t.a().a(this.i, z, builder, i4, this.mContext, a(this.b), view3, this.a, simpleName, new a() {
                public void onItemLoaded(Item item) {
                    if (item.getAdNetwork() == AD_NTWK.FACEBOOK || item.getAdNetwork() == AD_NTWK.GOOGLE) {
                        ColombiaMediationAdView colombiaMediationAdView = new ColombiaMediationAdView(ColumbiaAdItemview.this.mContext);
                        ColumbiaAdItemview.this.a = colombiaMediationAdView.a(item, AdViewType.M_320x60);
                        ((LinearLayout) view2).removeAllViews();
                        ((LinearLayout) view2).addView(ColumbiaAdItemview.this.a);
                    }
                    if (ColumbiaAdItemview.this.f != null) {
                        ColumbiaAdItemview.this.f.onAdLoadedatPosition(true, i2);
                    }
                    ColumbiaAdItemview.this.c.put(Integer.valueOf(i2), ADSTATUS.LOADED);
                }

                public void onItemRequestFailed(Exception exception) {
                    if (ColumbiaAdItemview.this.f != null) {
                        ColumbiaAdItemview.this.f.onAdLoadedatPosition(false, i2);
                    }
                    ColumbiaAdItemview.this.c.put(Integer.valueOf(i2), ADSTATUS.FAILED);
                }
            }, currentSponsoredOccassion);
            this.c.put(Integer.valueOf(i), ADSTATUS.LOADING);
            return view2;
        }
    }

    public void onClick(View view) {
        super.onClick(view);
    }

    public void a() {
        if (this.c != null) {
            for (Object put : this.c.keySet()) {
                this.c.put(put, ADSTATUS.REFRESH);
            }
        }
    }

    public long a(BaseGaanaFragment baseGaanaFragment) {
        if (baseGaanaFragment instanceof ListingFragment) {
            CharSequence d = ((ListingFragment) baseGaanaFragment).d();
            if ((baseGaanaFragment.getParentFragment() instanceof RevampedDetailListing) && e.j != 0) {
                d = String.valueOf(e.j);
            }
            if (!TextUtils.isEmpty(d)) {
                return Long.valueOf(d).longValue();
            }
        } else if (baseGaanaFragment instanceof SongParallexListingFragment) {
            String k = ((SongParallexListingFragment) baseGaanaFragment).k();
            if (!TextUtils.isEmpty(k)) {
                return Long.valueOf(k).longValue();
            }
        } else if (baseGaanaFragment instanceof GridActivityFragment) {
            CharSequence a;
            if (this.h == null || !this.h.equalsIgnoreCase("banner")) {
                a = ((GridActivityFragment) baseGaanaFragment).a();
            } else {
                a = ((GridActivityFragment) baseGaanaFragment).b();
            }
            if (!TextUtils.isEmpty(a)) {
                return Long.valueOf(a).longValue();
            }
        } else if (baseGaanaFragment instanceof DiscoverDetailFragment) {
            return e.i;
        } else {
            if ((baseGaanaFragment instanceof AlbumDetailsMaterialListing) || (baseGaanaFragment instanceof GaanaSpecialDetailsMaterialListing) || (baseGaanaFragment instanceof RevampedDetailListing)) {
                return e.c;
            }
            if (baseGaanaFragment instanceof ListingFragmentMaterial) {
                return e.j;
            }
            if (baseGaanaFragment instanceof PersonaDedicationFragment) {
                return e.q;
            }
        }
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(BusinessObject businessObject, BaseGaanaFragment baseGaanaFragment) {
        return (this.i || this.g || (!(baseGaanaFragment instanceof ListingFragment) && !(baseGaanaFragment instanceof GaanaSpecialDetailsMaterialListing) && ((businessObject == null || (!(businessObject instanceof Album) && !(businessObject instanceof Playlist) && !(businessObject instanceof Track))) && (GaanaApplication.getInstance().getListingComponents() == null || !(GaanaApplication.getInstance().getListingComponents().a() instanceof Artist))))) ? false : true;
    }
}
