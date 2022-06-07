package com.jimmy.degreesbatch.response

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class Body<T> {
    val dataType: String? = null
    val items: Items<T>? = null
    val pageNo: Int? = null
    val numOfRows: Int? = null
    val totalCount: Int? = null
}