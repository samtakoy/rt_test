package ru.samtakot.rttest.domain

import android.icu.util.Calendar
import javax.inject.Inject

class TimestampHolderImpl @Inject constructor(): TimestampHolder{

    override val timestampSeconds: Long
        get() = System.currentTimeMillis()/1000
}