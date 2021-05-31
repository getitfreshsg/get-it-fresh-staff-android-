package com.wetmarket.staff;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.retrofit.ApiTask;
import com.wetmarket.staff.retrofit.WebAPI;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApplicationClass extends Application {


    private static ApplicationClass mInstance;
    private AppCompatActivity activity;
    private ApiTask apiTask;
    private Retrofit retrofit;

    public static ApplicationClass getmInstance() {
        return mInstance;
    }

    public static void setmInstance(ApplicationClass mInstance) {
        ApplicationClass.mInstance = mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }


    private static ApplicationClass get(Context context) {
        return (ApplicationClass) context.getApplicationContext();
    }

    public static ApplicationClass create(Context context) {
        return ApplicationClass.get(context);
    }


    /**
     * Call when application is close
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mInstance != null) {
            mInstance = null;
        }


    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }


    public Retrofit getRetrofitInstance() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, CertificateException, IOException {
        if (retrofit == null) {

            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(interceptor);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    String token = "";
                    String language = "";
                    token = new MyPref(ApplicationClass.this).getData(MyPref.Keys.token);
                    if (StringUtils.isNotEmpty(new MyPref(ApplicationClass.this).getData(MyPref.Keys.language))) {
                        if (new MyPref(ApplicationClass.this).getData(MyPref.Keys.language).equalsIgnoreCase("EN")) {
                            language = "1";
                        } else {
                            language = "2";
                        }
                    } else {
                        language = "1";
                    }

                    Log.e("Token-->", token);
                    Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).addHeader("language", language)
                            .build();
                    return chain.proceed(request);
                }
            });
            httpClient.writeTimeout(60, TimeUnit.SECONDS);
            httpClient.readTimeout(60, TimeUnit.SECONDS);
            httpClient.connectTimeout(60, TimeUnit.SECONDS);

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, "keystore_pass".toCharArray());
            sslContext.init(null, trustAllCerts, new SecureRandom());
            httpClient.sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });


            retrofit = new Retrofit.Builder()
                    .baseUrl(WebAPI.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public ApiTask getApiTask() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        if (apiTask == null) {
            apiTask = new ApiTask(this);
        }
        return apiTask;
    }

}
