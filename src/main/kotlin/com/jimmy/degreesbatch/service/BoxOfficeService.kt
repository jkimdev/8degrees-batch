package com.jimmy.degreesbatch.service

import com.jimmy.degreesbatch.Mapper.BoxOfficeMapper
import com.jimmy.degreesbatch.Model.BoxOfficeDto
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class BoxOfficeService {
    @Autowired
    lateinit var mapper: BoxOfficeMapper

    fun insertBoxOffice(vo: BoxOfficeDto) {
        return mapper.insertBoxOffice(vo)
    }

    fun deleteBoxOffice() {
        return mapper.deleteBoxOffice()
    }
}