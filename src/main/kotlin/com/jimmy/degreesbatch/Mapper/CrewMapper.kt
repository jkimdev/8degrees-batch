package com.jimmy.degreesbatch.Mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface CrewMapper {
    fun insertCrew(performanceId: String, crew: String)
    fun deleteCrew()
}