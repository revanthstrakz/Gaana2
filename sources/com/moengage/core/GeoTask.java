package com.moengage.core;

import android.content.Context;
import android.text.TextUtils;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskResult;
import com.moengage.location.GeoManager;
import com.moengage.location.GeoManager.LocationHandler;
import com.moengage.location.GeoManager.TASK_TYPE;
import java.util.HashMap;

public class GeoTask extends SDKTask {
    String API;
    private TASK_TYPE geoTask;
    private HashMap<String, String> paramsMap;

    public String getTaskTag() {
        return "GEO_TASK";
    }

    public boolean isSynchronous() {
        return false;
    }

    public GeoTask(Context context, String str, HashMap<String, String> hashMap, TASK_TYPE task_type) {
        super(context);
        this.API = str;
        this.paramsMap = hashMap;
        this.geoTask = task_type;
    }

    public TaskResult execute() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("GeoTask : executing task ");
            stringBuilder.append(this.geoTask);
            Logger.v(stringBuilder.toString());
            if (ConfigurationProvider.getInstance(this.mContext).isAppEnabled()) {
                if (ConfigurationProvider.getInstance(this.mContext).isGeoEnabled()) {
                    switch (this.geoTask) {
                        case GEOFENCE_HIT:
                            APIManager.geoFenceHit(this.mContext, this.API, this.paramsMap);
                            break;
                        case GET_GEOFENCE:
                            String geoFences = APIManager.getGeoFences(this.mContext, this.API, this.paramsMap);
                            if (!TextUtils.isEmpty(geoFences)) {
                                LocationHandler handler = GeoManager.getInstance().getHandler(this.mContext);
                                if (handler != null) {
                                    handler.setGeoFences(this.mContext, geoFences);
                                    break;
                                }
                            }
                            break;
                        default:
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("GeoTask : Unknown Task ");
                            stringBuilder.append(this.geoTask);
                            Logger.e(stringBuilder.toString());
                            break;
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("GeoTask : completed execution ");
                    stringBuilder.append(this.geoTask);
                    Logger.v(stringBuilder.toString());
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            Logger.f("GeoTask: execute() ", e);
        }
    }
}
