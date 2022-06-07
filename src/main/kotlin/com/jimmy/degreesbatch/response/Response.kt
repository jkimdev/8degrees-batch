package com.jimmy.degreesbatch.response

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class Response<T> {
    val header: Header? = null
    val body: Body<T>? = null
}