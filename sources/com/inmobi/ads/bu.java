package com.inmobi.ads;

import android.os.SystemClock;
import android.webkit.URLUtil;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpStatusCodes;
import com.inmobi.a.n;
import com.inmobi.ads.c.j;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.network.e;
import com.til.colombia.android.vast.g;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class bu {
    private static final String a = "bu";
    private static final Map<String, TrackerEventType> d;
    private j b;
    private final String c = "Progressive";
    private int e = 0;
    private bx f;

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
        hashMap.put("Error", TrackerEventType.TRACKER_EVENT_TYPE_ERROR);
        d.put("Impression", TrackerEventType.TRACKER_EVENT_TYPE_RENDER);
        d.put("ClickTracking", TrackerEventType.TRACKER_EVENT_TYPE_CLICK);
        d.put("creativeView", TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW);
        d.put("start", TrackerEventType.TRACKER_EVENT_TYPE_PLAY);
        d.put("firstQuartile", TrackerEventType.TRACKER_EVENT_TYPE_Q1);
        d.put("midpoint", TrackerEventType.TRACKER_EVENT_TYPE_Q2);
        d.put("thirdQuartile", TrackerEventType.TRACKER_EVENT_TYPE_Q3);
        d.put("complete", TrackerEventType.TRACKER_EVENT_TYPE_Q4);
        d.put("mute", TrackerEventType.TRACKER_EVENT_TYPE_MUTE);
        d.put("unmute", TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE);
        d.put("pause", TrackerEventType.TRACKER_EVENT_TYPE_PAUSE);
        d.put("resume", TrackerEventType.TRACKER_EVENT_TYPE_RESUME);
        d.put("fullscreen", TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN);
        d.put("exitFullscreen", TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN);
    }

    public bu(j jVar) {
        this.b = jVar;
        this.f = new bx(this.b);
    }

    private static d b(String str) {
        c cVar = new c(HttpMethods.GET, str);
        cVar.u = false;
        cVar.t = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        d a = new e(cVar).a();
        try {
            n.a().a(cVar.g());
            n.a().b(a.c());
            n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
        } catch (Exception e) {
            new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return a;
    }

    private static int c(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            a.a().a(new com.inmobi.commons.core.e.a(e));
            return 0;
        }
    }

    static Node a(Node node, String str) {
        List b = b(node, str);
        return b.size() > 0 ? (Node) b.get(0) : null;
    }

    private static List<Node> b(Node node, String str) {
        ArrayList arrayList = new ArrayList();
        if (node == null || str == null) {
            return arrayList;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == (short) 1 && str.equals(item.getNodeName())) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    private static String c(Node node, String str) {
        return a(a(node, str));
    }

    private static Node a(List<Node> list, String str) {
        Node node = null;
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            node = a((Node) list.get(i), str);
            if (node != null) {
                break;
            }
        }
        return node;
    }

    static List<Node> a(Document document, String str) {
        if (document == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        NodeList elementsByTagName = document.getElementsByTagName(str);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            arrayList.add(elementsByTagName.item(i));
        }
        if (elementsByTagName.getLength() == 0) {
            return null;
        }
        return arrayList;
    }

    static String a(Node node) {
        if (node == null) {
            return null;
        }
        String textContent;
        try {
            textContent = node.getTextContent();
        } catch (DOMException e) {
            new StringBuilder("Error getting node value; ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
            textContent = null;
        }
        if (textContent != null) {
            return textContent.trim();
        }
        return null;
    }

    public final bx a(String str) {
        if (str == null || str.isEmpty()) {
            a((int) HttpStatusCodes.STATUS_CODE_SEE_OTHER);
            return this.f;
        }
        try {
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
            Node a = a((Node) parse, "VAST");
            if (a == null) {
                a(101);
                return this.f;
            }
            a = a(a, "Ad");
            if (a == null) {
                a((int) HttpStatusCodes.STATUS_CODE_SEE_OTHER);
                return this.f;
            }
            Node a2 = a(a, "Wrapper");
            List a3;
            if (a2 != null) {
                this.e++;
                if (this.e > this.b.a) {
                    a((int) HttpStatusCodes.STATUS_CODE_FOUND);
                    return this.f;
                } else if (b(a2)) {
                    a3 = a(parse, g.i);
                    if (a3 != null) {
                        a(b((Node) a3.get(0), g.j));
                    }
                    x.a(parse, this.f);
                    ag.a(parse, this.f);
                    a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, a(parse, "ClickTracking"));
                    str = c(a2, "VASTAdTagURI");
                    if (str == null) {
                        a(101);
                        return this.f;
                    } else if (str == null || str.isEmpty()) {
                        a((int) HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES);
                    } else {
                        d b = b(str);
                        if (b.a()) {
                            a((int) HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY);
                        } else {
                            a(b.b());
                        }
                    }
                } else {
                    a(101);
                    return this.f;
                }
            }
            a2 = a(a, "InLine");
            if (a2 == null) {
                a(101);
                return this.f;
            } else if (b(a2)) {
                a2 = a(a2, "Creatives");
                if (a2 == null) {
                    a(101);
                    return this.f;
                }
                a3 = b(a2, "Creative");
                if (a3.isEmpty()) {
                    a(101);
                    return this.f;
                }
                a = a(a3, "Linear");
                if (a == null) {
                    a((int) HttpStatusCodes.STATUS_CODE_CREATED);
                    return this.f;
                }
                List b2 = b(a, "Duration");
                if (b2.isEmpty()) {
                    a(101);
                    return this.f;
                }
                String a4 = a((Node) b2.get(0));
                if (a4 == null || a4.isEmpty() || !a4.matches("\\d*:[0-5][0-9]:[0-5][0-9](:[0-9][0-9][0-9])?")) {
                    a(101);
                    return this.f;
                }
                this.f.b = a4;
                Node a5 = a(a, "MediaFiles");
                if (a5 == null) {
                    a(101);
                    return this.f;
                }
                Node a6 = a(a, "VideoClicks");
                this.f.c = c(a6, "ClickThrough");
                a(TrackerEventType.TRACKER_EVENT_TYPE_CLICK, b(a6, "ClickTracking"));
                a(b(a(a, g.i), g.j));
                x.a(parse, this.f);
                ag.a(parse, this.f);
                a3 = b(a5, "MediaFile");
                if (a3.isEmpty()) {
                    a(401);
                    return this.f;
                }
                Node node;
                String a7;
                String attribute;
                String attribute2;
                int c;
                c.c cVar = this.b.d;
                for (int i = 0; i < a3.size(); i++) {
                    node = (Element) a3.get(i);
                    a7 = a(node);
                    if (a7 != null && !a7.trim().isEmpty()) {
                        attribute = node.getAttribute("delivery");
                        attribute2 = node.getAttribute("type");
                        c = c(node.getAttribute("bitrate"));
                        if ((!cVar.a || c > 0) && attribute != null && attribute.trim().equalsIgnoreCase("Progressive")) {
                            ArrayList arrayList = this.b.e;
                            if (attribute2 != null) {
                                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                    if (attribute2.equalsIgnoreCase((String) arrayList.get(i2))) {
                                        this.f.a.add(new bv(a7, attribute, attribute2, c));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.f.a.isEmpty()) {
                    a(403);
                }
                List b3 = b(a2, "Creative");
                if (b3.isEmpty()) {
                    a(101);
                    return this.f;
                }
                a2 = a(b3, g.e);
                if (a2 == null) {
                    return this.f;
                }
                String a8;
                StringBuilder stringBuilder;
                List<Node> a9 = a(parse, "CompanionAdTracking");
                HashMap hashMap = new HashMap();
                if (a9 != null) {
                    for (Node a52 : a9) {
                        a52 = a(a52, g.i);
                        if (a52 != null) {
                            NamedNodeMap attributes = a52.getAttributes();
                            a6 = (attributes == null || attributes.getLength() <= 0) ? null : attributes.getNamedItem("id");
                            if (a6 != null) {
                                String nodeValue = a6.getNodeValue();
                                for (Node node2 : b(a52, g.j)) {
                                    if ("closeEndCard".equals(((Element) node2).getAttribute("event"))) {
                                        a8 = a(node2);
                                        if (URLUtil.isValidUrl(a8)) {
                                            hashMap.put(nodeValue, new NativeTracker(a8, 0, TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE, null));
                                        } else {
                                            stringBuilder = new StringBuilder("Malformed URL: ");
                                            stringBuilder.append(a8);
                                            stringBuilder.append("; Discarding this tracker");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                List b4 = b(a2, g.d);
                int i3 = 0;
                int i4 = i3;
                while (i3 < b4.size()) {
                    a6 = (Element) b4.get(i3);
                    c = c(a6.getAttribute("width"));
                    int c2 = c(a6.getAttribute("height"));
                    if (!(c == 0 || c2 == 0)) {
                        attribute = c(a6, g.k);
                        if (!URLUtil.isValidUrl(attribute)) {
                            attribute = null;
                        }
                        attribute2 = a6.hasAttribute("id") ? a6.getAttribute("id") : null;
                        bt btVar = new bt(c, c2, attribute, attribute2);
                        node2 = a(a6, g.a);
                        if (node2 != null) {
                            a7 = a(node2);
                            a8 = ((Element) node2).getAttribute(g.p);
                            if (!(a8 == null || a8.trim().isEmpty())) {
                                if (d(a8)) {
                                    btVar.a(new a(1, a7));
                                } else {
                                    i4 = 1;
                                }
                            }
                        }
                        node2 = a(a6, g.c);
                        if (node2 != null) {
                            btVar.a(new a(2, a(node2)));
                        }
                        node2 = a(a6, g.b);
                        if (node2 != null) {
                            btVar.a(new a(3, a(node2)));
                        }
                        List list = btVar.c;
                        if (!(list == null || list.size() == 0)) {
                            a(b(a6, g.n), btVar);
                            for (Node node22 : b(a(a6, g.i), g.j)) {
                                a7 = ((Element) node22).getAttribute("event");
                                if (d.containsKey(a7)) {
                                    a8 = a(node22);
                                    if (URLUtil.isValidUrl(a8)) {
                                        btVar.a(new NativeTracker(a8, 0, (TrackerEventType) d.get(a7), null));
                                    } else {
                                        stringBuilder = new StringBuilder("Malformed URL: ");
                                        stringBuilder.append(a8);
                                        stringBuilder.append("; Discarding this tracker");
                                    }
                                }
                            }
                            if (attribute2 != null && hashMap.containsKey(attribute2)) {
                                btVar.a((NativeTracker) hashMap.get(attribute2));
                            }
                            this.f.e.add(btVar);
                        }
                    }
                    i3++;
                }
                i3 = this.f.e.size();
                if (i3 == 0 && i4 != 0) {
                    b(604);
                } else if (b4.size() > 0 && i3 == 0) {
                    b(600);
                }
            } else {
                a(101);
                return this.f;
            }
            return this.f;
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            a(100);
            a.a().a(new com.inmobi.commons.core.e.a(e));
            return this.f;
        }
    }

    private static boolean d(String str) {
        int size = bt.f.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase((String) bt.f.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static void a(List<Node> list, bt btVar) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = (Node) list.get(i);
                if ((short) 1 == node.getNodeType()) {
                    String a = a(node);
                    if (URLUtil.isValidUrl(a)) {
                        btVar.a(new NativeTracker(a, 0, TrackerEventType.TRACKER_EVENT_TYPE_CLICK, null));
                    } else {
                        StringBuilder stringBuilder = new StringBuilder("Malformed URL: ");
                        stringBuilder.append(a);
                        stringBuilder.append("; Discarding this tracker");
                    }
                }
            }
        }
    }

    private boolean b(Node node) {
        if (node == null) {
            return false;
        }
        a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, b(node, "Error"));
        List b = b(node, "Impression");
        if (b.isEmpty()) {
            return false;
        }
        return a(TrackerEventType.TRACKER_EVENT_TYPE_RENDER, b);
    }

    private void a(List<Node> list) {
        if (list != null && list.size() != 0) {
            for (Node node : list) {
                String attribute = ((Element) node).getAttribute("event");
                if (d.containsKey(attribute)) {
                    a((TrackerEventType) d.get(attribute), node);
                }
            }
        }
    }

    private boolean a(TrackerEventType trackerEventType, List<Node> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = (Node) list.get(i);
                if (node.getNodeType() == (short) 1 && !a(trackerEventType, node)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean a(TrackerEventType trackerEventType, Node node) {
        String a = a(node);
        if (URLUtil.isValidUrl(a)) {
            this.f.a(new NativeTracker(a, 0, trackerEventType, null));
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder("Malformed URL ");
        stringBuilder.append(a);
        stringBuilder.append(" Discarding this tracker");
        return trackerEventType != TrackerEventType.TRACKER_EVENT_TYPE_RENDER;
    }

    private void a(int i) {
        this.f.f = i;
        b(i);
    }

    private void b(int i) {
        com.inmobi.rendering.a.c a = com.inmobi.rendering.a.c.a();
        Map hashMap = new HashMap();
        hashMap.put("[ERRORCODE]", String.valueOf(i));
        for (NativeTracker nativeTracker : this.f.d) {
            if (nativeTracker.b == TrackerEventType.TRACKER_EVENT_TYPE_ERROR) {
                a.a(com.inmobi.commons.core.utilities.d.a(nativeTracker.a, hashMap), nativeTracker.c);
            }
        }
    }
}
