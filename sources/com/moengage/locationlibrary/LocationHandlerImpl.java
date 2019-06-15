package com.moengage.locationlibrary;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest.Builder;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.PayloadBuilder;
import com.moe.pushlibrary.models.GeoLocation;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.GeoTask;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.core.MoEUtils;
import com.moengage.location.GeoManager.LocationHandler;
import com.moengage.location.GeoManager.TASK_TYPE;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationHandlerImpl implements OnCompleteListener<Location>, LocationHandler {
    private static final int GEO_HIT = 2;
    private static final int SET_GEO_FENCE = 1;
    private static final int UPDATE_FENCE = 3;
    private Context context;
    private Intent geoFenceIntent;
    private GeoLocation geoLocation;
    private String geoResponse;
    boolean isLocationSynced = false;
    private int mode = -1;

    @Nullable
    private String getTransitionString(int i) {
        if (i == 4) {
            return LocationConstants.TRANSITION_TYPE_DWELL;
        }
        switch (i) {
            case 1:
                return LocationConstants.TRANSITION_TYPE_ENTER;
            case 2:
                return LocationConstants.TRANSITION_TYPE_EXIT;
            default:
                return null;
        }
    }

    public void setGeoFences(@NonNull Context context, String str) {
        Logger.v("LocationHandlerImpl: inside setGeoFences()");
        this.context = context;
        this.mode = 1;
        this.geoResponse = str;
        setGeoFenceInternal();
    }

    public void onGeoFenceHit(@NonNull Context context, Intent intent) {
        Logger.v("LocationHandlerImpl: inside onGeoFenceHit()");
        this.context = context;
        this.mode = 2;
        this.geoFenceIntent = intent;
        triggerLastLocationFetch();
    }

    public void updateFenceAndLocation(@NonNull Context context) {
        Logger.v("LocationHandlerImpl: inside updateFenceAndLocation()");
        this.context = context;
        this.mode = 3;
        if (!isSyncRequired(context)) {
            return;
        }
        if (!ConfigurationProvider.getInstance(context).isTrackLocationProhibited() || !ConfigurationProvider.getInstance(context).isSetGeoFenceProhibited()) {
            triggerLastLocationFetch();
        }
    }

    private GeofencingClient getGeoFencingClient() {
        return LocationServices.getGeofencingClient(this.context);
    }

    private void triggerLastLocationFetch() {
        try {
            if (MoEHelperUtils.hasPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") || MoEHelperUtils.hasPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION")) {
                LocationServices.getFusedLocationProviderClient(this.context).getLastLocation().addOnCompleteListener(this);
            }
        } catch (Exception e) {
            Logger.f("LocationHandlerImpl: triggerLastLocationFetch() ", e);
        }
    }

    public void onComplete(@NonNull Task<Location> task) {
        if (task != null) {
            try {
                Location location = (Location) task.getResult();
                if (location != null) {
                    this.geoLocation = new GeoLocation(location.getLatitude(), location.getLongitude());
                } else {
                    this.geoLocation = new GeoLocation(0.0d, 0.0d);
                }
                switch (this.mode) {
                    case 1:
                        setGeoFenceInternal();
                        return;
                    case 2:
                        geoFenceHitInternal();
                        return;
                    case 3:
                        updateFenceAndLocationInternal();
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                Logger.f("LocationHandlerImpl: onComplete()", e);
            }
        }
    }

    private void geoFenceHitInternal() {
        try {
            if (this.geoFenceIntent != null) {
                GeofencingEvent fromIntent = GeofencingEvent.fromIntent(this.geoFenceIntent);
                StringBuilder stringBuilder;
                if (fromIntent == null) {
                    Logger.e("LocationHandlerImpl : Null geo fence transition event");
                } else if (fromIntent.hasError()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("LocationHandlerImpl : Received geo fence transition intent with error");
                    stringBuilder.append(GeofenceStatusCodes.getStatusCodeString(fromIntent.getErrorCode()));
                    Logger.e(stringBuilder.toString());
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("LocationHandlerImpl geoFenceHitInternal() triggering Fence :");
                    stringBuilder.append(fromIntent.getTriggeringGeofences().toString());
                    Logger.v(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("LocationHandlerImpl geoFenceHitInternal() transition: ");
                    stringBuilder.append(fromIntent.getGeofenceTransition());
                    Logger.v(stringBuilder.toString());
                    Logger.v("LocationHandlerImpl geoFenceHitInternal() : Received geo fence transition intent");
                    int geofenceTransition = fromIntent.getGeofenceTransition();
                    if (!(geofenceTransition == 1 || geofenceTransition == 2)) {
                        if (geofenceTransition != 4) {
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("LocationHandlerImpl : geoFenceHitInternal() : Transition type was not in our interest: ");
                            stringBuilder2.append(geofenceTransition);
                            Logger.e(stringBuilder2.toString());
                        }
                    }
                    List<Geofence> triggeringGeofences = fromIntent.getTriggeringGeofences();
                    if (!(triggeringGeofences == null || triggeringGeofences.isEmpty())) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("LocationHandlerImpl : geoFenceHitInternal() received geo fences count: ");
                        stringBuilder3.append(triggeringGeofences.size());
                        Logger.v(stringBuilder3.toString());
                        for (Geofence geofence : triggeringGeofences) {
                            StringBuilder stringBuilder4 = new StringBuilder();
                            stringBuilder4.append("LocationHandlerImpl : geoFenceHitInternal() registering geo fencing hit for GeoId: ");
                            stringBuilder4.append(geofence.getRequestId());
                            Logger.v(stringBuilder4.toString());
                            HashMap buildRequestParams = buildRequestParams(geofenceTransition, geofence, this.geoLocation);
                            Context context = this.context;
                            StringBuilder stringBuilder5 = new StringBuilder();
                            stringBuilder5.append(MoEUtils.getAPIRoute(this.context));
                            stringBuilder5.append(LocationConstants.API_ENDPOINT_GEOFENCEHIT);
                            MoEDispatcher.getInstance(this.context).addTaskToQueue(new GeoTask(context, stringBuilder5.toString(), buildRequestParams, TASK_TYPE.GEOFENCE_HIT));
                            trackGeoFenceHitEvent(geofenceTransition, geofence, this.geoLocation);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.f("LocationHandlerImpl : geoFenceHitInternal()", e);
        }
    }

    private void setGeoFenceInternal() {
        try {
            ArrayList parseGeoFences = parseGeoFences();
            if (parseGeoFences == null || parseGeoFences.isEmpty()) {
                Logger.v("LocationHandlerImpl: setGeoFenceInternal(): no fences to set");
            }
            List savedGeoIds = getSavedGeoIds();
            if (savedGeoIds != null) {
                Logger.v("LocationHandlerImpl: setGeoFenceInternal(): Removing all existing geo fences");
                getGeoFencingClient().removeGeofences(savedGeoIds);
            }
            if (parseGeoFences.isEmpty()) {
                Logger.v("LocationHandlerImpl: setGeoFenceInternal(): No new geo fences found");
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            int size = parseGeoFences.size();
            for (int i = 0; i < size; i++) {
                stringBuilder.append(((Geofence) parseGeoFences.get(i)).getRequestId());
                if (i < size - 2) {
                    stringBuilder.append(";");
                }
            }
            ConfigurationProvider.getInstance(this.context).saveGeoIDList(stringBuilder.toString());
            PendingIntent service = PendingIntent.getService(this.context, 0, new Intent(this.context, GeofenceIntentService.class), 134217728);
            Builder builder = new Builder();
            builder.addGeofences(parseGeoFences);
            try {
                getGeoFencingClient().addGeofences(builder.build(), service);
            } catch (Exception e) {
                Logger.f("LocationHandlerImpl: setGeoFenceInternal()", e);
            }
        } catch (Exception e2) {
            Logger.f("LocationHandlerImpl: setGeoFenceInternal() ", e2);
        }
    }

    private void updateFenceAndLocationInternal() {
        HashMap hashMap = new HashMap();
        if (!ConfigurationProvider.getInstance(this.context).isTrackLocationProhibited()) {
            updateLastKnownLocation();
        }
        if (!ConfigurationProvider.getInstance(this.context).isSetGeoFenceProhibited()) {
            GeoLocation lastKnownUserLocation = ConfigurationProvider.getInstance(this.context).getLastKnownUserLocation();
            if (lastKnownUserLocation == null) {
                lastKnownUserLocation = new GeoLocation(0.0d, 0.0d);
            }
            hashMap.put(LocationConstants.PARAM_LAT, Double.toString(lastKnownUserLocation.latitude));
            hashMap.put(LocationConstants.PARAM_LNG, Double.toString(lastKnownUserLocation.longitude));
            MoEDispatcher instance = MoEDispatcher.getInstance(this.context);
            Context context = this.context;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MoEUtils.getAPIRoute(this.context));
            stringBuilder.append(LocationConstants.API_ENDPOINT_GEOFENCES);
            instance.addTaskToQueue(new GeoTask(context, stringBuilder.toString(), hashMap, TASK_TYPE.GET_GEOFENCE));
        }
        this.isLocationSynced = true;
    }

    private void updateLastKnownLocation() {
        Logger.v("LocationHandlerImpl: inside updateLastKnownLocation()");
        if (!this.geoLocation.equals(ConfigurationProvider.getInstance(this.context).getLastKnownUserLocation())) {
            ConfigurationProvider.getInstance(this.context).storeLastKnownLocation(this.geoLocation);
            MoEHelper.getInstance(this.context).setUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_LOCATION, this.geoLocation);
        }
    }

    private ArrayList<Geofence> parseGeoFences() {
        if (!TextUtils.isEmpty(this.geoResponse)) {
            try {
                JSONArray jSONArray = new JSONObject(this.geoResponse).getJSONArray(LocationConstants.RESPONSE_ATTR_FENCES_INFO);
                int length = jSONArray.length();
                ArrayList arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String string = jSONObject.getString(LocationConstants.RESPONSE_ATTR_TRANSITION_TYPE);
                        if (!TextUtils.isEmpty(string)) {
                            int i2 = string.equals(LocationConstants.TRANSITION_TYPE_EXIT) ? 2 : string.equals(LocationConstants.TRANSITION_TYPE_DWELL) ? 4 : 1;
                            Geofence.Builder transitionTypes = new Geofence.Builder().setRequestId(getGeoId(jSONObject)).setCircularRegion(jSONObject.getDouble(LocationConstants.PARAM_LAT), jSONObject.getDouble(LocationConstants.PARAM_LNG), (float) jSONObject.getDouble(LocationConstants.RESPONSE_ATTR_DISTANCE)).setExpirationDuration(-1).setTransitionTypes(i2);
                            if (jSONObject.has(LocationConstants.RESPONSE_ATTR_FENCE_LDELAY)) {
                                transitionTypes.setLoiteringDelay(Integer.parseInt(jSONObject.getString(LocationConstants.RESPONSE_ATTR_FENCE_LDELAY)));
                            }
                            if (jSONObject.has("expiry")) {
                                transitionTypes.setExpirationDuration(Long.parseLong(jSONObject.getString("expiry")));
                            }
                            if (jSONObject.has(LocationConstants.RESPONSE_ATTR_FENCE_RESPONSOVENESS)) {
                                transitionTypes.setNotificationResponsiveness(Integer.parseInt(LocationConstants.RESPONSE_ATTR_FENCE_RESPONSOVENESS));
                            }
                            arrayList.add(transitionTypes.build());
                        }
                    } catch (Exception e) {
                        Logger.f("Location: parseFencesInfo() - INNER", e);
                    }
                }
                return arrayList;
            } catch (Exception e2) {
                Logger.f("LocationHandlerImpl: parseFencesInfo()", e2);
            }
        }
        return null;
    }

    private HashMap<String, String> buildRequestParams(int i, Geofence geofence, GeoLocation geoLocation) {
        HashMap hashMap = new HashMap();
        String transitionString = getTransitionString(i);
        if (!TextUtils.isEmpty(transitionString)) {
            hashMap.put(LocationConstants.RESPONSE_ATTR_TRANSITION_TYPE, transitionString);
        }
        if (geoLocation != null) {
            hashMap.put(LocationConstants.CURRENT_LATITUDE, String.valueOf(geoLocation.latitude));
            hashMap.put(LocationConstants.CURRENT_LONGITUDE, String.valueOf(geoLocation.longitude));
        }
        hashMap.put(LocationConstants.PARAM_GEOIDS, getGeoIdFromRequestId(geofence.getRequestId()));
        return hashMap;
    }

    @Nullable
    private List<String> getSavedGeoIds() {
        String geoIDList = ConfigurationProvider.getInstance(this.context).getGeoIDList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LocationHandlerImpl: setGeoFenceInternal(): Existing fences: ");
        stringBuilder.append(geoIDList);
        Logger.v(stringBuilder.toString());
        if (TextUtils.isEmpty(geoIDList)) {
            return null;
        }
        if (geoIDList.contains(";")) {
            return Arrays.asList(geoIDList.split(";"));
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(geoIDList);
        return arrayList;
    }

    private boolean isSyncRequired(Context context) {
        long lastInAppupdate = ConfigurationProvider.getInstance(context).getLastInAppupdate() + 900000;
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Location: isSyncRequired: Next server sync will happen in ");
        stringBuilder.append((lastInAppupdate - currentTimeMillis) / 1000);
        stringBuilder.append(" seconds");
        Logger.v(stringBuilder.toString());
        return !this.isLocationSynced || lastInAppupdate < currentTimeMillis;
    }

    private String getGeoId(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("geoId");
        if (!jSONObject.has("cid")) {
            return string;
        }
        String string2 = jSONObject.getString("cid");
        return (TextUtils.isEmpty(string2) || string2.equals("null")) ? string : string.concat(":").concat(string2);
    }

    private void trackGeoFenceHitEvent(int i, Geofence geofence, GeoLocation geoLocation) {
        PayloadBuilder payloadBuilder = new PayloadBuilder();
        String campaignIdFromRequestId = getCampaignIdFromRequestId(geofence.getRequestId());
        if (!TextUtils.isEmpty(campaignIdFromRequestId)) {
            payloadBuilder.putAttrString("campaign_id", campaignIdFromRequestId);
            payloadBuilder.putAttrLocation(LocationConstants.EVENT_ATTRIBUTE_TRIGGER_LOCATION, geoLocation);
            String transitionString = getTransitionString(i);
            if (!TextUtils.isEmpty(transitionString)) {
                payloadBuilder.putAttrString(LocationConstants.EVENT_ATTRIBUTE_TRANSITION_TYPE, transitionString);
            }
            transitionString = getGeoIdFromRequestId(geofence.getRequestId());
            if (!TextUtils.isEmpty(transitionString)) {
                payloadBuilder.putAttrString(LocationConstants.EVENT_ATTRIBUTE_GEO_ID, transitionString);
            }
            MoEHelper.getInstance(this.context).trackEvent(LocationConstants.EVENT_GEO_FENCE_HIT, payloadBuilder.build());
        }
    }

    @Nullable
    private String getCampaignIdFromRequestId(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.contains(":")) {
                String[] split = str.split(":");
                if (!(split == null || split.length == 0)) {
                    return split[split.length - 1];
                }
            }
        } catch (Exception e) {
            Logger.f("LocationHandlerImpl: getCampaignIdFromRequestId() ", e);
        }
        return null;
    }

    @Nullable
    private String getGeoIdFromRequestId(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!str.contains(":")) {
                return str;
            }
            String[] split = str.split(":");
            if (split != null) {
                return split[0];
            }
            return null;
        } catch (Exception e) {
            Logger.f("LocationHandlerImpl getGeoIdFromRequestId() ", e);
        }
    }
}
