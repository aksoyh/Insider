package com.aksoyhasan.insider.error

class InsiderError(val code: Int, val description: String) {
    constructor(exception: Exception) : this(code = DEFAULT_ERROR, description = exception.message
            ?: "")
}

const val SHOW_SERVER_MESSAGE = 999
const val NO_INTERNET_CONNECTION = -1
const val NETWORK_ERROR = -2
const val DEFAULT_ERROR = -3
const val CHECK_YOUR_FIELDS = -103
const val SEARCH_ERROR = -104
const val COMMON_ERROR = 0
