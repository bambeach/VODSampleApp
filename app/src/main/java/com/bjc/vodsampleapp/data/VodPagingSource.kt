package com.bjc.vodsampleapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bjc.vodsampleapp.api.Pac12DataClient

private const val STARTING_PAGING_INDEX = 1

class VodPagingSource(
    private val client: Pac12DataClient
) : PagingSource<Int, VodItemWithNames>() {
    override fun getRefreshKey(state: PagingState<Int, VodItemWithNames>): Int? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id?.toInt() }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VodItemWithNames> {
        val page = params.key ?: STARTING_PAGING_INDEX

        return try {
            val response = client.getVodList(page, 10)
            val vodItems = response.programs
            val vodItemsWithNames = ArrayList<VodItemWithNames>(vodItems.size)
            for (vodItem in vodItems) {
                val schoolNames = vodItem.getSchoolNames(client)
                val sportName = vodItem.getSportsNames(client)
                val vodItemWithNames = VodItemWithNames(vodItem.id, vodItem.url, vodItem.title, vodItem.duration, sportName, schoolNames, vodItem.images)
                vodItemsWithNames.add(vodItemWithNames)
            }
            LoadResult.Page(
                data = vodItemsWithNames,
                prevKey = if (page == STARTING_PAGING_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}