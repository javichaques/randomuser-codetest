package com.javichaques.core.model.pagination

data class PagedList<T>(
    val seed: String,
    val results: Int,
    val page: Int,
    val items: List<T>,
) {
    fun <R> map(transform: (T) -> R): PagedList<R> {
        return PagedList(
            seed = seed,
            results = results,
            page = page,
            items =
                items.map { item ->
                    transform(item)
                },
        )
    }

    fun getNextKey(seed: String) =
        PageKey(
            page = page + 1,
            seed = seed,
        )

    fun getPreviousKey(seed: String): PageKey? {
        return if (page > 1) {
            PageKey(
                page = page - 1,
                seed = seed,
            )
        } else {
            null
        }
    }
}
