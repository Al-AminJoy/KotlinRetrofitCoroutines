package com.alamin.kotlin_retrofit_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call : APIInterface = APIClient
            .getRetrofitInstance()
            .create(APIInterface::class.java)
        val responseLiveData : LiveData<Response<List<AlbumsItem>>> = liveData {
            val response : Response<List<AlbumsItem>> = call.getAlbums();
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            if (it.isSuccessful) {
                val albumList = it.body()
                if (albumList != null) {
                    for (album in albumList) {
                        Log.d(TAG, "onCreate: "+album.title);
                    }
                }

            }
        })
    }
}