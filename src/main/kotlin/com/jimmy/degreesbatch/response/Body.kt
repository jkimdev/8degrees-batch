package com.jimmy.degreesbatch.response

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class Body<T> {
    private val dataType: String? = null
    private val items: Items<T>? = null
    private val pageNo: Int? = null
    private val numOfRows: Int? = null
    private val totalCount: Int? = null
}