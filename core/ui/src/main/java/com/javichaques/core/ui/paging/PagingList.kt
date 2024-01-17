package com.javichaques.core.ui.paging

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

fun <T> pagingList(
    items: LazyPagingItems<*>,
    onRefresh: () -> Unit = {},
    onEmpty: () -> Unit = {},
    onLoading: () -> Unit = {},
    onError: (error: Throwable) -> Unit = {},
    content: () -> Unit,
) {
    when (val state = items.loadState.refresh) {
        is LoadState.Loading -> {
            onRefresh()
        }

        is LoadState.NotLoading -> {
            content()
        }

        is LoadState.Error -> {
            onError(state.error)
        }
    }

    when (val state = items.loadState.append) {
        is LoadState.Loading -> {
            onLoading()
        }

        is LoadState.NotLoading -> {
            if (state.endOfPaginationReached && items.itemCount <= 0) {
                onEmpty()
            }
        }

        is LoadState.Error -> {
            // Nothing
        }
    }
}
