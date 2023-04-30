package com.enesaksoy.ikotlinartbooktest.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enesaksoy.ikotlinartbooktest.model.ImageResponse
import com.enesaksoy.ikotlinartbooktest.roomdb.Art
import com.enesaksoy.ikotlinartbooktest.util.Resource

class ArtRepositoryTeste : ArtRepositoryInterface {

    private val arts = mutableListOf<Art>()
    private val artsliveData = MutableLiveData<List<Art>>(arts)
    override suspend fun addArt(art: Art) {
        arts.add(art)
        refreshdata()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshdata()
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsliveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }

    fun refreshdata(){
        artsliveData.postValue(arts)
    }
}