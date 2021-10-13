package com.alamin.kotlin_retrofit_coroutines

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object {
        var retrofit : Retrofit? = null;
        val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
        fun getRetrofitInstance():Retrofit{
            if (retrofit!=null){
                return retrofit as Retrofit;
            } else{
              retrofit = Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                  .build();
                return retrofit as Retrofit;
            }
        }
    }
}