package ru.samtakot.rttest.domain.cache

enum class CacheStatus(
    val isNetworkBusy: Boolean
) {

    NOT_INITIALIZED(false),
    DATA_RETRIEVING(true),
    UNCOMPLETED(false),
    SYNCHRONIZED(false)



}