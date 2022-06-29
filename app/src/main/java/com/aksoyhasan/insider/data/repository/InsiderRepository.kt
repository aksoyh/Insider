package com.aksoyhasan.insider.data.repository

import com.aksoyhasan.insider.data.Resource
import com.aksoyhasan.insider.data.dto.StarDTO
import com.aksoyhasan.insider.data.remoteData.InsiderRemoteData
import com.aksoyhasan.insider.data.repositorySource.InsiderRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class InsiderRepository @Inject constructor(
    private val remoteData: InsiderRemoteData,
    private val ioDispatcher: CoroutineContext
) :
    InsiderRepositorySource {

    override fun getStarObj(isSmall: Boolean): Flow<Resource<StarDTO>> {
        return flow {
            emit(remoteData.getStarObj(isSmall))
        }.flowOn(ioDispatcher)
    }
}