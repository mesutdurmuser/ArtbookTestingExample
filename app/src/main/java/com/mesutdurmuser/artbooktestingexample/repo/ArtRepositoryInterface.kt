package com.mesutdurmuser.artbooktestingexample.repo

import androidx.lifecycle.LiveData
import com.mesutdurmuser.artbooktestingexample.model.ImageResponse
import com.mesutdurmuser.artbooktestingexample.roomdb.Art
import com.mesutdurmuser.artbooktestingexample.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    //Burada suspend fonksiyon kullanmadık çünkü livedata zaten asenkron çalışmaktadır.
    fun getArt() : LiveData<List<Art>>

    //Burada util paketindeki resource sınıfımızı kullandık böylelikle ImageResponce sonuçlarını takip edeceğiz.
    suspend fun searchImage(imageString: String) : Resource<ImageResponse>
}