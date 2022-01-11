package com.mesutdurmuser.artbooktestingexample.repo

import androidx.lifecycle.LiveData
import com.mesutdurmuser.artbooktestingexample.model.ImageResponse
import com.mesutdurmuser.artbooktestingexample.roomdb.Art
import com.mesutdurmuser.artbooktestingexample.util.Resource

//Bu interface'imizi ArtRepository'e bir arayüz olması için oluşturduk. Orada aşağıdaki fonksiyonları override edip DAO muza ve retrofit işlemlerimize bağlayacağız..

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    //Burada suspend fonksiyon kullanmadık çünkü livedata zaten asenkron çalışmaktadır.
    fun getArt() : LiveData<List<Art>>

    //Burada util paketindeki resource sınıfımızı kullandık böylelikle ImageResponce sonuçlarını takip edeceğiz.
    suspend fun searchImage(imageString: String) : Resource<ImageResponse>
}