package com.jimmy.degreesbatch.Mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProducerMapper {
    fun insertProducer(performanceId: String, producer: String)
    fun deleteProducer()
}