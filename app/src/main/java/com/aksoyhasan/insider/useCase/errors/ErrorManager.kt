package com.aksoyhasan.insider.useCase.errors

import com.aksoyhasan.insider.error.InsiderError
import com.aksoyhasan.insider.error.mapper.ErrorMapper
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): InsiderError {
        return InsiderError(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
