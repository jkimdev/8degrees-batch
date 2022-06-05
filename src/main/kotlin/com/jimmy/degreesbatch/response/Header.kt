package com.jimmy.degreesbatch.response

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class Header {
    private val resultCode: String? = null
    private val resultMsg: String? = null
}
