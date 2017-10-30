package com.classichu.classichu2.app;

/**
 * Created by louisgeek on 2017/2/22.
 */

public class FinalData {

    public static final class RequestCode {
        public static final int ITEM_SELECT_1 = 100;
        public static final int IMAGE_SELECT_1 = 101;
        public static final int PERMISSION = 1000;
    }

    public static final class BundleExtra {
        public static final String KEY_1 = "KEY_1";
        public static final String HIDE_BACK_BTN = "HIDE_BACK_BTN";
    }

    public static final class SharedP{
        public static final String HAS_OPENED_GUIDE = "HAS_OPENED_GUIDE";
        public static final String IS_LOAD_IMAGE = "IS_LOAD_IMAGE";
        public static final String IS_NIGHT = "IS_NIGHT";
    }

    public static final class NetWorkCodeMsg{

        public static final int UN_CONNECTED_CODE = -1;
        public static final String UN_CONNECTED_MSG = "网络未连接";

        public static final int ERROR_CODE = -2;
        public static final String ERROR_MSG = "数据连接异常";

    }
    public static final class ResponseCodeMsg{

        public static final int UNAUTHORIZED_RESPONSE_CODE = 401;
        public static final String UNAUTHORIZED_RESPONSE_MSG = "用户权限非法";

        public static final int FORBIDDEN_RESPONSE_CODE = 403;
        public static final String FORBIDDEN_RESPONSE_MSG = "禁止访问";

        public static final int NOT_FOUND_RESPONSE_CODE = 404;
        public static final String NOT_FOUND_RESPONSE_MSG = "未找到";

        public static final int METHOD_NOT_ALLOWED_RESPONSE_CODE = 405;
        public static final String METHOD_NOT_ALLOWED_RESPONSE_MSG = "请求方式错误";

        public static final int REQUEST_TIMEOUT_RESPONSE_CODE = 408;
        public static final String EQUEST_TIMEOUT_RESPONSE_MSG = "请求超时";

        public static final int INTERNAL_SERVER_ERROR_RESPONSE_CODE = 500;
        public static final String INTERNAL_SERVER_ERROR_RESPONSE_MSG = "内部服务器错误";

        public static final int BAD_GATEWAY_RESPONSE_CODE = 502;
        public static final String BAD_GATEWAY_RESPONSE_MSG = "错误的网关";

        public static final int SERVICE_UNAVAILABLE_RESPONSE_CODE = 503;
        public static final String SERVICE_UNAVAILABLE_RESPONSE_MSG = "临时的服务器维护";

        public static final int GATEWAY_TIMEOUT_RESPONSE_CODE = 504;
        public static final String GATEWAY_TIMEOUT_RESPONSE_MSG = "网关超时";

    }



}
