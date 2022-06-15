package com.jimmy.degreesbatch.Model

import lombok.*

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

class PerformanceDto {
    lateinit var mt20id: String
    lateinit var prfnm: String
}