package com.wetmarket.staff.retrofit;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;


import com.google.gson.Gson;
import com.wetmarket.staff.ApplicationClass;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@SuppressWarnings("unchecked")
public class ApiTask {
    private ApiCallInterface callapi;
    Context context;

    public ApiTask(ApplicationClass context) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        this.context = context;
        callapi = context.getRetrofitInstance().create(ApiCallInterface.class);

    }


    @SuppressLint("CheckResult")
    public void sendRequest(String body, HashMap<String, String> param, int reqCode, DisposableCallback apiCallback) {

        if (reqCode == ApiCallInterface.REGISTER) {
            callapi.register(param).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(apiCallback);
        }

    }



    public static RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    private MultipartBody.Part createMultipartBody(String paramName, String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            try {
                //File compressedImageFile = new Compressor(context).compressToFile(file);

                RequestBody requestBody = createRequestBody(file);
                return MultipartBody.Part.createFormData(paramName, file.getName(), requestBody);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    private RequestBody createRequestBody(File file) {
        return RequestBody.create(MediaType.parse("image/*"), file);
    }


    private MultipartBody.Part[] createDynamicMultipartArrayBody(List<String> multipleFilePath, String key) {

        MultipartBody.Part[] imagesList = new MultipartBody.Part[multipleFilePath.size()];

        for (int index = 0; index < multipleFilePath.size(); index++) {
            if (!multipleFilePath.get(index).equals("")) {
                File file = new File(multipleFilePath.get(index));
                RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file);
                imagesList[index] = MultipartBody.Part.createFormData(key + "[" + index + "]", file.getName(), surveyBody);

            } else {
                RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), "");
                imagesList[index] = MultipartBody.Part.createFormData(key + "[" + index + "]", "", surveyBody);

            }

        }
        return imagesList;
    }

    private MultipartBody.Part getImagePart(String name, File file) {
        RequestBody requestFile =
                RequestBody.create(MultipartBody.FORM, file);
        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);

    }


}

