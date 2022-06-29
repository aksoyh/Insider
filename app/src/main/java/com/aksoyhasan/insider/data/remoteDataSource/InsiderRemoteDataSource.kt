package com.aksoyhasan.insider.data.remoteDataSource

import com.aksoyhasan.insider.data.Resource
import com.aksoyhasan.insider.data.dto.StarDTO

interface InsiderRemoteDataSource {
    suspend fun getStarObj(isSmall: Boolean): Resource<StarDTO>
}