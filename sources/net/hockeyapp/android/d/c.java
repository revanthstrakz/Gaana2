package net.hockeyapp.android.d;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.hockeyapp.android.objects.Feedback;
import net.hockeyapp.android.objects.FeedbackAttachment;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.objects.FeedbackResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {

    private static class a {
        public static final c a = new c();
    }

    private c() {
    }

    public static c a() {
        return a.a;
    }

    public FeedbackResponse a(String str) {
        Throwable e;
        FeedbackResponse feedbackResponse;
        Throwable th;
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        try {
            JSONObject jSONObject;
            JSONObject jSONObject2;
            ArrayList arrayList;
            Feedback feedback;
            JSONObject jSONObject3 = new JSONObject(str2);
            JSONObject jSONObject4 = jSONObject3.getJSONObject("feedback");
            Feedback feedback2 = new Feedback();
            JSONArray jSONArray = jSONObject4.getJSONArray("messages");
            if (jSONArray.length() > 0) {
                ArrayList arrayList2 = new ArrayList();
                int i = 0;
                while (i < jSONArray.length()) {
                    String str3;
                    int i2;
                    String str4;
                    String str5;
                    String str6;
                    String str7;
                    String str8;
                    String str9 = jSONArray.getJSONObject(i).getString("subject").toString();
                    String str10 = jSONArray.getJSONObject(i).getString(MimeTypes.BASE_TYPE_TEXT).toString();
                    String str11 = jSONArray.getJSONObject(i).getString("oem").toString();
                    String str12 = jSONArray.getJSONObject(i).getString("model").toString();
                    String str13 = jSONArray.getJSONObject(i).getString("os_version").toString();
                    String str14 = jSONArray.getJSONObject(i).getString("created_at").toString();
                    int i3 = jSONArray.getJSONObject(i).getInt("id");
                    String str15 = jSONArray.getJSONObject(i).getString(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE).toString();
                    jSONObject = jSONObject3;
                    int i4 = jSONArray.getJSONObject(i).getInt("via");
                    jSONObject2 = jSONObject4;
                    str2 = jSONArray.getJSONObject(i).getString("user_string").toString();
                    Feedback feedback3 = feedback2;
                    String str16 = jSONArray.getJSONObject(i).getString("clean_text").toString();
                    ArrayList arrayList3 = arrayList2;
                    String str17 = jSONArray.getJSONObject(i).getString("name").toString();
                    int i5 = i4;
                    String str18 = jSONArray.getJSONObject(i).getString("app_id").toString();
                    JSONArray jSONArray2 = jSONArray;
                    jSONArray = jSONArray.getJSONObject(i).optJSONArray("attachments");
                    List emptyList = Collections.emptyList();
                    if (jSONArray != null) {
                        emptyList = new ArrayList();
                        str3 = str2;
                        i2 = i;
                        i = 0;
                        while (i < jSONArray.length()) {
                            str4 = str15;
                            int i6 = jSONArray.getJSONObject(i).getInt("id");
                            str5 = str10;
                            int i7 = jSONArray.getJSONObject(i).getInt("feedback_message_id");
                            str6 = str9;
                            str9 = jSONArray.getJSONObject(i).getString("file_name");
                            str7 = str13;
                            str10 = jSONArray.getJSONObject(i).getString("url");
                            str8 = str11;
                            str11 = jSONArray.getJSONObject(i).getString("created_at");
                            JSONArray jSONArray3 = jSONArray;
                            String string = jSONArray.getJSONObject(i).getString("updated_at");
                            FeedbackAttachment feedbackAttachment = new FeedbackAttachment();
                            feedbackAttachment.a(i6);
                            feedbackAttachment.b(i7);
                            feedbackAttachment.a(str9);
                            feedbackAttachment.b(str10);
                            feedbackAttachment.c(str11);
                            feedbackAttachment.d(string);
                            emptyList.add(feedbackAttachment);
                            i++;
                            str15 = str4;
                            str10 = str5;
                            str9 = str6;
                            str13 = str7;
                            str11 = str8;
                            jSONArray = jSONArray3;
                        }
                        str4 = str15;
                    } else {
                        str3 = str2;
                        str4 = str15;
                        i2 = i;
                    }
                    str6 = str9;
                    str5 = str10;
                    str8 = str11;
                    str7 = str13;
                    FeedbackMessage feedbackMessage = new FeedbackMessage();
                    feedbackMessage.k(str18);
                    feedbackMessage.i(str16);
                    feedbackMessage.f(str14);
                    feedbackMessage.a(i3);
                    feedbackMessage.d(str12);
                    feedbackMessage.j(str17);
                    feedbackMessage.c(str8);
                    feedbackMessage.e(str7);
                    feedbackMessage.a(str6);
                    feedbackMessage.b(str5);
                    feedbackMessage.g(str4);
                    feedbackMessage.h(str3);
                    feedbackMessage.b(i5);
                    feedbackMessage.a(emptyList);
                    arrayList = arrayList3;
                    arrayList.add(feedbackMessage);
                    i = i2 + 1;
                    arrayList2 = arrayList;
                    jSONObject3 = jSONObject;
                    jSONObject4 = jSONObject2;
                    feedback2 = feedback3;
                    jSONArray = jSONArray2;
                }
                jSONObject2 = jSONObject4;
                jSONObject = jSONObject3;
                arrayList = arrayList2;
                feedback = feedback2;
            } else {
                jSONObject2 = jSONObject4;
                jSONObject = jSONObject3;
                feedback = feedback2;
                arrayList = null;
            }
            feedback.a(arrayList);
            try {
                jSONObject3 = jSONObject2;
                try {
                    feedback.a(jSONObject3.getString("name").toString());
                } catch (JSONException e2) {
                    e = e2;
                }
            } catch (JSONException e3) {
                e = e3;
                jSONObject3 = jSONObject2;
                ThrowableExtension.printStackTrace(e);
                feedback.b(jSONObject3.getString("email").toString());
                feedback.a(jSONObject3.getInt("id"));
                feedback.c(jSONObject3.getString("created_at").toString());
                feedbackResponse = new FeedbackResponse();
                feedbackResponse.a(feedback);
                try {
                    jSONObject3 = jSONObject;
                    feedbackResponse.a(jSONObject3.getString("status").toString());
                } catch (JSONException e4) {
                    e = e4;
                    jSONObject3 = jSONObject;
                    ThrowableExtension.printStackTrace(e);
                    feedbackResponse.b(jSONObject3.getString(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE).toString());
                    return feedbackResponse;
                }
                feedbackResponse.b(jSONObject3.getString(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE).toString());
                return feedbackResponse;
            }
            try {
                feedback.b(jSONObject3.getString("email").toString());
            } catch (JSONException e5) {
                ThrowableExtension.printStackTrace(e5);
            }
            try {
                feedback.a(jSONObject3.getInt("id"));
            } catch (JSONException e52) {
                ThrowableExtension.printStackTrace(e52);
            }
            try {
                feedback.c(jSONObject3.getString("created_at").toString());
            } catch (JSONException e522) {
                ThrowableExtension.printStackTrace(e522);
            }
            feedbackResponse = new FeedbackResponse();
            try {
                feedbackResponse.a(feedback);
                jSONObject3 = jSONObject;
                try {
                    feedbackResponse.a(jSONObject3.getString("status").toString());
                } catch (JSONException e6) {
                    e522 = e6;
                }
                try {
                    feedbackResponse.b(jSONObject3.getString(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE).toString());
                    return feedbackResponse;
                } catch (JSONException e5222) {
                    ThrowableExtension.printStackTrace(e5222);
                    return feedbackResponse;
                }
            } catch (JSONException e52222) {
                th = e52222;
            }
        } catch (JSONException e522222) {
            th = e522222;
            feedbackResponse = null;
            ThrowableExtension.printStackTrace(th);
            return feedbackResponse;
        }
    }
}
