package com.constants;

import com.dynamicview.DynamicHomeFragment;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.DiscoverFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GridActivityFragment;
import com.fragments.ListingFragment;
import com.fragments.LocalMediaFragment;
import com.fragments.MyMusicFragment;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerFragmentV4;
import com.fragments.PlayerRadioFragmentV2;
import com.fragments.PlayerRadioFragmentV4;
import com.fragments.RadioActivityFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.fragments.SettingsFragment;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.gaana.fragments.BaseFragment;
import com.managers.PlayerManager.PlayerType;
import com.services.d;

public class a {

    public interface a {
        String getFragmentStackName();

        void onFragmentScroll();
    }

    public static String a(BaseGaanaFragment baseGaanaFragment) {
        if (baseGaanaFragment instanceof DynamicHomeFragment) {
            return "HOME";
        }
        if (baseGaanaFragment instanceof RadioActivityFragment) {
            return "RADIO";
        }
        if (baseGaanaFragment instanceof DiscoverFragment) {
            return CARD.DISCOVER;
        }
        if (baseGaanaFragment instanceof DiscoverDetailFragment) {
            return "DISCOVER_DETAILS";
        }
        if (baseGaanaFragment instanceof MyMusicFragment) {
            return "MYMUSIC";
        }
        if (baseGaanaFragment instanceof ListingFragment) {
            return "MY_PLAYLIST";
        }
        if (baseGaanaFragment instanceof FavoritesFragment) {
            return "FAVORITE";
        }
        if (baseGaanaFragment instanceof DownloadDetailsFragment) {
            return "DOWNLOAD";
        }
        if (baseGaanaFragment instanceof LocalMediaFragment) {
            return "MUSIC_ON_MY_PHONE";
        }
        if (baseGaanaFragment instanceof SettingsFragment) {
            return "SETTINGS";
        }
        if (baseGaanaFragment instanceof ArtistDetailsMaterialListing) {
            return "DETAIL_LISTING";
        }
        if (baseGaanaFragment instanceof RadioDetailsMaterialListing) {
            return "DETAIL_GRID";
        }
        return baseGaanaFragment instanceof GridActivityFragment ? "GRID" : "HOME";
    }

    public static BaseFragment a(PlayerType playerType) {
        BaseFragment playerFragmentV4;
        Constants.H = d.a().b("PREFERENCE_UJ_PLAYER_CAROUSEL_ENABLED", 1, false);
        if (Constants.H == 1) {
            if (playerType == PlayerType.GAANA) {
                playerFragmentV4 = new PlayerFragmentV4();
            } else {
                playerFragmentV4 = new PlayerRadioFragmentV4();
            }
        } else if (playerType == PlayerType.GAANA) {
            playerFragmentV4 = new PlayerFragmentV2();
        } else {
            playerFragmentV4 = new PlayerRadioFragmentV2();
        }
        playerFragmentV4.setPlayerType(playerType);
        return playerFragmentV4;
    }

    public static com.fragments.BaseGaanaFragment a(java.lang.String r1) {
        /*
        r0 = r1.hashCode();
        switch(r0) {
            case -906336856: goto L_0x0030;
            case 3208415: goto L_0x0026;
            case 108270587: goto L_0x001c;
            case 1434631203: goto L_0x0012;
            case 1522043897: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x003a;
    L_0x0008:
        r0 = "mymusic";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x003a;
    L_0x0010:
        r1 = 3;
        goto L_0x003b;
    L_0x0012:
        r0 = "settings";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x003a;
    L_0x001a:
        r1 = 4;
        goto L_0x003b;
    L_0x001c:
        r0 = "radio";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x003a;
    L_0x0024:
        r1 = 1;
        goto L_0x003b;
    L_0x0026:
        r0 = "home";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x003a;
    L_0x002e:
        r1 = 0;
        goto L_0x003b;
    L_0x0030:
        r0 = "search";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x003a;
    L_0x0038:
        r1 = 2;
        goto L_0x003b;
    L_0x003a:
        r1 = -1;
    L_0x003b:
        switch(r1) {
            case 0: goto L_0x005c;
            case 1: goto L_0x0056;
            case 2: goto L_0x0050;
            case 3: goto L_0x004a;
            case 4: goto L_0x0044;
            default: goto L_0x003e;
        };
    L_0x003e:
        r1 = new com.dynamicview.DynamicHomeFragment;
        r1.<init>();
        goto L_0x0061;
    L_0x0044:
        r1 = new com.fragments.SettingsFragment;
        r1.<init>();
        goto L_0x0061;
    L_0x004a:
        r1 = new com.fragments.MyMusicFragment;
        r1.<init>();
        goto L_0x0061;
    L_0x0050:
        r1 = new com.fragments.SearchEnchancedFragment;
        r1.<init>();
        goto L_0x0061;
    L_0x0056:
        r1 = new com.fragments.MoreRadioActivityFragment;
        r1.<init>();
        goto L_0x0061;
    L_0x005c:
        r1 = new com.dynamicview.DynamicHomeFragment;
        r1.<init>();
    L_0x0061:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.constants.a.a(java.lang.String):com.fragments.BaseGaanaFragment");
    }
}
