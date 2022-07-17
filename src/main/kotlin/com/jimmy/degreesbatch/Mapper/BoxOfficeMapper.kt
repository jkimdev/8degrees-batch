package com.jimmy.degreesbatch.Mapper

import com.jimmy.degreesbatch.Model.BoxOfficeDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BoxOfficeMapper {
    fun insertBoxOffice(vo: BoxOfficeDto)
    fun deleteBoxOffice()
}