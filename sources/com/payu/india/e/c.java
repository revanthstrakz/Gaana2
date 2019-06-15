package com.payu.india.e;

import android.os.AsyncTask;
import com.google.api.client.http.HttpMethods;
import com.payu.custombrowser.util.CBConstant;
import com.payu.india.Model.Emi;
import com.payu.india.Model.PaymentDetails;
import com.payu.india.Model.PayuConfig;
import com.payu.india.Model.PayuResponse;
import com.payu.india.Model.PostData;
import com.payu.india.Model.StoredCard;
import com.payu.india.Model.Upi;
import com.payu.india.b.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends AsyncTask<PayuConfig, Void, HashMap<String, String>> {
    PayuConfig a;
    HashMap<String, String> b;
    d c;

    class a extends AsyncTask<PayuConfig, String, PayuResponse> {

        class a implements Comparator<Emi> {
            a() {
            }

            /* renamed from: a */
            public int compare(Emi emi, Emi emi2) {
                return emi.a().compareTo(emi2.a());
            }
        }

        class b implements Comparator<PaymentDetails> {
            b() {
            }

            /* renamed from: a */
            public int compare(PaymentDetails paymentDetails, PaymentDetails paymentDetails2) {
                return paymentDetails.a().compareTo(paymentDetails2.a());
            }
        }

        a() {
        }

        /* Access modifiers changed, original: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public PayuResponse doInBackground(PayuConfig... payuConfigArr) {
            ProtocolException e;
            UnsupportedEncodingException e2;
            JSONException e3;
            IOException e4;
            PayuResponse payuResponse = new PayuResponse();
            PostData postData = new PostData();
            try {
                URL url;
                PayuConfig payuConfig = payuConfigArr[0];
                switch (payuConfig.b()) {
                    case 0:
                        url = new URL("https://info.payu.in/merchant/postservice.php?form=2");
                        break;
                    case 1:
                        url = new URL("https://mobiletest.payu.in/merchant/postservice?form=2");
                        break;
                    case 2:
                        url = new URL("https://test.payu.in/merchant/postservice?form=2");
                        break;
                    case 3:
                        url = new URL("https://mobiledev.payu.in/merchant/postservice?form=2");
                        break;
                    default:
                        url = new URL("https://info.payu.in/merchant/postservice.php?form=2");
                        break;
                }
                byte[] bytes = payuConfig.a().getBytes("UTF-8");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod(HttpMethods.POST);
                httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bytes.length));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(bytes);
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        stringBuffer.append(new String(bArr, 0, read));
                    } else {
                        JSONObject jSONObject;
                        ArrayList arrayList;
                        JSONObject jSONObject2 = new JSONObject(stringBuffer.toString());
                        if (jSONObject2.has("ibiboCodes")) {
                            JSONObject jSONObject3;
                            Iterator keys;
                            String str;
                            JSONObject jSONObject4;
                            PaymentDetails paymentDetails;
                            Iterator keys2;
                            ArrayList arrayList2;
                            jSONObject = jSONObject2.getJSONObject("ibiboCodes");
                            if (jSONObject.has("creditcard")) {
                                jSONObject3 = jSONObject.getJSONObject("creditcard");
                                arrayList = new ArrayList();
                                keys = jSONObject3.keys();
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    paymentDetails = new PaymentDetails();
                                    paymentDetails.b(str);
                                    paymentDetails.c(jSONObject4.getString("bank_id"));
                                    paymentDetails.a(jSONObject4.getString("title"));
                                    paymentDetails.d(jSONObject4.getString("pgId"));
                                    arrayList.add(paymentDetails);
                                }
                                payuResponse.c(arrayList);
                            }
                            if (jSONObject.has("debitcard")) {
                                jSONObject3 = jSONObject.getJSONObject("debitcard");
                                arrayList = new ArrayList();
                                keys = jSONObject3.keys();
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    PaymentDetails paymentDetails2 = new PaymentDetails();
                                    JSONObject jSONObject5 = jSONObject3.getJSONObject(str);
                                    paymentDetails2.b(str);
                                    paymentDetails2.c(jSONObject5.getString("bank_id"));
                                    paymentDetails2.a(jSONObject5.getString("title"));
                                    paymentDetails2.d(jSONObject5.getString("pgId"));
                                    arrayList.add(paymentDetails2);
                                }
                                payuResponse.d(arrayList);
                            }
                            if (jSONObject.has("netbanking")) {
                                jSONObject3 = jSONObject.getJSONObject("netbanking");
                                keys2 = jSONObject3.keys();
                                arrayList2 = new ArrayList();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    paymentDetails = new PaymentDetails();
                                    paymentDetails.b(str);
                                    paymentDetails.c(jSONObject4.getString("bank_id"));
                                    paymentDetails.a(jSONObject4.getString("title"));
                                    paymentDetails.d(jSONObject4.getString("pgId"));
                                    arrayList2.add(paymentDetails);
                                }
                                Collections.sort(arrayList2, new b());
                                payuResponse.e(arrayList2);
                            }
                            if (jSONObject.has("cashcard")) {
                                jSONObject3 = jSONObject.getJSONObject("cashcard");
                                keys2 = jSONObject3.keys();
                                arrayList2 = new ArrayList();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    paymentDetails = new PaymentDetails();
                                    paymentDetails.b(str);
                                    paymentDetails.c(jSONObject4.getString("bank_id"));
                                    paymentDetails.a(jSONObject4.getString("title"));
                                    paymentDetails.d(jSONObject4.getString("pgId"));
                                    arrayList2.add(paymentDetails);
                                }
                                Collections.sort(arrayList2, new b());
                                payuResponse.f(arrayList2);
                            }
                            if (jSONObject.has("ivr")) {
                                jSONObject3 = jSONObject.getJSONObject("ivr");
                                keys2 = jSONObject3.keys();
                                arrayList2 = new ArrayList();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    paymentDetails = new PaymentDetails();
                                    paymentDetails.b(str);
                                    paymentDetails.c(jSONObject4.getString("bank_id"));
                                    paymentDetails.a(jSONObject4.getString("title"));
                                    paymentDetails.d(jSONObject4.getString("pgId"));
                                    arrayList2.add(paymentDetails);
                                }
                                payuResponse.g(arrayList2);
                            }
                            if (jSONObject.has("ivrdc")) {
                                jSONObject3 = jSONObject.getJSONObject("ivrdc");
                                keys2 = jSONObject3.keys();
                                arrayList2 = new ArrayList();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    paymentDetails = new PaymentDetails();
                                    paymentDetails.b(str);
                                    paymentDetails.c(jSONObject4.getString("bank_id"));
                                    paymentDetails.a(jSONObject4.getString("title"));
                                    paymentDetails.d(jSONObject4.getString("pgId"));
                                    arrayList2.add(paymentDetails);
                                }
                                payuResponse.h(arrayList2);
                            }
                            if (jSONObject.has("paisawallet")) {
                                jSONObject3 = jSONObject.getJSONObject("paisawallet");
                                keys2 = jSONObject3.keys();
                                arrayList2 = new ArrayList();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    paymentDetails = new PaymentDetails();
                                    paymentDetails.b(str);
                                    paymentDetails.c(jSONObject4.getString("bank_id"));
                                    paymentDetails.a(jSONObject4.getString("title"));
                                    paymentDetails.d(jSONObject4.getString("pgId"));
                                    arrayList2.add(paymentDetails);
                                }
                                payuResponse.i(arrayList2);
                            }
                            if (jSONObject.has("lazypay")) {
                                jSONObject3 = jSONObject.getJSONObject("lazypay");
                                keys2 = jSONObject3.keys();
                                arrayList2 = new ArrayList();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    paymentDetails = new PaymentDetails();
                                    paymentDetails.b(str);
                                    paymentDetails.c(jSONObject4.getString("bank_id"));
                                    paymentDetails.a(jSONObject4.getString("title"));
                                    paymentDetails.d(jSONObject4.getString("pgId"));
                                    arrayList2.add(paymentDetails);
                                }
                                payuResponse.j(arrayList2);
                            }
                            if (jSONObject.has("emi")) {
                                jSONObject3 = jSONObject.getJSONObject("emi");
                                keys2 = jSONObject3.keys();
                                arrayList2 = new ArrayList();
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    jSONObject4 = jSONObject3.getJSONObject(str);
                                    Emi emi = new Emi();
                                    emi.b(str);
                                    emi.a(jSONObject4.getString("bank"));
                                    emi.c(jSONObject4.getString("title"));
                                    emi.d(jSONObject4.getString("pgId"));
                                    arrayList2.add(emi);
                                }
                                Collections.sort(arrayList2, new a());
                                payuResponse.b(arrayList2);
                            }
                            if (jSONObject.has("upi")) {
                                jSONObject = jSONObject.getJSONObject("upi");
                                if (jSONObject.has("upi".toUpperCase())) {
                                    jSONObject3 = jSONObject.getJSONObject("upi".toUpperCase());
                                    Upi upi = new Upi();
                                    upi.b(jSONObject3.getString("title"));
                                    upi.c(jSONObject3.getString("bank_id"));
                                    upi.a(jSONObject3.getString("pgId"));
                                    upi.d(jSONObject3.getString("show_form"));
                                    payuResponse.a(upi);
                                }
                                if (jSONObject.has("TEZ".toUpperCase())) {
                                    jSONObject = jSONObject.getJSONObject("TEZ".toUpperCase());
                                    Upi upi2 = new Upi();
                                    upi2.b(jSONObject.getString("title"));
                                    upi2.c(jSONObject.getString("bank_id"));
                                    upi2.a(jSONObject.getString("pgId"));
                                    upi2.d(jSONObject.getString("show_form"));
                                    payuResponse.b(upi2);
                                }
                            }
                        }
                        if (jSONObject2.has("userCards") && jSONObject2.getJSONObject("userCards").has("user_cards")) {
                            jSONObject = jSONObject2.getJSONObject("userCards").getJSONObject("user_cards");
                            Iterator keys3 = jSONObject.keys();
                            arrayList = new ArrayList();
                            while (keys3.hasNext()) {
                                String str2 = (String) keys3.next();
                                StoredCard storedCard = new StoredCard();
                                JSONObject jSONObject6 = jSONObject.getJSONObject(str2);
                                storedCard.a(jSONObject6.getString("name_on_card"));
                                storedCard.b(jSONObject6.getString("card_name"));
                                storedCard.c(jSONObject6.getString("expiry_year"));
                                storedCard.d(jSONObject6.getString("expiry_month"));
                                storedCard.e(jSONObject6.getString("card_type"));
                                storedCard.f(jSONObject6.getString("card_token"));
                                storedCard.a(Boolean.valueOf(jSONObject6.getInt("is_expired") != 0));
                                storedCard.g(jSONObject6.getString("card_mode"));
                                storedCard.h(jSONObject6.getString("card_no"));
                                storedCard.i(jSONObject6.getString("card_brand"));
                                storedCard.j(jSONObject6.getString("card_bin"));
                                storedCard.k(jSONObject6.getString("isDomestic"));
                                storedCard.l(jSONObject6.getString("issuingBank"));
                                if (jSONObject6.has("card_cvv")) {
                                    storedCard.a(jSONObject6.getInt("card_cvv"));
                                    if (c.this.b == null || c.this.b.get(storedCard.d()) == null) {
                                        storedCard.b(0);
                                        storedCard.m(null);
                                    } else {
                                        storedCard.b(jSONObject6.getInt("card_cvv"));
                                        storedCard.m((String) c.this.b.get(storedCard.d()));
                                    }
                                }
                                arrayList.add(storedCard);
                            }
                            payuResponse.a(arrayList);
                        }
                        if (jSONObject2.has("status") && jSONObject2.getString("status").contentEquals("0")) {
                            PostData postData2 = new PostData();
                            try {
                                postData2.a(5019);
                                postData2.a("ERROR");
                                postData2.b(jSONObject2.getString("msg"));
                                postData = postData2;
                            } catch (ProtocolException e5) {
                                e = e5;
                                postData = postData2;
                                postData.a(5016);
                                postData.a("ERROR");
                                postData.b(e.getMessage());
                                payuResponse.a(postData);
                                return payuResponse;
                            } catch (UnsupportedEncodingException e6) {
                                e2 = e6;
                                postData = postData2;
                                postData.a(5004);
                                postData.a("ERROR");
                                postData.b(e2.getMessage());
                                payuResponse.a(postData);
                                return payuResponse;
                            } catch (JSONException e7) {
                                e3 = e7;
                                postData = postData2;
                                postData.a(5014);
                                postData.a("ERROR");
                                postData.b(e3.getMessage());
                                payuResponse.a(postData);
                                return payuResponse;
                            } catch (IOException e8) {
                                e4 = e8;
                                postData = postData2;
                                postData.a(5016);
                                postData.a("ERROR");
                                postData.b(e4.getMessage());
                                payuResponse.a(postData);
                                return payuResponse;
                            }
                            payuResponse.a(postData);
                            return payuResponse;
                        }
                        postData.a(0);
                        postData.b("Data fetched successfully, Stored card status: ");
                        if (jSONObject2.has("userCards") && jSONObject2.getJSONObject("userCards").has("msg")) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(postData.b());
                            stringBuilder.append(" ");
                            stringBuilder.append(jSONObject2.getJSONObject("userCards").get("msg").toString());
                            postData.b(stringBuilder.toString());
                        }
                        postData.a("SUCCESS");
                        payuResponse.a(postData);
                        return payuResponse;
                    }
                }
            } catch (ProtocolException e9) {
                e = e9;
                postData.a(5016);
                postData.a("ERROR");
                postData.b(e.getMessage());
                payuResponse.a(postData);
                return payuResponse;
            } catch (UnsupportedEncodingException e10) {
                e2 = e10;
                postData.a(5004);
                postData.a("ERROR");
                postData.b(e2.getMessage());
                payuResponse.a(postData);
                return payuResponse;
            } catch (JSONException e11) {
                e3 = e11;
                postData.a(5014);
                postData.a("ERROR");
                postData.b(e3.getMessage());
                payuResponse.a(postData);
                return payuResponse;
            } catch (IOException e12) {
                e4 = e12;
                postData.a(5016);
                postData.a("ERROR");
                postData.b(e4.getMessage());
                payuResponse.a(postData);
                return payuResponse;
            }
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(PayuResponse payuResponse) {
            super.onPostExecute(payuResponse);
            c.this.c.a(payuResponse);
        }
    }

    public c(d dVar) {
        this.c = dVar;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public HashMap<String, String> doInBackground(PayuConfig... payuConfigArr) {
        this.a = payuConfigArr[0];
        String str = "";
        Matcher matcher = Pattern.compile("var1=([^&]*)").matcher(this.a.a());
        while (matcher.find()) {
            str = matcher.group(1);
        }
        if (!(str.equals("") || str.equals(CBConstant.DEFAULT_VALUE))) {
            com.payu.india.b.c a = com.payu.india.a.a.a();
            if (a != null) {
                return a.a(str);
            }
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(HashMap<String, String> hashMap) {
        super.onPostExecute(hashMap);
        this.b = hashMap;
        new a().execute(new PayuConfig[]{this.a});
    }
}
