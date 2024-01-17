package com.javichaques.core.domain.usecase.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.javichaques.core.common.Constants
import com.javichaques.core.common.Dispatcher
import com.javichaques.core.common.RUDispatchers
import com.javichaques.core.data.repository.users.UsersRepository
import com.javichaques.core.model.UserDO
import com.javichaques.core.model.pagination.PageKey
import com.javichaques.core.model.pagination.PaginationData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUsersUseCase
    @Inject
    constructor(
        @Dispatcher(RUDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
        private val usersRepository: UsersRepository,
    ) {
        suspend operator fun invoke() =
            withContext(ioDispatcher) {
                return@withContext Pager(
                    PagingConfig(
                        pageSize = Constants.DEFAULT_PAGE_SIZE,
                    ),
                ) {
                    object : PagingSource<PageKey, UserDO>() {
                        override suspend fun load(params: LoadParams<PageKey>): LoadResult<PageKey, UserDO> {
                            return try {
                                val pagination =
                                    PaginationData(
                                        seed = params.key?.seed,
                                        page = params.key?.page,
                                        results = params.loadSize,
                                    )

                                val result =
                                    usersRepository.getUsers(
                                        pagination = pagination,
                                    )

                                result.fold({
                                    LoadResult.Error(it)
                                }, {
                                    LoadResult.Page(
                                        data = it.items,
                                        prevKey = it.getNextKey(),
                                        nextKey = it.getPreviousKey(),
                                    )
                                })
                            } catch (e: Exception) {
                                Timber.e(e)
                                LoadResult.Error(e)
                            }
                        }

                        override fun getRefreshKey(state: PagingState<PageKey, UserDO>): PageKey? {
                            return null
                        }
                    }
                }.flow
            }
    }
