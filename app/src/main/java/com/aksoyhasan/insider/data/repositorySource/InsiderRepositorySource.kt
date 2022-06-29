package com.aksoyhasan.insider.data.repositorySource

import com.aksoyhasan.insider.data.Resource
import com.aksoyhasan.insider.data.dto.StarDTO
import kotlinx.coroutines.flow.Flow

interface InsiderRepositorySource {
    fun getStarObj(isSmall: Boolean): Flow<Resource<StarDTO>>
}