package com.jimmy.degreesbatch.Mapper

import com.jimmy.degreesbatch.Model.PerformanceDto
import org.apache.ibatis.annotations.Mapper

@Mapper
public interface PerformanceMapper{

    public fun findById(id: String): List<PerformanceDto>

}