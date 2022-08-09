package com.jimmy.degreesbatch.Mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface ScheduleMapper {
    fun insertSchedule(performanceId: String, schedule: String)
    fun deleteSchedule()
}