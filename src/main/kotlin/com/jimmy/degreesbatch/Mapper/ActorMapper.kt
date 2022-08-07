package com.jimmy.degreesbatch.Mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface ActorMapper {
    fun insertActor(performanceId: String, name: String)
    fun deleteActor()
}