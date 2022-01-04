package com.mesutdurmuser.artbooktestingexample.model

data class ImageResponse(
    //fotoğraflarımız için kullanacağımız modelimiz
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int

)
