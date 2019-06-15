package com.til.colombia.android.vast;

import android.util.Log;
import com.til.colombia.android.commons.b;
import com.til.colombia.android.vast.VastCompanionResource.CreativeType;
import com.til.colombia.android.vast.VastCompanionResource.Type;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class h {
    public static final String a = "pre";
    public static final String b = "post";
    private static final String c = "AudioFile";

    public static VastSponsoredAdConfig a(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        VastSponsoredAdConfig vastSponsoredAdConfig = new VastSponsoredAdConfig();
        xmlPullParser.require(2, null, VASTXmlParser.VAST_EXTN_TAG);
        String attributeValue = xmlPullParser.getAttributeValue(null, "sessionExpiry");
        try {
            if (b.b(attributeValue)) {
                vastSponsoredAdConfig.setAdFreeDuration(b.c(attributeValue).intValue());
            }
        } catch (NumberFormatException unused) {
            Log.e("VastXmlParser", String.format("Failed to parse skipoffset %s", new Object[]{attributeValue}));
            vastSponsoredAdConfig.setAdFreeDuration(-1);
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                attributeValue = xmlPullParser.getName();
                if (attributeValue != null && attributeValue.equals(g.d)) {
                    attributeValue = xmlPullParser.getAttributeValue(null, "type");
                    if (attributeValue != null && attributeValue.equalsIgnoreCase(a)) {
                        a(xmlPullParser, vastSponsoredAdConfig, a);
                    } else if (attributeValue == null || !attributeValue.equalsIgnoreCase(b)) {
                        VASTXmlParser.skip(xmlPullParser);
                    } else {
                        a(xmlPullParser, vastSponsoredAdConfig, b);
                    }
                }
            }
        }
        return vastSponsoredAdConfig;
    }

    private static void a(XmlPullParser xmlPullParser, VastSponsoredAdConfig vastSponsoredAdConfig, String str) throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        VastSponsoredAdConfig vastSponsoredAdConfig2 = vastSponsoredAdConfig;
        String str2 = str;
        VastSponsoredCompanion vastSponsoredCompanion = new VastSponsoredCompanion();
        xmlPullParser2.require(2, null, g.d);
        int parseInt = Integer.parseInt(xmlPullParser2.getAttributeValue(null, "width"));
        int parseInt2 = Integer.parseInt(xmlPullParser2.getAttributeValue(null, "height"));
        VastCompanionResource vastCompanionResource = null;
        String str3 = vastCompanionResource;
        List list = str3;
        List list2 = list;
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                String readText;
                VastCompanionResource vastCompanionResource2;
                VastTrackingEvent vastTrackingEvent;
                if (name != null && name.equals(g.a)) {
                    name = xmlPullParser2.getAttributeValue(null, g.p);
                    if (!com.til.colombia.android.internal.a.h.a(name) && (VastCompanionResource.VALID_IMAGE_TYPES.contains(name.toLowerCase()) || VastCompanionResource.VALID_APPLICATION_TYPES.contains(name.toLowerCase()))) {
                        xmlPullParser2.require(2, null, g.a);
                        readText = VASTXmlParser.readText(xmlPullParser);
                        xmlPullParser2.require(3, null, g.a);
                        vastCompanionResource2 = new VastCompanionResource(readText, Type.STATIC_RESOURCE, CreativeType.IMAGE, parseInt, parseInt2);
                    }
                } else if (name != null && name.equals(g.b)) {
                    xmlPullParser2.require(2, null, g.b);
                    readText = VASTXmlParser.readText(xmlPullParser);
                    xmlPullParser2.require(3, null, g.b);
                    vastCompanionResource2 = new VastCompanionResource(readText, Type.IFRAME_RESOURCE, CreativeType.IMAGE, parseInt, parseInt2);
                } else if (name != null && name.equals(g.c)) {
                    xmlPullParser2.require(2, null, g.c);
                    readText = VASTXmlParser.readText(xmlPullParser);
                    xmlPullParser2.require(3, null, g.c);
                    vastCompanionResource2 = new VastCompanionResource(readText, Type.HTML_RESOURCE, CreativeType.IMAGE, parseInt, parseInt2);
                } else if (name != null && name.equals(c)) {
                    xmlPullParser2.require(2, null, c);
                    vastSponsoredCompanion.setAudioSrc(VASTXmlParser.readText(xmlPullParser));
                    xmlPullParser2.require(3, null, c);
                } else if (name != null && name.equalsIgnoreCase(g.k)) {
                    xmlPullParser2.require(2, null, g.k);
                    str3 = VASTXmlParser.readText(xmlPullParser);
                    xmlPullParser2.require(3, null, g.k);
                } else if (name != null && name.equalsIgnoreCase(g.n)) {
                    xmlPullParser2.require(2, null, g.n);
                    vastTrackingEvent = new VastTrackingEvent(VASTXmlParser.readText(xmlPullParser), false, false);
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(vastTrackingEvent);
                    xmlPullParser2.require(3, null, g.n);
                } else if (name == null || !name.equalsIgnoreCase(g.i)) {
                    VASTXmlParser.skip(xmlPullParser);
                } else {
                    xmlPullParser2.require(2, null, g.i);
                    while (xmlPullParser.next() != 3) {
                        if (xmlPullParser.getEventType() == 2) {
                            name = xmlPullParser.getName();
                            if (name == null || !name.equals(g.j)) {
                                VASTXmlParser.skip(xmlPullParser);
                            } else {
                                name = xmlPullParser2.getAttributeValue(null, "event");
                                if (!com.til.colombia.android.internal.a.h.a(name) && name.equalsIgnoreCase("creativeView")) {
                                    xmlPullParser2.require(2, null, g.j);
                                    vastTrackingEvent = new VastTrackingEvent(VASTXmlParser.readText(xmlPullParser), false, false);
                                    List arrayList = list2 == null ? new ArrayList() : list2;
                                    arrayList.add(vastTrackingEvent);
                                    xmlPullParser2.require(3, null, g.j);
                                    list2 = arrayList;
                                }
                            }
                        }
                    }
                }
                vastCompanionResource = r17;
            }
        }
        vastSponsoredCompanion.setCompanionResource(new VastCompanionAdConfig(parseInt, parseInt2, vastCompanionResource, str3, list, list2).getVastResource());
        if (str2.equalsIgnoreCase(a)) {
            vastSponsoredAdConfig2.setPreAudioCompanion(vastSponsoredCompanion);
            return;
        }
        if (str2.equalsIgnoreCase(b)) {
            vastSponsoredAdConfig2.setPostAudioCompanion(vastSponsoredCompanion);
        }
    }
}
