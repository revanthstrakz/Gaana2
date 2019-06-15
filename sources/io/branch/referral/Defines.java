package io.branch.referral;

import com.facebook.internal.NativeProtocol;
import com.facebook.places.model.PlaceFields;
import com.payu.custombrowser.util.CBConstant;
import com.til.colombia.android.internal.e;

public class Defines {

    public enum Jsonkey {
        IdentityID("identity_id"),
        Identity("identity"),
        DeviceFingerprintID("device_fingerprint_id"),
        SessionID("session_id"),
        LinkClickID("link_click_id"),
        FaceBookAppLinkChecked("facebook_app_link_checked"),
        AppLinkUsed("branch_used"),
        ReferringBranchIdentity("referring_branch_identity"),
        BranchIdentity("branch_identity"),
        Bucket("bucket"),
        DefaultBucket(CBConstant.DEFAULT_VALUE),
        Amount("amount"),
        CalculationType("calculation_type"),
        Location(PlaceFields.LOCATION),
        Type("type"),
        CreationSource("creation_source"),
        Prefix("prefix"),
        Expiration("expiration"),
        Event("event"),
        Metadata(TtmlNode.TAG_METADATA),
        ReferralCode("referral_code"),
        Total("total"),
        Unique("unique"),
        Length("length"),
        Direction("direction"),
        BeginAfterID("begin_after_id"),
        Link("link"),
        ReferringData("referring_data"),
        ReferringLink("referring_link"),
        Data("data"),
        OS(e.C),
        HardwareID("hardware_id"),
        IsHardwareIDReal("is_hardware_id_real"),
        AppVersion("app_version"),
        OSVersion("os_version"),
        IsReferrable("is_referrable"),
        Update("update"),
        URIScheme("uri_scheme"),
        AppIdentifier("app_identifier"),
        LinkIdentifier("link_identifier"),
        GoogleAdvertisingID("google_advertising_id"),
        LATVal("lat_val"),
        Debug(InAppConstants.RESP_ATTR_DEBUG_ENABLED),
        Brand("brand"),
        Model("model"),
        ScreenDpi("screen_dpi"),
        ScreenHeight("screen_height"),
        ScreenWidth("screen_width"),
        WiFi(e.ad),
        Clicked_Branch_Link("+clicked_branch_link"),
        IsFirstSession("+is_first_session"),
        AndroidDeepLinkPath("$android_deeplink_path"),
        DeepLinkPath("$deeplink_path"),
        AndroidAppLinkURL("android_app_link_url"),
        AndroidPushNotificationKey("branch"),
        AndroidPushIdentifier("push_identifier"),
        CanonicalIdentifier("$canonical_identifier"),
        ContentTitle("$og_title"),
        ContentDesc("$og_description"),
        ContentImgUrl("$og_image_url"),
        CanonicalUrl("$canonical_url"),
        ContentType("$content_type"),
        PublicallyIndexable("$publicly_indexable"),
        ContentKeyWords("$keywords"),
        ContentExpiryTime("$exp_date"),
        Params(NativeProtocol.WEB_DIALOG_PARAMS),
        External_Intent_URI("external_intent_uri"),
        External_Intent_Extra("external_intent_extra"),
        Last_Round_Trip_Time("lrtt"),
        Branch_Round_Trip_Time("brtt"),
        Branch_Instrumentation("instrumentation"),
        Queue_Wait_Time("qwt"),
        BranchViewData("branch_view_data"),
        BranchViewID("id"),
        BranchViewAction(NativeProtocol.WEB_DIALOG_ACTION),
        BranchViewNumOfUse("number_of_use"),
        BranchViewUrl("url"),
        BranchViewHtml("html");
        
        private String key;

        private Jsonkey(String str) {
            this.key = "";
            this.key = str;
        }

        public String getKey() {
            return this.key;
        }

        public String toString() {
            return this.key;
        }
    }

    public enum LinkParam {
        Tags("tags"),
        Alias("alias"),
        Type("type"),
        Duration("duration"),
        Channel("channel"),
        Feature("feature"),
        Stage("stage"),
        Data("data"),
        URL("url");
        
        private String key;

        private LinkParam(String str) {
            this.key = "";
            this.key = str;
        }

        public String getKey() {
            return this.key;
        }

        public String toString() {
            return this.key;
        }
    }

    public enum RequestPath {
        RedeemRewards("v1/redeem"),
        GetURL("v1/url"),
        RegisterInstall("v1/install"),
        RegisterClose("v1/close"),
        RegisterOpen("v1/open"),
        RegisterView("v1/register-view"),
        Referrals("v1/referrals/"),
        SendAPPList("v1/applist"),
        GetCredits("v1/credits/"),
        GetCreditHistory("v1/credithistory"),
        CompletedAction("v1/event"),
        IdentifyUser("v1/profile"),
        Logout("v1/logout"),
        GetReferralCode("v1/referralcode"),
        ValidateReferralCode("v1/referralcode/"),
        ApplyReferralCode("v1/applycode/"),
        DebugConnect("v1/debug/connect");
        
        private String key;

        private RequestPath(String str) {
            this.key = "";
            this.key = str;
        }

        public String getPath() {
            return this.key;
        }

        public String toString() {
            return this.key;
        }
    }
}
