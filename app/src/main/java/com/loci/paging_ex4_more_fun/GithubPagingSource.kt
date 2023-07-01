package com.loci.paging_ex4_more_fun

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loci.paging_ex4_more_fun.data.Items
import com.loci.paging_ex4_more_fun.network.GitApi
import kotlinx.coroutines.delay


private const val STARTING_KEY = 1

class GithubPagingSource(private val gitApi: GitApi, private val query: String) :
    PagingSource<Int, Items>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {

        delay(1000)

        return try {
            val page = params.key ?: STARTING_KEY
            val response = gitApi.getData(query, page, params.loadSize)

            val data = response.items

            // 임시 에러 부분

            var count = 0

            if (page != 1) {
                count = (0..1).random()
            }

            Log.d("count", count.toString())

            if (count == 1) {
                throw java.lang.Exception("exception")
            }

            // 끝

            return LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + (params.loadSize / 30)
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

//        val page = params.key ?: STARTING_KEY
//        val response = gitApi.getData(query, page, params.loadSize)
//
//        val data = response.items
//        return LoadResult.Page(
//            data = data,
//            prevKey = if (page == 1) null else page - 1,
//            nextKey = page + (params.loadSize / 30)
//        )

    }

    override fun getRefreshKey(state: PagingState<Int, Items>): Int? {
        val anchorPosition = state.anchorPosition
        return anchorPosition?.let { it ->
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}