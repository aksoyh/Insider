package com.aksoyhasan.insider.util

fun String?.emptyIfNull(): String {
    return if (this.isNullOrBlank()) String.EMPTY else this
}

val String.Companion.EMPTY: String
    get() = ""

fun String.appendPercentage(): String {
    return String.format("%s %s", this, "%".padStart(0))
}
