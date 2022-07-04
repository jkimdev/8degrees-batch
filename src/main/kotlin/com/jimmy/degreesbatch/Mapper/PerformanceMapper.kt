package com.jimmy.degreesbatch.Mapper

import com.jimmy.degreesbatch.Model.PerformanceDetailDto
import com.jimmy.degreesbatch.Model.PerformanceDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface PerformanceMapper{

    fun findById(): List<PerformanceDto>

    fun insertPerformance(vo: PerformanceDetailDto);

    fun deletePerformance();

}