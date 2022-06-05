package com.jimmy.degreesbatch.response

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class Items<T> {
    private val item: List<T>? = null
}