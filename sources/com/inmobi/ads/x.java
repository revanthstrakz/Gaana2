package com.inmobi.ads;

import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class x {
    private static final String a = "x";

    x() {
    }

    static void a(Document document, bx bxVar) {
        List<Node> a = bu.a(document, "AVID");
        if (a != null) {
            for (Node a2 : a) {
                a(a2, bxVar);
            }
        }
    }

    private static void a(Node node, bx bxVar) {
        while (node.hasChildNodes()) {
            String nodeName = node.getNodeName();
            int i = -1;
            int hashCode = nodeName.hashCode();
            int i2 = 0;
            if (hashCode != -2077435339) {
                if (hashCode != -1320080837) {
                    if (hashCode != 2021392) {
                        if (hashCode == 1561251035 && nodeName.equals("JavaScriptResource")) {
                            i = 3;
                        }
                    } else if (nodeName.equals("AVID")) {
                        i = 0;
                    }
                } else if (nodeName.equals("Verification")) {
                    i = 2;
                }
            } else if (nodeName.equals("AdVerifications")) {
                i = 1;
            }
            switch (i) {
                case 0:
                    node = bu.a(node, "AdVerifications");
                    if (node == null) {
                        break;
                    }
                    continue;
                case 1:
                    if (node.hasChildNodes()) {
                        NodeList childNodes = node.getChildNodes();
                        int length = childNodes.getLength();
                        while (i2 < length) {
                            a(childNodes.item(i2), bxVar);
                            i2++;
                        }
                        return;
                    }
                    break;
                case 2:
                    node = bu.a(node, "JavaScriptResource");
                    if (node == null) {
                        break;
                    }
                    continue;
                case 3:
                    String a = bu.a(node);
                    if (a != null) {
                        bxVar.a(new NativeTracker(a, 0, TrackerEventType.TRACKER_EVENT_TYPE_IAS, null));
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
