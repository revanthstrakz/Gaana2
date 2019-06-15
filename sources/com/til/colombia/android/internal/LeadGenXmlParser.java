package com.til.colombia.android.internal;

import android.content.Context;
import android.util.Xml;
import com.google.android.gms.plus.PlusShare;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class LeadGenXmlParser {
    private static final String f = "leadgen";
    private static final String g = "post";
    private static final String h = "url";
    private static final String i = "bgImage";
    private static final String j = "conversionPixel";
    private static final String k = "formItem";
    private static final String l = "options";
    Context a;
    public String b;
    public String c;
    public List<String> d;
    public ArrayList<ViewObject> e = new ArrayList();
    private String m;

    public class ViewObject implements Serializable {
        public String errormsg;
        public String field;
        public String label;
        public int maxLength = -1;
        public int minLength = -1;
        public LinkedHashMap<String, String> options = new LinkedHashMap();
        public String pattern;
        public String placeholder;
        public String type;

        public void setType(String str) {
            this.type = str;
        }

        public void setField(String str) {
            this.field = str;
        }

        public void setLabel(String str) {
            this.label = str;
        }

        public void setPlaceholder(String str) {
            this.placeholder = str;
        }

        public void setErrormsg(String str) {
            this.errormsg = str;
        }

        public void addOption(String str, String str2) {
            this.options.put(str, str2);
        }

        public String getType() {
            return this.type;
        }

        public String getField() {
            return this.field;
        }

        public String getLabel() {
            return this.label;
        }

        public String getPlaceholder() {
            return this.placeholder;
        }

        public String getErrormsg() {
            return this.errormsg;
        }

        public LinkedHashMap<String, String> getOptions() {
            return this.options;
        }

        public String getPattern() {
            return this.pattern;
        }

        public void setPattern(String str) {
            this.pattern = str;
        }

        public int getMinLength() {
            return this.minLength;
        }

        public void setMinLength(int i) {
            this.minLength = i;
        }

        public int getMaxLength() {
            return this.maxLength;
        }

        public void setMaxLength(int i) {
            this.maxLength = i;
        }
    }

    private String e() {
        return this.m;
    }

    public final String a() {
        return this.c;
    }

    public final List<String> b() {
        return this.d;
    }

    public final ArrayList<ViewObject> c() {
        return this.e;
    }

    public LeadGenXmlParser(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void d() throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        newPullParser.setInput(new StringReader(this.b));
        newPullParser.nextTag();
        newPullParser.require(2, null, f);
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            if (newPullParser.getName() != null) {
                if (newPullParser.getName().equalsIgnoreCase("post")) {
                    a(newPullParser);
                } else if (newPullParser.getName().equalsIgnoreCase(i)) {
                    b(newPullParser);
                } else if (newPullParser.getName().equalsIgnoreCase(j)) {
                    c(newPullParser);
                } else if (newPullParser.getName().equalsIgnoreCase(k)) {
                    d(newPullParser);
                }
            }
        }
    }

    public void a(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = null;
        xmlPullParser.require(2, null, "post");
        int next = xmlPullParser.next();
        String name = xmlPullParser.getName();
        while (true) {
            if (next != 3 || !name.equalsIgnoreCase("post")) {
                switch (xmlPullParser.getEventType()) {
                    case 3:
                        if (!name.equalsIgnoreCase("url")) {
                            break;
                        }
                        this.c = str;
                        break;
                    case 4:
                        str = xmlPullParser.getText();
                        break;
                    default:
                        break;
                }
                next = xmlPullParser.next();
                name = xmlPullParser.getName();
            } else {
                return;
            }
        }
    }

    public void b(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = null;
        xmlPullParser.require(2, null, i);
        int next = xmlPullParser.next();
        String name = xmlPullParser.getName();
        while (true) {
            if (next != 3 || !name.equalsIgnoreCase(i)) {
                switch (xmlPullParser.getEventType()) {
                    case 3:
                        if (!name.equalsIgnoreCase("url")) {
                            break;
                        }
                        this.m = str;
                        break;
                    case 4:
                        str = xmlPullParser.getText();
                        break;
                    default:
                        break;
                }
                next = xmlPullParser.next();
                name = xmlPullParser.getName();
            } else {
                return;
            }
        }
    }

    public void c(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Object obj = null;
        xmlPullParser.require(2, null, j);
        this.d = new ArrayList();
        int next = xmlPullParser.next();
        String name = xmlPullParser.getName();
        while (true) {
            if (next != 3 || !name.equalsIgnoreCase(j)) {
                switch (xmlPullParser.getEventType()) {
                    case 3:
                        if (!name.equalsIgnoreCase("url")) {
                            break;
                        }
                        this.d.add(obj);
                        break;
                    case 4:
                        obj = xmlPullParser.getText();
                        break;
                    default:
                        break;
                }
                next = xmlPullParser.next();
                name = xmlPullParser.getName();
            } else {
                return;
            }
        }
    }

    public void d(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, k);
        ViewObject viewObject = new ViewObject();
        int next = xmlPullParser.next();
        String name = xmlPullParser.getName();
        String str = null;
        while (true) {
            if (next == 3 && name.equalsIgnoreCase(k)) {
                this.e.add(viewObject);
                return;
            } else if (name == null || !name.equalsIgnoreCase(l)) {
                switch (xmlPullParser.getEventType()) {
                    case 3:
                        if (!name.equalsIgnoreCase("type")) {
                            if (!name.equalsIgnoreCase("errorMsg")) {
                                if (!name.equalsIgnoreCase("field")) {
                                    if (!name.equalsIgnoreCase(PlusShare.KEY_CALL_TO_ACTION_LABEL)) {
                                        if (!name.equalsIgnoreCase("placeHolder")) {
                                            if (!name.equalsIgnoreCase("pattern")) {
                                                if (!name.equalsIgnoreCase("minLength")) {
                                                    if (name.equalsIgnoreCase("maxLength")) {
                                                        viewObject.setMaxLength(Integer.parseInt(str));
                                                        break;
                                                    }
                                                }
                                                viewObject.setMinLength(Integer.parseInt(str));
                                                break;
                                            }
                                            viewObject.setPattern(str);
                                            break;
                                        }
                                        viewObject.setPlaceholder(str);
                                        break;
                                    }
                                    viewObject.setLabel(str);
                                    break;
                                }
                                viewObject.setField(str);
                                break;
                            }
                            viewObject.setErrormsg(str);
                            break;
                        }
                        viewObject.setType(str);
                        break;
                        break;
                    case 4:
                        str = xmlPullParser.getText();
                        break;
                }
                next = xmlPullParser.next();
                name = xmlPullParser.getName();
            } else {
                next = xmlPullParser.next();
                name = xmlPullParser.getName();
                while (true) {
                    if (next == 3 && r4.equalsIgnoreCase(l)) {
                        next = xmlPullParser.next();
                        name = xmlPullParser.getName();
                    } else {
                        if (xmlPullParser.getName() != null && xmlPullParser.getName().equalsIgnoreCase("option")) {
                            xmlPullParser.require(2, null, "option");
                            next = xmlPullParser.next();
                            name = xmlPullParser.getName();
                            String str2 = null;
                            String str3 = str2;
                            while (true) {
                                if (next != 3 || !name.equalsIgnoreCase("option")) {
                                    switch (xmlPullParser.getEventType()) {
                                        case 3:
                                            if (!name.equalsIgnoreCase(PlusShare.KEY_CALL_TO_ACTION_LABEL)) {
                                                if (!name.equalsIgnoreCase("value")) {
                                                    break;
                                                }
                                                viewObject.addOption(str3, str2);
                                                break;
                                            }
                                            str3 = str2;
                                            break;
                                        case 4:
                                            str2 = xmlPullParser.getText();
                                            break;
                                        default:
                                            break;
                                    }
                                    next = xmlPullParser.next();
                                    name = xmlPullParser.getName();
                                }
                            }
                        }
                        next = xmlPullParser.next();
                        name = xmlPullParser.getName();
                    }
                }
            }
        }
    }

    private void a(XmlPullParser xmlPullParser, ViewObject viewObject) throws IOException, XmlPullParserException {
        int next = xmlPullParser.next();
        String name = xmlPullParser.getName();
        while (true) {
            if (next != 3 || !name.equalsIgnoreCase(l)) {
                if (xmlPullParser.getName() != null && xmlPullParser.getName().equalsIgnoreCase("option")) {
                    String str = null;
                    xmlPullParser.require(2, null, "option");
                    next = xmlPullParser.next();
                    name = xmlPullParser.getName();
                    String str2 = null;
                    while (true) {
                        if (next != 3 || !name.equalsIgnoreCase("option")) {
                            switch (xmlPullParser.getEventType()) {
                                case 3:
                                    if (!name.equalsIgnoreCase(PlusShare.KEY_CALL_TO_ACTION_LABEL)) {
                                        if (!name.equalsIgnoreCase("value")) {
                                            break;
                                        }
                                        viewObject.addOption(str2, str);
                                        break;
                                    }
                                    str2 = str;
                                    break;
                                case 4:
                                    str = xmlPullParser.getText();
                                    break;
                                default:
                                    break;
                            }
                            next = xmlPullParser.next();
                            name = xmlPullParser.getName();
                        }
                    }
                }
                next = xmlPullParser.next();
                name = xmlPullParser.getName();
            } else {
                return;
            }
        }
    }

    private static void b(XmlPullParser xmlPullParser, ViewObject viewObject) throws IOException, XmlPullParserException {
        String str = null;
        xmlPullParser.require(2, null, "option");
        int next = xmlPullParser.next();
        String name = xmlPullParser.getName();
        String str2 = null;
        while (true) {
            if (next != 3 || !name.equalsIgnoreCase("option")) {
                switch (xmlPullParser.getEventType()) {
                    case 3:
                        if (!name.equalsIgnoreCase(PlusShare.KEY_CALL_TO_ACTION_LABEL)) {
                            if (!name.equalsIgnoreCase("value")) {
                                break;
                            }
                            viewObject.addOption(str2, str);
                            break;
                        }
                        str2 = str;
                        break;
                    case 4:
                        str = xmlPullParser.getText();
                        break;
                    default:
                        break;
                }
                next = xmlPullParser.next();
                name = xmlPullParser.getName();
            } else {
                return;
            }
        }
    }
}
