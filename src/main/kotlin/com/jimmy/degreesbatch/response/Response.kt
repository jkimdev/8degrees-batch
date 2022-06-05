package com.jimmy.degreesbatch.response

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class Response<T> {
    private val header: Header? = null
    private val body: Body<T>? = null
}