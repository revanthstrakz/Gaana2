package com.helpshift.common.platform;

import com.facebook.internal.NativeProtocol;
import com.helpshift.account.b.a;
import com.helpshift.common.exception.ParseException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.h;
import com.helpshift.configuration.b.b;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.e;
import com.helpshift.conversation.activeconversation.message.f;
import com.helpshift.conversation.activeconversation.message.g;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.conversation.activeconversation.message.k;
import com.helpshift.conversation.activeconversation.message.m;
import com.helpshift.conversation.activeconversation.message.n;
import com.helpshift.conversation.activeconversation.message.o;
import com.helpshift.conversation.dto.ConversationStatus;
import com.helpshift.conversation.dto.d;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.payu.custombrowser.util.CBConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class l implements h {
    l() {
    }

    public a a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new a(jSONObject.getString("id"), jSONObject.getString("name"));
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while creating profile");
        }
    }

    public b b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new b(jSONObject.optBoolean("rne", false), jSONObject.optBoolean("pfe", true), jSONObject.optBoolean("san", true), jSONObject.optBoolean("csat", false), jSONObject.optBoolean("dia", false), a(jSONObject.optJSONObject("t")), jSONObject.optInt("dbgl", 100), jSONObject.optInt("bcl", 100), jSONObject.optString("rurl", ""), i(jSONObject.getJSONObject("pr")));
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while fetching config");
        }
    }

    private boolean a(JSONObject jSONObject) {
        return jSONObject != null ? jSONObject.optString("hl", "true").equals("true") ^ 1 : false;
    }

    public com.helpshift.conversation.activeconversation.a c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            List a = a(jSONObject.getJSONArray("messages"));
            String str2 = null;
            for (int size = a.size() - 1; size >= 0; size--) {
                j jVar = (j) a.get(size);
                if (!(jVar instanceof AdminAttachmentMessageDM) && !(jVar instanceof AdminImageAttachmentMessageDM)) {
                    str2 = jVar.k;
                    break;
                }
            }
            String str3 = str2;
            com.helpshift.conversation.activeconversation.a aVar = new com.helpshift.conversation.activeconversation.a(jSONObject.getString("id"), jSONObject.getString("title"), ConversationStatus.fromInt(jSONObject.getInt("status")), jSONObject.getString("created_at"), jSONObject.getString("updated_at"), jSONObject.getString("publish_id"), str3, jSONObject.optBoolean("show-agent-name", true));
            aVar.b(a);
            return aVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception in reading conversation");
        }
    }

    public o d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            o oVar = new o(jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"));
            oVar.i = jSONObject.getString("id");
            oVar.r = n(jSONObject.optString("md_state", ""));
            return oVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading user text message");
        }
    }

    private List<j> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("type");
                String string2 = jSONObject.getString("origin");
                if (string.equals("txt") && string2.equals("mobile")) {
                    arrayList.add(d(jSONObject.toString()));
                    i++;
                } else if (string.equals("txt") && string2.equals("admin")) {
                    arrayList.addAll(e(jSONObject));
                    i++;
                } else if (string.equals("rar") && string2.equals("admin")) {
                    arrayList.add(f(jSONObject));
                    i++;
                } else if (string.equals("ar") && string2.equals("mobile")) {
                    arrayList.add(e(jSONObject.toString()));
                    i++;
                } else if (string.equals("ncr") && string2.equals("mobile")) {
                    arrayList.add(f(jSONObject.toString()));
                    i++;
                } else if (string.equals("ca") && string2.equals("mobile")) {
                    arrayList.add(g(jSONObject.toString()));
                    i++;
                } else if (string.equals("rsc") && string2.equals("admin")) {
                    arrayList.addAll(g(jSONObject));
                    i++;
                } else if (string.equals("sc") && string2.equals("mobile")) {
                    arrayList.add(h(jSONObject));
                    i++;
                } else if (string.equals("rfr") && string2.equals("admin")) {
                    arrayList.add(d(jSONObject));
                    i++;
                } else if (string.equals("ra") && string2.equals("mobile")) {
                    arrayList.add(c(jSONObject));
                    i++;
                } else {
                    if (string.equals("rj") && string2.equals("mobile")) {
                        arrayList.add(b(jSONObject));
                    }
                    i++;
                }
            } catch (RootAPIException | JSONException e) {
                com.helpshift.util.l.c("Helpshift_AResponseParser", "Exception while parsing messages: ", e);
            }
        }
        return arrayList;
    }

    private com.helpshift.conversation.activeconversation.message.h b(JSONObject jSONObject) {
        try {
            com.helpshift.conversation.activeconversation.message.h hVar = new com.helpshift.conversation.activeconversation.message.h(jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"), jSONObject.getJSONObject("meta").getString("refers"));
            hVar.i = jSONObject.getString("id");
            hVar.r = n(jSONObject.optString("md_state", ""));
            return hVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading follow-up rejected message");
        }
    }

    private g c(JSONObject jSONObject) {
        try {
            g gVar = new g(jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"), jSONObject.getJSONObject("meta").getString("refers"));
            gVar.i = jSONObject.getString("id");
            gVar.r = n(jSONObject.optString("md_state", ""));
            return gVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading follow-up accepted message");
        }
    }

    private com.helpshift.conversation.activeconversation.message.l d(JSONObject jSONObject) {
        try {
            com.helpshift.conversation.activeconversation.message.l lVar = new com.helpshift.conversation.activeconversation.message.l(jSONObject.getString("id"), jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"));
            lVar.r = n(jSONObject.optString("md_state", ""));
            return lVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading reopen message");
        }
    }

    private List<j> e(JSONObject jSONObject) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.getJSONObject("meta").optJSONArray("attachments");
            com.helpshift.conversation.activeconversation.message.b bVar = new com.helpshift.conversation.activeconversation.message.b(jSONObject.getString("id"), jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"));
            bVar.r = n(jSONObject.optString("md_state", ""));
            arrayList.add(bVar);
            if (optJSONArray != null) {
                arrayList.addAll(a(jSONObject, optJSONArray));
            }
            return arrayList;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading admin text message");
        }
    }

    private List<j> a(JSONObject jSONObject, JSONArray jSONArray) {
        Exception e;
        JSONObject jSONObject2 = jSONObject;
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(jSONObject2.getString("id"));
                stringBuilder.append("_");
                stringBuilder.append(i);
                String stringBuilder2 = stringBuilder.toString();
                i++;
                String a = com.helpshift.common.util.a.a(com.helpshift.common.util.a.a, jSONObject2.getString("created_at"), i);
                try {
                    int i2;
                    int n = n(jSONObject2.optString("md_state", ""));
                    if (jSONObject3.optBoolean(TtmlNode.TAG_IMAGE)) {
                        i2 = i;
                        AdminImageAttachmentMessageDM adminImageAttachmentMessageDM = r8;
                        AdminImageAttachmentMessageDM adminImageAttachmentMessageDM2 = new AdminImageAttachmentMessageDM(stringBuilder2, jSONObject2.getString("body"), a, jSONObject2.getJSONObject("author").getString("name"), jSONObject3.getString("url"), jSONObject3.getString("file-name"), jSONObject3.getString("thumbnail"), jSONObject3.getString("content-type"), jSONObject3.getInt("size"));
                        adminImageAttachmentMessageDM.r = n;
                        arrayList.add(adminImageAttachmentMessageDM);
                    } else {
                        i2 = i;
                        AdminAttachmentMessageDM adminAttachmentMessageDM = new AdminAttachmentMessageDM(stringBuilder2, jSONObject2.getString("body"), a, jSONObject2.getJSONObject("author").getString("name"), jSONObject3.getInt("size"), jSONObject3.getString("content-type"), jSONObject3.getString("url"), jSONObject3.getString("file-name"));
                        adminAttachmentMessageDM.r = n;
                        arrayList.add(adminAttachmentMessageDM);
                    }
                    i = i2;
                } catch (JSONException e2) {
                    e = e2;
                    throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading admin attachment message");
                }
            }
            return arrayList;
        } catch (JSONException e3) {
            e = e3;
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading admin attachment message");
        }
    }

    private k f(JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.getJSONObject("meta").optJSONObject(CBConstant.RESPONSE);
            boolean z = false;
            boolean optBoolean = optJSONObject != null ? optJSONObject.optBoolean("state") : false;
            if (jSONObject.optBoolean("invisible") || optBoolean) {
                z = true;
            }
            k kVar = new k(jSONObject.getString("id"), jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"), z);
            kVar.r = n(jSONObject.optString("md_state", ""));
            return kVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading request review message");
        }
    }

    public com.helpshift.conversation.activeconversation.message.a e(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            com.helpshift.conversation.activeconversation.message.a aVar = new com.helpshift.conversation.activeconversation.message.a(jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"), jSONObject.getJSONObject("meta").getString("refers"));
            aVar.i = jSONObject.getString("id");
            aVar.r = n(jSONObject.optString("md_state", ""));
            return aVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading accepted review message");
        }
    }

    public f f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            f fVar = new f(jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"));
            fVar.i = jSONObject.getString("id");
            fVar.r = n(jSONObject.optString("md_state", ""));
            return fVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading confirmation rejected message");
        }
    }

    public e g(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            e eVar = new e(jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"));
            eVar.i = jSONObject.getString("id");
            eVar.r = n(jSONObject.optString("md_state", ""));
            return eVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading confirmation accepted message");
        }
    }

    private List<j> g(JSONObject jSONObject) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            JSONObject jSONObject2 = jSONObject.getJSONObject("meta");
            JSONObject optJSONObject = jSONObject2.optJSONObject(CBConstant.RESPONSE);
            JSONArray optJSONArray = jSONObject2.optJSONArray("attachments");
            if (optJSONObject != null) {
                z = optJSONObject.getBoolean("state");
            }
            m mVar = new m(jSONObject.getString("id"), jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"), z);
            mVar.r = n(jSONObject.optString("md_state", ""));
            arrayList.add(mVar);
            if (optJSONArray != null) {
                arrayList.addAll(a(jSONObject, optJSONArray));
            }
            return arrayList;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading request screenshot message");
        }
    }

    private n h(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("meta").getJSONArray("attachments").getJSONObject(0);
            n nVar = new n(jSONObject.getString("body"), jSONObject.getString("created_at"), jSONObject.getJSONObject("author").getString("name"), jSONObject2.getString("content-type"), jSONObject2.optString("thumbnail", ""), jSONObject2.getString("file-name"), jSONObject2.getString("url"), jSONObject2.getInt("size"));
            nVar.i = jSONObject.getString("id");
            nVar.r = n(jSONObject.optString("md_state", ""));
            return nVar;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading screenshot message");
        }
    }

    public n h(String str) {
        try {
            return h(new JSONObject(str));
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading reopen message");
        }
    }

    public com.helpshift.conversation.dto.b i(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("issues");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(c(jSONArray.getJSONObject(i).toString()));
            }
            return new com.helpshift.conversation.dto.b(jSONObject.getString(AvidJSONUtil.KEY_TIMESTAMP), arrayList);
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading conversation inbox");
        }
    }

    public com.helpshift.conversation.activeconversation.message.h j(String str) {
        try {
            return b(new JSONObject(str));
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading follow-up rejected message");
        }
    }

    public g k(String str) {
        try {
            return c(new JSONObject(str));
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Parsing exception while reading follow-up accepted message");
        }
    }

    public com.helpshift.c.b.a l(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new com.helpshift.c.b.a(jSONObject.getString(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE), jSONObject.getString("endpoint"));
        } catch (JSONException e) {
            com.helpshift.util.l.c("Helpshift_AResponseParser", "Exception in parsing auth token", e);
            return null;
        }
    }

    public com.helpshift.conversation.dto.f m(String str) {
        com.helpshift.conversation.dto.e eVar;
        Throwable e;
        try {
            JSONArray jSONArray = new JSONArray(str);
            int i = jSONArray.getInt(0);
            if (i == 100) {
                jSONArray = jSONArray.getJSONArray(2);
                eVar = null;
                int i2 = 0;
                while (i2 < jSONArray.length()) {
                    try {
                        JSONObject jSONObject = new JSONObject(jSONArray.getJSONObject(i2).getString("m"));
                        if ("agent_type_activity".equals(jSONObject.getString("stream"))) {
                            com.helpshift.conversation.dto.e eVar2;
                            String string = jSONObject.getString(NativeProtocol.WEB_DIALOG_ACTION);
                            if ("start".equals(string)) {
                                eVar2 = new com.helpshift.conversation.dto.e(true, TimeUnit.SECONDS.toMillis(jSONObject.getLong("ttl")));
                            } else if ("stop".equals(string)) {
                                eVar2 = new com.helpshift.conversation.dto.e(false, 0);
                            }
                            eVar = eVar2;
                        }
                        i2++;
                    } catch (JSONException e2) {
                        e = e2;
                        com.helpshift.util.l.c("Helpshift_AResponseParser", "Exception in parsing web-socket message", e);
                        return eVar;
                    }
                }
                return eVar;
            } else if (i != 107) {
                return null;
            } else {
                return new d(TimeUnit.SECONDS.toMillis(jSONArray.getLong(1)));
            }
        } catch (JSONException e3) {
            e = e3;
            eVar = null;
            com.helpshift.util.l.c("Helpshift_AResponseParser", "Exception in parsing web-socket message", e);
            return eVar;
        }
    }

    private com.helpshift.configuration.b.a i(JSONObject jSONObject) throws JSONException {
        return new com.helpshift.configuration.b.a(jSONObject.optBoolean("s"), jSONObject.optInt("i"), jSONObject.optString("t", ""));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A:{RETURN} */
    private int n(java.lang.String r6) {
        /*
        r5 = this;
        r0 = r6.hashCode();
        r1 = -840272977; // 0xffffffffcdea73af float:-4.91681248E8 double:NaN;
        r2 = 1;
        r3 = 2;
        r4 = 0;
        if (r0 == r1) goto L_0x002b;
    L_0x000c:
        r1 = 3496342; // 0x355996 float:4.899419E-39 double:1.7274225E-317;
        if (r0 == r1) goto L_0x0021;
    L_0x0011:
        r1 = 3526552; // 0x35cf98 float:4.941752E-39 double:1.742348E-317;
        if (r0 == r1) goto L_0x0017;
    L_0x0016:
        goto L_0x0035;
    L_0x0017:
        r0 = "sent";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x0035;
    L_0x001f:
        r6 = r3;
        goto L_0x0036;
    L_0x0021:
        r0 = "read";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x0035;
    L_0x0029:
        r6 = r2;
        goto L_0x0036;
    L_0x002b:
        r0 = "unread";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x0035;
    L_0x0033:
        r6 = r4;
        goto L_0x0036;
    L_0x0035:
        r6 = -1;
    L_0x0036:
        switch(r6) {
            case 0: goto L_0x003c;
            case 1: goto L_0x003b;
            case 2: goto L_0x003a;
            default: goto L_0x0039;
        };
    L_0x0039:
        return r4;
    L_0x003a:
        return r3;
    L_0x003b:
        return r2;
    L_0x003c:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.platform.l.n(java.lang.String):int");
    }
}
