package com.til.colombia.android.vast;

import android.graphics.Point;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.service.AdSize;
import com.til.colombia.android.vast.VastCompanionResource.CreativeType;
import com.til.colombia.android.vast.VastCompanionResource.Type;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class g {
    public static final String a = "StaticResource";
    public static final String b = "IFrameResource";
    public static final String c = "HTMLResource";
    public static final String d = "Companion";
    public static final String e = "CompanionAds";
    public static final String f = "Icons";
    public static final String g = "Icon";
    public static final String h = "IconViewTracking";
    public static final String i = "TrackingEvents";
    public static final String j = "Tracking";
    public static final String k = "CompanionClickThrough";
    public static final String l = "IconClicks";
    public static final String m = "IconClickThrough";
    public static final String n = "CompanionClickTracking";
    public static final String o = "IconClickTracking";
    public static final String p = "creativeType";
    public static final String q = "width";
    public static final String r = "height";
    private static final int s = 300;
    private static final int t = 250;

    public static List<VastCompanionAdConfig> a(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, null, str);
        while (xmlPullParser.next() != 3) {
            str = xmlPullParser.getName();
            if (xmlPullParser.getEventType() == 2) {
                if (str != null && str.equals(d)) {
                    arrayList.add(b(xmlPullParser, d));
                } else if (str == null || !str.equals(g)) {
                    VASTXmlParser.skip(xmlPullParser);
                } else {
                    arrayList.add(b(xmlPullParser, g));
                }
            }
        }
        return arrayList;
    }

    private static VastCompanionAdConfig b(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, str);
        if (str.equals(g)) {
            xmlPullParser.getAttributeValue(null, "program");
            xmlPullParser.getAttributeValue(null, "xPosition");
            xmlPullParser.getAttributeValue(null, "yPosition");
            xmlPullParser.getAttributeValue(null, "duration");
            xmlPullParser.getAttributeValue(null, "offset");
            xmlPullParser.getAttributeValue(null, "apiFramework");
        }
        int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue(null, "width"));
        int parseInt2 = Integer.parseInt(xmlPullParser.getAttributeValue(null, "height"));
        VastCompanionResource vastCompanionResource = null;
        String str2 = vastCompanionResource;
        List list = str2;
        List list2 = list;
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                String readText;
                VastCompanionResource vastCompanionResource2;
                VastTrackingEvent vastTrackingEvent;
                VastTrackingEvent vastTrackingEvent2;
                if (name != null && name.equals(a)) {
                    name = xmlPullParser.getAttributeValue(null, p);
                    if (!h.a(name) && (VastCompanionResource.VALID_IMAGE_TYPES.contains(name.toLowerCase()) || VastCompanionResource.VALID_APPLICATION_TYPES.contains(name.toLowerCase()))) {
                        xmlPullParser.require(2, null, a);
                        readText = VASTXmlParser.readText(xmlPullParser);
                        xmlPullParser.require(3, null, a);
                        if (name.equalsIgnoreCase("image/gif")) {
                            vastCompanionResource2 = new VastCompanionResource(readText, Type.STATIC_RESOURCE, CreativeType.GIF, parseInt, parseInt2);
                        } else if (name.equalsIgnoreCase("application/x-javascript")) {
                            vastCompanionResource2 = new VastCompanionResource(readText, Type.STATIC_RESOURCE, CreativeType.JAVASCRIPT, parseInt, parseInt2);
                        } else {
                            vastCompanionResource2 = new VastCompanionResource(readText, Type.STATIC_RESOURCE, CreativeType.IMAGE, parseInt, parseInt2);
                        }
                    }
                } else if (name != null && name.equals(b)) {
                    xmlPullParser.require(2, null, b);
                    readText = VASTXmlParser.readText(xmlPullParser);
                    xmlPullParser.require(3, null, b);
                    vastCompanionResource2 = new VastCompanionResource(readText, Type.IFRAME_RESOURCE, CreativeType.IMAGE, parseInt, parseInt2);
                } else if (name != null && name.equals(c)) {
                    xmlPullParser.require(2, null, c);
                    readText = VASTXmlParser.readText(xmlPullParser);
                    xmlPullParser.require(3, null, c);
                    vastCompanionResource2 = new VastCompanionResource(readText, Type.HTML_RESOURCE, CreativeType.IMAGE, parseInt, parseInt2);
                } else if (name != null && name.equals(l)) {
                    xmlPullParser.require(2, null, l);
                    while (xmlPullParser.next() != 3) {
                        if (xmlPullParser.getEventType() == 2) {
                            name = xmlPullParser.getName();
                            if (name != null && name.equalsIgnoreCase(m)) {
                                xmlPullParser.require(2, null, name);
                                str2 = VASTXmlParser.readText(xmlPullParser);
                                xmlPullParser.require(3, null, name);
                            } else if (name == null || !name.equalsIgnoreCase(o)) {
                                VASTXmlParser.skip(xmlPullParser);
                            } else {
                                xmlPullParser.require(2, null, name);
                                vastTrackingEvent = new VastTrackingEvent(VASTXmlParser.readText(xmlPullParser), false, false);
                                if (list == null) {
                                    list = new ArrayList();
                                }
                                list.add(vastTrackingEvent);
                                xmlPullParser.require(3, null, name);
                            }
                        }
                    }
                } else if (name != null && name.equalsIgnoreCase(k)) {
                    xmlPullParser.require(2, null, name);
                    str2 = VASTXmlParser.readText(xmlPullParser);
                    xmlPullParser.require(3, null, name);
                } else if (name != null && name.equalsIgnoreCase(n)) {
                    xmlPullParser.require(2, null, name);
                    vastTrackingEvent = new VastTrackingEvent(VASTXmlParser.readText(xmlPullParser), false, false);
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(vastTrackingEvent);
                    xmlPullParser.require(3, null, name);
                } else if (name != null && name.equalsIgnoreCase(i)) {
                    xmlPullParser.require(2, null, i);
                    while (xmlPullParser.next() != 3) {
                        if (xmlPullParser.getEventType() == 2) {
                            name = xmlPullParser.getName();
                            if (name == null || !name.equals(j)) {
                                VASTXmlParser.skip(xmlPullParser);
                            } else {
                                name = xmlPullParser.getAttributeValue(null, "event");
                                if (!h.a(name) && name.equalsIgnoreCase("creativeView")) {
                                    xmlPullParser.require(2, null, j);
                                    vastTrackingEvent2 = new VastTrackingEvent(VASTXmlParser.readText(xmlPullParser), false, false);
                                    if (list2 == null) {
                                        list2 = new ArrayList();
                                    }
                                    list2.add(vastTrackingEvent2);
                                    xmlPullParser.require(3, null, j);
                                }
                            }
                        }
                    }
                } else if (name == null || !name.equalsIgnoreCase(h)) {
                    VASTXmlParser.skip(xmlPullParser);
                } else {
                    xmlPullParser.require(2, null, h);
                    name = xmlPullParser.getName();
                    if (name != null && name.equals(h)) {
                        xmlPullParser.require(2, null, h);
                        vastTrackingEvent2 = new VastTrackingEvent(VASTXmlParser.readText(xmlPullParser), false, false);
                        if (list2 == null) {
                            list2 = new ArrayList();
                        }
                        list2.add(vastTrackingEvent2);
                        xmlPullParser.require(3, null, h);
                    }
                }
                vastCompanionResource = r13;
            }
        }
        if (str.equals(g)) {
            return new IconConfig(parseInt, parseInt2, vastCompanionResource, str2, list, list2);
        }
        return new VastCompanionAdConfig(parseInt, parseInt2, vastCompanionResource, str2, list, list2);
    }

    public static VastCompanionAdConfig a(List<VastCompanionAdConfig> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        double d = Double.POSITIVE_INFINITY;
        Point point = null;
        VastCompanionAdConfig vastCompanionAdConfig = null;
        VastCompanionResource vastCompanionResource = null;
        for (Type type : Type.values()) {
            for (VastCompanionAdConfig vastCompanionAdConfig2 : list) {
                int width = vastCompanionAdConfig2.getWidth();
                int height = vastCompanionAdConfig2.getHeight();
                if (width >= 300 && height >= 250) {
                    VastCompanionResource vastResource = vastCompanionAdConfig2.getVastResource();
                    if (vastResource != null && type == vastResource.getType()) {
                        Point a = CommonUtil.a(width, height);
                        VastCompanionResource vastCompanionResource2 = new VastCompanionResource(vastResource.getResource(), vastResource.getType(), vastResource.getCreativeType(), a.x, a.y);
                        double b = CommonUtil.b(width, height);
                        if (b < d) {
                            point = a;
                            vastCompanionAdConfig = vastCompanionAdConfig2;
                            d = b;
                            vastCompanionResource = vastCompanionResource2;
                        }
                    }
                }
            }
        }
        return vastCompanionResource != null ? new VastCompanionAdConfig(point.x, point.y, vastCompanionResource, vastCompanionAdConfig.getClickThroughUrl(), vastCompanionAdConfig.getClickTrackers(), vastCompanionAdConfig.getCreativeViewTrackers()) : null;
    }

    public static VastCompanionAdConfig a(List<VastCompanionAdConfig> list, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        if (list == null || list.size() == 0) {
            return null;
        }
        double d = Double.POSITIVE_INFINITY;
        Point point = null;
        VastCompanionAdConfig vastCompanionAdConfig = null;
        VastCompanionResource vastCompanionResource = null;
        for (Type type : Type.values()) {
            Point point2;
            VastCompanionAdConfig vastCompanionAdConfig2;
            for (VastCompanionAdConfig vastCompanionAdConfig3 : list) {
                int width = vastCompanionAdConfig3.getWidth();
                int height = vastCompanionAdConfig3.getHeight();
                if (width >= 300 && height >= 250) {
                    VastCompanionResource vastResource = vastCompanionAdConfig3.getVastResource();
                    if (vastResource != null) {
                        point2 = point;
                        if (type == vastResource.getType()) {
                            point = CommonUtil.a(width, height, i3, i4);
                            vastCompanionAdConfig2 = vastCompanionAdConfig;
                            VastCompanionResource vastCompanionResource2 = new VastCompanionResource(vastResource.getResource(), vastResource.getType(), vastResource.getCreativeType(), point.x, point.y);
                            double b = CommonUtil.b(width, height, i3, i4);
                            if (b < d) {
                                d = b;
                                vastCompanionAdConfig = vastCompanionAdConfig3;
                                vastCompanionResource = vastCompanionResource2;
                            }
                            point = point2;
                            vastCompanionAdConfig = vastCompanionAdConfig2;
                        }
                        vastCompanionAdConfig2 = vastCompanionAdConfig;
                        point = point2;
                        vastCompanionAdConfig = vastCompanionAdConfig2;
                    }
                }
                point2 = point;
                vastCompanionAdConfig2 = vastCompanionAdConfig;
                point = point2;
                vastCompanionAdConfig = vastCompanionAdConfig2;
            }
            point2 = point;
            vastCompanionAdConfig2 = vastCompanionAdConfig;
        }
        return vastCompanionResource != null ? new VastCompanionAdConfig(point.x, point.y, vastCompanionResource, vastCompanionAdConfig.getClickThroughUrl(), vastCompanionAdConfig.getClickTrackers(), vastCompanionAdConfig.getCreativeViewTrackers()) : null;
    }

    public static VastCompanionAdConfig a(List<VastCompanionAdConfig> list, ArrayList<AdSize> arrayList) {
        ArrayList<AdSize> arrayList2 = arrayList;
        if (arrayList2 == null || arrayList.size() == 0) {
            List<VastCompanionAdConfig> list2 = list;
            return a(list);
        }
        for (Type type : Type.values()) {
            for (VastCompanionAdConfig vastCompanionAdConfig : list) {
                VastCompanionResource vastResource = vastCompanionAdConfig.getVastResource();
                if (vastResource != null && type == vastResource.getType()) {
                    int width = vastCompanionAdConfig.getWidth();
                    int height = vastCompanionAdConfig.getHeight();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        AdSize adSize = (AdSize) it.next();
                        int width2 = adSize.getWidth();
                        int height2 = adSize.getHeight();
                        if (CommonUtil.c(width, height, width2, height2)) {
                            Point a = CommonUtil.a(width, height, width2, height2);
                            return new VastCompanionAdConfig(a.x, a.y, new VastCompanionResource(vastResource.getResource(), vastResource.getType(), vastResource.getCreativeType(), a.x, a.y), vastCompanionAdConfig.getClickThroughUrl(), vastCompanionAdConfig.getClickTrackers(), vastCompanionAdConfig.getCreativeViewTrackers(), adSize);
                        }
                    }
                    continue;
                }
            }
        }
        return a(list, ((AdSize) arrayList2.get(0)).getWidth(), ((AdSize) arrayList2.get(0)).getHeight());
    }
}
