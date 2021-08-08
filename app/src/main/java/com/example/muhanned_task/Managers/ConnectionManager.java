package com.example.muhanned_task.Managers;

import com.example.muhanned_task.utilities.ApiConstants;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public class ConnectionManager {

    public void getHttps(String URl, Map<String, String> Params, final ApiCallHandler callResponse) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ROOT_API)
                .client(new ConnectionManager().getHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ResponseBody> call = service.GET(URl, Params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.isSuccessful()) {
                        if (response == null) {
                            return;
                        }
                        if (response.body() == null) {
                            return;
                        }
                        callResponse.onSuccess(response.body().string(), response.message());
                    }

                } catch (IOException e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callResponse.onFailure(t.toString());
            }
        });

    }
    public OkHttpClient getHeader() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptorJ = new HttpLoggingInterceptor();
        httpClient.addInterceptor(interceptorJ);
        httpClient.connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS).build();


        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                builder.method(original.method(), original.body());

                return chain.proceed(builder.build());
            }
        });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = httpClient.build();
        return client;

    }
    public interface APIService {
        @GET()
        public Call<ResponseBody> GET(@Url String url, @QueryMap Map<String, String> params);

        @FormUrlEncoded
        @POST
        public Call<ResponseBody> POST_FormUrlEncoded(@Url String url, @FieldMap Map<String, String> params);

    }
}
