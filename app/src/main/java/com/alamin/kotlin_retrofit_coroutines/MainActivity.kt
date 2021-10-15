package com.alamin.kotlin_retrofit_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    lateinit var textView :TextView;

    var apiInterface:APIInterface?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text_view);
        apiInterface = APIClient
            .getRetrofitInstance()
            .create(APIInterface::class.java)
        //getAllAlbums();
        //getAlbumByQueryParameter();
        getAlbumByPathParameter(3);

    }

    private fun getAlbumByPathParameter(id: Int) {
        val responseLiveData:LiveData<Response<AlbumsItem>> = liveData {
            val response:Response<AlbumsItem> = apiInterface!!.getAlbumById(id)
            emit(response);
        }

        responseLiveData.observe(this, Observer {
            if (it.isSuccessful){
                val album = it.body();
                val result: String = "Album ID : ${album?.id}\n" +
                        "Name ID : ${album?.title}\n" +
                        "User ID : ${album?.userId}\n\n"
                textView.text = result;
            }
        })

    }

    private fun getAlbumByQueryParameter() {
       val responseLiveData : LiveData<Response<List<AlbumsItem>>> = liveData {
           val response :Response<List<AlbumsItem>> = apiInterface!!.getSortedAlbums(8)
           emit(response)
       }
        responseLiveData.observe(this, Observer {
            if (it.isSuccessful) {
                val albumList = it.body()
                if (albumList != null) {
                    for (album in albumList) {
                        Log.d(TAG, "onCreate: " + album.title);
                        val result: String = "Album ID : ${album.id}\n" +
                                "Name ID : ${album.title}\n" +
                                "User ID : ${album.userId}\n\n"
                        textView.append(result);
                    }
                }

            }
        })
    }

    private fun getAllAlbums() {
            val responseLiveData : LiveData<Response<List<AlbumsItem>>> = liveData {
                val response : Response<List<AlbumsItem>> = apiInterface!!.getAlbums();
                emit(response)
            }
            responseLiveData.observe(this, Observer {
                if (it.isSuccessful) {
                    val albumList = it.body()
                    if (albumList != null) {
                        for (album in albumList) {
                            Log.d(TAG, "onCreate: " + album.title);
                            val result: String = "Album ID : ${album.id}\n" +
                                    "Name ID : ${album.title}\n" +
                                    "User ID : ${album.userId}\n\n"
                            textView.append(result);
                        }
                    }

                }
            })
    }
}