package ru.samtakot.rttest.domain

import android.icu.util.Calendar

class TimestampHolderImpl: TimestampHolder{

    override val timestampSeconds: Long
        get() = System.currentTimeMillis()/1000
}