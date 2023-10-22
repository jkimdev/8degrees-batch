package com.jimmy.degreesbatch.Mapper

import com.jimmy.degreesbatch.Model.DetailDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DetailMapper {
        fun insertDetail(performanceId: String, styurl: String)
        fun deleteDetail()
}