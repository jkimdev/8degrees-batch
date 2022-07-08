package com.jimmy.degreesbatch.Model

import kotlin.properties.Delegates

class FacilityDto {
    lateinit var mt10id: String
    lateinit var fcltynm: String
    var mt13cnt by Delegates.notNull<Int>()
    lateinit var fcltychartr: String
    var opende by Delegates.notNull<Int>()
    var seatscale by Delegates.notNull<Int>()
    lateinit var telno: String
    lateinit var relateurl: String
    lateinit var adres: String
    var la: Double? = 0.0
    var lo: Double? = 0.0
}