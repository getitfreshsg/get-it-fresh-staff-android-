package com.wetmarket.staff.retrofit;

public class WebAPI {

    public static final String DOMAIN = "http://151.106.113.63:9090";
    public static final String BASE_URL = DOMAIN + "/api/";
    static final String LOGIN = "staff/login";
    static final String LOGOUT = "staff/logout";
    static final String GET_PROFILE = "staff/profile";
    static final String UPDATE_PROFILE = "staff/update";
    static final String ORDER_LIST = "staff/order-list";
    static final String OUT_DELIVERY_ORDER = "staff/order-out-for-delivery";
    static final String ORDER_ACCEPT = "staff/order-accept";
    static final String HAND_OVER_ITEM = "staff/purchase-available-item";
    static final String ORDER_STATUS_LIST = "user/order-status-list";
    static final String GET_LANGUAGE = "user/get-language";
    static final String GET_LABEL = "user/get-label";

}
