package com.wetmarket.staff.retrofit;


import com.wetmarket.staff.retrofit.model.BaseModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiCallInterface {
    int TOKEN_EXPIRED = 401;
    int REGISTER = 1;


    @POST(WebAPI.REGISTER)
    @FormUrlEncoded
    Observable<BaseModel> register(@FieldMap HashMap<String, String> map);


}
