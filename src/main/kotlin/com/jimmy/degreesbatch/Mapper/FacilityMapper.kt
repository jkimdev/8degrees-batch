package com.jimmy.degreesbatch.Mapper

import com.jimmy.degreesbatch.Model.FacilityDto

interface FacilityMapper {
    fun findById(): List<FacilityDto>
}