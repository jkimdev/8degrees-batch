package com.jimmy.degreesbatch.Utility

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTime() = Calendar.getInstance()
fun Calendar.withoutDash(): String = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(this.time)
fun Calendar.yesterDay() = this.apply {
    add(Calendar.DATE, -1)
}
fun Calendar.after3Months() = this.apply {
    add(Calendar.MONTH, 3)
}