package com.wetmarket.staff.retrofit;

public interface ParametersInterface {


    enum LOGIN {
        email, password, deviceId, FCMId, type;

    }

    enum ORDER_LIST {
        wetmarket_id, page, perPage, search_text, order_status, date,order_id;

    }


}
