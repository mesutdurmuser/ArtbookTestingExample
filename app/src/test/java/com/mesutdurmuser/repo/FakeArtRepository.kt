package com.mesutdurmuser.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mesutdurmuser.artbooktestingexample.model.ImageResponse
import com.mesutdurmuser.artbooktestingexample.repo.ArtRepositoryInterface
import com.mesutdurmuser.artbooktestingexample.roomdb.Art
import com.mesutdurmuser.artbooktestingexample.util.Resource

class FakeArtRepository : ArtRepositoryInterface {

    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }

    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}