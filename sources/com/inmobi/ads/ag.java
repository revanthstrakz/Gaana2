package com.inmobi.ads;

import android.text.TextUtils;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class ag {
    private static final String a = "ag";

    ag() {
    }

    static void a(Document document, bx bxVar) {
        List a = bu.a(document, "Verification");
        if (a != null) {
            String a2;
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = a.iterator();
            while (true) {
                int i = 0;
                if (!it.hasNext()) {
                    break;
                }
                Element element = (Element) ((Node) it.next());
                NodeList childNodes = element.getChildNodes();
                if ("Moat".equals(element.getAttribute("vendor")) && childNodes.getLength() > 0) {
                    while (i < childNodes.getLength()) {
                        Node item = childNodes.item(i);
                        if ("ViewableImpression".equals(item.getNodeName())) {
                            a2 = a(item);
                            if (!TextUtils.isEmpty(a2)) {
                                stringBuilder.append(a2);
                                stringBuilder.append(";");
                                InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
                                String str = a;
                                StringBuilder stringBuilder2 = new StringBuilder("Found Moat Verification tag in VAST with value : ");
                                stringBuilder2.append(a2);
                                Logger.a(internalLogLevel, str, stringBuilder2.toString());
                            }
                        }
                        i++;
                    }
                }
            }
            if (stringBuilder.length() != 0) {
                InternalLogLevel internalLogLevel2 = InternalLogLevel.INTERNAL;
                a2 = a;
                StringBuilder stringBuilder3 = new StringBuilder("Moat VastIDs in VAST : ");
                stringBuilder3.append(stringBuilder.toString());
                Logger.a(internalLogLevel2, a2, stringBuilder3.toString());
                bxVar.a(new NativeTracker(stringBuilder.toString(), 0, TrackerEventType.TRACKER_EVENT_TYPE_MOAT, null));
            }
        }
    }

    private static String a(Node node) {
        StringWriter stringWriter = new StringWriter();
        try {
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
        } catch (TransformerException e) {
            InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
            String str = a;
            StringBuilder stringBuilder = new StringBuilder("Exception while converting Moat node to string : ");
            stringBuilder.append(e.getMessage());
            Logger.a(internalLogLevel, str, stringBuilder.toString());
        }
        return stringWriter.toString();
    }
}
