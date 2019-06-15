package com.facebook.ads;

import com.facebook.ads.internal.s.a.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class ExtraHints {
    private final String a;

    public static class Builder {
        private HashMap<HintType, String> a = new HashMap();

        public ExtraHints build() {
            return new ExtraHints(this.a);
        }

        public Builder contentUrl(String str) {
            if (str == null) {
                return this;
            }
            this.a.put(HintType.CONTENT_URL, str);
            return this;
        }

        public Builder extraData(String str) {
            if (str == null) {
                return this;
            }
            this.a.put(HintType.EXTRA_DATA, str);
            return this;
        }

        public Builder keywords(List<Keyword> list) {
            if (list == null || list.isEmpty()) {
                return this;
            }
            ArrayList arrayList = new ArrayList();
            for (Keyword a : list) {
                arrayList.add(a.a);
                if (arrayList.size() == 5) {
                    break;
                }
            }
            this.a.put(HintType.KEYWORDS, s.a(arrayList, ";"));
            return this;
        }
    }

    public enum HintType {
        KEYWORDS("keywords"),
        CONTENT_URL("content_url"),
        EXTRA_DATA("extra_data");
        
        private String a;

        private HintType(String str) {
            this.a = str;
        }
    }

    public enum Keyword {
        ACCESSORIES("accessories"),
        ART_HISTORY("art_history"),
        AUTOMOTIVE("automotive"),
        BEAUTY("beauty"),
        BIOLOGY("biology"),
        BOARD_GAMES("board_games"),
        BUSINESS_SOFTWARE("business_software"),
        BUYING_SELLING_HOMES("buying_selling_homes"),
        CATS("cats"),
        CELEBRITIES("celebrities"),
        CLOTHING("clothing"),
        COMIC_BOOKS("comic_books"),
        DESKTOP_VIDEO("desktop_video"),
        DOGS("dogs"),
        EDUCATION("education"),
        EMAIL("email"),
        ENTERTAINMENT("entertainment"),
        FAMILY_PARENTING("family_parenting"),
        FASHION("fashion"),
        FINE_ART("fine_art"),
        FOOD_DRINK("food_drink"),
        FRENCH_CUISINE("french_cuisine"),
        GOVERNMENT("government"),
        HEALTH_FITNESS("health_fitness"),
        HOBBIES("hobbies"),
        HOME_GARDEN("home_garden"),
        HUMOR("humor"),
        INTERNET_TECHNOLOGY("internet_technology"),
        LARGE_ANIMALS("large_animals"),
        LAW("law"),
        LEGAL_ISSUES("legal_issues"),
        LITERATURE("literature"),
        MARKETING("marketing"),
        MOVIES("movies"),
        MUSIC("music"),
        NEWS("news"),
        PERSONAL_FINANCE("personal_finance"),
        PETS("pets"),
        PHOTOGRAPHY("photography"),
        POLITICS("politics"),
        REAL_ESTATE("real_estate"),
        ROLEPLAYING_GAMES("roleplaying_games"),
        SCIENCE("science"),
        SHOPPING("shopping"),
        SOCIETY("society"),
        SPORTS("sports"),
        TECHNOLOGY("technology"),
        TELEVISION("television"),
        TRAVEL("travel"),
        VIDEO_COMPUTER_GAMES("video_computer_games");
        
        private String a;

        private Keyword(String str) {
            this.a = str;
        }
    }

    private ExtraHints(HashMap<HintType, String> hashMap) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Entry entry : hashMap.entrySet()) {
            try {
                jSONObject2.put(((HintType) entry.getKey()).a, entry.getValue());
            } catch (JSONException unused) {
            }
        }
        try {
            jSONObject.put("hints", jSONObject2);
        } catch (JSONException unused2) {
        }
        this.a = jSONObject.toString();
    }

    public String getHints() {
        return this.a;
    }
}
