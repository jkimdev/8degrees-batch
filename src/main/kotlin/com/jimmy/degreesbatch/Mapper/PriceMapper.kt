package com.jimmy.degreesbatch.Mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface PriceMapper {
    fun insertPrice(performanceId: String, price: String)
    fun deletePrice()
}