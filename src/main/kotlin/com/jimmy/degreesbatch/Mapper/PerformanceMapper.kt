package com.jimmy.degreesbatch.Mapper

import com.jimmy.degreesbatch.Model.FacilityDto
import com.jimmy.degreesbatch.Model.PerformanceDetailDto
import com.jimmy.degreesbatch.Model.PerformanceDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface PerformanceMapper {

    fun findById(): List<PerformanceDto>
    fun selectDistinctFC(): List<String>
    fun insertPerformance(vo: PerformanceDetailDto)
    fun insertActor(performanceId: String, name: String)
    fun deletePerformance()
    fun deleteActor()

}