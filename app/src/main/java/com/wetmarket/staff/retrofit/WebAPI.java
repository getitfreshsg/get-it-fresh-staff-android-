package com.wetmarket.staff.retrofit;

public class WebAPI {

    public static final String DOMAIN = "https://api.doralhealthconnect.com";
    public static final String BASE_URL = DOMAIN+"/api/";


    static final String REGISTER = "auth/register";
    static final String LOGIN = "auth/login";
    static final String DESIGNATION = "auth/designation";
    static final String FORGOT_PASSWORD = "auth/forgot";
    static final String STATES = "auth/states";
    static final String CITY = "auth/cities";
    static final String SEND_OTP = "auth/nexmo-send";
    static final String VERIFY_OTP = "auth/nexmo-verify";
    static final String APPOITNMENT = "auth/appointment";
    static final String CHANGE_PASSWORD = "auth/password/reset";

    static final String ACCPET_REQUEST = "clinician-request-accept";
    static final String REQUEST_LIST = "clinician-patient-request-list";
}
