package com.mesutdurmuser.artbooktestingexample.model

data class ImageResponse(
    //Ana modelimiz atacağımız sorgudan üç yanıt gelecek hits; ImageResult modelinde belirttiğimiz liste öğeleridir
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int

)
