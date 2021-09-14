package com.bjc.vodsampleapp

import androidx.lifecycle.ViewModel
import com.bjc.vodsampleapp.network.ApiNetworkClient

class VodListViewModel : ViewModel() {
    val client: ApiNetworkClient = ApiNetworkClient()
//    val vodItems: LiveData<List<VodItem>> = client.getVodList().asLiveData()
}