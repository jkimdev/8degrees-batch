package com.jimmy.degreesbatch.Mapper

import com.jimmy.degreesbatch.Model.FacilityDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface FacilityMapper {
    fun insertFacility(vo: FacilityDto)

    fun deleteFacility()
}