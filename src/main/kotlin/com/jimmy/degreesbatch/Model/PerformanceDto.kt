package com.jimmy.degreesbatch.Model

import lombok.*

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class PerformanceDto {
    private var mt20id: String = "";
    private var prfnm: String = "";
}