package com.mesutdurmuser.artbooktestingexample.repo

import androidx.lifecycle.LiveData
import com.mesutdurmuser.artbooktestingexample.api.RetrofitAPI
import com.mesutdurmuser.artbooktestingexample.model.ImageResponse
import com.mesutdurmuser.artbooktestingexample.roomdb.Art
import com.mesutdurmuser.artbooktestingexample.roomdb.ArtDao
import com.mesutdurmuser.artbooktestingexample.util.Resource
import java.lang.Exception
import javax.inject.Inject

//Application module de oluşturduğumuz fonksiyonlarda yapacağımız işlemleri viewmodel'ımızda direk kullanmıyoruz.
//Bunun başlıca sebebi yapacağımız testlerde en stabil ve sade ortamı oluşturmaktır.
//Burada Application sınıfımızdaki işlemleri kullanmak için bir ara sınıf oluşturduk.
//MVVM yapısında viewmodel ve repository olarak ayrılmamış şekildedir. Fakat profesyonel projelerde repository sınıfı bulunur.

//Testler yaparken threading işlemleri yapılmaması (Herşey mainthread'te çalışmalı) ve network işlemleri olmaması gibi iki prensip vardır.
//Repository sınıfı en başta bu sebepten oluşturulur.

//@Inject constructor diyerek constructorumuza eklediğimiz artDao ve retrofit Api aracılığı ile Appmodule de bulunan ilgili yerleri burada kullanabilirim.

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
) : ArtRepositoryInterface  {

    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {

            val response = retrofitAPI.imageSearch(imageString)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else{
                Resource.error("Error", null)
            }
        } catch (e: Exception){
            Resource.error("No data!", null)
        }
    }
}