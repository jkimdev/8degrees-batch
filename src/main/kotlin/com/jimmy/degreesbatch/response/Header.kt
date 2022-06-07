package com.jimmy.degreesbatch.response

import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class Header {
    val resultCode: String? = null
    val resultMsg: String? = null
}
