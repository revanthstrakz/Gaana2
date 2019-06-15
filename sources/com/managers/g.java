package com.managers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.constants.Constants.SortOrder;
import com.dynamicview.a;
import com.dynamicview.f;
import com.fragments.BaseGaanaFragment;
import com.fragments.ListingFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Items;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.DownloadSongsItemView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.utilities.Util;
import java.util.ArrayList;

public class g {
    private f a;

    private g() {
        b();
    }

    public static g a() {
        return new g();
    }

    public ArrayList<BaseItemView> a(Context context, BaseGaanaFragment baseGaanaFragment) {
        return b(context, baseGaanaFragment);
    }

    private ArrayList<BaseItemView> b(Context context, BaseGaanaFragment baseGaanaFragment) {
        return new ArrayList();
    }

    public void b() {
        this.a = a.d();
    }

    public static void a(Context context, @Nullable BaseGaanaFragment baseGaanaFragment, @Nullable Bundle bundle) {
        if (Util.j(context)) {
            GaanaApplication instance = GaanaApplication.getInstance();
            BaseGaanaFragment listingFragment = new ListingFragment();
            listingFragment.a(SortOrder.Default);
            listingFragment.setAnimateFragmentElements(true);
            ListingParams listingParams = new ListingParams();
            listingParams.e(false);
            listingParams.g(false);
            listingParams.d(false);
            listingParams.f(true);
            listingParams.o(false);
            listingParams.b(false);
            listingParams.i(false);
            listingParams.k(false);
            listingParams.j(false);
            listingParams.m(false);
            listingParams.b(PAGE_SORCE_NAME.CURATED_DOWNLOAD_SUGGESTION.name());
            ListingButton listingButton = new ListingButton();
            listingButton.c(DownloadSongsItemView.class.getName());
            listingButton.c(false);
            listingButton.a("CURATED_DOWNLOADS_SUGGESTIONS");
            listingButton.a(true);
            URLManager uRLManager = new URLManager();
            uRLManager.a(Items.class);
            uRLManager.a("https://apiv2.gaana.com/home/curated/download");
            uRLManager.c(Boolean.valueOf(true));
            uRLManager.b(Boolean.valueOf(false));
            listingButton.a(uRLManager);
            listingParams.a(listingButton);
            listingFragment.a(listingParams);
            listingFragment.a(baseGaanaFragment);
            ListingComponents listingComponents = new ListingComponents();
            new ArrayList().add(listingButton);
            instance.setListingComponents(listingComponents);
            ((GaanaActivity) context).displayFragment(listingFragment);
            u.a().a("Suggestion");
            return;
        }
        aj.a().a(context, context.getResources().getString(R.string.error_msg_no_connection));
    }
}
