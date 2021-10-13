package com.alamin.kotlin_retrofit_coroutines

import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET("albums")
    suspend fun getAlbums() : Response<List<AlbumsItem>>
}