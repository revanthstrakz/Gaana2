package com.dynamicview;

import android.support.annotation.NonNull;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f.a;
import com.gaana.R;
import com.utilities.Util;

public class h {
    public static int a(@NonNull a aVar) {
        if (aVar.m().equals(DynamicViewType.double_scroll.name()) || aVar.m().equals(DynamicViewType.hor_scroll.name()) || aVar.m().equals(DynamicViewType.staggered_grid.name()) || aVar.m().equals(DynamicViewType.download.name()) || aVar.m().equals(DynamicViewType.chameleon.name())) {
            return Util.c(aVar.e());
        }
        if (aVar.m().equals(DynamicViewType.cir_hor_scroll.name())) {
            return Util.e(aVar.e());
        }
        if (aVar.m().equals(DynamicViewType.carousel.name())) {
            return d(aVar);
        }
        if (aVar.m().equals(DynamicViewType.user_activity.name())) {
            return Util.c(aVar.e());
        }
        if (aVar.m().equals(DynamicViewType.user_radio_activity.name())) {
            return Util.c(aVar.e());
        }
        return aVar.m().equals(DynamicViewType.tag_radio.name()) ? R.layout.tags_explore : -1;
    }

    public static int b(@NonNull a aVar) {
        if (aVar.m().equals(DynamicViewType.double_scroll.name()) || aVar.m().equals(DynamicViewType.hor_scroll.name())) {
            return Util.d(aVar.e());
        }
        return aVar.m().equals(DynamicViewType.cir_hor_scroll.name()) ? Util.e(aVar.e()) : -1;
    }

    public static int c(a aVar) {
        if (aVar.e() == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || aVar.e() == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
            return R.layout.item_playlist_grid_ad_200x200;
        }
        if (aVar.e() == VIEW_SIZE.SCROLL_MEDIUM_SQAUE.getNumVal()) {
            return R.layout.item_playlist_grid_ad_120x120;
        }
        return aVar.e() == VIEW_SIZE.CARD_BIG_SQUARE.getNumVal() ? R.layout.item_playlist_grid_ad_221x221 : R.layout.item_playlist_grid_ad_140x140;
    }

    public static int d(a aVar) {
        return aVar.e() == VIEW_SIZE.CAROUSEL_VIEW_XL_SQUARE.getNumVal() ? R.layout.carousel_view_item_large_square : R.layout.carousel_view_item;
    }

    public static int a(int i) {
        return i == VIEW_SIZE.CAROUSEL_VIEW_XL_SQUARE.getNumVal() ? R.layout.carousel_view_item_large_square : R.layout.carousel_view_item;
    }

    public static int b(int i) {
        return i == VIEW_SIZE.CAROUSEL_VIEW_XL_SQUARE.getNumVal() ? R.layout.carousel_video_view_item_large_square : R.layout.carousel_video_view_item;
    }
}
