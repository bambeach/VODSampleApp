package com.bjc.vodsampleapp

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bjc.vodsampleapp.api.Pac12DataClient
import com.bjc.vodsampleapp.data.VodItemWithNames
import com.bjc.vodsampleapp.data.VodPagingSource
import kotlinx.coroutines.flow.Flow

class VodListViewModel : ViewModel() {
//    val client: ApiNetworkClient = ApiNetworkClient()
//    val vodItems: LiveData<List<VodItem>> = client.getVodList().asLiveData()

    val client: Pac12DataClient = Pac12DataClient.create()
    val vodItems: Flow<PagingData<VodItemWithNames>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 100
        ),
        pagingSourceFactory = { VodPagingSource(client) }
    ).flow
}