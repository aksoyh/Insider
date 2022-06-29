package com.aksoyhasan.insider.data.remoteData

import com.aksoyhasan.insider.BuildConfig
import com.aksoyhasan.insider.data.Resource
import com.aksoyhasan.insider.data.dto.StarDTO
import com.aksoyhasan.insider.data.enum.BigColor
import com.aksoyhasan.insider.data.enum.Brightness
import com.aksoyhasan.insider.data.enum.SmallColor
import com.aksoyhasan.insider.data.remoteDataSource.InsiderRemoteDataSource
import com.aksoyhasan.insider.error.NETWORK_ERROR
import com.aksoyhasan.insider.error.NO_INTERNET_CONNECTION
import com.aksoyhasan.insider.util.NetworkConnectivity
import java.io.IOException
import javax.inject.Inject

class InsiderRemoteData @Inject
constructor(
    private val networkConnectivity: NetworkConnectivity
) :
    InsiderRemoteDataSource {

    override suspend fun getStarObj(isSmall: Boolean): Resource<StarDTO> {
        return try {
            when (val response = processCall { isSmall }) {
                is StarDTO -> {
                    Resource.Success(data = response)
                }
                else -> {
                    Resource.DataError(errorCode = response as Int)
                }
            }
        } catch (e: Exception) {
            if (networkConnectivity.isConnected())
                Resource.DataError(NETWORK_ERROR)
            else
                Resource.DataError(NO_INTERNET_CONNECTION)
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Boolean): Any {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val size = BuildConfig.URL_FULL_ADDRESS.substring(48, 51)
            if (response) {
                return StarDTO(size.toLong().div(listOf(1,2,3,4,5,6,7,8,9).random().toLong()), SmallColor.values().random().toString(), Brightness.values().random().toString())
            } else {
                return StarDTO(size.toLong().plus(listOf(1,2,3,4,5,6,7,8,9).random().toLong()*50), BigColor.values().random().toString(), Brightness.values().random().toString())
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}