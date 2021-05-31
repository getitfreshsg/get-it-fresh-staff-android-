package com.wetmarket.staff.retrofit;


import com.wetmarket.staff.Model.HandoverItemModel;
import com.wetmarket.staff.retrofit.model.BaseModel;
import com.wetmarket.staff.retrofit.model.LableModel;
import com.wetmarket.staff.retrofit.model.LanguageModel;
import com.wetmarket.staff.retrofit.model.OrderModel;
import com.wetmarket.staff.retrofit.model.OrderStatusModel;
import com.wetmarket.staff.retrofit.model.UserModel;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


public interface ApiCallInterface {
    int TOKEN_EXPIRED = 401;
    int LOGIN = 1;
    int LOGOUT = 2;
    int GET_PROFILE = 3;
    int UPDATE_PROFILE = 4;
    int ORDER_LIST = 5;
    int ORDER_STATUS_LIST = 6;
    int GET_LABEL = 7;
    int GET_LANGUAGE = 8;
    int ORDER_ACCEPT = 9;
    int OUT_DELIVERY_ORDER = 10;
    int HAND_OVER_ITEM = 11;


    @POST(WebAPI.LOGIN)
    @FormUrlEncoded
    Observable<UserModel> login(@FieldMap HashMap<String, String> map);

    @POST(WebAPI.LOGOUT)
    @FormUrlEncoded
    Observable<BaseModel> logout(@FieldMap HashMap<String, String> map);

    @GET(WebAPI.GET_PROFILE)
    Observable<UserModel> getProfile();

    @POST(WebAPI.UPDATE_PROFILE)
    @Multipart
    Observable<BaseModel> updateProfile(@PartMap HashMap<String, RequestBody> map, @Part MultipartBody.Part image);

    @POST(WebAPI.ORDER_LIST)
    @FormUrlEncoded
    Observable<OrderModel> orderList(@FieldMap HashMap<String, String> map);

    @POST(WebAPI.OUT_DELIVERY_ORDER)
    @FormUrlEncoded
    Observable<OrderModel> outOrderList(@FieldMap HashMap<String, String> map);

    @POST(WebAPI.ORDER_ACCEPT)
    @FormUrlEncoded
    Observable<BaseModel> acceptOrder(@FieldMap HashMap<String, String> map);

    @POST(WebAPI.ORDER_STATUS_LIST)
    @FormUrlEncoded
    Observable<OrderStatusModel> getStatusList(@FieldMap HashMap<String, String> map);

    @POST(WebAPI.HAND_OVER_ITEM)
    Observable<BaseModel> handOverItem(@Body HandoverItemModel map);

    @GET(WebAPI.GET_LANGUAGE)
    Observable<LanguageModel> getLanguage();

    @POST(WebAPI.GET_LABEL)
    @FormUrlEncoded
    Observable<LableModel> geLablel(@FieldMap HashMap<String, String> map);


}
