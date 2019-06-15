package com.dynamicview;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.constants.Constants;
import com.constants.Constants.ACTION_TYPE;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.facebook.share.internal.ShareConstants;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.SplashScreenActivity;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaVideoItem;
import com.gaana.view.AutoPlayVideoView;
import com.gaana.view.BaseItemView;
import com.gaana.view.ContentCardView;
import com.gaana.view.GaanaYourYearView;
import com.gaana.view.HomeSettingsItemView;
import com.gaana.view.ImageCardView;
import com.gaana.view.OccassionSeeAllView;
import com.gaana.view.SectionTitleViews;
import com.gaana.view.SponsoredOccasionCardView;
import com.gaana.view.TwoItemGridView;
import com.gaana.view.UpgradeHomeView;
import com.gaana.view.header.HomeCarouselView;
import com.gaana.view.header.OccasionHeaderView;
import com.gaana.view.item.GaanaIn2016View;
import com.gaana.view.item.OccasionDynamicScrollView;
import com.gaanavideo.FullScreenVideoPlayerActivity;
import com.i.i;
import com.i.j;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.f;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.l.ag;
import com.services.l.s;
import com.utilities.d;
import java.util.ArrayList;
import java.util.List;

public class c implements a, b<Object> {
    private static c a;
    private d b;
    private ag c;
    private String d = "";
    private String e = "";
    private boolean f;
    private String g;
    private String h;
    private List<f.a> i = new ArrayList();

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public void a(ag agVar, String str, String str2, boolean z) {
        this.c = agVar;
        this.h = str;
        URLManager b = b(str);
        if (str2 != null) {
            b.a(Integer.parseInt(str2));
        }
        b.c(Boolean.valueOf(z));
        c();
        this.g = str.substring(str.lastIndexOf("/") + 1, str.length());
        i.a().a(b, "OccasionDynamicApi", (b) this, (a) this);
    }

    public String b() {
        return this.g;
    }

    public void c() {
        j.a().a((Object) "OccasionDynamicApi");
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (this.c != null) {
            this.c.onOccasionError();
        }
    }

    public void onResponse(Object obj) {
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (dVar != null && dVar.a() != null && dVar.a().size() > 0) {
                this.b = dVar;
                if (this.c != null) {
                    this.c.onOccasionResponse();
                }
            } else if (this.c != null) {
                this.c.onOccasionError();
            }
        } else if (obj instanceof e) {
            e eVar = (e) obj;
            if (eVar.d() != null && eVar.d().size() > 0) {
                this.i.clear();
                for (int i = 0; i < eVar.d().size(); i++) {
                    if (!TextUtils.isEmpty(((e.a) eVar.d().get(i)).b())) {
                        this.i.add(new f.a(((e.a) eVar.d().get(i)).b(), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
                    }
                    List a = ((e.a) eVar.d().get(i)).a();
                    if (a != null) {
                        this.i.addAll(a);
                    }
                }
                if (this.c != null) {
                    this.c.onOccasionResponse();
                }
            } else if (this.c != null) {
                this.c.onOccasionError();
            }
        }
    }

    private URLManager b(String str) {
        URLManager uRLManager = new URLManager();
        if (str.contains("occasion")) {
            uRLManager.a(BusinessObjectType.DynamicViewCategories);
        } else {
            uRLManager.a(BusinessObjectType.DynamicViewSections);
        }
        uRLManager.a(str);
        return uRLManager;
    }

    public int d() {
        return this.b != null ? this.b.c() : -1;
    }

    public String e() {
        return this.b != null ? this.b.l() : null;
    }

    public String f() {
        return this.b != null ? this.b.h() : null;
    }

    public d g() {
        return this.b;
    }

    public String h() {
        if (this.b == null) {
            return null;
        }
        return Constants.a(this.b.b());
    }

    public String i() {
        if (this.b == null) {
            return null;
        }
        return Constants.b(this.b.b());
    }

    public List<BaseItemView> a(Context context, BaseGaanaFragment baseGaanaFragment) {
        return b(context, baseGaanaFragment);
    }

    private List<BaseItemView> b(Context context, BaseGaanaFragment baseGaanaFragment) {
        Context context2 = context;
        BaseGaanaFragment baseGaanaFragment2 = baseGaanaFragment;
        ArrayList arrayList;
        String m;
        int f;
        SectionTitleViews sectionTitleViews;
        StringBuilder stringBuilder;
        if (TextUtils.isEmpty(this.h) || !this.h.contains("occasion")) {
            arrayList = new ArrayList();
            if (this.i == null) {
                return arrayList;
            }
            for (f.a aVar : this.i) {
                if (aVar != null) {
                    m = aVar.m();
                    f = aVar.f();
                    if (!TextUtils.isEmpty(m)) {
                        if (m.equals(DynamicViewType.hor_scroll.name()) || m.equals(DynamicViewType.double_scroll.name()) || m.equals(DynamicViewType.cir_hor_scroll.name())) {
                            arrayList.add(new OccasionDynamicScrollView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.spon_oc.name())) {
                            arrayList.add(new SponsoredOccasionCardView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.card.name()) && aVar.p() != null && aVar.p().equalsIgnoreCase(ShareConstants.TITLE)) {
                            arrayList.add(new OccasionHeaderView(context2, baseGaanaFragment2, aVar, false));
                        } else if (m.equals(DynamicViewType.gaana_year_2016.name())) {
                            arrayList.add(new GaanaIn2016View(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.ad.name())) {
                            arrayList.add(new UpgradeHomeView(context2, baseGaanaFragment2, aVar, Constants.eb));
                        } else if (m.equals(DynamicViewType.gaana_video.name())) {
                            arrayList.add(new GaanaYourYearView(context2, baseGaanaFragment2, aVar, f == ACTION_TYPE.GAANA_VIDEO.getNumVal()));
                        } else if (m.equals(DynamicViewType.your_year.name())) {
                            arrayList.add(new GaanaYourYearView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.card.name())) {
                            arrayList.add(new ImageCardView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.content_card.name())) {
                            arrayList.add(new ContentCardView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.toggle.name())) {
                            this.f = true;
                        } else if (m.equals(DynamicViewType.carousel.name())) {
                            arrayList.add(new HomeCarouselView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.text_card.name())) {
                            arrayList.add(new ImageCardView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.inline_video.name())) {
                            arrayList.add(new AutoPlayVideoView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.section_heading.name())) {
                            sectionTitleViews = new SectionTitleViews(context2, baseGaanaFragment2, aVar);
                            sectionTitleViews.setHeading(true);
                            arrayList.add(sectionTitleViews);
                        } else if (m.equals(DynamicViewType.settings.name())) {
                            arrayList.add(new HomeSettingsItemView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.section_heading_occasion.name())) {
                            arrayList.add(new SectionTitleViews(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.view_more.name())) {
                            arrayList.add(new OccassionSeeAllView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.grid_2x2.name())) {
                            arrayList.add(new TwoItemGridView(context2, baseGaanaFragment2, aVar));
                        } else if (m.equals(DynamicViewType.user_radio_activity.name())) {
                            arrayList.add(new DynamicUserActivityView(context2, baseGaanaFragment2, aVar));
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.d);
                        stringBuilder.append("1");
                        this.d = stringBuilder.toString();
                        this.e = "Radio";
                    }
                }
            }
            return arrayList;
        }
        this.f = false;
        arrayList = new ArrayList();
        if (this.b == null) {
            return arrayList;
        }
        ArrayList<f.a> arrayList2 = new ArrayList();
        int size = (this.b == null || this.b.a() == null) ? 0 : this.b.a().size();
        if (size > 0 && !TextUtils.isEmpty(this.b.b())) {
            arrayList2.add(new f.a(this.b.b(), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
        }
        int i = 0;
        while (i < size) {
            if (!TextUtils.isEmpty(((d.a) this.b.a().get(i)).e())) {
                arrayList2.add(new f.a(((d.a) this.b.a().get(i)).e(), "url", DynamicViewType.section_heading_occasion.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
            }
            if (((d.a) this.b.a().get(i)).c() != null) {
                arrayList2.addAll(((d.a) this.b.a().get(i)).c());
            }
            if (((d.a) this.b.a().get(i)).d() && !TextUtils.isEmpty(((d.a) this.b.a().get(i)).b())) {
                f.a aVar2 = new f.a(((d.a) this.b.a().get(i)).b(), ((d.a) this.b.a().get(i)).a(), DynamicViewType.view_more.name(), ((d.a) this.b.a().get(i)).a(), "source_name", "ad_code", "0", "140");
                aVar2.i(this.b.b());
                arrayList2.add(aVar2);
            }
            i++;
        }
        for (f.a aVar3 : arrayList2) {
            if (aVar3 != null) {
                m = aVar3.m();
                f = aVar3.f();
                if (!TextUtils.isEmpty(m)) {
                    if (m.equals(DynamicViewType.hor_scroll.name()) || m.equals(DynamicViewType.double_scroll.name()) || m.equals(DynamicViewType.cir_hor_scroll.name())) {
                        arrayList.add(new OccasionDynamicScrollView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.spon_oc.name())) {
                        arrayList.add(new SponsoredOccasionCardView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.card.name()) && aVar3.p().equalsIgnoreCase(ShareConstants.TITLE)) {
                        arrayList.add(new OccasionHeaderView(context2, baseGaanaFragment2, aVar3, false));
                    } else if (m.equals(DynamicViewType.gaana_year_2016.name())) {
                        arrayList.add(new GaanaIn2016View(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.ad.name())) {
                        arrayList.add(new UpgradeHomeView(context2, baseGaanaFragment2, aVar3, Constants.eb));
                    } else if (m.equals(DynamicViewType.gaana_video.name())) {
                        arrayList.add(new GaanaYourYearView(context2, baseGaanaFragment2, aVar3, f == ACTION_TYPE.GAANA_VIDEO.getNumVal()));
                    } else if (m.equals(DynamicViewType.your_year.name())) {
                        arrayList.add(new GaanaYourYearView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.card.name())) {
                        arrayList.add(new ImageCardView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.content_card.name())) {
                        arrayList.add(new ContentCardView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.toggle.name())) {
                        this.f = true;
                    } else if (m.equals(DynamicViewType.carousel.name())) {
                        arrayList.add(new HomeCarouselView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.text_card.name())) {
                        arrayList.add(new ImageCardView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.inline_video.name())) {
                        arrayList.add(new AutoPlayVideoView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.section_heading.name())) {
                        m = Constants.l ? this.b.f() : this.b.e();
                        if (TextUtils.isEmpty(m)) {
                            sectionTitleViews = new SectionTitleViews(context2, baseGaanaFragment2, aVar3);
                            sectionTitleViews.setHeading(true);
                            arrayList.add(sectionTitleViews);
                        } else {
                            aVar3.a(m);
                            arrayList.add(new OccasionHeaderView(context2, baseGaanaFragment2, aVar3, true));
                        }
                    } else if (m.equals(DynamicViewType.settings.name())) {
                        arrayList.add(new HomeSettingsItemView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.section_heading_occasion.name())) {
                        arrayList.add(new SectionTitleViews(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.view_more.name())) {
                        arrayList.add(new OccassionSeeAllView(context2, baseGaanaFragment2, aVar3));
                    } else if (m.equals(DynamicViewType.grid_2x2.name())) {
                        arrayList.add(new TwoItemGridView(context2, baseGaanaFragment2, aVar3));
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.d);
                    stringBuilder.append("1");
                    this.d = stringBuilder.toString();
                    this.e = "OP";
                }
            }
        }
        return arrayList;
    }

    public String j() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public boolean k() {
        return this.f;
    }

    public boolean a(final Context context) {
        boolean z = false;
        if (this.b == null) {
            return false;
        }
        ArrayList<f.a> arrayList = new ArrayList();
        int size = (this.b == null || this.b.a() == null) ? 0 : this.b.a().size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new f.a(((d.a) this.b.a().get(i)).e(), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
            if (((d.a) this.b.a().get(i)).c() != null) {
                arrayList.addAll(((d.a) this.b.a().get(i)).c());
            }
        }
        for (f.a aVar : arrayList) {
            if (aVar.m().equals(DynamicViewType.gaana_video.name())) {
                URLManager uRLManager = new URLManager();
                uRLManager.a(aVar.l());
                uRLManager.a(GaanaVideoItem.class);
                i.a().a(new s() {
                    public void onRetreivalComplete(BusinessObject businessObject) {
                        if (businessObject != null && (businessObject instanceof GaanaVideoItem)) {
                            GaanaVideoItem gaanaVideoItem = (GaanaVideoItem) businessObject;
                            String videoStreamingUrl = gaanaVideoItem.getVideoStreamingUrl();
                            String videoShareUrl = gaanaVideoItem.getVideoShareUrl();
                            Intent intent;
                            if (TextUtils.isEmpty(videoStreamingUrl)) {
                                com.services.c.a(context).b(context, false);
                            } else if (context instanceof SplashScreenActivity) {
                                intent = new Intent(context, GaanaActivity.class);
                                intent.putExtra("share_url", videoShareUrl);
                                intent.putExtra("video_url", videoStreamingUrl);
                                intent.putExtra("LAUNCH_YEAR_VIDEO_PLAYER_ACTIVITY", true);
                                context.startActivity(intent);
                            } else if (context instanceof GaanaActivity) {
                                if (GaanaMusicService.t()) {
                                    o.a(context, PauseReasons.MEDIA_BUTTON_TAP);
                                    Constants.dc = true;
                                }
                                if (f.v().w()) {
                                    f.v().F();
                                    Constants.dc = true;
                                }
                                if (d.g()) {
                                    intent = new Intent(context, VideoPlayerActivityTwo.class);
                                } else {
                                    intent = new Intent(context, FullScreenVideoPlayerActivity.class);
                                }
                                intent.setAction("com.google.android.exoplayer.demo.action.VIEW");
                                intent.putExtra("share_url", videoShareUrl);
                                intent.putExtra("video_url", videoStreamingUrl);
                                ((GaanaActivity) context).startActivityForResult(intent, 1001);
                            } else {
                                com.services.c.a(context).b(context, false);
                            }
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        com.services.c.a(context).b(context, false);
                    }
                }, uRLManager);
                z = true;
                break;
            }
        }
        return z;
    }

    public String l() {
        return this.e;
    }
}
