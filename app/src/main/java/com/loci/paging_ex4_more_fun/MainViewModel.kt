package com.loci.paging_ex4_more_fun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.loci.paging_ex4_more_fun.data.Items
import com.loci.paging_ex4_more_fun.network.GitApi
import com.loci.paging_ex4_more_fun.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MainViewModel(val str: String) : ViewModel() {

    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items: Flow<PagingData<Items>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            GithubPagingSource(api, str)
        }
    )
        .flow
        .cachedIn(viewModelScope)

}





