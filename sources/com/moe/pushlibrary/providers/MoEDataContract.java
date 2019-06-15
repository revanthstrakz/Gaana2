package com.moe.pushlibrary.providers;

import android.content.Context;
import android.net.Uri;

public final class MoEDataContract {
    private static String AUTHORITY = null;
    private static final String AUTHORITY_PART = ".moengage.provider";
    public static final String QUERY_PARAMETER_LIMIT = "LIMIT";

    public interface BaseColumns {
        public static final int COLUMN_INDEX_GTIME = 1;
        public static final int COLUMN_INDEX_ID = 0;
        public static final String GTIME = "gtime";
        public static final String _ID = "_id";
    }

    interface BatchDataColumns extends BaseColumns {
        public static final String BATCHED_DATA = "batch_data";
    }

    interface CampaignListColumns extends BaseColumns {
        public static final String CAMPAIGN_ID = "campaign_id";
        public static final String CAMPAIGN_ID_TTL = "ttl";
        public static final int COLUMN_INDEX_CAMPAIGN_ID = 1;
        public static final int COLUMN_INDEX_CAMPAIGN_ID_TTL = 2;
    }

    interface DatapointColumns extends BaseColumns {
        public static final int COLUMN_INDEX_DETAILS = 2;
        public static final String DETAILS = "details";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, BaseColumns.GTIME, DETAILS};
    }

    interface InAppMessageColumns extends BaseColumns {
        public static final int COLUMN_INDEX_MSG_ALIGN_TYPE = 3;
        public static final int COLUMN_INDEX_MSG_AUTODISMISS_TIME = 15;
        public static final int COLUMN_INDEX_MSG_CAMPAIGN_ID = 2;
        public static final int COLUMN_INDEX_MSG_CANCELABLE = 16;
        public static final int COLUMN_INDEX_MSG_CONTAINER_STYLE = 20;
        public static final int COLUMN_INDEX_MSG_CONTENT = 17;
        public static final int COLUMN_INDEX_MSG_CONTEXT = 11;
        public static final int COLUMN_INDEX_MSG_HAS_ERRORS = 14;
        public static final int COLUMN_INDEX_MSG_INAPP_TYPE = 4;
        public static final int COLUMN_INDEX_MSG_IS_CLICKED = 13;
        public static final int COLUMN_INDEX_MSG_LAST_SHOWN = 12;
        public static final int COLUMN_INDEX_MSG_MAX_TIMES = 7;
        public static final int COLUMN_INDEX_MSG_MIN_DELAY = 6;
        public static final int COLUMN_INDEX_MSG_PERSISTENT = 9;
        public static final int COLUMN_INDEX_MSG_PRIORITY = 10;
        public static final int COLUMN_INDEX_MSG_SHOWN_COUNT = 8;
        public static final int COLUMN_INDEX_MSG_SHOW_ONLY_IN = 18;
        public static final int COLUMN_INDEX_MSG_STATUS = 19;
        public static final int COLUMN_INDEX_MSG_TTL = 5;
        public static final String DEFAULT_SORT_ORDER = "priority DESC, gtime DESC";
        public static final String MSG_ALIGN_TYPE = "align_type";
        public static final String MSG_AUTODISMISS_TIME = "auto_dismiss";
        public static final String MSG_CAMPAIGN_ID = "campaign_id";
        public static final String MSG_CANCELABLE = "cancelable";
        public static final String MSG_CONTAINER_STYLE = "dim_style";
        public static final String MSG_CONTENT = "content";
        public static final String MSG_CONTEXT = "context";
        public static final String MSG_HAS_ERRORS = "has_errors";
        public static final String MSG_INAPP_TYPE = "inapp_type";
        public static final String MSG_IS_CLICKED = "is_clicked";
        public static final String MSG_LAST_SHOWN = "last_shown";
        public static final String MSG_MAX_TIMES = "max_times";
        public static final String MSG_MIN_DELAY = "min_delay";
        public static final String MSG_PERSISTENT = "persistent";
        public static final String MSG_PRIORITY = "priority";
        public static final String MSG_SHOWN_COUNT = "shown_count";
        public static final String MSG_SHOW_ONLY_IN = "show_only_in";
        public static final String MSG_STATUS = "status";
        public static final String MSG_TTL = "ttl";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, BaseColumns.GTIME, "campaign_id", MSG_ALIGN_TYPE, MSG_INAPP_TYPE, "ttl", MSG_MIN_DELAY, "max_times", MSG_SHOWN_COUNT, "persistent", "priority", "context", MSG_LAST_SHOWN, MSG_IS_CLICKED, MSG_HAS_ERRORS, "auto_dismiss", MSG_CANCELABLE, "content", MSG_SHOW_ONLY_IN, "status", MSG_CONTAINER_STYLE};
    }

    interface MessageColumns extends BaseColumns {
        public static final int COLUMN_INDEX_MSG_CLICKED = 3;
        public static final int COLUMN_INDEX_MSG_DETAILS = 2;
        public static final int COLUMN_INDEX_MSG_TAG = 5;
        public static final int COLUMN_INDEX_MSG_TTL = 4;
        public static final String DEFAULT_SORT_ORDER = "gtime DESC";
        public static final String MSG_CLICKED = "msgclicked";
        public static final String MSG_DETAILS = "msg";
        public static final String MSG_TAG = "msg_tag";
        public static final String MSG_TTL = "msgttl";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, BaseColumns.GTIME, "msg", MSG_CLICKED, MSG_TTL, MSG_TAG};
    }

    interface UserAttributeColumns extends BaseColumns {
        public static final String ATTRIBUTE_NAME = "attribute_name";
        public static final String ATTRIBUTE_VALUE = "attribute_value";
        public static final int COLUMN_INDEX_ATTRIBUTE_NAME = 1;
        public static final int COLUMN_INDEX_ATTRIBUTE_VALUE = 2;
    }

    public static final class BatchDataEntity implements BatchDataColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.moe.batchdata";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.moe.batchdata";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, BatchDataColumns.BATCHED_DATA};

        public static Uri getContentUri(Context context) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("content://");
            stringBuilder.append(MoEDataContract.getAuthority(context));
            stringBuilder.append("/batchdata");
            return Uri.parse(stringBuilder.toString());
        }
    }

    public static final class CampaignListEntity implements CampaignListColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.moe.campaignlist";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.moe.campaignlist";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, "campaign_id", "ttl"};

        public static Uri getContentUri(Context context) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("content://");
            stringBuilder.append(MoEDataContract.getAuthority(context));
            stringBuilder.append("/campaignlist");
            return Uri.parse(stringBuilder.toString());
        }
    }

    public static final class DatapointEntity implements DatapointColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.moe.datapoint";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.moe.datapoints";

        public static Uri getContentUri(Context context) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("content://");
            stringBuilder.append(MoEDataContract.getAuthority(context));
            stringBuilder.append("/datapoints");
            return Uri.parse(stringBuilder.toString());
        }

        private DatapointEntity() {
        }
    }

    public static final class InAppMessageEntity implements InAppMessageColumns {
        static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.moe.inapp";
        static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.moe.inapps";

        public static Uri getContentUri(Context context) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("content://");
            stringBuilder.append(MoEDataContract.getAuthority(context));
            stringBuilder.append("/inapps");
            return Uri.parse(stringBuilder.toString());
        }

        private InAppMessageEntity() {
        }
    }

    public static final class MessageEntity implements MessageColumns {
        public static final int COLUMN_INDEX_MSG_CLICKED = 3;
        public static final int COLUMN_INDEX_MSG_DETAILS = 2;
        public static final int COLUMN_INDEX_MSG_TTL = 4;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.moe.message";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.moe.message";
        public static final String DEFAULT_SORT_ORDER = "gtime DESC";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, BaseColumns.GTIME, "msg", MessageColumns.MSG_CLICKED, MessageColumns.MSG_TTL};

        public static Uri getContentUri(Context context) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("content://");
            stringBuilder.append(MoEDataContract.getAuthority(context));
            stringBuilder.append("/messages");
            return Uri.parse(stringBuilder.toString());
        }

        private MessageEntity() {
        }
    }

    public static final class UserAttributeEntity implements UserAttributeColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.moe.userattributes";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.moe.userattributes";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, UserAttributeColumns.ATTRIBUTE_NAME, UserAttributeColumns.ATTRIBUTE_VALUE};

        public static Uri getContentUri(Context context) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("content://");
            stringBuilder.append(MoEDataContract.getAuthority(context));
            stringBuilder.append("/userattributes");
            return Uri.parse(stringBuilder.toString());
        }
    }

    private MoEDataContract() {
    }

    public static String getAuthority(Context context) {
        if (AUTHORITY == null) {
            String packageName = context.getPackageName();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(packageName);
            stringBuilder.append(AUTHORITY_PART);
            AUTHORITY = stringBuilder.toString();
        }
        return AUTHORITY;
    }
}
