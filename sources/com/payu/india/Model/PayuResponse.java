package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;

public class PayuResponse implements Parcelable {
    public static final Creator<PayuResponse> CREATOR = new Creator<PayuResponse>() {
        /* renamed from: a */
        public PayuResponse createFromParcel(Parcel parcel) {
            return new PayuResponse(parcel);
        }

        /* renamed from: a */
        public PayuResponse[] newArray(int i) {
            return new PayuResponse[i];
        }
    };
    private ArrayList<StoredCard> a;
    private ArrayList<Emi> b;
    private ArrayList<PaymentDetails> c;
    private ArrayList<PaymentDetails> d;
    private ArrayList<PaymentDetails> e;
    private ArrayList<PaymentDetails> f;
    private ArrayList<PaymentDetails> g;
    private ArrayList<PaymentDetails> h;
    private ArrayList<PaymentDetails> i;
    private ArrayList<PaymentDetails> j;
    private Upi k;
    private Upi l;
    private PostData m;
    private CardInformation n;
    private PayuOffer o;
    private ArrayList<TransactionDetails> p;
    private ArrayList<PayuOffer> q;
    private PayuOfferDetails r;
    private HashMap<String, HashMap<String, PayuEmiAmountAccordingToInterest>> s;

    public int describeContents() {
        return 0;
    }

    public void a(Upi upi) {
        this.k = upi;
    }

    public void b(Upi upi) {
        this.l = upi;
    }

    protected PayuResponse(Parcel parcel) {
        this.a = parcel.createTypedArrayList(StoredCard.CREATOR);
        this.b = parcel.createTypedArrayList(Emi.CREATOR);
        this.c = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.d = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.e = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.f = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.g = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.h = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.i = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.j = parcel.createTypedArrayList(PaymentDetails.CREATOR);
        this.m = (PostData) parcel.readParcelable(PostData.class.getClassLoader());
        this.n = (CardInformation) parcel.readParcelable(CardInformation.class.getClassLoader());
        this.o = (PayuOffer) parcel.readParcelable(PayuOffer.class.getClassLoader());
        this.p = parcel.createTypedArrayList(TransactionDetails.CREATOR);
        this.q = parcel.createTypedArrayList(PayuOffer.CREATOR);
        this.s = (HashMap) parcel.readParcelable(PayuEmiAmountAccordingToInterest.class.getClassLoader());
        this.r = (PayuOfferDetails) parcel.readParcelable(PayuOfferDetails.class.getClassLoader());
    }

    public ArrayList<StoredCard> a() {
        return this.a;
    }

    public void a(ArrayList<StoredCard> arrayList) {
        this.a = arrayList;
    }

    public void b(ArrayList<Emi> arrayList) {
        this.b = arrayList;
    }

    public void c(ArrayList<PaymentDetails> arrayList) {
        this.c = arrayList;
    }

    public void d(ArrayList<PaymentDetails> arrayList) {
        this.d = arrayList;
    }

    public void e(ArrayList<PaymentDetails> arrayList) {
        this.e = arrayList;
    }

    public void f(ArrayList<PaymentDetails> arrayList) {
        this.f = arrayList;
    }

    public void g(ArrayList<PaymentDetails> arrayList) {
        this.g = arrayList;
    }

    public void h(ArrayList<PaymentDetails> arrayList) {
        this.h = arrayList;
    }

    public void i(ArrayList<PaymentDetails> arrayList) {
        this.i = arrayList;
    }

    public void j(ArrayList<PaymentDetails> arrayList) {
        this.j = arrayList;
    }

    public PostData b() {
        return this.m;
    }

    public void a(PostData postData) {
        this.m = postData;
    }

    public CardInformation c() {
        return this.n;
    }

    public void a(CardInformation cardInformation) {
        this.n = cardInformation;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.a);
        parcel.writeTypedList(this.b);
        parcel.writeTypedList(this.c);
        parcel.writeTypedList(this.d);
        parcel.writeTypedList(this.e);
        parcel.writeTypedList(this.f);
        parcel.writeTypedList(this.g);
        parcel.writeTypedList(this.h);
        parcel.writeTypedList(this.i);
        parcel.writeTypedList(this.j);
        parcel.writeParcelable(this.m, i);
        parcel.writeParcelable(this.n, i);
        parcel.writeParcelable(this.o, i);
        parcel.writeTypedList(this.p);
        parcel.writeTypedList(this.q);
        parcel.writeParcelable(this.r, i);
    }
}
