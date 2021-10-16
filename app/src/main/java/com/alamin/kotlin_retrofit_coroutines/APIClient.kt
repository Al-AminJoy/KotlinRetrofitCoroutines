package com.alamin.kotlin_retrofit_coroutines

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    companion object {
        var retrofit : Retrofit? = null;
        val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
        fun getRetrofitInstance():Retrofit{

            val interceptor = HttpLoggingInterceptor()
                    .apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    }
            val client = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
                        .connectTimeout(30,TimeUnit.SECONDS)
                        .readTimeout(20,TimeUnit.SECONDS)
                        .writeTimeout(25,TimeUnit.SECONDS)

            }.build();

            if (retrofit!=null){
                return retrofit as Retrofit;
            }  else{
              retrofit = Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                  .build();
                return retrofit as Retrofit;
            }
        }
    }
}