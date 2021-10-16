package com.alamin.kotlin_retrofit_coroutines

import retrofit2.Response
import retrofit2.http.*

interface APIInterface {
    @GET("albums")
    suspend fun getAlbums() : Response<List<AlbumsItem>>

    //url like https://jsonplaceholder.typicode.com/albums?userId=8
    @GET("albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int) : Response<List<AlbumsItem>>

    //url like https://jsonplaceholder.typicode.com/albums/3

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path(value = "id") albumId:Int):Response<AlbumsItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album:AlbumsItem):Response<AlbumsItem>
}