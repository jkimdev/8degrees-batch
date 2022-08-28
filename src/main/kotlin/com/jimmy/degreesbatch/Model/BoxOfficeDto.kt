package com.jimmy.degreesbatch.Model

import kotlin.properties.Delegates

class BoxOfficeDto {
    lateinit var mt20id: String
    var rnum: Int? = 0
    lateinit var prfnm: String
    lateinit var cate: String
    lateinit var poster: String
    lateinit var prfpd: String
    lateinit var prfplcnm: String
    var seatcnt: Int? = 0
    var area: String? = ""
}