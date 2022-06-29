package com.aksoyhasan.insider.useCase.errors

import com.aksoyhasan.insider.error.InsiderError

interface ErrorUseCase {
    fun getError(errorCode: Int): InsiderError
}
